package androidx.compose.foundation.lazy;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListPrefetcher.android.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"LazyListPrefetcher", "", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "stateOfItemsProvider", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "itemContentFactory", "Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", "subcomposeLayoutState", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/runtime/State;Landroidx/compose/foundation/lazy/LazyListItemContentFactory;Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListPrefetcher_androidKt {
    public static final void LazyListPrefetcher(final LazyListState lazyListState, final State<? extends LazyListItemsProvider> stateOfItemsProvider, final LazyListItemContentFactory itemContentFactory, final SubcomposeLayoutState subcomposeLayoutState, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Intrinsics.checkNotNullParameter(stateOfItemsProvider, "stateOfItemsProvider");
        Intrinsics.checkNotNullParameter(itemContentFactory, "itemContentFactory");
        Intrinsics.checkNotNullParameter(subcomposeLayoutState, "subcomposeLayoutState");
        Composer startRestartGroup = composer.startRestartGroup(-2138645958);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyListPrefetcher)P(1,2)44@1732L7,45@1744L243:LazyListPrefetcher.android.kt#428nma");
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume = startRestartGroup.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        View view = (View) consume;
        int i2 = SubcomposeLayoutState.$stable;
        startRestartGroup.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean changed = startRestartGroup.changed(subcomposeLayoutState) | startRestartGroup.changed(lazyListState) | startRestartGroup.changed(view);
        Object rememberedValue = startRestartGroup.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            startRestartGroup.updateRememberedValue(new LazyListPrefetcher(subcomposeLayoutState, lazyListState, stateOfItemsProvider, itemContentFactory, view));
        }
        startRestartGroup.endReplaceableGroup();
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListPrefetcher_androidKt$LazyListPrefetcher$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                LazyListPrefetcher_androidKt.LazyListPrefetcher(LazyListState.this, stateOfItemsProvider, itemContentFactory, subcomposeLayoutState, composer2, i | 1);
            }
        });
    }
}
