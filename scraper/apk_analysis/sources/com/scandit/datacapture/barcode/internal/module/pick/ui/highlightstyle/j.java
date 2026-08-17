package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements BarcodePickIcon {
    public final Bitmap a;
    public BitmapDrawable b;

    public j(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.a = bitmap;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final Drawable a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BitmapDrawable bitmapDrawable = this.b;
        if (bitmapDrawable != null) {
            return bitmapDrawable;
        }
        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(context.getResources(), this.a);
        this.b = bitmapDrawable2;
        return bitmapDrawable2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(j.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BitmapIcon");
        return Intrinsics.areEqual(this.a, ((j) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final String toJson() {
        return BitmapExtensionsKt.toBase64(this.a);
    }
}
