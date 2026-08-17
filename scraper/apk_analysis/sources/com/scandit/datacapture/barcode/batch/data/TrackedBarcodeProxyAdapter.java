package com.scandit.datacapture.barcode.batch.data;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcodeProxyAdapter;", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcodeProxy;", "Lcom/scandit/datacapture/barcode/batch/internal/module/data/NativeTrackedBarcode;", "_NativeTrackedBarcode", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/batch/internal/module/data/NativeTrackedBarcode;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/batch/internal/module/data/NativeTrackedBarcode;", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "anchor", "Lcom/scandit/datacapture/core/common/geometry/Point;", "getAnchorPosition", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)Lcom/scandit/datacapture/core/common/geometry/Point;", "", "toJson", "()Ljava/lang/String;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getIdentifier", "()I", ViewHierarchyNode.JsonKeys.IDENTIFIER, "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "getLocation", "()Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "location", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class TrackedBarcodeProxyAdapter implements TrackedBarcodeProxy {
    private final NativeTrackedBarcode a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public TrackedBarcodeProxyAdapter(NativeTrackedBarcode _NativeTrackedBarcode, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeTrackedBarcode, "_NativeTrackedBarcode");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeTrackedBarcode;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedBarcodeProxy
    /* renamed from: _impl, reason: from getter */
    public NativeTrackedBarcode getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedBarcodeProxy
    public Point getAnchorPosition(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Point anchorPosition = this.a.getAnchorPosition(anchor);
        Intrinsics.checkNotNull(anchorPosition);
        return anchorPosition;
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedBarcodeProxy
    public int getIdentifier() {
        return this.a.getIdentifier();
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedBarcodeProxy
    public Quadrilateral getLocation() {
        Quadrilateral location = this.a.getLocation();
        Intrinsics.checkNotNull(location);
        return location;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedBarcodeProxy
    public String toJson() {
        String json = this.a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public /* synthetic */ TrackedBarcodeProxyAdapter(NativeTrackedBarcode nativeTrackedBarcode, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeTrackedBarcode, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
