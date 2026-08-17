package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Rational;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j0 implements CameraApi2Info {
    public final String a;
    public final M b;
    public final int c;
    public final Rational d;

    public j0(String id, CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        this.a = id;
        M m = new M(cameraCharacteristics);
        this.b = m;
        this.c = m.getLensFacing();
        this.d = m.getControlAeCompensationStep();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final CameraCharacteristicsWrapper getCharacteristics() {
        return this.b;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final Rational getCompensationStep() {
        return this.d;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final int getFacing() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final int getFocusCalibration() {
        Integer focusCalibration = this.b.getFocusCalibration();
        if (focusCalibration == null || focusCalibration.intValue() != 0) {
            if (focusCalibration != null && focusCalibration.intValue() == 1) {
                return 2;
            }
            if (focusCalibration != null && focusCalibration.intValue() == 2) {
                return 3;
            }
        }
        return 0;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final String getId() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info
    public final boolean isTimestampRealtime() {
        M m = this.b;
        CameraCharacteristics.Key key = CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE;
        Intrinsics.checkNotNullExpressionValue(key, "SENSOR_INFO_TIMESTAMP_SOURCE");
        m.getClass();
        Intrinsics.checkNotNullParameter(key, "key");
        Integer num = (Integer) m.a.get(key);
        return num != null && num.intValue() == 1;
    }
}
