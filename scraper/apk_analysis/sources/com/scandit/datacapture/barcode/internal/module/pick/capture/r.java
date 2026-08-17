package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class r extends NativeBarcodePickListener {
    public final BarcodePickListener a;
    public final ProxyCache b;
    public final WeakReference c;

    public r(f barcodePickListener, BarcodePickInternal mode) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(barcodePickListener, "barcodePickListener");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = barcodePickListener;
        this.b = proxyCache;
        this.c = new WeakReference(mode);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onItemResponseReceived(NativeBarcodePick mode, String id, boolean z) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(id, "id");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new h(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.a((BarcodePickInternal) orPut, id, z);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onObservationStarted(NativeBarcodePick mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new i(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.a((BarcodePickInternal) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onObservationStopped(NativeBarcodePick mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new j(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.b((BarcodePickInternal) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onPickUpdated(NativeBarcodePick mode, NativeBarcodePickSession session, NativeFrameData nativeFrameData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new l(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.a((BarcodePickInternal) orPut, (v) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePickSession.class), null, session, new m(barcodePickInternal)), nativeFrameData != null ? (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, nativeFrameData, new k(nativeFrameData)) : null);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onSessionUpdated(NativeBarcodePick mode, NativeBarcodePickSession session, NativeFrameData frameData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new o(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            v vVar = (v) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePickSession.class), null, session, new p(barcodePickInternal));
            FrameData frameData2 = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, frameData, new n(frameData));
            this.a.b((BarcodePickInternal) orPut, vVar, frameData2);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickListener
    public final void onStyleShouldBeRequested(NativeBarcodePick mode, HashMap requestedData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(requestedData, "requestedData");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePick.class), null, mode, new q(barcodePickInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.a((BarcodePickInternal) orPut, requestedData);
        }
    }
}
