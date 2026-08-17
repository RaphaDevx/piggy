package androidx.compose.material;

import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J8\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\b2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002J<\u0010\u000f\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\b2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002J\"\u0010\u0012\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\"\u0010\u0013\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0016J/\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00170\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\"\u0010\u001c\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\"\u0010\u001d\u001a\u00020\b*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/material/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "(ZF)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean z, float f) {
        this.singleLine = z;
        this.animationProgress = f;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo40measure3p2s80s(final MeasureScope receiver, List<? extends Measurable> measurables, long j) {
        float f;
        float f2;
        float f3;
        Object obj;
        Object obj2;
        Placeable placeable;
        final Placeable mo2510measureBRTryo0;
        Object obj3;
        int intValue;
        Object obj4;
        final int m905calculateWidthVsPV1Ek;
        final int m904calculateHeightYbqEFUw;
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        int i = receiver.mo222roundToPx0680j_4(TextFieldImplKt.getTextFieldPadding());
        f = TextFieldKt.FirstBaselineOffset;
        final int i2 = receiver.mo222roundToPx0680j_4(f);
        f2 = TextFieldKt.LastBaselineOffset;
        int i3 = receiver.mo222roundToPx0680j_4(f2);
        f3 = TextFieldKt.TextFieldTopPadding;
        final int i4 = receiver.mo222roundToPx0680j_4(f3);
        long m2968copyZbe2FdA$default = Constraints.m2968copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        List<? extends Measurable> list = measurables;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj), TextFieldImplKt.LeadingId)) {
                break;
            }
        }
        Measurable measurable = (Measurable) obj;
        Placeable mo2510measureBRTryo02 = measurable == null ? null : measurable.mo2510measureBRTryo0(m2968copyZbe2FdA$default);
        int widthOrZero = TextFieldImplKt.widthOrZero(mo2510measureBRTryo02);
        Iterator<T> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj2), TextFieldImplKt.TrailingId)) {
                break;
            }
        }
        Measurable measurable2 = (Measurable) obj2;
        if (measurable2 == null) {
            placeable = mo2510measureBRTryo02;
            mo2510measureBRTryo0 = null;
        } else {
            placeable = mo2510measureBRTryo02;
            mo2510measureBRTryo0 = measurable2.mo2510measureBRTryo0(ConstraintsKt.m2994offsetNN6EwU$default(m2968copyZbe2FdA$default, -widthOrZero, 0, 2, null));
        }
        int i5 = -i3;
        int i6 = -(widthOrZero + TextFieldImplKt.widthOrZero(mo2510measureBRTryo0));
        long m2993offsetNN6EwU = ConstraintsKt.m2993offsetNN6EwU(m2968copyZbe2FdA$default, i6, i5);
        Iterator<T> it3 = list.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj3 = null;
                break;
            }
            obj3 = it3.next();
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj3), TextFieldImplKt.LabelId)) {
                break;
            }
        }
        Measurable measurable3 = (Measurable) obj3;
        Placeable mo2510measureBRTryo03 = measurable3 == null ? null : measurable3.mo2510measureBRTryo0(m2993offsetNN6EwU);
        Integer valueOf = mo2510measureBRTryo03 == null ? null : Integer.valueOf(mo2510measureBRTryo03.get(AlignmentLineKt.getLastBaseline()));
        if (valueOf == null) {
            intValue = 0;
        } else {
            intValue = valueOf.intValue();
            if (intValue == Integer.MIN_VALUE) {
                intValue = mo2510measureBRTryo03.getHeight();
            }
        }
        final int max = Math.max(intValue, i2);
        long m2993offsetNN6EwU2 = ConstraintsKt.m2993offsetNN6EwU(Constraints.m2968copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i6, mo2510measureBRTryo03 != null ? (i5 - i4) - max : (-i) * 2);
        for (Measurable measurable4 : list) {
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.TextFieldId)) {
                final Placeable mo2510measureBRTryo04 = measurable4.mo2510measureBRTryo0(m2993offsetNN6EwU2);
                long m2968copyZbe2FdA$default2 = Constraints.m2968copyZbe2FdA$default(m2993offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                Iterator<T> it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        obj4 = null;
                        break;
                    }
                    obj4 = it4.next();
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj4), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                }
                Measurable measurable5 = (Measurable) obj4;
                final Placeable mo2510measureBRTryo05 = measurable5 == null ? null : measurable5.mo2510measureBRTryo0(m2968copyZbe2FdA$default2);
                m905calculateWidthVsPV1Ek = TextFieldKt.m905calculateWidthVsPV1Ek(TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(mo2510measureBRTryo0), mo2510measureBRTryo04.getWidth(), TextFieldImplKt.widthOrZero(mo2510measureBRTryo03), TextFieldImplKt.widthOrZero(mo2510measureBRTryo05), j);
                m904calculateHeightYbqEFUw = TextFieldKt.m904calculateHeightYbqEFUw(mo2510measureBRTryo04.getHeight(), mo2510measureBRTryo03 != null, max, TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(mo2510measureBRTryo0), TextFieldImplKt.heightOrZero(mo2510measureBRTryo05), j, receiver.getDensity());
                final Placeable placeable2 = mo2510measureBRTryo03;
                final int i7 = intValue;
                final Placeable placeable3 = placeable;
                return MeasureScope.DefaultImpls.layout$default(receiver, m905calculateWidthVsPV1Ek, m904calculateHeightYbqEFUw, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$measure$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        boolean z;
                        boolean z2;
                        float f4;
                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                        if (Placeable.this != null) {
                            int coerceAtLeast = RangesKt.coerceAtLeast(i2 - i7, 0);
                            int i8 = m905calculateWidthVsPV1Ek;
                            int i9 = m904calculateHeightYbqEFUw;
                            Placeable placeable4 = mo2510measureBRTryo04;
                            Placeable placeable5 = Placeable.this;
                            Placeable placeable6 = mo2510measureBRTryo05;
                            Placeable placeable7 = placeable3;
                            Placeable placeable8 = mo2510measureBRTryo0;
                            z2 = this.singleLine;
                            int i10 = i4 + max;
                            f4 = this.animationProgress;
                            TextFieldKt.placeWithLabel(layout, i8, i9, placeable4, placeable5, placeable6, placeable7, placeable8, z2, coerceAtLeast, i10, f4, receiver.getDensity());
                            return;
                        }
                        int i11 = m905calculateWidthVsPV1Ek;
                        int i12 = m904calculateHeightYbqEFUw;
                        Placeable placeable9 = mo2510measureBRTryo04;
                        Placeable placeable10 = mo2510measureBRTryo05;
                        Placeable placeable11 = placeable3;
                        Placeable placeable12 = mo2510measureBRTryo0;
                        z = this.singleLine;
                        TextFieldKt.placeWithoutLabel(layout, i11, i12, placeable9, placeable10, placeable11, placeable12, z, receiver.getDensity());
                    }
                }, 4, null);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight(intrinsicMeasureScope, measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$maxIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.maxIntrinsicHeight(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight(intrinsicMeasureScope, measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$minIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.minIntrinsicHeight(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$maxIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.maxIntrinsicWidth(i2);
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material.TextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return Integer.valueOf(invoke(intrinsicMeasurable, num.intValue()));
            }

            public final int invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return intrinsicMeasurable.minIntrinsicWidth(i2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        Object layoutId;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        long j;
        int m905calculateWidthVsPV1Ek;
        Object layoutId2;
        Object layoutId3;
        Object layoutId4;
        Object layoutId5;
        List<? extends IntrinsicMeasurable> list = measurables;
        for (Object obj5 : list) {
            layoutId = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj5);
            if (Intrinsics.areEqual(layoutId, TextFieldImplKt.TextFieldId)) {
                int intValue = intrinsicMeasurer.invoke(obj5, Integer.valueOf(height)).intValue();
                Iterator<T> it = list.iterator();
                while (true) {
                    obj = null;
                    if (!it.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it.next();
                    layoutId5 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj2);
                    if (Intrinsics.areEqual(layoutId5, TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj2;
                int intValue2 = intrinsicMeasurable == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue();
                Iterator<T> it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj3 = null;
                        break;
                    }
                    obj3 = it2.next();
                    layoutId4 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj3);
                    if (Intrinsics.areEqual(layoutId4, TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj3;
                int intValue3 = intrinsicMeasurable2 == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue();
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj4 = null;
                        break;
                    }
                    obj4 = it3.next();
                    layoutId3 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj4);
                    if (Intrinsics.areEqual(layoutId3, TextFieldImplKt.LeadingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
                int intValue4 = intrinsicMeasurable3 == null ? 0 : intrinsicMeasurer.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue();
                Iterator<T> it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    Object next = it4.next();
                    layoutId2 = TextFieldKt.getLayoutId((IntrinsicMeasurable) next);
                    if (Intrinsics.areEqual(layoutId2, TextFieldImplKt.PlaceholderId)) {
                        obj = next;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj;
                int intValue5 = intrinsicMeasurable4 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue() : 0;
                j = TextFieldKt.ZeroConstraints;
                m905calculateWidthVsPV1Ek = TextFieldKt.m905calculateWidthVsPV1Ek(intValue4, intValue3, intValue, intValue2, intValue5, j);
                return m905calculateWidthVsPV1Ek;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object layoutId;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        long j;
        int m904calculateHeightYbqEFUw;
        Object layoutId2;
        Object layoutId3;
        Object layoutId4;
        Object layoutId5;
        List<? extends IntrinsicMeasurable> list2 = list;
        for (Object obj5 : list2) {
            layoutId = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj5);
            if (Intrinsics.areEqual(layoutId, TextFieldImplKt.TextFieldId)) {
                int intValue = function2.invoke(obj5, Integer.valueOf(i)).intValue();
                Iterator<T> it = list2.iterator();
                while (true) {
                    obj = null;
                    if (!it.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it.next();
                    layoutId5 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj2);
                    if (Intrinsics.areEqual(layoutId5, TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj2;
                int intValue2 = intrinsicMeasurable == null ? 0 : function2.invoke(intrinsicMeasurable, Integer.valueOf(i)).intValue();
                Iterator<T> it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj3 = null;
                        break;
                    }
                    obj3 = it2.next();
                    layoutId4 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj3);
                    if (Intrinsics.areEqual(layoutId4, TextFieldImplKt.TrailingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj3;
                int intValue3 = intrinsicMeasurable2 == null ? 0 : function2.invoke(intrinsicMeasurable2, Integer.valueOf(i)).intValue();
                Iterator<T> it3 = list2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj4 = null;
                        break;
                    }
                    obj4 = it3.next();
                    layoutId3 = TextFieldKt.getLayoutId((IntrinsicMeasurable) obj4);
                    if (Intrinsics.areEqual(layoutId3, TextFieldImplKt.LeadingId)) {
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
                int intValue4 = intrinsicMeasurable3 == null ? 0 : function2.invoke(intrinsicMeasurable3, Integer.valueOf(i)).intValue();
                Iterator<T> it4 = list2.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    Object next = it4.next();
                    layoutId2 = TextFieldKt.getLayoutId((IntrinsicMeasurable) next);
                    if (Intrinsics.areEqual(layoutId2, TextFieldImplKt.PlaceholderId)) {
                        obj = next;
                        break;
                    }
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj;
                int intValue5 = intrinsicMeasurable4 == null ? 0 : function2.invoke(intrinsicMeasurable4, Integer.valueOf(i)).intValue();
                boolean z = intValue2 != 0;
                j = TextFieldKt.ZeroConstraints;
                m904calculateHeightYbqEFUw = TextFieldKt.m904calculateHeightYbqEFUw(intValue, z, intValue2, intValue4, intValue3, intValue5, j, intrinsicMeasureScope.getDensity());
                return m904calculateHeightYbqEFUw;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
