package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\t\n\u000bR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardNumberLength;", "", "", "", "a", "Ljava/util/List;", "getLengthRange", "()Ljava/util/List;", "lengthRange", "Unique", "Range", "IntList", "Lch/datatrans/payment/paymentmethods/CardNumberLength$IntList;", "Lch/datatrans/payment/paymentmethods/CardNumberLength$Range;", "Lch/datatrans/payment/paymentmethods/CardNumberLength$Unique;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public abstract class CardNumberLength {

    /* renamed from: a, reason: from kotlin metadata */
    public final List lengthRange;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardNumberLength$IntList;", "Lch/datatrans/payment/paymentmethods/CardNumberLength;", "values", "", "", "<init>", "(Ljava/util/List;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class IntList extends CardNumberLength {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IntList(List<Integer> values) {
            super(values, null);
            Intrinsics.checkNotNullParameter(values, "values");
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardNumberLength$Range;", "Lch/datatrans/payment/paymentmethods/CardNumberLength;", "start", "", "end", "<init>", "(II)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Range extends CardNumberLength {
        public Range(int i, int i2) {
            super(CollectionsKt.toList(new IntRange(i, i2)), null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardNumberLength$Unique;", "Lch/datatrans/payment/paymentmethods/CardNumberLength;", "value", "", "<init>", "(I)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Unique extends CardNumberLength {
        public Unique(int i) {
            super(CollectionsKt.listOf(Integer.valueOf(i)), null);
        }
    }

    public CardNumberLength(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this.lengthRange = list;
    }

    public final List<Integer> getLengthRange() {
        return this.lengthRange;
    }
}
