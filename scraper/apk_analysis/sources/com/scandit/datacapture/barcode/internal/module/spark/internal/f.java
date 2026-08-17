package com.scandit.datacapture.barcode.internal.module.spark.internal;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.TypeIntrinsics;

/* loaded from: classes2.dex */
public final class f implements d {
    public e a;

    public f(e eVar) {
        this.a = eVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.d
    public final void a() {
        w wVar;
        e eVar = this.a;
        x xVar = eVar != null ? eVar.b : null;
        if (eVar != null && (wVar = eVar.a) != null) {
            b bVar = (b) wVar;
            if (CollectionsKt.contains(bVar.b, xVar)) {
                TypeIntrinsics.asMutableCollection(bVar.b).remove(xVar);
            }
        }
        this.a = null;
    }
}
