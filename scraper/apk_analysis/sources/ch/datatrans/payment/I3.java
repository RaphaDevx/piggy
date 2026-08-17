package ch.datatrans.payment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.activity.ComponentActivity;
import androidx.browser.customtabs.CustomTabsService;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class I3 {
    public static ArrayList a(ComponentActivity componentActivity) {
        PackageManager packageManager = componentActivity.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.datatrans.ch"));
        intent.addCategory("android.intent.category.BROWSABLE");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(((ResolveInfo) obj).activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
