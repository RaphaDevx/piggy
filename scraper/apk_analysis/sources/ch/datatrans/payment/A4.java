package ch.datatrans.payment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import ch.datatrans.payment.api.TransactionRegistry;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/A4;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class A4 extends Fragment {
    public final Lazy a;
    public final Lazy b;

    public A4() {
        Function0 a = Gd.a(new Ad(this), Pa.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Jc.class);
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (transactionRegistry.isTransactionFailure$lib_release(requireActivity)) {
            return;
        }
        ((Jc) this.b.getValue()).k.postValue(Unit.INSTANCE);
    }
}
