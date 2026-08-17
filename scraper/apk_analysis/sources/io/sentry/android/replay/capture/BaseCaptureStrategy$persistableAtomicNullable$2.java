package io.sentry.android.replay.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.util.ExecutorsKt;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: BaseCaptureStrategy.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001J&\u0010\u0005\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0096\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002J.\u0010\u000e\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000fR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"io/sentry/android/replay/capture/BaseCaptureStrategy$persistableAtomicNullable$2", "Lkotlin/properties/ReadWriteProperty;", "", "value", "Ljava/util/concurrent/atomic/AtomicReference;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "runInBackground", "", "task", "Lkotlin/Function0;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BaseCaptureStrategy$persistableAtomicNullable$2<T> implements ReadWriteProperty<Object, T> {
    final /* synthetic */ Function3<String, T, T, Unit> $onChange;
    final /* synthetic */ String $propertyName;
    final /* synthetic */ BaseCaptureStrategy this$0;
    private final AtomicReference<T> value;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseCaptureStrategy$persistableAtomicNullable$2(T t, BaseCaptureStrategy baseCaptureStrategy, Function3<? super String, ? super T, ? super T, Unit> function3, String str) {
        this.this$0 = baseCaptureStrategy;
        this.$onChange = function3;
        this.$propertyName = str;
        this.value = new AtomicReference<>(t);
    }

    private final void runInBackground(final Function0<Unit> task) {
        SentryOptions sentryOptions;
        SentryOptions sentryOptions2;
        ScheduledExecutorService persistingExecutor;
        SentryOptions sentryOptions3;
        sentryOptions = this.this$0.options;
        if (sentryOptions.getThreadChecker().isMainThread()) {
            persistingExecutor = this.this$0.getPersistingExecutor();
            sentryOptions3 = this.this$0.options;
            ExecutorsKt.submitSafely(persistingExecutor, sentryOptions3, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$persistableAtomicNullable$2$runInBackground$1
                @Override // java.lang.Runnable
                public final void run() {
                    task.invoke();
                }
            });
        } else {
            try {
                task.invoke();
            } catch (Throwable th) {
                sentryOptions2 = this.this$0.options;
                sentryOptions2.getLogger().log(SentryLevel.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th);
            }
        }
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.value.get();
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(Object thisRef, KProperty<?> property, final T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        final T andSet = this.value.getAndSet(value);
        if (Intrinsics.areEqual(andSet, value)) {
            return;
        }
        final Function3<String, T, T, Unit> function3 = this.$onChange;
        final String str = this.$propertyName;
        runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$persistableAtomicNullable$2$setValue$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                function3.invoke(str, andSet, value);
            }
        });
    }
}
