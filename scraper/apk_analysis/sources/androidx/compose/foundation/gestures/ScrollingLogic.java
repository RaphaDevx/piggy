package androidx.compose.foundation.gestures;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: Scrollable.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0086@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u001b\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&J\u001b\u0010'\u001a\u00020#2\u0006\u0010$\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010&J'\u0010)\u001a\u00020 *\u00020*2\u0006\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/J\n\u00100\u001a\u00020 *\u00020 J\u0017\u00101\u001a\u00020 *\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103J\u0017\u00101\u001a\u00020 *\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00103J\u001a\u00105\u001a\u00020#*\u00020 ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b6\u00107J\u001a\u00108\u001a\u00020\u0019*\u00020 ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b9\u00107R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006:"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollingLogic;", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseDirection", "", "nestedScrollDispatcher", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "scrollableState", "Landroidx/compose/foundation/gestures/ScrollableState;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "(Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/runtime/State;Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/FlingBehavior;)V", "getFlingBehavior", "()Landroidx/compose/foundation/gestures/FlingBehavior;", "getNestedScrollDispatcher", "()Landroidx/compose/runtime/State;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "getReverseDirection", "()Z", "getScrollableState", "()Landroidx/compose/foundation/gestures/ScrollableState;", "doFlingAnimation", "Landroidx/compose/ui/unit/Velocity;", "available", "doFlingAnimation-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStopped", "", "axisVelocity", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performRawScroll", "Landroidx/compose/ui/geometry/Offset;", "scroll", "performRawScroll-MK-Hz9U", "(J)J", "performRelocationScroll", "performRelocationScroll-MK-Hz9U", "dispatchScroll", "Landroidx/compose/foundation/gestures/ScrollScope;", "scrollDelta", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "dispatchScroll-Tj5QQXo", "(Landroidx/compose/foundation/gestures/ScrollScope;FI)F", "reverseIfNeeded", "toFloat", "toFloat-k-4lQ0M", "(J)F", "toFloat-TH1AsA0", "toOffset", "toOffset-tuRUvjQ", "(F)J", "toVelocity", "toVelocity-adjELrA", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class ScrollingLogic {
    private final FlingBehavior flingBehavior;
    private final State<NestedScrollDispatcher> nestedScrollDispatcher;
    private final Orientation orientation;
    private final boolean reverseDirection;
    private final ScrollableState scrollableState;

    public ScrollingLogic(Orientation orientation, boolean z, State<NestedScrollDispatcher> nestedScrollDispatcher, ScrollableState scrollableState, FlingBehavior flingBehavior) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(nestedScrollDispatcher, "nestedScrollDispatcher");
        Intrinsics.checkNotNullParameter(scrollableState, "scrollableState");
        Intrinsics.checkNotNullParameter(flingBehavior, "flingBehavior");
        this.orientation = orientation;
        this.reverseDirection = z;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.scrollableState = scrollableState;
        this.flingBehavior = flingBehavior;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final boolean getReverseDirection() {
        return this.reverseDirection;
    }

    public final State<NestedScrollDispatcher> getNestedScrollDispatcher() {
        return this.nestedScrollDispatcher;
    }

    public final ScrollableState getScrollableState() {
        return this.scrollableState;
    }

    public final FlingBehavior getFlingBehavior() {
        return this.flingBehavior;
    }

    /* renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m241toOffsettuRUvjQ(float f) {
        if (f == 0.0f) {
            return Offset.INSTANCE.m1041getZeroF1C5BW0();
        }
        return this.orientation == Orientation.Horizontal ? OffsetKt.Offset(f, 0.0f) : OffsetKt.Offset(0.0f, f);
    }

    /* renamed from: toVelocity-adjELrA, reason: not valid java name */
    public final long m242toVelocityadjELrA(float f) {
        return this.orientation == Orientation.Horizontal ? VelocityKt.Velocity(f, 0.0f) : VelocityKt.Velocity(0.0f, f);
    }

    /* renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m240toFloatk4lQ0M(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m1025getXimpl(j) : Offset.m1026getYimpl(j);
    }

    /* renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float m239toFloatTH1AsA0(long j) {
        return this.orientation == Orientation.Horizontal ? Velocity.m3192getXimpl(j) : Velocity.m3193getYimpl(j);
    }

    public final float reverseIfNeeded(float f) {
        return this.reverseDirection ? f * (-1) : f;
    }

    /* renamed from: dispatchScroll-Tj5QQXo, reason: not valid java name */
    public final float m235dispatchScrollTj5QQXo(ScrollScope receiver, float f, int i) {
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        NestedScrollDispatcher value = this.nestedScrollDispatcher.getValue();
        float m240toFloatk4lQ0M = f - m240toFloatk4lQ0M(value.m2386dispatchPreScrollOzD1aCk(m241toOffsettuRUvjQ(f), i));
        float reverseIfNeeded = reverseIfNeeded(receiver.scrollBy(reverseIfNeeded(m240toFloatk4lQ0M)));
        float f2 = m240toFloatk4lQ0M - reverseIfNeeded;
        value.m2384dispatchPostScrollDzOQY0M(m241toOffsettuRUvjQ(reverseIfNeeded), m241toOffsettuRUvjQ(f2), i);
        return f2;
    }

    /* renamed from: performRawScroll-MK-Hz9U, reason: not valid java name */
    public final long m237performRawScrollMKHz9U(long scroll) {
        if (this.scrollableState.isScrollInProgress()) {
            return Offset.INSTANCE.m1041getZeroF1C5BW0();
        }
        return m241toOffsettuRUvjQ(reverseIfNeeded(this.scrollableState.dispatchRawDelta(reverseIfNeeded(m240toFloatk4lQ0M(scroll)))));
    }

    /* renamed from: performRelocationScroll-MK-Hz9U, reason: not valid java name */
    public final long m238performRelocationScrollMKHz9U(long scroll) {
        BuildersKt__Builders_commonKt.launch$default(this.nestedScrollDispatcher.getValue().getCoroutineScope(), null, null, new ScrollingLogic$performRelocationScroll$1(this, scroll, null), 3, null);
        return scroll;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0086 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onDragStopped(float r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1
            r0.<init>(r8, r10)
        L19:
            r6 = r0
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L4e
            if (r1 == r4) goto L44
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            kotlin.ResultKt.throwOnFailure(r10)
            goto La9
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            long r8 = r6.J$0
            java.lang.Object r1 = r6.L$0
            androidx.compose.foundation.gestures.ScrollingLogic r1 = (androidx.compose.foundation.gestures.ScrollingLogic) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L87
        L44:
            long r8 = r6.J$0
            java.lang.Object r1 = r6.L$0
            androidx.compose.foundation.gestures.ScrollingLogic r1 = (androidx.compose.foundation.gestures.ScrollingLogic) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L70
        L4e:
            kotlin.ResultKt.throwOnFailure(r10)
            long r9 = r8.m242toVelocityadjELrA(r9)
            androidx.compose.runtime.State r1 = r8.getNestedScrollDispatcher()
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r1 = (androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher) r1
            r6.L$0 = r8
            r6.J$0 = r9
            r6.label = r4
            java.lang.Object r1 = r1.m2385dispatchPreFlingQWom1Mo(r9, r6)
            if (r1 != r0) goto L6c
            return r0
        L6c:
            r7 = r1
            r1 = r8
            r8 = r9
            r10 = r7
        L70:
            androidx.compose.ui.unit.Velocity r10 = (androidx.compose.ui.unit.Velocity) r10
            long r4 = r10.getPackedValue()
            long r8 = androidx.compose.ui.unit.Velocity.m3195minusAH228Gc(r8, r4)
            r6.L$0 = r1
            r6.J$0 = r8
            r6.label = r3
            java.lang.Object r10 = r1.m236doFlingAnimationQWom1Mo(r8, r6)
            if (r10 != r0) goto L87
            return r0
        L87:
            androidx.compose.ui.unit.Velocity r10 = (androidx.compose.ui.unit.Velocity) r10
            long r4 = r10.getPackedValue()
            androidx.compose.runtime.State r10 = r1.getNestedScrollDispatcher()
            java.lang.Object r10 = r10.getValue()
            r1 = r10
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r1 = (androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher) r1
            long r8 = androidx.compose.ui.unit.Velocity.m3195minusAH228Gc(r8, r4)
            r10 = 0
            r6.L$0 = r10
            r6.label = r2
            r2 = r8
            java.lang.Object r8 = r1.m2383dispatchPostFlingRZ2iAVY(r2, r4, r6)
            if (r8 != r0) goto La9
            return r0
        La9:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.onDragStopped(float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: doFlingAnimation-QWom1Mo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m236doFlingAnimationQWom1Mo(long r12, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            r0.<init>(r11, r14)
        L19:
            r4 = r0
            java.lang.Object r14 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r11 = r4.L$0
            kotlin.jvm.internal.Ref$LongRef r11 = (kotlin.jvm.internal.Ref.LongRef) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L60
        L2f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L37:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$LongRef r14 = new kotlin.jvm.internal.Ref$LongRef
            r14.<init>()
            r14.element = r12
            androidx.compose.foundation.gestures.ScrollableState r1 = r11.getScrollableState()
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2 r3 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2
            r10 = 0
            r5 = r3
            r6 = r11
            r7 = r14
            r8 = r12
            r5.<init>(r6, r7, r8, r10)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4.L$0 = r14
            r4.label = r2
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r11 = androidx.compose.foundation.gestures.ScrollableState.DefaultImpls.scroll$default(r1, r2, r3, r4, r5, r6)
            if (r11 != r0) goto L5f
            return r0
        L5f:
            r11 = r14
        L60:
            long r11 = r11.element
            androidx.compose.ui.unit.Velocity r11 = androidx.compose.ui.unit.Velocity.m3183boximpl(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.m236doFlingAnimationQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
