package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.b;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Logic.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LogicKt", f = "Logic.kt", i = {}, l = {b.m}, m = "none", n = {}, s = {})
/* loaded from: classes4.dex */
final class FlowKt__LogicKt$none$1<T> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    FlowKt__LogicKt$none$1(Continuation<? super FlowKt__LogicKt$none$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.none(null, null, this);
    }
}
