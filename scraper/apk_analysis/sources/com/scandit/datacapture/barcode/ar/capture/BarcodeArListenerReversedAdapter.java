package com.scandit.datacapture.barcode.ar.capture;

import com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArListener;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
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

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0015\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeArListener;", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArListener;", "_BarcodeArListener", "Lcom/scandit/datacapture/barcode/internal/module/ar/capture/BarcodeArInternal;", "_BarcodeArInternal", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArListener;Lcom/scandit/datacapture/barcode/internal/module/ar/capture/BarcodeArInternal;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeAr;", "barcodeAr", "Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeArSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;", "data", "", "onSessionUpdated", "(Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeAr;Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeArSession;Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;)V", "onObservationStarted", "(Lcom/scandit/datacapture/barcode/internal/module/ar/capture/NativeBarcodeAr;)V", "onObservationStopped", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArListenerReversedAdapter extends NativeBarcodeArListener {
    private final BarcodeArListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodeArListenerReversedAdapter(BarcodeArListener barcodeArListener, BarcodeArInternal barcodeArInternal, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeArListener, barcodeArInternal, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArListener
    public void onObservationStarted(NativeBarcodeAr barcodeAr) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        if (((BarcodeArInternal) this.c.get()) != null) {
            this.a.onObservationStarted(BarcodeNativeTypeFactory.INSTANCE.convert(barcodeAr));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArListener
    public void onObservationStopped(NativeBarcodeAr barcodeAr) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        if (((BarcodeArInternal) this.c.get()) != null) {
            this.a.onObservationStopped(BarcodeNativeTypeFactory.INSTANCE.convert(barcodeAr));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArListener
    public void onSessionUpdated(NativeBarcodeAr barcodeAr, NativeBarcodeArSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeArInternal barcodeArInternal = (BarcodeArInternal) this.c.get();
        if (barcodeArInternal != null) {
            this.a.onSessionUpdated(BarcodeNativeTypeFactory.INSTANCE.convert(barcodeAr), (BarcodeArSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeArSession.class), null, session, new a(barcodeArInternal)), (FrameData) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new b(data)));
        }
    }

    public BarcodeArListenerReversedAdapter(BarcodeArListener _BarcodeArListener, BarcodeArInternal _BarcodeArInternal, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeArListener, "_BarcodeArListener");
        Intrinsics.checkNotNullParameter(_BarcodeArInternal, "_BarcodeArInternal");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeArListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_BarcodeArInternal);
    }
}
