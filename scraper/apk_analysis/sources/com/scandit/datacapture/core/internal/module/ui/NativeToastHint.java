package com.scandit.datacapture.core.internal.module.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeToastHint {

    public static final class CppProxy extends NativeToastHint {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native NativeHintStyle native_getHintStyle(long j);

        private native String native_getTag(long j);

        private native String native_getText(long j);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeToastHint
        public NativeHintStyle getHintStyle() {
            return native_getHintStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeToastHint
        public String getTag() {
            return native_getTag(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeToastHint
        public String getText() {
            return native_getText(this.nativeRef);
        }
    }

    public static native void applyToastHintStyle(NativeToastHintStyle nativeToastHintStyle, NativeHintStyleBuilder nativeHintStyleBuilder);

    public static native NativeToastHint create(NativeToastHintStyle nativeToastHintStyle, String str, String str2, boolean z);

    public static native NativeToastHint createWithHintStyle(NativeHintStyle nativeHintStyle, String str, String str2);

    public abstract NativeHintStyle getHintStyle();

    public abstract String getTag();

    public abstract String getText();
}
