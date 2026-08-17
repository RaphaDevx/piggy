package com.scandit.datacapture.core.area;

import com.scandit.datacapture.core.internal.sdk.area.NativeLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/core/area/RectangularLocationSelectionProxyAdapter;", "Lcom/scandit/datacapture/core/area/RectangularLocationSelectionProxy;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;", "_NativeRectangularLocationSelection", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeLocationSelection;", "_locationSelectionImpl", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeLocationSelection;", "", "toJson", "()Ljava/lang/String;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class RectangularLocationSelectionProxyAdapter implements RectangularLocationSelectionProxy {
    private final NativeRectangularLocationSelection a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeLocationSelection c;

    public RectangularLocationSelectionProxyAdapter(NativeRectangularLocationSelection _NativeRectangularLocationSelection, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeRectangularLocationSelection, "_NativeRectangularLocationSelection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeRectangularLocationSelection;
        this.proxyCache = proxyCache;
        NativeLocationSelection asLocationSelection = _NativeRectangularLocationSelection.asLocationSelection();
        Intrinsics.checkNotNullExpressionValue(asLocationSelection, "asLocationSelection(...)");
        this.c = asLocationSelection;
    }

    @Override // com.scandit.datacapture.core.area.RectangularLocationSelectionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeRectangularLocationSelection getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.area.RectangularLocationSelectionProxy, com.scandit.datacapture.core.area.LocationSelection, com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    /* renamed from: _locationSelectionImpl, reason: from getter */
    public NativeLocationSelection getC() {
        return this.c;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.area.RectangularLocationSelectionProxy, com.scandit.datacapture.core.area.LocationSelection, com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    public String toJson() {
        String json = this.a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public /* synthetic */ RectangularLocationSelectionProxyAdapter(NativeRectangularLocationSelection nativeRectangularLocationSelection, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeRectangularLocationSelection, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
