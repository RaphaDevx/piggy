package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TapGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 1, 1}, l = {221, 223}, m = "awaitChannelDown", n = {"onlyDownsSignal", "channel", "onlyDownsSignal", "channel"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$awaitChannelDown$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    TapGestureDetectorKt$awaitChannelDown$1(Continuation<? super TapGestureDetectorKt$awaitChannelDown$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitChannelDown;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitChannelDown = TapGestureDetectorKt.awaitChannelDown(null, null, this);
        return awaitChannelDown;
    }
}
