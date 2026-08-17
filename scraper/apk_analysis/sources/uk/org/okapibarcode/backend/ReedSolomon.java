package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class ReedSolomon {
    private int[] alog;
    private int logmod;
    private int[] logt;
    public int[] res;
    private int rlen;
    private int[] rspoly;

    public int getResult(int i) {
        return this.res[i];
    }

    public void init_gf(int i) {
        int i2 = 1;
        int i3 = 0;
        while (i2 <= i) {
            i3++;
            i2 <<= 1;
        }
        int i4 = i2 >> 1;
        int i5 = 1 << (i3 - 1);
        int i6 = i5 - 1;
        this.logmod = i6;
        this.logt = new int[i5];
        this.alog = new int[i6];
        int i7 = 1;
        for (int i8 = 0; i8 < this.logmod; i8++) {
            this.alog[i8] = i7;
            this.logt[i7] = i8;
            i7 <<= 1;
            if ((i7 & i4) != 0) {
                i7 ^= i;
            }
        }
    }

    public void init_code(int i, int i2) {
        int[] iArr = new int[i + 1];
        this.rspoly = iArr;
        this.rlen = i;
        iArr[0] = 1;
        for (int i3 = 1; i3 <= i; i3++) {
            this.rspoly[i3] = 1;
            for (int i4 = i3 - 1; i4 > 0; i4--) {
                int[] iArr2 = this.rspoly;
                int i5 = iArr2[i4];
                if (i5 != 0) {
                    iArr2[i4] = this.alog[(this.logt[i5] + i2) % this.logmod];
                }
                iArr2[i4] = iArr2[i4] ^ iArr2[i4 - 1];
            }
            int[] iArr3 = this.rspoly;
            iArr3[0] = this.alog[(this.logt[iArr3[0]] + i2) % this.logmod];
            i2++;
        }
    }

    public void encode(int i, int[] iArr) {
        int i2;
        int i3;
        this.res = new int[this.rlen];
        for (int i4 = 0; i4 < this.rlen; i4++) {
            this.res[i4] = 0;
        }
        for (int i5 = 0; i5 < i; i5++) {
            int[] iArr2 = this.res;
            int i6 = this.rlen;
            int i7 = iArr2[i6 - 1] ^ iArr[i5];
            for (int i8 = i6 - 1; i8 > 0; i8--) {
                if (i7 != 0 && (i3 = this.rspoly[i8]) != 0) {
                    int[] iArr3 = this.res;
                    int i9 = iArr3[i8 - 1];
                    int[] iArr4 = this.alog;
                    int[] iArr5 = this.logt;
                    iArr3[i8] = iArr4[(iArr5[i7] + iArr5[i3]) % this.logmod] ^ i9;
                } else {
                    int[] iArr6 = this.res;
                    iArr6[i8] = iArr6[i8 - 1];
                }
            }
            if (i7 != 0 && (i2 = this.rspoly[0]) != 0) {
                int[] iArr7 = this.res;
                int[] iArr8 = this.alog;
                int[] iArr9 = this.logt;
                iArr7[0] = iArr8[(iArr9[i7] + iArr9[i2]) % this.logmod];
            } else {
                this.res[0] = 0;
            }
        }
    }
}
