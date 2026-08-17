package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: ch.datatrans.payment.o1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0191o1 extends Lambda implements Function0 {
    public final /* synthetic */ C0257z1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0191o1(C0257z1 c0257z1) {
        super(0);
        this.a = c0257z1;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
