package com.salesforce.marketingcloud.messages.iam;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.salesforce.marketingcloud.R;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class IamFullImageFillActivity extends IamFullscreenActivity implements OnApplyWindowInsetsListener {
    private final void h() {
        requestWindowFeature(1);
        getWindow().setFlags(1536, 1536);
        getWindow().getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        DisplayCutoutCompat displayCutout;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        if (!isFinishing() && insets.hasInsets() && (displayCutout = insets.getDisplayCutout()) != null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.mcsdk_iam_fif_content_padding_top);
            int safeInsetTop = displayCutout.getSafeInsetTop();
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.mcsdk_iam_fif_content_padding_bottom);
            int safeInsetBottom = displayCutout.getSafeInsetBottom();
            View findViewById = v.findViewById(R.id.mcsdk_iam_container);
            if (safeInsetTop >= dimensionPixelSize) {
                dimensionPixelSize = safeInsetTop;
            }
            if (safeInsetBottom >= dimensionPixelSize2) {
                dimensionPixelSize2 = safeInsetBottom;
            }
            findViewById.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize2);
        }
        WindowInsetsCompat consumeSystemWindowInsets = insets.consumeSystemWindowInsets();
        Intrinsics.checkNotNullExpressionValue(consumeSystemWindowInsets, "consumeSystemWindowInsets(...)");
        return consumeSystemWindowInsets;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.IamFullscreenActivity, com.salesforce.marketingcloud.messages.iam.f, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        h();
        super.onCreate(bundle);
        View view = this.f;
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view, this);
        }
    }
}
