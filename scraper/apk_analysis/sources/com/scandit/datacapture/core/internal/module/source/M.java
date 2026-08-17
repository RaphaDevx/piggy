package com.scandit.datacapture.core.internal.module.source;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class M {
    public static final M a;
    public static final M b;
    public static final M c;
    public static final M d;
    public static final M e;
    public static final M f;
    public static final /* synthetic */ M[] g;

    static {
        M m = new M("INACTIVE", 0);
        a = m;
        M m2 = new M("ACTIVE_SCAN", 1);
        b = m2;
        M m3 = new M("FOCUSED_LOCKED", 2);
        c = m3;
        M m4 = new M("NOT_FOCUSED_LOCKED", 3);
        d = m4;
        M m5 = new M("FOCUS_DISTANCE_APPLIED", 4);
        e = m5;
        M m6 = new M("MISSING_CAMERA_SUPPORT", 5);
        f = m6;
        M[] mArr = {m, m2, m3, m4, m5, m6};
        g = mArr;
        EnumEntriesKt.enumEntries(mArr);
    }

    public M(String str, int i) {
    }

    public static M valueOf(String str) {
        return (M) Enum.valueOf(M.class, str);
    }

    public static M[] values() {
        return (M[]) g.clone();
    }
}
