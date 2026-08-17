package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o implements BarcodePickIcon {
    public final int a;
    public Drawable b;

    public o(int i) {
        this.a = i;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final Drawable a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = this.b;
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = context.getDrawable(this.a);
        this.b = drawable2;
        return drawable2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(o.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.ResourceIcon");
        return this.a == ((o) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final String toJson() {
        Bitmap a = h.a(this, AppAndroidEnvironment.INSTANCE.getApplicationContext());
        if (a != null) {
            return BitmapExtensionsKt.toBase64(a);
        }
        return null;
    }
}
