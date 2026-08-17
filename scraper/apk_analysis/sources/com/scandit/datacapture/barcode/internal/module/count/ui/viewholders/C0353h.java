package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountScreenRatioForUi;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0353h implements BarcodeCountViewUiLayoutHelper {
    public static final int e = PixelExtensionsKt.pxFromDp$default(25, (Context) null, 1, (Object) null);
    public static final int f = PixelExtensionsKt.pxFromDp$default(39, (Context) null, 1, (Object) null);
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final Lazy l;
    public static final Lazy m;
    public static final int n;
    public static final int o;
    public static final int p;
    public static final int q;
    public View a;
    public Size b = new Size(0, 0);
    public Integer c;
    public Integer d;

    static {
        int i2 = AbstractC0341b.c / 2;
        g = i2;
        int pxFromDp$default = PixelExtensionsKt.pxFromDp$default(80, (Context) null, 1, (Object) null);
        h = pxFromDp$default;
        i = i2 - (pxFromDp$default / 2);
        j = PixelExtensionsKt.pxFromDp$default(100, (Context) null, 1, (Object) null);
        k = PixelExtensionsKt.pxFromDp$default(200, (Context) null, 1, (Object) null);
        l = LazyKt.lazy(C0347e.a);
        m = LazyKt.lazy(C0345d.a);
        n = PixelExtensionsKt.pxFromDp$default(16, (Context) null, 1, (Object) null);
        o = PixelExtensionsKt.pxFromDp$default(360, (Context) null, 1, (Object) null);
        p = PixelExtensionsKt.pxFromDp$default(32, (Context) null, 1, (Object) null);
        q = PixelExtensionsKt.pxFromDp$default(480, (Context) null, 1, (Object) null);
        PixelExtensionsKt.pxFromDp$default(12, (Context) null, 1, (Object) null);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final void a(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.a = relativeLayout;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int b() {
        int height;
        int i2;
        Context context = k().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            return k().getHeight();
        }
        int i3 = AbstractC0351g.a[h().ordinal()];
        if (i3 == 1) {
            height = k().getHeight() - c();
            i2 = AbstractC0341b.c;
        } else if (i3 == 2) {
            height = k().getHeight();
            i2 = j() / 2;
        } else {
            if (i3 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            height = k().getHeight();
            i2 = j() / 2;
        }
        return height - i2;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int c() {
        int intValue;
        Integer num = this.c;
        if (num != null) {
            return num.intValue();
        }
        int j2 = j();
        int i2 = AbstractC0351g.a[h().ordinal()];
        if (i2 == 1) {
            intValue = (j2 - i) + ((Number) l.getValue()).intValue();
        } else if (i2 == 2) {
            intValue = (j2 - AbstractC0341b.c) / 2;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            intValue = ((j2 / 2) - AbstractC0341b.c) / 2;
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(intValue, 0);
        this.c = Integer.valueOf(coerceAtLeast);
        return coerceAtLeast;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final void d() {
        this.c = null;
        this.d = null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final Size e() {
        return this.b;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int f() {
        Context context = k().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return com.scandit.datacapture.barcode.internal.module.extensions.b.a(context) ? f : e;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int g() {
        int height = this.b.getHeight();
        int i2 = q;
        if (height > i2) {
            return p;
        }
        int height2 = this.b.getHeight();
        int i3 = o;
        if (height2 < i3) {
            return n;
        }
        int height3 = this.b.getHeight();
        float f2 = p;
        float f3 = n;
        float f4 = i3;
        return (int) ((((f2 - f3) / (i2 - f4)) * (height3 - f4)) + f3);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final BarcodeCountScreenRatioForUi h() {
        int j2 = j();
        if (j2 < 0 || j2 >= j) {
            return (j2 >= k || j > j2) ? BarcodeCountScreenRatioForUi.LARGE : BarcodeCountScreenRatioForUi.MEDIUM;
        }
        return BarcodeCountScreenRatioForUi.SMALL;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int i() {
        int intValue;
        Integer num = this.d;
        if (num != null) {
            return num.intValue();
        }
        int i2 = AbstractC0351g.a[h().ordinal()];
        if (i2 == 1) {
            intValue = ((Number) l.getValue()).intValue() + h + ((Number) m.getValue()).intValue();
        } else if (i2 == 2) {
            intValue = ((Number) m.getValue()).intValue();
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            intValue = ((Number) m.getValue()).intValue();
        }
        this.d = Integer.valueOf(intValue);
        return intValue;
    }

    public final int j() {
        int height;
        int height2;
        Context context = k().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            height = k().getWidth();
            height2 = this.b.getWidth();
        } else {
            height = k().getHeight();
            height2 = this.b.getHeight();
        }
        return RangesKt.coerceAtLeast(height - height2, 0);
    }

    public final View k() {
        View view = this.a;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final void a(Size previewSize) {
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        this.b = previewSize;
        this.c = null;
        this.d = null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper
    public final int a() {
        return (c() + g) - (AbstractC0341b.b - (PixelExtensionsKt.pxFromDp$default(40, (Context) null, 1, (Object) null) / 2));
    }
}
