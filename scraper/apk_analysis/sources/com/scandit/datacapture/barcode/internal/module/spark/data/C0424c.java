package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.barcode.spark.serialization.SparkScanScanningModeSerializer;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0424c implements com.scandit.datacapture.barcode.internal.module.spark.internal.x {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.spark.internal.g a;

    public C0424c(com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar) {
        this.a = gVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.x
    public final void a(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar = this.a;
        JSONObject jSONObject = new JSONObject(SparkScanScanningModeSerializer.toJson((SparkScanScanningMode) value, true));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("key", gVar.a);
        jSONObject2.put("value", jSONObject);
        gVar.a(jSONObject2);
    }
}
