package ch.datatrans.payment;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationOptions;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.PCIPTokenizationException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CvvOnlyCard;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: ch.datatrans.payment.w8, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0247w8 extends AndroidViewModel {
    public static final /* synthetic */ int r = 0;
    public final v4 a;
    public final PCIPTokenization b;
    public final CvvOnlyCard c;
    public final boolean d;
    public final String e;
    public final PCIPTokenizationOptions f;
    public final ArrayList g;
    public final Q7 h;
    public final MutableLiveData i;
    public final Xa j;
    public final Xa k;
    public final Xa l;
    public final Xa m;
    public final Xa n;
    public final Xa o;
    public Card p;
    public String q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0247w8(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.a = new v4();
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        Intrinsics.checkNotNull(tokenization);
        this.b = tokenization;
        CvvOnlyCard cvvOnlyCard = tokenization.getCvvOnlyCard();
        this.c = cvvOnlyCard;
        this.d = cvvOnlyCard != null;
        this.e = tokenization.getMerchantId();
        this.f = tokenization.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String();
        List<PaymentMethodType> paymentMethodTypes$lib_release = tokenization.getPaymentMethodTypes$lib_release();
        ArrayList arrayList = null;
        if (paymentMethodTypes$lib_release != null) {
            ArrayList arrayList2 = new ArrayList();
            for (PaymentMethodType paymentMethodType : paymentMethodTypes$lib_release) {
                if (!paymentMethodType.isCreditCard$lib_release()) {
                    Log.e("DTPL", "Ignoring payment method type '" + paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String() + "' because it can not be tokenized");
                    paymentMethodType = null;
                }
                if (paymentMethodType != null) {
                    arrayList2.add(paymentMethodType);
                }
            }
            arrayList = arrayList2;
        }
        this.g = arrayList;
        this.h = new Q7(r4.a());
        this.i = new MutableLiveData();
        this.j = new Xa();
        this.k = new Xa();
        this.l = new Xa();
        this.m = new Xa();
        this.n = new Xa();
        this.o = new Xa();
        this.p = this.b.getCard();
        c();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|(1:(1:(3:11|12|13)(2:19|20))(3:21|22|23))(4:24|25|(4:27|(1:29)|30|(1:32)(2:34|23))(2:35|(2:37|13))|33)|14|15|16))|44|6|7|(0)(0)|14|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00aa, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ab, code lost:
    
        r2 = r17.i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
    
        if ((r0 instanceof ch.datatrans.payment.exception.BackendException) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b1, code lost:
    
        r0 = r17.a((ch.datatrans.payment.exception.BackendException) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00bc, code lost:
    
        r2.postValue(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b8, code lost:
    
        r0 = r17.a(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.C0247w8 r17, kotlin.coroutines.jvm.internal.ContinuationImpl r18) {
        /*
            r1 = r17
            r0 = r18
            boolean r2 = r0 instanceof ch.datatrans.payment.C0240v8
            if (r2 == 0) goto L17
            r2 = r0
            ch.datatrans.payment.v8 r2 = (ch.datatrans.payment.C0240v8) r2
            int r3 = r2.e
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.e = r3
            goto L1c
        L17:
            ch.datatrans.payment.v8 r2 = new ch.datatrans.payment.v8
            r2.<init>(r1, r0)
        L1c:
            java.lang.Object r0 = r2.c
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.e
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L41
            if (r4 == r6) goto L3b
            if (r4 != r5) goto L33
            ch.datatrans.payment.Xa r2 = r2.a
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            goto La4
        L33:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3b:
            ch.datatrans.payment.Xa r2 = r2.a
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            goto L88
        L41:
            kotlin.ResultKt.throwOnFailure(r0)
            ch.datatrans.payment.Xa r0 = r1.l     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            boolean r4 = r1.d     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            if (r4 == 0) goto L8b
            ch.datatrans.payment.Q7 r4 = r1.h     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            java.lang.String r5 = r1.e     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            java.lang.String r7 = r1.q     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            if (r7 != 0) goto L58
            java.lang.String r7 = "cvv"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r7 = 0
        L58:
            r12 = r7
            ch.datatrans.payment.paymentmethods.CvvOnlyCard r7 = r1.c     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            java.lang.String r10 = r7.getMaskedCardNumber()     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.CvvOnlyCard r7 = r1.c     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.PaymentMethodType r9 = r7.getType()     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.CvvOnlyCard r7 = r1.c     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.CardExpiryDate r11 = r7.getExpiryDate()     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.Card r7 = new ch.datatrans.payment.paymentmethods.Card     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r14 = 16
            r15 = 0
            r13 = 0
            r8 = r7
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r2.a = r0     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r2.e = r6     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            java.lang.Object r2 = r4.a(r5, r7, r2)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            if (r2 != r3) goto L83
            goto L9e
        L83:
            r16 = r2
            r2 = r0
            r0 = r16
        L88:
            ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess r0 = (ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess) r0     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            goto La6
        L8b:
            ch.datatrans.payment.Q7 r4 = r1.h     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.api.tokenization.PCIPTokenization r6 = r1.b     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            ch.datatrans.payment.paymentmethods.Card r7 = r1.p     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r2.a = r0     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            r2.e = r5     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            java.lang.Object r2 = r4.a(r6, r7, r2)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            if (r2 != r3) goto L9f
        L9e:
            return r3
        L9f:
            r16 = r2
            r2 = r0
            r0 = r16
        La4:
            ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess r0 = (ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess) r0     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
        La6:
            r2.postValue(r0)     // Catch: ch.datatrans.payment.exception.TechnicalException -> Laa
            goto Lbf
        Laa:
            r0 = move-exception
            androidx.lifecycle.MutableLiveData r2 = r1.i
            boolean r3 = r0 instanceof ch.datatrans.payment.exception.BackendException
            if (r3 == 0) goto Lb8
            ch.datatrans.payment.exception.BackendException r0 = (ch.datatrans.payment.exception.BackendException) r0
            ch.datatrans.payment.m4 r0 = r1.a(r0)
            goto Lbc
        Lb8:
            ch.datatrans.payment.m4 r0 = r1.a(r0)
        Lbc:
            r2.postValue(r0)
        Lbf:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0247w8.a(ch.datatrans.payment.w8, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final Unit d(C0247w8 c0247w8) {
        c0247w8.i.setValue(null);
        Xa xa = c0247w8.k;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit e(C0247w8 c0247w8) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0247w8), null, null, new C0202q8(c0247w8, null), 3, null);
        return Unit.INSTANCE;
    }

    public static final Unit f(C0247w8 c0247w8) {
        c0247w8.i.setValue(null);
        Xa xa = c0247w8.k;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit g(C0247w8 c0247w8) {
        Xa xa = c0247w8.k;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void b() {
        MutableLiveData mutableLiveData = this.i;
        v4 v4Var = this.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0247w8.e(C0247w8.this);
            }
        };
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0247w8.f(C0247w8.this);
            }
        };
        v4Var.getClass();
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.setValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, retryAction, cancelAction));
    }

    public final void c() {
        if (!this.d) {
            if (this.p == null) {
                this.m.postValue(Unit.INSTANCE);
                return;
            } else {
                this.o.setValue(Boolean.valueOf(this.f.getCustomInitialLoader() == null));
                a(this.f.getCustomInitialLoader() == null);
                return;
            }
        }
        CvvOnlyCard cvvOnlyCard = this.c;
        if (cvvOnlyCard == null || (cvvOnlyCard.getType().getHasCvv$lib_release() && this.c.getType().isCreditCard$lib_release())) {
            this.m.postValue(Unit.INSTANCE);
        } else {
            this.i.postValue(new C0178m4(R.string.error_pcip_payment_method_type_not_valid_title, R.string.error_pcip_payment_method_type_not_valid_message, new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.g(C0247w8.this);
                }
            }));
        }
    }

    public static final Unit b(C0247w8 c0247w8, BackendException backendException) {
        c0247w8.i.setValue(null);
        c0247w8.j.setValue(new PCIPTokenizationException("Invalid MerchantId", backendException));
        return Unit.INSTANCE;
    }

    public static final Unit b(C0247w8 c0247w8, Exception exc) {
        c0247w8.i.setValue(null);
        BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
        String message = backendException != null ? backendException.getMessage() : null;
        if (message == null) {
            message = "Tokenization Error";
        }
        c0247w8.j.setValue(new PCIPTokenizationException(message, exc));
        return Unit.INSTANCE;
    }

    public static final Unit b(C0247w8 c0247w8) {
        c0247w8.i.setValue(null);
        Xa xa = c0247w8.k;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit c(C0247w8 c0247w8) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0247w8), null, null, new n8(c0247w8, null), 3, null);
        return Unit.INSTANCE;
    }

    public final void a(boolean z) {
        this.n.postValue(Boolean.valueOf(z));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C0232u8(this, null), 3, null);
    }

    public final void a() {
        MutableLiveData mutableLiveData = this.i;
        v4 v4Var = this.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0247w8.c(C0247w8.this);
            }
        };
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0247w8.d(C0247w8.this);
            }
        };
        v4Var.getClass();
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.setValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public final C0178m4 a(final BackendException backendException) {
        int errorCode = backendException.getErrorCode();
        if (errorCode >= 600) {
            v4 v4Var = this.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.a(C0247w8.this);
                }
            };
            v4Var.getClass();
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            return new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, dismissAction);
        }
        if (errorCode >= 500) {
            v4 v4Var2 = this.a;
            Function0 function0 = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.b(C0247w8.this);
                }
            };
            v4Var2.getClass();
            return v4.a(function0);
        }
        if (errorCode == 401) {
            v4 v4Var3 = this.a;
            Function0 dismissAction2 = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.b(C0247w8.this, backendException);
                }
            };
            v4Var3.getClass();
            Intrinsics.checkNotNullParameter(dismissAction2, "dismissAction");
            return new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction2);
        }
        if (errorCode == 403) {
            v4 v4Var4 = this.a;
            Function0 dismissAction3 = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.a(C0247w8.this, backendException);
                }
            };
            v4Var4.getClass();
            Intrinsics.checkNotNullParameter(dismissAction3, "dismissAction");
            return new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_invalid_credit_card, dismissAction3);
        }
        return a((Exception) backendException);
    }

    public final C0178m4 a(final Exception exc) {
        String message = exc.getMessage();
        if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "INVALID_CARD", false, 2, (Object) null)) {
            v4 v4Var = this.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0247w8.a(C0247w8.this, exc);
                }
            };
            v4Var.getClass();
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            return new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_invalid_credit_card, dismissAction);
        }
        v4 v4Var2 = this.a;
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.w8$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0247w8.b(C0247w8.this, exc);
            }
        };
        v4Var2.getClass();
        return v4.a(function0);
    }

    public static final Unit a(C0247w8 c0247w8, BackendException backendException) {
        c0247w8.i.setValue(null);
        BackendException backendException2 = backendException instanceof BackendException ? backendException : null;
        String message = backendException2 != null ? backendException2.getMessage() : null;
        if (message == null) {
            message = "Tokenization Error";
        }
        c0247w8.j.setValue(new PCIPTokenizationException(message, backendException));
        return Unit.INSTANCE;
    }

    public static final Unit a(C0247w8 c0247w8, Exception exc) {
        c0247w8.i.setValue(null);
        BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
        String message = backendException != null ? backendException.getMessage() : null;
        if (message == null) {
            message = "Tokenization Error";
        }
        c0247w8.j.setValue(new PCIPTokenizationException(message, exc));
        return Unit.INSTANCE;
    }

    public static final Unit a(C0247w8 c0247w8) {
        c0247w8.i.setValue(null);
        Xa xa = c0247w8.k;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }
}
