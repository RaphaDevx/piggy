package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.Transaction;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Kc implements ViewModelProvider.Factory {
    public final Transaction a;
    public final N9 b;
    public final boolean c;

    public Kc(Transaction transaction, N9 n9, boolean z) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        this.a = transaction;
        this.b = n9;
        this.c = z;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(Jc.class)) {
            return new Jc(this.a, this.b, this.c);
        }
        throw new IllegalStateException("Check failed.");
    }
}
