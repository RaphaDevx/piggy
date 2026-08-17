package com.scandit.datacapture.barcode.ui.overlay;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class NativeBarcodeCaptureOverlayStyle {
    public static final NativeBarcodeCaptureOverlayStyle FRAME;
    private static final /* synthetic */ NativeBarcodeCaptureOverlayStyle[] a;

    static {
        NativeBarcodeCaptureOverlayStyle nativeBarcodeCaptureOverlayStyle = new NativeBarcodeCaptureOverlayStyle();
        FRAME = nativeBarcodeCaptureOverlayStyle;
        a = new NativeBarcodeCaptureOverlayStyle[]{nativeBarcodeCaptureOverlayStyle};
    }

    private NativeBarcodeCaptureOverlayStyle() {
    }

    public static NativeBarcodeCaptureOverlayStyle valueOf(String str) {
        return (NativeBarcodeCaptureOverlayStyle) Enum.valueOf(NativeBarcodeCaptureOverlayStyle.class, str);
    }

    public static NativeBarcodeCaptureOverlayStyle[] values() {
        return (NativeBarcodeCaptureOverlayStyle[]) a.clone();
    }
}
