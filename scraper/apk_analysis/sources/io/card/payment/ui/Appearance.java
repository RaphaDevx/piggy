package io.card.payment.ui;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;

/* loaded from: classes2.dex */
public class Appearance {
    public static final Drawable ACTIONBAR_BACKGROUND;
    public static final int BUTTON_PRIMARY_DISABLED_COLOR;
    public static final int BUTTON_PRIMARY_FOCUS_COLOR;
    public static final int BUTTON_PRIMARY_NORMAL_COLOR;
    public static final int BUTTON_PRIMARY_PRESSED_COLOR;
    public static final int BUTTON_SECONDARY_DISABLED_COLOR;
    public static final int BUTTON_SECONDARY_FOCUS_COLOR;
    public static final int BUTTON_SECONDARY_NORMAL_COLOR;
    public static final int BUTTON_SECONDARY_PRESSED_COLOR;
    public static final int DEFAULT_BACKGROUND_COLOR;
    public static final int PAL_BLUE_COLOR;
    public static final int PAL_BLUE_COLOR_OPACITY_66;
    public static final int PAY_BLUE_COLOR;
    public static final int TEXT_COLOR_ERROR;
    public static final int TEXT_COLOR_LABEL;
    public static final int TEXT_COLOR_LIGHT;
    public static final Typeface TYPEFACE_BUTTON;
    public static final int[] BUTTON_STATE_PRESSED = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] BUTTON_STATE_NORMAL = {R.attr.state_enabled};
    public static final int[] BUTTON_STATE_DISABLED = {-16842910};
    public static final int[] BUTTON_STATE_FOCUSED = {R.attr.state_focused};

    static {
        int parseColor = Color.parseColor("#003087");
        PAY_BLUE_COLOR = parseColor;
        int parseColor2 = Color.parseColor("#009CDE");
        PAL_BLUE_COLOR = parseColor2;
        int parseColor3 = Color.parseColor("#aa009CDE");
        PAL_BLUE_COLOR_OPACITY_66 = parseColor3;
        ACTIONBAR_BACKGROUND = new ColorDrawable(Color.parseColor("#717074"));
        DEFAULT_BACKGROUND_COLOR = Color.parseColor("#f5f5f5");
        BUTTON_PRIMARY_NORMAL_COLOR = parseColor2;
        BUTTON_PRIMARY_FOCUS_COLOR = parseColor3;
        BUTTON_PRIMARY_PRESSED_COLOR = parseColor;
        BUTTON_PRIMARY_DISABLED_COLOR = Color.parseColor("#c5ddeb");
        BUTTON_SECONDARY_NORMAL_COLOR = Color.parseColor("#717074");
        BUTTON_SECONDARY_FOCUS_COLOR = Color.parseColor("#aa717074");
        BUTTON_SECONDARY_PRESSED_COLOR = Color.parseColor("#5a5a5d");
        BUTTON_SECONDARY_DISABLED_COLOR = Color.parseColor("#f5f5f5");
        int parseColor4 = Color.parseColor("#515151");
        TEXT_COLOR_LIGHT = parseColor4;
        TEXT_COLOR_ERROR = Color.parseColor("#b32317");
        TEXT_COLOR_LABEL = parseColor4;
        TYPEFACE_BUTTON = typefaceLight();
    }

    public static Drawable buttonBackgroundPrimary(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_PRIMARY_PRESSED_COLOR));
        stateListDrawable.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_PRIMARY_DISABLED_COLOR));
        stateListDrawable.addState(BUTTON_STATE_FOCUSED, buttonBackgroundPrimaryFocused(context));
        stateListDrawable.addState(BUTTON_STATE_NORMAL, buttonBackgroundPrimaryNormal(context));
        return stateListDrawable;
    }

    private static float getFocusBorderWidthPixels(Context context) {
        return (ViewUtil.typedDimensionValueToPixels("4dip", context) / 2.0f) * context.getResources().getDisplayMetrics().density;
    }

    private static Drawable buttonBackgroundPrimaryNormal(Context context) {
        return buttonNormal(BUTTON_PRIMARY_NORMAL_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonBackgroundPrimaryFocused(Context context) {
        return buttonFocused(BUTTON_PRIMARY_NORMAL_COLOR, BUTTON_PRIMARY_FOCUS_COLOR, getFocusBorderWidthPixels(context));
    }

    public static Drawable buttonBackgroundSecondary(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_SECONDARY_PRESSED_COLOR));
        stateListDrawable.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_SECONDARY_DISABLED_COLOR));
        stateListDrawable.addState(BUTTON_STATE_FOCUSED, buttonBackgroundSecondaryFocused(context));
        stateListDrawable.addState(BUTTON_STATE_NORMAL, buttonBackgroundSecondaryNormal(context));
        return stateListDrawable;
    }

    private static Drawable buttonBackgroundSecondaryNormal(Context context) {
        return buttonNormal(BUTTON_SECONDARY_NORMAL_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonBackgroundSecondaryFocused(Context context) {
        return buttonFocused(BUTTON_SECONDARY_NORMAL_COLOR, BUTTON_SECONDARY_FOCUS_COLOR, getFocusBorderWidthPixels(context));
    }

    private static Drawable buttonNormal(int i, float f) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(f * 2.0f);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
        return new LayerDrawable(new Drawable[]{new ColorDrawable(i), shapeDrawable});
    }

    private static Drawable buttonFocused(int i, int i2, float f) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(2.0f * f);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RectShape());
        shapeDrawable2.getPaint().setStrokeWidth(f);
        shapeDrawable2.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable2.getPaint().setColor(i2);
        return new LayerDrawable(new Drawable[]{new ColorDrawable(i), shapeDrawable, shapeDrawable2});
    }

    private static Typeface typefaceLight() {
        return Typeface.create("sans-serif-light", 0);
    }
}
