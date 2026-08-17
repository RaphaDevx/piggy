package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewOverlay;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.util.DebugOverlayDrawable;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import io.sentry.android.replay.util.TextLayout;
import io.sentry.android.replay.util.ViewsKt;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotRecorder.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u00020$J\u0006\u00103\u001a\u000201J\u0006\u00104\u001a\u000201J\b\u00105\u001a\u000201H\u0016J\u0006\u00106\u001a\u000201J\u0006\u00107\u001a\u000201J\u0010\u00108\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010$J\u0014\u00109\u001a\u00020:*\u00020&2\u0006\u0010;\u001a\u00020<H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b\u001f\u0010 R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u001a\u001a\u0004\b(\u0010)R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u001a\u001a\u0004\b-\u0010.¨\u0006="}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorder;", "Landroid/view/ViewTreeObserver$OnDrawListener;", FirebaseConstants.Commands.CONFIGURE, "Lio/sentry/android/replay/ScreenshotRecorderConfig;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "recorder", "Ljava/util/concurrent/ScheduledExecutorService;", "screenshotRecorderCallback", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "(Lio/sentry/android/replay/ScreenshotRecorderConfig;Lio/sentry/SentryOptions;Lio/sentry/android/replay/util/MainLooperHandler;Ljava/util/concurrent/ScheduledExecutorService;Lio/sentry/android/replay/ScreenshotRecorderCallback;)V", "getConfig", "()Lio/sentry/android/replay/ScreenshotRecorderConfig;", "contentChanged", "Ljava/util/concurrent/atomic/AtomicBoolean;", "debugOverlayDrawable", "Lio/sentry/android/replay/util/DebugOverlayDrawable;", "isCapturing", "lastCaptureSuccessful", "maskingPaint", "Landroid/graphics/Paint;", "getMaskingPaint", "()Landroid/graphics/Paint;", "maskingPaint$delegate", "Lkotlin/Lazy;", "getOptions", "()Lio/sentry/SentryOptions;", "prescaledMatrix", "Landroid/graphics/Matrix;", "getPrescaledMatrix", "()Landroid/graphics/Matrix;", "prescaledMatrix$delegate", "rootView", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "screenshot", "Landroid/graphics/Bitmap;", "singlePixelBitmap", "getSinglePixelBitmap", "()Landroid/graphics/Bitmap;", "singlePixelBitmap$delegate", "singlePixelBitmapCanvas", "Landroid/graphics/Canvas;", "getSinglePixelBitmapCanvas", "()Landroid/graphics/Canvas;", "singlePixelBitmapCanvas$delegate", "bind", "", "root", "capture", "close", "onDraw", "pause", "resume", "unbind", "dominantColorForRect", "", "rect", "Landroid/graphics/Rect;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ScreenshotRecorder implements ViewTreeObserver.OnDrawListener {
    public static final int $stable = 8;
    private final ScreenshotRecorderConfig config;
    private final AtomicBoolean contentChanged;
    private final DebugOverlayDrawable debugOverlayDrawable;
    private final AtomicBoolean isCapturing;
    private final AtomicBoolean lastCaptureSuccessful;
    private final MainLooperHandler mainLooperHandler;

    /* renamed from: maskingPaint$delegate, reason: from kotlin metadata */
    private final Lazy maskingPaint;
    private final SentryOptions options;

    /* renamed from: prescaledMatrix$delegate, reason: from kotlin metadata */
    private final Lazy prescaledMatrix;
    private final ScheduledExecutorService recorder;
    private WeakReference<View> rootView;
    private final Bitmap screenshot;
    private final ScreenshotRecorderCallback screenshotRecorderCallback;

    /* renamed from: singlePixelBitmap$delegate, reason: from kotlin metadata */
    private final Lazy singlePixelBitmap;

    /* renamed from: singlePixelBitmapCanvas$delegate, reason: from kotlin metadata */
    private final Lazy singlePixelBitmapCanvas;

    public ScreenshotRecorder(ScreenshotRecorderConfig config, SentryOptions options, MainLooperHandler mainLooperHandler, ScheduledExecutorService recorder, ScreenshotRecorderCallback screenshotRecorderCallback) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkNotNullParameter(recorder, "recorder");
        this.config = config;
        this.options = options;
        this.mainLooperHandler = mainLooperHandler;
        this.recorder = recorder;
        this.screenshotRecorderCallback = screenshotRecorderCallback;
        this.maskingPaint = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Paint>() { // from class: io.sentry.android.replay.ScreenshotRecorder$maskingPaint$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                return new Paint();
            }
        });
        this.singlePixelBitmap = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Bitmap>() { // from class: io.sentry.android.replay.ScreenshotRecorder$singlePixelBitmap$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bitmap invoke() {
                Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                return createBitmap;
            }
        });
        Bitmap createBitmap = Bitmap.createBitmap(config.getRecordingWidth(), config.getRecordingHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.screenshot = createBitmap;
        this.singlePixelBitmapCanvas = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Canvas>() { // from class: io.sentry.android.replay.ScreenshotRecorder$singlePixelBitmapCanvas$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Canvas invoke() {
                Bitmap singlePixelBitmap;
                singlePixelBitmap = ScreenshotRecorder.this.getSinglePixelBitmap();
                return new Canvas(singlePixelBitmap);
            }
        });
        this.prescaledMatrix = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Matrix>() { // from class: io.sentry.android.replay.ScreenshotRecorder$prescaledMatrix$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Matrix invoke() {
                Matrix matrix = new Matrix();
                ScreenshotRecorder screenshotRecorder = ScreenshotRecorder.this;
                matrix.preScale(screenshotRecorder.getConfig().getScaleFactorX(), screenshotRecorder.getConfig().getScaleFactorY());
                return matrix;
            }
        });
        this.contentChanged = new AtomicBoolean(false);
        this.isCapturing = new AtomicBoolean(true);
        this.lastCaptureSuccessful = new AtomicBoolean(false);
        this.debugOverlayDrawable = new DebugOverlayDrawable();
    }

    public final ScreenshotRecorderConfig getConfig() {
        return this.config;
    }

    public final SentryOptions getOptions() {
        return this.options;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Paint getMaskingPaint() {
        return (Paint) this.maskingPaint.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap getSinglePixelBitmap() {
        return (Bitmap) this.singlePixelBitmap.getValue();
    }

    private final Canvas getSinglePixelBitmapCanvas() {
        return (Canvas) this.singlePixelBitmapCanvas.getValue();
    }

    private final Matrix getPrescaledMatrix() {
        return (Matrix) this.prescaledMatrix.getValue();
    }

    public final void capture() {
        if (this.options.getSessionReplay().isDebug()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Capturing screenshot, isCapturing: %s", Boolean.valueOf(this.isCapturing.get()));
        }
        if (!this.isCapturing.get()) {
            if (this.options.getSessionReplay().isDebug()) {
                this.options.getLogger().log(SentryLevel.DEBUG, "ScreenshotRecorder is paused, not capturing screenshot", new Object[0]);
                return;
            }
            return;
        }
        if (this.options.getSessionReplay().isDebug()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Capturing screenshot, contentChanged: %s, lastCaptureSuccessful: %s", Boolean.valueOf(this.contentChanged.get()), Boolean.valueOf(this.lastCaptureSuccessful.get()));
        }
        if (!this.contentChanged.get() && this.lastCaptureSuccessful.get()) {
            ScreenshotRecorderCallback screenshotRecorderCallback = this.screenshotRecorderCallback;
            if (screenshotRecorderCallback != null) {
                screenshotRecorderCallback.onScreenshotRecorded(this.screenshot);
                return;
            }
            return;
        }
        WeakReference<View> weakReference = this.rootView;
        final View view = weakReference != null ? weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        Window phoneWindow = WindowsKt.getPhoneWindow(view);
        if (phoneWindow == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Window is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        try {
            this.contentChanged.set(false);
            PixelCopy.request(phoneWindow, this.screenshot, new PixelCopy.OnPixelCopyFinishedListener() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda0
                @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                public final void onPixelCopyFinished(int i) {
                    ScreenshotRecorder.capture$lambda$2(ScreenshotRecorder.this, view, i);
                }
            }, this.mainLooperHandler.getHandler());
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.WARNING, "Failed to capture replay recording", th);
            this.lastCaptureSuccessful.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2(final ScreenshotRecorder screenshotRecorder, final View view, int i) {
        if (i != 0) {
            screenshotRecorder.options.getLogger().log(SentryLevel.INFO, "Failed to capture replay recording: %d", Integer.valueOf(i));
            screenshotRecorder.lastCaptureSuccessful.set(false);
        } else if (screenshotRecorder.contentChanged.get()) {
            screenshotRecorder.options.getLogger().log(SentryLevel.INFO, "Failed to determine view hierarchy, not capturing", new Object[0]);
            screenshotRecorder.lastCaptureSuccessful.set(false);
        } else {
            final ViewHierarchyNode fromView = ViewHierarchyNode.INSTANCE.fromView(view, null, 0, screenshotRecorder.options);
            ViewsKt.traverse(view, fromView, screenshotRecorder.options);
            ExecutorsKt.submitSafely(screenshotRecorder.recorder, screenshotRecorder.options, "screenshot_recorder.mask", new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenshotRecorder.capture$lambda$2$lambda$1(ScreenshotRecorder.this, fromView, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2$lambda$1(final ScreenshotRecorder screenshotRecorder, ViewHierarchyNode viewHierarchyNode, final View view) {
        final ArrayList arrayList = new ArrayList();
        final Canvas canvas = new Canvas(screenshotRecorder.screenshot);
        canvas.setMatrix(screenshotRecorder.getPrescaledMatrix());
        viewHierarchyNode.traverse(new Function1<ViewHierarchyNode, Boolean>() { // from class: io.sentry.android.replay.ScreenshotRecorder$capture$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ViewHierarchyNode node) {
                Pair pair;
                Integer dominantColor;
                Paint maskingPaint;
                Paint maskingPaint2;
                Bitmap bitmap;
                int dominantColorForRect;
                Intrinsics.checkNotNullParameter(node, "node");
                if (node.getShouldMask() && node.getWidth() > 0 && node.getHeight() > 0) {
                    if (node.getVisibleRect() == null) {
                        return false;
                    }
                    if (node instanceof ViewHierarchyNode.ImageViewHierarchyNode) {
                        List listOf = CollectionsKt.listOf(node.getVisibleRect());
                        ScreenshotRecorder screenshotRecorder2 = ScreenshotRecorder.this;
                        bitmap = screenshotRecorder2.screenshot;
                        dominantColorForRect = screenshotRecorder2.dominantColorForRect(bitmap, node.getVisibleRect());
                        pair = TuplesKt.to(listOf, Integer.valueOf(dominantColorForRect));
                    } else {
                        boolean z = node instanceof ViewHierarchyNode.TextViewHierarchyNode;
                        int i = ViewCompat.MEASURED_STATE_MASK;
                        if (z) {
                            ViewHierarchyNode.TextViewHierarchyNode textViewHierarchyNode = (ViewHierarchyNode.TextViewHierarchyNode) node;
                            TextLayout layout = textViewHierarchyNode.getLayout();
                            if ((layout != null && (dominantColor = layout.getDominantTextColor()) != null) || (dominantColor = textViewHierarchyNode.getDominantColor()) != null) {
                                i = dominantColor.intValue();
                            }
                            pair = TuplesKt.to(ViewsKt.getVisibleRects(textViewHierarchyNode.getLayout(), node.getVisibleRect(), textViewHierarchyNode.getPaddingLeft(), textViewHierarchyNode.getPaddingTop()), Integer.valueOf(i));
                        } else {
                            pair = TuplesKt.to(CollectionsKt.listOf(node.getVisibleRect()), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
                        }
                    }
                    List list = (List) pair.component1();
                    int intValue = ((Number) pair.component2()).intValue();
                    maskingPaint = ScreenshotRecorder.this.getMaskingPaint();
                    maskingPaint.setColor(intValue);
                    Canvas canvas2 = canvas;
                    ScreenshotRecorder screenshotRecorder3 = ScreenshotRecorder.this;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        RectF rectF = new RectF((Rect) it.next());
                        maskingPaint2 = screenshotRecorder3.getMaskingPaint();
                        canvas2.drawRoundRect(rectF, 10.0f, 10.0f, maskingPaint2);
                    }
                    if (ScreenshotRecorder.this.getOptions().getReplayController().isDebugMaskingOverlayEnabled()) {
                        arrayList.addAll(list);
                    }
                }
                return true;
            }
        });
        if (screenshotRecorder.options.getReplayController().isDebugMaskingOverlayEnabled()) {
            screenshotRecorder.mainLooperHandler.post(new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenshotRecorder.capture$lambda$2$lambda$1$lambda$0(ScreenshotRecorder.this, view, arrayList);
                }
            });
        }
        ScreenshotRecorderCallback screenshotRecorderCallback = screenshotRecorder.screenshotRecorderCallback;
        if (screenshotRecorderCallback != null) {
            screenshotRecorderCallback.onScreenshotRecorded(screenshotRecorder.screenshot);
        }
        screenshotRecorder.lastCaptureSuccessful.set(true);
        screenshotRecorder.contentChanged.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2$lambda$1$lambda$0(ScreenshotRecorder screenshotRecorder, View view, List list) {
        if (screenshotRecorder.debugOverlayDrawable.getCallback() == null) {
            view.getOverlay().add(screenshotRecorder.debugOverlayDrawable);
        }
        screenshotRecorder.debugOverlayDrawable.updateMasks(list);
        view.postInvalidate();
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        if (this.isCapturing.get()) {
            WeakReference<View> weakReference = this.rootView;
            View view = weakReference != null ? weakReference.get() : null;
            if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            } else {
                this.contentChanged.set(true);
            }
        }
    }

    public final void bind(View root) {
        Intrinsics.checkNotNullParameter(root, "root");
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.rootView = new WeakReference<>(root);
        ViewsKt.addOnDrawListenerSafe(root, this);
        this.contentChanged.set(true);
    }

    public final void unbind(View root) {
        ViewOverlay overlay;
        if (this.options.getReplayController().isDebugMaskingOverlayEnabled() && root != null && (overlay = root.getOverlay()) != null) {
            overlay.remove(this.debugOverlayDrawable);
        }
        if (root != null) {
            ViewsKt.removeOnDrawListenerSafe(root, this);
        }
    }

    public final void pause() {
        this.isCapturing.set(false);
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
    }

    public final void resume() {
        View view;
        WeakReference<View> weakReference = this.rootView;
        if (weakReference != null && (view = weakReference.get()) != null) {
            ViewsKt.addOnDrawListenerSafe(view, this);
        }
        this.isCapturing.set(true);
    }

    public final void close() {
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        if (!this.screenshot.isRecycled()) {
            this.screenshot.recycle();
        }
        this.isCapturing.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dominantColorForRect(Bitmap bitmap, Rect rect) {
        Rect rect2 = new Rect(rect);
        RectF rectF = new RectF(rect2);
        getPrescaledMatrix().mapRect(rectF);
        rectF.round(rect2);
        getSinglePixelBitmapCanvas().drawBitmap(bitmap, rect2, new Rect(0, 0, 1, 1), (Paint) null);
        return getSinglePixelBitmap().getPixel(0, 0);
    }
}
