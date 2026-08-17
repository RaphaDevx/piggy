package io.card.payment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
class Preview extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "Preview";
    private int mPreviewHeight;
    private int mPreviewWidth;
    SurfaceView mSurfaceView;

    public Preview(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet);
        this.mPreviewWidth = i2;
        this.mPreviewHeight = i;
        SurfaceView surfaceView = new SurfaceView(context);
        this.mSurfaceView = surfaceView;
        addView(surfaceView);
    }

    public SurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    SurfaceHolder getSurfaceHolder() {
        return getSurfaceView().getHolder();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255, 255, 0, 0);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!z || getChildCount() <= 0) {
            return;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = this.mPreviewHeight;
        int i8 = i5 * i7;
        int i9 = this.mPreviewWidth;
        if (i8 > i6 * i9) {
            int i10 = (i9 * i6) / i7;
            this.mSurfaceView.layout((i5 - i10) / 2, 0, (i5 + i10) / 2, i6);
        } else {
            int i11 = (i7 * i5) / i9;
            this.mSurfaceView.layout(0, (i6 - i11) / 2, i5, (i6 + i11) / 2);
        }
    }
}
