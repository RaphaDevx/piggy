package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class e extends FunctionReferenceImpl implements Function1 {
    public static final e a = new e();

    public e() {
        super(1, ToteView.class, "showSub", "showSub()V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ToteView p0 = (ToteView) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        p0.showSub();
        return Unit.INSTANCE;
    }
}
