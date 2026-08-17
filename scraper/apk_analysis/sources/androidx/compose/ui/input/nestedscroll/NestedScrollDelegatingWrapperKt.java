package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: NestedScrollDelegatingWrapper.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"NoOpConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class NestedScrollDelegatingWrapperKt {
    private static final NestedScrollConnection NoOpConnection = new NestedScrollConnection() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapperKt$NoOpConnection$1
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPreScroll-OzD1aCk */
        public long mo234onPreScrollOzD1aCk(long available, int source) {
            return Offset.INSTANCE.m1041getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPostScroll-DzOQY0M */
        public long mo232onPostScrollDzOQY0M(long consumed, long available, int source) {
            return Offset.INSTANCE.m1041getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPreFling-QWom1Mo */
        public Object mo233onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
            return Velocity.m3183boximpl(Velocity.INSTANCE.m3203getZero9UxMQ8M());
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPostFling-RZ2iAVY */
        public Object mo231onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
            return Velocity.m3183boximpl(Velocity.INSTANCE.m3203getZero9UxMQ8M());
        }
    };
}
