package com.scandit.datacapture.barcode.find.ui;

import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ;2\u00020\u0001:\u0001;BY\u0012\b\b\u0003\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rB1\b\u0016\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000eBG\b\u0016\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\u000fBE\b\u0016\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\u001dR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u001f\u001a\u0004\b\"\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\u001f\u001a\u0004\b$\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b%\u0010\u001f\u001a\u0004\b&\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010*R\u0017\u0010\n\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b-\u0010(\u001a\u0004\b.\u0010*R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R$\u0010:\u001a\u0004\u0018\u0001038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006<"}, d2 = {"Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;", "", "", "inListItemColor", "notInListItemColor", "progressBarStartColor", "progressBarFinishColor", "", "soundEnabled", "hapticEnabled", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "<init>", "(IIIIZZZLjava/lang/Integer;)V", "(IIZZ)V", "(IIZZZLjava/lang/Integer;)V", "(IIIIZZ)V", "", "name", "value", "", "_setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "toJson", "()Ljava/lang/String;", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "a", "I", "getInListItemColor", "b", "getNotInListItemColor", "c", "getProgressBarStartColor", "d", "getProgressBarFinishColor", "e", "Z", "getSoundEnabled", "()Z", "f", "getHapticEnabled", "g", "getHardwareTriggerEnabled", "h", "Ljava/lang/Integer;", "getHardwareTriggerKeyCode", "()Ljava/lang/Integer;", "Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "i", "Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "getForcedScreenCategory$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "setForcedScreenCategory$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;)V", "forcedScreenCategory", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFindViewSettings {

    /* renamed from: a, reason: from kotlin metadata */
    private final int inListItemColor;

    /* renamed from: b, reason: from kotlin metadata */
    private final int notInListItemColor;

    /* renamed from: c, reason: from kotlin metadata */
    private final int progressBarStartColor;

    /* renamed from: d, reason: from kotlin metadata */
    private final int progressBarFinishColor;

    /* renamed from: e, reason: from kotlin metadata */
    private final boolean soundEnabled;

    /* renamed from: f, reason: from kotlin metadata */
    private final boolean hapticEnabled;

    /* renamed from: g, reason: from kotlin metadata */
    private final boolean hardwareTriggerEnabled;

    /* renamed from: h, reason: from kotlin metadata */
    private final Integer hardwareTriggerKeyCode;

    /* renamed from: i, reason: from kotlin metadata */
    private /* synthetic */ ScreenSizeCategory forcedScreenCategory;

    public BarcodeFindViewSettings() {
        this(0, 0, 0, 0, false, false, false, null, 255, null);
    }

    public final void _setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (Intrinsics.areEqual(name, "screenCategory") && (value instanceof String)) {
            this.forcedScreenCategory = ScreenSizeCategory.INSTANCE.fromJson((String) value);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(BarcodeFindViewSettings.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.scandit.datacapture.barcode.find.ui.BarcodeFindViewSettings");
        BarcodeFindViewSettings barcodeFindViewSettings = (BarcodeFindViewSettings) other;
        return this.inListItemColor == barcodeFindViewSettings.inListItemColor && this.notInListItemColor == barcodeFindViewSettings.notInListItemColor && this.progressBarStartColor == barcodeFindViewSettings.progressBarStartColor && this.progressBarFinishColor == barcodeFindViewSettings.progressBarFinishColor && this.soundEnabled == barcodeFindViewSettings.soundEnabled && this.hapticEnabled == barcodeFindViewSettings.hapticEnabled && this.hardwareTriggerEnabled == barcodeFindViewSettings.hardwareTriggerEnabled && Intrinsics.areEqual(this.hardwareTriggerKeyCode, barcodeFindViewSettings.hardwareTriggerKeyCode) && this.forcedScreenCategory == barcodeFindViewSettings.forcedScreenCategory;
    }

    /* renamed from: getForcedScreenCategory$scandit_barcode_capture, reason: from getter */
    public final ScreenSizeCategory getForcedScreenCategory() {
        return this.forcedScreenCategory;
    }

    public final boolean getHapticEnabled() {
        return this.hapticEnabled;
    }

    public final boolean getHardwareTriggerEnabled() {
        return this.hardwareTriggerEnabled;
    }

    public final Integer getHardwareTriggerKeyCode() {
        return this.hardwareTriggerKeyCode;
    }

    public final int getInListItemColor() {
        return this.inListItemColor;
    }

    public final int getNotInListItemColor() {
        return this.notInListItemColor;
    }

    public final int getProgressBarFinishColor() {
        return this.progressBarFinishColor;
    }

    public final int getProgressBarStartColor() {
        return this.progressBarStartColor;
    }

    public final boolean getSoundEnabled() {
        return this.soundEnabled;
    }

    public int hashCode() {
        int hashCode = (Boolean.hashCode(this.hardwareTriggerEnabled) + ((Boolean.hashCode(this.hapticEnabled) + ((Boolean.hashCode(this.soundEnabled) + (((((((this.inListItemColor * 31) + this.notInListItemColor) * 31) + this.progressBarStartColor) * 31) + this.progressBarFinishColor) * 31)) * 31)) * 31)) * 31;
        Integer num = this.hardwareTriggerKeyCode;
        int intValue = (hashCode + (num != null ? num.intValue() : 0)) * 31;
        ScreenSizeCategory screenSizeCategory = this.forcedScreenCategory;
        return intValue + (screenSizeCategory != null ? screenSizeCategory.hashCode() : 0);
    }

    public final void setForcedScreenCategory$scandit_barcode_capture(ScreenSizeCategory screenSizeCategory) {
        this.forcedScreenCategory = screenSizeCategory;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("inListItemColor", com.scandit.datacapture.barcode.internal.module.extensions.a.a(this.inListItemColor));
        jSONObject.put("notInListItemColor", com.scandit.datacapture.barcode.internal.module.extensions.a.a(this.notInListItemColor));
        jSONObject.put("progressBarStartColor", com.scandit.datacapture.barcode.internal.module.extensions.a.a(this.progressBarStartColor));
        jSONObject.put("progressBarFinishColor", com.scandit.datacapture.barcode.internal.module.extensions.a.a(this.progressBarFinishColor));
        jSONObject.put("soundEnabled", this.soundEnabled);
        jSONObject.put("hapticEnabled", this.hapticEnabled);
        jSONObject.put(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, this.hardwareTriggerEnabled);
        jSONObject.put(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, this.hardwareTriggerKeyCode);
        ScreenSizeCategory screenSizeCategory = this.forcedScreenCategory;
        if (screenSizeCategory != null) {
            jSONObject.put("screenCategory", screenSizeCategory.getA());
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    public BarcodeFindViewSettings(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, Integer num) {
        this.inListItemColor = i;
        this.notInListItemColor = i2;
        this.progressBarStartColor = i3;
        this.progressBarFinishColor = i4;
        this.soundEnabled = z;
        this.hapticEnabled = z2;
        this.hardwareTriggerEnabled = z3;
        this.hardwareTriggerKeyCode = num;
    }

    public /* synthetic */ BarcodeFindViewSettings(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, Integer num, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? -13712946 : i, (i5 & 2) != 0 ? -1 : i2, (i5 & 4) != 0 ? -449920562 : i3, (i5 & 8) != 0 ? -443031322 : i4, (i5 & 16) != 0 ? true : z, (i5 & 32) == 0 ? z2 : true, (i5 & 64) != 0 ? false : z3, (i5 & 128) != 0 ? null : num);
    }

    public /* synthetic */ BarcodeFindViewSettings(int i, int i2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -13712946 : i, (i3 & 2) != 0 ? -1 : i2, (i3 & 4) != 0 ? true : z, (i3 & 8) != 0 ? true : z2);
    }

    public BarcodeFindViewSettings(int i, int i2, boolean z, boolean z2) {
        this(i, i2, -449920562, -443031322, z, z2, false, (Integer) null);
    }

    public /* synthetic */ BarcodeFindViewSettings(int i, int i2, boolean z, boolean z2, boolean z3, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -13712946 : i, (i3 & 2) != 0 ? -1 : i2, (i3 & 4) != 0 ? true : z, (i3 & 8) == 0 ? z2 : true, (i3 & 16) != 0 ? false : z3, (i3 & 32) != 0 ? null : num);
    }

    public BarcodeFindViewSettings(int i, int i2, boolean z, boolean z2, boolean z3, Integer num) {
        this(i, i2, -449920562, -443031322, z, z2, z3, num);
    }

    public /* synthetic */ BarcodeFindViewSettings(int i, int i2, int i3, int i4, boolean z, boolean z2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? -13712946 : i, (i5 & 2) != 0 ? -1 : i2, (i5 & 4) != 0 ? -449920562 : i3, (i5 & 8) != 0 ? -443031322 : i4, (i5 & 16) != 0 ? true : z, (i5 & 32) != 0 ? true : z2);
    }

    public BarcodeFindViewSettings(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        this(i, i2, i3, i4, z, z2, false, (Integer) null);
    }
}
