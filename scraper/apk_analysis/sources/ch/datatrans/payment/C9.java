package ch.datatrans.payment;

import android.content.Context;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final /* synthetic */ class C9 extends FunctionReferenceImpl implements Function1 {
    public C9(SavedPaymentMethod savedPaymentMethod) {
        super(1, savedPaymentMethod, SavedPaymentMethod.class, "getInternalDisplayTitle", "getInternalDisplayTitle$lib_release(Landroid/content/Context;)Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context p0 = (Context) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((SavedPaymentMethod) this.receiver).getInternalDisplayTitle$lib_release(p0);
    }
}
