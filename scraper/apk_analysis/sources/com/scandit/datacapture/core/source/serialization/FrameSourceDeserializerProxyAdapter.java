package com.scandit.datacapture.core.source.serialization;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0017\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b \u0010!J\u001f\u0010#\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u00190.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00062"}, d2 = {"Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerProxyAdapter;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerProxy;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeFrameSourceDeserializer;", "_NativeFrameSourceDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/module/serialization/NativeFrameSourceDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeFrameSourceDeserializer;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;)V", "_deserializer", "()Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerHelper;", "helper", "_setHelper", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerHelper;)V", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_setListener", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerListener;)V", "", "jsonData", "Lcom/scandit/datacapture/core/source/FrameSource;", "_frameSourceFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/source/FrameSource;", "Lcom/scandit/datacapture/core/json/JsonValue;", "json", "_frameSourceFromJsonValue", "(Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/source/FrameSource;", "frameSource", "updateFrameSourceFromJson", "(Lcom/scandit/datacapture/core/source/FrameSource;Ljava/lang/String;)Lcom/scandit/datacapture/core/source/FrameSource;", "updateFrameSourceFromJsonValue", "(Lcom/scandit/datacapture/core/source/FrameSource;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/source/FrameSource;", "Lcom/scandit/datacapture/core/source/CameraSettings;", "cameraSettingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/source/CameraSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getWarnings", "()Ljava/util/List;", "warnings", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class FrameSourceDeserializerProxyAdapter implements FrameSourceDeserializerProxy {
    private final NativeFrameSourceDeserializer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private FrameSourceDeserializer c;

    public FrameSourceDeserializerProxyAdapter(NativeFrameSourceDeserializer _NativeFrameSourceDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeFrameSourceDeserializer, "_NativeFrameSourceDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeFrameSourceDeserializer;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public FrameSourceDeserializer _deserializer() {
        FrameSourceDeserializer frameSourceDeserializer = this.c;
        if (frameSourceDeserializer != null) {
            return frameSourceDeserializer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setDeserializer_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public FrameSource _frameSourceFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeFrameSource frameSourceFromJson = this.a.frameSourceFromJson(CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
        Intrinsics.checkNotNull(frameSourceFromJson);
        return (FrameSource) proxyCache.require(orCreateKotlinClass, null, frameSourceFromJson);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public FrameSource _frameSourceFromJsonValue(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        NativeJsonValue a = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a, json);
        NativeFrameSource frameSourceFromJson = this.a.frameSourceFromJson(a);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
        Intrinsics.checkNotNull(frameSourceFromJson);
        return (FrameSource) proxyCache.require(orCreateKotlinClass, null, frameSourceFromJson);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeFrameSourceDeserializer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public void _setDeserializer(FrameSourceDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.c = deserializer;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public void _setHelper(FrameSourceDeserializerHelper helper) {
        this.a.setHelper(helper != null ? (FrameSourceDeserializerHelperReversedAdapter) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(FrameSourceDeserializerHelper.class), null, helper, new j(helper)) : null);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public void _setListener(FrameSourceDeserializerListener listener) {
        this.a.setListener(listener != null ? (FrameSourceDeserializerListenerReversedAdapter) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(FrameSourceDeserializerListener.class), this, listener, new k(listener, this)) : null);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public CameraSettings cameraSettingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        NativeCameraSettings cameraSettingsFromJson = this.a.cameraSettingsFromJson(coreNativeTypeFactory.convert(jsonData));
        Intrinsics.checkNotNull(cameraSettingsFromJson);
        return coreNativeTypeFactory.convert(cameraSettingsFromJson);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public List<String> getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public CameraSettings updateCameraSettingsFromJson(CameraSettings cameraSettings, String str) {
        return FrameSourceDeserializerProxy.DefaultImpls.updateCameraSettingsFromJson(this, cameraSettings, str);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public FrameSource updateFrameSourceFromJson(FrameSource frameSource, String jsonData) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeFrameSource c = frameSource.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, c, frameSource);
        NativeFrameSource updateFrameSourceFromJson = this.a.updateFrameSourceFromJson(c, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
        Intrinsics.checkNotNull(updateFrameSourceFromJson);
        return (FrameSource) proxyCache.require(orCreateKotlinClass, null, updateFrameSourceFromJson);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerProxy
    public FrameSource updateFrameSourceFromJsonValue(FrameSource frameSource, JsonValue json) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeFrameSource c = frameSource.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, c, frameSource);
        NativeJsonValue a = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a, json);
        NativeFrameSource updateFrameSourceFromJson = this.a.updateFrameSourceFromJson(c, a);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
        Intrinsics.checkNotNull(updateFrameSourceFromJson);
        return (FrameSource) proxyCache.require(orCreateKotlinClass, null, updateFrameSourceFromJson);
    }

    public /* synthetic */ FrameSourceDeserializerProxyAdapter(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeFrameSourceDeserializer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
