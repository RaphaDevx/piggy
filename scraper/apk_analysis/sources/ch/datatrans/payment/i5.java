package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.api.TransactionOptions;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.GooglePayTransactionException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedCard;
import ch.datatrans.payment.paymentmethods.SavedGooglePay;
import com.google.android.gms.wallet.PaymentDataRequest;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.json.JSONException;

/* loaded from: classes3.dex */
public final class i5 extends D4 implements xb {
    public static final /* synthetic */ int l = 0;
    public final Xa h;
    public C0130de i;
    public C0137ee j;
    public final Hb k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i5(Nb transactionModel) {
        super(C4.f, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.h = new Xa();
        this.k = new Hb(transactionModel, this);
    }

    @Override // ch.datatrans.payment.xb
    public final void a(AuthenticationException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.e.postValue(exception);
    }

    @Override // ch.datatrans.payment.xb
    public final void b() {
        this.c.postValue(Unit.INSTANCE);
    }

    public final void d() {
        Y8 y8;
        GooglePayConfig googlePayConfig = e().h.i;
        if (googlePayConfig == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(TransactionOptions.ERROR_MISSING_OPTION, Arrays.copyOf(new Object[]{"Google Pay"}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            a(this, format, null, 2);
            return;
        }
        try {
            C0173l5 c0173l5 = new C0173l5(googlePayConfig);
            W8 w8 = e().k;
            if (w8 != null) {
                y8 = Z8.a(w8, null);
            } else {
                C0131e c0131e = e().d;
                Intrinsics.checkNotNull(c0131e);
                Intrinsics.checkNotNullParameter(c0131e, "<this>");
                y8 = new Y8(0, c0131e.a, c0131e.b, c0131e.c, c0131e.d, c0131e.e, null);
            }
            PaymentDataRequest fromJson = PaymentDataRequest.fromJson(c0173l5.a(y8).toString());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
            this.a.postValue(fromJson);
        } catch (JSONException e) {
            e.printStackTrace();
            a(this, null, null, 3);
        }
    }

    public static final Unit b(i5 i5Var) {
        i5Var.f.postValue(null);
        Xa xa = i5Var.c;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void a(final GooglePayTransactionException googlePayTransactionException) {
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        mutableLiveData.postValue(v4.a(new Function0() { // from class: ch.datatrans.payment.i5$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return i5.a(i5.this, googlePayTransactionException);
            }
        }));
    }

    public static final Unit a(i5 i5Var, GooglePayTransactionException googlePayTransactionException) {
        i5Var.f.postValue(null);
        i5Var.e.postValue(googlePayTransactionException);
        return Unit.INSTANCE;
    }

    public static final Unit a(i5 i5Var, Exception exc) {
        String str;
        TransactionException authenticationException;
        i5Var.f.postValue(null);
        Xa xa = i5Var.e;
        if (exc instanceof TransactionException) {
            authenticationException = (TransactionException) exc;
        } else {
            BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
            if (backendException == null || (str = backendException.getTransactionId()) == null) {
                str = i5Var.e().n;
            }
            authenticationException = new AuthenticationException(exc, PaymentMethodType.GOOGLE_PAY, str, null, 8, null);
        }
        xa.postValue(authenticationException);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.xb
    public final void a(C0178m4 c0178m4) {
        this.f.postValue(c0178m4);
    }

    @Override // ch.datatrans.payment.xb
    public final void a() {
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        c5 retryAction = new c5(this);
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.i5$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return i5.a(i5.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public static final Unit a(i5 i5Var) {
        i5Var.f.postValue(null);
        Xa xa = i5Var.c;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.xb
    public final void a(final Eb retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 retryAction2 = new Function0() { // from class: ch.datatrans.payment.i5$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return i5.a(i5.this, retryAction);
            }
        };
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.i5$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return i5.b(i5.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction2, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction2, cancelAction));
    }

    public static final Unit a(i5 i5Var, Function1 function1) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(i5Var), null, null, new C0134e5(function1, null), 3, null);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.xb
    public final void a(I9 paymentMethodWebProcess) {
        Intrinsics.checkNotNullParameter(paymentMethodWebProcess, "paymentMethodWebProcess");
        this.h.postValue(paymentMethodWebProcess);
    }

    @Override // ch.datatrans.payment.xb
    public final void a(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        String str = transactionModel.n;
        if (str == null) {
            str = "";
        }
        a(str);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:(2:3|(9:5|6|7|(1:(2:10|11)(2:24|25))(2:26|(3:(1:(3:30|(1:32)|33)(2:34|35))(1:36)|16|17)(7:37|(1:39)(1:50)|(4:41|42|(2:46|(1:48))|49)|23|(1:15)(1:19)|16|17))|12|13|(0)(0)|16|17))|7|(0)(0)|12|13|(0)(0)|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0113, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0114, code lost:
    
        r12.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(int r12, android.content.Intent r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.i5.a(int, android.content.Intent, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void a(String str) {
        String str2;
        String str3;
        C0137ee c0137ee = this.j;
        String str4 = "";
        if (c0137ee == null || (str2 = c0137ee.e) == null) {
            str2 = "";
        }
        if (c0137ee != null && (str3 = c0137ee.g) != null) {
            str4 = str3;
        }
        SavedCard savedCard = (c0137ee != null ? c0137ee.d : null) != null ? new SavedCard(c0137ee.d, str2, c0137ee.c, c0137ee.f, null) : null;
        Nb e = e();
        C0130de c0130de = this.i;
        e.a(new SavedGooglePay(str2, savedCard, str4, c0130de != null ? c0130de.c : null));
        e().n = str;
        this.b.postValue(Unit.INSTANCE);
    }

    public static void a(i5 i5Var, String str, Integer num, int i) {
        String str2;
        if ((i & 1) != 0) {
            str = "Google Pay Error";
        }
        if ((i & 2) != 0) {
            num = null;
        }
        i5Var.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (num != null) {
            str2 = ", error code: " + num.intValue();
        } else {
            str2 = null;
        }
        sb.append(str2);
        i5Var.a(new TechnicalException(sb.toString(), null, PaymentMethodType.GOOGLE_PAY, i5Var.e().n));
    }

    public final void a(final Exception exc) {
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.i5$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return i5.a(i5.this, exc);
            }
        };
        if (e().l != null) {
            MutableLiveData mutableLiveData = this.f;
            C7 c7 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed, dismissAction));
            return;
        }
        MutableLiveData mutableLiveData2 = this.f;
        C7 c72 = r4.a;
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData2.postValue(new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction));
    }
}
