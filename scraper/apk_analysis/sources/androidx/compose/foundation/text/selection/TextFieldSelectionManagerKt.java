package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\u0010\t\u001a\u0014\u0010\n\u001a\u00020\u0003*\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u000b"}, d2 = {"TextFieldSelectionHandle", "", "isStartHandle", "", "directions", "Lkotlin/Pair;", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(ZLkotlin/Pair;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "isSelectionHandleInVisibleBound", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldSelectionManagerKt {
    public static final void TextFieldSelectionHandle(final boolean z, final Pair<? extends ResolvedTextDirection, ? extends ResolvedTextDirection> directions, final TextFieldSelectionManager manager, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(directions, "directions");
        Intrinsics.checkNotNullParameter(manager, "manager");
        Composer startRestartGroup = composer.startRestartGroup(-1630622478);
        ComposerKt.sourceInformation(startRestartGroup, "C(TextFieldSelectionHandle)P(1)678@25182L90,681@25277L418:TextFieldSelectionManager.kt#eksfi3");
        Boolean valueOf = Boolean.valueOf(z);
        startRestartGroup.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean changed = startRestartGroup.changed(valueOf) | startRestartGroup.changed(manager);
        Object rememberedValue = startRestartGroup.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = manager.handleDragObserver$foundation_release(z);
            startRestartGroup.updateRememberedValue(rememberedValue);
        }
        startRestartGroup.endReplaceableGroup();
        TextDragObserver textDragObserver = (TextDragObserver) rememberedValue;
        Offset m1014boximpl = Offset.m1014boximpl(manager.m586getHandlePositiontuRUvjQ$foundation_release(true));
        Offset m1014boximpl2 = Offset.m1014boximpl(manager.m586getHandlePositiontuRUvjQ$foundation_release(false));
        boolean m2765getReversedimpl = TextRange.m2765getReversedimpl(manager.getValue().getSelection());
        Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textDragObserver, new TextFieldSelectionManagerKt$TextFieldSelectionHandle$1(textDragObserver, null));
        int i2 = i << 6;
        AndroidSelectionHandles_androidKt.m537SelectionHandleVGSPTLc(m1014boximpl, m1014boximpl2, z, directions, m2765getReversedimpl, pointerInput, null, startRestartGroup, (i2 & 896) | 1572864 | (i2 & 7168));
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$2
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
                TextFieldSelectionManagerKt.TextFieldSelectionHandle(z, directions, manager, composer2, i | 1);
            }
        });
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        LayoutCoordinates layoutCoordinates;
        Intrinsics.checkNotNullParameter(textFieldSelectionManager, "<this>");
        TextFieldState state = textFieldSelectionManager.getState();
        Rect rect = null;
        if (state != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
            rect = SelectionManagerKt.visibleBounds(layoutCoordinates);
        }
        if (rect == null) {
            return false;
        }
        return SelectionManagerKt.m574containsInclusiveUv8p0NA(rect, textFieldSelectionManager.m586getHandlePositiontuRUvjQ$foundation_release(z));
    }
}
