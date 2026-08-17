package com.salesforce.marketingcloud.messages.iam;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

/* loaded from: classes2.dex */
public class IamFullscreenActivity extends f {
    protected View f;
    private k g;
    private InAppMessage h;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[InAppMessage.Type.values().length];
            b = iArr;
            try {
                iArr[InAppMessage.Type.full.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[InAppMessage.Type.fullImageFill.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[InAppMessage.LayoutOrder.values().length];
            a = iArr2;
            try {
                iArr2[InAppMessage.LayoutOrder.ImageTitleBody.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[InAppMessage.LayoutOrder.TitleImageBody.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private int a(InAppMessage inAppMessage) {
        int i = R.layout.mcsdk_iam_full_inset_itb;
        int i2 = a.b[inAppMessage.type().ordinal()];
        if (i2 != 1) {
            return i2 != 2 ? i : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_fif_inset_itb : R.layout.mcsdk_iam_fif_e2e_itb;
        }
        int i3 = a.a[inAppMessage.layoutOrder().ordinal()];
        return i3 != 1 ? i3 != 2 ? i : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_full_inset_tib : R.layout.mcsdk_iam_full_e2e_tib : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_full_inset_itb : R.layout.mcsdk_iam_full_e2e_itb;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f
    public /* bridge */ /* synthetic */ void b(InAppMessage.Button button) {
        super.b(button);
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, android.app.Activity
    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.activity.ComponentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, android.view.View.OnClickListener
    public /* bridge */ /* synthetic */ void onClick(View view) {
        super.onClick(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        k kVar;
        super.onConfigurationChanged(configuration);
        InAppMessage inAppMessage = this.h;
        if (inAppMessage == null || inAppMessage.type() != InAppMessage.Type.fullImageFill || configuration.orientation != 2 || (kVar = this.g) == null) {
            return;
        }
        kVar.a(j.a(kVar.k(), this.g.j()));
        finish();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFinishing()) {
            return;
        }
        this.f = findViewById(android.R.id.content);
        k c = c();
        this.g = c;
        InAppMessage l = c.l();
        this.h = l;
        setContentView(a(l));
        new e(this, this.g.s()).a(this.f, this.g);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        View view = this.f;
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view, null);
        }
        super.onDestroy();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public /* bridge */ /* synthetic */ void onDismissed() {
        super.onDismissed();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public /* bridge */ /* synthetic */ void onSwipeStarted() {
        super.onSwipeStarted();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public /* bridge */ /* synthetic */ void onViewSettled() {
        super.onViewSettled();
    }
}
