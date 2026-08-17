package com.scandit.datacapture.barcode.count.capture.list;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListListener;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureListListener;", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;", "_BarcodeCountCaptureListListener", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "_BarcodeCountCaptureList", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;", "list", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureListSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "", "onCaptureListSessionUpdated", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureListSession;)V", "onCaptureListCompleted", "onObservationStarted", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;)V", "onObservationStopped", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCountCaptureListListenerReversedAdapter extends NativeBarcodeCountCaptureListListener {
    private final BarcodeCountCaptureListListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodeCountCaptureListListenerReversedAdapter(BarcodeCountCaptureListListener barcodeCountCaptureListListener, BarcodeCountCaptureList barcodeCountCaptureList, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCountCaptureListListener, barcodeCountCaptureList, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListListener
    public void onCaptureListCompleted(NativeBarcodeCountCaptureList list, NativeBarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodeCountCaptureList barcodeCountCaptureList = (BarcodeCountCaptureList) this.c.get();
        if (barcodeCountCaptureList != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureList.class), null, list, new b(barcodeCountCaptureList));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountCaptureListSession barcodeCountCaptureListSession = (BarcodeCountCaptureListSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureListSession.class), null, session, new c(barcodeCountCaptureList));
            this.a.onCaptureListCompleted((BarcodeCountCaptureList) orPut, barcodeCountCaptureListSession);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListListener
    public void onCaptureListSessionUpdated(NativeBarcodeCountCaptureList list, NativeBarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodeCountCaptureList barcodeCountCaptureList = (BarcodeCountCaptureList) this.c.get();
        if (barcodeCountCaptureList != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureList.class), null, list, new d(barcodeCountCaptureList));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountCaptureListSession barcodeCountCaptureListSession = (BarcodeCountCaptureListSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureListSession.class), null, session, new e(barcodeCountCaptureList));
            this.a.onCaptureListSessionUpdated((BarcodeCountCaptureList) orPut, barcodeCountCaptureListSession);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListListener
    public void onObservationStarted(NativeBarcodeCountCaptureList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        BarcodeCountCaptureList barcodeCountCaptureList = (BarcodeCountCaptureList) this.c.get();
        if (barcodeCountCaptureList != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureList.class), null, list, new f(barcodeCountCaptureList));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((BarcodeCountCaptureList) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListListener
    public void onObservationStopped(NativeBarcodeCountCaptureList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        BarcodeCountCaptureList barcodeCountCaptureList = (BarcodeCountCaptureList) this.c.get();
        if (barcodeCountCaptureList != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountCaptureList.class), null, list, new g(barcodeCountCaptureList));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((BarcodeCountCaptureList) orPut);
        }
    }

    public BarcodeCountCaptureListListenerReversedAdapter(BarcodeCountCaptureListListener _BarcodeCountCaptureListListener, BarcodeCountCaptureList _BarcodeCountCaptureList, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeCountCaptureListListener, "_BarcodeCountCaptureListListener");
        Intrinsics.checkNotNullParameter(_BarcodeCountCaptureList, "_BarcodeCountCaptureList");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeCountCaptureListListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_BarcodeCountCaptureList);
    }
}
