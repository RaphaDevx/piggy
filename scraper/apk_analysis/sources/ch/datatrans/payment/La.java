package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class La implements ViewModelProvider.Factory {
    public final Nb a;

    public La(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(Ka.class)) {
            return new Ka(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
