import { findDuplicatePairs, findDuplicateCandidates, buildMergePreview } from './receiptDuplicates';
import type { ReceiptWithItems } from '../types/receipt';
function receipt(id: string, fields: Partial<ReceiptWithItems> = {}): ReceiptWithItems {
  return { id,user_id:'owner',receipt_date:'2026-10-17',store_name:'Coop',store_category:null,total_amount:12.4,currency:'CHF',payment_method:null,payment_card:null,image_url:'original',original_image_url:null,raw_ocr_text:null,extra_fields:null,markdown_content:null,notes:null,created_at:'2026-10-17',project_id:null,receipt_items:[{id:`${id}-item`,receipt_id:id,name:'Original',quantity:1,unit:'Stk',unit_price:12.4,total_price:12.4,tags:['Lebensmittel']}],...fields };
}
test('suggests normalized merchant, same day/currency/cents only and never self or foreign owner',()=>{
 const a=receipt('a');
 expect(findDuplicateCandidates(a,[a,receipt('b',{store_name:' COOP. '}),receipt('c',{currency:'EUR'}),receipt('d',{user_id:'other'}),receipt('e',{receipt_date:null}),receipt('f',{total_amount:12.41})]).map(r=>r.id)).toEqual(['b']);
 expect(findDuplicateCandidates({...a,id:undefined},[a])).toHaveLength(1);
});
test('reversed dismissed pairs stay suppressed, distinct stores and missing data do not match',()=>{
 const list=[receipt('a'),receipt('b'),receipt('c',{store_name:'Migros'}),receipt('d',{total_amount:null}),receipt('e',{receipt_date:null})];
 expect(findDuplicatePairs(list)).toHaveLength(1);
 expect(findDuplicatePairs(list,[{receipt_a:'b',receipt_b:'a'}])).toHaveLength(0);
});
test('bank-linked survivor can take better image while keeping id, manual notes and items',()=>{
 const a=receipt('a',{notes:'Manuell geprüft',extra_fields:{cashier_number:null,receipt_number:'123',vat_number:null,store_address:null,store_email:null,store_phone:null,store_website:null}});
 const b=receipt('b',{image_url:'better',original_image_url:'full',notes:'OCR notes',payment_method:'TWINT',receipt_items:[]});
 const preview=buildMergePreview(a,b,{image:'source'});
 expect(preview.id).toBe('a');expect(preview.image_url).toBe('better');expect(preview.original_image_url).toBe('full');expect(preview.notes).toBe('Manuell geprüft');expect(preview.payment_method).toBe('TWINT');expect(preview.receipt_items).toEqual(a.receipt_items);
 expect(a.image_url).toBe('original');
});
test('explicit fields and extra choices override conflicts, target choices prevent missing autofill',()=>{
 const a=receipt('a',{notes:null});const b=receipt('b',{notes:'source',store_name:'COOP',extra_fields:{cashier_number:null,receipt_number:'new',vat_number:null,store_address:null,store_email:null,store_phone:null,store_website:null}});
 const preview=buildMergePreview(a,b,{fields:{notes:'target',store_name:'source'},items:'source',extras:{receipt_number:'source'}});
 expect(preview.notes).toBeNull();expect(preview.store_name).toBe('COOP');expect(preview.extra_fields?.receipt_number).toBe('new');expect(preview.receipt_items[0].receipt_id).toBe('a');
});
test('preview keeps target adjustment when replacing ordinary items from source',()=>{
 const a=receipt('a');a.receipt_items.push({...a.receipt_items[0],id:'tip',name:'Trinkgeld',total_price:2,is_adjustment:true});
 const b=receipt('b');b.receipt_items[0].name='New items';
 const preview=buildMergePreview(a,b,{items:'source'});
 expect(preview.receipt_items.map(item=>item.name)).toEqual(['New items','Trinkgeld']);
 expect(preview.receipt_items.every(item=>item.receipt_id==='a')).toBe(true);
});
