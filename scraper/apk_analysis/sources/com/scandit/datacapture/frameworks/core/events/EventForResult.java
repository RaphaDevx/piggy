package com.scandit.datacapture.frameworks.core.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import io.sentry.SentryEvent;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventForResult.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 \u001d*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001dB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\r\u001a\u00020\u000eJ;\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\u001f\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "name", "", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "(Ljava/lang/String;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;)V", "resultHolder", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lcom/scandit/datacapture/frameworks/core/events/PendingResult;", "cancel", "", "emit", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "payload", "", "timeoutResult", "timeoutMillis", "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Ljava/util/Map;Ljava/lang/Object;J)Ljava/lang/Object;", "getResult", "(Ljava/lang/Object;J)Ljava/lang/Object;", "submitResult", "value", "(Ljava/lang/Object;)V", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class EventForResult<T> {
    public static final long DEFAULT_TIMEOUT_MILLIS = 2000;
    private final FrameworksLog logger;
    private final MainThread mainThread;
    private final String name;
    private final ArrayBlockingQueue<PendingResult> resultHolder;

    public EventForResult(String name, MainThread mainThread, FrameworksLog logger) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.name = name;
        this.mainThread = mainThread;
        this.logger = logger;
        this.resultHolder = new ArrayBlockingQueue<>(1);
    }

    public /* synthetic */ EventForResult(String str, DefaultMainThread defaultMainThread, DefaultFrameworksLog defaultFrameworksLog, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread, (i & 4) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : defaultFrameworksLog);
    }

    public static /* synthetic */ Object emit$default(EventForResult eventForResult, Emitter emitter, Map map, Object obj, long j, int i, Object obj2) {
        if ((i & 8) != 0) {
            j = DEFAULT_TIMEOUT_MILLIS;
        }
        return eventForResult.emit(emitter, map, obj, j);
    }

    public final T emit(final Emitter emitter, final Map<String, Object> payload, T timeoutResult, long timeoutMillis) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.resultHolder.clear();
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.events.EventForResult$emit$1
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
                String str;
                Emitter emitter2 = Emitter.this;
                str = ((EventForResult) this).name;
                emitter2.emit(str, payload);
            }
        });
        return getResult(timeoutResult, timeoutMillis);
    }

    static /* synthetic */ Object getResult$default(EventForResult eventForResult, Object obj, long j, int i, Object obj2) {
        if ((i & 2) != 0) {
            j = DEFAULT_TIMEOUT_MILLIS;
        }
        return eventForResult.getResult(obj, j);
    }

    private final T getResult(T timeoutResult, long timeoutMillis) {
        PendingResult take;
        if (timeoutMillis != -1) {
            take = this.resultHolder.poll(timeoutMillis, TimeUnit.MILLISECONDS);
        } else {
            take = this.resultHolder.take();
        }
        if (take instanceof Cancellation) {
            this.logger.info("Callback `" + this.name + "` not finished, because onCancel was called.");
            return timeoutResult;
        }
        if (take instanceof Result) {
            return (T) ((Result) take).getValue();
        }
        this.logger.info("Callback `" + this.name + "` not finished after " + timeoutMillis + " milliseconds.");
        return timeoutResult;
    }

    public final void submitResult(T value) {
        this.resultHolder.offer(new Result(value));
    }

    public final void cancel() {
        this.resultHolder.offer(Cancellation.INSTANCE);
    }
}
