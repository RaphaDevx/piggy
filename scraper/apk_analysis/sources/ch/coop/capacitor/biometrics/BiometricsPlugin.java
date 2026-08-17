package ch.coop.capacitor.biometrics;

import android.content.pm.PackageManager;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import ch.coop.capacitor.biometrics.BiometricsPlugin;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@CapacitorPlugin(name = "Biometrics")
/* loaded from: classes3.dex */
public class BiometricsPlugin extends Plugin {
    static final String TAG = "BiometricsPlugin";

    public enum BiometryType {
        FACE,
        FINGERPRINT,
        IRIS
    }

    private static int getAllowedAuthenticators(boolean z) {
        return !z ? 33023 : 255;
    }

    @PluginMethod
    public void checkAvailability(PluginCall pluginCall) {
        String mapAndroidErrorCode;
        boolean z = false;
        int canAuthenticate = BiometricManager.from(getContext()).canAuthenticate(getAllowedAuthenticators(pluginCall.getBoolean("disablePasscodeFallback", false).booleanValue()));
        if (canAuthenticate == 0) {
            z = true;
            mapAndroidErrorCode = null;
        } else {
            mapAndroidErrorCode = mapAndroidErrorCode(canAuthenticate);
        }
        JSObject put = new JSObject().put("canAuthenticate", (Object) z).put("biometryTypes", (Object) JSArray.from(getDeviceBiometryTypeStrings().toArray()));
        if (mapAndroidErrorCode != null) {
            put.put("errorCode", mapAndroidErrorCode);
        }
        pluginCall.resolve(put);
    }

    @PluginMethod
    public void authenticate(final PluginCall pluginCall) {
        String string = pluginCall.getString("title");
        if (string == null) {
            pluginCall.reject("Missing 'title' property.", "INVALID_ARGUMENTS");
            return;
        }
        if (string.isEmpty()) {
            pluginCall.reject("Empty 'title' property not allowed.", "INVALID_ARGUMENTS");
            return;
        }
        String string2 = pluginCall.getString("subtitle");
        Boolean bool = pluginCall.getBoolean("disablePasscodeFallback", false);
        final BiometricPrompt biometricPrompt = new BiometricPrompt(getActivity(), ContextCompat.getMainExecutor(getContext()), new BiometricPrompt.AuthenticationCallback(this) { // from class: ch.coop.capacitor.biometrics.BiometricsPlugin.1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence charSequence) {
                super.onAuthenticationError(i, charSequence);
                pluginCall.reject(charSequence.toString(), BiometricsPlugin.mapAndroidErrorCode(i));
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                super.onAuthenticationSucceeded(authenticationResult);
                pluginCall.resolve();
            }
        });
        BiometricPrompt.PromptInfo.Builder allowedAuthenticators = new BiometricPrompt.PromptInfo.Builder().setTitle(string).setAllowedAuthenticators(getAllowedAuthenticators(bool.booleanValue()));
        if (string2 != null) {
            allowedAuthenticators.setSubtitle(string2);
        }
        if (bool.booleanValue()) {
            allowedAuthenticators.setNegativeButtonText(pluginCall.getString("cancelButtonText", "Cancel"));
        }
        final BiometricPrompt.PromptInfo build = allowedAuthenticators.build();
        getActivity().runOnUiThread(new Runnable() { // from class: ch.coop.capacitor.biometrics.BiometricsPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BiometricPrompt.this.authenticate(build);
            }
        });
    }

    private List<String> getDeviceBiometryTypeStrings() {
        return (List) getDeviceBiometryTypes().stream().map(new Function() { // from class: ch.coop.capacitor.biometrics.BiometricsPlugin$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BiometricsPlugin.lambda$getDeviceBiometryTypeStrings$1((BiometricsPlugin.BiometryType) obj);
            }
        }).filter(new Predicate() { // from class: ch.coop.capacitor.biometrics.BiometricsPlugin$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((String) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ String lambda$getDeviceBiometryTypeStrings$1(BiometryType biometryType) {
        int ordinal = biometryType.ordinal();
        if (ordinal == 0) {
            return "FACE";
        }
        if (ordinal == 1) {
            return "FINGERPRINT";
        }
        if (ordinal != 2) {
            return null;
        }
        return "IRIS";
    }

    private List<BiometryType> getDeviceBiometryTypes() {
        PackageManager packageManager = getContext().getPackageManager();
        ArrayList arrayList = new ArrayList();
        if (packageManager.hasSystemFeature("android.hardware.biometrics.face")) {
            arrayList.add(BiometryType.FACE);
        }
        if (packageManager.hasSystemFeature("android.hardware.fingerprint")) {
            arrayList.add(BiometryType.FINGERPRINT);
        }
        if (packageManager.hasSystemFeature("android.hardware.biometrics.iris")) {
            arrayList.add(BiometryType.IRIS);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String mapAndroidErrorCode(int i) {
        switch (i) {
            case -2:
                return "BIOMETRY_INCOMPATIBLE_OPTIONS";
            case -1:
                return "BIOMETRY_STATUS_UNKNOWN";
            case 0:
            case 6:
            default:
                return "UNKNOWN";
            case 1:
                return "BIOMETRY_NOT_AVAILABLE_TEMPORARILY";
            case 2:
                return "UNABLE_TO_PROCESS";
            case 3:
                return "TIMEOUT";
            case 4:
                return "NO_SPACE";
            case 5:
                return "SYSTEM_CANCEL";
            case 7:
                return "LOCKOUT_WAIT";
            case 8:
                return "VENDOR";
            case 9:
                return "LOCKOUT";
            case 10:
            case 13:
                return "USER_CANCEL";
            case 11:
                return "BIOMETRY_NOT_ENROLLED";
            case 12:
                return "BIOMETRY_NOT_AVAILABLE";
            case 14:
                return "PASSCODE_NOT_SET";
            case 15:
                return "SECURITY_UPDATE_REQUIRED";
        }
    }
}
