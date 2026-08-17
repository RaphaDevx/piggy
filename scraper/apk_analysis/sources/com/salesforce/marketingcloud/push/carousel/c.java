package com.salesforce.marketingcloud.push.carousel;

import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.push.carousel.a;
import com.salesforce.marketingcloud.push.data.Style;
import com.salesforce.marketingcloud.push.e;
import com.salesforce.marketingcloud.push.g;
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
public final class c {
    public static final List<a.C0076a> a(JSONArray jSONArray) {
        com.salesforce.marketingcloud.push.data.c cVar;
        com.salesforce.marketingcloud.push.data.c cVar2;
        Style.b bVar;
        ArrayList arrayList;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList<JSONObject> arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject2 = jSONArray.getJSONObject(nextInt);
                if (jSONObject2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject2 = (JSONObject) Integer.valueOf(jSONArray.getInt(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject2 = (JSONObject) Double.valueOf(jSONArray.getDouble(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject2 = (JSONObject) Long.valueOf(jSONArray.getLong(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject2 = (JSONObject) Boolean.valueOf(jSONArray.getBoolean(nextInt));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                Object string = jSONArray.getString(nextInt);
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject2 = (JSONObject) string;
            } else {
                Object obj = jSONArray.get(nextInt);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject2 = (JSONObject) obj;
            }
            arrayList2.add(jSONObject2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (JSONObject jSONObject3 : arrayList2) {
            String optString = jSONObject3.optString("id");
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String b = o.b(optString);
            if (b == null) {
                throw new e("id");
            }
            JSONObject optJSONObject = jSONObject3.optJSONObject(g.j);
            if (optJSONObject != null) {
                Intrinsics.checkNotNull(optJSONObject);
                com.salesforce.marketingcloud.push.data.b a = com.salesforce.marketingcloud.push.data.b.f.a(optJSONObject);
                if (a != null) {
                    JSONObject optJSONObject2 = jSONObject3.optJSONObject(g.n);
                    if (optJSONObject2 != null) {
                        Intrinsics.checkNotNull(optJSONObject2);
                        cVar = com.salesforce.marketingcloud.push.data.c.e.a(optJSONObject2);
                    } else {
                        cVar = null;
                    }
                    JSONObject optJSONObject3 = jSONObject3.optJSONObject(g.o);
                    if (optJSONObject3 != null) {
                        Intrinsics.checkNotNull(optJSONObject3);
                        cVar2 = com.salesforce.marketingcloud.push.data.c.e.a(optJSONObject3);
                    } else {
                        cVar2 = null;
                    }
                    JSONObject optJSONObject4 = jSONObject3.optJSONObject("s");
                    if (optJSONObject4 != null) {
                        Intrinsics.checkNotNull(optJSONObject4);
                        bVar = Style.a.a(optJSONObject4);
                    } else {
                        bVar = null;
                    }
                    JSONArray optJSONArray = jSONObject3.optJSONArray(g.l);
                    if (optJSONArray != null) {
                        IntRange until2 = RangesKt.until(0, optJSONArray.length());
                        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
                        Iterator<Integer> it2 = until2.iterator();
                        while (it2.hasNext()) {
                            int nextInt2 = ((IntIterator) it2).nextInt();
                            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(JSONObject.class);
                            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                jSONObject = optJSONArray.getJSONObject(nextInt2);
                                if (jSONObject == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                                }
                            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                                jSONObject = (JSONObject) Integer.valueOf(optJSONArray.getInt(nextInt2));
                            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                                jSONObject = (JSONObject) Double.valueOf(optJSONArray.getDouble(nextInt2));
                            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                                jSONObject = (JSONObject) Long.valueOf(optJSONArray.getLong(nextInt2));
                            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                                jSONObject = (JSONObject) Boolean.valueOf(optJSONArray.getBoolean(nextInt2));
                            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                                Object string2 = optJSONArray.getString(nextInt2);
                                if (string2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                                }
                                jSONObject = (JSONObject) string2;
                            } else {
                                Object obj2 = optJSONArray.get(nextInt2);
                                if (obj2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                                }
                                jSONObject = (JSONObject) obj2;
                            }
                            arrayList4.add(jSONObject);
                        }
                        ArrayList arrayList5 = new ArrayList();
                        Iterator it3 = arrayList4.iterator();
                        while (it3.hasNext()) {
                            com.salesforce.marketingcloud.push.data.a a2 = com.salesforce.marketingcloud.push.data.a.c.a((JSONObject) it3.next());
                            if (a2 != null) {
                                arrayList5.add(a2);
                            }
                        }
                        arrayList = arrayList5;
                    } else {
                        arrayList = null;
                    }
                    arrayList3.add(new a.C0076a(b, a, cVar, cVar2, bVar, arrayList));
                }
            }
            throw new e(g.j);
        }
        return arrayList3;
    }
}
