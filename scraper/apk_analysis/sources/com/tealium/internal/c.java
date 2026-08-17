package com.tealium.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.listeners.DispatchSendListener;
import com.tealium.internal.listeners.PublishSettingsUpdateListener;
import com.tealium.library.BuildConfig;
import com.tealium.library.DataSources;
import com.tealium.library.R;
import java.util.Locale;

/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public final class c implements PublishSettingsUpdateListener, DispatchSendListener {
    private final Context a;
    private volatile int b;

    public c(Context context, String str) {
        this.a = context.getApplicationContext();
        this.b = b(str);
    }

    public void a(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        switch (lowerCase) {
            case "qa":
                this.b = 4;
                break;
            case "dev":
                this.b = 2;
                break;
            case "prod":
                this.b = 7;
                break;
            default:
                this.b = Integer.MAX_VALUE;
                break;
        }
    }

    public void b(int i, Object... objArr) {
        if (b()) {
            Log.e(BuildConfig.TAG, this.a.getString(i, objArr));
        }
    }

    public void c(int i, Object... objArr) {
        if (c()) {
            Log.i(BuildConfig.TAG, this.a.getString(i, objArr));
        }
    }

    public void d(int i, Object... objArr) {
        if (this.b == 2) {
            Log.v(BuildConfig.TAG, this.a.getString(i, objArr));
        }
    }

    public void e(int i, Object... objArr) {
        if (e()) {
            Log.w(BuildConfig.TAG, this.a.getString(i, objArr));
        }
    }

    @Override // com.tealium.internal.listeners.DispatchSendListener
    public void onDispatchSend(Dispatch dispatch) {
        if (a()) {
            Log.d(BuildConfig.TAG, this.a.getString(R.string.logger_dispatch_send, "true".equals(dispatch.getString(DataSources.Key.WAS_QUEUED)) ? "queued" : "new", dispatch.toSortedJsonString()));
        }
    }

    @Override // com.tealium.internal.listeners.PublishSettingsUpdateListener
    public void onPublishSettingsUpdate(PublishSettings publishSettings) {
        if (!TextUtils.isEmpty(publishSettings.getOverrideLog())) {
            this.b = b(publishSettings.getOverrideLog());
        }
        c(R.string.logger_rcvd_publish_settings, publishSettings);
    }

    public void b(Throwable th) {
        if (this.b <= 7) {
            Log.wtf(BuildConfig.TAG, th);
        }
    }

    public boolean c() {
        return this.b <= 4;
    }

    public boolean d() {
        return this.b == 2;
    }

    public boolean e() {
        return this.b <= 5;
    }

    public boolean b() {
        return this.b <= 6;
    }

    private static int b(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        switch (lowerCase) {
            case "qa":
                return 4;
            case "dev":
                return 2;
            case "prod":
                return 7;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public void a(int i, Object... objArr) {
        if (a()) {
            Log.d(BuildConfig.TAG, this.a.getString(i, objArr));
        }
    }

    public void a(int i) {
        if (b()) {
            Log.e(BuildConfig.TAG, this.a.getString(i));
        }
    }

    public void a(int i, Throwable th, Object... objArr) {
        if (b()) {
            Log.e(BuildConfig.TAG, this.a.getString(i, objArr), th);
        }
    }

    public void a(Throwable th) {
        a(R.string.logger_error_caught_exception, th, new Object[0]);
    }

    public void a(int i, Throwable th) {
        if (this.b <= 7) {
            Log.wtf(BuildConfig.TAG, this.a.getString(i), th);
        }
    }

    public boolean a() {
        return this.b <= 3;
    }
}
