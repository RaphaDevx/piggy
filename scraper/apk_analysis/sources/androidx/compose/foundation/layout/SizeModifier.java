package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002BQ\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001aH\u0016J)\u0010\"\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0012H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J\u001c\u0010)\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010*\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001aH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0005\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R!\u0010\u0011\u001a\u00020\u0012*\u00020\u00138BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"}, d2 = {"Landroidx/compose/foundation/layout/SizeModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "minWidth", "Landroidx/compose/ui/unit/Dp;", "minHeight", "maxWidth", "maxHeight", "enforceIncoming", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(FFFFZLkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "targetConstraints", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/unit/Density;", "getTargetConstraints-OenEA2s", "(Landroidx/compose/ui/unit/Density;)J", "equals", Request.JsonKeys.OTHER, "", "hashCode", "", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "foundation-layout_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class SizeModifier extends InspectorValueInfo implements LayoutModifier {
    private final boolean enforceIncoming;
    private final float maxHeight;
    private final float maxWidth;
    private final float minHeight;
    private final float minWidth;

    public /* synthetic */ SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z, function1);
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

    @Override // androidx.compose.ui.Modifier
    public Modifier then(Modifier modifier) {
        return LayoutModifier.DefaultImpls.then(this, modifier);
    }

    public /* synthetic */ SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM() : f, (i & 2) != 0 ? Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM() : f2, (i & 4) != 0 ? Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM() : f3, (i & 8) != 0 ? Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM() : f4, z, function1, null);
    }

    private SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1<? super InspectorInfo, Unit> function1) {
        super(function1);
        this.minWidth = f;
        this.minHeight = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.enforceIncoming = z;
    }

    /* renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    private final long m366getTargetConstraintsOenEA2s(Density density) {
        int i;
        int coerceAtLeast;
        int i2 = 0;
        int mo222roundToPx0680j_4 = !Dp.m3012equalsimpl0(this.maxWidth, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM()) ? density.mo222roundToPx0680j_4(((Dp) RangesKt.coerceAtLeast(Dp.m3005boximpl(this.maxWidth), Dp.m3005boximpl(Dp.m3007constructorimpl(0)))).getValue()) : Integer.MAX_VALUE;
        int mo222roundToPx0680j_42 = !Dp.m3012equalsimpl0(this.maxHeight, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM()) ? density.mo222roundToPx0680j_4(((Dp) RangesKt.coerceAtLeast(Dp.m3005boximpl(this.maxHeight), Dp.m3005boximpl(Dp.m3007constructorimpl(0)))).getValue()) : Integer.MAX_VALUE;
        if (Dp.m3012equalsimpl0(this.minWidth, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM()) || (i = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo222roundToPx0680j_4(this.minWidth), mo222roundToPx0680j_4), 0)) == Integer.MAX_VALUE) {
            i = 0;
        }
        if (!Dp.m3012equalsimpl0(this.minHeight, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM()) && (coerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo222roundToPx0680j_4(this.minHeight), mo222roundToPx0680j_42), 0)) != Integer.MAX_VALUE) {
            i2 = coerceAtLeast;
        }
        return ConstraintsKt.Constraints(i, mo222roundToPx0680j_4, i2, mo222roundToPx0680j_42);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo56measure3p2s80s(MeasureScope receiver, Measurable measurable, long j) {
        int coerceAtMost;
        int coerceAtLeast;
        int coerceAtMost2;
        int coerceAtLeast2;
        long Constraints;
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long m366getTargetConstraintsOenEA2s = m366getTargetConstraintsOenEA2s(receiver);
        if (this.enforceIncoming) {
            Constraints = ConstraintsKt.m2989constrainN9IONVI(j, m366getTargetConstraintsOenEA2s);
        } else {
            if (!Dp.m3012equalsimpl0(this.minWidth, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM())) {
                coerceAtMost = Constraints.m2979getMinWidthimpl(m366getTargetConstraintsOenEA2s);
            } else {
                coerceAtMost = RangesKt.coerceAtMost(Constraints.m2979getMinWidthimpl(j), Constraints.m2977getMaxWidthimpl(m366getTargetConstraintsOenEA2s));
            }
            if (!Dp.m3012equalsimpl0(this.maxWidth, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM())) {
                coerceAtLeast = Constraints.m2977getMaxWidthimpl(m366getTargetConstraintsOenEA2s);
            } else {
                coerceAtLeast = RangesKt.coerceAtLeast(Constraints.m2977getMaxWidthimpl(j), Constraints.m2979getMinWidthimpl(m366getTargetConstraintsOenEA2s));
            }
            if (!Dp.m3012equalsimpl0(this.minHeight, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM())) {
                coerceAtMost2 = Constraints.m2978getMinHeightimpl(m366getTargetConstraintsOenEA2s);
            } else {
                coerceAtMost2 = RangesKt.coerceAtMost(Constraints.m2978getMinHeightimpl(j), Constraints.m2976getMaxHeightimpl(m366getTargetConstraintsOenEA2s));
            }
            if (!Dp.m3012equalsimpl0(this.maxHeight, Dp.INSTANCE.m3027getUnspecifiedD9Ej5fM())) {
                coerceAtLeast2 = Constraints.m2976getMaxHeightimpl(m366getTargetConstraintsOenEA2s);
            } else {
                coerceAtLeast2 = RangesKt.coerceAtLeast(Constraints.m2976getMaxHeightimpl(j), Constraints.m2978getMinHeightimpl(m366getTargetConstraintsOenEA2s));
            }
            Constraints = ConstraintsKt.Constraints(coerceAtMost, coerceAtLeast, coerceAtMost2, coerceAtLeast2);
        }
        final Placeable mo2510measureBRTryo0 = measurable.mo2510measureBRTryo0(Constraints);
        return MeasureScope.DefaultImpls.layout$default(receiver, mo2510measureBRTryo0.getWidth(), mo2510measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.SizeModifier$measure$1
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

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long m366getTargetConstraintsOenEA2s = m366getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2975getHasFixedWidthimpl(m366getTargetConstraintsOenEA2s)) {
            return Constraints.m2977getMaxWidthimpl(m366getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2991constrainWidthK40F9xA(m366getTargetConstraintsOenEA2s, measurable.minIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long m366getTargetConstraintsOenEA2s = m366getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2974getHasFixedHeightimpl(m366getTargetConstraintsOenEA2s)) {
            return Constraints.m2976getMaxHeightimpl(m366getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2990constrainHeightK40F9xA(m366getTargetConstraintsOenEA2s, measurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long m366getTargetConstraintsOenEA2s = m366getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2975getHasFixedWidthimpl(m366getTargetConstraintsOenEA2s)) {
            return Constraints.m2977getMaxWidthimpl(m366getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2991constrainWidthK40F9xA(m366getTargetConstraintsOenEA2s, measurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long m366getTargetConstraintsOenEA2s = m366getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2974getHasFixedHeightimpl(m366getTargetConstraintsOenEA2s)) {
            return Constraints.m2976getMaxHeightimpl(m366getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2990constrainHeightK40F9xA(m366getTargetConstraintsOenEA2s, measurable.maxIntrinsicHeight(i));
    }

    public boolean equals(Object other) {
        if (!(other instanceof SizeModifier)) {
            return false;
        }
        SizeModifier sizeModifier = (SizeModifier) other;
        return Dp.m3012equalsimpl0(this.minWidth, sizeModifier.minWidth) && Dp.m3012equalsimpl0(this.minHeight, sizeModifier.minHeight) && Dp.m3012equalsimpl0(this.maxWidth, sizeModifier.maxWidth) && Dp.m3012equalsimpl0(this.maxHeight, sizeModifier.maxHeight) && this.enforceIncoming == sizeModifier.enforceIncoming;
    }

    public int hashCode() {
        return ((((((Dp.m3013hashCodeimpl(this.minWidth) * 31) + Dp.m3013hashCodeimpl(this.minHeight)) * 31) + Dp.m3013hashCodeimpl(this.maxWidth)) * 31) + Dp.m3013hashCodeimpl(this.maxHeight)) * 31;
    }
}
