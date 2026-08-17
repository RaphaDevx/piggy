package com.salesforce.marketingcloud.messages.push;

import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class PushMessageManager {
    public static final String d = "com.salesforce.marketingcloud.messages.push.TOKEN_REFRESHED";
    public static final String e = "com.salesforce.marketingcloud.push.TOKEN_REFRESH_SUCCESSFUL";
    public static final String f = "com.salesforce.marketingcloud.push.TOKEN_SENDER_ID";
    public static final String g = "com.salesforce.marketingcloud.notifications.PUSH_ENABLED";
    public static final String h = "com.salesforce.marketingcloud.push.TOKEN";
    static final String i = g.a("PushMessageManager");

    public interface PushTokenRefreshListener {
        void onTokenRefreshed(String str);
    }

    public interface SilentPushListener {
        void silentPushReceived(Map<String, String> map);
    }

    public static boolean isMarketingCloudPush(Map<String, String> map) {
        return map != null && "SFMC".equalsIgnoreCase(map.get(NotificationMessage.NOTIF_KEY_SID));
    }

    public abstract void disablePush();

    public abstract void enablePush();

    public abstract JSONObject getPushDebugInfo();

    public abstract String getPushToken();

    public abstract boolean handleMessage(RemoteMessage remoteMessage);

    public abstract boolean handleMessage(Map<String, String> map);

    public abstract boolean isPushEnabled();

    public abstract void registerSilentPushListener(SilentPushListener silentPushListener);

    public abstract void registerTokenRefreshListener(PushTokenRefreshListener pushTokenRefreshListener);

    public abstract void setPushToken(String str);

    public abstract void unregisterSilentPushListener(SilentPushListener silentPushListener);

    public abstract void unregisterTokenRefreshListener(PushTokenRefreshListener pushTokenRefreshListener);

    public static boolean isMarketingCloudPush(RemoteMessage remoteMessage) {
        return remoteMessage != null && isMarketingCloudPush(remoteMessage.getData());
    }
}
