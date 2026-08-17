package com.scandit.datacapture.barcode.internal.module.spark.internal;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class b implements x, w {
    public static final /* synthetic */ KProperty[] c = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(b.class, "value", "getValue()Ljava/lang/Object;", 0)};
    public final a a;
    public final ArrayList b;

    public b(Object obj) {
        Delegates delegates = Delegates.INSTANCE;
        this.a = new a(obj, this);
        this.b = new ArrayList();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.x
    public final void a(Object obj) {
        this.a.setValue(this, c[0], obj);
    }

    public final f a(x listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.b.contains(listener)) {
            return null;
        }
        this.b.add(listener);
        listener.a(this.a.getValue(this, c[0]));
        return new f(new e(this, listener));
    }
}
