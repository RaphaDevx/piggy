package com.scandit.datacapture.core.area.serialization;

import com.scandit.datacapture.core.area.RadiusLocationSelection;
import com.scandit.datacapture.core.area.RectangularLocationSelection;
import com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializerHelper;
import com.scandit.datacapture.core.internal.sdk.area.NativeNoLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRadiusLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NoLocationSelection;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeLocationSelectionDeserializerHelper;", "Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializerHelper;", "_LocationSelectionDeserializerHelper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeNoLocationSelection;", "createNoLocationSelection", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeNoLocationSelection;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeRadiusLocationSelection;", "createRadiusLocationSelection", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeRadiusLocationSelection;", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;", "createRectangularLocationSelection", "()Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class LocationSelectionDeserializerHelperReversedAdapter extends NativeLocationSelectionDeserializerHelper {
    private final LocationSelectionDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public LocationSelectionDeserializerHelperReversedAdapter(LocationSelectionDeserializerHelper _LocationSelectionDeserializerHelper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_LocationSelectionDeserializerHelper, "_LocationSelectionDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _LocationSelectionDeserializerHelper;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializerHelper
    public NativeNoLocationSelection createNoLocationSelection() {
        NoLocationSelection createNoLocationSelectionFromJson = this.a.createNoLocationSelectionFromJson();
        NativeNoLocationSelection a = createNoLocationSelectionFromJson.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeNoLocationSelection.class), null, a, createNoLocationSelectionFromJson);
        return a;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializerHelper
    public NativeRadiusLocationSelection createRadiusLocationSelection() {
        RadiusLocationSelection createRadiusLocationSelectionFromJson = this.a.createRadiusLocationSelectionFromJson();
        NativeRadiusLocationSelection _impl = createRadiusLocationSelectionFromJson._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeRadiusLocationSelection.class), null, _impl, createRadiusLocationSelectionFromJson);
        return _impl;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializerHelper
    public NativeRectangularLocationSelection createRectangularLocationSelection() {
        RectangularLocationSelection createRectangularLocationSelectionFromJson = this.a.createRectangularLocationSelectionFromJson();
        NativeRectangularLocationSelection _impl = createRectangularLocationSelectionFromJson._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeRectangularLocationSelection.class), null, _impl, createRectangularLocationSelectionFromJson);
        return _impl;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    public /* synthetic */ LocationSelectionDeserializerHelperReversedAdapter(LocationSelectionDeserializerHelper locationSelectionDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(locationSelectionDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
