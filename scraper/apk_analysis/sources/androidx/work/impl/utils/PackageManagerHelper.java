package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.Logger;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    private PackageManagerHelper() {
    }

    public static void setComponentEnabled(Context context, Class<?> klazz, boolean enabled) {
        String str = ExtentionsKt.ENABLED_KEY;
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, klazz.getName()), enabled ? 1 : 2, 1);
            Logger logger = Logger.get();
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(klazz.getName());
            sb.append(StringUtils.SPACE);
            sb.append(enabled ? ExtentionsKt.ENABLED_KEY : "disabled");
            logger.debug(str2, sb.toString());
        } catch (Exception e) {
            Logger logger2 = Logger.get();
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(klazz.getName());
            sb2.append("could not be ");
            if (!enabled) {
                str = "disabled";
            }
            sb2.append(str);
            logger2.debug(str3, sb2.toString(), e);
        }
    }

    public static boolean isComponentExplicitlyEnabled(Context context, Class<?> klazz) {
        return isComponentExplicitlyEnabled(context, klazz.getName());
    }

    public static boolean isComponentExplicitlyEnabled(Context context, String className) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, className)) == 1;
    }
}
