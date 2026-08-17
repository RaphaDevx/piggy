package io.sentry.util;

import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;

/* loaded from: classes3.dex */
public class LoadClass {
    public Class<?> loadClass(String str, ILogger iLogger) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            if (iLogger == null) {
                return null;
            }
            iLogger.log(SentryLevel.INFO, "Class not available: " + str, new Object[0]);
            return null;
        } catch (UnsatisfiedLinkError e) {
            if (iLogger == null) {
                return null;
            }
            iLogger.log(SentryLevel.ERROR, "Failed to load (UnsatisfiedLinkError) " + str, e);
            return null;
        } catch (Throwable th) {
            if (iLogger == null) {
                return null;
            }
            iLogger.log(SentryLevel.ERROR, "Failed to initialize " + str, th);
            return null;
        }
    }

    public boolean isClassAvailable(String str, ILogger iLogger) {
        return loadClass(str, iLogger) != null;
    }

    public boolean isClassAvailable(String str, SentryOptions sentryOptions) {
        return isClassAvailable(str, sentryOptions != null ? sentryOptions.getLogger() : null);
    }
}
