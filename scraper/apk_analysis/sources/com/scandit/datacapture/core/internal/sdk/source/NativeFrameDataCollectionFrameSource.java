package com.scandit.datacapture.core.internal.sdk.source;

import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeFrameDataCollectionFrameSource {

    public static final class CppProxy extends NativeFrameDataCollectionFrameSource {
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

        private native void native_addListenerAsync(long j, NativeFrameSourceListener nativeFrameSourceListener);

        private native NativeFrameSource native_asFrameSource(long j);

        private native void native_flushPendingTasks(long j);

        private native FrameSourceState native_getCurrentState(long j);

        private native FrameSourceState native_getDesiredState(long j);

        private native void native_removeListenerAsync(long j, NativeFrameSourceListener nativeFrameSourceListener);

        private native void native_setRepeatAsync(long j, boolean z);

        private native NativeWrappedFuture native_switchToDesiredStateAsyncAndroid(long j, FrameSourceState frameSourceState);

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public void addListenerAsync(NativeFrameSourceListener nativeFrameSourceListener) {
            native_addListenerAsync(this.nativeRef, nativeFrameSourceListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public NativeFrameSource asFrameSource() {
            return native_asFrameSource(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public void flushPendingTasks() {
            native_flushPendingTasks(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public FrameSourceState getCurrentState() {
            return native_getCurrentState(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public FrameSourceState getDesiredState() {
            return native_getDesiredState(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public void removeListenerAsync(NativeFrameSourceListener nativeFrameSourceListener) {
            native_removeListenerAsync(this.nativeRef, nativeFrameSourceListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public void setRepeatAsync(boolean z) {
            native_setRepeatAsync(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataCollectionFrameSource
        public NativeWrappedFuture switchToDesiredStateAsyncAndroid(FrameSourceState frameSourceState) {
            return native_switchToDesiredStateAsyncAndroid(this.nativeRef, frameSourceState);
        }
    }

    public static native NativeFrameDataCollectionFrameSource create(NativeSingleBitmapFrameDataCollection nativeSingleBitmapFrameDataCollection);

    public abstract void addListenerAsync(NativeFrameSourceListener nativeFrameSourceListener);

    public abstract NativeFrameSource asFrameSource();

    public abstract void flushPendingTasks();

    public abstract FrameSourceState getCurrentState();

    public abstract FrameSourceState getDesiredState();

    public abstract void removeListenerAsync(NativeFrameSourceListener nativeFrameSourceListener);

    public abstract void setRepeatAsync(boolean z);

    public abstract NativeWrappedFuture switchToDesiredStateAsyncAndroid(FrameSourceState frameSourceState);
}
