package androidx.compose.foundation.gestures;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.wallet.WalletConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TapGestureDetector.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0}, l = {WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, TypedValues.CycleType.TYPE_WAVE_OFFSET}, m = "waitForUpOrCancellation", n = {"$this$waitForUpOrCancellation"}, s = {"L$0"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$waitForUpOrCancellation$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    TapGestureDetectorKt$waitForUpOrCancellation$1(Continuation<? super TapGestureDetectorKt$waitForUpOrCancellation$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TapGestureDetectorKt.waitForUpOrCancellation(null, this);
    }
}
