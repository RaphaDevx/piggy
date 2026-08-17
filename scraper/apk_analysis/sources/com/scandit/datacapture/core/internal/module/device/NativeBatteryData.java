package com.scandit.datacapture.core.internal.module.device;

/* loaded from: classes2.dex */
public final class NativeBatteryData {
    final int percentage;

    public NativeBatteryData(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public String toString() {
        return "NativeBatteryData{percentage=" + this.percentage + "}";
    }
}
