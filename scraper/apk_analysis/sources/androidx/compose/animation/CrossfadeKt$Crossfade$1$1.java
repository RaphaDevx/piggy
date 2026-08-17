package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.b;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: Crossfade.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class CrossfadeKt$Crossfade$1$1 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $$dirty;
    final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
    final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
    final /* synthetic */ T $key;
    final /* synthetic */ Transition<T> $transition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CrossfadeKt$Crossfade$1$1(Transition<T> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, T t, Function3<? super T, ? super Composer, ? super Integer, Unit> function3, int i) {
        super(2);
        this.$transition = transition;
        this.$animationSpec = finiteAnimationSpec;
        this.$key = t;
        this.$content = function3;
        this.$$dirty = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C69@2910L116,72@3070L22,72@3043L103:Crossfade.kt#xbi5r1");
        if (((i & 11) ^ 2) != 0 || !composer.getSkipping()) {
            Transition<T> transition = this.$transition;
            final FiniteAnimationSpec<Float> finiteAnimationSpec = this.$animationSpec;
            Function3 function3 = new Function3<Transition.Segment<T>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$1$1$alpha$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<T> animateFloat, Composer composer2, int i2) {
                    Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
                    composer2.startReplaceableGroup(2090118723);
                    FiniteAnimationSpec<Float> finiteAnimationSpec2 = finiteAnimationSpec;
                    composer2.endReplaceableGroup();
                    return finiteAnimationSpec2;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
            T t = this.$key;
            composer.startReplaceableGroup(1399888154);
            ComposerKt.sourceInformation(composer, "C(animateFloat)P(2)833@33385L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            composer.startReplaceableGroup(1847721733);
            ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
            Object currentState = transition.getCurrentState();
            composer.startReplaceableGroup(2090118759);
            ComposerKt.sourceInformation(composer, "C:Crossfade.kt#xbi5r1");
            float f = Intrinsics.areEqual(currentState, t) ? 1.0f : 0.0f;
            composer.endReplaceableGroup();
            Float valueOf = Float.valueOf(f);
            Object targetState = transition.getTargetState();
            composer.startReplaceableGroup(2090118759);
            ComposerKt.sourceInformation(composer, "C:Crossfade.kt#xbi5r1");
            float f2 = Intrinsics.areEqual(targetState, t) ? 1.0f : 0.0f;
            composer.endReplaceableGroup();
            final State createTransitionAnimation = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, valueOf, Float.valueOf(f2), (FiniteAnimationSpec) function3.invoke(transition.getSegment(), composer, 0), vectorConverter, "FloatAnimation", composer, 0);
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean changed = composer.changed(createTransitionAnimation);
            Object rememberedValue = composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                        invoke2(graphicsLayerScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GraphicsLayerScope graphicsLayer) {
                        float m75invoke$lambda1;
                        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                        m75invoke$lambda1 = CrossfadeKt$Crossfade$1$1.m75invoke$lambda1(createTransitionAnimation);
                        graphicsLayer.setAlpha(m75invoke$lambda1);
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) rememberedValue);
            Function3<T, Composer, Integer, Unit> function32 = this.$content;
            T t2 = this.$key;
            int i2 = this.$$dirty;
            composer.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composer, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
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
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(graphicsLayer);
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
            composer.startReplaceableGroup(2090118878);
            ComposerKt.sourceInformation(composer, "C73@3116L12:Crossfade.kt#xbi5r1");
            function32.invoke(t2, composer, Integer.valueOf(((i2 >> 6) & b.p) | (i2 & 8)));
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1, reason: not valid java name */
    public static final float m75invoke$lambda1(State<Float> state) {
        return state.getValue().floatValue();
    }
}
