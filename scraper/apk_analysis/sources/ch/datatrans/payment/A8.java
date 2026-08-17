package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class A8 extends Lambda implements Function0 {
    public final /* synthetic */ C0259z8 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A8(C0259z8 c0259z8) {
        super(0);
        this.a = c0259z8;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
