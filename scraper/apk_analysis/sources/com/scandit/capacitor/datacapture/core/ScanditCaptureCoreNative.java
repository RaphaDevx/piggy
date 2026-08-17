package com.scandit.capacitor.datacapture.core;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.salesforce.marketingcloud.config.a;
import com.scandit.capacitor.datacapture.core.data.ResizeAndMoveInfo;
import com.scandit.capacitor.datacapture.core.errors.JsonParseError;
import com.scandit.capacitor.datacapture.core.handlers.DataCaptureViewHandler;
import com.scandit.capacitor.datacapture.core.utils.CapacitorResult;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.FrameSourceStateDeserializer;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.frameworks.core.CoreModule;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import io.sentry.protocol.Feedback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ScanditCaptureCoreNative.kt */
@CapacitorPlugin(name = "ScanditCaptureCoreNative", permissions = {@Permission(alias = "camera", strings = {"android.permission.CAMERA"})})
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0003J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010*\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010+\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010-\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010/\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00100\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00101\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00102\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00103\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00104\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00105\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00106\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00107\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00108\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u00109\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J&\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020<2\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020<\u0012\u0006\u0012\u0004\u0018\u00010?0>H\u0016J\u0010\u0010@\u001a\u00020\u00192\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010A\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/ScanditCaptureCoreNative;", "Lcom/getcapacitor/Plugin;", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "<init>", "()V", "lifecycleDispatcher", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycleDispatcher;", "captureViewHandler", "Lcom/scandit/capacitor/datacapture/core/handlers/DataCaptureViewHandler;", "coreModule", "Lcom/scandit/datacapture/frameworks/core/CoreModule;", "lastFrameSourceState", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "plugins", "", "registerPluginInstance", "", "instance", "load", "handleOnStart", "handleOnStop", "handleOnDestroy", "handleOnResume", "handleOnPause", "checkCameraPermission", "", "checkOrRequestCameraPermissions", NotificationCompat.CATEGORY_CALL, "Lcom/getcapacitor/PluginCall;", "onCameraPermissionResult", "getCurrentCameraState", "isTorchAvailable", "registerListenerForCameraEvents", "unregisterListenerForCameraEvents", "switchCameraToDesiredState", "contextFromJSON", "disposeContext", "updateContextFromJSON", "setViewPositionAndSize", "showView", "hideView", "viewPointForFramePoint", "viewQuadrilateralForFrameQuadrilateral", "emitFeedback", "getDefaults", "subscribeContextListener", "unsubscribeContextListener", "subscribeViewListener", "unsubscribeViewListener", "getFrame", "subscribeVolumeButtonObserver", "unsubscribeVolumeButtonObserver", "addModeToContext", "removeModeFromContext", "removeAllModes", "createDataCaptureView", "removeDataCaptureView", "updateDataCaptureView", "emit", a.h, "", "payload", "", "", "hasListenersForEvent", "getOpenSourceSoftwareLicenseInfo", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ScanditCaptureCoreNative extends Plugin implements Emitter {
    private static final String EMPTY_STRING_ERROR = "Empty strings are not allowed.";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> SCANDIT_PLUGINS = CollectionsKt.listOf((Object[]) new String[]{"ScanditBarcodeNative", "ScanditParserNative", "ScanditIdNative", "ScanditTextNative"});
    private final ActivityLifecycleDispatcher lifecycleDispatcher = DefaultActivityLifecycle.INSTANCE.getInstance();
    private final DataCaptureViewHandler captureViewHandler = new DataCaptureViewHandler();
    private final CoreModule coreModule = CoreModule.INSTANCE.create(this);
    private FrameSourceState lastFrameSourceState = FrameSourceState.OFF;
    private final List<Plugin> plugins = new ArrayList();

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public boolean hasViewSpecificListenersForEvent(int i, String str) {
        return Emitter.DefaultImpls.hasViewSpecificListenersForEvent(this, i, str);
    }

    /* compiled from: ScanditCaptureCoreNative.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/ScanditCaptureCoreNative$Companion;", "", "<init>", "()V", "EMPTY_STRING_ERROR", "", "missingParameter", "parameterName", "SCANDIT_PLUGINS", "", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String missingParameter(String parameterName) {
            return "Missing parameter '" + parameterName + "' in call.";
        }
    }

    public final void registerPluginInstance(Plugin instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        this.plugins.add(instance);
    }

    @Override // com.getcapacitor.Plugin
    public void load() {
        super.load();
        List<Plugin> list = this.plugins;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Plugin) it.next()).getPluginHandle().getId());
        }
        ArrayList arrayList2 = arrayList;
        for (String str : SCANDIT_PLUGINS) {
            if (!arrayList2.contains(str)) {
                PluginHandle plugin = this.bridge.getPlugin(str);
                if (plugin != null) {
                    Plugin pluginHandle = plugin.getInstance();
                    Intrinsics.checkNotNullExpressionValue(pluginHandle, "getInstance(...)");
                    registerPluginInstance(pluginHandle);
                } else {
                    Log.e("Registering:", str + " not found");
                }
            }
        }
        DataCaptureViewHandler dataCaptureViewHandler = this.captureViewHandler;
        WebView webView = this.bridge.getWebView();
        Intrinsics.checkNotNullExpressionValue(webView, "getWebView(...)");
        dataCaptureViewHandler.initialize(webView);
        CoreModule coreModule = this.coreModule;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        coreModule.onCreate(context);
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnStart() {
        if (checkCameraPermission()) {
            this.coreModule.switchToDesiredCameraState(this.lastFrameSourceState);
        }
        this.coreModule.registerDataCaptureContextListener();
        this.coreModule.registerTopmostDataCaptureViewListener();
        this.coreModule.registerFrameSourceListener();
        this.lifecycleDispatcher.dispatchOnStart();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnStop() {
        this.lifecycleDispatcher.dispatchOnStop();
        FrameSourceState currentCameraDesiredState = this.coreModule.getCurrentCameraDesiredState();
        if (currentCameraDesiredState == null) {
            currentCameraDesiredState = FrameSourceState.OFF;
        }
        this.lastFrameSourceState = currentCameraDesiredState;
        this.coreModule.switchToDesiredCameraState(FrameSourceState.OFF);
        this.coreModule.unregisterDataCaptureContextListener();
        this.coreModule.unregisterTopmostDataCaptureViewListener();
        this.coreModule.unregisterFrameSourceListener();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnDestroy() {
        this.lifecycleDispatcher.dispatchOnDestroy();
        this.coreModule.onDestroy();
        this.captureViewHandler.disposeCurrentWebView();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnResume() {
        this.lifecycleDispatcher.dispatchOnResume();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnPause() {
        this.lifecycleDispatcher.dispatchOnPause();
    }

    private final boolean checkCameraPermission() {
        return getPermissionState("camera") == PermissionState.GRANTED;
    }

    private final void checkOrRequestCameraPermissions(PluginCall call) {
        if (!checkCameraPermission()) {
            requestPermissionForAlias("camera", call, "onCameraPermissionResult");
        } else {
            onCameraPermissionResult(call);
        }
    }

    @PermissionCallback
    private final void onCameraPermissionResult(PluginCall call) {
        if (checkCameraPermission()) {
            this.coreModule.switchToDesiredCameraState(this.lastFrameSourceState);
            call.resolve();
        } else {
            call.reject("Camera permissions not granted.");
        }
    }

    @PluginMethod
    public final void getCurrentCameraState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("position");
        if (string == null) {
            call.reject(INSTANCE.missingParameter("position"));
        } else {
            this.coreModule.getCameraState(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void isTorchAvailable(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("position");
        if (string == null) {
            call.reject(INSTANCE.missingParameter("position"));
        } else {
            this.coreModule.isTorchAvailable(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void registerListenerForCameraEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.registerFrameSourceListener();
        call.resolve();
    }

    @PluginMethod
    public final void unregisterListenerForCameraEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.unregisterFrameSourceListener();
        call.resolve();
    }

    @PluginMethod
    public final void switchCameraToDesiredState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("desiredStateJson");
        if (string == null) {
            call.reject(INSTANCE.missingParameter("desiredStateJson"));
            return;
        }
        if (checkCameraPermission()) {
            this.coreModule.switchCameraToDesiredState(string, new CapacitorResult(call));
            FrameSourceState currentCameraDesiredState = this.coreModule.getCurrentCameraDesiredState();
            if (currentCameraDesiredState == null) {
                currentCameraDesiredState = FrameSourceState.OFF;
            }
            this.lastFrameSourceState = currentCameraDesiredState;
            return;
        }
        this.lastFrameSourceState = FrameSourceStateDeserializer.fromJson(string);
        checkOrRequestCameraPermissions(call);
    }

    @PluginMethod
    public final void contextFromJSON(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("contextJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.createContextFromJson(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void disposeContext(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.disposeContext();
        removeAllListeners(call);
        Iterator<T> it = this.plugins.iterator();
        while (it.hasNext()) {
            ((Plugin) it.next()).removeAllListeners(call);
        }
    }

    @PluginMethod
    public final void updateContextFromJSON(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        final String string = call.getData().getString("contextJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            getActivity().runOnUiThread(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.ScanditCaptureCoreNative$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScanditCaptureCoreNative.updateContextFromJSON$lambda$6(ScanditCaptureCoreNative.this, string, call);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateContextFromJSON$lambda$6(ScanditCaptureCoreNative scanditCaptureCoreNative, String str, PluginCall pluginCall) {
        scanditCaptureCoreNative.coreModule.updateContextFromJson(str, new CapacitorResult(pluginCall));
    }

    @PluginMethod
    public final void setViewPositionAndSize(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            String string = call.getData().getString("position");
            if (string == null) {
                call.reject(EMPTY_STRING_ERROR);
                return;
            }
            this.captureViewHandler.setResizeAndMoveInfo(new ResizeAndMoveInfo(new JSONObject(string)));
            call.resolve();
        } catch (JSONException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void showView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.captureViewHandler.setVisible();
        call.resolve();
    }

    @PluginMethod
    public final void hideView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.captureViewHandler.setInvisible();
        call.resolve();
    }

    @PluginMethod
    public final void viewPointForFramePoint(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("point");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.viewPointForFramePoint(call.getData().getInt("viewId"), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void viewQuadrilateralForFrameQuadrilateral(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("quadrilateral");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.viewQuadrilateralForFrameQuadrilateral(call.getData().getInt("viewId"), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void emitFeedback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString(Feedback.TYPE);
        if (string != null) {
            this.coreModule.emitFeedback(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void getDefaults(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve(JSObject.fromJSONObject(new JSONObject(this.coreModule.getDefaults())));
    }

    @PluginMethod
    public final void subscribeContextListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.registerDataCaptureContextListener();
        call.resolve();
    }

    @PluginMethod
    public final void unsubscribeContextListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.unregisterDataCaptureContextListener();
        call.resolve();
    }

    @PluginMethod
    public final void subscribeViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.registerDataCaptureViewListener(call.getData().getInt("viewId"));
        call.resolve();
    }

    @PluginMethod
    public final void unsubscribeViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.unregisterDataCaptureViewListener(call.getData().getInt("viewId"));
        call.resolve();
    }

    @PluginMethod
    public final void getFrame(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("frameId");
        if (string == null) {
            call.reject(INSTANCE.missingParameter("frameId"));
        } else {
            this.coreModule.getLastFrameAsJson(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void subscribeVolumeButtonObserver(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void unsubscribeVolumeButtonObserver(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void addModeToContext(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.addModeToContext(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void removeModeFromContext(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.removeModeFromContext(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void removeAllModes(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.removeAllModes(new CapacitorResult(call));
    }

    @PluginMethod
    public final void createDataCaptureView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("viewJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
            return;
        }
        final DataCaptureView createDataCaptureView = this.coreModule.createDataCaptureView(string, new CapacitorResult(call));
        if (createDataCaptureView != null) {
            DataCaptureView dataCaptureView = this.captureViewHandler.getDataCaptureView();
            if (dataCaptureView != null) {
                this.coreModule.dataCaptureViewDisposed(dataCaptureView);
                this.captureViewHandler.removeDataCaptureView(dataCaptureView);
            }
            getActivity().runOnUiThread(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.ScanditCaptureCoreNative$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ScanditCaptureCoreNative.createDataCaptureView$lambda$9(ScanditCaptureCoreNative.this, createDataCaptureView);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createDataCaptureView$lambda$9(ScanditCaptureCoreNative scanditCaptureCoreNative, DataCaptureView dataCaptureView) {
        DataCaptureViewHandler dataCaptureViewHandler = scanditCaptureCoreNative.captureViewHandler;
        AppCompatActivity activity = scanditCaptureCoreNative.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        dataCaptureViewHandler.addDataCaptureView(dataCaptureView, activity);
    }

    @PluginMethod
    public final void removeDataCaptureView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        final DataCaptureView dataCaptureViewById = this.coreModule.getDataCaptureViewById(call.getData().getInt("viewId"));
        if (dataCaptureViewById != null) {
            dataCaptureViewById.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.ScanditCaptureCoreNative$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ScanditCaptureCoreNative.removeDataCaptureView$lambda$10(ScanditCaptureCoreNative.this, dataCaptureViewById);
                }
            });
        }
        call.resolve();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeDataCaptureView$lambda$10(ScanditCaptureCoreNative scanditCaptureCoreNative, DataCaptureView dataCaptureView) {
        scanditCaptureCoreNative.coreModule.dataCaptureViewDisposed(dataCaptureView);
        scanditCaptureCoreNative.captureViewHandler.removeDataCaptureView(dataCaptureView);
    }

    @PluginMethod
    public final void updateDataCaptureView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("viewJson");
        if (string == null) {
            call.reject(EMPTY_STRING_ERROR);
        } else {
            this.coreModule.updateDataCaptureView(string, new CapacitorResult(call));
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public void emit(String eventName, Map<String, Object> payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(payload, "payload");
        JSObject jSObject = new JSObject();
        jSObject.put("name", eventName);
        jSObject.put("data", new JSONObject(payload).toString());
        notifyListeners(eventName, jSObject);
    }

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public boolean hasListenersForEvent(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        return hasListeners(eventName);
    }

    @PluginMethod
    public final void getOpenSourceSoftwareLicenseInfo(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.coreModule.getOpenSourceSoftwareLicenseInfo(new CapacitorResult(call));
    }
}
