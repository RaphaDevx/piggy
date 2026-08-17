package io.ionic.libs.iongeolocationlib.controller;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: IONGLOCServiceHelper.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "io.ionic.libs.iongeolocationlib.controller.IONGLOCServiceHelper", f = "IONGLOCServiceHelper.kt", i = {}, l = {133}, m = "getCurrentLocation$IONGeolocationLib_release", n = {}, s = {})
/* loaded from: classes2.dex */
final class IONGLOCServiceHelper$getCurrentLocation$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IONGLOCServiceHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IONGLOCServiceHelper$getCurrentLocation$1(IONGLOCServiceHelper iONGLOCServiceHelper, Continuation<? super IONGLOCServiceHelper$getCurrentLocation$1> continuation) {
        super(continuation);
        this.this$0 = iONGLOCServiceHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getCurrentLocation$IONGeolocationLib_release(null, this);
    }
}
