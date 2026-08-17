package com.scandit.datacapture.core.framesave;

import com.scandit.datacapture.core.framesave.FrameSaveSessionListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements FrameSaveSessionListener {
    public final WeakReference a;

    public c(EngineSequenceFrameSaveSession owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.framesave.FrameSaveSessionListener
    public final void onFrameSaveFailure(FrameSaveSession session, String message, long j) {
        CopyOnWriteArraySet<FrameSaveSessionListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(message, "message");
        EngineSequenceFrameSaveSession engineSequenceFrameSaveSession = (EngineSequenceFrameSaveSession) this.a.get();
        if (engineSequenceFrameSaveSession == null || (listeners$scandit_capture_core = engineSequenceFrameSaveSession.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((FrameSaveSessionListener) it.next()).onFrameSaveFailure(session, message, j);
        }
    }

    @Override // com.scandit.datacapture.core.framesave.FrameSaveSessionListener
    public final void onFrameSaveSuccess(FrameSaveSession session, String message, long j) {
        CopyOnWriteArraySet<FrameSaveSessionListener> listeners$scandit_capture_core;
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(message, "message");
        EngineSequenceFrameSaveSession engineSequenceFrameSaveSession = (EngineSequenceFrameSaveSession) this.a.get();
        if (engineSequenceFrameSaveSession == null || (listeners$scandit_capture_core = engineSequenceFrameSaveSession.getListeners$scandit_capture_core()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_capture_core.iterator();
        while (it.hasNext()) {
            ((FrameSaveSessionListener) it.next()).onFrameSaveSuccess(session, message, j);
        }
    }

    @Override // com.scandit.datacapture.core.framesave.FrameSaveSessionListener
    public final void onObservationStarted(FrameSaveSession frameSaveSession) {
        FrameSaveSessionListener.DefaultImpls.onObservationStarted(this, frameSaveSession);
    }

    @Override // com.scandit.datacapture.core.framesave.FrameSaveSessionListener
    public final void onObservationStopped(FrameSaveSession frameSaveSession) {
        FrameSaveSessionListener.DefaultImpls.onObservationStopped(this, frameSaveSession);
    }
}
