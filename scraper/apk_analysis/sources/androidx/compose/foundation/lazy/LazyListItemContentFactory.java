package androidx.compose.foundation.lazy;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.SentryThread;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListItemContentFactory.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001fB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J&\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\b\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001¢\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u00060\fR\u00020\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", "", "saveableStateHolder", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "itemsProvider", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "(Landroidx/compose/runtime/saveable/SaveableStateHolder;Landroidx/compose/runtime/State;)V", "itemScope", "Landroidx/compose/foundation/lazy/LazyItemScopeImpl;", "lambdasCache", "", "Landroidx/compose/foundation/lazy/LazyListItemContentFactory$CachedItemContent;", "getContent", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", FirebaseAnalytics.Param.INDEX, "", "key", "(ILjava/lang/Object;)Lkotlin/jvm/functions/Function2;", "updateItemScope", "density", "Landroidx/compose/ui/unit/Density;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "updateItemScope-0kLqBqw", "(Landroidx/compose/ui/unit/Density;J)V", "updateKeyIndexMappingForVisibleItems", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/lazy/LazyListState;", "CachedItemContent", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListItemContentFactory {
    private LazyItemScopeImpl itemScope;
    private State<? extends LazyListItemsProvider> itemsProvider;
    private final Map<Object, CachedItemContent> lambdasCache;
    private final SaveableStateHolder saveableStateHolder;

    public LazyListItemContentFactory(SaveableStateHolder saveableStateHolder, State<? extends LazyListItemsProvider> itemsProvider) {
        LazyItemScopeImpl lazyItemScopeImpl;
        Intrinsics.checkNotNullParameter(saveableStateHolder, "saveableStateHolder");
        Intrinsics.checkNotNullParameter(itemsProvider, "itemsProvider");
        this.saveableStateHolder = saveableStateHolder;
        this.itemsProvider = itemsProvider;
        this.lambdasCache = new LinkedHashMap();
        lazyItemScopeImpl = LazyListItemContentFactoryKt.InitialLazyItemsScopeImpl;
        this.itemScope = lazyItemScopeImpl;
    }

    public final void updateKeyIndexMappingForVisibleItems(LazyListState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        LazyListItemsProvider value = this.itemsProvider.getValue();
        int itemsCount = value.getItemsCount();
        if (itemsCount <= 0) {
            return;
        }
        state.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(value);
        int m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release = state.m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release();
        int min = Math.min(itemsCount, state.getVisibleItemsCount() + m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release);
        if (m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release >= min) {
            return;
        }
        while (true) {
            int i = m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release + 1;
            CachedItemContent cachedItemContent = this.lambdasCache.get(value.getKey(m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release));
            if (cachedItemContent != null) {
                cachedItemContent.setIndex(m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release);
            }
            if (i >= min) {
                return;
            } else {
                m397getFirstVisibleItemIndexNonObservablejQJCoq8$foundation_release = i;
            }
        }
    }

    public final Function2<Composer, Integer, Unit> getContent(int index, Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        CachedItemContent cachedItemContent = this.lambdasCache.get(key);
        if (cachedItemContent != null && cachedItemContent.getIndex() == index) {
            return cachedItemContent.getContent();
        }
        CachedItemContent cachedItemContent2 = new CachedItemContent(this, index, this.itemScope, key);
        this.lambdasCache.put(key, cachedItemContent2);
        return cachedItemContent2.getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyListItemContentFactory.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007R\u001e\u0010\b\u001a\r\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR+\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemContentFactory$CachedItemContent;", "", "initialIndex", "", "scope", "Landroidx/compose/foundation/lazy/LazyItemScopeImpl;", "key", "(Landroidx/compose/foundation/lazy/LazyListItemContentFactory;ILandroidx/compose/foundation/lazy/LazyItemScopeImpl;Ljava/lang/Object;)V", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getContent", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "<set-?>", FirebaseAnalytics.Param.INDEX, "getIndex", "()I", "setIndex", "(I)V", "index$delegate", "Landroidx/compose/runtime/MutableState;", "getKey", "()Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    final class CachedItemContent {
        private final Function2<Composer, Integer, Unit> content;

        /* renamed from: index$delegate, reason: from kotlin metadata */
        private final MutableState index;
        private final Object key;
        private final LazyItemScopeImpl scope;
        final /* synthetic */ LazyListItemContentFactory this$0;

        public CachedItemContent(final LazyListItemContentFactory this$0, int i, LazyItemScopeImpl scope, Object key) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(scope, "scope");
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = this$0;
            this.scope = scope;
            this.key = key;
            this.index = SnapshotStateKt.mutableStateOf$default(Integer.valueOf(i), null, 2, null);
            this.content = ComposableLambdaKt.composableLambdaInstance(-985538056, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListItemContentFactory$CachedItemContent$content$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i2) {
                    State state;
                    LazyItemScopeImpl lazyItemScopeImpl;
                    SaveableStateHolder saveableStateHolder;
                    ComposerKt.sourceInformation(composer, "C:LazyListItemContentFactory.kt#428nma");
                    if (((i2 & 11) ^ 2) != 0 || !composer.getSkipping()) {
                        state = LazyListItemContentFactory.this.itemsProvider;
                        LazyListItemsProvider lazyListItemsProvider = (LazyListItemsProvider) state.getValue();
                        if (this.getIndex() < lazyListItemsProvider.getItemsCount()) {
                            composer.startReplaceableGroup(1025808653);
                            ComposerKt.sourceInformation(composer, "");
                            Object key2 = lazyListItemsProvider.getKey(this.getIndex());
                            if (Intrinsics.areEqual(key2, this.getKey())) {
                                composer.startReplaceableGroup(1025808746);
                                ComposerKt.sourceInformation(composer, "112@4595L35");
                                int index = this.getIndex();
                                lazyItemScopeImpl = this.scope;
                                Function2<Composer, Integer, Unit> content = lazyListItemsProvider.getContent(index, lazyItemScopeImpl);
                                saveableStateHolder = LazyListItemContentFactory.this.saveableStateHolder;
                                saveableStateHolder.SaveableStateProvider(key2, content, composer, 520);
                                composer.endReplaceableGroup();
                            } else {
                                composer.startReplaceableGroup(1025808914);
                                composer.endReplaceableGroup();
                            }
                            composer.endReplaceableGroup();
                            return;
                        }
                        composer.startReplaceableGroup(1025808928);
                        composer.endReplaceableGroup();
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            });
        }

        public final Object getKey() {
            return this.key;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final int getIndex() {
            return ((Number) this.index.getValue()).intValue();
        }

        public final void setIndex(int i) {
            this.index.setValue(Integer.valueOf(i));
        }

        public final Function2<Composer, Integer, Unit> getContent() {
            return this.content;
        }
    }

    /* renamed from: updateItemScope-0kLqBqw, reason: not valid java name */
    public final void m387updateItemScope0kLqBqw(Density density, long constraints) {
        Intrinsics.checkNotNullParameter(density, "density");
        if (Intrinsics.areEqual(this.itemScope.getDensity(), density) && Constraints.m2970equalsimpl0(this.itemScope.m386getConstraintsmsEJaDk(), constraints)) {
            return;
        }
        this.itemScope = new LazyItemScopeImpl(density, constraints, null);
        this.lambdasCache.clear();
    }
}
