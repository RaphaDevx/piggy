package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* renamed from: ch.datatrans.payment.m0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0177m0 extends ViewModel {
    public List a = CollectionsKt.emptyList();
    public final MutableLiveData b = new MutableLiveData(CollectionsKt.listOf(Integer.valueOf(G3.h)));
    public final MutableLiveData c = new MutableLiveData();
    public final MutableLiveData d = new MutableLiveData();
    public final MutableLiveData e = new MutableLiveData();
    public final MutableLiveData f = new MutableLiveData();
    public final Xa g = new Xa();
    public final MutableLiveData h = new MutableLiveData();
    public final MutableLiveData i = new MutableLiveData();
    public final MutableLiveData j = new MutableLiveData();
    public final MutableLiveData k = new MutableLiveData();
    public final Xa l = new Xa();
    public final PCIPTokenization m = PCIPTokenizationRegistry.INSTANCE.getTokenization();

    public final void a(boolean z) {
        if (!Intrinsics.areEqual(AbstractC0144g0.d.a, AbstractC0144g0.e)) {
            String str = AbstractC0144g0.d.a;
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            AbstractC0144g0.e = str;
            C0205r0 c0205r0 = AbstractC0144g0.a;
            c0205r0.d = false;
            Intrinsics.checkNotNullParameter("", "<set-?>");
            c0205r0.a = "";
            U1 u1 = AbstractC0144g0.b;
            u1.getClass();
            Intrinsics.checkNotNullParameter("", "<set-?>");
            u1.a = "";
            W0 w0 = AbstractC0144g0.c;
            w0.a = "";
            w0.b = "";
            w0.c = -1L;
            w0.d = "";
            w0.e = "";
            w0.f = a1.c;
            w0.g = false;
            w0.h = false;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new C0171l0(z, this, null), 2, null);
    }

    public final void b() {
        MutableLiveData mutableLiveData = this.h;
        if (!AbstractC0144g0.b.a() || !AbstractC0144g0.a.a()) {
            throw new IllegalStateException("Check failed.");
        }
        C3 c3 = AbstractC0144g0.d;
        PaymentMethodType paymentMethodType = c3.b.c;
        Intrinsics.checkNotNull(paymentMethodType);
        String str = c3.a;
        U1 u1 = AbstractC0144g0.b;
        Integer intOrNull = u1.a.length() >= 2 ? StringsKt.toIntOrNull(StringsKt.take(u1.a, 2)) : null;
        Intrinsics.checkNotNull(intOrNull);
        int intValue = intOrNull.intValue();
        U1 u12 = AbstractC0144g0.b;
        Integer intOrNull2 = u12.a.length() == 4 ? StringsKt.toIntOrNull(StringsKt.takeLast(u12.a, 2)) : null;
        Intrinsics.checkNotNull(intOrNull2);
        mutableLiveData.postValue(new Card(paymentMethodType, str, new CardExpiryDate(intValue, intOrNull2.intValue()), AbstractC0144g0.a.a, null, 16, null));
    }

    public final void c() {
        C0219t0 c0219t0 = AbstractC0144g0.g;
        if (AbstractC0144g0.c.f == a1.b) {
            this.g.postValue(EnumC0152h0.c);
            return;
        }
        if ((c0219t0 != null ? c0219t0.a : null) == null || !c0219t0.a(AbstractC0144g0.d.b.c) || AbstractC0144g0.c.f != a1.d) {
            if (!a()) {
                b();
                return;
            }
            MutableLiveData mutableLiveData = this.i;
            if (!AbstractC0144g0.a.a()) {
                throw new IllegalStateException("Check failed.");
            }
            mutableLiveData.postValue(AbstractC0144g0.a.a);
            return;
        }
        this.f.postValue(Boolean.TRUE);
        Xa xa = this.l;
        C3 c3 = AbstractC0144g0.d;
        String str = c3.a;
        PaymentMethodType paymentMethodType = c3.b.c;
        Intrinsics.checkNotNull(paymentMethodType);
        Q3 q3 = Q3.a;
        xa.postValue(new Y0(c0219t0, new Function2() { // from class: ch.datatrans.payment.m0$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return C0177m0.a(C0177m0.this, (Exception) obj, (Q3) obj2);
            }
        }, new Function2() { // from class: ch.datatrans.payment.m0$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return C0177m0.a(C0177m0.this, (X0) obj, (Q3) obj2);
            }
        }, paymentMethodType, Q3.a, null, str));
    }

    public final boolean a() {
        PCIPTokenization pCIPTokenization = this.m;
        return (pCIPTokenization != null ? pCIPTokenization.getCvvOnlyCard() : null) != null;
    }

    public static final Unit a(final C0177m0 c0177m0, Exception exception, Q3 q3) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(q3, "<unused var>");
        c0177m0.f.postValue(Boolean.FALSE);
        if (exception instanceof D7) {
            MutableLiveData mutableLiveData = c0177m0.k;
            C7 c7 = r4.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.m0$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0177m0.a(C0177m0.this);
                }
            };
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, dismissAction));
        } else {
            c0177m0.b();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0177m0 c0177m0) {
        c0177m0.k.postValue(null);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0177m0 c0177m0, X0 result, Q3 q3) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(q3, "<unused var>");
        c0177m0.f.postValue(Boolean.FALSE);
        AbstractC0144g0.c.a(result);
        if (AbstractC0144g0.c.f == a1.b) {
            c0177m0.g.postValue(EnumC0152h0.c);
        } else {
            c0177m0.b();
        }
        return Unit.INSTANCE;
    }
}
