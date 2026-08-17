package com.scandit.datacapture.core.internal.module.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class e {
    public final Context a;
    public final SharedPreferences b;

    public e(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        this.b = context.getSharedPreferences("com.scandit.device_id", 0);
    }

    public final String a() {
        String str = null;
        String string = this.b.getString(k.a.p, null);
        if (string != null) {
            return string;
        }
        String string2 = Settings.Secure.getString(this.a.getContentResolver(), lib.android.paypal.com.magnessdk.c.f);
        if (string2 != null && string2.length() >= 16) {
            str = string2;
        }
        if (str != null) {
            return str;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        String str2 = "bad1d000" + StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(k.a.p, str2);
        edit.apply();
        return str2;
    }
}
