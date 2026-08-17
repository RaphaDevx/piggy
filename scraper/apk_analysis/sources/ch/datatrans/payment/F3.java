package ch.datatrans.payment;

import android.content.Intent;
import android.content.IntentSender;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.wallet.CreditCardExpirationDate;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentRequest;
import com.google.android.gms.wallet.PaymentCardRecognitionResult;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class F3 {
    public final CoroutineScope a;
    public final r3 b;
    public final boolean c;
    public boolean d;
    public final boolean e;
    public IntentSender f;
    public final MutableLiveData g;
    public final ActivityResultLauncher h;
    public final ActivityResultLauncher i;

    public F3(CoroutineScope scope, r3 listener, boolean z) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = scope;
        this.b = listener;
        this.c = z;
        Lazy lazy = M7.b;
        this.e = ((Boolean) lazy.getValue()).booleanValue();
        MutableLiveData mutableLiveData = new MutableLiveData();
        this.g = mutableLiveData;
        ActivityResultLauncher registerForActivityResult = listener.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.F3$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                F3.a(F3.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.h = registerForActivityResult;
        ActivityResultLauncher registerForActivityResult2 = listener.registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.F3$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                F3.b(F3.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.i = registerForActivityResult2;
        Boolean bool = (Boolean) lazy.getValue();
        bool.getClass();
        mutableLiveData.postValue(bool);
        if (!((Boolean) M7.e.getValue()).booleanValue()) {
            a(false);
            return;
        }
        PaymentCardRecognitionIntentRequest defaultInstance = PaymentCardRecognitionIntentRequest.getDefaultInstance();
        Intrinsics.checkNotNullExpressionValue(defaultInstance, "getDefaultInstance(...)");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new E3(this, defaultInstance, null), 3, null);
    }

    public static final void a(F3 f3, ActivityResult activityResult) {
        Intent data = activityResult.getData();
        CreditCard creditCard = data != null ? (CreditCard) data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT) : null;
        CreditCard creditCard2 = creditCard instanceof CreditCard ? creditCard : null;
        if (creditCard2 != null) {
            r3 r3Var = f3.b;
            Intrinsics.checkNotNullParameter(creditCard2, "creditCard");
            String cardNumber = creditCard2.cardNumber;
            Intrinsics.checkNotNullExpressionValue(cardNumber, "cardNumber");
            r3Var.a(cardNumber, true);
            if (creditCard2.isExpiryValid()) {
                r3Var.a(creditCard2.expiryMonth, creditCard2.expiryYear);
            }
        }
    }

    public static final void b(F3 f3, ActivityResult activityResult) {
        PaymentCardRecognitionResult creditCard;
        if (activityResult.getResultCode() != -1) {
            f3.getClass();
            if (!((Boolean) M7.e.getValue()).booleanValue()) {
                f3.a(false);
                return;
            }
            PaymentCardRecognitionIntentRequest defaultInstance = PaymentCardRecognitionIntentRequest.getDefaultInstance();
            Intrinsics.checkNotNullExpressionValue(defaultInstance, "getDefaultInstance(...)");
            BuildersKt__Builders_commonKt.launch$default(f3.a, null, null, new E3(f3, defaultInstance, null), 3, null);
            return;
        }
        Intent data = activityResult.getData();
        if (data == null || (creditCard = PaymentCardRecognitionResult.getFromIntent(data)) == null) {
            return;
        }
        r3 r3Var = f3.b;
        Intrinsics.checkNotNullParameter(creditCard, "creditCard");
        String pan = creditCard.getPan();
        Intrinsics.checkNotNullExpressionValue(pan, "getPan(...)");
        r3Var.a(pan, true);
        CreditCardExpirationDate creditCardExpirationDate = creditCard.getCreditCardExpirationDate();
        if (creditCardExpirationDate == null || creditCardExpirationDate.getMonth() < 1 || 12 < creditCardExpirationDate.getMonth()) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        if (creditCardExpirationDate.getYear() < i) {
            return;
        }
        if ((creditCardExpirationDate.getYear() != i || creditCardExpirationDate.getMonth() >= i2) && creditCardExpirationDate.getYear() >= i) {
            r3Var.a(creditCardExpirationDate.getMonth(), creditCardExpirationDate.getYear());
        }
    }

    public final void a(boolean z) {
        this.d = z;
        this.g.postValue(Boolean.valueOf(z || ((Boolean) M7.b.getValue()).booleanValue()));
    }
}
