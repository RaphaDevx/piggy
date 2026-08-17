package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Bundle;

/* loaded from: classes2.dex */
public interface AddCardListener {
    void onFail(int i, Bundle bundle);

    void onProgress(int i, int i2, Bundle bundle);

    void onSuccess(int i, Card card);
}
