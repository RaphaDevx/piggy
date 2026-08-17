package com.scandit.datacapture.core.ui.gesture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.internal.module.ui.NativeSwipeToZoom;
import com.scandit.datacapture.core.internal.module.ui.NativeZoomGesture;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0018B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0007J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/core/ui/gesture/SwipeToZoom;", "Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;", "Lcom/scandit/datacapture/core/ui/gesture/SwipeToZoomProxy;", "Lcom/scandit/datacapture/core/internal/module/ui/NativeSwipeToZoom;", "impl", "<init>", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeSwipeToZoom;)V", "()V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeSwipeToZoom;", "Lcom/scandit/datacapture/core/internal/module/ui/NativeZoomGesture;", "_zoomGestureImpl", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeZoomGesture;", "", "toJson", "()Ljava/lang/String;", "", "triggerZoomIn", "triggerZoomOut", "Lcom/scandit/datacapture/core/ui/gesture/ZoomGestureListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/core/ui/gesture/ZoomGestureListener;)V", "removeListener", "com/scandit/datacapture/core/ui/gesture/b", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class SwipeToZoom implements ZoomGesture, SwipeToZoomProxy {
    private final /* synthetic */ SwipeToZoomProxyAdapter a;
    private final CopyOnWriteArraySet b;

    /* JADX WARN: Multi-variable type inference failed */
    public SwipeToZoom(NativeSwipeToZoom impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new SwipeToZoomProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.b = new CopyOnWriteArraySet();
        getA().addListener(new ZoomGestureListenerReversedAdapter(new b(this), this, null, 4, null));
    }

    @Override // com.scandit.datacapture.core.ui.gesture.SwipeToZoomProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeSwipeToZoom getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture, com.scandit.datacapture.core.ui.gesture.SwipeToZoomProxy
    @NativeImpl
    /* renamed from: _zoomGestureImpl */
    public NativeZoomGesture getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture
    public void addListener(ZoomGestureListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.add(listener);
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture
    public void removeListener(ZoomGestureListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.remove(listener);
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture, com.scandit.datacapture.core.ui.gesture.SwipeToZoomProxy
    @ProxyFunction(nativeName = "toJson")
    public String toJson() {
        return this.a.toJson();
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture, com.scandit.datacapture.core.ui.gesture.SwipeToZoomProxy
    @ProxyFunction(nativeName = "triggerZoomIn")
    public void triggerZoomIn() {
        this.a.triggerZoomIn();
    }

    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGesture, com.scandit.datacapture.core.ui.gesture.SwipeToZoomProxy
    @ProxyFunction(nativeName = "triggerZoomOut")
    public void triggerZoomOut() {
        this.a.triggerZoomOut();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SwipeToZoom() {
        /*
            r2 = this;
            com.scandit.datacapture.core.internal.module.ui.NativeSwipeToZoom r0 = com.scandit.datacapture.core.internal.module.ui.NativeSwipeToZoom.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.gesture.SwipeToZoom.<init>():void");
    }
}
