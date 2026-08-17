package com.salesforce.marketingcloud.notifications;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.text.TextUtils;
import android.webkit.URLUtil;
import androidx.core.app.NotificationCompat;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.q;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.push.buttons.a;
import com.salesforce.marketingcloud.push.data.RichFeatures;
import com.salesforce.marketingcloud.push.i;
import com.salesforce.marketingcloud.util.j;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
class b implements NotificationManager.NotificationBuilder {
    final int a;
    private final NotificationManager.NotificationLaunchIntentProvider b;
    private final NotificationManager.NotificationBuilder c;
    private final NotificationManager.NotificationChannelIdProvider d;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationMessage.Sound.values().length];
            a = iArr;
            try {
                iArr[NotificationMessage.Sound.CUSTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationMessage.Sound.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationMessage.Sound.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public b(int i, NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider, NotificationManager.NotificationBuilder notificationBuilder, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        this.b = notificationLaunchIntentProvider;
        this.c = notificationBuilder;
        this.d = notificationChannelIdProvider;
        this.a = i;
    }

    static NotificationCompat.Builder a(Context context, NotificationMessage notificationMessage, String str, int i) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, str);
        if (context.getApplicationInfo().icon > 0) {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
        }
        if (i > 0) {
            builder.setSmallIcon(i);
        }
        String str2 = notificationMessage.title;
        if (str2 != null) {
            builder.setContentTitle(str2);
        }
        String str3 = notificationMessage.alert;
        try {
            try {
                if (!TextUtils.isEmpty(notificationMessage.mediaUrl)) {
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(q.a.a(notificationMessage.mediaUrl)).setSummaryText(str3));
                }
            } catch (com.salesforce.marketingcloud.push.a e) {
                g.b(NotificationManager.d, e, "Unable to load notification image %s", notificationMessage.mediaUrl);
                str3 = notificationMessage.mediaAltText;
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(str3).setBigContentTitle(notificationMessage.title));
            }
            builder.setContentText(str3);
            builder.setTicker(str3);
            builder.setOnlyAlertOnce(true);
            builder.setAutoCancel(true);
            RichFeatures richFeatures = notificationMessage.richFeatures;
            if (richFeatures != null) {
                String largeIcon = richFeatures.getLargeIcon();
                if (largeIcon != null) {
                    if (URLUtil.isValidUrl(largeIcon)) {
                        try {
                            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_large_icon_size);
                            builder.setLargeIcon(Bitmap.createScaledBitmap(a(largeIcon), dimensionPixelSize, dimensionPixelSize, false));
                        } catch (com.salesforce.marketingcloud.push.a e2) {
                            g.b(NotificationManager.d, e2, "Unable to load notification large icon: %s", largeIcon);
                        }
                    } else {
                        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), q.a.a(context, largeIcon)));
                    }
                }
                String smallIcon = richFeatures.getSmallIcon();
                if (smallIcon != null) {
                    builder.setSmallIcon(q.a.a(context, smallIcon));
                }
                com.salesforce.marketingcloud.push.buttons.a buttons = richFeatures.getButtons();
                if (buttons != null && com.salesforce.marketingcloud.push.buttons.a.a(buttons)) {
                    com.salesforce.marketingcloud.push.b bVar = new com.salesforce.marketingcloud.push.b(context, notificationMessage);
                    for (a.c cVar : buttons.k()) {
                        builder.addAction(new NotificationCompat.Action(0, cVar.p().n(), bVar.a((com.salesforce.marketingcloud.push.data.a[]) cVar.i().toArray(new com.salesforce.marketingcloud.push.data.a[0]), com.salesforce.marketingcloud.analytics.stats.b.o, cVar.d(), cVar.p().n())));
                    }
                }
                i.a.a(context, notificationMessage, builder);
            }
            int i2 = a.a[notificationMessage.sound.ordinal()];
            if (i2 == 1) {
                String str4 = notificationMessage.soundName;
                if (str4 != null) {
                    builder.setSound(q.a.a(context, str4, Settings.System.DEFAULT_NOTIFICATION_URI));
                } else {
                    builder.setSound(null);
                }
            } else if (i2 == 2) {
                builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
            } else if (i2 == 3) {
                builder.setSound(null);
                g.a(NotificationManager.d, "No sound was set for notification.", new Object[0]);
            }
            return builder;
        } catch (Throwable th) {
            builder.setContentText(str3);
            builder.setTicker(str3);
            throw th;
        }
    }

    static String b(Context context, boolean z) {
        android.app.NotificationManager notificationManager;
        if (j.c() && (notificationManager = (android.app.NotificationManager) context.getSystemService("notification")) != null && (notificationManager.getNotificationChannel(NotificationManager.DEFAULT_CHANNEL_ID) == null || z)) {
            NotificationChannel notificationChannel = new NotificationChannel(NotificationManager.DEFAULT_CHANNEL_ID, context.getString(R.string.mcsdk_default_notification_channel_name), 3);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return NotificationManager.DEFAULT_CHANNEL_ID;
    }

    PendingIntent c(Context context, NotificationMessage notificationMessage) {
        try {
            NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider = this.b;
            if (notificationLaunchIntentProvider != null) {
                return notificationLaunchIntentProvider.getNotificationPendingIntent(context, notificationMessage);
            }
        } catch (IllegalArgumentException e) {
            g.b(NotificationManager.d, e, "Missing FLAG_IMMUTABLE or FLAG_MUTABLE flag in PendingIntent", new Object[0]);
        }
        int a2 = j.a(134217728);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            return null;
        }
        Intent a3 = NotificationManager.a(launchIntentForPackage, notificationMessage);
        a3.addFlags(134217728);
        return PendingIntent.getActivity(context, notificationMessage.notificationId(), a3, a2);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0017  */
    @Override // com.salesforce.marketingcloud.notifications.NotificationManager.NotificationBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.core.app.NotificationCompat.Builder setupNotificationBuilder(android.content.Context r5, com.salesforce.marketingcloud.notifications.NotificationMessage r6) throws com.salesforce.marketingcloud.push.f {
        /*
            r4 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationBuilder r0 = r4.c
            if (r0 == 0) goto L14
            androidx.core.app.NotificationCompat$Builder r0 = r0.setupNotificationBuilder(r5, r6)     // Catch: java.lang.Exception -> L9
            goto L15
        L9:
            r0 = move-exception
            java.lang.String r1 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "Custom notification builder threw an exception.  Using default notification builder."
            com.salesforce.marketingcloud.g.b(r1, r0, r3, r2)
        L14:
            r0 = 0
        L15:
            if (r0 != 0) goto L2f
            java.lang.String r0 = r4.b(r5, r6)
            int r1 = r4.a
            androidx.core.app.NotificationCompat$Builder r0 = a(r5, r6, r0, r1)
            android.app.PendingIntent r4 = r4.c(r5, r6)
            if (r4 == 0) goto L2f
            r1 = 1
            android.app.PendingIntent r4 = com.salesforce.marketingcloud.notifications.NotificationManager.redirectIntentForAnalytics(r5, r4, r6, r1)
            r0.setContentIntent(r4)
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.setupNotificationBuilder(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):androidx.core.app.NotificationCompat$Builder");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String b(android.content.Context r4, com.salesforce.marketingcloud.notifications.NotificationMessage r5) {
        /*
            r3 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationChannelIdProvider r3 = r3.d
            r0 = 0
            if (r3 == 0) goto L14
            java.lang.String r3 = r3.getNotificationChannelId(r4, r5)     // Catch: java.lang.Exception -> La
            goto L15
        La:
            r3 = move-exception
            java.lang.String r5 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "Exception thrown while app determined channel id for notification message."
            com.salesforce.marketingcloud.g.b(r5, r3, r2, r1)
        L14:
            r3 = 0
        L15:
            if (r3 != 0) goto L1c
            b(r4, r0)
            java.lang.String r3 = "com.salesforce.marketingcloud.DEFAULT_CHANNEL"
        L1c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.b(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):java.lang.String");
    }

    private static Bitmap a(String str) throws com.salesforce.marketingcloud.push.a {
        Bitmap a2;
        String str2 = NotificationManager.d;
        g.a(str2, "Fetching Large Icon: " + str, new Object[0]);
        o a3 = i.a.a();
        String str3 = str + StringUtils.LF;
        if (a3 != null && (a2 = a3.a(str3)) != null) {
            g.a(str2, "Large Icon found in cache. Returning cached bitmap.", new Object[0]);
            return a2;
        }
        g.a(str2, "Downloading Large Icon from network: " + str, new Object[0]);
        Bitmap a4 = q.a.a(str);
        if (a3 != null) {
            g.a(str2, "Updating memory cache with downloaded Large Icon.", new Object[0]);
            a3.a(str3, a4);
        } else {
            g.b(str2, "ImageHandler is null. Unable to cache the downloaded image.", new Object[0]);
        }
        return a4;
    }

    static String a(Context context, boolean z) {
        android.app.NotificationManager notificationManager;
        if (j.c() && (notificationManager = (android.app.NotificationManager) context.getSystemService("notification")) != null && (notificationManager.getNotificationChannel(NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID) == null || z)) {
            NotificationChannel notificationChannel = new NotificationChannel(NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID, context.getString(R.string.mcsdk_foreground_notification_channel_name), 3);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound(null, null);
            notificationChannel.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String a(android.content.Context r4, com.salesforce.marketingcloud.notifications.NotificationMessage r5) {
        /*
            r3 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationChannelIdProvider r3 = r3.d
            r0 = 0
            if (r3 == 0) goto L14
            java.lang.String r3 = r3.getNotificationChannelId(r4, r5)     // Catch: java.lang.Exception -> La
            goto L15
        La:
            r3 = move-exception
            java.lang.String r5 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "Exception thrown while app determined channel id for notification message."
            com.salesforce.marketingcloud.g.b(r5, r3, r2, r1)
        L14:
            r3 = 0
        L15:
            if (r3 != 0) goto L1c
            a(r4, r0)
            java.lang.String r3 = "com.salesforce.marketingcloud.DEFAULT_FOREGROUND_CHANNEL"
        L1c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.a(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):java.lang.String");
    }
}
