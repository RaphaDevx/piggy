package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.md, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0181md implements ViewModelProvider.Factory {
    public final Nb a;

    public C0181md(Nb transactionModel) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(C0176ld.class)) {
            return new C0176ld(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
