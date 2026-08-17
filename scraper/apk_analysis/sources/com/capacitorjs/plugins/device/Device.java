package com.capacitorjs.plugins.device;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.webkit.WebView;
import io.sentry.ProfileChunk;
import lib.android.paypal.com.magnessdk.c;
import net.openid.appauth.browser.Browsers;

/* loaded from: classes3.dex */
public class Device {
    private Context context;

    Device(Context context) {
        this.context = context;
    }

    public long getMemUsed() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public String getPlatform() {
        return ProfileChunk.PLATFORM_ANDROID;
    }

    public String getUuid() {
        return Settings.Secure.getString(this.context.getContentResolver(), c.f);
    }

    public float getBatteryLevel() {
        int i;
        Intent registerReceiver = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int i2 = -1;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("level", -1);
            i = registerReceiver.getIntExtra("scale", -1);
            i2 = intExtra;
        } else {
            i = -1;
        }
        return i2 / i;
    }

    public boolean isCharging() {
        Intent registerReceiver = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return false;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    public boolean isVirtual() {
        return Build.FINGERPRINT.contains("generic") || Build.PRODUCT.contains("sdk");
    }

    public String getName() {
        return Settings.Global.getString(this.context.getContentResolver(), "device_name");
    }

    public String getWebViewVersion() {
        PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
        if (currentWebViewPackage != null) {
            return currentWebViewPackage.versionName;
        }
        return Build.VERSION.RELEASE;
    }

    private PackageInfo getWebViewVersionSubAndroid26() throws PackageManager.NameNotFoundException {
        return this.context.getPackageManager().getPackageInfo(Browsers.Chrome.PACKAGE_NAME, 0);
    }
}
