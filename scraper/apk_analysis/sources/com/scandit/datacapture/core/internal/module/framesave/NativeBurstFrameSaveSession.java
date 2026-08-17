package com.scandit.datacapture.core.internal.module.framesave;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBurstFrameSaveSession {

    public static final class CppProxy extends NativeBurstFrameSaveSession {
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

        private native void native_addListenerAsync(long j, NativeFrameSaveSessionListener nativeFrameSaveSessionListener);

        private native void native_addToContext(long j, NativeDataCaptureContext nativeDataCaptureContext);

        private native NativeFrameSaveSession native_asFrameSaveSession(long j);

        private native void native_disable(long j);

        private native boolean native_empty(long j);

        private native void native_enable(long j);

        private native void native_flushPendingTasks(long j);

        private native boolean native_isCollectingFrames(long j);

        private native void native_removeFromContext(long j, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_removeListenerAsync(long j, NativeFrameSaveSessionListener nativeFrameSaveSessionListener);

        private native void native_save(long j);

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void addListenerAsync(NativeFrameSaveSessionListener nativeFrameSaveSessionListener) {
            native_addListenerAsync(this.nativeRef, nativeFrameSaveSessionListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void addToContext(NativeDataCaptureContext nativeDataCaptureContext) {
            native_addToContext(this.nativeRef, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public NativeFrameSaveSession asFrameSaveSession() {
            return native_asFrameSaveSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void disable() {
            native_disable(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public boolean empty() {
            return native_empty(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void enable() {
            native_enable(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void flushPendingTasks() {
            native_flushPendingTasks(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public boolean isCollectingFrames() {
            return native_isCollectingFrames(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void removeFromContext(NativeDataCaptureContext nativeDataCaptureContext) {
            native_removeFromContext(this.nativeRef, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void removeListenerAsync(NativeFrameSaveSessionListener nativeFrameSaveSessionListener) {
            native_removeListenerAsync(this.nativeRef, nativeFrameSaveSessionListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBurstFrameSaveSession
        public void save() {
            native_save(this.nativeRef);
        }
    }

    public static native NativeBurstFrameSaveSession create(NativeBurstFrameSaveConfiguration nativeBurstFrameSaveConfiguration);

    public abstract void addListenerAsync(NativeFrameSaveSessionListener nativeFrameSaveSessionListener);

    public abstract void addToContext(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract NativeFrameSaveSession asFrameSaveSession();

    public abstract void disable();

    public abstract boolean empty();

    public abstract void enable();

    public abstract void flushPendingTasks();

    public abstract boolean isCollectingFrames();

    public abstract void removeFromContext(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void removeListenerAsync(NativeFrameSaveSessionListener nativeFrameSaveSessionListener);

    public abstract void save();
}
