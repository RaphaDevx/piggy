package com.salesforce.marketingcloud.push.carousel;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.PushNotificationActionHandler;
import com.salesforce.marketingcloud.push.data.RichFeatures;
import com.salesforce.marketingcloud.util.j;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public class b extends com.salesforce.marketingcloud.push.b {
    public static final a k = new a(null);
    public static final String l = "com.salesforce.marketingcloud.notifications.ACTION_CAROUSEL_NEXT";
    public static final String m = "com.salesforce.marketingcloud.notifications.ACTION_CAROUSEL_PREVIOUS";
    public static final String n = "com.salesforce.marketingcloud.notifications.INTENT_KEY_CAROUSEL_DATA";
    private final Context i;
    private final NotificationMessage j;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context, NotificationMessage message) {
        super(context, message);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        this.i = context;
        this.j = message;
    }

    public final PendingIntent a(String intentAction, com.salesforce.marketingcloud.push.carousel.a data) {
        RichFeatures richFeatures;
        NotificationMessage copy;
        NotificationMessage copy2;
        Intrinsics.checkNotNullParameter(intentAction, "intentAction");
        Intrinsics.checkNotNullParameter(data, "data");
        NotificationMessage notificationMessage = this.j;
        RichFeatures richFeatures2 = notificationMessage.richFeatures;
        if (richFeatures2 != null) {
            richFeatures = RichFeatures.copy$default(richFeatures2, null, null, com.salesforce.marketingcloud.push.carousel.a.a(data, null, Intrinsics.areEqual(intentAction, l) ? data.m() + 1 : Intrinsics.areEqual(intentAction, m) ? data.m() - 1 : data.m(), null, 5, null), null, 11, null);
        } else {
            richFeatures = null;
        }
        copy = notificationMessage.copy((r37 & 1) != 0 ? notificationMessage.id : null, (r37 & 2) != 0 ? notificationMessage.requestId : null, (r37 & 4) != 0 ? notificationMessage.region : null, (r37 & 8) != 0 ? notificationMessage.alert : null, (r37 & 16) != 0 ? notificationMessage.sound : null, (r37 & 32) != 0 ? notificationMessage.soundName : null, (r37 & 64) != 0 ? notificationMessage.title : null, (r37 & 128) != 0 ? notificationMessage.subtitle : null, (r37 & 256) != 0 ? notificationMessage.type : null, (r37 & 512) != 0 ? notificationMessage.trigger : null, (r37 & 1024) != 0 ? notificationMessage.url : null, (r37 & 2048) != 0 ? notificationMessage.mediaUrl : null, (r37 & 4096) != 0 ? notificationMessage.mediaAltText : null, (r37 & 8192) != 0 ? notificationMessage.customKeys : null, (r37 & 16384) != 0 ? notificationMessage.custom : null, (r37 & 32768) != 0 ? notificationMessage.payload : null, (r37 & 65536) != 0 ? notificationMessage.richFeatures : richFeatures, (r37 & 131072) != 0 ? notificationMessage.propertyBag : null, (r37 & 262144) != 0 ? notificationMessage.notificationId : 0);
        Context context = this.i;
        int hashCode = UUID.randomUUID().hashCode();
        Intent intent = new Intent(this.i, (Class<?>) PushNotificationActionHandler.class);
        copy2 = copy.copy((r37 & 1) != 0 ? copy.id : null, (r37 & 2) != 0 ? copy.requestId : null, (r37 & 4) != 0 ? copy.region : null, (r37 & 8) != 0 ? copy.alert : null, (r37 & 16) != 0 ? copy.sound : NotificationMessage.Sound.NONE, (r37 & 32) != 0 ? copy.soundName : null, (r37 & 64) != 0 ? copy.title : null, (r37 & 128) != 0 ? copy.subtitle : null, (r37 & 256) != 0 ? copy.type : null, (r37 & 512) != 0 ? copy.trigger : null, (r37 & 1024) != 0 ? copy.url : null, (r37 & 2048) != 0 ? copy.mediaUrl : null, (r37 & 4096) != 0 ? copy.mediaAltText : null, (r37 & 8192) != 0 ? copy.customKeys : null, (r37 & 16384) != 0 ? copy.custom : null, (r37 & 32768) != 0 ? copy.payload : null, (r37 & 65536) != 0 ? copy.richFeatures : null, (r37 & 131072) != 0 ? copy.propertyBag : null, (r37 & 262144) != 0 ? copy.notificationId : 0);
        intent.putExtra(com.salesforce.marketingcloud.push.b.e, copy2);
        Unit unit = Unit.INSTANCE;
        intent.setAction(intentAction);
        intent.putExtra(n, data);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, hashCode, intent, j.a(134217728));
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }
}
