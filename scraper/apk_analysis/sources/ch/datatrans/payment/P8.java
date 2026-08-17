package ch.datatrans.payment;

import androidx.lifecycle.ViewModelKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final /* synthetic */ class P8 extends FunctionReferenceImpl implements Function0 {
    public P8(Object obj) {
        super(0, obj, S8.class, "startAliasRequest", "startAliasRequest()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        S8 s8 = (S8) this.receiver;
        int i = S8.j;
        s8.getClass();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(s8), null, null, new R8(s8, null), 3, null);
        return Unit.INSTANCE;
    }
}
