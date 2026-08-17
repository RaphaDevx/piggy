package io.sentry.android.core.internal.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.util.thread.IThreadChecker;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class ScreenshotUtils {
    private static final long CAPTURE_TIMEOUT_MS = 1000;

    public static byte[] takeScreenshot(Activity activity, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        return takeScreenshot(activity, AndroidThreadChecker.getInstance(), iLogger, buildInfoProvider);
    }

    public static byte[] takeScreenshot(Activity activity, IThreadChecker iThreadChecker, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        return compressBitmapToPng(captureScreenshot(activity, iThreadChecker, iLogger, buildInfoProvider), iLogger);
    }

    public static Bitmap captureScreenshot(Activity activity, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        return captureScreenshot(activity, AndroidThreadChecker.getInstance(), iLogger, buildInfoProvider);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap captureScreenshot(android.app.Activity r10, io.sentry.util.thread.IThreadChecker r11, final io.sentry.ILogger r12, io.sentry.android.core.BuildInfoProvider r13) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.ScreenshotUtils.captureScreenshot(android.app.Activity, io.sentry.util.thread.IThreadChecker, io.sentry.ILogger, io.sentry.android.core.BuildInfoProvider):android.graphics.Bitmap");
    }

    static /* synthetic */ void lambda$captureScreenshot$0(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, int i) {
        atomicBoolean.set(i == 0);
        countDownLatch.countDown();
    }

    static /* synthetic */ void lambda$captureScreenshot$1(View view, Canvas canvas, ILogger iLogger, CountDownLatch countDownLatch) {
        try {
            view.draw(canvas);
        } finally {
            try {
            } finally {
            }
        }
    }

    public static byte[] compressBitmapToPng(Bitmap bitmap, ILogger iLogger) {
        if (bitmap != null && !bitmap.isRecycled()) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                    bitmap.recycle();
                    if (byteArrayOutputStream.size() <= 0) {
                        iLogger.log(SentryLevel.DEBUG, "Screenshot is 0 bytes, not attaching the image.", new Object[0]);
                        byteArrayOutputStream.close();
                        return null;
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                } finally {
                }
            } catch (Throwable th) {
                iLogger.log(SentryLevel.ERROR, "Compressing bitmap failed.", th);
            }
        }
        return null;
    }

    private static boolean isActivityValid(Activity activity) {
        return (activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }
}
