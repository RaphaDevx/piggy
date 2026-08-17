package com.scandit.datacapture.frameworks.core.common;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: BaseFrameworkModule.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlin/Function0;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule$getPostSpecificViewCreationActions$1", f = "BaseFrameworkModule.kt", i = {0}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {"$this$sequence"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class BaseFrameworkModule$getPostSpecificViewCreationActions$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Function0<? extends Unit>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ConcurrentLinkedQueue<Function0<Unit>> $queue;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BaseFrameworkModule$getPostSpecificViewCreationActions$1(ConcurrentLinkedQueue<Function0<Unit>> concurrentLinkedQueue, Continuation<? super BaseFrameworkModule$getPostSpecificViewCreationActions$1> continuation) {
        super(2, continuation);
        this.$queue = concurrentLinkedQueue;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BaseFrameworkModule$getPostSpecificViewCreationActions$1 baseFrameworkModule$getPostSpecificViewCreationActions$1 = new BaseFrameworkModule$getPostSpecificViewCreationActions$1(this.$queue, continuation);
        baseFrameworkModule$getPostSpecificViewCreationActions$1.L$0 = obj;
        return baseFrameworkModule$getPostSpecificViewCreationActions$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Function0<? extends Unit>> sequenceScope, Continuation<? super Unit> continuation) {
        return invoke2((SequenceScope<? super Function0<Unit>>) sequenceScope, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(SequenceScope<? super Function0<Unit>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((BaseFrameworkModule$getPostSpecificViewCreationActions$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SequenceScope sequenceScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceScope = (SequenceScope) this.L$0;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (true) {
            if (this.$queue == null || !(!r4.isEmpty())) {
                break;
            }
            Function0<Unit> poll = this.$queue.poll();
            if (poll != null) {
                this.L$0 = sequenceScope;
                this.label = 1;
                if (sequenceScope.yield(poll, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
