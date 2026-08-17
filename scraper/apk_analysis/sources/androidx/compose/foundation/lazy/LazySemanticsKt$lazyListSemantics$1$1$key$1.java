package androidx.compose.foundation.lazy;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: LazySemantics.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
/* synthetic */ class LazySemanticsKt$lazyListSemantics$1$1$key$1 extends FunctionReferenceImpl implements Function1<Integer, Object> {
    LazySemanticsKt$lazyListSemantics$1$1$key$1(LazyListItemsProvider lazyListItemsProvider) {
        super(1, lazyListItemsProvider, LazyListItemsProvider.class, "getKey", "getKey(I)Ljava/lang/Object;", 0);
    }

    public final Object invoke(int i) {
        return ((LazyListItemsProvider) this.receiver).getKey(i);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
        return invoke(num.intValue());
    }
}
