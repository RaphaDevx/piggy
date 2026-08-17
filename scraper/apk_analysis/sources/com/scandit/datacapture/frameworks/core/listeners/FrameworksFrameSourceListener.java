package com.scandit.datacapture.frameworks.core.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceListener;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.FrameSourceStateUtilsKt;
import com.scandit.datacapture.core.source.TorchListener;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.source.TorchStateUtilsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksFrameSourceListener.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\u00020\u00012\u00020\u0002:\u0001\u0017B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;", "Lcom/scandit/datacapture/core/source/FrameSourceListener;", "Lcom/scandit/datacapture/core/source/TorchListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;)V", "frameStateChangedEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "torchStateChangedEvent", "disable", "", "enable", "onStateChanged", "frameSource", "Lcom/scandit/datacapture/core/source/FrameSource;", "newState", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "onTorchStateChanged", "state", "Lcom/scandit/datacapture/core/source/TorchState;", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksFrameSourceListener implements FrameSourceListener, TorchListener {
    private static final String FIELD_STATE = "state";
    public static final String FRAME_STATE_CHANGE_EVENT_NAME = "FrameSourceListener.onStateChanged";
    public static final String TORCH_STATE_CHANGE_EVENT_NAME = "TorchListener.onTorchStateChanged";
    private final Emitter eventEmitter;
    private final Event frameStateChangedEvent;
    private final AtomicReference<Boolean> isEnabled;
    private final Event torchStateChangedEvent;

    public FrameworksFrameSourceListener(Emitter eventEmitter) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.isEnabled = new AtomicReference<>(false);
        this.frameStateChangedEvent = new Event(FRAME_STATE_CHANGE_EVENT_NAME);
        this.torchStateChangedEvent = new Event(TORCH_STATE_CHANGE_EVENT_NAME);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public void onFrameOutput(FrameSource frameSource, FrameData frameData) {
        FrameSourceListener.DefaultImpls.onFrameOutput(this, frameSource, frameData);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public void onObservationStarted(FrameSource frameSource) {
        FrameSourceListener.DefaultImpls.onObservationStarted(this, frameSource);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public void onObservationStopped(FrameSource frameSource) {
        FrameSourceListener.DefaultImpls.onObservationStopped(this, frameSource);
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public void onStateChanged(FrameSource frameSource, FrameSourceState newState) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(newState, "newState");
        if (this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent(FRAME_STATE_CHANGE_EVENT_NAME)) {
            this.frameStateChangedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("state", FrameSourceStateUtilsKt.toJson(newState))));
        }
    }

    @Override // com.scandit.datacapture.core.source.TorchListener
    public void onTorchStateChanged(TorchState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent(TORCH_STATE_CHANGE_EVENT_NAME)) {
            this.torchStateChangedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("state", TorchStateUtilsKt.toJson(state))));
        }
    }
}
