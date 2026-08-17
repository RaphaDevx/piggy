package com.scandit.datacapture.barcode.ar.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraPositionSerializer;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0016\u001a\u00020\u0004H\u0097\u0001J\u0011\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0097\u0001J\r\u0010\u001b\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u001cJ\u0013\u0010\u001d\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016J\u0006\u0010\"\u001a\u00020#R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\r8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\r8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006$"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewSettings;", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewSettingsProxy;", "()V", "impl", "Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArViewSettings;", "(Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArViewSettings;)V", "<set-?>", "Lcom/scandit/datacapture/core/source/CameraPosition;", "defaultCameraPosition", "getDefaultCameraPosition", "()Lcom/scandit/datacapture/core/source/CameraPosition;", "setDefaultCameraPosition", "(Lcom/scandit/datacapture/core/source/CameraPosition;)V", "", "hapticEnabled", "getHapticEnabled", "()Z", "setHapticEnabled", "(Z)V", "soundEnabled", "getSoundEnabled", "setSoundEnabled", "_impl", "_updateFromJson", "", "json", "Lcom/scandit/datacapture/core/json/JsonValue;", "clone", "clone$scandit_barcode_capture", "equals", Request.JsonKeys.OTHER, "", "hashCode", "", "toJson", "", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArViewSettings implements BarcodeArViewSettingsProxy {
    private final /* synthetic */ BarcodeArViewSettingsProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeArViewSettings(NativeBarcodeArViewSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeArViewSettingsProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeArViewSettings getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(nativeName = "updateFromJson")
    public void _updateFromJson(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a._updateFromJson(json);
    }

    public final /* synthetic */ BarcodeArViewSettings clone$scandit_barcode_capture() {
        BarcodeArViewSettings barcodeArViewSettings = new BarcodeArViewSettings();
        barcodeArViewSettings.setSoundEnabled(getSoundEnabled());
        barcodeArViewSettings.setHapticEnabled(getHapticEnabled());
        barcodeArViewSettings.setDefaultCameraPosition(getDefaultCameraPosition());
        return barcodeArViewSettings;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(BarcodeArViewSettings.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettings");
        BarcodeArViewSettings barcodeArViewSettings = (BarcodeArViewSettings) other;
        return getSoundEnabled() == barcodeArViewSettings.getSoundEnabled() && getHapticEnabled() == barcodeArViewSettings.getHapticEnabled() && getDefaultCameraPosition() == barcodeArViewSettings.getDefaultCameraPosition();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(nativeName = "getCameraPosition", property = "defaultCameraPosition")
    public CameraPosition getDefaultCameraPosition() {
        return this.a.getDefaultCameraPosition();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(property = "hapticEnabled")
    public boolean getHapticEnabled() {
        return this.a.getHapticEnabled();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(property = "soundEnabled")
    public boolean getSoundEnabled() {
        return this.a.getSoundEnabled();
    }

    public int hashCode() {
        return getDefaultCameraPosition().hashCode() + ((Boolean.hashCode(getHapticEnabled()) + (Boolean.hashCode(getSoundEnabled()) * 31)) * 31);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(nativeName = "setCameraPosition", property = "defaultCameraPosition")
    public void setDefaultCameraPosition(CameraPosition cameraPosition) {
        Intrinsics.checkNotNullParameter(cameraPosition, "<set-?>");
        this.a.setDefaultCameraPosition(cameraPosition);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(property = "hapticEnabled")
    public void setHapticEnabled(boolean z) {
        this.a.setHapticEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettingsProxy
    @ProxyFunction(property = "soundEnabled")
    public void setSoundEnabled(boolean z) {
        this.a.setSoundEnabled(z);
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("soundEnabled", getSoundEnabled());
        jSONObject.put("hapticEnabled", getHapticEnabled());
        jSONObject.put("defaultCameraPosition", CameraPositionSerializer.toJson(getDefaultCameraPosition()));
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeArViewSettings() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings r0 = com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.ar.ui.BarcodeArViewSettings.<init>():void");
    }
}
