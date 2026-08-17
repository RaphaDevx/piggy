package com.salesforce.marketingcloud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class MCReceiver extends BroadcastReceiver {
    public static final String a = "com.salesforce.marketingcloud.WAKE_FOR_ALARM";
    private static final String b = "alarmName";
    private static final String c = g.a("MCReceiver");

    public static Intent a(Context context, String str) {
        return new Intent(context, (Class<?>) MCReceiver.class).setAction(context.getApplicationContext().getPackageName() + ".com.salesforce.marketingcloud.WAKE_FOR_ALARM").putExtra(b, str);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String replaceFirst;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            g.a(c, "Action was empty %s", intent.toString());
        }
        replaceFirst = action.replaceFirst(context.getApplicationContext().getPackageName() + ".", "");
        g.d(c, "onReceive with action: %s", replaceFirst);
        replaceFirst.hashCode();
        replaceFirst.hashCode();
        switch (replaceFirst) {
            case "android.intent.action.AIRPLANE_MODE":
            case "android.intent.action.TIMEZONE_CHANGED":
            case "android.intent.action.BOOT_COMPLETED":
            case "android.intent.action.MY_PACKAGE_REPLACED":
            case "android.intent.action.ACTION_SHUTDOWN":
                com.salesforce.marketingcloud.behaviors.a a2 = com.salesforce.marketingcloud.behaviors.a.a(replaceFirst);
                if (a2 != null) {
                    MCService.a(context, a2, intent.getExtras());
                    break;
                }
                break;
            case "com.salesforce.marketingcloud.WAKE_FOR_ALARM":
                MCService.a(context, intent.getStringExtra(b));
                break;
        }
    }
}
