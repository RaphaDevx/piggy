package io.sentry.android.replay;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.Breadcrumb;
import io.sentry.DataCategory;
import io.sentry.Hint;
import io.sentry.IConnectionStatusProvider;
import io.sentry.IScope;
import io.sentry.IScopes;
import io.sentry.ISentryExecutorService;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.NoOpReplayBreadcrumbConverter;
import io.sentry.ReplayBreadcrumbConverter;
import io.sentry.ReplayController;
import io.sentry.ScopeCallback;
import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayOptions;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.BufferCaptureStrategy;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.android.replay.capture.SessionCaptureStrategy;
import io.sentry.android.replay.gestures.GestureRecorder;
import io.sentry.android.replay.gestures.TouchRecorderCallback;
import io.sentry.android.replay.util.ContextKt;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import io.sentry.android.replay.util.SamplingKt;
import io.sentry.cache.PersistingScopeObserver;
import io.sentry.hints.Backfillable;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.transport.RateLimiter;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.FileUtils;
import io.sentry.util.HintUtils;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Random;
import java.io.Closeable;
import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: ReplayIntegration.kt */
@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\b\u0007\u0018\u0000 |2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0003|}~B\u0017\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB\u0091\u0001\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012#\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012\u0012%\b\u0002\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u000f¢\u0006\u0002\u0010 BN\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012¢\u0006\u0002\u0010!J\u0017\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020LH\u0002J\u0012\u0010P\u001a\u00020L2\b\b\u0002\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u00020LH\u0016J\b\u0010T\u001a\u00020LH\u0016J\b\u0010U\u001a\u00020LH\u0016J\b\u0010V\u001a\u00020LH\u0002J\b\u0010W\u001a\u000209H\u0016J\b\u0010X\u001a\u00020\u0013H\u0016J\b\u0010Y\u001a\u00020\u0019H\u0016J\b\u0010Z\u001a\u00020\u0019H\u0016J\u000e\u0010[\u001a\u00020L2\u0006\u0010\\\u001a\u00020]J\u0010\u0010^\u001a\u00020L2\u0006\u0010_\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020L2\u0006\u0010b\u001a\u00020cH\u0016J\u0010\u0010d\u001a\u00020L2\u0006\u0010e\u001a\u00020fH\u0016J\u0018\u0010d\u001a\u00020L2\u0006\u0010g\u001a\u00020;2\u0006\u0010h\u001a\u00020iH\u0016J\u0010\u0010j\u001a\u00020L2\u0006\u0010k\u001a\u00020lH\u0016J\u0018\u0010m\u001a\u00020L2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020oH\u0016J\b\u0010q\u001a\u00020LH\u0016J\b\u0010r\u001a\u00020LH\u0002J\u0018\u0010s\u001a\u00020L2\u0006\u0010I\u001a\u00020J2\u0006\u0010/\u001a\u000200H\u0016J\b\u0010t\u001a\u00020LH\u0002J\b\u0010u\u001a\u00020LH\u0016J\b\u0010v\u001a\u00020LH\u0002J\u0010\u0010w\u001a\u00020L2\u0006\u0010x\u001a\u000209H\u0016J\b\u0010y\u001a\u00020LH\u0016J\b\u0010z\u001a\u00020LH\u0016J\b\u0010{\u001a\u00020LH\u0002R\u0010\u0010\"\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020&X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b3\u00104R\u0010\u00107\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010:\u001a\u0004\u0018\u00010;8F¢\u0006\u0006\u001a\u0004\b<\u0010=R+\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010>\u001a\n @*\u0004\u0018\u00010?0?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u00106\u001a\u0004\bA\u0010BR\u001b\u0010D\u001a\u00020E8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bH\u00106\u001a\u0004\bF\u0010GR\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u007f"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration;", "Lio/sentry/Integration;", "Ljava/io/Closeable;", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "Lio/sentry/android/replay/gestures/TouchRecorderCallback;", "Lio/sentry/ReplayController;", "Lio/sentry/IConnectionStatusProvider$IConnectionStatusObserver;", "Lio/sentry/transport/RateLimiter$IRateLimitObserver;", "Lio/sentry/android/replay/WindowCallback;", "context", "Landroid/content/Context;", "dateProvider", "Lio/sentry/transport/ICurrentDateProvider;", "(Landroid/content/Context;Lio/sentry/transport/ICurrentDateProvider;)V", "recorderProvider", "Lkotlin/Function0;", "Lio/sentry/android/replay/Recorder;", "replayCacheProvider", "Lkotlin/Function1;", "Lio/sentry/protocol/SentryId;", "Lkotlin/ParameterName;", "name", "replayId", "Lio/sentry/android/replay/ReplayCache;", "replayCaptureStrategyProvider", "", "isFullSession", "Lio/sentry/android/replay/capture/CaptureStrategy;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "gestureRecorderProvider", "Lio/sentry/android/replay/gestures/GestureRecorder;", "(Landroid/content/Context;Lio/sentry/transport/ICurrentDateProvider;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lio/sentry/android/replay/util/MainLooperHandler;Lkotlin/jvm/functions/Function0;)V", "(Landroid/content/Context;Lio/sentry/transport/ICurrentDateProvider;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "captureStrategy", "debugMaskingEnabled", "gestureRecorder", "isEnabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isEnabled$sentry_android_replay_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "isManualPause", "isManualPause$sentry_android_replay_release", "lifecycle", "Lio/sentry/android/replay/ReplayLifecycle;", "lifecycleLock", "Lio/sentry/util/AutoClosableReentrantLock;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "random", "Lio/sentry/util/Random;", "getRandom", "()Lio/sentry/util/Random;", "random$delegate", "Lkotlin/Lazy;", "recorder", "replayBreadcrumbConverter", "Lio/sentry/ReplayBreadcrumbConverter;", "replayCacheDir", "Ljava/io/File;", "getReplayCacheDir", "()Ljava/io/File;", "replayExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "getReplayExecutor", "()Ljava/util/concurrent/ScheduledExecutorService;", "replayExecutor$delegate", "rootViewsSpy", "Lio/sentry/android/replay/RootViewsSpy;", "getRootViewsSpy$sentry_android_replay_release", "()Lio/sentry/android/replay/RootViewsSpy;", "rootViewsSpy$delegate", "scopes", "Lio/sentry/IScopes;", "captureReplay", "", "isTerminating", "(Ljava/lang/Boolean;)V", "checkCanRecord", "cleanupReplays", "unfinishedReplayId", "", "close", "disableDebugMaskingOverlay", "enableDebugMaskingOverlay", "finalizePreviousReplay", "getBreadcrumbConverter", "getReplayId", "isDebugMaskingOverlayEnabled", "isRecording", "onConfigurationChanged", FirebaseConstants.Commands.CONFIGURE, "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "onConnectionStatusChanged", "status", "Lio/sentry/IConnectionStatusProvider$ConnectionStatus;", "onRateLimitChanged", "rateLimiter", "Lio/sentry/transport/RateLimiter;", "onScreenshotRecorded", "bitmap", "Landroid/graphics/Bitmap;", "screenshot", "frameTimestamp", "", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "onWindowSizeChanged", "width", "", "height", "pause", "pauseInternal", "register", "registerRootViewListeners", "resume", "resumeInternal", "setBreadcrumbConverter", "converter", "start", "stop", "unregisterRootViewListeners", "Companion", "PreviousReplayHint", "ReplayExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ReplayIntegration implements Integration, Closeable, ScreenshotRecorderCallback, TouchRecorderCallback, ReplayController, IConnectionStatusProvider.IConnectionStatusObserver, RateLimiter.IRateLimitObserver, WindowCallback {
    private CaptureStrategy captureStrategy;
    private final Context context;
    private final ICurrentDateProvider dateProvider;
    private boolean debugMaskingEnabled;
    private GestureRecorder gestureRecorder;
    private Function0<GestureRecorder> gestureRecorderProvider;
    private final AtomicBoolean isEnabled;
    private final AtomicBoolean isManualPause;
    private final ReplayLifecycle lifecycle;
    private final AutoClosableReentrantLock lifecycleLock;
    private MainLooperHandler mainLooperHandler;
    private SentryOptions options;

    /* renamed from: random$delegate, reason: from kotlin metadata */
    private final Lazy random;
    private Recorder recorder;
    private final Function0<Recorder> recorderProvider;
    private ReplayBreadcrumbConverter replayBreadcrumbConverter;
    private final Function1<SentryId, ReplayCache> replayCacheProvider;
    private Function1<? super Boolean, ? extends CaptureStrategy> replayCaptureStrategyProvider;

    /* renamed from: replayExecutor$delegate, reason: from kotlin metadata */
    private final Lazy replayExecutor;

    /* renamed from: rootViewsSpy$delegate, reason: from kotlin metadata */
    private final Lazy rootViewsSpy;
    private IScopes scopes;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: ReplayIntegration.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration$PreviousReplayHint;", "Lio/sentry/hints/Backfillable;", "()V", "shouldEnrich", "", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class PreviousReplayHint implements Backfillable {
        @Override // io.sentry.hints.Backfillable
        public boolean shouldEnrich() {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ReplayIntegration(Context context, ICurrentDateProvider dateProvider, Function0<? extends Recorder> function0, Function1<? super SentryId, ReplayCache> function1) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        this.context = context;
        this.dateProvider = dateProvider;
        this.recorderProvider = function0;
        this.replayCacheProvider = function1;
        this.random = LazyKt.lazy(new Function0<Random>() { // from class: io.sentry.android.replay.ReplayIntegration$random$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Random invoke() {
                return new Random();
            }
        });
        this.rootViewsSpy = LazyKt.lazy(new Function0<RootViewsSpy>() { // from class: io.sentry.android.replay.ReplayIntegration$rootViewsSpy$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RootViewsSpy invoke() {
                return RootViewsSpy.INSTANCE.install();
            }
        });
        this.replayExecutor = LazyKt.lazy(new Function0<ScheduledExecutorService>() { // from class: io.sentry.android.replay.ReplayIntegration$replayExecutor$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return Executors.newSingleThreadScheduledExecutor(new ReplayIntegration.ReplayExecutorServiceThreadFactory());
            }
        });
        this.isEnabled = new AtomicBoolean(false);
        this.isManualPause = new AtomicBoolean(false);
        NoOpReplayBreadcrumbConverter noOpReplayBreadcrumbConverter = NoOpReplayBreadcrumbConverter.getInstance();
        Intrinsics.checkNotNullExpressionValue(noOpReplayBreadcrumbConverter, "getInstance(...)");
        this.replayBreadcrumbConverter = noOpReplayBreadcrumbConverter;
        this.mainLooperHandler = new MainLooperHandler(null, 1, null);
        this.lifecycleLock = new AutoClosableReentrantLock();
        this.lifecycle = new ReplayLifecycle();
    }

    public /* synthetic */ ReplayIntegration(Context context, ICurrentDateProvider iCurrentDateProvider, Function0 function0, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, iCurrentDateProvider, (i & 4) != 0 ? null : function0, (i & 8) != 0 ? null : function1);
    }

    /* compiled from: ReplayIntegration.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration$Companion;", "", "()V", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SentryIntegrationPackageStorage.getInstance().addPackage("maven:io.sentry:sentry-android-replay", "8.23.0");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ReplayIntegration(Context context, ICurrentDateProvider dateProvider) {
        this(ContextKt.appContext(context), dateProvider, null, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
    }

    public /* synthetic */ ReplayIntegration(Context context, ICurrentDateProvider iCurrentDateProvider, Function0 function0, Function1 function1, Function1 function12, MainLooperHandler mainLooperHandler, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, iCurrentDateProvider, function0, function1, (i & 16) != 0 ? null : function12, (i & 32) != 0 ? null : mainLooperHandler, (i & 64) != 0 ? null : function02);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ReplayIntegration(Context context, ICurrentDateProvider dateProvider, Function0<? extends Recorder> function0, Function1<? super SentryId, ReplayCache> function1, Function1<? super Boolean, ? extends CaptureStrategy> function12, MainLooperHandler mainLooperHandler, Function0<GestureRecorder> function02) {
        this(ContextKt.appContext(context), dateProvider, function0, function1);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        this.replayCaptureStrategyProvider = function12;
        this.mainLooperHandler = mainLooperHandler == null ? new MainLooperHandler(null, 1, null) : mainLooperHandler;
        this.gestureRecorderProvider = function02;
    }

    private final Random getRandom() {
        return (Random) this.random.getValue();
    }

    public final RootViewsSpy getRootViewsSpy$sentry_android_replay_release() {
        return (RootViewsSpy) this.rootViewsSpy.getValue();
    }

    private final ScheduledExecutorService getReplayExecutor() {
        return (ScheduledExecutorService) this.replayExecutor.getValue();
    }

    /* renamed from: isEnabled$sentry_android_replay_release, reason: from getter */
    public final AtomicBoolean getIsEnabled() {
        return this.isEnabled;
    }

    /* renamed from: isManualPause$sentry_android_replay_release, reason: from getter */
    public final AtomicBoolean getIsManualPause() {
        return this.isManualPause;
    }

    public final File getReplayCacheDir() {
        CaptureStrategy captureStrategy = this.captureStrategy;
        if (captureStrategy != null) {
            return captureStrategy.getReplayCacheDir();
        }
        return null;
    }

    @Override // io.sentry.Integration
    public void register(IScopes scopes, SentryOptions options) {
        WindowRecorder windowRecorder;
        GestureRecorder gestureRecorder;
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
        if (!options.getSessionReplay().isSessionReplayEnabled() && !options.getSessionReplay().isSessionReplayForErrorsEnabled()) {
            options.getLogger().log(SentryLevel.INFO, "Session replay is disabled, no sample rate specified", new Object[0]);
            return;
        }
        this.scopes = scopes;
        Function0<Recorder> function0 = this.recorderProvider;
        if (function0 == null || (windowRecorder = function0.invoke()) == null) {
            MainLooperHandler mainLooperHandler = this.mainLooperHandler;
            ScheduledExecutorService replayExecutor = getReplayExecutor();
            Intrinsics.checkNotNullExpressionValue(replayExecutor, "<get-replayExecutor>(...)");
            windowRecorder = new WindowRecorder(options, this, this, mainLooperHandler, replayExecutor);
        }
        this.recorder = windowRecorder;
        Function0<GestureRecorder> function02 = this.gestureRecorderProvider;
        if (function02 == null || (gestureRecorder = function02.invoke()) == null) {
            gestureRecorder = new GestureRecorder(options, this);
        }
        this.gestureRecorder = gestureRecorder;
        this.isEnabled.set(true);
        options.getConnectionStatusProvider().addConnectionStatusObserver(this);
        RateLimiter rateLimiter = scopes.getRateLimiter();
        if (rateLimiter != null) {
            rateLimiter.addRateLimitObserver(this);
        }
        IntegrationUtils.addIntegrationToSdkVersion("Replay");
        finalizePreviousReplay();
    }

    @Override // io.sentry.ReplayController
    public boolean isRecording() {
        return this.lifecycle.getCurrentState().compareTo(ReplayState.STARTED) >= 0 && this.lifecycle.getCurrentState().compareTo(ReplayState.STOPPED) < 0;
    }

    @Override // io.sentry.ReplayController
    public void start() {
        SentryOptions sentryOptions;
        BufferCaptureStrategy bufferCaptureStrategy;
        CaptureStrategy captureStrategy;
        SentryOptions sentryOptions2;
        ISentryLifecycleToken acquire = this.lifecycleLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (!this.isEnabled.get()) {
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            if (!this.lifecycle.isAllowed(ReplayState.STARTED)) {
                SentryOptions sentryOptions3 = this.options;
                if (sentryOptions3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions3 = null;
                }
                sentryOptions3.getLogger().log(SentryLevel.DEBUG, "Session replay is already being recorded, not starting a new one", new Object[0]);
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            Random random = getRandom();
            SentryOptions sentryOptions4 = this.options;
            if (sentryOptions4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                sentryOptions4 = null;
            }
            boolean sample = SamplingKt.sample(random, sentryOptions4.getSessionReplay().getSessionSampleRate());
            if (!sample) {
                SentryOptions sentryOptions5 = this.options;
                if (sentryOptions5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions5 = null;
                }
                if (!sentryOptions5.getSessionReplay().isSessionReplayForErrorsEnabled()) {
                    SentryOptions sentryOptions6 = this.options;
                    if (sentryOptions6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                        sentryOptions6 = null;
                    }
                    sentryOptions6.getLogger().log(SentryLevel.INFO, "Session replay is not started, full session was not sampled and onErrorSampleRate is not specified", new Object[0]);
                    AutoCloseableKt.closeFinally(acquire, null);
                    return;
                }
            }
            this.lifecycle.setCurrentState$sentry_android_replay_release(ReplayState.STARTED);
            Function1<? super Boolean, ? extends CaptureStrategy> function1 = this.replayCaptureStrategyProvider;
            if (function1 == null || (captureStrategy = function1.invoke(Boolean.valueOf(sample))) == null) {
                if (sample) {
                    SentryOptions sentryOptions7 = this.options;
                    if (sentryOptions7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                        sentryOptions2 = null;
                    } else {
                        sentryOptions2 = sentryOptions7;
                    }
                    IScopes iScopes = this.scopes;
                    ICurrentDateProvider iCurrentDateProvider = this.dateProvider;
                    ScheduledExecutorService replayExecutor = getReplayExecutor();
                    Intrinsics.checkNotNullExpressionValue(replayExecutor, "<get-replayExecutor>(...)");
                    bufferCaptureStrategy = new SessionCaptureStrategy(sentryOptions2, iScopes, iCurrentDateProvider, replayExecutor, this.replayCacheProvider);
                } else {
                    SentryOptions sentryOptions8 = this.options;
                    if (sentryOptions8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                        sentryOptions = null;
                    } else {
                        sentryOptions = sentryOptions8;
                    }
                    IScopes iScopes2 = this.scopes;
                    ICurrentDateProvider iCurrentDateProvider2 = this.dateProvider;
                    Random random2 = getRandom();
                    ScheduledExecutorService replayExecutor2 = getReplayExecutor();
                    Intrinsics.checkNotNullExpressionValue(replayExecutor2, "<get-replayExecutor>(...)");
                    bufferCaptureStrategy = new BufferCaptureStrategy(sentryOptions, iScopes2, iCurrentDateProvider2, random2, replayExecutor2, this.replayCacheProvider);
                }
                captureStrategy = bufferCaptureStrategy;
            }
            this.captureStrategy = captureStrategy;
            Recorder recorder = this.recorder;
            if (recorder != null) {
                recorder.start();
            }
            CaptureStrategy captureStrategy2 = this.captureStrategy;
            if (captureStrategy2 != null) {
                CaptureStrategy.DefaultImpls.start$default(captureStrategy2, 0, null, null, 7, null);
            }
            registerRootViewListeners();
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(acquire, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(acquire, th);
                throw th2;
            }
        }
    }

    @Override // io.sentry.ReplayController
    public void resume() {
        this.isManualPause.set(false);
        resumeInternal();
    }

    private final void resumeInternal() {
        IScopes iScopes;
        IScopes iScopes2;
        RateLimiter rateLimiter;
        RateLimiter rateLimiter2;
        ISentryLifecycleToken acquire = this.lifecycleLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (this.isEnabled.get() && this.lifecycle.isAllowed(ReplayState.RESUMED)) {
                if (!this.isManualPause.get()) {
                    SentryOptions sentryOptions = this.options;
                    if (sentryOptions == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                        sentryOptions = null;
                    }
                    if (sentryOptions.getConnectionStatusProvider().getConnectionStatus() != IConnectionStatusProvider.ConnectionStatus.DISCONNECTED && (((iScopes = this.scopes) == null || (rateLimiter2 = iScopes.getRateLimiter()) == null || !rateLimiter2.isActiveForCategory(DataCategory.All)) && ((iScopes2 = this.scopes) == null || (rateLimiter = iScopes2.getRateLimiter()) == null || !rateLimiter.isActiveForCategory(DataCategory.Replay)))) {
                        this.lifecycle.setCurrentState$sentry_android_replay_release(ReplayState.RESUMED);
                        CaptureStrategy captureStrategy = this.captureStrategy;
                        if (captureStrategy != null) {
                            captureStrategy.resume();
                        }
                        Recorder recorder = this.recorder;
                        if (recorder != null) {
                            recorder.resume();
                            Unit unit = Unit.INSTANCE;
                        }
                        AutoCloseableKt.closeFinally(acquire, null);
                        return;
                    }
                }
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    @Override // io.sentry.ReplayController
    public void captureReplay(Boolean isTerminating) {
        if (this.isEnabled.get() && isRecording()) {
            SentryId sentryId = SentryId.EMPTY_ID;
            CaptureStrategy captureStrategy = this.captureStrategy;
            SentryOptions sentryOptions = null;
            if (sentryId.equals(captureStrategy != null ? captureStrategy.getCurrentReplayId() : null)) {
                SentryOptions sentryOptions2 = this.options;
                if (sentryOptions2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                } else {
                    sentryOptions = sentryOptions2;
                }
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "Replay id is not set, not capturing for event", new Object[0]);
                return;
            }
            CaptureStrategy captureStrategy2 = this.captureStrategy;
            if (captureStrategy2 != null) {
                captureStrategy2.captureReplay(Intrinsics.areEqual((Object) isTerminating, (Object) true), new Function1<Date, Unit>() { // from class: io.sentry.android.replay.ReplayIntegration$captureReplay$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Date date) {
                        invoke2(date);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Date newTimestamp) {
                        CaptureStrategy captureStrategy3;
                        CaptureStrategy captureStrategy4;
                        CaptureStrategy captureStrategy5;
                        Intrinsics.checkNotNullParameter(newTimestamp, "newTimestamp");
                        captureStrategy3 = ReplayIntegration.this.captureStrategy;
                        if (captureStrategy3 != null) {
                            captureStrategy5 = ReplayIntegration.this.captureStrategy;
                            Integer valueOf = captureStrategy5 != null ? Integer.valueOf(captureStrategy5.getCurrentSegment()) : null;
                            Intrinsics.checkNotNull(valueOf);
                            captureStrategy3.setCurrentSegment(valueOf.intValue() + 1);
                        }
                        captureStrategy4 = ReplayIntegration.this.captureStrategy;
                        if (captureStrategy4 == null) {
                            return;
                        }
                        captureStrategy4.setSegmentTimestamp(newTimestamp);
                    }
                });
            }
            CaptureStrategy captureStrategy3 = this.captureStrategy;
            this.captureStrategy = captureStrategy3 != null ? captureStrategy3.convert() : null;
        }
    }

    @Override // io.sentry.ReplayController
    public SentryId getReplayId() {
        SentryId currentReplayId;
        CaptureStrategy captureStrategy = this.captureStrategy;
        if (captureStrategy != null && (currentReplayId = captureStrategy.getCurrentReplayId()) != null) {
            return currentReplayId;
        }
        SentryId EMPTY_ID = SentryId.EMPTY_ID;
        Intrinsics.checkNotNullExpressionValue(EMPTY_ID, "EMPTY_ID");
        return EMPTY_ID;
    }

    @Override // io.sentry.ReplayController
    public void setBreadcrumbConverter(ReplayBreadcrumbConverter converter) {
        Intrinsics.checkNotNullParameter(converter, "converter");
        this.replayBreadcrumbConverter = converter;
    }

    @Override // io.sentry.ReplayController
    /* renamed from: getBreadcrumbConverter, reason: from getter */
    public ReplayBreadcrumbConverter getReplayBreadcrumbConverter() {
        return this.replayBreadcrumbConverter;
    }

    @Override // io.sentry.ReplayController
    public void pause() {
        this.isManualPause.set(true);
        pauseInternal();
    }

    @Override // io.sentry.IReplayApi
    public void enableDebugMaskingOverlay() {
        this.debugMaskingEnabled = true;
    }

    @Override // io.sentry.IReplayApi
    public void disableDebugMaskingOverlay() {
        this.debugMaskingEnabled = false;
    }

    @Override // io.sentry.ReplayController
    /* renamed from: isDebugMaskingOverlayEnabled, reason: from getter */
    public boolean getDebugMaskingEnabled() {
        return this.debugMaskingEnabled;
    }

    private final void pauseInternal() {
        ISentryLifecycleToken acquire = this.lifecycleLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (this.isEnabled.get() && this.lifecycle.isAllowed(ReplayState.PAUSED)) {
                Recorder recorder = this.recorder;
                if (recorder != null) {
                    recorder.pause();
                }
                CaptureStrategy captureStrategy = this.captureStrategy;
                if (captureStrategy != null) {
                    captureStrategy.pause();
                }
                this.lifecycle.setCurrentState$sentry_android_replay_release(ReplayState.PAUSED);
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    @Override // io.sentry.ReplayController
    public void stop() {
        ISentryLifecycleToken acquire = this.lifecycleLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (this.isEnabled.get() && this.lifecycle.isAllowed(ReplayState.STOPPED)) {
                unregisterRootViewListeners();
                Recorder recorder = this.recorder;
                if (recorder != null) {
                    recorder.reset();
                }
                Recorder recorder2 = this.recorder;
                if (recorder2 != null) {
                    recorder2.stop();
                }
                GestureRecorder gestureRecorder = this.gestureRecorder;
                if (gestureRecorder != null) {
                    gestureRecorder.stop();
                }
                CaptureStrategy captureStrategy = this.captureStrategy;
                if (captureStrategy != null) {
                    captureStrategy.stop();
                }
                this.captureStrategy = null;
                this.lifecycle.setCurrentState$sentry_android_replay_release(ReplayState.STOPPED);
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    @Override // io.sentry.android.replay.ScreenshotRecorderCallback
    public void onScreenshotRecorded(final Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        IScopes iScopes = this.scopes;
        if (iScopes != null) {
            iScopes.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.ReplayIntegration$$ExternalSyntheticLambda1
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    ReplayIntegration.onScreenshotRecorded$lambda$4(Ref.ObjectRef.this, iScope);
                }
            });
        }
        CaptureStrategy captureStrategy = this.captureStrategy;
        if (captureStrategy != null) {
            captureStrategy.onScreenshotRecorded(bitmap, new Function2<ReplayCache, Long, Unit>() { // from class: io.sentry.android.replay.ReplayIntegration$onScreenshotRecorded$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ReplayCache replayCache, Long l) {
                    invoke(replayCache, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ReplayCache onScreenshotRecorded, long j) {
                    Intrinsics.checkNotNullParameter(onScreenshotRecorded, "$this$onScreenshotRecorded");
                    onScreenshotRecorded.addFrame$sentry_android_replay_release(bitmap, j, objectRef.element);
                    this.checkCanRecord();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onScreenshotRecorded$lambda$4(Ref.ObjectRef objectRef, IScope it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String screen = it.getScreen();
        objectRef.element = screen != null ? StringsKt.substringAfterLast$default(screen, ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null) : 0;
    }

    @Override // io.sentry.android.replay.ScreenshotRecorderCallback
    public void onScreenshotRecorded(final File screenshot, final long frameTimestamp) {
        Intrinsics.checkNotNullParameter(screenshot, "screenshot");
        CaptureStrategy captureStrategy = this.captureStrategy;
        if (captureStrategy != null) {
            CaptureStrategy.DefaultImpls.onScreenshotRecorded$default(captureStrategy, null, new Function2<ReplayCache, Long, Unit>() { // from class: io.sentry.android.replay.ReplayIntegration$onScreenshotRecorded$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ReplayCache replayCache, Long l) {
                    invoke(replayCache, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ReplayCache onScreenshotRecorded, long j) {
                    Intrinsics.checkNotNullParameter(onScreenshotRecorded, "$this$onScreenshotRecorded");
                    ReplayCache.addFrame$default(onScreenshotRecorded, screenshot, frameTimestamp, null, 4, null);
                    this.checkCanRecord();
                }
            }, 1, null);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        RateLimiter rateLimiter;
        ISentryLifecycleToken acquire = this.lifecycleLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (this.isEnabled.get() && this.lifecycle.isAllowed(ReplayState.CLOSED)) {
                SentryOptions sentryOptions = this.options;
                if (sentryOptions == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions = null;
                }
                sentryOptions.getConnectionStatusProvider().removeConnectionStatusObserver(this);
                IScopes iScopes = this.scopes;
                if (iScopes != null && (rateLimiter = iScopes.getRateLimiter()) != null) {
                    rateLimiter.removeRateLimitObserver(this);
                }
                stop();
                Recorder recorder = this.recorder;
                if (recorder != null) {
                    recorder.close();
                }
                this.recorder = null;
                getRootViewsSpy$sentry_android_replay_release().close();
                ScheduledExecutorService replayExecutor = getReplayExecutor();
                Intrinsics.checkNotNullExpressionValue(replayExecutor, "<get-replayExecutor>(...)");
                ScheduledExecutorService scheduledExecutorService = replayExecutor;
                SentryOptions sentryOptions2 = this.options;
                if (sentryOptions2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions2 = null;
                }
                ExecutorsKt.gracefullyShutdown(scheduledExecutorService, sentryOptions2);
                this.lifecycle.setCurrentState$sentry_android_replay_release(ReplayState.CLOSED);
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    @Override // io.sentry.IConnectionStatusProvider.IConnectionStatusObserver
    public void onConnectionStatusChanged(IConnectionStatusProvider.ConnectionStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        if (this.captureStrategy instanceof SessionCaptureStrategy) {
            if (status == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED) {
                pauseInternal();
            } else {
                resumeInternal();
            }
        }
    }

    @Override // io.sentry.transport.RateLimiter.IRateLimitObserver
    public void onRateLimitChanged(RateLimiter rateLimiter) {
        Intrinsics.checkNotNullParameter(rateLimiter, "rateLimiter");
        if (this.captureStrategy instanceof SessionCaptureStrategy) {
            if (rateLimiter.isActiveForCategory(DataCategory.All) || rateLimiter.isActiveForCategory(DataCategory.Replay)) {
                pauseInternal();
            } else {
                resumeInternal();
            }
        }
    }

    @Override // io.sentry.android.replay.gestures.TouchRecorderCallback
    public void onTouchEvent(MotionEvent event) {
        CaptureStrategy captureStrategy;
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.isEnabled.get() && this.lifecycle.isTouchRecordingAllowed() && (captureStrategy = this.captureStrategy) != null) {
            captureStrategy.onTouchEvent(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkCanRecord() {
        IScopes iScopes;
        IScopes iScopes2;
        RateLimiter rateLimiter;
        RateLimiter rateLimiter2;
        if (this.captureStrategy instanceof SessionCaptureStrategy) {
            SentryOptions sentryOptions = this.options;
            if (sentryOptions == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                sentryOptions = null;
            }
            if (sentryOptions.getConnectionStatusProvider().getConnectionStatus() == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED || !(((iScopes = this.scopes) == null || (rateLimiter2 = iScopes.getRateLimiter()) == null || !rateLimiter2.isActiveForCategory(DataCategory.All)) && ((iScopes2 = this.scopes) == null || (rateLimiter = iScopes2.getRateLimiter()) == null || !rateLimiter.isActiveForCategory(DataCategory.Replay)))) {
                pauseInternal();
            }
        }
    }

    private final void registerRootViewListeners() {
        if (this.recorder instanceof OnRootViewsChangedListener) {
            CopyOnWriteArrayList<OnRootViewsChangedListener> listeners = getRootViewsSpy$sentry_android_replay_release().getListeners();
            Recorder recorder = this.recorder;
            Intrinsics.checkNotNull(recorder, "null cannot be cast to non-null type io.sentry.android.replay.OnRootViewsChangedListener");
            listeners.add((OnRootViewsChangedListener) recorder);
        }
        getRootViewsSpy$sentry_android_replay_release().getListeners().add(this.gestureRecorder);
    }

    private final void unregisterRootViewListeners() {
        if (this.recorder instanceof OnRootViewsChangedListener) {
            CopyOnWriteArrayList<OnRootViewsChangedListener> listeners = getRootViewsSpy$sentry_android_replay_release().getListeners();
            Recorder recorder = this.recorder;
            Intrinsics.checkNotNull(recorder, "null cannot be cast to non-null type io.sentry.android.replay.OnRootViewsChangedListener");
            listeners.remove((OnRootViewsChangedListener) recorder);
        }
        getRootViewsSpy$sentry_android_replay_release().getListeners().remove(this.gestureRecorder);
    }

    static /* synthetic */ void cleanupReplays$default(ReplayIntegration replayIntegration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        replayIntegration.cleanupReplays(str);
    }

    private final void cleanupReplays(String unfinishedReplayId) {
        File[] listFiles;
        SentryOptions sentryOptions = this.options;
        if (sentryOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
            sentryOptions = null;
        }
        String cacheDirPath = sentryOptions.getCacheDirPath();
        if (cacheDirPath == null || (listFiles = new File(cacheDirPath).listFiles()) == null) {
            return;
        }
        Intrinsics.checkNotNull(listFiles);
        for (File file : listFiles) {
            String name = file.getName();
            Intrinsics.checkNotNull(name);
            if (StringsKt.startsWith$default(name, "replay_", false, 2, (Object) null)) {
                String str = name;
                String sentryId = getReplayId().toString();
                Intrinsics.checkNotNullExpressionValue(sentryId, "toString(...)");
                if (!StringsKt.contains$default((CharSequence) str, (CharSequence) sentryId, false, 2, (Object) null)) {
                    String str2 = unfinishedReplayId;
                    if (StringsKt.isBlank(str2) || !StringsKt.contains$default((CharSequence) str, (CharSequence) str2, false, 2, (Object) null)) {
                        FileUtils.deleteRecursively(file);
                    }
                }
            }
        }
    }

    private final void finalizePreviousReplay() {
        SentryOptions sentryOptions = this.options;
        SentryOptions sentryOptions2 = null;
        if (sentryOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
            sentryOptions = null;
        }
        ISentryExecutorService executorService = sentryOptions.getExecutorService();
        Intrinsics.checkNotNullExpressionValue(executorService, "getExecutorService(...)");
        SentryOptions sentryOptions3 = this.options;
        if (sentryOptions3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
        } else {
            sentryOptions2 = sentryOptions3;
        }
        ExecutorsKt.submitSafely(executorService, sentryOptions2, "ReplayIntegration.finalize_previous_replay", new Runnable() { // from class: io.sentry.android.replay.ReplayIntegration$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReplayIntegration.finalizePreviousReplay$lambda$10(ReplayIntegration.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finalizePreviousReplay$lambda$10(ReplayIntegration replayIntegration) {
        SentryOptions sentryOptions;
        SentryOptions sentryOptions2 = replayIntegration.options;
        if (sentryOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
            sentryOptions2 = null;
        }
        PersistingScopeObserver findPersistingScopeObserver = sentryOptions2.findPersistingScopeObserver();
        if (findPersistingScopeObserver != null) {
            SentryOptions sentryOptions3 = replayIntegration.options;
            if (sentryOptions3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                sentryOptions3 = null;
            }
            String str = (String) findPersistingScopeObserver.read(sentryOptions3, PersistingScopeObserver.REPLAY_FILENAME, String.class);
            if (str != null) {
                SentryId sentryId = new SentryId(str);
                if (Intrinsics.areEqual(sentryId, SentryId.EMPTY_ID)) {
                    cleanupReplays$default(replayIntegration, null, 1, null);
                    return;
                }
                ReplayCache.Companion companion = ReplayCache.INSTANCE;
                SentryOptions sentryOptions4 = replayIntegration.options;
                if (sentryOptions4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions4 = null;
                }
                LastSegmentData fromDisk$sentry_android_replay_release = companion.fromDisk$sentry_android_replay_release(sentryOptions4, sentryId, replayIntegration.replayCacheProvider);
                if (fromDisk$sentry_android_replay_release == null) {
                    cleanupReplays$default(replayIntegration, null, 1, null);
                    return;
                }
                SentryOptions sentryOptions5 = replayIntegration.options;
                if (sentryOptions5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions5 = null;
                }
                Object read = findPersistingScopeObserver.read(sentryOptions5, PersistingScopeObserver.BREADCRUMBS_FILENAME, List.class);
                List<Breadcrumb> list = read instanceof List ? (List) read : null;
                CaptureStrategy.Companion companion2 = CaptureStrategy.INSTANCE;
                IScopes iScopes = replayIntegration.scopes;
                SentryOptions sentryOptions6 = replayIntegration.options;
                if (sentryOptions6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                    sentryOptions = null;
                } else {
                    sentryOptions = sentryOptions6;
                }
                CaptureStrategy.ReplaySegment createSegment = companion2.createSegment(iScopes, sentryOptions, fromDisk$sentry_android_replay_release.getDuration(), fromDisk$sentry_android_replay_release.getTimestamp(), sentryId, fromDisk$sentry_android_replay_release.getId(), fromDisk$sentry_android_replay_release.getRecorderConfig().getRecordingHeight(), fromDisk$sentry_android_replay_release.getRecorderConfig().getRecordingWidth(), fromDisk$sentry_android_replay_release.getReplayType(), fromDisk$sentry_android_replay_release.getCache(), fromDisk$sentry_android_replay_release.getRecorderConfig().getFrameRate(), fromDisk$sentry_android_replay_release.getRecorderConfig().getBitRate(), fromDisk$sentry_android_replay_release.getScreenAtStart(), list, new LinkedList(fromDisk$sentry_android_replay_release.getEvents()));
                if (createSegment instanceof CaptureStrategy.ReplaySegment.Created) {
                    Hint createWithTypeCheckHint = HintUtils.createWithTypeCheckHint(new PreviousReplayHint());
                    IScopes iScopes2 = replayIntegration.scopes;
                    Intrinsics.checkNotNull(createWithTypeCheckHint);
                    ((CaptureStrategy.ReplaySegment.Created) createSegment).capture(iScopes2, createWithTypeCheckHint);
                }
                replayIntegration.cleanupReplays(str);
                return;
            }
        }
        cleanupReplays$default(replayIntegration, null, 1, null);
    }

    @Override // io.sentry.android.replay.WindowCallback
    public void onWindowSizeChanged(int width, int height) {
        if (this.isEnabled.get() && isRecording()) {
            SentryOptions sentryOptions = this.options;
            SentryOptions sentryOptions2 = null;
            if (sentryOptions == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                sentryOptions = null;
            }
            if (sentryOptions.getSessionReplay().isTrackConfiguration()) {
                ScreenshotRecorderConfig.Companion companion = ScreenshotRecorderConfig.INSTANCE;
                Context context = this.context;
                SentryOptions sentryOptions3 = this.options;
                if (sentryOptions3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(RRWebOptionsEvent.EVENT_TAG);
                } else {
                    sentryOptions2 = sentryOptions3;
                }
                SentryReplayOptions sessionReplay = sentryOptions2.getSessionReplay();
                Intrinsics.checkNotNullExpressionValue(sessionReplay, "getSessionReplay(...)");
                onConfigurationChanged(companion.fromSize(context, sessionReplay, width, height));
            }
        }
    }

    public final void onConfigurationChanged(ScreenshotRecorderConfig config) {
        Recorder recorder;
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.isEnabled.get() && isRecording()) {
            CaptureStrategy captureStrategy = this.captureStrategy;
            if (captureStrategy != null) {
                captureStrategy.onConfigurationChanged(config);
            }
            Recorder recorder2 = this.recorder;
            if (recorder2 != null) {
                recorder2.onConfigurationChanged(config);
            }
            if (this.lifecycle.getCurrentState() != ReplayState.PAUSED || (recorder = this.recorder) == null) {
                return;
            }
            recorder.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReplayIntegration.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration$ReplayExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class ReplayExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryReplayIntegration-");
            int i = this.cnt;
            this.cnt = i + 1;
            sb.append(i);
            Thread thread = new Thread(r, sb.toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}
