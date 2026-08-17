package com.scandit.datacapture.barcode.filter.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR0\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00168V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aRH\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000e0\u001c2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000e0\u001c8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010(\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020#8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010+\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020#8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'¨\u0006,"}, d2 = {"Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettingsProxyAdapter;", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettingsProxy;", "Lcom/scandit/datacapture/barcode/filter/capture/NativeBarcodeFilterSettings;", "_NativeBarcodeFilterSettings", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/filter/capture/NativeBarcodeFilterSettings;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/filter/capture/NativeBarcodeFilterSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "p0", "getExcludedSymbologies", "()Ljava/util/Set;", "setExcludedSymbologies", "(Ljava/util/Set;)V", "excludedSymbologies", "", "getExcludedCodesRegex", "()Ljava/lang/String;", "setExcludedCodesRegex", "(Ljava/lang/String;)V", "excludedCodesRegex", "", "", "getExcludedSymbolCounts", "()Ljava/util/Map;", "setExcludedSymbolCounts", "(Ljava/util/Map;)V", "excludedSymbolCounts", "", "getExcludeEan13", "()Z", "setExcludeEan13", "(Z)V", "excludeEan13", "getExcludeUpca", "setExcludeUpca", "excludeUpca", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFilterSettingsProxyAdapter implements BarcodeFilterSettingsProxy {
    private final NativeBarcodeFilterSettings a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeFilterSettingsProxyAdapter(NativeBarcodeFilterSettings _NativeBarcodeFilterSettings, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeFilterSettings, "_NativeBarcodeFilterSettings");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeFilterSettings;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeFilterSettings getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public boolean getExcludeEan13() {
        return this.a.isExcludeEan13();
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public boolean getExcludeUpca() {
        return this.a.isExcludeUpca();
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public String getExcludedCodesRegex() {
        String excludedCodesRegex = this.a.getExcludedCodesRegex();
        Intrinsics.checkNotNull(excludedCodesRegex);
        return excludedCodesRegex;
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public Map<Symbology, Set<Short>> getExcludedSymbolCounts() {
        HashMap<Symbology, HashSet<Short>> excludedSymbolCounts = this.a.getExcludedSymbolCounts();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(excludedSymbolCounts);
        return barcodeNativeTypeFactory.convertMapOfSymbologyToSetOfInt(excludedSymbolCounts);
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public Set<Symbology> getExcludedSymbologies() {
        HashSet<Symbology> excludedSymbologies = this.a.getExcludedSymbologies();
        Intrinsics.checkNotNull(excludedSymbologies);
        return excludedSymbologies;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public void setExcludeEan13(boolean z) {
        this.a.setExcludeEan13(z);
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public void setExcludeUpca(boolean z) {
        this.a.setExcludeUpca(z);
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public void setExcludedCodesRegex(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setExcludedCodesRegex(p0);
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public void setExcludedSymbolCounts(Map<Symbology, ? extends Set<Short>> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setExcludedSymbolCounts(BarcodeNativeTypeFactory.INSTANCE.convert(p0));
    }

    @Override // com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettingsProxy
    public void setExcludedSymbologies(Set<? extends Symbology> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setExcludedSymbologies(BarcodeNativeTypeFactory.INSTANCE.convertSymbologySetToHashSet(p0));
    }

    public /* synthetic */ BarcodeFilterSettingsProxyAdapter(NativeBarcodeFilterSettings nativeBarcodeFilterSettings, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeFilterSettings, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
