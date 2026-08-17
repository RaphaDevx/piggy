package androidx.compose.foundation.lazy;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyGrid.kt */
@ExperimentalFoundationApi
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0006¢\u0006\u0002\u0010\u0012J+\u0010\u0013\u001a\u00020\t2\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0016¢\u0006\u0002\u0010\u0016JH\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u000721\u0010\u0019\u001a-\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0016¢\u0006\u0002\u0010\u001cR6\u0010\u0003\u001a*\u0012&\u0012$\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n0\u0005¢\u0006\u0002\b\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/lazy/LazyGridScopeImpl;", "Landroidx/compose/foundation/lazy/LazyGridScope;", "()V", "intervals", "Landroidx/compose/foundation/lazy/IntervalList;", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "totalSize", "getTotalSize", "()I", "contentFor", FirebaseAnalytics.Param.INDEX, "scope", "(ILandroidx/compose/foundation/lazy/LazyItemScope;)Lkotlin/jvm/functions/Function2;", "item", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function3;)V", "items", "count", "itemContent", "Lkotlin/ParameterName;", "name", "(ILkotlin/jvm/functions/Function4;)V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyGridScopeImpl implements LazyGridScope {
    private final IntervalList<Function2<LazyItemScope, Integer, Function2<Composer, Integer, Unit>>> intervals = new IntervalList<>();

    public final int getTotalSize() {
        return this.intervals.getTotalSize();
    }

    public final Function2<Composer, Integer, Unit> contentFor(int index, LazyItemScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        IntervalHolder<Function2<LazyItemScope, Integer, Function2<Composer, Integer, Unit>>> intervalForIndex = this.intervals.intervalForIndex(index);
        return intervalForIndex.getContent().invoke(scope, Integer.valueOf(index - intervalForIndex.getStartIndex()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.lazy.LazyGridScope
    public void item(final Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.intervals.add(1, new Function2<LazyItemScope, Integer, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.lazy.LazyGridScopeImpl$item$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Function2<? super Composer, ? super Integer, ? extends Unit> invoke(LazyItemScope lazyItemScope, Integer num) {
                return invoke(lazyItemScope, num.intValue());
            }

            public final Function2<Composer, Integer, Unit> invoke(final LazyItemScope add, int i) {
                Intrinsics.checkNotNullParameter(add, "$this$add");
                final Function3<LazyItemScope, Composer, Integer, Unit> function3 = content;
                return ComposableLambdaKt.composableLambdaInstance(-985535238, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridScopeImpl$item$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i2) {
                        ComposerKt.sourceInformation(composer, "C229@7611L9:LazyGrid.kt#428nma");
                        if (((i2 & 11) ^ 2) == 0 && composer.getSkipping()) {
                            composer.skipToGroupEnd();
                        } else {
                            function3.invoke(add, composer, 0);
                        }
                    }
                });
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.lazy.LazyGridScope
    public void items(int count, final Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        this.intervals.add(count, new Function2<LazyItemScope, Integer, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.lazy.LazyGridScopeImpl$items$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Function2<? super Composer, ? super Integer, ? extends Unit> invoke(LazyItemScope lazyItemScope, Integer num) {
                return invoke(lazyItemScope, num.intValue());
            }

            public final Function2<Composer, Integer, Unit> invoke(final LazyItemScope add, final int i) {
                Intrinsics.checkNotNullParameter(add, "$this$add");
                final Function4<LazyItemScope, Integer, Composer, Integer, Unit> function4 = itemContent;
                return ComposableLambdaKt.composableLambdaInstance(-985534678, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridScopeImpl$items$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i2) {
                        ComposerKt.sourceInformation(composer, "C234@7787L15:LazyGrid.kt#428nma");
                        if (((i2 & 11) ^ 2) == 0 && composer.getSkipping()) {
                            composer.skipToGroupEnd();
                        } else {
                            function4.invoke(add, Integer.valueOf(i), composer, 0);
                        }
                    }
                });
            }
        });
    }
}
