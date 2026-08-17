package uk.org.okapibarcode.util;

import uk.org.okapibarcode.backend.OkapiException;

/* loaded from: classes4.dex */
public final class Arrays {
    private Arrays() {
    }

    public static int positionOf(char c, char[] cArr) {
        for (int i = 0; i < cArr.length; i++) {
            if (c == cArr[i]) {
                return i;
            }
        }
        throw new OkapiException("Unable to find character '" + c + "' in character array.");
    }

    public static int positionOf(int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        throw new OkapiException("Unable to find integer '" + i + "' in integer array.");
    }

    public static boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAt(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            int i3 = i + i2;
            if (i3 >= bArr.length || bArr[i3] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] insertArray(int[] iArr, int i, int[] iArr2) {
        int length = iArr.length + iArr2.length;
        int[] iArr3 = new int[length];
        System.arraycopy(iArr, 0, iArr3, 0, i);
        System.arraycopy(iArr2, 0, iArr3, i, iArr2.length);
        System.arraycopy(iArr, i, iArr3, iArr2.length + i, (length - i) - iArr2.length);
        return iArr3;
    }
}
