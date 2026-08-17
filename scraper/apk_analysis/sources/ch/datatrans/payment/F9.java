package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class F9 implements ViewModelProvider.Factory {
    public final Nb a;

    public F9(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(E9.class)) {
            return new E9(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
