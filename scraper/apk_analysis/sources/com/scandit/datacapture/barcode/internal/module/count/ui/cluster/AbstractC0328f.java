package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.view.MotionEvent;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.cluster.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0328f {
    public static boolean a(MotionEvent event, List items, Function1 onCluster) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onCluster, "onCluster");
        int intValue = ((Number) j.h.getValue()).intValue();
        Iterator it = items.iterator();
        while (it.hasNext()) {
            D d = (D) it.next();
            NativeCluster nativeCluster = d.a;
            if (nativeCluster != null) {
                float f = intValue;
                if (event.getX() >= d.d.getX() - f && event.getX() <= d.d.getX() + f && event.getY() >= d.d.getY() - f && event.getY() <= d.d.getY() + f) {
                    onCluster.invoke(nativeCluster);
                    return true;
                }
            }
        }
        return false;
    }
}
