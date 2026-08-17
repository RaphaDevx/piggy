package androidx.compose.foundation.lazy;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.runtime.Composer;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* compiled from: LazyGrid.kt */
@ExperimentalFoundationApi
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH&¢\u0006\u0002\u0010\tJH\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f21\u0010\r\u001a-\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH&¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/lazy/LazyGridScope;", "", "item", "", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)V", "items", "count", "", "itemContent", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.INDEX, "(ILkotlin/jvm/functions/Function4;)V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface LazyGridScope {
    void item(Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content);

    void items(int count, Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent);
}
