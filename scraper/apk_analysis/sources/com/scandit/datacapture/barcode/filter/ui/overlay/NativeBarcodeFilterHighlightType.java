package com.scandit.datacapture.barcode.filter.ui.overlay;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class NativeBarcodeFilterHighlightType {
    public static final NativeBarcodeFilterHighlightType BRUSH;
    private static final /* synthetic */ NativeBarcodeFilterHighlightType[] a;

    static {
        NativeBarcodeFilterHighlightType nativeBarcodeFilterHighlightType = new NativeBarcodeFilterHighlightType();
        BRUSH = nativeBarcodeFilterHighlightType;
        a = new NativeBarcodeFilterHighlightType[]{nativeBarcodeFilterHighlightType};
    }

    private NativeBarcodeFilterHighlightType() {
    }

    public static NativeBarcodeFilterHighlightType valueOf(String str) {
        return (NativeBarcodeFilterHighlightType) Enum.valueOf(NativeBarcodeFilterHighlightType.class, str);
    }

    public static NativeBarcodeFilterHighlightType[] values() {
        return (NativeBarcodeFilterHighlightType[]) a.clone();
    }
}
