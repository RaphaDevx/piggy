package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedVisibility.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0007\u001a\u00020\b*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0016R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Landroidx/compose/animation/AnimatedVisibilityScope;", "", "transition", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "getTransition", "()Landroidx/compose/animation/core/Transition;", "animateEnterExit", "Landroidx/compose/ui/Modifier;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@ExperimentalAnimationApi
/* loaded from: classes.dex */
public interface AnimatedVisibilityScope {
    Modifier animateEnterExit(Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition);

    Transition<EnterExitState> getTransition();

    /* compiled from: AnimatedVisibility.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ Modifier animateEnterExit$default(AnimatedVisibilityScope animatedVisibilityScope, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animateEnterExit");
            }
            if ((i & 1) != 0) {
                enterTransition = EnterExitTransitionKt.fadeIn$default(0.0f, null, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, null, false, 15, null));
            }
            if ((i & 2) != 0) {
                exitTransition = EnterExitTransitionKt.fadeOut$default(0.0f, null, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, null, false, 15, null));
            }
            return animatedVisibilityScope.animateEnterExit(modifier, enterTransition, exitTransition);
        }

        public static Modifier animateEnterExit(final AnimatedVisibilityScope animatedVisibilityScope, Modifier receiver, final EnterTransition enter, final ExitTransition exit) {
            Intrinsics.checkNotNullParameter(animatedVisibilityScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            Intrinsics.checkNotNullParameter(enter, "enter");
            Intrinsics.checkNotNullParameter(exit, "exit");
            return ComposedModifierKt.composed(receiver, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityScope$DefaultImpls$animateEnterExit$$inlined$debugInspectorInfo$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                    invoke2(inspectorInfo);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(InspectorInfo inspectorInfo) {
                    Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                    inspectorInfo.setName("animateEnterExit");
                    inspectorInfo.getProperties().set("enter", EnterTransition.this);
                    inspectorInfo.getProperties().set("exit", exit);
                }
            } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier composed, Composer composer, int i) {
                    Intrinsics.checkNotNullParameter(composed, "$this$composed");
                    composer.startReplaceableGroup(254972107);
                    ComposerKt.sourceInformation(composer, "C657@36536L27:AnimatedVisibility.kt#xbi5r1");
                    Modifier then = composed.then(EnterExitTransitionKt.createModifier(AnimatedVisibilityScope.this.getTransition(), enter, exit, composer, 0));
                    composer.endReplaceableGroup();
                    return then;
                }
            });
        }
    }
}
