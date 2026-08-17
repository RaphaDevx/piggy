package ch.datatrans.payment;

import android.content.Context;
import android.content.IntentSender;
import android.util.Log;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentRequest;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentResponse;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class E3 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ F3 b;
    public final /* synthetic */ PaymentCardRecognitionIntentRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E3(F3 f3, PaymentCardRecognitionIntentRequest paymentCardRecognitionIntentRequest, Continuation continuation) {
        super(2, continuation);
        this.b = f3;
        this.c = paymentCardRecognitionIntentRequest;
    }

    public static final void a(F3 f3) {
        f3.a(false);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new E3(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new E3(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object hasGooglePay;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            GooglePayConfig.Companion companion = GooglePayConfig.INSTANCE;
            Context requireContext = this.b.b.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            List listOf = CollectionsKt.listOf((Object[]) new PaymentMethodType[]{PaymentMethodType.VISA, PaymentMethodType.MASTER_CARD});
            List listOf2 = CollectionsKt.listOf((Object[]) new GooglePayConfig.AuthenticationMethodType[]{GooglePayConfig.AuthenticationMethodType.PAN_ONLY, GooglePayConfig.AuthenticationMethodType.CRYPTOGRAM_3DS});
            boolean z = this.b.c;
            this.a = 1;
            hasGooglePay = companion.hasGooglePay(requireContext, listOf, (r19 & 4) != 0 ? GooglePayConfig.l : listOf2, (r19 & 8) != 0 ? false : z, (r19 & 16) != 0, (r19 & 32) != 0, (r19 & 64) != 0 ? false : false, this);
            if (hasGooglePay == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            hasGooglePay = obj;
        }
        if (((Boolean) hasGooglePay).booleanValue()) {
            Context context = this.b.b.requireContext();
            Intrinsics.checkNotNullExpressionValue(context, "requireContext(...)");
            boolean z2 = this.b.c;
            Intrinsics.checkNotNullParameter(context, "context");
            PaymentsClient paymentsClient = Wallet.getPaymentsClient(context, new Wallet.WalletOptions.Builder().setEnvironment(z2 ? 3 : 1).build());
            Intrinsics.checkNotNullExpressionValue(paymentsClient, "getPaymentsClient(...)");
            Task<PaymentCardRecognitionIntentResponse> paymentCardRecognitionIntent = paymentsClient.getPaymentCardRecognitionIntent(this.c);
            final F3 f3 = this.b;
            final Function1 function1 = new Function1() { // from class: ch.datatrans.payment.E3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return E3.a(F3.this, (PaymentCardRecognitionIntentResponse) obj2);
                }
            };
            Task<PaymentCardRecognitionIntentResponse> addOnSuccessListener = paymentCardRecognitionIntent.addOnSuccessListener(new OnSuccessListener() { // from class: ch.datatrans.payment.E3$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj2) {
                    E3.a(Function1.this, obj2);
                }
            });
            final F3 f32 = this.b;
            Task<PaymentCardRecognitionIntentResponse> addOnFailureListener = addOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: ch.datatrans.payment.E3$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    E3.a(F3.this, exc);
                }
            });
            final F3 f33 = this.b;
            addOnFailureListener.addOnCanceledListener(new OnCanceledListener() { // from class: ch.datatrans.payment.E3$$ExternalSyntheticLambda3
                @Override // com.google.android.gms.tasks.OnCanceledListener
                public final void onCanceled() {
                    E3.a(F3.this);
                }
            });
        } else {
            this.b.a(false);
        }
        return Unit.INSTANCE;
    }

    public static final void a(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    public static final Unit a(F3 f3, PaymentCardRecognitionIntentResponse paymentCardRecognitionIntentResponse) {
        IntentSender intentSender = paymentCardRecognitionIntentResponse.getPaymentCardRecognitionPendingIntent().getIntentSender();
        Intrinsics.checkNotNullExpressionValue(intentSender, "getIntentSender(...)");
        f3.f = intentSender;
        f3.a(true);
        return Unit.INSTANCE;
    }

    public static final void a(F3 f3, Exception exc) {
        Intrinsics.checkNotNull(exc);
        Log.e("DTPL", "Payment card ocr not available.", exc);
        f3.a(false);
    }
}
