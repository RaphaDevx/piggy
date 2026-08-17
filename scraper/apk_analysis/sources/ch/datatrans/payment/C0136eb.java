package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: ch.datatrans.payment.eb, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0136eb extends Lambda implements Function0 {
    public final /* synthetic */ C0129db a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0136eb(C0129db c0129db) {
        super(0);
        this.a = c0129db;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
