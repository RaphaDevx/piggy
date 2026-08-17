package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraPositionSerializer;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0425d implements com.scandit.datacapture.barcode.internal.module.spark.internal.x {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.spark.internal.g a;

    public C0425d(com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar) {
        this.a = gVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.x
    public final void a(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar = this.a;
        String json = CameraPositionSerializer.toJson((CameraPosition) value);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", gVar.a);
        jSONObject.put("value", json);
        gVar.a(jSONObject);
    }
}
