package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class T8 implements ViewModelProvider.Factory {
    public final Nb a;

    public T8(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(S8.class)) {
            return new S8(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
