package com.scandit.datacapture.frameworks.barcode.count.data.defaults;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarDefaults;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCountToolbarSettingsDefaults.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountToolbarSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "context", "Landroid/content/Context;", "toolbarDefaults", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountToolbarDefaults;", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountToolbarDefaults;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountToolbarSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_AUDIO_BUTTON_CONTENT_DESCRIPTION = "audioButtonContentDescription";
    private static final String FIELD_AUDIO_OFF_BUTTON_TEXT = "audioOffButtonText";
    private static final String FIELD_AUDIO_ON_BUTTON_TEXT = "audioOnButtonText";
    private static final String FIELD_COLOR_SCHEME_BUTTON_CONTENT_DESCRIPTION = "colorSchemeButtonContentDescription";
    private static final String FIELD_COLOR_SCHEME_OFF_BUTTON_TEXT = "colorSchemeOffButtonText";
    private static final String FIELD_COLOR_SCHEME_ON_BUTTON_TEXT = "colorSchemeOnButtonText";
    private static final String FIELD_STRAP_MODE_BUTTON_CONTENT_DESCRIPTION = "strapModeButtonContentDescription";
    private static final String FIELD_STRAP_MODE_OFF_BUTTON_TEXT = "strapModeOffButtonText";
    private static final String FIELD_STRAP_MODE_ON_BUTTON_TEXT = "strapModeOnButtonText";
    private static final String FIELD_VIBRATION_BUTTON_CONTENT_DESCRIPTION = "vibrationButtonContentDescription";
    private static final String FIELD_VIBRATION_OFF_BUTTON_TEXT = "vibrationOffButtonText";
    private static final String FIELD_VIBRATION_ON_BUTTON_TEXT = "vibrationOnButtonText";
    private final Context context;
    private final BarcodeCountToolbarDefaults toolbarDefaults;

    public /* synthetic */ BarcodeCountToolbarSettingsDefaults(Context context, BarcodeCountToolbarDefaults barcodeCountToolbarDefaults, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, barcodeCountToolbarDefaults);
    }

    @JvmStatic
    public static final Map<String, Object> get(Context context) {
        return INSTANCE.get(context);
    }

    private BarcodeCountToolbarSettingsDefaults(Context context, BarcodeCountToolbarDefaults barcodeCountToolbarDefaults) {
        this.context = context;
        this.toolbarDefaults = barcodeCountToolbarDefaults;
    }

    /* synthetic */ BarcodeCountToolbarSettingsDefaults(Context context, BarcodeCountToolbarDefaults barcodeCountToolbarDefaults, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new BarcodeCountToolbarDefaults() : barcodeCountToolbarDefaults);
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_AUDIO_ON_BUTTON_TEXT, this.toolbarDefaults.audioOnButtonText(this.context)), TuplesKt.to(FIELD_AUDIO_OFF_BUTTON_TEXT, this.toolbarDefaults.audioOffButtonText(this.context)), TuplesKt.to(FIELD_AUDIO_BUTTON_CONTENT_DESCRIPTION, this.toolbarDefaults.audioButtonContentDescription(this.context)), TuplesKt.to(FIELD_VIBRATION_ON_BUTTON_TEXT, this.toolbarDefaults.vibrationOnButtonText(this.context)), TuplesKt.to(FIELD_VIBRATION_OFF_BUTTON_TEXT, this.toolbarDefaults.vibrationOffButtonText(this.context)), TuplesKt.to(FIELD_VIBRATION_BUTTON_CONTENT_DESCRIPTION, this.toolbarDefaults.vibrationButtonContentDescription(this.context)), TuplesKt.to(FIELD_STRAP_MODE_ON_BUTTON_TEXT, this.toolbarDefaults.strapModeOnButtonText(this.context)), TuplesKt.to(FIELD_STRAP_MODE_OFF_BUTTON_TEXT, this.toolbarDefaults.strapModeOffButtonText(this.context)), TuplesKt.to(FIELD_STRAP_MODE_BUTTON_CONTENT_DESCRIPTION, this.toolbarDefaults.strapModeButtonContentDescription(this.context)), TuplesKt.to(FIELD_COLOR_SCHEME_ON_BUTTON_TEXT, this.toolbarDefaults.colorSchemeOnButtonText(this.context)), TuplesKt.to(FIELD_COLOR_SCHEME_OFF_BUTTON_TEXT, this.toolbarDefaults.colorSchemeOffButtonText(this.context)), TuplesKt.to(FIELD_COLOR_SCHEME_BUTTON_CONTENT_DESCRIPTION, this.toolbarDefaults.colorSchemeButtonContentDescription(this.context)));
    }

    /* compiled from: BarcodeCountToolbarSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountToolbarSettingsDefaults$Companion;", "", "()V", "FIELD_AUDIO_BUTTON_CONTENT_DESCRIPTION", "", "FIELD_AUDIO_OFF_BUTTON_TEXT", "FIELD_AUDIO_ON_BUTTON_TEXT", "FIELD_COLOR_SCHEME_BUTTON_CONTENT_DESCRIPTION", "FIELD_COLOR_SCHEME_OFF_BUTTON_TEXT", "FIELD_COLOR_SCHEME_ON_BUTTON_TEXT", "FIELD_STRAP_MODE_BUTTON_CONTENT_DESCRIPTION", "FIELD_STRAP_MODE_OFF_BUTTON_TEXT", "FIELD_STRAP_MODE_ON_BUTTON_TEXT", "FIELD_VIBRATION_BUTTON_CONTENT_DESCRIPTION", "FIELD_VIBRATION_OFF_BUTTON_TEXT", "FIELD_VIBRATION_ON_BUTTON_TEXT", "get", "", "context", "Landroid/content/Context;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new BarcodeCountToolbarSettingsDefaults(context, new BarcodeCountToolbarDefaults(), null).toMap();
        }
    }
}
