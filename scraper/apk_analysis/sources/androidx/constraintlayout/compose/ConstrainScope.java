package androidx.constraintlayout.compose;

import androidx.compose.foundation.layout.LayoutScopeMarker;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope;
import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryThread;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConstrainScope.kt */
@LayoutScopeMarker
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b \b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J<\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020J2\u001d\u0010d\u001a\u0019\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020E0e¢\u0006\u0002\bgH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bh\u0010iJ!\u0010j\u001a\u00020b2\u0017\u0010d\u001a\u0013\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u00020E0C¢\u0006\u0002\bgH\u0002J\u0015\u0010k\u001a\u00020E2\u0006\u0010l\u001a\u00020DH\u0000¢\u0006\u0002\bmJ\u000e\u0010n\u001a\u00020E2\u0006\u0010o\u001a\u00020pJ\u000e\u0010n\u001a\u00020E2\u0006\u0010o\u001a\u00020qJ\u0018\u0010r\u001a\u00020E2\u0006\u0010s\u001a\u00020'2\b\b\u0003\u0010t\u001a\u00020\u000bJ\u000e\u0010u\u001a\u00020E2\u0006\u0010s\u001a\u00020'J\u0018\u0010v\u001a\u00020E2\u0006\u0010s\u001a\u00020'2\b\b\u0003\u0010t\u001a\u00020\u000bJ+\u0010w\u001a\u00020E2\u0006\u0010s\u001a\u00020'2\u0006\u0010x\u001a\u00020\u000b2\u0006\u0010y\u001a\u00020Jø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bz\u0010{J\u0006\u0010|\u001a\u00020EJ\u0006\u0010}\u001a\u00020EJ\u0006\u0010~\u001a\u00020EJ[\u0010\u007f\u001a\u00020E2\u0006\u0010H\u001a\u00020p2\u0006\u0010\u0015\u001a\u00020p2\t\b\u0002\u0010\u0080\u0001\u001a\u00020J2\t\b\u0002\u0010\u0081\u0001\u001a\u00020J2\t\b\u0002\u0010\u0082\u0001\u001a\u00020J2\t\b\u0002\u0010\u0083\u0001\u001a\u00020J2\b\b\u0003\u0010t\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J£\u0001\u0010\u007f\u001a\u00020E2\u0006\u0010?\u001a\u00020q2\u0006\u0010H\u001a\u00020p2\u0006\u0010\u0019\u001a\u00020q2\u0006\u0010\u0015\u001a\u00020p2\t\b\u0002\u0010\u0086\u0001\u001a\u00020J2\t\b\u0002\u0010\u0080\u0001\u001a\u00020J2\t\b\u0002\u0010\u0087\u0001\u001a\u00020J2\t\b\u0002\u0010\u0081\u0001\u001a\u00020J2\t\b\u0002\u0010\u0088\u0001\u001a\u00020J2\t\b\u0002\u0010\u0082\u0001\u001a\u00020J2\t\b\u0002\u0010\u0089\u0001\u001a\u00020J2\t\b\u0002\u0010\u0083\u0001\u001a\u00020J2\t\b\u0003\u0010\u008a\u0001\u001a\u00020\u000b2\t\b\u0003\u0010\u008b\u0001\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J[\u0010\u007f\u001a\u00020E2\u0006\u0010?\u001a\u00020q2\u0006\u0010\u0019\u001a\u00020q2\t\b\u0002\u0010\u0086\u0001\u001a\u00020J2\t\b\u0002\u0010\u0087\u0001\u001a\u00020J2\t\b\u0002\u0010\u0088\u0001\u001a\u00020J2\t\b\u0002\u0010\u0089\u0001\u001a\u00020J2\b\b\u0003\u0010t\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0084\u0001\u0010\u008e\u0001J\u0007\u0010\u008f\u0001\u001a\u00020EJ\u0007\u0010\u0090\u0001\u001a\u00020ER\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R&\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u001b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010!\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000e\"\u0004\b#\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R$\u0010*\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010\u0010R$\u0010-\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000e\"\u0004\b/\u0010\u0010R$\u00100\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000e\"\u0004\b2\u0010\u0010R$\u00103\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u0010\u0010R$\u00106\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u000e\"\u0004\b8\u0010\u0010R$\u00109\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u000e\"\u0004\b;\u0010\u0010R$\u0010<\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000e\"\u0004\b>\u0010\u0010R\u0011\u0010?\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0007R&\u0010A\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020E0C0BX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0011\u0010H\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0018R/\u0010K\u001a\u00020J2\u0006\u0010\n\u001a\u00020J@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010N\u001a\u0004\bL\u0010\u000e\"\u0004\bM\u0010\u0010R/\u0010O\u001a\u00020J2\u0006\u0010\n\u001a\u00020J@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010N\u001a\u0004\bP\u0010\u000e\"\u0004\bQ\u0010\u0010R/\u0010R\u001a\u00020J2\u0006\u0010\n\u001a\u00020J@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010N\u001a\u0004\bS\u0010\u000e\"\u0004\bT\u0010\u0010R$\u0010U\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u000e\"\u0004\bW\u0010\u0010R$\u0010Y\u001a\u00020X2\u0006\u0010\n\u001a\u00020X@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R$\u0010^\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u001b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u001e\"\u0004\b`\u0010 \u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0091\u0001"}, d2 = {"Landroidx/constraintlayout/compose/ConstrainScope;", "", "id", "(Ljava/lang/Object;)V", "absoluteLeft", "Landroidx/constraintlayout/compose/VerticalAnchorable;", "getAbsoluteLeft", "()Landroidx/constraintlayout/compose/VerticalAnchorable;", "absoluteRight", "getAbsoluteRight", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "baseline", "Landroidx/constraintlayout/compose/BaselineAnchorable;", "getBaseline", "()Landroidx/constraintlayout/compose/BaselineAnchorable;", "bottom", "Landroidx/constraintlayout/compose/HorizontalAnchorable;", "getBottom", "()Landroidx/constraintlayout/compose/HorizontalAnchorable;", "end", "getEnd", "Landroidx/constraintlayout/compose/Dimension;", "height", "getHeight", "()Landroidx/constraintlayout/compose/Dimension;", "setHeight", "(Landroidx/constraintlayout/compose/Dimension;)V", "horizontalChainWeight", "getHorizontalChainWeight", "setHorizontalChainWeight", "getId$compose_release", "()Ljava/lang/Object;", "parent", "Landroidx/constraintlayout/compose/ConstrainedLayoutReference;", "getParent", "()Landroidx/constraintlayout/compose/ConstrainedLayoutReference;", "pivotX", "getPivotX", "setPivotX", "pivotY", "getPivotY", "setPivotY", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "start", "getStart", "tasks", "", "Lkotlin/Function1;", "Landroidx/constraintlayout/compose/State;", "", "getTasks$compose_release", "()Ljava/util/List;", RRWebVideoEvent.JsonKeys.TOP, "getTop", "Landroidx/compose/ui/unit/Dp;", "translationX", "getTranslationX-D9Ej5fM", "setTranslationX-0680j_4", "F", "translationY", "getTranslationY-D9Ej5fM", "setTranslationY-0680j_4", "translationZ", "getTranslationZ-D9Ej5fM", "setTranslationZ-0680j_4", "verticalChainWeight", "getVerticalChainWeight", "setVerticalChainWeight", "Landroidx/constraintlayout/compose/Visibility;", "visibility", "getVisibility", "()Landroidx/constraintlayout/compose/Visibility;", "setVisibility", "(Landroidx/constraintlayout/compose/Visibility;)V", "width", "getWidth", "setWidth", "addFloatTransformFromDp", "", "dpValue", "change", "Lkotlin/Function2;", "Landroidx/constraintlayout/core/state/ConstraintReference;", "Lkotlin/ExtensionFunctionType;", "addFloatTransformFromDp-D5KLDUw", "(FLkotlin/jvm/functions/Function2;)Z", "addTransform", "applyTo", SentryThread.JsonKeys.STATE, "applyTo$compose_release", "centerAround", "anchor", "Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;", "Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;", "centerHorizontallyTo", Request.JsonKeys.OTHER, "bias", "centerTo", "centerVerticallyTo", "circular", "angle", "distance", "circular-wH6b6FI", "(Landroidx/constraintlayout/compose/ConstrainedLayoutReference;FF)V", "clearConstraints", "clearHorizontal", "clearVertical", "linkTo", "topMargin", "bottomMargin", "topGoneMargin", "bottomGoneMargin", "linkTo-8ZKsbrE", "(Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;FFFFF)V", "startMargin", "endMargin", "startGoneMargin", "endGoneMargin", "horizontalBias", "verticalBias", "linkTo-R7zmacU", "(Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;FFFFFFFFFF)V", "(Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;FFFFF)V", "resetDimensions", "resetTransforms", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ConstrainScope {
    private final VerticalAnchorable absoluteLeft;
    private final VerticalAnchorable absoluteRight;
    private float alpha;
    private final BaselineAnchorable baseline;
    private final HorizontalAnchorable bottom;
    private final VerticalAnchorable end;
    private Dimension height;
    private float horizontalChainWeight;
    private final Object id;
    private final ConstrainedLayoutReference parent;
    private float pivotX;
    private float pivotY;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private final VerticalAnchorable start;
    private final List<Function1<State, Unit>> tasks;
    private final HorizontalAnchorable top;
    private float translationX;
    private float translationY;
    private float translationZ;
    private float verticalChainWeight;
    private Visibility visibility;
    private Dimension width;

    public ConstrainScope(Object id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        ArrayList arrayList = new ArrayList();
        this.tasks = arrayList;
        Integer PARENT = androidx.constraintlayout.core.state.State.PARENT;
        Intrinsics.checkNotNullExpressionValue(PARENT, "PARENT");
        this.parent = new ConstrainedLayoutReference(PARENT);
        this.start = new ConstraintVerticalAnchorable(id, -2, arrayList);
        this.absoluteLeft = new ConstraintVerticalAnchorable(id, 0, arrayList);
        this.top = new ConstraintHorizontalAnchorable(id, 0, arrayList);
        this.end = new ConstraintVerticalAnchorable(id, -1, arrayList);
        this.absoluteRight = new ConstraintVerticalAnchorable(id, 1, arrayList);
        this.bottom = new ConstraintHorizontalAnchorable(id, 1, arrayList);
        this.baseline = new ConstraintBaselineAnchorable(id, arrayList);
        this.width = Dimension.INSTANCE.getWrapContent();
        this.height = Dimension.INSTANCE.getWrapContent();
        this.visibility = Visibility.INSTANCE.getVisible();
        this.alpha = 1.0f;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        float f = 0;
        this.translationX = Dp.m3007constructorimpl(f);
        this.translationY = Dp.m3007constructorimpl(f);
        this.translationZ = Dp.m3007constructorimpl(f);
        this.pivotX = 0.5f;
        this.pivotY = 0.5f;
        this.horizontalChainWeight = Float.NaN;
        this.verticalChainWeight = Float.NaN;
    }

    /* renamed from: getId$compose_release, reason: from getter */
    public final Object getId() {
        return this.id;
    }

    public final List<Function1<State, Unit>> getTasks$compose_release() {
        return this.tasks;
    }

    public final void applyTo$compose_release(State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Iterator<T> it = this.tasks.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(state);
        }
    }

    public final ConstrainedLayoutReference getParent() {
        return this.parent;
    }

    public final VerticalAnchorable getStart() {
        return this.start;
    }

    public final VerticalAnchorable getAbsoluteLeft() {
        return this.absoluteLeft;
    }

    public final HorizontalAnchorable getTop() {
        return this.top;
    }

    public final VerticalAnchorable getEnd() {
        return this.end;
    }

    public final VerticalAnchorable getAbsoluteRight() {
        return this.absoluteRight;
    }

    public final HorizontalAnchorable getBottom() {
        return this.bottom;
    }

    public final BaselineAnchorable getBaseline() {
        return this.baseline;
    }

    public final Dimension getWidth() {
        return this.width;
    }

    public final void setWidth(final Dimension value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.width = value;
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$width$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).width(((DimensionDescription) value).toSolverDimension$compose_release(state));
            }
        });
    }

    public final Dimension getHeight() {
        return this.height;
    }

    public final void setHeight(final Dimension value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.height = value;
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$height$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).height(((DimensionDescription) value).toSolverDimension$compose_release(state));
            }
        });
    }

    public final Visibility getVisibility() {
        return this.visibility;
    }

    public final void setVisibility(final Visibility value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.visibility = value;
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$visibility$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                ConstraintReference constraints = state.constraints(ConstrainScope.this.getId());
                Visibility visibility = value;
                constraints.visibility(visibility.getSolverValue());
                if (Intrinsics.areEqual(visibility, Visibility.INSTANCE.getInvisible())) {
                    constraints.alpha(0.0f);
                }
            }
        });
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(final float f) {
        this.alpha = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                if (Intrinsics.areEqual(ConstrainScope.this.getVisibility(), Visibility.INSTANCE.getInvisible())) {
                    return;
                }
                addTransform.alpha(f);
            }
        });
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final void setScaleX(final float f) {
        this.scaleX = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$scaleX$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.scaleX(f);
            }
        });
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    public final void setScaleY(final float f) {
        this.scaleY = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$scaleY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.scaleY(f);
            }
        });
    }

    public final float getRotationX() {
        return this.rotationX;
    }

    public final void setRotationX(final float f) {
        this.rotationX = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$rotationX$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.rotationX(f);
            }
        });
    }

    public final float getRotationY() {
        return this.rotationY;
    }

    public final void setRotationY(final float f) {
        this.rotationY = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$rotationY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.rotationY(f);
            }
        });
    }

    public final float getRotationZ() {
        return this.rotationZ;
    }

    public final void setRotationZ(final float f) {
        this.rotationZ = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$rotationZ$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.rotationZ(f);
            }
        });
    }

    /* renamed from: getTranslationX-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTranslationX() {
        return this.translationX;
    }

    /* renamed from: setTranslationX-0680j_4, reason: not valid java name */
    public final void m3230setTranslationX0680j_4(float f) {
        this.translationX = f;
        m3219addFloatTransformFromDpD5KLDUw(f, new Function2<ConstraintReference, Float, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$translationX$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference, Float f2) {
                invoke(constraintReference, f2.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ConstraintReference addFloatTransformFromDp, float f2) {
                Intrinsics.checkNotNullParameter(addFloatTransformFromDp, "$this$addFloatTransformFromDp");
                addFloatTransformFromDp.translationX(f2);
            }
        });
    }

    /* renamed from: getTranslationY-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTranslationY() {
        return this.translationY;
    }

    /* renamed from: setTranslationY-0680j_4, reason: not valid java name */
    public final void m3231setTranslationY0680j_4(float f) {
        this.translationY = f;
        m3219addFloatTransformFromDpD5KLDUw(f, new Function2<ConstraintReference, Float, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$translationY$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference, Float f2) {
                invoke(constraintReference, f2.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ConstraintReference addFloatTransformFromDp, float f2) {
                Intrinsics.checkNotNullParameter(addFloatTransformFromDp, "$this$addFloatTransformFromDp");
                addFloatTransformFromDp.translationY(f2);
            }
        });
    }

    /* renamed from: getTranslationZ-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTranslationZ() {
        return this.translationZ;
    }

    /* renamed from: setTranslationZ-0680j_4, reason: not valid java name */
    public final void m3232setTranslationZ0680j_4(float f) {
        this.translationZ = f;
        m3219addFloatTransformFromDpD5KLDUw(f, new Function2<ConstraintReference, Float, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$translationZ$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference, Float f2) {
                invoke(constraintReference, f2.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ConstraintReference addFloatTransformFromDp, float f2) {
                Intrinsics.checkNotNullParameter(addFloatTransformFromDp, "$this$addFloatTransformFromDp");
                addFloatTransformFromDp.translationZ(f2);
            }
        });
    }

    public final float getPivotX() {
        return this.pivotX;
    }

    public final void setPivotX(final float f) {
        this.pivotX = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$pivotX$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.pivotX(f);
            }
        });
    }

    public final float getPivotY() {
        return this.pivotY;
    }

    public final void setPivotY(final float f) {
        this.pivotY = f;
        addTransform(new Function1<ConstraintReference, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$pivotY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ConstraintReference constraintReference) {
                invoke2(constraintReference);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ConstraintReference addTransform) {
                Intrinsics.checkNotNullParameter(addTransform, "$this$addTransform");
                addTransform.pivotY(f);
            }
        });
    }

    public final float getHorizontalChainWeight() {
        return this.horizontalChainWeight;
    }

    public final void setHorizontalChainWeight(final float f) {
        this.horizontalChainWeight = f;
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$horizontalChainWeight$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).setHorizontalChainWeight(f);
            }
        });
    }

    public final float getVerticalChainWeight() {
        return this.verticalChainWeight;
    }

    public final void setVerticalChainWeight(final float f) {
        this.verticalChainWeight = f;
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$verticalChainWeight$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).setVerticalChainWeight(f);
            }
        });
    }

    private final boolean addTransform(final Function1<? super ConstraintReference, Unit> change) {
        return this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$addTransform$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                Function1<ConstraintReference, Unit> function1 = change;
                ConstraintReference constraints = state.constraints(this.getId());
                Intrinsics.checkNotNullExpressionValue(constraints, "state.constraints(id)");
                function1.invoke(constraints);
            }
        });
    }

    /* renamed from: addFloatTransformFromDp-D5KLDUw, reason: not valid java name */
    private final boolean m3219addFloatTransformFromDpD5KLDUw(final float dpValue, final Function2<? super ConstraintReference, ? super Float, Unit> change) {
        return this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$addFloatTransformFromDp$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                if (state == null) {
                    return;
                }
                Function2<ConstraintReference, Float, Unit> function2 = change;
                ConstrainScope constrainScope = this;
                float f = dpValue;
                ConstraintReference constraints = state.constraints(constrainScope.getId());
                Intrinsics.checkNotNullExpressionValue(constraints, "state.constraints(id)");
                function2.invoke(constraints, Float.valueOf(state.convertDimension(Dp.m3005boximpl(f))));
            }
        });
    }

    /* renamed from: linkTo-8ZKsbrE, reason: not valid java name */
    public final void m3228linkTo8ZKsbrE(ConstraintLayoutBaseScope.VerticalAnchor start, ConstraintLayoutBaseScope.VerticalAnchor end, float startMargin, float endMargin, float startGoneMargin, float endGoneMargin, final float bias) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        this.start.mo3216linkToVpY3zN4(start, startMargin, startGoneMargin);
        this.end.mo3216linkToVpY3zN4(end, endMargin, endGoneMargin);
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$linkTo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(this.getId()).horizontalBias(state.getLayoutDirection() == LayoutDirection.Rtl ? 1 - bias : bias);
            }
        });
    }

    /* renamed from: linkTo-8ZKsbrE, reason: not valid java name */
    public final void m3227linkTo8ZKsbrE(ConstraintLayoutBaseScope.HorizontalAnchor top, ConstraintLayoutBaseScope.HorizontalAnchor bottom, float topMargin, float bottomMargin, float topGoneMargin, float bottomGoneMargin, final float bias) {
        Intrinsics.checkNotNullParameter(top, "top");
        Intrinsics.checkNotNullParameter(bottom, "bottom");
        this.top.mo3215linkToVpY3zN4(top, topMargin, topGoneMargin);
        this.bottom.mo3215linkToVpY3zN4(bottom, bottomMargin, bottomGoneMargin);
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$linkTo$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).verticalBias(bias);
            }
        });
    }

    /* renamed from: linkTo-R7zmacU, reason: not valid java name */
    public final void m3229linkToR7zmacU(ConstraintLayoutBaseScope.VerticalAnchor start, ConstraintLayoutBaseScope.HorizontalAnchor top, ConstraintLayoutBaseScope.VerticalAnchor end, ConstraintLayoutBaseScope.HorizontalAnchor bottom, float startMargin, float topMargin, float endMargin, float bottomMargin, float startGoneMargin, float topGoneMargin, float endGoneMargin, float bottomGoneMargin, float horizontalBias, float verticalBias) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(top, "top");
        Intrinsics.checkNotNullParameter(end, "end");
        Intrinsics.checkNotNullParameter(bottom, "bottom");
        m3228linkTo8ZKsbrE(start, end, startMargin, endMargin, startGoneMargin, endGoneMargin, horizontalBias);
        m3227linkTo8ZKsbrE(top, bottom, topMargin, bottomMargin, topGoneMargin, bottomGoneMargin, verticalBias);
    }

    public final void centerTo(ConstrainedLayoutReference other) {
        Intrinsics.checkNotNullParameter(other, "other");
        m3222linkToR7zmacU$default(this, other.getStart(), other.getTop(), other.getEnd(), other.getBottom(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 16368, null);
    }

    public static /* synthetic */ void centerHorizontallyTo$default(ConstrainScope constrainScope, ConstrainedLayoutReference constrainedLayoutReference, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.5f;
        }
        constrainScope.centerHorizontallyTo(constrainedLayoutReference, f);
    }

    public final void centerHorizontallyTo(ConstrainedLayoutReference other, float bias) {
        Intrinsics.checkNotNullParameter(other, "other");
        m3221linkTo8ZKsbrE$default(this, other.getStart(), other.getEnd(), 0.0f, 0.0f, 0.0f, 0.0f, bias, 60, (Object) null);
    }

    public static /* synthetic */ void centerVerticallyTo$default(ConstrainScope constrainScope, ConstrainedLayoutReference constrainedLayoutReference, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.5f;
        }
        constrainScope.centerVerticallyTo(constrainedLayoutReference, f);
    }

    public final void centerVerticallyTo(ConstrainedLayoutReference other, float bias) {
        Intrinsics.checkNotNullParameter(other, "other");
        m3220linkTo8ZKsbrE$default(this, other.getTop(), other.getBottom(), 0.0f, 0.0f, 0.0f, 0.0f, bias, 60, (Object) null);
    }

    public final void centerAround(ConstraintLayoutBaseScope.VerticalAnchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        m3221linkTo8ZKsbrE$default(this, anchor, anchor, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 124, (Object) null);
    }

    public final void centerAround(ConstraintLayoutBaseScope.HorizontalAnchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        m3220linkTo8ZKsbrE$default(this, anchor, anchor, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 124, (Object) null);
    }

    /* renamed from: circular-wH6b6FI, reason: not valid java name */
    public final void m3223circularwH6b6FI(final ConstrainedLayoutReference other, final float angle, final float distance) {
        Intrinsics.checkNotNullParameter(other, "other");
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$circular$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).circularConstraint(other.getId(), angle, state.convertDimension(Dp.m3005boximpl(distance)));
            }
        });
    }

    public final void clearHorizontal() {
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$clearHorizontal$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).clearHorizontal();
            }
        });
    }

    public final void clearVertical() {
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$clearVertical$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).clearVertical();
            }
        });
    }

    public final void clearConstraints() {
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$clearConstraints$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).clear();
            }
        });
    }

    public final void resetDimensions() {
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$resetDimensions$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                androidx.constraintlayout.core.state.Dimension solverDimension$compose_release = ((DimensionDescription) Dimension.INSTANCE.getWrapContent()).toSolverDimension$compose_release(state);
                state.constraints(ConstrainScope.this.getId()).width(solverDimension$compose_release).height(solverDimension$compose_release);
            }
        });
    }

    public final void resetTransforms() {
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstrainScope$resetTransforms$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                state.constraints(ConstrainScope.this.getId()).alpha(Float.NaN).scaleX(Float.NaN).scaleY(Float.NaN).rotationX(Float.NaN).rotationY(Float.NaN).rotationZ(Float.NaN).translationX(Float.NaN).translationY(Float.NaN).translationZ(Float.NaN).pivotX(Float.NaN).pivotY(Float.NaN);
            }
        });
    }

    /* renamed from: linkTo-8ZKsbrE$default, reason: not valid java name */
    public static /* synthetic */ void m3221linkTo8ZKsbrE$default(ConstrainScope constrainScope, ConstraintLayoutBaseScope.VerticalAnchor verticalAnchor, ConstraintLayoutBaseScope.VerticalAnchor verticalAnchor2, float f, float f2, float f3, float f4, float f5, int i, Object obj) {
        constrainScope.m3228linkTo8ZKsbrE(verticalAnchor, verticalAnchor2, (i & 4) != 0 ? Dp.m3007constructorimpl(0) : f, (i & 8) != 0 ? Dp.m3007constructorimpl(0) : f2, (i & 16) != 0 ? Dp.m3007constructorimpl(0) : f3, (i & 32) != 0 ? Dp.m3007constructorimpl(0) : f4, (i & 64) != 0 ? 0.5f : f5);
    }

    /* renamed from: linkTo-8ZKsbrE$default, reason: not valid java name */
    public static /* synthetic */ void m3220linkTo8ZKsbrE$default(ConstrainScope constrainScope, ConstraintLayoutBaseScope.HorizontalAnchor horizontalAnchor, ConstraintLayoutBaseScope.HorizontalAnchor horizontalAnchor2, float f, float f2, float f3, float f4, float f5, int i, Object obj) {
        constrainScope.m3227linkTo8ZKsbrE(horizontalAnchor, horizontalAnchor2, (i & 4) != 0 ? Dp.m3007constructorimpl(0) : f, (i & 8) != 0 ? Dp.m3007constructorimpl(0) : f2, (i & 16) != 0 ? Dp.m3007constructorimpl(0) : f3, (i & 32) != 0 ? Dp.m3007constructorimpl(0) : f4, (i & 64) != 0 ? 0.5f : f5);
    }

    /* renamed from: linkTo-R7zmacU$default, reason: not valid java name */
    public static /* synthetic */ void m3222linkToR7zmacU$default(ConstrainScope constrainScope, ConstraintLayoutBaseScope.VerticalAnchor verticalAnchor, ConstraintLayoutBaseScope.HorizontalAnchor horizontalAnchor, ConstraintLayoutBaseScope.VerticalAnchor verticalAnchor2, ConstraintLayoutBaseScope.HorizontalAnchor horizontalAnchor2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i, Object obj) {
        constrainScope.m3229linkToR7zmacU(verticalAnchor, horizontalAnchor, verticalAnchor2, horizontalAnchor2, (i & 16) != 0 ? Dp.m3007constructorimpl(0) : f, (i & 32) != 0 ? Dp.m3007constructorimpl(0) : f2, (i & 64) != 0 ? Dp.m3007constructorimpl(0) : f3, (i & 128) != 0 ? Dp.m3007constructorimpl(0) : f4, (i & 256) != 0 ? Dp.m3007constructorimpl(0) : f5, (i & 512) != 0 ? Dp.m3007constructorimpl(0) : f6, (i & 1024) != 0 ? Dp.m3007constructorimpl(0) : f7, (i & 2048) != 0 ? Dp.m3007constructorimpl(0) : f8, (i & 4096) != 0 ? 0.5f : f9, (i & 8192) != 0 ? 0.5f : f10);
    }
}
