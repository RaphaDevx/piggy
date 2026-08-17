package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings;
import com.scandit.datacapture.barcode.capture.SymbologySettings;
import com.scandit.datacapture.barcode.data.ArucoDictionary;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoDictionary;
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

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R$\u0010(\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettingsProxyAdapter;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSettingsProxy;", "Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "_NativeBarcodeTrackingSettings", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "Lcom/scandit/datacapture/barcode/data/Symbology;", "symbology", "Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "getSymbologySettings", "(Lcom/scandit/datacapture/barcode/data/Symbology;)Lcom/scandit/datacapture/barcode/capture/SymbologySettings;", "", ExtentionsKt.ENABLED_KEY, "", "enableSymbology", "(Lcom/scandit/datacapture/barcode/data/Symbology;Z)V", "", "symbologies", "enableSymbologies", "(Ljava/util/Set;)V", "Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "dictionary", "setArucoDictionary", "(Lcom/scandit/datacapture/barcode/data/ArucoDictionary;)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getEnabledSymbologies", "()Ljava/util/Set;", "enabledSymbologies", "p0", "getExpectsOnlyUniqueBarcodes", "()Z", "setExpectsOnlyUniqueBarcodes", "(Z)V", "expectsOnlyUniqueBarcodes", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeBatchSettingsProxyAdapter implements BarcodeBatchSettingsProxy {
    private final NativeBarcodeTrackingSettings a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeBatchSettingsProxyAdapter(NativeBarcodeTrackingSettings _NativeBarcodeTrackingSettings, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeTrackingSettings, "_NativeBarcodeTrackingSettings");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeTrackingSettings;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeTrackingSettings getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public void enableSymbologies(Set<? extends Symbology> symbologies) {
        Intrinsics.checkNotNullParameter(symbologies, "symbologies");
        this.a.enableSymbologies(BarcodeNativeTypeFactory.INSTANCE.convertSymbologySetToHashSet(symbologies));
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public void enableSymbology(Symbology symbology, boolean enabled) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        this.a.setSymbologyEnabled(symbology, enabled);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public Set<Symbology> getEnabledSymbologies() {
        HashSet<Symbology> enabledSymbologies = this.a.getEnabledSymbologies();
        Intrinsics.checkNotNull(enabledSymbologies);
        return enabledSymbologies;
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public boolean getExpectsOnlyUniqueBarcodes() {
        return this.a.expectsOnlyUniqueBarcodes();
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public SymbologySettings getSymbologySettings(Symbology symbology) {
        Intrinsics.checkNotNullParameter(symbology, "symbology");
        NativeSymbologySettings symbologySettings = this.a.getSymbologySettings(symbology);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeSymbologySettings.class);
        Intrinsics.checkNotNull(symbologySettings);
        return (SymbologySettings) proxyCache.getOrPut(orCreateKotlinClass, null, symbologySettings, new H(symbologySettings));
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public void setArucoDictionary(ArucoDictionary dictionary) {
        Intrinsics.checkNotNullParameter(dictionary, "dictionary");
        NativeArucoDictionary a = dictionary.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeArucoDictionary.class), null, a, dictionary);
        this.a.setArucoDictionary(a);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettingsProxy
    public void setExpectsOnlyUniqueBarcodes(boolean z) {
        this.a.setExpectsOnlyUniqueBarcodes(z);
    }

    public /* synthetic */ BarcodeBatchSettingsProxyAdapter(NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeTrackingSettings, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
