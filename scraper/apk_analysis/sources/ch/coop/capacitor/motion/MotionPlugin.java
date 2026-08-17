package ch.coop.capacitor.motion;

import android.hardware.SensorManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.squareup.seismic.ShakeDetector;

@CapacitorPlugin(name = TypedValues.MotionType.NAME)
/* loaded from: classes3.dex */
public class MotionPlugin extends Plugin implements ShakeDetector.Listener {
    private static final String EVENT_NAME_SHAKE = "shake";
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    @Override // com.getcapacitor.Plugin
    @PluginMethod(returnType = "none")
    public void addListener(PluginCall pluginCall) {
        super.addListener(pluginCall);
        if (hasListeners(EVENT_NAME_SHAKE) && this.shakeDetector == null) {
            startShakeDetector();
        }
    }

    @Override // com.getcapacitor.Plugin
    public void removeListener(PluginCall pluginCall) {
        super.removeListener(pluginCall);
        removeShakeDetectorIfNotNeeded();
    }

    @Override // com.getcapacitor.Plugin
    public void removeAllListeners(PluginCall pluginCall) {
        super.removeAllListeners(pluginCall);
        removeShakeDetectorIfNotNeeded();
    }

    @Override // com.squareup.seismic.ShakeDetector.Listener
    public void hearShake() {
        notifyListeners(EVENT_NAME_SHAKE, null);
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnPause() {
        super.handleOnPause();
        ShakeDetector shakeDetector = this.shakeDetector;
        if (shakeDetector != null) {
            shakeDetector.stop();
        }
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnResume() {
        super.handleOnResume();
        if (this.shakeDetector != null) {
            startShakeDetector();
        }
    }

    private void startShakeDetector() {
        if (this.sensorManager == null) {
            this.sensorManager = (SensorManager) getContext().getSystemService("sensor");
        }
        if (this.shakeDetector == null) {
            this.shakeDetector = new ShakeDetector(this);
        }
        this.shakeDetector.start(this.sensorManager, 1);
    }

    private void removeShakeDetectorIfNotNeeded() {
        ShakeDetector shakeDetector;
        if (hasListeners(EVENT_NAME_SHAKE) || (shakeDetector = this.shakeDetector) == null) {
            return;
        }
        shakeDetector.stop();
        this.shakeDetector = null;
    }
}
