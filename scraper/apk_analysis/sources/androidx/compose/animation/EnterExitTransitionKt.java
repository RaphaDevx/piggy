package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnterExitTransition.kt */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aT\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2#\b\u0002\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001aT\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00142#\b\u0002\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00040\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001aT\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00182#\b\u0002\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001a\"\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0010H\u0007\u001a\"\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001d2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0010H\u0007\u001aT\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\b2#\b\u0002\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001aT\u0010$\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u00142#\b\u0002\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00040\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001aT\u0010&\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u00182#\b\u0002\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000\u001a>\u0010(\u001a\u00020\u00062!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00020\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a@\u0010*\u001a\u00020\u00062#\b\u0002\u0010+\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a@\u0010,\u001a\u00020\u00062#\b\u0002\u0010-\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a>\u0010.\u001a\u00020\u001f2!\u0010/\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00020\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a@\u00100\u001a\u00020\u001f2#\b\u0002\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a@\u00102\u001a\u00020\u001f2#\b\u0002\u00103\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0007ø\u0001\u0000\u001a'\u00104\u001a\u000205*\b\u0012\u0004\u0012\u000207062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u001fH\u0001¢\u0006\u0002\u0010:\u001a:\u0010;\u001a\u000205*\u0002052\f\u0010<\u001a\b\u0012\u0004\u0012\u000207062\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?0>2\u000e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?0>H\u0002\u001a:\u0010A\u001a\u000205*\u0002052\f\u0010<\u001a\b\u0012\u0004\u0012\u000207062\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0>2\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0>H\u0002\u001a\f\u0010C\u001a\u00020\u0014*\u00020\bH\u0002\u001a\f\u0010C\u001a\u00020\u0014*\u00020\u0018H\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"defaultOffsetAnimationSpec", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/unit/IntOffset;", "defaultSizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "expandHorizontally", "Landroidx/compose/animation/EnterTransition;", "expandFrom", "Landroidx/compose/ui/Alignment$Horizontal;", "initialWidth", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "fullWidth", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "clip", "", "expandIn", "Landroidx/compose/ui/Alignment;", "initialSize", "fullSize", "expandVertically", "Landroidx/compose/ui/Alignment$Vertical;", "initialHeight", "fullHeight", "fadeIn", "initialAlpha", "", "fadeOut", "Landroidx/compose/animation/ExitTransition;", "targetAlpha", "shrinkHorizontally", "shrinkTowards", "targetWidth", "shrinkOut", "targetSize", "shrinkVertically", "targetHeight", "slideIn", "initialOffset", "slideInHorizontally", "initialOffsetX", "slideInVertically", "initialOffsetY", "slideOut", "targetOffset", "slideOutHorizontally", "targetOffsetX", "slideOutVertically", "targetOffsetY", "createModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "shrinkExpand", "transition", "expand", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/ChangeSize;", "shrink", "slideInOut", "Landroidx/compose/animation/Slide;", "toAlignment", "animation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EnterExitTransitionKt {
    private static final SpringSpec<IntOffset> defaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
    private static final SpringSpec<IntSize> defaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);

    /* compiled from: EnterExitTransition.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnterExitState.valuesCustom().length];
            iArr[EnterExitState.Visible.ordinal()] = 1;
            iArr[EnterExitState.PreEnter.ordinal()] = 2;
            iArr[EnterExitState.PostExit.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ EnterTransition fadeIn$default(float f, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return fadeIn(f, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition fadeIn(float f, FiniteAnimationSpec<Float> animationSpec) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(new Fade(f, animationSpec), null, null, 6, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(float f, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return fadeOut(f, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition fadeOut(float f, FiniteAnimationSpec<Float> animationSpec) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new ExitTransitionImpl(new TransitionData(new Fade(f, animationSpec), null, null, 6, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideIn(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition slideIn(Function1<? super IntSize, IntOffset> initialOffset, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(initialOffset, "initialOffset");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(null, new Slide(initialOffset, animationSpec), null, 5, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideOut(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition slideOut(Function1<? super IntSize, IntOffset> targetOffset, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(targetOffset, "targetOffset");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new ExitTransitionImpl(new TransitionData(null, new Slide(targetOffset, animationSpec), null, 5, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(Alignment alignment, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandIn$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m3119boximpl(m81invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
                }

                /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
                public final long m81invokemzRDjE0mzRDjE0(long j) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return expandIn(alignment, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition expandIn(Alignment expandFrom, Function1<? super IntSize, IntSize> initialSize, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialSize, "initialSize");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(expandFrom, initialSize, animationSpec, z), 3, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(Alignment alignment, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkOut$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m3119boximpl(m86invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
                }

                /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
                public final long m86invokemzRDjE0mzRDjE0(long j) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return shrinkOut(alignment, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition shrinkOut(Alignment shrinkTowards, Function1<? super IntSize, IntSize> targetSize, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetSize, "targetSize");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(shrinkTowards, targetSize, animationSpec, z), 3, null));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(Alignment.Horizontal horizontal, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandHorizontally$1
                public final int invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return expandHorizontally(horizontal, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition expandHorizontally(Alignment.Horizontal expandFrom, final Function1<? super Integer, Integer> initialWidth, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialWidth, "initialWidth");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return expandIn(toAlignment(expandFrom), new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandHorizontally$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m3119boximpl(m80invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
            public final long m80invokemzRDjE0mzRDjE0(long j) {
                return IntSizeKt.IntSize(initialWidth.invoke(Integer.valueOf(IntSize.m3127getWidthimpl(j))).intValue(), IntSize.m3126getHeightimpl(j));
            }
        }, animationSpec, z);
    }

    public static /* synthetic */ EnterTransition expandVertically$default(Alignment.Vertical vertical, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$1
                public final int invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return expandVertically(vertical, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition expandVertically(Alignment.Vertical expandFrom, final Function1<? super Integer, Integer> initialHeight, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialHeight, "initialHeight");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return expandIn(toAlignment(expandFrom), new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m3119boximpl(m82invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
            public final long m82invokemzRDjE0mzRDjE0(long j) {
                return IntSizeKt.IntSize(IntSize.m3127getWidthimpl(j), initialHeight.invoke(Integer.valueOf(IntSize.m3126getHeightimpl(j))).intValue());
            }
        }, animationSpec, z);
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(Alignment.Horizontal horizontal, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkHorizontally$1
                public final int invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return shrinkHorizontally(horizontal, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition shrinkHorizontally(Alignment.Horizontal shrinkTowards, final Function1<? super Integer, Integer> targetWidth, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetWidth, "targetWidth");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return shrinkOut(toAlignment(shrinkTowards), new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkHorizontally$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m3119boximpl(m85invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
            public final long m85invokemzRDjE0mzRDjE0(long j) {
                return IntSizeKt.IntSize(targetWidth.invoke(Integer.valueOf(IntSize.m3127getWidthimpl(j))).intValue(), IntSize.m3126getHeightimpl(j));
            }
        }, animationSpec, z);
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(Alignment.Vertical vertical, Function1 function1, FiniteAnimationSpec finiteAnimationSpec, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$1
                public final int invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return shrinkVertically(vertical, function1, finiteAnimationSpec, z);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition shrinkVertically(Alignment.Vertical shrinkTowards, final Function1<? super Integer, Integer> targetHeight, FiniteAnimationSpec<IntSize> animationSpec, boolean z) {
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetHeight, "targetHeight");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return shrinkOut(toAlignment(shrinkTowards), new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m3119boximpl(m87invokemzRDjE0mzRDjE0(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mzRDjE0-mzRDjE0, reason: not valid java name */
            public final long m87invokemzRDjE0mzRDjE0(long j) {
                return IntSizeKt.IntSize(IntSize.m3127getWidthimpl(j), targetHeight.invoke(Integer.valueOf(IntSize.m3126getHeightimpl(j))).intValue());
            }
        }, animationSpec, z);
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInHorizontally$1
                public final int invoke(int i2) {
                    return (-i2) / 2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideInHorizontally(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition slideInHorizontally(final Function1<? super Integer, Integer> initialOffsetX, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(initialOffsetX, "initialOffsetX");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return slideIn(new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInHorizontally$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m3076boximpl(m88invokemHKZG7ImHKZG7I(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mHKZG7I-mHKZG7I, reason: not valid java name */
            public final long m88invokemHKZG7ImHKZG7I(long j) {
                return IntOffsetKt.IntOffset(initialOffsetX.invoke(Integer.valueOf(IntSize.m3127getWidthimpl(j))).intValue(), 0);
            }
        }, animationSpec);
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$1
                public final int invoke(int i2) {
                    return (-i2) / 2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideInVertically(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final EnterTransition slideInVertically(final Function1<? super Integer, Integer> initialOffsetY, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(initialOffsetY, "initialOffsetY");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return slideIn(new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m3076boximpl(m91invokemHKZG7ImHKZG7I(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mHKZG7I-mHKZG7I, reason: not valid java name */
            public final long m91invokemHKZG7ImHKZG7I(long j) {
                return IntOffsetKt.IntOffset(0, initialOffsetY.invoke(Integer.valueOf(IntSize.m3126getHeightimpl(j))).intValue());
            }
        }, animationSpec);
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutHorizontally$1
                public final int invoke(int i2) {
                    return (-i2) / 2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideOutHorizontally(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition slideOutHorizontally(final Function1<? super Integer, Integer> targetOffsetX, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(targetOffsetX, "targetOffsetX");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return slideOut(new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutHorizontally$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m3076boximpl(m92invokemHKZG7ImHKZG7I(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mHKZG7I-mHKZG7I, reason: not valid java name */
            public final long m92invokemHKZG7ImHKZG7I(long j) {
                return IntOffsetKt.IntOffset(targetOffsetX.invoke(Integer.valueOf(IntSize.m3127getWidthimpl(j))).intValue(), 0);
            }
        }, animationSpec);
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(Function1 function1, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$1
                public final int invoke(int i2) {
                    return (-i2) / 2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return Integer.valueOf(invoke(num.intValue()));
                }
            };
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
        }
        return slideOutVertically(function1, finiteAnimationSpec);
    }

    @ExperimentalAnimationApi
    public static final ExitTransition slideOutVertically(final Function1<? super Integer, Integer> targetOffsetY, FiniteAnimationSpec<IntOffset> animationSpec) {
        Intrinsics.checkNotNullParameter(targetOffsetY, "targetOffsetY");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return slideOut(new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m3076boximpl(m93invokemHKZG7ImHKZG7I(intSize.getPackedValue()));
            }

            /* renamed from: invoke-mHKZG7I-mHKZG7I, reason: not valid java name */
            public final long m93invokemHKZG7ImHKZG7I(long j) {
                return IntOffsetKt.IntOffset(0, targetOffsetY.invoke(Integer.valueOf(IntSize.m3126getHeightimpl(j))).intValue());
            }
        }, animationSpec);
    }

    private static final Alignment toAlignment(Alignment.Horizontal horizontal) {
        return Intrinsics.areEqual(horizontal, Alignment.INSTANCE.getStart()) ? Alignment.INSTANCE.getCenterStart() : Intrinsics.areEqual(horizontal, Alignment.INSTANCE.getEnd()) ? Alignment.INSTANCE.getCenterEnd() : Alignment.INSTANCE.getCenter();
    }

    private static final Alignment toAlignment(Alignment.Vertical vertical) {
        return Intrinsics.areEqual(vertical, Alignment.INSTANCE.getTop()) ? Alignment.INSTANCE.getTopCenter() : Intrinsics.areEqual(vertical, Alignment.INSTANCE.getBottom()) ? Alignment.INSTANCE.getBottomCenter() : Alignment.INSTANCE.getCenter();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0156  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.Modifier createModifier(androidx.compose.animation.core.Transition<androidx.compose.animation.EnterExitState> r17, final androidx.compose.animation.EnterTransition r18, final androidx.compose.animation.ExitTransition r19, androidx.compose.runtime.Composer r20, int r21) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.EnterExitTransitionKt.createModifier(androidx.compose.animation.core.Transition, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
    }

    /* renamed from: createModifier$lambda-1, reason: not valid java name */
    private static final boolean m77createModifier$lambda1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* renamed from: createModifier$lambda-2, reason: not valid java name */
    private static final void m78createModifier$lambda2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Modifier slideInOut(Modifier modifier, final Transition<EnterExitState> transition, final State<Slide> state, final State<Slide> state2) {
        return ComposedModifierKt.composed$default(modifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInOut$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            /* renamed from: invoke$lambda-1, reason: not valid java name */
            private static final boolean m89invoke$lambda1(MutableState<Boolean> mutableState) {
                return mutableState.getValue().booleanValue();
            }

            /* renamed from: invoke$lambda-2, reason: not valid java name */
            private static final void m90invoke$lambda2(MutableState<Boolean> mutableState, boolean z) {
                mutableState.setValue(Boolean.valueOf(z));
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(905891773);
                ComposerKt.sourceInformation(composer, "C767@33213L46:EnterExitTransition.kt#xbi5r1");
                Transition<EnterExitState> transition2 = transition;
                composer.startReplaceableGroup(-3686930);
                ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                boolean changed = composer.changed(transition2);
                Object rememberedValue = composer.rememberedValue();
                if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    composer.updateRememberedValue(rememberedValue);
                }
                composer.endReplaceableGroup();
                MutableState mutableState = (MutableState) rememberedValue;
                if (transition.getCurrentState() == transition.getTargetState()) {
                    m90invoke$lambda2(mutableState, false);
                } else if (state.getValue() != null || state2.getValue() != null) {
                    m90invoke$lambda2(mutableState, true);
                }
                if (m89invoke$lambda1(mutableState)) {
                    composer.startReplaceableGroup(905892282);
                    ComposerKt.sourceInformation(composer, "777@33537L59,778@33620L88");
                    Transition.DeferredAnimation createDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), "slide", composer, 448, 0);
                    Transition<EnterExitState> transition3 = transition;
                    State<Slide> state3 = state;
                    State<Slide> state4 = state2;
                    composer.startReplaceableGroup(-3686930);
                    ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                    boolean changed2 = composer.changed(transition3);
                    Object rememberedValue2 = composer.rememberedValue();
                    if (changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = new SlideModifier(createDeferredAnimation, state3, state4);
                        composer.updateRememberedValue(rememberedValue2);
                    }
                    composer.endReplaceableGroup();
                    composed = composed.then((SlideModifier) rememberedValue2);
                    composer.endReplaceableGroup();
                } else {
                    composer.startReplaceableGroup(905892530);
                    composer.endReplaceableGroup();
                }
                composer.endReplaceableGroup();
                return composed;
            }
        }, 1, null);
    }

    private static final Modifier shrinkExpand(Modifier modifier, final Transition<EnterExitState> transition, final State<ChangeSize> state, final State<ChangeSize> state2) {
        return ComposedModifierKt.composed$default(modifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkExpand$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            /* renamed from: invoke$lambda-1, reason: not valid java name */
            private static final boolean m83invoke$lambda1(MutableState<Boolean> mutableState) {
                return mutableState.getValue().booleanValue();
            }

            /* renamed from: invoke$lambda-2, reason: not valid java name */
            private static final void m84invoke$lambda2(MutableState<Boolean> mutableState, boolean z) {
                mutableState.setValue(Boolean.valueOf(z));
            }

            /* JADX WARN: Removed duplicated region for block: B:19:0x00e5  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x0121  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x014b  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0170  */
            /* JADX WARN: Removed duplicated region for block: B:32:0x0180  */
            /* JADX WARN: Removed duplicated region for block: B:43:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x014f  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final androidx.compose.ui.Modifier invoke(androidx.compose.ui.Modifier r23, androidx.compose.runtime.Composer r24, int r25) {
                /*
                    Method dump skipped, instructions count: 451
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.EnterExitTransitionKt$shrinkExpand$1.invoke(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createModifier$lambda-4, reason: not valid java name */
    public static final float m79createModifier$lambda4(State<Float> state) {
        return state.getValue().floatValue();
    }
}
