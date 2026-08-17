package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.datatrans.payment.api.TransactionOptions;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountConstants;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes3.dex */
public final class Ka extends ViewModel {
    public static final /* synthetic */ int i = 0;
    public final Nb a;
    public final Xa b;
    public final Xa c;
    public final Xa d;
    public final Xa e;
    public final MutableLiveData f;
    public final Lazy g;
    public String h;

    public Ka(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
        Xa xa = new Xa();
        this.b = xa;
        Xa xa2 = new Xa();
        this.c = xa2;
        this.d = new Xa();
        this.e = new Xa();
        this.f = new MutableLiveData();
        Lazy lazy = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.Ka$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Double.valueOf(Ka.a(Ka.this));
            }
        });
        this.g = lazy;
        SamsungPayConfig samsungPayConfig = transactionModel.h.o;
        if (samsungPayConfig == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(TransactionOptions.ERROR_MISSING_OPTION, Arrays.copyOf(new Object[]{"Samsung Pay"}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            a(this, format, null, 2);
            return;
        }
        if (transactionModel.d != null) {
            transactionModel.a(new SavedPaymentMethod(PaymentMethodType.SAMSUNG_PAY, ""));
            transactionModel.n = "";
            xa2.postValue(Unit.INSTANCE);
            return;
        }
        W8 w8 = transactionModel.k;
        Intrinsics.checkNotNull(w8);
        CustomSheet customSheet = new CustomSheet();
        W8 w82 = transactionModel.k;
        Intrinsics.checkNotNull(w82);
        AmountBoxControl amountBoxControl = new AmountBoxControl("amount_control", w82.b);
        amountBoxControl.setAmountTotal(((Number) lazy.getValue()).doubleValue(), AmountConstants.FORMAT_TOTAL_PRICE_ONLY);
        customSheet.addControl(amountBoxControl);
        CustomSheetPaymentInfo build = new CustomSheetPaymentInfo.Builder().setMerchantName(samsungPayConfig.getMerchantName()).setMerchantId(w8.c).setOrderNumber(w8.d).setPaymentProtocol(CustomSheetPaymentInfo.PaymentProtocol.PROTOCOL_3DS).setAddressInPaymentSheet(CustomSheetPaymentInfo.AddressInPaymentSheet.DO_NOT_SHOW).setAllowedCardBrands(samsungPayConfig.getCardBrands$lib_release(samsungPayConfig.getSupportedNetworks$lib_release())).setCardHolderNameEnabled(true).setRecurringEnabled(false).setCustomSheet(customSheet).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        xa.postValue(build);
    }

    public static final double a(Ka ka) {
        Intrinsics.checkNotNull(ka.a.k);
        return r4.a * 0.01d;
    }

    public static final Unit c(Ka ka) {
        ka.f.postValue(null);
        Xa xa = ka.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void b() {
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Ga retryAction = new Ga(this);
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.Ka$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Ka.c(Ka.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, retryAction, cancelAction));
    }

    public static void a(Ka ka, String str, Integer num, int i2) {
        String str2;
        if ((i2 & 1) != 0) {
            str = "Samsung Pay Error";
        }
        if ((i2 & 2) != 0) {
            num = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (num != null) {
            str2 = ", error code: " + num.intValue();
        } else {
            str2 = null;
        }
        sb.append(str2);
        ka.a(new TechnicalException(sb.toString(), null, PaymentMethodType.SAMSUNG_PAY, ka.a.n));
    }

    public final void a() {
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Ea retryAction = new Ea(this);
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.Ka$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Ka.b(Ka.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public static final Unit b(Ka ka) {
        ka.f.postValue(null);
        Xa xa = ka.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void a(final Exception exc) {
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.Ka$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Ka.a(Ka.this, exc);
            }
        };
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed, dismissAction));
    }

    public static final Unit a(Ka ka, Exception exc) {
        String str;
        TransactionException authenticationException;
        ka.f.postValue(null);
        Xa xa = ka.e;
        if (exc instanceof TransactionException) {
            authenticationException = (TransactionException) exc;
        } else {
            BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
            if (backendException == null || (str = backendException.getTransactionId()) == null) {
                str = ka.a.n;
            }
            authenticationException = new AuthenticationException(exc, PaymentMethodType.SAMSUNG_PAY, str, null, 8, null);
        }
        xa.postValue(authenticationException);
        return Unit.INSTANCE;
    }
}
