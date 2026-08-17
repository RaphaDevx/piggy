package com.salesforce.marketingcloud.messages.iam;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout;
import io.sentry.protocol.SentryStackFrame;

/* loaded from: classes2.dex */
abstract class f extends FragmentActivity implements View.OnClickListener, SwipeDismissConstraintLayout.SwipeDismissListener {
    private static final int c = 123;
    private static final String d = "completedEvent";
    private static final String e = com.salesforce.marketingcloud.g.a("IamBaseActivity");
    private k a;
    private j b;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[InAppMessage.Button.ActionType.values().length];
            a = iArr;
            try {
                iArr[InAppMessage.Button.ActionType.url.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[InAppMessage.Button.ActionType.pushSettings.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[InAppMessage.Button.ActionType.locationSettings.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    f() {
    }

    private void d() {
        if (com.salesforce.marketingcloud.util.f.b(this) && MarketingCloudSdk.isReady()) {
            RegionMessageManager regionMessageManager = MarketingCloudSdk.getInstance().getRegionMessageManager();
            try {
                if (regionMessageManager.enableGeofenceMessaging()) {
                    com.salesforce.marketingcloud.g.a(e, "Geofence messaging enabled from IAM action", new Object[0]);
                }
                if (regionMessageManager.enableProximityMessaging()) {
                    com.salesforce.marketingcloud.g.a(e, "Proximity messaging enabled from IAM action", new Object[0]);
                }
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(e, e2, "Unable to enable region messaging", new Object[0]);
            }
        }
    }

    private void e() {
        if (com.salesforce.marketingcloud.util.f.b(this)) {
            com.salesforce.marketingcloud.g.a(e, "Location permission already allowed.  Skipping action from button click.", new Object[0]);
            d();
            finish();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                ActivityCompat.requestPermissions(this, com.salesforce.marketingcloud.util.f.a, c);
                return;
            }
            try {
                startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, getPackageName(), null)), c);
            } catch (ActivityNotFoundException e2) {
                com.salesforce.marketingcloud.g.b(e, e2, "Unable to launch application settings page for location permission request.", new Object[0]);
                finish();
            }
        }
    }

    private void g() {
        try {
            int q = c().q();
            if (q != 0) {
                getWindow().setStatusBarColor(q);
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.a(e, e2, "Failed to find status bar color from meta-data", new Object[0]);
        }
    }

    protected void a(j jVar) {
        this.b = jVar;
    }

    protected InAppMessage b() {
        return this.a.l();
    }

    protected k c() {
        return this.a;
    }

    @Override // android.app.Activity
    public void finish() {
        k kVar = this.a;
        if (kVar != null) {
            kVar.a(this.b);
        }
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == c) {
            d();
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.b = j.b(this.a.k(), a());
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            if (view.getTag() instanceof InAppMessage.Button) {
                b((InAppMessage.Button) view.getTag());
            } else if (view.getTag() instanceof InAppMessage.CloseButton) {
                this.b = j.b(this.a.k(), a());
                finish();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            this.a = (k) getIntent().getParcelableExtra("messageHandler");
        }
        k kVar = this.a;
        if (kVar == null || !kVar.i()) {
            finish();
            return;
        }
        g();
        if (bundle != null) {
            this.b = (j) bundle.getParcelable(d);
        }
    }

    public void onDismissed() {
        this.b = j.b(this.a.k(), a());
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        c().n();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        d();
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        c().o();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(d, this.b);
    }

    public void onSwipeStarted() {
    }

    public void onViewSettled() {
    }

    private void f() {
        Intent putExtra = new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        if (putExtra != null) {
            try {
                startActivity(putExtra);
            } catch (ActivityNotFoundException e2) {
                com.salesforce.marketingcloud.g.b(e, e2, "Unable to handle push settings button action.", new Object[0]);
            }
        } else {
            com.salesforce.marketingcloud.g.a(e, "Unable to launch notification settings for this device.", new Object[0]);
        }
        finish();
    }

    protected long a() {
        k c2 = c();
        c2.r();
        return c2.j();
    }

    public void b(InAppMessage.Button button) {
        if (button != null) {
            this.b = j.a(this.a.k(), a(), button);
            int i = a.a[button.actionType().ordinal()];
            if (i == 1) {
                a(button);
                return;
            }
            if (i == 2) {
                f();
            } else if (i != 3) {
                finish();
            } else {
                e();
            }
        }
    }

    private void a(InAppMessage.Button button) {
        PendingIntent a2 = c().a(this, button);
        if (a2 != null) {
            try {
                a2.send();
            } catch (PendingIntent.CanceledException e2) {
                com.salesforce.marketingcloud.g.b(e, e2, "Unable to launch url for button click", new Object[0]);
            }
        } else {
            com.salesforce.marketingcloud.g.a(e, "No PendingIntent returned for button click.", new Object[0]);
        }
        finish();
    }
}
