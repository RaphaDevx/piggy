package io.ionic.libs.iongeolocationlib.controller;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.rrweb.RRWebOptionsEvent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: IONGLOCController.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController", f = "IONGLOCController.kt", i = {0, 0, 1}, l = {53, 59}, m = "getCurrentPosition-0E7RQCE", n = {"this", RRWebOptionsEvent.EVENT_TAG, "this"}, s = {"L$0", "L$1", "L$0"})
/* loaded from: classes2.dex */
final class IONGLOCController$getCurrentPosition$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IONGLOCController$getCurrentPosition$1(IONGLOCController iONGLOCController, Continuation<? super IONGLOCController$getCurrentPosition$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m3800getCurrentPosition0E7RQCE = this.this$0.m3800getCurrentPosition0E7RQCE(null, null, this);
        return m3800getCurrentPosition0E7RQCE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m3800getCurrentPosition0E7RQCE : Result.m3944boximpl(m3800getCurrentPosition0E7RQCE);
    }
}
