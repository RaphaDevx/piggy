package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.paymentmethods.boncard.BoncardConfig;
import ch.datatrans.payment.paymentmethods.boncard.BoncardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class E9 extends ViewModel {
    public final Nb a;
    public final Xa b;

    public E9(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
        this.b = new Xa();
    }

    public static final Unit b(E9 e9, PaymentMethodType paymentMethodType) {
        e9.a.i = paymentMethodType;
        Xa xa = e9.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final List a(final List paymentMethods, boolean z) {
        Intrinsics.checkNotNullParameter(paymentMethods, "paymentMethods");
        GooglePayConfig googlePayConfig = this.a.h.i;
        if ((googlePayConfig != null ? googlePayConfig.getShowLargeButton() : false) && this.a.d == null && paymentMethods.contains(PaymentMethodType.GOOGLE_PAY)) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : paymentMethods) {
                if (((PaymentMethodType) obj) != PaymentMethodType.GOOGLE_PAY) {
                    arrayList.add(obj);
                }
            }
            paymentMethods = z ? CollectionsKt.plus((Collection) CollectionsKt.listOf(PaymentMethodType.GOOGLE_PAY), (Iterable) arrayList) : CollectionsKt.plus((Collection) arrayList, (Iterable) CollectionsKt.listOf(PaymentMethodType.GOOGLE_PAY));
        }
        List mutableList = CollectionsKt.toMutableList((Collection) a(paymentMethods));
        Iterator it = paymentMethods.iterator();
        final int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (((PaymentMethodType) it.next()).isCreditCard$lib_release()) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            mutableList.add(i, new C0162j4(new Z9(this.a.h.h.getLabelRes(), new Object[0]), R.drawable.dtpl_generic_credit_card, null, new Function0() { // from class: ch.datatrans.payment.E9$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return E9.a(E9.this, paymentMethods, i);
                }
            }));
        }
        return mutableList;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0101 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList a(android.content.Context r8, java.util.List r9) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.E9.a(android.content.Context, java.util.List):java.util.ArrayList");
    }

    public static final Unit a(E9 e9, SavedPaymentMethod savedPaymentMethod) {
        e9.a.a(savedPaymentMethod);
        e9.a.b = true;
        Xa xa = e9.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit a(E9 e9, List list, int i) {
        e9.a.i = (PaymentMethodType) list.get(i);
        Xa xa = e9.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final List a(List list) {
        List createListBuilder = CollectionsKt.createListBuilder();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!((PaymentMethodType) obj).isCreditCard$lib_release()) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            final PaymentMethodType paymentMethodType = (PaymentMethodType) obj2;
            if (paymentMethodType == PaymentMethodType.BONCARD) {
                BoncardConfig boncardConfig = this.a.h.b;
                if (boncardConfig == null) {
                    boncardConfig = new BoncardConfig(null, 1, null);
                }
                List<BoncardType> boncardTypes$lib_release = boncardConfig.getBoncardTypes$lib_release();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(boncardTypes$lib_release, 10));
                for (final BoncardType boncardType : boncardTypes$lib_release) {
                    arrayList2.add(new C0162j4(new Z9(boncardType.getTitleId(), new Object[0]), boncardType.getLogo(), null, new Function0() { // from class: ch.datatrans.payment.E9$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return E9.a(E9.this, paymentMethodType, boncardType);
                        }
                    }));
                }
                createListBuilder.addAll(arrayList2);
            } else {
                GooglePayConfig googlePayConfig = this.a.h.i;
                if ((googlePayConfig != null ? googlePayConfig.getShowLargeButton() : false) && paymentMethodType == PaymentMethodType.GOOGLE_PAY && this.a.d == null) {
                    createListBuilder.add(new C0179m5(new Function0() { // from class: ch.datatrans.payment.E9$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return E9.a(E9.this, paymentMethodType);
                        }
                    }));
                } else {
                    createListBuilder.add(new C0162j4(y4.a(paymentMethodType), paymentMethodType.getLogo(), null, new Function0() { // from class: ch.datatrans.payment.E9$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return E9.b(E9.this, paymentMethodType);
                        }
                    }));
                }
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    public static final Unit a(E9 e9, PaymentMethodType paymentMethodType, BoncardType boncardType) {
        Nb nb = e9.a;
        nb.i = paymentMethodType;
        nb.q = boncardType;
        Xa xa = e9.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit a(E9 e9, PaymentMethodType paymentMethodType) {
        e9.a.i = paymentMethodType;
        Xa xa = e9.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }
}
