package com.scandit.datacapture.frameworks.barcode.selection.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession;
import io.sentry.cache.EnvelopeCache;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeSelectionSession.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/FrameworksBarcodeSelectionSession;", "", "frameSequenceId", "", "selectedBarcodes", "", "Lcom/scandit/datacapture/barcode/data/Barcode;", "selectionSession", "Ljava/lang/ref/WeakReference;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;", "(JLjava/util/List;Ljava/lang/ref/WeakReference;)V", "getFrameSequenceId", "()J", "getSelectedBarcodes", "()Ljava/util/List;", "getSelectionSession", "()Ljava/lang/ref/WeakReference;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeSelectionSession {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long frameSequenceId;
    private final List<Barcode> selectedBarcodes;
    private final WeakReference<BarcodeSelectionSession> selectionSession;

    public FrameworksBarcodeSelectionSession(long j, List<Barcode> selectedBarcodes, WeakReference<BarcodeSelectionSession> selectionSession) {
        Intrinsics.checkNotNullParameter(selectedBarcodes, "selectedBarcodes");
        Intrinsics.checkNotNullParameter(selectionSession, "selectionSession");
        this.frameSequenceId = j;
        this.selectedBarcodes = selectedBarcodes;
        this.selectionSession = selectionSession;
    }

    public final long getFrameSequenceId() {
        return this.frameSequenceId;
    }

    public final List<Barcode> getSelectedBarcodes() {
        return this.selectedBarcodes;
    }

    public /* synthetic */ FrameworksBarcodeSelectionSession(long j, List list, WeakReference weakReference, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, list, (i & 4) != 0 ? new WeakReference(null) : weakReference);
    }

    public final WeakReference<BarcodeSelectionSession> getSelectionSession() {
        return this.selectionSession;
    }

    /* compiled from: FrameworksBarcodeSelectionSession.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/FrameworksBarcodeSelectionSession$Companion;", "", "()V", "fromSelectionSession", "Lcom/scandit/datacapture/frameworks/barcode/selection/data/FrameworksBarcodeSelectionSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeSelectionSession fromSelectionSession(BarcodeSelectionSession session) {
            Intrinsics.checkNotNullParameter(session, "session");
            return new FrameworksBarcodeSelectionSession(session.getFrameSequenceId(), CollectionsKt.toList(session.getSelectedBarcodes()), new WeakReference(session));
        }
    }
}
