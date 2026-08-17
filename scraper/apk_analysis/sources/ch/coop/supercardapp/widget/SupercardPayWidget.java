package ch.coop.supercardapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.R;
import com.salesforce.marketingcloud.push.g;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.IntConsumer;

/* loaded from: classes3.dex */
public class SupercardPayWidget extends BaseWidget {
    public static final WidgetType WIDGET_TYPE = WidgetType.SUPERCARDPAY;

    @Override // android.appwidget.AppWidgetProvider
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        lambda$onUpdate$0(context, appWidgetManager, i);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, int[] iArr) {
        Arrays.stream(iArr).forEach(new IntConsumer() { // from class: ch.coop.supercardapp.widget.SupercardPayWidget$$ExternalSyntheticLambda3
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                SupercardPayWidget.this.lambda$onUpdate$0(context, appWidgetManager, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateWidget, reason: merged with bridge method [inline-methods] */
    public void lambda$onUpdate$0(Context context, AppWidgetManager appWidgetManager, int i) {
        int i2;
        int i3;
        Map<String, Integer> m;
        WidgetHelper widgetHelper = new WidgetHelper(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_supercard_pay);
        Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(i);
        if (context.getResources().getConfiguration().orientation == 1) {
            i2 = appWidgetOptions.getInt("appWidgetMinWidth");
            i3 = appWidgetOptions.getInt("appWidgetMaxHeight");
        } else {
            i2 = appWidgetOptions.getInt("appWidgetMaxWidth");
            i3 = appWidgetOptions.getInt("appWidgetMinHeight");
        }
        boolean z = i2 < 132 || i3 < 64;
        remoteViews.setViewVisibility(R.id.widget_text_and_icon, i2 >= 184 && i3 >= 104 ? 0 : 4);
        if (Build.VERSION.SDK_INT >= 31) {
            remoteViews.setViewLayoutWidth(R.id.widget_supercard_pay_logo_logo, z ? 85.0f : 108.0f, 1);
            remoteViews.setViewLayoutHeight(R.id.widget_supercard_pay_logo_logo, z ? 32.0f : 40.0f, 1);
        }
        int i4 = R.id.widget_supercard_pay_text;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_supercard_pay_text_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_supercard_pay_text_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_supercard_pay_text_it))});
        widgetHelper.setTextInAppLanguage(remoteViews, i4, m);
        widgetHelper.updateLink(remoteViews, context.getString(R.string.widget_supercard_pay_deeplink_path), WIDGET_TYPE);
        appWidgetManager.updateAppWidget(i, remoteViews);
    }
}
