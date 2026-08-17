package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements View.OnTouchListener {
    public float a;
    public float b;
    public a c;
    public float d;
    public float e;
    public long f;

    public i() {
        a aVar = this.c;
        this.d = aVar != null ? aVar.a() : 0.0f;
        a aVar2 = this.c;
        this.e = aVar2 != null ? aVar2.b() : 0.0f;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        float rawX;
        if (view == null || motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f = System.currentTimeMillis();
            this.a = view.getX() - motionEvent.getRawX();
            this.b = view.getY() - motionEvent.getRawY();
            motionEvent.getRawX();
            motionEvent.getRawY();
        } else if (action != 1) {
            if (action == 2) {
                float f = 0.0f;
                if (motionEvent.getRawX() + this.a < 0.0f) {
                    rawX = 0.0f;
                } else {
                    float rawX2 = motionEvent.getRawX() + this.a + view.getWidth();
                    Object parent = view.getParent();
                    Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                    rawX = rawX2 > ((float) ((View) parent).getWidth()) ? this.d : motionEvent.getRawX() + this.a;
                }
                this.d = rawX;
                if (motionEvent.getRawY() + this.b >= 0.0f) {
                    float rawY = motionEvent.getRawY() + this.b + view.getHeight();
                    Object parent2 = view.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
                    f = rawY > ((float) ((View) parent2).getHeight()) ? this.e : motionEvent.getRawY() + this.b;
                }
                this.e = f;
                view.setX(this.d);
                view.setY(this.e);
            }
        } else if (System.currentTimeMillis() - this.f < 200) {
            view.performClick();
        } else {
            a aVar = this.c;
            if (aVar != null) {
                aVar.e.edit().putFloat("barcode-count-floating-shutter-x-location", this.d).apply();
            }
            a aVar2 = this.c;
            if (aVar2 != null) {
                aVar2.e.edit().putFloat("barcode-count-floating-shutter-y-location", this.e).apply();
            }
        }
        return true;
    }
}
