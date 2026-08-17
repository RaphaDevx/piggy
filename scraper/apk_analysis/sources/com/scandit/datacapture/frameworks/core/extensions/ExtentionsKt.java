package com.scandit.datacapture.frameworks.core.extensions;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: Extentions.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001\u001a)\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u0013*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0016¢\u0006\u0002\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u001d\u001a\u00020\u0006*\u00020\u0001\u001a\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u0001*\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\f\u0010 \u001a\u00020\u000f*\u0004\u0018\u00010\b\u001a\u0012\u0010!\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001\u001a?\u0010\"\u001a\u00020\u001f*\u00020\u001f2.\u0010#\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b0%0$\"\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b0%¢\u0006\u0002\u0010&\u001a\u0012\u0010'\u001a\u00020(*\u00020)2\u0006\u0010*\u001a\u00020+\u001a\n\u0010,\u001a\u00020\u0001*\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"%\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00078F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006-"}, d2 = {"DATA_CAPTURE_VIEW_ID_KEY", "", "ENABLED_KEY", "MODE_ID_KEY", "TYPE_KEY", "dataCaptureView", "", "", "", "getDataCaptureView", "(Ljava/util/Map;)Ljava/lang/Integer;", "hexString", "getHexString", "(I)Ljava/lang/String;", "containsKey", "", "key", "findViewOfType", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Landroid/view/ViewGroup;", "viewType", "Ljava/lang/Class;", "(Landroid/view/ViewGroup;Ljava/lang/Class;)Landroid/view/View;", "getContextForModeCreation", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "modeType", "getJsonValueTypeAttribute", "getModeId", "getOrNull", "Lorg/json/JSONObject;", "isValidHiddenPropertyType", "isValueTrue", "put", "pairs", "", "Lkotlin/Pair;", "(Lorg/json/JSONObject;[Lkotlin/Pair;)Lorg/json/JSONObject;", "reject", "", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "throwable", "", "toAARRGGBB", "scandit-datacapture-frameworks-core_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ExtentionsKt {
    public static final String DATA_CAPTURE_VIEW_ID_KEY = "dataCaptureViewId";
    public static final String ENABLED_KEY = "enabled";
    public static final String MODE_ID_KEY = "modeId";
    public static final String TYPE_KEY = "type";

    public static final String getHexString(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.getDefault(), "%08X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        StringBuilder sb = new StringBuilder("#");
        String substring = format.substring(2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        sb.append(substring);
        String substring2 = format.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        sb.append(substring2);
        return sb.toString();
    }

    public static final void reject(FrameworksResult frameworksResult, Throwable throwable) {
        Intrinsics.checkNotNullParameter(frameworksResult, "<this>");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        frameworksResult.error("0", throwable.getMessage(), null);
    }

    public static final DataCaptureContext getContextForModeCreation(FrameworksCaptureContext frameworksCaptureContext, String modeType) {
        Intrinsics.checkNotNullParameter(frameworksCaptureContext, "<this>");
        Intrinsics.checkNotNullParameter(modeType, "modeType");
        DataCaptureContext context = frameworksCaptureContext.getContext();
        if (context != null) {
            return context;
        }
        DefaultFrameworksLog.INSTANCE.getInstance().error("Unable to add the " + modeType + " to the DataCaptureContext, the context is not yet initialized.");
        return null;
    }

    public static final JSONObject put(JSONObject jSONObject, Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair<String, ? extends Object> pair : pairs) {
            jSONObject.put(pair.getFirst(), pair.getSecond());
        }
        return jSONObject;
    }

    public static final String getOrNull(JSONObject jSONObject, String key) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!jSONObject.has(key) || jSONObject.isNull(key)) {
            return null;
        }
        return jSONObject.getString(key);
    }

    public static final Integer getDataCaptureView(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        if (!map.containsKey(DATA_CAPTURE_VIEW_ID_KEY)) {
            return null;
        }
        Object obj = map.get(DATA_CAPTURE_VIEW_ID_KEY);
        Number number = obj instanceof Number ? (Number) obj : null;
        if (number != null) {
            return Integer.valueOf(number.intValue());
        }
        return null;
    }

    public static final String getJsonValueTypeAttribute(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new JsonValue(str).getByKeyAsString("type", "");
    }

    public static final int getModeId(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new JsonValue(str).getByKeyAsInt(MODE_ID_KEY, 0);
    }

    public static final boolean isValueTrue(String str, String key) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return new JsonValue(str).getByKeyAsBoolean(key, false);
    }

    public static final boolean containsKey(String str, String key) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return new JsonValue(str).contains(key);
    }

    public static final String toAARRGGBB(String str) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        String trimStart = StringsKt.trimStart(str, '#');
        if (trimStart.length() >= 2) {
            str2 = trimStart.substring(0, 2);
            Intrinsics.checkNotNullExpressionValue(str2, "substring(...)");
        } else {
            str2 = "FF";
        }
        if (str.length() >= 6) {
            trimStart = trimStart.substring(2, 6);
            Intrinsics.checkNotNullExpressionValue(trimStart, "substring(...)");
        }
        return "#" + str2 + trimStart;
    }

    public static final boolean isValidHiddenPropertyType(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Integer ? true : obj instanceof Double ? true : obj instanceof Float ? true : obj instanceof Long ? true : obj instanceof Short ? true : obj instanceof Byte ? true : obj instanceof Boolean ? true : obj instanceof Character ? true : obj instanceof String) {
            return true;
        }
        return obj instanceof Map;
    }

    public static final <T extends View> T findViewOfType(ViewGroup viewGroup, Class<T> viewType) {
        T t;
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (viewType.isInstance(childAt)) {
                return viewType.cast(childAt);
            }
            if ((childAt instanceof ViewGroup) && (t = (T) findViewOfType((ViewGroup) childAt, viewType)) != null) {
                return t;
            }
        }
        return null;
    }
}
