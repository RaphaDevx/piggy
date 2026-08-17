package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.oneplus;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.source.O;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final a d = new a();
    public static final String e = "CPH2415|CPH2413|CPH2417";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range b(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        ArrayList arrayList = new ArrayList();
        for (Range range : frameRateRanges) {
            Integer num = (Integer) range.getUpper();
            if (num == null || num.intValue() != 24) {
                arrayList.add(range);
            }
        }
        return O.b((Range[]) arrayList.toArray(new Range[0]), f);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
