package io.card.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/* loaded from: classes2.dex */
class Logo {
    private final Context mContext;
    private Bitmap mLogo;
    private final Paint mPaint;
    private boolean mUseCardIOLogo;

    public Logo(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        paint.setAlpha(100);
        this.mLogo = null;
        this.mContext = context;
    }

    void loadLogo(boolean z) {
        if (this.mLogo == null || z != this.mUseCardIOLogo) {
            this.mUseCardIOLogo = z;
            if (z) {
                this.mLogo = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.cio_card_io_logo);
            } else {
                this.mLogo = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.cio_paypal_logo);
            }
        }
    }

    public void draw(Canvas canvas, float f, float f2) {
        if (this.mLogo == null) {
            loadLogo(false);
        }
        canvas.save();
        float height = this.mLogo.getHeight() / this.mLogo.getWidth();
        if (f2 / f < height) {
            f = f2 / height;
        } else {
            f2 = f * height;
        }
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        canvas.drawBitmap(this.mLogo, new Rect(0, 0, this.mLogo.getWidth(), this.mLogo.getHeight()), new RectF(-f3, -f4, f3, f4), this.mPaint);
        canvas.restore();
    }
}
