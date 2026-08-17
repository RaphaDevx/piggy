package ch.datatrans.payment;

import ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class S7 extends Lambda implements Function0 {
    public final /* synthetic */ PCIPTokenizationRequestSheetActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S7(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity) {
        super(0);
        this.a = pCIPTokenizationRequestSheetActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getViewModelStore();
    }
}
