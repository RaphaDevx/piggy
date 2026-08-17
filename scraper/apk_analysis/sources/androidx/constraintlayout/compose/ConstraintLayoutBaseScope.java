package androidx.constraintlayout.compose;

import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.state.helpers.BarrierReference;
import androidx.constraintlayout.core.state.helpers.GuidelineReference;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.PointerIconCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConstraintLayoutBaseScope.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\b'\u0018\u00002\u00020\u0001:\u0003MNOB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000fJ'\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u001aJ'\u0010\u0015\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u001c2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u001aJ1\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J1\u0010&\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010%J1\u0010(\u001a\u00020)2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+J1\u0010,\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010%J\u001b\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J\u000e\u0010.\u001a\u00020\u001e2\u0006\u00102\u001a\u000203J\u001b\u00104\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00101J\u000e\u00104\u001a\u00020\u001e2\u0006\u00102\u001a\u000203J\u001b\u00106\u001a\u00020)2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u00108J\u000e\u00106\u001a\u00020)2\u0006\u00102\u001a\u000203J\u001b\u00109\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u00101J\u000e\u00109\u001a\u00020\u001e2\u0006\u00102\u001a\u000203J\u001b\u0010;\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u00101J\u000e\u0010;\u001a\u00020\u001e2\u0006\u00102\u001a\u000203J\u001b\u0010=\u001a\u00020)2\u0006\u0010/\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u00108J\u000e\u0010=\u001a\u00020)2\u0006\u00102\u001a\u000203J\b\u0010?\u001a\u00020\u0004H\u0002J)\u0010@\u001a\u00020\u00182\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010A\u001a\u00020B¢\u0006\u0002\u0010CJ1\u0010D\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u0010%J1\u0010F\u001a\u00020)2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010\"\u001a\u00020#ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bG\u0010+J)\u0010H\u001a\u00020\u001c2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!2\b\b\u0002\u0010A\u001a\u00020B¢\u0006\u0002\u0010IJ\b\u0010J\u001a\u00020\u0010H\u0016J\u0010\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\rX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006P"}, d2 = {"Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope;", "", "()V", "HelpersStartId", "", "helperId", "helpersHashCode", "getHelpersHashCode$annotations", "getHelpersHashCode", "()I", "setHelpersHashCode", "(I)V", "tasks", "", "Lkotlin/Function1;", "Landroidx/constraintlayout/compose/State;", "", "getTasks", "()Ljava/util/List;", "applyTo", SentryThread.JsonKeys.STATE, "constrain", "Landroidx/constraintlayout/compose/HorizontalChainScope;", "ref", "Landroidx/constraintlayout/compose/HorizontalChainReference;", "constrainBlock", "Lkotlin/ExtensionFunctionType;", "Landroidx/constraintlayout/compose/VerticalChainScope;", "Landroidx/constraintlayout/compose/VerticalChainReference;", "createAbsoluteLeftBarrier", "Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;", "elements", "", "Landroidx/constraintlayout/compose/ConstrainedLayoutReference;", "margin", "Landroidx/compose/ui/unit/Dp;", "createAbsoluteLeftBarrier-3ABfNKs", "([Landroidx/constraintlayout/compose/ConstrainedLayoutReference;F)Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;", "createAbsoluteRightBarrier", "createAbsoluteRightBarrier-3ABfNKs", "createBottomBarrier", "Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;", "createBottomBarrier-3ABfNKs", "([Landroidx/constraintlayout/compose/ConstrainedLayoutReference;F)Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;", "createEndBarrier", "createEndBarrier-3ABfNKs", "createGuidelineFromAbsoluteLeft", TypedValues.CycleType.S_WAVE_OFFSET, "createGuidelineFromAbsoluteLeft-0680j_4", "(F)Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;", "fraction", "", "createGuidelineFromAbsoluteRight", "createGuidelineFromAbsoluteRight-0680j_4", "createGuidelineFromBottom", "createGuidelineFromBottom-0680j_4", "(F)Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;", "createGuidelineFromEnd", "createGuidelineFromEnd-0680j_4", "createGuidelineFromStart", "createGuidelineFromStart-0680j_4", "createGuidelineFromTop", "createGuidelineFromTop-0680j_4", "createHelperId", "createHorizontalChain", "chainStyle", "Landroidx/constraintlayout/compose/ChainStyle;", "([Landroidx/constraintlayout/compose/ConstrainedLayoutReference;Landroidx/constraintlayout/compose/ChainStyle;)Landroidx/constraintlayout/compose/HorizontalChainReference;", "createStartBarrier", "createStartBarrier-3ABfNKs", "createTopBarrier", "createTopBarrier-3ABfNKs", "createVerticalChain", "([Landroidx/constraintlayout/compose/ConstrainedLayoutReference;Landroidx/constraintlayout/compose/ChainStyle;)Landroidx/constraintlayout/compose/VerticalChainReference;", "reset", "updateHelpersHashCode", "value", "BaselineAnchor", "HorizontalAnchor", "VerticalAnchor", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public abstract class ConstraintLayoutBaseScope {
    public static final int $stable = 8;
    private int helpersHashCode;
    private final List<Function1<State, Unit>> tasks = new ArrayList();
    private final int HelpersStartId = 1000;
    private int helperId = 1000;

    public static /* synthetic */ void getHelpersHashCode$annotations() {
    }

    protected final List<Function1<State, Unit>> getTasks() {
        return this.tasks;
    }

    public final void applyTo(State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Iterator<T> it = this.tasks.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(state);
        }
    }

    public void reset() {
        this.tasks.clear();
        this.helperId = this.HelpersStartId;
        this.helpersHashCode = 0;
    }

    public final int getHelpersHashCode() {
        return this.helpersHashCode;
    }

    public final void setHelpersHashCode(int i) {
        this.helpersHashCode = i;
    }

    private final void updateHelpersHashCode(int value) {
        this.helpersHashCode = ((this.helpersHashCode * PointerIconCompat.TYPE_VERTICAL_TEXT) + value) % 1000000007;
    }

    private final int createHelperId() {
        int i = this.helperId;
        this.helperId = i + 1;
        return i;
    }

    /* compiled from: ConstraintLayoutBaseScope.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\n\u001a\u00020\u0001HÀ\u0003¢\u0006\u0002\b\u000bJ\u000e\u0010\f\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$VerticalAnchor;", "", "id", FirebaseAnalytics.Param.INDEX, "", "(Ljava/lang/Object;I)V", "getId$compose_release", "()Ljava/lang/Object;", "getIndex$compose_release", "()I", "component1", "component1$compose_release", "component2", "component2$compose_release", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "toString", "", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class VerticalAnchor {
        private final Object id;
        private final int index;

        public static /* synthetic */ VerticalAnchor copy$default(VerticalAnchor verticalAnchor, Object obj, int i, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                obj = verticalAnchor.id;
            }
            if ((i2 & 2) != 0) {
                i = verticalAnchor.index;
            }
            return verticalAnchor.copy(obj, i);
        }

        /* renamed from: component1$compose_release, reason: from getter */
        public final Object getId() {
            return this.id;
        }

        /* renamed from: component2$compose_release, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        public final VerticalAnchor copy(Object id, int index) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new VerticalAnchor(id, index);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VerticalAnchor)) {
                return false;
            }
            VerticalAnchor verticalAnchor = (VerticalAnchor) other;
            return Intrinsics.areEqual(this.id, verticalAnchor.id) && this.index == verticalAnchor.index;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + Integer.hashCode(this.index);
        }

        public String toString() {
            return "VerticalAnchor(id=" + this.id + ", index=" + this.index + ')';
        }

        public VerticalAnchor(Object id, int i) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.index = i;
        }

        public final Object getId$compose_release() {
            return this.id;
        }

        public final int getIndex$compose_release() {
            return this.index;
        }
    }

    /* compiled from: ConstraintLayoutBaseScope.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\n\u001a\u00020\u0001HÀ\u0003¢\u0006\u0002\b\u000bJ\u000e\u0010\f\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$HorizontalAnchor;", "", "id", FirebaseAnalytics.Param.INDEX, "", "(Ljava/lang/Object;I)V", "getId$compose_release", "()Ljava/lang/Object;", "getIndex$compose_release", "()I", "component1", "component1$compose_release", "component2", "component2$compose_release", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "toString", "", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class HorizontalAnchor {
        private final Object id;
        private final int index;

        public static /* synthetic */ HorizontalAnchor copy$default(HorizontalAnchor horizontalAnchor, Object obj, int i, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                obj = horizontalAnchor.id;
            }
            if ((i2 & 2) != 0) {
                i = horizontalAnchor.index;
            }
            return horizontalAnchor.copy(obj, i);
        }

        /* renamed from: component1$compose_release, reason: from getter */
        public final Object getId() {
            return this.id;
        }

        /* renamed from: component2$compose_release, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        public final HorizontalAnchor copy(Object id, int index) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new HorizontalAnchor(id, index);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HorizontalAnchor)) {
                return false;
            }
            HorizontalAnchor horizontalAnchor = (HorizontalAnchor) other;
            return Intrinsics.areEqual(this.id, horizontalAnchor.id) && this.index == horizontalAnchor.index;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + Integer.hashCode(this.index);
        }

        public String toString() {
            return "HorizontalAnchor(id=" + this.id + ", index=" + this.index + ')';
        }

        public HorizontalAnchor(Object id, int i) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.index = i;
        }

        public final Object getId$compose_release() {
            return this.id;
        }

        public final int getIndex$compose_release() {
            return this.index;
        }
    }

    /* compiled from: ConstraintLayoutBaseScope.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u0001HÀ\u0003¢\u0006\u0002\b\u0007J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Landroidx/constraintlayout/compose/ConstraintLayoutBaseScope$BaselineAnchor;", "", "id", "(Ljava/lang/Object;)V", "getId$compose_release", "()Ljava/lang/Object;", "component1", "component1$compose_release", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "", "compose_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class BaselineAnchor {
        private final Object id;

        public static /* synthetic */ BaselineAnchor copy$default(BaselineAnchor baselineAnchor, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = baselineAnchor.id;
            }
            return baselineAnchor.copy(obj);
        }

        /* renamed from: component1$compose_release, reason: from getter */
        public final Object getId() {
            return this.id;
        }

        public final BaselineAnchor copy(Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new BaselineAnchor(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BaselineAnchor) && Intrinsics.areEqual(this.id, ((BaselineAnchor) other).id);
        }

        public int hashCode() {
            return this.id.hashCode();
        }

        public String toString() {
            return "BaselineAnchor(id=" + this.id + ')';
        }

        public BaselineAnchor(Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
        }

        public final Object getId$compose_release() {
            return this.id;
        }
    }

    public final HorizontalChainScope constrain(HorizontalChainReference ref, Function1<? super HorizontalChainScope, Unit> constrainBlock) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(constrainBlock, "constrainBlock");
        HorizontalChainScope horizontalChainScope = new HorizontalChainScope(ref.getId());
        constrainBlock.invoke(horizontalChainScope);
        getTasks().addAll(horizontalChainScope.getTasks$compose_release());
        return horizontalChainScope;
    }

    public final VerticalChainScope constrain(VerticalChainReference ref, Function1<? super VerticalChainScope, Unit> constrainBlock) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(constrainBlock, "constrainBlock");
        VerticalChainScope verticalChainScope = new VerticalChainScope(ref.getId());
        constrainBlock.invoke(verticalChainScope);
        getTasks().addAll(verticalChainScope.getTasks$compose_release());
        return verticalChainScope;
    }

    /* renamed from: createGuidelineFromStart-0680j_4, reason: not valid java name */
    public final VerticalAnchor m3247createGuidelineFromStart0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromStart$1
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
                GuidelineReference verticalGuideline = state.verticalGuideline(Integer.valueOf(createHelperId));
                float f = offset;
                LayoutDirection layoutDirection = state.getLayoutDirection();
                LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
                Dp m3005boximpl = Dp.m3005boximpl(f);
                if (layoutDirection == layoutDirection2) {
                    verticalGuideline.start(m3005boximpl);
                } else {
                    verticalGuideline.end(m3005boximpl);
                }
            }
        });
        updateHelpersHashCode(1);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createGuidelineFromAbsoluteLeft-0680j_4, reason: not valid java name */
    public final VerticalAnchor m3243createGuidelineFromAbsoluteLeft0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromAbsoluteLeft$1
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
                state.verticalGuideline(Integer.valueOf(createHelperId)).start(Dp.m3005boximpl(offset));
            }
        });
        updateHelpersHashCode(2);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public final VerticalAnchor createGuidelineFromStart(final float fraction) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromStart$2
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
                GuidelineReference verticalGuideline = state.verticalGuideline(Integer.valueOf(createHelperId));
                float f = fraction;
                if (state.getLayoutDirection() == LayoutDirection.Ltr) {
                    verticalGuideline.percent(f);
                } else {
                    verticalGuideline.percent(1.0f - f);
                }
            }
        });
        updateHelpersHashCode(3);
        updateHelpersHashCode(Float.hashCode(fraction));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public final VerticalAnchor createGuidelineFromAbsoluteLeft(final float fraction) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromAbsoluteLeft$2
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
                state.verticalGuideline(Integer.valueOf(createHelperId)).percent(fraction);
            }
        });
        updateHelpersHashCode(4);
        updateHelpersHashCode(Float.hashCode(fraction));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createGuidelineFromEnd-0680j_4, reason: not valid java name */
    public final VerticalAnchor m3246createGuidelineFromEnd0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromEnd$1
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
                GuidelineReference verticalGuideline = state.verticalGuideline(Integer.valueOf(createHelperId));
                float f = offset;
                LayoutDirection layoutDirection = state.getLayoutDirection();
                LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
                Dp m3005boximpl = Dp.m3005boximpl(f);
                if (layoutDirection == layoutDirection2) {
                    verticalGuideline.end(m3005boximpl);
                } else {
                    verticalGuideline.start(m3005boximpl);
                }
            }
        });
        updateHelpersHashCode(5);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createGuidelineFromAbsoluteRight-0680j_4, reason: not valid java name */
    public final VerticalAnchor m3244createGuidelineFromAbsoluteRight0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromAbsoluteRight$1
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
                state.verticalGuideline(Integer.valueOf(createHelperId)).end(Dp.m3005boximpl(offset));
            }
        });
        updateHelpersHashCode(6);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public final VerticalAnchor createGuidelineFromEnd(float fraction) {
        return createGuidelineFromStart(1.0f - fraction);
    }

    public final VerticalAnchor createGuidelineFromAbsoluteRight(float fraction) {
        return createGuidelineFromAbsoluteLeft(1.0f - fraction);
    }

    /* renamed from: createGuidelineFromTop-0680j_4, reason: not valid java name */
    public final HorizontalAnchor m3248createGuidelineFromTop0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromTop$1
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
                state.horizontalGuideline(Integer.valueOf(createHelperId)).start(Dp.m3005boximpl(offset));
            }
        });
        updateHelpersHashCode(7);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new HorizontalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public final HorizontalAnchor createGuidelineFromTop(final float fraction) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromTop$2
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
                state.horizontalGuideline(Integer.valueOf(createHelperId)).percent(fraction);
            }
        });
        updateHelpersHashCode(8);
        updateHelpersHashCode(Float.hashCode(fraction));
        return new HorizontalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createGuidelineFromBottom-0680j_4, reason: not valid java name */
    public final HorizontalAnchor m3245createGuidelineFromBottom0680j_4(final float offset) {
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createGuidelineFromBottom$1
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
                state.horizontalGuideline(Integer.valueOf(createHelperId)).end(Dp.m3005boximpl(offset));
            }
        });
        updateHelpersHashCode(9);
        updateHelpersHashCode(Dp.m3013hashCodeimpl(offset));
        return new HorizontalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public final HorizontalAnchor createGuidelineFromBottom(float fraction) {
        return createGuidelineFromTop(1.0f - fraction);
    }

    /* renamed from: createStartBarrier-3ABfNKs, reason: not valid java name */
    public final VerticalAnchor m3249createStartBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createStartBarrier$1
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
                State.Direction direction;
                Intrinsics.checkNotNullParameter(state, "state");
                if (state.getLayoutDirection() == LayoutDirection.Ltr) {
                    direction = State.Direction.LEFT;
                } else {
                    direction = State.Direction.RIGHT;
                }
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), direction);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(10);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createAbsoluteLeftBarrier-3ABfNKs, reason: not valid java name */
    public final VerticalAnchor m3239createAbsoluteLeftBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createAbsoluteLeftBarrier$1
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
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), State.Direction.LEFT);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(11);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createTopBarrier-3ABfNKs, reason: not valid java name */
    public final HorizontalAnchor m3250createTopBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createTopBarrier$1
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
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), State.Direction.TOP);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(12);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new HorizontalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createEndBarrier-3ABfNKs, reason: not valid java name */
    public final VerticalAnchor m3242createEndBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createEndBarrier$1
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
                State.Direction direction;
                Intrinsics.checkNotNullParameter(state, "state");
                if (state.getLayoutDirection() == LayoutDirection.Ltr) {
                    direction = State.Direction.RIGHT;
                } else {
                    direction = State.Direction.LEFT;
                }
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), direction);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(13);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createAbsoluteRightBarrier-3ABfNKs, reason: not valid java name */
    public final VerticalAnchor m3240createAbsoluteRightBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createAbsoluteRightBarrier$1
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
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), State.Direction.RIGHT);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(14);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new VerticalAnchor(Integer.valueOf(createHelperId), 0);
    }

    /* renamed from: createBottomBarrier-3ABfNKs, reason: not valid java name */
    public final HorizontalAnchor m3241createBottomBarrier3ABfNKs(final ConstrainedLayoutReference[] elements, final float margin) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createBottomBarrier$1
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
                BarrierReference barrier = state.barrier(Integer.valueOf(createHelperId), State.Direction.BOTTOM);
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    barrier.add(Arrays.copyOf(array, array.length));
                    barrier.margin(state.convertDimension(Dp.m3005boximpl(margin)));
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(15);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(Dp.m3013hashCodeimpl(margin));
        return new HorizontalAnchor(Integer.valueOf(createHelperId), 0);
    }

    public static /* synthetic */ HorizontalChainReference createHorizontalChain$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, ChainStyle chainStyle, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createHorizontalChain");
        }
        if ((i & 2) != 0) {
            chainStyle = ChainStyle.INSTANCE.getSpread();
        }
        return constraintLayoutBaseScope.createHorizontalChain(constrainedLayoutReferenceArr, chainStyle);
    }

    public final HorizontalChainReference createHorizontalChain(final ConstrainedLayoutReference[] elements, final ChainStyle chainStyle) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Intrinsics.checkNotNullParameter(chainStyle, "chainStyle");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createHorizontalChain$1
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
                HelperReference helper = state.helper(Integer.valueOf(createHelperId), State.Helper.HORIZONTAL_CHAIN);
                if (helper == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.core.state.helpers.HorizontalChainReference");
                }
                androidx.constraintlayout.core.state.helpers.HorizontalChainReference horizontalChainReference = (androidx.constraintlayout.core.state.helpers.HorizontalChainReference) helper;
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    horizontalChainReference.add(Arrays.copyOf(array, array.length));
                    horizontalChainReference.style(chainStyle.getStyle());
                    horizontalChainReference.apply();
                    if (chainStyle.getBias() != null) {
                        state.constraints(elements[0].getId()).horizontalBias(chainStyle.getBias().floatValue());
                        return;
                    }
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(16);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(chainStyle.hashCode());
        return new HorizontalChainReference(Integer.valueOf(createHelperId));
    }

    public static /* synthetic */ VerticalChainReference createVerticalChain$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, ChainStyle chainStyle, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createVerticalChain");
        }
        if ((i & 2) != 0) {
            chainStyle = ChainStyle.INSTANCE.getSpread();
        }
        return constraintLayoutBaseScope.createVerticalChain(constrainedLayoutReferenceArr, chainStyle);
    }

    public final VerticalChainReference createVerticalChain(final ConstrainedLayoutReference[] elements, final ChainStyle chainStyle) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Intrinsics.checkNotNullParameter(chainStyle, "chainStyle");
        final int createHelperId = createHelperId();
        this.tasks.add(new Function1<State, Unit>() { // from class: androidx.constraintlayout.compose.ConstraintLayoutBaseScope$createVerticalChain$1
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
                HelperReference helper = state.helper(Integer.valueOf(createHelperId), State.Helper.VERTICAL_CHAIN);
                if (helper == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.core.state.helpers.VerticalChainReference");
                }
                androidx.constraintlayout.core.state.helpers.VerticalChainReference verticalChainReference = (androidx.constraintlayout.core.state.helpers.VerticalChainReference) helper;
                ConstrainedLayoutReference[] constrainedLayoutReferenceArr = elements;
                ArrayList arrayList = new ArrayList(constrainedLayoutReferenceArr.length);
                for (ConstrainedLayoutReference constrainedLayoutReference : constrainedLayoutReferenceArr) {
                    arrayList.add(constrainedLayoutReference.getId());
                }
                Object[] array = arrayList.toArray(new Object[0]);
                if (array != null) {
                    verticalChainReference.add(Arrays.copyOf(array, array.length));
                    verticalChainReference.style(chainStyle.getStyle());
                    verticalChainReference.apply();
                    if (chainStyle.getBias() != null) {
                        state.constraints(elements[0].getId()).verticalBias(chainStyle.getBias().floatValue());
                        return;
                    }
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
        updateHelpersHashCode(17);
        for (ConstrainedLayoutReference constrainedLayoutReference : elements) {
            updateHelpersHashCode(constrainedLayoutReference.hashCode());
        }
        updateHelpersHashCode(chainStyle.hashCode());
        return new VerticalChainReference(Integer.valueOf(createHelperId));
    }

    /* renamed from: createStartBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ VerticalAnchor m3237createStartBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createStartBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3249createStartBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }

    /* renamed from: createAbsoluteLeftBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ VerticalAnchor m3233createAbsoluteLeftBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createAbsoluteLeftBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3239createAbsoluteLeftBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }

    /* renamed from: createTopBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ HorizontalAnchor m3238createTopBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createTopBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3250createTopBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }

    /* renamed from: createEndBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ VerticalAnchor m3236createEndBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEndBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3242createEndBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }

    /* renamed from: createAbsoluteRightBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ VerticalAnchor m3234createAbsoluteRightBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createAbsoluteRightBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3240createAbsoluteRightBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }

    /* renamed from: createBottomBarrier-3ABfNKs$default, reason: not valid java name */
    public static /* synthetic */ HorizontalAnchor m3235createBottomBarrier3ABfNKs$default(ConstraintLayoutBaseScope constraintLayoutBaseScope, ConstrainedLayoutReference[] constrainedLayoutReferenceArr, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createBottomBarrier-3ABfNKs");
        }
        if ((i & 2) != 0) {
            f = Dp.m3007constructorimpl(0);
        }
        return constraintLayoutBaseScope.m3241createBottomBarrier3ABfNKs(constrainedLayoutReferenceArr, f);
    }
}
