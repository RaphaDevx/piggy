package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class e {
    public static final JSONArray a(Function1 function1) {
        Bitmap a;
        BarcodePickState[] values = BarcodePickState.values();
        JSONArray jSONArray = new JSONArray();
        for (BarcodePickState barcodePickState : values) {
            BarcodePickIcon barcodePickIcon = (BarcodePickIcon) function1.invoke(barcodePickState);
            if (!Intrinsics.areEqual(barcodePickIcon, m.a) && (a = h.a(barcodePickIcon, AppAndroidEnvironment.INSTANCE.getApplicationContext())) != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(BarcodePickDeserializer.FIELD_BARCODE_PICK_STATE, barcodePickState.toJsonString());
                jSONObject.put(BarcodePickDeserializer.FIELD_ICON, BitmapExtensionsKt.toBase64(a));
                jSONArray.put(jSONObject);
            }
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }
}
