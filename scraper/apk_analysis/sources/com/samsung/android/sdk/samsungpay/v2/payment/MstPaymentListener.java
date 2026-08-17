package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;

/* loaded from: classes2.dex */
public interface MstPaymentListener {
    void onFail(int i, Bundle bundle);

    void onSuccess(SpayResponseInfo spayResponseInfo);
}
