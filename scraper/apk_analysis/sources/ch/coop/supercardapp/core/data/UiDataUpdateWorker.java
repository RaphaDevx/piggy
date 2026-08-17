package ch.coop.supercardapp.core.data;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import java.util.List;

/* loaded from: classes3.dex */
public class UiDataUpdateWorker extends Worker {
    private final DataUpdateManager dataUpdateManager;

    public UiDataUpdateWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.dataUpdateManager = AppContainer.getInstance(context).getDataUpdateManager();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        List<WidgetType> m;
        DataUpdateManager dataUpdateManager = this.dataUpdateManager;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{WidgetType.GIFTCARD});
        dataUpdateManager.triggerWidgetsUIUpdate(m);
        return ListenableWorker.Result.success();
    }
}
