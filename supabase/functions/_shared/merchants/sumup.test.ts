import { readSumUpReceipt } from './sumup';
const access={accessToken:'test-token',merchantCode:'MTEST1234'};
const payload={transaction_data:{merchant_code:access.merchantCode,transaction_id:'410fc44a-5956-44e1-b5cc-19c6f8d727a4',amount:'24.50',currency:'CHF'},merchant_data:{name:'Laden'},products:[{name:'Rivella',quantity:1}]};
const response=(body:unknown)=>new Response(JSON.stringify(body));
test('fixed provider URL and minimal read request; original data and decimal string retained',async()=>{
 const fetcher=jest.fn(async()=>response(payload));const receipt=await readSumUpReceipt(access,'TCODE1234',undefined,fetcher as typeof fetch);
 const [url,options]=fetcher.mock.calls[0] as unknown as [URL,RequestInit];
 expect(url.origin).toBe('https://api.sumup.com');expect(url.pathname).toBe('/v1.1/receipts/TCODE1234');expect(url.searchParams.get('mid')).toBe(access.merchantCode);expect(options.method).toBe('GET');expect(options.redirect).toBe('error');
 expect(receipt.amount).toBe('24.50');expect(receipt.raw).toEqual(payload);expect(receipt.externalKey).toContain(payload.transaction_data.transaction_id);
});
test('transaction code and canonical UUID produce same identity',async()=>{
 const fetcher=async()=>response(payload);expect((await readSumUpReceipt(access,'TCODE1234',undefined,fetcher as typeof fetch)).externalKey).toBe((await readSumUpReceipt(access,payload.transaction_data.transaction_id,undefined,fetcher as typeof fetch)).externalKey);
});
test('refund event has separate identity and is actually requested',async()=>{
 const fetcher=jest.fn(async()=>response(payload));const receipt=await readSumUpReceipt(access,'TCODE1234',42,fetcher as typeof fetch);expect(receipt.externalKey.endsWith(':42')).toBe(true);expect((fetcher.mock.calls[0] as unknown as [URL])[0].searchParams.get('tx_event_id')).toBe('42');
});
test('rejects response for a different merchant',async()=>{
 await expect(readSumUpReceipt(access,'TCODE1234',undefined,(async()=>response({...payload,transaction_data:{...payload.transaction_data,merchant_code:'MOTHER'}})) as typeof fetch)).rejects.toThrow('merchant mismatch');
});
test('rejects path injection and invalid event before network access',async()=>{
 const fetcher=jest.fn();await expect(readSumUpReceipt(access,'../other',undefined,fetcher)).rejects.toThrow();await expect(readSumUpReceipt(access,'TCODE1234',-1,fetcher)).rejects.toThrow();expect(fetcher).not.toHaveBeenCalled();
});
test('provider failure never returns a receipt; no invented products for missing lines',async()=>{
 await expect(readSumUpReceipt(access,'TCODE1234',undefined,(async()=>new Response('',{status:401})) as typeof fetch)).rejects.toThrow('HTTP 401');
 const result=await readSumUpReceipt(access,'TCODE1234',undefined,(async()=>response({transaction_data:payload.transaction_data})) as typeof fetch);expect(result.raw.products).toBeUndefined();
});
