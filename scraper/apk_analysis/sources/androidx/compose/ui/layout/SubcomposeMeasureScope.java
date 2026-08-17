package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.DpRect;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubcomposeLayout.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface SubcomposeMeasureScope extends MeasureScope {
    List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content);

    /* compiled from: SubcomposeLayout.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static MeasureResult layout(SubcomposeMeasureScope subcomposeMeasureScope, int i, int i2, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            Intrinsics.checkNotNullParameter(alignmentLines, "alignmentLines");
            Intrinsics.checkNotNullParameter(placementBlock, "placementBlock");
            return MeasureScope.DefaultImpls.layout(subcomposeMeasureScope, i, i2, alignmentLines, placementBlock);
        }

        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m2584roundToPxR2X_6o(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2529roundToPxR2X_6o(subcomposeMeasureScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m2585roundToPx0680j_4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2530roundToPx0680j_4(subcomposeMeasureScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m2586toDpGaN1DYA(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2531toDpGaN1DYA(subcomposeMeasureScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2587toDpu2uoSUM(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2532toDpu2uoSUM(subcomposeMeasureScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m2588toDpu2uoSUM(SubcomposeMeasureScope subcomposeMeasureScope, int i) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2533toDpu2uoSUM((MeasureScope) subcomposeMeasureScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m2589toPxR2X_6o(SubcomposeMeasureScope subcomposeMeasureScope, long j) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2534toPxR2X_6o(subcomposeMeasureScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m2590toPx0680j_4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2535toPx0680j_4(subcomposeMeasureScope, f);
        }

        public static Rect toRect(SubcomposeMeasureScope subcomposeMeasureScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return MeasureScope.DefaultImpls.toRect(subcomposeMeasureScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m2591toSp0xMU5do(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2536toSp0xMU5do(subcomposeMeasureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2592toSpkPz2Gy4(SubcomposeMeasureScope subcomposeMeasureScope, float f) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2537toSpkPz2Gy4(subcomposeMeasureScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m2593toSpkPz2Gy4(SubcomposeMeasureScope subcomposeMeasureScope, int i) {
            Intrinsics.checkNotNullParameter(subcomposeMeasureScope, "this");
            return MeasureScope.DefaultImpls.m2538toSpkPz2Gy4((MeasureScope) subcomposeMeasureScope, i);
        }
    }
}
