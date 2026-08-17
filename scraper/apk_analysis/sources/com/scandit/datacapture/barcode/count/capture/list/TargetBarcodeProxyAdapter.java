package com.scandit.datacapture.barcode.count.capture.list;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeTargetBarcode;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/list/TargetBarcodeProxyAdapter;", "Lcom/scandit/datacapture/barcode/count/capture/list/TargetBarcodeProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeTargetBarcode;", "_NativeTargetBarcode", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeTargetBarcode;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeTargetBarcode;", "", "getData", "()Ljava/lang/String;", "", "getQuantity", "()I", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class TargetBarcodeProxyAdapter implements TargetBarcodeProxy {
    private final NativeTargetBarcode a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public TargetBarcodeProxyAdapter(NativeTargetBarcode _NativeTargetBarcode, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeTargetBarcode, "_NativeTargetBarcode");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeTargetBarcode;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.TargetBarcodeProxy
    /* renamed from: _impl, reason: from getter */
    public NativeTargetBarcode getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.TargetBarcodeProxy
    public String getData() {
        String data = this.a.getData();
        Intrinsics.checkNotNull(data);
        return data;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.TargetBarcodeProxy
    public int getQuantity() {
        return this.a.getQuantity();
    }

    public /* synthetic */ TargetBarcodeProxyAdapter(NativeTargetBarcode nativeTargetBarcode, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeTargetBarcode, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
