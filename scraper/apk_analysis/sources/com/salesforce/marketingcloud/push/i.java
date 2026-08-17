package com.salesforce.marketingcloud.push;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.webkit.URLUtil;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.q;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.push.data.RichFeatures;
import com.salesforce.marketingcloud.push.data.Template;
import com.salesforce.marketingcloud.push.k;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i {
    private static o b;
    public static final i a = new i();
    private static final String c = com.salesforce.marketingcloud.g.a("RichFeatureRenderer");

    static final class a extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to load notification large icon " + this.b;
        }
    }

    static final class b extends Lambda implements Function0<String> {
        final /* synthetic */ NotificationMessage b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(NotificationMessage notificationMessage) {
            super(0);
            this.b = notificationMessage;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to load notification image " + this.b.mediaUrl;
        }
    }

    private i() {
    }

    public final o a() {
        return b;
    }

    public final String b() {
        return c;
    }

    public final void a(o oVar) {
        b = oVar;
    }

    public final void a(Context context, NotificationMessage message, NotificationCompat.Builder builder) {
        Template viewTemplate;
        com.salesforce.marketingcloud.push.buttons.a buttons;
        RemoteViews remoteViews;
        String largeIcon;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(builder, "builder");
        RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), a(message));
        remoteViews2.setViewVisibility(R.id.mcsdk_push_alert, 0);
        remoteViews2.setTextViewText(R.id.mcsdk_push_alert, message.alert);
        remoteViews2.setViewVisibility(R.id.mcsdk_push_title, 0);
        remoteViews2.setTextViewText(R.id.mcsdk_push_title, message.title);
        RichFeatures richFeatures = message.richFeatures;
        if (richFeatures != null && (largeIcon = richFeatures.getLargeIcon()) != null) {
            i iVar = a;
            if (iVar.a(message.richFeatures)) {
                remoteViews2.setViewVisibility(R.id.mcsdk_push_large_icon, 0);
                if (URLUtil.isValidUrl(largeIcon)) {
                    try {
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.notification_large_icon_width);
                        int i = R.id.mcsdk_push_large_icon;
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(q.a.a(largeIcon), dimensionPixelSize, dimensionPixelSize, false);
                        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(...)");
                        remoteViews2.setImageViewBitmap(i, iVar.a(createScaledBitmap, context.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_radius)));
                    } catch (com.salesforce.marketingcloud.push.a e) {
                        com.salesforce.marketingcloud.g.a.b(c, e, new a(largeIcon));
                    }
                } else {
                    int i2 = R.id.mcsdk_push_large_icon;
                    Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), q.a.a(context, largeIcon));
                    Intrinsics.checkNotNullExpressionValue(decodeResource, "decodeResource(...)");
                    remoteViews2.setImageViewBitmap(i2, iVar.a(decodeResource, context.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_radius)));
                }
            }
        }
        String str = message.mediaUrl;
        if (str != null && str.length() != 0) {
            remoteViews2.setViewVisibility(R.id.mcsdk_big_image, 0);
            String str2 = message.alert;
            try {
                Bitmap a2 = a(q.a.a(message.mediaUrl), context.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_radius));
                remoteViews2.setImageViewBitmap(R.id.mcsdk_big_image, a2);
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(a2).setSummaryText(str2));
            } catch (com.salesforce.marketingcloud.push.a e2) {
                com.salesforce.marketingcloud.g.a.b(c, e2, new b(message));
                str2 = message.mediaAltText;
                remoteViews2.setViewVisibility(R.id.mcsdk_big_image, 8);
            } finally {
                builder.setContentText(str2);
                builder.setTicker(str2);
            }
        }
        RichFeatures richFeatures2 = message.richFeatures;
        if (richFeatures2 != null && (buttons = richFeatures2.getButtons()) != null) {
            if (com.salesforce.marketingcloud.push.buttons.a.f.a(buttons)) {
                remoteViews = remoteViews2;
            } else {
                remoteViews = k.a.C0088a.a(k.a.a, Template.Type.RichButtons, context, message, null, 8, null).a(remoteViews2, buttons);
                builder.setCustomBigContentView(remoteViews);
            }
            if (remoteViews != null) {
                remoteViews2 = remoteViews;
            }
        }
        RichFeatures richFeatures3 = message.richFeatures;
        if (richFeatures3 == null || (viewTemplate = richFeatures3.getViewTemplate()) == null) {
            return;
        }
        builder.setStyle(new NotificationCompat.BigPictureStyle());
        builder.setCustomBigContentView(k.a.a.a(viewTemplate.f(), context, message, b).a(remoteViews2, viewTemplate));
    }

    private final int a(NotificationMessage notificationMessage) {
        if (notificationMessage.mediaUrl == null) {
            RichFeatures richFeatures = notificationMessage.richFeatures;
            if ((richFeatures != null ? richFeatures.getViewTemplate() : null) == null) {
                return R.layout.mcsdk_push_custom_button_layout;
            }
        }
        return R.layout.mcsdk_push_layout;
    }

    private final boolean a(RichFeatures richFeatures) {
        return richFeatures.getViewTemplate() == null;
    }

    public final Bitmap a(Bitmap bitmap, int i) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        float f = i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
