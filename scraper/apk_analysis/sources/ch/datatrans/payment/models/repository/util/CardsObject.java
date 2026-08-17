package ch.datatrans.payment.models.repository.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lch/datatrans/payment/models/repository/util/CardsObject;", "", "", "Lch/datatrans/payment/models/repository/util/BINRangeIntermediate;", "cards", "<init>", "(Ljava/util/List;)V", "a", "Ljava/util/List;", "()Ljava/util/List;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class CardsObject {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("cards")
    private final List<BINRangeIntermediate> cards;

    public CardsObject(List<BINRangeIntermediate> cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        this.cards = cards;
    }

    /* renamed from: a, reason: from getter */
    public final List getCards() {
        return this.cards;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CardsObject) && Intrinsics.areEqual(this.cards, ((CardsObject) obj).cards);
    }

    public final int hashCode() {
        return this.cards.hashCode();
    }

    public final String toString() {
        return "CardsObject(cards=" + this.cards + ')';
    }
}
