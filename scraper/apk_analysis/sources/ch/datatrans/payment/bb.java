package ch.datatrans.payment;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class bb implements b9 {
    public final Context a;
    public final boolean b;

    public bb(Application context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        this.b = z;
    }

    @Override // ch.datatrans.payment.b9
    public final PaymentMethodType a() {
        return PaymentMethodType.SWISH;
    }

    @Override // ch.datatrans.payment.b9
    public final Object a(Continuation continuation) {
        boolean z = false;
        try {
            this.a.getPackageManager().getPackageInfo(this.b ? "se.bankgirot.swish.sandbox" : "se.bankgirot.swish", 0);
            z = true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return Boxing.boxBoolean(z);
    }
}
