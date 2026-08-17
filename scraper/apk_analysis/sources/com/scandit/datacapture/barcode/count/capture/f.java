package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.data.Cluster;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSessionCallback;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements InternalBarcodeCountListener {
    public final WeakReference a;

    public f(BarcodeCount owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void a(InternalBarcodeCountSession session, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void b(BarcodeCount mode, InternalBarcodeCountSession session, FrameData data) {
        CopyOnWriteArrayList<BarcodeCountListener> listeners$scandit_barcode_capture;
        CopyOnWriteArrayList<InternalBarcodeCountListener> internalListeners$scandit_barcode_capture;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCount barcodeCount = (BarcodeCount) this.a.get();
        if (barcodeCount != null && (internalListeners$scandit_barcode_capture = barcodeCount.getInternalListeners$scandit_barcode_capture()) != null) {
            Iterator<T> it = internalListeners$scandit_barcode_capture.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeCountListener) it.next()).b(mode, session, data);
            }
        }
        BarcodeCount barcodeCount2 = (BarcodeCount) this.a.get();
        if (barcodeCount2 == null || (listeners$scandit_barcode_capture = barcodeCount2.getListeners$scandit_barcode_capture()) == null) {
            return;
        }
        BarcodeCountSession a = a(session);
        Iterator<T> it2 = listeners$scandit_barcode_capture.iterator();
        while (it2.hasNext()) {
            ((BarcodeCountListener) it2.next()).onScan(mode, a, data);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void onObservationStarted(BarcodeCount mode) {
        CopyOnWriteArrayList<BarcodeCountListener> listeners$scandit_barcode_capture;
        CopyOnWriteArrayList<InternalBarcodeCountListener> internalListeners$scandit_barcode_capture;
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCount barcodeCount = (BarcodeCount) this.a.get();
        if (barcodeCount != null && (internalListeners$scandit_barcode_capture = barcodeCount.getInternalListeners$scandit_barcode_capture()) != null) {
            Iterator<T> it = internalListeners$scandit_barcode_capture.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeCountListener) it.next()).onObservationStarted(mode);
            }
        }
        BarcodeCount barcodeCount2 = (BarcodeCount) this.a.get();
        if (barcodeCount2 == null || (listeners$scandit_barcode_capture = barcodeCount2.getListeners$scandit_barcode_capture()) == null) {
            return;
        }
        Iterator<T> it2 = listeners$scandit_barcode_capture.iterator();
        while (it2.hasNext()) {
            ((BarcodeCountListener) it2.next()).onObservationStarted(mode);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void onObservationStopped(BarcodeCount mode) {
        CopyOnWriteArrayList<BarcodeCountListener> listeners$scandit_barcode_capture;
        CopyOnWriteArrayList<InternalBarcodeCountListener> internalListeners$scandit_barcode_capture;
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeCount barcodeCount = (BarcodeCount) this.a.get();
        if (barcodeCount != null && (internalListeners$scandit_barcode_capture = barcodeCount.getInternalListeners$scandit_barcode_capture()) != null) {
            Iterator<T> it = internalListeners$scandit_barcode_capture.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeCountListener) it.next()).onObservationStopped(mode);
            }
        }
        BarcodeCount barcodeCount2 = (BarcodeCount) this.a.get();
        if (barcodeCount2 == null || (listeners$scandit_barcode_capture = barcodeCount2.getListeners$scandit_barcode_capture()) == null) {
            return;
        }
        Iterator<T> it2 = listeners$scandit_barcode_capture.iterator();
        while (it2.hasNext()) {
            ((BarcodeCountListener) it2.next()).onObservationStopped(mode);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void a(BarcodeCount mode, InternalBarcodeCountSession session, FrameData data) {
        CopyOnWriteArrayList<BarcodeCountListener> listeners$scandit_barcode_capture;
        CopyOnWriteArrayList<InternalBarcodeCountListener> internalListeners$scandit_barcode_capture;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeCount barcodeCount = (BarcodeCount) this.a.get();
        if (barcodeCount != null && (internalListeners$scandit_barcode_capture = barcodeCount.getInternalListeners$scandit_barcode_capture()) != null) {
            Iterator<T> it = internalListeners$scandit_barcode_capture.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeCountListener) it.next()).a(mode, session, data);
            }
        }
        BarcodeCount barcodeCount2 = (BarcodeCount) this.a.get();
        if (barcodeCount2 == null || (listeners$scandit_barcode_capture = barcodeCount2.getListeners$scandit_barcode_capture()) == null) {
            return;
        }
        BarcodeCountSession a = a(session);
        Iterator<T> it2 = listeners$scandit_barcode_capture.iterator();
        while (it2.hasNext()) {
            ((BarcodeCountListener) it2.next()).onSessionUpdated(mode, a, data);
        }
    }

    public static BarcodeCountSession a(InternalBarcodeCountSession internalBarcodeCountSession) {
        List plus = CollectionsKt.plus((Collection) internalBarcodeCountSession.i(), (Iterable) internalBarcodeCountSession.f().values());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        Iterator it = plus.iterator();
        while (it.hasNext()) {
            arrayList.add(((TrackedBarcode) it.next()).getBarcode());
        }
        ArrayList<NativeCluster> clusters = internalBarcodeCountSession.a().getClusters();
        Intrinsics.checkNotNullExpressionValue(clusters, "getClusters(...)");
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(clusters, 10));
        for (NativeCluster nativeCluster : clusters) {
            Intrinsics.checkNotNull(nativeCluster);
            arrayList2.add(new Cluster(nativeCluster));
        }
        return new BarcodeCountSession(arrayList, arrayList2, internalBarcodeCountSession.e(), internalBarcodeCountSession.b(), internalBarcodeCountSession.j(), new InternalBarcodeCountSessionCallback(new c(internalBarcodeCountSession), new d(internalBarcodeCountSession), new e(internalBarcodeCountSession)));
    }
}
