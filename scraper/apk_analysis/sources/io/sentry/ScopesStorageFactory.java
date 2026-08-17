package io.sentry;

import io.sentry.util.LoadClass;
import io.sentry.util.Platform;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public final class ScopesStorageFactory {
    private static final String OTEL_SCOPES_STORAGE = "io.sentry.opentelemetry.OtelContextScopesStorage";

    public static IScopesStorage create(LoadClass loadClass, ILogger iLogger) {
        IScopesStorage createInternal = createInternal(loadClass, iLogger);
        createInternal.init();
        return createInternal;
    }

    private static IScopesStorage createInternal(LoadClass loadClass, ILogger iLogger) {
        Class<?> loadClass2;
        if (Platform.isJvm() && loadClass.isClassAvailable(OTEL_SCOPES_STORAGE, iLogger) && (loadClass2 = loadClass.loadClass(OTEL_SCOPES_STORAGE, iLogger)) != null) {
            try {
                Object newInstance = loadClass2.getDeclaredConstructor(null).newInstance(null);
                if (newInstance != null && (newInstance instanceof IScopesStorage)) {
                    return (IScopesStorage) newInstance;
                }
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
        return new DefaultScopesStorage();
    }
}
