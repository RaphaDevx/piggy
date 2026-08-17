package ch.coop.supercardapp.core.helpers;

import android.content.Context;
import android.util.TypedValue;

/* loaded from: classes3.dex */
public class ViewHelper {
    public static int dpToPixels(Context context, float f) {
        return Math.round(TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics()));
    }
}
