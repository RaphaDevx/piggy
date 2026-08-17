package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings;
import com.scandit.datacapture.barcode.capture.SymbologySettings;
import com.scandit.datacapture.barcode.data.ArucoDictionary;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.utils.TypeConverter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0010\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0097\u0001¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\"\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\u0015\u0010$\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001b¢\u0006\u0004\b$\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\t8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b&\u0010'R$\u0010.\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u00108W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00060"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettings;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettingsProxy;", "Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;)V", "()V", "_impl", "()Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "symbologies", "", "enableSymbologies", "(Ljava/util/Set;)V", "symbology", "", ExtentionsKt.ENABLED_KEY, "enableSymbology", "(Lcom/scandit/datacapture/barcode/data/Symbology;Z)V", "Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "getSymbologySettings", "(Lcom/scandit/datacapture/barcode/data/Symbology;)Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "dictionary", "setArucoDictionary", "(Lcom/scandit/datacapture/barcode/data/ArucoDictionary;)V", "", "jsonData", "updateFromJson", "(Ljava/lang/String;)V", "name", "", "value", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "getEnabledSymbologies", "()Ljava/util/Set;", "enabledSymbologies", "<set-?>", "getExpectsOnlyUniqueBarcodes", "()Z", "setExpectsOnlyUniqueBarcodes", "(Z)V", "expectsOnlyUniqueBarcodes", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeBatchSettings implements BarcodeBatchSettingsProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeBatchSettingsProxyAdapter a;
    private com.scandit.datacapture.barcode.internal.module.batch.capture.a b;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettings$Companion;", "", "", "jsonData", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettings;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettings;", "FREEZE_INDICATION_LISTENER", "Ljava/lang/String;", "FREEZE_INDICATION_WAIT_TIME", "FREEZE_INDICATOR", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final BarcodeBatchSettings fromJson(String jsonData) {
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new BarcodeBatchDeserializer().settingsFromJson(jsonData);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeBatchSettings(NativeBarcodeTrackingSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeBatchSettingsProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final BarcodeBatchSettings fromJson(String str) {
        return INSTANCE.fromJson(str);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeTrackingSettings getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction
    public void enableSymbologies(Set<? extends Symbology> symbologies) {
        Intrinsics.checkNotNullParameter(symbologies, "symbologies");
        this.a.enableSymbologies(symbologies);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction(nativeName = "setSymbologyEnabled")
    public void enableSymbology(Symbology symbology, boolean enabled) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        this.a.enableSymbology(symbology, enabled);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction(property = "enabledSymbologies")
    public Set<Symbology> getEnabledSymbologies() {
        return this.a.getEnabledSymbologies();
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction(nativeName = "expectsOnlyUniqueBarcodes", property = "expectsOnlyUniqueBarcodes")
    public boolean getExpectsOnlyUniqueBarcodes() {
        return this.a.getExpectsOnlyUniqueBarcodes();
    }

    public final Object getProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int hashCode = name.hashCode();
        if (hashCode != -1372984747) {
            if (hashCode != -272164569) {
                if (hashCode == 670080312 && name.equals("freezeIndicator")) {
                    Object obj = this.b;
                    if (obj == null) {
                        return -1;
                    }
                    return obj;
                }
            } else if (name.equals("freezeIndicationListener")) {
                com.scandit.datacapture.barcode.internal.module.batch.capture.a aVar = this.b;
                if (aVar == null || (obj = aVar.a) == null) {
                    return -1;
                }
                return obj;
            }
        } else if (name.equals("freezeIndicationWaitTime")) {
            com.scandit.datacapture.barcode.internal.module.batch.capture.a aVar2 = this.b;
            return Integer.valueOf(aVar2 != null ? (int) aVar2.b : -1);
        }
        Integer intProperty = getA().getIntProperty(name);
        if (intProperty != null) {
            r2 = intProperty.intValue();
        } else {
            Boolean boolProperty = getA().getBoolProperty(name);
            Integer valueOf = boolProperty != null ? Integer.valueOf(Boolean.compare(boolProperty.booleanValue(), false)) : null;
            if (valueOf != null) {
                r2 = valueOf.intValue();
            }
        }
        return Integer.valueOf(r2);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction
    public SymbologySettings getSymbologySettings(Symbology symbology) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        return this.a.getSymbologySettings(symbology);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction
    public void setArucoDictionary(ArucoDictionary dictionary) {
        Intrinsics.checkNotNullParameter(dictionary, "dictionary");
        this.a.setArucoDictionary(dictionary);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    @ProxyFunction(nativeName = "setExpectsOnlyUniqueBarcodes", property = "expectsOnlyUniqueBarcodes")
    public void setExpectsOnlyUniqueBarcodes(boolean z) {
        this.a.setExpectsOnlyUniqueBarcodes(z);
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(name, "freezeIndicationListener") && (value instanceof Runnable)) {
            com.scandit.datacapture.barcode.internal.module.batch.capture.a aVar = this.b;
            if (aVar == null) {
                aVar = new com.scandit.datacapture.barcode.internal.module.batch.capture.a();
            }
            aVar.a = (Runnable) value;
            this.b = aVar;
            return;
        }
        if (Intrinsics.areEqual(name, "freezeIndicationWaitTime") && (value instanceof Integer)) {
            com.scandit.datacapture.barcode.internal.module.batch.capture.a aVar2 = this.b;
            if (aVar2 == null) {
                aVar2 = new com.scandit.datacapture.barcode.internal.module.batch.capture.a();
            }
            aVar2.b = ((Number) value).intValue();
            this.b = aVar2;
            return;
        }
        if (value instanceof Boolean) {
            getA().setBoolProperty(name, ((Boolean) value).booleanValue());
            return;
        }
        Integer intFromAny = TypeConverter.INSTANCE.intFromAny(value);
        if (intFromAny != null) {
            getA().setIntProperty(name, intFromAny.intValue());
        }
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new BarcodeBatchDeserializer().updateSettingsFromJson(this, jsonData);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeBatchSettings() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings r0 = com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettings.<init>():void");
    }
}
