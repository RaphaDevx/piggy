package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.view.View;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.core.internal.sdk.ui.interpolators.EaseInOutSineInterpolator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class Y extends View {
    public boolean a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(Context context) {
        super(context);
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        int i2 = X.a[NativeBarcodeCountBasicOverlayColorScheme.DEFAULT.ordinal()];
        if (i2 == 1) {
            i = R.drawable.sc_ic_barcode_unscanned_background;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.drawable.sc_ic_barcode_unscanned_background_alt;
        }
        setBackgroundResource(i);
    }

    public final void a() {
        animate().setInterpolator(new EaseInOutSineInterpolator()).setDuration(1000L).scaleX(1.0f).scaleY(1.0f).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.Y$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Y.a(Y.this);
            }
        }).start();
    }

    public final void b() {
        onAttachedToWindow();
        if (this.a) {
            return;
        }
        this.a = true;
        setScaleX(0.667f);
        setScaleY(0.667f);
        a();
    }

    public final void c() {
        onDetachedFromWindow();
        clearAnimation();
        this.a = false;
    }

    public static final void a(Y this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.a) {
            this$0.a = true;
            this$0.setScaleX(0.667f);
            this$0.setScaleY(0.667f);
            this$0.a();
        }
    }
}
