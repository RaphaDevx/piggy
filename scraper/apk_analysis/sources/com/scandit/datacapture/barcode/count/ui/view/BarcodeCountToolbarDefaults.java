package com.scandit.datacapture.barcode.count.ui.view;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountToolbarDefaults;", "", "()V", "audioButtonContentDescription", "", "context", "Landroid/content/Context;", "audioOffButtonText", "audioOnButtonText", "colorSchemeButtonContentDescription", "colorSchemeOffButtonText", "colorSchemeOnButtonText", "discloseButtonContentDescription", "strapModeButtonContentDescription", "strapModeOffButtonText", "strapModeOnButtonText", "vibrationButtonContentDescription", "vibrationOffButtonText", "vibrationOnButtonText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountToolbarDefaults {
    public final String audioButtonContentDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_audio_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String audioOffButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_audio_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String audioOnButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_audio_on);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String colorSchemeButtonContentDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_color_filter_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String colorSchemeOffButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_color_filter);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String colorSchemeOnButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_color_filter);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String discloseButtonContentDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_disclose_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String strapModeButtonContentDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_strap_mode_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String strapModeOffButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_strap_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String strapModeOnButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_strap_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String vibrationButtonContentDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_haptic_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String vibrationOffButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_haptic_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String vibrationOnButtonText(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.sc_barcodeCount_toolbar_haptic_on);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
