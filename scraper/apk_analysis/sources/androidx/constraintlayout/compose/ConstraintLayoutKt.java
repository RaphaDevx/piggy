package androidx.constraintlayout.compose;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ScaleKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.compose.Dimension;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.protocol.SentryThread;
import java.util.EnumSet;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: ConstraintLayout.kt */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aE\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001e\b\u0004\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001an\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u00012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0010\b\n\u0010 \u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010!2\u0013\b\b\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u000f0!¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\"\u001a'\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001b2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0018\u001a\u0018\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001b2\b\b\u0001\u0010'\u001a\u00020(\u001a\u001f\u0010#\u001a\u00020\u001b2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0018\u001a\u0010\u0010#\u001a\u00020\u001b2\b\b\u0001\u0010'\u001a\u00020(\u001a#\u0010#\u001a\u00020\u001b2\b\b\u0001\u0010\u0014\u001a\u00020(2\n\b\u0003\u0010)\u001a\u0004\u0018\u00010(H\u0007¢\u0006\u0002\u0010*\u001a\u001e\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H\u0000\u001a\b\u00101\u001a\u000202H\u0000\u001a3\u00103\u001a\u0002042\u0006\u0010\u0012\u001a\u00020\u00132\f\u00105\u001a\b\u0012\u0004\u0012\u000207062\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u000209H\u0001¢\u0006\u0002\u0010:\u001aE\u00103\u001a\u0014\u0012\u0004\u0012\u000204\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0!0;2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00162\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u0001062\u0006\u00108\u001a\u000209H\u0001¢\u0006\u0002\u0010>\u001a\u001f\u0010?\u001a\u00020\u0003*\u00020\u00042\u0006\u0010@\u001a\u00020Aø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bB\u0010C\u001a\u001f\u0010?\u001a\u00020\u0007*\u00020\b2\u0006\u0010@\u001a\u00020Aø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bB\u0010D\u001a!\u0010\u0002\u001a\u00020\u0007*\u00020\b2\u0006\u0010@\u001a\u00020AH\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bE\u0010D\u001a\u001f\u0010F\u001a\u00020\b*\u00020\u00042\u0006\u0010@\u001a\u00020Aø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bG\u0010H\u001a\u001f\u0010F\u001a\u00020\u0007*\u00020\u00032\u0006\u0010@\u001a\u00020Aø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bG\u0010I\u001a\f\u0010J\u001a\u00020(*\u00020KH\u0002\u001a\f\u0010J\u001a\u00020(*\u00020LH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0015\u0010\u0002\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\t\"\u0015\u0010\n\u001a\u00020\b*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\n\u001a\u00020\u0007*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r*\f\b\u0000\u0010M\"\u00020N2\u00020N*\f\b\u0000\u0010O\"\u00020P2\u00020P*\f\b\u0000\u0010Q\"\u00020R2\u00020R*\f\b\u0000\u0010S\"\u00020T2\u00020T\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006U"}, d2 = {"DEBUG", "", "atLeastWrapContent", "Landroidx/constraintlayout/compose/Dimension$MaxCoercible;", "Landroidx/constraintlayout/compose/Dimension$Coercible;", "getAtLeastWrapContent", "(Landroidx/constraintlayout/compose/Dimension$Coercible;)Landroidx/constraintlayout/compose/Dimension$MaxCoercible;", "Landroidx/constraintlayout/compose/Dimension;", "Landroidx/constraintlayout/compose/Dimension$MinCoercible;", "(Landroidx/constraintlayout/compose/Dimension$MinCoercible;)Landroidx/constraintlayout/compose/Dimension;", "atMostWrapContent", "getAtMostWrapContent", "(Landroidx/constraintlayout/compose/Dimension$Coercible;)Landroidx/constraintlayout/compose/Dimension$MinCoercible;", "(Landroidx/constraintlayout/compose/Dimension$MaxCoercible;)Landroidx/constraintlayout/compose/Dimension;", "ConstraintLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "optimizationLevel", "", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/constraintlayout/compose/ConstraintLayoutScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "constraintSet", "Landroidx/constraintlayout/compose/ConstraintSet;", "animateChanges", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "finishedAnimationListener", "Lkotlin/Function0;", "(Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/compose/ui/Modifier;IZLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ConstraintSet", "extendConstraintSet", "description", "Landroidx/constraintlayout/compose/ConstraintSetScope;", "jsonContent", "", "overrideVariables", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/constraintlayout/compose/ConstraintSet;", "buildMapping", SentryThread.JsonKeys.STATE, "Landroidx/constraintlayout/compose/State;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "createId", "", "rememberConstraintLayoutMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "needsUpdate", "Landroidx/compose/runtime/MutableState;", "", "measurer", "Landroidx/constraintlayout/compose/Measurer;", "(ILandroidx/compose/runtime/MutableState;Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/constraintlayout/compose/Measurer;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "Lkotlin/Pair;", "scope", "remeasureRequesterState", "(ILandroidx/constraintlayout/compose/ConstraintLayoutScope;Landroidx/compose/runtime/MutableState;Landroidx/constraintlayout/compose/Measurer;Landroidx/compose/runtime/Composer;I)Lkotlin/Pair;", "atLeast", "dp", "Landroidx/compose/ui/unit/Dp;", "atLeast-3ABfNKs", "(Landroidx/constraintlayout/compose/Dimension$Coercible;F)Landroidx/constraintlayout/compose/Dimension$MaxCoercible;", "(Landroidx/constraintlayout/compose/Dimension$MinCoercible;F)Landroidx/constraintlayout/compose/Dimension;", "atLeastWrapContent-3ABfNKs", "atMost", "atMost-3ABfNKs", "(Landroidx/constraintlayout/compose/Dimension$Coercible;F)Landroidx/constraintlayout/compose/Dimension$MinCoercible;", "(Landroidx/constraintlayout/compose/Dimension$MaxCoercible;F)Landroidx/constraintlayout/compose/Dimension;", "toDebugString", "Landroidx/constraintlayout/core/widgets/ConstraintWidget;", "Landroidx/constraintlayout/core/widgets/analyzer/BasicMeasure$Measure;", "SolverChain", "Landroidx/constraintlayout/core/state/State$Chain;", "SolverDimension", "Landroidx/constraintlayout/core/state/Dimension;", "SolverDirection", "Landroidx/constraintlayout/core/state/State$Direction;", "SolverState", "Landroidx/constraintlayout/core/state/State;", "compose_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ConstraintLayoutKt {
    private static final boolean DEBUG = false;

    public static final void ConstraintLayout(Modifier modifier, int i, final Function3<? super ConstraintLayoutScope, ? super Composer, ? super Integer, Unit> content, Composer composer, final int i2, int i3) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-270267587);
        ComposerKt.sourceInformation(composer, "C(ConstraintLayout)P(1,2)");
        Modifier modifier2 = (i3 & 1) != 0 ? Modifier.INSTANCE : modifier;
        int i4 = (i3 & 2) != 0 ? 257 : i;
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Measurer();
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Measurer measurer = (Measurer) rememberedValue;
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue2 = composer.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new ConstraintLayoutScope();
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        final ConstraintLayoutScope constraintLayoutScope = (ConstraintLayoutScope) rememberedValue2;
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue3 = composer.rememberedValue();
        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
            rememberedValue3 = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            composer.updateRememberedValue(rememberedValue3);
        }
        composer.endReplaceableGroup();
        Pair<MeasurePolicy, Function0<Unit>> rememberConstraintLayoutMeasurePolicy = rememberConstraintLayoutMeasurePolicy(i4, constraintLayoutScope, (MutableState<Boolean>) rememberedValue3, measurer, composer, ((i2 >> 3) & 14) | 4544);
        MeasurePolicy component1 = rememberConstraintLayoutMeasurePolicy.component1();
        final Function0<Unit> component2 = rememberConstraintLayoutMeasurePolicy.component2();
        LayoutKt.MultiMeasureLayout(SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                ToolingUtilsKt.setDesignInfoProvider(semantics, Measurer.this);
            }
        }, 1, null), ComposableLambdaKt.composableLambda(composer, -819894182, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$2
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

            public final void invoke(Composer composer2, int i5) {
                if (((i5 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                    int helpersHashCode = ConstraintLayoutScope.this.getHelpersHashCode();
                    ConstraintLayoutScope.this.reset();
                    content.invoke(ConstraintLayoutScope.this, composer2, Integer.valueOf(((i2 >> 3) & b.p) | 8));
                    if (ConstraintLayoutScope.this.getHelpersHashCode() != helpersHashCode) {
                        component2.invoke();
                        return;
                    }
                    return;
                }
                composer2.skipToGroupEnd();
            }
        }), component1, composer, 48, 0);
        composer.endReplaceableGroup();
    }

    public static final Pair<MeasurePolicy, Function0<Unit>> rememberConstraintLayoutMeasurePolicy(final int i, ConstraintLayoutScope scope, final MutableState<Boolean> remeasureRequesterState, final Measurer measurer, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(remeasureRequesterState, "remeasureRequesterState");
        Intrinsics.checkNotNullParameter(measurer, "measurer");
        composer.startReplaceableGroup(-441911751);
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new ConstraintSetForInlineDsl(scope);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final ConstraintSetForInlineDsl constraintSetForInlineDsl = (ConstraintSetForInlineDsl) rememberedValue;
        Integer valueOf = Integer.valueOf(i);
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(valueOf);
        Object rememberedValue2 = composer.rememberedValue();
        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = TuplesKt.to(new MeasurePolicy() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$rememberConstraintLayoutMeasurePolicy$1$measurePolicy$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo40measure3p2s80s(MeasureScope MeasurePolicy, final List<? extends Measurable> measurables, long j) {
                    Intrinsics.checkNotNullParameter(MeasurePolicy, "$this$MeasurePolicy");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    long m3275performMeasureDjhGOtQ = Measurer.this.m3275performMeasureDjhGOtQ(j, MeasurePolicy.getLayoutDirection(), constraintSetForInlineDsl, measurables, i, MeasurePolicy);
                    remeasureRequesterState.getValue();
                    int m3127getWidthimpl = IntSize.m3127getWidthimpl(m3275performMeasureDjhGOtQ);
                    int m3126getHeightimpl = IntSize.m3126getHeightimpl(m3275performMeasureDjhGOtQ);
                    final Measurer measurer2 = Measurer.this;
                    return MeasureScope.DefaultImpls.layout$default(MeasurePolicy, m3127getWidthimpl, m3126getHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$rememberConstraintLayoutMeasurePolicy$1$measurePolicy$1$measure$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout) {
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Measurer.this.performLayout(layout, measurables);
                        }
                    }, 4, null);
                }
            }, new Function0<Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$rememberConstraintLayoutMeasurePolicy$1$onHelpersChanged$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    remeasureRequesterState.setValue(Boolean.valueOf(!r0.getValue().booleanValue()));
                    constraintSetForInlineDsl.setKnownDirty(true);
                }
            });
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        Pair<MeasurePolicy, Function0<Unit>> pair = (Pair) rememberedValue2;
        composer.endReplaceableGroup();
        return pair;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void ConstraintLayout(final ConstraintSet constraintSet, Modifier modifier, int i, boolean z, AnimationSpec<Float> animationSpec, Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i2, int i3) {
        String str;
        Object obj;
        Intrinsics.checkNotNullParameter(constraintSet, "constraintSet");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-270262697);
        ComposerKt.sourceInformation(composer, "C(ConstraintLayout)P(2,5,6!2,4)");
        Modifier modifier2 = (i3 & 2) != 0 ? Modifier.INSTANCE : modifier;
        int i4 = (i3 & 4) != 0 ? 257 : i;
        boolean z2 = (i3 & 8) != 0 ? false : z;
        AnimationSpec<Float> tween$default = (i3 & 16) != 0 ? AnimationSpecKt.tween$default(0, 0, null, 7, null) : animationSpec;
        Function0<Unit> function02 = (i3 & 32) != 0 ? null : function0;
        if (z2) {
            composer.startReplaceableGroup(-270262314);
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default(constraintSet, null, 2, null);
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            MutableState mutableState = (MutableState) rememberedValue;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = SnapshotStateKt.mutableStateOf$default(constraintSet, null, 2, null);
                composer.updateRememberedValue(rememberedValue2);
            }
            composer.endReplaceableGroup();
            MutableState mutableState2 = (MutableState) rememberedValue2;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue3 = composer.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                composer.updateRememberedValue(rememberedValue3);
            }
            composer.endReplaceableGroup();
            Animatable animatable = (Animatable) rememberedValue3;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue4 = composer.rememberedValue();
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = ChannelKt.Channel$default(-1, null, null, 6, null);
                composer.updateRememberedValue(rememberedValue4);
            }
            composer.endReplaceableGroup();
            final Channel channel = (Channel) rememberedValue4;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue5 = composer.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                rememberedValue5 = SnapshotStateKt.mutableStateOf$default(1, null, 2, null);
                composer.updateRememberedValue(rememberedValue5);
            }
            composer.endReplaceableGroup();
            EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    channel.mo5440trySendJP2dKIU(constraintSet);
                }
            }, composer, 0);
            EffectsKt.LaunchedEffect(channel, new ConstraintLayoutKt$ConstraintLayout$4(channel, (MutableState) rememberedValue5, animatable, tween$default, function02, mutableState, mutableState2, null), composer, 8);
            ConstraintSet m3252ConstraintLayout$lambda6 = m3252ConstraintLayout$lambda6(mutableState);
            ConstraintSet m3254ConstraintLayout$lambda9 = m3254ConstraintLayout$lambda9(mutableState2);
            float floatValue = ((Number) animatable.getValue()).floatValue();
            composer.startReplaceableGroup(-1330873847);
            ComposerKt.sourceInformation(composer, "C(MotionLayout)P(6,2,7,5,1,3,4)");
            EnumSet of = EnumSet.of(MotionLayoutDebugFlags.NONE);
            Intrinsics.checkNotNullExpressionValue(of, "of(MotionLayoutDebugFlags.NONE)");
            composer.startReplaceableGroup(-1330870962);
            ComposerKt.sourceInformation(composer, "C(MotionLayout)P(7,2,8,6,1,3,4,5)");
            final int i5 = ((((((i2 << 12) & 458752) << 3) & 3670016) | 229376) & 3670016) | 229376;
            composer.startReplaceableGroup(-1401224268);
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue6 = composer.rememberedValue();
            if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                rememberedValue6 = new MotionMeasurer();
                composer.updateRememberedValue(rememberedValue6);
            }
            composer.endReplaceableGroup();
            MotionMeasurer motionMeasurer = (MotionMeasurer) rememberedValue6;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue7 = composer.rememberedValue();
            if (rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                rememberedValue7 = new MotionLayoutScope(motionMeasurer);
                composer.updateRememberedValue(rememberedValue7);
            }
            composer.endReplaceableGroup();
            final MotionLayoutScope motionLayoutScope = (MotionLayoutScope) rememberedValue7;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue8 = composer.rememberedValue();
            if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                str = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                obj = null;
                rememberedValue8 = SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                composer.updateRememberedValue(rememberedValue8);
            } else {
                str = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                obj = null;
            }
            composer.endReplaceableGroup();
            MutableState mutableState3 = (MutableState) rememberedValue8;
            mutableState3.setValue(Float.valueOf(floatValue));
            String str2 = str;
            MeasurePolicy rememberMotionLayoutMeasurePolicy = MotionLayoutKt.rememberMotionLayoutMeasurePolicy(257, of, 0L, m3252ConstraintLayout$lambda6, m3254ConstraintLayout$lambda9, null, mutableState3, motionMeasurer, composer, 18350528);
            motionMeasurer.addLayoutInformationReceiver(null);
            float forcedScaleFactor = motionMeasurer.getForcedScaleFactor();
            if (!of.contains(MotionLayoutDebugFlags.NONE) || !Float.isNaN(forcedScaleFactor)) {
                composer.startReplaceableGroup(-1401223142);
                if (!Float.isNaN(forcedScaleFactor)) {
                    modifier2 = ScaleKt.scale(modifier2, motionMeasurer.getForcedScaleFactor());
                }
                composer.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composer, str2);
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy rememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer, 0);
                composer.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composer, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) consume;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume2 = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                LayoutDirection layoutDirection = (LayoutDirection) consume2;
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(companion);
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor);
                } else {
                    composer.useNode();
                }
                composer.disableReusing();
                Composer m941constructorimpl = Updater.m941constructorimpl(composer);
                Updater.m948setimpl(m941constructorimpl, rememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m948setimpl(m941constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composer.enableReusing();
                materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(composer)), composer, 0);
                composer.startReplaceableGroup(2058660585);
                composer.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composer, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                LayoutKt.MultiMeasureLayout(SemanticsModifierKt.semantics$default(modifier2, false, new MotionLayoutKt$MotionLayoutCore$3$1(motionMeasurer), 1, null), ComposableLambdaKt.composableLambda(composer, -819900388, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$$inlined$MotionLayout$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i6) {
                        if (((i6 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                        } else {
                            content.invoke(composer2, Integer.valueOf((i2 >> 18) & 14));
                        }
                    }
                }), rememberMotionLayoutMeasurePolicy, composer, 48, 0);
                if (!Float.isNaN(forcedScaleFactor)) {
                    composer.startReplaceableGroup(-922833881);
                    motionMeasurer.drawDebugBounds(boxScopeInstance, forcedScaleFactor, composer, 518);
                    composer.endReplaceableGroup();
                } else {
                    composer.startReplaceableGroup(-922833807);
                    composer.endReplaceableGroup();
                }
                if (!of.contains(MotionLayoutDebugFlags.NONE)) {
                    composer.startReplaceableGroup(-922833740);
                    motionMeasurer.drawDebug(boxScopeInstance, composer, 70);
                    composer.endReplaceableGroup();
                } else {
                    composer.startReplaceableGroup(-922833689);
                    composer.endReplaceableGroup();
                }
                Unit unit = Unit.INSTANCE;
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                composer.endNode();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(-1401222327);
                LayoutKt.MultiMeasureLayout(SemanticsModifierKt.semantics$default(modifier2, false, new MotionLayoutKt$MotionLayoutCore$4(motionMeasurer), 1, null), ComposableLambdaKt.composableLambda(composer, -819896774, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$$inlined$MotionLayout$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i6) {
                        if (((i6 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                        } else {
                            content.invoke(composer2, Integer.valueOf((i2 >> 18) & 14));
                        }
                    }
                }), rememberMotionLayoutMeasurePolicy, composer, 48, 0);
                composer.endReplaceableGroup();
            }
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-270260906);
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue9 = composer.rememberedValue();
            if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                rememberedValue9 = SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
                composer.updateRememberedValue(rememberedValue9);
            }
            composer.endReplaceableGroup();
            MutableState<Long> mutableState4 = (MutableState) rememberedValue9;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue10 = composer.rememberedValue();
            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                rememberedValue10 = new Measurer();
                composer.updateRememberedValue(rememberedValue10);
            }
            composer.endReplaceableGroup();
            final Measurer measurer = (Measurer) rememberedValue10;
            Modifier modifier3 = modifier2;
            MeasurePolicy rememberConstraintLayoutMeasurePolicy = rememberConstraintLayoutMeasurePolicy(i4, mutableState4, constraintSet, measurer, composer, ((i2 >> 6) & 14) | 4144 | ((i2 << 6) & 896));
            if (constraintSet instanceof EditableJSONLayout) {
                ((EditableJSONLayout) constraintSet).setUpdateFlag(mutableState4);
            }
            measurer.addLayoutInformationReceiver(constraintSet instanceof LayoutInformationReceiver ? (LayoutInformationReceiver) constraintSet : null);
            float forcedScaleFactor2 = measurer.getForcedScaleFactor();
            if (!Float.isNaN(forcedScaleFactor2)) {
                composer.startReplaceableGroup(-270260292);
                Modifier scale = ScaleKt.scale(modifier3, measurer.getForcedScaleFactor());
                composer.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composer, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                MeasurePolicy rememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer, 0);
                composer.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composer, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume3 = composer.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density2 = (Density) consume3;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume4 = composer.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                LayoutDirection layoutDirection2 = (LayoutDirection) consume4;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf2 = LayoutKt.materializerOf(companion2);
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor2);
                } else {
                    composer.useNode();
                }
                composer.disableReusing();
                Composer m941constructorimpl2 = Updater.m941constructorimpl(composer);
                Updater.m948setimpl(m941constructorimpl2, rememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m948setimpl(m941constructorimpl2, density2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m948setimpl(m941constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composer.enableReusing();
                materializerOf2.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(composer)), composer, 0);
                composer.startReplaceableGroup(2058660585);
                composer.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composer, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                LayoutKt.MultiMeasureLayout(SemanticsModifierKt.semantics$default(scale, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$6$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        ToolingUtilsKt.setDesignInfoProvider(semantics, Measurer.this);
                    }
                }, 1, null), ComposableLambdaKt.composableLambda(composer, -819900598, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$6$2
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

                    public final void invoke(Composer composer2, int i6) {
                        if (((i6 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                            Measurer.this.createDesignElements(composer2, 8);
                            content.invoke(composer2, Integer.valueOf((i2 >> 18) & 14));
                        } else {
                            composer2.skipToGroupEnd();
                        }
                    }
                }), rememberConstraintLayoutMeasurePolicy, composer, 48, 0);
                measurer.drawDebugBounds(boxScopeInstance2, forcedScaleFactor2, composer, 518);
                Unit unit2 = Unit.INSTANCE;
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                composer.endNode();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(-270259702);
                LayoutKt.MultiMeasureLayout(SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$7
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        ToolingUtilsKt.setDesignInfoProvider(semantics, Measurer.this);
                    }
                }, 1, null), ComposableLambdaKt.composableLambda(composer, -819901122, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$ConstraintLayout$8
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

                    public final void invoke(Composer composer2, int i6) {
                        if (((i6 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                            Measurer.this.createDesignElements(composer2, 8);
                            content.invoke(composer2, Integer.valueOf((i2 >> 18) & 14));
                        } else {
                            composer2.skipToGroupEnd();
                        }
                    }
                }), rememberConstraintLayoutMeasurePolicy, composer, 48, 0);
                composer.endReplaceableGroup();
            }
            composer.endReplaceableGroup();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ConstraintLayout$lambda-6, reason: not valid java name */
    public static final ConstraintSet m3252ConstraintLayout$lambda6(MutableState<ConstraintSet> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ConstraintLayout$lambda-9, reason: not valid java name */
    public static final ConstraintSet m3254ConstraintLayout$lambda9(MutableState<ConstraintSet> mutableState) {
        return mutableState.getValue();
    }

    public static final MeasurePolicy rememberConstraintLayoutMeasurePolicy(final int i, MutableState<Long> needsUpdate, final ConstraintSet constraintSet, final Measurer measurer, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(needsUpdate, "needsUpdate");
        Intrinsics.checkNotNullParameter(constraintSet, "constraintSet");
        Intrinsics.checkNotNullParameter(measurer, "measurer");
        composer.startReplaceableGroup(-441904452);
        Integer valueOf = Integer.valueOf(i);
        Long value = needsUpdate.getValue();
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean changed = composer.changed(value) | composer.changed(valueOf) | composer.changed(constraintSet);
        MeasurePolicy rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            measurer.parseDesignElements(constraintSet);
            rememberedValue = new MeasurePolicy() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$rememberConstraintLayoutMeasurePolicy$2$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo40measure3p2s80s(MeasureScope MeasurePolicy, final List<? extends Measurable> measurables, long j) {
                    Intrinsics.checkNotNullParameter(MeasurePolicy, "$this$MeasurePolicy");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    long m3275performMeasureDjhGOtQ = Measurer.this.m3275performMeasureDjhGOtQ(j, MeasurePolicy.getLayoutDirection(), constraintSet, measurables, i, MeasurePolicy);
                    int m3127getWidthimpl = IntSize.m3127getWidthimpl(m3275performMeasureDjhGOtQ);
                    int m3126getHeightimpl = IntSize.m3126getHeightimpl(m3275performMeasureDjhGOtQ);
                    final Measurer measurer2 = Measurer.this;
                    return MeasureScope.DefaultImpls.layout$default(MeasurePolicy, m3127getWidthimpl, m3126getHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$rememberConstraintLayoutMeasurePolicy$2$1$measure$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout) {
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Measurer.this.performLayout(layout, measurables);
                        }
                    }, 4, null);
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue;
        composer.endReplaceableGroup();
        return measurePolicy;
    }

    public static final Object createId() {
        return new Object() { // from class: androidx.constraintlayout.compose.ConstraintLayoutKt$createId$1
        };
    }

    public static final Dimension.MaxCoercible getAtLeastWrapContent(Dimension.Coercible coercible) {
        Intrinsics.checkNotNullParameter(coercible, "<this>");
        DimensionDescription dimensionDescription = (DimensionDescription) coercible;
        dimensionDescription.setMinSymbol(androidx.constraintlayout.core.state.Dimension.WRAP_DIMENSION);
        return dimensionDescription;
    }

    /* renamed from: atLeast-3ABfNKs, reason: not valid java name */
    public static final Dimension.MaxCoercible m3259atLeast3ABfNKs(Dimension.Coercible atLeast, float f) {
        Intrinsics.checkNotNullParameter(atLeast, "$this$atLeast");
        DimensionDescription dimensionDescription = (DimensionDescription) atLeast;
        dimensionDescription.m3270setMinYLDhkOg(Dp.m3005boximpl(f));
        return dimensionDescription;
    }

    /* renamed from: atMost-3ABfNKs, reason: not valid java name */
    public static final Dimension.MinCoercible m3262atMost3ABfNKs(Dimension.Coercible atMost, float f) {
        Intrinsics.checkNotNullParameter(atMost, "$this$atMost");
        DimensionDescription dimensionDescription = (DimensionDescription) atMost;
        dimensionDescription.m3269setMaxYLDhkOg(Dp.m3005boximpl(f));
        return dimensionDescription;
    }

    public static final Dimension.MinCoercible getAtMostWrapContent(Dimension.Coercible coercible) {
        Intrinsics.checkNotNullParameter(coercible, "<this>");
        DimensionDescription dimensionDescription = (DimensionDescription) coercible;
        dimensionDescription.setMaxSymbol(androidx.constraintlayout.core.state.Dimension.WRAP_DIMENSION);
        return dimensionDescription;
    }

    @Deprecated(message = "Unintended method name, use atLeast(dp) instead", replaceWith = @ReplaceWith(expression = "this.atLeast(dp)", imports = {"androidx.constraintlayout.compose.atLeast"}))
    /* renamed from: atLeastWrapContent-3ABfNKs, reason: not valid java name */
    public static final Dimension m3261atLeastWrapContent3ABfNKs(Dimension.MinCoercible atLeastWrapContent, float f) {
        Intrinsics.checkNotNullParameter(atLeastWrapContent, "$this$atLeastWrapContent");
        DimensionDescription dimensionDescription = (DimensionDescription) atLeastWrapContent;
        dimensionDescription.m3270setMinYLDhkOg(Dp.m3005boximpl(f));
        return dimensionDescription;
    }

    /* renamed from: atLeast-3ABfNKs, reason: not valid java name */
    public static final Dimension m3260atLeast3ABfNKs(Dimension.MinCoercible atLeast, float f) {
        Intrinsics.checkNotNullParameter(atLeast, "$this$atLeast");
        DimensionDescription dimensionDescription = (DimensionDescription) atLeast;
        dimensionDescription.m3270setMinYLDhkOg(Dp.m3005boximpl(f));
        return dimensionDescription;
    }

    public static final Dimension getAtLeastWrapContent(Dimension.MinCoercible minCoercible) {
        Intrinsics.checkNotNullParameter(minCoercible, "<this>");
        DimensionDescription dimensionDescription = (DimensionDescription) minCoercible;
        dimensionDescription.setMinSymbol(androidx.constraintlayout.core.state.Dimension.WRAP_DIMENSION);
        return dimensionDescription;
    }

    /* renamed from: atMost-3ABfNKs, reason: not valid java name */
    public static final Dimension m3263atMost3ABfNKs(Dimension.MaxCoercible atMost, float f) {
        Intrinsics.checkNotNullParameter(atMost, "$this$atMost");
        DimensionDescription dimensionDescription = (DimensionDescription) atMost;
        dimensionDescription.m3269setMaxYLDhkOg(Dp.m3005boximpl(f));
        return dimensionDescription;
    }

    public static final Dimension getAtMostWrapContent(Dimension.MaxCoercible maxCoercible) {
        Intrinsics.checkNotNullParameter(maxCoercible, "<this>");
        DimensionDescription dimensionDescription = (DimensionDescription) maxCoercible;
        dimensionDescription.setMaxSymbol(androidx.constraintlayout.core.state.Dimension.WRAP_DIMENSION);
        return dimensionDescription;
    }

    public static final ConstraintSet ConstraintSet(String content, String str, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(1704604894);
        ComposerKt.sourceInformation(composer, "C(ConstraintSet)");
        if ((i2 & 2) != 0) {
            str = null;
        }
        String str2 = str;
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean changed = composer.changed(content) | composer.changed(str2);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new JSONConstraintSet(content, str2, null, 4, null);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (JSONConstraintSet) rememberedValue;
    }

    public static final ConstraintSet ConstraintSet(String jsonContent) {
        Intrinsics.checkNotNullParameter(jsonContent, "jsonContent");
        return new JSONConstraintSet(jsonContent, null, null, 6, null);
    }

    public static final ConstraintSet ConstraintSet(ConstraintSet extendConstraintSet, String jsonContent) {
        Intrinsics.checkNotNullParameter(extendConstraintSet, "extendConstraintSet");
        Intrinsics.checkNotNullParameter(jsonContent, "jsonContent");
        return new JSONConstraintSet(jsonContent, null, extendConstraintSet, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConstraintSet ConstraintSet(Function1<? super ConstraintSetScope, Unit> description) {
        Intrinsics.checkNotNullParameter(description, "description");
        return new DslConstraintSet(description, null, 2, 0 == true ? 1 : 0);
    }

    public static final ConstraintSet ConstraintSet(ConstraintSet extendConstraintSet, Function1<? super ConstraintSetScope, Unit> description) {
        Intrinsics.checkNotNullParameter(extendConstraintSet, "extendConstraintSet");
        Intrinsics.checkNotNullParameter(description, "description");
        return new DslConstraintSet(description, extendConstraintSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toDebugString(ConstraintWidget constraintWidget) {
        return ((Object) constraintWidget.getDebugName()) + " width " + constraintWidget.getWidth() + " minWidth " + constraintWidget.getMinWidth() + " maxWidth " + constraintWidget.getMaxWidth() + " height " + constraintWidget.getHeight() + " minHeight " + constraintWidget.getMinHeight() + " maxHeight " + constraintWidget.getMaxHeight() + " HDB " + constraintWidget.getHorizontalDimensionBehaviour() + " VDB " + constraintWidget.getVerticalDimensionBehaviour() + " MCW " + constraintWidget.mMatchConstraintDefaultWidth + " MCH " + constraintWidget.mMatchConstraintDefaultHeight + " percentW " + constraintWidget.mMatchConstraintPercentWidth + " percentH " + constraintWidget.mMatchConstraintPercentHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toDebugString(BasicMeasure.Measure measure) {
        return "measure strategy is ";
    }

    public static final void buildMapping(State state, List<? extends Measurable> measurables) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        int size = measurables.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            Measurable measurable = measurables.get(i);
            Object layoutId = LayoutIdKt.getLayoutId(measurable);
            if (layoutId == null && (layoutId = ConstraintLayoutTagKt.getConstraintLayoutId(measurable)) == null) {
                layoutId = createId();
            }
            state.map(layoutId, measurable);
            Object constraintLayoutTag = ConstraintLayoutTagKt.getConstraintLayoutTag(measurable);
            if (constraintLayoutTag != null && (constraintLayoutTag instanceof String) && (layoutId instanceof String)) {
                state.setTag((String) layoutId, (String) constraintLayoutTag);
            }
            if (i2 > size) {
                return;
            } else {
                i = i2;
            }
        }
    }
}
