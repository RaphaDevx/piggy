package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR*\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/SubView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "", "value", "c", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "text", TypedValues.Custom.S_COLOR, "getColor", "()I", "setColor", "(I)V", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class SubView extends FrameLayout {

    @Deprecated
    public static final int MAX_TEXT_LENGTH = 4;
    private final SubDrawable a;
    private final TextView b;

    /* renamed from: c, reason: from kotlin metadata */
    private String text;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SubView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final int getColor() {
        return this.a.getSubColor();
    }

    public final String getText() {
        return this.text;
    }

    public final void setColor(int i) {
        this.a.setSubColor(i);
        this.b.setTextColor(MapEditorUtils.INSTANCE.uiElementColorOnBackground(i));
    }

    public final void setText(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.text = value;
        this.b.setText(MapEditorUtils.INSTANCE.ellipsizeTextIfTooLong(value, 4));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SubView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ SubView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        SubDrawable subDrawable = new SubDrawable(context);
        this.a = subDrawable;
        TextView textView = new TextView(context);
        textView.setGravity(17);
        int pxFromDp = PixelExtensionsKt.pxFromDp(8, context);
        int pxFromDp2 = PixelExtensionsKt.pxFromDp(2, context);
        textView.setPadding(pxFromDp, pxFromDp2, pxFromDp, pxFromDp2);
        textView.setTextSize(2, 12.0f);
        textView.setTypeface(Typeface.create(null, 600, false));
        textView.setMaxLines(1);
        textView.setTextColor(MapEditorUtils.INSTANCE.uiElementColorOnBackground(getColor()));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 81;
        addView(textView, layoutParams);
        this.b = textView;
        this.text = "";
        setBackground(subDrawable);
        int pxFromDp3 = (int) PixelExtensionsKt.pxFromDp(66.0f, context);
        setMinimumWidth(pxFromDp3);
        setMinimumHeight(pxFromDp3);
    }
}
