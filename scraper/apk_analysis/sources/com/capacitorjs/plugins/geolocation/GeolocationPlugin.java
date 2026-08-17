package com.capacitorjs.plugins.geolocation;

import android.content.Context;
import android.os.Build;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.capacitorjs.plugins.geolocation.GeolocationErrors;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.salesforce.marketingcloud.storage.db.h;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import io.sentry.SentryEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: GeolocationPlugin.kt */
@CapacitorPlugin(name = "Geolocation", permissions = {@Permission(alias = "location", strings = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}), @Permission(alias = GeolocationPlugin.COARSE_LOCATION_ALIAS, strings = {"android.permission.ACCESS_COARSE_LOCATION"})})
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u0000 62\u00020\u0001:\u00016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0014J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0017J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0017J\u001e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J&\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H\u0002J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0003J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0003J\u001e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H\u0002J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001a\u0010%\u001a\u00020\r2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J)\u0010(\u001a\u00020\r*\u00020\u000b2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\u00020\r*\u00020\u000b2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u0002012\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u001c\u00102\u001a\u000203*\u00020\u000b2\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u000203H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationPlugin;", "Lcom/getcapacitor/Plugin;", "<init>", "()V", "controller", "Lio/ionic/libs/iongeolocationlib/controller/IONGLOCController;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "watchingCalls", "", "", "Lcom/getcapacitor/PluginCall;", "load", "", "handleOnDestroy", "checkPermissions", NotificationCompat.CATEGORY_CALL, "requestPermissions", "checkLocationState", "onLocationEnabled", "Lkotlin/Function0;", "getCurrentPosition", "watchPosition", "handlePermissionRequest", "callbackName", "onPermissionGranted", "completeCurrentPosition", "completeWatchPosition", "handlePermissionResult", "clearWatch", "getAlias", "getPosition", "startWatch", "getJSObjectForLocation", "Lcom/getcapacitor/JSObject;", "locationResult", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", "onLocationError", SentryEvent.JsonKeys.EXCEPTION, "", "sendSuccess", "result", "keepCallback", "", "(Lcom/getcapacitor/PluginCall;Lcom/getcapacitor/JSObject;Ljava/lang/Boolean;)V", "sendError", "error", "Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "createOptions", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "getNumber", "", "name", "defaultValue", "Companion", "capacitor-geolocation_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class GeolocationPlugin extends Plugin {
    public static final String COARSE_LOCATION_ALIAS = "coarseLocation";
    public static final String LOCATION_ALIAS = "location";
    private IONGLOCController controller;
    private CoroutineScope coroutineScope;
    private final Map<String, PluginCall> watchingCalls = new LinkedHashMap();

    @Override // com.getcapacitor.Plugin
    public void load() {
        super.load();
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
        ActivityResultLauncher registerForActivityResult = getActivity().registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                GeolocationPlugin.load$lambda$0(GeolocationPlugin.this, (ActivityResult) obj);
            }
        });
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(...)");
        this.controller = new IONGLOCController(fusedLocationProviderClient, registerForActivityResult, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void load$lambda$0(GeolocationPlugin geolocationPlugin, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new GeolocationPlugin$load$activityLauncher$1$1(geolocationPlugin, result, null), 3, null);
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnDestroy() {
        super.handleOnDestroy();
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        }
        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkPermissions$lambda$1(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        super.checkPermissions(pluginCall);
        return Unit.INSTANCE;
    }

    @Override // com.getcapacitor.Plugin
    @PluginMethod
    public void checkPermissions(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        checkLocationState(call, new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit checkPermissions$lambda$1;
                checkPermissions$lambda$1 = GeolocationPlugin.checkPermissions$lambda$1(GeolocationPlugin.this, call);
                return checkPermissions$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestPermissions$lambda$2(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        super.requestPermissions(pluginCall);
        return Unit.INSTANCE;
    }

    @Override // com.getcapacitor.Plugin
    @PluginMethod
    public void requestPermissions(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        checkLocationState(call, new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit requestPermissions$lambda$2;
                requestPermissions$lambda$2 = GeolocationPlugin.requestPermissions$lambda$2(GeolocationPlugin.this, call);
                return requestPermissions$lambda$2;
            }
        });
    }

    private final void checkLocationState(PluginCall call, Function0<Unit> onLocationEnabled) {
        IONGLOCController iONGLOCController = this.controller;
        if (iONGLOCController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
            iONGLOCController = null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (iONGLOCController.areLocationServicesEnabled(context)) {
            onLocationEnabled.invoke();
        } else {
            sendError(call, GeolocationErrors.INSTANCE.getLOCATION_DISABLED());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getCurrentPosition$lambda$3(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        geolocationPlugin.getPosition(pluginCall);
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void getCurrentPosition(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        handlePermissionRequest(call, "completeCurrentPosition", new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit currentPosition$lambda$3;
                currentPosition$lambda$3 = GeolocationPlugin.getCurrentPosition$lambda$3(GeolocationPlugin.this, call);
                return currentPosition$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit watchPosition$lambda$4(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        geolocationPlugin.startWatch(pluginCall);
        return Unit.INSTANCE;
    }

    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public final void watchPosition(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        handlePermissionRequest(call, "completeWatchPosition", new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit watchPosition$lambda$4;
                watchPosition$lambda$4 = GeolocationPlugin.watchPosition$lambda$4(GeolocationPlugin.this, call);
                return watchPosition$lambda$4;
            }
        });
    }

    private final void handlePermissionRequest(PluginCall call, String callbackName, Function0<Unit> onPermissionGranted) {
        String alias = getAlias(call);
        if (getPermissionState(alias) != PermissionState.GRANTED) {
            requestPermissionForAlias(alias, call, callbackName);
        } else {
            onPermissionGranted.invoke();
        }
    }

    @PermissionCallback
    private final void completeCurrentPosition(final PluginCall call) {
        handlePermissionResult(call, new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit completeCurrentPosition$lambda$5;
                completeCurrentPosition$lambda$5 = GeolocationPlugin.completeCurrentPosition$lambda$5(GeolocationPlugin.this, call);
                return completeCurrentPosition$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit completeCurrentPosition$lambda$5(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        geolocationPlugin.getPosition(pluginCall);
        return Unit.INSTANCE;
    }

    @PermissionCallback
    private final void completeWatchPosition(final PluginCall call) {
        handlePermissionResult(call, new Function0() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit completeWatchPosition$lambda$6;
                completeWatchPosition$lambda$6 = GeolocationPlugin.completeWatchPosition$lambda$6(GeolocationPlugin.this, call);
                return completeWatchPosition$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit completeWatchPosition$lambda$6(GeolocationPlugin geolocationPlugin, PluginCall pluginCall) {
        geolocationPlugin.startWatch(pluginCall);
        return Unit.INSTANCE;
    }

    private final void handlePermissionResult(PluginCall call, Function0<Unit> onPermissionGranted) {
        if (getPermissionState(COARSE_LOCATION_ALIAS) == PermissionState.GRANTED) {
            onPermissionGranted.invoke();
        } else {
            sendError(call, GeolocationErrors.INSTANCE.getLOCATION_PERMISSIONS_DENIED());
        }
    }

    @PluginMethod
    public final void clearWatch(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getString("id");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            sendError(call, GeolocationErrors.INSTANCE.getWATCH_ID_NOT_PROVIDED());
            return;
        }
        PluginCall remove = this.watchingCalls.remove(string);
        if (remove != null) {
            remove.release(this.bridge);
        }
        IONGLOCController iONGLOCController = this.controller;
        if (iONGLOCController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
            iONGLOCController = null;
        }
        if (iONGLOCController.clearWatch(string)) {
            sendSuccess$default(this, call, null, null, 3, null);
        } else {
            sendError(call, GeolocationErrors.INSTANCE.getWATCH_ID_NOT_FOUND());
        }
    }

    private final String getAlias(PluginCall call) {
        if (Build.VERSION.SDK_INT >= 31) {
            Boolean bool = call.getBoolean("enableHighAccuracy");
            if (!(bool != null ? bool.booleanValue() : false)) {
                return COARSE_LOCATION_ALIAS;
            }
        }
        return "location";
    }

    private final void getPosition(PluginCall call) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2 = this.coroutineScope;
        if (coroutineScope2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        } else {
            coroutineScope = coroutineScope2;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new GeolocationPlugin$getPosition$1(this, call, null), 3, null);
    }

    private final void startWatch(PluginCall call) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2 = this.coroutineScope;
        if (coroutineScope2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        } else {
            coroutineScope = coroutineScope2;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new GeolocationPlugin$startWatch$1(call, this, null), 3, null);
        this.watchingCalls.put(call.getCallbackId(), call);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSObject getJSObjectForLocation(IONGLOCLocationResult locationResult) {
        JSObject jSObject = new JSObject();
        jSObject.put(h.a.b, locationResult.getLatitude());
        jSObject.put(h.a.c, locationResult.getLongitude());
        jSObject.put("accuracy", (Object) Float.valueOf(locationResult.getAccuracy()));
        jSObject.put("altitude", locationResult.getAltitude());
        Float altitudeAccuracy = locationResult.getAltitudeAccuracy();
        if (altitudeAccuracy != null) {
            jSObject.put("altitudeAccuracy", (Object) Float.valueOf(altitudeAccuracy.floatValue()));
        }
        jSObject.put("speed", (Object) Float.valueOf(locationResult.getSpeed()));
        jSObject.put("heading", (Object) Float.valueOf(locationResult.getHeading()));
        JSObject jSObject2 = new JSObject();
        jSObject2.put("timestamp", locationResult.getTimestamp());
        jSObject2.put("coords", (Object) jSObject);
        return jSObject2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onLocationError(Throwable exception, PluginCall call) {
        if (exception instanceof IONGLOCException.IONGLOCRequestDeniedException) {
            sendError(call, GeolocationErrors.INSTANCE.getLOCATION_ENABLE_REQUEST_DENIED());
            return;
        }
        if (exception instanceof IONGLOCException.IONGLOCSettingsException) {
            sendError(call, GeolocationErrors.INSTANCE.getLOCATION_SETTINGS_ERROR());
            return;
        }
        if (exception instanceof IONGLOCException.IONGLOCInvalidTimeoutException) {
            sendError(call, GeolocationErrors.INSTANCE.getINVALID_TIMEOUT());
            return;
        }
        if (exception instanceof IONGLOCException.IONGLOCGoogleServicesException) {
            if (((IONGLOCException.IONGLOCGoogleServicesException) exception).getResolvable()) {
                sendError(call, GeolocationErrors.INSTANCE.getGOOGLE_SERVICES_RESOLVABLE());
                return;
            } else {
                sendError(call, GeolocationErrors.INSTANCE.getGOOGLE_SERVICES_ERROR());
                return;
            }
        }
        if (exception instanceof IONGLOCException.IONGLOCLocationRetrievalTimeoutException) {
            sendError(call, GeolocationErrors.INSTANCE.getGET_LOCATION_TIMEOUT());
        } else {
            sendError(call, GeolocationErrors.INSTANCE.getPOSITION_UNAVAILABLE());
        }
    }

    static /* synthetic */ void sendSuccess$default(GeolocationPlugin geolocationPlugin, PluginCall pluginCall, JSObject jSObject, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            jSObject = null;
        }
        if ((i & 2) != 0) {
            bool = false;
        }
        geolocationPlugin.sendSuccess(pluginCall, jSObject, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSuccess(PluginCall pluginCall, JSObject jSObject, Boolean bool) {
        pluginCall.setKeepAlive(bool);
        if (jSObject != null) {
            pluginCall.resolve(jSObject);
        } else {
            pluginCall.resolve();
        }
    }

    private final void sendError(PluginCall pluginCall, GeolocationErrors.ErrorInfo errorInfo) {
        pluginCall.reject(errorInfo.getMessage(), errorInfo.getCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IONGLOCLocationOptions createOptions(PluginCall call) {
        long number = getNumber(call, "timeout", 10000L);
        long number2 = getNumber(call, "maximumAge", 0L);
        Boolean bool = call.getBoolean("enableHighAccuracy", false);
        return new IONGLOCLocationOptions(number, number2, bool != null ? bool.booleanValue() : false, Long.valueOf(getNumber(call, "minimumUpdateInterval", 5000L)));
    }

    private final long getNumber(PluginCall pluginCall, String str, long j) {
        Long l = pluginCall.getLong(str);
        if (l == null) {
            l = pluginCall.getInt(str) != null ? Long.valueOf(r0.intValue()) : null;
            if (l == null) {
                return j;
            }
        }
        return l.longValue();
    }
}
