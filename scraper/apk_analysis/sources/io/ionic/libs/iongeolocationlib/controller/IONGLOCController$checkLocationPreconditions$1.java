package io.ionic.libs.iongeolocationlib.controller;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: IONGLOCController.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCController", f = "IONGLOCController.kt", i = {0}, l = {173, 182}, m = "checkLocationPreconditions-BWLJW6A", n = {"this"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class IONGLOCController$checkLocationPreconditions$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IONGLOCController$checkLocationPreconditions$1(IONGLOCController iONGLOCController, Continuation<? super IONGLOCController$checkLocationPreconditions$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m3799checkLocationPreconditionsBWLJW6A;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m3799checkLocationPreconditionsBWLJW6A = this.this$0.m3799checkLocationPreconditionsBWLJW6A(null, null, false, this);
        return m3799checkLocationPreconditionsBWLJW6A == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m3799checkLocationPreconditionsBWLJW6A : Result.m3944boximpl(m3799checkLocationPreconditionsBWLJW6A);
    }
}
