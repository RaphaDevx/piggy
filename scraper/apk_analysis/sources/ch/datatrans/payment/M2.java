package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class M2 extends ViewModel implements xb {
    public static final /* synthetic */ int o = 0;
    public final Nb a;
    public final W0 b;
    public final ArrayList c;
    public final MutableLiveData d;
    public final Xa e;
    public final Xa f;
    public final Xa g;
    public final Xa h;
    public final MutableLiveData i;
    public final Xa j;
    public final Xa k;
    public final Xa l;
    public final Hb m;
    public final P3 n;

    public M2(Nb transactionModel, R9 preferencesRepository) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.a = transactionModel;
        this.b = new W0();
        List list = transactionModel.j;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((PaymentMethodType) obj).isCreditCard$lib_release()) {
                arrayList.add(obj);
            }
        }
        this.c = arrayList;
        this.d = new MutableLiveData();
        this.e = new Xa();
        this.f = new Xa();
        this.g = new Xa();
        this.h = new Xa();
        this.i = new MutableLiveData();
        this.j = new Xa();
        this.k = new Xa();
        this.l = new Xa();
        this.m = new Hb(this.a, this);
        this.n = new P3(preferencesRepository, this.a.h.A);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
    
        if (r5.a(r0) != r1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
    
        if (r5.a(r0) == r1) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.M2 r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            boolean r0 = r6 instanceof ch.datatrans.payment.F2
            if (r0 == 0) goto L13
            r0 = r6
            ch.datatrans.payment.F2 r0 = (ch.datatrans.payment.F2) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            ch.datatrans.payment.F2 r0 = new ch.datatrans.payment.F2
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L55
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4a
        L38:
            kotlin.ResultKt.throwOnFailure(r6)
            ch.datatrans.payment.Nb r6 = r5.a
            boolean r6 = r6.b
            if (r6 != 0) goto L4a
            r0.d = r4
            java.lang.Object r6 = r5.a(r0)
            if (r6 != r1) goto L4a
            goto L54
        L4a:
            ch.datatrans.payment.Hb r5 = r5.m
            r0.d = r3
            java.lang.Object r5 = r5.a(r0)
            if (r5 != r1) goto L55
        L54:
            return r1
        L55:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.M2.a(ch.datatrans.payment.M2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final Unit e(M2 m2) {
        m2.d.postValue(null);
        Xa xa = m2.f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.xb
    public final void b() {
        this.f.postValue(Unit.INSTANCE);
    }

    public final void c() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new L2(this, null), 3, null);
    }

    public final void d() {
        Nb nb = this.a;
        SavedPaymentMethod savedPaymentMethod = nb.l;
        Card card = nb.f;
        W8 w8 = nb.k;
        String str = w8 != null ? w8.c : null;
        C0219t0 c0219t0 = new C0219t0(str, nb.r, nb.t, nb.d == null);
        if (card != null && str != null && c0219t0.a(card.getType())) {
            a(c0219t0, card.getNumber(), card.getType(), false);
        } else if (savedPaymentMethod == null || str == null || !c0219t0.a(savedPaymentMethod.getType())) {
            c();
        } else {
            a(c0219t0, savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), savedPaymentMethod.getType(), true);
        }
    }

    public static final Unit b(M2 m2) {
        m2.d.postValue(null);
        Xa xa = m2.f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit c(M2 m2) {
        m2.d.postValue(null);
        Xa xa = m2.f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void e() {
        MutableLiveData mutableLiveData = this.d;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return M2.a(M2.this);
            }
        };
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, dismissAction));
    }

    public static final Unit d(M2 m2) {
        m2.d.postValue(null);
        Xa xa = m2.f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.xb
    public final void a(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.g.postValue(transactionModel);
        Nb nb = this.a;
        L3 l3 = nb.g;
        SavedPaymentMethod savedPaymentMethod = nb.l;
        if (savedPaymentMethod != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new E2(l3, this, savedPaymentMethod, null), 2, null);
        }
    }

    @Override // ch.datatrans.payment.xb
    public final void a(C0178m4 c0178m4) {
        this.d.postValue(c0178m4);
        if (c0178m4 != null) {
            Nb nb = this.a;
            L3 l3 = nb.g;
            SavedPaymentMethod savedPaymentMethod = nb.l;
            if (savedPaymentMethod != null) {
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new E2(l3, this, savedPaymentMethod, null), 2, null);
            }
        }
    }

    @Override // ch.datatrans.payment.xb
    public final void a(AuthenticationException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.e.postValue(exception);
    }

    @Override // ch.datatrans.payment.xb
    public final void a(final Eb retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        MutableLiveData mutableLiveData = this.d;
        C7 c7 = r4.a;
        Function0 retryAction2 = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return M2.a(M2.this, retryAction);
            }
        };
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return M2.c(M2.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction2, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction2, cancelAction));
    }

    public static final Unit a(M2 m2, Function1 function1) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(m2), null, null, new B2(function1, null), 3, null);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.xb
    public final void a(I9 paymentMethodWebProcess) {
        Intrinsics.checkNotNullParameter(paymentMethodWebProcess, "paymentMethodWebProcess");
        this.h.postValue(paymentMethodWebProcess);
    }

    @Override // ch.datatrans.payment.xb
    public final void a() {
        MutableLiveData mutableLiveData = this.d;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return M2.b(M2.this);
            }
        };
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, dismissAction));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof ch.datatrans.payment.C0252x2
            if (r0 == 0) goto L13
            r0 = r13
            ch.datatrans.payment.x2 r0 = (ch.datatrans.payment.C0252x2) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            ch.datatrans.payment.x2 r0 = new ch.datatrans.payment.x2
            r0.<init>(r12, r13)
        L18:
            java.lang.Object r13 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r12 = r0.g
            java.util.Map r12 = (java.util.Map) r12
            ch.datatrans.payment.paymentmethods.Card r12 = r0.f
            java.lang.Object r1 = r0.b
            ch.datatrans.payment.W8 r1 = (ch.datatrans.payment.W8) r1
            ch.datatrans.payment.Nb r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r13)
            goto L8f
        L35:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L3d:
            kotlin.ResultKt.throwOnFailure(r13)
            ch.datatrans.payment.Nb r12 = r12.a
            ch.datatrans.payment.e r13 = r12.d
            if (r13 == 0) goto L60
            java.lang.String r2 = "aliasRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r2)
            ch.datatrans.payment.W8 r2 = new ch.datatrans.payment.W8
            java.lang.String r6 = r13.b
            java.lang.String r7 = r13.a
            java.lang.String r8 = r13.c
            java.lang.String r9 = r13.d
            java.lang.String r10 = r13.e
            r5 = 0
            java.lang.String r11 = ""
            r4 = r2
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r12.k = r2
        L60:
            ch.datatrans.payment.W8 r13 = r12.k
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            ch.datatrans.payment.paymentmethods.Card r2 = r12.f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            ch.datatrans.payment.Q5 r4 = r12.h
            java.util.LinkedHashMap r4 = r4.k
            ch.datatrans.payment.C7 r5 = ch.datatrans.payment.r4.a()
            java.lang.String r6 = r13.c
            r0.a = r12
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.b = r13
            r0.f = r2
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.g = r13
            r0.e = r3
            java.lang.Object r13 = r5.a(r6, r2, r4, r0)
            if (r13 != r1) goto L8d
            return r1
        L8d:
            r0 = r12
            r12 = r2
        L8f:
            ch.datatrans.payment.I r13 = (ch.datatrans.payment.I) r13
            ch.datatrans.payment.paymentmethods.SavedCard r7 = new ch.datatrans.payment.paymentmethods.SavedCard
            ch.datatrans.payment.paymentmethods.PaymentMethodType r2 = r12.getType()
            java.lang.String r3 = r13.a
            ch.datatrans.payment.paymentmethods.CardExpiryDate r4 = r12.getExpiryDate()
            java.lang.String r5 = r13.b
            java.lang.String r6 = r12.getCardholder()
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            r0.a(r7)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.M2.a(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void a(C0219t0 c0219t0, String str, PaymentMethodType paymentMethodType, boolean z) {
        Q3 q3;
        this.i.postValue(Boolean.TRUE);
        String str2 = !z ? str : null;
        String str3 = z ? str : null;
        Xa xa = this.l;
        if (z) {
            if (this.a.e != null) {
                q3 = Q3.d;
            } else {
                q3 = Q3.c;
            }
        } else {
            q3 = Q3.b;
        }
        xa.postValue(new Y0(c0219t0, new Function2() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return M2.a(M2.this, (Exception) obj, (Q3) obj2);
            }
        }, new Function2() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return M2.a(M2.this, (X0) obj, (Q3) obj2);
            }
        }, paymentMethodType, q3, str3, str2));
    }

    public static final Unit a(final M2 m2, Exception exception, Q3 dccType) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(dccType, "dccType");
        m2.i.postValue(Boolean.FALSE);
        if (exception instanceof D7) {
            if (dccType != Q3.b && dccType != Q3.d) {
                MutableLiveData mutableLiveData = m2.d;
                C7 c7 = r4.a;
                Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return M2.e(M2.this);
                    }
                };
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, dismissAction));
            } else {
                MutableLiveData mutableLiveData2 = m2.d;
                C7 c72 = r4.a;
                G2 retryAction = new G2(m2);
                Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return M2.d(M2.this);
                    }
                };
                Intrinsics.checkNotNullParameter(retryAction, "retryAction");
                Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
                mutableLiveData2.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
            }
        } else {
            m2.c();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(M2 m2, X0 result, Q3 dccType) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(dccType, "dccType");
        m2.i.postValue(Boolean.FALSE);
        m2.b.a(result);
        if (m2.b.f == a1.b) {
            if (dccType != Q3.c && dccType != Q3.d) {
                if (dccType == Q3.b) {
                    m2.k.postValue(Unit.INSTANCE);
                } else {
                    m2.c();
                }
            } else {
                P3 p3 = m2.n;
                SavedPaymentMethod savedPaymentMethod = m2.a.l;
                Intrinsics.checkNotNull(savedPaymentMethod);
                String alias = savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String();
                p3.getClass();
                Intrinsics.checkNotNullParameter(alias, "alias");
                int i = O3.a[p3.b.ordinal()];
                boolean z = true;
                if (i != 1) {
                    if (i == 2) {
                        z = false;
                    } else if (i == 3) {
                        int i2 = p3.a(alias).a;
                        int i3 = i2 * 5 * i2;
                        int i4 = i3 + 4;
                        int sqrt = (int) Math.sqrt(i4);
                        if (sqrt * sqrt != i4) {
                            int i5 = i3 - 4;
                            int sqrt2 = (int) Math.sqrt(i5);
                            if (sqrt2 * sqrt2 != i5) {
                                Calendar calendar = Calendar.getInstance();
                                Date parse = p3.c.parse(p3.a(alias).b);
                                Intrinsics.checkNotNull(parse);
                                calendar.setTime(parse);
                                calendar.add(5, 30);
                                Calendar calendar2 = Calendar.getInstance();
                                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance(...)");
                                z = calendar.before(calendar2);
                            }
                        }
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                if (z) {
                    m2.k.postValue(Unit.INSTANCE);
                } else {
                    m2.c();
                }
            }
        } else {
            m2.c();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(M2 m2) {
        m2.d.postValue(null);
        Xa xa = m2.f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void a(final Exception exc) {
        final String str;
        BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
        if (backendException == null || (str = backendException.getTransactionId()) == null) {
            str = this.a.n;
        }
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.M2$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return M2.a(M2.this, exc, str);
            }
        };
        if (this.a.l != null) {
            MutableLiveData mutableLiveData = this.d;
            C7 c7 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_payment, R.string.datatrans_sdk_error_message_invalid_credit_card, dismissAction));
            return;
        }
        MutableLiveData mutableLiveData2 = this.d;
        C7 c72 = r4.a;
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData2.postValue(new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_invalid_credit_card, dismissAction));
    }

    public static final Unit a(M2 m2, Exception exc, String str) {
        m2.d.postValue(null);
        Xa xa = m2.e;
        PaymentMethodType paymentMethodType = m2.a.i;
        Intrinsics.checkNotNull(paymentMethodType);
        xa.postValue(new AuthenticationException(exc, paymentMethodType, str, null, 8, null));
        return Unit.INSTANCE;
    }
}
