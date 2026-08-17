package ch.coop.supercardapp.widget;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import ch.coop.supercardapp.AppContainer;

/* loaded from: classes3.dex */
public class BaseWidget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        super.onEnabled(context);
        AppContainer.getInstance(context).getDataUpdateManager().startOrRestartPeriodicDataUpdate();
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        AppContainer.getInstance(context).getDataUpdateManager().widgetWasDisabled();
    }
}
