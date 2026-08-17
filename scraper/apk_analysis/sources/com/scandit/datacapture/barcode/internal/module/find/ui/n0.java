package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class n0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ List b;
    public final /* synthetic */ float c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(u0 u0Var, List list, float f) {
        super(1);
        this.a = u0Var;
        this.b = list;
        this.c = f;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.find.ui.camera.e it = (com.scandit.datacapture.barcode.internal.module.find.ui.camera.e) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.find.ui.camera.e eVar = this.a.j;
        List zoomLevels = this.b;
        float f = this.c;
        eVar.getClass();
        Intrinsics.checkNotNullParameter(zoomLevels, "zoomLevels");
        eVar.a = CollectionsKt.sorted(CollectionsKt.distinct(zoomLevels));
        if (eVar.b != f) {
            eVar.b = f;
            eVar.a();
        }
        u0 u0Var = this.a;
        if (!u0Var.n) {
            u0Var.n = true;
            ViewExtensionsKt.runOnMainThread(u0Var.j, new i0(u0Var));
        }
        return Unit.INSTANCE;
    }
}
