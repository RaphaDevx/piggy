package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DragGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", i = {0, 0, 0}, l = {754, 792}, m = "awaitVerticalTouchSlopOrCancellation-jO51t88", n = {"pointer$iv", "touchSlop$iv", "totalPositionChange$iv"}, s = {"L$2", "F$0", "F$1"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$awaitVerticalTouchSlopOrCancellation$1 extends ContinuationImpl {
    float F$0;
    float F$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    DragGestureDetectorKt$awaitVerticalTouchSlopOrCancellation$1(Continuation<? super DragGestureDetectorKt$awaitVerticalTouchSlopOrCancellation$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorKt.m192awaitVerticalTouchSlopOrCancellationjO51t88(null, 0L, null, this);
    }
}
