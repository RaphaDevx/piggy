package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.capture.SymbologySettings;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettings;
import com.scandit.datacapture.core.data.ClusteringMode;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.utils.TypeConverter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0097\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0097\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001d\u0010\u001eR$\u0010%\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010*\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00138W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\n0\t8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b+\u0010,R$\u00100\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00138W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b.\u0010'\"\u0004\b/\u0010)R\u0014\u00104\u001a\u0002018WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b2\u00103R$\u00107\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00138W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b5\u0010'\"\u0004\b6\u0010)¨\u00069"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettingsProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;)V", "()V", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "symbologies", "", "enableSymbologies", "(Ljava/util/Set;)V", "symbology", "Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "getSymbologySettings", "(Lcom/scandit/datacapture/barcode/data/Symbology;)Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "", ExtentionsKt.ENABLED_KEY, "setSymbologyEnabled", "(Lcom/scandit/datacapture/barcode/data/Symbology;Z)V", "", "name", "", "value", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "Lcom/scandit/datacapture/core/data/ClusteringMode;", "<set-?>", "getClusteringMode", "()Lcom/scandit/datacapture/core/data/ClusteringMode;", "setClusteringMode", "(Lcom/scandit/datacapture/core/data/ClusteringMode;)V", "clusteringMode", "getDisableModeWhenCaptureListCompleted", "()Z", "setDisableModeWhenCaptureListCompleted", "(Z)V", "disableModeWhenCaptureListCompleted", "getEnabledSymbologies", "()Ljava/util/Set;", "enabledSymbologies", "getExpectsOnlyUniqueBarcodes", "setExpectsOnlyUniqueBarcodes", "expectsOnlyUniqueBarcodes", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "getFilterSettings", "()Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "filterSettings", "getMappingEnabled", "setMappingEnabled", "mappingEnabled", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCountSettings implements BarcodeCountSettingsProxy {
    public static final String PROPERTY_TRIGGER_AUTO_FOCUS_DELAY = "trigger_auto_focus_delay";
    public static final String PROPERTY_TRIGGER_AUTO_FOCUS_ON_SHUTTER = "trigger_auto_focus_on_shutter";
    public static final String PROPERTY_USE_IMU_DATA = "use_imu_data";
    public static final long TRIGGER_AUTO_FOCUS_DELAY_DEFAULT = 100;
    public static final boolean TRIGGER_AUTO_FOCUS_ON_SHUTTER_DEFAULT = true;
    private final /* synthetic */ BarcodeCountSettingsProxyAdapter a;
    private boolean b;
    private long c;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCountSettings(NativeBarcodeCountSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeCountSettingsProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.b = true;
        this.c = 100L;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCountSettings getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction
    public void enableSymbologies(Set<? extends Symbology> symbologies) {
        Intrinsics.checkNotNullParameter(symbologies, "symbologies");
        this.a.enableSymbologies(symbologies);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "getClusteringMode", property = "clusteringMode")
    public ClusteringMode getClusteringMode() {
        return this.a.getClusteringMode();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "shouldDisableModeWhenCaptureListCompleted", property = "disableModeWhenCaptureListCompleted")
    public boolean getDisableModeWhenCaptureListCompleted() {
        return this.a.getDisableModeWhenCaptureListCompleted();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "getEnabledSymbologies", property = "enabledSymbologies")
    public Set<Symbology> getEnabledSymbologies() {
        return this.a.getEnabledSymbologies();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "expectsOnlyUniqueBarcodes", property = "expectsOnlyUniqueBarcodes")
    public boolean getExpectsOnlyUniqueBarcodes() {
        return this.a.getExpectsOnlyUniqueBarcodes();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "getFilterSettings", property = "filterSettings")
    public BarcodeFilterSettings getFilterSettings() {
        return this.a.getFilterSettings();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "getMappingEnabled", property = "mappingEnabled")
    public boolean getMappingEnabled() {
        return this.a.getMappingEnabled();
    }

    public final Object getProperty(String name) {
        int intValue;
        Intrinsics.checkNotNullParameter(name, "name");
        int hashCode = name.hashCode();
        if (hashCode != -1145110768) {
            if (hashCode != -371273613) {
                if (hashCode == 1444281501 && name.equals(PROPERTY_TRIGGER_AUTO_FOCUS_ON_SHUTTER)) {
                    return Boolean.valueOf(this.b);
                }
            } else if (name.equals(PROPERTY_TRIGGER_AUTO_FOCUS_DELAY)) {
                return Long.valueOf(this.c);
            }
        } else if (name.equals(PROPERTY_USE_IMU_DATA)) {
            return Boolean.valueOf(getA().getShouldUseIMUData());
        }
        Integer intProperty = getA().getIntProperty(name);
        if (intProperty != null) {
            intValue = intProperty.intValue();
        } else {
            Boolean boolProperty = getA().getBoolProperty(name);
            Integer valueOf = boolProperty != null ? Integer.valueOf(Boolean.compare(boolProperty.booleanValue(), false)) : null;
            intValue = valueOf != null ? valueOf.intValue() : -1;
        }
        return Integer.valueOf(intValue);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction
    public SymbologySettings getSymbologySettings(Symbology symbology) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        return this.a.getSymbologySettings(symbology);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "setClusteringMode", property = "clusteringMode")
    public void setClusteringMode(ClusteringMode clusteringMode) {
        Intrinsics.checkNotNullParameter(clusteringMode, "<set-?>");
        this.a.setClusteringMode(clusteringMode);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "setShouldDisableModeWhenCaptureListCompleted", property = "disableModeWhenCaptureListCompleted")
    public void setDisableModeWhenCaptureListCompleted(boolean z) {
        this.a.setDisableModeWhenCaptureListCompleted(z);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "setExpectsOnlyUniqueBarcodes", property = "expectsOnlyUniqueBarcodes")
    public void setExpectsOnlyUniqueBarcodes(boolean z) {
        this.a.setExpectsOnlyUniqueBarcodes(z);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction(nativeName = "setMappingEnabled", property = "mappingEnabled")
    public void setMappingEnabled(boolean z) {
        this.a.setMappingEnabled(z);
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        int hashCode = name.hashCode();
        if (hashCode != -1145110768) {
            if (hashCode != -371273613) {
                if (hashCode == 1444281501 && name.equals(PROPERTY_TRIGGER_AUTO_FOCUS_ON_SHUTTER)) {
                    Boolean booleanFromAny = TypeConverter.INSTANCE.booleanFromAny(value);
                    if (booleanFromAny != null) {
                        this.b = booleanFromAny.booleanValue();
                        return;
                    }
                    return;
                }
            } else if (name.equals(PROPERTY_TRIGGER_AUTO_FOCUS_DELAY)) {
                Long longFromAny = TypeConverter.INSTANCE.longFromAny(value);
                if (longFromAny != null) {
                    this.c = longFromAny.longValue();
                    return;
                }
                return;
            }
        } else if (name.equals(PROPERTY_USE_IMU_DATA)) {
            Boolean booleanFromAny2 = TypeConverter.INSTANCE.booleanFromAny(value);
            if (booleanFromAny2 != null) {
                getA().setShouldUseIMUData(booleanFromAny2.booleanValue());
                return;
            }
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

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    @ProxyFunction
    public void setSymbologyEnabled(Symbology symbology, boolean enabled) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        this.a.setSymbologyEnabled(symbology, enabled);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCountSettings() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings r0 = com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings.<init>():void");
    }
}
