package ch.datatrans.payment;

import ch.datatrans.payment.bottomsheet.TransactionSheetActivity;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class qg {
    public static void a(Function1 function1, Xa xa, TransactionSheetActivity transactionSheetActivity) {
        xa.observe(transactionSheetActivity, new Ub(function1));
    }
}
