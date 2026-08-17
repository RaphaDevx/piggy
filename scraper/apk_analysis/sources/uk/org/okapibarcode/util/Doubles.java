package uk.org.okapibarcode.util;

/* loaded from: classes4.dex */
public final class Doubles {
    private Doubles() {
    }

    public static boolean roughlyEqual(double d, double d2) {
        return Math.abs(d - d2) < 1.0E-4d;
    }
}
