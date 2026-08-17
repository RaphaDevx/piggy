package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TapGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {}, l = {205, 208}, m = "withNullableTimeout", n = {}, s = {})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$withNullableTimeout$1<T> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    TapGestureDetectorKt$withNullableTimeout$1(Continuation<? super TapGestureDetectorKt$withNullableTimeout$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object withNullableTimeout;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        withNullableTimeout = TapGestureDetectorKt.withNullableTimeout(null, null, null, this);
        return withNullableTimeout;
    }
}
