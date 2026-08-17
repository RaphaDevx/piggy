package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureListener;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureListener;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListener;", "_BarcodeCaptureListener", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "_BarcodeCapture", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListener;Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;", "mode", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;", "data", "", "onScan", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSession;Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;)V", "onSessionUpdated", "onObservationStarted", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;)V", "onObservationStopped", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCaptureListenerReversedAdapter extends NativeBarcodeCaptureListener {
    private final BarcodeCaptureListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodeCaptureListenerReversedAdapter(BarcodeCaptureListener barcodeCaptureListener, BarcodeCapture barcodeCapture, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCaptureListener, barcodeCapture, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureListener
    public void onObservationStarted(NativeBarcodeCapture mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCapture barcodeCapture = (BarcodeCapture) this.c.get();
        if (barcodeCapture != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new J(barcodeCapture));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((BarcodeCapture) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureListener
    public void onObservationStopped(NativeBarcodeCapture mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCapture barcodeCapture = (BarcodeCapture) this.c.get();
        if (barcodeCapture != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new K(barcodeCapture));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((BarcodeCapture) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureListener
    public void onScan(NativeBarcodeCapture mode, NativeBarcodeCaptureSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCapture barcodeCapture = (BarcodeCapture) this.c.get();
        if (barcodeCapture != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new L(barcodeCapture));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCaptureSession barcodeCaptureSession = (BarcodeCaptureSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSession.class), null, session, new M(barcodeCapture));
            FrameData frameData = (FrameData) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new N(data));
            this.a.onBarcodeScanned((BarcodeCapture) orPut, barcodeCaptureSession, frameData);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureListener
    public void onSessionUpdated(NativeBarcodeCapture mode, NativeBarcodeCaptureSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCapture barcodeCapture = (BarcodeCapture) this.c.get();
        if (barcodeCapture != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new O(barcodeCapture));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCaptureSession barcodeCaptureSession = (BarcodeCaptureSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSession.class), null, session, new P(barcodeCapture));
            FrameData frameData = (FrameData) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new Q(data));
            this.a.onSessionUpdated((BarcodeCapture) orPut, barcodeCaptureSession, frameData);
        }
    }

    public BarcodeCaptureListenerReversedAdapter(BarcodeCaptureListener _BarcodeCaptureListener, BarcodeCapture _BarcodeCapture, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeCaptureListener, "_BarcodeCaptureListener");
        Intrinsics.checkNotNullParameter(_BarcodeCapture, "_BarcodeCapture");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeCaptureListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_BarcodeCapture);
    }
}
