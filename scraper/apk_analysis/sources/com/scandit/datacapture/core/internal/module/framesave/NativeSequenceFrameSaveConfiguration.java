package com.scandit.datacapture.core.internal.module.framesave;

import com.scandit.datacapture.core.framesave.FileFormat;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSequenceFrameSaveConfiguration {

    public static final class CppProxy extends NativeSequenceFrameSaveConfiguration {
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

    public static native NativeSequenceFrameSaveConfiguration forLocalStorage(String str, String str2, FileFormat fileFormat, String str3);

    public static native NativeSequenceFrameSaveConfiguration forRemoteStagingStorage(String str, FileFormat fileFormat, String str2);

    public static native NativeSequenceFrameSaveConfiguration forRemoteStorage(String str, FileFormat fileFormat, String str2);
}
