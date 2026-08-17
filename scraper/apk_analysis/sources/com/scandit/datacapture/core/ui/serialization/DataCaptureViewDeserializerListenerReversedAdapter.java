package com.scandit.datacapture.core.ui.serialization;

import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerListener;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.tealium.library.DataSources;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerListenerReversedAdapter;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializerListener;", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerListener;", "_DataCaptureViewDeserializerListener", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;", "_DataCaptureViewDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerListener;Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializer;", "deserializer", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "", "onViewDeserializationStarted", "(Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializer;Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "onViewDeserializationFinished", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureViewDeserializerListenerReversedAdapter extends NativeDataCaptureViewDeserializerListener {
    private final DataCaptureViewDeserializerListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ DataCaptureViewDeserializerListenerReversedAdapter(DataCaptureViewDeserializerListener dataCaptureViewDeserializerListener, DataCaptureViewDeserializer dataCaptureViewDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataCaptureViewDeserializerListener, dataCaptureViewDeserializer, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerListener
    public void onViewDeserializationFinished(NativeDataCaptureViewDeserializer deserializer, NativeDataCaptureView view, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureViewDeserializer dataCaptureViewDeserializer = (DataCaptureViewDeserializer) this.c.get();
        if (dataCaptureViewDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureViewDeserializer.class), null, deserializer, new f(dataCaptureViewDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            DataCaptureView dataCaptureView = (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new g(json));
            this.a.onViewDeserializationFinished((DataCaptureViewDeserializer) orPut, dataCaptureView, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializerListener
    public void onViewDeserializationStarted(NativeDataCaptureViewDeserializer deserializer, NativeDataCaptureView view, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureViewDeserializer dataCaptureViewDeserializer = (DataCaptureViewDeserializer) this.c.get();
        if (dataCaptureViewDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureViewDeserializer.class), null, deserializer, new h(dataCaptureViewDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            DataCaptureView dataCaptureView = (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new i(json));
            this.a.onViewDeserializationStarted((DataCaptureViewDeserializer) orPut, dataCaptureView, jsonValue);
        }
    }

    public DataCaptureViewDeserializerListenerReversedAdapter(DataCaptureViewDeserializerListener _DataCaptureViewDeserializerListener, DataCaptureViewDeserializer _DataCaptureViewDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_DataCaptureViewDeserializerListener, "_DataCaptureViewDeserializerListener");
        Intrinsics.checkNotNullParameter(_DataCaptureViewDeserializer, "_DataCaptureViewDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _DataCaptureViewDeserializerListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_DataCaptureViewDeserializer);
    }
}
