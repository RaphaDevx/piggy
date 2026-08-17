package com.scandit.datacapture.barcode.ar.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArListener;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyReversedAdapter;
import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@ProxyReversedAdapter(owner = BarcodeArInternal.class, value = NativeBarcodeArListener.class)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArListener;", "", "onObservationStarted", "", "barcodeAr", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;", "onObservationStopped", "onSessionUpdated", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArSession;", "frameData", "Lcom/scandit/datacapture/core/data/FrameData;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface BarcodeArListener {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        @ProxyFunction
        public static void onObservationStarted(BarcodeArListener barcodeArListener, BarcodeAr barcodeAr) {
            Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        }

        @ProxyFunction
        public static void onObservationStopped(BarcodeArListener barcodeArListener, BarcodeAr barcodeAr) {
            Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        }

        @ProxyFunction
        public static void onSessionUpdated(BarcodeArListener barcodeArListener, BarcodeAr barcodeAr, BarcodeArSession session, FrameData frameData) {
            Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(frameData, "frameData");
        }
    }

    @ProxyFunction
    void onObservationStarted(BarcodeAr barcodeAr);

    @ProxyFunction
    void onObservationStopped(BarcodeAr barcodeAr);

    @ProxyFunction
    void onSessionUpdated(BarcodeAr barcodeAr, BarcodeArSession session, FrameData frameData);
}
