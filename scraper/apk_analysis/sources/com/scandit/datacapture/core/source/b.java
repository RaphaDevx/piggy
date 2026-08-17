package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public final /* synthetic */ class b extends FunctionReference implements Function1 {
    public b(com.scandit.datacapture.core.internal.module.source.camera.behavior.c cVar) {
        super(1, cVar);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "applyResolution";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CameraBehaviorDelegate.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "applyResolution(Ljava/lang/String;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String p0 = (String) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((CameraBehaviorDelegate) this.receiver).a(p0);
        return Unit.INSTANCE;
    }
}
