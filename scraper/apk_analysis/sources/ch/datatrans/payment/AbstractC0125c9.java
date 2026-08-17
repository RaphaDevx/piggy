package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.c9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0125c9 {
    public static final b9 a(b9 b9Var, E5 transaction) {
        Intrinsics.checkNotNullParameter(b9Var, "<this>");
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        if (!((List) transaction.f.getValue()).contains(b9Var.a())) {
            SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) transaction.h.getValue();
            if ((savedPaymentMethod != null ? savedPaymentMethod.getType() : null) != b9Var.a()) {
                return null;
            }
        }
        return b9Var;
    }
}
