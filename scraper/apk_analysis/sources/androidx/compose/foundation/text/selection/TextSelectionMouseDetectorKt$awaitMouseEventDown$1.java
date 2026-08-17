package androidx.compose.foundation.text.selection;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TextSelectionMouseDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt", f = "TextSelectionMouseDetector.kt", i = {0, 0}, l = {129}, m = "awaitMouseEventDown", n = {"$this$awaitMouseEventDown", "finalPass"}, s = {"L$0", "Z$0"})
/* loaded from: classes.dex */
final class TextSelectionMouseDetectorKt$awaitMouseEventDown$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    TextSelectionMouseDetectorKt$awaitMouseEventDown$1(Continuation<? super TextSelectionMouseDetectorKt$awaitMouseEventDown$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitMouseEventDown;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitMouseEventDown = TextSelectionMouseDetectorKt.awaitMouseEventDown(null, false, this);
        return awaitMouseEventDown;
    }
}
