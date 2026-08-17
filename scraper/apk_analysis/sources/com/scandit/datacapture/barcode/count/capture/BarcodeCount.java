package com.scandit.datacapture.barcode.count.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListenerReversedAdapter;
import com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusProvider;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.data.ClusteringMode;
import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategy;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.VideoResolution;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.Feedback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u008a\u00012\u00020\u00012\u00020\u0002:\u0006\u008a\u0001\u008b\u0001\u008c\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013H\u0097\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0097\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001d\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ#\u0010#\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!H\u0007¢\u0006\u0004\b#\u0010$J\u000f\u0010'\u001a\u00020\u001fH\u0000¢\u0006\u0004\b%\u0010&J\r\u0010(\u001a\u00020\u0010¢\u0006\u0004\b(\u0010\u001aJ\r\u0010)\u001a\u00020\u0010¢\u0006\u0004\b)\u0010\u001aJ\u0015\u0010,\u001a\u00020\u00102\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\u00020\u00102\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b.\u0010-J\u0017\u00102\u001a\u00020\u00102\u0006\u0010+\u001a\u00020/H\u0000¢\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020\u00102\u0006\u0010+\u001a\u00020/H\u0000¢\u0006\u0004\b3\u00101J\r\u00105\u001a\u00020\u0005¢\u0006\u0004\b5\u00106J\r\u00107\u001a\u00020\u0010¢\u0006\u0004\b7\u0010\u001aJ#\u00107\u001a\u00020\u00102\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001008H\u0000¢\u0006\u0004\b9\u0010:J\r\u0010;\u001a\u00020\u0010¢\u0006\u0004\b;\u0010\u001aJ\r\u0010<\u001a\u00020\u0010¢\u0006\u0004\b<\u0010\u001aJ\u0015\u0010=\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b=\u0010\u0012J\u000f\u0010?\u001a\u00020\u0013H\u0000¢\u0006\u0004\b>\u0010\u0015J\u000f\u0010C\u001a\u00020@H\u0000¢\u0006\u0004\bA\u0010BJ\u0017\u0010H\u001a\u00020\u00102\u0006\u0010E\u001a\u00020DH\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010L\u001a\u00020\u00102\f\u0010K\u001a\b\u0012\u0004\u0012\u00020J0I¢\u0006\u0004\bL\u0010MJ\u0017\u0010R\u001a\u00020\u00102\u0006\u0010O\u001a\u00020NH\u0000¢\u0006\u0004\bP\u0010QJ\u0017\u0010W\u001a\u00020\u00102\u0006\u0010T\u001a\u00020SH\u0000¢\u0006\u0004\bU\u0010VJ\u0017\u0010Y\u001a\u00020\u00102\u0006\u0010T\u001a\u00020SH\u0000¢\u0006\u0004\bX\u0010VR \u0010_\u001a\b\u0012\u0004\u0012\u00020*0Z8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R \u0010b\u001a\b\u0012\u0004\u0012\u00020/0Z8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b`\u0010\\\u001a\u0004\ba\u0010^R \u0010f\u001a\b\u0012\u0004\u0012\u00020c0Z8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bd\u0010\\\u001a\u0004\be\u0010^R\u0018\u0010g\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bg\u0010hR*\u0010q\u001a\u00020i2\u0006\u0010j\u001a\u00020i8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR$\u0010y\u001a\u0004\u0018\u00010r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bs\u0010t\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR$\u0010{\u001a\u00020\u00132\u0006\u0010z\u001a\u00020\u00138W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b{\u0010\u0015\"\u0004\b|\u0010}R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0081\u0001\u001a\u00020\u00138@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010\u0015R\u0018\u0010\u0085\u0001\u001a\u00030\u0082\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0086\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001¨\u0006\u008d\u0001"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;", "impl", "Lcom/scandit/datacapture/barcode/internal/module/count/capture/InternalBarcodeCountSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;Lcom/scandit/datacapture/barcode/internal/module/count/capture/InternalBarcodeCountSession;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "list", "", "_setBarcodeCountCaptureList", "(Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;)V", "", "_shouldDisableModeWhenCaptureListCompleted", "()Z", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeClusterEditor;", "beginClusterEditing", "()Lcom/scandit/datacapture/barcode/count/capture/BarcodeClusterEditor;", "clearAdditionalBarcodes", "()V", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "_setDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;", "settings", "Ljava/lang/Runnable;", "whenDone", "applySettings", "(Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;Ljava/lang/Runnable;)V", "_getSettings$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;", "_getSettings", "startScanningPhase", "reset", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountListener;)V", "removeListener", "Lcom/scandit/datacapture/barcode/internal/module/count/capture/InternalBarcodeCountListener;", "addInternalListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/count/capture/InternalBarcodeCountListener;)V", "addInternalListener", "removeInternalListener$scandit_barcode_capture", "removeInternalListener", "_session", "()Lcom/scandit/datacapture/barcode/internal/module/count/capture/InternalBarcodeCountSession;", "endScanningPhase", "Lkotlin/Function1;", "endScanningPhase$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function1;)V", "_stopImuDataStream", "_enableImuDataIfNeeded", "setBarcodeCountCaptureList", "isCaptureListSet$scandit_barcode_capture", "isCaptureListSet", "", "getCaptureListTargetQuantity$scandit_barcode_capture", "()I", "getCaptureListTargetQuantity", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusListener;", "viewHandler", "_setStatusListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusListener;)V", "_setStatusListener", "", "Lcom/scandit/datacapture/barcode/data/Barcode;", "additionalBarcodes", "setAdditionalBarcodes", "(Ljava/util/List;)V", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusProvider;", "statusProvider", "setInternalStatusProvider$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusProvider;)V", "setInternalStatusProvider", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "trackedBarcode", "didAcceptNotInListBarcode$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;)V", "didAcceptNotInListBarcode", "didRejectNotInListBarcode$scandit_barcode_capture", "didRejectNotInListBarcode", "Ljava/util/concurrent/CopyOnWriteArrayList;", "e", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getListeners$scandit_barcode_capture", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "listeners", "f", "getInternalListeners$scandit_barcode_capture", "internalListeners", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;", "g", "getListListeners$scandit_barcode_capture", "listListeners", "captureList", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;", "value", "h", "Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;", "getFeedback", "()Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;", "setFeedback", "(Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;)V", Feedback.TYPE, "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountFeedbackChangeListener;", "i", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountFeedbackChangeListener;", "getFeedbackChangeListener$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountFeedbackChangeListener;", "setFeedbackChangeListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountFeedbackChangeListener;)V", "feedbackChangeListener", "<set-?>", "isEnabled", "setEnabled", "(Z)V", "getDataCaptureContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getTriggerAutoFocusOnShutter$scandit_barcode_capture", "triggerAutoFocusOnShutter", "", "getTriggerAutoFocusDelay$scandit_barcode_capture", "()J", "triggerAutoFocusDelay", "Lcom/scandit/datacapture/core/data/ClusteringMode;", "getClusteringMode$scandit_barcode_capture", "()Lcom/scandit/datacapture/core/data/ClusteringMode;", "clusteringMode", "Companion", "com/scandit/datacapture/barcode/count/capture/f", "com/scandit/datacapture/barcode/count/capture/g", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCount implements DataCaptureMode, BarcodeCountProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final InternalBarcodeCountSession a;
    private final /* synthetic */ BarcodeCountProxyAdapter b;
    private DataCaptureContext c;
    private BarcodeCountCaptureList captureList;
    private BarcodeCountSettings d;

    /* renamed from: e, reason: from kotlin metadata */
    private final CopyOnWriteArrayList listeners;

    /* renamed from: f, reason: from kotlin metadata */
    private final CopyOnWriteArrayList internalListeners;

    /* renamed from: g, reason: from kotlin metadata */
    private final CopyOnWriteArrayList listListeners;

    /* renamed from: h, reason: from kotlin metadata */
    private BarcodeCountFeedback feedback;

    /* renamed from: i, reason: from kotlin metadata */
    private BarcodeCountFeedbackChangeListener feedbackChangeListener;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J!\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount$Companion;", "", "Lcom/scandit/datacapture/core/source/CameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/source/CameraSettings;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;", "settings", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "forDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;)Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final CameraSettings createRecommendedCameraSettings() {
            CameraSettings cameraSettings = new CameraSettings();
            cameraSettings.setFocusGestureStrategy(FocusGestureStrategy.NONE);
            cameraSettings.setPreferredResolution(VideoResolution.UHD4K);
            cameraSettings.setZoomGestureZoomFactor(1.0f);
            cameraSettings.setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", Boolean.TRUE);
            cameraSettings.setProperty("exposureTargetBias", -1);
            cameraSettings.setProperty("api", 2);
            cameraSettings.setProperty("focusStrategy", "forceContinuous");
            return cameraSettings;
        }

        @JvmStatic
        public final BarcodeCount forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeCountSettings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            BarcodeCount barcodeCount = new BarcodeCount(dataCaptureContext, settings, null);
            ProxyCacheKt.getGlobalProxyCache().getOrPut(Reflection.getOrCreateKotlinClass(BarcodeCount.class), null, barcodeCount, new b(barcodeCount));
            if (dataCaptureContext != null) {
                dataCaptureContext.setMode(barcodeCount);
            }
            return barcodeCount;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCount(NativeBarcodeCount impl, InternalBarcodeCountSession session) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(session, "session");
        this.a = session;
        this.b = new BarcodeCountProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.d = new BarcodeCountSettings();
        this.listeners = new CopyOnWriteArrayList();
        this.internalListeners = new CopyOnWriteArrayList();
        this.listListeners = new CopyOnWriteArrayList();
        impl.addListenerAsync(new com.scandit.datacapture.barcode.internal.module.count.capture.k(new f(this), this), 1);
        BarcodeCountFeedback defaultFeedback = BarcodeCountFeedback.INSTANCE.defaultFeedback();
        getA().setSuccessFeedback(new BarcodeCount$setNativeFeedback$1(defaultFeedback));
        getA().setUnrecognizedFeedback(new BarcodeCount$setNativeFeedback$2(defaultFeedback));
        getA().setFailureFeedback(new BarcodeCount$setNativeFeedback$3(defaultFeedback));
        this.feedback = defaultFeedback;
    }

    public static /* synthetic */ void applySettings$default(BarcodeCount barcodeCount, BarcodeCountSettings barcodeCountSettings, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        barcodeCount.applySettings(barcodeCountSettings, runnable);
    }

    @JvmStatic
    public static final CameraSettings createRecommendedCameraSettings() {
        return INSTANCE.createRecommendedCameraSettings();
    }

    @JvmStatic
    public static final BarcodeCount forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeCountSettings barcodeCountSettings) {
        return INSTANCE.forDataCaptureContext(dataCaptureContext, barcodeCountSettings);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @NativeImpl
    /* renamed from: _dataCaptureModeImpl */
    public NativeDataCaptureMode getC() {
        return this.b.getC();
    }

    public final void _enableImuDataIfNeeded() {
        DataCaptureContext dataCaptureContext;
        DataCaptureContext dataCaptureContext2 = this.c;
        if ((dataCaptureContext2 == null || dataCaptureContext2._isCameraTimestampsRealtime()) && (dataCaptureContext = this.c) != null) {
            Object property = this.d.getProperty(BarcodeCountSettings.PROPERTY_USE_IMU_DATA);
            Intrinsics.checkNotNull(property, "null cannot be cast to non-null type kotlin.Boolean");
            dataCaptureContext._setShouldUseImuData(((Boolean) property).booleanValue());
        }
    }

    /* renamed from: _getSettings$scandit_barcode_capture, reason: from getter */
    public final BarcodeCountSettings getD() {
        return this.d;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCount getA() {
        return this.b.getA();
    }

    /* renamed from: _session, reason: from getter */
    public final InternalBarcodeCountSession getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    @ProxyFunction(nativeName = "setBarcodeCountCaptureList")
    public void _setBarcodeCountCaptureList(BarcodeCountCaptureList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.b._setBarcodeCountCaptureList(list);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.c = dataCaptureContext;
        _enableImuDataIfNeeded();
    }

    public final void _setStatusListener$scandit_barcode_capture(NativeBarcodeCountStatusListener viewHandler) {
        Intrinsics.checkNotNullParameter(viewHandler, "viewHandler");
        getA().setStatusListener(viewHandler);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    @ProxyFunction(nativeName = "shouldDisableModeWhenCaptureListCompleted")
    public boolean _shouldDisableModeWhenCaptureListCompleted() {
        return this.b._shouldDisableModeWhenCaptureListCompleted();
    }

    public final void _stopImuDataStream() {
        DataCaptureContext c = getC();
        if (c != null) {
            c._setShouldUseImuData(false);
        }
    }

    public final void addInternalListener$scandit_barcode_capture(InternalBarcodeCountListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.internalListeners.contains(listener)) {
            return;
        }
        this.internalListeners.add(listener);
        listener.onObservationStarted(this);
    }

    public final void addListener(BarcodeCountListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
        listener.onObservationStarted(this);
    }

    public final void applySettings(BarcodeCountSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        applySettings$default(this, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    @ProxyFunction
    public BarcodeClusterEditor beginClusterEditing() {
        return this.b.beginClusterEditing();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountProxy
    @ProxyFunction(nativeName = "clearAdditionalBarcodes")
    public void clearAdditionalBarcodes() {
        this.b.clearAdditionalBarcodes();
    }

    public final void didAcceptNotInListBarcode$scandit_barcode_capture(TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        getA().didAcceptNotInListBarcode(trackedBarcode.getA());
    }

    public final void didRejectNotInListBarcode$scandit_barcode_capture(TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        getA().didRejectNotInListBarcode(trackedBarcode.getA());
    }

    public final void endScanningPhase() {
        endScanningPhase$scandit_barcode_capture(h.a);
    }

    public final void endScanningPhase$scandit_barcode_capture(Function1<? super Boolean, Unit> whenDone) {
        FrameSource frameSource;
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        setEnabled(false);
        DataCaptureContext c = getC();
        if (c != null && (frameSource = c.get_frameSource()) != null) {
            frameSource.switchToDesiredState(FrameSourceState.OFF, LambdaExtensionsKt.Callback(whenDone));
        }
        _stopImuDataStream();
    }

    public final int getCaptureListTargetQuantity$scandit_barcode_capture() {
        BarcodeCountCaptureList barcodeCountCaptureList = this.captureList;
        if (barcodeCountCaptureList != null) {
            return barcodeCountCaptureList.getTargetBarcodesQuantity$scandit_barcode_capture();
        }
        return 0;
    }

    public final ClusteringMode getClusteringMode$scandit_barcode_capture() {
        return this.d.getClusteringMode();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext, reason: from getter */
    public DataCaptureContext getC() {
        return this.c;
    }

    public final BarcodeCountFeedback getFeedback() {
        return this.feedback;
    }

    /* renamed from: getFeedbackChangeListener$scandit_barcode_capture, reason: from getter */
    public final BarcodeCountFeedbackChangeListener getFeedbackChangeListener() {
        return this.feedbackChangeListener;
    }

    public final CopyOnWriteArrayList<InternalBarcodeCountListener> getInternalListeners$scandit_barcode_capture() {
        return this.internalListeners;
    }

    public final CopyOnWriteArrayList<BarcodeCountCaptureListListener> getListListeners$scandit_barcode_capture() {
        return this.listListeners;
    }

    public final CopyOnWriteArrayList<BarcodeCountListener> getListeners$scandit_barcode_capture() {
        return this.listeners;
    }

    public final long getTriggerAutoFocusDelay$scandit_barcode_capture() {
        BarcodeCountSettings barcodeCountSettings = this.d;
        Object property = barcodeCountSettings != null ? barcodeCountSettings.getProperty(BarcodeCountSettings.PROPERTY_TRIGGER_AUTO_FOCUS_DELAY) : null;
        Long l = property instanceof Long ? (Long) property : null;
        if (l != null) {
            return l.longValue();
        }
        return 100L;
    }

    public final boolean getTriggerAutoFocusOnShutter$scandit_barcode_capture() {
        BarcodeCountSettings barcodeCountSettings = this.d;
        Object property = barcodeCountSettings != null ? barcodeCountSettings.getProperty(BarcodeCountSettings.PROPERTY_TRIGGER_AUTO_FOCUS_ON_SHUTTER) : null;
        Boolean bool = property instanceof Boolean ? (Boolean) property : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final boolean isCaptureListSet$scandit_barcode_capture() {
        return this.captureList != null;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public boolean isEnabled() {
        return this.b.isEnabled();
    }

    public final void removeInternalListener$scandit_barcode_capture(InternalBarcodeCountListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.internalListeners.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    public final void removeListener(BarcodeCountListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.listeners.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    public final void reset() {
        getA().reset();
    }

    public final void setAdditionalBarcodes(List<Barcode> additionalBarcodes) {
        Intrinsics.checkNotNullParameter(additionalBarcodes, "additionalBarcodes");
        NativeBarcodeCount a = getA();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(additionalBarcodes, 10));
        Iterator<T> it = additionalBarcodes.iterator();
        while (it.hasNext()) {
            arrayList.add(((Barcode) it.next()).getA());
        }
        a.setAdditionalBarcodes(CollectionsExtensionsKt.toArrayList(arrayList));
    }

    public final void setBarcodeCountCaptureList(BarcodeCountCaptureList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.captureList = list;
        _setBarcodeCountCaptureList(list);
        list.getA().addListenerAsync(new BarcodeCountCaptureListListenerReversedAdapter(new g(this), list, null, 4, null));
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public void setEnabled(boolean z) {
        this.b.setEnabled(z);
    }

    public final void setFeedback(BarcodeCountFeedback value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.feedback = value;
        getA().setSuccessFeedback(new BarcodeCount$setNativeFeedback$1(value));
        getA().setUnrecognizedFeedback(new BarcodeCount$setNativeFeedback$2(value));
        getA().setFailureFeedback(new BarcodeCount$setNativeFeedback$3(value));
        BarcodeCountFeedbackChangeListener barcodeCountFeedbackChangeListener = this.feedbackChangeListener;
        if (barcodeCountFeedbackChangeListener != null) {
            barcodeCountFeedbackChangeListener.feedbackChanged(value);
        }
    }

    public final void setFeedbackChangeListener$scandit_barcode_capture(BarcodeCountFeedbackChangeListener barcodeCountFeedbackChangeListener) {
        this.feedbackChangeListener = barcodeCountFeedbackChangeListener;
    }

    public final void setInternalStatusProvider$scandit_barcode_capture(NativeBarcodeCountStatusProvider statusProvider) {
        Intrinsics.checkNotNullParameter(statusProvider, "statusProvider");
        getA().setStatusProvider(statusProvider);
    }

    public final void startScanningPhase() {
        getA().activateState(BarcodeCountState.SCANNING);
    }

    public final void applySettings(BarcodeCountSettings settings, Runnable whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeWrappedFuture applySettingsWrapped = getA().applySettingsWrapped(settings.getA());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, whenDone);
        this.d = settings;
        _enableImuDataIfNeeded();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCount(com.scandit.datacapture.core.capture.DataCaptureContext r2, com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r1 = this;
            if (r2 == 0) goto L7
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext r2 = r2.getA()
            goto L8
        L7:
            r2 = 0
        L8:
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings r4 = r3.getA()
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount r2 = com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount.create(r2, r4)
            java.lang.String r4 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession r4 = new com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession
            com.scandit.datacapture.barcode.count.capture.a r0 = new com.scandit.datacapture.barcode.count.capture.a
            r0.<init>(r2)
            r4.<init>(r0)
            r1.<init>(r2, r4)
            r1._enableImuDataIfNeeded()
            r1.d = r3
            r1._enableImuDataIfNeeded()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.capture.BarcodeCount.<init>(com.scandit.datacapture.core.capture.DataCaptureContext, com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
