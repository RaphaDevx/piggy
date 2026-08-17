package androidx.compose.foundation.gestures;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/TapGestureEvent;", "", "()V", "AllUp", "Cancel", "Down", "Up", "Landroidx/compose/foundation/gestures/TapGestureEvent$Down;", "Landroidx/compose/foundation/gestures/TapGestureEvent$Up;", "Landroidx/compose/foundation/gestures/TapGestureEvent$AllUp;", "Landroidx/compose/foundation/gestures/TapGestureEvent$Cancel;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
abstract class TapGestureEvent {
    public /* synthetic */ TapGestureEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/TapGestureEvent$Down;", "Landroidx/compose/foundation/gestures/TapGestureEvent;", "position", "Landroidx/compose/ui/geometry/Offset;", "uptimeMillis", "", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPosition-F1C5BW0", "()J", "J", "getUptimeMillis", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Down extends TapGestureEvent {
        public static final int $stable = 0;
        private final long position;
        private final long uptimeMillis;

        public /* synthetic */ Down(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2);
        }

        private Down(long j, long j2) {
            super(null);
            this.position = j;
            this.uptimeMillis = j2;
        }

        /* renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
        public final long getPosition() {
            return this.position;
        }

        public final long getUptimeMillis() {
            return this.uptimeMillis;
        }
    }

    private TapGestureEvent() {
    }

    /* compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/TapGestureEvent$Up;", "Landroidx/compose/foundation/gestures/TapGestureEvent;", "position", "Landroidx/compose/ui/geometry/Offset;", "uptimeMillis", "", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPosition-F1C5BW0", "()J", "J", "getUptimeMillis", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Up extends TapGestureEvent {
        public static final int $stable = 0;
        private final long position;
        private final long uptimeMillis;

        public /* synthetic */ Up(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2);
        }

        private Up(long j, long j2) {
            super(null);
            this.position = j;
            this.uptimeMillis = j2;
        }

        /* renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
        public final long getPosition() {
            return this.position;
        }

        public final long getUptimeMillis() {
            return this.uptimeMillis;
        }
    }

    /* compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TapGestureEvent$AllUp;", "Landroidx/compose/foundation/gestures/TapGestureEvent;", "()V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AllUp extends TapGestureEvent {
        public static final int $stable = 0;
        public static final AllUp INSTANCE = new AllUp();

        private AllUp() {
            super(null);
        }
    }

    /* compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TapGestureEvent$Cancel;", "Landroidx/compose/foundation/gestures/TapGestureEvent;", "()V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Cancel extends TapGestureEvent {
        public static final int $stable = 0;
        public static final Cancel INSTANCE = new Cancel();

        private Cancel() {
            super(null);
        }
    }
}
