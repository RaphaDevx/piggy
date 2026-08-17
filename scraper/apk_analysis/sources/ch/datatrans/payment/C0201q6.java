package ch.datatrans.payment;

import androidx.lifecycle.ViewModelKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: ch.datatrans.payment.q6, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final /* synthetic */ class C0201q6 extends FunctionReferenceImpl implements Function0 {
    public C0201q6(Object obj) {
        super(0, obj, C0221t6.class, "startTokenRequest", "startTokenRequest()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0221t6 c0221t6 = (C0221t6) this.receiver;
        int i = C0221t6.l;
        c0221t6.getClass();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0221t6), null, null, new C0215s6(c0221t6, null), 3, null);
        return Unit.INSTANCE;
    }
}
