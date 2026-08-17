package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* renamed from: ch.datatrans.payment.ga, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0149ga {
    public final CoroutineScope a;
    public final ViewModel b;

    /* JADX WARN: Multi-variable type inference failed */
    public C0149ga(CoroutineScope scope, InterfaceC0141f4 exceptionHandler) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(exceptionHandler, "exceptionHandler");
        this.a = scope;
        this.b = (ViewModel) exceptionHandler;
    }
}
