package com.scandit.datacapture.core.capture;

import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001e\u0010\u001dJ\u0019\u0010!\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/core/capture/DataCaptureContextProxyAdapter;", "Lcom/scandit/datacapture/core/capture/DataCaptureContextProxy;", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "_NativeDataCaptureContext", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "_setContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "_context", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;", "settings", "applySettings", "(Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;)V", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "mode", "Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_addModeAsyncWrapped", "(Lcom/scandit/datacapture/core/capture/DataCaptureMode;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_setModeAsyncWrapped", "_removeModeAsyncWrapped", "_removeCurrentModeAsyncWrapped", "()Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_removeAllModesAsyncWrapped", "Lcom/scandit/datacapture/core/source/FrameSource;", "frameSource", "_setFrameSourceAsyncWrapped", "(Lcom/scandit/datacapture/core/source/FrameSource;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureContextProxyAdapter implements DataCaptureContextProxy {
    private final NativeDataCaptureContext a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private DataCaptureContext c;

    public DataCaptureContextProxyAdapter(NativeDataCaptureContext _NativeDataCaptureContext, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeDataCaptureContext, "_NativeDataCaptureContext");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeDataCaptureContext;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _addModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeDataCaptureMode c = mode.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureMode.class), null, c, mode);
        NativeWrappedFuture addModeAsyncWrapped = this.a.addModeAsyncWrapped(c);
        Intrinsics.checkNotNull(addModeAsyncWrapped);
        return addModeAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public DataCaptureContext _context() {
        DataCaptureContext dataCaptureContext = this.c;
        if (dataCaptureContext != null) {
            return dataCaptureContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setContext_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    /* renamed from: _impl, reason: from getter */
    public NativeDataCaptureContext getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _removeAllModesAsyncWrapped() {
        NativeWrappedFuture removeAllModesAsyncWrapped = this.a.removeAllModesAsyncWrapped();
        Intrinsics.checkNotNull(removeAllModesAsyncWrapped);
        return removeAllModesAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _removeCurrentModeAsyncWrapped() {
        NativeWrappedFuture removeCurrentModeAsyncWrapped = this.a.removeCurrentModeAsyncWrapped();
        Intrinsics.checkNotNull(removeCurrentModeAsyncWrapped);
        return removeCurrentModeAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _removeModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeDataCaptureMode c = mode.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureMode.class), null, c, mode);
        NativeWrappedFuture removeModeAsyncWrapped = this.a.removeModeAsyncWrapped(c);
        Intrinsics.checkNotNull(removeModeAsyncWrapped);
        return removeModeAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public void _setContext(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        this.c = dataCaptureContext;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _setFrameSourceAsyncWrapped(FrameSource frameSource) {
        NativeFrameSource nativeFrameSource = null;
        if (frameSource != null) {
            NativeFrameSource c = frameSource.getC();
            this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, c, frameSource);
            nativeFrameSource = c;
        }
        NativeWrappedFuture frameSourceAsyncWrapped = this.a.setFrameSourceAsyncWrapped(nativeFrameSource);
        Intrinsics.checkNotNull(frameSourceAsyncWrapped);
        return frameSourceAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public NativeWrappedFuture _setModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeDataCaptureMode c = mode.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureMode.class), null, c, mode);
        NativeWrappedFuture modeAsyncWrapped = this.a.setModeAsyncWrapped(c);
        Intrinsics.checkNotNull(modeAsyncWrapped);
        return modeAsyncWrapped;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    public void applySettings(DataCaptureContextSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeDataCaptureContextSettings a = settings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContextSettings.class), null, a, settings);
        this.a.applySettings(a);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    public /* synthetic */ DataCaptureContextProxyAdapter(NativeDataCaptureContext nativeDataCaptureContext, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeDataCaptureContext, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
