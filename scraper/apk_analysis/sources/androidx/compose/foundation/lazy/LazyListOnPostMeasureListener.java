package androidx.compose.foundation.lazy;

import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: LazyListState.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListOnPostMeasureListener;", "", "onPostMeasure", "", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "childConstraints", "Landroidx/compose/ui/unit/Constraints;", "result", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "onPostMeasure-VKLhPVY", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;JLandroidx/compose/foundation/lazy/LazyListMeasureResult;)V", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface LazyListOnPostMeasureListener {
    /* renamed from: onPostMeasure-VKLhPVY, reason: not valid java name */
    void mo391onPostMeasureVKLhPVY(SubcomposeMeasureScope subcomposeMeasureScope, long j, LazyListMeasureResult lazyListMeasureResult);
}
