package androidx.compose.foundation.lazy;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: IntervalList.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J$\u0010\u0011\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0002J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\u0016\u001a\u00020\bR\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/IntervalList;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "intervals", "", "Landroidx/compose/foundation/lazy/IntervalHolder;", "<set-?>", "", "totalSize", "getTotalSize$foundation_release", "()I", "add", "", RRWebVideoEvent.JsonKeys.SIZE, FirebaseAnalytics.Param.CONTENT, "(ILjava/lang/Object;)V", "findIndexOfHighestValueLesserThan", "list", "", "value", "intervalForIndex", FirebaseAnalytics.Param.INDEX, "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class IntervalList<T> {
    private final List<IntervalHolder<T>> intervals = new ArrayList();
    private int totalSize;

    /* renamed from: getTotalSize$foundation_release, reason: from getter */
    public final int getTotalSize() {
        return this.totalSize;
    }

    public final void add(int size, T content) {
        if (size == 0) {
            return;
        }
        IntervalHolder<T> intervalHolder = new IntervalHolder<>(this.totalSize, size, content);
        this.totalSize += size;
        this.intervals.add(intervalHolder);
    }

    public final IntervalHolder<T> intervalForIndex(int index) {
        if (index < 0 || index >= this.totalSize) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + this.totalSize);
        }
        List<IntervalHolder<T>> list = this.intervals;
        return list.get(findIndexOfHighestValueLesserThan(list, index));
    }

    private final int findIndexOfHighestValueLesserThan(List<IntervalHolder<T>> list, int value) {
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 0;
        while (i < lastIndex) {
            int i2 = (i + lastIndex) / 2;
            int startIndex = list.get(i2).getStartIndex();
            if (startIndex == value) {
                return i2;
            }
            if (startIndex < value) {
                i = i2 + 1;
                if (value < list.get(i).getStartIndex()) {
                    return i2;
                }
            } else {
                lastIndex = i2 - 1;
            }
        }
        return i;
    }
}
