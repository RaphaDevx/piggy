/** @jest-environment node */
import { presignUrl, uriEncode, encodeKeyPath } from './sigv4';

// Testvektor aus der öffentlichen AWS-Dokumentation (Beispiel-Schlüssel, keine echten Zugangsdaten).
test('AWS-Dokumentationsbeispiel für vorsignierte GET-URL', async () => {
  const url = await presignUrl({
    method: 'GET', host: 'examplebucket.s3.amazonaws.com', path: '/test.txt', region: 'us-east-1',
    accessKeyId: 'AKIAIOSFODNN7EXAMPLE', secretAccessKey: 'wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY',
    expiresSeconds: 86400, now: new Date('2013-05-24T00:00:00Z'),
  });
  expect(url).toBe(
    'https://examplebucket.s3.amazonaws.com/test.txt?X-Amz-Algorithm=AWS4-HMAC-SHA256'
    + '&X-Amz-Credential=AKIAIOSFODNN7EXAMPLE%2F20130524%2Fus-east-1%2Fs3%2Faws4_request'
    + '&X-Amz-Date=20130524T000000Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host'
    + '&X-Amz-Signature=aeeed9bbccd4d02ee5c0109b86d86835f995330da4c265957d157751f604d404',
  );
});

test('Kodierung nach RFC 3986, Schrägstriche bleiben im Pfad', () => {
  expect(uriEncode("a b!'()*")).toBe('a%20b%21%27%28%29%2A');
  expect(encodeKeyPath('receipts/u 1/a+b.jpg')).toBe('receipts/u%201/a%2Bb.jpg');
});
