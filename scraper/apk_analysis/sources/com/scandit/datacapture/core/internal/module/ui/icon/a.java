package com.scandit.datacapture.core.internal.module.ui.icon;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends LayerDrawable {
    public final float a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Drawable[] drawables) {
        super(drawables);
        Intrinsics.checkNotNullParameter(drawables, "drawables");
        this.a = 0.5f;
        setLayerGravity(1, 17);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return ScanditIcon.INSTANCE.getIconBackgroundSize$scandit_capture_core();
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return ScanditIcon.INSTANCE.getIconBackgroundSize$scandit_capture_core();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        int min = Math.min(i3 - i, i4 - i2);
        setLayerSize(0, min, min);
        Drawable drawable = getDrawable(1);
        if (drawable == null) {
            super.setBounds(i, i2, i3, i4);
            return;
        }
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float intrinsicWidth = drawable.getIntrinsicWidth();
        boolean z = intrinsicHeight >= intrinsicWidth;
        float f = min * this.a;
        float f2 = f / (z ? intrinsicHeight / intrinsicWidth : intrinsicWidth / intrinsicHeight);
        int i5 = (int) (z ? f2 : f);
        if (!z) {
            f = f2;
        }
        setLayerSize(1, i5, (int) f);
        super.setBounds(i, i2, i3, i4);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
    }
}
