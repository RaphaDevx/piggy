package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.u6, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0231u6 implements ViewModelProvider.Factory {
    public final Nb a;

    public C0231u6(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(C0221t6.class)) {
            return new C0221t6(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
