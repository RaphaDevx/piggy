package com.scandit.datacapture.barcode.spark.internal.module.ui;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class NativeSparkScanOverlayStyle {
    public static final NativeSparkScanOverlayStyle FRAME;
    private static final /* synthetic */ NativeSparkScanOverlayStyle[] a;

    static {
        NativeSparkScanOverlayStyle nativeSparkScanOverlayStyle = new NativeSparkScanOverlayStyle();
        FRAME = nativeSparkScanOverlayStyle;
        a = new NativeSparkScanOverlayStyle[]{nativeSparkScanOverlayStyle};
    }

    private NativeSparkScanOverlayStyle() {
    }

    public static NativeSparkScanOverlayStyle valueOf(String str) {
        return (NativeSparkScanOverlayStyle) Enum.valueOf(NativeSparkScanOverlayStyle.class, str);
    }

    public static NativeSparkScanOverlayStyle[] values() {
        return (NativeSparkScanOverlayStyle[]) a.clone();
    }
}
