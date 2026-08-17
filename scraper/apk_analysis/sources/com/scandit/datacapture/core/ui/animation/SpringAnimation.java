package com.scandit.datacapture.core.ui.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;

/* loaded from: classes2.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private SpringForce m;
    private float n;
    private boolean o;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    @Override // com.scandit.datacapture.core.ui.animation.DynamicAnimation
    final boolean a(long j) {
        if (this.o) {
            float f = this.n;
            if (f != Float.MAX_VALUE) {
                this.m.setFinalPosition(f);
                this.n = Float.MAX_VALUE;
            }
            this.b = this.m.getFinalPosition();
            this.a = 0.0f;
            this.o = false;
            return true;
        }
        if (this.n != Float.MAX_VALUE) {
            this.m.getFinalPosition();
            long j2 = j / 2;
            t a = this.m.a(this.b, this.a, j2);
            this.m.setFinalPosition(this.n);
            this.n = Float.MAX_VALUE;
            t a2 = this.m.a(a.a, a.b, j2);
            this.b = a2.a;
            this.a = a2.b;
        } else {
            t a3 = this.m.a(this.b, this.a, j);
            this.b = a3.a;
            this.a = a3.b;
        }
        float max = Math.max(this.b, this.h);
        this.b = max;
        float min = Math.min(max, this.g);
        this.b = min;
        if (!this.m.isAtEquilibrium(min, this.a)) {
            return false;
        }
        this.b = this.m.getFinalPosition();
        this.a = 0.0f;
        return true;
    }

    public void animateToFinalPosition(float f) {
        if (isRunning()) {
            this.n = f;
            return;
        }
        if (this.m == null) {
            this.m = new SpringForce(f);
        }
        this.m.setFinalPosition(f);
        start();
    }

    public boolean canSkipToEnd() {
        return this.m.b > 0.0d;
    }

    public SpringForce getSpring() {
        return this.m;
    }

    public SpringAnimation setSpring(SpringForce springForce) {
        this.m = springForce;
        return this;
    }

    public void skipToEnd() {
        if (!canSkipToEnd()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f) {
            this.o = true;
        }
    }

    @Override // com.scandit.datacapture.core.ui.animation.DynamicAnimation
    public void start() {
        SpringForce springForce = this.m;
        if (springForce == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double finalPosition = springForce.getFinalPosition();
        if (finalPosition > this.g) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if (finalPosition < this.h) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
        this.m.a(a());
        super.start();
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat, float f) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
        this.m = new SpringForce(f);
    }
}
