package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.location.LocationManagerCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.tealium.library.DataSources;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* compiled from: IONGLOCController.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 22\u00020\u0001:\u00012B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ0\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00100\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J4\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001eH\u0082@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\fJ\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u001eH\u0002J,\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+J\u0016\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.H\u0086@¢\u0006\u0002\u0010/J\f\u00100\u001a\u00020\u0017*\u000201H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCController;", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "helper", "Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;", "(Lcom/google/android/gms/location/FusedLocationProviderClient;Landroidx/activity/result/ActivityResultLauncher;Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;)V", "locationCallbacks", "", "", "Lcom/google/android/gms/location/LocationCallback;", "resolveLocationSettingsResultFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/Result;", "", "watchIdsBlacklist", "", "addWatch", "Lkotlinx/coroutines/flow/Flow;", "", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroid/app/Activity;", RRWebOptionsEvent.EVENT_TAG, "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "watchId", "areLocationServicesEnabled", "", "context", "Landroid/content/Context;", "checkLocationPreconditions", "isSingleLocationRequest", "checkLocationPreconditions-BWLJW6A", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkWatchInBlackList", "clearWatch", "id", "addToBlackList", "getCurrentPosition", "getCurrentPosition-0E7RQCE", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResolvableExceptionResult", "resultCode", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOSLocationResult", "Landroid/location/Location;", "Companion", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class IONGLOCController {
    private static final String LOG_TAG = "IONGeolocationController";
    private final IONGLOCServiceHelper helper;
    private final Map<String, LocationCallback> locationCallbacks;
    private MutableSharedFlow<Result<Unit>> resolveLocationSettingsResultFlow;
    private final List<String> watchIdsBlacklist;

    public IONGLOCController(FusedLocationProviderClient fusedLocationClient, ActivityResultLauncher<IntentSenderRequest> activityLauncher, IONGLOCServiceHelper helper) {
        Intrinsics.checkNotNullParameter(fusedLocationClient, "fusedLocationClient");
        Intrinsics.checkNotNullParameter(activityLauncher, "activityLauncher");
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.helper = helper;
        this.locationCallbacks = new LinkedHashMap();
        this.watchIdsBlacklist = new ArrayList();
    }

    public /* synthetic */ IONGLOCController(FusedLocationProviderClient fusedLocationProviderClient, ActivityResultLauncher activityResultLauncher, IONGLOCServiceHelper iONGLOCServiceHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fusedLocationProviderClient, activityResultLauncher, (i & 4) != 0 ? new IONGLOCServiceHelper(fusedLocationProviderClient, activityResultLauncher) : iONGLOCServiceHelper);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0062 A[Catch: Exception -> 0x0097, TryCatch #0 {Exception -> 0x0097, blocks: (B:12:0x002d, B:13:0x008a, B:19:0x0042, B:20:0x005c, B:22:0x0062, B:24:0x006a, B:25:0x0071, B:27:0x007a, B:31:0x004f), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007a A[Catch: Exception -> 0x0097, TryCatch #0 {Exception -> 0x0097, blocks: (B:12:0x002d, B:13:0x008a, B:19:0x0042, B:20:0x005c, B:22:0x0062, B:24:0x006a, B:25:0x0071, B:27:0x007a, B:31:0x004f), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* renamed from: getCurrentPosition-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m3800getCurrentPosition0E7RQCE(android.app.Activity r6, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r7, kotlin.coroutines.Continuation<? super kotlin.Result<io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$getCurrentPosition$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r5 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> L97
            goto L8a
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            java.lang.Object r5 = r0.L$1
            r7 = r5
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r7 = (io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions) r7
            java.lang.Object r5 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r5 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> L97
            kotlin.Result r8 = (kotlin.Result) r8     // Catch: java.lang.Exception -> L97
            java.lang.Object r6 = r8.getValue()     // Catch: java.lang.Exception -> L97
            goto L5c
        L4c:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5     // Catch: java.lang.Exception -> L97
            r0.L$1 = r7     // Catch: java.lang.Exception -> L97
            r0.label = r4     // Catch: java.lang.Exception -> L97
            java.lang.Object r6 = r5.m3799checkLocationPreconditionsBWLJW6A(r6, r7, r4, r0)     // Catch: java.lang.Exception -> L97
            if (r6 != r1) goto L5c
            return r1
        L5c:
            boolean r8 = kotlin.Result.m3951isFailureimpl(r6)     // Catch: java.lang.Exception -> L97
            if (r8 == 0) goto L7a
            kotlin.Result$Companion r5 = kotlin.Result.INSTANCE     // Catch: java.lang.Exception -> L97
            java.lang.Throwable r5 = kotlin.Result.m3948exceptionOrNullimpl(r6)     // Catch: java.lang.Exception -> L97
            if (r5 != 0) goto L71
            java.lang.NullPointerException r5 = new java.lang.NullPointerException     // Catch: java.lang.Exception -> L97
            r5.<init>()     // Catch: java.lang.Exception -> L97
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch: java.lang.Exception -> L97
        L71:
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)     // Catch: java.lang.Exception -> L97
            java.lang.Object r5 = kotlin.Result.m3945constructorimpl(r5)     // Catch: java.lang.Exception -> L97
            return r5
        L7a:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r6 = r5.helper     // Catch: java.lang.Exception -> L97
            r0.L$0 = r5     // Catch: java.lang.Exception -> L97
            r8 = 0
            r0.L$1 = r8     // Catch: java.lang.Exception -> L97
            r0.label = r3     // Catch: java.lang.Exception -> L97
            java.lang.Object r8 = r6.getCurrentLocation$IONGeolocationLib_release(r7, r0)     // Catch: java.lang.Exception -> L97
            if (r8 != r1) goto L8a
            return r1
        L8a:
            android.location.Location r8 = (android.location.Location) r8     // Catch: java.lang.Exception -> L97
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE     // Catch: java.lang.Exception -> L97
            io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult r5 = r5.toOSLocationResult(r8)     // Catch: java.lang.Exception -> L97
            java.lang.Object r5 = kotlin.Result.m3945constructorimpl(r5)     // Catch: java.lang.Exception -> L97
            return r5
        L97:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Error fetching location: "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "IONGeolocationController"
            android.util.Log.d(r7, r6)
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m3945constructorimpl(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCController.m3800getCurrentPosition0E7RQCE(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object onResolvableExceptionResult(int i, Continuation<? super Unit> continuation) {
        Object m3945constructorimpl;
        MutableSharedFlow<Result<Unit>> mutableSharedFlow = this.resolveLocationSettingsResultFlow;
        if (mutableSharedFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolveLocationSettingsResultFlow");
            mutableSharedFlow = null;
        }
        if (i == -1) {
            Result.Companion companion = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(Unit.INSTANCE);
        } else {
            Result.Companion companion2 = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(new IONGLOCException.IONGLOCRequestDeniedException("Request to enable location denied.", null, 2, null)));
        }
        Object emit = mutableSharedFlow.emit(Result.m3944boximpl(m3945constructorimpl), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    public final boolean areLocationServicesEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        return LocationManagerCompat.isLocationEnabled((LocationManager) systemService);
    }

    public final Flow<Result<List<IONGLOCLocationResult>>> addWatch(Activity activity, IONGLOCLocationOptions options, String watchId) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(watchId, "watchId");
        return FlowKt.callbackFlow(new IONGLOCController$addWatch$1(this, activity, options, watchId, null));
    }

    public final boolean clearWatch(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return clearWatch(id, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: checkLocationPreconditions-BWLJW6A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m3799checkLocationPreconditionsBWLJW6A(android.app.Activity r11, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r12, boolean r13, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1
            if (r0 == 0) goto L14
            r0 = r14
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCController$checkLocationPreconditions$1
            r0.<init>(r10, r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r2 = 1
            r8 = 2
            r9 = 0
            if (r1 == 0) goto L3f
            if (r1 == r2) goto L37
            if (r1 != r8) goto L2f
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lcd
        L2f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L37:
            java.lang.Object r10 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCController r10 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCController) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto La5
        L3f:
            kotlin.ResultKt.throwOnFailure(r14)
            long r3 = r12.getTimeout()
            r5 = 0
            int r14 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r14 > 0) goto L60
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCInvalidTimeoutException r10 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCInvalidTimeoutException
            java.lang.String r11 = "Timeout needs to be a positive value."
            r10.<init>(r11, r9, r8, r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m3945constructorimpl(r10)
            return r10
        L60:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r14 = r10.helper
            java.lang.Object r14 = r14.m3802x6e9c783(r11)
            boolean r1 = kotlin.Result.m3951isFailureimpl(r14)
            if (r1 == 0) goto L84
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            java.lang.Throwable r10 = kotlin.Result.m3948exceptionOrNullimpl(r14)
            if (r10 != 0) goto L7b
            java.lang.NullPointerException r10 = new java.lang.NullPointerException
            r10.<init>()
            java.lang.Throwable r10 = (java.lang.Throwable) r10
        L7b:
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m3945constructorimpl(r10)
            return r10
        L84:
            r14 = 7
            r1 = 0
            kotlinx.coroutines.flow.MutableSharedFlow r14 = kotlinx.coroutines.flow.SharedFlowKt.MutableSharedFlow$default(r1, r1, r9, r14, r9)
            r10.resolveLocationSettingsResultFlow = r14
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r1 = r10.helper
            if (r13 == 0) goto L92
            r4 = r5
            goto L97
        L92:
            long r13 = r12.getTimeout()
            r4 = r13
        L97:
            r0.L$0 = r10
            r0.label = r2
            r2 = r11
            r3 = r12
            r6 = r0
            java.lang.Object r14 = r1.checkLocationSettings$IONGeolocationLib_release(r2, r3, r4, r6)
            if (r14 != r7) goto La5
            return r7
        La5:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r11 = r14.booleanValue()
            if (r11 == 0) goto Lb6
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            java.lang.Object r10 = kotlin.Result.m3945constructorimpl(r10)
            return r10
        Lb6:
            kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Result<kotlin.Unit>> r10 = r10.resolveLocationSettingsResultFlow
            if (r10 != 0) goto Lc0
            java.lang.String r10 = "resolveLocationSettingsResultFlow"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r10 = r9
        Lc0:
            kotlinx.coroutines.flow.Flow r10 = (kotlinx.coroutines.flow.Flow) r10
            r0.L$0 = r9
            r0.label = r8
            java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt.first(r10, r0)
            if (r14 != r7) goto Lcd
            return r7
        Lcd:
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r10 = r14.getValue()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCController.m3799checkLocationPreconditionsBWLJW6A(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean clearWatch(String id, boolean addToBlackList) {
        LocationCallback remove = this.locationCallbacks.remove(id);
        if (remove != null) {
            this.helper.removeLocationUpdates$IONGeolocationLib_release(remove);
            return true;
        }
        if (addToBlackList) {
            this.watchIdsBlacklist.add(id);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkWatchInBlackList(String watchId) {
        if (!this.watchIdsBlacklist.contains(watchId)) {
            return false;
        }
        if (!clearWatch(watchId, false)) {
            return true;
        }
        this.watchIdsBlacklist.remove(watchId);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IONGLOCLocationResult toOSLocationResult(Location location) {
        return new IONGLOCLocationResult(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), IONGLOCBuildConfig.INSTANCE.getAndroidSdkVersionCode() >= 26 ? Float.valueOf(location.getVerticalAccuracyMeters()) : null, location.getBearing(), location.getSpeed(), location.getTime());
    }
}
