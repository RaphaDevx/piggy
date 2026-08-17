package ch.datatrans.payment;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.x8, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0253x8 implements ViewModelProvider.Factory {
    public final Application a;

    public C0253x8(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.a = application;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(C0247w8.class)) {
            return new C0247w8(this.a);
        }
        throw new IllegalStateException("Check failed.");
    }
}
