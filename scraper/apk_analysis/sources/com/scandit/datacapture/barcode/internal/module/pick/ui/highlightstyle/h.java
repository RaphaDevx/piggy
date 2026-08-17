package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class h {
    public static final Bitmap a(BarcodePickIcon barcodePickIcon, Context context) {
        Intrinsics.checkNotNullParameter(barcodePickIcon, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable a = barcodePickIcon.a(context);
        if (a == null) {
            return null;
        }
        if (a instanceof BitmapDrawable) {
            return ((BitmapDrawable) a).getBitmap();
        }
        a.setBounds(0, 0, a.getIntrinsicWidth(), a.getIntrinsicHeight());
        Bitmap createBitmap = Bitmap.createBitmap(a.getIntrinsicWidth(), a.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        a.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
