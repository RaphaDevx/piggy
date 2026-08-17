package com.scandit.datacapture.barcode.ar.ui;

import android.content.Context;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.ui.control.CameraSwitchControl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ BarcodeArView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(BarcodeArView barcodeArView) {
        super(1);
        this.a = barcodeArView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Camera camera;
        BarcodeArViewSettings barcodeArViewSettings;
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        camera = this.a.c;
        Camera.Companion companion = Camera.INSTANCE;
        barcodeArViewSettings = this.a.b;
        Camera camera2 = companion.getCamera(BarcodeArView$controlsHandler$1$WhenMappings.$EnumSwitchMapping$0[barcodeArViewSettings.getDefaultCameraPosition().ordinal()] == 1 ? CameraPosition.WORLD_FACING : CameraPosition.USER_FACING, BarcodeAr.INSTANCE.createRecommendedCameraSettings());
        if (camera == null || camera2 == null) {
            return null;
        }
        return new CameraSwitchControl(it, camera, camera2);
    }
}
