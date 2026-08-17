package com.scandit.datacapture.core.ui.animation;

/* loaded from: classes2.dex */
public final class SpringForce {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    double a;
    double b;
    private boolean c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    private final t j;

    public SpringForce() {
        this.a = Math.sqrt(1500.0d);
        this.b = 1.0d;
        this.c = false;
        this.i = Double.MAX_VALUE;
        this.j = new t();
    }

    final t a(double d, double d2, long j) {
        double cos;
        double d3;
        if (!this.c) {
            if (this.i == Double.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            double d4 = this.b;
            if (d4 > 1.0d) {
                double d5 = this.a;
                this.f = (Math.sqrt((d4 * d4) - 1.0d) * d5) + ((-d4) * d5);
                double d6 = this.b;
                double d7 = this.a;
                this.g = ((-d6) * d7) - (Math.sqrt((d6 * d6) - 1.0d) * d7);
            } else if (d4 >= 0.0d && d4 < 1.0d) {
                this.h = Math.sqrt(1.0d - (d4 * d4)) * this.a;
            }
            this.c = true;
        }
        double d8 = j / 1000.0d;
        double d9 = d - this.i;
        double d10 = this.b;
        if (d10 > 1.0d) {
            double d11 = this.g;
            double d12 = ((d11 * d9) - d2) / (d11 - this.f);
            double d13 = d9 - d12;
            d3 = (Math.pow(2.718281828459045d, this.f * d8) * d12) + (Math.pow(2.718281828459045d, d11 * d8) * d13);
            double d14 = this.g;
            double pow = Math.pow(2.718281828459045d, d14 * d8) * d13 * d14;
            double d15 = this.f;
            cos = (Math.pow(2.718281828459045d, d15 * d8) * d12 * d15) + pow;
        } else if (d10 == 1.0d) {
            double d16 = this.a;
            double d17 = (d16 * d9) + d2;
            double d18 = (d17 * d8) + d9;
            double pow2 = Math.pow(2.718281828459045d, (-d16) * d8) * d18;
            double pow3 = Math.pow(2.718281828459045d, (-this.a) * d8) * d18;
            double d19 = -this.a;
            cos = (Math.pow(2.718281828459045d, d19 * d8) * d17) + (pow3 * d19);
            d3 = pow2;
        } else {
            double d20 = 1.0d / this.h;
            double d21 = this.a;
            double d22 = ((d10 * d21 * d9) + d2) * d20;
            double sin = ((Math.sin(this.h * d8) * d22) + (Math.cos(this.h * d8) * d9)) * Math.pow(2.718281828459045d, (-d10) * d21 * d8);
            double d23 = this.a;
            double d24 = this.b;
            double d25 = (-d23) * sin * d24;
            double pow4 = Math.pow(2.718281828459045d, (-d24) * d23 * d8);
            double d26 = this.h;
            double sin2 = Math.sin(d26 * d8) * (-d26) * d9;
            double d27 = this.h;
            cos = (((Math.cos(d27 * d8) * d22 * d27) + sin2) * pow4) + d25;
            d3 = sin;
        }
        t tVar = this.j;
        tVar.a = (float) (d3 + this.i);
        tVar.b = (float) cos;
        return tVar;
    }

    public float getAcceleration(float f, float f2) {
        float finalPosition = f - getFinalPosition();
        double d = this.a;
        return (float) (((-(d * d)) * finalPosition) - (((d * 2.0d) * this.b) * f2));
    }

    public float getDampingRatio() {
        return (float) this.b;
    }

    public float getFinalPosition() {
        return (float) this.i;
    }

    public float getStiffness() {
        double d = this.a;
        return (float) (d * d);
    }

    public boolean isAtEquilibrium(float f, float f2) {
        return ((double) Math.abs(f2)) < this.e && ((double) Math.abs(f - getFinalPosition())) < this.d;
    }

    public SpringForce setDampingRatio(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.b = f;
        this.c = false;
        return this;
    }

    public SpringForce setFinalPosition(float f) {
        this.i = f;
        return this;
    }

    public SpringForce setStiffness(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.a = Math.sqrt(f);
        this.c = false;
        return this;
    }

    public SpringForce(float f) {
        this.a = Math.sqrt(1500.0d);
        this.b = 1.0d;
        this.c = false;
        this.j = new t();
        this.i = f;
    }

    final void a(double d) {
        double abs = Math.abs(d);
        this.d = abs;
        this.e = abs * 62.5d;
    }
}
