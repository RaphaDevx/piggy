package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: LazyListScrolling.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0082\b\u001a%\u0010\u000b\u001a\u00020\u0007*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"BoundDistance", "Landroidx/compose/ui/unit/Dp;", "F", "DEBUG", "", "TargetDistance", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "doSmoothScrollToItem", "Landroidx/compose/foundation/lazy/LazyListState;", FirebaseAnalytics.Param.INDEX, "", "scrollOffset", "(Landroidx/compose/foundation/lazy/LazyListState;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListScrollingKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m3007constructorimpl(2500);
    private static final float BoundDistance = Dp.m3007constructorimpl(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);

    private static final void debugLog(Function0<String> function0) {
    }

    public static final Object doSmoothScrollToItem(LazyListState lazyListState, int i, int i2, Continuation<? super Unit> continuation) {
        Object scroll$default = ScrollableState.DefaultImpls.scroll$default(lazyListState, null, new LazyListScrollingKt$doSmoothScrollToItem$2(lazyListState, i, AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), i2, null), continuation, 1, null);
        return scroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListItemInfo doSmoothScrollToItem$getTargetItem(LazyListState lazyListState, int i) {
        LazyListItemInfo lazyListItemInfo;
        List<LazyListItemInfo> visibleItemsInfo = lazyListState.getLayoutInfo().getVisibleItemsInfo();
        int size = visibleItemsInfo.size() - 1;
        if (size >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                lazyListItemInfo = visibleItemsInfo.get(i2);
                if (lazyListItemInfo.getIndex() == i) {
                    break;
                }
                if (i3 > size) {
                    break;
                }
                i2 = i3;
            }
            return lazyListItemInfo;
        }
        lazyListItemInfo = null;
        return lazyListItemInfo;
    }
}
