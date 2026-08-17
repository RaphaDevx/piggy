package ch.datatrans.payment;

import ch.datatrans.payment.exception.ModuleMissingException;
import ch.datatrans.payment.exception.PaymentMethodValidationException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.PaymentMethodTypeKt;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import ch.datatrans.payment.paymentmethods.SavedBoncard;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.paymentmethods.boncard.BoncardType;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Nb {
    public String a;
    public boolean b;
    public String c;
    public C0131e d;
    public SavedPaymentMethod e;
    public Card f;
    public L3 g;
    public Q5 h;
    public PaymentMethodType i;
    public List j;
    public W8 k;
    public SavedPaymentMethod l;
    public List m;
    public String n;
    public String o;
    public String p;
    public BoncardType q;
    public List r;
    public final Lazy s;
    public List t;

    public Nb() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.c = uuid;
        this.h = new Q5();
        this.j = CollectionsKt.emptyList();
        this.m = CollectionsKt.emptyList();
        this.r = CollectionsKt.emptyList();
        this.t = CollectionsKt.emptyList();
        this.s = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.Nb$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(Nb.a(Nb.this));
            }
        });
    }

    public final Nb a() {
        Nb nb = new Nb();
        String str = this.a;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileToken");
            str = null;
        }
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        nb.a = str;
        nb.b = this.b;
        nb.c = this.c;
        nb.e = this.e;
        nb.d = this.d;
        Card card = this.f;
        if (card != null) {
            nb.a(CollectionsKt.listOf(card.getType()));
        }
        nb.f = card;
        nb.h = this.h;
        nb.i = this.i;
        nb.a(this.j);
        nb.k = this.k;
        nb.a(this.l);
        nb.m = this.m;
        nb.n = this.n;
        nb.q = this.q;
        nb.p = this.p;
        nb.g = this.g;
        nb.r = this.r;
        nb.t = this.t;
        return nb;
    }

    public final boolean b() {
        if (this.d != null) {
            return false;
        }
        boolean isEmpty = this.m.isEmpty();
        boolean contains = this.j.contains(PaymentMethodType.GOOGLE_PAY);
        boolean contains2 = this.j.contains(PaymentMethodType.SAMSUNG_PAY);
        GooglePayConfig googlePayConfig = this.h.i;
        boolean showGooglePayAsSavedPaymentMethod = googlePayConfig != null ? googlePayConfig.getShowGooglePayAsSavedPaymentMethod() : false;
        SamsungPayConfig samsungPayConfig = this.h.o;
        boolean showSamsungPayAsSavedPaymentMethod = samsungPayConfig != null ? samsungPayConfig.getShowSamsungPayAsSavedPaymentMethod() : false;
        if (!isEmpty) {
            return true;
        }
        if (showGooglePayAsSavedPaymentMethod && contains) {
            return true;
        }
        return showSamsungPayAsSavedPaymentMethod && contains2;
    }

    public final void c() {
        try {
            PaymentMethodType paymentMethodType = this.i;
            if (paymentMethodType != null) {
                PaymentMethodTypeKt.validateConfigurations(paymentMethodType, this.h, this.b);
                return;
            }
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                PaymentMethodTypeKt.validateConfigurations((PaymentMethodType) it.next(), this.h, this.b);
            }
        } catch (ModuleMissingException e) {
            if (this.h.v) {
                throw e;
            }
            e.getMessage();
        } catch (PaymentMethodValidationException e2) {
            if (this.h.v) {
                throw e2;
            }
            e2.getMessage();
        }
    }

    public final void a(List value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.size() != 1) {
            if (!value.isEmpty()) {
                if (!value.isEmpty()) {
                    Iterator it = value.iterator();
                    while (it.hasNext()) {
                        if (!((PaymentMethodType) it.next()).isCreditCard$lib_release()) {
                            break;
                        }
                    }
                }
            }
            this.j = value;
        }
        this.i = (PaymentMethodType) value.get(0);
        this.j = value;
    }

    public final void a(SavedPaymentMethod savedPaymentMethod) {
        if (savedPaymentMethod != null) {
            this.i = savedPaymentMethod.getType();
            SavedBoncard savedBoncard = savedPaymentMethod instanceof SavedBoncard ? (SavedBoncard) savedPaymentMethod : null;
            this.q = savedBoncard != null ? savedBoncard.getBoncardType() : null;
        }
        this.l = savedPaymentMethod;
    }

    public static final boolean a(Nb nb) {
        return (nb.f == null && nb.e == null && nb.m.isEmpty()) ? false : true;
    }
}
