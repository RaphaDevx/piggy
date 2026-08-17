package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "awaitRelease", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitRelease", "", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface PressGestureScope extends Density {
    Object awaitRelease(Continuation<? super Unit> continuation);

    Object tryAwaitRelease(Continuation<? super Boolean> continuation);

    /* compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m211roundToPxR2X_6o(PressGestureScope pressGestureScope, long j) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m2995roundToPxR2X_6o(pressGestureScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m212roundToPx0680j_4(PressGestureScope pressGestureScope, float f) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m2996roundToPx0680j_4(pressGestureScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m213toDpGaN1DYA(PressGestureScope pressGestureScope, long j) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m2997toDpGaN1DYA(pressGestureScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m214toDpu2uoSUM(PressGestureScope pressGestureScope, float f) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m2998toDpu2uoSUM(pressGestureScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m215toDpu2uoSUM(PressGestureScope pressGestureScope, int i) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m2999toDpu2uoSUM((Density) pressGestureScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m216toPxR2X_6o(PressGestureScope pressGestureScope, long j) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m3000toPxR2X_6o(pressGestureScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m217toPx0680j_4(PressGestureScope pressGestureScope, float f) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m3001toPx0680j_4(pressGestureScope, f);
        }

        public static Rect toRect(PressGestureScope pressGestureScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return Density.DefaultImpls.toRect(pressGestureScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m218toSp0xMU5do(PressGestureScope pressGestureScope, float f) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m3002toSp0xMU5do(pressGestureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m219toSpkPz2Gy4(PressGestureScope pressGestureScope, float f) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m3003toSpkPz2Gy4(pressGestureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m220toSpkPz2Gy4(PressGestureScope pressGestureScope, int i) {
            Intrinsics.checkNotNullParameter(pressGestureScope, "this");
            return Density.DefaultImpls.m3004toSpkPz2Gy4((Density) pressGestureScope, i);
        }
    }
}
