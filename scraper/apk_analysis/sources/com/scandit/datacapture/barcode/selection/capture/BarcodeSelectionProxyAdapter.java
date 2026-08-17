package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u000fJ\u001f\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001b\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R(\u0010*\u001a\u0004\u0018\u00010%2\b\u0010 \u001a\u0004\u0018\u00010%8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionProxyAdapter;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "_NativeBarcodeSelection", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "", "selectUnselectedBarcodes", "()V", "unfreezeCamera", "freezeCamera", "selectAimedBarcode", "", "json", "", ExtentionsKt.ENABLED_KEY, "setSelectBarcodeEnabledFromJsonString", "(Ljava/lang/String;Z)V", "unselectBarcodesFromJsonString", "(Ljava/lang/String;)V", "increaseCountForBarcodesFromJsonString", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "p0", "isEnabled", "()Z", "setEnabled", "(Z)V", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "getPointOfInterest", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "setPointOfInterest", "(Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "pointOfInterest", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionProxyAdapter implements BarcodeSelectionProxy {
    private final NativeBarcodeSelection a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureMode c;

    public BarcodeSelectionProxyAdapter(NativeBarcodeSelection _NativeBarcodeSelection, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeSelection, "_NativeBarcodeSelection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeSelection;
        this.proxyCache = proxyCache;
        NativeDataCaptureMode asDataCaptureMode = _NativeBarcodeSelection.asDataCaptureMode();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureMode, "asDataCaptureMode(...)");
        this.c = asDataCaptureMode;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    /* renamed from: _dataCaptureModeImpl, reason: from getter */
    public NativeDataCaptureMode getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeSelection getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void freezeCamera() {
        this.a.freezeCamera();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public PointWithUnit getPointOfInterest() {
        return this.a.getPointOfInterest();
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void increaseCountForBarcodesFromJsonString(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.increaseCountForBarcodesFromJsonString(json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public boolean isEnabled() {
        return this.a.isEnabled();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void selectAimedBarcode() {
        this.a.selectAimedBarcode();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void selectUnselectedBarcodes() {
        this.a.selectAllUnselectedBarcodes();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void setEnabled(boolean z) {
        this.a.setEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void setPointOfInterest(PointWithUnit pointWithUnit) {
        this.a.setPointOfInterest(pointWithUnit);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void setSelectBarcodeEnabledFromJsonString(String json, boolean enabled) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.setSelectBarcodeEnabledFromJsonString(json, enabled);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void unfreezeCamera() {
        this.a.unfreezeCamera();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    public void unselectBarcodesFromJsonString(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.unselectBarcodesFromJsonString(json);
    }

    public /* synthetic */ BarcodeSelectionProxyAdapter(NativeBarcodeSelection nativeBarcodeSelection, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeSelection, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
