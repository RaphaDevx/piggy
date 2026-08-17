package com.scandit.datacapture.core.ui.control;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public final /* synthetic */ class k extends FunctionReference implements Function1 {
    public k(Object obj) {
        super(1, obj);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "onFrameSourceStarted";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(TorchSwitchControl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "onFrameSourceStarted(Lcom/scandit/datacapture/core/source/FrameSource;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FrameSource p0 = (FrameSource) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        TorchSwitchControl.access$onFrameSourceStarted((TorchSwitchControl) this.receiver, p0);
        return Unit.INSTANCE;
    }
}
