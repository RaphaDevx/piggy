package com.scandit.datacapture.frameworks.barcode.batch.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSession;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeBatchSession.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\u0010\u000bR\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", "", "frameSequenceId", "", "trackedBarcodes", "", "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "batchSession", "Ljava/lang/ref/WeakReference;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSession;", "(JLjava/util/Map;Ljava/lang/ref/WeakReference;)V", "getBatchSession", "()Ljava/lang/ref/WeakReference;", "getFrameSequenceId", "()J", "getTrackedBarcodes", "()Ljava/util/Map;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeBatchSession {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final WeakReference<BarcodeBatchSession> batchSession;
    private final long frameSequenceId;
    private final Map<Integer, TrackedBarcode> trackedBarcodes;

    public FrameworksBarcodeBatchSession(long j, Map<Integer, TrackedBarcode> trackedBarcodes, WeakReference<BarcodeBatchSession> batchSession) {
        Intrinsics.checkNotNullParameter(trackedBarcodes, "trackedBarcodes");
        Intrinsics.checkNotNullParameter(batchSession, "batchSession");
        this.frameSequenceId = j;
        this.trackedBarcodes = trackedBarcodes;
        this.batchSession = batchSession;
    }

    public final long getFrameSequenceId() {
        return this.frameSequenceId;
    }

    public final Map<Integer, TrackedBarcode> getTrackedBarcodes() {
        return this.trackedBarcodes;
    }

    public /* synthetic */ FrameworksBarcodeBatchSession(long j, Map map, WeakReference weakReference, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, map, (i & 4) != 0 ? new WeakReference(null) : weakReference);
    }

    public final WeakReference<BarcodeBatchSession> getBatchSession() {
        return this.batchSession;
    }

    /* compiled from: FrameworksBarcodeBatchSession.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession$Companion;", "", "()V", "fromBatchSession", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSession;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeBatchSession fromBatchSession(BarcodeBatchSession session) {
            Intrinsics.checkNotNullParameter(session, "session");
            return new FrameworksBarcodeBatchSession(session.getFrameSequenceId(), MapsKt.toMap(session.getTrackedBarcodes()), new WeakReference(session));
        }
    }
}
