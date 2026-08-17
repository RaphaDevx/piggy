package ch.datatrans.payment;

import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.d7, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0128d7 extends AbstractSavedStateViewModelFactory {
    public final /* synthetic */ Nb a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0128d7(C0118c7 c0118c7, Nb nb) {
        super(c0118c7, null);
        this.a = nb;
    }

    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
    public final ViewModel create(String key, Class modelClass, SavedStateHandle handle) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(handle, "handle");
        if (modelClass.isAssignableFrom(k7.class)) {
            return new k7(this.a, handle);
        }
        throw new IllegalStateException("Check failed.");
    }
}
