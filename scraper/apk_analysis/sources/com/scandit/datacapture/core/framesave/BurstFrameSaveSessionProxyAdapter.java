package com.scandit.datacapture.core.framesave;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession;
import com.scandit.datacapture.core.internal.module.framesave.NativeFrameSaveSession;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/core/framesave/BurstFrameSaveSessionProxyAdapter;", "Lcom/scandit/datacapture/core/framesave/BurstFrameSaveSessionProxy;", "Lcom/scandit/datacapture/core/internal/module/framesave/NativeBurstFrameSaveSession;", "_NativeBurstFrameSaveSession", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/module/framesave/NativeBurstFrameSaveSession;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/framesave/NativeBurstFrameSaveSession;", "Lcom/scandit/datacapture/core/internal/module/framesave/NativeFrameSaveSession;", "_frameSaveSessionImpl", "()Lcom/scandit/datacapture/core/internal/module/framesave/NativeFrameSaveSession;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "addToContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "removeFromContext", "enable", "()V", "disable", "save", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BurstFrameSaveSessionProxyAdapter implements BurstFrameSaveSessionProxy {
    private final NativeBurstFrameSaveSession a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeFrameSaveSession c;

    public BurstFrameSaveSessionProxyAdapter(NativeBurstFrameSaveSession _NativeBurstFrameSaveSession, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBurstFrameSaveSession, "_NativeBurstFrameSaveSession");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBurstFrameSaveSession;
        this.proxyCache = proxyCache;
        NativeFrameSaveSession asFrameSaveSession = _NativeBurstFrameSaveSession.asFrameSaveSession();
        Intrinsics.checkNotNullExpressionValue(asFrameSaveSession, "asFrameSaveSession(...)");
        this.c = asFrameSaveSession;
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    /* renamed from: _frameSaveSessionImpl, reason: from getter */
    public NativeFrameSaveSession getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBurstFrameSaveSession getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    public void addToContext(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        NativeDataCaptureContext a = dataCaptureContext.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, dataCaptureContext);
        this.a.addToContext(a);
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    public void disable() {
        this.a.disable();
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    public void enable() {
        this.a.enable();
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    public void removeFromContext(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        NativeDataCaptureContext a = dataCaptureContext.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, dataCaptureContext);
        this.a.removeFromContext(a);
    }

    @Override // com.scandit.datacapture.core.framesave.BurstFrameSaveSessionProxy
    public void save() {
        this.a.save();
    }

    public /* synthetic */ BurstFrameSaveSessionProxyAdapter(NativeBurstFrameSaveSession nativeBurstFrameSaveSession, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBurstFrameSaveSession, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
