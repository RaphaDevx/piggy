package androidx.compose.foundation.lazy;

import androidx.compose.ui.Modifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: LazyItemScope.kt */
@LazyScopeMarker
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0007\u001a\u00020\u0003*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/LazyItemScope;", "", "fillParentMaxHeight", "Landroidx/compose/ui/Modifier;", "fraction", "", "fillParentMaxSize", "fillParentMaxWidth", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface LazyItemScope {
    Modifier fillParentMaxHeight(Modifier modifier, float f);

    Modifier fillParentMaxSize(Modifier modifier, float f);

    Modifier fillParentMaxWidth(Modifier modifier, float f);

    /* compiled from: LazyItemScope.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ Modifier fillParentMaxSize$default(LazyItemScope lazyItemScope, Modifier modifier, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fillParentMaxSize");
            }
            if ((i & 1) != 0) {
                f = 1.0f;
            }
            return lazyItemScope.fillParentMaxSize(modifier, f);
        }

        public static /* synthetic */ Modifier fillParentMaxWidth$default(LazyItemScope lazyItemScope, Modifier modifier, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fillParentMaxWidth");
            }
            if ((i & 1) != 0) {
                f = 1.0f;
            }
            return lazyItemScope.fillParentMaxWidth(modifier, f);
        }

        public static /* synthetic */ Modifier fillParentMaxHeight$default(LazyItemScope lazyItemScope, Modifier modifier, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fillParentMaxHeight");
            }
            if ((i & 1) != 0) {
                f = 1.0f;
            }
            return lazyItemScope.fillParentMaxHeight(modifier, f);
        }
    }
}
