package com.scandit.datacapture.barcode.pick.serialization;

import android.view.ViewGroup;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper;
import com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProvider;
import com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallback;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProduct;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetter;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetterKind;
import com.scandit.datacapture.tools.internal.sdk.ProxySetter;
import com.tealium.library.DataSources;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 U2\u00020\u0001:\u0003UVWB\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H\u0097\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0016H\u0097\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0011\u001a\u00020\u0016H\u0097\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b \u0010!J\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b#\u0010$J \u0010&\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0016H\u0097\u0001¢\u0006\u0004\b&\u0010'J \u0010*\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b*\u0010+J\u0018\u0010,\u001a\u00020(2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b,\u0010-J \u0010.\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b.\u0010/J \u00100\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b0\u00101J\u001d\u00105\u001a\u0002042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00103\u001a\u000202¢\u0006\u0004\b5\u00106J%\u00108\u001a\u00020\u00122\u0006\u00107\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b8\u0010\u0014J\u0015\u00109\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b9\u0010$J\u0015\u0010:\u001a\u00020(2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b:\u0010-J-\u0010>\u001a\u00020=2\u0006\u0010<\u001a\u00020;2\u0006\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b>\u0010?J7\u0010>\u001a\u00020=2\u0006\u0010<\u001a\u00020;2\u0006\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010A\u001a\u0004\u0018\u00010@¢\u0006\u0004\b>\u0010BJ7\u0010>\u001a\u00020=2\u0006\u0010<\u001a\u00020;2\u0006\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010D\u001a\u0004\u0018\u00010C¢\u0006\u0004\b>\u0010EJ\u001d\u0010G\u001a\u00020=2\u0006\u0010F\u001a\u00020=2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\bG\u0010HR$\u0010P\u001a\u0004\u0018\u00010I8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0014\u0010T\u001a\u00020Q8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bR\u0010S¨\u0006X"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerProxy;", "<init>", "()V", "_deserializer", "()Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/NativeBarcodePickDeserializer;", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/NativeBarcodePickDeserializer;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "_modeDeserializerImpl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "context", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProvider;", "productProvider", "", "json", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "_modeFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProvider;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "mode", "Lcom/scandit/datacapture/core/json/JsonValue;", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "_overlayFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProduct;", "_productsFromJson", "(Lcom/scandit/datacapture/core/json/JsonValue;)Ljava/util/List;", "deserializerProxy", "", "_setDeserializer", "(Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;)V", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "_settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "overlay", "_updateOverlayFromJson", "(Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "settings", "_updateViewSettingsFromJson", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "_viewSettingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProvider;", "asyncMapperProductProviderFromJson", "(Ljava/lang/String;Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;)Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProvider;", "dataCaptureContext", "modeFromJson", "settingsFromJson", "viewSettingsFromJson", "Landroid/view/ViewGroup;", "parentView", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "viewFromJson", "(Landroid/view/ViewGroup;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncProvider;", "asyncProvider", "(Landroid/view/ViewGroup;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Ljava/lang/String;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncProvider;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleCustomViewProvider;", "asyncCustomViewProvider", "(Landroid/view/ViewGroup;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Ljava/lang/String;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleCustomViewProvider;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "updateViewFromJson", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerListener;", "c", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerListener;", "getListener", "()Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerListener;", "setListener", "(Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/BarcodePickDeserializerHelper;", "get_helper", "()Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/BarcodePickDeserializerHelper;", "_helper", "Companion", "com/scandit/datacapture/barcode/pick/serialization/b", "com/scandit/datacapture/barcode/pick/serialization/k", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickDeserializer implements BarcodePickDeserializerProxy {
    public static final String FIELD_BARCODE_PICK_STATE = "barcodePickState";
    public static final String FIELD_ICON = "icon";
    public static final String FIELD_ICONS_FOR_STATE = "iconsForState";
    public static final String FIELD_SELECTED_ICONS_FOR_STATE = "selectedIconsForState";
    private final k a;
    private final /* synthetic */ BarcodePickDeserializerProxyAdapter b;

    /* renamed from: c, reason: from kotlin metadata */
    private BarcodePickDeserializerListener listener;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodePickDeserializer() {
        k kVar = new k();
        com.scandit.datacapture.barcode.internal.module.pick.serialization.e eVar = new com.scandit.datacapture.barcode.internal.module.pick.serialization.e(kVar);
        NativeBarcodePickDeserializer create = NativeBarcodePickDeserializer.create(eVar);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.a = kVar;
        this.b = new BarcodePickDeserializerProxyAdapter(create, null, 2, 0 == true ? 1 : 0);
        ProxyCacheKt.getGlobalProxyCache().getOrPut(Reflection.getOrCreateKotlinClass(BarcodePickDeserializerHelper.class), null, kVar, new a(eVar));
        _setDeserializer(this);
        create.setListener(new BarcodePickDeserializerListenerReversedAdapter(new b(this), this, null, 4, null));
    }

    private final BarcodePickView a(ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePick barcodePick, String str, Object obj) {
        CameraSettings cameraSettings;
        JSONObject jSONObject = new JSONObject(str);
        JSONObject optJSONObject = jSONObject.optJSONObject("viewSettings");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        String jSONObject2 = optJSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        BarcodePickViewSettings viewSettingsFromJson = viewSettingsFromJson(jSONObject2);
        BarcodePickViewHighlightStyle highlightStyle = viewSettingsFromJson.getHighlightStyle();
        if ((highlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons) && (obj instanceof BarcodePickViewHighlightStyleAsyncProvider)) {
            ((BarcodePickViewHighlightStyle.DotWithIcons) highlightStyle).setAsyncStyleProvider((BarcodePickViewHighlightStyleAsyncProvider) obj);
        } else if ((highlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) && (obj instanceof BarcodePickViewHighlightStyleAsyncProvider)) {
            ((BarcodePickViewHighlightStyle.RectangularWithIcons) highlightStyle).setAsyncStyleProvider((BarcodePickViewHighlightStyleAsyncProvider) obj);
        } else if ((highlightStyle instanceof BarcodePickViewHighlightStyle.CustomView) && (obj instanceof BarcodePickViewHighlightStyleCustomViewProvider)) {
            ((BarcodePickViewHighlightStyle.CustomView) highlightStyle).setAsyncCustomViewProvider((BarcodePickViewHighlightStyleCustomViewProvider) obj);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("cameraSettings");
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        }
        boolean optBoolean = optJSONObject2.optBoolean("usesRecommendedSettings", true);
        if (optBoolean) {
            cameraSettings = BarcodePick.INSTANCE.createRecommendedCameraSettings();
        } else {
            if (optBoolean) {
                throw new NoWhenBranchMatchedException();
            }
            cameraSettings = new CameraSettings();
        }
        FrameSourceDeserializer frameSourceDeserializer = new FrameSourceDeserializer(CollectionsKt.emptyList());
        String jSONObject3 = optJSONObject2.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject3, "toString(...)");
        return BarcodePickView.INSTANCE.newInstance(viewGroup, dataCaptureContext, barcodePick, viewSettingsFromJson, frameSourceDeserializer.updateCameraSettingsFromJson(cameraSettings, jSONObject3));
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyGetter(ProxyGetterKind.WITH_SETTER)
    public BarcodePickDeserializer _deserializer() {
        return this.b._deserializer();
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodePickDeserializer getA() {
        return this.b.getA();
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy, com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    @NativeImpl
    /* renamed from: _modeDeserializerImpl */
    public NativeDataCaptureModeDeserializer getC() {
        return this.b.getC();
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "barcodePickFromJson")
    public BarcodePick _modeFromJson(DataCaptureContext context, BarcodePickProductProvider productProvider, String json) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(productProvider, "productProvider");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._modeFromJson(context, productProvider, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "overlayFromJson")
    public BarcodePickBasicOverlay _overlayFromJson(BarcodePick mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._overlayFromJson(mode, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "barcodePickProductsFromJson")
    public List<BarcodePickProduct> _productsFromJson(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._productsFromJson(json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxySetter
    public void _setDeserializer(BarcodePickDeserializer deserializerProxy) {
        Intrinsics.checkNotNullParameter(deserializerProxy, "deserializerProxy");
        this.b._setDeserializer(deserializerProxy);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "settingsFromJson")
    public BarcodePickSettings _settingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._settingsFromJson(json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "updateOverlayFromJson")
    public BarcodePickBasicOverlay _updateOverlayFromJson(BarcodePickBasicOverlay overlay, JsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._updateOverlayFromJson(overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "updateViewSettingsFromJson")
    public BarcodePickViewSettings _updateViewSettingsFromJson(BarcodePickViewSettings settings, String json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._updateViewSettingsFromJson(settings, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "viewSettingsFromJson")
    public BarcodePickViewSettings _viewSettingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b._viewSettingsFromJson(json);
    }

    public final BarcodePickAsyncMapperProductProvider asyncMapperProductProviderFromJson(String json, BarcodePickAsyncMapperProductProviderCallback callback) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(callback, "callback");
        return new BarcodePickAsyncMapperProductProvider(CollectionsKt.toSet(_productsFromJson(new JsonValue(json))), callback);
    }

    public final BarcodePickDeserializerListener getListener() {
        return this.listener;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    @ProxyFunction(nativeName = "getHelper", property = "_helper")
    public BarcodePickDeserializerHelper get_helper() {
        return this.b.get_helper();
    }

    public final BarcodePick modeFromJson(DataCaptureContext dataCaptureContext, BarcodePickProductProvider productProvider, String json) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(productProvider, "productProvider");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePick _modeFromJson = _modeFromJson(dataCaptureContext, productProvider, json);
        this.a.a = null;
        return _modeFromJson;
    }

    public final void setListener(BarcodePickDeserializerListener barcodePickDeserializerListener) {
        this.listener = barcodePickDeserializerListener;
    }

    public final BarcodePickSettings settingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickSettings _settingsFromJson = _settingsFromJson(json);
        this.a.a = null;
        return _settingsFromJson;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction(nativeName = "updateBarcodePickFromJson")
    public BarcodePick updateModeFromJson(BarcodePick mode, String json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b.updateModeFromJson(mode, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    @ProxyFunction
    public BarcodePickSettings updateSettingsFromJson(BarcodePickSettings settings, String json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        return this.b.updateSettingsFromJson(settings, json);
    }

    public final BarcodePickView updateViewFromJson(BarcodePickView view, String json) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        view._applySettings$scandit_barcode_capture(viewSettingsFromJson(json));
        return view;
    }

    public final BarcodePickView viewFromJson(ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, String json) {
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        return a(parentView, dataCaptureContext, mode, json, null);
    }

    public final BarcodePickViewSettings viewSettingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickViewSettings _viewSettingsFromJson = _viewSettingsFromJson(json);
        get_helper().clear();
        return _viewSettingsFromJson;
    }

    public final BarcodePickView viewFromJson(ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, String json, BarcodePickViewHighlightStyleAsyncProvider asyncProvider) {
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        return a(parentView, dataCaptureContext, mode, json, asyncProvider);
    }

    public final BarcodePickView viewFromJson(ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, String json, BarcodePickViewHighlightStyleCustomViewProvider asyncCustomViewProvider) {
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        return a(parentView, dataCaptureContext, mode, json, asyncCustomViewProvider);
    }
}
