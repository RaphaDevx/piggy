package com.salesforce.marketingcloud.messages.iam;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class h {
    public static final InAppMessage.CloseButton a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        InAppMessage.Alignment alignment = InAppMessage.Alignment.end;
        String optString = jSONObject.optString("alignment");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = o.b(optString);
        if (b != null) {
            alignment = InAppMessage.Alignment.valueOf(b);
        }
        return new InAppMessage.CloseButton(alignment);
    }

    public static final InAppMessage.Media b(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String string = jSONObject.getString("url");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        InAppMessage.Media.ImageSize imageSize = InAppMessage.Media.ImageSize.e2e;
        String optString = jSONObject.optString(RRWebVideoEvent.JsonKeys.SIZE);
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = o.b(optString);
        if (b != null) {
            imageSize = InAppMessage.Media.ImageSize.valueOf(b);
        }
        InAppMessage.Media.ImageSize imageSize2 = imageSize;
        String optString2 = jSONObject.optString("altText");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b2 = o.b(optString2);
        InAppMessage.Size size = InAppMessage.Size.s;
        String optString3 = jSONObject.optString("borderWidth");
        Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
        String b3 = o.b(optString3);
        InAppMessage.Size valueOf = b3 != null ? InAppMessage.Size.valueOf(b3) : size;
        String optString4 = jSONObject.optString("borderColor");
        Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
        String b4 = o.b(optString4);
        String optString5 = jSONObject.optString("cornerRadius");
        Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
        String b5 = o.b(optString5);
        return new InAppMessage.Media(string, imageSize2, b2, valueOf, b4, b5 != null ? InAppMessage.Size.valueOf(b5) : size);
    }

    public static final InAppMessage.TextField c(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String string = jSONObject.getString("text");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        InAppMessage.Size size = InAppMessage.Size.s;
        String optString = jSONObject.optString("fontSize");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = o.b(optString);
        if (b != null) {
            size = InAppMessage.Size.valueOf(b);
        }
        String optString2 = jSONObject.optString("fontColor");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b2 = o.b(optString2);
        InAppMessage.Alignment alignment = InAppMessage.Alignment.center;
        String optString3 = jSONObject.optString("alignment");
        Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
        String b3 = o.b(optString3);
        if (b3 != null) {
            alignment = InAppMessage.Alignment.valueOf(b3);
        }
        return new InAppMessage.TextField(string, size, b2, alignment);
    }

    public static final List<InAppMessage.Button> a(JSONArray jSONArray) {
        InAppMessage.Button button;
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
        ArrayList arrayList2 = new ArrayList();
        for (JSONObject jSONObject2 : arrayList) {
            try {
                String string2 = jSONObject2.getString("id");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                int optInt = jSONObject2.optInt(FirebaseAnalytics.Param.INDEX, 0);
                String string3 = jSONObject2.getString("text");
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                InAppMessage.Button.ActionType actionType = InAppMessage.Button.ActionType.close;
                String optString = jSONObject2.optString("actionType");
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                String b = o.b(optString);
                if (b != null) {
                    actionType = InAppMessage.Button.ActionType.valueOf(b);
                }
                String optString2 = jSONObject2.optString("actionAndroid");
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                String b2 = o.b(optString2);
                String optString3 = jSONObject2.optString("fontColor");
                Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                String b3 = o.b(optString3);
                InAppMessage.Size size = InAppMessage.Size.s;
                String optString4 = jSONObject2.optString("fontSize");
                Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                String b4 = o.b(optString4);
                InAppMessage.Size valueOf = b4 != null ? InAppMessage.Size.valueOf(b4) : size;
                String optString5 = jSONObject2.optString("backgroundColor");
                Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                String b5 = o.b(optString5);
                String optString6 = jSONObject2.optString("borderColor");
                Intrinsics.checkNotNullExpressionValue(optString6, "optString(...)");
                String b6 = o.b(optString6);
                String optString7 = jSONObject2.optString("borderWidth");
                Intrinsics.checkNotNullExpressionValue(optString7, "optString(...)");
                String b7 = o.b(optString7);
                InAppMessage.Size valueOf2 = b7 != null ? InAppMessage.Size.valueOf(b7) : size;
                String optString8 = jSONObject2.optString("cornerRadius");
                Intrinsics.checkNotNullExpressionValue(optString8, "optString(...)");
                String b8 = o.b(optString8);
                button = new InAppMessage.Button(string2, optInt, string3, actionType, b2, b3, valueOf, b5, b6, valueOf2, b8 != null ? InAppMessage.Size.valueOf(b8) : size);
            } catch (Exception unused) {
                button = null;
            }
            InAppMessage.Button button2 = button;
            if (button2 != null) {
                arrayList2.add(button2);
            }
        }
        return arrayList2;
    }
}
