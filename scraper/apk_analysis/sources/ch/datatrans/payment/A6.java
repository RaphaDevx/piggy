package ch.datatrans.payment;

import androidx.lifecycle.LifecycleCoroutineScope;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class A6 {
    public final R9 a;
    public final CoroutineScope b;
    public final CoroutineDispatcher c;

    public A6(R9 preferencesRepository, LifecycleCoroutineScope scope) {
        CoroutineDispatcher dispatcher = Dispatchers.getIO();
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.a = preferencesRepository;
        this.b = scope;
        this.c = dispatcher;
    }
}
