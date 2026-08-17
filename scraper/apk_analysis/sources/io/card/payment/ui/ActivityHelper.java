package io.card.payment.ui;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import io.sentry.ProfileChunk;

/* loaded from: classes2.dex */
public class ActivityHelper {
    private static boolean actionBarSupported() {
        return true;
    }

    public static boolean holoSupported() {
        return true;
    }

    public static void addActionBarIfSupported(Activity activity) {
        if (actionBarSupported()) {
            activity.requestWindowFeature(8);
        }
    }

    public static void setupActionBarIfSupported(Activity activity, TextView textView, String str, String str2, Drawable drawable) {
        if (str2 == null) {
            str2 = "";
        }
        activity.setTitle(str2 + str);
        if (!actionBarSupported() || !actionBarNonNull(activity)) {
            if (textView != null) {
                textView.setText(str);
            }
        } else {
            setupActionBar(activity, str, drawable);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
    }

    private static boolean actionBarNonNull(Activity activity) {
        return activity.getActionBar() != null;
    }

    private static void setupActionBar(Activity activity, String str, Drawable drawable) {
        ActionBar actionBar = activity.getActionBar();
        actionBar.setBackgroundDrawable(Appearance.ACTIONBAR_BACKGROUND);
        actionBar.setTitle(str);
        TextView textView = (TextView) activity.findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", ProfileChunk.PLATFORM_ANDROID));
        if (textView != null) {
            textView.setTextColor(-1);
        }
        actionBar.setDisplayHomeAsUpEnabled(false);
        if (drawable != null) {
            setActionBarHomeIcon(actionBar, drawable);
        } else {
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }

    private static void setActionBarHomeIcon(ActionBar actionBar, Drawable drawable) {
        actionBar.setIcon(drawable);
    }

    public static void setActivityTheme(Activity activity, boolean z) {
        if (z && activity.getApplicationInfo().theme != 0) {
            activity.setTheme(activity.getApplicationInfo().theme);
        } else if (holoSupported()) {
            activity.setTheme(R.style.Theme.Holo.Light);
        } else {
            activity.setTheme(R.style.Theme.Light);
        }
    }

    public static void setFlagSecure(Activity activity) {
        activity.getWindow().addFlags(8192);
    }
}
