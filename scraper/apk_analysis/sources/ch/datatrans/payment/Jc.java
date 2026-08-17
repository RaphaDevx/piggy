package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.ModuleMissingException;
import ch.datatrans.payment.exception.PaymentMethodValidationException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.AuthorizationInitRequestConfig;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.PaymentMethodTypeKt;
import ch.datatrans.payment.paymentmethods.SavedBoncard;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.paymentmethods.boncard.BoncardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class Jc extends ViewModel implements Be, lg, S9, InterfaceC0141f4 {
    public final N9 a;
    public final boolean b;
    public final Xa c;
    public final Xa d;
    public final Xa e;
    public final Xa f;
    public final Xa g;
    public final Xa h;
    public final Xa i;
    public final Xa j;
    public final Xa k;
    public final MutableLiveData l;
    public final Xa m;
    public final MutableLiveData n;
    public final Xa o;
    public final Xa p;
    public long q;
    public boolean r;
    public Be s;

    public Jc(Transaction transaction, N9 n9, boolean z) {
        Object obj;
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        this.a = n9;
        this.b = z;
        this.c = new Xa();
        this.d = new Xa();
        this.e = new Xa();
        this.f = new Xa();
        this.g = new Xa();
        this.h = new Xa();
        this.i = new Xa();
        this.j = new Xa();
        this.k = new Xa();
        this.l = new MutableLiveData();
        this.m = new Xa();
        this.n = new MutableLiveData(Boolean.FALSE);
        this.o = new Xa();
        this.p = new Xa();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        Nb transactionModel$lib_release = transactionRegistry.getTransactionModel$lib_release();
        String mobileToken = transaction.getMobileToken();
        transactionModel$lib_release.getClass();
        Intrinsics.checkNotNullParameter(mobileToken, "<set-?>");
        transactionModel$lib_release.a = mobileToken;
        Nb transactionModel$lib_release2 = transactionRegistry.getTransactionModel$lib_release();
        Card card = transaction.getCard();
        if (card != null) {
            transactionModel$lib_release2.getClass();
            transactionModel$lib_release2.a(CollectionsKt.listOf(card.getType()));
        }
        transactionModel$lib_release2.f = card;
        Iterator<T> it = transaction.getSavedPaymentMethods$lib_release().iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (((SavedPaymentMethod) obj).getType() == PaymentMethodType.GOOGLE_PAY) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) obj;
        Nb transactionModel$lib_release3 = TransactionRegistry.INSTANCE.getTransactionModel$lib_release();
        List<SavedPaymentMethod> savedPaymentMethods$lib_release = transaction.getSavedPaymentMethods$lib_release();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : savedPaymentMethods$lib_release) {
            if (((SavedPaymentMethod) obj2).isValid()) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj3 = arrayList.get(i);
            i++;
            SavedPaymentMethod savedPaymentMethod2 = (SavedPaymentMethod) obj3;
            if (savedPaymentMethod2.getType() != PaymentMethodType.GOOGLE_PAY || Intrinsics.areEqual(savedPaymentMethod2, savedPaymentMethod)) {
                arrayList2.add(obj3);
            }
        }
        transactionModel$lib_release3.getClass();
        Intrinsics.checkNotNullParameter(arrayList2, "<set-?>");
        transactionModel$lib_release3.m = arrayList2;
        TransactionRegistry transactionRegistry2 = TransactionRegistry.INSTANCE;
        transactionRegistry2.getTransactionModel$lib_release().e = transaction.getAppInitSavedPaymentMethod();
        Nb transactionModel$lib_release4 = transactionRegistry2.getTransactionModel$lib_release();
        Q5 internalOptions = transaction.getInternalOptions();
        transactionModel$lib_release4.getClass();
        Intrinsics.checkNotNullParameter(internalOptions, "<set-?>");
        transactionModel$lib_release4.h = internalOptions;
        this.o.postValue(Boolean.valueOf(transactionRegistry2.getTransactionModel$lib_release().h.y == null));
    }

    public static final Unit c(Jc jc) {
        jc.l.postValue(null);
        jc.c.postValue(new TechnicalException("No payment method available", null, null, null, 14, null));
        return Unit.INSTANCE;
    }

    public final void a(Nb value, TransactionException exception) {
        Intrinsics.checkNotNullParameter(value, "model");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        this.c.postValue(exception);
    }

    public final void b() {
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        PaymentMethodType paymentMethodType = transactionRegistry.getTransactionModel$lib_release().i;
        if (paymentMethodType == null) {
            throw new IllegalStateException("Required value was null.");
        }
        try {
            PaymentMethodTypeKt.validateConfigurations$default(paymentMethodType, transactionRegistry.getTransactionModel$lib_release().h, false, 2, null);
            if (paymentMethodType.isCreditCard$lib_release()) {
                this.n.postValue(Boolean.TRUE);
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new Hc(this, null), 2, null);
                return;
            }
            switch (Bc.a[paymentMethodType.ordinal()]) {
                case 1:
                    this.f.postValue(Ac.d);
                    return;
                case 2:
                    this.f.postValue(Ac.e);
                    return;
                case 3:
                    this.f.postValue(Ac.f);
                    return;
                case 4:
                    this.f.postValue(Ac.g);
                    return;
                case 5:
                    this.f.postValue(Ac.h);
                    return;
                case 6:
                    this.f.postValue(Ac.i);
                    return;
                case 7:
                    this.f.postValue(Ac.j);
                    return;
                case 8:
                    this.f.postValue(Ac.k);
                    return;
                case 9:
                    this.f.postValue(Ac.m);
                    return;
                case 10:
                    PaymentMethodType paymentMethodType2 = PaymentMethodType.IDEAL;
                    String str = transactionRegistry.getTransactionModel$lib_release().h.a;
                    Intrinsics.checkNotNull(str);
                    AuthorizationInitRequestConfig authorizationInitRequestConfig = new AuthorizationInitRequestConfig(paymentMethodType2.generateAppCallbackUrl$lib_release(str));
                    CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
                    C0149ga c0149ga = new C0149ga(viewModelScope, this);
                    W8 w8 = transactionRegistry.getTransactionModel$lib_release().k;
                    Intrinsics.checkNotNull(w8);
                    T9 request = new T9(new U9(c0149ga, this, w8, transactionRegistry.getTransactionModel$lib_release().h, paymentMethodType2, null), authorizationInitRequestConfig, null);
                    Intrinsics.checkNotNullParameter(paymentMethodType2, "paymentMethodType");
                    Intrinsics.checkNotNullParameter(request, "request");
                    BuildersKt__Builders_commonKt.launch$default(viewModelScope, null, null, new fa(request, c0149ga, false, paymentMethodType2, null), 3, null);
                    return;
                default:
                    a((Be) null);
                    return;
            }
        } catch (ModuleMissingException e) {
            a(e);
        } catch (PaymentMethodValidationException e2) {
            a(e2);
        }
    }

    public final void d() {
        Nb value = new Nb();
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        this.c.postValue(new TechnicalException("Transaction cannot continue, state recovery failed.", null, null, null, 14, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0060, code lost:
    
        if (((r4 == null || (r4 = r4.getBoncardTypes$lib_release()) == null) ? 1 : r4.size()) == 0) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            r5 = this;
            ch.datatrans.payment.api.TransactionRegistry r0 = ch.datatrans.payment.api.TransactionRegistry.INSTANCE
            ch.datatrans.payment.Nb r1 = r0.getTransactionModel$lib_release()
            boolean r1 = r1.b()
            if (r1 != 0) goto L13
            ch.datatrans.payment.Nb r1 = r0.getTransactionModel$lib_release()
            r1.c()
        L13:
            ch.datatrans.payment.Nb r1 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.paymentmethods.PaymentMethodType r1 = r1.i
            ch.datatrans.payment.paymentmethods.PaymentMethodType r2 = ch.datatrans.payment.paymentmethods.PaymentMethodType.BONCARD
            if (r1 != r2) goto L3b
            ch.datatrans.payment.Nb r3 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.Q5 r3 = r3.h
            ch.datatrans.payment.paymentmethods.boncard.BoncardConfig r3 = r3.b
            if (r3 == 0) goto L3b
            java.util.List r3 = r3.getBoncardTypes$lib_release()
            if (r3 == 0) goto L3b
            java.lang.Object r3 = kotlin.collections.CollectionsKt.singleOrNull(r3)
            ch.datatrans.payment.paymentmethods.boncard.BoncardType r3 = (ch.datatrans.payment.paymentmethods.boncard.BoncardType) r3
            if (r3 == 0) goto L3b
            ch.datatrans.payment.Nb r4 = r0.getTransactionModel$lib_release()
            r4.q = r3
        L3b:
            ch.datatrans.payment.Nb r3 = r0.getTransactionModel$lib_release()
            java.util.List r3 = r3.j
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto Lbd
            r3 = 1
            if (r1 != r2) goto L63
            ch.datatrans.payment.Nb r4 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.Q5 r4 = r4.h
            ch.datatrans.payment.paymentmethods.boncard.BoncardConfig r4 = r4.b
            if (r4 == 0) goto L5f
            java.util.List r4 = r4.getBoncardTypes$lib_release()
            if (r4 == 0) goto L5f
            int r4 = r4.size()
            goto L60
        L5f:
            r4 = r3
        L60:
            if (r4 != 0) goto L63
            goto Lbd
        L63:
            ch.datatrans.payment.Nb r4 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.paymentmethods.SavedPaymentMethod r4 = r4.l
            if (r4 == 0) goto L6f
            r5.a()
            return
        L6f:
            ch.datatrans.payment.Nb r4 = r0.getTransactionModel$lib_release()
            boolean r4 = r4.b()
            if (r4 == 0) goto L83
            r5.r = r3
            ch.datatrans.payment.Xa r5 = r5.f
            ch.datatrans.payment.Ac r0 = ch.datatrans.payment.Ac.a
            r5.postValue(r0)
            return
        L83:
            if (r1 != r2) goto La7
            ch.datatrans.payment.Nb r1 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.Q5 r1 = r1.h
            ch.datatrans.payment.paymentmethods.boncard.BoncardConfig r1 = r1.b
            if (r1 == 0) goto L9a
            java.util.List r1 = r1.getBoncardTypes$lib_release()
            if (r1 == 0) goto L9a
            int r1 = r1.size()
            goto L9b
        L9a:
            r1 = r3
        L9b:
            if (r1 <= r3) goto La7
            r5.r = r3
            ch.datatrans.payment.Xa r5 = r5.f
            ch.datatrans.payment.Ac r0 = ch.datatrans.payment.Ac.b
            r5.postValue(r0)
            return
        La7:
            ch.datatrans.payment.Nb r0 = r0.getTransactionModel$lib_release()
            ch.datatrans.payment.paymentmethods.PaymentMethodType r0 = r0.i
            if (r0 == 0) goto Lb3
            r5.b()
            return
        Lb3:
            r5.r = r3
            ch.datatrans.payment.Xa r5 = r5.f
            ch.datatrans.payment.Ac r0 = ch.datatrans.payment.Ac.b
            r5.postValue(r0)
            return
        Lbd:
            androidx.lifecycle.MutableLiveData r0 = r5.l
            ch.datatrans.payment.C7 r1 = ch.datatrans.payment.r4.a
            ch.datatrans.payment.Jc$$ExternalSyntheticLambda3 r1 = new ch.datatrans.payment.Jc$$ExternalSyntheticLambda3
            r1.<init>()
            ch.datatrans.payment.m4 r5 = ch.datatrans.payment.v4.a(r1)
            r0.postValue(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.Jc.e():void");
    }

    @Override // ch.datatrans.payment.Be
    public final void c() {
        Be be = this.s;
        if (be != null) {
            be.c();
        }
        if (this.r) {
            return;
        }
        this.e.postValue(Unit.INSTANCE);
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void c(Function0 retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retry");
        MutableLiveData mutableLiveData = this.l;
        C7 c7 = r4.a;
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.Jc$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jc.b(Jc.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, retryAction, cancelAction));
    }

    public static final Unit b(Jc jc) {
        jc.l.postValue(null);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void b(final Function0 retry) {
        Intrinsics.checkNotNullParameter(retry, "retry");
        MutableLiveData mutableLiveData = this.l;
        C7 c7 = r4.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.Jc$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jc.a(Jc.this, retry);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        mutableLiveData.postValue(new C0228u(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction));
    }

    public final void a(Nb value) {
        Intrinsics.checkNotNullParameter(value, "model");
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        this.d.postValue(Unit.INSTANCE);
    }

    public final I9 a(PaymentMethodType paymentMethodType) {
        String a;
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        De de2 = null;
        if (transactionRegistry.getTransactionModel$lib_release().d != null) {
            De de3 = r4.e;
            if (de3 != null) {
                de2 = de3;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("webRequestFactory");
            }
            Nb model = transactionRegistry.getTransactionModel$lib_release();
            de2.getClass();
            Intrinsics.checkNotNullParameter(model, "model");
            C0131e aliasRequest = model.d;
            if (aliasRequest != null) {
                Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
                a = de2.a(new W8(0, aliasRequest.b, aliasRequest.a, aliasRequest.c, aliasRequest.d, aliasRequest.e, ""), model);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        } else {
            De de4 = r4.e;
            if (de4 != null) {
                de2 = de4;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("webRequestFactory");
            }
            Nb model2 = transactionRegistry.getTransactionModel$lib_release();
            de2.getClass();
            Intrinsics.checkNotNullParameter(model2, "model");
            W8 w8 = model2.k;
            if (w8 != null) {
                a = de2.a(w8, model2);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        return new I9(a, paymentMethodType, this);
    }

    public final void a() {
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        SavedPaymentMethod savedPaymentMethod = transactionRegistry.getTransactionModel$lib_release().l;
        if (savedPaymentMethod != null) {
            try {
                PaymentMethodTypeKt.validateConfigurations(savedPaymentMethod.getType(), transactionRegistry.getTransactionModel$lib_release().h, true);
                if (savedPaymentMethod.getType().isCreditCard$lib_release()) {
                    this.f.postValue(Ac.c);
                    return;
                }
                if (savedPaymentMethod.getType() == PaymentMethodType.PAY_PAL) {
                    this.f.postValue(Ac.d);
                    return;
                }
                if (savedPaymentMethod.getType() == PaymentMethodType.GOOGLE_PAY) {
                    this.f.postValue(Ac.e);
                    return;
                }
                if (savedPaymentMethod.getType() == PaymentMethodType.SAMSUNG_PAY) {
                    this.f.postValue(Ac.f);
                    return;
                }
                if (savedPaymentMethod.getType() == PaymentMethodType.HALF_FARE_PLUS) {
                    this.p.postValue(transactionRegistry.getTransactionModel$lib_release());
                    return;
                } else if (savedPaymentMethod.getType() == PaymentMethodType.POST_FINANCE_PAY) {
                    this.f.postValue(Ac.m);
                    return;
                } else {
                    this.d.postValue(Unit.INSTANCE);
                    return;
                }
            } catch (ModuleMissingException e) {
                a(e);
                return;
            } catch (PaymentMethodValidationException e2) {
                a(e2);
                return;
            }
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // ch.datatrans.payment.S9
    public final void a(String str, String transactionId, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        transactionRegistry.getTransactionModel$lib_release().n = transactionId;
        this.i.postValue(Unit.INSTANCE);
        Xa xa = this.g;
        Intrinsics.checkNotNull(str);
        String str2 = transactionRegistry.getTransactionModel$lib_release().h.a;
        Intrinsics.checkNotNull(str2);
        xa.postValue(paymentMethodType.getWebProcess$lib_release(this, str, str2));
    }

    public final void a(Be be) {
        C0196p5 c0196p5;
        Ae i9;
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        if (transactionRegistry.getTransactionModel$lib_release().i != null) {
            this.s = be;
            PaymentMethodType paymentMethodType = transactionRegistry.getTransactionModel$lib_release().i;
            Intrinsics.checkNotNull(paymentMethodType);
            if (paymentMethodType.getRequiresExternalWebProcess$lib_release() && this.b) {
                this.h.postValue(a(paymentMethodType));
                return;
            }
            De de2 = null;
            if (transactionRegistry.getTransactionModel$lib_release().d != null) {
                De de3 = r4.e;
                if (de3 != null) {
                    de2 = de3;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("webRequestFactory");
                }
                Nb model = transactionRegistry.getTransactionModel$lib_release();
                N9 n9 = this.a;
                de2.getClass();
                Intrinsics.checkNotNullParameter(model, "model");
                C0131e aliasRequest = model.d;
                if (aliasRequest != null) {
                    Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
                    c0196p5 = new C0196p5(de2.a.b((model.n == null || model.i == PaymentMethodType.HALF_FARE_PLUS) ? false : true), "UTF-8", de2.a(new W8(0, aliasRequest.b, aliasRequest.a, aliasRequest.c, aliasRequest.d, aliasRequest.e, ""), model, n9));
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            } else {
                De de4 = r4.e;
                if (de4 != null) {
                    de2 = de4;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("webRequestFactory");
                }
                Nb model2 = transactionRegistry.getTransactionModel$lib_release();
                N9 n92 = this.a;
                de2.getClass();
                Intrinsics.checkNotNullParameter(model2, "model");
                W8 w8 = model2.k;
                if (w8 != null) {
                    c0196p5 = new C0196p5(de2.a.b((model2.n == null || model2.i == PaymentMethodType.HALF_FARE_PLUS) ? false : true), "UTF-8", de2.a(w8, model2, n92));
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            if (paymentMethodType == PaymentMethodType.POST_FINANCE_CARD) {
                i9 = new Q9(c0196p5, paymentMethodType, this);
            } else {
                i9 = new I9(c0196p5, paymentMethodType, this, false);
            }
            this.g.postValue(i9);
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    @Override // ch.datatrans.payment.Be
    public final void a(BackendException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Be be = this.s;
        if (be != null) {
            be.a(e);
        }
        String transactionId = e.getTransactionId();
        if (transactionId == null) {
            transactionId = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().n;
        }
        PaymentMethodType paymentMethodType = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().i;
        Intrinsics.checkNotNull(paymentMethodType);
        this.c.postValue(new AuthenticationException(e, paymentMethodType, transactionId, null, 8, null));
    }

    @Override // ch.datatrans.payment.Be
    public final void a(String str, Map parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Be be = this.s;
        if (be != null) {
            be.a(str, parameters);
        }
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        BoncardType boncardType = transactionRegistry.getTransactionModel$lib_release().q;
        SavedPaymentMethod savedPaymentMethod = null;
        if (boncardType == null || transactionRegistry.getTransactionModel$lib_release().i != PaymentMethodType.BONCARD) {
            boncardType = null;
        }
        Nb transactionModel$lib_release = transactionRegistry.getTransactionModel$lib_release();
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        if (parameters.get("aliasCC") != null) {
            if (boncardType != null) {
                Intrinsics.checkNotNull(boncardType);
                parameters = MapsKt.plus(parameters, TuplesKt.to(SavedBoncard.BONCARD_TYPE_KEY, boncardType.name()));
            }
            String jSONObject = new JSONObject(parameters).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            savedPaymentMethod = SavedPaymentMethod.INSTANCE.create(jSONObject);
        }
        transactionModel$lib_release.a(savedPaymentMethod);
        if (str != null) {
            transactionRegistry.getTransactionModel$lib_release().n = str;
        }
        this.d.postValue(Unit.INSTANCE);
    }

    public static final Unit a(Jc jc, Function0 function0) {
        jc.l.postValue(null);
        function0.invoke();
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void a(Function0 retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retry");
        MutableLiveData mutableLiveData = this.l;
        C7 c7 = r4.a;
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.Jc$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jc.a(Jc.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public static final Unit a(Jc jc) {
        jc.l.postValue(null);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void a(final Exception e, final PaymentMethodType paymentMethodType) {
        C0178m4 c0178m4;
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.Jc$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jc.a(Jc.this, e, paymentMethodType);
            }
        };
        MutableLiveData mutableLiveData = this.l;
        if (TransactionRegistry.INSTANCE.getTransactionModel$lib_release().l != null) {
            C7 c7 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed, dismissAction);
        } else {
            C7 c72 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction);
        }
        mutableLiveData.postValue(c0178m4);
    }

    public static final Unit a(Jc jc, Exception exc, PaymentMethodType paymentMethodType) {
        String str;
        jc.l.postValue(null);
        BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
        if (backendException == null || (str = backendException.getTransactionId()) == null) {
            str = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().n;
        }
        jc.m.postValue(exc instanceof TechnicalException ? (TransactionException) exc : new AuthenticationException(exc, paymentMethodType, str, null, 8, null));
        return Unit.INSTANCE;
    }

    public final void a(final TechnicalException technicalException) {
        MutableLiveData mutableLiveData = this.l;
        C7 c7 = r4.a;
        mutableLiveData.postValue(v4.a(new Function0() { // from class: ch.datatrans.payment.Jc$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Jc.a(Jc.this, technicalException);
            }
        }));
    }

    public static final Unit a(Jc jc, TechnicalException technicalException) {
        jc.l.postValue(null);
        jc.m.postValue(technicalException);
        return Unit.INSTANCE;
    }
}
