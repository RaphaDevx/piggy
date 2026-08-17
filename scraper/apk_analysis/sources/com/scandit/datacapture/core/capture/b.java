package com.scandit.datacapture.core.capture;

import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.source.FrameSource;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements DataCaptureContextListener {
    public final WeakReference a;

    public b(DataCaptureContext owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onFrameSourceChanged(DataCaptureContext dataCaptureContext, FrameSource frameSource) {
        CopyOnWriteArraySet<DataCaptureContextListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        DataCaptureContext dataCaptureContext2 = (DataCaptureContext) this.a.get();
        if (dataCaptureContext2 == null || (listeners$scandit_capture_core = dataCaptureContext2.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((DataCaptureContextListener) it.next()).onFrameSourceChanged(dataCaptureContext, frameSource);
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onModeAdded(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        CopyOnWriteArraySet<DataCaptureContextListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(dataCaptureMode, "dataCaptureMode");
        DataCaptureContext dataCaptureContext2 = (DataCaptureContext) this.a.get();
        if (dataCaptureContext2 == null || (listeners$scandit_capture_core = dataCaptureContext2.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((DataCaptureContextListener) it.next()).onModeAdded(dataCaptureContext, dataCaptureMode);
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onModeRemoved(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        CopyOnWriteArraySet<DataCaptureContextListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(dataCaptureMode, "dataCaptureMode");
        DataCaptureContext dataCaptureContext2 = (DataCaptureContext) this.a.get();
        if (dataCaptureContext2 == null || (listeners$scandit_capture_core = dataCaptureContext2.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((DataCaptureContextListener) it.next()).onModeRemoved(dataCaptureContext, dataCaptureMode);
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onObservationStarted(DataCaptureContext dataCaptureContext) {
        Object obj;
        List list;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        DataCaptureContext dataCaptureContext2 = (DataCaptureContext) this.a.get();
        if (dataCaptureContext2 != null) {
            obj = dataCaptureContext2.e;
            synchronized (obj) {
                dataCaptureContext2.f = true;
                list = CollectionsKt.toList(dataCaptureContext2.getListeners$scandit_capture_core());
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((DataCaptureContextListener) it.next()).onObservationStarted(dataCaptureContext);
            }
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onObservationStopped(DataCaptureContext dataCaptureContext) {
        DataCaptureContextListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onStatusChanged(DataCaptureContext dataCaptureContext, ContextStatus contextStatus) {
        CopyOnWriteArraySet<DataCaptureContextListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(contextStatus, "contextStatus");
        DataCaptureContext dataCaptureContext2 = (DataCaptureContext) this.a.get();
        if (dataCaptureContext2 == null || (listeners$scandit_capture_core = dataCaptureContext2.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((DataCaptureContextListener) it.next()).onStatusChanged(dataCaptureContext, contextStatus);
        }
    }
}
