package ch.datatrans.payment;

import ch.datatrans.payment.exception.BackendException;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class S8 extends D4 implements Be {
    public static final /* synthetic */ int j = 0;
    public final Xa h;
    public final Xa i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S8(Nb transactionModel) {
        super(C4.c, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.h = new Xa();
        this.i = new Xa();
    }

    @Override // ch.datatrans.payment.Be
    public final void a(BackendException e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // ch.datatrans.payment.Be
    public final void c() {
        this.c.postValue(null);
    }

    @Override // ch.datatrans.payment.Be
    public final void a(String str, Map parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
    }
}
