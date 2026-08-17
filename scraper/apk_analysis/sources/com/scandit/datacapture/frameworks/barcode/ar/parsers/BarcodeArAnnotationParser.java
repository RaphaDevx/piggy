package com.scandit.datacapture.frameworks.barcode.ar.parsers;

import android.content.Context;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArAnnotationTriggerDeserializer;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArStatusIconAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationFooter;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.frameworks.barcode.ar.extensions.SerializationExtensionsKt;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArInfoAnnotationListener;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArPopoverAnnotationListener;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import io.sentry.SentryEvent;
import io.sentry.SentryLogEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BarcodeArAnnotationParser.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010#\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010%\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArAnnotationParser;", "", "barcodeCheckInfoAnnotationListener", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArInfoAnnotationListener;", "barcodeCheckPopoverAnnotationListener", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArPopoverAnnotationListener;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "(Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArInfoAnnotationListener;Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArPopoverAnnotationListener;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;)V", "get", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotation;", "context", "Landroid/content/Context;", "json", "Lorg/json/JSONObject;", "barcode", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getBarcodeArInfoAnnotationBodyComponent", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationBodyComponent;", "getFooter", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationFooter;", "getHeader", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;", "getInfoAnnotation", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;", "getPopoverAnnotation", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArPopoverAnnotation;", "getPopoverAnnotationButtons", "", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationButton;", "getStatusIconAnnotation", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArStatusIconAnnotation;", "updateAnnotation", "", "annotation", "updateBarcodeArPopoverButton", "updateInfoAnnotation", "updatePopoverAnnotation", "updateStatusIconAnnotation", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArAnnotationParser {
    private final FrameworksBarcodeArInfoAnnotationListener barcodeCheckInfoAnnotationListener;
    private final FrameworksBarcodeArPopoverAnnotationListener barcodeCheckPopoverAnnotationListener;
    private final FrameworksLog logger;

    public BarcodeArAnnotationParser(FrameworksBarcodeArInfoAnnotationListener barcodeCheckInfoAnnotationListener, FrameworksBarcodeArPopoverAnnotationListener barcodeCheckPopoverAnnotationListener, FrameworksLog logger) {
        Intrinsics.checkNotNullParameter(barcodeCheckInfoAnnotationListener, "barcodeCheckInfoAnnotationListener");
        Intrinsics.checkNotNullParameter(barcodeCheckPopoverAnnotationListener, "barcodeCheckPopoverAnnotationListener");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.barcodeCheckInfoAnnotationListener = barcodeCheckInfoAnnotationListener;
        this.barcodeCheckPopoverAnnotationListener = barcodeCheckPopoverAnnotationListener;
        this.logger = logger;
    }

    public /* synthetic */ BarcodeArAnnotationParser(FrameworksBarcodeArInfoAnnotationListener frameworksBarcodeArInfoAnnotationListener, FrameworksBarcodeArPopoverAnnotationListener frameworksBarcodeArPopoverAnnotationListener, DefaultFrameworksLog defaultFrameworksLog, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodeArInfoAnnotationListener, frameworksBarcodeArPopoverAnnotationListener, (i & 4) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : defaultFrameworksLog);
    }

    public final BarcodeArAnnotation get(Context context, JSONObject json, Barcode barcode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        String string = json.getString("type");
        if (string != null) {
            int hashCode = string.hashCode();
            if (hashCode != -1121982749) {
                if (hashCode != -274940021) {
                    if (hashCode == 1083835822 && string.equals("barcodeArInfoAnnotation")) {
                        return getInfoAnnotation(context, barcode, json);
                    }
                } else if (string.equals("barcodeArStatusIconAnnotation")) {
                    return getStatusIconAnnotation(context, barcode, json);
                }
            } else if (string.equals("barcodeArPopoverAnnotation")) {
                return getPopoverAnnotation(context, barcode, json);
            }
        }
        this.logger.error("Not supported annotation type. " + string);
        return null;
    }

    private final BarcodeArInfoAnnotation getInfoAnnotation(Context context, Barcode barcode, JSONObject json) {
        BarcodeArInfoAnnotation barcodeArInfoAnnotation = new BarcodeArInfoAnnotation(context, barcode);
        updateInfoAnnotation(barcodeArInfoAnnotation, json);
        return barcodeArInfoAnnotation;
    }

    private final void updateInfoAnnotation(BarcodeArInfoAnnotation annotation, JSONObject json) {
        annotation.setHasTip(json.optBoolean("hasTip", false));
        annotation.setEntireAnnotationTappable(json.optBoolean("isEntireAnnotationTappable", false));
        String string = json.getString("anchor");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        annotation.setAnchor(SerializationExtensionsKt.toBarcodeCheckInfoAnnotationAnchor(string));
        String string2 = json.getString("width");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        annotation.setWidth(SerializationExtensionsKt.toBarcodeCheckInfoAnnotationWidth(string2));
        String string3 = json.getString("annotationTrigger");
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        annotation.setAnnotationTrigger(BarcodeArAnnotationTriggerDeserializer.fromJson(string3));
        annotation.setHeader(getHeader(json));
        annotation.setFooter(getFooter(json));
        JSONArray jSONArray = json.getJSONArray(SentryLogEvent.JsonKeys.BODY);
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Intrinsics.checkNotNull(jSONObject);
            arrayList.add(getBarcodeArInfoAnnotationBodyComponent(jSONObject));
        }
        annotation.setBody(arrayList);
        annotation.setListener(json.optBoolean("hasListener", false) ? this.barcodeCheckInfoAnnotationListener : null);
    }

    private final BarcodeArInfoAnnotationHeader getHeader(JSONObject json) {
        JSONObject optJSONObject = json.optJSONObject("header");
        if (optJSONObject == null) {
            return null;
        }
        BarcodeArInfoAnnotationHeader barcodeArInfoAnnotationHeader = new BarcodeArInfoAnnotationHeader();
        String orNull = ExtentionsKt.getOrNull(optJSONObject, BarcodePickDeserializer.FIELD_ICON);
        barcodeArInfoAnnotationHeader.setIcon(orNull != null ? SerializationExtensionsKt.toScanditIcon(orNull) : null);
        barcodeArInfoAnnotationHeader.setText(ExtentionsKt.getOrNull(optJSONObject, "text"));
        String orNull2 = ExtentionsKt.getOrNull(optJSONObject, "backgroundColor");
        if (orNull2 != null) {
            barcodeArInfoAnnotationHeader.setBackgroundColor(Color.parseColor(ExtentionsKt.toAARRGGBB(orNull2)));
        }
        String string = optJSONObject.getString("fontFamily");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        barcodeArInfoAnnotationHeader.setTypeface(SerializationExtensionsKt.toTypeface(string));
        barcodeArInfoAnnotationHeader.setTextSize((float) optJSONObject.getDouble("textSize"));
        String orNull3 = ExtentionsKt.getOrNull(optJSONObject, "textColor");
        if (orNull3 != null) {
            barcodeArInfoAnnotationHeader.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(orNull3)));
        }
        return barcodeArInfoAnnotationHeader;
    }

    private final BarcodeArInfoAnnotationFooter getFooter(JSONObject json) {
        JSONObject optJSONObject = json.optJSONObject("footer");
        if (optJSONObject == null) {
            return null;
        }
        BarcodeArInfoAnnotationFooter barcodeArInfoAnnotationFooter = new BarcodeArInfoAnnotationFooter();
        String orNull = ExtentionsKt.getOrNull(optJSONObject, BarcodePickDeserializer.FIELD_ICON);
        barcodeArInfoAnnotationFooter.setIcon(orNull != null ? SerializationExtensionsKt.toScanditIcon(orNull) : null);
        barcodeArInfoAnnotationFooter.setText(ExtentionsKt.getOrNull(optJSONObject, "text"));
        String orNull2 = ExtentionsKt.getOrNull(optJSONObject, "backgroundColor");
        if (orNull2 != null) {
            barcodeArInfoAnnotationFooter.setBackgroundColor(Color.parseColor(ExtentionsKt.toAARRGGBB(orNull2)));
        }
        String string = optJSONObject.getString("fontFamily");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        barcodeArInfoAnnotationFooter.setTypeface(SerializationExtensionsKt.toTypeface(string));
        barcodeArInfoAnnotationFooter.setTextSize((float) optJSONObject.getDouble("textSize"));
        String orNull3 = ExtentionsKt.getOrNull(optJSONObject, "textColor");
        if (orNull3 != null) {
            barcodeArInfoAnnotationFooter.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(orNull3)));
        }
        return barcodeArInfoAnnotationFooter;
    }

    private final BarcodeArInfoAnnotationBodyComponent getBarcodeArInfoAnnotationBodyComponent(JSONObject json) {
        BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent = new BarcodeArInfoAnnotationBodyComponent();
        barcodeArInfoAnnotationBodyComponent.setText(ExtentionsKt.getOrNull(json, "text"));
        String string = json.getString("textColor");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        barcodeArInfoAnnotationBodyComponent.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(string)));
        String string2 = json.getString("textAlign");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        barcodeArInfoAnnotationBodyComponent.setTextAlignment(SerializationExtensionsKt.toTextAlignment(string2));
        barcodeArInfoAnnotationBodyComponent.setLeftIconTappable(json.optBoolean("isLeftIconTappable", false));
        String orNull = ExtentionsKt.getOrNull(json, "leftIcon");
        barcodeArInfoAnnotationBodyComponent.setLeftIcon(orNull != null ? SerializationExtensionsKt.toScanditIcon(orNull) : null);
        barcodeArInfoAnnotationBodyComponent.setRightIconTappable(json.optBoolean("isRightIconTappable", false));
        String orNull2 = ExtentionsKt.getOrNull(json, "rightIcon");
        barcodeArInfoAnnotationBodyComponent.setRightIcon(orNull2 != null ? SerializationExtensionsKt.toScanditIcon(orNull2) : null);
        return barcodeArInfoAnnotationBodyComponent;
    }

    private final BarcodeArPopoverAnnotation getPopoverAnnotation(Context context, Barcode barcode, JSONObject json) {
        BarcodeArPopoverAnnotation barcodeArPopoverAnnotation = new BarcodeArPopoverAnnotation(context, barcode, getPopoverAnnotationButtons(json));
        updatePopoverAnnotation(barcodeArPopoverAnnotation, json);
        return barcodeArPopoverAnnotation;
    }

    private final void updatePopoverAnnotation(BarcodeArPopoverAnnotation annotation, JSONObject json) {
        annotation.setEntirePopoverTappable(json.optBoolean("isEntirePopoverTappable", false));
        String string = json.getString("annotationTrigger");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        annotation.setAnnotationTrigger(BarcodeArAnnotationTriggerDeserializer.fromJson(string));
        annotation.setListener(json.optBoolean("hasListener", false) ? this.barcodeCheckPopoverAnnotationListener : null);
    }

    private final List<BarcodeArPopoverAnnotationButton> getPopoverAnnotationButtons(JSONObject json) {
        JSONArray jSONArray = json.getJSONArray("buttons");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Intrinsics.checkNotNull(jSONObject);
            String orNull = ExtentionsKt.getOrNull(jSONObject, BarcodePickDeserializer.FIELD_ICON);
            ScanditIcon scanditIcon = orNull != null ? SerializationExtensionsKt.toScanditIcon(orNull) : null;
            if (scanditIcon == null) {
                this.logger.error("BarcodeArPopoverAnnotationButton json didn't contain the icon.");
            } else {
                String string = jSONObject.getString("text");
                Intrinsics.checkNotNull(string);
                BarcodeArPopoverAnnotationButton barcodeArPopoverAnnotationButton = new BarcodeArPopoverAnnotationButton(scanditIcon, string);
                String string2 = jSONObject.getString("textColor");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                barcodeArPopoverAnnotationButton.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(string2)));
                barcodeArPopoverAnnotationButton.setTextSize((float) jSONObject.getDouble("textSize"));
                String string3 = jSONObject.getString("fontFamily");
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                barcodeArPopoverAnnotationButton.setTypeface(SerializationExtensionsKt.toTypeface(string3));
                barcodeArPopoverAnnotationButton.setEnabled(jSONObject.optBoolean(ExtentionsKt.ENABLED_KEY, false));
                arrayList.add(barcodeArPopoverAnnotationButton);
            }
        }
        return arrayList;
    }

    private final BarcodeArStatusIconAnnotation getStatusIconAnnotation(Context context, Barcode barcode, JSONObject json) {
        BarcodeArStatusIconAnnotation barcodeArStatusIconAnnotation = new BarcodeArStatusIconAnnotation(context, barcode);
        updateStatusIconAnnotation(barcodeArStatusIconAnnotation, json);
        return barcodeArStatusIconAnnotation;
    }

    private final void updateStatusIconAnnotation(BarcodeArStatusIconAnnotation annotation, JSONObject json) {
        String string = json.getString(BarcodePickDeserializer.FIELD_ICON);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        annotation.setIcon(SerializationExtensionsKt.toScanditIcon(string));
        annotation.setHasTip(json.optBoolean("hasTip", false));
        annotation.setText(ExtentionsKt.getOrNull(json, "text"));
        String string2 = json.getString("textColor");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        annotation.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(string2)));
        String string3 = json.getString("backgroundColor");
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        annotation.setBackgroundColor(Color.parseColor(ExtentionsKt.toAARRGGBB(string3)));
        String string4 = json.getString("annotationTrigger");
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        annotation.setAnnotationTrigger(BarcodeArAnnotationTriggerDeserializer.fromJson(string4));
    }

    public final void updateAnnotation(BarcodeArAnnotation annotation, JSONObject json) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(json, "json");
        if (annotation instanceof BarcodeArInfoAnnotation) {
            updateInfoAnnotation((BarcodeArInfoAnnotation) annotation, json);
        } else if (annotation instanceof BarcodeArStatusIconAnnotation) {
            updateStatusIconAnnotation((BarcodeArStatusIconAnnotation) annotation, json);
        } else if (annotation instanceof BarcodeArPopoverAnnotation) {
            updatePopoverAnnotation((BarcodeArPopoverAnnotation) annotation, json);
        }
    }

    public final void updateBarcodeArPopoverButton(BarcodeArPopoverAnnotation annotation, JSONObject json) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(json, "json");
        int i = json.getInt(FirebaseAnalytics.Param.INDEX);
        if (i < 0) {
            this.logger.error("Invalid index (" + i + ") received when trying to update the updateBarcodeCheckPopoverButton");
            return;
        }
        if (i > annotation.getButtons().size() - 1) {
            this.logger.error("Invalid index (" + i + ") received when trying to update the updateBarcodeCheckPopoverButton. Buttons Size " + annotation.getButtons().size());
            return;
        }
        BarcodeArPopoverAnnotationButton barcodeArPopoverAnnotationButton = annotation.getButtons().get(i);
        String string = json.getString("textColor");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        barcodeArPopoverAnnotationButton.setTextColor(Color.parseColor(ExtentionsKt.toAARRGGBB(string)));
        barcodeArPopoverAnnotationButton.setTextSize((float) json.getDouble("textSize"));
        String string2 = json.getString("fontFamily");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        barcodeArPopoverAnnotationButton.setTypeface(SerializationExtensionsKt.toTypeface(string2));
        barcodeArPopoverAnnotationButton.setEnabled(json.optBoolean(ExtentionsKt.ENABLED_KEY, false));
    }
}
