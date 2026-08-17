package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DragGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", i = {0}, l = {754}, m = "horizontalDrag-jO51t88", n = {"pointer$iv$iv"}, s = {"L$2"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$horizontalDrag$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    DragGestureDetectorKt$horizontalDrag$1(Continuation<? super DragGestureDetectorKt$horizontalDrag$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DragGestureDetectorKt.m195horizontalDragjO51t88(null, 0L, null, this);
    }
}
