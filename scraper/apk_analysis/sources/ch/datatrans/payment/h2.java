package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class h2 extends Lambda implements Function0 {
    public final /* synthetic */ C0145g2 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h2(C0145g2 c0145g2) {
        super(0);
        this.a = c0145g2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
