package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m extends FrameLayout {
    public static final int d = PixelExtensionsKt.pxFromDp$default(16, (Context) null, 1, (Object) null);
    public static final MainThreadHelperImpl e = new MainThreadHelperImpl();
    public boolean a;
    public final ImageView b;
    public final ImageView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(Context context, n statusShowingMode) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(statusShowingMode, "statusShowingMode");
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.sc_ic_status_none);
        imageView.setAdjustViewBounds(true);
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.b = imageView;
        int i = d;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i);
        layoutParams.gravity = 17;
        addView(imageView, layoutParams);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageResource(R.drawable.sc_ic_status_none);
        imageView2.setAdjustViewBounds(true);
        imageView2.setAlpha(0.0f);
        imageView2.setScaleX(0.0f);
        imageView2.setScaleY(0.0f);
        Intrinsics.checkNotNullParameter(imageView2, "<set-?>");
        this.c = imageView2;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        addView(a(), layoutParams2);
        int ordinal = statusShowingMode.ordinal();
        if (ordinal == 0) {
            imageView.setVisibility(0);
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            imageView.setVisibility(8);
        }
        a(false);
    }

    public final ImageView a() {
        ImageView imageView = this.c;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("statusIcon");
        return null;
    }

    public final void a(boolean z, boolean z2) {
        if (z && a().getAlpha() == 1.0f) {
            return;
        }
        if (z || a().getAlpha() != 0.0f) {
            ImageView a = a();
            i iVar = i.a;
            MainThreadHelperImpl mainThreadHelperImpl = e;
            mainThreadHelperImpl.runOnMainThread(new j(a, z, z2, iVar));
            mainThreadHelperImpl.runOnMainThread(new l(a(), z, z2, k.a));
        }
    }

    public final void a(boolean z) {
        if (this.a) {
            this.a = false;
            e.runOnMainThread(new j(this, false, z, i.a));
            a(false, z);
        }
    }
}
