package com.scandit.datacapture.core.internal.sdk.capture;

/* loaded from: classes2.dex */
public final class NativeInertialMeasurementAndroid {
    final long timestampNanos;
    final float x;
    final float y;
    final float z;

    public NativeInertialMeasurementAndroid(float f, float f2, float f3, long j) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.timestampNanos = j;
    }

    public long getTimestampNanos() {
        return this.timestampNanos;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public String toString() {
        return "NativeInertialMeasurementAndroid{x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",timestampNanos=" + this.timestampNanos + "}";
    }
}
