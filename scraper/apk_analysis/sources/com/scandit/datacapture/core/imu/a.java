package com.scandit.datacapture.core.imu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements SensorEventListener {
    public final WeakReference a;

    public a(ImuDataCollector owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ImuDataCollector imuDataCollector = (ImuDataCollector) this.a.get();
        if (imuDataCollector != null) {
            ImuDataCollector.access$onSensorEvent(imuDataCollector, event);
        }
    }
}
