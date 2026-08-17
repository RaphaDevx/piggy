package ch.datatrans.payment;

import androidx.lifecycle.ViewModelKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final /* synthetic */ class c5 extends FunctionReferenceImpl implements Function0 {
    public c5(i5 i5Var) {
        super(0, i5Var, i5.class, "startWalletRequest", "startWalletRequest()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        i5 i5Var = (i5) this.receiver;
        i5Var.getClass();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(i5Var), null, null, new C0154h5(i5Var, null), 3, null);
        return Unit.INSTANCE;
    }
}
