package com.scandit.datacapture.core.common.graphic;

import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/core/common/graphic/ImageBufferProxyAdapter;", "Lcom/scandit/datacapture/core/common/graphic/ImageBufferProxy;", "Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;", "_NativeImageBuffer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getWidth", "()I", "width", "getHeight", "height", "", "Lcom/scandit/datacapture/core/common/graphic/ImagePlane;", "getPlanes", "()Ljava/util/List;", "planes", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ImageBufferProxyAdapter implements ImageBufferProxy {
    private final NativeImageBuffer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public ImageBufferProxyAdapter(NativeImageBuffer _NativeImageBuffer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeImageBuffer, "_NativeImageBuffer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeImageBuffer;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    /* renamed from: _impl, reason: from getter */
    public NativeImageBuffer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    public int getHeight() {
        return this.a.getHeight();
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    public List<ImagePlane> getPlanes() {
        ArrayList<ImagePlane> planes = this.a.getPlanes();
        Intrinsics.checkNotNull(planes);
        return planes;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.common.graphic.ImageBufferProxy
    public int getWidth() {
        return this.a.getWidth();
    }

    public /* synthetic */ ImageBufferProxyAdapter(NativeImageBuffer nativeImageBuffer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeImageBuffer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
