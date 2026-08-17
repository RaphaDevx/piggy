package com.samsung.android.sdk.samsungpay.v2.service;

import android.os.Bundle;

@Deprecated
/* loaded from: classes2.dex */
public interface UserInfoListener {
    void onFail(int i, Bundle bundle);

    void onSuccess(UserInfoCollection userInfoCollection);
}
