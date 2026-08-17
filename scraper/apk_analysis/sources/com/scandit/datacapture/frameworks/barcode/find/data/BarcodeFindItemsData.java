package com.scandit.datacapture.frameworks.barcode.find.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItemSearchOptions;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BarcodeFindItemsData.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0002J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindItemsData;", "", "barcodeFindItemsJson", "", "(Ljava/lang/String;)V", "getBarcodeFindItem", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindItem;", "item", "Lorg/json/JSONObject;", "getBitmapFromBase64EncodedString", "Landroid/graphics/Bitmap;", "baseEncodedString", "getItems", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFindItemsData {
    private final String barcodeFindItemsJson;

    public BarcodeFindItemsData(String barcodeFindItemsJson) {
        Intrinsics.checkNotNullParameter(barcodeFindItemsJson, "barcodeFindItemsJson");
        this.barcodeFindItemsJson = barcodeFindItemsJson;
    }

    public final Set<BarcodeFindItem> getItems() {
        JSONArray jSONArray = new JSONArray(this.barcodeFindItemsJson);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Intrinsics.checkNotNull(jSONObject);
            linkedHashSet.add(getBarcodeFindItem(jSONObject));
        }
        return linkedHashSet;
    }

    private final BarcodeFindItem getBarcodeFindItem(JSONObject item) {
        BarcodeFindItemContent barcodeFindItemContent;
        String string = item.getJSONObject("searchOptions").getString("barcodeData");
        if (item.has(FirebaseAnalytics.Param.CONTENT)) {
            JSONObject jSONObject = item.getJSONObject("searchOptions");
            Intrinsics.checkNotNull(jSONObject);
            String orNull = ExtentionsKt.getOrNull(jSONObject, "info");
            String orNull2 = ExtentionsKt.getOrNull(jSONObject, "additionalInfo");
            String orNull3 = ExtentionsKt.getOrNull(jSONObject, "image");
            if (orNull != null || orNull2 != null || orNull3 != null) {
                barcodeFindItemContent = new BarcodeFindItemContent(orNull, orNull2, getBitmapFromBase64EncodedString(orNull3));
                Intrinsics.checkNotNull(string);
                return new BarcodeFindItem(new BarcodeFindItemSearchOptions(string, (Brush) null, 2, (DefaultConstructorMarker) null), barcodeFindItemContent);
            }
        }
        barcodeFindItemContent = null;
        Intrinsics.checkNotNull(string);
        return new BarcodeFindItem(new BarcodeFindItemSearchOptions(string, (Brush) null, 2, (DefaultConstructorMarker) null), barcodeFindItemContent);
    }

    private final Bitmap getBitmapFromBase64EncodedString(String baseEncodedString) {
        if (baseEncodedString == null) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(StringsKt.removeRange((CharSequence) baseEncodedString, 0, StringsKt.indexOf$default((CharSequence) baseEncodedString, FirebaseConstants.SEPARATOR, 0, false, 6, (Object) null)).toString(), 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
