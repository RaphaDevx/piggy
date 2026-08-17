package com.scandit.datacapture.tools.internal.module.mapmaker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public abstract class u {
    public static final C0630s a;
    public static final t b;
    public static final /* synthetic */ u[] c;

    static {
        C0630s c0630s = new C0630s();
        a = c0630s;
        t tVar = new t();
        b = tVar;
        c = new u[]{c0630s, tVar};
    }

    public u(String str, int i) {
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) c.clone();
    }

    public abstract AbstractC0617e a();
}
