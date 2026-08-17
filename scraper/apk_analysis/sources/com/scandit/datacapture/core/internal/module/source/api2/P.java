package com.scandit.datacapture.core.internal.module.source.api2;

import android.util.Range;
import kotlin.ranges.ClosedRange;

/* loaded from: classes2.dex */
public final class P implements ClosedRange {
    public final /* synthetic */ Range a;

    public P(Range range) {
        this.a = range;
    }

    @Override // kotlin.ranges.ClosedRange
    public final boolean contains(Comparable comparable) {
        return ClosedRange.DefaultImpls.contains(this, comparable);
    }

    @Override // kotlin.ranges.ClosedRange
    public final Comparable getEndInclusive() {
        return this.a.getUpper();
    }

    @Override // kotlin.ranges.ClosedRange
    public final Comparable getStart() {
        return this.a.getLower();
    }

    @Override // kotlin.ranges.ClosedRange
    public final boolean isEmpty() {
        return ClosedRange.DefaultImpls.isEmpty(this);
    }
}
