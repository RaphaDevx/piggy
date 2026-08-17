package androidx.constraintlayout.compose;

import android.graphics.Matrix;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* compiled from: MotionLayout.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0016J4\u0010\f\u001a\u00020\n2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\n2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020\u0004J(\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\b\u0010\u0005\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0004J;\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020#2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010,\u001a\u00020-H\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020-H\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b2\u00103Jc\u00104\u001a\u0002052\u0006\u0010,\u001a\u00020-2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020#2\b\u0010\u0005\u001a\u0004\u0018\u00010%2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010:\u001a\u00020;ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b<\u0010=Je\u0010>\u001a\u00020\n2\u0006\u0010,\u001a\u00020-2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020#2\b\u0010\u0005\u001a\u0004\u0018\u00010%2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010?\u001a\u000201H\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b@\u0010AJ\u0011\u0010B\u001a\u00020\n*\u00020CH\u0007¢\u0006\u0002\u0010DJ1\u0010E\u001a\u00020\n*\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u0019H\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\bL\u0010MJI\u0010N\u001a\u00020\n*\u00020F2\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020H2\u0006\u0010R\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u0019H\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\bS\u0010TR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006U"}, d2 = {"Landroidx/constraintlayout/compose/MotionMeasurer;", "Landroidx/constraintlayout/compose/Measurer;", "()V", Key.MOTIONPROGRESS, "", "transition", "Landroidx/constraintlayout/core/state/Transition;", "getTransition", "()Landroidx/constraintlayout/core/state/Transition;", "clearConstraintSets", "", "computeLayoutResult", "encodeKeyFrames", "json", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "location", "", "types", "", "progress", "count", "", "encodeRoot", "getCustomColor", "Landroidx/compose/ui/graphics/Color;", "id", "", "name", "getCustomColor-WaAFU9c", "(Ljava/lang/String;Ljava/lang/String;)J", "getCustomFloat", "getProgress", "initWith", "start", "Landroidx/constraintlayout/compose/ConstraintSet;", "end", "Landroidx/constraintlayout/compose/Transition;", "measureConstraintSet", "optimizationLevel", "constraintSet", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measureConstraintSet--hBUhpc", "(ILandroidx/constraintlayout/compose/ConstraintSet;Ljava/util/List;J)V", "needsRemeasure", "", "needsRemeasure-BRTryo0", "(J)Z", "performInterpolationMeasure", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "constraintSetStart", "constraintSetEnd", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "performInterpolationMeasure-OQbXsTc", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/constraintlayout/compose/Transition;Ljava/util/List;IFLandroidx/compose/ui/layout/MeasureScope;)J", "recalculateInterpolation", "remeasure", "recalculateInterpolation-36Wf7g4", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/constraintlayout/compose/ConstraintSet;Landroidx/constraintlayout/compose/Transition;Ljava/util/List;IFZ)V", "drawDebug", "Landroidx/compose/foundation/layout/BoxScope;", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "drawFrame", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", TypedValues.AttributesType.S_FRAME, "Landroidx/constraintlayout/core/state/WidgetFrame;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", TypedValues.Custom.S_COLOR, "drawFrame-g2O1Hgs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/constraintlayout/core/state/WidgetFrame;Landroidx/compose/ui/graphics/PathEffect;J)V", "drawFrameDebug", "parentWidth", "parentHeight", "startFrame", "endFrame", "drawFrameDebug-PE3pjmc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFLandroidx/constraintlayout/core/state/WidgetFrame;Landroidx/constraintlayout/core/state/WidgetFrame;Landroidx/compose/ui/graphics/PathEffect;J)V", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MotionMeasurer extends Measurer {
    private float motionProgress;
    private final androidx.constraintlayout.core.state.Transition transition = new androidx.constraintlayout.core.state.Transition();

    public final androidx.constraintlayout.core.state.Transition getTransition() {
        return this.transition;
    }

    /* renamed from: getProgress, reason: from getter */
    public final float getMotionProgress() {
        return this.motionProgress;
    }

    /* renamed from: measureConstraintSet--hBUhpc, reason: not valid java name */
    private final void m3303measureConstraintSethBUhpc(int optimizationLevel, ConstraintSet constraintSet, List<? extends Measurable> measurables, long constraints) {
        boolean z;
        String obj;
        getState().reset();
        constraintSet.applyTo(getState(), measurables);
        getState().apply(getRoot());
        ArrayList<ConstraintWidget> children = getRoot().getChildren();
        Intrinsics.checkNotNullExpressionValue(children, "root.children");
        ArrayList<ConstraintWidget> arrayList = children;
        int size = arrayList.size() - 1;
        if (size >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                arrayList.get(i).setAnimated(true);
                if (i2 > size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        m3274applyRootSizeBRTryo0(constraints);
        getRoot().updateHierarchy();
        z = MotionLayoutKt.DEBUG;
        if (z) {
            getRoot().setDebugName("ConstraintLayout");
            ArrayList<ConstraintWidget> children2 = getRoot().getChildren();
            Intrinsics.checkNotNullExpressionValue(children2, "root.children");
            for (ConstraintWidget constraintWidget : children2) {
                Object companionWidget = constraintWidget.getCompanionWidget();
                Measurable measurable = companionWidget instanceof Measurable ? (Measurable) companionWidget : null;
                Object layoutId = measurable == null ? null : LayoutIdKt.getLayoutId(measurable);
                String str = "NOTAG";
                if (layoutId != null && (obj = layoutId.toString()) != null) {
                    str = obj;
                }
                constraintWidget.setDebugName(str);
            }
        }
        ArrayList<ConstraintWidget> children3 = getRoot().getChildren();
        Intrinsics.checkNotNullExpressionValue(children3, "root.children");
        for (ConstraintWidget constraintWidget2 : children3) {
            Object companionWidget2 = constraintWidget2.getCompanionWidget();
            Measurable measurable2 = companionWidget2 instanceof Measurable ? (Measurable) companionWidget2 : null;
            Object layoutId2 = measurable2 == null ? null : LayoutIdKt.getLayoutId(measurable2);
            if (layoutId2 == null) {
                layoutId2 = measurable2 == null ? null : ConstraintLayoutTagKt.getConstraintLayoutId(measurable2);
            }
            constraintWidget2.stringId = layoutId2 == null ? null : layoutId2.toString();
        }
        getRoot().setOptimizationLevel(optimizationLevel);
        getRoot().measure(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x006a, code lost:
    
        if (r10 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        if (r1.intValue() != Integer.MIN_VALUE) goto L22;
     */
    /* renamed from: performInterpolationMeasure-OQbXsTc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long m3307performInterpolationMeasureOQbXsTc(long r13, androidx.compose.ui.unit.LayoutDirection r15, androidx.constraintlayout.compose.ConstraintSet r16, androidx.constraintlayout.compose.ConstraintSet r17, androidx.constraintlayout.compose.Transition r18, java.util.List<? extends androidx.compose.ui.layout.Measurable> r19, int r20, float r21, androidx.compose.ui.layout.MeasureScope r22) {
        /*
            r12 = this;
            r11 = r12
            r0 = r22
            java.lang.String r1 = "layoutDirection"
            r3 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r1)
            java.lang.String r1 = "constraintSetStart"
            r4 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            java.lang.String r1 = "constraintSetEnd"
            r5 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            java.lang.String r1 = "measurables"
            r7 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "measureScope"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r1 = r0
            androidx.compose.ui.unit.Density r1 = (androidx.compose.ui.unit.Density) r1
            r12.setDensity(r1)
            r12.setMeasureScope(r0)
            boolean r10 = r12.m3304needsRemeasureBRTryo0(r13)
            float r0 = r11.motionProgress
            int r0 = (r0 > r21 ? 1 : (r0 == r21 ? 0 : -1))
            if (r0 != 0) goto L6c
            androidx.constraintlayout.compose.LayoutInformationReceiver r0 = r12.getLayoutInformationReceiver()
            r1 = 0
            if (r0 != 0) goto L3f
            r0 = r1
            goto L47
        L3f:
            int r0 = r0.getForcedWidth()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L47:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != 0) goto L4c
            goto L52
        L4c:
            int r0 = r0.intValue()
            if (r0 == r2) goto L6a
        L52:
            androidx.constraintlayout.compose.LayoutInformationReceiver r0 = r12.getLayoutInformationReceiver()
            if (r0 != 0) goto L59
            goto L61
        L59:
            int r0 = r0.getForcedHeight()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
        L61:
            if (r1 != 0) goto L64
            goto L6c
        L64:
            int r0 = r1.intValue()
            if (r0 != r2) goto L6c
        L6a:
            if (r10 == 0) goto L7e
        L6c:
            r0 = r12
            r1 = r13
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r0.m3305recalculateInterpolation36Wf7g4(r1, r3, r4, r5, r6, r7, r8, r9, r10)
        L7e:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = r12.getRoot()
            int r0 = r0.getWidth()
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = r12.getRoot()
            int r1 = r1.getHeight()
            long r0 = androidx.compose.ui.unit.IntSizeKt.IntSize(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.compose.MotionMeasurer.m3307performInterpolationMeasureOQbXsTc(long, androidx.compose.ui.unit.LayoutDirection, androidx.constraintlayout.compose.ConstraintSet, androidx.constraintlayout.compose.ConstraintSet, androidx.constraintlayout.compose.Transition, java.util.List, int, float, androidx.compose.ui.layout.MeasureScope):long");
    }

    /* renamed from: needsRemeasure-BRTryo0, reason: not valid java name */
    private final boolean m3304needsRemeasureBRTryo0(long constraints) {
        WidgetFrame interpolated;
        Placeable placeable;
        if (this.transition.isEmpty() || getFrameCache().isEmpty()) {
            return true;
        }
        if ((Constraints.m2974getHasFixedHeightimpl(constraints) && !getState().sameFixedHeight(Constraints.m2976getMaxHeightimpl(constraints))) || (Constraints.m2975getHasFixedWidthimpl(constraints) && !getState().sameFixedWidth(Constraints.m2977getMaxWidthimpl(constraints)))) {
            return true;
        }
        ArrayList<ConstraintWidget> children = getRoot().getChildren();
        Intrinsics.checkNotNullExpressionValue(children, "root.children");
        ArrayList<ConstraintWidget> arrayList = children;
        int size = arrayList.size() - 1;
        if (size >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                ConstraintWidget constraintWidget = arrayList.get(i);
                Object companionWidget = constraintWidget.getCompanionWidget();
                Measurable measurable = companionWidget instanceof Measurable ? (Measurable) companionWidget : null;
                if (measurable != null && (interpolated = getTransition().getInterpolated(constraintWidget)) != null && (placeable = getPlaceables().get(measurable)) != null) {
                    int width = placeable.getWidth();
                    int height = placeable.getHeight();
                    if (width != interpolated.width() || height != interpolated.height()) {
                        return true;
                    }
                }
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0127, code lost:
    
        if (r9.intValue() != r7) goto L45;
     */
    /* renamed from: recalculateInterpolation-36Wf7g4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void m3305recalculateInterpolation36Wf7g4(long r13, androidx.compose.ui.unit.LayoutDirection r15, androidx.constraintlayout.compose.ConstraintSet r16, androidx.constraintlayout.compose.ConstraintSet r17, androidx.constraintlayout.compose.Transition r18, java.util.List<? extends androidx.compose.ui.layout.Measurable> r19, int r20, float r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.compose.MotionMeasurer.m3305recalculateInterpolation36Wf7g4(long, androidx.compose.ui.unit.LayoutDirection, androidx.constraintlayout.compose.ConstraintSet, androidx.constraintlayout.compose.ConstraintSet, androidx.constraintlayout.compose.Transition, java.util.List, int, float, boolean):void");
    }

    private final void encodeKeyFrames(StringBuilder json, float[] location, int[] types, int[] progress, int count) {
        if (count == 0) {
            return;
        }
        json.append("keyTypes : [");
        int i = 0;
        if (count > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                json.append(StringUtils.SPACE + types[i2] + ',');
                if (i3 >= count) {
                    break;
                } else {
                    i2 = i3;
                }
            }
        }
        json.append("],\n");
        json.append("keyPos : [");
        int i4 = count * 2;
        if (i4 > 0) {
            int i5 = 0;
            while (true) {
                int i6 = i5 + 1;
                json.append(StringUtils.SPACE + location[i5] + ',');
                if (i6 >= i4) {
                    break;
                } else {
                    i5 = i6;
                }
            }
        }
        json.append("],\n ");
        json.append("keyFrames : [");
        if (count > 0) {
            while (true) {
                int i7 = i + 1;
                json.append(StringUtils.SPACE + progress[i] + ',');
                if (i7 >= count) {
                    break;
                } else {
                    i = i7;
                }
            }
        }
        json.append("],\n ");
    }

    public final void encodeRoot(StringBuilder json) {
        Intrinsics.checkNotNullParameter(json, "json");
        json.append("  root: {");
        json.append("interpolated: { left:  0,");
        json.append("  top:  0,");
        json.append("  right:   " + getRoot().getWidth() + " ,");
        json.append("  bottom:  " + getRoot().getHeight() + " ,");
        json.append(" } }");
    }

    @Override // androidx.constraintlayout.compose.Measurer
    public void computeLayoutResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        encodeRoot(sb);
        int[] iArr = new int[50];
        int[] iArr2 = new int[50];
        float[] fArr = new float[100];
        Iterator<ConstraintWidget> it = getRoot().getChildren().iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            WidgetFrame start = this.transition.getStart(next.stringId);
            WidgetFrame end = this.transition.getEnd(next.stringId);
            WidgetFrame interpolated = this.transition.getInterpolated(next.stringId);
            float[] path = this.transition.getPath(next.stringId);
            int keyFrames = this.transition.getKeyFrames(next.stringId, fArr, iArr, iArr2);
            sb.append(StringUtils.SPACE + ((Object) next.stringId) + ": {");
            sb.append(" interpolated : ");
            interpolated.serialize(sb, true);
            sb.append(", start : ");
            start.serialize(sb);
            sb.append(", end : ");
            end.serialize(sb);
            encodeKeyFrames(sb, fArr, iArr, iArr2, keyFrames);
            sb.append(" path : [");
            Intrinsics.checkNotNullExpressionValue(path, "path");
            int length = path.length;
            int i = 0;
            while (i < length) {
                float f = path[i];
                i++;
                sb.append(StringUtils.SPACE + f + " ,");
            }
            sb.append(" ] ");
            sb.append("}, ");
        }
        sb.append(" }");
        LayoutInformationReceiver layoutInformationReceiver = getLayoutInformationReceiver();
        if (layoutInformationReceiver == null) {
            return;
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "json.toString()");
        layoutInformationReceiver.setLayoutInformation(sb2);
    }

    public final void drawDebug(final BoxScope boxScope, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(boxScope, "<this>");
        Composer startRestartGroup = composer.startRestartGroup(436942847);
        ComposerKt.sourceInformation(startRestartGroup, "C(drawDebug)");
        CanvasKt.Canvas(boxScope.matchParentSize(Modifier.INSTANCE), new Function1<DrawScope, Unit>() { // from class: androidx.constraintlayout.compose.MotionMeasurer$drawDebug$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope Canvas) {
                Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                PathEffect dashPathEffect = PathEffect.INSTANCE.dashPathEffect(new float[]{10.0f, 10.0f}, 0.0f);
                Iterator<ConstraintWidget> it = MotionMeasurer.this.getRoot().getChildren().iterator();
                while (it.hasNext()) {
                    ConstraintWidget next = it.next();
                    WidgetFrame startFrame = MotionMeasurer.this.getTransition().getStart(next);
                    WidgetFrame endFrame = MotionMeasurer.this.getTransition().getEnd(next);
                    MotionMeasurer motionMeasurer = MotionMeasurer.this;
                    Canvas.getDrawContext().getTransform().translate(2.0f, 2.0f);
                    float m1094getWidthimpl = Size.m1094getWidthimpl(Canvas.mo1627getSizeNHjbRc());
                    float m1091getHeightimpl = Size.m1091getHeightimpl(Canvas.mo1627getSizeNHjbRc());
                    Intrinsics.checkNotNullExpressionValue(startFrame, "startFrame");
                    Intrinsics.checkNotNullExpressionValue(endFrame, "endFrame");
                    motionMeasurer.m3302drawFrameDebugPE3pjmc(Canvas, m1094getWidthimpl, m1091getHeightimpl, startFrame, endFrame, dashPathEffect, Color.INSTANCE.m1291getWhite0d7_KjU());
                    Canvas.getDrawContext().getTransform().translate(-2.0f, -2.0f);
                    MotionMeasurer.this.m3302drawFrameDebugPE3pjmc(Canvas, Size.m1094getWidthimpl(Canvas.mo1627getSizeNHjbRc()), Size.m1091getHeightimpl(Canvas.mo1627getSizeNHjbRc()), startFrame, endFrame, dashPathEffect, Color.INSTANCE.m1281getBlue0d7_KjU());
                }
            }
        }, startRestartGroup, 0);
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.constraintlayout.compose.MotionMeasurer$drawDebug$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                MotionMeasurer.this.drawDebug(boxScope, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawFrameDebug-PE3pjmc, reason: not valid java name */
    public final void m3302drawFrameDebugPE3pjmc(DrawScope drawScope, float f, float f2, WidgetFrame widgetFrame, WidgetFrame widgetFrame2, PathEffect pathEffect, long j) {
        m3301drawFrameg2O1Hgs(drawScope, widgetFrame, pathEffect, j);
        m3301drawFrameg2O1Hgs(drawScope, widgetFrame2, pathEffect, j);
        int numberKeyPositions = this.transition.getNumberKeyPositions(widgetFrame);
        new MotionRenderDebug(23.0f).draw(AndroidCanvas_androidKt.getNativeCanvas(drawScope.getDrawContext().getCanvas()), this.transition.getMotion(widgetFrame.widget.stringId), 1000, 1, (int) f, (int) f2);
        if (numberKeyPositions == 0) {
            return;
        }
        float[] fArr = new float[numberKeyPositions];
        float[] fArr2 = new float[numberKeyPositions];
        float[] fArr3 = new float[numberKeyPositions];
        this.transition.fillKeyPositions(widgetFrame, fArr, fArr2, fArr3);
        widgetFrame.centerX();
        widgetFrame.centerY();
        int i = numberKeyPositions - 1;
        if (i < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            float f3 = fArr3[i2] / 100.0f;
            float f4 = 1 - f3;
            float width = (widgetFrame.width() * f4) + (widgetFrame2.width() * f3);
            float height = (f4 * widgetFrame.height()) + (f3 * widgetFrame2.height());
            float f5 = (fArr[i2] * f) + (width / 2.0f);
            float f6 = (fArr2[i2] * f2) + (height / 2.0f);
            Path Path = AndroidPath_androidKt.Path();
            Path.moveTo(f5 - 20.0f, f6);
            Path.lineTo(f5, f6 + 20.0f);
            Path.lineTo(f5 + 20.0f, f6);
            Path.lineTo(f5, f6 - 20.0f);
            Path.close();
            DrawScope.DefaultImpls.m1666drawPathLG529CI$default(drawScope, Path, j, 1.0f, new Stroke(3.0f, 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
            if (i2 == i) {
                return;
            } else {
                i2 = i3;
            }
        }
    }

    /* renamed from: drawFrame-g2O1Hgs, reason: not valid java name */
    private final void m3301drawFrameg2O1Hgs(DrawScope drawScope, WidgetFrame widgetFrame, PathEffect pathEffect, long j) {
        if (widgetFrame.isDefaultTransform()) {
            DrawScope.DefaultImpls.m1670drawRectnJ9OG0$default(drawScope, j, OffsetKt.Offset(widgetFrame.left, widgetFrame.top), SizeKt.Size(widgetFrame.width(), widgetFrame.height()), 0.0f, new Stroke(3.0f, 0.0f, 0, 0, pathEffect, 14, null), null, 0, 104, null);
            return;
        }
        Matrix matrix = new Matrix();
        if (!Float.isNaN(widgetFrame.rotationZ)) {
            matrix.preRotate(widgetFrame.rotationZ, widgetFrame.centerX(), widgetFrame.centerY());
        }
        matrix.preScale(Float.isNaN(widgetFrame.scaleX) ? 1.0f : widgetFrame.scaleX, Float.isNaN(widgetFrame.scaleY) ? 1.0f : widgetFrame.scaleY, widgetFrame.centerX(), widgetFrame.centerY());
        float[] fArr = {widgetFrame.left, widgetFrame.top, widgetFrame.right, widgetFrame.top, widgetFrame.right, widgetFrame.bottom, widgetFrame.left, widgetFrame.bottom};
        matrix.mapPoints(fArr);
        DrawScope.DefaultImpls.m1662drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fArr[0], fArr[1]), OffsetKt.Offset(fArr[2], fArr[3]), 3.0f, 0, pathEffect, 0.0f, null, 0, 464, null);
        DrawScope.DefaultImpls.m1662drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fArr[2], fArr[3]), OffsetKt.Offset(fArr[4], fArr[5]), 3.0f, 0, pathEffect, 0.0f, null, 0, 464, null);
        DrawScope.DefaultImpls.m1662drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fArr[4], fArr[5]), OffsetKt.Offset(fArr[6], fArr[7]), 3.0f, 0, pathEffect, 0.0f, null, 0, 464, null);
        DrawScope.DefaultImpls.m1662drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(fArr[6], fArr[7]), OffsetKt.Offset(fArr[0], fArr[1]), 3.0f, 0, pathEffect, 0.0f, null, 0, 464, null);
    }

    /* renamed from: getCustomColor-WaAFU9c, reason: not valid java name */
    public final long m3306getCustomColorWaAFU9c(String id, String name) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        if (!this.transition.contains(id)) {
            return Color.INSTANCE.m1280getBlack0d7_KjU();
        }
        this.transition.interpolate(getRoot().getWidth(), getRoot().getHeight(), this.motionProgress);
        return ColorKt.Color(this.transition.getInterpolated(id).getCustomColor(name));
    }

    public final float getCustomFloat(String id, String name) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        if (!this.transition.contains(id)) {
            return 0.0f;
        }
        WidgetFrame start = this.transition.getStart(id);
        WidgetFrame end = this.transition.getEnd(id);
        float customFloat = start.getCustomFloat(name);
        float customFloat2 = end.getCustomFloat(name);
        float f = this.motionProgress;
        return ((1.0f - f) * customFloat) + (f * customFloat2);
    }

    public final void clearConstraintSets() {
        this.transition.clear();
        getFrameCache().clear();
    }

    public final void initWith(ConstraintSet start, ConstraintSet end, Transition transition, float progress) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        clearConstraintSets();
        start.applyTo(this.transition, 0);
        end.applyTo(this.transition, 1);
        this.transition.interpolate(0, 0, progress);
        if (transition == null) {
            return;
        }
        transition.applyTo(this.transition, 0);
    }
}
