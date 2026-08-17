package androidx.compose.ui.node;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: LayoutNode.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"androidx/compose/ui/node/LayoutNode$measureScope$1", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/unit/Density;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LayoutNode$measureScope$1 implements MeasureScope, Density {
    final /* synthetic */ LayoutNode this$0;

    LayoutNode$measureScope$1(LayoutNode layoutNode) {
        this.this$0 = layoutNode;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(int i, int i2, Map<AlignmentLine, Integer> map, Function1<? super Placeable.PlacementScope, Unit> function1) {
        return MeasureScope.DefaultImpls.layout(this, i, i2, map, function1);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx--R2X_6o */
    public int mo221roundToPxR2X_6o(long j) {
        return MeasureScope.DefaultImpls.m2529roundToPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public int mo222roundToPx0680j_4(float f) {
        return MeasureScope.DefaultImpls.m2530roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-GaN1DYA */
    public float mo223toDpGaN1DYA(long j) {
        return MeasureScope.DefaultImpls.m2531toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo224toDpu2uoSUM(float f) {
        return MeasureScope.DefaultImpls.m2532toDpu2uoSUM(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo225toDpu2uoSUM(int i) {
        return MeasureScope.DefaultImpls.m2533toDpu2uoSUM((MeasureScope) this, i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public float mo226toPxR2X_6o(long j) {
        return MeasureScope.DefaultImpls.m2534toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public float mo227toPx0680j_4(float f) {
        return MeasureScope.DefaultImpls.m2535toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        return MeasureScope.DefaultImpls.toRect(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-0xMU5do */
    public long mo228toSp0xMU5do(float f) {
        return MeasureScope.DefaultImpls.m2536toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo229toSpkPz2Gy4(float f) {
        return MeasureScope.DefaultImpls.m2537toSpkPz2Gy4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo230toSpkPz2Gy4(int i) {
        return MeasureScope.DefaultImpls.m2538toSpkPz2Gy4((MeasureScope) this, i);
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.this$0.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getFontScale() {
        return this.this$0.getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.this$0.getLayoutDirection();
    }
}
