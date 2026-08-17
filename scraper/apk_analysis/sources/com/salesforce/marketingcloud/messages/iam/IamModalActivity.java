package com.salesforce.marketingcloud.messages.iam;

import android.os.Bundle;
import android.view.View;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

/* loaded from: classes2.dex */
public class IamModalActivity extends f implements View.OnClickListener {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[InAppMessage.LayoutOrder.values().length];
            a = iArr;
            try {
                iArr[InAppMessage.LayoutOrder.ImageTitleBody.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[InAppMessage.LayoutOrder.TitleImageBody.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected int a(InAppMessage inAppMessage) {
        int i = R.layout.mcsdk_iam_modal_inset_itb;
        InAppMessage.Media media = inAppMessage.media();
        int i2 = a.a[inAppMessage.layoutOrder().ordinal()];
        return i2 != 1 ? i2 != 2 ? i : (media == null || media.size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_modal_inset_tib : R.layout.mcsdk_iam_modal_e2e_tib : (media == null || media.size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_modal_inset_itb : R.layout.mcsdk_iam_modal_e2e_itb;
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

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFinishing()) {
            return;
        }
        setContentView(a(c().l()));
        new l(this, c().s()).a(findViewById(android.R.id.content), c());
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
