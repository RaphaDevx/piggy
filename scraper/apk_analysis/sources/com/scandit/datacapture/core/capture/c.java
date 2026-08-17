package com.scandit.datacapture.core.capture;

import com.scandit.datacapture.core.imu.ImuDataCollector;
import com.scandit.datacapture.core.imu.ImuMeasurement;
import com.scandit.datacapture.core.imu.ImuRotationMeasurement;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements ImuDataCollector.Listener {
    public final WeakReference a;

    public c(DataCaptureContext owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.imu.ImuDataCollector.Listener
    public final void onMeasurement(ImuMeasurement measurement) {
        NativeDataCaptureContext a;
        Intrinsics.checkNotNullParameter(measurement, "measurement");
        DataCaptureContext dataCaptureContext = (DataCaptureContext) this.a.get();
        if (dataCaptureContext == null || (a = dataCaptureContext.getA()) == null) {
            return;
        }
        a.addInertialMeasurementAsyncAndroid(measurement.toNativeMeasurement(), measurement.getType());
    }

    @Override // com.scandit.datacapture.core.imu.ImuDataCollector.Listener
    public final void onRotationMeasurement(ImuRotationMeasurement measurement) {
        NativeDataCaptureContext a;
        Intrinsics.checkNotNullParameter(measurement, "measurement");
        DataCaptureContext dataCaptureContext = (DataCaptureContext) this.a.get();
        if (dataCaptureContext == null || (a = dataCaptureContext.getA()) == null) {
            return;
        }
        a.addRotationVectorAsyncAndroid(measurement.toNativeRotationMeasurement());
    }
}
