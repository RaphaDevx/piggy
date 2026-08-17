package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.source.TorchStateSerializer;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0423b implements com.scandit.datacapture.barcode.internal.module.spark.internal.x {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.spark.internal.g a;

    public C0423b(com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar) {
        this.a = gVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.x
    public final void a(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar = this.a;
        String json = TorchStateSerializer.toJson((TorchState) value);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", gVar.a);
        jSONObject.put("value", json);
        gVar.a(jSONObject);
    }
}
