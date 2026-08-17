package com.salesforce.marketingcloud.internal;

import androidx.exifinterface.media.ExifInterface;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class o {
    private static final String a = com.salesforce.marketingcloud.g.a("GeneralUtils");
    private static final String b = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final TimeZone c;
    private static final Charset d;

    static final class a extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to parse " + this.b + " as a Date.";
        }
    }

    static {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Intrinsics.checkNotNullExpressionValue(timeZone, "getTimeZone(...)");
        c = timeZone;
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        d = forName;
    }

    public static final TimeZone a() {
        return c;
    }

    public static final Charset b() {
        return d;
    }

    public static final String a(Date date) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
        simpleDateFormat.setTimeZone(c);
        String format = simpleDateFormat.format(date);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final String b(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return null;
        }
        return str;
    }

    public static final Map<String, String> b(JSONArray jSONArray) {
        JSONObject jSONObject;
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList<JSONObject> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = jSONArray.getJSONObject(nextInt);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(jSONArray.getInt(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(jSONArray.getDouble(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(jSONArray.getLong(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(jSONArray.getBoolean(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                Object string = jSONArray.getString(nextInt);
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) string;
            } else {
                Object obj = jSONArray.get(nextInt);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) obj;
            }
            arrayList.add(jSONObject);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (JSONObject jSONObject2 : arrayList) {
            Pair pair = TuplesKt.to(jSONObject2.optString("key"), jSONObject2.optString("value"));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public static final Date a(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
            simpleDateFormat.setTimeZone(c);
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            com.salesforce.marketingcloud.g.a.b(a, e, new a(str));
            return null;
        }
    }

    public static final /* synthetic */ <T> List<T> a(JSONArray jSONArray) {
        Object obj;
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                obj = jSONArray.getJSONObject(nextInt);
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                obj = Integer.valueOf(jSONArray.getInt(nextInt));
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                obj = Double.valueOf(jSONArray.getDouble(nextInt));
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                obj = Long.valueOf(jSONArray.getLong(nextInt));
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                obj = Boolean.valueOf(jSONArray.getBoolean(nextInt));
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                obj = jSONArray.getString(nextInt);
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            } else {
                obj = jSONArray.get(nextInt);
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static final boolean b(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= charSequence.length()) {
                z = true;
                break;
            }
            char charAt = charSequence.charAt(i);
            if (!Character.isWhitespace(charAt) && charAt != 160 && charAt != 8199 && charAt != 8239) {
                break;
            }
            i++;
        }
        return !z;
    }

    public static final /* synthetic */ <T extends Enum<T>> T a(JSONObject jSONObject, String name) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        String string = jSONObject.getString(name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) Enum.valueOf(null, string);
    }

    public static final /* synthetic */ <T extends Enum<T>> T a(JSONObject jSONObject, String name, T t) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(t, "default");
        String optString = jSONObject.optString(name);
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b2 = b(optString);
        if (b2 == null) {
            return t;
        }
        Intrinsics.reifiedOperationMarker(5, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) Enum.valueOf(null, b2);
    }

    public static final boolean a(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!Character.isWhitespace(charAt) && charAt != 160 && charAt != 8199 && charAt != 8239) {
                return false;
            }
        }
        return true;
    }

    public static final JSONArray a(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", key);
            jSONObject.put("value", value);
            arrayList.add(jSONObject);
        }
        return new JSONArray((Collection) arrayList);
    }
}
