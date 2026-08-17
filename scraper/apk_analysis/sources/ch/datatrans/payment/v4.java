package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class v4 {
    public static C0178m4 a(Function0 dismissAction) {
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        return new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_generic, dismissAction);
    }
}
