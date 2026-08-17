package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnimationModifier.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001*B\u001e\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001b\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 J)\u0010!\u001a\u00020\"*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fRO\u0010\u0010\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0011X\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/animation/SizeAnimationModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "animSpec", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/ui/unit/IntSize;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/animation/core/AnimationSpec;Lkotlinx/coroutines/CoroutineScope;)V", "animData", "Landroidx/compose/animation/SizeAnimationModifier$AnimData;", "getAnimData", "()Landroidx/compose/animation/SizeAnimationModifier$AnimData;", "setAnimData", "(Landroidx/compose/animation/SizeAnimationModifier$AnimData;)V", "getAnimSpec", "()Landroidx/compose/animation/core/AnimationSpec;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "startSize", "endSize", "", "getListener", "()Lkotlin/jvm/functions/Function2;", "setListener", "(Lkotlin/jvm/functions/Function2;)V", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "animateTo", "targetSize", "animateTo-mzRDjE0", "(J)J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "AnimData", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class SizeAnimationModifier implements LayoutModifier {
    private AnimData animData;
    private final AnimationSpec<IntSize> animSpec;
    private Function2<? super IntSize, ? super IntSize, Unit> listener;
    private final CoroutineScope scope;

    public SizeAnimationModifier(AnimationSpec<IntSize> animSpec, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(animSpec, "animSpec");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.animSpec = animSpec;
        this.scope = scope;
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean all(Function1<? super Modifier.Element, Boolean> function1) {
        return LayoutModifier.DefaultImpls.all(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean any(Function1<? super Modifier.Element, Boolean> function1) {
        return LayoutModifier.DefaultImpls.any(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldIn(R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
        return (R) LayoutModifier.DefaultImpls.foldIn(this, r, function2);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldOut(R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
        return (R) LayoutModifier.DefaultImpls.foldOut(this, r, function2);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.Modifier
    public Modifier then(Modifier modifier) {
        return LayoutModifier.DefaultImpls.then(this, modifier);
    }

    public final AnimationSpec<IntSize> getAnimSpec() {
        return this.animSpec;
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final Function2<IntSize, IntSize, Unit> getListener() {
        return this.listener;
    }

    public final void setListener(Function2<? super IntSize, ? super IntSize, Unit> function2) {
        this.listener = function2;
    }

    /* compiled from: AnimationModifier.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B$\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003ø\u0001\u0000J\u0019\u0010\u0010\u001a\u00020\u0004HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u000bJ6\u0010\u0012\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR%\u0010\u0006\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/animation/SizeAnimationModifier$AnimData;", "", "anim", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "startSize", "(Landroidx/compose/animation/core/Animatable;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnim", "()Landroidx/compose/animation/core/Animatable;", "getStartSize-YbymL2g", "()J", "setStartSize-ozmzZPI", "(J)V", "J", "component1", "component2", "component2-YbymL2g", "copy", "copy-O0kMr_c", "(Landroidx/compose/animation/core/Animatable;J)Landroidx/compose/animation/SizeAnimationModifier$AnimData;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class AnimData {
        public static final int $stable = 8;
        private final Animatable<IntSize, AnimationVector2D> anim;
        private long startSize;

        public /* synthetic */ AnimData(Animatable animatable, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(animatable, j);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: copy-O0kMr_c$default, reason: not valid java name */
        public static /* synthetic */ AnimData m101copyO0kMr_c$default(AnimData animData, Animatable animatable, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                animatable = animData.anim;
            }
            if ((i & 2) != 0) {
                j = animData.startSize;
            }
            return animData.m103copyO0kMr_c(animatable, j);
        }

        public final Animatable<IntSize, AnimationVector2D> component1() {
            return this.anim;
        }

        /* renamed from: component2-YbymL2g, reason: not valid java name and from getter */
        public final long getStartSize() {
            return this.startSize;
        }

        /* renamed from: copy-O0kMr_c, reason: not valid java name */
        public final AnimData m103copyO0kMr_c(Animatable<IntSize, AnimationVector2D> anim, long startSize) {
            Intrinsics.checkNotNullParameter(anim, "anim");
            return new AnimData(anim, startSize, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AnimData)) {
                return false;
            }
            AnimData animData = (AnimData) other;
            return Intrinsics.areEqual(this.anim, animData.anim) && IntSize.m3125equalsimpl0(this.startSize, animData.startSize);
        }

        public int hashCode() {
            return (this.anim.hashCode() * 31) + IntSize.m3128hashCodeimpl(this.startSize);
        }

        public String toString() {
            return "AnimData(anim=" + this.anim + ", startSize=" + ((Object) IntSize.m3130toStringimpl(this.startSize)) + ')';
        }

        private AnimData(Animatable<IntSize, AnimationVector2D> animatable, long j) {
            this.anim = animatable;
            this.startSize = j;
        }

        public final Animatable<IntSize, AnimationVector2D> getAnim() {
            return this.anim;
        }

        /* renamed from: getStartSize-YbymL2g, reason: not valid java name */
        public final long m104getStartSizeYbymL2g() {
            return this.startSize;
        }

        /* renamed from: setStartSize-ozmzZPI, reason: not valid java name */
        public final void m105setStartSizeozmzZPI(long j) {
            this.startSize = j;
        }
    }

    public final AnimData getAnimData() {
        return this.animData;
    }

    public final void setAnimData(AnimData animData) {
        this.animData = animData;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo56measure3p2s80s(MeasureScope receiver, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        final Placeable mo2510measureBRTryo0 = measurable.mo2510measureBRTryo0(j);
        long m100animateTomzRDjE0 = m100animateTomzRDjE0(IntSizeKt.IntSize(mo2510measureBRTryo0.getWidth(), mo2510measureBRTryo0.getHeight()));
        return MeasureScope.DefaultImpls.layout$default(receiver, IntSize.m3127getWidthimpl(m100animateTomzRDjE0), IntSize.m3126getHeightimpl(m100animateTomzRDjE0), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SizeAnimationModifier$measure$1
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
                Placeable.PlacementScope.placeRelative$default(layout, Placeable.this, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* renamed from: animateTo-mzRDjE0, reason: not valid java name */
    public final long m100animateTomzRDjE0(long targetSize) {
        AnimData animData = this.animData;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (animData == null) {
            animData = null;
        } else if (!IntSize.m3125equalsimpl0(targetSize, animData.getAnim().getTargetValue().getPackedValue())) {
            animData.m105setStartSizeozmzZPI(animData.getAnim().getValue().getPackedValue());
            BuildersKt__Builders_commonKt.launch$default(getScope(), null, null, new SizeAnimationModifier$animateTo$data$1$1(animData, targetSize, this, null), 3, null);
        }
        if (animData == null) {
            animData = new AnimData(new Animatable(IntSize.m3119boximpl(targetSize), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), IntSize.m3119boximpl(IntSizeKt.IntSize(1, 1))), targetSize, defaultConstructorMarker);
        }
        this.animData = animData;
        return animData.getAnim().getValue().getPackedValue();
    }
}
