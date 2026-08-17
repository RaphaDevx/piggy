package androidx.compose.ui.input.pointer;

import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HitPathTracker.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\nJ\u001b\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/input/pointer/HitPathTracker;", "", "rootCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "root", "Landroidx/compose/ui/input/pointer/NodeParent;", "getRoot$ui_release", "()Landroidx/compose/ui/input/pointer/NodeParent;", "addHitPath", "", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerInputFilters", "", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "addHitPath-KNwqfcY", "(JLjava/util/List;)V", "dispatchChanges", "", "internalPointerEvent", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "processCancel", "removeDetachedPointerInputFilters", "removeHitPath", "removeHitPath-0FcD4WY", "(J)V", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class HitPathTracker {
    private final NodeParent root;
    private final LayoutCoordinates rootCoordinates;

    public HitPathTracker(LayoutCoordinates rootCoordinates) {
        Intrinsics.checkNotNullParameter(rootCoordinates, "rootCoordinates");
        this.rootCoordinates = rootCoordinates;
        this.root = new NodeParent();
    }

    /* renamed from: getRoot$ui_release, reason: from getter */
    public final NodeParent getRoot() {
        return this.root;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007f A[LOOP:0: B:4:0x0012->B:23:0x007f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0081 A[SYNTHETIC] */
    /* renamed from: addHitPath-KNwqfcY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2409addHitPathKNwqfcY(long r11, java.util.List<? extends androidx.compose.ui.input.pointer.PointerInputFilter> r13) {
        /*
            r10 = this;
            java.lang.String r0 = "pointerInputFilters"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            androidx.compose.ui.input.pointer.NodeParent r10 = r10.root
            int r0 = r13.size()
            int r0 = r0 + (-1)
            if (r0 < 0) goto L81
            r1 = 1
            r2 = 0
            r3 = r2
        L12:
            int r4 = r3 + 1
            java.lang.Object r3 = r13.get(r3)
            androidx.compose.ui.input.pointer.PointerInputFilter r3 = (androidx.compose.ui.input.pointer.PointerInputFilter) r3
            if (r1 == 0) goto L62
            androidx.compose.runtime.collection.MutableVector r5 = r10.getChildren()
            int r6 = r5.getSize()
            if (r6 <= 0) goto L3f
            java.lang.Object[] r5 = r5.getContent()
            r7 = r2
        L2b:
            r8 = r5[r7]
            r9 = r8
            androidx.compose.ui.input.pointer.Node r9 = (androidx.compose.ui.input.pointer.Node) r9
            androidx.compose.ui.input.pointer.PointerInputFilter r9 = r9.getPointerInputFilter()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r3)
            if (r9 == 0) goto L3b
            goto L40
        L3b:
            int r7 = r7 + 1
            if (r7 < r6) goto L2b
        L3f:
            r8 = 0
        L40:
            androidx.compose.ui.input.pointer.Node r8 = (androidx.compose.ui.input.pointer.Node) r8
            if (r8 == 0) goto L61
            androidx.compose.runtime.collection.MutableVector r10 = r8.getPointerIds()
            androidx.compose.ui.input.pointer.PointerId r3 = androidx.compose.ui.input.pointer.PointerId.m2416boximpl(r11)
            boolean r10 = r10.contains(r3)
            if (r10 != 0) goto L5d
            androidx.compose.runtime.collection.MutableVector r10 = r8.getPointerIds()
            androidx.compose.ui.input.pointer.PointerId r3 = androidx.compose.ui.input.pointer.PointerId.m2416boximpl(r11)
            r10.add(r3)
        L5d:
            androidx.compose.ui.input.pointer.NodeParent r8 = (androidx.compose.ui.input.pointer.NodeParent) r8
            r10 = r8
            goto L7c
        L61:
            r1 = r2
        L62:
            androidx.compose.ui.input.pointer.Node r5 = new androidx.compose.ui.input.pointer.Node
            r5.<init>(r3)
            androidx.compose.runtime.collection.MutableVector r3 = r5.getPointerIds()
            androidx.compose.ui.input.pointer.PointerId r6 = androidx.compose.ui.input.pointer.PointerId.m2416boximpl(r11)
            r3.add(r6)
            androidx.compose.runtime.collection.MutableVector r10 = r10.getChildren()
            r10.add(r5)
            androidx.compose.ui.input.pointer.NodeParent r5 = (androidx.compose.ui.input.pointer.NodeParent) r5
            r10 = r5
        L7c:
            if (r4 <= r0) goto L7f
            goto L81
        L7f:
            r3 = r4
            goto L12
        L81:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.HitPathTracker.m2409addHitPathKNwqfcY(long, java.util.List):void");
    }

    /* renamed from: removeHitPath-0FcD4WY, reason: not valid java name */
    public final void m2410removeHitPath0FcD4WY(long pointerId) {
        this.root.m2414recursivelyRemovePointerId0FcD4WY(pointerId);
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent) {
        Intrinsics.checkNotNullParameter(internalPointerEvent, "internalPointerEvent");
        return this.root.dispatchFinalEventPass() || this.root.dispatchMainEventPass(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent);
    }

    public final void processCancel() {
        this.root.dispatchCancel();
        this.root.clear();
    }

    public final void removeDetachedPointerInputFilters() {
        this.root.removeDetachedPointerInputFilters();
    }
}
