package com.scandit.datacapture.core.ui.animation;

/* loaded from: classes2.dex */
public final class j extends FloatPropertyCompat {
    public final /* synthetic */ FloatValueHolder a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(FloatValueHolder floatValueHolder) {
        super("FloatValueHolder");
        this.a = floatValueHolder;
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final float getValue(Object obj) {
        return this.a.getValue();
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final void setValue(Object obj, float f) {
        this.a.setValue(f);
    }
}
