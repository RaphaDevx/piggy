package ch.datatrans.payment;

import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class Vf extends Lambda implements Function0 {
    public final /* synthetic */ C0118c7 a;
    public final /* synthetic */ Lazy b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Vf(C0118c7 c0118c7, Lazy lazy) {
        super(0);
        this.a = c0118c7;
        this.b = lazy;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ViewModelStoreOwner m3406viewModels$lambda1;
        ViewModelProvider.Factory defaultViewModelProviderFactory;
        m3406viewModels$lambda1 = FragmentViewModelLazyKt.m3406viewModels$lambda1(this.b);
        HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m3406viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m3406viewModels$lambda1 : null;
        return (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) ? this.a.getDefaultViewModelProviderFactory() : defaultViewModelProviderFactory;
    }
}
