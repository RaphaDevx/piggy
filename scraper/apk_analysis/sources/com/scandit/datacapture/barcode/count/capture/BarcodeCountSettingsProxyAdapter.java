package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.capture.SymbologySettings;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettings;
import com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.core.data.ClusteringMode;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR$\u0010$\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010'\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R$\u0010*\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u0014\u0010.\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R$\u00104\u001a\u00020/2\u0006\u0010\u001f\u001a\u00020/8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u00065"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettingsProxyAdapter;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettingsProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;", "_NativeBarcodeCountSettings", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;", "Lcom/scandit/datacapture/barcode/data/Symbology;", "symbology", "Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "getSymbologySettings", "(Lcom/scandit/datacapture/barcode/data/Symbology;)Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "", "symbologies", "", "enableSymbologies", "(Ljava/util/Set;)V", "", ExtentionsKt.ENABLED_KEY, "setSymbologyEnabled", "(Lcom/scandit/datacapture/barcode/data/Symbology;Z)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getEnabledSymbologies", "()Ljava/util/Set;", "enabledSymbologies", "p0", "getExpectsOnlyUniqueBarcodes", "()Z", "setExpectsOnlyUniqueBarcodes", "(Z)V", "expectsOnlyUniqueBarcodes", "getDisableModeWhenCaptureListCompleted", "setDisableModeWhenCaptureListCompleted", "disableModeWhenCaptureListCompleted", "getMappingEnabled", "setMappingEnabled", "mappingEnabled", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "getFilterSettings", "()Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "filterSettings", "Lcom/scandit/datacapture/core/data/ClusteringMode;", "getClusteringMode", "()Lcom/scandit/datacapture/core/data/ClusteringMode;", "setClusteringMode", "(Lcom/scandit/datacapture/core/data/ClusteringMode;)V", "clusteringMode", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCountSettingsProxyAdapter implements BarcodeCountSettingsProxy {
    private final NativeBarcodeCountSettings a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeCountSettingsProxyAdapter(NativeBarcodeCountSettings _NativeBarcodeCountSettings, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeCountSettings, "_NativeBarcodeCountSettings");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeCountSettings;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeCountSettings getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void enableSymbologies(Set<? extends Symbology> symbologies) {
        Intrinsics.checkNotNullParameter(symbologies, "symbologies");
        this.a.enableSymbologies(BarcodeNativeTypeFactory.INSTANCE.convertSymbologySetToHashSet(symbologies));
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public ClusteringMode getClusteringMode() {
        ClusteringMode clusteringMode = this.a.getClusteringMode();
        Intrinsics.checkNotNull(clusteringMode);
        return clusteringMode;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public boolean getDisableModeWhenCaptureListCompleted() {
        return this.a.shouldDisableModeWhenCaptureListCompleted();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public Set<Symbology> getEnabledSymbologies() {
        HashSet<Symbology> enabledSymbologies = this.a.getEnabledSymbologies();
        Intrinsics.checkNotNull(enabledSymbologies);
        return enabledSymbologies;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public boolean getExpectsOnlyUniqueBarcodes() {
        return this.a.expectsOnlyUniqueBarcodes();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public BarcodeFilterSettings getFilterSettings() {
        NativeBarcodeFilterSettings filterSettings = this.a.getFilterSettings();
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeFilterSettings.class);
        Intrinsics.checkNotNull(filterSettings);
        return (BarcodeFilterSettings) proxyCache.getOrPut(orCreateKotlinClass, null, filterSettings, new j(filterSettings));
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public boolean getMappingEnabled() {
        return this.a.getMappingEnabled();
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public SymbologySettings getSymbologySettings(Symbology symbology) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        NativeSymbologySettings symbologySettings = this.a.getSymbologySettings(symbology);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeSymbologySettings.class);
        Intrinsics.checkNotNull(symbologySettings);
        return (SymbologySettings) proxyCache.getOrPut(orCreateKotlinClass, null, symbologySettings, new k(symbologySettings));
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void setClusteringMode(ClusteringMode p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setClusteringMode(p0);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void setDisableModeWhenCaptureListCompleted(boolean z) {
        this.a.setShouldDisableModeWhenCaptureListCompleted(z);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void setExpectsOnlyUniqueBarcodes(boolean z) {
        this.a.setExpectsOnlyUniqueBarcodes(z);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void setMappingEnabled(boolean z) {
        this.a.setMappingEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountSettingsProxy
    public void setSymbologyEnabled(Symbology symbology, boolean enabled) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        this.a.setSymbologyEnabled(symbology, enabled);
    }

    public /* synthetic */ BarcodeCountSettingsProxyAdapter(NativeBarcodeCountSettings nativeBarcodeCountSettings, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeCountSettings, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
