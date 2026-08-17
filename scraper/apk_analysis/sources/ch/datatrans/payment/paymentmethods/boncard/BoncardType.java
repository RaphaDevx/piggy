package ch.datatrans.payment.paymentmethods.boncard;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u001a\u0010\u0007\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\n\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lch/datatrans/payment/paymentmethods/boncard/BoncardType;", "", "", "a", "I", "getTitleId$lib_release", "()I", "titleId", "b", "getLogo$lib_release", "logo", "GIFT_CARD", "BONCARD", "LUNCH_CHECK", "SBB_VOUCHER", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class BoncardType {
    public static final BoncardType BONCARD;
    public static final BoncardType GIFT_CARD;
    public static final BoncardType LUNCH_CHECK;
    public static final BoncardType SBB_VOUCHER;
    public static final /* synthetic */ BoncardType[] c;
    public static final /* synthetic */ EnumEntries d;

    /* renamed from: a, reason: from kotlin metadata */
    public final int titleId;

    /* renamed from: b, reason: from kotlin metadata */
    public final int logo;

    static {
        BoncardType boncardType = new BoncardType("GIFT_CARD", 0, R.string.datatrans_sdk_payment_method_boncard_gift_card, R.drawable.dtpl_payment_method_bon_gift_card);
        GIFT_CARD = boncardType;
        BoncardType boncardType2 = new BoncardType("BONCARD", 1, R.string.datatrans_sdk_payment_method_boncard_boncard, R.drawable.dtpl_payment_method_bon_boncard);
        BONCARD = boncardType2;
        BoncardType boncardType3 = new BoncardType("LUNCH_CHECK", 2, R.string.datatrans_sdk_payment_method_boncard_lunch_check, R.drawable.dtpl_payment_method_bon_lunch_check);
        LUNCH_CHECK = boncardType3;
        BoncardType boncardType4 = new BoncardType("SBB_VOUCHER", 3, R.string.datatrans_sdk_payment_method_boncard_sbb_voucher, R.drawable.dtpl_payment_method_bon_sbb_voucher);
        SBB_VOUCHER = boncardType4;
        BoncardType[] boncardTypeArr = {boncardType, boncardType2, boncardType3, boncardType4};
        c = boncardTypeArr;
        d = EnumEntriesKt.enumEntries(boncardTypeArr);
    }

    public BoncardType(String str, int i, int i2, int i3) {
        this.titleId = i2;
        this.logo = i3;
    }

    public static EnumEntries<BoncardType> getEntries() {
        return d;
    }

    public static BoncardType valueOf(String str) {
        return (BoncardType) Enum.valueOf(BoncardType.class, str);
    }

    public static BoncardType[] values() {
        return (BoncardType[]) c.clone();
    }

    /* renamed from: getLogo$lib_release, reason: from getter */
    public final int getLogo() {
        return this.logo;
    }

    /* renamed from: getTitleId$lib_release, reason: from getter */
    public final int getTitleId() {
        return this.titleId;
    }
}
