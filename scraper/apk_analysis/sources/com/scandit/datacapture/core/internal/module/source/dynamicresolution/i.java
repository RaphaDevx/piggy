package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public final /* synthetic */ class i extends FunctionReference implements Function3 {
    public i(Object obj) {
        super(3, obj);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "stop";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(j.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "stop(Lcom/scandit/datacapture/core/internal/module/source/dynamicresolution/DynamicResolutionSettings;Lcom/scandit/datacapture/core/source/FrameSource;Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        j jVar = (j) this.receiver;
        g gVar = jVar.j;
        if (gVar != null) {
            gVar.cancel();
            jVar.j = null;
        }
        ((b) jVar.d).b.set(false);
        ((b) jVar.d).a();
        return Unit.INSTANCE;
    }
}
