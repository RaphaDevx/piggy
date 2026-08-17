package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.AuthorizationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.TransactionListenerOnSuccessException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* renamed from: ch.datatrans.payment.td, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0224td extends ViewModel {
    public static final /* synthetic */ int f = 0;
    public final MutableLiveData a = new MutableLiveData();
    public final Xa b = new Xa();
    public final Xa c = new Xa();
    public final Xa d = new Xa();
    public final MutableLiveData e = new MutableLiveData();

    /* JADX WARN: Removed duplicated region for block: B:12:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.C0224td r7, ch.datatrans.payment.Nb r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            boolean r0 = r9 instanceof ch.datatrans.payment.od
            if (r0 == 0) goto L13
            r0 = r9
            ch.datatrans.payment.od r0 = (ch.datatrans.payment.od) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            ch.datatrans.payment.od r0 = new ch.datatrans.payment.od
            r0.<init>(r7, r9)
        L18:
            r6 = r0
            java.lang.Object r9 = r6.b
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.d
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r8 = r6.a
            ch.datatrans.payment.Nb r8 = (ch.datatrans.payment.Nb) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L63
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            ch.datatrans.payment.C7 r1 = ch.datatrans.payment.r4.a()
            ch.datatrans.payment.W8 r9 = r8.k
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            ch.datatrans.payment.paymentmethods.PaymentMethodType r3 = r8.i
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            ch.datatrans.payment.Q5 r4 = r8.h
            ch.datatrans.payment.paymentmethods.SavedPaymentMethod r5 = r8.l
            if (r5 == 0) goto L52
            java.lang.String r5 = r5.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String()
            goto L53
        L52:
            r5 = 0
        L53:
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r6.a = r8
            r6.d = r2
            r2 = r9
            java.lang.Object r9 = r1.a(r2, r3, r4, r5, r6)
            if (r9 != r0) goto L63
            return r0
        L63:
            ch.datatrans.payment.nd r9 = (ch.datatrans.payment.C0188nd) r9
            boolean r8 = r9.b
            if (r8 == 0) goto L71
            ch.datatrans.payment.Xa r8 = r7.c
            java.lang.String r9 = r9.a
            r8.postValue(r9)
            goto L78
        L71:
            ch.datatrans.payment.Xa r8 = r7.d
            java.lang.String r9 = r9.a
            r8.postValue(r9)
        L78:
            androidx.lifecycle.MutableLiveData r7 = r7.e
            r8 = 0
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            r7.postValue(r8)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0224td.a(ch.datatrans.payment.td, ch.datatrans.payment.Nb, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void b(final Nb nb) {
        MutableLiveData mutableLiveData = this.a;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.td$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0224td.b(C0224td.this, nb);
            }
        };
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, dismissAction));
    }

    public static final Unit b(C0224td c0224td, Nb model) {
        c0224td.a.postValue(null);
        Intrinsics.checkNotNullParameter(model, "model");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0224td), Dispatchers.getIO(), null, new pd(c0224td, model, null), 2, null);
        return Unit.INSTANCE;
    }

    public final void a(final Nb nb) {
        MutableLiveData mutableLiveData = this.a;
        C7 c7 = r4.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.td$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0224td.a(C0224td.this, nb);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        mutableLiveData.postValue(new C0228u(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction));
    }

    public static final Unit a(C0224td c0224td, Nb model) {
        c0224td.a.postValue(null);
        Intrinsics.checkNotNullParameter(model, "model");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0224td), Dispatchers.getIO(), null, new pd(c0224td, model, null), 2, null);
        return Unit.INSTANCE;
    }

    public final void a(Exception exc, PaymentMethodType paymentMethodType, String str) {
        String transactionId;
        if (!(exc instanceof TransactionListenerOnSuccessException)) {
            BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
            if (backendException != null && (transactionId = backendException.getTransactionId()) != null) {
                str = transactionId;
            }
            final AuthorizationException authorizationException = new AuthorizationException(exc, paymentMethodType, str);
            MutableLiveData mutableLiveData = this.a;
            C7 c7 = r4.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.td$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0224td.a(C0224td.this, authorizationException);
                }
            };
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_payment, R.string.datatrans_sdk_error_message_authorize_failed, dismissAction));
            return;
        }
        throw ((TransactionListenerOnSuccessException) exc).getCause();
    }

    public static final Unit a(C0224td c0224td, AuthorizationException authorizationException) {
        c0224td.a.postValue(null);
        c0224td.b.postValue(authorizationException);
        return Unit.INSTANCE;
    }
}
