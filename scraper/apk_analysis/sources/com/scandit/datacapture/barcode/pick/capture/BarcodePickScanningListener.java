package com.scandit.datacapture.barcode.pick.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickScanningListener;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyReversedAdapter;
import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@ProxyReversedAdapter(owner = BarcodePickInternal.class, value = NativeBarcodePickScanningListener.class)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0017J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0017¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickScanningListener;", "", "onObservationStarted", "", "barcodePick", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "onObservationStopped", "onScanningSessionCompleted", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickScanningSession;", "onScanningSessionUpdated", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface BarcodePickScanningListener {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        @ProxyFunction
        public static void onObservationStarted(BarcodePickScanningListener barcodePickScanningListener, BarcodePick barcodePick) {
            Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        }

        @ProxyFunction
        public static void onObservationStopped(BarcodePickScanningListener barcodePickScanningListener, BarcodePick barcodePick) {
            Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        }

        @ProxyFunction
        public static void onScanningSessionCompleted(BarcodePickScanningListener barcodePickScanningListener, BarcodePick barcodePick, BarcodePickScanningSession session) {
            Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
            Intrinsics.checkNotNullParameter(session, "session");
        }

        @ProxyFunction
        public static void onScanningSessionUpdated(BarcodePickScanningListener barcodePickScanningListener, BarcodePick barcodePick, BarcodePickScanningSession session) {
            Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
            Intrinsics.checkNotNullParameter(session, "session");
        }
    }

    @ProxyFunction
    void onObservationStarted(BarcodePick barcodePick);

    @ProxyFunction
    void onObservationStopped(BarcodePick barcodePick);

    @ProxyFunction
    void onScanningSessionCompleted(BarcodePick barcodePick, BarcodePickScanningSession session);

    @ProxyFunction
    void onScanningSessionUpdated(BarcodePick barcodePick, BarcodePickScanningSession session);
}
