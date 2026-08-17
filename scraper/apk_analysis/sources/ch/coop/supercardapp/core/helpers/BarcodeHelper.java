package ch.coop.supercardapp.core.helpers;

import android.graphics.Bitmap;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
public class BarcodeHelper {
    private static final int bgColor = -1;
    private static final int fgColor = -16777216;

    public static Bitmap createBitmap(BitMatrix bitMatrix, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] enclosingRectangle;
        int i6 = 0;
        boolean z2 = true;
        if (!z || (enclosingRectangle = bitMatrix.getEnclosingRectangle()) == null) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            int i7 = enclosingRectangle[0];
            int i8 = enclosingRectangle[1];
            int i9 = enclosingRectangle[2];
            i = enclosingRectangle[3];
            i4 = i9;
            i3 = i7;
            i2 = i8;
            z2 = false;
        }
        if (z2) {
            i4 = bitMatrix.getWidth();
            i5 = bitMatrix.getHeight();
            i2 = 0;
        } else {
            i5 = i;
            i6 = i3;
        }
        int i10 = i4;
        int i11 = i6 + i10;
        int i12 = i2 + i5;
        int[] iArr = new int[i10 * i5];
        for (int i13 = i2; i13 < i12; i13++) {
            int i14 = (i13 - i2) * i10;
            for (int i15 = i6; i15 < i11; i15++) {
                iArr[(i14 + i15) - i6] = bitMatrix.get(i15, i13) ? -16777216 : -1;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i10, i5, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, i10, 0, 0, i10, i5);
        return createBitmap;
    }
}
