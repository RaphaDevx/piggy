package com.scandit.datacapture.barcode.internal.module.spark.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class c implements d {
    public final ArrayList a = new ArrayList();

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.d
    public final void a() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a();
        }
        this.a.clear();
    }
}
