package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class L4 extends Lambda implements Function0 {
    public final /* synthetic */ K4 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L4(K4 k4) {
        super(0);
        this.a = k4;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
