package com.scandit.datacapture.core.source;

import android.media.Image;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.internal.module.common.geometry.NativeAxis;
import com.scandit.datacapture.core.internal.module.source.BufferStack;
import com.scandit.datacapture.core.internal.module.source.N;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.module.source.b0;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.core.internal.sdk.extensions.ImageExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeFrameDataGeneratorFrameSource;
import com.scandit.datacapture.core.source.SequenceFrameSource;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 12\u00020\u00012\u00020\u0002:\u0003123B?\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ(\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0096\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\r2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\r2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b$\u0010#J%\u0010(\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\f¢\u0006\u0004\b(\u0010)J\u0015\u0010(\u001a\u00020\r2\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b(\u0010,R\u0014\u0010/\u001a\u00020\u00168WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0014\u0010\u0017\u001a\u00020\u00168WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b0\u0010.¨\u00064"}, d2 = {"Lcom/scandit/datacapture/core/source/SequenceFrameSource;", "Lcom/scandit/datacapture/core/source/FrameSource;", "Lcom/scandit/datacapture/core/source/SequenceFrameSourceProxy;", "Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataGeneratorFrameSource;", "impl", "Lcom/scandit/datacapture/core/source/CameraPosition;", "captureDevicePosition", "", "captureDeviceOrientation", "Lcom/scandit/datacapture/core/internal/module/source/BufferStack;", "buffers", "Lkotlin/Function1;", "", "", "bufferProcessingFinished", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataGeneratorFrameSource;Lcom/scandit/datacapture/core/source/CameraPosition;ILcom/scandit/datacapture/core/internal/module/source/BufferStack;Lkotlin/jvm/functions/Function1;)V", "Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "_frameSourceImpl", "()Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/source/NativeFrameDataGeneratorFrameSource;", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "desiredState", "Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_switchToDesiredState", "(Lcom/scandit/datacapture/core/source/FrameSourceState;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "Lcom/scandit/datacapture/core/common/async/Callback;", "", "whenDone", "switchToDesiredState", "(Lcom/scandit/datacapture/core/source/FrameSourceState;Lcom/scandit/datacapture/core/common/async/Callback;)V", "Lcom/scandit/datacapture/core/source/FrameSourceListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/core/source/FrameSourceListener;)V", "removeListener", "width", "height", "buffer", "addFrame", "(II[B)V", "Landroid/media/Image;", TypedValues.AttributesType.S_FRAME, "(Landroid/media/Image;)V", "getCurrentState", "()Lcom/scandit/datacapture/core/source/FrameSourceState;", "currentState", "getDesiredState", "Companion", "com/scandit/datacapture/core/source/g", "com/scandit/datacapture/core/source/h", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class SequenceFrameSource implements FrameSource, SequenceFrameSourceProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CameraPosition a;
    private final int b;
    private final BufferStack c;
    private final Function1 d;
    private final /* synthetic */ SequenceFrameSourceProxyAdapter e;
    private final LinkedHashMap f;
    private final h g;
    private final CopyOnWriteArraySet h;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0005\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/core/source/SequenceFrameSource$Companion;", "", "Lcom/scandit/datacapture/core/source/CameraPosition;", "cameraPosition", "Lcom/scandit/datacapture/core/source/SequenceFrameSource;", "create", "(Lcom/scandit/datacapture/core/source/CameraPosition;)Lcom/scandit/datacapture/core/source/SequenceFrameSource;", "", "captureDeviceOrientation", "Lcom/scandit/datacapture/core/source/SequenceFrameSourceProcessListener;", "sequenceFrameSourceProcess", "(Lcom/scandit/datacapture/core/source/CameraPosition;ILcom/scandit/datacapture/core/source/SequenceFrameSourceProcessListener;)Lcom/scandit/datacapture/core/source/SequenceFrameSource;", "BUFFER_STACK_SIZE", "I", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(byte[] it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }

        @JvmStatic
        public final SequenceFrameSource create(CameraPosition cameraPosition) {
            Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
            return create(cameraPosition, 90, new SequenceFrameSourceProcessListener() { // from class: com.scandit.datacapture.core.source.SequenceFrameSource$Companion$$ExternalSyntheticLambda0
                @Override // com.scandit.datacapture.core.source.SequenceFrameSourceProcessListener
                public final void processingFinished(byte[] bArr) {
                    SequenceFrameSource.Companion.a(bArr);
                }
            });
        }

        @JvmStatic
        public final SequenceFrameSource create(CameraPosition cameraPosition, int captureDeviceOrientation, SequenceFrameSourceProcessListener sequenceFrameSourceProcess) {
            Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
            Intrinsics.checkNotNullParameter(sequenceFrameSourceProcess, "sequenceFrameSourceProcess");
            NativeFrameDataGeneratorFrameSource create = NativeFrameDataGeneratorFrameSource.create();
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return new SequenceFrameSource(create, cameraPosition, captureDeviceOrientation, null, new f(sequenceFrameSourceProcess), 8, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SequenceFrameSource(NativeFrameDataGeneratorFrameSource impl, CameraPosition captureDevicePosition, int i, BufferStack buffers, Function1<? super byte[], Unit> bufferProcessingFinished) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(captureDevicePosition, "captureDevicePosition");
        Intrinsics.checkNotNullParameter(buffers, "buffers");
        Intrinsics.checkNotNullParameter(bufferProcessingFinished, "bufferProcessingFinished");
        this.a = captureDevicePosition;
        this.b = i;
        this.c = buffers;
        this.d = bufferProcessingFinished;
        this.e = new SequenceFrameSourceProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.f = new LinkedHashMap();
        this.g = new h(this);
        this.h = new CopyOnWriteArraySet();
        impl.addListenerAsync(new b0(new g(this), this));
    }

    public static final void access$returnToPool(SequenceFrameSource sequenceFrameSource, NativeCameraFrameData nativeCameraFrameData) {
        sequenceFrameSource.f.remove(nativeCameraFrameData);
        byte[] takeBuffer = nativeCameraFrameData.takeBuffer();
        if (takeBuffer != null) {
            sequenceFrameSource.d.invoke(takeBuffer);
            sequenceFrameSource.c.a(takeBuffer);
        }
    }

    @JvmStatic
    public static final SequenceFrameSource create(CameraPosition cameraPosition) {
        return INSTANCE.create(cameraPosition);
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @NativeImpl
    public NativeFrameSource _frameSourceImpl() {
        return this.e.getC();
    }

    @Override // com.scandit.datacapture.core.source.SequenceFrameSourceProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeFrameDataGeneratorFrameSource getA() {
        return this.e.getA();
    }

    @Override // com.scandit.datacapture.core.source.SequenceFrameSourceProxy
    @ProxyFunction(nativeName = "switchToDesiredStateAsyncAndroid")
    public NativeWrappedFuture _switchToDesiredState(FrameSourceState desiredState) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        return this.e._switchToDesiredState(desiredState);
    }

    public final void addFrame(int width, int height, byte[] buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.length != ((width * height) * 12) / 8) {
            throw new AssertionError("Invalid image format: only NV21 format allowed");
        }
        NativeCameraFrameData create = NativeCameraFrameData.create(width, height, buffer, this.g, this.b, this.a == CameraPosition.USER_FACING ? NativeAxis.X : NativeAxis.NONE, NativeCameraCaptureParameters.create(), null);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        LinkedHashMap linkedHashMap = this.f;
        NativeFrameData asFrameData = create.asFrameData();
        Intrinsics.checkNotNullExpressionValue(asFrameData, "asFrameData(...)");
        linkedHashMap.put(create, asFrameData);
        getA().addFrame((NativeFrameData) this.f.get(create));
    }

    @Override // com.scandit.datacapture.core.source.FrameSource
    public void addListener(FrameSourceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.h.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @ProxyFunction(property = "currentState")
    public FrameSourceState getCurrentState() {
        return this.e.getCurrentState();
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @ProxyFunction(property = "desiredState")
    public FrameSourceState getDesiredState() {
        return this.e.getDesiredState();
    }

    @Override // com.scandit.datacapture.core.source.FrameSource
    public void removeListener(FrameSourceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.h.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public void switchToDesiredState(FrameSourceState desiredState, Callback<? super Boolean> whenDone) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        this.e.switchToDesiredState(desiredState, whenDone);
    }

    @JvmStatic
    public static final SequenceFrameSource create(CameraPosition cameraPosition, int i, SequenceFrameSourceProcessListener sequenceFrameSourceProcessListener) {
        return INSTANCE.create(cameraPosition, i, sequenceFrameSourceProcessListener);
    }

    public final void addFrame(Image frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        if (frame.getFormat() == 35) {
            NativeCameraFrameData convertToFrameData$default = ImageExtensionsKt.convertToFrameData$default(frame, this.c, this.g, this.b, this.a == CameraPosition.USER_FACING, false, null, 32, null);
            if (convertToFrameData$default != null) {
                LinkedHashMap linkedHashMap = this.f;
                NativeFrameData asFrameData = convertToFrameData$default.asFrameData();
                Intrinsics.checkNotNullExpressionValue(asFrameData, "asFrameData(...)");
                linkedHashMap.put(convertToFrameData$default, asFrameData);
                getA().addFrame((NativeFrameData) this.f.get(convertToFrameData$default));
                return;
            }
            return;
        }
        throw new AssertionError("Invalid image format: only YUV_420_888 format allowed");
    }

    public /* synthetic */ SequenceFrameSource(NativeFrameDataGeneratorFrameSource nativeFrameDataGeneratorFrameSource, CameraPosition cameraPosition, int i, BufferStack bufferStack, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeFrameDataGeneratorFrameSource, cameraPosition, i, (i2 & 8) != 0 ? new N() : bufferStack, function1);
    }
}
