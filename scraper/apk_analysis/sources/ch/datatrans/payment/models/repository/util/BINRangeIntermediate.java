package ch.datatrans.payment.models.repository.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u000e\b\u0080\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0004¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\u0011R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0010\u001a\u0004\b\u0012\u0010\u0011R&\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\r\u0010\u0010\u001a\u0004\b\u000f\u0010\u0011¨\u0006\u0013"}, d2 = {"Lch/datatrans/payment/models/repository/util/BINRangeIntermediate;", "", "", "type", "", "", "numberLengths", "numberSpacing", "numberRanges", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "a", "Ljava/lang/String;", "d", "()Ljava/lang/String;", "b", "Ljava/util/List;", "()Ljava/util/List;", "c", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class BINRangeIntermediate {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("type")
    private final String type;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("numberLengths")
    private final List<Integer> numberLengths;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("numberSpacing")
    private final List<Integer> numberSpacing;

    /* renamed from: d, reason: from kotlin metadata */
    @SerializedName("numberRanges")
    private final List<List<Integer>> numberRanges;

    /* JADX WARN: Multi-variable type inference failed */
    public BINRangeIntermediate(String type, List<Integer> numberLengths, List<Integer> list, List<? extends List<Integer>> numberRanges) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(numberLengths, "numberLengths");
        Intrinsics.checkNotNullParameter(numberRanges, "numberRanges");
        this.type = type;
        this.numberLengths = numberLengths;
        this.numberSpacing = list;
        this.numberRanges = numberRanges;
    }

    /* renamed from: a, reason: from getter */
    public final List getNumberLengths() {
        return this.numberLengths;
    }

    /* renamed from: b, reason: from getter */
    public final List getNumberRanges() {
        return this.numberRanges;
    }

    /* renamed from: c, reason: from getter */
    public final List getNumberSpacing() {
        return this.numberSpacing;
    }

    /* renamed from: d, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BINRangeIntermediate)) {
            return false;
        }
        BINRangeIntermediate bINRangeIntermediate = (BINRangeIntermediate) obj;
        return Intrinsics.areEqual(this.type, bINRangeIntermediate.type) && Intrinsics.areEqual(this.numberLengths, bINRangeIntermediate.numberLengths) && Intrinsics.areEqual(this.numberSpacing, bINRangeIntermediate.numberSpacing) && Intrinsics.areEqual(this.numberRanges, bINRangeIntermediate.numberRanges);
    }

    public final int hashCode() {
        int hashCode = (this.numberLengths.hashCode() + (this.type.hashCode() * 31)) * 31;
        List<Integer> list = this.numberSpacing;
        return this.numberRanges.hashCode() + ((hashCode + (list == null ? 0 : list.hashCode())) * 31);
    }

    public final String toString() {
        return "BINRangeIntermediate(type=" + this.type + ", numberLengths=" + this.numberLengths + ", numberSpacing=" + this.numberSpacing + ", numberRanges=" + this.numberRanges + ')';
    }
}
