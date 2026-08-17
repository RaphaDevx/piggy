package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconSerializer;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n implements BarcodePickIcon {
    public final ScanditIcon a;

    public n(ScanditIcon scanditIcon) {
        Intrinsics.checkNotNullParameter(scanditIcon, "scanditIcon");
        this.a = scanditIcon;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final Drawable a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ScanditIconUtilsKt.getDrawable(this.a, context);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(n.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.PickScanditIcon");
        return Intrinsics.areEqual(this.a, ((n) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon
    public final String toJson() {
        return ScanditIconSerializer.toJson(this.a);
    }
}
