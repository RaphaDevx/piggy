package io.sentry.android.replay.capture;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.salesforce.marketingcloud.UrlHandler;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IScope;
import io.sentry.IScopes;
import io.sentry.ReplayRecording;
import io.sentry.ScopeCallback;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.GeneratedVideo;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: CaptureStrategy.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b`\u0018\u0000 ?2\u00020\u0001:\u0002?@J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0#H&J\b\u0010$\u001a\u00020\u0000H&J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'H&J\u0012\u0010(\u001a\u00020\u001f2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016JB\u0010+\u001a\u00020\u001f2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2,\u0010.\u001a(\u0012\u0004\u0012\u000200\u0012\u0013\u0012\u001101¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u001f0/¢\u0006\u0002\b5H&J\u0010\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u000208H&J\b\u00109\u001a\u00020\u001fH&J\b\u0010:\u001a\u00020\u001fH&J(\u0010;\u001a\u00020\u001f2\b\b\u0002\u0010<\u001a\u00020\t2\b\b\u0002\u0010=\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J\b\u0010>\u001a\u00020\u001fH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u0013X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006A"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy;", "", "currentReplayId", "Lio/sentry/protocol/SentryId;", "getCurrentReplayId", "()Lio/sentry/protocol/SentryId;", "setCurrentReplayId", "(Lio/sentry/protocol/SentryId;)V", "currentSegment", "", "getCurrentSegment", "()I", "setCurrentSegment", "(I)V", "replayCacheDir", "Ljava/io/File;", "getReplayCacheDir", "()Ljava/io/File;", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "getReplayType", "()Lio/sentry/SentryReplayEvent$ReplayType;", "setReplayType", "(Lio/sentry/SentryReplayEvent$ReplayType;)V", "segmentTimestamp", "Ljava/util/Date;", "getSegmentTimestamp", "()Ljava/util/Date;", "setSegmentTimestamp", "(Ljava/util/Date;)V", "captureReplay", "", "isTerminating", "", "onSegmentSent", "Lkotlin/Function1;", "convert", "onConfigurationChanged", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "onScreenChanged", "screen", "", "onScreenshotRecorded", "bitmap", "Landroid/graphics/Bitmap;", "store", "Lkotlin/Function2;", "Lio/sentry/android/replay/ReplayCache;", "", "Lkotlin/ParameterName;", "name", "frameTimestamp", "Lkotlin/ExtensionFunctionType;", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "pause", "resume", "start", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "replayId", "stop", "Companion", "ReplaySegment", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface CaptureStrategy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void captureReplay(boolean isTerminating, Function1<? super Date, Unit> onSegmentSent);

    CaptureStrategy convert();

    SentryId getCurrentReplayId();

    int getCurrentSegment();

    File getReplayCacheDir();

    SentryReplayEvent.ReplayType getReplayType();

    Date getSegmentTimestamp();

    void onConfigurationChanged(ScreenshotRecorderConfig recorderConfig);

    void onScreenChanged(String screen);

    void onScreenshotRecorded(Bitmap bitmap, Function2<? super ReplayCache, ? super Long, Unit> store);

    void onTouchEvent(MotionEvent event);

    void pause();

    void resume();

    void setCurrentReplayId(SentryId sentryId);

    void setCurrentSegment(int i);

    void setReplayType(SentryReplayEvent.ReplayType replayType);

    void setSegmentTimestamp(Date date);

    void start(int segmentId, SentryId replayId, SentryReplayEvent.ReplayType replayType);

    void stop();

    /* compiled from: CaptureStrategy.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static void onScreenChanged(CaptureStrategy captureStrategy, String str) {
        }

        public static /* synthetic */ void start$default(CaptureStrategy captureStrategy, int i, SentryId sentryId, SentryReplayEvent.ReplayType replayType, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: start");
            }
            if ((i2 & 1) != 0) {
                i = 0;
            }
            if ((i2 & 2) != 0) {
                sentryId = new SentryId();
            }
            if ((i2 & 4) != 0) {
                replayType = null;
            }
            captureStrategy.start(i, sentryId, replayType);
        }

        public static /* synthetic */ void onScreenshotRecorded$default(CaptureStrategy captureStrategy, Bitmap bitmap, Function2 function2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onScreenshotRecorded");
            }
            if ((i & 1) != 0) {
                bitmap = null;
            }
            captureStrategy.onScreenshotRecorded(bitmap, function2);
        }
    }

    /* compiled from: CaptureStrategy.kt */
    @Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0086\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\u0092\u0001\u0010!\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fJ;\u0010*\u001a\u00020+2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010,\u001a\u00020\u00042\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020+\u0018\u00010.H\u0000¢\u0006\u0002\b/J\u000e\u00100\u001a\u000201*\u0004\u0018\u00010\u001dH\u0002J\f\u00102\u001a\u000201*\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$Companion;", "", "()V", "MAX_SEGMENT_DURATION", "", "NETWORK_BREADCRUMB_START_OFFSET", "buildReplay", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", RRWebVideoEvent.EVENT_TAG, "Ljava/io/File;", "currentReplayId", "Lio/sentry/protocol/SentryId;", "segmentTimestamp", "Ljava/util/Date;", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "height", "width", RRWebVideoEvent.JsonKeys.FRAME_COUNT, RRWebVideoEvent.JsonKeys.FRAME_RATE, "videoDuration", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "screenAtStart", "", SentryBaseEvent.JsonKeys.BREADCRUMBS, "", "Lio/sentry/Breadcrumb;", "events", "Ljava/util/Deque;", "Lio/sentry/rrweb/RRWebEvent;", "createSegment", "scopes", "Lio/sentry/IScopes;", "duration", "currentSegmentTimestamp", "replayId", "cache", "Lio/sentry/android/replay/ReplayCache;", "bitRate", "rotateEvents", "", "until", PluginMethod.RETURN_CALLBACK, "Lkotlin/Function1;", "rotateEvents$sentry_android_replay_release", "isNetworkAvailable", "", "isNetworkConnectivity", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final long MAX_SEGMENT_DURATION = 300000;
        private static final long NETWORK_BREADCRUMB_START_OFFSET = 5000;

        private Companion() {
        }

        private final boolean isNetworkAvailable(Breadcrumb breadcrumb) {
            if (breadcrumb != null && Intrinsics.areEqual(breadcrumb.getCategory(), "network.event")) {
                Map<String, Object> data = breadcrumb.getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Object obj = data.get(UrlHandler.ACTION);
                if (obj == null) {
                    obj = null;
                }
                if (Intrinsics.areEqual(obj, "NETWORK_AVAILABLE")) {
                    return true;
                }
            }
            return false;
        }

        private final boolean isNetworkConnectivity(Breadcrumb breadcrumb) {
            return Intrinsics.areEqual(breadcrumb.getCategory(), "network.event") && breadcrumb.getData().containsKey("network_type");
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [T, java.util.List] */
        public final ReplaySegment createSegment(IScopes scopes, SentryOptions options, long duration, Date currentSegmentTimestamp, SentryId replayId, int segmentId, int height, int width, SentryReplayEvent.ReplayType replayType, ReplayCache cache, int frameRate, int bitRate, String screenAtStart, List<Breadcrumb> breadcrumbs, Deque<RRWebEvent> events) {
            GeneratedVideo createVideoOf$default;
            List<Breadcrumb> list;
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(currentSegmentTimestamp, "currentSegmentTimestamp");
            Intrinsics.checkNotNullParameter(replayId, "replayId");
            Intrinsics.checkNotNullParameter(replayType, "replayType");
            Intrinsics.checkNotNullParameter(events, "events");
            if (cache == null || (createVideoOf$default = ReplayCache.createVideoOf$default(cache, Math.min(duration, 300000L), currentSegmentTimestamp.getTime(), segmentId, height, width, frameRate, bitRate, null, 128, null)) == null) {
                return ReplaySegment.Failed.INSTANCE;
            }
            File video = createVideoOf$default.getVideo();
            int frameCount = createVideoOf$default.getFrameCount();
            long duration2 = createVideoOf$default.getDuration();
            if (breadcrumbs == null) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = CollectionsKt.emptyList();
                if (scopes != null) {
                    scopes.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.capture.CaptureStrategy$Companion$$ExternalSyntheticLambda0
                        @Override // io.sentry.ScopeCallback
                        public final void run(IScope iScope) {
                            CaptureStrategy.Companion.createSegment$lambda$1(Ref.ObjectRef.this, iScope);
                        }
                    });
                }
                list = (List) objectRef.element;
            } else {
                list = breadcrumbs;
            }
            return buildReplay(options, video, replayId, currentSegmentTimestamp, segmentId, height, width, frameCount, frameRate, duration2, replayType, screenAtStart, list, events);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.ArrayList] */
        public static final void createSegment$lambda$1(Ref.ObjectRef objectRef, IScope scope) {
            Intrinsics.checkNotNullParameter(scope, "scope");
            objectRef.element = new ArrayList(scope.getBreadcrumbs());
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x00ca, code lost:
        
            if ((r12.getTimestamp().getTime() + 5000) >= r23.getTime()) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x012b, code lost:
        
            if (r13 == null) goto L35;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final io.sentry.android.replay.capture.CaptureStrategy.ReplaySegment buildReplay(io.sentry.SentryOptions r20, java.io.File r21, io.sentry.protocol.SentryId r22, final java.util.Date r23, int r24, int r25, int r26, int r27, int r28, long r29, io.sentry.SentryReplayEvent.ReplayType r31, java.lang.String r32, java.util.List<io.sentry.Breadcrumb> r33, java.util.Deque<io.sentry.rrweb.RRWebEvent> r34) {
            /*
                Method dump skipped, instructions count: 419
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.capture.CaptureStrategy.Companion.buildReplay(io.sentry.SentryOptions, java.io.File, io.sentry.protocol.SentryId, java.util.Date, int, int, int, int, int, long, io.sentry.SentryReplayEvent$ReplayType, java.lang.String, java.util.List, java.util.Deque):io.sentry.android.replay.capture.CaptureStrategy$ReplaySegment");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void rotateEvents$sentry_android_replay_release$default(Companion companion, Deque deque, long j, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                function1 = null;
            }
            companion.rotateEvents$sentry_android_replay_release(deque, j, function1);
        }

        public final void rotateEvents$sentry_android_replay_release(Deque<RRWebEvent> events, long until, Function1<? super RRWebEvent, Unit> callback) {
            Intrinsics.checkNotNullParameter(events, "events");
            Iterator<RRWebEvent> it = events.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                RRWebEvent next = it.next();
                if (next.getTimestamp() < until) {
                    if (callback != null) {
                        Intrinsics.checkNotNull(next);
                        callback.invoke(next);
                    }
                    it.remove();
                }
            }
        }
    }

    /* compiled from: CaptureStrategy.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "", "()V", "Created", "Failed", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Created;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Failed;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static abstract class ReplaySegment {
        public static final int $stable = 0;

        public /* synthetic */ ReplaySegment(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: CaptureStrategy.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Failed;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "()V", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Failed extends ReplaySegment {
            public static final int $stable = 0;
            public static final Failed INSTANCE = new Failed();

            private Failed() {
                super(null);
            }
        }

        private ReplaySegment() {
        }

        /* compiled from: CaptureStrategy.kt */
        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0019J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Created;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "replay", "Lio/sentry/SentryReplayEvent;", "recording", "Lio/sentry/ReplayRecording;", "(Lio/sentry/SentryReplayEvent;Lio/sentry/ReplayRecording;)V", "getRecording", "()Lio/sentry/ReplayRecording;", "getReplay", "()Lio/sentry/SentryReplayEvent;", "capture", "", "scopes", "Lio/sentry/IScopes;", "hint", "Lio/sentry/Hint;", "component1", "component2", "copy", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "", "setSegmentId", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "toString", "", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final /* data */ class Created extends ReplaySegment {
            public static final int $stable = 8;
            private final ReplayRecording recording;
            private final SentryReplayEvent replay;

            public static /* synthetic */ Created copy$default(Created created, SentryReplayEvent sentryReplayEvent, ReplayRecording replayRecording, int i, Object obj) {
                if ((i & 1) != 0) {
                    sentryReplayEvent = created.replay;
                }
                if ((i & 2) != 0) {
                    replayRecording = created.recording;
                }
                return created.copy(sentryReplayEvent, replayRecording);
            }

            /* renamed from: component1, reason: from getter */
            public final SentryReplayEvent getReplay() {
                return this.replay;
            }

            /* renamed from: component2, reason: from getter */
            public final ReplayRecording getRecording() {
                return this.recording;
            }

            public final Created copy(SentryReplayEvent replay, ReplayRecording recording) {
                Intrinsics.checkNotNullParameter(replay, "replay");
                Intrinsics.checkNotNullParameter(recording, "recording");
                return new Created(replay, recording);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Created)) {
                    return false;
                }
                Created created = (Created) other;
                return Intrinsics.areEqual(this.replay, created.replay) && Intrinsics.areEqual(this.recording, created.recording);
            }

            public int hashCode() {
                return (this.replay.hashCode() * 31) + this.recording.hashCode();
            }

            public String toString() {
                return "Created(replay=" + this.replay + ", recording=" + this.recording + ')';
            }

            public final ReplayRecording getRecording() {
                return this.recording;
            }

            public final SentryReplayEvent getReplay() {
                return this.replay;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Created(SentryReplayEvent replay, ReplayRecording recording) {
                super(null);
                Intrinsics.checkNotNullParameter(replay, "replay");
                Intrinsics.checkNotNullParameter(recording, "recording");
                this.replay = replay;
                this.recording = recording;
            }

            public static /* synthetic */ void capture$default(Created created, IScopes iScopes, Hint hint, int i, Object obj) {
                if ((i & 2) != 0) {
                    hint = new Hint();
                }
                created.capture(iScopes, hint);
            }

            public final void capture(IScopes scopes, Hint hint) {
                Intrinsics.checkNotNullParameter(hint, "hint");
                if (scopes != null) {
                    SentryReplayEvent sentryReplayEvent = this.replay;
                    hint.setReplayRecording(this.recording);
                    Unit unit = Unit.INSTANCE;
                    scopes.captureReplay(sentryReplayEvent, hint);
                }
            }

            public final void setSegmentId(int segmentId) {
                this.replay.setSegmentId(segmentId);
                List<? extends RRWebEvent> payload = this.recording.getPayload();
                if (payload != null) {
                    for (RRWebEvent rRWebEvent : payload) {
                        if (rRWebEvent instanceof RRWebVideoEvent) {
                            ((RRWebVideoEvent) rRWebEvent).setSegmentId(segmentId);
                        }
                    }
                }
            }
        }
    }
}
