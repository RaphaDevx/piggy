package com.scandit.internal.sdk.bar;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class LoggerHandler {

    public static final class CppProxy extends LoggerHandler {
        private final long nativeRef;

        private CppProxy(long j) {
            new AtomicBoolean(false);
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);
    }

    public static native void addLogger(Logger logger);

    public static native void clearLoggers();

    public static native void debug(String str, String str2);

    public static native void error(String str, String str2);

    public static native void info(String str, String str2);

    public static native void log(LogPriority logPriority, String str, String str2);

    public static native void removeLogger(Logger logger);

    public static native void verbose(String str, String str2);

    public static native void warning(String str, String str2);

    public static native void wtf(String str, String str2);
}
