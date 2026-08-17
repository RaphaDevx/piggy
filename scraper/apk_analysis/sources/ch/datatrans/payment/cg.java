package ch.datatrans.payment;

import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class cg extends Lambda implements Function0 {
    public final /* synthetic */ bg a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cg(bg bgVar) {
        super(0);
        this.a = bgVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (ViewModelStoreOwner) this.a.invoke();
    }
}
