package com.salesforce.marketingcloud.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.NotificationOpenedService;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.j;
import com.salesforce.marketingcloud.internal.k;
import com.salesforce.marketingcloud.push.f;

/* loaded from: classes2.dex */
public abstract class NotificationManager {
    public static final String ACTION_NOTIFICATION_CLICKED = "com.salesforce.marketingcloud.NOTIFICATION_CLICKED";
    public static final String DEFAULT_CHANNEL_ID = "com.salesforce.marketingcloud.DEFAULT_CHANNEL";
    public static final String DEFAULT_FOREGROUND_CHANNEL_ID = "com.salesforce.marketingcloud.DEFAULT_FOREGROUND_CHANNEL";
    static final String d = g.a("NotificationManager");
    private static final String e = "com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE";

    public interface NotificationBuilder {
        NotificationCompat.Builder setupNotificationBuilder(Context context, NotificationMessage notificationMessage) throws f;
    }

    public interface NotificationChannelIdProvider {
        String getNotificationChannelId(Context context, NotificationMessage notificationMessage);
    }

    public interface NotificationLaunchIntentProvider {
        PendingIntent getNotificationPendingIntent(Context context, NotificationMessage notificationMessage);
    }

    public interface NotificationMessageDisplayedListener {
        void onNotificationMessageDisplayed(NotificationMessage notificationMessage);
    }

    public interface ShouldShowNotificationListener {
        boolean shouldShowNotification(NotificationMessage notificationMessage);
    }

    static Intent a(Intent intent, NotificationMessage notificationMessage) {
        return intent.putExtra(e, k.a(notificationMessage));
    }

    public static void cancelNotificationMessage(Context context, NotificationMessage notificationMessage) {
        if (notificationMessage.notificationId() >= 0) {
            ((android.app.NotificationManager) context.getSystemService("notification")).cancel("com.marketingcloud.salesforce.notifications.TAG", notificationMessage.notificationId());
        }
    }

    public static String createDefaultNotificationChannel(Context context, boolean z) {
        return b.b(context, z);
    }

    public static String createForegroundNotificationChannel(Context context) {
        return b.a(context, false);
    }

    public static NotificationMessage extractMessage(Intent intent) {
        try {
            return (NotificationMessage) k.a(intent.getByteArrayExtra(e), NotificationMessage.CREATOR);
        } catch (Exception e2) {
            g.b(d, e2, "Unable to retrieve NotificationMessage from Intent (%s).", intent);
            return null;
        }
    }

    public static NotificationCompat.Builder getDefaultNotificationBuilder(Context context, NotificationMessage notificationMessage, String str, int i) {
        return b.a(context, notificationMessage, str, i);
    }

    public static PendingIntent redirectIntentForAnalytics(Context context, PendingIntent pendingIntent, RemoteMessage remoteMessage, boolean z) {
        try {
            return redirectIntentForAnalytics(context, pendingIntent, j.a(remoteMessage.getData()), z, null);
        } catch (Exception e2) {
            g.b(d, e2, "Failed to create {NotificationMessage} from {RemoteMessage}, not processing {PendingIntent} for analytics.", new Object[0]);
            return null;
        }
    }

    public abstract boolean areNotificationsEnabled();

    public abstract void disableNotifications();

    public abstract void enableNotifications();

    public abstract void registerNotificationMessageDisplayedListener(NotificationMessageDisplayedListener notificationMessageDisplayedListener);

    public abstract void setShouldShowNotificationListener(ShouldShowNotificationListener shouldShowNotificationListener);

    public abstract void unregisterNotificationMessageDisplayedListener(NotificationMessageDisplayedListener notificationMessageDisplayedListener);

    public static String createDefaultNotificationChannel(Context context) {
        return b.b(context, false);
    }

    public static PendingIntent redirectIntentForAnalytics(Context context, PendingIntent pendingIntent, NotificationMessage notificationMessage, boolean z) {
        return redirectIntentForAnalytics(context, pendingIntent, notificationMessage, z, null);
    }

    public static PendingIntent redirectIntentForAnalytics(Context context, PendingIntent pendingIntent, NotificationMessage notificationMessage, boolean z, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putByteArray(e, k.a(notificationMessage));
        bundle.putParcelable("com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT", pendingIntent);
        bundle.putBoolean("com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL", z);
        Uri fromParts = Uri.fromParts("mcsdk", "pushOpen", String.valueOf(System.currentTimeMillis()));
        int a = com.salesforce.marketingcloud.util.j.a(BasicMeasure.EXACTLY);
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getActivity(context, 0, NotificationOpenActivity.a(context, bundle).setData(fromParts), a);
        }
        return PendingIntent.getService(context, 0, NotificationOpenedService.b(context, bundle).setData(fromParts), a);
    }
}
