package ch.coop.capacitor.auth;

import androidx.appcompat.app.AppCompatActivity;
import ch.coop.auth.Auth;
import ch.coop.auth.AuthConfiguration;
import ch.coop.auth.AuthConfigurationUIOptions;
import ch.coop.auth.AuthConfigurationUIOptionsAlert;
import ch.coop.auth.FontDefinition;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import net.openid.appauth.ResponseTypeValues;

@CapacitorPlugin(name = "Auth")
/* loaded from: classes3.dex */
public class AuthPlugin extends Plugin {
    public Auth getAuth() {
        return Auth.getInstance(getContext());
    }

    public AppCompatActivity getAppActivity() {
        return getActivity();
    }

    @PluginMethod
    public void configure(PluginCall pluginCall) {
        String string = pluginCall.getString("redirectUri");
        String string2 = pluginCall.getString("postLogoutRedirectUri");
        String string3 = pluginCall.getString("scope");
        String string4 = pluginCall.getString("clientId");
        String string5 = pluginCall.getString("clientSecret");
        String string6 = pluginCall.getString("authorizationEndpoint");
        String string7 = pluginCall.getString("tokenEndpoint");
        String string8 = pluginCall.getString("endSessionEndpoint");
        JSObject object = pluginCall.getObject("uiOptions");
        getAuth().configure(new AuthConfiguration(string, string2, string3, string4, string5, string6, string7, string8, object != null ? new AuthConfigurationUIOptions(object.getString("tintColor"), object.getString("headerColor"), object.getString("headerLineColor"), object.getString("headerTitleAuthorize"), object.getString("headerTitleEndSession"), FontDefinition.fromJSObject(object.getJSObject("headerTitleFont")), object.getString("closeButtonTitle"), FontDefinition.fromJSObject(object.getJSObject("closeButtonFont")), AuthConfigurationUIOptionsAlert.fromJSONObject(object.getJSObject("closeAlertReauthorize")), AuthConfigurationUIOptionsAlert.fromJSONObject(object.getJSObject("closeAlertRegistration")), AuthConfigurationUIOptions.jsonArrayOfStringsToList(object.optJSONArray("closeAlertRegistrationTriggerUrlPathSuffixes")), AuthConfigurationUIOptions.jsonArrayOfHttpAuthCredentialsToList(object.optJSONArray("httpAuthCredentials"))) : null));
        pluginCall.resolve();
    }

    @PluginMethod
    public void authorize(final PluginCall pluginCall) {
        final Boolean bool = pluginCall.getBoolean("tokenRefresh", false);
        getAuth().authorize(getAppActivity(), new Auth.ActionResultCallback() { // from class: ch.coop.capacitor.auth.AuthPlugin$$ExternalSyntheticLambda0
            @Override // ch.coop.auth.Auth.ActionResultCallback
            public final void onResult(Auth.ActionResult actionResult) {
                PluginCall.this.resolve(AuthPlugin.pluginCallResultData(actionResult.status.toString(), bool, actionResult.token));
            }
        });
    }

    @PluginMethod
    public void refreshTokens(final PluginCall pluginCall) {
        getAuth().refreshTokens(new Auth.ActionResultCallback() { // from class: ch.coop.capacitor.auth.AuthPlugin$$ExternalSyntheticLambda2
            @Override // ch.coop.auth.Auth.ActionResultCallback
            public final void onResult(Auth.ActionResult actionResult) {
                PluginCall.this.resolve(AuthPlugin.pluginCallResultData(actionResult.status.toString(), true, actionResult.token));
            }
        });
    }

    @PluginMethod
    public void endSession(final PluginCall pluginCall) {
        getAuth().endSession(getAppActivity(), new Auth.ActionResultCallback() { // from class: ch.coop.capacitor.auth.AuthPlugin$$ExternalSyntheticLambda1
            @Override // ch.coop.auth.Auth.ActionResultCallback
            public final void onResult(Auth.ActionResult actionResult) {
                PluginCall.this.resolve(AuthPlugin.pluginCallResultData(actionResult.status.toString()));
            }
        });
    }

    @PluginMethod
    public void testSupportInvalidateRefreshToken(PluginCall pluginCall) {
        getAuth().testSupportInvalidateRefreshToken();
        pluginCall.resolve();
    }

    @PluginMethod
    public void testSupportInvalidateIdToken(PluginCall pluginCall) {
        getAuth().testSupportInvalidateIdToken();
        pluginCall.resolve();
    }

    private static JSObject pluginCallResultData(String str) {
        return pluginCallResultData(str, null, null);
    }

    private static JSObject pluginCallResultData(String str, Boolean bool, String str2) {
        JSObject jSObject = new JSObject();
        jSObject.put("status", str);
        if (bool != null) {
            jSObject.put("tokenRefresh", (Object) bool);
        }
        if (str2 != null) {
            jSObject.put(ResponseTypeValues.TOKEN, str2);
        }
        return jSObject;
    }
}
