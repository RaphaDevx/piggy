package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import android.app.Dialog;
import android.os.Looper;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.tealium.library.DataSources;
import io.ionic.libs.iongeolocationlib.model.IONGLOCException;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.sentry.rrweb.RRWebOptionsEvent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IONGLOCServiceHelper.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J#\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0081@¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b J+\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Lio/ionic/libs/iongeolocationlib/controller/IONGLOCServiceHelper;", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/IntentSenderRequest;", "(Lcom/google/android/gms/location/FusedLocationProviderClient;Landroidx/activity/result/ActivityResultLauncher;)V", "checkGooglePlayServicesAvailable", "Lkotlin/Result;", "", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroid/app/Activity;", "checkGooglePlayServicesAvailable-IoAF18A$IONGeolocationLib_release", "(Landroid/app/Activity;)Ljava/lang/Object;", "checkLocationSettings", "", RRWebOptionsEvent.EVENT_TAG, "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;", "interval", "", "checkLocationSettings$IONGeolocationLib_release", "(Landroid/app/Activity;Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentLocation", "Landroid/location/Location;", "getCurrentLocation$IONGeolocationLib_release", "(Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeLocationUpdates", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "removeLocationUpdates$IONGeolocationLib_release", "requestLocationUpdates", "requestLocationUpdates$IONGeolocationLib_release", "sendResultWithGoogleServicesException", "resolvable", "message", "", "sendResultWithGoogleServicesException-gIAlu-s", "(ZLjava/lang/String;)Ljava/lang/Object;", "IONGeolocationLib_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class IONGLOCServiceHelper {
    private final ActivityResultLauncher<IntentSenderRequest> activityLauncher;
    private final FusedLocationProviderClient fusedLocationClient;

    public IONGLOCServiceHelper(FusedLocationProviderClient fusedLocationClient, ActivityResultLauncher<IntentSenderRequest> activityLauncher) {
        Intrinsics.checkNotNullParameter(fusedLocationClient, "fusedLocationClient");
        Intrinsics.checkNotNullParameter(activityLauncher, "activityLauncher");
        this.fusedLocationClient = fusedLocationClient;
        this.activityLauncher = activityLauncher;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkLocationSettings$IONGeolocationLib_release(android.app.Activity r5, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r6, long r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$checkLocationSettings$1
            r0.<init>(r4, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r4 = r0.L$0
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper r4 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper) r4
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            goto L7f
        L2e:
            r4 = move-exception
            goto L84
        L30:
            r5 = move-exception
            goto L8e
        L32:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3a:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.gms.location.LocationRequest$Builder r9 = new com.google.android.gms.location.LocationRequest$Builder
            boolean r6 = r6.getEnableHighAccuracy()
            if (r6 == 0) goto L48
            r6 = 100
            goto L4a
        L48:
            r6 = 102(0x66, float:1.43E-43)
        L4a:
            r9.<init>(r6, r7)
            com.google.android.gms.location.LocationRequest r6 = r9.build()
            java.lang.String r7 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            com.google.android.gms.location.LocationSettingsRequest$Builder r7 = new com.google.android.gms.location.LocationSettingsRequest$Builder
            r7.<init>()
            r7.addLocationRequest(r6)
            com.google.android.gms.location.SettingsClient r5 = com.google.android.gms.location.LocationServices.getSettingsClient(r5)
            java.lang.String r6 = "getSettingsClient(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            com.google.android.gms.location.LocationSettingsRequest r6 = r7.build()     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            com.google.android.gms.tasks.Task r5 = r5.checkLocationSettings(r6)     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            java.lang.String r6 = "checkLocationSettings(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            r0.L$0 = r4     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            r0.label = r3     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            java.lang.Object r5 = kotlinx.coroutines.tasks.TasksKt.await(r5, r0)     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            if (r5 != r1) goto L7f
            return r1
        L7f:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch: java.lang.Exception -> L2e com.google.android.gms.common.api.ResolvableApiException -> L30
            return r4
        L84:
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCSettingsException r5 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCSettingsException
            java.lang.String r6 = "There is an error with the location settings."
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r5.<init>(r6, r4)
            throw r5
        L8e:
            androidx.activity.result.IntentSenderRequest$Builder r6 = new androidx.activity.result.IntentSenderRequest$Builder
            android.app.PendingIntent r5 = r5.getResolution()
            java.lang.String r7 = "getResolution(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
            r6.<init>(r5)
            androidx.activity.result.IntentSenderRequest r5 = r6.build()
            androidx.activity.result.ActivityResultLauncher<androidx.activity.result.IntentSenderRequest> r4 = r4.activityLauncher
            r4.launch(r5)
            r4 = 0
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper.checkLocationSettings$IONGeolocationLib_release(android.app.Activity, io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: checkGooglePlayServicesAvailable-IoAF18A$IONGeolocationLib_release, reason: not valid java name */
    public final Object m3802x6e9c783(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance(...)");
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable != 0) {
            if (googleApiAvailability.isUserResolvableError(isGooglePlayServicesAvailable)) {
                Dialog errorDialog = googleApiAvailability.getErrorDialog(activity, isGooglePlayServicesAvailable, 1);
                if (errorDialog != null) {
                    errorDialog.show();
                }
                return m3801sendResultWithGoogleServicesExceptiongIAlus(true, "Google Play Services error user resolvable.");
            }
            return m3801sendResultWithGoogleServicesExceptiongIAlus(false, "Google Play Services error.");
        }
        Result.Companion companion = Result.INSTANCE;
        return Result.m3945constructorimpl(Unit.INSTANCE);
    }

    /* renamed from: sendResultWithGoogleServicesException-gIAlu-s, reason: not valid java name */
    private final Object m3801sendResultWithGoogleServicesExceptiongIAlus(boolean resolvable, String message) {
        Result.Companion companion = Result.INSTANCE;
        return Result.m3945constructorimpl(ResultKt.createFailure(new IONGLOCException.IONGLOCGoogleServicesException(resolvable, message, null, 4, null)));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentLocation$IONGeolocationLib_release(io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions r8, kotlin.coroutines.Continuation<? super android.location.Location> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1 r0 = (io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1 r0 = new io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper$getCurrentLocation$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlin.ResultKt.throwOnFailure(r9)
            goto L77
        L2b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = new com.google.android.gms.location.CurrentLocationRequest$Builder
            r9.<init>()
            boolean r2 = r8.getEnableHighAccuracy()
            if (r2 == 0) goto L44
            r2 = 100
            goto L46
        L44:
            r2 = 102(0x66, float:1.43E-43)
        L46:
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = r9.setPriority(r2)
            long r5 = r8.getMaximumAge()
            com.google.android.gms.location.CurrentLocationRequest$Builder r9 = r9.setMaxUpdateAgeMillis(r5)
            long r5 = r8.getTimeout()
            com.google.android.gms.location.CurrentLocationRequest$Builder r8 = r9.setDurationMillis(r5)
            com.google.android.gms.location.CurrentLocationRequest r8 = r8.build()
            java.lang.String r9 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            com.google.android.gms.location.FusedLocationProviderClient r7 = r7.fusedLocationClient
            com.google.android.gms.tasks.Task r7 = r7.getCurrentLocation(r8, r4)
            java.lang.String r8 = "getCurrentLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.tasks.TasksKt.await(r7, r0)
            if (r9 != r1) goto L77
            return r1
        L77:
            android.location.Location r9 = (android.location.Location) r9
            if (r9 == 0) goto L7c
            return r9
        L7c:
            io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCLocationRetrievalTimeoutException r7 = new io.ionic.libs.iongeolocationlib.model.IONGLOCException$IONGLOCLocationRetrievalTimeoutException
            java.lang.String r8 = "Location request timed out"
            r9 = 2
            r7.<init>(r8, r4, r9, r4)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper.getCurrentLocation$IONGeolocationLib_release(io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void requestLocationUpdates$IONGeolocationLib_release(IONGLOCLocationOptions options, LocationCallback locationCallback) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(locationCallback, "locationCallback");
        LocationRequest.Builder builder = new LocationRequest.Builder(options.getTimeout());
        builder.setMaxUpdateAgeMillis(options.getMaximumAge());
        builder.setPriority(options.getEnableHighAccuracy() ? 100 : 102);
        if (options.getMinUpdateInterval() != null) {
            builder.setMinUpdateIntervalMillis(options.getMinUpdateInterval().longValue());
        }
        LocationRequest build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.fusedLocationClient.requestLocationUpdates(build, locationCallback, Looper.getMainLooper());
    }

    public final void removeLocationUpdates$IONGeolocationLib_release(LocationCallback locationCallback) {
        Intrinsics.checkNotNullParameter(locationCallback, "locationCallback");
        this.fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
