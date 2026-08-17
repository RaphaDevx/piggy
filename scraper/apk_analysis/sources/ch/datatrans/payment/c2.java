package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class c2 extends Lambda implements Function0 {
    public final /* synthetic */ C0115b2 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c2(C0115b2 c0115b2) {
        super(0);
        this.a = c0115b2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
