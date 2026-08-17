package androidx.compose.foundation.gestures;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TapGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 0, 0, 0}, l = {310, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 322, 343}, m = "translatePointerEventsToChannel", n = {"$this$translatePointerEventsToChannel", "scope", "channel", "detectDownsOnly", "consumeAllUntilUp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$translatePointerEventsToChannel$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    TapGestureDetectorKt$translatePointerEventsToChannel$1(Continuation<? super TapGestureDetectorKt$translatePointerEventsToChannel$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object translatePointerEventsToChannel;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        translatePointerEventsToChannel = TapGestureDetectorKt.translatePointerEventsToChannel(null, null, null, null, null, this);
        return translatePointerEventsToChannel;
    }
}
