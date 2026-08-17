package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.s, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0315s extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0315s(C0316t c0316t) {
        super(0);
        this.a = c0316t;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        L l;
        for (Map.Entry entry : O.c().entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            com.scandit.datacapture.barcode.internal.module.count.ui.status.b statusInternal = (com.scandit.datacapture.barcode.internal.module.count.ui.status.b) entry.getValue();
            if (!Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a)) {
                if ((Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a) ? true : statusInternal instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e) && (l = (L) this.a.r.get(Integer.valueOf(intValue))) != null) {
                    Intrinsics.checkNotNullParameter(statusInternal, "statusInternal");
                    l.f = statusInternal;
                    com.scandit.datacapture.barcode.internal.module.count.ui.status.m mVar = l.c;
                    mVar.getClass();
                    Intrinsics.checkNotNullParameter(statusInternal, "statusInternal");
                    com.scandit.datacapture.barcode.internal.module.count.ui.status.m.e.runOnMainThread(new com.scandit.datacapture.barcode.internal.module.count.ui.status.g(mVar, statusInternal));
                    l.a(true);
                }
            }
        }
        this.a.q.a();
        return Unit.INSTANCE;
    }
}
