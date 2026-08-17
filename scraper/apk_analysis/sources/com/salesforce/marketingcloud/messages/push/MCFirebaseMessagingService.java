package com.salesforce.marketingcloud.messages.push;

import android.content.Context;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.MCService;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.util.j;

/* loaded from: classes2.dex */
public class MCFirebaseMessagingService extends FirebaseMessagingService {
    private static final String a = g.a("MCFirebaseMessagingService");

    static void a(Context context) {
        MarketingCloudSdk a2 = a();
        if (a2 == null) {
            g.e(a, "Marketing Cloud SDK init failed.  Unable to update push token.", new Object[0]);
            return;
        }
        String senderId = a2.getMarketingCloudConfig().senderId();
        if (senderId != null) {
            MCService.b(context, senderId);
        } else {
            g.a(a, "Received new token intent but senderId was not set.", new Object[0]);
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        g.d(a, "onMessageReceived()", new Object[0]);
        a(remoteMessage);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        a(this);
    }

    static void a(RemoteMessage remoteMessage) {
        String str;
        if (remoteMessage == null) {
            g.e(a, "RemoteMessage was null.", new Object[0]);
            return;
        }
        if (remoteMessage.getData() != null && remoteMessage.getData().containsKey(NotificationMessage.NOTIF_KEY_ID)) {
            str = remoteMessage.getData().get(NotificationMessage.NOTIF_KEY_ID);
        } else {
            str = "Unknown Message";
        }
        String str2 = a;
        g.d(str2, "onMessageReceived() for MessageID: '%s'", str);
        MarketingCloudSdk a2 = a();
        if (a2 == null) {
            g.e(str2, "Marketing Cloud SDK init failed.  Push message ignored.", new Object[0]);
        } else {
            a2.getPushMessageManager().handleMessage(remoteMessage);
        }
    }

    private static MarketingCloudSdk a() {
        if (j.a(3000L, 50L) && MarketingCloudSdk.getInstance() != null) {
            return MarketingCloudSdk.getInstance();
        }
        g.e(a, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
        return null;
    }
}
