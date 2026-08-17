package com.capacitorjs.plugins.browser;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import com.capacitorjs.plugins.browser.EventGroup;

/* loaded from: classes3.dex */
public class Browser {
    public static final int BROWSER_FINISHED = 2;
    public static final int BROWSER_LOADED = 1;
    private static final String FALLBACK_CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    private BrowserEventListener browserEventListener;
    private CustomTabsSession browserSession;
    private Context context;
    private CustomTabsClient customTabsClient;
    private boolean isInitialLoad = false;
    private CustomTabsServiceConnection connection = new CustomTabsServiceConnection() { // from class: com.capacitorjs.plugins.browser.Browser.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // androidx.browser.customtabs.CustomTabsServiceConnection
        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            Browser.this.customTabsClient = customTabsClient;
            customTabsClient.warmup(0L);
        }
    };
    private EventGroup group = new EventGroup(new EventGroup.EventGroupCompletion() { // from class: com.capacitorjs.plugins.browser.Browser$$ExternalSyntheticLambda0
        @Override // com.capacitorjs.plugins.browser.EventGroup.EventGroupCompletion
        public final void onGroupCompletion() {
            Browser.this.handleGroupCompletion();
        }
    });

    interface BrowserEventListener {
        void onBrowserEvent(int i);
    }

    public Browser(Context context) {
        this.context = context;
    }

    public void setBrowserEventListener(BrowserEventListener browserEventListener) {
        this.browserEventListener = browserEventListener;
    }

    public BrowserEventListener getBrowserEventListenerListener() {
        return this.browserEventListener;
    }

    public void open(Uri uri) {
        open(uri, null);
    }

    public void open(Uri uri, Integer num) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(getCustomTabsSession());
        builder.setShareState(1);
        if (num != null) {
            builder.setDefaultColorSchemeParams(new CustomTabColorSchemeParams.Builder().setToolbarColor(num.intValue()).build());
        }
        CustomTabsIntent build = builder.build();
        build.intent.putExtra("android.intent.extra.REFERRER", Uri.parse("2//" + this.context.getPackageName()));
        this.isInitialLoad = true;
        this.group.reset();
        build.launchUrl(this.context, uri);
    }

    public boolean bindService() {
        String packageName = CustomTabsClient.getPackageName(this.context, null);
        if (packageName == null) {
            packageName = "com.android.chrome";
        }
        boolean bindCustomTabsService = CustomTabsClient.bindCustomTabsService(this.context, packageName, this.connection);
        this.group.leave();
        return bindCustomTabsService;
    }

    public void unbindService() {
        this.context.unbindService(this.connection);
        this.group.enter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handledNavigationEvent(int i) {
        if (i != 2) {
            if (i == 5) {
                this.group.enter();
                return;
            } else {
                if (i != 6) {
                    return;
                }
                this.group.leave();
                return;
            }
        }
        if (this.isInitialLoad) {
            BrowserEventListener browserEventListener = this.browserEventListener;
            if (browserEventListener != null) {
                browserEventListener.onBrowserEvent(1);
            }
            this.isInitialLoad = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGroupCompletion() {
        BrowserEventListener browserEventListener = this.browserEventListener;
        if (browserEventListener != null) {
            browserEventListener.onBrowserEvent(2);
        }
    }

    private CustomTabsSession getCustomTabsSession() {
        CustomTabsClient customTabsClient = this.customTabsClient;
        if (customTabsClient == null) {
            return null;
        }
        if (this.browserSession == null) {
            this.browserSession = customTabsClient.newSession(new CustomTabsCallback() { // from class: com.capacitorjs.plugins.browser.Browser.2
                @Override // androidx.browser.customtabs.CustomTabsCallback
                public void onNavigationEvent(int i, Bundle bundle) {
                    Browser.this.handledNavigationEvent(i);
                }
            });
        }
        return this.browserSession;
    }
}
