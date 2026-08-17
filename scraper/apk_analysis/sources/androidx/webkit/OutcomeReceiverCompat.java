package androidx.webkit;

import java.lang.Throwable;

/* loaded from: classes3.dex */
public interface OutcomeReceiverCompat<T, E extends Throwable> {
    default void onError(E e) {
    }

    void onResult(T t);
}
