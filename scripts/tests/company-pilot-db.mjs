/** Actual company foundation + hardening excerpt + pilot migration, isolated in PGlite. No network DB. */
import { readFileSync } from 'node:fs';
import { pathToFileURL } from 'node:url';
import assert from 'node:assert/strict';
import { createHash } from 'node:crypto';
const { PGlite } = await import(pathToFileURL(process.env.PIGGY_PGLITE_MODULE ?? '/tmp/piggy-duplicate-pg/node_modules/@electric-sql/pglite/dist/index.js'));
const sql = name => readFileSync(new URL('../../supabase/migrations/' + name, import.meta.url),'utf8');
const db = new PGlite();
const admin='11111111-1111-4111-8111-111111111111', member='22222222-2222-4222-8222-222222222222', outsider='33333333-3333-4333-8333-333333333333';
let passed=0;
async function as(user, role='authenticated') { await db.exec('RESET ROLE'); await db.query("SELECT set_config('request.jwt.claim.sub',$1,false)",[user ?? '']); await db.exec('SET ROLE '+role); }
const action = async (verb,input={}) => (await db.query('SELECT public.company_workspace($1,$2) AS result',[verb,input])).rows[0].result;
async function test(name, run) { await run(); passed++; console.log('PASS',name); }
async function denied(run) { await assert.rejects(run); }
try {
 await db.exec(readFileSync(new URL('./company-pilot.fixture.sql',import.meta.url),'utf8'));
 await db.exec(sql('20260708000001_company_foundation.sql'));
 const hardening=sql('20260930000001_security_hardening.sql');
 // Use the real helper definitions and company policies; exclude unrelated social/storage policies.
 const helpers=hardening.slice(hardening.indexOf('CREATE SCHEMA IF NOT EXISTS private;'), hardening.indexOf('CREATE OR REPLACE FUNCTION private.is_split_participant'));
 await db.exec(helpers);
 await db.exec(hardening.slice(hardening.indexOf('-- companies\n'),hardening.indexOf('-- receipt_items shared with me')));
 await db.exec(sql('20261127193728_company_expense_pilot.sql'));
 for(const id of [admin,member,outsider]) { await db.query('INSERT INTO auth.users VALUES($1)',[id]); await db.query('INSERT INTO public.profiles VALUES($1,$2)',[id,id===admin?'Admin':'Mitarbeitend']); }
 await as(admin);
 const company=await action('create',{name:'Pilot AG',currency:'CHF',meal_limit:30}); const cid=company.company_id;
 await test('company creation is atomic and creator is admin',async()=> { const list=await action('list'); assert.equal(list.companies[0].role,'admin'); assert.equal(list.companies[0].currency,'CHF'); });
 await test('invalid company rolls back without orphan rows',async()=> { await denied(()=>action('create',{name:'x'})); const list=await action('list'); assert.equal(list.companies.length,1); });
 await test('outsider cannot read workspace or grant themselves a role',async()=> { await as(outsider); await denied(()=>action('workspace',{company_id:cid})); await denied(()=>db.query('INSERT INTO company_members(company_id,user_id,role) VALUES($1,$2,$3)',[cid,outsider,'admin'])); });
 await test('invite joins only as member and duplicate join is idempotent',async()=> { await as(member); await action('join',{invite_code:company.invite_code,role:'admin'}); await action('join',{invite_code:company.invite_code}); assert.equal((await action('list')).companies[0].role,'member'); });
 await test('rotated invites are invalid and member cannot issue invitations',async()=> { await denied(()=>action('rotate_invite',{company_id:cid})); await as(admin); const next=await action('rotate_invite',{company_id:cid}); await as(outsider); await denied(()=>action('join',{invite_code:company.invite_code})); await as(admin); assert.notEqual(next.invite_code,company.invite_code); });
 await as(null,'anon'); await test('anonymous users cannot call workflow',()=>denied(()=>action('list')));
 await as(null,'service_role'); await test('service cannot impersonate an authenticated user without identity',()=>denied(()=>action('list')));
 await db.exec('RESET ROLE');
 const rid='aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa';
 await db.query("INSERT INTO receipts(id,user_id,receipt_date,store_name,total_amount,currency,image_url,notes,raw_ocr_text) VALUES($1,$2,'2026-11-27','Restaurant',24.50,'CHF',$3,'Private Notiz','Restaurant Total 24.50')",[rid,member,member+'/meal.jpg']);
 await as(member); const submitted=await action('submit',{company_id:cid,receipt_id:rid,category:'meal',note:'Mittagessen bei Kundentermin'}); const sid=submitted.submission_id;
 await test('snapshot excludes private notes and repeated submit keeps one claim',async()=> { const repeated=await action('submit',{company_id:cid,receipt_id:rid,category:'meal',note:'Andere Notiz'}); assert.equal(repeated.submission_id,sid); const w=await action('workspace',{company_id:cid}); assert.equal(w.submissions.length,1); assert.equal(w.submissions[0].snapshot.total_amount,24.5); assert.equal(w.submissions[0].snapshot.notes,undefined); assert.equal(w.submissions[0].events.length,1); });
 await test('client cannot edit history, self-approve, or mark reimbursement paid',async()=> {
   await denied(()=>action('review',{company_id:cid,submission_id:sid,decision:'approved'}));
   await denied(()=>db.query("UPDATE expense_submissions SET status='reimbursed' WHERE id=$1",[sid]));
   await denied(()=>action('confirm_payment',{company_id:cid,submission_id:sid}));
   await denied(()=>db.query("UPDATE company_expense_snapshots SET snapshot='{}' WHERE submission_id=$1",[sid]));
   await denied(()=>db.query('DELETE FROM company_expense_events WHERE submission_id=$1',[sid]));
 });
 await test('personal receipt changes do not change submitted snapshot',async()=> { await db.query('UPDATE receipts SET total_amount=1 WHERE id=$1',[rid]); assert.equal((await action('workspace',{company_id:cid})).submissions[0].snapshot.total_amount,24.5); await denied(()=>db.query('DELETE FROM receipts WHERE id=$1',[rid])); });
 await test('foreign member/outsider cannot see claim or use a foreign receipt',async()=> { await as(outsider); assert.equal((await db.query('SELECT * FROM company_expense_snapshots')).rows.length,0); await denied(()=>action('submit',{company_id:cid,receipt_id:rid,category:'meal',note:'Unberechtigt'})); });
 await test('manager cannot approve evidence that is not archived',async()=> { await as(admin); await denied(()=>action('review',{company_id:cid,submission_id:sid,decision:'approved'})); });
 const hash='a'.repeat(64), imageKey=`company-receipts/${cid}/${sid}/${hash}`;
 await test('only trusted archiver can attest original image',async()=> {
   await as(member); await denied(()=>db.query('SELECT public.company_archive_complete($1,$2,$3)',[sid,imageKey,hash]));
   await as(null,'service_role'); await db.query('SELECT public.company_archive_complete($1,$2,$3)',[sid,imageKey,hash]);
   await db.query('SELECT public.company_archive_complete($1,$2,$3)',[sid,imageKey,hash]);
   await denied(()=>db.query('SELECT public.company_archive_complete($1,$2,$3)',[sid,imageKey,'b'.repeat(64)]));
 });
 await test('independent approval and export are distinct from a payment',async()=> {
   await as(admin); await action('review',{company_id:cid,submission_id:sid,decision:'approved',note:'Geprüft'});
   const exp=await action('prepare_export',{company_id:cid,submission_id:sid});
   const repeated=await action('prepare_export',{company_id:cid,submission_id:sid}); assert.equal(exp.export_id,repeated.export_id);
   assert.equal(exp.payload.abacus_transfer_confirmed,false); assert.equal(exp.payload.payment_confirmed,false);
   assert.equal(JSON.parse(exp.payload.canonical_snapshot).total_amount,24.5);
   assert.equal(createHash('sha256').update(exp.payload.canonical_snapshot).digest('hex'),exp.payload.snapshot_sha256);
   const w=await action('workspace',{company_id:cid}); assert.equal(w.submissions[0].status,'approved'); assert.equal(w.submissions[0].events.filter(x=>x.event_type==='export_prepared').length,1);
 });
 await test('last administrator is protected and staff cannot change roles',async()=> {
   await denied(()=>action('set_role',{company_id:cid,user_id:admin,role:'member'}));
   await as(member); await denied(()=>action('set_role',{company_id:cid,user_id:member,role:'admin'}));
 });

 await test('foreign direct reads expose no events or export packages',async()=> {
   await as(outsider);
   for(const table of ['company_expense_snapshots','company_expense_events','company_expense_exports']) assert.equal((await db.query('SELECT * FROM '+table)).rows.length,0);
   await as(member); assert.equal((await db.query('SELECT * FROM company_expense_exports')).rows.length,0);
 });
 await test('an actually expired invitation is rejected',async()=> {
   await db.exec('RESET ROLE'); await db.query("UPDATE private.company_invites SET expires_at=now()-interval '1 second' WHERE company_id=$1",[cid]);
   await as(admin); const invite=await action('rotate_invite',{company_id:cid});
   await db.exec('RESET ROLE'); await db.query("UPDATE private.company_invites SET expires_at=now()-interval '1 second' WHERE company_id=$1",[cid]);
   await as(outsider); await denied(()=>action('join',{invite_code:invite.invite_code}));
 });
 async function newReceipt(owner,amount=24.5,currency='CHF',image=true) {
   await db.exec('RESET ROLE');
   const id=(await db.query('SELECT gen_random_uuid() AS id')).rows[0].id;
   await db.query("INSERT INTO receipts(id,user_id,receipt_date,store_name,total_amount,currency,image_url) VALUES($1,$2,'2026-11-27','Restaurant',$3,$4,$5)",[id,owner,amount,currency,image?owner+'/'+id+'.png':null]);
   await as(owner);return id;
 }
 const submitInput=id=>({company_id:cid,receipt_id:id,category:'meal',note:'Geschäftliches Mittagessen'});
 await test('wrong currency and missing original cannot be submitted',async()=> {
   for(const [currency,image] of [['EUR',true],['CHF',false]]) { const id=await newReceipt(member,20,currency,image); await denied(()=>action('submit',submitInput(id))); }
 });
 await test('administrator cannot approve their own archived claim',async()=> {
   const id=await newReceipt(admin);const claim=await action('submit',submitInput(id));const h='c'.repeat(64);
   await as(null,'service_role'); await db.query('SELECT company_archive_complete($1,$2,$3)',[claim.submission_id,`company-receipts/${cid}/${claim.submission_id}/${h}`,h]);
   await as(admin); await denied(()=>action('review',{company_id:cid,submission_id:claim.submission_id,decision:'approved'}));
 });
 await test('meal limit and pending export are enforced server-side',async()=> {
   const id=await newReceipt(member,50);const claim=await action('submit',submitInput(id));const h='d'.repeat(64);
   await as(null,'service_role'); await db.query('SELECT company_archive_complete($1,$2,$3)',[claim.submission_id,`company-receipts/${cid}/${claim.submission_id}/${h}`,h]);
   await as(admin);await denied(()=>action('review',{company_id:cid,submission_id:claim.submission_id,decision:'approved'}));
   await denied(()=>action('prepare_export',{company_id:cid,submission_id:claim.submission_id}));
   await action('review',{company_id:cid,submission_id:claim.submission_id,decision:'rejected',note:'Über der Grenze'});
   assert.equal((await action('workspace',{company_id:cid})).submissions.find(x=>x.id===claim.submission_id).status,'rejected');
 });
 await test('identical original cannot be archived for a second claim',async()=> {
   const id=await newReceipt(member); const claim=await action('submit',submitInput(id));
   await as(null,'service_role');await denied(()=>db.query('SELECT company_archive_complete($1,$2,$3)',[claim.submission_id,`company-receipts/${cid}/${claim.submission_id}/${hash}`,hash]));
 });
 await test('same receipt cannot be actively claimed at another company',async()=> {
   await as(member); const other=await action('create',{name:'Andere Firma'});
   await denied(()=>action('submit',{...submitInput(rid),company_id:other.company_id}));
 });
 console.log(`${passed} company workflow scenarios passed (disposable PostgreSQL).`);
} finally { await db.close(); }
