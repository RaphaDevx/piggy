package com.scandit.datacapture.core.ui.serialization;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0017\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020(0'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006,"}, d2 = {"Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerProxyAdapter;", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerProxy;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializer;", "_NativeDataCaptureViewDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureViewDeserializer;", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;)V", "_deserializer", "()Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerHelper;", "helper", "_setHelper", "(Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerHelper;)V", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_setListener", "(Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializerListener;)V", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/core/json/JsonValue;", "json", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "_viewFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/ui/DataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "_updateViewFromJson", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/ui/DataCaptureView;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "", "getWarnings", "()Ljava/util/List;", "warnings", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureViewDeserializerProxyAdapter implements DataCaptureViewDeserializerProxy {
    private final NativeDataCaptureViewDeserializer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private DataCaptureViewDeserializer c;

    public DataCaptureViewDeserializerProxyAdapter(NativeDataCaptureViewDeserializer _NativeDataCaptureViewDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeDataCaptureViewDeserializer, "_NativeDataCaptureViewDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeDataCaptureViewDeserializer;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public DataCaptureViewDeserializer _deserializer() {
        DataCaptureViewDeserializer dataCaptureViewDeserializer = this.c;
        if (dataCaptureViewDeserializer != null) {
            return dataCaptureViewDeserializer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setDeserializer_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeDataCaptureViewDeserializer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public void _setDeserializer(DataCaptureViewDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.c = deserializer;
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public void _setHelper(DataCaptureViewDeserializerHelper helper) {
        this.a.setHelper(helper != null ? (DataCaptureViewDeserializerHelperReversedAdapter) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(DataCaptureViewDeserializerHelper.class), null, helper, new j(helper)) : null);
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public void _setListener(DataCaptureViewDeserializerListener listener) {
        this.a.setListener(listener != null ? (DataCaptureViewDeserializerListenerReversedAdapter) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(DataCaptureViewDeserializerListener.class), this, listener, new k(listener, this)) : null);
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public DataCaptureView _updateViewFromJson(DataCaptureView view, JsonValue json) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeDataCaptureView a = view.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, a, view);
        NativeJsonValue a2 = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a2, json);
        NativeDataCaptureView updateViewFromJson = this.a.updateViewFromJson(a, a2);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class);
        Intrinsics.checkNotNull(updateViewFromJson);
        return (DataCaptureView) proxyCache.require(orCreateKotlinClass, null, updateViewFromJson);
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public DataCaptureView _viewFromJson(DataCaptureContext dataCaptureContext, JsonValue json) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeDataCaptureContext a = dataCaptureContext.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, dataCaptureContext);
        NativeJsonValue a2 = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a2, json);
        NativeDataCaptureView viewFromJson = this.a.viewFromJson(a, a2);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class);
        Intrinsics.checkNotNull(viewFromJson);
        return (DataCaptureView) proxyCache.require(orCreateKotlinClass, null, viewFromJson);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerProxy
    public List<String> getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    public /* synthetic */ DataCaptureViewDeserializerProxyAdapter(NativeDataCaptureViewDeserializer nativeDataCaptureViewDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeDataCaptureViewDeserializer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
