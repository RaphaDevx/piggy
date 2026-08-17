package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes2.dex */
public final /* synthetic */ class e extends FunctionReference implements Function1 {
    public e() {
        super(1, l.a);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "info";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(l.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "info(Ljava/lang/String;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String p0 = (String) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((l) this.receiver).getClass();
        l.a(p0);
        return Unit.INSTANCE;
    }
}
