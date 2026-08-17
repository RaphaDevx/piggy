package androidx.compose.foundation.lazy;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyDsl.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u00142\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J5\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u001e¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010 Jm\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00062#\u0010\u001c\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001e21\u0010%\u001a-\u0012\u0004\u0012\u00020\u0017\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00130&¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u001fH\u0016¢\u0006\u0002\u0010'J5\u0010(\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u001e¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u001fH\u0017¢\u0006\u0002\u0010 R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006)"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScopeImpl;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "()V", "_headerIndexes", "", "", "headerIndexes", "", "getHeaderIndexes", "()Ljava/util/List;", "intervals", "Landroidx/compose/foundation/lazy/IntervalList;", "Landroidx/compose/foundation/lazy/IntervalContent;", "itemsCount", "getItemsCount", "()I", "getContent", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", FirebaseAnalytics.Param.INDEX, "scope", "Landroidx/compose/foundation/lazy/LazyItemScope;", "(ILandroidx/compose/foundation/lazy/LazyItemScope;)Lkotlin/jvm/functions/Function2;", "getKey", "", "item", "key", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "items", "count", "Lkotlin/ParameterName;", "name", "itemContent", "Lkotlin/Function2;", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "stickyHeader", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class LazyListScopeImpl implements LazyListScope, LazyListItemsProvider {
    private List<Integer> _headerIndexes;
    private final IntervalList<IntervalContent> intervals = new IntervalList<>();

    @Override // androidx.compose.foundation.lazy.LazyListItemsProvider
    public int getItemsCount() {
        return this.intervals.getTotalSize();
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemsProvider
    public List<Integer> getHeaderIndexes() {
        List<Integer> list = this._headerIndexes;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemsProvider
    public Object getKey(int index) {
        IntervalHolder<IntervalContent> intervalForIndex = this.intervals.intervalForIndex(index);
        int startIndex = index - intervalForIndex.getStartIndex();
        Function1<Integer, Object> key = intervalForIndex.getContent().getKey();
        Object invoke = key == null ? null : key.invoke(Integer.valueOf(startIndex));
        return invoke == null ? Lazy_androidKt.getDefaultLazyKeyFor(index) : invoke;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemsProvider
    public Function2<Composer, Integer, Unit> getContent(int index, LazyItemScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        IntervalHolder<IntervalContent> intervalForIndex = this.intervals.intervalForIndex(index);
        return intervalForIndex.getContent().getContent().invoke(scope, Integer.valueOf(index - intervalForIndex.getStartIndex()));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void items(int count, Function1<? super Integer, ? extends Object> key, final Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        this.intervals.add(count, new IntervalContent(key, new Function2<LazyItemScope, Integer, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.lazy.LazyListScopeImpl$items$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Function2<? super Composer, ? super Integer, ? extends Unit> invoke(LazyItemScope lazyItemScope, Integer num) {
                return invoke(lazyItemScope, num.intValue());
            }

            public final Function2<Composer, Integer, Unit> invoke(final LazyItemScope $receiver, final int i) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                final Function4<LazyItemScope, Integer, Composer, Integer, Unit> function4 = itemContent;
                return ComposableLambdaKt.composableLambdaInstance(-985542111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListScopeImpl$items$1.1
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
                        ComposerKt.sourceInformation(composer, "C199@9060L18:LazyDsl.kt#428nma");
                        if (((i2 & 11) ^ 2) == 0 && composer.getSkipping()) {
                            composer.skipToGroupEnd();
                        } else {
                            function4.invoke($receiver, Integer.valueOf(i), composer, 0);
                        }
                    }
                });
            }
        }));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void item(final Object key, final Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.intervals.add(1, new IntervalContent(key != null ? new Function1<Integer, Object>() { // from class: androidx.compose.foundation.lazy.LazyListScopeImpl$item$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Object invoke(int i) {
                return key;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }
        } : null, new Function2<LazyItemScope, Integer, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.lazy.LazyListScopeImpl$item$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Function2<? super Composer, ? super Integer, ? extends Unit> invoke(LazyItemScope lazyItemScope, Integer num) {
                return invoke(lazyItemScope, num.intValue());
            }

            public final Function2<Composer, Integer, Unit> invoke(final LazyItemScope $receiver, int i) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                final Function3<LazyItemScope, Composer, Integer, Unit> function3 = content;
                return ComposableLambdaKt.composableLambdaInstance(-985541160, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListScopeImpl$item$2.1
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
                        ComposerKt.sourceInformation(composer, "C209@9373L9:LazyDsl.kt#428nma");
                        if (((i2 & 11) ^ 2) == 0 && composer.getSkipping()) {
                            composer.skipToGroupEnd();
                        } else {
                            function3.invoke($receiver, composer, 0);
                        }
                    }
                });
            }
        }));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    @ExperimentalFoundationApi
    public void stickyHeader(Object key, Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        ArrayList arrayList = this._headerIndexes;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this._headerIndexes = arrayList;
        }
        arrayList.add(Integer.valueOf(getItemsCount()));
        item(key, content);
    }
}
