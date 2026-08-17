package ch.datatrans.payment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Insets;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.activity.ComponentActivity;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class N9 {
    public final boolean a;
    public final boolean b;

    public N9(ComponentActivity activity, boolean z) {
        boolean a;
        int i;
        int i2;
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        int systemBars;
        Insets insetsIgnoringVisibility;
        Rect bounds;
        Rect bounds2;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (z) {
            a = a(activity, "https://universal-test.postfinance.ch/epayment/", "ch.postfinance.android.t1") || a(activity, "https://universal-t2.postfinance.ch/epayment/", "ch.postfinance.android.t2");
        } else {
            a = a(activity, "https://universal.postfinance.ch/epayment/", "ch.postfinance.android");
        }
        this.a = a;
        if (Build.VERSION.SDK_INT >= 30) {
            currentWindowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(currentWindowMetrics, "getCurrentWindowMetrics(...)");
            windowInsets = currentWindowMetrics.getWindowInsets();
            systemBars = WindowInsets.Type.systemBars();
            insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(systemBars);
            Intrinsics.checkNotNullExpressionValue(insetsIgnoringVisibility, "getInsetsIgnoringVisibility(...)");
            bounds = currentWindowMetrics.getBounds();
            i = (bounds.width() - insetsIgnoringVisibility.left) - insetsIgnoringVisibility.right;
            bounds2 = currentWindowMetrics.getBounds();
            i2 = (bounds2.height() - insetsIgnoringVisibility.top) - insetsIgnoringVisibility.bottom;
        } else {
            WindowManager windowManager = activity.getWindowManager();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.densityDpi;
            int i5 = displayMetrics.heightPixels / i4;
            i = i3 / i4;
            i2 = i5;
        }
        this.b = Math.sqrt((double) ((i2 * i2) + (i * i))) <= 7.0d;
    }

    public static boolean a(Activity activity, String str, String str2) {
        List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
        if ((queryIntentActivities instanceof Collection) && queryIntentActivities.isEmpty()) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (Intrinsics.areEqual(resolveInfo.activityInfo.packageName, str2) || Intrinsics.areEqual(resolveInfo.activityInfo.packageName, "ch.postfinance.android")) {
                return true;
            }
        }
        return false;
    }
}
