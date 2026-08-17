package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class K extends ObservableProperty {
    public final /* synthetic */ N a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(Integer num, N n) {
        super(num);
        this.a = n;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        int i;
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(obj, obj2)) {
            return;
        }
        N n = this.a;
        Integer num = (Integer) n.n.getValue(n, N.q[0]);
        if (num != null) {
            i = num.intValue();
        } else {
            NativeColor defaultToolbarBackgroundColor = NativeSparkScanViewDefaults.defaultToolbarBackgroundColor();
            Intrinsics.checkNotNullExpressionValue(defaultToolbarBackgroundColor, "defaultToolbarBackgroundColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultToolbarBackgroundColor);
        }
        n.setBackgroundColor(i);
    }
}
