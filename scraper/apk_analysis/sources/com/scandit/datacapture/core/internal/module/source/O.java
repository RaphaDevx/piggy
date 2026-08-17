package com.scandit.datacapture.core.internal.module.source;

import android.util.Range;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class O {
    public static final Comparator a = new Comparator() { // from class: com.scandit.datacapture.core.internal.module.source.O$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return O.a((Range) obj, (Range) obj2);
        }
    };
    public static final Comparator b = new Comparator() { // from class: com.scandit.datacapture.core.internal.module.source.O$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return O.b((Range) obj, (Range) obj2);
        }
    };

    public static final int a(Range range, Range range2) {
        Object upper = range.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper, "getUpper(...)");
        int intValue = ((Number) upper).intValue();
        Object upper2 = range2.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper2, "getUpper(...)");
        if (intValue <= ((Number) upper2).intValue()) {
            Object upper3 = range.getUpper();
            Intrinsics.checkNotNullExpressionValue(upper3, "getUpper(...)");
            int intValue2 = ((Number) upper3).intValue();
            Object upper4 = range2.getUpper();
            Intrinsics.checkNotNullExpressionValue(upper4, "getUpper(...)");
            if (intValue2 >= ((Number) upper4).intValue()) {
                Object lower = range.getLower();
                Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
                int intValue3 = ((Number) lower).intValue();
                Object lower2 = range2.getLower();
                Intrinsics.checkNotNullExpressionValue(lower2, "getLower(...)");
                if (intValue3 <= ((Number) lower2).intValue()) {
                    Object lower3 = range.getLower();
                    Intrinsics.checkNotNullExpressionValue(lower3, "getLower(...)");
                    int intValue4 = ((Number) lower3).intValue();
                    Object lower4 = range2.getLower();
                    Intrinsics.checkNotNullExpressionValue(lower4, "getLower(...)");
                    if (intValue4 >= ((Number) lower4).intValue()) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        return -1;
    }

    public static final int b(Range range, Range range2) {
        Object upper = range.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper, "getUpper(...)");
        int intValue = ((Number) upper).intValue();
        Object upper2 = range2.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper2, "getUpper(...)");
        if (intValue <= ((Number) upper2).intValue()) {
            Object upper3 = range.getUpper();
            Intrinsics.checkNotNullExpressionValue(upper3, "getUpper(...)");
            int intValue2 = ((Number) upper3).intValue();
            Object upper4 = range2.getUpper();
            Intrinsics.checkNotNullExpressionValue(upper4, "getUpper(...)");
            if (intValue2 >= ((Number) upper4).intValue()) {
                Object lower = range.getLower();
                Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
                int intValue3 = ((Number) lower).intValue();
                Object lower2 = range2.getLower();
                Intrinsics.checkNotNullExpressionValue(lower2, "getLower(...)");
                if (intValue3 >= ((Number) lower2).intValue()) {
                    Object lower3 = range.getLower();
                    Intrinsics.checkNotNullExpressionValue(lower3, "getLower(...)");
                    int intValue4 = ((Number) lower3).intValue();
                    Object lower4 = range2.getLower();
                    Intrinsics.checkNotNullExpressionValue(lower4, "getLower(...)");
                    if (intValue4 <= ((Number) lower4).intValue()) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        return -1;
    }

    public static final Range c(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        ArrayList arrayList = new ArrayList();
        for (Range range : frameRateRanges) {
            if ((((Integer) range.getUpper()) != null ? Float.valueOf(r4.intValue()) : null).floatValue() <= f) {
                arrayList.add(range);
            }
        }
        return (Range) CollectionsKt.firstOrNull(CollectionsKt.sortedWith(arrayList, b));
    }

    public static final Range a(Range[] frameRateRanges, float f) {
        Object next;
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        ArrayList arrayList = new ArrayList();
        int length = frameRateRanges.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Range range = frameRateRanges[i];
            if ((((Integer) range.getUpper()) != null ? Float.valueOf(r5.intValue()) : null).floatValue() <= f) {
                arrayList.add(range);
            }
            i++;
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                Integer num = (Integer) ((Range) next).getUpper();
                do {
                    Object next2 = it.next();
                    Integer num2 = (Integer) ((Range) next2).getUpper();
                    if (num.compareTo(num2) < 0) {
                        next = next2;
                        num = num2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Range range2 = (Range) next;
        Integer num3 = range2 != null ? (Integer) range2.getUpper() : null;
        if (num3 != null) {
            return new Range(num3, num3);
        }
        return null;
    }

    public static final Range b(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        ArrayList arrayList = new ArrayList();
        for (Range range : frameRateRanges) {
            if ((((Integer) range.getUpper()) != null ? Float.valueOf(r4.intValue()) : null).floatValue() <= f) {
                arrayList.add(range);
            }
        }
        return (Range) CollectionsKt.firstOrNull(CollectionsKt.sortedWith(arrayList, a));
    }
}
