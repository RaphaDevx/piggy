package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Transformable.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0, 0}, l = {162}, m = "awaitTwoDowns", n = {"$this$awaitTwoDowns", "firstDown", "requireUnconsumed"}, s = {"L$0", "L$1", "Z$0"})
/* loaded from: classes.dex */
final class TransformableKt$awaitTwoDowns$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    TransformableKt$awaitTwoDowns$1(Continuation<? super TransformableKt$awaitTwoDowns$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitTwoDowns;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitTwoDowns = TransformableKt.awaitTwoDowns(null, false, this);
        return awaitTwoDowns;
    }
}
