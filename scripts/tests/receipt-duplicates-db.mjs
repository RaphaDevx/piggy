/** npm install --prefix /tmp/piggy-duplicate-pg --no-audit --no-fund @electric-sql/pglite@0.5.10
 * PIGGY_PGLITE_MODULE=/tmp/piggy-duplicate-pg/node_modules/@electric-sql/pglite/dist/index.js node scripts/tests/receipt-duplicates-db.mjs
 * Uses disposable in-memory PostgreSQL; NEVER connects to Piggy production.
 */
import { readFileSync } from 'node:fs';
import assert from 'node:assert/strict';
import { pathToFileURL } from 'node:url';
const { PGlite } = await import(pathToFileURL(process.env.PIGGY_PGLITE_MODULE ?? '/tmp/piggy-duplicate-pg/node_modules/@electric-sql/pglite/dist/index.js'));
const fixture = readFileSync(new URL('./receipt-duplicates.fixture.sql', import.meta.url),'utf8');
const migration = readFileSync(new URL('../../supabase/migrations/20261017114452_receipt_duplicate_review.sql',import.meta.url),'utf8');
const U='11111111-1111-4111-8111-111111111111', OTHER='22222222-2222-4222-8222-222222222222';
const A='aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa',B='bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb',C='cccccccc-cccc-4ccc-8ccc-cccccccccccc';
let passed=0;
async function scenario(name,run) {
 const db = new PGlite();
 try {
  await db.exec(fixture); await db.exec(migration);
  await db.query('INSERT INTO auth.users VALUES ($1),($2)',[U,OTHER]);
  await db.query("INSERT INTO receipts(id,user_id,store_name,receipt_date,total_amount,currency,image_url,extra_fields) VALUES($1,$2,'Coop','2026-10-17',10,'CHF','old-image', '{\"receipt_number\":\"manual\"}'),($3,$2,'Coop','2026-10-17',10,'CHF','better-image','{\"store_phone\":\"123\"}')",[A,U,B]);
  await db.query("INSERT INTO receipt_items(receipt_id,name,total_price,tags) VALUES($1,'existing',10,ARRAY['Lebensmittel']),($2,'new',10,ARRAY['Lebensmittel'])",[A,B]);
  await db.query("SELECT set_config('request.jwt.claim.sub',$1,false)",[U]);
  await run(db);
  passed++; process.stdout.write(`PASS ${name}\n`);
 } finally { await db.close(); }
}
const merge=(db,choices={})=>db.query('SELECT public.merge_receipt_duplicates($1,$2,$3)',[A,B,choices]);
const expectReject=async(promise,pattern)=>{await assert.rejects(promise,pattern);};
await scenario('bank linked target keeps match and manual data while using better source image',async db=>{
 await db.query('INSERT INTO bank_transactions(id,user_id) VALUES($1,$2)',[C,U]);
 await db.query("INSERT INTO receipt_matches(user_id,receipt_id,transaction_id,score,match_type) VALUES($1,$2,$3,1,'manual')",[U,A,C]);
 await db.exec('SET ROLE authenticated');
 await merge(db,{image:'source'});
 const {rows:[r]}=await db.query('SELECT * FROM receipts WHERE id=$1',[A]);
 assert.equal(r.image_url,'better-image'); assert.equal(r.extra_fields.receipt_number,'manual');assert.equal(r.extra_fields.store_phone,'123');
 await db.exec('RESET ROLE');
 assert.equal((await db.query('SELECT receipt_id FROM receipt_matches')).rows[0].receipt_id,A);
 assert.equal((await db.query('SELECT match_status FROM bank_transactions')).rows[0].match_status,'matched');
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,1);
 const history=(await db.query('SELECT * FROM private.receipt_merge_history')).rows[0];
 assert.equal(history.source_before.image_url,'better-image'); assert.equal(history.target_before.image_url,'old-image');
 assert.equal(history.source_before.receipt_items[0].name,'new');
});
await scenario('scan merges atomically without inserting a second receipt',async db=>{
 await db.query('DELETE FROM receipts WHERE id=$1',[B]);
 const scan={store_name:'COOP',receipt_date:'2026-10-17',total_amount:10,currency:'CHF',image_url:'scan-image',receipt_items:[{name:'scan-item',quantity:1,total_price:10,tags:['Lebensmittel']}],extra_fields:{receipt_number:'scan-number'}};
 await db.exec('SET ROLE authenticated');
 await db.query('SELECT merge_receipt_scan($1,$2,$3)',[A,scan,{image:'source',items:'source',extras:{receipt_number:'source'}}]);
 await db.exec('RESET ROLE');
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,1);
 assert.equal((await db.query('SELECT name FROM receipt_items')).rows[0].name,'scan-item');
 assert.equal((await db.query('SELECT extra_fields FROM receipts')).rows[0].extra_fields.receipt_number,'scan-number');
});
await scenario('owner checks reject another user and unauthenticated calls',async db=>{
 await db.query('UPDATE receipts SET user_id=$1 WHERE id=$2',[OTHER,B]);
 await expectReject(merge(db),/eigenen Besitz/);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,2);
 await db.query("SELECT set_config('request.jwt.claim.sub','',false)");
 await expectReject(merge(db),/anmelden/);
});
await scenario('kept separate pairs canonicalize and survive another receipt merge',async db=>{
 await db.query("INSERT INTO receipts(id,user_id,store_name,total_amount,currency) VALUES($1,$2,'Coop',10,'CHF')",[C,U]);
 await db.query('SELECT keep_receipts_separate($1,$2)',[C,B]);
 await db.query('SELECT keep_receipts_separate($1,$2)',[B,C]);
 await merge(db);
 const {rows}=await db.query('SELECT receipt_a,receipt_b FROM receipt_duplicate_decisions');
 assert.deepEqual(rows,[{receipt_a:A,receipt_b:C}]);
});
await scenario('split on source keeps participant settlements and item exclusions',async db=>{
 await db.query("INSERT INTO splits(id,receipt_id,payer_id,total_amount,currency) VALUES($1,$2,$3,10,'CHF')",[C,B,U]);
 await db.query('INSERT INTO split_participants(split_id,user_id,amount,settled) VALUES($1,$2,5,true)',[C,OTHER]);
 await db.query('INSERT INTO split_exclusions(split_id,receipt_item_id) SELECT $1,id FROM receipt_items WHERE receipt_id=$2',[C,B]);
 await expectReject(merge(db),/aufgeteilten/);
 await merge(db,{items:'source'});
 assert.equal((await db.query('SELECT receipt_id FROM splits')).rows[0].receipt_id,A);
 assert.equal((await db.query('SELECT settled FROM split_participants')).rows[0].settled,true);
 assert.equal((await db.query('SELECT count(*)::int n FROM split_exclusions')).rows[0].n,1);
});
await scenario('two bank transactions block merge without partial writes',async db=>{
 await db.query('INSERT INTO bank_transactions(id,user_id) VALUES($1,$2),($3,$2)',[A,U,B]);
 await db.query("INSERT INTO receipt_matches(receipt_id,transaction_id,match_type) VALUES($1,$1,'manual'),($2,$2,'manual')",[A,B]);
 await expectReject(merge(db,{image:'source'}),/Unterschiedliche Bankbuchungen/);
 assert.equal((await db.query('SELECT image_url FROM receipts WHERE id=$1',[A])).rows[0].image_url,'old-image');
 assert.equal((await db.query('SELECT count(*)::int n FROM private.receipt_merge_history')).rows[0].n,0);
});
await scenario('manual item classifications cannot be silently replaced',async db=>{
 await db.query('UPDATE receipt_items SET classified_at=now() WHERE receipt_id=$1',[A]);
 await expectReject(merge(db,{items:'source'}),/Geprüfte Artikel/);
 await merge(db,{image:'source',items:'target'});
 assert.equal((await db.query('SELECT name FROM receipt_items')).rows[0].name,'existing');
});
await scenario('source links, suggestions, shares, project and company survive',async db=>{
 await db.query('UPDATE receipts SET project_id=$1,company_id=$1 WHERE id=$2',[C,B]);
 await db.query("INSERT INTO shared_receipts(receipt_id,sender_id,recipient_id,status) VALUES($1,$2,$3,'accepted')",[B,U,OTHER]);
 await db.query("INSERT INTO expense_submissions(receipt_id,company_id,submitter_id,status) VALUES($1,$2,$3,'approved')",[B,C,U]);
 await db.query("INSERT INTO match_suggestions(receipt_id,transaction_id,status) VALUES($1,$3,'open'),($2,$3,'rejected')",[A,B,C]);
 await db.query('INSERT INTO rematch_queue(receipt_id,user_id) VALUES($1,$2)',[B,U]);
 await merge(db);
 for(const table of ['shared_receipts','expense_submissions','match_suggestions','rematch_queue']) assert.equal((await db.query(`SELECT receipt_id FROM ${table}`)).rows[0].receipt_id,A);
 assert.equal((await db.query('SELECT status FROM match_suggestions')).rows[0].status,'rejected');
 assert.equal((await db.query('SELECT project_id FROM receipts')).rows[0].project_id,C);
});
await scenario('new unhandled receipt relationship fails closed',async db=>{
 await db.exec('CREATE TABLE public.future_reference(receipt_id uuid REFERENCES receipts ON DELETE CASCADE)');
 await expectReject(merge(db),/Weitere Quittungsverknüpfung/);
});
await scenario('invalid choices fail and audit table is protected from clients',async db=>{
 await expectReject(merge(db,{fields:{user_id:'source'}}),/Feldauswahl/);
 await expectReject(merge(db,{items:'guess'}),/Artikelauswahl/);
 await db.exec('SET ROLE authenticated');
 await expectReject(db.query('SELECT * FROM private.receipt_merge_history'),/permission denied/);
 await db.exec('RESET ROLE'); await db.exec('SET ROLE anon');
 await expectReject(merge(db),/permission denied/);
});
await scenario('save separate is atomic and failures do not leave phantom receipts',async db=>{
 const scan={store_name:'Coop',receipt_date:'2026-10-17',total_amount:10,currency:'CHF',receipt_items:[{name:'fresh',quantity:1,total_price:10,tags:['Lebensmittel']}]};
 await db.exec('SET ROLE authenticated');
 const result=await db.query('SELECT save_separate_receipt_scan($1,$2) id',[scan,[A,B]]);
 const id=result.rows[0].id;
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,3);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipt_duplicate_decisions')).rows[0].n,2);
 await expectReject(db.query('SELECT save_separate_receipt_scan($1,$2)',[{...scan,receipt_items:[{name:'bad',quantity:'not-a-number'}]},[A]]),/numeric/);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,3);
 await db.exec('RESET ROLE');
 assert.equal((await db.query('SELECT name FROM receipt_items WHERE receipt_id=$1',[id])).rows[0].name,'fresh');
});
await scenario('review deletion protects bank/split relationships and owner',async db=>{
 await db.query('INSERT INTO bank_transactions(id,user_id) VALUES($1,$2)',[C,U]);
 await db.query("INSERT INTO receipt_matches(receipt_id,transaction_id,match_type) VALUES($1,$2,'manual')",[A,C]);
 await db.exec('SET ROLE authenticated');
 await expectReject(db.query('SELECT delete_reviewed_receipt($1)',[A]),/verknüpft/);
 await db.query('SELECT delete_reviewed_receipt($1)',[B]);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,1);
});
await scenario('source manual data cannot be discarded and conflicting projects block',async db=>{
 await db.query('UPDATE receipt_items SET classified_at=now() WHERE receipt_id=$1',[B]);
 await expectReject(merge(db),/zweite Quittung enthält geprüfte/);
 await db.query('UPDATE receipts SET project_id=id');
 await expectReject(merge(db,{items:'source'}),/Unterschiedliche Projekte/);
});
await scenario('normal fresh scan preserves date, amount, raw fields and items',async db=>{
 const scan={store_name:'Migros',receipt_date:'2026-10-16',total_amount:42.35,currency:'EUR',store_category:'Lebensmittel',payment_method:'Card',payment_card:'1234',raw_ocr_text:'raw',markdown_content:'md',notes:'note',original_image_url:'original-file',image_url:'processed',extra_fields:{receipt_number:'123'},receipt_items:[{name:'Bread',quantity:2,unit:'Stk',unit_price:21.175,total_price:42.35,tags:['Lebensmittel'],subcategory:'food.bread'}]};
 await db.exec('SET ROLE authenticated');
 const {rows:[{id}]}=await db.query('SELECT save_separate_receipt_scan($1,$2) id',[scan,[]]);
 const {rows:[r]}=await db.query('SELECT * FROM receipts WHERE id=$1',[id]);
 for(const key of ['store_name','total_amount','currency','store_category','payment_method','payment_card','raw_ocr_text','markdown_content','notes','original_image_url','image_url']) assert.equal(String(r[key]),String(scan[key]));
 assert.equal(new Date(r.receipt_date).toISOString().slice(0,10),'2026-10-16');
 assert.equal(r.user_id,U);
});
await scenario('foreign suppression pair fails before creating new receipt or decisions',async db=>{
 await db.query('UPDATE receipts SET user_id=$1 WHERE id=$2',[OTHER,B]);
 await expectReject(db.query('SELECT save_separate_receipt_scan($1,$2)',[{store_name:'Coop',currency:'CHF',receipt_items:[]},[B]]),/eigenen Besitz/);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipts')).rows[0].n,2);
 assert.equal((await db.query('SELECT count(*)::int n FROM receipt_duplicate_decisions')).rows[0].n,0);
});
await scenario('linked amount/currency cannot change and identical links keep manual review',async db=>{
 await db.query('INSERT INTO bank_transactions(id,user_id) VALUES($1,$2)',[C,U]);
 await db.query("INSERT INTO receipt_matches(receipt_id,transaction_id,match_type) VALUES($1,$3,'auto'),($2,$3,'manual')",[A,B,C]);
 await db.query('UPDATE receipts SET total_amount=11 WHERE id=$1',[B]);
 await expectReject(merge(db,{fields:{total_amount:'source'}}),/Betrag und Währung/);
 await db.query('UPDATE receipts SET total_amount=10 WHERE id=$1',[B]);
 await merge(db);
 const {rows:[m]}=await db.query('SELECT receipt_id,match_type FROM receipt_matches');
 assert.equal(m.receipt_id,A); assert.equal(m.match_type,'manual');
});
await scenario('conflicting completed suggestions and two splits cannot disappear',async db=>{
 await db.query("INSERT INTO match_suggestions(receipt_id,transaction_id,status) VALUES($1,$3,'accepted'),($2,$3,'rejected')",[A,B,C]);
 await expectReject(merge(db),/Bankvorschlags/);
 await db.exec('DELETE FROM match_suggestions');
 await db.query("INSERT INTO splits(receipt_id,payer_id,total_amount,currency) VALUES($1,$3,10,'CHF'),($2,$3,10,'CHF')",[A,B,U]);
 await expectReject(merge(db),/Beide Quittungen haben eine Aufteilung/);
});
await scenario('adjustment survives replacing ordinary items and invalid scan rolls everything back',async db=>{
 await db.query("INSERT INTO receipt_items(receipt_id,name,total_price,is_adjustment) VALUES($1,'tip',2,true)",[A]);
 await merge(db,{items:'source'});
 const {rows}=await db.query('SELECT name FROM receipt_items ORDER BY name');
 assert.deepEqual(rows.map(r=>r.name),['new','tip']);
 await expectReject(db.query('SELECT merge_receipt_scan($1,$2,$3)',[A,{image_url:'overwrite',receipt_items:[{name:'hack',is_adjustment:true}]},{image:'source',items:'source'}]),/Ungültiger Scan-Artikel/);
 assert.equal((await db.query('SELECT image_url FROM receipts WHERE id=$1',[A])).rows[0].image_url,'old-image');
 assert.equal((await db.query('SELECT count(*)::int n FROM private.receipt_merge_history')).rows[0].n,1);
});
console.log(`${passed} database scenarios passed (disposable PostgreSQL).`);
