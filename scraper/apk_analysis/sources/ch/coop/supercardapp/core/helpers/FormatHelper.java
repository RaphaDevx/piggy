package ch.coop.supercardapp.core.helpers;

import android.content.Context;
import ch.coop.supercardapp.AppContainer;
import java.util.Locale;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public class FormatHelper {
    public static final float CENTS_PER_CHF = 100.0f;

    public static String formatPoints(Context context, int i) {
        return replaceGroupingSeparator(context, String.format(Locale.US, "%,d", Integer.valueOf(i)));
    }

    public static String formatCents(Context context, int i) {
        return replaceGroupingSeparator(context, String.format(Locale.US, "CHF %,.2f", Float.valueOf(i / 100.0f)));
    }

    private static String replaceGroupingSeparator(Context context, String str) {
        return str.replace(',', "fr".equals(AppContainer.getInstance(context).getAppState().getAppLanguage()) ? ' ' : Typography.rightSingleQuote);
    }
}
