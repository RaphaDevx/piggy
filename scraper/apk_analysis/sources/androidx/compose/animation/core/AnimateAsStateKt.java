package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimateAsState.kt */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001aM\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u00022\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010\u001d\u001aC\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010\u001f\u001aK\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0013\u001a\u00020\b2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"\u001aK\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0013\u001a\u00020\n2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\"\u001aK\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\u00122\u0006\u0010\u0013\u001a\u00020\f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\"\u001aC\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010(\u001aK\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\"\u001as\u0010+\u001a\b\u0012\u0004\u0012\u0002H,0\u0012\"\u0004\b\u0000\u0010,\"\b\b\u0001\u0010-*\u00020.2\u0006\u0010\u0013\u001a\u0002H,2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u0002H-002\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H,0\u00152\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u0001H,2\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0007¢\u0006\u0002\u00101\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"defaultAnimation", "Landroidx/compose/animation/core/SpringSpec;", "", "dpDefaultSpring", "Landroidx/compose/ui/unit/Dp;", "intDefaultSpring", "", "intOffsetDefaultSpring", "Landroidx/compose/ui/unit/IntOffset;", "intSizeDefaultSpring", "Landroidx/compose/ui/unit/IntSize;", "offsetDefaultSpring", "Landroidx/compose/ui/geometry/Offset;", "rectDefaultSpring", "Landroidx/compose/ui/geometry/Rect;", "sizeDefaultSpring", "Landroidx/compose/ui/geometry/Size;", "animateDpAsState", "Landroidx/compose/runtime/State;", "targetValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "finishedListener", "Lkotlin/Function1;", "", "animateDpAsState-Kz89ssw", "(FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateFloatAsState", "visibilityThreshold", "(FLandroidx/compose/animation/core/AnimationSpec;FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntAsState", "(ILandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntOffsetAsState", "animateIntOffsetAsState-8f6pmRE", "(JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntSizeAsState", "animateIntSizeAsState-zTRF_AQ", "animateOffsetAsState", "animateOffsetAsState-N6fFfp4", "animateRectAsState", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateSizeAsState", "animateSizeAsState-LjSzlW0", "animateValueAsState", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animation-core_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class AnimateAsStateKt {
    private static final SpringSpec<Float> defaultAnimation = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
    private static final SpringSpec<Dp> dpDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m3005boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
    private static final SpringSpec<Size> sizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m1082boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
    private static final SpringSpec<Offset> offsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m1014boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
    private static final SpringSpec<Rect> rectDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
    private static final SpringSpec<Integer> intDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Integer.valueOf(VisibilityThresholdsKt.getVisibilityThreshold(IntCompanionObject.INSTANCE)), 3, null);
    private static final SpringSpec<IntOffset> intOffsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
    private static final SpringSpec<IntSize> intSizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);

    public static final State<Float> animateFloatAsState(float f, AnimationSpec<Float> animationSpec, float f2, Function1<? super Float, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(841393235);
        ComposerKt.sourceInformation(composer, "C(animateFloatAsState)P(2!1,3)72@3191L158:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = defaultAnimation;
        }
        if ((i2 & 4) != 0) {
            f2 = 0.01f;
        }
        Function1<? super Float, Unit> function12 = (i2 & 8) != 0 ? null : function1;
        if (animationSpec == defaultAnimation) {
            composer.startReplaceableGroup(841393525);
            ComposerKt.sourceInformation(composer, "68@3043L83");
            Float valueOf = Float.valueOf(f2);
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean changed = composer.changed(valueOf);
            Object rememberedValue = composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, Float.valueOf(f2), 3, null);
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            animationSpec = (SpringSpec) rememberedValue;
        } else {
            composer.startReplaceableGroup(841393638);
            composer.endReplaceableGroup();
        }
        int i3 = i << 3;
        State<Float> animateValueAsState = animateValueAsState(Float.valueOf(f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), animationSpec, Float.valueOf(f2), function12, composer, (i & 14) | (i3 & 7168) | (i3 & 57344), 0);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /* renamed from: animateDpAsState-Kz89ssw, reason: not valid java name */
    public static final State<Dp> m112animateDpAsStateKz89ssw(float f, AnimationSpec<Dp> animationSpec, Function1<? super Dp, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1364859110);
        ComposerKt.sourceInformation(composer, "C(animateDpAsState)P(2:c#ui.unit.Dp)108@4762L142:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = dpDefaultSpring;
        }
        AnimationSpec<Dp> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<Dp> animateValueAsState = animateValueAsState(Dp.m3005boximpl(f), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /* renamed from: animateSizeAsState-LjSzlW0, reason: not valid java name */
    public static final State<Size> m116animateSizeAsStateLjSzlW0(long j, AnimationSpec<Size> animationSpec, Function1<? super Size, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1414928158);
        ComposerKt.sourceInformation(composer, "C(animateSizeAsState)P(2:c#ui.geometry.Size)146@6457L144:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = sizeDefaultSpring;
        }
        AnimationSpec<Size> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<Size> animateValueAsState = animateValueAsState(Size.m1082boximpl(j), VectorConvertersKt.getVectorConverter(Size.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /* renamed from: animateOffsetAsState-N6fFfp4, reason: not valid java name */
    public static final State<Offset> m115animateOffsetAsStateN6fFfp4(long j, AnimationSpec<Offset> animationSpec, Function1<? super Offset, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-200212809);
        ComposerKt.sourceInformation(composer, "C(animateOffsetAsState)P(2:c#ui.geometry.Offset)183@8136L122:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = offsetDefaultSpring;
        }
        AnimationSpec<Offset> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<Offset> animateValueAsState = animateValueAsState(Offset.m1014boximpl(j), VectorConvertersKt.getVectorConverter(Offset.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    public static final State<Rect> animateRectAsState(Rect targetValue, AnimationSpec<Rect> animationSpec, Function1<? super Rect, Unit> function1, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        composer.startReplaceableGroup(231660788);
        ComposerKt.sourceInformation(composer, "C(animateRectAsState)P(2)218@9832L120:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = rectDefaultSpring;
        }
        AnimationSpec<Rect> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<Rect> animateValueAsState = animateValueAsState(targetValue, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    public static final State<Integer> animateIntAsState(int i, AnimationSpec<Integer> animationSpec, Function1<? super Integer, Unit> function1, Composer composer, int i2, int i3) {
        composer.startReplaceableGroup(-887370562);
        ComposerKt.sourceInformation(composer, "C(animateIntAsState)P(2)250@11387L119:AnimateAsState.kt#pdpnli");
        if ((i3 & 2) != 0) {
            animationSpec = intDefaultSpring;
        }
        AnimationSpec<Integer> animationSpec2 = animationSpec;
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        State<Integer> animateValueAsState = animateValueAsState(Integer.valueOf(i), VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), animationSpec2, null, function1, composer, (i2 & 14) | ((i2 << 3) & 896) | ((i2 << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /* renamed from: animateIntOffsetAsState-8f6pmRE, reason: not valid java name */
    public static final State<IntOffset> m113animateIntOffsetAsState8f6pmRE(long j, AnimationSpec<IntOffset> animationSpec, Function1<? super IntOffset, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1370676387);
        ComposerKt.sourceInformation(composer, "C(animateIntOffsetAsState)P(2:c#ui.unit.IntOffset)284@13067L125:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = intOffsetDefaultSpring;
        }
        AnimationSpec<IntOffset> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<IntOffset> animateValueAsState = animateValueAsState(IntOffset.m3076boximpl(j), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /* renamed from: animateIntSizeAsState-zTRF_AQ, reason: not valid java name */
    public static final State<IntSize> m114animateIntSizeAsStatezTRF_AQ(long j, AnimationSpec<IntSize> animationSpec, Function1<? super IntSize, Unit> function1, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1724517475);
        ComposerKt.sourceInformation(composer, "C(animateIntSizeAsState)P(2:c#ui.unit.IntSize)316@14673L123:AnimateAsState.kt#pdpnli");
        if ((i2 & 2) != 0) {
            animationSpec = intSizeDefaultSpring;
        }
        AnimationSpec<IntSize> animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        State<IntSize> animateValueAsState = animateValueAsState(IntSize.m3119boximpl(j), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), animationSpec2, null, function1, composer, (i & 14) | ((i << 3) & 896) | ((i << 6) & 57344), 8);
        composer.endReplaceableGroup();
        return animateValueAsState;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r12v1 ??, still in use, count: 1, list:
          (r12v1 ?? I:java.lang.Object) from 0x006b: INVOKE (r23v0 ?? I:androidx.compose.runtime.Composer), (r12v1 ?? I:java.lang.Object) INTERFACE call: androidx.compose.runtime.Composer.updateRememberedValue(java.lang.Object):void A[MD:(java.lang.Object):void (m)] (LINE:403)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final <T, V extends androidx.compose.animation.core.AnimationVector> androidx.compose.runtime.State<T> animateValueAsState(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r12v1 ??, still in use, count: 1, list:
          (r12v1 ?? I:java.lang.Object) from 0x006b: INVOKE (r23v0 ?? I:androidx.compose.runtime.Composer), (r12v1 ?? I:java.lang.Object) INTERFACE call: androidx.compose.runtime.Composer.updateRememberedValue(java.lang.Object):void A[MD:(java.lang.Object):void (m)] (LINE:403)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r18v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:238)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:79)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:340)
        */

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animateValueAsState$lambda-3, reason: not valid java name */
    public static final <T> Function1<T, Unit> m117animateValueAsState$lambda3(State<? extends Function1<? super T, Unit>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animateValueAsState$lambda-4, reason: not valid java name */
    public static final <T> AnimationSpec<T> m118animateValueAsState$lambda4(State<? extends AnimationSpec<T>> state) {
        return state.getValue();
    }
}
