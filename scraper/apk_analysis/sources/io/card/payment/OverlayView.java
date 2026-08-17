package io.card.payment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import java.lang.ref.WeakReference;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
class OverlayView extends View {
    private static final GradientDrawable.Orientation[] GRADIENT_ORIENTATIONS = {GradientDrawable.Orientation.TOP_BOTTOM, GradientDrawable.Orientation.LEFT_RIGHT, GradientDrawable.Orientation.BOTTOM_TOP, GradientDrawable.Orientation.RIGHT_LEFT};
    private static final String TAG = "OverlayView";
    private int guideColor;
    private boolean hideCardIOLogo;
    private Bitmap mBitmap;
    private Rect mCameraPreviewRect;
    private DetectionInfo mDInfo;
    private CreditCard mDetectedCard;
    private GradientDrawable mGradientDrawable;
    private Rect mGuide;
    private final Paint mGuidePaint;
    private final Paint mLockedBackgroundPaint;
    private Path mLockedBackgroundPath;
    private final Logo mLogo;
    private Rect mLogoRect;
    private int mRotation;
    private int mRotationFlip;
    private float mScale;
    private final WeakReference<CardIOActivity> mScanActivityRef;
    private final boolean mShowTorch;
    private int mState;
    private final Torch mTorch;
    private Rect mTorchRect;
    private String scanInstructions;

    public OverlayView(CardIOActivity cardIOActivity, AttributeSet attributeSet, boolean z) {
        super(cardIOActivity, attributeSet);
        this.mScale = 1.0f;
        this.mShowTorch = z;
        this.mScanActivityRef = new WeakReference<>(cardIOActivity);
        this.mRotationFlip = 1;
        this.mScale = getResources().getDisplayMetrics().density / 1.5f;
        float f = this.mScale;
        this.mTorch = new Torch(70.0f * f, f * 50.0f);
        this.mLogo = new Logo(cardIOActivity);
        this.mGuidePaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mLockedBackgroundPaint = paint;
        paint.clearShadowLayer();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1157627904);
        this.scanInstructions = LocalizedStrings.getString(StringKey.SCAN_GUIDE);
    }

    public void setGuideColor(int i) {
        this.guideColor = i;
    }

    public void setHideCardIOLogo(boolean z) {
        this.hideCardIOLogo = z;
    }

    public void setScanInstructions(String str) {
        this.scanInstructions = str;
    }

    public void setGuideAndRotation(Rect rect, int i) {
        Point point;
        this.mRotation = i;
        this.mGuide = rect;
        invalidate();
        if (this.mRotation % 180 != 0) {
            float f = this.mScale;
            point = new Point((int) (40.0f * f), (int) (f * 60.0f));
            this.mRotationFlip = -1;
        } else {
            float f2 = this.mScale;
            point = new Point((int) (60.0f * f2), (int) (f2 * 40.0f));
            this.mRotationFlip = 1;
        }
        if (this.mCameraPreviewRect != null) {
            Point point2 = new Point(this.mCameraPreviewRect.left + point.x, this.mCameraPreviewRect.top + point.y);
            float f3 = this.mScale;
            this.mTorchRect = Util.rectGivenCenter(point2, (int) (70.0f * f3), (int) (f3 * 50.0f));
            Point point3 = new Point(this.mCameraPreviewRect.right - point.x, this.mCameraPreviewRect.top + point.y);
            float f4 = this.mScale;
            this.mLogoRect = Util.rectGivenCenter(point3, (int) (100.0f * f4), (int) (f4 * 50.0f));
            GradientDrawable gradientDrawable = new GradientDrawable(GRADIENT_ORIENTATIONS[(this.mRotation / 90) % 4], new int[]{-1, ViewCompat.MEASURED_STATE_MASK});
            this.mGradientDrawable = gradientDrawable;
            gradientDrawable.setGradientType(0);
            this.mGradientDrawable.setBounds(this.mGuide);
            this.mGradientDrawable.setAlpha(50);
            Path path = new Path();
            this.mLockedBackgroundPath = path;
            path.addRect(new RectF(this.mCameraPreviewRect), Path.Direction.CW);
            this.mLockedBackgroundPath.addRect(new RectF(this.mGuide), Path.Direction.CCW);
        }
    }

    public void setBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.mBitmap = bitmap;
        if (bitmap != null) {
            decorateBitmap();
        }
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setDetectionInfo(DetectionInfo detectionInfo) {
        DetectionInfo detectionInfo2 = this.mDInfo;
        if (detectionInfo2 != null && !detectionInfo2.sameEdgesAs(detectionInfo)) {
            invalidate();
        }
        this.mDInfo = detectionInfo;
    }

    public Bitmap getCardImage() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap bitmap2 = this.mBitmap;
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.mBitmap.getHeight());
    }

    private Rect guideStrokeRect(int i, int i2, int i3, int i4) {
        int i5 = (int) (this.mScale * 8.0f);
        Rect rect = new Rect();
        rect.left = Math.min(i, i3) - i5;
        rect.right = Math.max(i, i3) + i5;
        rect.top = Math.min(i2, i4) - i5;
        rect.bottom = Math.max(i2, i4) + i5;
        return rect;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        if (this.mGuide == null || this.mCameraPreviewRect == null) {
            return;
        }
        canvas.save();
        this.mGradientDrawable.draw(canvas);
        int i2 = this.mRotation;
        if (i2 == 0 || i2 == 180) {
            i = (this.mGuide.bottom - this.mGuide.top) / 4;
        } else {
            i = (this.mGuide.right - this.mGuide.left) / 4;
        }
        DetectionInfo detectionInfo = this.mDInfo;
        if (detectionInfo != null && detectionInfo.numVisibleEdges() == 4) {
            canvas.drawPath(this.mLockedBackgroundPath, this.mLockedBackgroundPaint);
        }
        this.mGuidePaint.clearShadowLayer();
        this.mGuidePaint.setStyle(Paint.Style.FILL);
        this.mGuidePaint.setColor(this.guideColor);
        canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left + i, this.mGuide.top), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left, this.mGuide.top + i), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right - i, this.mGuide.top), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right, this.mGuide.top + i), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.left + i, this.mGuide.bottom), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.left, this.mGuide.bottom - i), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.bottom, this.mGuide.right - i, this.mGuide.bottom), this.mGuidePaint);
        canvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.bottom, this.mGuide.right, this.mGuide.bottom - i), this.mGuidePaint);
        DetectionInfo detectionInfo2 = this.mDInfo;
        if (detectionInfo2 != null) {
            if (detectionInfo2.topEdge) {
                canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.right, this.mGuide.top), this.mGuidePaint);
            }
            if (this.mDInfo.bottomEdge) {
                canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.right, this.mGuide.bottom), this.mGuidePaint);
            }
            if (this.mDInfo.leftEdge) {
                canvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left, this.mGuide.bottom), this.mGuidePaint);
            }
            if (this.mDInfo.rightEdge) {
                canvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right, this.mGuide.bottom), this.mGuidePaint);
            }
            if (this.mDInfo.numVisibleEdges() < 3) {
                float f = this.mScale;
                float f2 = 34.0f * f;
                float f3 = f * 26.0f;
                Util.setupTextPaintStyle(this.mGuidePaint);
                this.mGuidePaint.setTextAlign(Paint.Align.CENTER);
                this.mGuidePaint.setTextSize(f3);
                canvas.translate(this.mGuide.left + (this.mGuide.width() / 2), this.mGuide.top + (this.mGuide.height() / 2));
                canvas.rotate(this.mRotationFlip * this.mRotation);
                String str = this.scanInstructions;
                if (str != null && str != "") {
                    float f4 = (-((((r2.length - 1) * f2) - f3) / 2.0f)) - 3.0f;
                    for (String str2 : str.split(StringUtils.LF)) {
                        canvas.drawText(str2, 0.0f, f4, this.mGuidePaint);
                        f4 += f2;
                    }
                }
            }
        }
        canvas.restore();
        if (!this.hideCardIOLogo) {
            canvas.save();
            canvas.translate(this.mLogoRect.exactCenterX(), this.mLogoRect.exactCenterY());
            canvas.rotate(this.mRotationFlip * this.mRotation);
            Logo logo = this.mLogo;
            float f5 = this.mScale;
            logo.draw(canvas, 100.0f * f5, f5 * 50.0f);
            canvas.restore();
        }
        if (this.mShowTorch) {
            canvas.save();
            canvas.translate(this.mTorchRect.exactCenterX(), this.mTorchRect.exactCenterY());
            canvas.rotate(this.mRotationFlip * this.mRotation);
            this.mTorch.draw(canvas);
            canvas.restore();
        }
    }

    public void setDetectedCard(CreditCard creditCard) {
        this.mDetectedCard = creditCard;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Rect rect;
        try {
            if ((motionEvent.getAction() & 255) == 0) {
                Rect rectGivenCenter = Util.rectGivenCenter(new Point((int) motionEvent.getX(), (int) motionEvent.getY()), 20, 20);
                if (this.mShowTorch && (rect = this.mTorchRect) != null && Rect.intersects(rect, rectGivenCenter)) {
                    this.mScanActivityRef.get().toggleFlash();
                } else {
                    this.mScanActivityRef.get().triggerAutoFocus();
                }
            }
            return false;
        } catch (NullPointerException unused) {
            Log.d(TAG, "NullPointerException caught in onTouchEvent method");
            return false;
        }
    }

    private void decorateBitmap() {
        RectF rectF = new RectF(2.0f, 2.0f, this.mBitmap.getWidth() - 2, this.mBitmap.getHeight() - 2);
        float height = this.mBitmap.getHeight() * 0.06666667f;
        Bitmap createBitmap = Bitmap.createBitmap(this.mBitmap.getWidth(), this.mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        Paint paint = new Paint(1);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF, height, height, paint);
        Paint paint2 = new Paint();
        paint2.setFilterBitmap(false);
        Canvas canvas2 = new Canvas(this.mBitmap);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint2);
        paint2.setXfermode(null);
        createBitmap.recycle();
    }

    public void markupCard() {
        if (this.mBitmap == null) {
            return;
        }
        if (this.mDetectedCard.flipped) {
            Matrix matrix = new Matrix();
            matrix.setRotate(180.0f, this.mBitmap.getWidth() / 2, this.mBitmap.getHeight() / 2);
            Bitmap bitmap = this.mBitmap;
            this.mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.mBitmap.getHeight(), matrix, false);
        }
        Canvas canvas = new Canvas(this.mBitmap);
        Paint paint = new Paint();
        Util.setupTextPaintStyle(paint);
        paint.setTextSize(this.mScale * 28.0f);
        int length = this.mDetectedCard.cardNumber.length();
        float width = this.mBitmap.getWidth() / 428.0f;
        int i = (int) ((this.mDetectedCard.yoff * width) - 6.0f);
        for (int i2 = 0; i2 < length; i2++) {
            canvas.drawText("" + this.mDetectedCard.cardNumber.charAt(i2), (int) (this.mDetectedCard.xoff[i2] * width), i, paint);
        }
    }

    public boolean isAnimating() {
        return this.mState != 0;
    }

    public void setCameraPreviewRect(Rect rect) {
        this.mCameraPreviewRect = rect;
    }

    public void setTorchOn(boolean z) {
        this.mTorch.setOn(z);
        invalidate();
    }

    public void setUseCardIOLogo(boolean z) {
        this.mLogo.loadLogo(z);
    }

    public Rect getTorchRect() {
        return this.mTorchRect;
    }
}
