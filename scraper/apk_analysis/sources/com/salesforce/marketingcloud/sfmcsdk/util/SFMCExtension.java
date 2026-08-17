package com.salesforce.marketingcloud.sfmcsdk.util;

import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SFMCExtension.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/util/SFMCExtension;", "", "()V", "TAG", "", "getValidContactKey", "contactKey", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SFMCExtension {
    public static final SFMCExtension INSTANCE = new SFMCExtension();
    public static final String TAG = "~$SFMCExtension";

    private SFMCExtension() {
    }

    @JvmStatic
    public static final String getValidContactKey(String contactKey) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        String str = contactKey;
        if (TextUtils.isEmpty(str) || TextUtils.getTrimmedLength(str) == 0) {
            SFMCSdkLogger.INSTANCE.w(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.SFMCExtension$getValidContactKey$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "An empty or blank ContactKey will not be transmitted to the Salesforce servers as its invalid.";
                }
            });
            return null;
        }
        return StringsKt.trim((CharSequence) str).toString();
    }
}
