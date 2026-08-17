package ch.coop.capacitor.devicepro;

import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Vibrator;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.location.LocationManagerCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import io.sentry.ProfilingTraceData;
import io.sentry.protocol.Request;
import java.util.Locale;

@CapacitorPlugin(name = "DevicePro")
/* loaded from: classes3.dex */
public class DeviceProPlugin extends Plugin {
    @PluginMethod
    public void modelIdentifier(PluginCall pluginCall) {
        String str = Build.MODEL;
        JSObject jSObject = new JSObject();
        jSObject.put("modelIdentifier", str);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void ringerMode(PluginCall pluginCall) {
        String str;
        AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
        if (audioManager == null) {
            pluginCall.reject("Could not get system service 'AUDIO_SERVICE'");
            return;
        }
        int ringerMode = audioManager.getRingerMode();
        if (ringerMode == 0) {
            str = NotificationCompat.GROUP_KEY_SILENT;
        } else if (ringerMode == 1) {
            str = "vibrate";
        } else if (ringerMode != 2) {
            str = Request.JsonKeys.OTHER;
        } else {
            str = ProfilingTraceData.TRUNCATION_REASON_NORMAL;
        }
        JSObject jSObject = new JSObject();
        jSObject.put("ringerMode", str);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void canVibrate(PluginCall pluginCall) {
        Vibrator vibrator = (Vibrator) getContext().getSystemService("vibrator");
        if (vibrator == null) {
            pluginCall.reject("Could not get system service 'VIBRATOR_SERVICE'");
            return;
        }
        JSObject jSObject = new JSObject();
        jSObject.put("canVibrate", vibrator.hasVibrator());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getLanguageCode(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", Locale.getDefault().getLanguage());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getGeolocationPermissionDetails(PluginCall pluginCall) {
        LocationManager locationManager = (LocationManager) getContext().getSystemService("location");
        JSObject jSObject = new JSObject();
        jSObject.put("device", LocationManagerCompat.isLocationEnabled(locationManager));
        jSObject.put("coarse", isPermissionGranted("android.permission.ACCESS_COARSE_LOCATION"));
        jSObject.put("fine", isPermissionGranted("android.permission.ACCESS_FINE_LOCATION"));
        jSObject.put("background", isPermissionGranted("android.permission.ACCESS_BACKGROUND_LOCATION"));
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void isLowPowerModeEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("isLowPowerModeEnabled", false);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void badgeEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("badgeEnabled", false);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getNotificationSettings(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("authorizationStatus", "unknown");
        jSObject.put("alertSetting", "unknown");
        jSObject.put("lockScreenSetting", "unknown");
        jSObject.put("notificationCenter", "unknown");
        jSObject.put("soundSetting", "unknown");
        jSObject.put("alertStyle", "unknown");
        jSObject.put("showPreviewSetting", "unknown");
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getTrackingTransparency(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("status", "unknown");
        pluginCall.resolve(jSObject);
    }

    private boolean isPermissionGranted(String str) {
        return ContextCompat.checkSelfPermission(getContext(), str) == 0;
    }
}
