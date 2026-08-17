package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: ch.datatrans.payment.k9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0168k9 extends Lambda implements Function0 {
    public final /* synthetic */ C0164j9 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0168k9(C0164j9 c0164j9) {
        super(0);
        this.a = c0164j9;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
