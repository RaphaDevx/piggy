package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class L extends FrameLayout {
    public static final /* synthetic */ int h = 0;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.status.n a;
    public final C0317u b;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.status.m c;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.status.a d;
    public I e;
    public com.scandit.datacapture.barcode.internal.module.count.ui.status.b f;
    public W g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(Context context, com.scandit.datacapture.barcode.internal.module.count.ui.status.n statusShowingMode) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(statusShowingMode, "statusShowingMode");
        this.a = statusShowingMode;
        int i = AbstractC0301d.a;
        Intrinsics.checkNotNullParameter(context, "context");
        C0317u c0317u = new C0317u(context);
        this.b = c0317u;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(statusShowingMode, "statusShowingMode");
        com.scandit.datacapture.barcode.internal.module.count.ui.status.m mVar = new com.scandit.datacapture.barcode.internal.module.count.ui.status.m(context, statusShowingMode);
        this.c = mVar;
        com.scandit.datacapture.barcode.internal.module.count.ui.status.a aVar = new com.scandit.datacapture.barcode.internal.module.count.ui.status.a(context, null, 0);
        this.d = aVar;
        this.e = I.a;
        this.f = com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a;
        mVar.setAlpha(0.0f);
        addView(c0317u, new FrameLayout.LayoutParams(-1, -1));
        addView(mVar, new FrameLayout.LayoutParams(-1, -1));
        addView(aVar, new FrameLayout.LayoutParams(-1, -1));
        int ordinal = statusShowingMode.ordinal();
        if (ordinal == 0) {
            a(aVar, false);
        } else {
            if (ordinal != 1) {
                return;
            }
            b(aVar, false);
        }
    }

    public final void a(z clusterIndicatorInfo) {
        Intrinsics.checkNotNullParameter(clusterIndicatorInfo, "clusterIndicatorInfo");
        this.g = W.b;
        int i = AbstractC0301d.a;
        C0317u normalView = this.b;
        Intrinsics.checkNotNullParameter(normalView, "normalView");
        Intrinsics.checkNotNullParameter(clusterIndicatorInfo, "clusterIndicatorInfo");
        int ordinal = AbstractC0301d.a(clusterIndicatorInfo.g, clusterIndicatorInfo.j).ordinal();
        if (ordinal == 0) {
            Brush brush = clusterIndicatorInfo.e;
            normalView.a(Integer.valueOf(R.drawable.sc_ic_barcode_dot), Integer.valueOf(brush.getFillColor()), clusterIndicatorInfo.h);
        } else if (ordinal == 1) {
            W w = clusterIndicatorInfo.j;
            NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme = clusterIndicatorInfo.h;
            normalView.a(AbstractC0301d.a(w, nativeBarcodeCountBasicOverlayColorScheme), nativeBarcodeCountBasicOverlayColorScheme);
        } else if (ordinal == 2) {
            W w2 = clusterIndicatorInfo.j;
            Brush brush2 = clusterIndicatorInfo.e;
            NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme2 = clusterIndicatorInfo.h;
            normalView.a(AbstractC0301d.a(w2, nativeBarcodeCountBasicOverlayColorScheme2), Integer.valueOf(brush2.getFillColor()), nativeBarcodeCountBasicOverlayColorScheme2);
        }
        Y y = normalView.b;
        if (y == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
            y = null;
        }
        y.setVisibility(8);
        normalView.a(R.string.sc_barcode_count_indicator_content_description_cluster_data, clusterIndicatorInfo.i, R.string.sc_barcode_count_scan_status_scanned);
    }

    public final void b(boolean z) {
        J a = a();
        int i = a == null ? -1 : K.a[a.ordinal()];
        if (i == 1) {
            a(this.b, z);
            com.scandit.datacapture.barcode.internal.module.count.ui.status.m mVar = this.c;
            if (mVar.a) {
                return;
            }
            mVar.a = true;
            com.scandit.datacapture.barcode.internal.module.count.ui.status.m.e.runOnMainThread(new com.scandit.datacapture.barcode.internal.module.count.ui.status.j(mVar, true, z, new com.scandit.datacapture.barcode.internal.module.count.ui.status.h(mVar, z)));
            return;
        }
        if (i == 2) {
            b(this.b, z);
            this.c.a(z);
        } else {
            if (i != 3) {
                return;
            }
            a(this.b, z);
        }
    }

    public static void b(View view, boolean z) {
        if (view.getAlpha() == 1.0f) {
            return;
        }
        if (z) {
            view.animate().setDuration(500L).alpha(1.0f).start();
        } else {
            view.setAlpha(1.0f);
        }
    }

    public final void a(boolean z) {
        a(this.d, z);
        if (a() == J.a) {
            this.c.a(true, z);
        }
    }

    public final J a() {
        I i = this.e;
        I i2 = I.a;
        if (i == i2 && this.a == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b) {
            com.scandit.datacapture.barcode.internal.module.count.ui.status.b bVar = this.f;
            if (!Intrinsics.areEqual(bVar, com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a) && !Intrinsics.areEqual(bVar, com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a)) {
                if (!(bVar instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (((com.scandit.datacapture.barcode.internal.module.count.ui.status.e) bVar).a != BarcodeCountStatus.NONE) {
                    return J.a;
                }
                return J.b;
            }
            return J.a;
        }
        if (i == i2 && this.a == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.a) {
            return J.a;
        }
        if (i == I.b) {
            return J.c;
        }
        return null;
    }

    public static void a(View view, boolean z) {
        if (view.getAlpha() == 0.0f) {
            return;
        }
        if (z) {
            view.animate().setDuration(500L).alpha(0.0f).start();
        } else {
            view.setAlpha(0.0f);
        }
    }
}
