package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: LazyListScrolling.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.lazy.LazyListScrollingKt$doSmoothScrollToItem$2", f = "LazyListScrolling.kt", i = {0, 0, 0, 0, 0}, l = {ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL, 188}, m = "invokeSuspend", n = {"prevVelocity", "loops", "anim", "forward", TypedValues.AttributesType.S_TARGET}, s = {"L$2", "L$3", "L$4", "I$0", "F$1"})
/* loaded from: classes.dex */
final class LazyListScrollingKt$doSmoothScrollToItem$2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $index;
    final /* synthetic */ int $scrollOffset;
    final /* synthetic */ LazyListState $this_doSmoothScrollToItem;
    float F$0;
    float F$1;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyListScrollingKt$doSmoothScrollToItem$2(LazyListState lazyListState, int i, AnimationSpec<Float> animationSpec, int i2, Continuation<? super LazyListScrollingKt$doSmoothScrollToItem$2> continuation) {
        super(2, continuation);
        this.$this_doSmoothScrollToItem = lazyListState;
        this.$index = i;
        this.$animationSpec = animationSpec;
        this.$scrollOffset = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LazyListScrollingKt$doSmoothScrollToItem$2 lazyListScrollingKt$doSmoothScrollToItem$2 = new LazyListScrollingKt$doSmoothScrollToItem$2(this.$this_doSmoothScrollToItem, this.$index, this.$animationSpec, this.$scrollOffset, continuation);
        lazyListScrollingKt$doSmoothScrollToItem$2.L$0 = obj;
        return lazyListScrollingKt$doSmoothScrollToItem$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((LazyListScrollingKt$doSmoothScrollToItem$2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x013f: IGET (r15 I:float) = (r11 I:kotlin.jvm.internal.Ref$FloatRef) (LINE:181) kotlin.jvm.internal.Ref.FloatRef.element float, block:B:43:0x013f */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x017a: INVOKE 
      (r3v2 ?? I:androidx.compose.foundation.lazy.LazyListScrollingKt$doSmoothScrollToItem$2$3)
      (r0v5 ?? I:float)
      (r4v2 ?? I:kotlin.jvm.internal.Ref$FloatRef)
      (r13 I:androidx.compose.foundation.gestures.ScrollScope)
     DIRECT call: androidx.compose.foundation.lazy.LazyListScrollingKt$doSmoothScrollToItem$2.3.<init>(float, kotlin.jvm.internal.Ref$FloatRef, androidx.compose.foundation.gestures.ScrollScope):void A[MD:(float, kotlin.jvm.internal.Ref$FloatRef, androidx.compose.foundation.gestures.ScrollScope):void (m)], block:B:46:0x0176 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00aa A[Catch: ItemFoundInScroll -> 0x013e, TryCatch #0 {ItemFoundInScroll -> 0x013e, blocks: (B:14:0x0038, B:15:0x0124, B:16:0x00a6, B:18:0x00aa, B:21:0x00d9, B:24:0x00e2, B:30:0x0081, B:32:0x008b, B:37:0x009c, B:40:0x0138, B:41:0x013d), top: B:2:0x000b }] */
    /* JADX WARN: Type inference failed for: r11v0, types: [kotlin.jvm.internal.Ref$FloatRef] */
    /* JADX WARN: Type inference failed for: r13v0, types: [androidx.compose.foundation.gestures.ScrollScope] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0121 -> B:15:0x0124). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r38) {
        /*
            Method dump skipped, instructions count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListScrollingKt$doSmoothScrollToItem$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
