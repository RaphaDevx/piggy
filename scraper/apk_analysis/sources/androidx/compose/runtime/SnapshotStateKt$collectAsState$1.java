package androidx.compose.runtime;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: SnapshotState.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt$collectAsState$1", f = "SnapshotState.kt", i = {}, l = {908, 800}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SnapshotStateKt$collectAsState$1<R> extends SuspendLambda implements Function2<ProduceStateScope<R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Flow<T> $this_collectAsState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SnapshotStateKt$collectAsState$1(CoroutineContext coroutineContext, Flow<? extends T> flow, Continuation<? super SnapshotStateKt$collectAsState$1> continuation) {
        super(2, continuation);
        this.$context = coroutineContext;
        this.$this_collectAsState = flow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SnapshotStateKt$collectAsState$1 snapshotStateKt$collectAsState$1 = new SnapshotStateKt$collectAsState$1(this.$context, this.$this_collectAsState, continuation);
        snapshotStateKt$collectAsState$1.L$0 = obj;
        return snapshotStateKt$collectAsState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProduceStateScope<R> produceStateScope, Continuation<? super Unit> continuation) {
        return ((SnapshotStateKt$collectAsState$1) create(produceStateScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: SnapshotState.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt$collectAsState$1$2", f = "SnapshotState.kt", i = {}, l = {908}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.runtime.SnapshotStateKt$collectAsState$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ProduceStateScope<R> $$this$produceState;
        final /* synthetic */ Flow<T> $this_collectAsState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Flow<? extends T> flow, ProduceStateScope<R> produceStateScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_collectAsState = flow;
            this.$$this$produceState = produceStateScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$this_collectAsState, this.$$this$produceState, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<T> flow = this.$this_collectAsState;
                final ProduceStateScope<R> produceStateScope = this.$$this$produceState;
                this.label = 1;
                if (flow.collect(new FlowCollector<T>() { // from class: androidx.compose.runtime.SnapshotStateKt$collectAsState$1$2$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(T t, Continuation<? super Unit> continuation) {
                        ProduceStateScope.this.setValue(t);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProduceStateScope produceStateScope = (ProduceStateScope) this.L$0;
            if (!Intrinsics.areEqual(this.$context, EmptyCoroutineContext.INSTANCE)) {
                this.label = 2;
                if (BuildersKt.withContext(this.$context, new AnonymousClass2(this.$this_collectAsState, produceStateScope, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.label = 1;
                if (this.$this_collectAsState.collect(new FlowCollector<T>() { // from class: androidx.compose.runtime.SnapshotStateKt$collectAsState$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(T t, Continuation<? super Unit> continuation) {
                        ProduceStateScope.this.setValue(t);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
