package com.scandit.datacapture.core.imu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.capture.NativeInertialMeasurementType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/core/imu/ImuDataCollector;", "", "Lcom/scandit/datacapture/core/imu/ImuDataCollector$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "<init>", "(Lcom/scandit/datacapture/core/imu/ImuDataCollector$Listener;)V", "", "enable", "", "setEnabled", "(Z)V", "Listener", "com/scandit/datacapture/core/imu/a", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ImuDataCollector {
    private final Listener a;
    private final SensorManager b;
    private final List c;
    private final a d;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/core/imu/ImuDataCollector$Listener;", "", "onMeasurement", "", "measurement", "Lcom/scandit/datacapture/core/imu/ImuMeasurement;", "onRotationMeasurement", "Lcom/scandit/datacapture/core/imu/ImuRotationMeasurement;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Listener {
        void onMeasurement(ImuMeasurement measurement);

        void onRotationMeasurement(ImuRotationMeasurement measurement);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList] */
    public ImuDataCollector(Listener listener) {
        ?? emptyList;
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = listener;
        SensorManager sensorManager = (SensorManager) AppAndroidEnvironment.INSTANCE.getApplicationContext().getSystemService(SensorManager.class);
        this.b = sensorManager;
        if (sensorManager != null) {
            List mutableListOf = CollectionsKt.mutableListOf(1, 4, 9, 15);
            emptyList = new ArrayList();
            Iterator it = mutableListOf.iterator();
            while (it.hasNext()) {
                Sensor defaultSensor = sensorManager.getDefaultSensor(((Number) it.next()).intValue());
                if (defaultSensor != null) {
                    emptyList.add(defaultSensor);
                }
            }
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        this.c = emptyList;
        this.d = new a(this);
    }

    public static final void access$onSensorEvent(ImuDataCollector imuDataCollector, SensorEvent sensorEvent) {
        imuDataCollector.getClass();
        long j = sensorEvent.timestamp;
        int type = sensorEvent.sensor.getType();
        int i = 0;
        if (type == 1) {
            Listener listener = imuDataCollector.a;
            NativeInertialMeasurementType nativeInertialMeasurementType = NativeInertialMeasurementType.ACCELEROMETER;
            float[] fArr = new float[3];
            for (int i2 = 0; i2 < 3; i2++) {
                fArr[i2] = sensorEvent.values[i2];
            }
            Intrinsics.checkNotNullParameter(fArr, "<this>");
            float[] fArr2 = new float[3];
            while (i < 3) {
                fArr2[i] = fArr[i] / (-9.81f);
                i++;
            }
            listener.onMeasurement(new ImuMeasurement(nativeInertialMeasurementType, fArr2, j));
            return;
        }
        if (type == 4) {
            Listener listener2 = imuDataCollector.a;
            NativeInertialMeasurementType nativeInertialMeasurementType2 = NativeInertialMeasurementType.GYROSCOPE;
            float[] fArr3 = new float[3];
            while (i < 3) {
                fArr3[i] = sensorEvent.values[i];
                i++;
            }
            listener2.onMeasurement(new ImuMeasurement(nativeInertialMeasurementType2, fArr3, j));
            return;
        }
        if (type != 9) {
            if (type != 15) {
                return;
            }
            Listener listener3 = imuDataCollector.a;
            float[] fArr4 = sensorEvent.values;
            listener3.onRotationMeasurement(new ImuRotationMeasurement(new float[]{fArr4[3], fArr4[0], fArr4[1], fArr4[2]}, j));
            return;
        }
        Listener listener4 = imuDataCollector.a;
        NativeInertialMeasurementType nativeInertialMeasurementType3 = NativeInertialMeasurementType.GRAVITY;
        float[] fArr5 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr5[i3] = sensorEvent.values[i3];
        }
        Intrinsics.checkNotNullParameter(fArr5, "<this>");
        float[] fArr6 = new float[3];
        while (i < 3) {
            fArr6[i] = fArr5[i] / (-9.81f);
            i++;
        }
        listener4.onMeasurement(new ImuMeasurement(nativeInertialMeasurementType3, fArr6, j));
    }

    public final void setEnabled(boolean enable) {
        if (enable) {
            for (Sensor sensor : this.c) {
                SensorManager sensorManager = this.b;
                if (sensorManager != null) {
                    sensorManager.registerListener(this.d, sensor, com.salesforce.marketingcloud.config.a.n, 0);
                }
            }
            return;
        }
        for (Sensor sensor2 : this.c) {
            SensorManager sensorManager2 = this.b;
            if (sensorManager2 != null) {
                sensorManager2.unregisterListener(this.d, sensor2);
            }
        }
    }
}
