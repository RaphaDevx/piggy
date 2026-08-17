package kotlinx.coroutines.stream;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;

/* compiled from: Stream.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0096@¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\t\u0010\u0007\u001a\u00020\bX\u0082\u0004¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "<init>", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
final class StreamFlow<T> implements Flow<T> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater consumed$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed$volatile");
    private volatile /* synthetic */ int consumed$volatile;
    private final Stream<T> stream;

    private final /* synthetic */ int getConsumed$volatile() {
        return this.consumed$volatile;
    }

    private final /* synthetic */ void setConsumed$volatile(int i) {
        this.consumed$volatile = i;
    }

    public StreamFlow(Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0061 A[Catch: all -> 0x007c, TRY_LEAVE, TryCatch #1 {all -> 0x007c, blocks: (B:15:0x005b, B:17:0x0061), top: B:14:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.stream.StreamFlow$collect$1
            if (r0 == 0) goto L14
            r0 = r7
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = (kotlinx.coroutines.stream.StreamFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = new kotlinx.coroutines.stream.StreamFlow$collect$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r5 = r0.L$2
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.stream.StreamFlow r2 = (kotlinx.coroutines.stream.StreamFlow) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L38
            r7 = r6
            r6 = r2
            goto L5b
        L38:
            r5 = move-exception
            r6 = r2
            goto L82
        L3b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L43:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = getConsumed$volatile$FU()
            r2 = 0
            boolean r7 = r7.compareAndSet(r5, r2, r3)
            if (r7 == 0) goto L88
            java.util.stream.Stream<T> r7 = r5.stream     // Catch: java.lang.Throwable -> L7e
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L7e
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L5b:
            boolean r2 = r5.hasNext()     // Catch: java.lang.Throwable -> L7c
            if (r2 == 0) goto L74
            java.lang.Object r2 = r5.next()     // Catch: java.lang.Throwable -> L7c
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L7c
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L7c
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L7c
            r0.label = r3     // Catch: java.lang.Throwable -> L7c
            java.lang.Object r2 = r7.emit(r2, r0)     // Catch: java.lang.Throwable -> L7c
            if (r2 != r1) goto L5b
            return r1
        L74:
            java.util.stream.Stream<T> r5 = r6.stream
            r5.close()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L7c:
            r5 = move-exception
            goto L82
        L7e:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L82:
            java.util.stream.Stream<T> r6 = r6.stream
            r6.close()
            throw r5
        L88:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Stream.consumeAsFlow can be collected only once"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.stream.StreamFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
