import { validateOwnPath } from '../storage/paths.ts';

export interface Evidence {
  submission_id: string; company_id: string; submitter_id: string;
  snapshot: { image_url?: string }; image_state: string; image_key: string | null;
}
export interface ArchiveDeps {
  authenticate(req: Request): Promise<string | null>;
  evidence(id: string): Promise<Evidence | null>;
  role(company: string, user: string): Promise<string | null>;
  source(bucket: string, path: string): Promise<Response>;
  put(key: string, bytes: Uint8Array, mime: string): Promise<void>;
  confirm(id: string, key: string, hash: string): Promise<void>;
  imageURL(key: string): Promise<string>;
  supabaseURL: string;
}
const cors = { 'Access-Control-Allow-Origin': '*', 'Access-Control-Allow-Headers': 'authorization, apikey, content-type, x-client-info' };
const json = (body: unknown, status = 200) => new Response(JSON.stringify(body), { status, headers: { ...cors, 'Content-Type': 'application/json' } });
const uuid = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;
export const MAX_EVIDENCE_BYTES = 15 * 1024 * 1024;

export function sourcePath(raw: string, user: string, base: string): {bucket: string; path: string} {
  let bucket = 'receipt-images', path = raw;
  if (raw.startsWith('https://')) {
    const url = new URL(raw), trusted = new URL(base);
    if (url.origin !== trusted.origin || url.username || url.password) throw new Error('source');
    const match = url.pathname.match(/^\/storage\/v1\/object\/(?:public|sign|authenticated)\/(receipts|receipt-images)\/(.+)$/);
    if (!match) throw new Error('source');
    bucket = match[1]; path = decodeURIComponent(match[2]);
  }
  if (path.includes('%') || validateOwnPath(user, path)) throw new Error('source');
  return { bucket, path };
}

export async function evidenceBytes(response: Response): Promise<{bytes: Uint8Array; mime: string}> {
  if (response.status !== 200 || !response.body) throw new Error('image');
  const reader = response.body.getReader();
  const parts: Uint8Array[] = []; let size = 0;
  try {
    if (Number(response.headers.get('content-length')) > MAX_EVIDENCE_BYTES) throw new Error('size');
    while (true) {
      const chunk = await reader.read(); if (chunk.done) break;
      size += chunk.value.byteLength;
      if (size > MAX_EVIDENCE_BYTES) throw new Error('size');
      parts.push(chunk.value);
    }
  } finally { await reader.cancel().catch(() => {}); }
  const bytes = new Uint8Array(size); let offset = 0;
  for (const part of parts) { bytes.set(part, offset); offset += part.length; }
  const png = [137,80,78,71,13,10,26,10].every((b,i) => bytes[i] === b);
  const jpeg = bytes[0] === 255 && bytes[1] === 216 && bytes[2] === 255;
  if (!png && !jpeg) throw new Error('image');
  return {bytes, mime: png ? 'image/png' : 'image/jpeg'};
}

export async function handleCompanyEvidence(req: Request, deps: ArchiveDeps): Promise<Response> {
  if (req.method === 'OPTIONS') return new Response('ok', {headers: cors});
  if (req.method !== 'POST') return json({error: 'Method not allowed'}, 405);
  try {
    const user = await deps.authenticate(req); if (!user) return json({error:'Bitte anmelden.'},401);
    const body = await req.json().catch(() => null);
    if (!body || !['archive','image'].includes(body.op) || !uuid.test(body.submission_id ?? '') || Object.keys(body).some(k => !['op','submission_id'].includes(k))) return json({error:'Ungültige Anfrage.'},400);
    const evidence = await deps.evidence(body.submission_id);
    if (!evidence) return json({error:'Kein Zugriff.'},403);
    const role = await deps.role(evidence.company_id, user);
    if (!role || (evidence.submitter_id !== user && !['admin','accountant'].includes(role))) return json({error:'Kein Zugriff.'},403);
    if (body.op === 'image') {
      if (evidence.image_state !== 'archived' || !evidence.image_key) return json({error:'Original noch nicht archiviert.'},409);
      return json({url: await deps.imageURL(evidence.image_key), expiresIn:300});
    }
    if (evidence.submitter_id !== user) return json({error:'Nur die einreichende Person kann das Original archivieren.'},403);
    if (evidence.image_state === 'archived') return json({archived:true});
    let source;
    try { source = sourcePath(evidence.snapshot.image_url ?? '', user, deps.supabaseURL); }
    catch { return json({error:'Originalpfad ist nicht zulässig.'},400); }
    const {bytes,mime} = await evidenceBytes(await deps.source(source.bucket,source.path));
    const digest = await crypto.subtle.digest('SHA-256',new Uint8Array(bytes).buffer);
    const hash = Array.from(new Uint8Array(digest),b => b.toString(16).padStart(2,'0')).join('');
    const key = `company-receipts/${evidence.company_id}/${evidence.submission_id}/${hash}`;
    await deps.put(key,bytes,mime);
    // Never delete after an ambiguous confirmation: the database may already have committed.
    await deps.confirm(evidence.submission_id,key,hash);
    return json({archived:true,sha256:hash});
  } catch {
    return json({error:'Archivierung nicht abgeschlossen. Erneut versuchen; keine Auszahlung ausgelöst.'},503);
  }
}
