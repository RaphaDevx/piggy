package com.scandit.datacapture.core.ui.viewfinder;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "", "SQUARE", "ROUNDED", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class RectangularViewfinderStyle {
    public static final RectangularViewfinderStyle ROUNDED;
    public static final RectangularViewfinderStyle SQUARE;
    private static final /* synthetic */ RectangularViewfinderStyle[] a;
    private static final /* synthetic */ EnumEntries b;

    static {
        RectangularViewfinderStyle rectangularViewfinderStyle = new RectangularViewfinderStyle("SQUARE", 0);
        SQUARE = rectangularViewfinderStyle;
        RectangularViewfinderStyle rectangularViewfinderStyle2 = new RectangularViewfinderStyle("ROUNDED", 1);
        ROUNDED = rectangularViewfinderStyle2;
        RectangularViewfinderStyle[] rectangularViewfinderStyleArr = {rectangularViewfinderStyle, rectangularViewfinderStyle2};
        a = rectangularViewfinderStyleArr;
        b = EnumEntriesKt.enumEntries(rectangularViewfinderStyleArr);
    }

    private RectangularViewfinderStyle(String str, int i) {
    }

    public static EnumEntries<RectangularViewfinderStyle> getEntries() {
        return b;
    }

    public static RectangularViewfinderStyle valueOf(String str) {
        return (RectangularViewfinderStyle) Enum.valueOf(RectangularViewfinderStyle.class, str);
    }

    public static RectangularViewfinderStyle[] values() {
        return (RectangularViewfinderStyle[]) a.clone();
    }
}
