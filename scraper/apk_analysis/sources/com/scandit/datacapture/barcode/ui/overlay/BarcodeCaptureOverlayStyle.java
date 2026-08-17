package com.scandit.datacapture.barcode.ui.overlay;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Deprecated(message = "Deprecated")
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002¨\u0006\u0003"}, d2 = {"Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;", "", "FRAME", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCaptureOverlayStyle {
    public static final BarcodeCaptureOverlayStyle FRAME;
    private static final /* synthetic */ BarcodeCaptureOverlayStyle[] a;
    private static final /* synthetic */ EnumEntries b;

    static {
        BarcodeCaptureOverlayStyle barcodeCaptureOverlayStyle = new BarcodeCaptureOverlayStyle();
        FRAME = barcodeCaptureOverlayStyle;
        BarcodeCaptureOverlayStyle[] barcodeCaptureOverlayStyleArr = {barcodeCaptureOverlayStyle};
        a = barcodeCaptureOverlayStyleArr;
        b = EnumEntriesKt.enumEntries(barcodeCaptureOverlayStyleArr);
    }

    private BarcodeCaptureOverlayStyle() {
    }

    public static EnumEntries<BarcodeCaptureOverlayStyle> getEntries() {
        return b;
    }

    public static BarcodeCaptureOverlayStyle valueOf(String str) {
        return (BarcodeCaptureOverlayStyle) Enum.valueOf(BarcodeCaptureOverlayStyle.class, str);
    }

    public static BarcodeCaptureOverlayStyle[] values() {
        return (BarcodeCaptureOverlayStyle[]) a.clone();
    }
}
