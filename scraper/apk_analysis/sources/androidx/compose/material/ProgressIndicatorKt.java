package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.b;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressIndicator.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a3\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a;\u0010\u001e\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a3\u0010*\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010+\u001a\u00020#H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a;\u0010*\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010+\u001a\u00020#H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a9\u00100\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u00107\u001a9\u00108\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u00107\u001aA\u0010:\u001a\u00020\u001f*\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b;\u0010<\u001a9\u0010=\u001a\u00020\u001f*\u0002012\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010A\u001a)\u0010B\u001a\u00020\u001f*\u0002012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010D\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0004\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0012\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u0013\u0010\u0013\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"BaseRotationAngle", "", "CircularEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "CircularIndicatorDiameter", "Landroidx/compose/ui/unit/Dp;", "F", "FirstLineHeadDelay", "", "FirstLineHeadDuration", "FirstLineHeadEasing", "FirstLineTailDelay", "FirstLineTailDuration", "FirstLineTailEasing", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "JumpRotationAngle", "LinearAnimationDuration", "LinearIndicatorHeight", "LinearIndicatorWidth", "RotationAngleOffset", "RotationDuration", "RotationsPerCycle", "SecondLineHeadDelay", "SecondLineHeadDuration", "SecondLineHeadEasing", "SecondLineTailDelay", "SecondLineTailDuration", "SecondLineTailEasing", "StartAngleOffset", "CircularProgressIndicator", "", "modifier", "Landroidx/compose/ui/Modifier;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "progress", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", "backgroundColor", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawLinearIndicator", "startFraction", "endFraction", "drawLinearIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJF)V", "drawLinearIndicatorBackground", "drawLinearIndicatorBackground-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JF)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final float StartAngleOffset = -90.0f;
    private static final float LinearIndicatorHeight = ProgressIndicatorDefaults.INSTANCE.m792getStrokeWidthD9Ej5fM();
    private static final float LinearIndicatorWidth = Dp.m3007constructorimpl(240);
    private static final float CircularIndicatorDiameter = Dp.m3007constructorimpl(40);
    private static final CubicBezierEasing FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
    private static final CubicBezierEasing FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
    private static final CubicBezierEasing SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
    private static final CubicBezierEasing CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);

    /* JADX WARN: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0063  */
    /* renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m800LinearProgressIndicatoreaDK9VM(final float r19, androidx.compose.ui.Modifier r20, long r21, long r23, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m800LinearProgressIndicatoreaDK9VM(float, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final void m799LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j3;
        long j4;
        final Modifier.Companion companion;
        long m678getPrimary0d7_KjU;
        final long j5;
        final long j6;
        Composer startRestartGroup = composer.startRestartGroup(96019871);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(2,1:c#ui.graphics.Color,0:c#ui.graphics.Color)104@4663L6,107@4788L28,111@5070L319,122@5434L319,133@5799L323,144@6168L323,160@6648L557,155@6496L709:ProgressIndicator.kt#jmzs0o");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & b.p) == 0) {
            j3 = j;
            i3 |= ((i2 & 2) == 0 && startRestartGroup.changed(j3)) ? 32 : 16;
        } else {
            j3 = j;
        }
        if ((i & 896) == 0) {
            j4 = j2;
            i3 |= ((i2 & 4) == 0 && startRestartGroup.changed(j4)) ? 256 : 128;
        } else {
            j4 = j2;
        }
        if (((i3 & 731) ^ 146) != 0 || !startRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipCurrentGroup();
                companion = modifier2;
                m678getPrimary0d7_KjU = j3;
            } else {
                startRestartGroup.startDefaults();
                companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
                m678getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(startRestartGroup, 0).m678getPrimary0d7_KjU() : j3;
                if ((i2 & 4) != 0) {
                    j4 = Color.m1253copywmQWz5c$default(m678getPrimary0d7_KjU, 0.24f, 0.0f, 0.0f, 0.0f, 14, null);
                }
                startRestartGroup.endDefaults();
            }
            final long j7 = j4;
            InfiniteTransition rememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(startRestartGroup, 0);
            final State<Float> animateFloat = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 0);
                    cubicBezierEasing = ProgressIndicatorKt.FirstLineHeadEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(1.0f), 750);
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State<Float> animateFloat2 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 333);
                    cubicBezierEasing = ProgressIndicatorKt.FirstLineTailEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(1.0f), 1183);
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State<Float> animateFloat3 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 1000);
                    cubicBezierEasing = ProgressIndicatorKt.SecondLineHeadEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(1.0f), 1567);
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State<Float> animateFloat4 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 1267);
                    cubicBezierEasing = ProgressIndicatorKt.SecondLineTailEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(1.0f), 1800);
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            Modifier focusable$default = FocusableKt.focusable$default(SizeKt.m357sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion), LinearIndicatorWidth, LinearIndicatorHeight), false, null, 3, null);
            Object[] objArr = {Color.m1244boximpl(j7), animateFloat, animateFloat2, Color.m1244boximpl(m678getPrimary0d7_KjU), animateFloat3, animateFloat4};
            startRestartGroup.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            int i5 = 0;
            boolean z = false;
            while (i5 < 6) {
                Object obj = objArr[i5];
                i5++;
                z |= startRestartGroup.changed(obj);
            }
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                final long j8 = m678getPrimary0d7_KjU;
                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope Canvas) {
                        float m801LinearProgressIndicator_RIQooxk$lambda1;
                        float m802LinearProgressIndicator_RIQooxk$lambda2;
                        float m803LinearProgressIndicator_RIQooxk$lambda3;
                        float m804LinearProgressIndicator_RIQooxk$lambda4;
                        float m803LinearProgressIndicator_RIQooxk$lambda32;
                        float m804LinearProgressIndicator_RIQooxk$lambda42;
                        float m801LinearProgressIndicator_RIQooxk$lambda12;
                        float m802LinearProgressIndicator_RIQooxk$lambda22;
                        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                        float m1091getHeightimpl = Size.m1091getHeightimpl(Canvas.mo1627getSizeNHjbRc());
                        ProgressIndicatorKt.m821drawLinearIndicatorBackgroundbw27NRU(Canvas, j7, m1091getHeightimpl);
                        m801LinearProgressIndicator_RIQooxk$lambda1 = ProgressIndicatorKt.m801LinearProgressIndicator_RIQooxk$lambda1(animateFloat);
                        m802LinearProgressIndicator_RIQooxk$lambda2 = ProgressIndicatorKt.m802LinearProgressIndicator_RIQooxk$lambda2(animateFloat2);
                        if (m801LinearProgressIndicator_RIQooxk$lambda1 - m802LinearProgressIndicator_RIQooxk$lambda2 > 0.0f) {
                            m801LinearProgressIndicator_RIQooxk$lambda12 = ProgressIndicatorKt.m801LinearProgressIndicator_RIQooxk$lambda1(animateFloat);
                            m802LinearProgressIndicator_RIQooxk$lambda22 = ProgressIndicatorKt.m802LinearProgressIndicator_RIQooxk$lambda2(animateFloat2);
                            ProgressIndicatorKt.m820drawLinearIndicator42QJj7c(Canvas, m801LinearProgressIndicator_RIQooxk$lambda12, m802LinearProgressIndicator_RIQooxk$lambda22, j8, m1091getHeightimpl);
                        }
                        m803LinearProgressIndicator_RIQooxk$lambda3 = ProgressIndicatorKt.m803LinearProgressIndicator_RIQooxk$lambda3(animateFloat3);
                        m804LinearProgressIndicator_RIQooxk$lambda4 = ProgressIndicatorKt.m804LinearProgressIndicator_RIQooxk$lambda4(animateFloat4);
                        if (m803LinearProgressIndicator_RIQooxk$lambda3 - m804LinearProgressIndicator_RIQooxk$lambda4 > 0.0f) {
                            m803LinearProgressIndicator_RIQooxk$lambda32 = ProgressIndicatorKt.m803LinearProgressIndicator_RIQooxk$lambda3(animateFloat3);
                            m804LinearProgressIndicator_RIQooxk$lambda42 = ProgressIndicatorKt.m804LinearProgressIndicator_RIQooxk$lambda4(animateFloat4);
                            ProgressIndicatorKt.m820drawLinearIndicator42QJj7c(Canvas, m803LinearProgressIndicator_RIQooxk$lambda32, m804LinearProgressIndicator_RIQooxk$lambda42, j8, m1091getHeightimpl);
                        }
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            startRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(focusable$default, (Function1) rememberedValue, startRestartGroup, 0);
            j5 = m678getPrimary0d7_KjU;
            j6 = j7;
        } else {
            startRestartGroup.skipToGroupEnd();
            companion = modifier2;
            j5 = j3;
            j6 = j4;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$LinearProgressIndicator$4
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
                ProgressIndicatorKt.m799LinearProgressIndicatorRIQooxk(Modifier.this, j5, j6, composer2, i | 1, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicator-42QJj7c, reason: not valid java name */
    public static final void m820drawLinearIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, float f3) {
        float m1094getWidthimpl = Size.m1094getWidthimpl(drawScope.mo1627getSizeNHjbRc());
        float m1091getHeightimpl = Size.m1091getHeightimpl(drawScope.mo1627getSizeNHjbRc()) / 2;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        DrawScope.DefaultImpls.m1662drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset((z ? f : 1.0f - f2) * m1094getWidthimpl, m1091getHeightimpl), OffsetKt.Offset((z ? f2 : 1.0f - f) * m1094getWidthimpl, m1091getHeightimpl), f3, 0, null, 0.0f, null, 0, 496, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicatorBackground-bw27NRU, reason: not valid java name */
    public static final void m821drawLinearIndicatorBackgroundbw27NRU(DrawScope drawScope, long j, float f) {
        m820drawLinearIndicator42QJj7c(drawScope, 0.0f, 1.0f, j, f);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x005e  */
    /* renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m793CircularProgressIndicatorMBs18nI(final float r22, androidx.compose.ui.Modifier r23, long r24, float r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m793CircularProgressIndicatorMBs18nI(float, androidx.compose.ui.Modifier, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final void m794CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j2;
        float f2;
        final Modifier.Companion companion;
        long m678getPrimary0d7_KjU;
        final float f3;
        final long j3;
        final float f4;
        int i4;
        int i5;
        Composer startRestartGroup = composer.startRestartGroup(1769711483);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.unit.Dp)261@10395L6,*264@10510L7,268@10616L28,270@10774L278,282@11167L230,293@11515L345,305@11895L354,316@12254L607:ProgressIndicator.kt#jmzs0o");
        int i6 = i2 & 1;
        if (i6 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 14) == 0) {
            modifier2 = modifier;
            i3 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & b.p) == 0) {
            if ((i2 & 2) == 0) {
                j2 = j;
                if (startRestartGroup.changed(j2)) {
                    i5 = 32;
                    i3 |= i5;
                }
            } else {
                j2 = j;
            }
            i5 = 16;
            i3 |= i5;
        } else {
            j2 = j;
        }
        if ((i & 896) == 0) {
            if ((i2 & 4) == 0) {
                f2 = f;
                if (startRestartGroup.changed(f2)) {
                    i4 = 256;
                    i3 |= i4;
                }
            } else {
                f2 = f;
            }
            i4 = 128;
            i3 |= i4;
        } else {
            f2 = f;
        }
        if (((i3 & 731) ^ 146) != 0 || !startRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipCurrentGroup();
                companion = modifier2;
                m678getPrimary0d7_KjU = j2;
                f3 = f2;
            } else {
                startRestartGroup.startDefaults();
                companion = i6 != 0 ? Modifier.INSTANCE : modifier2;
                m678getPrimary0d7_KjU = (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(startRestartGroup, 0).m678getPrimary0d7_KjU() : j2;
                float m792getStrokeWidthD9Ej5fM = (i2 & 4) != 0 ? ProgressIndicatorDefaults.INSTANCE.m792getStrokeWidthD9Ej5fM() : f2;
                startRestartGroup.endDefaults();
                f3 = m792getStrokeWidthD9Ej5fM;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Stroke stroke = new Stroke(((Density) consume).mo227toPx0680j_4(f3), 0.0f, StrokeCap.INSTANCE.m1525getSquareKaPHkGw(), 0, null, 26, null);
            InfiniteTransition rememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(startRestartGroup, 0);
            final State animateValue = InfiniteTransitionKt.animateValue(rememberInfiniteTransition, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 4528 | (InfiniteRepeatableSpec.$stable << 12));
            final State<Float> animateFloat = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, BaseRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State<Float> animateFloat2 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$endAngle$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1332);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 0);
                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(290.0f), 666);
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final State<Float> animateFloat3 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.infiniteRepeatable$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$startAngle$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
                    CubicBezierEasing cubicBezierEasing;
                    Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
                    keyframes.setDurationMillis(1332);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframes.at(Float.valueOf(0.0f), 666);
                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                    keyframes.with(at, cubicBezierEasing);
                    keyframes.at(Float.valueOf(290.0f), keyframes.getDurationMillis());
                }
            }), null, 2, null), startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9));
            final long j4 = m678getPrimary0d7_KjU;
            CanvasKt.Canvas(FocusableKt.focusable$default(SizeKt.m356size3ABfNKs(ProgressSemanticsKt.progressSemantics(companion), CircularIndicatorDiameter), false, null, 3, null), new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope Canvas) {
                    int m797CircularProgressIndicator_aM_cp0Q$lambda8;
                    float m795CircularProgressIndicator_aM_cp0Q$lambda10;
                    float m796CircularProgressIndicator_aM_cp0Q$lambda11;
                    float m798CircularProgressIndicator_aM_cp0Q$lambda9;
                    float m796CircularProgressIndicator_aM_cp0Q$lambda112;
                    Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                    m797CircularProgressIndicator_aM_cp0Q$lambda8 = ProgressIndicatorKt.m797CircularProgressIndicator_aM_cp0Q$lambda8(animateValue);
                    m795CircularProgressIndicator_aM_cp0Q$lambda10 = ProgressIndicatorKt.m795CircularProgressIndicator_aM_cp0Q$lambda10(animateFloat2);
                    m796CircularProgressIndicator_aM_cp0Q$lambda11 = ProgressIndicatorKt.m796CircularProgressIndicator_aM_cp0Q$lambda11(animateFloat3);
                    float abs = Math.abs(m795CircularProgressIndicator_aM_cp0Q$lambda10 - m796CircularProgressIndicator_aM_cp0Q$lambda11);
                    m798CircularProgressIndicator_aM_cp0Q$lambda9 = ProgressIndicatorKt.m798CircularProgressIndicator_aM_cp0Q$lambda9(animateFloat);
                    float f5 = (((m797CircularProgressIndicator_aM_cp0Q$lambda8 * 216.0f) % 360.0f) - 90.0f) + m798CircularProgressIndicator_aM_cp0Q$lambda9;
                    m796CircularProgressIndicator_aM_cp0Q$lambda112 = ProgressIndicatorKt.m796CircularProgressIndicator_aM_cp0Q$lambda11(animateFloat3);
                    ProgressIndicatorKt.m819drawIndeterminateCircularIndicatorhrjfTZI(Canvas, m796CircularProgressIndicator_aM_cp0Q$lambda112 + f5, f3, abs, j4, stroke);
                }
            }, startRestartGroup, 0);
            j3 = m678getPrimary0d7_KjU;
            f4 = f3;
        } else {
            startRestartGroup.skipToGroupEnd();
            companion = modifier2;
            j3 = j2;
            f4 = f2;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ProgressIndicatorKt$CircularProgressIndicator$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i7) {
                ProgressIndicatorKt.m794CircularProgressIndicatoraMcp0Q(Modifier.this, j3, f4, composer2, i | 1, i2);
            }
        });
    }

    /* renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m817drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float f3 = 2;
        float width = stroke.getWidth() / f3;
        float m1094getWidthimpl = Size.m1094getWidthimpl(drawScope.mo1627getSizeNHjbRc()) - (f3 * width);
        DrawScope.DefaultImpls.m1656drawArcyD3GUKo$default(drawScope, j, f, f2, false, OffsetKt.Offset(width, width), androidx.compose.ui.geometry.SizeKt.Size(m1094getWidthimpl, m1094getWidthimpl), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m818drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        m817drawCircularIndicator42QJj7c(drawScope, f, f2, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m819drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m817drawCircularIndicator42QJj7c(drawScope, f + (((f2 / Dp.m3007constructorimpl(CircularIndicatorDiameter / 2)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-1, reason: not valid java name */
    public static final float m801LinearProgressIndicator_RIQooxk$lambda1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-2, reason: not valid java name */
    public static final float m802LinearProgressIndicator_RIQooxk$lambda2(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-3, reason: not valid java name */
    public static final float m803LinearProgressIndicator_RIQooxk$lambda3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LinearProgressIndicator_RIQooxk$lambda-4, reason: not valid java name */
    public static final float m804LinearProgressIndicator_RIQooxk$lambda4(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-8, reason: not valid java name */
    public static final int m797CircularProgressIndicator_aM_cp0Q$lambda8(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-9, reason: not valid java name */
    public static final float m798CircularProgressIndicator_aM_cp0Q$lambda9(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-10, reason: not valid java name */
    public static final float m795CircularProgressIndicator_aM_cp0Q$lambda10(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularProgressIndicator_aM_cp0Q$lambda-11, reason: not valid java name */
    public static final float m796CircularProgressIndicator_aM_cp0Q$lambda11(State<Float> state) {
        return state.getValue().floatValue();
    }
}
