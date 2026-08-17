package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u001a\u0010\u0007\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardLabelType;", "", "", "a", "I", "getLabelRes$lib_release", "()I", "labelRes", "CREDIT_OR_DEBIT_CARD", "CREDIT_CARD", "DEBIT_CARD", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class CardLabelType {
    public static final CardLabelType CREDIT_CARD;
    public static final CardLabelType CREDIT_OR_DEBIT_CARD;
    public static final CardLabelType DEBIT_CARD;
    public static final /* synthetic */ CardLabelType[] b;
    public static final /* synthetic */ EnumEntries c;

    /* renamed from: a, reason: from kotlin metadata */
    public final int labelRes;

    static {
        CardLabelType cardLabelType = new CardLabelType("CREDIT_OR_DEBIT_CARD", 0, R.string.datatrans_sdk_generic_credit_or_debit_card_name);
        CREDIT_OR_DEBIT_CARD = cardLabelType;
        CardLabelType cardLabelType2 = new CardLabelType("CREDIT_CARD", 1, R.string.datatrans_sdk_generic_credit_card_name);
        CREDIT_CARD = cardLabelType2;
        CardLabelType cardLabelType3 = new CardLabelType("DEBIT_CARD", 2, R.string.datatrans_sdk_generic_debit_card_name);
        DEBIT_CARD = cardLabelType3;
        CardLabelType[] cardLabelTypeArr = {cardLabelType, cardLabelType2, cardLabelType3};
        b = cardLabelTypeArr;
        c = EnumEntriesKt.enumEntries(cardLabelTypeArr);
    }

    public CardLabelType(String str, int i, int i2) {
        this.labelRes = i2;
    }

    public static EnumEntries<CardLabelType> getEntries() {
        return c;
    }

    public static CardLabelType valueOf(String str) {
        return (CardLabelType) Enum.valueOf(CardLabelType.class, str);
    }

    public static CardLabelType[] values() {
        return (CardLabelType[]) b.clone();
    }

    /* renamed from: getLabelRes$lib_release, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }
}
