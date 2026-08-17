package com.salesforce.marketingcloud.notifications;

import android.app.PendingIntent;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.salesforce.marketingcloud.notifications.NotificationManager;

/* loaded from: classes2.dex */
public class c extends b {
    public c(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        super(i, null, null, notificationChannelIdProvider);
    }

    @Override // com.salesforce.marketingcloud.notifications.b, com.salesforce.marketingcloud.notifications.NotificationManager.NotificationBuilder
    public NotificationCompat.Builder setupNotificationBuilder(Context context, NotificationMessage notificationMessage) {
        NotificationCompat.Builder a = b.a(context, notificationMessage, a(context, notificationMessage), this.a);
        PendingIntent c = c(context, notificationMessage);
        if (c != null) {
            a.setContentIntent(NotificationManager.redirectIntentForAnalytics(context, c, notificationMessage, true));
        }
        return a;
    }
}
