package com.scandit.datacapture.barcode.internal.module.ar.ui;

import android.content.Context;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.ui.control.CameraSwitchControl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ Function1 a;
    public final /* synthetic */ d b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(com.scandit.datacapture.barcode.ar.ui.a aVar, d dVar) {
        super(0);
        this.a = aVar;
        this.b = dVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function1 function1 = this.a;
        Context context = this.b.b.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        CameraSwitchControl cameraSwitchControl = (CameraSwitchControl) function1.invoke(context);
        if (cameraSwitchControl == null) {
            return null;
        }
        cameraSwitchControl.setPrimaryCameraImage(R.drawable.sc_barcode_ar_camera_switch_control_primary_camera);
        cameraSwitchControl.setPrimaryCameraPressedImage(R.drawable.sc_barcode_ar_camera_switch_control_primary_camera_pressed);
        cameraSwitchControl.setSecondaryCameraImage(R.drawable.sc_barcode_ar_camera_switch_control_secondary_camera);
        cameraSwitchControl.setSecondaryCameraPressedImage(R.drawable.sc_barcode_ar_camera_switch_control_secondary_camera_pressed);
        return cameraSwitchControl;
    }
}
