package com.scandit.datacapture.core.ui.serialization;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.module.ui.NativeSwipeToZoom;
import com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.gesture.SwipeToZoom;
import com.scandit.datacapture.core.ui.gesture.TapToFocus;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0015\u0010\u000fJ\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializerHelper;", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerHelper;", "_DataCaptureViewDeserializerHelper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "context", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "createView", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;)Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "", "createdNullFocusGesture", "()V", "", "showUiIndicator", "Lcom/scandit/datacapture/core/internal/module/ui/NativeTapToFocus;", "createTapToFocus", "(Z)Lcom/scandit/datacapture/core/internal/module/ui/NativeTapToFocus;", "createdNullZoomGesture", "Lcom/scandit/datacapture/core/internal/module/ui/NativeSwipeToZoom;", "createSwipeToZoom", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeSwipeToZoom;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "updateViewFromJson", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureViewDeserializerHelperReversedAdapter extends NativeDataCaptureViewDeserializerHelper {
    private final DataCaptureViewDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public DataCaptureViewDeserializerHelperReversedAdapter(DataCaptureViewDeserializerHelper _DataCaptureViewDeserializerHelper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_DataCaptureViewDeserializerHelper, "_DataCaptureViewDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _DataCaptureViewDeserializerHelper;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public NativeSwipeToZoom createSwipeToZoom() {
        SwipeToZoom createSwipeToZoom = this.a.createSwipeToZoom();
        NativeSwipeToZoom a = createSwipeToZoom.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeSwipeToZoom.class), null, a, createSwipeToZoom);
        return a;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public NativeTapToFocus createTapToFocus(boolean showUiIndicator) {
        TapToFocus createTapToFocus = this.a.createTapToFocus(showUiIndicator);
        NativeTapToFocus a = createTapToFocus.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeTapToFocus.class), null, a, createTapToFocus);
        return a;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public NativeDataCaptureView createView(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DataCaptureView createView = this.a.createView((DataCaptureContext) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new d(context)));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(DataCaptureView.class), null, createView, createView._impl());
        NativeDataCaptureView _impl = createView._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, _impl, createView);
        return _impl;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public void createdNullFocusGesture() {
        this.a.createdNullFocusGesture();
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public void createdNullZoomGesture() {
        this.a.createdNullZoomGesture();
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerHelper
    public void updateViewFromJson(NativeDataCaptureView view, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateViewFromJson((DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new e(json)));
    }

    public /* synthetic */ DataCaptureViewDeserializerHelperReversedAdapter(DataCaptureViewDeserializerHelper dataCaptureViewDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataCaptureViewDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
