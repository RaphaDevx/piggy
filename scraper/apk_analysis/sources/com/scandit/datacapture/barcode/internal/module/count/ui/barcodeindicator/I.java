package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import androidx.webkit.Profile;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class I {
    public static final I a;
    public static final I b;
    public static final /* synthetic */ I[] c;

    static {
        I i = new I(Profile.DEFAULT_PROFILE_NAME, 0);
        a = i;
        I i2 = new I("IndicatorOnly", 1);
        b = i2;
        I[] iArr = {i, i2};
        c = iArr;
        EnumEntriesKt.enumEntries(iArr);
    }

    public I(String str, int i) {
    }

    public static I valueOf(String str) {
        return (I) Enum.valueOf(I.class, str);
    }

    public static I[] values() {
        return (I[]) c.clone();
    }
}
