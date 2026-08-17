package com.scandit.datacapture.frameworks.barcode.capture.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureSession;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.data.LocalizedOnlyBarcode;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCaptureSession.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\u0002\u0010\fR\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/FrameworksBarcodeCaptureSession;", "", "frameSequenceId", "", "newlyRecognizedBarcode", "Lcom/scandit/datacapture/barcode/data/Barcode;", "newlyLocalizedBarcodes", "", "Lcom/scandit/datacapture/barcode/data/LocalizedOnlyBarcode;", "captureSession", "Ljava/lang/ref/WeakReference;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSession;", "(JLcom/scandit/datacapture/barcode/data/Barcode;Ljava/util/List;Ljava/lang/ref/WeakReference;)V", "getCaptureSession", "()Ljava/lang/ref/WeakReference;", "getFrameSequenceId", "()J", "getNewlyLocalizedBarcodes", "()Ljava/util/List;", "getNewlyRecognizedBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeCaptureSession {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final WeakReference<BarcodeCaptureSession> captureSession;
    private final long frameSequenceId;
    private final List<LocalizedOnlyBarcode> newlyLocalizedBarcodes;
    private final Barcode newlyRecognizedBarcode;

    public FrameworksBarcodeCaptureSession(long j, Barcode barcode, List<LocalizedOnlyBarcode> newlyLocalizedBarcodes, WeakReference<BarcodeCaptureSession> captureSession) {
        Intrinsics.checkNotNullParameter(newlyLocalizedBarcodes, "newlyLocalizedBarcodes");
        Intrinsics.checkNotNullParameter(captureSession, "captureSession");
        this.frameSequenceId = j;
        this.newlyRecognizedBarcode = barcode;
        this.newlyLocalizedBarcodes = newlyLocalizedBarcodes;
        this.captureSession = captureSession;
    }

    public final long getFrameSequenceId() {
        return this.frameSequenceId;
    }

    public final Barcode getNewlyRecognizedBarcode() {
        return this.newlyRecognizedBarcode;
    }

    public final List<LocalizedOnlyBarcode> getNewlyLocalizedBarcodes() {
        return this.newlyLocalizedBarcodes;
    }

    public /* synthetic */ FrameworksBarcodeCaptureSession(long j, Barcode barcode, List list, WeakReference weakReference, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, barcode, list, (i & 8) != 0 ? new WeakReference(null) : weakReference);
    }

    public final WeakReference<BarcodeCaptureSession> getCaptureSession() {
        return this.captureSession;
    }

    /* compiled from: FrameworksBarcodeCaptureSession.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/FrameworksBarcodeCaptureSession$Companion;", "", "()V", "fromCaptureSession", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/FrameworksBarcodeCaptureSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSession;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeCaptureSession fromCaptureSession(BarcodeCaptureSession session) {
            Intrinsics.checkNotNullParameter(session, "session");
            return new FrameworksBarcodeCaptureSession(session.getFrameSequenceId(), session.getNewlyRecognizedBarcode(), session.getNewlyLocalizedBarcodes(), new WeakReference(session));
        }
    }
}
