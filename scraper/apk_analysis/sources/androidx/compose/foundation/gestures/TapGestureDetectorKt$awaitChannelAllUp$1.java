package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TapGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 1}, l = {263, 265}, m = "awaitChannelAllUp", n = {"channel", "channel"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$awaitChannelAllUp$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    TapGestureDetectorKt$awaitChannelAllUp$1(Continuation<? super TapGestureDetectorKt$awaitChannelAllUp$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitChannelAllUp;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitChannelAllUp = TapGestureDetectorKt.awaitChannelAllUp(null, null, this);
        return awaitChannelAllUp;
    }
}
