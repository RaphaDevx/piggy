package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListItemContentFactory.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\bJ*\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0014\u0010\"\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0014\u0010#\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016R\u001c\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u00020\rX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u000eR\u0019\u0010\u000f\u001a\u00020\rX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u000e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/foundation/lazy/LazyItemScopeImpl;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "density", "Landroidx/compose/ui/unit/Density;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "(Landroidx/compose/ui/unit/Density;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConstraints-msEJaDk", "()J", "J", "getDensity", "()Landroidx/compose/ui/unit/Density;", "maxHeight", "Landroidx/compose/ui/unit/Dp;", "F", "maxWidth", "component1", "component2", "component2-msEJaDk", "copy", "copy-0kLqBqw", "(Landroidx/compose/ui/unit/Density;J)Landroidx/compose/foundation/lazy/LazyItemScopeImpl;", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "", "toString", "", "fillParentMaxHeight", "Landroidx/compose/ui/Modifier;", "fraction", "", "fillParentMaxSize", "fillParentMaxWidth", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final /* data */ class LazyItemScopeImpl implements LazyItemScope {
    private final long constraints;
    private final Density density;
    private final float maxHeight;
    private final float maxWidth;

    public /* synthetic */ LazyItemScopeImpl(Density density, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(density, j);
    }

    /* renamed from: copy-0kLqBqw$default, reason: not valid java name */
    public static /* synthetic */ LazyItemScopeImpl m383copy0kLqBqw$default(LazyItemScopeImpl lazyItemScopeImpl, Density density, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            density = lazyItemScopeImpl.density;
        }
        if ((i & 2) != 0) {
            j = lazyItemScopeImpl.constraints;
        }
        return lazyItemScopeImpl.m385copy0kLqBqw(density, j);
    }

    /* renamed from: component1, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* renamed from: component2-msEJaDk, reason: not valid java name and from getter */
    public final long getConstraints() {
        return this.constraints;
    }

    /* renamed from: copy-0kLqBqw, reason: not valid java name */
    public final LazyItemScopeImpl m385copy0kLqBqw(Density density, long constraints) {
        Intrinsics.checkNotNullParameter(density, "density");
        return new LazyItemScopeImpl(density, constraints, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LazyItemScopeImpl)) {
            return false;
        }
        LazyItemScopeImpl lazyItemScopeImpl = (LazyItemScopeImpl) other;
        return Intrinsics.areEqual(this.density, lazyItemScopeImpl.density) && Constraints.m2970equalsimpl0(this.constraints, lazyItemScopeImpl.constraints);
    }

    public int hashCode() {
        return (this.density.hashCode() * 31) + Constraints.m2980hashCodeimpl(this.constraints);
    }

    public String toString() {
        return "LazyItemScopeImpl(density=" + this.density + ", constraints=" + ((Object) Constraints.m2982toStringimpl(this.constraints)) + ')';
    }

    private LazyItemScopeImpl(Density density, long j) {
        this.density = density;
        this.constraints = j;
        this.maxWidth = density.mo225toDpu2uoSUM(Constraints.m2977getMaxWidthimpl(m386getConstraintsmsEJaDk()));
        this.maxHeight = density.mo225toDpu2uoSUM(Constraints.m2976getMaxHeightimpl(m386getConstraintsmsEJaDk()));
    }

    public final Density getDensity() {
        return this.density;
    }

    /* renamed from: getConstraints-msEJaDk, reason: not valid java name */
    public final long m386getConstraintsmsEJaDk() {
        return this.constraints;
    }

    @Override // androidx.compose.foundation.lazy.LazyItemScope
    public Modifier fillParentMaxSize(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return SizeKt.m357sizeVpY3zN4(modifier, Dp.m3007constructorimpl(this.maxWidth * f), Dp.m3007constructorimpl(this.maxHeight * f));
    }

    @Override // androidx.compose.foundation.lazy.LazyItemScope
    public Modifier fillParentMaxWidth(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return SizeKt.m360width3ABfNKs(modifier, Dp.m3007constructorimpl(this.maxWidth * f));
    }

    @Override // androidx.compose.foundation.lazy.LazyItemScope
    public Modifier fillParentMaxHeight(Modifier modifier, float f) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return SizeKt.m343height3ABfNKs(modifier, Dp.m3007constructorimpl(this.maxHeight * f));
    }
}
