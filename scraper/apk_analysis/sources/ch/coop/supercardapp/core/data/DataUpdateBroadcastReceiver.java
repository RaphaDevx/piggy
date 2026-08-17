package ch.coop.supercardapp.core.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ch.coop.supercardapp.AppContainer;

/* loaded from: classes3.dex */
public class DataUpdateBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("ch.coop.supercardapp.TRIGGER_DATA_UPDATE".equals(intent.getAction())) {
            AppContainer.getInstance(context).getDataUpdateManager().startOrRestartPeriodicDataUpdate();
        }
    }
}
