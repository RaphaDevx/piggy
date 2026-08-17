package io.sentry;

import io.sentry.util.LoadClass;
import io.sentry.util.Platform;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public final class SpanFactoryFactory {
    private static final String OTEL_SPAN_FACTORY = "io.sentry.opentelemetry.OtelSpanFactory";

    public static ISpanFactory create(LoadClass loadClass, ILogger iLogger) {
        Class<?> loadClass2;
        if (Platform.isJvm() && loadClass.isClassAvailable(OTEL_SPAN_FACTORY, iLogger) && (loadClass2 = loadClass.loadClass(OTEL_SPAN_FACTORY, iLogger)) != null) {
            try {
                Object newInstance = loadClass2.getDeclaredConstructor(null).newInstance(null);
                if (newInstance != null && (newInstance instanceof ISpanFactory)) {
                    return (ISpanFactory) newInstance;
                }
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
        return new DefaultSpanFactory();
    }
}
