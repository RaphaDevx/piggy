package io.sentry.util;

import java.util.Set;

/* loaded from: classes3.dex */
public final class ExceptionUtils {
    public static Throwable findRootCause(Throwable th) {
        Objects.requireNonNull(th, "throwable cannot be null");
        while (th.getCause() != null && th.getCause() != th) {
            th = th.getCause();
        }
        return th;
    }

    public static boolean isIgnored(Set<Class<? extends Throwable>> set, Throwable th) {
        return set.contains(th.getClass());
    }
}
