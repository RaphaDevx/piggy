package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.config.a;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionHandleCache;", "", "()V", RRWebVideoEvent.JsonKeys.LEFT, "", a.j, "Landroidx/compose/ui/graphics/Path;", "createPath", "density", "Landroidx/compose/ui/unit/Density;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class SelectionHandleCache {
    private boolean left;
    private Path path;

    public final Path createPath(Density density, boolean left) {
        float mo227toPx0680j_4;
        Intrinsics.checkNotNullParameter(density, "density");
        Path path = this.path;
        if (this.left != left || path == null) {
            this.left = left;
            if (path == null) {
                path = AndroidPath_androidKt.Path();
                this.path = path;
            }
            path.reset();
            float mo227toPx0680j_42 = density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_HEIGHT()) * 0.5f;
            float mo227toPx0680j_43 = left ? density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_WIDTH()) * 0.5f : 0.0f;
            if (left) {
                mo227toPx0680j_4 = density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_WIDTH());
            } else {
                mo227toPx0680j_4 = density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_WIDTH()) * 0.5f;
            }
            path.addRect(new Rect(mo227toPx0680j_43, 0.0f, mo227toPx0680j_4, mo227toPx0680j_42));
            path.addOval(new Rect(0.0f, 0.0f, density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_WIDTH()), density.mo227toPx0680j_4(SelectionHandlesKt.getHANDLE_HEIGHT())));
        }
        return path;
    }
}
