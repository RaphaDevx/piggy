package com.scandit.datacapture.core.ui.animation;

import android.util.FloatProperty;

/* loaded from: classes2.dex */
public final class u extends FloatPropertyCompat {
    public final /* synthetic */ FloatProperty a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(String str, FloatProperty floatProperty) {
        super(str);
        this.a = floatProperty;
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final float getValue(Object obj) {
        return ((Float) this.a.get(obj)).floatValue();
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final void setValue(Object obj, float f) {
        this.a.setValue(obj, f);
    }
}
