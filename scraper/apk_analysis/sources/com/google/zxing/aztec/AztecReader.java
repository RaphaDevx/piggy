package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;

/* loaded from: classes2.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006e A[LOOP:0: B:27:0x006c->B:28:0x006e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009f  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r10, java.util.Map<com.google.zxing.DecodeHintType, ?> r11) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r9 = this;
            com.google.zxing.aztec.detector.Detector r9 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r10 = r10.getBlackMatrix()
            r9.<init>(r10)
            r10 = 0
            r0 = 0
            com.google.zxing.aztec.AztecDetectorResult r1 = r9.detect(r0)     // Catch: com.google.zxing.FormatException -> L2e com.google.zxing.NotFoundException -> L36
            com.google.zxing.ResultPoint[] r2 = r1.getPoints()     // Catch: com.google.zxing.FormatException -> L2e com.google.zxing.NotFoundException -> L36
            int r3 = r1.getErrorsCorrected()     // Catch: com.google.zxing.FormatException -> L2a com.google.zxing.NotFoundException -> L2c
            com.google.zxing.aztec.decoder.Decoder r4 = new com.google.zxing.aztec.decoder.Decoder     // Catch: com.google.zxing.FormatException -> L26 com.google.zxing.NotFoundException -> L28
            r4.<init>()     // Catch: com.google.zxing.FormatException -> L26 com.google.zxing.NotFoundException -> L28
            com.google.zxing.common.DecoderResult r1 = r4.decode(r1)     // Catch: com.google.zxing.FormatException -> L26 com.google.zxing.NotFoundException -> L28
            r4 = r3
            r3 = r2
            r2 = r10
            r10 = r1
            r1 = r2
            goto L3c
        L26:
            r1 = move-exception
            goto L31
        L28:
            r1 = move-exception
            goto L39
        L2a:
            r1 = move-exception
            goto L30
        L2c:
            r1 = move-exception
            goto L38
        L2e:
            r1 = move-exception
            r2 = r10
        L30:
            r3 = r0
        L31:
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r10
            goto L3c
        L36:
            r1 = move-exception
            r2 = r10
        L38:
            r3 = r0
        L39:
            r4 = r3
            r3 = r2
            r2 = r10
        L3c:
            if (r10 != 0) goto L5d
            r10 = 1
            com.google.zxing.aztec.AztecDetectorResult r9 = r9.detect(r10)     // Catch: java.lang.Throwable -> L55
            com.google.zxing.ResultPoint[] r3 = r9.getPoints()     // Catch: java.lang.Throwable -> L55
            int r4 = r9.getErrorsCorrected()     // Catch: java.lang.Throwable -> L55
            com.google.zxing.aztec.decoder.Decoder r10 = new com.google.zxing.aztec.decoder.Decoder     // Catch: java.lang.Throwable -> L55
            r10.<init>()     // Catch: java.lang.Throwable -> L55
            com.google.zxing.common.DecoderResult r10 = r10.decode(r9)     // Catch: java.lang.Throwable -> L55
            goto L5d
        L55:
            r9 = move-exception
            if (r1 != 0) goto L5c
            if (r2 == 0) goto L5b
            throw r2
        L5b:
            throw r9
        L5c:
            throw r1
        L5d:
            r5 = r3
            r9 = r4
            if (r11 == 0) goto L76
            com.google.zxing.DecodeHintType r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            java.lang.Object r11 = r11.get(r1)
            com.google.zxing.ResultPointCallback r11 = (com.google.zxing.ResultPointCallback) r11
            if (r11 == 0) goto L76
            int r1 = r5.length
        L6c:
            if (r0 >= r1) goto L76
            r2 = r5[r0]
            r11.foundPossibleResultPoint(r2)
            int r0 = r0 + 1
            goto L6c
        L76:
            com.google.zxing.Result r11 = new com.google.zxing.Result
            java.lang.String r2 = r10.getText()
            byte[] r3 = r10.getRawBytes()
            int r4 = r10.getNumBits()
            com.google.zxing.BarcodeFormat r6 = com.google.zxing.BarcodeFormat.AZTEC
            long r7 = java.lang.System.currentTimeMillis()
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            java.util.List r0 = r10.getByteSegments()
            if (r0 == 0) goto L99
            com.google.zxing.ResultMetadataType r1 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r11.putMetadata(r1, r0)
        L99:
            java.lang.String r0 = r10.getECLevel()
            if (r0 == 0) goto La4
            com.google.zxing.ResultMetadataType r1 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r11.putMetadata(r1, r0)
        La4:
            java.lang.Integer r0 = r10.getErrorsCorrected()
            int r0 = r0.intValue()
            int r9 = r9 + r0
            com.google.zxing.ResultMetadataType r0 = com.google.zxing.ResultMetadataType.ERRORS_CORRECTED
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r11.putMetadata(r0, r9)
            com.google.zxing.ResultMetadataType r9 = com.google.zxing.ResultMetadataType.SYMBOLOGY_IDENTIFIER
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "]z"
            r0.<init>(r1)
            int r10 = r10.getSymbologyModifier()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r11.putMetadata(r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }
}
