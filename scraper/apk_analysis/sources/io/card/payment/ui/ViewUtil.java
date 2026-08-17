package io.card.payment.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class ViewUtil {
    private static final Map<String, Integer> DIMENSION_STRING_CONSTANT = initDimensionStringConstantMap();
    static Pattern DIMENSION_VALUE_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");
    static HashMap<String, Float> pxDimensionLookupTable = new HashMap<>();

    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    static Map<String, Integer> initDimensionStringConstantMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("px", 0);
        hashMap.put("dip", 1);
        hashMap.put("dp", 1);
        hashMap.put("sp", 2);
        hashMap.put("pt", 3);
        hashMap.put("in", 4);
        hashMap.put("mm", 5);
        return Collections.unmodifiableMap(hashMap);
    }

    public static int typedDimensionValueToPixelsInt(String str, Context context) {
        if (str == null) {
            return 0;
        }
        return (int) typedDimensionValueToPixels(str, context);
    }

    public static float typedDimensionValueToPixels(String str, Context context) {
        if (str == null) {
            return 0.0f;
        }
        String lowerCase = str.toLowerCase();
        if (pxDimensionLookupTable.containsKey(lowerCase)) {
            return pxDimensionLookupTable.get(lowerCase).floatValue();
        }
        Matcher matcher = DIMENSION_VALUE_PATTERN.matcher(lowerCase);
        if (!matcher.matches()) {
            throw new NumberFormatException();
        }
        float parseFloat = Float.parseFloat(matcher.group(1));
        Integer num = DIMENSION_STRING_CONSTANT.get(matcher.group(3).toLowerCase());
        if (num == null) {
            num = 1;
        }
        float applyDimension = TypedValue.applyDimension(num.intValue(), parseFloat, context.getResources().getDisplayMetrics());
        pxDimensionLookupTable.put(lowerCase, Float.valueOf(applyDimension));
        return applyDimension;
    }

    public static void setPadding(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        view.setPadding(typedDimensionValueToPixelsInt(str, context), typedDimensionValueToPixelsInt(str2, context), typedDimensionValueToPixelsInt(str3, context), typedDimensionValueToPixelsInt(str4, context));
    }

    public static void setMargins(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(typedDimensionValueToPixelsInt(str, context), typedDimensionValueToPixelsInt(str2, context), typedDimensionValueToPixelsInt(str3, context), typedDimensionValueToPixelsInt(str4, context));
        }
    }

    public static void setDimensions(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
    }

    public static void styleAsButton(Button button, boolean z, Context context, boolean z2) {
        Drawable buttonBackgroundSecondary;
        setDimensions(button, -1, -2);
        button.setFocusable(true);
        setPadding(button, "10dip", "0dip", "10dip", "0dip");
        if (z2) {
            return;
        }
        if (z) {
            buttonBackgroundSecondary = Appearance.buttonBackgroundPrimary(context);
        } else {
            buttonBackgroundSecondary = Appearance.buttonBackgroundSecondary(context);
        }
        setBackground(button, buttonBackgroundSecondary);
        button.setGravity(17);
        button.setMinimumHeight(typedDimensionValueToPixelsInt("54dip", context));
        button.setTextColor(-1);
        button.setTextSize(20.0f);
        button.setTypeface(Appearance.TYPEFACE_BUTTON);
    }
}
