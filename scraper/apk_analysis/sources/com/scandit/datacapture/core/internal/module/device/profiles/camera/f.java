package com.scandit.datacapture.core.internal.module.device.profiles.camera;

/* loaded from: classes2.dex */
public final class f {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final float d;
    public final boolean e;
    public final boolean f;
    public final int g;

    public f(boolean z, boolean z2, boolean z3, float f, boolean z4, boolean z5, int i) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = f;
        this.e = z4;
        this.f = z5;
        this.g = i;
    }

    public static f a(f fVar, boolean z, float f, boolean z2, boolean z3, int i, int i2) {
        if ((i2 & 1) != 0) {
            z = fVar.a;
        }
        boolean z4 = z;
        boolean z5 = (i2 & 2) != 0 ? fVar.b : false;
        boolean z6 = (i2 & 4) != 0 ? fVar.c : false;
        if ((i2 & 8) != 0) {
            f = fVar.d;
        }
        float f2 = f;
        if ((i2 & 16) != 0) {
            z2 = fVar.e;
        }
        boolean z7 = z2;
        if ((i2 & 32) != 0) {
            z3 = fVar.f;
        }
        boolean z8 = z3;
        if ((i2 & 64) != 0) {
            i = fVar.g;
        }
        fVar.getClass();
        return new f(z4, z5, z6, f2, z7, z8, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.a == fVar.a && this.b == fVar.b && this.c == fVar.c && Float.compare(this.d, fVar.d) == 0 && this.e == fVar.e && this.f == fVar.f && this.g == fVar.g;
    }

    public final int hashCode() {
        return Integer.hashCode(this.g) + ((Boolean.hashCode(this.f) + ((Boolean.hashCode(this.e) + ((Float.hashCode(this.d) + ((Boolean.hashCode(this.c) + ((Boolean.hashCode(this.b) + (Boolean.hashCode(this.a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Settings(disableTorch=" + this.a + ", disableContinuous=" + this.b + ", disableMacro=" + this.c + ", minExposureTargetBias=" + this.d + ", disableCustomMeteringAndFocusArea=" + this.e + ", hasBrokenFullCropZoom=" + this.f + ", defaultSharpnessStrength=" + this.g + ')';
    }
}
