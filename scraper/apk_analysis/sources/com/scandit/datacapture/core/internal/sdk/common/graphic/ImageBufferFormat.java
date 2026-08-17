package com.scandit.datacapture.core.internal.sdk.common.graphic;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/common/graphic/ImageBufferFormat;", "", "ARGB", "YUV", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ImageBufferFormat {
    public static final ImageBufferFormat ARGB;
    public static final ImageBufferFormat YUV;
    private static final /* synthetic */ ImageBufferFormat[] a;
    private static final /* synthetic */ EnumEntries b;

    static {
        ImageBufferFormat imageBufferFormat = new ImageBufferFormat("ARGB", 0);
        ARGB = imageBufferFormat;
        ImageBufferFormat imageBufferFormat2 = new ImageBufferFormat("YUV", 1);
        YUV = imageBufferFormat2;
        ImageBufferFormat[] imageBufferFormatArr = {imageBufferFormat, imageBufferFormat2};
        a = imageBufferFormatArr;
        b = EnumEntriesKt.enumEntries(imageBufferFormatArr);
    }

    private ImageBufferFormat(String str, int i) {
    }

    public static EnumEntries<ImageBufferFormat> getEntries() {
        return b;
    }

    public static ImageBufferFormat valueOf(String str) {
        return (ImageBufferFormat) Enum.valueOf(ImageBufferFormat.class, str);
    }

    public static ImageBufferFormat[] values() {
        return (ImageBufferFormat[]) a.clone();
    }
}
