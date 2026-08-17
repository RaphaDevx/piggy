package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.j5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0163j5 implements ViewModelProvider.Factory {
    public final Nb a;

    public C0163j5(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(i5.class)) {
            return new i5(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
