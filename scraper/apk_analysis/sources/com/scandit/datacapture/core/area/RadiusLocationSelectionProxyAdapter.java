package com.scandit.datacapture.core.area;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.area.NativeLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRadiusLocationSelection;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/core/area/RadiusLocationSelectionProxyAdapter;", "Lcom/scandit/datacapture/core/area/RadiusLocationSelectionProxy;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeRadiusLocationSelection;", "_NativeRadiusLocationSelection", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/area/NativeRadiusLocationSelection;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeRadiusLocationSelection;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeLocationSelection;", "_locationSelectionImpl", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeLocationSelection;", "", "toJson", "()Ljava/lang/String;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getRadius", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "radius", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class RadiusLocationSelectionProxyAdapter implements RadiusLocationSelectionProxy {
    private final NativeRadiusLocationSelection a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeLocationSelection c;

    public RadiusLocationSelectionProxyAdapter(NativeRadiusLocationSelection _NativeRadiusLocationSelection, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeRadiusLocationSelection, "_NativeRadiusLocationSelection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeRadiusLocationSelection;
        this.proxyCache = proxyCache;
        NativeLocationSelection asLocationSelection = _NativeRadiusLocationSelection.asLocationSelection();
        Intrinsics.checkNotNullExpressionValue(asLocationSelection, "asLocationSelection(...)");
        this.c = asLocationSelection;
    }

    @Override // com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeRadiusLocationSelection getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    /* renamed from: _locationSelectionImpl, reason: from getter */
    public NativeLocationSelection getC() {
        return this.c;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    public FloatWithUnit getRadius() {
        FloatWithUnit radius = this.a.getRadius();
        Intrinsics.checkNotNull(radius);
        return radius;
    }

    @Override // com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    public String toJson() {
        String json = this.a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public /* synthetic */ RadiusLocationSelectionProxyAdapter(NativeRadiusLocationSelection nativeRadiusLocationSelection, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeRadiusLocationSelection, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
