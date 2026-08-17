package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.CardNumberLength;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class G3 {
    public static final int h = R.drawable.dtpl_unknown_card;
    public final boolean a;
    public final int b;
    public final PaymentMethodType c;
    public final List d;
    public final Integer e;
    public final CardNumberLength f;
    public final List g;

    public G3(List possibleBINRanges) {
        List distinct;
        Intrinsics.checkNotNullParameter(possibleBINRanges, "possibleBINRanges");
        if (possibleBINRanges.size() == 1) {
            C0189o c0189o = (C0189o) CollectionsKt.first(possibleBINRanges);
            this.a = true;
            this.b = c0189o.a.getCvvLength();
            PaymentMethodType paymentMethodType = c0189o.a;
            this.c = paymentMethodType;
            this.d = CollectionsKt.listOf(Integer.valueOf(paymentMethodType.getLogo()));
            this.e = null;
            this.f = c0189o.e;
            this.g = c0189o.d;
            return;
        }
        boolean isEmpty = possibleBINRanges.isEmpty();
        this.a = false;
        this.b = 0;
        this.c = null;
        if (isEmpty) {
            distinct = CollectionsKt.listOf(Integer.valueOf(h));
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(possibleBINRanges, 10));
            Iterator it = possibleBINRanges.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((C0189o) it.next()).a.getLogo()));
            }
            distinct = CollectionsKt.distinct(arrayList);
        }
        this.d = distinct;
        this.e = isEmpty ? Integer.valueOf(R.string.datatrans_sdk_unknown_card_hint) : null;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(possibleBINRanges, 10));
        Iterator it2 = possibleBINRanges.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((C0189o) it2.next()).e);
        }
        this.f = CollectionsKt.distinct(arrayList2).size() == 1 ? ((C0189o) CollectionsKt.first(possibleBINRanges)).e : new CardNumberLength.Unique(16);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(possibleBINRanges, 10));
        Iterator it3 = possibleBINRanges.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((C0189o) it3.next()).d);
        }
        this.g = CollectionsKt.distinct(arrayList3).size() == 1 ? ((C0189o) CollectionsKt.first(possibleBINRanges)).d : CollectionsKt.listOf((Object[]) new Integer[]{4, 8, 12});
    }
}
