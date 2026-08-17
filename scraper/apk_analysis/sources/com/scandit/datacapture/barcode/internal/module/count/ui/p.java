package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.V0;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p extends FrameLayout {
    public final Function0 a;
    public boolean b;
    public boolean c;
    public final ImageView d;
    public final ImageView e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(Context context, V0 onStatusButtonTapped) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onStatusButtonTapped, "onStatusButtonTapped");
        this.a = onStatusButtonTapped;
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.sc_button_status_enabled);
        imageView.setAdjustViewBounds(true);
        addView(imageView, new FrameLayout.LayoutParams(-1, -1));
        this.d = imageView;
        ImageView imageView2 = new ImageView(context);
        imageView2.setImageResource(R.drawable.sc_button_status_active);
        imageView2.setAdjustViewBounds(true);
        imageView2.setAlpha(0.0f);
        addView(imageView2, new FrameLayout.LayoutParams(-1, -1));
        this.e = imageView2;
        setContentDescription(BarcodeCountViewDefaults.INSTANCE.getStatusModeButtonContentDescription());
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.p$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                p.a(p.this, view);
            }
        });
    }

    public static final void a(p this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.a.invoke();
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.b) {
            setAlpha(z ? 1.0f : 0.5f);
        } else {
            this.d.setImageResource(z ? R.drawable.sc_button_status_enabled : R.drawable.sc_button_status_disabled);
        }
    }

    public final void a(boolean z) {
        synchronized (this) {
            if (!this.c) {
                this.c = true;
                this.b = z;
                this.d.animate().setDuration(300L).alpha(this.b ? 0.0f : 1.0f).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.p$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        p.a(p.this);
                    }
                }).start();
                this.e.animate().setDuration(300L).alpha(this.b ? 1.0f : 0.0f).start();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void a(p this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = false;
    }
}
