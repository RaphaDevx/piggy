package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicInteger;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class x extends l implements SensorEventListener {
    private static AtomicInteger j = new AtomicInteger(0);
    private Sensor b;
    private SensorManager c;
    private JSONObject d;
    private JSONArray e;
    private Handler f;
    private JSONArray g;
    private int h;
    private long i = 0;

    x(Context context, Handler handler, int i) {
        this.f = handler;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.c = sensorManager;
        this.h = i;
        this.b = sensorManager.getDefaultSensor(i);
    }

    private void a(SensorManager sensorManager) {
        AtomicInteger atomicInteger;
        try {
            if (this.b == null || (atomicInteger = j) == null || atomicInteger.get() >= 120) {
                return;
            }
            sensorManager.registerListener(this, this.b, 50000, this.f);
            j.getAndIncrement();
            JSONObject a = o.a(this.d, o.a(this.b));
            this.d = a;
            if (this.h == 1) {
                a.put(g.a.SENSOR_TYPE.toString(), g.l.AC.toString());
            }
            if (this.h == 4) {
                this.d.put(g.a.SENSOR_TYPE.toString(), g.l.GY.toString());
            }
            if (this.h == 2) {
                this.d.put(g.a.SENSOR_TYPE.toString(), g.l.MG.toString());
            }
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
    }

    private void b(SensorManager sensorManager) {
        a(sensorManager);
    }

    private void c(SensorManager sensorManager) {
        sensorManager.unregisterListener(this, this.b);
        AtomicInteger atomicInteger = j;
        if (atomicInteger == null || atomicInteger.get() <= 0) {
            return;
        }
        j.getAndDecrement();
    }

    private void e() {
        try {
            this.d.put(g.a.SENSOR_PAYLOAD.toString(), this.g);
            this.e.put(this.d);
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void c() {
        this.d = new JSONObject();
        this.g = new JSONArray();
        this.e = new JSONArray();
        b();
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void d() {
        b(this.c);
    }

    public JSONObject f() {
        if (this.b == null) {
            return new JSONObject();
        }
        c(this.c);
        e();
        return this.d;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.i <= 25 || this.g.length() >= 150) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(String.valueOf(sensorEvent.values[0]));
        jSONArray.put(String.valueOf(sensorEvent.values[1]));
        jSONArray.put(String.valueOf(sensorEvent.values[2]));
        jSONArray.put(currentTimeMillis);
        this.g.put(jSONArray);
        this.i = currentTimeMillis;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f == null) {
            return;
        }
        d();
    }
}
