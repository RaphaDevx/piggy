package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArHeaderFooterElementDataHolder;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class u extends LinearLayout {
    public static final Lazy c = LazyKt.lazy(r.a);
    public final TextView a;
    public final ImageView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setOrientation(0);
        setGravity(17);
        ImageView imageView = new ImageView(context);
        Lazy lazy = c;
        addView(imageView, new LinearLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue()));
        this.b = imageView;
        TextView textView = new TextView(context);
        addView(textView, new LinearLayout.LayoutParams(-2, -2));
        this.a = textView;
    }

    public static final void a(BarcodeArHeaderFooterElementDataHolder barcodeArHeaderFooterElementDataHolder, View view) {
        barcodeArHeaderFooterElementDataHolder.getElementTapped().invoke();
    }

    public final void a(final BarcodeArHeaderFooterElementDataHolder barcodeArHeaderFooterElementDataHolder) {
        int pxFromDp$default;
        if (barcodeArHeaderFooterElementDataHolder == null) {
            setVisibility(8);
            return;
        }
        TextView textView = this.a;
        textView.setText(barcodeArHeaderFooterElementDataHolder.getText());
        textView.setTypeface(barcodeArHeaderFooterElementDataHolder.getTypeface());
        textView.setTextSize(barcodeArHeaderFooterElementDataHolder.getTextSize());
        textView.setTextColor(barcodeArHeaderFooterElementDataHolder.getTextColor());
        if (!barcodeArHeaderFooterElementDataHolder.isEntireAnnotationTappable()) {
            setContentDescription(getResources().getString(R.string.sc_barcode_ar_annotation_info_header_footer_content_description, barcodeArHeaderFooterElementDataHolder.getText()));
        }
        if (barcodeArHeaderFooterElementDataHolder.getIcon() != null) {
            ImageView imageView = this.b;
            ScanditIcon icon = barcodeArHeaderFooterElementDataHolder.getIcon();
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            imageView.setImageDrawable(ScanditIconUtilsKt.getDrawable(icon, context));
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
            this.b.setImageDrawable(null);
        }
        String text = barcodeArHeaderFooterElementDataHolder.getText();
        boolean z = text == null || text.length() == 0;
        boolean z2 = barcodeArHeaderFooterElementDataHolder.getIcon() != null;
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.rightMargin = 0;
        } else {
            layoutParams2 = null;
        }
        if (!z && z2 && layoutParams2 != null) {
            int i = t.a[barcodeArHeaderFooterElementDataHolder.getWidth().ordinal()];
            if (i == 1) {
                pxFromDp$default = PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null);
            } else if (i == 2) {
                pxFromDp$default = PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null);
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                pxFromDp$default = PixelExtensionsKt.pxFromDp$default(12, (Context) null, 1, (Object) null);
            }
            layoutParams2.rightMargin = pxFromDp$default;
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.u$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                u.a(BarcodeArHeaderFooterElementDataHolder.this, view);
            }
        });
        setClickable(!barcodeArHeaderFooterElementDataHolder.isEntireAnnotationTappable());
        if (isClickable()) {
            ViewExtensionsKt.addRippleForeground$default(this, null, 1, null);
        } else {
            setForeground(null);
        }
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        int paddingAndCornerRadius$scandit_barcode_capture = (int) barcodeArHeaderFooterElementDataHolder.getWidth().toPaddingAndCornerRadius$scandit_barcode_capture();
        setPadding(paddingAndCornerRadius$scandit_barcode_capture, paddingAndCornerRadius$scandit_barcode_capture, paddingAndCornerRadius$scandit_barcode_capture, paddingAndCornerRadius$scandit_barcode_capture);
        setLayoutParams(layoutParams3);
        setBackgroundColor(barcodeArHeaderFooterElementDataHolder.getBackgroundColor());
        setVisibility(0);
    }
}
