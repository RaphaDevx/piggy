import { createClient } from 'npm:@supabase/supabase-js@2';
import { createR2, r2ConfigFromEnv } from '../_shared/storage/r2.ts';
import { handleCompanyEvidence, type Evidence } from '../_shared/company/archive.ts';

const url = Deno.env.get('SUPABASE_URL')!;
const config = r2ConfigFromEnv(name => Deno.env.get(name));
const r2 = config ? createR2(config) : null;
const service = createClient(url,Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!, {auth:{persistSession:false}});
Deno.serve(req => handleCompanyEvidence(req, {
  supabaseURL:url,
  async authenticate(request) {
    const token = (request.headers.get('Authorization') ?? '').replace(/^Bearer\s+/i,'');
    if (!token || !r2) return null;
    const client = createClient(url,Deno.env.get('SUPABASE_ANON_KEY')!,{global:{headers:{Authorization:`Bearer ${token}`}},auth:{persistSession:false}});
    const {data,error} = await client.auth.getUser(); return error ? null : data.user?.id ?? null;
  },
  async evidence(id) {
    const {data,error} = await service.from('company_expense_snapshots').select('submission_id,company_id,submitter_id,snapshot,image_state,image_key').eq('submission_id',id).maybeSingle();
    if (error) throw error; return data as Evidence | null;
  },
  async role(company,user) {
    const {data,error} = await service.from('company_members').select('role').eq('company_id',company).eq('user_id',user).maybeSingle();
    if (error) throw error; return data?.role ?? null;
  },
  async source(bucket,path) {
    if (!r2) throw new Error('R2 missing');
    const options: RequestInit = {redirect:'error',signal:AbortSignal.timeout(30000)};
    const response = await fetch(await r2.presign('GET',`${bucket}/${path}`,60),options);
    if (response.status !== 404) return response;
    await response.body?.cancel();
    const {data,error} = await service.storage.from(bucket).createSignedUrl(path,60);
    if (error || !data) throw new Error('source missing');
    const legacy = new URL(data.signedUrl);
    if (legacy.origin !== new URL(url).origin) throw new Error('source host');
    return fetch(legacy, {...options,signal:AbortSignal.timeout(30000)});
  },
  async put(key,bytes,mime) { if (!r2) throw new Error('R2 missing'); await r2.put(key,bytes,mime); },
  async confirm(id,key,hash) {
    const {data,error} = await service.rpc('company_archive_complete',{p_submission_id:id,p_image_key:key,p_sha256:hash});
    if (error || data !== true) throw new Error('confirmation failed');
  },
  async imageURL(key) { if (!r2) throw new Error('R2 missing'); return r2.presign('GET',key,300); },
}));
