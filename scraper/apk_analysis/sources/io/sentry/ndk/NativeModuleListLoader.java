package io.sentry.ndk;

/* loaded from: classes2.dex */
public final class NativeModuleListLoader {
    public static native void nativeClearModuleList();

    public static native DebugImage[] nativeLoadModuleList();

    public DebugImage[] loadModuleList() {
        return nativeLoadModuleList();
    }

    public void clearModuleList() {
        nativeClearModuleList();
    }
}
