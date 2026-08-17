package io.sentry.android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
import com.salesforce.marketingcloud.UrlHandler;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.AppState;
import io.sentry.android.core.internal.util.AndroidCurrentDateProvider;
import io.sentry.android.core.internal.util.Debouncer;
import io.sentry.protocol.Device;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import io.sentry.util.StringUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public final class SystemEventsBreadcrumbsIntegration implements Integration, Closeable, AppState.AppStateListener {
    private final String[] actions;
    private final Context context;
    private volatile IntentFilter filter;
    private volatile HandlerThread handlerThread;
    private volatile boolean isClosed;
    private final AtomicBoolean isReceiverRegistered;
    private volatile boolean isStopped;
    private SentryAndroidOptions options;
    private BatteryState previousBatteryState;
    volatile SystemEventsBroadcastReceiver receiver;
    private final AutoClosableReentrantLock receiverLock;
    private IScopes scopes;

    public SystemEventsBreadcrumbsIntegration(Context context) {
        this(context, getDefaultActionsInternal());
    }

    SystemEventsBreadcrumbsIntegration(Context context, String[] strArr) {
        this.isClosed = false;
        this.isStopped = false;
        this.filter = null;
        this.handlerThread = null;
        this.isReceiverRegistered = new AtomicBoolean(false);
        this.receiverLock = new AutoClosableReentrantLock();
        this.context = ContextUtils.getApplicationContext(context);
        this.actions = strArr;
    }

    public SystemEventsBreadcrumbsIntegration(Context context, List<String> list) {
        this.isClosed = false;
        this.isStopped = false;
        this.filter = null;
        this.handlerThread = null;
        this.isReceiverRegistered = new AtomicBoolean(false);
        this.receiverLock = new AutoClosableReentrantLock();
        this.context = ContextUtils.getApplicationContext(context);
        String[] strArr = new String[list.size()];
        this.actions = strArr;
        list.toArray(strArr);
    }

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        Objects.requireNonNull(iScopes, "Scopes are required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        this.scopes = iScopes;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SystemEventsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.options.isEnableSystemEventBreadcrumbs()));
        if (this.options.isEnableSystemEventBreadcrumbs()) {
            AppState.getInstance().addAppStateListener(this);
            if (ContextUtils.isForegroundImportance()) {
                registerReceiver(this.scopes, this.options);
            }
        }
    }

    private void registerReceiver(final IScopes iScopes, final SentryAndroidOptions sentryAndroidOptions) {
        if (sentryAndroidOptions.isEnableSystemEventBreadcrumbs() && !this.isClosed && !this.isStopped && this.receiver == null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.SystemEventsBreadcrumbsIntegration$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SystemEventsBreadcrumbsIntegration.this.m3868xce8519cc(iScopes, sentryAndroidOptions);
                    }
                });
            } catch (Throwable unused) {
                sentryAndroidOptions.getLogger().log(SentryLevel.WARNING, "Failed to start SystemEventsBreadcrumbsIntegration on executor thread.", new Object[0]);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0092, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0095, code lost:
    
        return;
     */
    /* renamed from: lambda$registerReceiver$0$io-sentry-android-core-SystemEventsBreadcrumbsIntegration, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ void m3868xce8519cc(io.sentry.IScopes r7, io.sentry.android.core.SentryAndroidOptions r8) {
        /*
            r6 = this;
            io.sentry.util.AutoClosableReentrantLock r0 = r6.receiverLock
            io.sentry.ISentryLifecycleToken r0 = r0.acquire()
            boolean r1 = r6.isClosed     // Catch: java.lang.Throwable -> L96
            if (r1 != 0) goto L90
            boolean r1 = r6.isStopped     // Catch: java.lang.Throwable -> L96
            if (r1 != 0) goto L90
            io.sentry.android.core.SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver r1 = r6.receiver     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L14
            goto L90
        L14:
            io.sentry.android.core.SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver r1 = new io.sentry.android.core.SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver     // Catch: java.lang.Throwable -> L96
            r1.<init>(r7, r8)     // Catch: java.lang.Throwable -> L96
            r6.receiver = r1     // Catch: java.lang.Throwable -> L96
            android.content.IntentFilter r7 = r6.filter     // Catch: java.lang.Throwable -> L96
            r1 = 0
            if (r7 != 0) goto L37
            android.content.IntentFilter r7 = new android.content.IntentFilter     // Catch: java.lang.Throwable -> L96
            r7.<init>()     // Catch: java.lang.Throwable -> L96
            r6.filter = r7     // Catch: java.lang.Throwable -> L96
            java.lang.String[] r7 = r6.actions     // Catch: java.lang.Throwable -> L96
            int r2 = r7.length     // Catch: java.lang.Throwable -> L96
            r3 = r1
        L2b:
            if (r3 >= r2) goto L37
            r4 = r7[r3]     // Catch: java.lang.Throwable -> L96
            android.content.IntentFilter r5 = r6.filter     // Catch: java.lang.Throwable -> L96
            r5.addAction(r4)     // Catch: java.lang.Throwable -> L96
            int r3 = r3 + 1
            goto L2b
        L37:
            android.os.HandlerThread r7 = r6.handlerThread     // Catch: java.lang.Throwable -> L96
            if (r7 != 0) goto L4b
            android.os.HandlerThread r7 = new android.os.HandlerThread     // Catch: java.lang.Throwable -> L96
            java.lang.String r2 = "SystemEventsReceiver"
            r3 = 10
            r7.<init>(r2, r3)     // Catch: java.lang.Throwable -> L96
            r6.handlerThread = r7     // Catch: java.lang.Throwable -> L96
            android.os.HandlerThread r7 = r6.handlerThread     // Catch: java.lang.Throwable -> L96
            r7.start()     // Catch: java.lang.Throwable -> L96
        L4b:
            android.os.Handler r7 = new android.os.Handler     // Catch: java.lang.Throwable -> L7b
            android.os.HandlerThread r2 = r6.handlerThread     // Catch: java.lang.Throwable -> L7b
            android.os.Looper r2 = r2.getLooper()     // Catch: java.lang.Throwable -> L7b
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            android.content.Context r2 = r6.context     // Catch: java.lang.Throwable -> L7b
            io.sentry.android.core.SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver r3 = r6.receiver     // Catch: java.lang.Throwable -> L7b
            android.content.IntentFilter r4 = r6.filter     // Catch: java.lang.Throwable -> L7b
            io.sentry.android.core.ContextUtils.registerReceiver(r2, r8, r3, r4, r7)     // Catch: java.lang.Throwable -> L7b
            java.util.concurrent.atomic.AtomicBoolean r6 = r6.isReceiverRegistered     // Catch: java.lang.Throwable -> L7b
            r7 = 1
            boolean r6 = r6.getAndSet(r7)     // Catch: java.lang.Throwable -> L7b
            if (r6 != 0) goto L8a
            io.sentry.ILogger r6 = r8.getLogger()     // Catch: java.lang.Throwable -> L7b
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.DEBUG     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = "SystemEventsBreadcrumbsIntegration installed."
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7b
            r6.log(r7, r2, r3)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r6 = "SystemEventsBreadcrumbs"
            io.sentry.util.IntegrationUtils.addIntegrationToSdkVersion(r6)     // Catch: java.lang.Throwable -> L7b
            goto L8a
        L7b:
            r6 = move-exception
            r8.setEnableSystemEventBreadcrumbs(r1)     // Catch: java.lang.Throwable -> L96
            io.sentry.ILogger r7 = r8.getLogger()     // Catch: java.lang.Throwable -> L96
            io.sentry.SentryLevel r8 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> L96
            java.lang.String r1 = "Failed to initialize SystemEventsBreadcrumbsIntegration."
            r7.log(r8, r1, r6)     // Catch: java.lang.Throwable -> L96
        L8a:
            if (r0 == 0) goto L8f
            r0.close()
        L8f:
            return
        L90:
            if (r0 == 0) goto L95
            r0.close()
        L95:
            return
        L96:
            r6 = move-exception
            if (r0 == 0) goto La1
            r0.close()     // Catch: java.lang.Throwable -> L9d
            goto La1
        L9d:
            r7 = move-exception
            r6.addSuppressed(r7)
        La1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.SystemEventsBreadcrumbsIntegration.m3868xce8519cc(io.sentry.IScopes, io.sentry.android.core.SentryAndroidOptions):void");
    }

    private void scheduleUnregisterReceiver() {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions == null) {
            return;
        }
        try {
            sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.SystemEventsBreadcrumbsIntegration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SystemEventsBreadcrumbsIntegration.this.m3869x776299dd();
                }
            });
        } catch (RejectedExecutionException unused) {
            m3869x776299dd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: unregisterReceiver, reason: merged with bridge method [inline-methods] */
    public void m3869x776299dd() {
        ISentryLifecycleToken acquire = this.receiverLock.acquire();
        try {
            this.isStopped = true;
            SystemEventsBroadcastReceiver systemEventsBroadcastReceiver = this.receiver;
            this.receiver = null;
            if (acquire != null) {
                acquire.close();
            }
            if (systemEventsBroadcastReceiver != null) {
                this.context.unregisterReceiver(systemEventsBroadcastReceiver);
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ISentryLifecycleToken acquire = this.receiverLock.acquire();
        try {
            this.isClosed = true;
            this.filter = null;
            if (this.handlerThread != null) {
                this.handlerThread.quit();
            }
            this.handlerThread = null;
            if (acquire != null) {
                acquire.close();
            }
            AppState.getInstance().removeAppStateListener(this);
            scheduleUnregisterReceiver();
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SystemEventsBreadcrumbsIntegration removed.", new Object[0]);
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static List<String> getDefaultActions() {
        return Arrays.asList(getDefaultActionsInternal());
    }

    private static String[] getDefaultActionsInternal() {
        return new String[]{"android.intent.action.ACTION_SHUTDOWN", "android.intent.action.AIRPLANE_MODE", "android.intent.action.BATTERY_CHANGED", "android.intent.action.CAMERA_BUTTON", "android.intent.action.CONFIGURATION_CHANGED", "android.intent.action.DATE_CHANGED", "android.intent.action.DEVICE_STORAGE_LOW", "android.intent.action.DEVICE_STORAGE_OK", "android.intent.action.DOCK_EVENT", "android.intent.action.DREAMING_STARTED", "android.intent.action.DREAMING_STOPPED", "android.intent.action.INPUT_METHOD_CHANGED", "android.intent.action.LOCALE_CHANGED", "android.intent.action.SCREEN_OFF", "android.intent.action.SCREEN_ON", "android.intent.action.TIMEZONE_CHANGED", "android.intent.action.TIME_SET", "android.os.action.DEVICE_IDLE_MODE_CHANGED", "android.os.action.POWER_SAVE_MODE_CHANGED"};
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onForeground() {
        if (this.scopes == null || this.options == null) {
            return;
        }
        this.isStopped = false;
        registerReceiver(this.scopes, this.options);
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onBackground() {
        scheduleUnregisterReceiver();
    }

    final class SystemEventsBroadcastReceiver extends BroadcastReceiver {
        private static final long DEBOUNCE_WAIT_TIME_MS = 60000;
        private final Debouncer batteryChangedDebouncer = new Debouncer(AndroidCurrentDateProvider.getInstance(), 60000, 0);
        private final char[] buf = new char[64];
        private final SentryAndroidOptions options;
        private final IScopes scopes;

        SystemEventsBroadcastReceiver(IScopes iScopes, SentryAndroidOptions sentryAndroidOptions) {
            this.scopes = iScopes;
            this.options = sentryAndroidOptions;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BatteryState batteryState;
            String action = intent.getAction();
            if (!"android.intent.action.BATTERY_CHANGED".equals(action)) {
                batteryState = null;
            } else {
                if (this.batteryChangedDebouncer.checkForDebounce()) {
                    return;
                }
                Float batteryLevel = DeviceInfoUtil.getBatteryLevel(intent, this.options);
                BatteryState batteryState2 = new BatteryState(batteryLevel != null ? Integer.valueOf(batteryLevel.intValue()) : null, DeviceInfoUtil.isCharging(intent, this.options));
                if (batteryState2.equals(SystemEventsBreadcrumbsIntegration.this.previousBatteryState)) {
                    return;
                }
                SystemEventsBreadcrumbsIntegration.this.previousBatteryState = batteryState2;
                batteryState = batteryState2;
            }
            Breadcrumb createBreadcrumb = createBreadcrumb(System.currentTimeMillis(), intent, action, batteryState);
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_INTENT, intent);
            this.scopes.addBreadcrumb(createBreadcrumb, hint);
        }

        String getStringAfterDotFast(String str) {
            if (str == null) {
                return null;
            }
            int length = str.length();
            int length2 = this.buf.length;
            for (int i = length - 1; i >= 0; i--) {
                char charAt = str.charAt(i);
                if (charAt == '.') {
                    char[] cArr = this.buf;
                    return new String(cArr, length2, cArr.length - length2);
                }
                if (length2 == 0) {
                    return StringUtils.getStringAfterDot(str);
                }
                length2--;
                this.buf[length2] = charAt;
            }
            return str;
        }

        private Breadcrumb createBreadcrumb(long j, Intent intent, String str, BatteryState batteryState) {
            Bundle extras;
            Breadcrumb breadcrumb = new Breadcrumb(j);
            breadcrumb.setType("system");
            breadcrumb.setCategory("device.event");
            String stringAfterDotFast = getStringAfterDotFast(str);
            if (stringAfterDotFast != null) {
                breadcrumb.setData(UrlHandler.ACTION, stringAfterDotFast);
            }
            if (batteryState == null) {
                if (this.options.isEnableSystemEventBreadcrumbsExtras() && (extras = intent.getExtras()) != null && !extras.isEmpty()) {
                    HashMap hashMap = new HashMap(extras.size());
                    for (String str2 : extras.keySet()) {
                        try {
                            Object obj = extras.get(str2);
                            if (obj != null) {
                                hashMap.put(str2, obj.toString());
                            }
                        } catch (Throwable th) {
                            this.options.getLogger().log(SentryLevel.ERROR, th, "%s key of the %s action threw an error.", str2, str);
                        }
                    }
                    breadcrumb.setData("extras", hashMap);
                }
            } else {
                if (batteryState.level != null) {
                    breadcrumb.setData("level", batteryState.level);
                }
                if (batteryState.charging != null) {
                    breadcrumb.setData(Device.JsonKeys.CHARGING, batteryState.charging);
                }
            }
            breadcrumb.setLevel(SentryLevel.INFO);
            return breadcrumb;
        }
    }

    static final class BatteryState {
        private final Boolean charging;
        private final Integer level;

        BatteryState(Integer num, Boolean bool) {
            this.level = num;
            this.charging = bool;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BatteryState)) {
                return false;
            }
            BatteryState batteryState = (BatteryState) obj;
            return Objects.equals(this.level, batteryState.level) && Objects.equals(this.charging, batteryState.charging);
        }

        public int hashCode() {
            return Objects.hash(this.level, this.charging);
        }
    }
}
