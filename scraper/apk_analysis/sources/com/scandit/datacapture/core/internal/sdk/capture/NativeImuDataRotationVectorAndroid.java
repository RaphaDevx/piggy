package com.scandit.datacapture.core.internal.sdk.capture;

/* loaded from: classes2.dex */
public final class NativeImuDataRotationVectorAndroid {
    final long timestampNanos;
    final float w;
    final float x;
    final float y;
    final float z;

    public NativeImuDataRotationVectorAndroid(float f, float f2, float f3, float f4, long j) {
        this.w = f;
        this.x = f2;
        this.y = f3;
        this.z = f4;
        this.timestampNanos = j;
    }

    public long getTimestampNanos() {
        return this.timestampNanos;
    }

    public float getW() {
        return this.w;
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
        return "NativeImuDataRotationVectorAndroid{w=" + this.w + ",x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",timestampNanos=" + this.timestampNanos + "}";
    }
}
