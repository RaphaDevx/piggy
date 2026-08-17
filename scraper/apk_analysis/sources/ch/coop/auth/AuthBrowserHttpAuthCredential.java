package ch.coop.auth;

import io.sentry.Sentry;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AuthBrowserHttpAuthCredential {
    private static final String KEY_HOST = "host";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REALM = "realm";
    private static final String KEY_USERNAME = "username";
    private final String host;
    private final String password;
    private final String realm;
    private final String username;

    public AuthBrowserHttpAuthCredential(String str, String str2, String str3, String str4) {
        this.host = str;
        this.realm = str2;
        this.username = str3;
        this.password = str4;
    }

    public String getHost() {
        return this.host;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthBrowserHttpAuthCredential authBrowserHttpAuthCredential = (AuthBrowserHttpAuthCredential) obj;
        return Objects.equals(this.host, authBrowserHttpAuthCredential.host) && Objects.equals(this.realm, authBrowserHttpAuthCredential.realm) && Objects.equals(this.username, authBrowserHttpAuthCredential.username) && Objects.equals(this.password, authBrowserHttpAuthCredential.password);
    }

    public int hashCode() {
        return Objects.hash(this.host, this.realm, this.username, this.password);
    }

    public static AuthBrowserHttpAuthCredential fromJSONObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            return new AuthBrowserHttpAuthCredential(jSONObject.optString(KEY_HOST), jSONObject.optString(KEY_REALM), jSONObject.optString("username"), jSONObject.optString("password"));
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.host;
            if (str != null) {
                jSONObject.put(KEY_HOST, str);
            }
            String str2 = this.realm;
            if (str2 != null) {
                jSONObject.put(KEY_REALM, str2);
            }
            String str3 = this.username;
            if (str3 != null) {
                jSONObject.put("username", str3);
            }
            String str4 = this.password;
            if (str4 != null) {
                jSONObject.put("password", str4);
            }
        } catch (JSONException e) {
            Sentry.captureException(e);
        }
        return jSONObject;
    }
}
