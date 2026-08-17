package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterEditor;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountProxyAdapter;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;", "_NativeBarcodeCount", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "list", "", "_setBarcodeCountCaptureList", "(Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;)V", "clearAdditionalBarcodes", "()V", "", "_shouldDisableModeWhenCaptureListCompleted", "()Z", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeClusterEditor;", "beginClusterEditing", "()Lcom/scandit/datacapture/barcode/count/capture/BarcodeClusterEditor;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "p0", "isEnabled", "setEnabled", "(Z)V", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCountProxyAdapter implements BarcodeCountProxy {
    private final NativeBarcodeCount a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureMode c;

    public BarcodeCountProxyAdapter(NativeBarcodeCount _NativeBarcodeCount, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeCount, "_NativeBarcodeCount");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeCount;
        this.proxyCache = proxyCache;
        NativeDataCaptureMode asDataCaptureMode = _NativeBarcodeCount.asDataCaptureMode();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureMode, "asDataCaptureMode(...)");
        this.c = asDataCaptureMode;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    /* renamed from: _dataCaptureModeImpl, reason: from getter */
    public NativeDataCaptureMode getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeCount getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public void _setBarcodeCountCaptureList(BarcodeCountCaptureList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        NativeBarcodeCountCaptureList a = list.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureList.class), null, a, list);
        this.a.setBarcodeCountCaptureList(a);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public boolean _shouldDisableModeWhenCaptureListCompleted() {
        return this.a.shouldDisableModeWhenCaptureListCompleted();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public BarcodeClusterEditor beginClusterEditing() {
        NativeBarcodeClusterEditor beginClusterEditing = this.a.beginClusterEditing();
        if (beginClusterEditing != null) {
            return (BarcodeClusterEditor) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeClusterEditor.class), null, beginClusterEditing, new i(beginClusterEditing));
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public void clearAdditionalBarcodes() {
        this.a.clearAdditionalBarcodes();
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public boolean isEnabled() {
        return this.a.isEnabled();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    public void setEnabled(boolean z) {
        this.a.setEnabled(z);
    }

    public /* synthetic */ BarcodeCountProxyAdapter(NativeBarcodeCount nativeBarcodeCount, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeCount, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
