package com.scandit.datacapture.barcode.internal.module.count.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class k extends NativeBarcodeCountListener {
    public final InternalBarcodeCountListener a;
    public final ProxyCache b;
    public final WeakReference c;

    public k(com.scandit.datacapture.barcode.count.capture.f _InternalBarcodeCountListener, BarcodeCount _BarcodeCount) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_InternalBarcodeCountListener, "_InternalBarcodeCountListener");
        Intrinsics.checkNotNullParameter(_BarcodeCount, "_BarcodeCount");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _InternalBarcodeCountListener;
        this.b = proxyCache;
        this.c = new WeakReference(_BarcodeCount);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener
    public final void onObservationStarted(NativeBarcodeCount mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCount barcodeCount = (BarcodeCount) this.c.get();
        if (barcodeCount != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode, new a(barcodeCount));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((BarcodeCount) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener
    public final void onObservationStopped(NativeBarcodeCount mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCount barcodeCount = (BarcodeCount) this.c.get();
        if (barcodeCount != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode, new b(barcodeCount));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((BarcodeCount) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener
    public final void onScan(NativeBarcodeCount mode, NativeBarcodeCountSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCount barcodeCount = (BarcodeCount) this.c.get();
        if (barcodeCount != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode, new c(barcodeCount));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            InternalBarcodeCountSession internalBarcodeCountSession = (InternalBarcodeCountSession) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountSession.class), null, session, new d(barcodeCount));
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new e(data));
            this.a.b((BarcodeCount) orPut, internalBarcodeCountSession, frameData);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener
    public final void onSessionUpdated(NativeBarcodeCount mode, NativeBarcodeCountSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCount barcodeCount = (BarcodeCount) this.c.get();
        if (barcodeCount != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode, new f(barcodeCount));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            InternalBarcodeCountSession internalBarcodeCountSession = (InternalBarcodeCountSession) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountSession.class), null, session, new g(barcodeCount));
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new h(data));
            this.a.a((BarcodeCount) orPut, internalBarcodeCountSession, frameData);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountListener
    public final void onTrackedBarcodeUncounted(NativeBarcodeCountSession session, NativeTrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCount barcodeCount = (BarcodeCount) this.c.get();
        if (barcodeCount != null) {
            this.a.a((InternalBarcodeCountSession) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountSession.class), null, session, new i(barcodeCount)), (TrackedBarcode) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeTrackedBarcode.class), null, trackedBarcode, new j(trackedBarcode)));
        }
    }
}
