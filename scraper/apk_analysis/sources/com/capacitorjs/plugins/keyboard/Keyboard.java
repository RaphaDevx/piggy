package com.capacitorjs.plugins.keyboard;

import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

/* loaded from: classes3.dex */
public class Keyboard {
    static final String EVENT_KB_DID_HIDE = "keyboardDidHide";
    static final String EVENT_KB_DID_SHOW = "keyboardDidShow";
    static final String EVENT_KB_WILL_HIDE = "keyboardWillHide";
    static final String EVENT_KB_WILL_SHOW = "keyboardWillShow";
    private AppCompatActivity activity;
    private FrameLayout.LayoutParams frameLayoutParams;
    private KeyboardEventListener keyboardEventListener;
    private View mChildOfContent;
    private View rootView;
    private int usableHeightPrevious;

    interface KeyboardEventListener {
        void onKeyboardEvent(String str, int i);
    }

    public void setKeyboardEventListener(KeyboardEventListener keyboardEventListener) {
        this.keyboardEventListener = keyboardEventListener;
    }

    public Keyboard(final AppCompatActivity appCompatActivity, final boolean z) {
        this.activity = appCompatActivity;
        FrameLayout frameLayout = (FrameLayout) appCompatActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        View rootView = frameLayout.getRootView();
        this.rootView = rootView;
        ViewCompat.setWindowInsetsAnimationCallback(rootView, new WindowInsetsAnimationCompat.Callback(0) { // from class: com.capacitorjs.plugins.keyboard.Keyboard.1
            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
                return windowInsetsCompat;
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
                boolean isVisible = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).isVisible(WindowInsetsCompat.Type.ime());
                int i = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).getInsets(WindowInsetsCompat.Type.ime()).bottom;
                float f = appCompatActivity.getResources().getDisplayMetrics().density;
                if (z) {
                    Keyboard.this.possiblyResizeChildOfContent(isVisible);
                }
                if (isVisible) {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_WILL_SHOW, Math.round(i / f));
                } else {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_WILL_HIDE, 0);
                }
                return super.onStart(windowInsetsAnimationCompat, boundsCompat);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
                super.onEnd(windowInsetsAnimationCompat);
                boolean isVisible = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).isVisible(WindowInsetsCompat.Type.ime());
                int i = ViewCompat.getRootWindowInsets(Keyboard.this.rootView).getInsets(WindowInsetsCompat.Type.ime()).bottom;
                float f = appCompatActivity.getResources().getDisplayMetrics().density;
                if (isVisible) {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_DID_SHOW, Math.round(i / f));
                } else {
                    Keyboard.this.keyboardEventListener.onKeyboardEvent(Keyboard.EVENT_KB_DID_HIDE, 0);
                }
            }
        });
        View childAt = frameLayout.getChildAt(0);
        this.mChildOfContent = childAt;
        this.frameLayoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
    }

    public void show() {
        ((InputMethodManager) this.activity.getSystemService("input_method")).showSoftInput(this.activity.getCurrentFocus(), 0);
    }

    public boolean hide() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method");
        View currentFocus = this.activity.getCurrentFocus();
        if (currentFocus == null) {
            return false;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent(boolean z) {
        int computeUsableHeight = z ? computeUsableHeight() : -1;
        if (this.usableHeightPrevious != computeUsableHeight) {
            this.frameLayoutParams.height = computeUsableHeight;
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return isOverlays() ? rect.bottom : rect.height();
    }

    private boolean isOverlays() {
        return (this.activity.getWindow().getDecorView().getSystemUiVisibility() & 1024) == 1024;
    }
}
