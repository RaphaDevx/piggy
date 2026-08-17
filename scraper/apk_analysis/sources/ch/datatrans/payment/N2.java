package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class N2 implements ViewModelProvider.Factory {
    public final Nb a;
    public final R9 b;

    public N2(Nb transactionModel, R9 preferencesRepository) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.a = transactionModel;
        this.b = preferencesRepository;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(M2.class)) {
            return new M2(this.a, this.b);
        }
        throw new IllegalStateException("Check failed.");
    }
}
