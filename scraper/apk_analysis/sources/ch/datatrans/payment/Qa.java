package ch.datatrans.payment;

/* loaded from: classes3.dex */
public final class Qa {
    public static final long[] b = {-84501687036131310L, -6290157327259397203L, -4191586600210624997L, -8405416864778474610L};
    public static final long[] c = {-3003896733343505914L, 2385455903904644402L, -5941112033576683674L, 496995657181149250L, 5538731372441419158L, -5698274054190818476L, -1845225449451202711L, 1082599943525767139L, 8088822888457806097L, -7270021974475061413L, -5101733810603990467L, -4005242055997231539L, 929004985614344442L, 2655166129046329680L, 5925091955675133904L, -7921744747867877392L, -5517711313994332241L, -2740931089490646140L, -1022212549664838612L, 1520217078884190738L, 3919755584730530012L, 6237950846521423668L, 4992456068047364275L, 6977230084424287975L, -8250896503135368545L, -5507239328585262558L, -5925011988140438307L, -3510865967485580956L, -2281725633664350728L, 1158080803585582239L, 316610647334685308L, 3036428601401319013L};
    public final int[] a;

    public Qa() {
        long[] jArr = c;
        long[] jArr2 = new long[32];
        for (int i = 0; i < 32; i++) {
            jArr2[i] = jArr[i] ^ (-7720809895872829801L);
        }
        this.a = new int[64];
        for (int i2 = 0; i2 < 32; i2++) {
            int[] iArr = this.a;
            int i3 = i2 * 2;
            long j = jArr2[i2];
            iArr[i3] = (int) ((j >> 32) & 4294967295L);
            iArr[i3 + 1] = (int) (j & 4294967295L);
        }
    }

    public static int a(int i, int i2) {
        return (i << (32 - i2)) | (i >>> i2);
    }

    public final byte[] a(byte[] bArr) {
        long[] jArr = b;
        char c2 = 4;
        long[] jArr2 = new long[4];
        for (int i = 0; i < 4; i++) {
            jArr2[i] = jArr[i] ^ (-7720809895872829801L);
        }
        long j = jArr2[0];
        long j2 = jArr2[1];
        int i2 = (int) (j2 & 4294967295L);
        int i3 = 3;
        long j3 = jArr2[2];
        char c3 = 5;
        long j4 = jArr2[3];
        int i4 = 7;
        int[] iArr = {(int) ((j >> 32) & 4294967295L), (int) (j & 4294967295L), (int) ((j2 >> 32) & 4294967295L), i2, (int) ((j3 >> 32) & 4294967295L), (int) (j3 & 4294967295L), (int) ((j4 >> 32) & 4294967295L), (int) (4294967295L & j4)};
        int length = bArr.length;
        int i5 = 64;
        int i6 = ((((length + 8) / 64) + 1) * 64) / 4;
        int[] iArr2 = new int[i6];
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 / 4;
            iArr2[i8] = iArr2[i8] | ((bArr[i7] & 255) << (24 - ((i7 % 4) * 8)));
        }
        int i9 = length / 4;
        iArr2[i9] = iArr2[i9] | (128 << (24 - ((length % 4) * 8)));
        iArr2[i6 - 1] = length * 8;
        int i10 = 16;
        int i11 = i6 / 16;
        int i12 = 0;
        while (i12 < i11) {
            int[] iArr3 = new int[i10];
            for (int i13 = 0; i13 < i10; i13++) {
                iArr3[i13] = iArr2[(i12 * 16) + i13];
            }
            int[] iArr4 = new int[i5];
            for (int i14 = 0; i14 < i10; i14++) {
                iArr4[i14] = iArr3[i14];
            }
            int i15 = i10;
            while (i15 < i5) {
                int i16 = i15 - 15;
                int a = (a(iArr4[i16], i4) ^ a(iArr4[i16], 18)) ^ (iArr4[i16] >>> i3);
                int i17 = i15 - 2;
                iArr4[i15] = iArr4[i15 - 16] + a + iArr4[i15 - 7] + ((iArr4[i17] >>> 10) ^ (a(iArr4[i17], 19) ^ a(iArr4[i17], 17)));
                i15++;
                i4 = 7;
                i5 = 64;
            }
            int i18 = iArr[0];
            int i19 = iArr[1];
            int i20 = iArr[2];
            int i21 = iArr[i3];
            int i22 = iArr[c2];
            int i23 = iArr[c3];
            int i24 = 6;
            int i25 = iArr[6];
            int i26 = iArr[7];
            int i27 = 0;
            int i28 = i25;
            int i29 = 64;
            while (i27 < i29) {
                int a2 = i26 + (a(i22, 25) ^ (a(i22, i24) ^ a(i22, 11))) + (((~i22) & i28) ^ (i22 & i23)) + this.a[i27] + iArr4[i27];
                int i30 = i21 + a2;
                int a3 = a2 + ((a(i18, 2) ^ a(i18, 13)) ^ a(i18, 22)) + (((i18 & i19) ^ (i18 & i20)) ^ (i19 & i20));
                i27++;
                i21 = i20;
                i26 = i28;
                i28 = i23;
                i29 = 64;
                i20 = i19;
                i23 = i22;
                i19 = i18;
                i18 = a3;
                i22 = i30;
                i24 = 6;
            }
            iArr[0] = iArr[0] + i18;
            iArr[1] = iArr[1] + i19;
            iArr[2] = iArr[2] + i20;
            iArr[3] = iArr[3] + i21;
            iArr[4] = iArr[4] + i22;
            iArr[5] = iArr[5] + i23;
            iArr[6] = iArr[6] + i28;
            iArr[7] = iArr[7] + i26;
            i12++;
            c2 = 4;
            i4 = 7;
            i10 = 16;
            i3 = 3;
            i5 = 64;
            c3 = 5;
        }
        byte[] bArr2 = new byte[32];
        for (int i31 = 0; i31 < 8; i31++) {
            int i32 = i31 * 4;
            int i33 = iArr[i31];
            bArr2[i32] = (byte) ((i33 >> 24) & 255);
            bArr2[i32 + 1] = (byte) ((i33 >> 16) & 255);
            bArr2[i32 + 2] = (byte) ((i33 >> 8) & 255);
            bArr2[i32 + 3] = (byte) (i33 & 255);
        }
        return bArr2;
    }
}
