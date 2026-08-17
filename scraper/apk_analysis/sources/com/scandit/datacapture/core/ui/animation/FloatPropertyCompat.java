package com.scandit.datacapture.core.ui.animation;

import android.util.FloatProperty;

/* loaded from: classes2.dex */
public abstract class FloatPropertyCompat<T> {
    public FloatPropertyCompat(String str) {
    }

    public static <T> FloatPropertyCompat<T> createFloatPropertyCompat(FloatProperty<T> floatProperty) {
        return new u(floatProperty.getName(), floatProperty);
    }

    public abstract float getValue(T t);

    public abstract void setValue(T t, float f);
}
