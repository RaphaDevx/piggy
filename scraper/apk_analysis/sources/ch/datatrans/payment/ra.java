package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class ra extends Lambda implements Function0 {
    public final /* synthetic */ C0203qa a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ra(C0203qa c0203qa) {
        super(0);
        this.a = c0203qa;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
