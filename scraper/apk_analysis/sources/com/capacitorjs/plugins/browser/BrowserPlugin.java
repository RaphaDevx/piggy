package com.capacitorjs.plugins.browser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.capacitorjs.plugins.browser.Browser;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;

@CapacitorPlugin(name = "Browser")
/* loaded from: classes3.dex */
public class BrowserPlugin extends Plugin {
    private static BrowserControllerActivity browserControllerActivityInstance;
    public static BrowserControllerListener browserControllerListener;
    private Browser implementation;

    public static void setBrowserControllerListener(BrowserControllerListener browserControllerListener2) {
        browserControllerListener = browserControllerListener2;
        if (browserControllerListener2 == null) {
            browserControllerActivityInstance = null;
        }
    }

    @Override // com.getcapacitor.Plugin
    public void load() {
        Browser browser = new Browser(getContext());
        this.implementation = browser;
        browser.setBrowserEventListener(new Browser.BrowserEventListener() { // from class: com.capacitorjs.plugins.browser.BrowserPlugin$$ExternalSyntheticLambda1
            @Override // com.capacitorjs.plugins.browser.Browser.BrowserEventListener
            public final void onBrowserEvent(int i) {
                BrowserPlugin.this.onBrowserEvent(i);
            }
        });
    }

    @PluginMethod
    public void open(final PluginCall pluginCall) {
        String string = pluginCall.getString("url");
        if (string == null) {
            pluginCall.reject("Must provide a URL to open");
            return;
        }
        if (string.isEmpty()) {
            pluginCall.reject("URL must not be empty");
            return;
        }
        try {
            final Uri parse = Uri.parse(string);
            String string2 = pluginCall.getString("toolbarColor");
            final Integer num = null;
            if (string2 != null) {
                try {
                    num = Integer.valueOf(WebColor.parseColor(string2));
                } catch (IllegalArgumentException unused) {
                    Logger.error(getLogTag(), "Invalid color provided for toolbarColor. Using default", null);
                }
            }
            Intent intent = new Intent(getContext(), (Class<?>) BrowserControllerActivity.class);
            intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            intent.addFlags(268435456);
            getContext().startActivity(intent);
            setBrowserControllerListener(new BrowserControllerListener() { // from class: com.capacitorjs.plugins.browser.BrowserPlugin$$ExternalSyntheticLambda0
                @Override // com.capacitorjs.plugins.browser.BrowserControllerListener
                public final void onControllerReady(BrowserControllerActivity browserControllerActivity) {
                    BrowserPlugin.this.lambda$open$0(parse, num, pluginCall, browserControllerActivity);
                }
            });
        } catch (Exception e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$open$0(Uri uri, Integer num, PluginCall pluginCall, BrowserControllerActivity browserControllerActivity) {
        try {
            browserControllerActivity.open(this.implementation, uri, num);
            browserControllerActivityInstance = browserControllerActivity;
            pluginCall.resolve();
        } catch (ActivityNotFoundException e) {
            Logger.error(getLogTag(), e.getLocalizedMessage(), null);
            pluginCall.reject("Unable to display URL");
        }
    }

    @PluginMethod
    public void close(PluginCall pluginCall) {
        if (browserControllerActivityInstance != null) {
            browserControllerActivityInstance = null;
            Intent intent = new Intent(getContext(), (Class<?>) BrowserControllerActivity.class);
            intent.putExtra("close", true);
            getContext().startActivity(intent);
        }
        pluginCall.resolve();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnResume() {
        if (this.implementation.bindService()) {
            return;
        }
        Logger.error(getLogTag(), "Error binding to custom tabs service", null);
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnPause() {
        this.implementation.unbindService();
    }

    void onBrowserEvent(int i) {
        if (i == 1) {
            notifyListeners("browserPageLoaded", null);
        } else {
            if (i != 2) {
                return;
            }
            notifyListeners("browserFinished", null);
        }
    }
}
