package com.scandit.datacapture.frameworks.barcode.batch;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.PointerIconCompat;
import com.getcapacitor.PluginCall;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializer;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSession;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.AnchorDeserializer;
import com.scandit.datacapture.core.common.geometry.PointWithUnitDeserializer;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.core.ui.style.BrushDeserializer;
import com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData;
import com.scandit.datacapture.frameworks.barcode.batch.data.BarcodeBatchModeCreationData;
import com.scandit.datacapture.frameworks.barcode.batch.data.BarcodeBatchOverlayCreationData;
import com.scandit.datacapture.frameworks.barcode.batch.data.FrameworksBarcodeBatchSession;
import com.scandit.datacapture.frameworks.barcode.batch.data.defaults.BarcodeBatchDefaults;
import com.scandit.datacapture.frameworks.barcode.batch.listeners.FrameworksBarcodeBatchAdvancedOverlayListener;
import com.scandit.datacapture.frameworks.barcode.batch.listeners.FrameworksBarcodeBatchBasicOverlayListener;
import com.scandit.datacapture.frameworks.barcode.batch.mode.FrameworksBarcodeBatchMode;
import com.scandit.datacapture.frameworks.core.common.BasicFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.FrameDataNullError;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.handlers.DataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.handlers.DefaultDataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.method.FrameworksMethodCall;
import com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.scandit.datacapture.frameworks.core.utils.AdvancedOverlayViewCache;
import com.scandit.datacapture.frameworks.core.utils.DefaultAdvancedOverlayViewCache;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.SentryEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeBatchModule.kt */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001mBc\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 J\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u001e\u0010$\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u00020\u001eH\u0002J\u000e\u0010*\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010+\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010'\u001a\u00020(J\u0016\u00100\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 2\u0006\u00101\u001a\u00020-J\u0014\u00102\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0006\u0012\u0004\u0018\u00010403J\u0016\u00105\u001a\u00020\u001e2\u0006\u00106\u001a\u00020&2\u0006\u0010'\u001a\u00020(J!\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020 2\b\u0010:\u001a\u0004\u0018\u00010;H\u0002¢\u0006\u0002\u0010<J\u001a\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020 2\b\u0010@\u001a\u0004\u0018\u00010AJ\u0006\u0010B\u001a\u00020-J\b\u0010C\u001a\u00020\u001eH\u0016J\u0010\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020&H\u0016J\u0018\u0010F\u001a\u00020\u001e2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020&H\u0016J\b\u0010J\u001a\u00020\u001eH\u0016J\u0010\u0010K\u001a\u00020\u001e2\u0006\u0010L\u001a\u00020MH\u0016J\b\u0010N\u001a\u00020\u001eH\u0016J\u0010\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020QH\u0016J\u0010\u0010R\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020&H\u0016J\u000e\u0010S\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010T\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 J\u000e\u0010U\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0017\u0010V\u001a\u00020\u001e2\b\u0010:\u001a\u0004\u0018\u00010;H\u0002¢\u0006\u0002\u0010WJ\u0015\u0010X\u001a\u00020\u001e2\b\u0010:\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010WJ-\u0010Y\u001a\u00020\u001e2\u0006\u0010Z\u001a\u00020&2\u0006\u0010?\u001a\u00020 2\b\u0010[\u001a\u0004\u0018\u00010;2\u0006\u0010\u001f\u001a\u00020 ¢\u0006\u0002\u0010\\J-\u0010]\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010^\u001a\u00020&2\u0006\u0010?\u001a\u00020 2\b\u0010[\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010_J\u0016\u0010`\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020 2\u0006\u00101\u001a\u00020-J-\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020&2\u0006\u0010?\u001a\u00020 2\b\u0010[\u001a\u0004\u0018\u00010;2\u0006\u0010\u001f\u001a\u00020 ¢\u0006\u0002\u0010\\J\u000e\u0010c\u001a\u00020\u001e2\u0006\u00101\u001a\u00020-J/\u0010d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010G\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020 2\b\u0010[\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010eJ.\u0010f\u001a\u00020\u001e2&\u0010g\u001a\"\u0012\u0004\u0012\u00020&\u0012\u0006\u0012\u0004\u0018\u0001040hj\u0010\u0012\u0004\u0012\u00020&\u0012\u0006\u0012\u0004\u0018\u000104`iJ\u001e\u0010j\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010I\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u001e\u0010k\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010I\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u0016\u0010l\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020&2\u0006\u0010'\u001a\u00020(R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/BarcodeBatchModule;", "Lcom/scandit/datacapture/frameworks/core/common/BasicFrameworkModule;", "Lcom/scandit/datacapture/frameworks/barcode/batch/mode/FrameworksBarcodeBatchMode;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "barcodeBatchBasicOverlayListener", "Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchBasicOverlayListener;", "barcodeBatchAdvancedOverlayListener", "Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchAdvancedOverlayListener;", "barcodeBatchDeserializer", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "dataCaptureViewHandler", "Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchBasicOverlayListener;Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchAdvancedOverlayListener;Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "advancedOverlayViewPool", "Lcom/scandit/datacapture/frameworks/core/utils/AdvancedOverlayViewCache;", "cachedBatchSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", "addAdvancedOverlayListener", "", ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY, "", "addBarcodeBatchListener", ExtentionsKt.MODE_ID_KEY, "addBasicOverlayListener", "applyModeSettings", "modeSettingsJson", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "cancelAllListenerPendingEvents", "clearAdvancedOverlayTrackedBarcodeViews", "clearBasicOverlayTrackedBarcodeBrushes", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "finishDidUpdateSession", ExtentionsKt.ENABLED_KEY, "getDefaults", "", "", "getFrameDataBytes", "frameId", "getTrackedBarcodeFromLatestSession", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "barcodeId", "frameSequenceId", "", "(ILjava/lang/Long;)Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "getTrackedBarcodeViewFromBitmap", "Landroid/view/View;", "trackedBarcodeId", "bitmap", "Landroid/graphics/Bitmap;", "isModeEnabled", "onActivityStop", "onAddModeToContext", "modeJson", "onAddOverlayToView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "overlayJson", "onAllModesRemovedFromContext", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "onOverlayRemovedFromView", "overlay", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "onRemoveModeFromContext", "removeAdvancedOverlayListener", "removeBarcodeBatchListener", "removeBasicOverlayListener", "resetLatestSession", "(Ljava/lang/Long;)V", "resetSession", "setAnchorForTrackedBarcode", "anchorJson", "sessionFrameSequenceId", "(Ljava/lang/String;ILjava/lang/Long;I)V", "setBasicOverlayBrushForTrackedBarcode", "brushJson", "(ILjava/lang/String;ILjava/lang/Long;)V", "setModeEnabled", "setOffsetForTrackedBarcode", "offsetJson", "setTopMostModeEnabled", "setViewForTrackedBarcode", "(ILandroid/view/View;ILjava/lang/Long;)V", "setWidgetForTrackedBarcode", "viewParams", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "updateAdvancedOverlay", "updateBasicOverlay", "updateModeFromJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeBatchModule extends BasicFrameworkModule<FrameworksBarcodeBatchMode> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AdvancedOverlayViewCache advancedOverlayViewPool;
    private final FrameworksBarcodeBatchAdvancedOverlayListener barcodeBatchAdvancedOverlayListener;
    private final FrameworksBarcodeBatchBasicOverlayListener barcodeBatchBasicOverlayListener;
    private final BarcodeBatchDeserializer barcodeBatchDeserializer;
    private final AtomicReference<FrameworksBarcodeBatchSession> cachedBatchSession;
    private final FrameworksCaptureContext captureContext;
    private final DataCaptureViewHandler dataCaptureViewHandler;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksLog logger;
    private final MainThread mainThread;

    @JvmStatic
    public static final BarcodeBatchModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public /* synthetic */ BarcodeBatchModule(Emitter emitter, FrameworksBarcodeBatchBasicOverlayListener frameworksBarcodeBatchBasicOverlayListener, FrameworksBarcodeBatchAdvancedOverlayListener frameworksBarcodeBatchAdvancedOverlayListener, BarcodeBatchDeserializer barcodeBatchDeserializer, MainThread mainThread, FrameworksLog frameworksLog, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext frameworksCaptureContext, DeserializationLifecycleObserver deserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, frameworksBarcodeBatchBasicOverlayListener, frameworksBarcodeBatchAdvancedOverlayListener, (i & 8) != 0 ? new BarcodeBatchDeserializer() : barcodeBatchDeserializer, (i & 16) != 0 ? DefaultMainThread.INSTANCE.getInstance() : mainThread, (i & 32) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : frameworksLog, (i & 64) != 0 ? DefaultDataCaptureViewHandler.INSTANCE.getInstance() : dataCaptureViewHandler, (i & 128) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i & 256) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : frameworksCaptureContext, (i & 512) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : deserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeBatchModule(Emitter emitter, FrameworksBarcodeBatchBasicOverlayListener barcodeBatchBasicOverlayListener, FrameworksBarcodeBatchAdvancedOverlayListener barcodeBatchAdvancedOverlayListener, BarcodeBatchDeserializer barcodeBatchDeserializer, MainThread mainThread, FrameworksLog logger, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(barcodeBatchBasicOverlayListener, "barcodeBatchBasicOverlayListener");
        Intrinsics.checkNotNullParameter(barcodeBatchAdvancedOverlayListener, "barcodeBatchAdvancedOverlayListener");
        Intrinsics.checkNotNullParameter(barcodeBatchDeserializer, "barcodeBatchDeserializer");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dataCaptureViewHandler, "dataCaptureViewHandler");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.barcodeBatchBasicOverlayListener = barcodeBatchBasicOverlayListener;
        this.barcodeBatchAdvancedOverlayListener = barcodeBatchAdvancedOverlayListener;
        this.barcodeBatchDeserializer = barcodeBatchDeserializer;
        this.mainThread = mainThread;
        this.logger = logger;
        this.dataCaptureViewHandler = dataCaptureViewHandler;
        this.frameDataHandler = frameDataHandler;
        this.captureContext = captureContext;
        this.cachedBatchSession = new AtomicReference<>();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onCreate(context);
        this.advancedOverlayViewPool = new DefaultAdvancedOverlayViewCache(context);
        attachForActivityLifecycleEvents();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        AdvancedOverlayViewCache advancedOverlayViewCache = this.advancedOverlayViewPool;
        if (advancedOverlayViewCache != null) {
            advancedOverlayViewCache.clear();
        }
        this.advancedOverlayViewPool = null;
        detachForActivityLifecycleEvents();
        super.onDestroy();
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeBatchDefaults.INSTANCE.get();
    }

    public final void addBarcodeBatchListener(final int modeId) {
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$addBarcodeBatchListener$mode$1
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
                    BarcodeBatchModule.this.addBarcodeBatchListener(modeId);
                }
            });
        } else {
            modeFromCache.addListener();
        }
    }

    public final void removeBarcodeBatchListener(int modeId) {
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            return;
        }
        modeFromCache.removeListener();
    }

    public final void finishDidUpdateSession(int modeId, boolean enabled) {
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            return;
        }
        modeFromCache.finishDidUpdateSession(enabled);
    }

    public final void resetSession(Long frameSequenceId) {
        resetLatestSession(frameSequenceId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    public final void addBasicOverlayListener(int dataCaptureViewId) {
        ?? r1;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view != null) {
            Iterator it = view.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    r1 = 0;
                    break;
                } else {
                    r1 = it.next();
                    if (((DataCaptureOverlay) r1) instanceof BarcodeBatchBasicOverlay) {
                        break;
                    }
                }
            }
            r0 = r1 instanceof BarcodeBatchBasicOverlay ? r1 : null;
        }
        if (r0 == null) {
            return;
        }
        r0.setListener(this.barcodeBatchBasicOverlayListener);
    }

    public final void removeBasicOverlayListener(int dataCaptureViewId) {
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay;
        Object obj;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view != null) {
            Iterator<T> it = view.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (((DataCaptureOverlay) obj) instanceof BarcodeBatchBasicOverlay) {
                        break;
                    }
                }
            }
            if (!(obj instanceof BarcodeBatchBasicOverlay)) {
                obj = null;
            }
            barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) obj;
        } else {
            barcodeBatchBasicOverlay = null;
        }
        if (barcodeBatchBasicOverlay == null) {
            return;
        }
        barcodeBatchBasicOverlay.setListener(null);
    }

    public final void clearBasicOverlayTrackedBarcodeBrushes(int dataCaptureViewId) {
        Object obj;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchBasicOverlay) {
                    break;
                }
            }
        }
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) (obj instanceof BarcodeBatchBasicOverlay ? obj : null);
        if (barcodeBatchBasicOverlay != null) {
            barcodeBatchBasicOverlay.clearTrackedBarcodeBrushes();
        }
    }

    public final void setBasicOverlayBrushForTrackedBarcode(int dataCaptureViewId, String brushJson, int trackedBarcodeId, Long sessionFrameSequenceId) {
        TrackedBarcode trackedBarcodeFromLatestSession;
        Object obj;
        Intrinsics.checkNotNullParameter(brushJson, "brushJson");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null || (trackedBarcodeFromLatestSession = getTrackedBarcodeFromLatestSession(trackedBarcodeId, sessionFrameSequenceId)) == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchBasicOverlay) {
                    break;
                }
            }
        }
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) (obj instanceof BarcodeBatchBasicOverlay ? obj : null);
        if (barcodeBatchBasicOverlay != null) {
            barcodeBatchBasicOverlay.setBrushForTrackedBarcode(trackedBarcodeFromLatestSession, BrushDeserializer.fromJson(brushJson));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    public final void addAdvancedOverlayListener(int dataCaptureViewId) {
        ?? r1;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view != null) {
            Iterator it = view.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    r1 = 0;
                    break;
                } else {
                    r1 = it.next();
                    if (((DataCaptureOverlay) r1) instanceof BarcodeBatchAdvancedOverlay) {
                        break;
                    }
                }
            }
            r0 = r1 instanceof BarcodeBatchAdvancedOverlay ? r1 : null;
        }
        if (r0 == null) {
            return;
        }
        r0.setListener(this.barcodeBatchAdvancedOverlayListener);
    }

    public final void removeAdvancedOverlayListener(int dataCaptureViewId) {
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay;
        Object obj;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view != null) {
            Iterator<T> it = view.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                        break;
                    }
                }
            }
            if (!(obj instanceof BarcodeBatchAdvancedOverlay)) {
                obj = null;
            }
            barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) obj;
        } else {
            barcodeBatchAdvancedOverlay = null;
        }
        if (barcodeBatchAdvancedOverlay != null) {
            barcodeBatchAdvancedOverlay.setListener(null);
        }
        AdvancedOverlayViewCache advancedOverlayViewCache = this.advancedOverlayViewPool;
        if (advancedOverlayViewCache != null) {
            advancedOverlayViewCache.clear();
        }
    }

    public final void clearAdvancedOverlayTrackedBarcodeViews(int dataCaptureViewId) {
        Object obj;
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                    break;
                }
            }
        }
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) (obj instanceof BarcodeBatchAdvancedOverlay ? obj : null);
        if (barcodeBatchAdvancedOverlay != null) {
            barcodeBatchAdvancedOverlay.clearTrackedBarcodeViews();
        }
    }

    public final void setWidgetForTrackedBarcode(HashMap<String, Object> viewParams) {
        final TrackedBarcode trackedBarcodeFromLatestSession;
        final ImageView orCreateViewFromBase64EncodedBytes;
        Object obj;
        Intrinsics.checkNotNullParameter(viewParams, "viewParams");
        AdvancedOverlayViewData create = AdvancedOverlayViewData.INSTANCE.create(viewParams);
        final FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(create.getDataCaptureViewId());
        if (view == null || (trackedBarcodeFromLatestSession = getTrackedBarcodeFromLatestSession(create.getTrackedBarcodeId(), -1L)) == null) {
            return;
        }
        if (create.getWidgetBytes() == null) {
            AdvancedOverlayViewCache advancedOverlayViewCache = this.advancedOverlayViewPool;
            if (advancedOverlayViewCache != null) {
                advancedOverlayViewCache.removeView(String.valueOf(trackedBarcodeFromLatestSession.getIdentifier()));
            }
            Iterator<T> it = view.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                        break;
                    }
                }
            }
            if (!(obj instanceof BarcodeBatchAdvancedOverlay)) {
                obj = null;
            }
            BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) obj;
            if (barcodeBatchAdvancedOverlay != null) {
                barcodeBatchAdvancedOverlay.setViewForTrackedBarcode(trackedBarcodeFromLatestSession, null);
                return;
            }
            return;
        }
        AdvancedOverlayViewCache advancedOverlayViewCache2 = this.advancedOverlayViewPool;
        if (advancedOverlayViewCache2 == null || (orCreateViewFromBase64EncodedBytes = advancedOverlayViewCache2.getOrCreateViewFromBase64EncodedBytes(String.valueOf(trackedBarcodeFromLatestSession.getIdentifier()), create.getWidgetBytes())) == null) {
            return;
        }
        orCreateViewFromBase64EncodedBytes.setContentDescription("advancedOverlayLocator-" + trackedBarcodeFromLatestSession.getBarcode().getData());
        orCreateViewFromBase64EncodedBytes.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BarcodeBatchModule.setWidgetForTrackedBarcode$lambda$2$lambda$1(BarcodeBatchModule.this, trackedBarcodeFromLatestSession, view2);
            }
        });
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$setWidgetForTrackedBarcode$1$2
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
                Object obj2;
                Iterator<T> it2 = FrameworksDataCaptureView.this.getOverlays().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    } else {
                        obj2 = it2.next();
                        if (((DataCaptureOverlay) obj2) instanceof BarcodeBatchAdvancedOverlay) {
                            break;
                        }
                    }
                }
                BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay2 = (BarcodeBatchAdvancedOverlay) (obj2 instanceof BarcodeBatchAdvancedOverlay ? obj2 : null);
                if (barcodeBatchAdvancedOverlay2 != null) {
                    barcodeBatchAdvancedOverlay2.setViewForTrackedBarcode(trackedBarcodeFromLatestSession, orCreateViewFromBase64EncodedBytes);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setWidgetForTrackedBarcode$lambda$2$lambda$1(BarcodeBatchModule this$0, TrackedBarcode trackedBarcode, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(trackedBarcode, "$trackedBarcode");
        this$0.barcodeBatchAdvancedOverlayListener.viewForBarcodeTapped(trackedBarcode);
    }

    public final View getTrackedBarcodeViewFromBitmap(int trackedBarcodeId, Bitmap bitmap) {
        AdvancedOverlayViewCache advancedOverlayViewCache = this.advancedOverlayViewPool;
        return advancedOverlayViewCache != null ? advancedOverlayViewCache.getOrCreateViewFromBitmap(String.valueOf(trackedBarcodeId), bitmap) : null;
    }

    public final void setViewForTrackedBarcode(int dataCaptureViewId, final View view, int trackedBarcodeId, Long sessionFrameSequenceId) {
        final TrackedBarcode trackedBarcodeFromLatestSession;
        final FrameworksDataCaptureView view2 = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view2 == null || (trackedBarcodeFromLatestSession = getTrackedBarcodeFromLatestSession(trackedBarcodeId, sessionFrameSequenceId)) == null) {
            return;
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    BarcodeBatchModule.setViewForTrackedBarcode$lambda$4$lambda$3(BarcodeBatchModule.this, trackedBarcodeFromLatestSession, view3);
                }
            });
        }
        if (view != null) {
            view.setContentDescription("advancedOverlayLocator-" + trackedBarcodeFromLatestSession.getBarcode().getData());
        }
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$setViewForTrackedBarcode$1$2
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
                Object obj;
                Iterator<T> it = FrameworksDataCaptureView.this.getOverlays().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                            break;
                        }
                    }
                }
                BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) (obj instanceof BarcodeBatchAdvancedOverlay ? obj : null);
                if (barcodeBatchAdvancedOverlay != null) {
                    barcodeBatchAdvancedOverlay.setViewForTrackedBarcode(trackedBarcodeFromLatestSession, view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewForTrackedBarcode$lambda$4$lambda$3(BarcodeBatchModule this$0, TrackedBarcode trackedBarcode, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(trackedBarcode, "$trackedBarcode");
        this$0.barcodeBatchAdvancedOverlayListener.viewForBarcodeTapped(trackedBarcode);
    }

    public final void setAnchorForTrackedBarcode(String anchorJson, int trackedBarcodeId, Long sessionFrameSequenceId, int dataCaptureViewId) {
        TrackedBarcode trackedBarcodeFromLatestSession;
        Object obj;
        Intrinsics.checkNotNullParameter(anchorJson, "anchorJson");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null || (trackedBarcodeFromLatestSession = getTrackedBarcodeFromLatestSession(trackedBarcodeId, sessionFrameSequenceId)) == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                    break;
                }
            }
        }
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) (obj instanceof BarcodeBatchAdvancedOverlay ? obj : null);
        if (barcodeBatchAdvancedOverlay != null) {
            barcodeBatchAdvancedOverlay.setAnchorForTrackedBarcode(trackedBarcodeFromLatestSession, AnchorDeserializer.fromJson(anchorJson));
        }
    }

    public final void setOffsetForTrackedBarcode(String offsetJson, int trackedBarcodeId, Long sessionFrameSequenceId, int dataCaptureViewId) {
        TrackedBarcode trackedBarcodeFromLatestSession;
        Object obj;
        Intrinsics.checkNotNullParameter(offsetJson, "offsetJson");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null || (trackedBarcodeFromLatestSession = getTrackedBarcodeFromLatestSession(trackedBarcodeId, sessionFrameSequenceId)) == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                    break;
                }
            }
        }
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) (obj instanceof BarcodeBatchAdvancedOverlay ? obj : null);
        if (barcodeBatchAdvancedOverlay != null) {
            barcodeBatchAdvancedOverlay.setOffsetForTrackedBarcode(trackedBarcodeFromLatestSession, PointWithUnitDeserializer.fromJson(offsetJson));
        }
    }

    public final boolean isModeEnabled() {
        FrameworksBarcodeBatchMode topmostMode = getTopmostMode();
        return topmostMode != null && topmostMode.isEnabled();
    }

    public final void setModeEnabled(int modeId, boolean enabled) {
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            return;
        }
        modeFromCache.setEnabled(enabled);
    }

    public final void setTopMostModeEnabled(boolean enabled) {
        FrameworksBarcodeBatchMode topmostMode = getTopmostMode();
        if (topmostMode == null) {
            return;
        }
        topmostMode.setEnabled(enabled);
    }

    public final void updateModeFromJson(String modeJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(ExtentionsKt.getModeId(modeJson));
        if (modeFromCache == null) {
            result.success(null);
        } else {
            modeFromCache.updateModeFromJson(modeJson);
            result.success(null);
        }
    }

    public final void applyModeSettings(int modeId, String modeSettingsJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            result.success(null);
        } else {
            modeFromCache.applySettings(modeSettingsJson);
            result.success(null);
        }
    }

    public final void updateBasicOverlay(int dataCaptureViewId, String overlayJson, FrameworksResult result) {
        Object obj;
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchBasicOverlay) {
                    break;
                }
            }
        }
        if (!(obj instanceof BarcodeBatchBasicOverlay)) {
            obj = null;
        }
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) obj;
        if (barcodeBatchBasicOverlay == null) {
            result.success(null);
        } else {
            this.barcodeBatchDeserializer.updateBasicOverlayFromJson(barcodeBatchBasicOverlay, overlayJson);
            result.success(true);
        }
    }

    public final void updateAdvancedOverlay(int dataCaptureViewId, String overlayJson, FrameworksResult result) {
        Object obj;
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(dataCaptureViewId);
        if (view == null) {
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeBatchAdvancedOverlay) {
                    break;
                }
            }
        }
        if (!(obj instanceof BarcodeBatchAdvancedOverlay)) {
            obj = null;
        }
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = (BarcodeBatchAdvancedOverlay) obj;
        if (barcodeBatchAdvancedOverlay == null) {
            result.success(null);
        } else {
            this.barcodeBatchDeserializer.updateAdvancedOverlayFromJson(barcodeBatchAdvancedOverlay, overlayJson);
            result.success(true);
        }
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$getFrameDataBytes$1
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
                if (map == null) {
                    ExtentionsKt.reject(FrameworksResult.this, new FrameDataNullError());
                } else {
                    FrameworksResult.this.success(map);
                }
            }
        });
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityStop() {
        cancelAllListenerPendingEvents();
        super.onActivityStop();
    }

    private final void cancelAllListenerPendingEvents() {
        Iterator<T> it = getAllModesInCache().iterator();
        while (it.hasNext()) {
            ((FrameworksBarcodeBatchMode) it.next()).cancelPendingEvents();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddModeToContext(String modeJson) {
        DataCaptureContext contextForModeCreation;
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        BarcodeBatchModeCreationData fromJson = BarcodeBatchModeCreationData.INSTANCE.fromJson(modeJson);
        if (Intrinsics.areEqual(fromJson.getModeType(), BarcodeBatchModeCreationData.MODE_TYPE) && (contextForModeCreation = ExtentionsKt.getContextForModeCreation(this.captureContext, BarcodeBatchModeCreationData.MODE_TYPE)) != null) {
            FrameworksBarcodeBatchMode create = FrameworksBarcodeBatchMode.INSTANCE.create(this.emitter, this.captureContext, fromJson, contextForModeCreation, this.cachedBatchSession);
            addModeToCache(fromJson.getModeId(), create);
            Iterator<Function0<Unit>> it = getPostModeCreationActions(fromJson.getModeId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            Integer num = create.get_parentId();
            Iterator<Function0<Unit>> it2 = getPostModeCreationActionsByParent(num != null ? num.intValue() : -1).iterator();
            while (it2.hasNext()) {
                it2.next().invoke();
            }
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onRemoveModeFromContext(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        if (Intrinsics.areEqual(ExtentionsKt.getJsonValueTypeAttribute(modeJson), BarcodeBatchModeCreationData.MODE_TYPE)) {
            int modeId = ExtentionsKt.getModeId(modeJson);
            FrameworksBarcodeBatchMode modeFromCache = getModeFromCache(modeId);
            if (modeFromCache == null) {
                this.logger.error("Unable to add the BarcodeBatchMode from the DataCaptureContext, the mode is null.");
                return;
            }
            modeFromCache.dispose();
            removeModeFromCache(modeId);
            clearPostModeCreationActions(Integer.valueOf(modeId));
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAllModesRemovedFromContext() {
        Iterator<FrameworksBarcodeBatchMode> it = getAllModesInCache().iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        removeAllModesFromCache();
        clearPostModeCreationActions(null);
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onOverlayRemovedFromView(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        BarcodeBatchAdvancedOverlay barcodeBatchAdvancedOverlay = overlay instanceof BarcodeBatchAdvancedOverlay ? (BarcodeBatchAdvancedOverlay) overlay : null;
        if (barcodeBatchAdvancedOverlay != null) {
            barcodeBatchAdvancedOverlay.setListener(null);
        }
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = overlay instanceof BarcodeBatchBasicOverlay ? (BarcodeBatchBasicOverlay) overlay : null;
        if (barcodeBatchBasicOverlay != null) {
            barcodeBatchBasicOverlay.setListener(null);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddOverlayToView(final FrameworksDataCaptureView view, final String overlayJson) {
        FrameworksBarcodeBatchMode modeFromCache;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        BarcodeBatchOverlayCreationData fromJson = BarcodeBatchOverlayCreationData.INSTANCE.fromJson(overlayJson);
        if (fromJson.getIsBasic() || fromJson.getIsAdvanced()) {
            Integer num = view.get_parentId();
            int intValue = num != null ? num.intValue() : -1;
            if (intValue != -1) {
                FrameworksBaseMode modeFromCacheByParent = getModeFromCacheByParent(intValue);
                modeFromCache = modeFromCacheByParent instanceof FrameworksBarcodeBatchMode ? (FrameworksBarcodeBatchMode) modeFromCacheByParent : null;
            } else {
                modeFromCache = getModeFromCache(fromJson.getModeId());
            }
            if (modeFromCache == null) {
                if (intValue != -1) {
                    addPostModeCreationActionByParent(intValue, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$onAddOverlayToView$1
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
                            BarcodeBatchModule.this.onAddOverlayToView(view, overlayJson);
                        }
                    });
                    return;
                } else {
                    addPostModeCreationAction(fromJson.getModeId(), new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule$onAddOverlayToView$2
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
                            BarcodeBatchModule.this.onAddOverlayToView(view, overlayJson);
                        }
                    });
                    return;
                }
            }
            try {
                if (fromJson.getIsAdvanced()) {
                    BarcodeBatchAdvancedOverlay advancedOverlayFromJson = this.barcodeBatchDeserializer.advancedOverlayFromJson(modeFromCache.getMode(), fromJson.getOverlayJsonString());
                    if (fromJson.getHasListeners()) {
                        advancedOverlayFromJson.setListener(this.barcodeBatchAdvancedOverlayListener);
                    }
                    view.addOverlay(advancedOverlayFromJson);
                    return;
                }
                BarcodeBatchBasicOverlay basicOverlayFromJson = this.barcodeBatchDeserializer.basicOverlayFromJson(modeFromCache.getMode(), overlayJson);
                if (fromJson.getHasListeners()) {
                    basicOverlayFromJson.setListener(this.barcodeBatchBasicOverlayListener);
                }
                view.addOverlay(basicOverlayFromJson);
            } catch (Exception e) {
                this.logger.error(e);
            }
        }
    }

    public final boolean execute(FrameworksMethodCall method, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(result, "result");
        String method2 = method.getMethod();
        if (method2 == null) {
            return false;
        }
        switch (method2.hashCode()) {
            case -1368140248:
                if (!method2.equals("setAnchorForTrackedBarcode")) {
                    return false;
                }
                setAnchorForTrackedBarcode((String) method.argument("anchor"), ((Number) method.argument("trackedBarcodeIdentifier")).intValue(), (Long) method.argument("sessionFrameSequenceID"), ((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -1201274862:
                if (!method2.equals("removeBarcodeBatchListener")) {
                    return false;
                }
                removeBarcodeBatchListener(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -996595838:
                if (!method2.equals("getBarcodeBatchDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case -889766975:
                if (!method2.equals("unsubscribeBarcodeBatchBasicOverlayListener")) {
                    return false;
                }
                removeBasicOverlayListener(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -785175995:
                if (!method2.equals("clearTrackedBarcodeBrushes")) {
                    return false;
                }
                clearBasicOverlayTrackedBarcodeBrushes(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -751569435:
                if (!method2.equals("updateBarcodeBatchBasicOverlay")) {
                    return false;
                }
                updateBasicOverlay(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue(), (String) method.argument("overlayJson"), result);
                return true;
            case -698415797:
                if (!method2.equals("updateBarcodeBatchAdvancedOverlay")) {
                    return false;
                }
                updateAdvancedOverlay(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue(), (String) method.argument("overlayJson"), result);
                return true;
            case -695023046:
                if (!method2.equals("subscribeBarcodeBatchBasicOverlayListener")) {
                    return false;
                }
                addBasicOverlayListener(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -516927569:
                if (!method2.equals("addBarcodeBatchListener")) {
                    return false;
                }
                addBarcodeBatchListener(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -435990482:
                if (!method2.equals("applyBarcodeBatchModeSettings")) {
                    return false;
                }
                applyModeSettings(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), (String) method.argument("modeSettingsJson"), result);
                return true;
            case 40550919:
                if (!method2.equals("setBrushForTrackedBarcode")) {
                    return false;
                }
                setBasicOverlayBrushForTrackedBarcode(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue(), (String) method.argument("brushJson"), ((Number) method.argument("trackedBarcodeIdentifier")).intValue(), (Long) method.argument("sessionFrameSequenceID"));
                result.success(null);
                return true;
            case 328035961:
                if (!method2.equals("setWidgetForTrackedBarcode")) {
                    return false;
                }
                Object arguments = method.arguments();
                HashMap<String, Object> hashMap = arguments instanceof HashMap ? (HashMap) arguments : null;
                if (hashMap == null) {
                    result.error(PluginCall.CALLBACK_ID_DANGLING, "Invalid argument for setWidgetForTrackedBarcode", "");
                    return true;
                }
                setWidgetForTrackedBarcode(hashMap);
                result.success(null);
                return true;
            case 398809996:
                if (!method2.equals("clearTrackedBarcodeWidgets")) {
                    return false;
                }
                clearAdvancedOverlayTrackedBarcodeViews(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case 590834698:
                if (!method2.equals("setOffsetForTrackedBarcode")) {
                    return false;
                }
                setOffsetForTrackedBarcode((String) method.argument("offsetJson"), ((Number) method.argument("trackedBarcodeIdentifier")).intValue(), (Long) method.argument("sessionFrameSequenceID"), ((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case 687462997:
                if (!method2.equals("removeBarcodeBatchAdvancedOverlayDelegate")) {
                    return false;
                }
                removeAdvancedOverlayListener(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case 1207090381:
                if (!method2.equals("resetBarcodeBatchSession")) {
                    return false;
                }
                resetSession((Long) method.argument("frameSequenceId"));
                result.success(null);
                return true;
            case 1641371541:
                if (!method2.equals("setModeEnabledState")) {
                    return false;
                }
                setModeEnabled(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 1826405656:
                if (!method2.equals("addBarcodeBatchAdvancedOverlayDelegate")) {
                    return false;
                }
                addAdvancedOverlayListener(((Number) method.argument(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY)).intValue());
                result.success(null);
                return true;
            case 1854597702:
                if (!method2.equals("updateBarcodeBatchMode")) {
                    return false;
                }
                updateModeFromJson((String) method.arguments(), result);
                return true;
            case 1866548539:
                if (!method2.equals("barcodeBatchFinishDidUpdateSession")) {
                    return false;
                }
                finishDidUpdateSession(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(true);
                return true;
            case 1927438603:
                if (!method2.equals("getLastFrameData")) {
                    return false;
                }
                getFrameDataBytes((String) method.arguments(), result);
                return true;
            default:
                return false;
        }
    }

    private final TrackedBarcode getTrackedBarcodeFromLatestSession(int barcodeId, Long frameSequenceId) {
        FrameworksBarcodeBatchSession frameworksBarcodeBatchSession = this.cachedBatchSession.get();
        if (frameworksBarcodeBatchSession == null) {
            return null;
        }
        if (frameSequenceId == null || frameSequenceId.longValue() < 0 || frameworksBarcodeBatchSession.getFrameSequenceId() == frameSequenceId.longValue()) {
            return frameworksBarcodeBatchSession.getTrackedBarcodes().get(Integer.valueOf(barcodeId));
        }
        return null;
    }

    private final void resetLatestSession(Long frameSequenceId) {
        BarcodeBatchSession barcodeBatchSession;
        FrameworksBarcodeBatchSession frameworksBarcodeBatchSession = this.cachedBatchSession.get();
        if (frameworksBarcodeBatchSession == null) {
            return;
        }
        if ((frameSequenceId == null || frameworksBarcodeBatchSession.getFrameSequenceId() == frameSequenceId.longValue()) && (barcodeBatchSession = frameworksBarcodeBatchSession.getBatchSession().get()) != null) {
            barcodeBatchSession.reset();
        }
    }

    /* compiled from: BarcodeBatchModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/BarcodeBatchModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/batch/BarcodeBatchModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeBatchModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeBatchModule(emitter, new FrameworksBarcodeBatchBasicOverlayListener(emitter), new FrameworksBarcodeBatchAdvancedOverlayListener(emitter), null, null, null, null, null, null, null, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null);
        }
    }
}
