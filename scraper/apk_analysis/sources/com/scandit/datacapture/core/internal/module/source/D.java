package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.source.CameraPosition;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public final class D {
    public final LinkedHashMap a = new LinkedHashMap();

    public static B a(CameraPosition cameraPosition) {
        int i = C.a[cameraPosition.ordinal()];
        if (i == 1) {
            return C0606z.a;
        }
        if (i == 2) {
            return A.a;
        }
        throw new IllegalStateException(("Unsupported camera position " + cameraPosition).toString());
    }
}
