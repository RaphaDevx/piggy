package com.scandit.datacapture.core.internal.sdk.extensions;

import android.media.Image;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.common.graphic.Channel;
import com.scandit.datacapture.core.internal.module.common.geometry.NativeAxis;
import com.scandit.datacapture.core.internal.module.source.BufferStack;
import com.scandit.datacapture.core.internal.module.source.DirectByteBufferCache;
import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a^\u0010\u0015\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0019\b\u0002\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001aX\u0010\u0019\u001a\u0004\u0018\u00010\u0005*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0019\b\u0002\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u001c\u001a\u00020\u001b*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u001c\u0010\u001d\"\u0015\u0010 \u001a\u00020\u000b*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0018\u0010!\u001a\u00020\r*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;", "", "toNv21Bytes", "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;)[B", "Landroid/media/Image;", "Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameData;", "frameData", "Lcom/scandit/datacapture/core/internal/module/source/DirectByteBufferCache;", "directByteBufferCache", "Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameDataPool;", "pool", "", "captureDeviceOrientation", "", "isImageMirrored", "isTimestampRealtime", "Lkotlin/Function1;", "Lcom/scandit/datacapture/core/internal/sdk/data/NativeCameraCaptureParameters;", "", "Lkotlin/ExtensionFunctionType;", "initParameters", "updateFrameData", "(Landroid/media/Image;Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameData;Lcom/scandit/datacapture/core/internal/module/source/DirectByteBufferCache;Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameDataPool;IZZLkotlin/jvm/functions/Function1;)Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameData;", "Lcom/scandit/datacapture/core/internal/module/source/BufferStack;", "buffers", "convertToFrameData", "(Landroid/media/Image;Lcom/scandit/datacapture/core/internal/module/source/BufferStack;Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameDataPool;IZZLkotlin/jvm/functions/Function1;)Lcom/scandit/datacapture/core/internal/sdk/source/NativeCameraFrameData;", "Lcom/scandit/datacapture/core/internal/sdk/extensions/ImageData;", "toImageData", "(Landroid/media/Image;Lcom/scandit/datacapture/core/internal/module/source/DirectByteBufferCache;)Lcom/scandit/datacapture/core/internal/sdk/extensions/ImageData;", "getNv21BytesSize", "(Lcom/scandit/datacapture/core/internal/sdk/common/geometry/NativeImageBuffer;)I", "nv21BytesSize", "isUsingDirectBuffers", "(Landroid/media/Image;)Z", "scandit-capture-core"}, k = 2, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ImageExtensionsKt {
    private static final ByteBuffer a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byteBuffer2.capacity();
        byteBuffer.capacity();
        byteBuffer.position(0);
        byteBuffer2.position(0);
        byteBuffer.put(byteBuffer2);
        return byteBuffer;
    }

    public static final NativeCameraFrameData convertToFrameData(Image image, BufferStack buffers, NativeCameraFrameDataPool pool, int i, boolean z, boolean z2, Function1<? super NativeCameraCaptureParameters, Unit> initParameters) {
        Intrinsics.checkNotNullParameter(image, "<this>");
        Intrinsics.checkNotNullParameter(buffers, "buffers");
        Intrinsics.checkNotNullParameter(pool, "pool");
        Intrinsics.checkNotNullParameter(initParameters, "initParameters");
        Long valueOf = z2 ? Long.valueOf(image.getTimestamp()) : null;
        if (buffers.isEmpty()) {
            return null;
        }
        Image.Plane plane = image.getPlanes()[0];
        Image.Plane plane2 = image.getPlanes()[1];
        Image.Plane plane3 = image.getPlanes()[2];
        ByteBuffer buffer = plane.getBuffer();
        ByteBuffer buffer2 = plane2.getBuffer();
        ByteBuffer buffer3 = plane3.getBuffer();
        buffer.position(0);
        buffer2.position(0);
        buffer3.position(0);
        int width = image.getWidth();
        int height = image.getHeight();
        byte[] a = buffers.a(buffer3.remaining() + buffer2.remaining() + buffer.remaining());
        int pixelStride = plane2.getPixelStride();
        int rowStride = plane.getRowStride();
        int rowStride2 = plane2.getRowStride();
        NativeAxis nativeAxis = z ? NativeAxis.Y : NativeAxis.NONE;
        NativeCameraCaptureParameters create = NativeCameraCaptureParameters.create();
        initParameters.invoke(create);
        return NativeCameraFrameData.createNv21FrameData(width, height, a, buffer, buffer2, buffer3, pixelStride, rowStride, rowStride2, pool, i, nativeAxis, create, valueOf);
    }

    public static /* synthetic */ NativeCameraFrameData convertToFrameData$default(Image image, BufferStack bufferStack, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i, boolean z, boolean z2, Function1 function1, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            function1 = a.a;
        }
        return convertToFrameData(image, bufferStack, nativeCameraFrameDataPool, i, z, z2, function1);
    }

    public static final int getNv21BytesSize(NativeImageBuffer nativeImageBuffer) {
        Intrinsics.checkNotNullParameter(nativeImageBuffer, "<this>");
        return ((nativeImageBuffer.getHeight() * nativeImageBuffer.getWidth()) * 12) / 8;
    }

    public static final boolean isUsingDirectBuffers(Image image) {
        Intrinsics.checkNotNullParameter(image, "<this>");
        return image.getPlanes()[0].getBuffer().isDirect() && image.getPlanes()[1].getBuffer().isDirect() && image.getPlanes()[2].getBuffer().isDirect();
    }

    public static final ImageData toImageData(Image image, DirectByteBufferCache directByteBufferCache) {
        Intrinsics.checkNotNullParameter(image, "<this>");
        Intrinsics.checkNotNullParameter(directByteBufferCache, "directByteBufferCache");
        if (isUsingDirectBuffers(image)) {
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            Intrinsics.checkNotNullExpressionValue(buffer, "getBuffer(...)");
            ImagePlane imagePlane = new ImagePlane(buffer, image.getPlanes()[0].getRowStride(), image.getPlanes()[0].getPixelStride());
            ByteBuffer buffer2 = image.getPlanes()[1].getBuffer();
            Intrinsics.checkNotNullExpressionValue(buffer2, "getBuffer(...)");
            ImagePlane imagePlane2 = new ImagePlane(buffer2, image.getPlanes()[1].getRowStride(), image.getPlanes()[1].getPixelStride());
            ByteBuffer buffer3 = image.getPlanes()[2].getBuffer();
            Intrinsics.checkNotNullExpressionValue(buffer3, "getBuffer(...)");
            return new ImageData(imagePlane, imagePlane2, new ImagePlane(buffer3, image.getPlanes()[2].getRowStride(), image.getPlanes()[2].getPixelStride()), null, null);
        }
        ByteBuffer buffer4 = image.getPlanes()[0].getBuffer();
        ByteBuffer buffer5 = image.getPlanes()[1].getBuffer();
        ByteBuffer buffer6 = image.getPlanes()[2].getBuffer();
        ByteBuffer a = directByteBufferCache.a(buffer4.capacity());
        Intrinsics.checkNotNull(buffer4);
        ByteBuffer a2 = a(a, buffer4);
        ByteBuffer a3 = directByteBufferCache.a(buffer5.capacity() + 1);
        int capacity = buffer5.capacity();
        int position = a3.position();
        int limit = a3.limit();
        a3.position(0);
        a3.limit(capacity);
        ByteBuffer slice = a3.slice();
        a3.limit(limit);
        a3.position(position);
        Intrinsics.checkNotNull(slice);
        Intrinsics.checkNotNull(buffer5);
        ByteBuffer a4 = a(slice, buffer5);
        int capacity2 = buffer6.capacity();
        int position2 = a3.position();
        int limit2 = a3.limit();
        a3.position(1);
        a3.limit(capacity2 + 1);
        ByteBuffer slice2 = a3.slice();
        a3.limit(limit2);
        a3.position(position2);
        Intrinsics.checkNotNull(slice2);
        Intrinsics.checkNotNull(buffer6);
        return new ImageData(new ImagePlane(a2, image.getPlanes()[0].getRowStride(), image.getPlanes()[0].getPixelStride()), new ImagePlane(a4, image.getPlanes()[1].getRowStride(), image.getPlanes()[1].getPixelStride()), new ImagePlane(a(slice2, buffer6), image.getPlanes()[2].getRowStride(), image.getPlanes()[2].getPixelStride()), directByteBufferCache, CollectionsKt.listOf((Object[]) new ByteBuffer[]{a2, a3}));
    }

    public static final byte[] toNv21Bytes(NativeImageBuffer nativeImageBuffer) {
        Intrinsics.checkNotNullParameter(nativeImageBuffer, "<this>");
        ArrayList<com.scandit.datacapture.core.common.graphic.ImagePlane> planes = nativeImageBuffer.getPlanes();
        if (planes.size() != 3) {
            throw new IllegalStateException("image buffer must contain YUV planes".toString());
        }
        if (planes.get(0).getChannel() != Channel.Y) {
            throw new IllegalStateException("image buffer must contain YUV planes".toString());
        }
        if (planes.get(1).getChannel() != Channel.U) {
            throw new IllegalStateException("image buffer must contain YUV planes".toString());
        }
        if (planes.get(2).getChannel() != Channel.V) {
            throw new IllegalStateException("image buffer must contain YUV planes".toString());
        }
        try {
            byte[] array = ByteBuffer.allocate(getNv21BytesSize(nativeImageBuffer)).put(planes.get(0).getData()).put(planes.get(2).getData()).array();
            Intrinsics.checkNotNull(array);
            return array;
        } catch (Exception unused) {
            byte[] bitmapRepresentationFromYUV = nativeImageBuffer.getBitmapRepresentationFromYUV(new Rect(new Point(0.0f, 0.0f), new Size2(nativeImageBuffer.getWidth(), nativeImageBuffer.getHeight())));
            Intrinsics.checkNotNull(bitmapRepresentationFromYUV);
            return bitmapRepresentationFromYUV;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0075, code lost:
    
        if (r3 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData updateFrameData(android.media.Image r19, com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData r20, com.scandit.datacapture.core.internal.module.source.DirectByteBufferCache r21, com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool r22, int r23, boolean r24, boolean r25, kotlin.jvm.functions.Function1<? super com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters, kotlin.Unit> r26) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.extensions.ImageExtensionsKt.updateFrameData(android.media.Image, com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData, com.scandit.datacapture.core.internal.module.source.DirectByteBufferCache, com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool, int, boolean, boolean, kotlin.jvm.functions.Function1):com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData");
    }
}
