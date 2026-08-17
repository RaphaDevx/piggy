package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource;
import com.scandit.datacapture.core.source.BitmapFrameSourceProxy;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000e\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/core/source/BitmapFrameSourceProxyAdapter;", "Lcom/scandit/datacapture/core/source/BitmapFrameSourceProxy;", "Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataCollectionFrameSource;", "_NativeFrameDataCollectionFrameSource", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataCollectionFrameSource;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataCollectionFrameSource;", "Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "_frameSourceImpl", "()Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "desiredState", "Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_switchToDesiredState", "(Lcom/scandit/datacapture/core/source/FrameSourceState;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getCurrentState", "()Lcom/scandit/datacapture/core/source/FrameSourceState;", "currentState", "getDesiredState", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BitmapFrameSourceProxyAdapter implements BitmapFrameSourceProxy {
    private final NativeFrameDataCollectionFrameSource a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeFrameSource c;

    public BitmapFrameSourceProxyAdapter(NativeFrameDataCollectionFrameSource _NativeFrameDataCollectionFrameSource, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeFrameDataCollectionFrameSource, "_NativeFrameDataCollectionFrameSource");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeFrameDataCollectionFrameSource;
        this.proxyCache = proxyCache;
        NativeFrameSource asFrameSource = _NativeFrameDataCollectionFrameSource.asFrameSource();
        Intrinsics.checkNotNullExpressionValue(asFrameSource, "asFrameSource(...)");
        this.c = asFrameSource;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    /* renamed from: _frameSourceImpl, reason: from getter */
    public NativeFrameSource getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    /* renamed from: _impl, reason: from getter */
    public NativeFrameDataCollectionFrameSource getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public NativeWrappedFuture _switchToDesiredState(FrameSourceState desiredState) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        NativeWrappedFuture switchToDesiredStateAsyncAndroid = this.a.switchToDesiredStateAsyncAndroid(desiredState);
        Intrinsics.checkNotNull(switchToDesiredStateAsyncAndroid);
        return switchToDesiredStateAsyncAndroid;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public FrameSourceState getCurrentState() {
        FrameSourceState currentState = this.a.getCurrentState();
        Intrinsics.checkNotNull(currentState);
        return currentState;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public FrameSourceState getDesiredState() {
        FrameSourceState desiredState = this.a.getDesiredState();
        Intrinsics.checkNotNull(desiredState);
        return desiredState;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public void switchToDesiredState(FrameSourceState frameSourceState, Callback<? super Boolean> callback) {
        BitmapFrameSourceProxy.DefaultImpls.switchToDesiredState(this, frameSourceState, callback);
    }

    public /* synthetic */ BitmapFrameSourceProxyAdapter(NativeFrameDataCollectionFrameSource nativeFrameDataCollectionFrameSource, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeFrameDataCollectionFrameSource, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
