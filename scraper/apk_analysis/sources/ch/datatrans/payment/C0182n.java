package ch.datatrans.payment;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.AuthorizationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.TransactionListenerOnSuccessException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.LinkedHashMap;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: ch.datatrans.payment.n, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0182n extends AndroidViewModel {
    public static final /* synthetic */ int f = 0;
    public final MutableLiveData a;
    public final Xa b;
    public final Xa c;
    public final MutableLiveData d;
    public final Xa e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0182n(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.a = new MutableLiveData();
        this.b = new Xa();
        this.c = new Xa();
        this.d = new MutableLiveData();
        this.e = new Xa();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x01ca, code lost:
    
        if (r3 == ch.datatrans.payment.paymentmethods.PaymentMethodType.BONCARD) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f4, code lost:
    
        if (r2 == r4) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x011b, code lost:
    
        if (r2 == r4) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0174, code lost:
    
        if (r2 == r4) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0197, code lost:
    
        if (r2 == r4) goto L66;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Enum, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.C0182n r25, ch.datatrans.payment.Nb r26, kotlin.coroutines.jvm.internal.ContinuationImpl r27) {
        /*
            Method dump skipped, instructions count: 527
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0182n.a(ch.datatrans.payment.n, ch.datatrans.payment.Nb, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final Unit b(C0182n c0182n, Nb nb) {
        c0182n.a.postValue(null);
        c0182n.a(nb);
        return Unit.INSTANCE;
    }

    public final void c(final Nb nb) {
        MutableLiveData mutableLiveData = this.a;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.n$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0182n.b(C0182n.this, nb);
            }
        };
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, dismissAction));
    }

    public final void b(final Nb nb) {
        MutableLiveData mutableLiveData = this.a;
        C7 c7 = r4.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.n$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0182n.a(C0182n.this, nb);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        mutableLiveData.postValue(new C0228u(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction));
    }

    public static final Unit b(C0182n c0182n, AuthorizationException authorizationException) {
        c0182n.a.postValue(null);
        c0182n.b.postValue(authorizationException);
        return Unit.INSTANCE;
    }

    public final void a(Nb model) {
        Intrinsics.checkNotNullParameter(model, "model");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C0151h(this, model, null), 3, null);
    }

    public static Object a(Nb nb, C0157i c0157i) {
        Y8 y8;
        W8 w8 = nb.k;
        if (w8 != null) {
            y8 = Z8.a(w8, null);
        } else {
            C0131e c0131e = nb.d;
            Intrinsics.checkNotNull(c0131e);
            Intrinsics.checkNotNullParameter(c0131e, "<this>");
            y8 = new Y8(0, c0131e.a, c0131e.b, c0131e.c, c0131e.d, c0131e.e, null);
        }
        Q5 q5 = nb.h;
        String str = nb.n;
        Intrinsics.checkNotNull(str);
        String str2 = y8.a;
        String str3 = y8.d;
        String str4 = y8.e;
        String str5 = y8.f;
        X9 x9 = q5.n;
        LinkedHashMap linkedHashMap = q5.k;
        String str6 = nb.p;
        boolean z = nb.i == PaymentMethodType.IDEAL;
        X8 x8 = new X8(str, str2, str3, str4, str5, x9, linkedHashMap, str6, z);
        C7 a = r4.a();
        return a.a(a.b.h(), Lf.a(MapsKt.toMutableMap(a.c.b()), x8), z, c0157i);
    }

    public static final Unit a(C0182n c0182n, Nb nb) {
        c0182n.a.postValue(null);
        c0182n.a(nb);
        return Unit.INSTANCE;
    }

    public final void a(Exception exc, boolean z, PaymentMethodType paymentMethodType, String str) {
        String transactionId;
        if (!(exc instanceof TransactionListenerOnSuccessException)) {
            BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
            if (backendException != null && (transactionId = backendException.getTransactionId()) != null) {
                str = transactionId;
            }
            final AuthorizationException authorizationException = new AuthorizationException(exc, paymentMethodType, str);
            if (z) {
                MutableLiveData mutableLiveData = this.a;
                C7 c7 = r4.a;
                Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.n$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return C0182n.a(C0182n.this, authorizationException);
                    }
                };
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction));
                return;
            }
            MutableLiveData mutableLiveData2 = this.a;
            C7 c72 = r4.a;
            Function0 dismissAction2 = new Function0() { // from class: ch.datatrans.payment.n$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0182n.b(C0182n.this, authorizationException);
                }
            };
            Intrinsics.checkNotNullParameter(dismissAction2, "dismissAction");
            mutableLiveData2.postValue(new C0178m4(R.string.datatrans_sdk_error_title_payment, R.string.datatrans_sdk_error_message_authorize_failed, dismissAction2));
            return;
        }
        throw ((TransactionListenerOnSuccessException) exc).getCause();
    }

    public static final Unit a(C0182n c0182n, AuthorizationException authorizationException) {
        c0182n.a.postValue(null);
        c0182n.b.postValue(authorizationException);
        return Unit.INSTANCE;
    }
}
