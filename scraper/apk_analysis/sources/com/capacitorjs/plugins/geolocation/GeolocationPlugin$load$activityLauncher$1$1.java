package com.capacitorjs.plugins.geolocation;

import androidx.activity.result.ActivityResult;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.ionic.libs.iongeolocationlib.controller.IONGLOCController;
import kotlin.Metadata;
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
@DebugMetadata(c = "com.capacitorjs.plugins.geolocation.GeolocationPlugin$load$activityLauncher$1$1", f = "GeolocationPlugin.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class GeolocationPlugin$load$activityLauncher$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ActivityResult $result;
    int label;
    final /* synthetic */ GeolocationPlugin this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeolocationPlugin$load$activityLauncher$1$1(GeolocationPlugin geolocationPlugin, ActivityResult activityResult, Continuation<? super GeolocationPlugin$load$activityLauncher$1$1> continuation) {
        super(2, continuation);
        this.this$0 = geolocationPlugin;
        this.$result = activityResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeolocationPlugin$load$activityLauncher$1$1(this.this$0, this.$result, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeolocationPlugin$load$activityLauncher$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IONGLOCController iONGLOCController;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            iONGLOCController = this.this$0.controller;
            if (iONGLOCController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
                iONGLOCController = null;
            }
            this.label = 1;
            if (iONGLOCController.onResolvableExceptionResult(this.$result.getResultCode(), this) == coroutine_suspended) {
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
