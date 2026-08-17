package com.capacitorjs.plugins.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: classes3.dex */
public class BrowserControllerActivity extends Activity {
    private boolean isCustomTabsOpen = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isCustomTabsOpen = false;
        if (BrowserPlugin.browserControllerListener != null) {
            BrowserPlugin.browserControllerListener.onControllerReady(this);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra("close")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isCustomTabsOpen) {
            this.isCustomTabsOpen = false;
            finish();
        } else {
            this.isCustomTabsOpen = true;
        }
    }

    public void open(Browser browser, Uri uri, Integer num) {
        browser.open(uri, num);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.isCustomTabsOpen = false;
        BrowserPlugin.setBrowserControllerListener(null);
    }
}
