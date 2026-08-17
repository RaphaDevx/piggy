package ch.datatrans.payment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionOptions;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\n\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u000b\u0010\u0003J\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\r\u0010\bJ\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lch/datatrans/payment/ExternalProcessRelayActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "(Landroid/os/Bundle;)V", "onResume", "onPause", "onRestart", "outState", "onSaveInstanceState", "Landroid/content/Intent;", "intent", "onNewIntent", "(Landroid/content/Intent;)V", "Companion", "ch/datatrans/payment/z4", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class ExternalProcessRelayActivity extends AppCompatActivity {
    public static final C0258z4 Companion = new C0258z4();
    public static final String EXTRA_CALLBACK_URL = "extra_callback_url";
    public static final String EXTRA_CUSTOM_TABS_URL = "extra_custom_tabs_url";
    public static final String EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL = "REDIRECT_URL";
    public static final String EXTRA_TRANSACTION_ID = "extra_transaction_id";
    public static final String IDEAL_ACTION = "ch.datatrans.payment.IDEAL_ACTION";
    public static final int RESULT_APP_NOT_INSTALLED = 13;
    public boolean a;
    public boolean b;
    public String c;
    public final Handler d = new Handler(Looper.getMainLooper());
    public final ActivityResultLauncher e;

    public ExternalProcessRelayActivity() {
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.ExternalProcessRelayActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ExternalProcessRelayActivity.a(ExternalProcessRelayActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.e = registerForActivityResult;
    }

    public static final void a(ExternalProcessRelayActivity externalProcessRelayActivity, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        externalProcessRelayActivity.setResult(result.getResultCode(), result.getData());
        externalProcessRelayActivity.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Bundle extras;
        TransactionOptions transactionOptions;
        super.onCreate(savedInstanceState);
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        Transaction transaction = transactionRegistry.getTransaction();
        String str = null;
        String appCallbackScheme = (transaction == null || (transactionOptions = transaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String()) == null) ? null : transactionOptions.getAppCallbackScheme();
        Uri data = getIntent().getData();
        StringBuilder sb = new StringBuilder();
        sb.append(data != null ? data.getScheme() : null);
        sb.append("://");
        sb.append(data != null ? data.getHost() : null);
        sb.append(data != null ? data.getEncodedPath() : null);
        String sb2 = sb.toString();
        if (appCallbackScheme != null && StringsKt.startsWith$default(sb2, PaymentMethodType.IDEAL.generateAppCallbackUrl$lib_release(appCallbackScheme), false, 2, (Object) null)) {
            Intent intent = new Intent();
            intent.setAction(IDEAL_ACTION);
            intent.putExtra("data", data);
            sendBroadcast(intent);
            finish();
            return;
        }
        Intent intent2 = getIntent();
        if (intent2 != null && (extras = intent2.getExtras()) != null) {
            str = extras.getString(EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL);
        }
        this.c = str;
        if (str != null && savedInstanceState == null) {
            this.e.launch(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return;
        }
        if (getIntent().getStringExtra(EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL) != null) {
            if (savedInstanceState == null) {
                this.c = getIntent().getStringExtra(EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL);
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getIntent().getStringExtra(EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL))));
                } catch (Exception unused) {
                    setResult(13);
                    finish();
                }
                this.b = false;
                return;
            }
            return;
        }
        if (transactionRegistry.getTransaction() == null && PCIPTokenizationRegistry.INSTANCE.getTokenization() == null) {
            Log.e("DTPL", "Unable to find request data");
            finish();
        } else if (savedInstanceState != null) {
            this.a = savedInstanceState.getBoolean("custom_tabs_started", false);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        Uri data = intent.getData();
        String uri = data != null ? data.toString() : null;
        if (uri == null || StringsKt.isBlank(uri)) {
            return;
        }
        Intent intent2 = new Intent();
        Uri data2 = intent.getData();
        intent2.putExtra(EXTRA_CALLBACK_URL, data2 != null ? data2.toString() : null);
        Uri data3 = intent.getData();
        intent2.putExtra(EXTRA_TRANSACTION_ID, data3 != null ? data3.getQueryParameter("transactionId") : null);
        setResult(-1, intent2);
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.c == null) {
            this.d.removeCallbacks(new ExternalProcessRelayActivity$$ExternalSyntheticLambda0(this));
        }
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        this.b = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        String uri;
        super.onResume();
        if (this.c != null) {
            if (this.b) {
                setResult(0);
                finish();
                return;
            }
            return;
        }
        if (this.a) {
            if (!this.b || isFinishing()) {
                return;
            }
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            Uri data = intent.getData();
            uri = data != null ? data.toString() : null;
            if (uri == null || StringsKt.isBlank(uri)) {
                a();
                return;
            }
            return;
        }
        String url = getIntent().getStringExtra(EXTRA_CUSTOM_TABS_URL);
        if (url == null) {
            finish();
            return;
        }
        Intrinsics.checkNotNullParameter(this, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        PackageManager packageManager = getPackageManager();
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent2.addCategory("android.intent.category.BROWSABLE");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent2, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : queryIntentActivities) {
            Intent intent3 = new Intent();
            intent3.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent3.setPackage(((ResolveInfo) obj).activityInfo.packageName);
            if (packageManager.resolveService(intent3, 0) != null) {
                arrayList.add(obj);
            }
        }
        uri = arrayList.isEmpty() ? null : ((ResolveInfo) arrayList.get(0)).activityInfo.packageName;
        this.a = true;
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        build.intent.setPackage(uri);
        build.launchUrl(this, Uri.parse(url));
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        if (this.c == null) {
            outState.putBoolean("custom_tabs_started", this.a);
        }
    }

    public final void a() {
        this.d.postDelayed(new ExternalProcessRelayActivity$$ExternalSyntheticLambda0(this), 100L);
    }
}
