package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class Y {
    public static final Y a;
    public static final Y b;
    public static final Y c;
    public static final Y d;
    public static final /* synthetic */ Y[] e;

    static {
        Y y = new Y("TORCH_OFF", 0);
        a = y;
        Y y2 = new Y("TORCH_ON", 1);
        b = y2;
        Y y3 = new Y("TORCH_ON_DELAY_TO_OFF", 2);
        c = y3;
        Y y4 = new Y("TORCH_OFF_DELAY_TO_ON", 3);
        d = y4;
        Y[] yArr = {y, y2, y3, y4};
        e = yArr;
        EnumEntriesKt.enumEntries(yArr);
    }

    public Y(String str, int i) {
    }

    public static Y valueOf(String str) {
        return (Y) Enum.valueOf(Y.class, str);
    }

    public static Y[] values() {
        return (Y[]) e.clone();
    }
}
