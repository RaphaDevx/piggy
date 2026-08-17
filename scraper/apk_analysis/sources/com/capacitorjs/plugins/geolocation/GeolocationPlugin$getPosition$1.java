package com.capacitorjs.plugins.geolocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GeolocationPlugin.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$getPosition$1", f = "GeolocationPlugin.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class GeolocationPlugin$getPosition$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$getPosition$1(GeolocationPlugin geolocationPlugin, PluginCall pluginCall, Continuation<? super GeolocationPlugin$getPosition$1> continuation) {
        super(2, continuation);
        this.this$0 = geolocationPlugin;
        this.$call = pluginCall;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$getPosition$1(this.this$0, this.$call, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$getPosition$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IONGLOCLocationOptions createOptions;
        IONGLOCController iONGLOCController;
        Object m3800getCurrentPosition0E7RQCE;
        JSObject jSObjectForLocation;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            createOptions = this.this$0.createOptions(this.$call);
            iONGLOCController = this.this$0.controller;
            if (iONGLOCController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                iONGLOCController = null;
            }
            AppCompatActivity activity = this.this$0.getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            this.label = 1;
            m3800getCurrentPosition0E7RQCE = iONGLOCController.m3800getCurrentPosition0E7RQCE(activity, createOptions, this);
            if (m3800getCurrentPosition0E7RQCE == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            m3800getCurrentPosition0E7RQCE = ((Result) obj).getValue();
        }
        GeolocationPlugin geolocationPlugin = this.this$0;
        PluginCall pluginCall = this.$call;
        if (Result.m3952isSuccessimpl(m3800getCurrentPosition0E7RQCE)) {
            jSObjectForLocation = geolocationPlugin.getJSObjectForLocation((IONGLOCLocationResult) m3800getCurrentPosition0E7RQCE);
            GeolocationPlugin.sendSuccess$default(geolocationPlugin, pluginCall, jSObjectForLocation, null, 2, null);
        }
        GeolocationPlugin geolocationPlugin2 = this.this$0;
        PluginCall pluginCall2 = this.$call;
        Throwable m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3800getCurrentPosition0E7RQCE);
        if (m3948exceptionOrNullimpl != null) {
            geolocationPlugin2.onLocationError(m3948exceptionOrNullimpl, pluginCall2);
        }
        return Unit.INSTANCE;
    }
}
