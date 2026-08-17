package androidx.compose.foundation.lazy;

import androidx.compose.runtime.Composer;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyDsl.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001Bc\u0012#\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003\u00127\u0010\b\u001a3\u0012\u0004\u0012\u00020\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fRB\u0010\b\u001a3\u0012\u0004\u0012\u00020\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\r0\t¢\u0006\u0002\b\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R.\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/lazy/IntervalContent;", "", "key", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.INDEX, FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "getContent", "()Lkotlin/jvm/functions/Function2;", "getKey", "()Lkotlin/jvm/functions/Function1;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class IntervalContent {
    private final Function2<LazyItemScope, Integer, Function2<Composer, Integer, Unit>> content;
    private final Function1<Integer, Object> key;

    /* JADX WARN: Multi-variable type inference failed */
    public IntervalContent(Function1<? super Integer, ? extends Object> function1, Function2<? super LazyItemScope, ? super Integer, ? extends Function2<? super Composer, ? super Integer, Unit>> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.key = function1;
        this.content = content;
    }

    public final Function1<Integer, Object> getKey() {
        return this.key;
    }

    public final Function2<LazyItemScope, Integer, Function2<Composer, Integer, Unit>> getContent() {
        return this.content;
    }
}
