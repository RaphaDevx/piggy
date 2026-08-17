package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.LayoutNodeWrapper;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutCoordinates.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0005\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0012\u0010\t\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0012\u0010\n\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRoot", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return layoutCoordinates.mo2524localToRootMKHz9U(Offset.INSTANCE.m1041getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return layoutCoordinates.mo2525localToWindowMKHz9U(Offset.INSTANCE.m1041getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return LayoutCoordinates.DefaultImpls.localBoundingBoxOf$default(findRoot(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates findRoot = findRoot(layoutCoordinates);
        Rect boundsInRoot = boundsInRoot(layoutCoordinates);
        long mo2525localToWindowMKHz9U = findRoot.mo2525localToWindowMKHz9U(OffsetKt.Offset(boundsInRoot.getLeft(), boundsInRoot.getTop()));
        long mo2525localToWindowMKHz9U2 = findRoot.mo2525localToWindowMKHz9U(OffsetKt.Offset(boundsInRoot.getRight(), boundsInRoot.getTop()));
        long mo2525localToWindowMKHz9U3 = findRoot.mo2525localToWindowMKHz9U(OffsetKt.Offset(boundsInRoot.getRight(), boundsInRoot.getBottom()));
        long mo2525localToWindowMKHz9U4 = findRoot.mo2525localToWindowMKHz9U(OffsetKt.Offset(boundsInRoot.getLeft(), boundsInRoot.getBottom()));
        return new Rect(ComparisonsKt.minOf(Offset.m1025getXimpl(mo2525localToWindowMKHz9U), Offset.m1025getXimpl(mo2525localToWindowMKHz9U2), Offset.m1025getXimpl(mo2525localToWindowMKHz9U4), Offset.m1025getXimpl(mo2525localToWindowMKHz9U3)), ComparisonsKt.minOf(Offset.m1026getYimpl(mo2525localToWindowMKHz9U), Offset.m1026getYimpl(mo2525localToWindowMKHz9U2), Offset.m1026getYimpl(mo2525localToWindowMKHz9U4), Offset.m1026getYimpl(mo2525localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m1025getXimpl(mo2525localToWindowMKHz9U), Offset.m1025getXimpl(mo2525localToWindowMKHz9U2), Offset.m1025getXimpl(mo2525localToWindowMKHz9U4), Offset.m1025getXimpl(mo2525localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m1026getYimpl(mo2525localToWindowMKHz9U), Offset.m1026getYimpl(mo2525localToWindowMKHz9U2), Offset.m1026getYimpl(mo2525localToWindowMKHz9U4), Offset.m1026getYimpl(mo2525localToWindowMKHz9U3)));
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates == null ? Offset.INSTANCE.m1041getZeroF1C5BW0() : parentLayoutCoordinates.mo2523localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m1041getZeroF1C5BW0());
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            return LayoutCoordinates.DefaultImpls.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null);
        }
        return new Rect(0.0f, 0.0f, IntSize.m3127getWidthimpl(layoutCoordinates.mo2522getSizeYbymL2g()), IntSize.m3126getHeightimpl(layoutCoordinates.mo2522getSizeYbymL2g()));
    }

    public static final LayoutCoordinates findRoot(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        LayoutNodeWrapper layoutNodeWrapper = layoutCoordinates2 instanceof LayoutNodeWrapper ? (LayoutNodeWrapper) layoutCoordinates2 : null;
        if (layoutNodeWrapper == null) {
            return layoutCoordinates2;
        }
        LayoutNodeWrapper wrappedBy = layoutNodeWrapper.getWrappedBy();
        while (true) {
            LayoutNodeWrapper layoutNodeWrapper2 = wrappedBy;
            LayoutNodeWrapper layoutNodeWrapper3 = layoutNodeWrapper;
            layoutNodeWrapper = layoutNodeWrapper2;
            if (layoutNodeWrapper != null) {
                wrappedBy = layoutNodeWrapper.getWrappedBy();
            } else {
                return layoutNodeWrapper3;
            }
        }
    }
}
