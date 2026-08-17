package com.scandit.datacapture.barcode.pick.capture;

import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicListener;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickPublicListener;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickListener;", "_BarcodePickListener", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/BarcodePickInternal;", "_BarcodePickInternal", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickListener;Lcom/scandit/datacapture/barcode/internal/module/pick/capture/BarcodePickInternal;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePick;", "mode", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickPublicSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "", "onSessionUpdated", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePick;Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickPublicSession;)V", "onObservationStarted", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePick;)V", "onObservationStopped", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickListenerReversedAdapter extends NativeBarcodePickPublicListener {
    private final BarcodePickListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodePickListenerReversedAdapter(BarcodePickListener barcodePickListener, BarcodePickInternal barcodePickInternal, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodePickListener, barcodePickInternal, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicListener
    public void onObservationStarted(NativeBarcodePick mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (((BarcodePickInternal) this.c.get()) != null) {
            this.a.onObservationStarted(BarcodeNativeTypeFactory.INSTANCE.convert(mode));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicListener
    public void onObservationStopped(NativeBarcodePick mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (((BarcodePickInternal) this.c.get()) != null) {
            this.a.onObservationStopped(BarcodeNativeTypeFactory.INSTANCE.convert(mode));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicListener
    public void onSessionUpdated(NativeBarcodePick mode, NativeBarcodePickPublicSession session) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.c.get();
        if (barcodePickInternal != null) {
            this.a.onSessionUpdated(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (BarcodePickSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodePickPublicSession.class), null, session, new b(barcodePickInternal)));
        }
    }

    public BarcodePickListenerReversedAdapter(BarcodePickListener _BarcodePickListener, BarcodePickInternal _BarcodePickInternal, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodePickListener, "_BarcodePickListener");
        Intrinsics.checkNotNullParameter(_BarcodePickInternal, "_BarcodePickInternal");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodePickListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_BarcodePickInternal);
    }
}
