package ch.datatrans.payment;

import android.app.Activity;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.v, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0236v {
    public static void a(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.overridePendingTransition(R.anim.dtpl_slide_up, 0);
    }
}
