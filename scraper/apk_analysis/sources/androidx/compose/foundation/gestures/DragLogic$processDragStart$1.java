package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Draggable.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragLogic", f = "Draggable.kt", i = {0, 0, 0, 1}, l = {357, 360, 362}, m = "processDragStart", n = {"this", "$this$processDragStart", "event", DataSources.EventTypeValue.INTERACTION_EVENT_TYPE}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes.dex */
final class DragLogic$processDragStart$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DragLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragLogic$processDragStart$1(DragLogic dragLogic, Continuation<? super DragLogic$processDragStart$1> continuation) {
        super(continuation);
        this.this$0 = dragLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.processDragStart(null, null, this);
    }
}
