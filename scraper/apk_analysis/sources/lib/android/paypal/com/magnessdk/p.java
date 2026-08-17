package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class p {
    private p() {
    }

    public static boolean a(Context context, List<String> list) {
        if (context == null || list == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        Iterator<String> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                packageManager.getPackageInfo(it.next(), 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public static boolean a(PackageManager packageManager, Intent intent) {
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }
}
