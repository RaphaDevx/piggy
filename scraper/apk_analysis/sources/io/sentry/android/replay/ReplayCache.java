package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.DateUtils;
import io.sentry.ISentryLifecycleToken;
import io.sentry.ReplayRecording;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.video.SimpleVideoEncoder;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.FileUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* compiled from: ReplayCache.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\r\b\u0007\u0018\u0000 E2\u00020\u0001:\u0001EB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J)\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\b(J\"\u0010!\u001a\u00020\"2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0016J\b\u0010*\u001a\u00020\"H\u0016JJ\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002002\u0006\u00103\u001a\u0002002\u0006\u00104\u001a\u0002002\b\b\u0002\u00105\u001a\u00020\u0019J\u0010\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\u0019H\u0002J\u0012\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\rH\u0002J\u0011\u0010;\u001a\u0004\u0018\u00010&H\u0000¢\u0006\u0004\b<\u0010=J\u001f\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\u00162\b\u0010@\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\bAJ\u0017\u0010B\u001a\u0004\u0018\u00010\u00162\u0006\u0010C\u001a\u00020&H\u0000¢\u0006\u0002\bDR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u00198@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b\u001f\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lio/sentry/android/replay/ReplayCache;", "Ljava/io/Closeable;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "replayId", "Lio/sentry/protocol/SentryId;", "(Lio/sentry/SentryOptions;Lio/sentry/protocol/SentryId;)V", "encoder", "Lio/sentry/android/replay/video/SimpleVideoEncoder;", "encoderLock", "Lio/sentry/util/AutoClosableReentrantLock;", "frames", "", "Lio/sentry/android/replay/ReplayFrame;", "getFrames$sentry_android_replay_release", "()Ljava/util/List;", "framesLock", "isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", SentryStackFrame.JsonKeys.LOCK, "ongoingSegment", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "ongoingSegmentFile", "Ljava/io/File;", "getOngoingSegmentFile$sentry_android_replay_release", "()Ljava/io/File;", "ongoingSegmentFile$delegate", "Lkotlin/Lazy;", "replayCacheDir", "getReplayCacheDir$sentry_android_replay_release", "replayCacheDir$delegate", "addFrame", "", "bitmap", "Landroid/graphics/Bitmap;", "frameTimestamp", "", "screen", "addFrame$sentry_android_replay_release", "screenshot", "close", "createVideoOf", "Lio/sentry/android/replay/GeneratedVideo;", "duration", "from", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "height", "width", RRWebVideoEvent.JsonKeys.FRAME_RATE, "bitRate", "videoFile", "deleteFile", "file", "encode", "", TypedValues.AttributesType.S_FRAME, "firstFrameTimestamp", "firstFrameTimestamp$sentry_android_replay_release", "()Ljava/lang/Long;", "persistSegmentValues", "key", "value", "persistSegmentValues$sentry_android_replay_release", "rotate", "until", "rotate$sentry_android_replay_release", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ReplayCache implements Closeable {
    public static final String ONGOING_SEGMENT = ".ongoing_segment";
    public static final String SEGMENT_KEY_BIT_RATE = "config.bit-rate";
    public static final String SEGMENT_KEY_FRAME_RATE = "config.frame-rate";
    public static final String SEGMENT_KEY_HEIGHT = "config.height";
    public static final String SEGMENT_KEY_ID = "segment.id";
    public static final String SEGMENT_KEY_REPLAY_ID = "replay.id";
    public static final String SEGMENT_KEY_REPLAY_RECORDING = "replay.recording";
    public static final String SEGMENT_KEY_REPLAY_SCREEN_AT_START = "replay.screen-at-start";
    public static final String SEGMENT_KEY_REPLAY_TYPE = "replay.type";
    public static final String SEGMENT_KEY_TIMESTAMP = "segment.timestamp";
    public static final String SEGMENT_KEY_WIDTH = "config.width";
    private SimpleVideoEncoder encoder;
    private final AutoClosableReentrantLock encoderLock;
    private final List<ReplayFrame> frames;
    private final AutoClosableReentrantLock framesLock;
    private final AtomicBoolean isClosed;
    private final AutoClosableReentrantLock lock;
    private final LinkedHashMap<String, String> ongoingSegment;

    /* renamed from: ongoingSegmentFile$delegate, reason: from kotlin metadata */
    private final Lazy ongoingSegmentFile;
    private final SentryOptions options;

    /* renamed from: replayCacheDir$delegate, reason: from kotlin metadata */
    private final Lazy replayCacheDir;
    private final SentryId replayId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ReplayCache(SentryOptions options, SentryId replayId) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(replayId, "replayId");
        this.options = options;
        this.replayId = replayId;
        this.isClosed = new AtomicBoolean(false);
        this.encoderLock = new AutoClosableReentrantLock();
        this.lock = new AutoClosableReentrantLock();
        this.framesLock = new AutoClosableReentrantLock();
        this.replayCacheDir = LazyKt.lazy(new Function0<File>() { // from class: io.sentry.android.replay.ReplayCache$replayCacheDir$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                SentryOptions sentryOptions;
                SentryId sentryId;
                ReplayCache.Companion companion = ReplayCache.INSTANCE;
                sentryOptions = ReplayCache.this.options;
                sentryId = ReplayCache.this.replayId;
                return companion.makeReplayCacheDir(sentryOptions, sentryId);
            }
        });
        this.frames = new ArrayList();
        this.ongoingSegment = new LinkedHashMap<>();
        this.ongoingSegmentFile = LazyKt.lazy(new Function0<File>() { // from class: io.sentry.android.replay.ReplayCache$ongoingSegmentFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                if (ReplayCache.this.getReplayCacheDir$sentry_android_replay_release() == null) {
                    return null;
                }
                File file = new File(ReplayCache.this.getReplayCacheDir$sentry_android_replay_release(), ReplayCache.ONGOING_SEGMENT);
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            }
        });
    }

    public final File getReplayCacheDir$sentry_android_replay_release() {
        return (File) this.replayCacheDir.getValue();
    }

    public final List<ReplayFrame> getFrames$sentry_android_replay_release() {
        return this.frames;
    }

    public final File getOngoingSegmentFile$sentry_android_replay_release() {
        return (File) this.ongoingSegmentFile.getValue();
    }

    public static /* synthetic */ void addFrame$sentry_android_replay_release$default(ReplayCache replayCache, Bitmap bitmap, long j, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        replayCache.addFrame$sentry_android_replay_release(bitmap, j, str);
    }

    public final void addFrame$sentry_android_replay_release(Bitmap bitmap, long frameTimestamp, String screen) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (getReplayCacheDir$sentry_android_replay_release() == null || bitmap.isRecycled()) {
            return;
        }
        File replayCacheDir$sentry_android_replay_release = getReplayCacheDir$sentry_android_replay_release();
        if (replayCacheDir$sentry_android_replay_release != null) {
            replayCacheDir$sentry_android_replay_release.mkdirs();
        }
        File file = new File(getReplayCacheDir$sentry_android_replay_release(), frameTimestamp + ".jpg");
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            bitmap.compress(Bitmap.CompressFormat.JPEG, this.options.getSessionReplay().getQuality().screenshotQuality, fileOutputStream2);
            fileOutputStream2.flush();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            addFrame(file, frameTimestamp, screen);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void addFrame$default(ReplayCache replayCache, File file, long j, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        replayCache.addFrame(file, j, str);
    }

    public final void addFrame(File screenshot, long frameTimestamp, String screen) {
        Intrinsics.checkNotNullParameter(screenshot, "screenshot");
        ReplayFrame replayFrame = new ReplayFrame(screenshot, frameTimestamp, screen);
        ISentryLifecycleToken acquire = this.framesLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            this.frames.add(replayFrame);
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    public final Long firstFrameTimestamp$sentry_android_replay_release() {
        ISentryLifecycleToken acquire = this.framesLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            ReplayFrame replayFrame = (ReplayFrame) CollectionsKt.firstOrNull((List) this.frames);
            Long valueOf = replayFrame != null ? Long.valueOf(replayFrame.getTimestamp()) : null;
            AutoCloseableKt.closeFinally(acquire, null);
            return valueOf;
        } finally {
        }
    }

    public static /* synthetic */ GeneratedVideo createVideoOf$default(ReplayCache replayCache, long j, long j2, int i, int i2, int i3, int i4, int i5, File file, int i6, Object obj) {
        File file2;
        if ((i6 & 128) != 0) {
            file2 = new File(replayCache.getReplayCacheDir$sentry_android_replay_release(), i + ".mp4");
        } else {
            file2 = file;
        }
        return replayCache.createVideoOf(j, j2, i, i2, i3, i4, i5, file2);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x014c A[LOOP:0: B:27:0x00dd->B:42:0x014c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0151 A[EDGE_INSN: B:43:0x0151->B:44:0x0151 BREAK  A[LOOP:0: B:27:0x00dd->B:42:0x014c], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.android.replay.GeneratedVideo createVideoOf(long r26, long r28, int r30, int r31, int r32, int r33, int r34, java.io.File r35) {
        /*
            Method dump skipped, instructions count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.ReplayCache.createVideoOf(long, long, int, int, int, int, int, java.io.File):io.sentry.android.replay.GeneratedVideo");
    }

    private final boolean encode(ReplayFrame frame) {
        if (frame == null) {
            return false;
        }
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(frame.getScreenshot().getAbsolutePath());
            ISentryLifecycleToken acquire = this.encoderLock.acquire();
            try {
                ISentryLifecycleToken iSentryLifecycleToken = acquire;
                SimpleVideoEncoder simpleVideoEncoder = this.encoder;
                if (simpleVideoEncoder != null) {
                    Intrinsics.checkNotNull(decodeFile);
                    simpleVideoEncoder.encode(decodeFile);
                    Unit unit = Unit.INSTANCE;
                }
                AutoCloseableKt.closeFinally(acquire, null);
                decodeFile.recycle();
                return true;
            } finally {
            }
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.WARNING, "Unable to decode bitmap and encode it into a video, skipping frame", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFile(File file) {
        try {
            if (file.delete()) {
                return;
            }
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete replay frame: %s", file.getAbsolutePath());
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to delete replay frame: %s", file.getAbsolutePath());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String rotate$sentry_android_replay_release(final long until) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ISentryLifecycleToken acquire = this.framesLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            CollectionsKt.removeAll((List) this.frames, (Function1) new Function1<ReplayFrame, Boolean>() { // from class: io.sentry.android.replay.ReplayCache$rotate$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ReplayFrame it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it.getTimestamp() < until) {
                        this.deleteFile(it.getScreenshot());
                        return true;
                    }
                    if (objectRef.element == null) {
                        objectRef.element = it.getScreen();
                    }
                    return false;
                }
            });
            AutoCloseableKt.closeFinally(acquire, null);
            return (String) objectRef.element;
        } finally {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ISentryLifecycleToken acquire = this.encoderLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            SimpleVideoEncoder simpleVideoEncoder = this.encoder;
            if (simpleVideoEncoder != null) {
                simpleVideoEncoder.release();
            }
            this.encoder = null;
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(acquire, null);
            this.isClosed.set(true);
        } finally {
        }
    }

    public final void persistSegmentValues$sentry_android_replay_release(String key, String value) {
        File ongoingSegmentFile$sentry_android_replay_release;
        File ongoingSegmentFile$sentry_android_replay_release2;
        Intrinsics.checkNotNullParameter(key, "key");
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (this.isClosed.get()) {
                AutoCloseableKt.closeFinally(acquire, null);
                return;
            }
            File ongoingSegmentFile$sentry_android_replay_release3 = getOngoingSegmentFile$sentry_android_replay_release();
            if ((ongoingSegmentFile$sentry_android_replay_release3 == null || !ongoingSegmentFile$sentry_android_replay_release3.exists()) && (ongoingSegmentFile$sentry_android_replay_release = getOngoingSegmentFile$sentry_android_replay_release()) != null) {
                ongoingSegmentFile$sentry_android_replay_release.createNewFile();
            }
            if (this.ongoingSegment.isEmpty() && (ongoingSegmentFile$sentry_android_replay_release2 = getOngoingSegmentFile$sentry_android_replay_release()) != null) {
                Reader inputStreamReader = new InputStreamReader(new FileInputStream(ongoingSegmentFile$sentry_android_replay_release2), Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    Sequence<String> lineSequence = TextStreamsKt.lineSequence(bufferedReader);
                    LinkedHashMap<String, String> linkedHashMap = this.ongoingSegment;
                    Iterator<String> it = lineSequence.iterator();
                    while (it.hasNext()) {
                        List split$default = StringsKt.split$default((CharSequence) it.next(), new String[]{"="}, false, 2, 2, (Object) null);
                        Pair pair = TuplesKt.to((String) split$default.get(0), (String) split$default.get(1));
                        linkedHashMap.put(pair.getFirst(), pair.getSecond());
                    }
                    LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
                    CloseableKt.closeFinally(bufferedReader, null);
                } finally {
                }
            }
            if (value == null) {
                this.ongoingSegment.remove(key);
            } else {
                this.ongoingSegment.put(key, value);
            }
            File ongoingSegmentFile$sentry_android_replay_release4 = getOngoingSegmentFile$sentry_android_replay_release();
            if (ongoingSegmentFile$sentry_android_replay_release4 != null) {
                Set<Map.Entry<String, String>> entrySet = this.ongoingSegment.entrySet();
                Intrinsics.checkNotNullExpressionValue(entrySet, "<get-entries>(...)");
                FilesKt.writeText$default(ongoingSegmentFile$sentry_android_replay_release4, CollectionsKt.joinToString$default(entrySet, StringUtils.LF, null, null, 0, null, new Function1<Map.Entry<String, String>, CharSequence>() { // from class: io.sentry.android.replay.ReplayCache$persistSegmentValues$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(Map.Entry<String, String> entry) {
                        Intrinsics.checkNotNullParameter(entry, "<name for destructuring parameter 0>");
                        return entry.getKey() + '=' + entry.getValue();
                    }
                }, 30, null), null, 2, null);
                Unit unit = Unit.INSTANCE;
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    /* compiled from: ReplayCache.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JF\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u001aJ\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lio/sentry/android/replay/ReplayCache$Companion;", "", "()V", "ONGOING_SEGMENT", "", "SEGMENT_KEY_BIT_RATE", "SEGMENT_KEY_FRAME_RATE", "SEGMENT_KEY_HEIGHT", "SEGMENT_KEY_ID", "SEGMENT_KEY_REPLAY_ID", "SEGMENT_KEY_REPLAY_RECORDING", "SEGMENT_KEY_REPLAY_SCREEN_AT_START", "SEGMENT_KEY_REPLAY_TYPE", "SEGMENT_KEY_TIMESTAMP", "SEGMENT_KEY_WIDTH", "fromDisk", "Lio/sentry/android/replay/LastSegmentData;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "replayId", "Lio/sentry/protocol/SentryId;", "replayCacheProvider", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lio/sentry/android/replay/ReplayCache;", "fromDisk$sentry_android_replay_release", "makeReplayCacheDir", "Ljava/io/File;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File makeReplayCacheDir(SentryOptions options, SentryId replayId) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(replayId, "replayId");
            String cacheDirPath = options.getCacheDirPath();
            if (cacheDirPath == null || cacheDirPath.length() == 0) {
                options.getLogger().log(SentryLevel.WARNING, "SentryOptions.cacheDirPath is not set, session replay is no-op", new Object[0]);
                return null;
            }
            String cacheDirPath2 = options.getCacheDirPath();
            Intrinsics.checkNotNull(cacheDirPath2);
            File file = new File(cacheDirPath2, "replay_" + replayId);
            file.mkdirs();
            return file;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LastSegmentData fromDisk$sentry_android_replay_release$default(Companion companion, SentryOptions sentryOptions, SentryId sentryId, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                function1 = null;
            }
            return companion.fromDisk$sentry_android_replay_release(sentryOptions, sentryId, function1);
        }

        public final LastSegmentData fromDisk$sentry_android_replay_release(SentryOptions options, SentryId replayId, Function1<? super SentryId, ReplayCache> replayCacheProvider) {
            Date date;
            SentryReplayEvent.ReplayType replayType;
            final ReplayCache replayCache;
            LinkedList emptyList;
            String str = "";
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(replayId, "replayId");
            File makeReplayCacheDir = makeReplayCacheDir(options, replayId);
            File file = new File(makeReplayCacheDir, ReplayCache.ONGOING_SEGMENT);
            LinkedList linkedList = null;
            if (!file.exists()) {
                options.getLogger().log(SentryLevel.DEBUG, "No ongoing segment found for replay: %s", replayId);
                FileUtils.deleteRecursively(makeReplayCacheDir);
                return null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                LinkedHashMap linkedHashMap2 = linkedHashMap;
                Iterator<String> it = TextStreamsKt.lineSequence(bufferedReader).iterator();
                while (it.hasNext()) {
                    List split$default = StringsKt.split$default((CharSequence) it.next(), new String[]{"="}, false, 2, 2, (Object) null);
                    Pair pair = TuplesKt.to((String) split$default.get(0), (String) split$default.get(1));
                    linkedHashMap2.put(pair.getFirst(), pair.getSecond());
                }
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                CloseableKt.closeFinally(bufferedReader, null);
                String str2 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_HEIGHT);
                Integer intOrNull = str2 != null ? StringsKt.toIntOrNull(str2) : null;
                String str3 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_WIDTH);
                Integer intOrNull2 = str3 != null ? StringsKt.toIntOrNull(str3) : null;
                String str4 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_FRAME_RATE);
                Integer intOrNull3 = str4 != null ? StringsKt.toIntOrNull(str4) : null;
                String str5 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_BIT_RATE);
                Integer intOrNull4 = str5 != null ? StringsKt.toIntOrNull(str5) : null;
                String str6 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_ID);
                Integer intOrNull5 = str6 != null ? StringsKt.toIntOrNull(str6) : null;
                try {
                    String str7 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_TIMESTAMP);
                    if (str7 == null) {
                        str7 = "";
                    }
                    date = DateUtils.getDateTime(str7);
                } catch (Throwable unused) {
                    date = null;
                }
                try {
                    String str8 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_REPLAY_TYPE);
                    if (str8 != null) {
                        str = str8;
                    }
                    replayType = SentryReplayEvent.ReplayType.valueOf(str);
                } catch (Throwable unused2) {
                    replayType = null;
                }
                if (intOrNull == null || intOrNull2 == null || intOrNull3 == null || intOrNull4 == null || intOrNull5 == null || intOrNull5.intValue() == -1 || date == null || replayType == null) {
                    options.getLogger().log(SentryLevel.DEBUG, "Incorrect segment values found for replay: %s, deleting the replay", replayId);
                    FileUtils.deleteRecursively(makeReplayCacheDir);
                    return null;
                }
                ScreenshotRecorderConfig screenshotRecorderConfig = new ScreenshotRecorderConfig(intOrNull2.intValue(), intOrNull.intValue(), 1.0f, 1.0f, intOrNull3.intValue(), intOrNull4.intValue());
                if (replayCacheProvider == null || (replayCache = replayCacheProvider.invoke(replayId)) == null) {
                    replayCache = new ReplayCache(options, replayId);
                }
                File replayCacheDir$sentry_android_replay_release = replayCache.getReplayCacheDir$sentry_android_replay_release();
                if (replayCacheDir$sentry_android_replay_release != null) {
                    replayCacheDir$sentry_android_replay_release.listFiles(new FilenameFilter() { // from class: io.sentry.android.replay.ReplayCache$Companion$$ExternalSyntheticLambda0
                        @Override // java.io.FilenameFilter
                        public final boolean accept(File file2, String str9) {
                            boolean fromDisk$lambda$3;
                            fromDisk$lambda$3 = ReplayCache.Companion.fromDisk$lambda$3(ReplayCache.this, file2, str9);
                            return fromDisk$lambda$3;
                        }
                    });
                }
                if (replayCache.getFrames$sentry_android_replay_release().isEmpty()) {
                    options.getLogger().log(SentryLevel.DEBUG, "No frames found for replay: %s, deleting the replay", replayId);
                    FileUtils.deleteRecursively(makeReplayCacheDir);
                    return null;
                }
                List<ReplayFrame> frames$sentry_android_replay_release = replayCache.getFrames$sentry_android_replay_release();
                if (frames$sentry_android_replay_release.size() > 1) {
                    CollectionsKt.sortWith(frames$sentry_android_replay_release, new Comparator() { // from class: io.sentry.android.replay.ReplayCache$Companion$fromDisk$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Long.valueOf(((ReplayFrame) t).getTimestamp()), Long.valueOf(((ReplayFrame) t2).getTimestamp()));
                        }
                    });
                }
                int intValue = replayType == SentryReplayEvent.ReplayType.SESSION ? intOrNull5.intValue() : 0;
                if (replayType != SentryReplayEvent.ReplayType.SESSION) {
                    date = DateUtils.getDateTime(((ReplayFrame) CollectionsKt.first((List) replayCache.getFrames$sentry_android_replay_release())).getTimestamp());
                    Intrinsics.checkNotNull(date);
                }
                Date date2 = date;
                long timestamp = (((ReplayFrame) CollectionsKt.last((List) replayCache.getFrames$sentry_android_replay_release())).getTimestamp() - date2.getTime()) + (1000 / intOrNull3.intValue());
                String str9 = (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_REPLAY_RECORDING);
                if (str9 != null) {
                    ReplayRecording replayRecording = (ReplayRecording) options.getSerializer().deserialize(new StringReader(str9), ReplayRecording.class);
                    if ((replayRecording != null ? replayRecording.getPayload() : null) != null) {
                        List<? extends RRWebEvent> payload = replayRecording.getPayload();
                        Intrinsics.checkNotNull(payload);
                        linkedList = new LinkedList(payload);
                    }
                    if (linkedList != null) {
                        emptyList = linkedList;
                        return new LastSegmentData(screenshotRecorderConfig, replayCache, date2, intValue, timestamp, replayType, (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_REPLAY_SCREEN_AT_START), CollectionsKt.sortedWith(emptyList, new Comparator() { // from class: io.sentry.android.replay.ReplayCache$Companion$fromDisk$$inlined$sortedBy$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                return ComparisonsKt.compareValues(Long.valueOf(((RRWebEvent) t).getTimestamp()), Long.valueOf(((RRWebEvent) t2).getTimestamp()));
                            }
                        }));
                    }
                }
                emptyList = CollectionsKt.emptyList();
                return new LastSegmentData(screenshotRecorderConfig, replayCache, date2, intValue, timestamp, replayType, (String) linkedHashMap.get(ReplayCache.SEGMENT_KEY_REPLAY_SCREEN_AT_START), CollectionsKt.sortedWith(emptyList, new Comparator() { // from class: io.sentry.android.replay.ReplayCache$Companion$fromDisk$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((RRWebEvent) t).getTimestamp()), Long.valueOf(((RRWebEvent) t2).getTimestamp()));
                    }
                }));
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean fromDisk$lambda$3(ReplayCache replayCache, File file, String str) {
            Intrinsics.checkNotNull(str);
            if (StringsKt.endsWith$default(str, ".jpg", false, 2, (Object) null)) {
                File file2 = new File(file, str);
                Long longOrNull = StringsKt.toLongOrNull(FilesKt.getNameWithoutExtension(file2));
                if (longOrNull != null) {
                    ReplayCache.addFrame$default(replayCache, file2, longOrNull.longValue(), null, 4, null);
                }
            }
            return false;
        }
    }
}
