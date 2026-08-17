package com.scandit.datacapture.barcode.internal.module.spark.data;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0427f implements com.scandit.datacapture.barcode.internal.module.spark.internal.x {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.spark.internal.g a;

    public C0427f(com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar) {
        this.a = gVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.x
    public final void a(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar = this.a;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", gVar.a);
        jSONObject.put("value", value);
        gVar.a(jSONObject);
    }
}
