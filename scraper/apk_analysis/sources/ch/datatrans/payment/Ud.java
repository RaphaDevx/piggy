package ch.datatrans.payment;

import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Ud extends AbstractSavedStateViewModelFactory {
    public final /* synthetic */ Nb a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ud(Td td, Nb nb) {
        super(td, null);
        this.a = nb;
    }

    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
    public final ViewModel create(String key, Class modelClass, SavedStateHandle handle) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(handle, "handle");
        if (modelClass.isAssignableFrom(Zd.class)) {
            return new Zd(this.a, handle);
        }
        throw new IllegalStateException("Check failed.");
    }
}
