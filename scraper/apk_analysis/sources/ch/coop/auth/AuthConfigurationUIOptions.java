package ch.coop.auth;

import io.sentry.Sentry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AuthConfigurationUIOptions {
    private static final String KEY_CLOSE_ALERT_REAUTHORIZE = "closeAlertReauthorize";
    private static final String KEY_CLOSE_ALERT_REGISTRATION = "closeAlertRegistration";
    private static final String KEY_CLOSE_ALERT_REGISTRATION_TRIGGER_URL_PATH_SUFFIXES = "closeAlertRegistrationTriggerUrlPathSuffixes";
    private static final String KEY_CLOSE_BUTTON_FONT = "closeButtonFont";
    private static final String KEY_CLOSE_BUTTON_TITLE = "closeButtonTitle";
    private static final String KEY_HEADER_COLOR = "headerColor";
    private static final String KEY_HEADER_LINE_COLOR = "headerLineColor";
    private static final String KEY_HEADER_TITLE_AUTHORIZE = "headerTitleAuthorize";
    private static final String KEY_HEADER_TITLE_END_SESSION = "headerTitleEndSession";
    private static final String KEY_HEADER_TITLE_FONT = "headerTitleFont";
    private static final String KEY_HTTP_AUTH_CREDENTIALS = "httpAuthCredentials";
    private static final String KEY_TINT_COLOR = "tintColor";
    private final AuthConfigurationUIOptionsAlert closeAlertReauthorize;
    private final AuthConfigurationUIOptionsAlert closeAlertRegistration;
    private final List<String> closeAlertRegistrationTriggerUrlPathSuffixes;
    private final FontDefinition closeButtonFont;
    private final String closeButtonTitle;
    private final String headerColor;
    private final String headerLineColor;
    private final String headerTitleAuthorize;
    private final String headerTitleEndSession;
    private final FontDefinition headerTitleFont;
    private final List<AuthBrowserHttpAuthCredential> httpAuthCredentials;
    private final String tintColor;

    public AuthConfigurationUIOptions(String str, String str2, String str3, String str4, String str5, FontDefinition fontDefinition, String str6, FontDefinition fontDefinition2, AuthConfigurationUIOptionsAlert authConfigurationUIOptionsAlert, AuthConfigurationUIOptionsAlert authConfigurationUIOptionsAlert2, List<String> list, List<AuthBrowserHttpAuthCredential> list2) {
        this.tintColor = str;
        this.headerColor = str2;
        this.headerLineColor = str3;
        this.headerTitleAuthorize = str4;
        this.headerTitleEndSession = str5;
        this.headerTitleFont = fontDefinition;
        this.closeButtonTitle = str6;
        this.closeButtonFont = fontDefinition2;
        this.closeAlertReauthorize = authConfigurationUIOptionsAlert;
        this.closeAlertRegistration = authConfigurationUIOptionsAlert2;
        this.closeAlertRegistrationTriggerUrlPathSuffixes = list;
        this.httpAuthCredentials = list2;
    }

    public String getTintColor() {
        return this.tintColor;
    }

    public String getHeaderColor() {
        return this.headerColor;
    }

    public String getHeaderLineColor() {
        return this.headerLineColor;
    }

    public String getHeaderTitleAuthorize() {
        return this.headerTitleAuthorize;
    }

    public String getHeaderTitleEndSession() {
        return this.headerTitleEndSession;
    }

    public FontDefinition getHeaderTitleFont() {
        return this.headerTitleFont;
    }

    public String getCloseButtonTitle() {
        return this.closeButtonTitle;
    }

    public FontDefinition getCloseButtonFont() {
        return this.closeButtonFont;
    }

    public AuthConfigurationUIOptionsAlert getCloseAlertReauthorize() {
        return this.closeAlertReauthorize;
    }

    public AuthConfigurationUIOptionsAlert getCloseAlertRegistration() {
        return this.closeAlertRegistration;
    }

    public List<String> getCloseAlertRegistrationTriggerUrlPathSuffixes() {
        return Collections.unmodifiableList(this.closeAlertRegistrationTriggerUrlPathSuffixes);
    }

    public List<AuthBrowserHttpAuthCredential> getHttpAuthCredentials() {
        List<AuthBrowserHttpAuthCredential> list = this.httpAuthCredentials;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConfigurationUIOptions authConfigurationUIOptions = (AuthConfigurationUIOptions) obj;
        return Objects.equals(this.tintColor, authConfigurationUIOptions.tintColor) && Objects.equals(this.headerColor, authConfigurationUIOptions.headerColor) && Objects.equals(this.headerLineColor, authConfigurationUIOptions.headerLineColor) && Objects.equals(this.headerTitleAuthorize, authConfigurationUIOptions.headerTitleAuthorize) && Objects.equals(this.headerTitleEndSession, authConfigurationUIOptions.headerTitleEndSession) && Objects.equals(this.headerTitleFont, authConfigurationUIOptions.headerTitleFont) && Objects.equals(this.closeButtonTitle, authConfigurationUIOptions.closeButtonTitle) && Objects.equals(this.closeButtonFont, authConfigurationUIOptions.closeButtonFont) && Objects.equals(this.closeAlertReauthorize, authConfigurationUIOptions.closeAlertReauthorize) && Objects.equals(this.closeAlertRegistration, authConfigurationUIOptions.closeAlertRegistration) && Objects.equals(this.closeAlertRegistrationTriggerUrlPathSuffixes, authConfigurationUIOptions.closeAlertRegistrationTriggerUrlPathSuffixes) && Objects.equals(this.httpAuthCredentials, authConfigurationUIOptions.httpAuthCredentials);
    }

    public int hashCode() {
        return Objects.hash(this.tintColor, this.headerColor, this.headerLineColor, this.headerTitleAuthorize, this.headerTitleEndSession, this.headerTitleFont, this.closeButtonTitle, this.closeButtonFont, this.closeAlertReauthorize, this.closeAlertRegistration, this.closeAlertRegistrationTriggerUrlPathSuffixes, this.httpAuthCredentials);
    }

    public static AuthConfigurationUIOptions fromJsonString(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new AuthConfigurationUIOptions(jSONObject.optString(KEY_TINT_COLOR), jSONObject.optString(KEY_HEADER_COLOR), jSONObject.optString(KEY_HEADER_LINE_COLOR), jSONObject.optString(KEY_HEADER_TITLE_AUTHORIZE), jSONObject.optString(KEY_HEADER_TITLE_END_SESSION), FontDefinition.fromJSONObject(jSONObject.optJSONObject(KEY_HEADER_TITLE_FONT)), jSONObject.optString(KEY_CLOSE_BUTTON_TITLE), FontDefinition.fromJSONObject(jSONObject.optJSONObject(KEY_CLOSE_BUTTON_FONT)), AuthConfigurationUIOptionsAlert.fromJSONObject(jSONObject.optJSONObject(KEY_CLOSE_ALERT_REAUTHORIZE)), AuthConfigurationUIOptionsAlert.fromJSONObject(jSONObject.optJSONObject(KEY_CLOSE_ALERT_REGISTRATION)), jsonArrayOfStringsToList(jSONObject.optJSONArray(KEY_CLOSE_ALERT_REGISTRATION_TRIGGER_URL_PATH_SUFFIXES)), jsonArrayOfHttpAuthCredentialsToList(jSONObject.optJSONArray(KEY_HTTP_AUTH_CREDENTIALS)));
            } catch (JSONException e) {
                Sentry.captureException(e);
            }
        }
        return null;
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_TINT_COLOR, this.tintColor);
            jSONObject.put(KEY_HEADER_COLOR, this.headerColor);
            jSONObject.put(KEY_HEADER_LINE_COLOR, this.headerLineColor);
            jSONObject.put(KEY_HEADER_TITLE_AUTHORIZE, this.headerTitleAuthorize);
            jSONObject.put(KEY_HEADER_TITLE_END_SESSION, this.headerTitleEndSession);
            FontDefinition fontDefinition = this.headerTitleFont;
            if (fontDefinition != null) {
                jSONObject.put(KEY_HEADER_TITLE_FONT, fontDefinition.toJSONObject());
            }
            jSONObject.put(KEY_CLOSE_BUTTON_TITLE, this.closeButtonTitle);
            FontDefinition fontDefinition2 = this.closeButtonFont;
            if (fontDefinition2 != null) {
                jSONObject.put(KEY_CLOSE_BUTTON_FONT, fontDefinition2.toJSONObject());
            }
            AuthConfigurationUIOptionsAlert authConfigurationUIOptionsAlert = this.closeAlertReauthorize;
            if (authConfigurationUIOptionsAlert != null) {
                jSONObject.put(KEY_CLOSE_ALERT_REAUTHORIZE, authConfigurationUIOptionsAlert.toJSONObject());
            }
            AuthConfigurationUIOptionsAlert authConfigurationUIOptionsAlert2 = this.closeAlertRegistration;
            if (authConfigurationUIOptionsAlert2 != null) {
                jSONObject.put(KEY_CLOSE_ALERT_REGISTRATION, authConfigurationUIOptionsAlert2.toJSONObject());
            }
            if (this.closeAlertRegistrationTriggerUrlPathSuffixes != null) {
                jSONObject.put(KEY_CLOSE_ALERT_REGISTRATION_TRIGGER_URL_PATH_SUFFIXES, new JSONArray((Collection) this.closeAlertRegistrationTriggerUrlPathSuffixes));
            }
            if (this.httpAuthCredentials != null) {
                jSONObject.put(KEY_HTTP_AUTH_CREDENTIALS, new JSONArray((Collection) this.httpAuthCredentials.stream().map(new Function() { // from class: ch.coop.auth.AuthConfigurationUIOptions$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((AuthBrowserHttpAuthCredential) obj).toJSONObject();
                    }
                }).collect(Collectors.toList())));
            }
        } catch (JSONException e) {
            Sentry.captureException(e);
        }
        return jSONObject;
    }

    public static List<String> jsonArrayOfStringsToList(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    public static List<AuthBrowserHttpAuthCredential> jsonArrayOfHttpAuthCredentialsToList(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            AuthBrowserHttpAuthCredential fromJSONObject = AuthBrowserHttpAuthCredential.fromJSONObject(jSONArray.optJSONObject(i));
            if (fromJSONObject != null) {
                arrayList.add(fromJSONObject);
            }
        }
        return arrayList;
    }
}
