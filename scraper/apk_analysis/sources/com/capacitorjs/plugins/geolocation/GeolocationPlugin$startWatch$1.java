package com.capacitorjs.plugins.geolocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationOptions;
import io.ionic.libs.iongeolocationlib.model.IONGLOCLocationResult;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: GeolocationPlugin.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$startWatch$1", f = "GeolocationPlugin.kt", i = {}, l = {232}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class GeolocationPlugin$startWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PluginCall $call;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$startWatch$1(PluginCall pluginCall, GeolocationPlugin geolocationPlugin, Continuation<? super GeolocationPlugin$startWatch$1> continuation) {
        super(2, continuation);
        this.$call = pluginCall;
        this.this$0 = geolocationPlugin;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$startWatch$1(this.$call, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$startWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IONGLOCLocationOptions createOptions;
        IONGLOCController iONGLOCController;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String callbackId = this.$call.getCallbackId();
            createOptions = this.this$0.createOptions(this.$call);
            iONGLOCController = this.this$0.controller;
            if (iONGLOCController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                iONGLOCController = null;
            }
            AppCompatActivity activity = this.this$0.getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            Intrinsics.checkNotNull(callbackId);
            Flow<Result<List<IONGLOCLocationResult>>> addWatch = iONGLOCController.addWatch(activity, createOptions, callbackId);
            final GeolocationPlugin geolocationPlugin = this.this$0;
            final PluginCall pluginCall = this.$call;
            this.label = 1;
            if (addWatch.collect(new FlowCollector() { // from class: com.capacitorjs.plugins.geolocation.GeolocationPlugin$startWatch$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation<? super Unit> continuation) {
                    JSObject jSObjectForLocation;
                    Result result = (Result) obj2;
                    Object value = result.getValue();
                    GeolocationPlugin geolocationPlugin2 = GeolocationPlugin.this;
                    PluginCall pluginCall2 = pluginCall;
                    if (Result.m3952isSuccessimpl(value)) {
                        Iterator<T> it = ((List) value).iterator();
                        while (it.hasNext()) {
                            jSObjectForLocation = geolocationPlugin2.getJSObjectForLocation((IONGLOCLocationResult) it.next());
                            geolocationPlugin2.sendSuccess(pluginCall2, jSObjectForLocation, Boxing.boxBoolean(true));
                        }
                    }
                    Object value2 = result.getValue();
                    GeolocationPlugin geolocationPlugin3 = GeolocationPlugin.this;
                    PluginCall pluginCall3 = pluginCall;
                    Throwable m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(value2);
                    if (m3948exceptionOrNullimpl != null) {
                        geolocationPlugin3.onLocationError(m3948exceptionOrNullimpl, pluginCall3);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
