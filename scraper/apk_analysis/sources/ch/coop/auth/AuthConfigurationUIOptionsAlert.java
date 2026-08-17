package ch.coop.auth;

import io.sentry.Sentry;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AuthConfigurationUIOptionsAlert {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TEXT_BUTTON_ACTION = "textButtonAction";
    private static final String KEY_TEXT_BUTTON_CANCEL = "textButtonCancel";
    private static final String KEY_TITLE = "title";
    private final String message;
    private final String textButtonAction;
    private final String textButtonCancel;
    private final String title;

    public AuthConfigurationUIOptionsAlert(String str, String str2, String str3, String str4) {
        this.title = str;
        this.message = str2;
        this.textButtonAction = str3;
        this.textButtonCancel = str4;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTextButtonAction() {
        return this.textButtonAction;
    }

    public String getTextButtonCancel() {
        return this.textButtonCancel;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConfigurationUIOptionsAlert authConfigurationUIOptionsAlert = (AuthConfigurationUIOptionsAlert) obj;
        return Objects.equals(this.title, authConfigurationUIOptionsAlert.title) && Objects.equals(this.message, authConfigurationUIOptionsAlert.message) && Objects.equals(this.textButtonAction, authConfigurationUIOptionsAlert.textButtonAction) && Objects.equals(this.textButtonCancel, authConfigurationUIOptionsAlert.textButtonCancel);
    }

    public int hashCode() {
        return Objects.hash(this.title, this.message, this.textButtonAction, this.textButtonCancel);
    }

    public static AuthConfigurationUIOptionsAlert fromJSONObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            return new AuthConfigurationUIOptionsAlert(jSONObject.optString("title"), jSONObject.optString("message"), jSONObject.optString(KEY_TEXT_BUTTON_ACTION), jSONObject.optString(KEY_TEXT_BUTTON_CANCEL));
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.title;
            if (str != null) {
                jSONObject.put("title", str);
            }
            String str2 = this.message;
            if (str2 != null) {
                jSONObject.put("message", str2);
            }
            String str3 = this.textButtonAction;
            if (str3 != null) {
                jSONObject.put(KEY_TEXT_BUTTON_ACTION, str3);
            }
            String str4 = this.textButtonCancel;
            if (str4 != null) {
                jSONObject.put(KEY_TEXT_BUTTON_CANCEL, str4);
            }
        } catch (JSONException e) {
            Sentry.captureException(e);
        }
        return jSONObject;
    }
}
