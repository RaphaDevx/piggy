package io.sentry.android.replay.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.ISerializer;
import io.sentry.ReplayRecording;
import io.sentry.SentryOptions;
import io.sentry.android.replay.ReplayCache;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Persistable.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0016\u0010\u0010\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0002H\u0016R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lio/sentry/android/replay/util/PersistableLinkedList;", "Ljava/util/concurrent/ConcurrentLinkedDeque;", "Lio/sentry/rrweb/RRWebEvent;", "propertyName", "", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "persistingExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "cacheProvider", "Lkotlin/Function0;", "Lio/sentry/android/replay/ReplayCache;", "(Ljava/lang/String;Lio/sentry/SentryOptions;Ljava/util/concurrent/ScheduledExecutorService;Lkotlin/jvm/functions/Function0;)V", "add", "", "element", "addAll", "elements", "", "persistRecording", "", "remove", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PersistableLinkedList extends ConcurrentLinkedDeque<RRWebEvent> {
    public static final int $stable = 8;
    private final Function0<ReplayCache> cacheProvider;
    private final SentryOptions options;
    private final ScheduledExecutorService persistingExecutor;
    private final String propertyName;

    public /* bridge */ boolean contains(RRWebEvent rRWebEvent) {
        return super.contains((Object) rRWebEvent);
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof RRWebEvent) {
            return contains((RRWebEvent) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ boolean remove(RRWebEvent rRWebEvent) {
        return super.remove((Object) rRWebEvent);
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final /* bridge */ boolean remove(Object obj) {
        if (obj == null ? true : obj instanceof RRWebEvent) {
            return remove((RRWebEvent) obj);
        }
        return false;
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final /* bridge */ int size() {
        return getSize();
    }

    public PersistableLinkedList(String propertyName, SentryOptions options, ScheduledExecutorService persistingExecutor, Function0<ReplayCache> cacheProvider) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(persistingExecutor, "persistingExecutor");
        Intrinsics.checkNotNullParameter(cacheProvider, "cacheProvider");
        this.propertyName = propertyName;
        this.options = options;
        this.persistingExecutor = persistingExecutor;
        this.cacheProvider = cacheProvider;
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean addAll(Collection<? extends RRWebEvent> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean addAll = super.addAll(elements);
        persistRecording();
        return addAll;
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    public boolean add(RRWebEvent element) {
        Intrinsics.checkNotNullParameter(element, "element");
        boolean add = super.add((PersistableLinkedList) element);
        persistRecording();
        return add;
    }

    @Override // java.util.concurrent.ConcurrentLinkedDeque, java.util.Deque, java.util.Queue
    public RRWebEvent remove() {
        RRWebEvent rRWebEvent = (RRWebEvent) super.remove();
        persistRecording();
        Intrinsics.checkNotNull(rRWebEvent);
        return rRWebEvent;
    }

    private final void persistRecording() {
        final ReplayCache invoke = this.cacheProvider.invoke();
        if (invoke == null) {
            return;
        }
        final ReplayRecording replayRecording = new ReplayRecording();
        replayRecording.setPayload(new ArrayList(this));
        if (this.options.getThreadChecker().isMainThread()) {
            this.persistingExecutor.submit(new Runnable() { // from class: io.sentry.android.replay.util.PersistableLinkedList$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PersistableLinkedList.persistRecording$lambda$1(PersistableLinkedList.this, replayRecording, invoke);
                }
            });
            return;
        }
        StringWriter stringWriter = new StringWriter();
        this.options.getSerializer().serialize((ISerializer) replayRecording, (Writer) new BufferedWriter(stringWriter));
        invoke.persistSegmentValues$sentry_android_replay_release(this.propertyName, stringWriter.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void persistRecording$lambda$1(PersistableLinkedList persistableLinkedList, ReplayRecording replayRecording, ReplayCache replayCache) {
        StringWriter stringWriter = new StringWriter();
        persistableLinkedList.options.getSerializer().serialize((ISerializer) replayRecording, (Writer) new BufferedWriter(stringWriter));
        replayCache.persistSegmentValues$sentry_android_replay_release(persistableLinkedList.propertyName, stringWriter.toString());
    }
}
