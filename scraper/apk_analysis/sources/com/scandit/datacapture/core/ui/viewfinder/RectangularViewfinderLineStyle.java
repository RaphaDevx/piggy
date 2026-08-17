package com.scandit.datacapture.core.ui.viewfinder;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "", "LIGHT", "BOLD", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class RectangularViewfinderLineStyle {
    public static final RectangularViewfinderLineStyle BOLD;
    public static final RectangularViewfinderLineStyle LIGHT;
    private static final /* synthetic */ RectangularViewfinderLineStyle[] a;
    private static final /* synthetic */ EnumEntries b;

    static {
        RectangularViewfinderLineStyle rectangularViewfinderLineStyle = new RectangularViewfinderLineStyle("LIGHT", 0);
        LIGHT = rectangularViewfinderLineStyle;
        RectangularViewfinderLineStyle rectangularViewfinderLineStyle2 = new RectangularViewfinderLineStyle("BOLD", 1);
        BOLD = rectangularViewfinderLineStyle2;
        RectangularViewfinderLineStyle[] rectangularViewfinderLineStyleArr = {rectangularViewfinderLineStyle, rectangularViewfinderLineStyle2};
        a = rectangularViewfinderLineStyleArr;
        b = EnumEntriesKt.enumEntries(rectangularViewfinderLineStyleArr);
    }

    private RectangularViewfinderLineStyle(String str, int i) {
    }

    public static EnumEntries<RectangularViewfinderLineStyle> getEntries() {
        return b;
    }

    public static RectangularViewfinderLineStyle valueOf(String str) {
        return (RectangularViewfinderLineStyle) Enum.valueOf(RectangularViewfinderLineStyle.class, str);
    }

    public static RectangularViewfinderLineStyle[] values() {
        return (RectangularViewfinderLineStyle[]) a.clone();
    }
}
