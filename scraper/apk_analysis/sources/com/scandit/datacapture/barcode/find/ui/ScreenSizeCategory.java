package com.scandit.datacapture.barcode.find.ui;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "", "", "toJson", "()Ljava/lang/String;", "Companion", "SMALL", "MID", "LARGE", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ScreenSizeCategory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final ScreenSizeCategory LARGE;
    public static final ScreenSizeCategory MID;
    public static final ScreenSizeCategory SMALL;
    private static final /* synthetic */ ScreenSizeCategory[] b;
    private static final /* synthetic */ EnumEntries c;
    private final String a;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory$Companion;", "", "", TypedValues.Custom.S_STRING, "Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/find/ui/ScreenSizeCategory;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final ScreenSizeCategory fromJson(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            for (ScreenSizeCategory screenSizeCategory : ScreenSizeCategory.values()) {
                if (Intrinsics.areEqual(screenSizeCategory.a, string)) {
                    return screenSizeCategory;
                }
            }
            return null;
        }
    }

    static {
        ScreenSizeCategory screenSizeCategory = new ScreenSizeCategory("SMALL", 0, "small");
        SMALL = screenSizeCategory;
        ScreenSizeCategory screenSizeCategory2 = new ScreenSizeCategory("MID", 1, "mid");
        MID = screenSizeCategory2;
        ScreenSizeCategory screenSizeCategory3 = new ScreenSizeCategory("LARGE", 2, "large");
        LARGE = screenSizeCategory3;
        ScreenSizeCategory[] screenSizeCategoryArr = {screenSizeCategory, screenSizeCategory2, screenSizeCategory3};
        b = screenSizeCategoryArr;
        c = EnumEntriesKt.enumEntries(screenSizeCategoryArr);
        INSTANCE = new Companion(null);
    }

    private ScreenSizeCategory(String str, int i, String str2) {
        this.a = str2;
    }

    public static EnumEntries<ScreenSizeCategory> getEntries() {
        return c;
    }

    public static ScreenSizeCategory valueOf(String str) {
        return (ScreenSizeCategory) Enum.valueOf(ScreenSizeCategory.class, str);
    }

    public static ScreenSizeCategory[] values() {
        return (ScreenSizeCategory[]) b.clone();
    }

    /* renamed from: toJson, reason: from getter */
    public final String getA() {
        return this.a;
    }
}
