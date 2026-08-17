package io.sentry.util;

import androidx.compose.material.TextFieldImplKt;
import io.sentry.ILogger;
import io.sentry.SentryLevel;

/* loaded from: classes3.dex */
public final class LogUtils {
    public static void logNotInstanceOf(Class<?> cls, Object obj, ILogger iLogger) {
        iLogger.log(SentryLevel.DEBUG, "%s is not %s", obj != null ? obj.getClass().getCanonicalName() : TextFieldImplKt.PlaceholderId, cls.getCanonicalName());
    }
}
