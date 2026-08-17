package ch.datatrans.payment;

import androidx.lifecycle.ViewModelKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final /* synthetic */ class Ea extends FunctionReferenceImpl implements Function0 {
    public Ea(Ka ka) {
        super(0, ka, Ka.class, "startWalletRequest", "startWalletRequest()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Ka ka = (Ka) this.receiver;
        ka.getClass();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(ka), null, null, new Ja(ka, null), 3, null);
        return Unit.INSTANCE;
    }
}
