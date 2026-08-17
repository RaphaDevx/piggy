package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NestedScrollDelegatingWrapper.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0096@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0096@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/ParentWrapperNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "parent", "self", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "getParent", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setParent", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "getSelf", "setSelf", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class ParentWrapperNestedScrollConnection implements NestedScrollConnection {
    private NestedScrollConnection parent;
    private NestedScrollConnection self;

    public ParentWrapperNestedScrollConnection(NestedScrollConnection parent, NestedScrollConnection self) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(self, "self");
        this.parent = parent;
        this.self = self;
    }

    public final NestedScrollConnection getParent() {
        return this.parent;
    }

    public final void setParent(NestedScrollConnection nestedScrollConnection) {
        Intrinsics.checkNotNullParameter(nestedScrollConnection, "<set-?>");
        this.parent = nestedScrollConnection;
    }

    public final NestedScrollConnection getSelf() {
        return this.self;
    }

    public final void setSelf(NestedScrollConnection nestedScrollConnection) {
        Intrinsics.checkNotNullParameter(nestedScrollConnection, "<set-?>");
        this.self = nestedScrollConnection;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo234onPreScrollOzD1aCk(long available, int source) {
        long mo234onPreScrollOzD1aCk = this.parent.mo234onPreScrollOzD1aCk(available, source);
        return Offset.m1030plusMKHz9U(mo234onPreScrollOzD1aCk, this.self.mo234onPreScrollOzD1aCk(Offset.m1029minusMKHz9U(available, mo234onPreScrollOzD1aCk), source));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo232onPostScrollDzOQY0M(long consumed, long available, int source) {
        long mo232onPostScrollDzOQY0M = this.self.mo232onPostScrollDzOQY0M(consumed, available, source);
        return Offset.m1030plusMKHz9U(mo232onPostScrollDzOQY0M, this.parent.mo232onPostScrollDzOQY0M(Offset.m1030plusMKHz9U(consumed, mo232onPostScrollDzOQY0M), Offset.m1029minusMKHz9U(available, mo232onPostScrollDzOQY0M), source));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo233onPreFlingQWom1Mo(long r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L41
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            long r6 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L72
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            long r7 = r0.J$0
            java.lang.Object r6 = r0.L$0
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection r6 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L55
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r9 = r6.getParent()
            r0.L$0 = r6
            r0.J$0 = r7
            r0.label = r4
            java.lang.Object r9 = r9.mo233onPreFlingQWom1Mo(r7, r0)
            if (r9 != r1) goto L55
            return r1
        L55:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r4 = r9.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r6 = r6.getSelf()
            long r7 = androidx.compose.ui.unit.Velocity.m3195minusAH228Gc(r7, r4)
            r9 = 0
            r0.L$0 = r9
            r0.J$0 = r4
            r0.label = r3
            java.lang.Object r9 = r6.mo233onPreFlingQWom1Mo(r7, r0)
            if (r9 != r1) goto L71
            return r1
        L71:
            r6 = r4
        L72:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r8 = r9.getPackedValue()
            long r6 = androidx.compose.ui.unit.Velocity.m3196plusAH228Gc(r6, r8)
            androidx.compose.ui.unit.Velocity r6 = androidx.compose.ui.unit.Velocity.m3183boximpl(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection.mo233onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo231onPostFlingRZ2iAVY(long r16, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r20
            boolean r2 = r1 instanceof androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1
            if (r2 == 0) goto L17
            r2 = r1
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1 r2 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L17
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L1c
        L17:
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1 r2 = new androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1
            r2.<init>(r15, r1)
        L1c:
            java.lang.Object r1 = r2.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r10 = 2
            r4 = 1
            if (r3 == 0) goto L48
            if (r3 == r4) goto L3a
            if (r3 != r10) goto L32
            long r2 = r2.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8f
        L32:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3a:
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r0 = r2.L$0
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection r0 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection) r0
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r3
            r11 = r5
            goto L67
        L48:
            kotlin.ResultKt.throwOnFailure(r1)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r15.getSelf()
            r2.L$0 = r0
            r11 = r16
            r2.J$0 = r11
            r13 = r18
            r2.J$1 = r13
            r2.label = r4
            r4 = r16
            r6 = r18
            r8 = r2
            java.lang.Object r1 = r3.mo231onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L67
            return r9
        L67:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r3 = r1.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r0 = r0.getParent()
            long r5 = androidx.compose.ui.unit.Velocity.m3196plusAH228Gc(r11, r3)
            long r7 = androidx.compose.ui.unit.Velocity.m3195minusAH228Gc(r13, r3)
            r1 = 0
            r2.L$0 = r1
            r2.J$0 = r3
            r2.label = r10
            r15 = r0
            r16 = r5
            r18 = r7
            r20 = r2
            java.lang.Object r1 = r15.mo231onPostFlingRZ2iAVY(r16, r18, r20)
            if (r1 != r9) goto L8e
            return r9
        L8e:
            r2 = r3
        L8f:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r0 = r1.getPackedValue()
            long r0 = androidx.compose.ui.unit.Velocity.m3196plusAH228Gc(r2, r0)
            androidx.compose.ui.unit.Velocity r0 = androidx.compose.ui.unit.Velocity.m3183boximpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection.mo231onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
