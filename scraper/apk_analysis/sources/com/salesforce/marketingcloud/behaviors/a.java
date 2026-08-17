package com.salesforce.marketingcloud.behaviors;

/* loaded from: classes2.dex */
public enum a {
    BEHAVIOR_DEVICE_SHUTDOWN("com.salesforce.marketingcloud.DEVICE_SHUTDOWN"),
    BEHAVIOR_DEVICE_BOOT_COMPLETE("com.salesforce.marketingcloud.BOOT_COMPLETE"),
    BEHAVIOR_DEVICE_TIME_ZONE_CHANGED("com.salesforce.marketingcloud.TIME_ZONE_CHANGED"),
    BEHAVIOR_APP_PACKAGE_REPLACED("com.salesforce.marketingcloud.PACKAGE_REPLACED"),
    BEHAVIOR_APP_FOREGROUNDED("com.salesforce.marketingcloud.APP_FOREGROUNDED", true),
    BEHAVIOR_APP_BACKGROUNDED("com.salesforce.marketingcloud.APP_BACKGROUNDED", BEHAVIOR_APP_FOREGROUNDED),
    BEHAVIOR_SDK_REGISTRATION_SEND("com.salesforce.marketingcloud.REGISTRATION_SEND"),
    BEHAVIOR_SDK_PUSH_RECEIVED("com.salesforce.marketingcloud.PUSH_RECEIVED"),
    BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED("com.salesforce.marketingcloud.FENCE_MESSAGING_TOGGLED"),
    BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED("com.salesforce.marketingcloud.PROXIMITY_MESSAGING_TOGGLED"),
    BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED("com.salesforce.marketingcloud.PUSH_MESSAGING_TOGGLED"),
    BEHAVIOR_SDK_NOTIFICATION_OPENED("com.salesforce.marketingcloud.NOTIFICATION_OPENED"),
    BEHAVIOR_SDK_TOKEN_REFRESHED("com.salesforce.marketingcloud.TOKEN_REFRESHED");

    public final String b;
    public final boolean c;
    public final a d;

    a(String str) {
        this(str, false);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.b;
    }

    a(String str, boolean z) {
        this.b = str;
        this.c = z;
        this.d = null;
    }

    public static a a(String str) {
        int i;
        if (str == null) {
            return null;
        }
        str.hashCode();
        switch (str) {
            case "android.intent.action.TIMEZONE_CHANGED":
                return BEHAVIOR_DEVICE_TIME_ZONE_CHANGED;
            case "android.intent.action.BOOT_COMPLETED":
                return BEHAVIOR_DEVICE_BOOT_COMPLETE;
            case "android.intent.action.MY_PACKAGE_REPLACED":
                return BEHAVIOR_APP_PACKAGE_REPLACED;
            case "android.intent.action.ACTION_SHUTDOWN":
                return BEHAVIOR_DEVICE_SHUTDOWN;
            default:
                for (a aVar : values()) {
                    if (str.equals(aVar.b)) {
                        return aVar;
                    }
                }
                return null;
        }
    }

    a(String str, a aVar) {
        this.b = str;
        this.c = false;
        this.d = aVar;
    }
}
