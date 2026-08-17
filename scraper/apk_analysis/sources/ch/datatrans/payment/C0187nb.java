package ch.datatrans.payment;

import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.nb, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0187nb extends AbstractSavedStateViewModelFactory {
    public final /* synthetic */ Nb a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0187nb(mb mbVar, Nb nb) {
        super(mbVar, null);
        this.a = nb;
    }

    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
    public final ViewModel create(String key, Class modelClass, SavedStateHandle handle) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Intrinsics.checkNotNullParameter(handle, "handle");
        if (!modelClass.isAssignableFrom(C0217sb.class)) {
            throw new IllegalStateException("Check failed.");
        }
        Nb nb = this.a;
        return new C0217sb(nb, nb.h.v, handle);
    }
}
