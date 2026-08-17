package androidx.compose.animation;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedVisibility.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class AnimatedVisibilityKt$AnimatedEnterExitImpl$4 implements MeasurePolicy {
    final /* synthetic */ AnimatedVisibilityScopeImpl $scope;

    public AnimatedVisibilityKt$AnimatedEnterExitImpl$4(AnimatedVisibilityScopeImpl animatedVisibilityScopeImpl) {
        this.$scope = animatedVisibilityScopeImpl;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v22 */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo40measure3p2s80s(MeasureScope Layout, List<? extends Measurable> measureables, long j) {
        Object obj;
        Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
        Intrinsics.checkNotNullParameter(measureables, "measureables");
        List<? extends Measurable> list = measureables;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Measurable) it.next()).mo2510measureBRTryo0(j));
        }
        final ArrayList arrayList2 = arrayList;
        Placeable placeable = null;
        int i = 1;
        if (arrayList2.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList2.get(0);
            int width = ((Placeable) obj).getWidth();
            int lastIndex = CollectionsKt.getLastIndex(arrayList2);
            if (1 <= lastIndex) {
                int i2 = 1;
                while (true) {
                    int i3 = i2 + 1;
                    Object obj2 = arrayList2.get(i2);
                    int width2 = ((Placeable) obj2).getWidth();
                    if (width < width2) {
                        obj = obj2;
                        width = width2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2 = i3;
                }
            }
        }
        Placeable placeable2 = (Placeable) obj;
        int width3 = placeable2 == null ? 0 : placeable2.getWidth();
        if (!arrayList2.isEmpty()) {
            ?? r11 = arrayList2.get(0);
            int height = ((Placeable) r11).getHeight();
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList2);
            boolean z = r11;
            if (1 <= lastIndex2) {
                while (true) {
                    int i4 = i + 1;
                    Object obj3 = arrayList2.get(i);
                    int height2 = ((Placeable) obj3).getHeight();
                    r11 = z;
                    if (height < height2) {
                        r11 = obj3;
                        height = height2;
                    }
                    if (i == lastIndex2) {
                        break;
                    }
                    i = i4;
                    z = r11;
                }
            }
            placeable = r11;
        }
        Placeable placeable3 = placeable;
        int height3 = placeable3 != null ? placeable3.getHeight() : 0;
        this.$scope.getTargetSize$animation_release().setValue(IntSize.m3119boximpl(IntSizeKt.IntSize(width3, height3)));
        return MeasureScope.DefaultImpls.layout$default(Layout, width3, height3, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$4$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                List<Placeable> list2 = arrayList2;
                int size = list2.size() - 1;
                if (size < 0) {
                    return;
                }
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    Placeable.PlacementScope.place$default(layout, list2.get(i5), 0, 0, 0.0f, 4, null);
                    if (i6 > size) {
                        return;
                    } else {
                        i5 = i6;
                    }
                }
            }
        }, 4, null);
    }
}
