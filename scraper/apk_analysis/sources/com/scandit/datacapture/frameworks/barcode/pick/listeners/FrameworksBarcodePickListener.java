package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickListener;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSession;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.cache.EnvelopeCache;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickListener.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickListener;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "viewId", "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "disable", "", "enable", "onSessionUpdated", "barcodePick", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSession;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodePickListener implements BarcodePickListener {
    public static final String DID_UPDATE_SESSION = "BarcodePickListener.didUpdateSession";
    private final Emitter emitter;
    private final AtomicReference<Boolean> isEnabled;
    private final int viewId;

    public FrameworksBarcodePickListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.isEnabled = new AtomicReference<>(false);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    @ProxyFunction
    public void onObservationStarted(BarcodePick barcodePick) {
        BarcodePickListener.DefaultImpls.onObservationStarted(this, barcodePick);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    @ProxyFunction
    public void onObservationStopped(BarcodePick barcodePick) {
        BarcodePickListener.DefaultImpls.onObservationStopped(this, barcodePick);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    public void onSessionUpdated(BarcodePick barcodePick, BarcodePickSession session) {
        Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.isEnabled.get().booleanValue()) {
            this.emitter.emit(DID_UPDATE_SESSION, MapsKt.mutableMapOf(TuplesKt.to(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, session.toJson()), TuplesKt.to("viewId", Integer.valueOf(this.viewId))));
        }
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
    }
}
