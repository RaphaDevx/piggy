package com.scandit.datacapture.barcode.selection.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionListener;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession;
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

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ)\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J)\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionListener;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "barcodeSelection", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListener;Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "mode", "", "onObservationStarted", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;)V", "onObservationStopped", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;", "data", "onSessionUpdated", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSession;Lcom/scandit/datacapture/core/internal/sdk/data/NativeFrameData;)V", "onSelectionUpdated", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionListenerReversedAdapter extends NativeBarcodeSelectionListener {
    private final BarcodeSelectionListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodeSelectionListenerReversedAdapter(BarcodeSelectionListener barcodeSelectionListener, BarcodeSelection barcodeSelection, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeSelectionListener, barcodeSelection, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionListener
    public void onObservationStarted(NativeBarcodeSelection mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeSelection barcodeSelection = (BarcodeSelection) this.c.get();
        if (barcodeSelection != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode, new x(barcodeSelection));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((BarcodeSelection) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionListener
    public void onObservationStopped(NativeBarcodeSelection mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeSelection barcodeSelection = (BarcodeSelection) this.c.get();
        if (barcodeSelection != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode, new y(barcodeSelection));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((BarcodeSelection) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionListener
    public void onSelectionUpdated(NativeBarcodeSelection mode, NativeBarcodeSelectionSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodeSelection barcodeSelection = (BarcodeSelection) this.c.get();
        if (barcodeSelection != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode, new A(barcodeSelection));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onSelectionUpdated((BarcodeSelection) orPut, (BarcodeSelectionSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSession.class), null, session, new B(barcodeSelection)), data != null ? (FrameData) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new z(data)) : null);
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionListener
    public void onSessionUpdated(NativeBarcodeSelection mode, NativeBarcodeSelectionSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        BarcodeSelection barcodeSelection = (BarcodeSelection) this.c.get();
        if (barcodeSelection != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode, new D(barcodeSelection));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onSessionUpdated((BarcodeSelection) orPut, (BarcodeSelectionSession) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSession.class), null, session, new E(barcodeSelection)), data != null ? (FrameData) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new C(data)) : null);
        }
    }

    public BarcodeSelectionListenerReversedAdapter(BarcodeSelectionListener listener, BarcodeSelection barcodeSelection, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(barcodeSelection, "barcodeSelection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = listener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(barcodeSelection);
    }
}
