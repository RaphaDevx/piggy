package ch.coop.supercardapp.core.data;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ch.coop.auth.Auth;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import ch.coop.supercardapp.containers.employeeDiscountCard.EmployeeDiscountCardState;
import ch.coop.supercardapp.containers.supercard.SupercardState;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import io.sentry.Sentry;
import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class DataUpdateWorker extends Worker {
    private final Auth auth;
    private final DataUpdateManager dataUpdateManager;
    private final DataUpdateState dataUpdateState;
    private final EmployeeDiscountCardState employeeDiscountCardState;
    private final GiftcardState giftcardState;
    private final SupercardState supercardState;
    public Runnable wait;

    public DataUpdateWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.wait = new Runnable() { // from class: ch.coop.supercardapp.core.data.DataUpdateWorker$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Thread.sleep(EventForResult.DEFAULT_TIMEOUT_MILLIS);
            }
        };
        AppContainer appContainer = AppContainer.getInstance(context);
        this.auth = appContainer.getAuth();
        this.dataUpdateManager = appContainer.getDataUpdateManager();
        this.dataUpdateState = appContainer.getDataUpdateState();
        this.supercardState = appContainer.getSupercardState();
        this.giftcardState = appContainer.getGiftcardState();
        this.employeeDiscountCardState = appContainer.getEmployeeDiscountCardState();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        List<WidgetType> m;
        DataUpdateManager dataUpdateManager = this.dataUpdateManager;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{WidgetType.GIFTCARD});
        dataUpdateManager.triggerWidgetsUIUpdate(m);
        boolean z = true;
        int i = 0;
        while (z && i < 5) {
            i++;
            try {
                InetAddress.getAllByName("login.supercard.ch");
                z = false;
            } catch (Exception unused) {
                this.wait.run();
            }
        }
        this.dataUpdateState.dataUpdateStarted();
        Set<WidgetType> installedWidgetTypes = this.dataUpdateManager.getInstalledWidgetTypes();
        boolean isLoggedIn = this.auth.isLoggedIn();
        Optional<String> loginIdentifier = this.dataUpdateState.getLoginIdentifier();
        if (isLoggedIn) {
            String loginIdentifierNullable = this.auth.loginIdentifierNullable();
            if (loginIdentifier.isPresent()) {
                loginIdentifier.get().equals(loginIdentifierNullable);
            }
            this.dataUpdateState.setLoginIdentifier(loginIdentifierNullable);
        } else if (loginIdentifier.isPresent()) {
            this.dataUpdateState.setNeedsReauthorization(false);
            this.giftcardState.removeAllCardsButEmployeeGiftcard();
            this.dataUpdateState.setLoginIdentifier(null);
        }
        if (isLoggedIn) {
            refreshIdTokenIfNecessary();
        }
        if (installedWidgetTypes.contains(WidgetType.SUPERCARD)) {
            if (!this.dataUpdateState.getNeedsReauthorization().booleanValue() && isLoggedIn) {
                this.supercardState.retrieveSupercardFromAPI();
                this.supercardState.retrieveSupercardDatamatrixFromAPI();
            }
            if (!isLoggedIn) {
                this.supercardState.resetState();
            }
        } else {
            this.supercardState.resetState();
        }
        if (installedWidgetTypes.contains(WidgetType.GIFTCARD) || installedWidgetTypes.contains(WidgetType.PAYMENTCARD)) {
            if (!this.dataUpdateState.getNeedsReauthorization().booleanValue()) {
                this.employeeDiscountCardState.retrieveCredentialsFromPhoneApp();
                this.employeeDiscountCardState.retrieveEmployeeDiscountCardFromAPI();
                this.giftcardState.retrieveGiftcardsFromAPI();
            }
        } else {
            this.giftcardState.resetState();
            this.employeeDiscountCardState.resetState();
        }
        this.dataUpdateState.dataUpdateCompleted();
        this.dataUpdateManager.triggerWidgetsUIUpdate();
        return ListenableWorker.Result.success();
    }

    private void refreshIdTokenIfNecessary() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.auth.refreshIdTokenIfNecessary(new Auth.ActionResultCallback() { // from class: ch.coop.supercardapp.core.data.DataUpdateWorker$$ExternalSyntheticLambda1
            @Override // ch.coop.auth.Auth.ActionResultCallback
            public final void onResult(Auth.ActionResult actionResult) {
                DataUpdateWorker.this.lambda$refreshIdTokenIfNecessary$1(countDownLatch, actionResult);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Sentry.captureException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshIdTokenIfNecessary$1(CountDownLatch countDownLatch, Auth.ActionResult actionResult) {
        if (actionResult.status == Auth.ResultStatus.INVALID_REFRESH_TOKEN) {
            this.dataUpdateState.setNeedsReauthorization(true);
        } else if (actionResult.status == Auth.ResultStatus.SUCCESS) {
            this.dataUpdateState.setNeedsReauthorization(false);
        }
        countDownLatch.countDown();
    }
}
