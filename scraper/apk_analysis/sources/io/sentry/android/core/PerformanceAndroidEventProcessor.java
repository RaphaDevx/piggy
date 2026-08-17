package io.sentry.android.core;

import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.ISentryLifecycleToken;
import io.sentry.MeasurementUnit;
import io.sentry.SentryEvent;
import io.sentry.SpanContext;
import io.sentry.SpanDataConvention;
import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.android.core.internal.util.AndroidThreadChecker;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.android.core.performance.TimeSpan;
import io.sentry.protocol.App;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentrySpan;
import io.sentry.protocol.SentryTransaction;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
final class PerformanceAndroidEventProcessor implements EventProcessor {
    private static final String APP_METRICS_ACTIVITIES_OP = "activity.load";
    private static final String APP_METRICS_APPLICATION_OP = "application.load";
    private static final String APP_METRICS_CONTENT_PROVIDER_OP = "contentprovider.load";
    private static final String APP_METRICS_ORIGIN = "auto.ui";
    private static final String APP_METRICS_PROCESS_INIT_OP = "process.load";
    private static final long MAX_PROCESS_INIT_APP_START_DIFF_MS = 10000;
    private final ActivityFramesTracker activityFramesTracker;
    private final SentryAndroidOptions options;
    private boolean sentStartMeasurement = false;
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();

    @Override // io.sentry.EventProcessor
    public SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        return sentryEvent;
    }

    PerformanceAndroidEventProcessor(SentryAndroidOptions sentryAndroidOptions, ActivityFramesTracker activityFramesTracker) {
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.activityFramesTracker = (ActivityFramesTracker) Objects.requireNonNull(activityFramesTracker, "ActivityFramesTracker is required");
    }

    @Override // io.sentry.EventProcessor
    public SentryTransaction process(SentryTransaction sentryTransaction, Hint hint) {
        Map<String, MeasurementValue> takeMetrics;
        String str;
        String str2;
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (!this.options.isTracingEnabled()) {
                if (acquire != null) {
                    acquire.close();
                }
                return sentryTransaction;
            }
            AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
            if (hasAppStartSpan(sentryTransaction)) {
                if (appStartMetrics.shouldSendStartMeasurements()) {
                    long durationMs = appStartMetrics.getAppStartTimeSpanWithFallback(this.options).getDurationMs();
                    if (durationMs != 0) {
                        MeasurementValue measurementValue = new MeasurementValue(Float.valueOf(durationMs), MeasurementUnit.Duration.MILLISECOND.apiName());
                        if (appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD) {
                            str2 = MeasurementValue.KEY_APP_START_COLD;
                        } else {
                            str2 = MeasurementValue.KEY_APP_START_WARM;
                        }
                        sentryTransaction.getMeasurements().put(str2, measurementValue);
                        attachAppStartSpans(appStartMetrics, sentryTransaction);
                        appStartMetrics.onAppStartSpansSent();
                    }
                }
                App app = sentryTransaction.getContexts().getApp();
                if (app == null) {
                    app = new App();
                    sentryTransaction.getContexts().setApp(app);
                }
                if (appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD) {
                    str = "cold";
                } else {
                    str = "warm";
                }
                app.setStartType(str);
            }
            setContributingFlags(sentryTransaction);
            SentryId eventId = sentryTransaction.getEventId();
            SpanContext trace = sentryTransaction.getContexts().getTrace();
            if (eventId != null && trace != null && trace.getOperation().contentEquals("ui.load") && (takeMetrics = this.activityFramesTracker.takeMetrics(eventId)) != null) {
                sentryTransaction.getMeasurements().putAll(takeMetrics);
            }
            if (acquire != null) {
                acquire.close();
            }
            return sentryTransaction;
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void setContributingFlags(SentryTransaction sentryTransaction) {
        Object obj;
        SentrySpan sentrySpan = null;
        SentrySpan sentrySpan2 = null;
        for (SentrySpan sentrySpan3 : sentryTransaction.getSpans()) {
            if ("ui.load.initial_display".equals(sentrySpan3.getOp())) {
                sentrySpan = sentrySpan3;
            } else if ("ui.load.full_display".equals(sentrySpan3.getOp())) {
                sentrySpan2 = sentrySpan3;
            }
            if (sentrySpan != null && sentrySpan2 != null) {
                break;
            }
        }
        if (sentrySpan == null && sentrySpan2 == null) {
            return;
        }
        for (SentrySpan sentrySpan4 : sentryTransaction.getSpans()) {
            if (sentrySpan4 != sentrySpan && sentrySpan4 != sentrySpan2) {
                Map<String, Object> data = sentrySpan4.getData();
                boolean z = false;
                boolean z2 = sentrySpan != null && isTimestampWithinSpan(sentrySpan4.getStartTimestamp().doubleValue(), sentrySpan) && (data == null || (obj = data.get(SpanDataConvention.THREAD_NAME)) == null || "main".equals(obj));
                if (sentrySpan2 != null && isTimestampWithinSpan(sentrySpan4.getStartTimestamp().doubleValue(), sentrySpan2)) {
                    z = true;
                }
                if (z2 || z) {
                    Map<String, Object> data2 = sentrySpan4.getData();
                    if (data2 == null) {
                        data2 = new ConcurrentHashMap<>();
                        sentrySpan4.setData(data2);
                    }
                    if (z2) {
                        data2.put(SpanDataConvention.CONTRIBUTES_TTID, true);
                    }
                    if (z) {
                        data2.put(SpanDataConvention.CONTRIBUTES_TTFD, true);
                    }
                }
            }
        }
    }

    private static boolean isTimestampWithinSpan(double d, SentrySpan sentrySpan) {
        return d >= sentrySpan.getStartTimestamp().doubleValue() && (sentrySpan.getTimestamp() == null || d <= sentrySpan.getTimestamp().doubleValue());
    }

    private boolean hasAppStartSpan(SentryTransaction sentryTransaction) {
        for (SentrySpan sentrySpan : sentryTransaction.getSpans()) {
            if (sentrySpan.getOp().contentEquals("app.start.cold") || sentrySpan.getOp().contentEquals("app.start.warm")) {
                return true;
            }
        }
        SpanContext trace = sentryTransaction.getContexts().getTrace();
        return trace != null && (trace.getOperation().equals("app.start.cold") || trace.getOperation().equals("app.start.warm"));
    }

    private void attachAppStartSpans(AppStartMetrics appStartMetrics, SentryTransaction sentryTransaction) {
        SpanContext trace;
        SpanId spanId;
        if (appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD && (trace = sentryTransaction.getContexts().getTrace()) != null) {
            SentryId traceId = trace.getTraceId();
            Iterator<SentrySpan> it = sentryTransaction.getSpans().iterator();
            while (true) {
                if (!it.hasNext()) {
                    spanId = null;
                    break;
                }
                SentrySpan next = it.next();
                if (next.getOp().contentEquals("app.start.cold")) {
                    spanId = next.getSpanId();
                    break;
                }
            }
            TimeSpan createProcessInitSpan = appStartMetrics.createProcessInitSpan();
            if (createProcessInitSpan.hasStarted() && Math.abs(createProcessInitSpan.getDurationMs()) <= 10000) {
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(createProcessInitSpan, spanId, traceId, APP_METRICS_PROCESS_INIT_OP));
            }
            List<TimeSpan> contentProviderOnCreateTimeSpans = appStartMetrics.getContentProviderOnCreateTimeSpans();
            if (!contentProviderOnCreateTimeSpans.isEmpty()) {
                Iterator<TimeSpan> it2 = contentProviderOnCreateTimeSpans.iterator();
                while (it2.hasNext()) {
                    sentryTransaction.getSpans().add(timeSpanToSentrySpan(it2.next(), spanId, traceId, APP_METRICS_CONTENT_PROVIDER_OP));
                }
            }
            TimeSpan applicationOnCreateTimeSpan = appStartMetrics.getApplicationOnCreateTimeSpan();
            if (applicationOnCreateTimeSpan.hasStopped()) {
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(applicationOnCreateTimeSpan, spanId, traceId, APP_METRICS_APPLICATION_OP));
            }
        }
    }

    private static SentrySpan timeSpanToSentrySpan(TimeSpan timeSpan, SpanId spanId, SentryId sentryId, String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(SpanDataConvention.THREAD_ID, Long.valueOf(AndroidThreadChecker.mainThreadSystemId));
        hashMap.put(SpanDataConvention.THREAD_NAME, "main");
        hashMap.put(SpanDataConvention.CONTRIBUTES_TTID, true);
        hashMap.put(SpanDataConvention.CONTRIBUTES_TTFD, true);
        return new SentrySpan(Double.valueOf(timeSpan.getStartTimestampSecs()), Double.valueOf(timeSpan.getProjectedStopTimestampSecs()), sentryId, new SpanId(), spanId, str, timeSpan.getDescription(), SpanStatus.OK, APP_METRICS_ORIGIN, new ConcurrentHashMap(), new ConcurrentHashMap(), hashMap);
    }

    @Override // io.sentry.EventProcessor
    public Long getOrder() {
        return 9000L;
    }
}
