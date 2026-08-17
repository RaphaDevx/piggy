package com.salesforce.marketingcloud.messages.iam;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.salesforce.marketingcloud.R;

/* loaded from: classes2.dex */
public class SwipeDismissConstraintLayout extends ConstraintLayout {
    private static final float DRAG_SENSITIVITY = 1.0f;
    ViewDragHelper dragHelper;
    int draggingState;
    SwipeDismissListener listener;
    float minScaledFlingVelocity;
    View swipeTarget;
    private int swipeTargetId;

    public interface SwipeDismissListener {
        void onDismissed();

        void onSwipeStarted();

        void onViewSettled();
    }

    public class a extends ViewDragHelper.Callback {
        private int a;

        public a() {
        }

        private boolean a(View view, float f) {
            if (Math.abs(f) <= SwipeDismissConstraintLayout.this.minScaledFlingVelocity) {
                return false;
            }
            int left = view.getLeft();
            int i = this.a;
            return (left < i && f < 0.0f) || (left > i && f > 0.0f);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return MathUtils.clamp(this.a - view.getWidth(), i, this.a + view.getWidth());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            this.a = (int) ((SwipeDismissConstraintLayout.this.getWidth() - view.getWidth()) * 0.5f);
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
            if (swipeDismissListener != null) {
                swipeDismissListener.onSwipeStarted();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            SwipeDismissConstraintLayout.this.draggingState = i;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i;
            boolean z;
            int width = view.getWidth();
            if (a(view, f)) {
                int x = (int) (this.a - SwipeDismissConstraintLayout.this.getX());
                int left = view.getLeft();
                int i2 = this.a;
                i = left < i2 ? (i2 - width) - x : i2 + width + x;
                z = true;
            } else {
                i = this.a;
                z = false;
            }
            if (SwipeDismissConstraintLayout.this.dragHelper.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, SwipeDismissConstraintLayout.this.new b(view, z));
            } else {
                SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
                if (swipeDismissListener != null) {
                    if (z) {
                        swipeDismissListener.onDismissed();
                    } else {
                        swipeDismissListener.onViewSettled();
                    }
                }
            }
            SwipeDismissConstraintLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return view == SwipeDismissConstraintLayout.this.swipeTarget;
        }
    }

    private class b implements Runnable {
        private final View b;
        private final boolean c;

        b(View view, boolean z) {
            this.b = view;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper viewDragHelper = SwipeDismissConstraintLayout.this.dragHelper;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.b, this);
                return;
            }
            SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
            if (swipeDismissListener != null) {
                if (this.c) {
                    swipeDismissListener.onDismissed();
                } else {
                    swipeDismissListener.onViewSettled();
                }
            }
        }
    }

    public SwipeDismissConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwipeDismissConstraintLayout, 0, 0);
        try {
            this.swipeTargetId = obtainStyledAttributes.getResourceId(R.styleable.SwipeDismissConstraintLayout_swipeTargetId, 0);
            obtainStyledAttributes.recycle();
            this.dragHelper = ViewDragHelper.create(this, 1.0f, new a());
            this.minScaledFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private boolean isTarget(MotionEvent motionEvent) {
        int i;
        View view = this.swipeTarget;
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int i2 = iArr[0];
        return rawX > i2 && rawX < i2 + this.swipeTarget.getMeasuredWidth() && rawY > (i = iArr[1]) && rawY < i + this.swipeTarget.getMeasuredWidth();
    }

    boolean isMoving() {
        int i = this.draggingState;
        return i == 1 || i == 2;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        this.swipeTarget = findViewById(this.swipeTargetId);
        super.onFinishInflate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return isTarget(motionEvent) && this.dragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isTarget(motionEvent) && !isMoving()) {
            return super.onTouchEvent(motionEvent);
        }
        this.dragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void setListener(SwipeDismissListener swipeDismissListener) {
        this.listener = swipeDismissListener;
    }

    public SwipeDismissConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }
}
