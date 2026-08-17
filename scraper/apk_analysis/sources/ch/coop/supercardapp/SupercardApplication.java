package ch.coop.supercardapp;

import android.app.Application;
import android.provider.Settings;
import ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin;
import ch.coop.capacitor.marketingcloud.MarketingCloudManager;
import ch.coop.supercardapp.core.data.DataUpdateManager;
import io.sentry.Sentry;
import io.sentry.SentryOptions;
import io.sentry.android.core.SentryAndroid;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.User;
import lib.android.paypal.com.magnessdk.a;
import lib.android.paypal.com.magnessdk.c;

/* loaded from: classes3.dex */
public class SupercardApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MarketingCloudManager.getInstance().applicationOnCreate(this, R.raw.marketing_cloud_sdk_configuration, R.drawable.ic_notification_icon);
        final String str = a.d;
        SentryAndroid.init(this, (Sentry.OptionsConfiguration<SentryAndroidOptions>) new Sentry.OptionsConfiguration() { // from class: ch.coop.supercardapp.SupercardApplication$$ExternalSyntheticLambda0
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                SupercardApplication.lambda$onCreate$0(str, (SentryAndroidOptions) sentryOptions);
            }
        });
        User user = new User();
        user.setId(Settings.Secure.getString(getContentResolver(), c.f));
        Sentry.setUser(user);
        DataUpdateManager dataUpdateManager = AppContainer.getInstance(this).getDataUpdateManager();
        ExtensionConnectorPlugin.setDataUpdateManager(dataUpdateManager);
        dataUpdateManager.startOrRestartPeriodicDataUpdate();
    }

    static /* synthetic */ void lambda$onCreate$0(String str, SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setDsn("https://a7d7e137b26242db854ec3483102e170@o462489.ingest.sentry.io/5601870");
        sentryAndroidOptions.setRelease("ch.coop.supercard@6.11.2+2984");
        sentryAndroidOptions.setDebug(false);
        sentryAndroidOptions.setEnvironment(str);
        sentryAndroidOptions.setSendDefaultPii(true);
        sentryAndroidOptions.setSampleRate(Double.valueOf(0.1d));
        sentryAndroidOptions.setTracesSampleRate(null);
        sentryAndroidOptions.setEnableAutoSessionTracking(false);
    }
}
