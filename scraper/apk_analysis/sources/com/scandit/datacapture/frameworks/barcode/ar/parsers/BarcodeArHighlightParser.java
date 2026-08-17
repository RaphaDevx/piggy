package com.scandit.datacapture.frameworks.barcode.ar.parsers;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArCircleHighlight;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArRectangleHighlight;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.style.BrushDeserializer;
import com.scandit.datacapture.frameworks.barcode.ar.extensions.SerializationExtensionsKt;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeArHighlightParser.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArHighlightParser;", "", "()V", "get", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlight;", "context", "Landroid/content/Context;", "barcode", "Lcom/scandit/datacapture/barcode/data/Barcode;", "json", "Lorg/json/JSONObject;", "getBarcodeCheckCircleHighlight", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArCircleHighlight;", "getBarcodeCheckRectangleHighlight", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArRectangleHighlight;", "updateCircleHighlight", "", "highlight", "updateHighlight", "updateRectangleHighlight", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArHighlightParser {
    public final BarcodeArHighlight get(Context context, Barcode barcode, JSONObject json) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(json, "json");
        String string = json.getString("type");
        if (Intrinsics.areEqual(string, "barcodeArCircleHighlight")) {
            return getBarcodeCheckCircleHighlight(context, barcode, json);
        }
        if (Intrinsics.areEqual(string, "barcodeArRectangleHighlight")) {
            return getBarcodeCheckRectangleHighlight(context, barcode, json);
        }
        DefaultFrameworksLog.INSTANCE.getInstance().error("Not supported highlight type. " + string);
        return null;
    }

    public final void updateHighlight(BarcodeArHighlight highlight, JSONObject json) {
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        Intrinsics.checkNotNullParameter(json, "json");
        if (highlight instanceof BarcodeArCircleHighlight) {
            updateCircleHighlight((BarcodeArCircleHighlight) highlight, json);
        } else if (highlight instanceof BarcodeArRectangleHighlight) {
            updateRectangleHighlight((BarcodeArRectangleHighlight) highlight, json);
        }
    }

    private final BarcodeArCircleHighlight getBarcodeCheckCircleHighlight(Context context, Barcode barcode, JSONObject json) {
        String string = json.getString("preset");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        BarcodeArCircleHighlight barcodeArCircleHighlight = new BarcodeArCircleHighlight(context, barcode, SerializationExtensionsKt.toBarcodeCheckCircleHighlightPreset(string));
        updateCircleHighlight(barcodeArCircleHighlight, json);
        return barcodeArCircleHighlight;
    }

    private final BarcodeArRectangleHighlight getBarcodeCheckRectangleHighlight(Context context, Barcode barcode, JSONObject json) {
        BarcodeArRectangleHighlight barcodeArRectangleHighlight = new BarcodeArRectangleHighlight(context, barcode);
        updateRectangleHighlight(barcodeArRectangleHighlight, json);
        return barcodeArRectangleHighlight;
    }

    private final void updateRectangleHighlight(BarcodeArRectangleHighlight highlight, JSONObject json) {
        String string = json.getString("brush");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Brush fromJson = BrushDeserializer.fromJson(string);
        String orNull = ExtentionsKt.getOrNull(json, BarcodePickDeserializer.FIELD_ICON);
        highlight.setIcon(orNull != null ? SerializationExtensionsKt.toScanditIcon(orNull) : null);
        highlight.setBrush(fromJson);
    }

    private final void updateCircleHighlight(BarcodeArCircleHighlight highlight, JSONObject json) {
        String orNull = ExtentionsKt.getOrNull(json, "brush");
        String orNull2 = ExtentionsKt.getOrNull(json, BarcodePickDeserializer.FIELD_ICON);
        ScanditIcon scanditIcon = orNull2 != null ? SerializationExtensionsKt.toScanditIcon(orNull2) : null;
        float f = (float) json.getDouble(RRWebVideoEvent.JsonKeys.SIZE);
        highlight.setIcon(scanditIcon);
        highlight.setSize(f);
        if (orNull != null) {
            highlight.setBrush(BrushDeserializer.fromJson(orNull));
        }
    }
}
