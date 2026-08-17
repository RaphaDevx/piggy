package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArBodyElementRowDataHolder;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class n extends RelativeLayout {
    public final k a;
    public final ImageView b;
    public final ImageView c;
    public final TextView d;
    public final RelativeLayout.LayoutParams e;
    public final RelativeLayout.LayoutParams f;
    public final RelativeLayout.LayoutParams g;
    public i h;
    public static final /* synthetic */ KProperty[] j = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(n.class, "data", "getData()Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArBodyElementRowDataHolder;", 0)};
    public static final h i = new h();
    public static final Lazy k = LazyKt.lazy(f.a);
    public static final Lazy l = LazyKt.lazy(g.a);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(Context context, BarcodeArBodyElementRowDataHolder initialRowDataHolder) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(initialRowDataHolder, "initialRowDataHolder");
        Delegates delegates = Delegates.INSTANCE;
        this.a = new k(initialRowDataHolder, this);
        ImageView imageView = new ImageView(context);
        imageView.setId(R.id.sc_ar_info_annotation_body_left_icon);
        this.b = imageView;
        ImageView imageView2 = new ImageView(context);
        imageView2.setId(R.id.sc_ar_info_annotation_body_right_icon);
        this.c = imageView2;
        TextView textView = new TextView(context);
        textView.setId(R.id.sc_ar_info_annotation_body_text_view);
        this.d = textView;
        View view = new View(context);
        view.setId(R.id.sc_ar_info_annotation_body_center_guide);
        i.getClass();
        Lazy lazy = k;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        layoutParams.addRule(15);
        this.e = layoutParams;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        layoutParams2.addRule(15);
        this.f = layoutParams2;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(15);
        this.g = layoutParams3;
        addView(imageView, layoutParams);
        addView(imageView2, layoutParams2);
        addView(textView, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(1, 1);
        layoutParams4.addRule(14);
        Unit unit = Unit.INSTANCE;
        addView(view, layoutParams4);
        b();
    }

    public final BarcodeArBodyElementRowDataHolder a() {
        return (BarcodeArBodyElementRowDataHolder) this.a.getValue(this, j[0]);
    }

    public final void b() {
        String text;
        int i2;
        BarcodeArInfoAnnotationBodyComponent bodyElement = a().getBodyElement();
        CharSequence styledText = bodyElement.getStyledText();
        if (styledText == null || styledText.length() == 0) {
            this.d.setText(bodyElement.getText());
            this.d.setTextColor(bodyElement.getTextColor());
            this.d.setTypeface(bodyElement.getTypeface());
            this.d.setTextSize(bodyElement.getTextSize());
            this.d.setTextAlignment(bodyElement.getTextAlignment());
        } else {
            this.d.setText(bodyElement.getStyledText());
        }
        a(this.b, a().getBodyElement().getLeftIcon(), !a().isEntireAnnotationTappable() && a().getBodyElement().isLeftIconTappable(), new l(this));
        a(this.c, a().getBodyElement().getRightIcon(), !a().isEntireAnnotationTappable() && a().getBodyElement().isRightIconTappable(), new m(this));
        BarcodeArInfoAnnotationBodyComponent bodyElement2 = a().getBodyElement();
        boolean z = bodyElement2.getLeftIcon() != null;
        boolean z2 = bodyElement2.getRightIcon() != null;
        CharSequence styledText2 = bodyElement2.getStyledText();
        boolean z3 = ((styledText2 == null || styledText2.length() == 0) && ((text = bodyElement2.getText()) == null || text.length() == 0)) ? false : true;
        i iVar = new i(z, z2, z3, a().getWidth());
        if (!Intrinsics.areEqual(iVar, this.h)) {
            RelativeLayout.LayoutParams layoutParams = this.e;
            layoutParams.removeRule(0);
            layoutParams.removeRule(1);
            layoutParams.removeRule(14);
            layoutParams.removeRule(9);
            layoutParams.removeRule(11);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            RelativeLayout.LayoutParams layoutParams2 = this.f;
            layoutParams2.removeRule(0);
            layoutParams2.removeRule(1);
            layoutParams2.removeRule(14);
            layoutParams2.removeRule(9);
            layoutParams2.removeRule(11);
            layoutParams2.leftMargin = 0;
            layoutParams2.rightMargin = 0;
            RelativeLayout.LayoutParams layoutParams3 = this.g;
            layoutParams3.removeRule(0);
            layoutParams3.removeRule(1);
            layoutParams3.removeRule(14);
            layoutParams3.removeRule(9);
            layoutParams3.removeRule(11);
            layoutParams3.leftMargin = 0;
            layoutParams3.rightMargin = 0;
            if (z3) {
                this.e.addRule(9, z ? -1 : 0);
                this.f.addRule(11, z2 ? -1 : 0);
                int i3 = j.a[a().getWidth().ordinal()];
                if (i3 == 1 || i3 == 2) {
                    i2 = 8;
                } else {
                    if (i3 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i2 = 12;
                }
                int pxFromDp$default = PixelExtensionsKt.pxFromDp$default(i2, (Context) null, 1, (Object) null);
                RelativeLayout.LayoutParams layoutParams4 = this.g;
                layoutParams4.leftMargin = !z ? 0 : pxFromDp$default;
                if (!z2) {
                    pxFromDp$default = 0;
                }
                layoutParams4.rightMargin = pxFromDp$default;
                if (z) {
                    layoutParams4.addRule(1, R.id.sc_ar_info_annotation_body_left_icon);
                }
                if (z2) {
                    this.g.addRule(0, R.id.sc_ar_info_annotation_body_right_icon);
                }
            } else if (z && z2) {
                this.e.addRule(0, R.id.sc_ar_info_annotation_body_center_guide);
                this.f.addRule(1, R.id.sc_ar_info_annotation_body_center_guide);
                RelativeLayout.LayoutParams layoutParams5 = this.e;
                i.getClass();
                Lazy lazy = l;
                layoutParams5.rightMargin = ((Number) lazy.getValue()).intValue() / 2;
                this.f.leftMargin = ((Number) lazy.getValue()).intValue() / 2;
            } else if (z) {
                this.e.addRule(14);
            } else if (z2) {
                this.f.addRule(14);
            }
            this.h = iVar;
        }
        int paddingAndCornerRadius$scandit_barcode_capture = (int) a().getWidth().toPaddingAndCornerRadius$scandit_barcode_capture();
        ViewGroup.LayoutParams layoutParams6 = getLayoutParams();
        LinearLayout.LayoutParams layoutParams7 = layoutParams6 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams6 : null;
        if (layoutParams7 != null) {
            layoutParams7.setMargins(paddingAndCornerRadius$scandit_barcode_capture, 0, paddingAndCornerRadius$scandit_barcode_capture, 0);
        }
        BarcodeArBodyElementRowDataHolder a = a();
        ImageView imageView = this.b;
        boolean isEntireAnnotationTappable = a.isEntireAnnotationTappable();
        int i4 = R.string.sc_barcode_ar_annotation_info_body_left_icon_content_description;
        if (isEntireAnnotationTappable) {
            imageView.setImportantForAccessibility(2);
            imageView.setContentDescription(null);
        } else {
            imageView.setImportantForAccessibility(1);
            imageView.setContentDescription(getResources().getString(i4));
        }
        ImageView imageView2 = this.c;
        boolean isEntireAnnotationTappable2 = a.isEntireAnnotationTappable();
        int i5 = R.string.sc_barcode_ar_annotation_info_body_right_icon_content_description;
        if (isEntireAnnotationTappable2) {
            imageView2.setImportantForAccessibility(2);
            imageView2.setContentDescription(null);
        } else {
            imageView2.setImportantForAccessibility(1);
            imageView2.setContentDescription(getResources().getString(i5));
        }
        TextView textView = this.d;
        if (a.isEntireAnnotationTappable()) {
            textView.setImportantForAccessibility(2);
        } else {
            textView.setContentDescription(textView.getResources().getString(R.string.sc_barcode_ar_annotation_info_body_content_description, a.getBodyElement().getText()));
        }
    }

    public static final void a(Function0 onClick, View view) {
        Intrinsics.checkNotNullParameter(onClick, "$onClick");
        onClick.invoke();
    }

    public final void a(ImageView imageView, ScanditIcon scanditIcon, boolean z, final Function0 function0) {
        Drawable drawable;
        imageView.setVisibility(scanditIcon == null ? 8 : 0);
        if (scanditIcon != null) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            drawable = ScanditIconUtilsKt.getDrawable(scanditIcon, context);
        } else {
            drawable = null;
        }
        if (z) {
            ViewExtensionsKt.setRippleWrappedDrawable(imageView, drawable);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.n$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    n.a(Function0.this, view);
                }
            });
        } else {
            imageView.setOnClickListener(null);
            imageView.setImageDrawable(drawable);
        }
        imageView.setClickable(z);
    }
}
