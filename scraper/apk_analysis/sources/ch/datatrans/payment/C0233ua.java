package ch.datatrans.payment;

import android.os.Bundle;
import androidx.lifecycle.ViewModelKt;
import com.samsung.android.sdk.samsungpay.v2.payment.CardInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountConstants;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: ch.datatrans.payment.ua, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0233ua implements PaymentManager.CustomSheetTransactionInfoListener {
    public final /* synthetic */ Ba a;
    public final /* synthetic */ PaymentManager b;

    public C0233ua(Ba ba, PaymentManager paymentManager) {
        this.a = ba;
        this.b = paymentManager;
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.CustomSheetTransactionInfoListener
    public final void onCardInfoUpdated(CardInfo cardInfo, CustomSheet customSheet) {
        if (customSheet == null) {
            Ka ka = (Ka) this.a.c.getValue();
            ka.getClass();
            Ka.a(ka, null, null, 3);
        } else {
            SheetControl sheetControl = customSheet.getSheetControl("amount_control");
            Intrinsics.checkNotNull(sheetControl, "null cannot be cast to non-null type com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl");
            AmountBoxControl amountBoxControl = (AmountBoxControl) sheetControl;
            amountBoxControl.setAmountTotal(((Number) ((Ka) this.a.c.getValue()).g.getValue()).doubleValue(), AmountConstants.FORMAT_TOTAL_PRICE_ONLY);
            customSheet.updateControl(amountBoxControl);
            this.b.updateSheet(customSheet);
        }
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.CustomSheetTransactionInfoListener
    public final void onFailure(int i, Bundle bundle) {
        Ka ka = (Ka) this.a.c.getValue();
        if (i == -7) {
            ka.d.postValue(Unit.INSTANCE);
        } else {
            ka.getClass();
            Ka.a(ka, null, Integer.valueOf(i), 1);
        }
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.CustomSheetTransactionInfoListener
    public final void onSuccess(CustomSheetPaymentInfo customSheetPaymentInfo, String walletToken, Bundle bundle) {
        if (walletToken == null) {
            Ka ka = (Ka) this.a.c.getValue();
            ka.getClass();
            Ka.a(ka, null, null, 3);
        } else {
            Ka ka2 = (Ka) this.a.c.getValue();
            ka2.getClass();
            Intrinsics.checkNotNullParameter(walletToken, "walletToken");
            ka2.h = walletToken;
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(ka2), null, null, new Ia(ka2, null), 3, null);
        }
    }
}
