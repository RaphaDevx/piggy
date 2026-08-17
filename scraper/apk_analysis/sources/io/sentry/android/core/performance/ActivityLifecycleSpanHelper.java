package io.sentry.android.core.performance;

import android.os.Looper;
import android.os.SystemClock;
import io.sentry.ISpan;
import io.sentry.Instrumenter;
import io.sentry.SentryDate;
import io.sentry.SpanDataConvention;
import io.sentry.SpanStatus;
import io.sentry.android.core.AndroidDateUtils;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ActivityLifecycleSpanHelper {
    private static final String APP_METRICS_ACTIVITIES_OP = "activity.load";
    private final String activityName;
    private SentryDate onCreateStartTimestamp = null;
    private SentryDate onStartStartTimestamp = null;
    private ISpan onCreateSpan = null;
    private ISpan onStartSpan = null;

    public ActivityLifecycleSpanHelper(String str) {
        this.activityName = str;
    }

    public void setOnCreateStartTimestamp(SentryDate sentryDate) {
        this.onCreateStartTimestamp = sentryDate;
    }

    public void setOnStartStartTimestamp(SentryDate sentryDate) {
        this.onStartStartTimestamp = sentryDate;
    }

    public void createAndStopOnCreateSpan(ISpan iSpan) {
        if (this.onCreateStartTimestamp == null || iSpan == null) {
            return;
        }
        ISpan createLifecycleSpan = createLifecycleSpan(iSpan, this.activityName + ".onCreate", this.onCreateStartTimestamp);
        this.onCreateSpan = createLifecycleSpan;
        createLifecycleSpan.finish();
    }

    public void createAndStopOnStartSpan(ISpan iSpan) {
        if (this.onStartStartTimestamp == null || iSpan == null) {
            return;
        }
        ISpan createLifecycleSpan = createLifecycleSpan(iSpan, this.activityName + ".onStart", this.onStartStartTimestamp);
        this.onStartSpan = createLifecycleSpan;
        createLifecycleSpan.finish();
    }

    public ISpan getOnCreateSpan() {
        return this.onCreateSpan;
    }

    public ISpan getOnStartSpan() {
        return this.onStartSpan;
    }

    public SentryDate getOnCreateStartTimestamp() {
        return this.onCreateStartTimestamp;
    }

    public SentryDate getOnStartStartTimestamp() {
        return this.onStartStartTimestamp;
    }

    public void saveSpanToAppStartMetrics() {
        ISpan iSpan = this.onCreateSpan;
        if (iSpan == null || this.onStartSpan == null) {
            return;
        }
        SentryDate finishDate = iSpan.getFinishDate();
        SentryDate finishDate2 = this.onStartSpan.getFinishDate();
        if (finishDate == null || finishDate2 == null) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        SentryDate currentSentryDateTime = AndroidDateUtils.getCurrentSentryDateTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(currentSentryDateTime.diff(this.onCreateSpan.getStartDate()));
        long millis2 = TimeUnit.NANOSECONDS.toMillis(currentSentryDateTime.diff(finishDate));
        long millis3 = TimeUnit.NANOSECONDS.toMillis(currentSentryDateTime.diff(this.onStartSpan.getStartDate()));
        long millis4 = TimeUnit.NANOSECONDS.toMillis(currentSentryDateTime.diff(finishDate2));
        ActivityLifecycleTimeSpan activityLifecycleTimeSpan = new ActivityLifecycleTimeSpan();
        activityLifecycleTimeSpan.getOnCreate().setup(this.onCreateSpan.getDescription(), TimeUnit.NANOSECONDS.toMillis(this.onCreateSpan.getStartDate().nanoTimestamp()), uptimeMillis - millis, uptimeMillis - millis2);
        activityLifecycleTimeSpan.getOnStart().setup(this.onStartSpan.getDescription(), TimeUnit.NANOSECONDS.toMillis(this.onStartSpan.getStartDate().nanoTimestamp()), uptimeMillis - millis3, uptimeMillis - millis4);
        AppStartMetrics.getInstance().addActivityLifecycleTimeSpans(activityLifecycleTimeSpan);
    }

    private ISpan createLifecycleSpan(ISpan iSpan, String str, SentryDate sentryDate) {
        ISpan startChild = iSpan.startChild(APP_METRICS_ACTIVITIES_OP, str, sentryDate, Instrumenter.SENTRY);
        setDefaultStartSpanData(startChild);
        return startChild;
    }

    public void clear() {
        ISpan iSpan = this.onCreateSpan;
        if (iSpan != null && !iSpan.isFinished()) {
            this.onCreateSpan.finish(SpanStatus.CANCELLED);
        }
        this.onCreateSpan = null;
        ISpan iSpan2 = this.onStartSpan;
        if (iSpan2 != null && !iSpan2.isFinished()) {
            this.onStartSpan.finish(SpanStatus.CANCELLED);
        }
        this.onStartSpan = null;
    }

    private void setDefaultStartSpanData(ISpan iSpan) {
        iSpan.setData(SpanDataConvention.THREAD_ID, Long.valueOf(Looper.getMainLooper().getThread().getId()));
        iSpan.setData(SpanDataConvention.THREAD_NAME, "main");
        iSpan.setData(SpanDataConvention.CONTRIBUTES_TTID, true);
        iSpan.setData(SpanDataConvention.CONTRIBUTES_TTFD, true);
    }
}
