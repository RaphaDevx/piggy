package com.scandit.datacapture.barcode.data;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoMarker;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/scandit/datacapture/barcode/data/ArucoMarkerProxyAdapter;", "Lcom/scandit/datacapture/barcode/data/ArucoMarkerProxy;", "Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeArucoMarker;", "_NativeArucoMarker", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeArucoMarker;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeArucoMarker;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getSize", "()I", RRWebVideoEvent.JsonKeys.SIZE, "", "getData", "()[B", "data", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ArucoMarkerProxyAdapter implements ArucoMarkerProxy {
    private final NativeArucoMarker a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public ArucoMarkerProxyAdapter(NativeArucoMarker _NativeArucoMarker, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeArucoMarker, "_NativeArucoMarker");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeArucoMarker;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.data.ArucoMarkerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeArucoMarker getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.data.ArucoMarkerProxy
    public byte[] getData() {
        byte[] bits = this.a.getBits();
        Intrinsics.checkNotNull(bits);
        return bits;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.data.ArucoMarkerProxy
    public int getSize() {
        return this.a.getSize();
    }

    public /* synthetic */ ArucoMarkerProxyAdapter(NativeArucoMarker nativeArucoMarker, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeArucoMarker, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
