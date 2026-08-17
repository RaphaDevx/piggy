package ch.datatrans.payment;

import ch.datatrans.payment.bottomsheet.TransactionSheetActivity;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class rg extends Lambda implements Function0 {
    public final /* synthetic */ TransactionSheetActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rg(TransactionSheetActivity transactionSheetActivity) {
        super(0);
        this.a = transactionSheetActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getDefaultViewModelProviderFactory();
    }
}
