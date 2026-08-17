package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.core.internal.sdk.analytics.NativePropertyBehaviorSubject;
import com.scandit.datacapture.core.internal.sdk.analytics.NativePropertySubscriber;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class g extends NativePropertyBehaviorSubject {
    public final String a;
    public final CopyOnWriteArrayList b;
    public JSONObject c;

    public g(String keyName, String str) {
        Intrinsics.checkNotNullParameter(keyName, "keyName");
        this.a = keyName;
        this.b = new CopyOnWriteArrayList();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", keyName);
        jSONObject.put("value", str);
        this.c = jSONObject;
    }

    public final void a(JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "newValue");
        if (Intrinsics.areEqual(jsonObject, this.c)) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.b;
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<this>");
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        String jsonString = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonString, "toString(...)");
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<this>");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            ((NativePropertySubscriber) it.next()).onPropertyChanged(new JSONObject(jsonString));
        }
        this.c = jsonObject;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.analytics.NativePropertyBehaviorSubject
    public final void addSubscriber(NativePropertySubscriber nativePropertySubscriber) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.b;
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<this>");
        if (nativePropertySubscriber != null) {
            copyOnWriteArrayList.add(nativePropertySubscriber);
        }
        if (nativePropertySubscriber != null) {
            List listOf = CollectionsKt.listOf(nativePropertySubscriber);
            JSONObject jsonObject = this.c;
            Intrinsics.checkNotNullParameter(listOf, "<this>");
            Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
            String jsonString = jsonObject.toString();
            Intrinsics.checkNotNullExpressionValue(jsonString, "toString(...)");
            Intrinsics.checkNotNullParameter(listOf, "<this>");
            Intrinsics.checkNotNullParameter(jsonString, "jsonString");
            Iterator it = listOf.iterator();
            while (it.hasNext()) {
                ((NativePropertySubscriber) it.next()).onPropertyChanged(new JSONObject(jsonString));
            }
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.analytics.NativePropertyBehaviorSubject
    public final void removeSubscriber(NativePropertySubscriber nativePropertySubscriber) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.b;
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<this>");
        if (nativePropertySubscriber != null) {
            copyOnWriteArrayList.remove(nativePropertySubscriber);
        }
    }
}
