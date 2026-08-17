package io.ionic.libs.iongeolocationlib.controller;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: IONGLOCController.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/Result;", "", "Lio/ionic/libs/iongeolocationlib/model/IONGLOCLocationResult;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1", f = "IONGLOCController.kt", i = {0}, l = {b.n, 135}, m = "invokeSuspend", n = {"$this$callbackFlow"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class IONGLOCController$addWatch$1 extends SuspendLambda implements Function2<ProducerScope<? super Result<? extends List<? extends IONGLOCLocationResult>>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ IONGLOCLocationOptions $options;
    final /* synthetic */ String $watchId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IONGLOCController$addWatch$1(IONGLOCController iONGLOCController, Activity activity, IONGLOCLocationOptions iONGLOCLocationOptions, String str, Continuation<? super IONGLOCController$addWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = iONGLOCController;
        this.$activity = activity;
        this.$options = iONGLOCLocationOptions;
        this.$watchId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IONGLOCController$addWatch$1 iONGLOCController$addWatch$1 = new IONGLOCController$addWatch$1(this.this$0, this.$activity, this.$options, this.$watchId, continuation);
        iONGLOCController$addWatch$1.L$0 = obj;
        return iONGLOCController$addWatch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super Result<? extends List<? extends IONGLOCLocationResult>>> producerScope, Continuation<? super Unit> continuation) {
        return invoke2((ProducerScope<? super Result<? extends List<IONGLOCLocationResult>>>) producerScope, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(ProducerScope<? super Result<? extends List<IONGLOCLocationResult>>> producerScope, Continuation<? super Unit> continuation) {
        return ((IONGLOCController$addWatch$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.channels.ProducerScope] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        Object m3799checkLocationPreconditionsBWLJW6A;
        final ProducerScope producerScope2;
        Map map;
        IONGLOCServiceHelper iONGLOCServiceHelper;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
        } catch (Exception e) {
            Log.d("IONGeolocationController", "Error requesting location updates: " + e.getMessage());
            Result.Companion companion = Result.INSTANCE;
            r1.mo5440trySendJP2dKIU(Result.m3944boximpl(Result.m3945constructorimpl(ResultKt.createFailure(e))));
            producerScope = r1;
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            this.L$0 = producerScope3;
            this.label = 1;
            m3799checkLocationPreconditionsBWLJW6A = this.this$0.m3799checkLocationPreconditionsBWLJW6A(this.$activity, this.$options, true, this);
            producerScope2 = producerScope3;
            if (m3799checkLocationPreconditionsBWLJW6A == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (r1 != 1) {
                if (r1 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            m3799checkLocationPreconditionsBWLJW6A = ((Result) obj).getValue();
            producerScope2 = producerScope4;
        }
        if (!Result.m3951isFailureimpl(m3799checkLocationPreconditionsBWLJW6A)) {
            map = this.this$0.locationCallbacks;
            String str = this.$watchId;
            final IONGLOCController iONGLOCController = this.this$0;
            final String str2 = this.$watchId;
            LocationCallback locationCallback = new LocationCallback() { // from class: io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1.1
                @Override // com.google.android.gms.location.LocationCallback
                public void onLocationResult(LocationResult location) {
                    boolean checkWatchInBlackList;
                    IONGLOCLocationResult oSLocationResult;
                    Intrinsics.checkNotNullParameter(location, "location");
                    checkWatchInBlackList = IONGLOCController.this.checkWatchInBlackList(str2);
                    if (checkWatchInBlackList) {
                        return;
                    }
                    List<Location> locations = location.getLocations();
                    Intrinsics.checkNotNullExpressionValue(locations, "getLocations(...)");
                    List<Location> list = locations;
                    IONGLOCController iONGLOCController2 = IONGLOCController.this;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (Location location2 : list) {
                        Intrinsics.checkNotNull(location2);
                        oSLocationResult = iONGLOCController2.toOSLocationResult(location2);
                        arrayList.add(oSLocationResult);
                    }
                    ProducerScope<Result<? extends List<IONGLOCLocationResult>>> producerScope5 = producerScope2;
                    Result.Companion companion2 = Result.INSTANCE;
                    producerScope5.mo5440trySendJP2dKIU(Result.m3944boximpl(Result.m3945constructorimpl(arrayList)));
                }
            };
            IONGLOCController iONGLOCController2 = this.this$0;
            IONGLOCLocationOptions iONGLOCLocationOptions = this.$options;
            iONGLOCServiceHelper = iONGLOCController2.helper;
            iONGLOCServiceHelper.requestLocationUpdates$IONGeolocationLib_release(iONGLOCLocationOptions, locationCallback);
            map.put(str, locationCallback);
            producerScope = producerScope2;
        } else {
            Result.Companion companion2 = Result.INSTANCE;
            NullPointerException m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3799checkLocationPreconditionsBWLJW6A);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new NullPointerException();
            }
            producerScope2.mo5440trySendJP2dKIU(Result.m3944boximpl(Result.m3945constructorimpl(ResultKt.createFailure(m3948exceptionOrNullimpl))));
            producerScope = producerScope2;
        }
        final IONGLOCController iONGLOCController3 = this.this$0;
        final String str3 = this.$watchId;
        this.L$0 = null;
        this.label = 2;
        if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: io.ionic.libs.iongeolocationlib.controller.IONGLOCController$addWatch$1.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                IONGLOCController.this.clearWatch(str3);
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
