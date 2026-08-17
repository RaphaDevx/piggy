package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SuspendingPointerInputFilter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J@\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\r¢\u0006\u0002\b\u0011H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u001b\u0010\u0002\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/unit/Density;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "awaitPointerEventScope", "R", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface PointerInputScope extends Density {
    <R> Object awaitPointerEventScope(Function2<? super AwaitPointerEventScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation);

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    long m2443getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    /* compiled from: SuspendingPointerInputFilter.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m2444roundToPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m2995roundToPxR2X_6o(pointerInputScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m2445roundToPx0680j_4(PointerInputScope pointerInputScope, float f) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m2996roundToPx0680j_4(pointerInputScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m2446toDpGaN1DYA(PointerInputScope pointerInputScope, long j) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m2997toDpGaN1DYA(pointerInputScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2447toDpu2uoSUM(PointerInputScope pointerInputScope, float f) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m2998toDpu2uoSUM(pointerInputScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2448toDpu2uoSUM(PointerInputScope pointerInputScope, int i) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m2999toDpu2uoSUM((Density) pointerInputScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m2449toPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m3000toPxR2X_6o(pointerInputScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m2450toPx0680j_4(PointerInputScope pointerInputScope, float f) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m3001toPx0680j_4(pointerInputScope, f);
        }

        public static Rect toRect(PointerInputScope pointerInputScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return Density.DefaultImpls.toRect(pointerInputScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m2451toSp0xMU5do(PointerInputScope pointerInputScope, float f) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m3002toSp0xMU5do(pointerInputScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2452toSpkPz2Gy4(PointerInputScope pointerInputScope, float f) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m3003toSpkPz2Gy4(pointerInputScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2453toSpkPz2Gy4(PointerInputScope pointerInputScope, int i) {
            Intrinsics.checkNotNullParameter(pointerInputScope, "this");
            return Density.DefaultImpls.m3004toSpkPz2Gy4((Density) pointerInputScope, i);
        }
    }
}
