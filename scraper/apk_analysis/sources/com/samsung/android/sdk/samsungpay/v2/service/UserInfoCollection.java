package com.samsung.android.sdk.samsungpay.v2.service;

import android.os.Bundle;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.service.Address;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Deprecated
/* loaded from: classes2.dex */
public class UserInfoCollection {
    private static final String TAG = "UserInfoCollection";
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy/dd/MM");
    private RequestType[] mAvailableTypes;
    private Bundle mUserInfoBundle;

    public UserInfoCollection(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.clone();
        this.mUserInfoBundle = bundle2;
        this.mAvailableTypes = new RequestType[bundle2.keySet().size()];
        Iterator<String> it = this.mUserInfoBundle.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            this.mAvailableTypes[i] = RequestType.valueOf(it.next());
            i++;
        }
    }

    public final RequestType[] getAvailableTypes() {
        return this.mAvailableTypes;
    }

    public String getString(RequestType requestType) {
        return this.mUserInfoBundle.getString(requestType.toString());
    }

    public Address getAddress(RequestType requestType) {
        try {
            return new Address.Builder(this.mUserInfoBundle.getBundle(requestType.toString())).build();
        } catch (Exception e) {
            Log.e(TAG, "getAddress - error: " + e.toString());
            return null;
        }
    }

    public Date getDate(RequestType requestType) {
        try {
            return sDateFormatter.parse(this.mUserInfoBundle.getString(requestType.toString()));
        } catch (Exception e) {
            Log.e(TAG, "getDate - error: " + e.toString());
            return null;
        }
    }
}
