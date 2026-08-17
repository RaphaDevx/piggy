package ch.coop.capacitor.splashscreenpro;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes3.dex */
public class SplashScreenProView {
    public boolean _isHiding;
    public boolean _isVisible;
    private final Context context;
    protected Dialog dialog;
    private final String DEFAULT_GRADIENT_START_COLOR = "#0097d8";
    private final String DEFAULT_GRADIENT_END_COLOR = "#0068b2";
    private final String PREFERENCES_KEY_PREFIX = "splashScreen.gradient";

    public SplashScreenProView(Context context) {
        this.context = context;
    }

    public void show(AppCompatActivity appCompatActivity) {
        _showDialog(appCompatActivity);
    }

    public void hide(AppCompatActivity appCompatActivity) {
        _hideDialog(appCompatActivity);
    }

    public boolean isVisible() {
        return this._isVisible;
    }

    public void onPause() {
        _tearDown();
    }

    public void onDestroy() {
        _tearDown();
    }

    public void _showDialog(final AppCompatActivity appCompatActivity) {
        if (appCompatActivity == null || appCompatActivity.isFinishing() || this._isVisible) {
            return;
        }
        appCompatActivity.runOnUiThread(new Runnable() { // from class: ch.coop.capacitor.splashscreenpro.SplashScreenProView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashScreenProView.this.lambda$_showDialog$1(appCompatActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_showDialog$1(final AppCompatActivity appCompatActivity) {
        Dialog dialog = new Dialog(appCompatActivity, R.style.DialogTheme);
        this.dialog = dialog;
        if (dialog.getWindow() != null) {
            SharedPreferences preferences = getPreferences();
            this.dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            this.dialog.getWindow().setBackgroundDrawable(getBackgroundGradient(preferences));
            this.dialog.getWindow().setStatusBarColor(getBackgroundColorEnd(preferences));
        }
        int identifier = this.context.getResources().getIdentifier("splash_screen_pro_view", "layout", this.context.getPackageName());
        if (identifier != 0) {
            this.dialog.setContentView(identifier);
        }
        this.dialog.setCancelable(false);
        this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: ch.coop.capacitor.splashscreenpro.SplashScreenProView$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return SplashScreenProView.lambda$_showDialog$0(AppCompatActivity.this, dialogInterface, i, keyEvent);
            }
        });
        if (!this.dialog.isShowing()) {
            this.dialog.show();
        }
        this._isVisible = true;
    }

    static /* synthetic */ boolean lambda$_showDialog$0(AppCompatActivity appCompatActivity, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Boolean bool = false;
        if (i == 4 && keyEvent.getAction() == 1) {
            appCompatActivity.finish();
            bool = true;
        }
        return bool.booleanValue();
    }

    private SharedPreferences getPreferences() {
        return this.context.getSharedPreferences("CapacitorStorage", 0);
    }

    private int getBackgroundColorStart(SharedPreferences sharedPreferences) {
        return getColorFromPreferences(sharedPreferences, "backgroundStart", "#0097d8");
    }

    private int getBackgroundColorEnd(SharedPreferences sharedPreferences) {
        return getColorFromPreferences(sharedPreferences, "backgroundEnd", "#0068b2");
    }

    private int getColorFromPreferences(SharedPreferences sharedPreferences, String str, String str2) {
        try {
            return Color.parseColor(sharedPreferences.getString("splashScreen.gradient." + str, str2));
        } catch (IllegalArgumentException unused) {
            return Color.parseColor(str2);
        }
    }

    private GradientDrawable getBackgroundGradient(SharedPreferences sharedPreferences) {
        float parseFloat = Float.parseFloat(sharedPreferences.getString("splashScreen.gradient.backgroundEndPercentage", "0.98"));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        gradientDrawable.setGradientType(0);
        gradientDrawable.setColors(new int[]{getBackgroundColorStart(sharedPreferences), getBackgroundColorEnd(sharedPreferences)}, new float[]{0.0f, parseFloat});
        return gradientDrawable;
    }

    public void _hideDialog(final AppCompatActivity appCompatActivity) {
        if (this._isHiding) {
            return;
        }
        this._isHiding = true;
        appCompatActivity.runOnUiThread(new Runnable() { // from class: ch.coop.capacitor.splashscreenpro.SplashScreenProView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SplashScreenProView.this.lambda$_hideDialog$2(appCompatActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_hideDialog$2(AppCompatActivity appCompatActivity) {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        if (!appCompatActivity.isFinishing() && !appCompatActivity.isDestroyed()) {
            this.dialog.dismiss();
        }
        this.dialog = null;
        _tearDown();
    }

    public void _tearDown() {
        this._isHiding = false;
        this._isVisible = false;
    }
}
