package androidx.constraintlayout.compose;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* compiled from: MotionLayout.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.constraintlayout.compose.MotionLayoutKt$MotionLayoutCore$2", f = "MotionLayout.kt", i = {}, l = {237, 247}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class MotionLayoutKt$MotionLayoutCore$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $animateToEnd$delegate;
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ Channel<ConstraintSet> $channel;
    final /* synthetic */ MutableState<ConstraintSet> $end$delegate;
    final /* synthetic */ Function0<Unit> $finishedAnimationListener;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $progress;
    final /* synthetic */ MutableState<ConstraintSet> $start$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MotionLayoutKt$MotionLayoutCore$2(Channel<ConstraintSet> channel, Animatable<Float, AnimationVector1D> animatable, AnimationSpec<Float> animationSpec, Function0<Unit> function0, MutableState<Boolean> mutableState, MutableState<ConstraintSet> mutableState2, MutableState<ConstraintSet> mutableState3, Continuation<? super MotionLayoutKt$MotionLayoutCore$2> continuation) {
        super(2, continuation);
        this.$channel = channel;
        this.$progress = animatable;
        this.$animationSpec = animationSpec;
        this.$finishedAnimationListener = function0;
        this.$animateToEnd$delegate = mutableState;
        this.$start$delegate = mutableState2;
        this.$end$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MotionLayoutKt$MotionLayoutCore$2(this.$channel, this.$progress, this.$animationSpec, this.$finishedAnimationListener, this.$animateToEnd$delegate, this.$start$delegate, this.$end$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MotionLayoutKt$MotionLayoutCore$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00c5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0085 -> B:9:0x0034). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b2 -> B:6:0x00b5). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r0 = r16
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L2b
            if (r2 == r4) goto L21
            if (r2 != r3) goto L19
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            kotlin.ResultKt.throwOnFailure(r17)
            goto Lb5
        L19:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L21:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            kotlin.ResultKt.throwOnFailure(r17)
            r5 = r17
            goto L42
        L2b:
            kotlin.ResultKt.throwOnFailure(r17)
            kotlinx.coroutines.channels.Channel<androidx.constraintlayout.compose.ConstraintSet> r2 = r0.$channel
            kotlinx.coroutines.channels.ChannelIterator r2 = r2.iterator()
        L34:
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r5 = r2.hasNext(r5)
            if (r5 != r1) goto L42
            return r1
        L42:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto Lca
            java.lang.Object r5 = r2.next()
            androidx.constraintlayout.compose.ConstraintSet r5 = (androidx.constraintlayout.compose.ConstraintSet) r5
            kotlinx.coroutines.channels.Channel<androidx.constraintlayout.compose.ConstraintSet> r6 = r0.$channel
            java.lang.Object r6 = r6.mo5448tryReceivePtdJZtk()
            java.lang.Object r6 = kotlinx.coroutines.channels.ChannelResult.m5460getOrNullimpl(r6)
            androidx.constraintlayout.compose.ConstraintSet r6 = (androidx.constraintlayout.compose.ConstraintSet) r6
            if (r6 != 0) goto L5f
            goto L60
        L5f:
            r5 = r6
        L60:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r6 = r0.$animateToEnd$delegate
            boolean r6 = androidx.constraintlayout.compose.MotionLayoutKt.m3286access$MotionLayoutCore$lambda16(r6)
            if (r6 == 0) goto L6b
            r6 = 1065353216(0x3f800000, float:1.0)
            goto L6c
        L6b:
            r6 = 0
        L6c:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r0.$animateToEnd$delegate
            boolean r7 = androidx.constraintlayout.compose.MotionLayoutKt.m3286access$MotionLayoutCore$lambda16(r7)
            if (r7 == 0) goto L7b
            androidx.compose.runtime.MutableState<androidx.constraintlayout.compose.ConstraintSet> r7 = r0.$start$delegate
            androidx.constraintlayout.compose.ConstraintSet r7 = androidx.constraintlayout.compose.MotionLayoutKt.m3290access$MotionLayoutCore$lambda8(r7)
            goto L81
        L7b:
            androidx.compose.runtime.MutableState<androidx.constraintlayout.compose.ConstraintSet> r7 = r0.$end$delegate
            androidx.constraintlayout.compose.ConstraintSet r7 = androidx.constraintlayout.compose.MotionLayoutKt.m3284access$MotionLayoutCore$lambda11(r7)
        L81:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r7)
            if (r7 != 0) goto L34
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r0.$animateToEnd$delegate
            boolean r7 = androidx.constraintlayout.compose.MotionLayoutKt.m3286access$MotionLayoutCore$lambda16(r7)
            if (r7 == 0) goto L95
            androidx.compose.runtime.MutableState<androidx.constraintlayout.compose.ConstraintSet> r7 = r0.$end$delegate
            androidx.constraintlayout.compose.MotionLayoutKt.m3285access$MotionLayoutCore$lambda12(r7, r5)
            goto L9a
        L95:
            androidx.compose.runtime.MutableState<androidx.constraintlayout.compose.ConstraintSet> r7 = r0.$start$delegate
            androidx.constraintlayout.compose.MotionLayoutKt.m3291access$MotionLayoutCore$lambda9(r7, r5)
        L9a:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r8 = r0.$progress
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            androidx.compose.animation.core.AnimationSpec<java.lang.Float> r10 = r0.$animationSpec
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r0.L$0 = r2
            r0.label = r3
            r11 = 0
            r12 = 0
            r14 = 12
            r15 = 0
            java.lang.Object r5 = androidx.compose.animation.core.Animatable.animateTo$default(r8, r9, r10, r11, r12, r13, r14, r15)
            if (r5 != r1) goto Lb5
            return r1
        Lb5:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r5 = r0.$animateToEnd$delegate
            boolean r6 = androidx.constraintlayout.compose.MotionLayoutKt.m3286access$MotionLayoutCore$lambda16(r5)
            r6 = r6 ^ r4
            androidx.constraintlayout.compose.MotionLayoutKt.m3287access$MotionLayoutCore$lambda17(r5, r6)
            kotlin.jvm.functions.Function0<kotlin.Unit> r5 = r0.$finishedAnimationListener
            if (r5 != 0) goto Lc5
            goto L34
        Lc5:
            r5.invoke()
            goto L34
        Lca:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.compose.MotionLayoutKt$MotionLayoutCore$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
