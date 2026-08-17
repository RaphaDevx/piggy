package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class V5 extends Lambda implements Function0 {
    public final /* synthetic */ U5 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V5(U5 u5) {
        super(0);
        this.a = u5;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
