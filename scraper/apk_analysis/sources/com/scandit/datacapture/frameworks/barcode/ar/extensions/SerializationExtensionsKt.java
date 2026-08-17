package com.scandit.datacapture.frameworks.barcode.ar.extensions;

import android.graphics.Typeface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArAnnotationTriggerSerializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArCircleHighlightPresetDeserializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArCircleHighlightPresetSerializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArInfoAnnotationAnchorDeserializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArInfoAnnotationAnchorSerializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArInfoAnnotationWidthDeserializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArInfoAnnotationWidthSerializer;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArCircleHighlightPreset;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconDeserializer;
import com.scandit.datacapture.core.ui.icon.ScanditIconSerializer;
import com.scandit.datacapture.core.ui.icon.ScanditIconType;
import com.scandit.datacapture.core.ui.icon.ScanditIconTypeSerializer;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SerializationExtensions.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\bH\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\u0004H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\u0006H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\tH\u0000\u001a\f\u0010\u0007\u001a\u00020\u0002*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\t*\u00020\u0002H\u0000\u001a\f\u0010\f\u001a\u00020\r*\u00020\u0002H\u0000\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0000¨\u0006\u0010"}, d2 = {"toBarcodeCheckCircleHighlightPreset", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArCircleHighlightPreset;", "", "toBarcodeCheckInfoAnnotationAnchor", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationAnchor;", "toBarcodeCheckInfoAnnotationWidth", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;", "toJson", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "Lcom/scandit/datacapture/core/ui/icon/ScanditIconType;", "toScanditIcon", "toTextAlignment", "", "toTypeface", "Landroid/graphics/Typeface;", "scandit-datacapture-frameworks-barcode_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SerializationExtensionsKt {
    public static final BarcodeArCircleHighlightPreset toBarcodeCheckCircleHighlightPreset(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return BarcodeArCircleHighlightPresetDeserializer.fromJson(str);
    }

    public static final String toJson(BarcodeArCircleHighlightPreset barcodeArCircleHighlightPreset) {
        Intrinsics.checkNotNullParameter(barcodeArCircleHighlightPreset, "<this>");
        return BarcodeArCircleHighlightPresetSerializer.toJson(barcodeArCircleHighlightPreset);
    }

    public static final String toJson(BarcodeArAnnotationTrigger barcodeArAnnotationTrigger) {
        Intrinsics.checkNotNullParameter(barcodeArAnnotationTrigger, "<this>");
        return BarcodeArAnnotationTriggerSerializer.toJson(barcodeArAnnotationTrigger);
    }

    public static final String toJson(ScanditIcon scanditIcon) {
        Intrinsics.checkNotNullParameter(scanditIcon, "<this>");
        return ScanditIconSerializer.toJson(scanditIcon);
    }

    public static final ScanditIcon toScanditIcon(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return ScanditIconDeserializer.fromJson(str);
    }

    public static final Typeface toTypeface(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int hashCode = str.hashCode();
        if (hashCode != -2005437790) {
            if (hashCode != -721225454) {
                if (hashCode == 642840418 && str.equals("systemSans")) {
                    Typeface SANS_SERIF = Typeface.SANS_SERIF;
                    Intrinsics.checkNotNullExpressionValue(SANS_SERIF, "SANS_SERIF");
                    return SANS_SERIF;
                }
            } else if (str.equals("systemDefault")) {
                Typeface DEFAULT = Typeface.DEFAULT;
                Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
                return DEFAULT;
            }
        } else if (str.equals("modernMono")) {
            Typeface MONOSPACE = Typeface.MONOSPACE;
            Intrinsics.checkNotNullExpressionValue(MONOSPACE, "MONOSPACE");
            return MONOSPACE;
        }
        Typeface DEFAULT2 = Typeface.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(DEFAULT2, "DEFAULT");
        return DEFAULT2;
    }

    public static final BarcodeArInfoAnnotationAnchor toBarcodeCheckInfoAnnotationAnchor(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return BarcodeArInfoAnnotationAnchorDeserializer.fromJson(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final int toTextAlignment(String str) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    return 4;
                }
                return 2;
            case 100571:
                if (str.equals("end")) {
                    return 3;
                }
                return 2;
            case 3317767:
                str2 = RRWebVideoEvent.JsonKeys.LEFT;
                break;
            case 108511772:
                if (str.equals("right")) {
                    return 3;
                }
                return 2;
            case 109757538:
                str2 = "start";
                break;
            default:
                return 2;
        }
        str.equals(str2);
        return 2;
    }

    public static final String toJson(ScanditIconType scanditIconType) {
        Intrinsics.checkNotNullParameter(scanditIconType, "<this>");
        return ScanditIconTypeSerializer.toJson(scanditIconType);
    }

    public static final String toJson(BarcodeArInfoAnnotationWidthPreset barcodeArInfoAnnotationWidthPreset) {
        Intrinsics.checkNotNullParameter(barcodeArInfoAnnotationWidthPreset, "<this>");
        return BarcodeArInfoAnnotationWidthSerializer.toJson(barcodeArInfoAnnotationWidthPreset);
    }

    public static final BarcodeArInfoAnnotationWidthPreset toBarcodeCheckInfoAnnotationWidth(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return BarcodeArInfoAnnotationWidthDeserializer.fromJson(str);
    }

    public static final String toJson(BarcodeArInfoAnnotationAnchor barcodeArInfoAnnotationAnchor) {
        Intrinsics.checkNotNullParameter(barcodeArInfoAnnotationAnchor, "<this>");
        return BarcodeArInfoAnnotationAnchorSerializer.toJson(barcodeArInfoAnnotationAnchor);
    }
}
