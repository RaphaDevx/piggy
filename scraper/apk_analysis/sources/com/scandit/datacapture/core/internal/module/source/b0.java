package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class b0 extends NativeFrameSourceListener {
    public final T a;
    public final ProxyCache b;
    public final WeakReference c;

    public b0(T _FrameSourceListenerInternal, FrameSource _FrameSource) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_FrameSourceListenerInternal, "_FrameSourceListenerInternal");
        Intrinsics.checkNotNullParameter(_FrameSource, "_FrameSource");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _FrameSourceListenerInternal;
        this.b = proxyCache;
        this.c = new WeakReference(_FrameSource);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onDataCaptureContextAttached(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (((FrameSource) this.c.get()) != null) {
            this.a.b((DataCaptureContext) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new U(context)));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onDataCaptureContextDetached(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (((FrameSource) this.c.get()) != null) {
            this.a.a((DataCaptureContext) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new V(context)));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onFrameOutputAndroid(NativeFrameSource source, NativeFrameData frame) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(frame, "frame");
        FrameSource frameSource = (FrameSource) this.c.get();
        if (frameSource != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, source, new W(frameSource));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, frame, new X(frame));
            this.a.onFrameOutput((FrameSource) orPut, frameData);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onObservationStarted(NativeFrameSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        FrameSource frameSource = (FrameSource) this.c.get();
        if (frameSource != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, source, new Y(frameSource));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((FrameSource) orPut);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onObservationStopped(NativeFrameSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        FrameSource frameSource = (FrameSource) this.c.get();
        if (frameSource != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, source, new Z(frameSource));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((FrameSource) orPut);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
    public final void onStateChanged(NativeFrameSource source, FrameSourceState newState) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(newState, "newState");
        FrameSource frameSource = (FrameSource) this.c.get();
        if (frameSource != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameSource.class), null, source, new a0(frameSource));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onStateChanged((FrameSource) orPut, newState);
        }
    }
}
