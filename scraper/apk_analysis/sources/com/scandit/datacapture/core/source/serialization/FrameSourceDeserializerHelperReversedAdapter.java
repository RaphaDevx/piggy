package com.scandit.datacapture.core.source.serialization;

import android.util.Log;
import com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializerHelper;
import com.scandit.datacapture.core.internal.module.source.NativeAbstractCamera;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.BitmapFrameSource;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J1\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeFrameSourceDeserializerHelper;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerHelper;", "helper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/source/CameraPosition;", "position", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "settings", "", "cameraDeviceType", "cameraSubtype", "Lcom/scandit/datacapture/core/internal/module/source/NativeAbstractCamera;", "createCamera", "(Lcom/scandit/datacapture/core/source/CameraPosition;Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;Ljava/lang/String;Ljava/lang/String;)Lcom/scandit/datacapture/core/internal/module/source/NativeAbstractCamera;", "camera", "", "applySettings", "(Lcom/scandit/datacapture/core/internal/module/source/NativeAbstractCamera;Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;)V", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "updateCameraFromJson", "(Lcom/scandit/datacapture/core/internal/module/source/NativeAbstractCamera;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "base64Image", "Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "createImageFrameSource", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class FrameSourceDeserializerHelperReversedAdapter extends NativeFrameSourceDeserializerHelper {
    private final FrameSourceDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public /* synthetic */ FrameSourceDeserializerHelperReversedAdapter(FrameSourceDeserializerHelper frameSourceDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameSourceDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializerHelper
    public void applySettings(NativeAbstractCamera camera, NativeCameraSettings settings) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter(settings, "settings");
        try {
            ProxyCache proxyCache = this.proxyCache;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
            NativeFrameSource asFrameSource = camera.asFrameSource();
            Intrinsics.checkNotNullExpressionValue(asFrameSource, "asFrameSource(...)");
            FrameSource frameSource = (FrameSource) proxyCache.require(orCreateKotlinClass, null, asFrameSource);
            Camera camera2 = frameSource instanceof Camera ? (Camera) frameSource : null;
            if (camera2 != null) {
                this.a.applySettings(camera2, CoreNativeTypeFactory.INSTANCE.convert(settings));
                return;
            }
            throw new IllegalStateException(("No cached kotlin version of Camera (" + camera + ") found (cast failure).").toString());
        } catch (Exception e) {
            Log.e("ScanditDataCapture", "Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializerHelper
    public NativeAbstractCamera createCamera(CameraPosition position, NativeCameraSettings settings, String cameraDeviceType, String cameraSubtype) {
        NativeAndroidCamera a;
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(cameraDeviceType, "cameraDeviceType");
        Intrinsics.checkNotNullParameter(cameraSubtype, "cameraSubtype");
        try {
            Camera createCamera = this.a.createCamera(position, CoreNativeTypeFactory.INSTANCE.convert(settings), cameraDeviceType, cameraSubtype);
            if (createCamera != null) {
                ProxyCache proxyCache = this.proxyCache;
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
                NativeFrameSource asFrameSource = createCamera.getA().asFrameSource();
                Intrinsics.checkNotNullExpressionValue(asFrameSource, "asFrameSource(...)");
                proxyCache.put(orCreateKotlinClass, null, asFrameSource, createCamera);
            } else {
                createCamera = null;
            }
            if (createCamera == null || (a = createCamera.getA()) == null) {
                return null;
            }
            return a.asAbstractCamera();
        } catch (Exception e) {
            Log.e("ScanditDataCapture", "Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializerHelper
    public NativeFrameSource createImageFrameSource(String base64Image) {
        NativeFrameDataCollectionFrameSource _impl;
        Intrinsics.checkNotNullParameter(base64Image, "base64Image");
        try {
            FrameSource createImageFrameSource = this.a.createImageFrameSource(base64Image);
            BitmapFrameSource bitmapFrameSource = createImageFrameSource instanceof BitmapFrameSource ? (BitmapFrameSource) createImageFrameSource : null;
            if (bitmapFrameSource != null) {
                ProxyCache proxyCache = this.proxyCache;
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
                NativeFrameSource asFrameSource = bitmapFrameSource._impl().asFrameSource();
                Intrinsics.checkNotNullExpressionValue(asFrameSource, "asFrameSource(...)");
                proxyCache.put(orCreateKotlinClass, null, asFrameSource, bitmapFrameSource);
            } else {
                bitmapFrameSource = null;
            }
            if (bitmapFrameSource == null || (_impl = bitmapFrameSource._impl()) == null) {
                return null;
            }
            return _impl.asFrameSource();
        } catch (Exception e) {
            Log.e("ScanditDataCapture", "Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializerHelper
    public void updateCameraFromJson(NativeAbstractCamera camera, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            ProxyCache proxyCache = this.proxyCache;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeFrameSource.class);
            NativeFrameSource asFrameSource = camera.asFrameSource();
            Intrinsics.checkNotNullExpressionValue(asFrameSource, "asFrameSource(...)");
            FrameSource frameSource = (FrameSource) proxyCache.require(orCreateKotlinClass, null, asFrameSource);
            Camera camera2 = frameSource instanceof Camera ? (Camera) frameSource : null;
            if (camera2 != null) {
                this.a.updateCameraFromJson(camera2, new JsonValue(json));
                return;
            }
            throw new IllegalStateException(("No cached kotlin version of Camera (" + camera + ") found (cast failure).").toString());
        } catch (Exception e) {
            Log.e("ScanditDataCapture", "Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    public FrameSourceDeserializerHelperReversedAdapter(FrameSourceDeserializerHelper helper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = helper;
        this.proxyCache = proxyCache;
    }
}
