package ch.coop.capacitor.marketingcloud;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.MCLogListener;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.InitializationStatus;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener;
import com.tealium.library.ConsentManager;
import java.util.Random;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MarketingCloudManager {
    private static final String TAG = "MarketingCloudManager";
    private static final MarketingCloudManager instance = new MarketingCloudManager();
    private static final Boolean DEBUG = true;

    public interface GetStringCallback {
        void onResult(String str);
    }

    private MarketingCloudManager() {
    }

    public static MarketingCloudManager getInstance() {
        return instance;
    }

    public void applicationOnCreate(Context context, int i, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        boolean z2;
        boolean z3;
        String str5;
        try {
            final Class<?> cls = Class.forName(context.getString(R.string.capacitor_marketingcloud_host_activity));
            try {
                JSONArray jSONArray = new JSONArray(getStringFromResource(context, i));
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    str = jSONObject.getString("senderid");
                    str5 = jSONObject.getString("appid");
                    str2 = jSONObject.getString("accesstoken");
                    str3 = jSONObject.getString("marketing_cloud_server_url");
                    str4 = jSONObject.getString("mid");
                    z2 = jSONObject.getBoolean("etanalytics");
                    z3 = jSONObject.getBoolean(ConsentManager.ConsentCategory.ANALYTICS);
                    z = jSONObject.getBoolean("location");
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                    z = false;
                    z2 = false;
                    z3 = false;
                    str5 = null;
                }
                if (str5 == null || str2 == null || str3 == null || str4 == null) {
                    Log.e(TAG, "Configuration file is incomplete.");
                    return;
                }
                MarketingCloudSdk.setLogLevel(DEBUG.booleanValue() ? 2 : 6);
                MarketingCloudSdk.setLogListener(new MCLogListener.AndroidLogListener());
                final MarketingCloudConfig build = MarketingCloudConfig.builder().setApplicationId(str5).setAccessToken(str2).setSenderId(str).setMarketingCloudServerUrl(str3).setMid(str4).setAnalyticsEnabled(z3).setPiAnalyticsEnabled(z2).setGeofencingEnabled(z).setProximityEnabled(z).setNotificationCustomizationOptions(NotificationCustomizationOptions.create(i2, new NotificationManager.NotificationLaunchIntentProvider(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.1
                    @Override // com.salesforce.marketingcloud.notifications.NotificationManager.NotificationLaunchIntentProvider
                    public PendingIntent getNotificationPendingIntent(Context context2, NotificationMessage notificationMessage) {
                        Log.i(MarketingCloudManager.TAG, "Received push notification: " + notificationMessage.toString());
                        int nextInt = new Random().nextInt();
                        String str6 = notificationMessage.url;
                        Uri parse = !TextUtils.isEmpty(str6) ? Uri.parse(str6) : null;
                        Intent intent = new Intent(context2, (Class<?>) cls);
                        intent.setAction("android.intent.action.VIEW");
                        if (parse != null) {
                            intent.setData(parse);
                        }
                        return PendingIntent.getActivity(context2, nextInt, intent, 201326592);
                    }
                }, new NotificationManager.NotificationChannelIdProvider(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.2
                    @Override // com.salesforce.marketingcloud.notifications.NotificationManager.NotificationChannelIdProvider
                    public String getNotificationChannelId(Context context2, NotificationMessage notificationMessage) {
                        return NotificationManager.createDefaultNotificationChannel(context2);
                    }
                })).build(context);
                SFMCSdk.configure(context, SFMCSdkModuleConfig.build(new Function1() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MarketingCloudManager.lambda$applicationOnCreate$0(MarketingCloudConfig.this, (SFMCSdkModuleConfig.Builder) obj);
                    }
                }), new Function1() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit lambda$applicationOnCreate$2;
                        lambda$applicationOnCreate$2 = MarketingCloudManager.this.lambda$applicationOnCreate$2((InitializationStatus) obj);
                        return lambda$applicationOnCreate$2;
                    }
                });
            } catch (JSONException unused) {
                Log.e(TAG, "Could not read configuration file. Invalid JSON or incomplete configuration.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "MarketingCloudManager was improperly configured. You need to provide the package.className for the MainActivity using the 'capacitor_marketingcloud_host_activity' string resource.");
        }
    }

    static /* synthetic */ Unit lambda$applicationOnCreate$0(MarketingCloudConfig marketingCloudConfig, SFMCSdkModuleConfig.Builder builder) {
        builder.setPushModuleConfig(marketingCloudConfig);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$applicationOnCreate$2(InitializationStatus initializationStatus) {
        if (initializationStatus.getStatus() == 1) {
            Log.d(TAG, "SFMCSdk init success.");
            if (!DEBUG.booleanValue()) {
                return null;
            }
            SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda1
                @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
                public final void ready(SFMCSdk sFMCSdk) {
                    MarketingCloudManager.this.lambda$applicationOnCreate$1(sFMCSdk);
                }
            });
            return null;
        }
        Log.e(TAG, "SFMCSdk init failed.");
        return null;
    }

    /* renamed from: ch.coop.capacitor.marketingcloud.MarketingCloudManager$3, reason: invalid class name */
    class AnonymousClass3 implements PushModuleReadyListener {
        AnonymousClass3(MarketingCloudManager marketingCloudManager) {
        }

        @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
        public void ready(PushModuleInterface pushModuleInterface) {
            RegistrationManager registrationManager = pushModuleInterface.getRegistrationManager();
            Log.d(MarketingCloudManager.TAG, "contactKey: " + registrationManager.getContactKey());
            Log.d(MarketingCloudManager.TAG, "deviceId: " + registrationManager.getDeviceId());
            Log.d(MarketingCloudManager.TAG, "systemToken: " + registrationManager.getSystemToken());
            Log.d(MarketingCloudManager.TAG, "DEFAULT_CHANNEL_ID: com.salesforce.marketingcloud.DEFAULT_CHANNEL");
            PushMessageManager pushMessageManager = pushModuleInterface.getPushMessageManager();
            Log.d(MarketingCloudManager.TAG, "pushDebugInfo: " + pushMessageManager.getPushDebugInfo());
            Log.d(MarketingCloudManager.TAG, "pushToken: " + pushMessageManager.getPushToken());
            Log.d(MarketingCloudManager.TAG, "isPushEnabled: " + pushMessageManager.isPushEnabled());
            pushMessageManager.registerTokenRefreshListener(new PushMessageManager.PushTokenRefreshListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$3$$ExternalSyntheticLambda0
                @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager.PushTokenRefreshListener
                public final void onTokenRefreshed(String str) {
                    Log.d(MarketingCloudManager.TAG, "onTokenRefreshed: " + str);
                }
            });
        }

        @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
        public void ready(ModuleInterface moduleInterface) {
            ready((PushModuleInterface) moduleInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applicationOnCreate$1(SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new AnonymousClass3(this));
    }

    public boolean processMessage(final RemoteMessage remoteMessage) {
        if (!PushMessageManager.isMarketingCloudPush(remoteMessage)) {
            return false;
        }
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda9
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$processMessage$3(remoteMessage, sFMCSdk);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processMessage$3(final RemoteMessage remoteMessage, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.4
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getPushMessageManager().handleMessage(remoteMessage);
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                ready((PushModuleInterface) moduleInterface);
            }
        });
    }

    public void onNewToken(final String str) {
        Log.d(TAG, "onNewToken: " + str);
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda4
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$onNewToken$4(str, sFMCSdk);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNewToken$4(final String str, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.5
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
                pushModuleInterface.getPushMessageManager().setPushToken(str);
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                ready((PushModuleInterface) moduleInterface);
            }
        });
    }

    public void getDeviceIdentifier(final GetStringCallback getStringCallback) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda3
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$getDeviceIdentifier$5(getStringCallback, sFMCSdk);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDeviceIdentifier$5(final GetStringCallback getStringCallback, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.6
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
                getStringCallback.onResult(pushModuleInterface.getRegistrationManager().getDeviceId());
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                ready((PushModuleInterface) moduleInterface);
            }
        });
    }

    public void getDeviceToken(final GetStringCallback getStringCallback) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda0
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$getDeviceToken$6(getStringCallback, sFMCSdk);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDeviceToken$6(final GetStringCallback getStringCallback, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.7
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
                getStringCallback.onResult(pushModuleInterface.getRegistrationManager().getSystemToken());
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                ready((PushModuleInterface) moduleInterface);
            }
        });
    }

    public void getContactKey(final GetStringCallback getStringCallback) {
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda8
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$getContactKey$7(getStringCallback, sFMCSdk);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getContactKey$7(final GetStringCallback getStringCallback, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.8
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                getStringCallback.onResult(moduleInterface.getModuleIdentity().getProfileId());
            }
        });
    }

    public void setContactKey(final String str) {
        if (DEBUG.booleanValue()) {
            Log.e(TAG, "setContactKey: " + str);
        }
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda10
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.lambda$setContactKey$8(str, sFMCSdk);
            }
        });
    }

    static /* synthetic */ void lambda$setContactKey$8(String str, SFMCSdk sFMCSdk) {
        sFMCSdk.getIdentity().setProfileId(str);
        if (DEBUG.booleanValue()) {
            Log.e(TAG, "setContactKey result: done");
        }
    }

    public void setAttribute(final String str, final String str2) {
        if (DEBUG.booleanValue()) {
            Log.e(TAG, "setAttribute: " + str + "=" + str2);
        }
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda5
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.lambda$setAttribute$9(str, str2, sFMCSdk);
            }
        });
    }

    static /* synthetic */ void lambda$setAttribute$9(String str, String str2, SFMCSdk sFMCSdk) {
        sFMCSdk.getIdentity().setProfileAttribute(str, str2);
        if (DEBUG.booleanValue()) {
            Log.e(TAG, "setAttribute result: done");
        }
    }

    public void getAttribute(final String str, final GetStringCallback getStringCallback) {
        if (DEBUG.booleanValue()) {
            Log.e(TAG, "getAttribute: " + str);
        }
        SFMCSdk.requestSdk(new SFMCSdkReadyListener() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager$$ExternalSyntheticLambda2
            @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
            public final void ready(SFMCSdk sFMCSdk) {
                MarketingCloudManager.this.lambda$getAttribute$10(getStringCallback, str, sFMCSdk);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getAttribute$10(final GetStringCallback getStringCallback, final String str, SFMCSdk sFMCSdk) {
        sFMCSdk.mp(new PushModuleReadyListener(this) { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudManager.9
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener
            public void ready(PushModuleInterface pushModuleInterface) {
                getStringCallback.onResult(pushModuleInterface.getRegistrationManager().getAttributes().get(str));
            }

            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener, com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public void ready(ModuleInterface moduleInterface) {
                ready((PushModuleInterface) moduleInterface);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getStringFromResource(android.content.Context r6, int r7) {
        /*
            java.lang.String r0 = "Error closing raw resource: "
            java.lang.String r1 = "MarketingCloudManager"
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r3.<init>()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            android.content.res.Resources r6 = r6.getResources()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.io.InputStream r6 = r6.openRawResource(r7)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41
        L1c:
            java.lang.String r6 = r4.readLine()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L6d
            if (r6 == 0) goto L26
            r3.append(r6)     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L6d
            goto L1c
        L26:
            java.lang.String r6 = r3.toString()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L6d
            r4.close()     // Catch: java.io.IOException -> L2e
            goto L3e
        L2e:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            android.util.Log.e(r1, r7, r2)
        L3e:
            return r6
        L3f:
            r6 = move-exception
            goto L6f
        L41:
            r4 = r2
        L42:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6d
            r6.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = "Error opening raw resource: "
            r6.append(r3)     // Catch: java.lang.Throwable -> L6d
            r6.append(r7)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L6d
            android.util.Log.e(r1, r6)     // Catch: java.lang.Throwable -> L6d
            if (r4 == 0) goto L6c
            r4.close()     // Catch: java.io.IOException -> L5c
            goto L6c
        L5c:
            r6 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            android.util.Log.e(r1, r7, r6)
        L6c:
            return r2
        L6d:
            r6 = move-exception
            r2 = r4
        L6f:
            if (r2 == 0) goto L85
            r2.close()     // Catch: java.io.IOException -> L75
            goto L85
        L75:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            android.util.Log.e(r1, r7, r2)
        L85:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.coop.capacitor.marketingcloud.MarketingCloudManager.getStringFromResource(android.content.Context, int):java.lang.String");
    }
}
