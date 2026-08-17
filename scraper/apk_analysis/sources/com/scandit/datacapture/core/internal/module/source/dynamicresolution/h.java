package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.source.FrameSource;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public final /* synthetic */ class h extends FunctionReference implements Function3 {
    public h(Object obj) {
        super(3, obj);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "start";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(j.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "start(Lcom/scandit/datacapture/core/internal/module/source/dynamicresolution/DynamicResolutionSettings;Lcom/scandit/datacapture/core/source/FrameSource;Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        m p0 = (m) obj;
        FrameSource p1 = (FrameSource) obj2;
        DataCaptureContext p2 = (DataCaptureContext) obj3;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        j jVar = (j) this.receiver;
        ((b) jVar.d).b.set(false);
        ((b) jVar.d).a();
        ((b) jVar.d).b.set(true);
        g gVar = jVar.j;
        if (gVar != null) {
            gVar.cancel();
            jVar.j = null;
        }
        l lVar = l.a;
        l.b("Checking " + p0.d + " resolution");
        long j = (long) p0.c;
        g gVar2 = new g(jVar, p0);
        jVar.i.schedule(gVar2, j);
        jVar.j = gVar2;
        return Unit.INSTANCE;
    }
}
