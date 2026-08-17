package com.getcapacitor;

import android.R;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/* loaded from: classes3.dex */
public class CapacitorWebView extends WebView {
    private Bridge bridge;
    private BaseInputConnection capInputConnection;

    public CapacitorWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    @Override // android.webkit.WebView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        CapConfig loadDefault;
        Bridge bridge = this.bridge;
        if (bridge != null) {
            loadDefault = bridge.getConfig();
        } else {
            loadDefault = CapConfig.loadDefault(getContext());
        }
        if (loadDefault.isInputCaptured()) {
            if (this.capInputConnection == null) {
                this.capInputConnection = new BaseInputConnection(this, false);
            }
            return this.capInputConnection;
        }
        return super.onCreateInputConnection(editorInfo);
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 2) {
            evaluateJavascript("document.activeElement.value = document.activeElement.value + '" + keyEvent.getCharacters() + "';", null);
            return false;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void edgeToEdgeHandler(Bridge bridge) {
        String adjustMarginsForEdgeToEdge = bridge.getConfig().adjustMarginsForEdgeToEdge();
        if (adjustMarginsForEdgeToEdge.equals("disable")) {
            return;
        }
        boolean equals = adjustMarginsForEdgeToEdge.equals("force");
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 35 && adjustMarginsForEdgeToEdge.equals("auto")) {
            TypedValue typedValue = new TypedValue();
            boolean resolveAttribute = getContext().getTheme().resolveAttribute(R.attr.windowOptOutEdgeToEdgeEnforcement, typedValue, true);
            boolean z2 = typedValue.data != 0;
            if (!resolveAttribute || !z2) {
                z = true;
            }
        }
        if (equals || z) {
            ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() { // from class: com.getcapacitor.CapacitorWebView$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    return CapacitorWebView.lambda$edgeToEdgeHandler$0(view, windowInsetsCompat);
                }
            });
        }
    }

    static /* synthetic */ WindowInsetsCompat lambda$edgeToEdgeHandler$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.leftMargin = insets.left;
        marginLayoutParams.bottomMargin = insets.bottom;
        marginLayoutParams.rightMargin = insets.right;
        marginLayoutParams.topMargin = insets.top;
        view.setLayoutParams(marginLayoutParams);
        return WindowInsetsCompat.CONSUMED;
    }
}
