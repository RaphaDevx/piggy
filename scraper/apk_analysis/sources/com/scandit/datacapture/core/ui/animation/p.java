package com.scandit.datacapture.core.ui.animation;

import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.scandit.datacapture.core.ui.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public final class p extends DynamicAnimation.ViewProperty {
    public p() {
        super(Key.ROTATION);
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final float getValue(View view) {
        return view.getRotation();
    }

    @Override // com.scandit.datacapture.core.ui.animation.FloatPropertyCompat
    public final void setValue(View view, float f) {
        view.setRotation(f);
    }
}
