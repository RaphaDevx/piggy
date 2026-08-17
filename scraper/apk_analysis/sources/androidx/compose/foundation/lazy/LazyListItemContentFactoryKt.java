package androidx.compose.foundation.lazy;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DensityKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListItemContentFactory.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\u0010\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"InitialLazyItemsScopeImpl", "Landroidx/compose/foundation/lazy/LazyItemScopeImpl;", "rememberItemContentFactory", "Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", "stateOfItemsProvider", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/runtime/State;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListItemContentFactoryKt {
    private static final LazyItemScopeImpl InitialLazyItemsScopeImpl = new LazyItemScopeImpl(DensityKt.Density(0.0f, 0.0f), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), null);

    public static final LazyListItemContentFactory rememberItemContentFactory(State<? extends LazyListItemsProvider> stateOfItemsProvider, LazyListState state, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(stateOfItemsProvider, "stateOfItemsProvider");
        Intrinsics.checkNotNullParameter(state, "state");
        composer.startReplaceableGroup(149230656);
        ComposerKt.sourceInformation(composer, "C(rememberItemContentFactory)P(1)39@1536L29,40@1584L116:LazyListItemContentFactory.kt#428nma");
        SaveableStateHolder rememberSaveableStateHolder = SaveableStateHolderKt.rememberSaveableStateHolder(composer, 0);
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(stateOfItemsProvider);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new LazyListItemContentFactory(rememberSaveableStateHolder, stateOfItemsProvider);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        LazyListItemContentFactory lazyListItemContentFactory = (LazyListItemContentFactory) rememberedValue;
        lazyListItemContentFactory.updateKeyIndexMappingForVisibleItems(state);
        composer.endReplaceableGroup();
        return lazyListItemContentFactory;
    }
}
