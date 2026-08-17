package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class Z0 extends ViewModel {
    public final V0 a = new V0(ViewModelKt.getViewModelScope(this));

    public final void a(Y0 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        V0 v0 = this.a;
        C0219t0 dccData = init.a;
        PaymentMethodType paymentMethodType = init.d;
        String str = init.f;
        String str2 = init.g;
        Q3 dccType = init.e;
        Function2 errorAction = init.b;
        Function2 successAction = init.c;
        v0.getClass();
        Intrinsics.checkNotNullParameter(dccData, "dccData");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(dccType, "dccType");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        BuildersKt__Builders_commonKt.launch$default(v0.a, Dispatchers.getIO(), null, new T0(v0, dccData, paymentMethodType, str2, str, dccType, successAction, errorAction, null), 2, null);
    }
}
