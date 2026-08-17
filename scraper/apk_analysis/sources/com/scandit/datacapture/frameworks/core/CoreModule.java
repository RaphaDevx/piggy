package com.scandit.datacapture.frameworks.core;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.feedback.Feedback;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.PointUtilsKt;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPositionDeserializer;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.FrameSourceStateDeserializer;
import com.scandit.datacapture.core.source.FrameSourceStateUtilsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.data.DataCaptureViewCreationData;
import com.scandit.datacapture.frameworks.core.data.defaults.CoreDefaults;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.CameraNotReadyError;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.errors.FrameDataNullError;
import com.scandit.datacapture.frameworks.core.errors.ModuleNotStartedError;
import com.scandit.datacapture.frameworks.core.errors.NullDataCaptureViewError;
import com.scandit.datacapture.frameworks.core.errors.WrongCameraPositionError;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.configuration.FramesHandlingConfiguration;
import com.scandit.datacapture.frameworks.core.handlers.DataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.handlers.DefaultDataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.listeners.FrameworksDataCaptureContextListener;
import com.scandit.datacapture.frameworks.core.listeners.FrameworksFrameSourceDeserializer;
import com.scandit.datacapture.frameworks.core.listeners.FrameworksFrameSourceListener;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.result.NoopFrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.DefaultWorkerThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.scandit.datacapture.frameworks.core.utils.WorkerThread;
import io.sentry.SentryEvent;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONException;

/* compiled from: CoreModule.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0016\u0018\u0000 n2\u00020\u0001:\u0001nBm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0018\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u00104\u001a\u00020*2\u0006\u00105\u001a\u000202J\u000e\u00104\u001a\u00020*2\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u00020*J\u0006\u00109\u001a\u00020*J\u0016\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020,2\u0006\u0010-\u001a\u00020.J!\u0010>\u001a\b\u0012\u0004\u0012\u00020,0?2\u0006\u0010=\u001a\u00020,ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010AJ\b\u0010B\u001a\u0004\u0018\u00010CJ\u000e\u0010D\u001a\u00020*2\u0006\u0010-\u001a\u00020.J\u0010\u0010E\u001a\u0004\u0018\u0001022\u0006\u00106\u001a\u000207J\u0014\u0010F\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0006\u0012\u0004\u0018\u00010H0GJ!\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001a0?2\u0006\u0010=\u001a\u00020,ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u0010AJ\u0016\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010M\u001a\u00020*2\u0006\u0010L\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010N\u001a\u00020*2\u0006\u0010L\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u0010O\u001a\u00020*2\u0006\u0010-\u001a\u00020.J)\u0010P\u001a\b\u0012\u0004\u0012\u00020,0?2\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020,ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bQ\u0010RJ)\u0010S\u001a\b\u0012\u0004\u0012\u00020,0?2\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020,ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bT\u0010RJ\u0016\u0010U\u001a\u00020*2\u0006\u0010=\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0010\u0010V\u001a\u00020*2\u0006\u0010W\u001a\u00020XH\u0016J\b\u0010Y\u001a\u00020*H\u0016J\u0006\u0010Z\u001a\u00020*J\u000e\u0010[\u001a\u00020*2\u0006\u00106\u001a\u000207J\u0006\u0010\\\u001a\u00020*J\u0006\u0010]\u001a\u00020*J\u000e\u0010^\u001a\u00020*2\u0006\u0010-\u001a\u00020.J\b\u0010_\u001a\u00020*H\u0002J\u0016\u0010`\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\b\u0010a\u001a\u00020*H\u0002J\u0016\u0010b\u001a\u00020*2\u0006\u0010c\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u000e\u0010d\u001a\u00020*2\u0006\u0010e\u001a\u00020CJ\u0006\u0010f\u001a\u00020*J\u000e\u0010g\u001a\u00020*2\u0006\u00106\u001a\u000207J\u0006\u0010h\u001a\u00020*J\u0006\u0010i\u001a\u00020*J\u0018\u0010j\u001a\u00020*2\u0006\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0007J\u0016\u0010k\u001a\u00020*2\u0006\u00103\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u001e\u0010l\u001a\u00020*2\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u001e\u0010m\u001a\u00020*2\u0006\u00106\u001a\u0002072\u0006\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020.R\u001a\u0010\u0019\u001a\u00020\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\u00020$*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0018\u0010#\u001a\u00020'*\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010(\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006o"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/CoreModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "frameSourceListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;", "dataCaptureContextListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureContextListener;", "frameSourceDeserializerListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "workerThread", "Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "dataCaptureViewHandler", "Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureContextListener;Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;)V", "automaticAttachToDeserializationObserver", "", "getAutomaticAttachToDeserializationObserver", "()Z", "setAutomaticAttachToDeserializationObserver", "(Z)V", "displayMetricsDensity", "", "latestFeedback", "Lcom/scandit/datacapture/core/common/feedback/Feedback;", "densityIndependent", "Lcom/scandit/datacapture/core/common/geometry/Point;", "getDensityIndependent", "(Lcom/scandit/datacapture/core/common/geometry/Point;)Lcom/scandit/datacapture/core/common/geometry/Point;", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "addModeToContext", "", "modeJson", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "createContextFromJson", "json", "createDataCaptureView", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "viewJson", "dataCaptureViewDisposed", "dataCaptureView", "viewId", "", "disposeContext", "disposeDataCaptureView", "emitFeedback", "feedbackAsJson", "getCameraState", "cameraPosition", "getCameraStateByPosition", "Lkotlin/Result;", "getCameraStateByPosition-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "getCurrentCameraDesiredState", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "getCurrentCameraState", "getDataCaptureViewById", "getDefaults", "", "", "getIsTorchAvailableByPosition", "getIsTorchAvailableByPosition-IoAF18A", "getLastFrameAsJson", "frameId", "getLastFrameOrNullAsJson", "getLastFrameOrNullAsMap", "getOpenSourceSoftwareLicenseInfo", "getViewPointForFramePoint", "getViewPointForFramePoint-gIAlu-s", "(ILjava/lang/String;)Ljava/lang/Object;", "getViewQuadrilateralForFrameQuadrilateral", "getViewQuadrilateralForFrameQuadrilateral-gIAlu-s", "isTorchAvailable", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "registerDataCaptureContextListener", "registerDataCaptureViewListener", "registerFrameSourceListener", "registerTopmostDataCaptureViewListener", "removeAllModes", "removeAllViews", "removeModeFromContext", "removeTopMostDataCaptureView", "switchCameraToDesiredState", "stateJson", "switchToDesiredCameraState", SentryThread.JsonKeys.STATE, "unregisterDataCaptureContextListener", "unregisterDataCaptureViewListener", "unregisterFrameSourceListener", "unregisterTopmostDataCaptureViewListener", "updateContextFromJson", "updateDataCaptureView", "viewPointForFramePoint", "viewQuadrilateralForFrameQuadrilateral", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class CoreModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean automaticAttachToDeserializationObserver;
    private final FrameworksCaptureContext captureContext;
    private final FrameworksDataCaptureContextListener dataCaptureContextListener;
    private final DataCaptureViewHandler dataCaptureViewHandler;
    private final DefaultDeserializationLifecycleObserver deserializationLifecycleObserver;
    private float displayMetricsDensity;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksFrameSourceDeserializer frameSourceDeserializerListener;
    private final FrameworksFrameSourceListener frameSourceListener;
    private Feedback latestFeedback;
    private final FrameworksLog logger;
    private final MainThread mainThread;
    private final WorkerThread workerThread;

    @JvmStatic
    public static final CoreModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    @JvmStatic
    public static final CoreModule create(Emitter emitter, FrameworksFrameSourceListener frameworksFrameSourceListener, FrameworksDataCaptureContextListener frameworksDataCaptureContextListener) {
        return INSTANCE.create(emitter, frameworksFrameSourceListener, frameworksDataCaptureContextListener);
    }

    public /* synthetic */ CoreModule(Emitter emitter, FrameworksFrameSourceListener frameworksFrameSourceListener, FrameworksDataCaptureContextListener frameworksDataCaptureContextListener, FrameworksFrameSourceDeserializer frameworksFrameSourceDeserializer, MainThread mainThread, WorkerThread workerThread, FrameDataHandler frameDataHandler, FrameworksLog frameworksLog, DataCaptureViewHandler dataCaptureViewHandler, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, FrameworksCaptureContext frameworksCaptureContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, frameworksFrameSourceListener, frameworksDataCaptureContextListener, (i & 8) != 0 ? new FrameworksFrameSourceDeserializer(frameworksFrameSourceListener) : frameworksFrameSourceDeserializer, (i & 16) != 0 ? DefaultMainThread.INSTANCE.getInstance() : mainThread, (i & 32) != 0 ? DefaultWorkerThread.INSTANCE.getInstance() : workerThread, (i & 64) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i & 128) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : frameworksLog, (i & 256) != 0 ? DefaultDataCaptureViewHandler.INSTANCE.getInstance() : dataCaptureViewHandler, (i & 512) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver, (i & 1024) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : frameworksCaptureContext);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreModule(Emitter emitter, FrameworksFrameSourceListener frameSourceListener, FrameworksDataCaptureContextListener dataCaptureContextListener, FrameworksFrameSourceDeserializer frameSourceDeserializerListener, MainThread mainThread, WorkerThread workerThread, FrameDataHandler frameDataHandler, FrameworksLog logger, DataCaptureViewHandler dataCaptureViewHandler, DefaultDeserializationLifecycleObserver deserializationLifecycleObserver, FrameworksCaptureContext captureContext) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(frameSourceListener, "frameSourceListener");
        Intrinsics.checkNotNullParameter(dataCaptureContextListener, "dataCaptureContextListener");
        Intrinsics.checkNotNullParameter(frameSourceDeserializerListener, "frameSourceDeserializerListener");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        Intrinsics.checkNotNullParameter(workerThread, "workerThread");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dataCaptureViewHandler, "dataCaptureViewHandler");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        this.emitter = emitter;
        this.frameSourceListener = frameSourceListener;
        this.dataCaptureContextListener = dataCaptureContextListener;
        this.frameSourceDeserializerListener = frameSourceDeserializerListener;
        this.mainThread = mainThread;
        this.workerThread = workerThread;
        this.frameDataHandler = frameDataHandler;
        this.logger = logger;
        this.dataCaptureViewHandler = dataCaptureViewHandler;
        this.deserializationLifecycleObserver = deserializationLifecycleObserver;
        this.captureContext = captureContext;
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule
    public boolean getAutomaticAttachToDeserializationObserver() {
        return this.automaticAttachToDeserializationObserver;
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule
    public void setAutomaticAttachToDeserializationObserver(boolean z) {
        this.automaticAttachToDeserializationObserver = z;
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onCreate(context);
        this.displayMetricsDensity = context.getResources().getDisplayMetrics().density;
        DefaultFrameworksCaptureContext.INSTANCE.getInstance().configure(this.frameSourceDeserializerListener, this.dataCaptureContextListener);
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        Feedback feedback = this.latestFeedback;
        if (feedback != null) {
            feedback.release();
        }
        disposeContext();
        super.onDestroy();
    }

    public final Map<String, Object> getDefaults() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.checkNotNullExpressionValue("CoreModule", "getSimpleName(...)");
            throw new ModuleNotStartedError("CoreModule");
        }
        return CoreDefaults.INSTANCE.get(context);
    }

    public final void createContextFromJson(String json, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            Context context = getContext();
            if (context == null) {
                CoreModule coreModule = this;
                result.success(null);
            } else {
                DataCaptureContext initialize = this.captureContext.initialize(json, context);
                this.frameDataHandler.configure(context, FramesHandlingConfiguration.INSTANCE.create(json));
                initialize.addListener(this.dataCaptureContextListener);
                result.success(null);
            }
        } catch (AssertionError e) {
            this.logger.error("AssertionError parsing Json:");
            this.logger.error(json);
            ExtentionsKt.reject(result, e);
        } catch (RuntimeException e2) {
            this.logger.error("Error parsing Json:");
            this.logger.error(json);
            ExtentionsKt.reject(result, e2);
        } catch (JSONException e3) {
            this.logger.error("JSONException parsing Json:");
            this.logger.error(json);
            ExtentionsKt.reject(result, e3);
        }
    }

    public final synchronized void updateContextFromJson(String json, FrameworksResult result) {
        Context context;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            try {
                context = getContext();
            } catch (AssertionError e) {
                this.logger.error("AssertionError parsing Json " + e.getMessage() + ':');
                this.logger.error(json);
                ExtentionsKt.reject(result, e);
            } catch (RuntimeException e2) {
                this.logger.error("Error parsing Json " + e2.getMessage() + ':');
                this.logger.error(json);
                ExtentionsKt.reject(result, e2);
            }
        } catch (DataCaptureContextNotInitialized e3) {
            ExtentionsKt.reject(result, e3);
        } catch (JSONException e4) {
            this.logger.error("JSONException parsing Json " + e4.getMessage() + ':');
            this.logger.error(json);
            ExtentionsKt.reject(result, e4);
        }
        if (context == null) {
            CoreModule coreModule = this;
            result.success(null);
        } else {
            this.captureContext.update(json);
            this.frameDataHandler.configure(context, FramesHandlingConfiguration.INSTANCE.create(json));
            result.success(null);
        }
    }

    public final void emitFeedback(String feedbackAsJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackAsJson, "feedbackAsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            Feedback fromJson = Feedback.INSTANCE.fromJson(feedbackAsJson);
            Feedback feedback = this.latestFeedback;
            if (feedback != null) {
                feedback.release();
            }
            fromJson.emit();
            this.latestFeedback = fromJson;
            result.success(null);
        } catch (RuntimeException e) {
            ExtentionsKt.reject(result, e);
        } catch (JSONException e2) {
            ExtentionsKt.reject(result, e2);
        }
    }

    public final void viewPointForFramePoint(int viewId, String json, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3795getViewPointForFramePointgIAlus = m3795getViewPointForFramePointgIAlus(viewId, json);
        if (Result.m3951isFailureimpl(m3795getViewPointForFramePointgIAlus)) {
            Error m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3795getViewPointForFramePointgIAlus);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Error("Generic error while getting the view point.");
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        ResultKt.throwOnFailure(m3795getViewPointForFramePointgIAlus);
        result.success(m3795getViewPointForFramePointgIAlus);
    }

    /* renamed from: getViewPointForFramePoint-gIAlu-s, reason: not valid java name */
    public final Object m3795getViewPointForFramePointgIAlus(int viewId, String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new NullDataCaptureViewError()));
        }
        try {
            Point densityIndependent = getDensityIndependent(view.mapFramePointToView(json));
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(PointUtilsKt.toJson(densityIndependent));
        } catch (RuntimeException e) {
            this.logger.error(e);
            Result.Companion companion3 = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(e));
        }
    }

    private final Point getDensityIndependent(Point point) {
        return new Point(point.getX() / this.displayMetricsDensity, point.getY() / this.displayMetricsDensity);
    }

    public final void viewQuadrilateralForFrameQuadrilateral(int viewId, String json, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3796getViewQuadrilateralForFrameQuadrilateralgIAlus = m3796getViewQuadrilateralForFrameQuadrilateralgIAlus(viewId, json);
        if (Result.m3951isFailureimpl(m3796getViewQuadrilateralForFrameQuadrilateralgIAlus)) {
            Error m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3796getViewQuadrilateralForFrameQuadrilateralgIAlus);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Error("Generic error on getting the view quadrilateral.");
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        ResultKt.throwOnFailure(m3796getViewQuadrilateralForFrameQuadrilateralgIAlus);
        result.success(m3796getViewQuadrilateralForFrameQuadrilateralgIAlus);
    }

    /* renamed from: getViewQuadrilateralForFrameQuadrilateral-gIAlu-s, reason: not valid java name */
    public final Object m3796getViewQuadrilateralForFrameQuadrilateralgIAlus(int viewId, String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new NullDataCaptureViewError()));
        }
        try {
            Quadrilateral densityIndependent = getDensityIndependent(view.mapFrameQuadrilateralToView(json));
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(QuadrilateralUtilsKt.toJson(densityIndependent));
        } catch (RuntimeException e) {
            Result.Companion companion3 = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(e));
        }
    }

    private final Quadrilateral getDensityIndependent(Quadrilateral quadrilateral) {
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point densityIndependent = getDensityIndependent(topLeft);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point densityIndependent2 = getDensityIndependent(topRight);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point densityIndependent3 = getDensityIndependent(bottomRight);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(densityIndependent, densityIndependent2, densityIndependent3, getDensityIndependent(bottomLeft));
    }

    public final void getCameraState(String cameraPosition, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3793getCameraStateByPositionIoAF18A = m3793getCameraStateByPositionIoAF18A(cameraPosition);
        if (Result.m3951isFailureimpl(m3793getCameraStateByPositionIoAF18A)) {
            CameraNotReadyError m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3793getCameraStateByPositionIoAF18A);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new CameraNotReadyError();
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        ResultKt.throwOnFailure(m3793getCameraStateByPositionIoAF18A);
        result.success(m3793getCameraStateByPositionIoAF18A);
    }

    /* renamed from: getCameraStateByPosition-IoAF18A, reason: not valid java name */
    public final Object m3793getCameraStateByPositionIoAF18A(String cameraPosition) {
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        Camera camera = this.frameSourceDeserializerListener.getCamera();
        if ((camera != null ? camera.getPosition() : null) != CameraPositionDeserializer.fromJson(cameraPosition)) {
            camera = null;
        }
        if (camera == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new CameraNotReadyError()));
        }
        Result.Companion companion2 = Result.INSTANCE;
        return Result.m3945constructorimpl(FrameSourceStateUtilsKt.toJson(camera.getCurrentState()));
    }

    public final void isTorchAvailable(String cameraPosition, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3794getIsTorchAvailableByPositionIoAF18A = m3794getIsTorchAvailableByPositionIoAF18A(cameraPosition);
        if (Result.m3951isFailureimpl(m3794getIsTorchAvailableByPositionIoAF18A)) {
            CameraNotReadyError m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3794getIsTorchAvailableByPositionIoAF18A);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new CameraNotReadyError();
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        ResultKt.throwOnFailure(m3794getIsTorchAvailableByPositionIoAF18A);
        result.success(m3794getIsTorchAvailableByPositionIoAF18A);
    }

    /* renamed from: getIsTorchAvailableByPosition-IoAF18A, reason: not valid java name */
    public final Object m3794getIsTorchAvailableByPositionIoAF18A(String cameraPosition) {
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        Camera camera = this.frameSourceDeserializerListener.getCamera();
        if (camera == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new CameraNotReadyError()));
        }
        if (camera.getPosition() != CameraPositionDeserializer.fromJson(cameraPosition)) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new WrongCameraPositionError()));
        }
        Result.Companion companion3 = Result.INSTANCE;
        return Result.m3945constructorimpl(Boolean.valueOf(camera.isTorchAvailable()));
    }

    public final void registerDataCaptureContextListener() {
        this.dataCaptureContextListener.enable();
    }

    public final void unregisterDataCaptureContextListener() {
        this.dataCaptureContextListener.disable();
    }

    public final void registerFrameSourceListener() {
        this.frameSourceListener.enable();
    }

    public final void unregisterFrameSourceListener() {
        this.frameSourceListener.disable();
    }

    public final void registerDataCaptureViewListener(final int viewId) {
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$registerDataCaptureViewListener$viewInstance$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CoreModule.this.registerDataCaptureViewListener(viewId);
                }
            });
        } else {
            view.registerDataCaptureViewListener();
        }
    }

    public final void registerTopmostDataCaptureViewListener() {
        FrameworksDataCaptureView topmostDataCaptureView = this.dataCaptureViewHandler.getTopmostDataCaptureView();
        if (topmostDataCaptureView == null) {
            return;
        }
        topmostDataCaptureView.registerDataCaptureViewListener();
    }

    public final void unregisterDataCaptureViewListener(final int viewId) {
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$unregisterDataCaptureViewListener$viewInstance$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CoreModule.this.unregisterDataCaptureViewListener(viewId);
                }
            });
        } else {
            view.unregisterDataCaptureViewListener();
        }
    }

    public final void unregisterTopmostDataCaptureViewListener() {
        FrameworksDataCaptureView topmostDataCaptureView = this.dataCaptureViewHandler.getTopmostDataCaptureView();
        if (topmostDataCaptureView == null) {
            return;
        }
        topmostDataCaptureView.unregisterDataCaptureViewListener();
    }

    public final FrameSourceState getCurrentCameraDesiredState() {
        Camera camera = this.frameSourceDeserializerListener.getCamera();
        if (camera != null) {
            return camera.getDesiredState();
        }
        return null;
    }

    public final void getCurrentCameraState(FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Camera camera = this.frameSourceDeserializerListener.getCamera();
        if (camera == null) {
            ExtentionsKt.reject(result, new CameraNotReadyError());
        } else {
            result.success(camera.getCurrentState());
        }
    }

    public final void switchToDesiredCameraState(FrameSourceState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        FrameworksFrameSourceDeserializer.switchCameraToState$default(this.frameSourceDeserializerListener, state, null, 2, null);
    }

    public final void switchCameraToDesiredState(final String stateJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(stateJson, "stateJson");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameSourceDeserializerListener.switchCameraToState(FrameSourceStateDeserializer.fromJson(stateJson), LambdaExtensionsKt.Callback(new Function1<Boolean, Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$switchCameraToDesiredState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    FrameworksResult.this.success(null);
                    return;
                }
                ExtentionsKt.reject(FrameworksResult.this, new Error("Unable to switch the camera to " + stateJson + ClassUtils.PACKAGE_SEPARATOR_CHAR));
            }
        }));
    }

    public final void disposeContext() {
        this.deserializationLifecycleObserver.dispatchAllModesRemoved();
        this.frameSourceDeserializerListener.releaseCurrentCamera();
        removeAllViews();
        this.mainThread.cleanup();
        this.workerThread.cleanup();
        this.frameDataHandler.release();
        this.captureContext.release();
    }

    public final void disposeDataCaptureView() {
        removeTopMostDataCaptureView();
    }

    public final void addModeToContext(String modeJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            this.deserializationLifecycleObserver.dispatchAddModeToContext(modeJson);
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void removeModeFromContext(String modeJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            this.deserializationLifecycleObserver.dispatchRemoveModeFromContext(modeJson);
            this.frameDataHandler.release();
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void removeAllModes(FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.captureContext.removeAllModes();
        this.deserializationLifecycleObserver.dispatchAllModesRemoved();
        this.frameDataHandler.release();
        result.success(null);
    }

    public final DataCaptureView createDataCaptureView(String viewJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            Context context = getContext();
            if (context == null) {
                Intrinsics.checkNotNullExpressionValue("CoreModule", "getSimpleName(...)");
                throw new ModuleNotStartedError("CoreModule");
            }
            DataCaptureContext context2 = this.captureContext.getContext();
            if (context2 == null) {
                throw new DataCaptureContextNotInitialized();
            }
            DataCaptureViewCreationData fromJson = DataCaptureViewCreationData.INSTANCE.fromJson(viewJson);
            FrameworksDataCaptureView create = FrameworksDataCaptureView.INSTANCE.create(this.emitter, context, context2, fromJson);
            this.dataCaptureViewHandler.addView(create);
            this.deserializationLifecycleObserver.dispatchDataCaptureViewDeserialized(create.getView());
            Iterator<String> it = fromJson.getOverlaysJson().iterator();
            while (it.hasNext()) {
                this.deserializationLifecycleObserver.dispatchAddOverlayToView(create, it.next());
            }
            Iterator<Function0<Unit>> it2 = getPostSpecificViewCreationActions(create.get_viewId()).iterator();
            while (it2.hasNext()) {
                it2.next().invoke();
            }
            result.success(null);
            return create.getView();
        } catch (Exception e) {
            this.logger.error(e);
            return null;
        }
    }

    public final void updateDataCaptureView(final String viewJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            DataCaptureViewCreationData fromJson = DataCaptureViewCreationData.INSTANCE.fromJson(viewJson);
            FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(fromJson.getViewId());
            if (view == null) {
                CoreModule coreModule = this;
                addPostSpecificViewCreationAction(fromJson.getViewId(), new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$updateDataCaptureView$viewInstance$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        CoreModule.this.updateDataCaptureView(viewJson, new NoopFrameworksResult());
                    }
                });
                return;
            }
            view.updateView(fromJson);
            view.removeAllOverlays();
            Iterator<String> it = fromJson.getOverlaysJson().iterator();
            while (it.hasNext()) {
                this.deserializationLifecycleObserver.dispatchAddOverlayToView(view, it.next());
            }
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void getLastFrameAsJson(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsJsonString(frameId, new Function1<String, Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$getLastFrameAsJson$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (str == null) {
                    ExtentionsKt.reject(FrameworksResult.this, new FrameDataNullError());
                } else {
                    FrameworksResult.this.success(str);
                }
            }
        });
    }

    public final void getLastFrameOrNullAsJson(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsJsonString(frameId, new Function1<String, Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$getLastFrameOrNullAsJson$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                FrameworksResult.this.success(str);
            }
        });
    }

    public final void getLastFrameOrNullAsMap(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$getLastFrameOrNullAsMap$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Object> map) {
                invoke2(map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, ? extends Object> map) {
                FrameworksResult.this.success(map);
            }
        });
    }

    public final void dataCaptureViewDisposed(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        DataCaptureViewHandler dataCaptureViewHandler = this.dataCaptureViewHandler;
        Object tag = dataCaptureView.getTag();
        Integer num = tag instanceof Integer ? (Integer) tag : null;
        dataCaptureViewHandler.removeView(num != null ? num.intValue() : 0);
    }

    public final void dataCaptureViewDisposed(int viewId) {
        DataCaptureView view;
        this.dataCaptureViewHandler.removeView(viewId);
        FrameworksDataCaptureView topmostDataCaptureView = this.dataCaptureViewHandler.getTopmostDataCaptureView();
        if (topmostDataCaptureView == null || (view = topmostDataCaptureView.getView()) == null) {
            return;
        }
        view.dispatchWindowVisibilityChanged(0);
    }

    private final void removeTopMostDataCaptureView() {
        this.dataCaptureViewHandler.removeTopmostView();
        this.deserializationLifecycleObserver.dispatchDataCaptureViewDeserialized(null);
    }

    private final void removeAllViews() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.CoreModule$removeAllViews$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DataCaptureViewHandler dataCaptureViewHandler;
                dataCaptureViewHandler = CoreModule.this.dataCaptureViewHandler;
                dataCaptureViewHandler.removeAllViews();
            }
        });
        this.deserializationLifecycleObserver.dispatchDataCaptureViewDeserialized(null);
    }

    public final void getOpenSourceSoftwareLicenseInfo(FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        result.success(DataCaptureContext.INSTANCE.getOpenSourceSoftwareLicenseInfo().getLicenseText());
    }

    public final DataCaptureView getDataCaptureViewById(int viewId) {
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view != null) {
            return view.getView();
        }
        return null;
    }

    /* compiled from: CoreModule.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/CoreModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/core/CoreModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "frameSourceListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;", "dataCaptureContextListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureContextListener;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CoreModule create(Emitter emitter, FrameworksFrameSourceListener frameSourceListener, FrameworksDataCaptureContextListener dataCaptureContextListener) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(frameSourceListener, "frameSourceListener");
            Intrinsics.checkNotNullParameter(dataCaptureContextListener, "dataCaptureContextListener");
            return new CoreModule(emitter, frameSourceListener, dataCaptureContextListener, null, null, null, null, null, null, null, null, 2040, null);
        }

        @JvmStatic
        public final CoreModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new CoreModule(emitter, new FrameworksFrameSourceListener(emitter), new FrameworksDataCaptureContextListener(emitter), null, null, null, null, null, null, null, null, 2040, null);
        }
    }
}
