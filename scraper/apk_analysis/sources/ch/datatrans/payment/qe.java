package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class qe extends Lambda implements Function0 {
    public final /* synthetic */ pe a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qe(pe peVar) {
        super(0);
        this.a = peVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
