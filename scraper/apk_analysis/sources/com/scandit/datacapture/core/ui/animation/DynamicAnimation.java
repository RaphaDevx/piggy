package com.scandit.datacapture.core.ui.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import com.scandit.datacapture.core.ui.animation.DynamicAnimation;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements b {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;
    float a;
    float b;
    boolean c;
    final Object d;
    final FloatPropertyCompat e;
    boolean f;
    float g;
    float h;
    private long i;
    private float j;
    private final ArrayList k;
    private final ArrayList l;
    public static final ViewProperty TRANSLATION_X = new k();
    public static final ViewProperty TRANSLATION_Y = new l();
    public static final ViewProperty TRANSLATION_Z = new m();
    public static final ViewProperty SCALE_X = new n();
    public static final ViewProperty SCALE_Y = new o();
    public static final ViewProperty ROTATION = new p();
    public static final ViewProperty ROTATION_X = new q();
    public static final ViewProperty ROTATION_Y = new r();
    public static final ViewProperty X = new s();
    public static final ViewProperty Y = new e();
    public static final ViewProperty Z = new f();
    public static final ViewProperty ALPHA = new g();
    public static final ViewProperty SCROLL_X = new h();
    public static final ViewProperty SCROLL_Y = new i();

    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2);
    }

    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2);
    }

    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        ViewProperty(String str) {
            super(str);
        }
    }

    DynamicAnimation(FloatValueHolder floatValueHolder) {
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -3.4028235E38f;
        this.i = 0L;
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.d = null;
        this.e = new j(floatValueHolder);
        this.j = 1.0f;
    }

    private void a(boolean z) {
        this.f = false;
        ThreadLocal threadLocal = d.g;
        if (threadLocal.get() == null) {
            threadLocal.set(new d());
        }
        d dVar = (d) threadLocal.get();
        dVar.a.remove(this);
        int indexOf = dVar.b.indexOf(this);
        if (indexOf >= 0) {
            dVar.b.set(indexOf, null);
            dVar.f = true;
        }
        this.i = 0L;
        this.c = false;
        for (int i = 0; i < this.k.size(); i++) {
            if (this.k.get(i) != null) {
                ((OnAnimationEndListener) this.k.get(i)).onAnimationEnd(this, z, this.b, this.a);
            }
        }
        ArrayList arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    abstract boolean a(long j);

    public T addEndListener(OnAnimationEndListener onAnimationEndListener) {
        if (!this.k.contains(onAnimationEndListener)) {
            this.k.add(onAnimationEndListener);
        }
        return this;
    }

    public T addUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        if (isRunning()) {
            throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
        }
        if (!this.l.contains(onAnimationUpdateListener)) {
            this.l.add(onAnimationUpdateListener);
        }
        return this;
    }

    public void cancel() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
        }
        if (this.f) {
            a(true);
        }
    }

    @Override // com.scandit.datacapture.core.ui.animation.b
    public boolean doAnimationFrame(long j) {
        long j2 = this.i;
        if (j2 == 0) {
            this.i = j;
            this.e.setValue(this.d, this.b);
            for (int i = 0; i < this.l.size(); i++) {
                if (this.l.get(i) != null) {
                    ((OnAnimationUpdateListener) this.l.get(i)).onAnimationUpdate(this, this.b, this.a);
                }
            }
            ArrayList arrayList = this.l;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (arrayList.get(size) == null) {
                    arrayList.remove(size);
                }
            }
            return false;
        }
        this.i = j;
        boolean a = a(j - j2);
        float min = Math.min(this.b, this.g);
        this.b = min;
        float max = Math.max(min, this.h);
        this.b = max;
        this.e.setValue(this.d, max);
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.get(i2) != null) {
                ((OnAnimationUpdateListener) this.l.get(i2)).onAnimationUpdate(this, this.b, this.a);
            }
        }
        ArrayList arrayList2 = this.l;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            if (arrayList2.get(size2) == null) {
                arrayList2.remove(size2);
            }
        }
        if (a) {
            a(false);
        }
        return a;
    }

    public float getMinimumVisibleChange() {
        return this.j;
    }

    public boolean isRunning() {
        return this.f;
    }

    public void removeEndListener(OnAnimationEndListener onAnimationEndListener) {
        ArrayList arrayList = this.k;
        int indexOf = arrayList.indexOf(onAnimationEndListener);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    public void removeUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        ArrayList arrayList = this.l;
        int indexOf = arrayList.indexOf(onAnimationUpdateListener);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    public T setMaxValue(float f) {
        this.g = f;
        return this;
    }

    public T setMinValue(float f) {
        this.h = f;
        return this;
    }

    public T setMinimumVisibleChange(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.j = f;
        return this;
    }

    public T setStartValue(float f) {
        this.b = f;
        this.c = true;
        return this;
    }

    public T setStartVelocity(float f) {
        this.a = f;
        return this;
    }

    public void start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        boolean z = this.f;
        if (z || z) {
            return;
        }
        this.f = true;
        if (!this.c) {
            this.b = this.e.getValue(this.d);
        }
        float f = this.b;
        if (f > this.g || f < this.h) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        ThreadLocal threadLocal = d.g;
        if (threadLocal.get() == null) {
            threadLocal.set(new d());
        }
        d dVar = (d) threadLocal.get();
        if (dVar.b.size() == 0) {
            if (dVar.d == null) {
                dVar.d = new c(dVar.c);
            }
            dVar.d.a();
        }
        if (dVar.b.contains(this)) {
            return;
        }
        dVar.b.add(this);
    }

    final float a() {
        return this.j * 0.75f;
    }

    DynamicAnimation(Object obj, FloatPropertyCompat floatPropertyCompat) {
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -3.4028235E38f;
        this.i = 0L;
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.d = obj;
        this.e = floatPropertyCompat;
        if (floatPropertyCompat != ROTATION && floatPropertyCompat != ROTATION_X && floatPropertyCompat != ROTATION_Y) {
            if (floatPropertyCompat == ALPHA) {
                this.j = 0.00390625f;
                return;
            } else if (floatPropertyCompat != SCALE_X && floatPropertyCompat != SCALE_Y) {
                this.j = 1.0f;
                return;
            } else {
                this.j = 0.00390625f;
                return;
            }
        }
        this.j = 0.1f;
    }
}
