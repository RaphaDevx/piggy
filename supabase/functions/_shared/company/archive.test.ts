import { webcrypto } from 'node:crypto';
import { handleCompanyEvidence, sourcePath, evidenceBytes, MAX_EVIDENCE_BYTES, type ArchiveDeps } from './archive';
Object.defineProperty(globalThis, 'crypto', {value:webcrypto, configurable:true});
const user='11111111-1111-4111-8111-111111111111', sid='aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa';
const png=new Uint8Array([137,80,78,71,13,10,26,10,0,1]);
function setup() {
 const deps:ArchiveDeps={
  authenticate:jest.fn(async()=>user),
  evidence:jest.fn(async()=>({submission_id:sid,company_id:'company',submitter_id:user,snapshot:{image_url:user+'/test.png'},image_state:'pending',image_key:null})),
  role:jest.fn(async()=>'member'), source:jest.fn(async()=>new Response(png)),
  put:jest.fn(async()=>{}),confirm:jest.fn(async()=>{}),imageURL:jest.fn(async()=>'https://signed.example/read'),supabaseURL:'https://piggy.supabase.co',
 };
 const call=(body:unknown={op:'archive',submission_id:sid})=>handleCompanyEvidence(new Request('https://local',{method:'POST',body:JSON.stringify(body)}),deps);
 return {deps,call};
}
test('rejects unauthenticated requests before reading evidence',async()=>{const {deps,call}=setup();deps.authenticate=async()=>null;expect((await call()).status).toBe(401);expect(deps.evidence).not.toHaveBeenCalled();});
test('outsider cannot archive or obtain image URL',async()=>{const {deps,call}=setup();deps.role=async()=>null;expect((await call()).status).toBe(403);expect((await call({op:'image',submission_id:sid})).status).toBe(403);expect(deps.source).not.toHaveBeenCalled();expect(deps.imageURL).not.toHaveBeenCalled();});
test('caller cannot select a source path',async()=>{const {deps,call}=setup();expect((await call({op:'archive',submission_id:sid,path:'evil'})).status).toBe(400);expect(deps.source).not.toHaveBeenCalled();});
test('source paths reject external hosts, foreign users and traversal',()=>{
 for(const raw of ['https://evil.example/'+user+'/x', 'another/x', user+'/../x', user+'/%2e%2e/x', 'https://piggy.supabase.co/storage/v1/object/public/receipts/other/x']) {
  expect(()=>sourcePath(raw,user,'https://piggy.supabase.co')).toThrow();
 }
 expect(sourcePath(`https://piggy.supabase.co/storage/v1/object/sign/receipts/${user}/a.jpg?token=x`,user,'https://piggy.supabase.co')).toEqual({bucket:'receipts',path:user+'/a.jpg'});
});
test('archive hashes actual bytes and confirms after upload',async()=>{const {deps,call}=setup();expect((await call()).status).toBe(200);const hash=Buffer.from(await webcrypto.subtle.digest('SHA-256',png)).toString('hex');expect(deps.put).toHaveBeenCalledWith(`company-receipts/company/${sid}/${hash}`,png,'image/png');expect(deps.confirm).toHaveBeenCalledWith(sid,`company-receipts/company/${sid}/${hash}`,hash);});
test('already archived retries do not reread mutable source',async()=>{const {deps,call}=setup();deps.evidence=async()=>({submission_id:sid,company_id:'c',submitter_id:user,snapshot:{},image_state:'archived',image_key:'company-receipts/c/s/h'});expect((await call()).status).toBe(200);expect(deps.source).not.toHaveBeenCalled();});
test('only submitter archives; managers can read archived evidence',async()=>{const {deps,call}=setup();deps.role=async()=>'admin';deps.evidence=async()=>({submission_id:sid,company_id:'c',submitter_id:'other',snapshot:{},image_state:'archived',image_key:'company-receipts/c/s/h'});expect((await call()).status).toBe(403);expect((await call({op:'image',submission_id:sid})).status).toBe(200);expect(deps.imageURL).toHaveBeenCalledWith('company-receipts/c/s/h');});
test('oversized and non-image responses are rejected',async()=>{await expect(evidenceBytes(new Response('text'))).rejects.toThrow();await expect(evidenceBytes(new Response(png,{headers:{'content-length':String(MAX_EVIDENCE_BYTES+1)}}))).rejects.toThrow();});
test('failed confirmation stays retryable and never reports success',async()=>{const {deps,call}=setup();deps.confirm=async()=>{throw new Error('db unavailable');};expect((await call()).status).toBe(503);});
