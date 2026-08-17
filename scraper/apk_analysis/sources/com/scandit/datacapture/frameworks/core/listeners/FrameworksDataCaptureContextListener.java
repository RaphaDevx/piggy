package com.scandit.datacapture.frameworks.core.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.common.ContextStatusUtilsKt;
import com.scandit.datacapture.core.license.LicenseInfo;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksDataCaptureContextListener.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureContextListener;", "Lcom/scandit/datacapture/core/capture/DataCaptureContextListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;)V", "didChangeStatusEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "observationStartedEvent", "disable", "", "enable", "onObservationStarted", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "onStatusChanged", "contextStatus", "Lcom/scandit/datacapture/core/common/ContextStatus;", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksDataCaptureContextListener implements DataCaptureContextListener {
    public static final String DID_CHANGE_STATUS_EVENT_NAME = "DataCaptureContextListener.onStatusChanged";
    public static final String DID_START_OBSERVING_EVENT_NAME = "DataCaptureContextListener.onObservationStarted";
    private static final String FIELD_LICENCE_INFO = "licenseInfo";
    private static final String FIELD_STATUS = "status";
    private final Event didChangeStatusEvent;
    private final Emitter eventEmitter;
    private final AtomicReference<Boolean> isEnabled;
    private final Event observationStartedEvent;

    public FrameworksDataCaptureContextListener(Emitter eventEmitter) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.isEnabled = new AtomicReference<>(false);
        this.observationStartedEvent = new Event(DID_START_OBSERVING_EVENT_NAME);
        this.didChangeStatusEvent = new Event(DID_CHANGE_STATUS_EVENT_NAME);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onFrameSourceChanged(DataCaptureContext dataCaptureContext, FrameSource frameSource) {
        DataCaptureContextListener.DefaultImpls.onFrameSourceChanged(this, dataCaptureContext, frameSource);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onModeAdded(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        DataCaptureContextListener.DefaultImpls.onModeAdded(this, dataCaptureContext, dataCaptureMode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onModeRemoved(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        DataCaptureContextListener.DefaultImpls.onModeRemoved(this, dataCaptureContext, dataCaptureMode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onObservationStopped(DataCaptureContext dataCaptureContext) {
        DataCaptureContextListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onObservationStarted(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        if (this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent(DID_START_OBSERVING_EVENT_NAME)) {
            Event event = this.observationStartedEvent;
            Emitter emitter = this.eventEmitter;
            Pair[] pairArr = new Pair[1];
            LicenseInfo licenseInfo = dataCaptureContext.getLicenseInfo();
            pairArr[0] = TuplesKt.to(FIELD_LICENCE_INFO, licenseInfo != null ? licenseInfo.toJson() : null);
            event.emit(emitter, MapsKt.mutableMapOf(pairArr));
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public void onStatusChanged(DataCaptureContext dataCaptureContext, ContextStatus contextStatus) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(contextStatus, "contextStatus");
        if (this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent(DID_CHANGE_STATUS_EVENT_NAME)) {
            this.didChangeStatusEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("status", ContextStatusUtilsKt.toJson(contextStatus))));
        }
    }
}
