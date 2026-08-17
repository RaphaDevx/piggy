package com.scandit.datacapture.barcode.internal.module.ar.feedback;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListener;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.barcode.ar.feedback.BarcodeArFeedback;
import com.scandit.datacapture.core.common.feedback.Feedback;
import com.scandit.datacapture.core.data.FrameData;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class BarcodeArFeedbackEmitter implements BarcodeArListener {
    private final BarcodeAr a;
    private final boolean b;
    private final boolean c;
    private boolean d;
    private long e;
    private Feedback f;
    private Feedback g;

    public BarcodeArFeedbackEmitter(BarcodeAr barcodeAr, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        this.a = barcodeAr;
        this.b = z;
        this.c = z2;
        this.e = -1L;
        this.f = null;
        this.g = null;
    }

    public final void a(BarcodeArFeedback userSetFeedback) {
        Intrinsics.checkNotNullParameter(userSetFeedback, "userSetFeedback");
        Feedback feedback = new Feedback(this.c ? userSetFeedback.getTapped().getVibration() : null, this.b ? userSetFeedback.getTapped().getSound() : null);
        Feedback feedback2 = this.f;
        if (feedback2 != null) {
            feedback2.release();
        }
        this.f = feedback;
        Feedback feedback3 = new Feedback(this.c ? userSetFeedback.getScanned().getVibration() : null, this.b ? userSetFeedback.getScanned().getSound() : null);
        Feedback feedback4 = this.g;
        if (feedback4 != null) {
            feedback4.release();
        }
        this.g = feedback3;
    }

    public final void b() {
        Feedback feedback;
        if (!this.d || (feedback = this.f) == null) {
            return;
        }
        feedback.emit();
    }

    public final void c() {
        this.d = true;
        this.a.addListener(this);
        a(this.a.getFeedback());
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public final void onObservationStarted(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStarted(this, barcodeAr);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public final void onObservationStopped(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStopped(this, barcodeAr);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public final void onSessionUpdated(BarcodeAr barcodeAr, BarcodeArSession session, FrameData frameData) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        if (session.getAddedTrackedBarcodes().isEmpty() || System.currentTimeMillis() - this.e <= 500) {
            return;
        }
        Feedback feedback = this.g;
        if (feedback != null) {
            feedback.emit();
        }
        this.e = System.currentTimeMillis();
    }

    public final void a() {
        this.d = false;
        this.a.removeListener(this);
        Feedback feedback = this.f;
        if (feedback != null) {
            feedback.release();
        }
        Feedback feedback2 = this.g;
        if (feedback2 != null) {
            feedback2.release();
        }
    }
}
