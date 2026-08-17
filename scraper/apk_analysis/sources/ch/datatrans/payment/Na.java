package ch.datatrans.payment;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/Na;", "Lch/datatrans/payment/p9;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class Na extends p9 {
    public final int h = R.string.datatrans_sdk_saved_method_selection_title;
    public final boolean i = true;

    public static final Unit a(Na na) {
        Nb value = ((E9) na.b.getValue()).a;
        value.l = null;
        value.b = false;
        value.i = (PaymentMethodType) CollectionsKt.singleOrNull(value.j);
        Jc jc = (Jc) na.a.getValue();
        jc.getClass();
        Intrinsics.checkNotNullParameter(value, "model");
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        transactionRegistry.getTransactionModel$lib_release().c();
        Intrinsics.checkNotNullParameter(value, "value");
        transactionRegistry.setTransactionModel$lib_release(value);
        if (transactionRegistry.getTransactionModel$lib_release().i != null) {
            jc.b();
        } else {
            if (transactionRegistry.getTransactionModel$lib_release().j.size() != 1) {
                List list = transactionRegistry.getTransactionModel$lib_release().j;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (!((PaymentMethodType) it.next()).isCreditCard$lib_release()) {
                            jc.r = true;
                            jc.f.postValue(Ac.b);
                            break;
                        }
                    }
                }
            }
            TransactionRegistry transactionRegistry2 = TransactionRegistry.INSTANCE;
            transactionRegistry2.getTransactionModel$lib_release().i = (PaymentMethodType) CollectionsKt.first(transactionRegistry2.getTransactionModel$lib_release().j);
            jc.b();
        }
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.p9
    /* renamed from: b, reason: from getter */
    public final boolean getI() {
        return this.i;
    }

    @Override // ch.datatrans.payment.p9
    /* renamed from: c, reason: from getter */
    public final int getG() {
        return this.h;
    }

    @Override // ch.datatrans.payment.p9
    public final C0254x9 a() {
        E9 e9 = (E9) this.b.getValue();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        ArrayList values = e9.a(requireContext, ((E9) this.b.getValue()).a.m);
        if (values == null) {
            return super.a();
        }
        ((Jc) this.a.getValue()).getClass();
        List list = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().j;
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.Na$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Na.a(Na.this);
            }
        };
        if (list.size() == 1 && list.get(0) == PaymentMethodType.HALF_FARE_PLUS) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(values, "values");
        C0254x9 c0254x9 = new C0254x9(values);
        c0254x9.b = function0;
        return c0254x9;
    }
}
