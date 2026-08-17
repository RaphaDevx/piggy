package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;

/* loaded from: classes2.dex */
public interface TransactionResultListener {
    void onFail(int i, Bundle bundle);

    void onSuccess(Bundle bundle);
}
