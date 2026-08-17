package com.scandit.datacapture.core.common.graphic;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ImagePlane {
    final Channel channel;
    final ByteBuffer data;
    final int pixelStride;
    final int rowStride;
    final int subsamplingX;
    final int subsamplingY;

    public ImagePlane(Channel channel, int i, int i2, int i3, int i4, ByteBuffer byteBuffer) {
        this.channel = channel;
        this.subsamplingX = i;
        this.subsamplingY = i2;
        this.rowStride = i3;
        this.pixelStride = i4;
        this.data = byteBuffer;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public int getPixelStride() {
        return this.pixelStride;
    }

    public int getRowStride() {
        return this.rowStride;
    }

    public int getSubsamplingX() {
        return this.subsamplingX;
    }

    public int getSubsamplingY() {
        return this.subsamplingY;
    }

    public String toString() {
        return "ImagePlane{channel=" + this.channel + ",subsamplingX=" + this.subsamplingX + ",subsamplingY=" + this.subsamplingY + ",rowStride=" + this.rowStride + ",pixelStride=" + this.pixelStride + ",data=" + this.data + "}";
    }
}
