package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.component.NativeDataCaptureComponent;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContextDeserializerResult {

    public static final class CppProxy extends NativeDataCaptureContextDeserializerResult {
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

        private native ArrayList<NativeDataCaptureComponent> native_getComponents(long j);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeDataCaptureView native_getView(long j);

        private native ArrayList<String> native_getWarnings(long j);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult
        public ArrayList<NativeDataCaptureComponent> getComponents() {
            return native_getComponents(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult
        public NativeDataCaptureView getView() {
            return native_getView(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeDataCaptureComponent> getComponents();

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeDataCaptureView getView();

    public abstract ArrayList<String> getWarnings();
}
