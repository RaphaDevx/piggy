package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class T1 implements ViewModelProvider.Factory {
    public final U1 a;
    public final C0205r0 b;
    public final W0 c;
    public final C3 d;
    public final C0219t0 e;

    public T1(U1 dateModel, C0205r0 cvvModel, W0 dccModel, C3 numberModel, C0219t0 c0219t0) {
        Intrinsics.checkNotNullParameter(dateModel, "dateModel");
        Intrinsics.checkNotNullParameter(cvvModel, "cvvModel");
        Intrinsics.checkNotNullParameter(dccModel, "dccModel");
        Intrinsics.checkNotNullParameter(numberModel, "numberModel");
        this.a = dateModel;
        this.b = cvvModel;
        this.c = dccModel;
        this.d = numberModel;
        this.e = c0219t0;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(S1.class)) {
            return new S1(this.a, this.b, this.c, this.d, this.e);
        }
        throw new IllegalStateException("Check failed.");
    }
}
