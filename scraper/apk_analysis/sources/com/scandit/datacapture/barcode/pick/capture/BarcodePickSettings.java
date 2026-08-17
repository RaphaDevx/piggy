package com.scandit.datacapture.barcode.pick.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.SymbologySettings;
import com.scandit.datacapture.barcode.data.ArucoDictionary;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettings;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSettings;
import com.scandit.datacapture.core.internal.sdk.utils.TypeConverter;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u001e\u001a\u00020\u0004H\u0097\u0001J\u0011\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0097\u0001J\u0017\u0010#\u001a\u00020 2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0097\u0001J\u0019\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u0007H\u0097\u0001J\u0010\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+J\u0011\u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020\u000fH\u0097\u0001J\u0011\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0097\u0001J\u0018\u00101\u001a\u00020 2\u0006\u0010*\u001a\u00020+2\b\u00102\u001a\u0004\u0018\u00010)R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00128W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0019\u0010\n\"\u0004\b\u001a\u0010\fR$\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u001c\u0010\n\"\u0004\b\u001d\u0010\f¨\u00063"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettingsProxy;", "()V", "impl", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickSettings;", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickSettings;)V", "<set-?>", "", "cachingEnabled", "getCachingEnabled", "()Z", "setCachingEnabled", "(Z)V", "enabledSymbologies", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "getEnabledSymbologies", "()Ljava/util/Set;", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "filterSettings", "getFilterSettings", "()Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "setFilterSettings", "(Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;)V", "hapticsEnabled", "getHapticsEnabled", "setHapticsEnabled", "soundEnabled", "getSoundEnabled", "setSoundEnabled", "_impl", "_updateFromJson", "", "json", "Lcom/scandit/datacapture/core/json/JsonValue;", "enableSymbologies", "symbologies", "enableSymbology", "symbology", ExtentionsKt.ENABLED_KEY, "getProperty", "", "name", "", "getSymbologySettings", "Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "setArucoDictionary", "dictionary", "Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "setProperty", "value", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickSettings implements BarcodePickSettingsProxy {
    private final /* synthetic */ BarcodePickSettingsProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodePickSettings(NativeBarcodePickSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodePickSettingsProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodePickSettings getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "updateFromJson")
    public void _updateFromJson(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a._updateFromJson(json);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction
    public void enableSymbologies(Set<? extends Symbology> symbologies) {
        Intrinsics.checkNotNullParameter(symbologies, "symbologies");
        this.a.enableSymbologies(symbologies);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "setSymbologyEnabled")
    public void enableSymbology(Symbology symbology, boolean enabled) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        this.a.enableSymbology(symbology, enabled);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "getShouldEnableCaching", property = "cachingEnabled")
    public boolean getCachingEnabled() {
        return this.a.getCachingEnabled();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(property = "enabledSymbologies")
    public Set<Symbology> getEnabledSymbologies() {
        return this.a.getEnabledSymbologies();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(property = "filterSettings")
    public BarcodeFilterSettings getFilterSettings() {
        return this.a.getFilterSettings();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "getHapticEnabled", property = "hapticsEnabled")
    public boolean getHapticsEnabled() {
        return this.a.getHapticsEnabled();
    }

    public final Object getProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer intProperty = getA().getIntProperty(name);
        if (intProperty != null) {
            return intProperty;
        }
        Boolean boolProperty = getA().getBoolProperty(name);
        Integer valueOf = boolProperty != null ? Integer.valueOf(Boolean.compare(boolProperty.booleanValue(), false)) : null;
        if (valueOf == null) {
            return -1;
        }
        return valueOf;
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(property = "soundEnabled")
    public boolean getSoundEnabled() {
        return this.a.getSoundEnabled();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction
    public SymbologySettings getSymbologySettings(Symbology symbology) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        return this.a.getSymbologySettings(symbology);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction
    public void setArucoDictionary(ArucoDictionary dictionary) {
        Intrinsics.checkNotNullParameter(dictionary, "dictionary");
        this.a.setArucoDictionary(dictionary);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "setShouldEnableCaching", property = "cachingEnabled")
    public void setCachingEnabled(boolean z) {
        this.a.setCachingEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(property = "filterSettings")
    public void setFilterSettings(BarcodeFilterSettings barcodeFilterSettings) {
        Intrinsics.checkNotNullParameter(barcodeFilterSettings, "<set-?>");
        this.a.setFilterSettings(barcodeFilterSettings);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(nativeName = "setHapticEnabled", property = "hapticsEnabled")
    public void setHapticsEnabled(boolean z) {
        this.a.setHapticsEnabled(z);
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (value instanceof Boolean) {
            getA().setBoolProperty(name, ((Boolean) value).booleanValue());
            return;
        }
        Integer intFromAny = TypeConverter.INSTANCE.intFromAny(value);
        if (intFromAny != null) {
            getA().setIntProperty(name, intFromAny.intValue());
        }
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickSettingsProxy
    @ProxyFunction(property = "soundEnabled")
    public void setSoundEnabled(boolean z) {
        this.a.setSoundEnabled(z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickSettings() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSettings r0 = com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSettings.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings.<init>():void");
    }
}
