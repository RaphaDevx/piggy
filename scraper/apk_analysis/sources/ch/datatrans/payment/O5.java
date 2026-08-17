package ch.datatrans.payment;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class O5 implements ViewModelProvider.Factory {
    public final Application a;
    public final Nb b;

    public O5(Application application, Nb transactionModel) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = application;
        this.b = transactionModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(N5.class)) {
            return new N5(this.a, this.b);
        }
        throw new IllegalStateException("Check failed.");
    }
}
