package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeStructDeserializer {

    public static final class CppProxy extends NativeStructDeserializer {
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

    public static native NativeBrush brushFromJson(NativeJsonValue nativeJsonValue);

    public static native Point pointFromJson(NativeJsonValue nativeJsonValue);

    public static native Quadrilateral quadrilateralFromJson(NativeJsonValue nativeJsonValue);
}
