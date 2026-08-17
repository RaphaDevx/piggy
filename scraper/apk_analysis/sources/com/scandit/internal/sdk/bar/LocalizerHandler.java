package com.scandit.internal.sdk.bar;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class LocalizerHandler {

    public static final class CppProxy extends LocalizerHandler {
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

    public static native Localizer getDefaultLocalizer();

    public static native void setDefaultLocalizer(Localizer localizer);
}
