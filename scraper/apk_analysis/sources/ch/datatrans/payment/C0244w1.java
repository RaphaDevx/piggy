package ch.datatrans.payment;

import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: ch.datatrans.payment.w1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0244w1 extends Lambda implements Function0 {
    public final /* synthetic */ Lazy a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0244w1(Lazy lazy) {
        super(0);
        this.a = lazy;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ViewModelStoreOwner m3406viewModels$lambda1;
        m3406viewModels$lambda1 = FragmentViewModelLazyKt.m3406viewModels$lambda1(this.a);
        return m3406viewModels$lambda1.getViewModelStore();
    }
}
