package io.sentry.util;

/* loaded from: classes3.dex */
public final class ClassLoaderUtils {
    public static ClassLoader classLoaderOrDefault(ClassLoader classLoader) {
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader != null ? contextClassLoader : ClassLoader.getSystemClassLoader();
    }
}
