package com.salesforce.marketingcloud.messages.iam;

import android.R;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

/* loaded from: classes2.dex */
public class IamBannerActivity extends f {
    private static final String i = com.salesforce.marketingcloud.g.a("IamBaseActivity");
    private com.salesforce.marketingcloud.messages.iam.a f;
    private boolean g;
    private long h;

    class a extends com.salesforce.marketingcloud.messages.iam.a {
        a(long j, long j2) {
            super(j, j2);
        }

        @Override // com.salesforce.marketingcloud.messages.iam.a, android.os.CountDownTimer
        public void onFinish() {
            IamBannerActivity.this.h();
        }
    }

    private void a(long j, long j2) {
        if (j > 0) {
            com.salesforce.marketingcloud.g.d(i, "Banner dismiss timer set.  Will auto dismiss in %dms", Long.valueOf(j - j2));
            a aVar = new a(j, j2);
            this.f = aVar;
            aVar.start();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f
    public /* bridge */ /* synthetic */ void b(InAppMessage.Button button) {
        super.b(button);
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, android.app.Activity
    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    void h() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.content);
        if (findFragmentById != null) {
            supportFragmentManager.beginTransaction().setCustomAnimations(0, b(c().l())).remove(findFragmentById).commitAllowingStateLoss();
        }
        a(j.a(c().k(), a()));
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
        k c = c();
        InAppMessage l = c.l();
        findViewById(R.id.content).setBackgroundDrawable(new ColorDrawable(g.a(this, l.windowColor(), com.salesforce.marketingcloud.R.color.mcsdk_iam_default_window_background)));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentById(R.id.content) == null) {
            this.g = true;
            supportFragmentManager.beginTransaction().setCustomAnimations(a(l), 0).add(R.id.content, c.a(c)).commit();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public /* bridge */ /* synthetic */ void onDismissed() {
        super.onDismissed();
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        com.salesforce.marketingcloud.messages.iam.a aVar = this.f;
        if (aVar != null) {
            aVar.cancel();
            this.f = null;
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        long displayDuration = b().displayDuration();
        long integer = this.g ? (long) (getResources().getInteger(com.salesforce.marketingcloud.R.integer.mcsdk_iam_banner_animation_duration) * (-1.0d)) : 0L;
        this.g = false;
        a(displayDuration, integer);
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public void onSwipeStarted() {
        super.onSwipeStarted();
        com.salesforce.marketingcloud.messages.iam.a aVar = this.f;
        if (aVar != null) {
            aVar.cancel();
            this.h = this.f.a();
            this.f = null;
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.f, com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
    public void onViewSettled() {
        super.onViewSettled();
        a(b().displayDuration(), this.h);
    }

    private int b(InAppMessage inAppMessage) {
        return inAppMessage.type() == InAppMessage.Type.bannerTop ? com.salesforce.marketingcloud.R.anim.mcsdk_iam_slide_out_from_top : com.salesforce.marketingcloud.R.anim.mcsdk_iam_slide_out_from_bottom;
    }

    private int a(InAppMessage inAppMessage) {
        if (inAppMessage.type() == InAppMessage.Type.bannerTop) {
            return com.salesforce.marketingcloud.R.anim.mcsdk_iam_slide_in_from_top;
        }
        return com.salesforce.marketingcloud.R.anim.mcsdk_iam_slide_in_from_bottom;
    }
}
