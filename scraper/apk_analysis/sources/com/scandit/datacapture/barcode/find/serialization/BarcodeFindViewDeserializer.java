package com.scandit.datacapture.barcode.find.serialization;

import android.view.View;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindView;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewSettings;
import com.scandit.datacapture.barcode.internal.module.find.serialization.o;
import com.scandit.datacapture.barcode.internal.module.serialization.BarcodeCameraSettingsDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.AnchorDeserializer;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/barcode/find/serialization/BarcodeFindViewDeserializer;", "", "<init>", "()V", "Landroid/view/View;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "barcodeFind", "", "json", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "viewFromJson", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "updateViewFromJson", "(Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;Ljava/lang/String;)V", "", "getWarnings", "()Ljava/util/List;", "warnings", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFindViewDeserializer {
    private final ArrayList a = new ArrayList();

    public final List<String> getWarnings() {
        return this.a;
    }

    public final void updateViewFromJson(BarcodeFindView view, String json) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.clear();
        JsonValue jsonValue = new JsonValue(json);
        a(view, jsonValue);
        this.a.addAll(jsonValue._getWarnings());
    }

    public final BarcodeFindView viewFromJson(View parentView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, String json) {
        Object fromJson;
        BarcodeFindViewSettings barcodeFindViewSettings;
        Object fromJson2;
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.clear();
        JsonValue jsonValue = new JsonValue(json);
        CameraSettings cameraSettings = null;
        if (jsonValue.contains("viewSettings")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson = new o().a(jsonValue.requireByKeyAsObject("viewSettings"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("viewSettings"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson = jsonValue.requireByKeyAsString("viewSettings");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("viewSettings"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("viewSettings"));
            }
            if (fromJson == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.scandit.datacapture.barcode.find.ui.BarcodeFindViewSettings");
            }
            barcodeFindViewSettings = (BarcodeFindViewSettings) fromJson;
        } else {
            barcodeFindViewSettings = null;
        }
        if (barcodeFindViewSettings == null) {
            barcodeFindViewSettings = new BarcodeFindViewSettings(0, 0, false, false, 15, (DefaultConstructorMarker) null);
        }
        if (jsonValue.contains("cameraSettings")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(CameraSettings.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson2 = new o().a(jsonValue.requireByKeyAsObject("cameraSettings"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson2 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("cameraSettings"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson2 = jsonValue.requireByKeyAsString("cameraSettings");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson2 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("cameraSettings"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson2 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("cameraSettings"));
            }
            if (fromJson2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.scandit.datacapture.core.source.CameraSettings");
            }
            cameraSettings = (CameraSettings) fromJson2;
        }
        if (cameraSettings == null) {
            cameraSettings = BarcodeFind.INSTANCE.getRecommendedCameraSettings();
        }
        BarcodeFindView newInstance = BarcodeFindView.INSTANCE.newInstance(parentView, dataCaptureContext, barcodeFind, barcodeFindViewSettings, cameraSettings);
        a(newInstance, jsonValue);
        this.a.addAll(jsonValue._getWarnings());
        return newInstance;
    }

    private static void a(BarcodeFindView barcodeFindView, JsonValue jsonValue) {
        Object fromJson;
        Boolean bool;
        Object fromJson2;
        Boolean bool2;
        Object fromJson3;
        Boolean bool3;
        Object fromJson4;
        Boolean bool4;
        Object fromJson5;
        Boolean bool5;
        Object fromJson6;
        Boolean bool6;
        Object fromJson7;
        Boolean bool7;
        Object fromJson8;
        Anchor anchor;
        Object fromJson9;
        Boolean bool8;
        Object fromJson10;
        String str;
        Object fromJson11;
        String str2;
        Object fromJson12;
        String str3;
        Object fromJson13;
        String str4;
        Object fromJson14;
        String str5;
        Object fromJson15;
        String str6;
        Object fromJson16;
        String str7;
        Object fromJson17;
        String str8 = null;
        if (jsonValue.contains("shouldShowUserGuidanceView")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson = new o().a(jsonValue.requireByKeyAsObject("shouldShowUserGuidanceView"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowUserGuidanceView"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson = jsonValue.requireByKeyAsString("shouldShowUserGuidanceView");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowUserGuidanceView"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowUserGuidanceView"));
            }
            if (fromJson == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool = (Boolean) fromJson;
        } else {
            bool = null;
        }
        if (bool != null) {
            barcodeFindView.setShouldShowUserGuidanceView(bool.booleanValue());
            Unit unit = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowHints")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson2 = new o().a(jsonValue.requireByKeyAsObject("shouldShowHints"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson2 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowHints"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson2 = jsonValue.requireByKeyAsString("shouldShowHints");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson2 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowHints"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson2 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowHints"));
            }
            if (fromJson2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool2 = (Boolean) fromJson2;
        } else {
            bool2 = null;
        }
        if (bool2 != null) {
            barcodeFindView.setShouldShowHints(bool2.booleanValue());
            Unit unit2 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowCarousel")) {
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson3 = new o().a(jsonValue.requireByKeyAsObject("shouldShowCarousel"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson3 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowCarousel"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson3 = jsonValue.requireByKeyAsString("shouldShowCarousel");
            } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson3 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowCarousel"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson3 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowCarousel"));
            }
            if (fromJson3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool3 = (Boolean) fromJson3;
        } else {
            bool3 = null;
        }
        if (bool3 != null) {
            barcodeFindView.setShouldShowCarousel(bool3.booleanValue());
            Unit unit3 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowPauseButton")) {
            KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson4 = new o().a(jsonValue.requireByKeyAsObject("shouldShowPauseButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson4 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowPauseButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson4 = jsonValue.requireByKeyAsString("shouldShowPauseButton");
            } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson4 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowPauseButton"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson4 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowPauseButton"));
            }
            if (fromJson4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool4 = (Boolean) fromJson4;
        } else {
            bool4 = null;
        }
        if (bool4 != null) {
            barcodeFindView.setShouldShowPauseButton(bool4.booleanValue());
            Unit unit4 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowFinishButton")) {
            KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson5 = new o().a(jsonValue.requireByKeyAsObject("shouldShowFinishButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson5 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowFinishButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson5 = jsonValue.requireByKeyAsString("shouldShowFinishButton");
            } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson5 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowFinishButton"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson5 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowFinishButton"));
            }
            if (fromJson5 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool5 = (Boolean) fromJson5;
        } else {
            bool5 = null;
        }
        if (bool5 != null) {
            barcodeFindView.setShouldShowFinishButton(bool5.booleanValue());
            Unit unit5 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowProgressBar")) {
            KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson6 = new o().a(jsonValue.requireByKeyAsObject("shouldShowProgressBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson6 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowProgressBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson6 = jsonValue.requireByKeyAsString("shouldShowProgressBar");
            } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson6 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowProgressBar"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson6 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowProgressBar"));
            }
            if (fromJson6 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool6 = (Boolean) fromJson6;
        } else {
            bool6 = null;
        }
        if (bool6 != null) {
            barcodeFindView.setShouldShowProgressBar(bool6.booleanValue());
            Unit unit6 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowTorchControl")) {
            KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson7 = new o().a(jsonValue.requireByKeyAsObject("shouldShowTorchControl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson7 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowTorchControl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson7 = jsonValue.requireByKeyAsString("shouldShowTorchControl");
            } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson7 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowTorchControl"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson7 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowTorchControl"));
            }
            if (fromJson7 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool7 = (Boolean) fromJson7;
        } else {
            bool7 = null;
        }
        if (bool7 != null) {
            barcodeFindView.setShouldShowTorchControl(bool7.booleanValue());
            Unit unit7 = Unit.INSTANCE;
        }
        if (jsonValue.contains("torchControlPosition")) {
            KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Anchor.class);
            if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson8 = new o().a(jsonValue.requireByKeyAsObject("torchControlPosition"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson8 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("torchControlPosition"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson8 = jsonValue.requireByKeyAsString("torchControlPosition");
            } else if (Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson8 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("torchControlPosition"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass8, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson8 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("torchControlPosition"));
            }
            if (fromJson8 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.scandit.datacapture.core.common.geometry.Anchor");
            }
            anchor = (Anchor) fromJson8;
        } else {
            anchor = null;
        }
        if (anchor != null) {
            barcodeFindView.setTorchControlPosition(anchor);
            Unit unit8 = Unit.INSTANCE;
        }
        if (jsonValue.contains("shouldShowZoomControl")) {
            KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass9, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson9 = new o().a(jsonValue.requireByKeyAsObject("shouldShowZoomControl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass9, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson9 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("shouldShowZoomControl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass9, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson9 = jsonValue.requireByKeyAsString("shouldShowZoomControl");
            } else if (Intrinsics.areEqual(orCreateKotlinClass9, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson9 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("shouldShowZoomControl"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass9, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson9 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("shouldShowZoomControl"));
            }
            if (fromJson9 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
            bool8 = (Boolean) fromJson9;
        } else {
            bool8 = null;
        }
        if (bool8 != null) {
            barcodeFindView.setShouldShowZoomControl(bool8.booleanValue());
            Unit unit9 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForCollapseCardsButton")) {
            KClass orCreateKotlinClass10 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass10, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson10 = new o().a(jsonValue.requireByKeyAsObject("textForCollapseCardsButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass10, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson10 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForCollapseCardsButton"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass10, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson10 = jsonValue.requireByKeyAsString("textForCollapseCardsButton");
            } else if (Intrinsics.areEqual(orCreateKotlinClass10, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson10 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForCollapseCardsButton"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass10, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson10 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForCollapseCardsButton"));
            }
            if (fromJson10 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str = (String) fromJson10;
        } else {
            str = null;
        }
        if (str != null) {
            barcodeFindView.setTextForCollapseCardsButton(str);
            Unit unit10 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForAllItemsFoundSuccessfullyHint")) {
            KClass orCreateKotlinClass11 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass11, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson11 = new o().a(jsonValue.requireByKeyAsObject("textForAllItemsFoundSuccessfullyHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass11, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson11 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForAllItemsFoundSuccessfullyHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass11, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson11 = jsonValue.requireByKeyAsString("textForAllItemsFoundSuccessfullyHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass11, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson11 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForAllItemsFoundSuccessfullyHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass11, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson11 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForAllItemsFoundSuccessfullyHint"));
            }
            if (fromJson11 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str2 = (String) fromJson11;
        } else {
            str2 = null;
        }
        if (str2 != null) {
            barcodeFindView.setTextForAllItemsFoundSuccessfullyHint(str2);
            Unit unit11 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForPointAtBarcodesToSearchHint")) {
            KClass orCreateKotlinClass12 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass12, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson12 = new o().a(jsonValue.requireByKeyAsObject("textForPointAtBarcodesToSearchHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass12, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson12 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForPointAtBarcodesToSearchHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass12, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson12 = jsonValue.requireByKeyAsString("textForPointAtBarcodesToSearchHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass12, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson12 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForPointAtBarcodesToSearchHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass12, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson12 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForPointAtBarcodesToSearchHint"));
            }
            if (fromJson12 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str3 = (String) fromJson12;
        } else {
            str3 = null;
        }
        if (str3 != null) {
            barcodeFindView.setTextForPointAtBarcodesToSearchHint(str3);
            Unit unit12 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForMoveCloserToBarcodesHint")) {
            KClass orCreateKotlinClass13 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass13, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson13 = new o().a(jsonValue.requireByKeyAsObject("textForMoveCloserToBarcodesHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass13, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson13 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForMoveCloserToBarcodesHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass13, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson13 = jsonValue.requireByKeyAsString("textForMoveCloserToBarcodesHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass13, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson13 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForMoveCloserToBarcodesHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass13, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson13 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForMoveCloserToBarcodesHint"));
            }
            if (fromJson13 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str4 = (String) fromJson13;
        } else {
            str4 = null;
        }
        if (str4 != null) {
            barcodeFindView.setTextForMoveCloserToBarcodesHint(str4);
            Unit unit13 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForTapShutterToPauseScreenHint")) {
            KClass orCreateKotlinClass14 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass14, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson14 = new o().a(jsonValue.requireByKeyAsObject("textForTapShutterToPauseScreenHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass14, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson14 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForTapShutterToPauseScreenHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass14, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson14 = jsonValue.requireByKeyAsString("textForTapShutterToPauseScreenHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass14, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson14 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForTapShutterToPauseScreenHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass14, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson14 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForTapShutterToPauseScreenHint"));
            }
            if (fromJson14 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str5 = (String) fromJson14;
        } else {
            str5 = null;
        }
        if (str5 != null) {
            barcodeFindView.setTextForTapShutterToPauseScreenHint(str5);
            Unit unit14 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForTapShutterToResumeSearchHint")) {
            KClass orCreateKotlinClass15 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass15, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson15 = new o().a(jsonValue.requireByKeyAsObject("textForTapShutterToResumeSearchHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass15, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson15 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForTapShutterToResumeSearchHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass15, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson15 = jsonValue.requireByKeyAsString("textForTapShutterToResumeSearchHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass15, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson15 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForTapShutterToResumeSearchHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass15, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson15 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForTapShutterToResumeSearchHint"));
            }
            if (fromJson15 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str6 = (String) fromJson15;
        } else {
            str6 = null;
        }
        if (str6 != null) {
            barcodeFindView.setTextForTapShutterToResumeSearchHint(str6);
            Unit unit15 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForItemListUpdatedHint")) {
            KClass orCreateKotlinClass16 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass16, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson16 = new o().a(jsonValue.requireByKeyAsObject("textForItemListUpdatedHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass16, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson16 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForItemListUpdatedHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass16, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson16 = jsonValue.requireByKeyAsString("textForItemListUpdatedHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass16, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson16 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForItemListUpdatedHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass16, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson16 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForItemListUpdatedHint"));
            }
            if (fromJson16 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str7 = (String) fromJson16;
        } else {
            str7 = null;
        }
        if (str7 != null) {
            barcodeFindView.setTextForItemListUpdatedHint(str7);
            Unit unit16 = Unit.INSTANCE;
        }
        if (jsonValue.contains("textForItemListUpdatedWhenPausedHint")) {
            KClass orCreateKotlinClass17 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass17, Reflection.getOrCreateKotlinClass(BarcodeFindViewSettings.class))) {
                fromJson17 = new o().a(jsonValue.requireByKeyAsObject("textForItemListUpdatedWhenPausedHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass17, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                fromJson17 = Boolean.valueOf(jsonValue.requireByKeyAsBoolean("textForItemListUpdatedWhenPausedHint"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass17, Reflection.getOrCreateKotlinClass(String.class))) {
                fromJson17 = jsonValue.requireByKeyAsString("textForItemListUpdatedWhenPausedHint");
            } else if (Intrinsics.areEqual(orCreateKotlinClass17, Reflection.getOrCreateKotlinClass(CameraSettings.class))) {
                fromJson17 = new BarcodeCameraSettingsDeserializer(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).a(jsonValue.requireByKeyAsObject("textForItemListUpdatedWhenPausedHint"));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass17, Reflection.getOrCreateKotlinClass(Anchor.class))) {
                    throw new UnsupportedOperationException("Unsupported");
                }
                fromJson17 = AnchorDeserializer.fromJson(jsonValue.requireByKeyAsString("textForItemListUpdatedWhenPausedHint"));
            }
            if (fromJson17 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            str8 = (String) fromJson17;
        }
        if (str8 != null) {
            barcodeFindView.setTextForItemListUpdatedWhenPausedHint(str8);
            Unit unit17 = Unit.INSTANCE;
        }
    }
}
