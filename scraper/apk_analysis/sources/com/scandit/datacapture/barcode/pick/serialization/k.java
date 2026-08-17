package com.scandit.datacapture.barcode.pick.serialization;

import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleDot;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleDotWithIcons;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangularWithIcons;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightType;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconDeserializer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k implements BarcodePickDeserializerHelper, DataCaptureDeserializerHelper {
    public BarcodePickSettings a;

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final void a(BarcodePick mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper
    public final void clear() {
        this.a = null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final BarcodePickSettings createSettings() {
        BarcodePickSettings barcodePickSettings = this.a;
        if (barcodePickSettings != null) {
            return barcodePickSettings;
        }
        BarcodePickSettings barcodePickSettings2 = new BarcodePickSettings();
        this.a = barcodePickSettings2;
        return barcodePickSettings2;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final BarcodePick a(DataCaptureContext dataCaptureContext, BarcodePickProductProvider provider) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(provider, "provider");
        BarcodePickSettings barcodePickSettings = new BarcodePickSettings();
        this.a = barcodePickSettings;
        return new BarcodePick(dataCaptureContext, barcodePickSettings, provider);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final void a(BarcodePickSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        settings._updateFromJson(json);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final void a(BarcodePick mode, BarcodePickSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        BarcodePick._applySettings$scandit_barcode_capture$default(mode, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final BarcodePickBasicOverlay a(BarcodePick mode, BarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        return new BarcodePickBasicOverlay(AppAndroidEnvironment.INSTANCE.getApplicationContext(), mode, viewSettings);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final BarcodePickViewSettings a() {
        return new BarcodePickViewSettings();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper
    public final void a(BarcodePickViewSettings viewSettings, JsonValue json) {
        BarcodePickViewHighlightStyle rectangular;
        BarcodePickViewHighlightStyle rectangularWithIcons;
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonValue byKeyAsObject = json.getByKeyAsObject("highlightStyle", null);
        if (byKeyAsObject == null) {
            return;
        }
        NativeBarcodePickViewHighlightType highlightStyleType = viewSettings.getA().getHighlightStyleType();
        Intrinsics.checkNotNull(highlightStyleType);
        int i = BarcodePickDeserializer$Helper$WhenMappings.$EnumSwitchMapping$0[highlightStyleType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                NativeBarcodePickViewHighlightStyleRectangularWithIcons highlightStyleAsRectangularWithIcons = viewSettings.getA().getHighlightStyleAsRectangularWithIcons();
                Intrinsics.checkNotNullExpressionValue(highlightStyleAsRectangularWithIcons, "getHighlightStyleAsRectangularWithIcons(...)");
                rectangularWithIcons = new BarcodePickViewHighlightStyle.RectangularWithIcons(highlightStyleAsRectangularWithIcons, null, 2, null);
                a(rectangularWithIcons, byKeyAsObject, BarcodePickDeserializer.FIELD_ICONS_FOR_STATE, c.a, d.a, e.a, f.a);
                a(rectangularWithIcons, byKeyAsObject, BarcodePickDeserializer.FIELD_SELECTED_ICONS_FOR_STATE, g.a, h.a, i.a, j.a);
            } else if (i == 3) {
                NativeBarcodePickViewHighlightStyleDot highlightStyleAsDot = viewSettings.getA().getHighlightStyleAsDot();
                Intrinsics.checkNotNullExpressionValue(highlightStyleAsDot, "getHighlightStyleAsDot(...)");
                rectangular = new BarcodePickViewHighlightStyle.Dot(highlightStyleAsDot);
            } else if (i == 4) {
                NativeBarcodePickViewHighlightStyleDotWithIcons highlightStyleAsDotWithIcons = viewSettings.getA().getHighlightStyleAsDotWithIcons();
                Intrinsics.checkNotNullExpressionValue(highlightStyleAsDotWithIcons, "getHighlightStyleAsDotWithIcons(...)");
                rectangularWithIcons = new BarcodePickViewHighlightStyle.DotWithIcons(highlightStyleAsDotWithIcons, null, 2, null);
                a(rectangularWithIcons, byKeyAsObject, BarcodePickDeserializer.FIELD_ICONS_FOR_STATE, c.a, d.a, e.a, f.a);
                a(rectangularWithIcons, byKeyAsObject, BarcodePickDeserializer.FIELD_SELECTED_ICONS_FOR_STATE, g.a, h.a, i.a, j.a);
            } else {
                if (i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                NativeBarcodePickViewHighlightStyleCustomView highlightStyleAsCustomView = viewSettings.getA().getHighlightStyleAsCustomView();
                Intrinsics.checkNotNullExpressionValue(highlightStyleAsCustomView, "getHighlightStyleAsCustomView(...)");
                rectangular = new BarcodePickViewHighlightStyle.CustomView(highlightStyleAsCustomView);
            }
            rectangular = rectangularWithIcons;
        } else {
            NativeBarcodePickViewHighlightStyleRectangular highlightStyleAsRectangular = viewSettings.getA().getHighlightStyleAsRectangular();
            Intrinsics.checkNotNullExpressionValue(highlightStyleAsRectangular, "getHighlightStyleAsRectangular(...)");
            rectangular = new BarcodePickViewHighlightStyle.Rectangular(highlightStyleAsRectangular);
        }
        viewSettings.setHighlightStyle(rectangular);
    }

    public static void a(BarcodePickViewHighlightStyle barcodePickViewHighlightStyle, JsonValue jsonValue, String str, Function3 function3, Function3 function32, Function3 function33, Function3 function34) {
        Object m3945constructorimpl;
        if (jsonValue.contains(str)) {
            JsonValue requireByKeyAsArray = jsonValue.requireByKeyAsArray(str);
            int size = (int) requireByKeyAsArray.getSize();
            for (int i = 0; i < size; i++) {
                JsonValue requireByIndex = requireByKeyAsArray.requireByIndex(i);
                BarcodePickState fromJsonString = BarcodePickState.INSTANCE.fromJsonString(requireByIndex.requireByKeyAsString(BarcodePickDeserializer.FIELD_BARCODE_PICK_STATE));
                try {
                    Result.Companion companion = Result.INSTANCE;
                    JsonValue byKeyAsObject = requireByIndex.getByKeyAsObject(BarcodePickDeserializer.FIELD_ICON, null);
                    Intrinsics.checkNotNull(byKeyAsObject);
                    m3945constructorimpl = Result.m3945constructorimpl(ScanditIconDeserializer.fromJson(byKeyAsObject.jsonString()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m3952isSuccessimpl(m3945constructorimpl)) {
                    ScanditIcon scanditIcon = (ScanditIcon) m3945constructorimpl;
                    if (barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) {
                        function32.invoke(barcodePickViewHighlightStyle, scanditIcon, fromJsonString);
                    } else if (barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons) {
                        function34.invoke(barcodePickViewHighlightStyle, scanditIcon, fromJsonString);
                    }
                }
                if (Result.m3948exceptionOrNullimpl(m3945constructorimpl) != null) {
                    Bitmap bitmapFromBase64 = BitmapExtensionsKt.bitmapFromBase64(requireByIndex.requireByKeyAsString(BarcodePickDeserializer.FIELD_ICON));
                    if (barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) {
                        function3.invoke(barcodePickViewHighlightStyle, bitmapFromBase64, fromJsonString);
                    } else if (barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons) {
                        function33.invoke(barcodePickViewHighlightStyle, bitmapFromBase64, fromJsonString);
                    }
                }
            }
        }
    }
}
