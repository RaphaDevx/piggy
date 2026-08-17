package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.u, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0317u extends FrameLayout {
    public final ImageView a;
    public final Y b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0317u(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setClickable(true);
        setFocusable(true);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        Y y = new Y(context2);
        this.b = y;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(y, layoutParams);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.sc_ic_barcode_scanned);
        imageView.setAdjustViewBounds(true);
        this.a = imageView;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        addView(imageView, layoutParams2);
    }

    public final void a(Integer num, Integer num2, NativeBarcodeCountBasicOverlayColorScheme colorScheme) {
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        Y y = null;
        if (num == null || num2 == null) {
            ImageView imageView = this.a;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
                imageView = null;
            }
            imageView.setImageBitmap(null);
            ImageView imageView2 = this.a;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
                imageView2 = null;
            }
            imageView2.clearColorFilter();
        } else {
            ImageView imageView3 = this.a;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
                imageView3 = null;
            }
            imageView3.setImageResource(num.intValue());
            ImageView imageView4 = this.a;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
                imageView4 = null;
            }
            imageView4.setColorFilter(new LightingColorFilter(-1, num2.intValue()));
        }
        Y y2 = this.b;
        if (y2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
        } else {
            y = y2;
        }
        y.getClass();
        Intrinsics.checkNotNullParameter(colorScheme, "<set-?>");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Y y = this.b;
        if (y == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
            y = null;
        }
        y.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Y y = this.b;
        if (y == null) {
            Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
            y = null;
        }
        y.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.Y] */
    public final void a(Integer num, NativeBarcodeCountBasicOverlayColorScheme colorScheme) {
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        ImageView imageView = null;
        if (num == null) {
            ImageView imageView2 = this.a;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
                imageView2 = null;
            }
            imageView2.setImageBitmap(null);
            ImageView imageView3 = this.a;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
            } else {
                imageView = imageView3;
            }
            imageView.clearColorFilter();
            return;
        }
        ImageView imageView4 = this.a;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
            imageView4 = null;
        }
        imageView4.setImageResource(num.intValue());
        ImageView imageView5 = this.a;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(BarcodePickDeserializer.FIELD_ICON);
            imageView5 = null;
        }
        imageView5.clearColorFilter();
        ?? r3 = this.b;
        if (r3 == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
        } else {
            imageView = r3;
        }
        imageView.getClass();
        Intrinsics.checkNotNullParameter(colorScheme, "<set-?>");
    }

    public final void a(int i, String str, int i2) {
        setContentDescription(getResources().getString(i, str, getResources().getString(i2)));
    }
}
