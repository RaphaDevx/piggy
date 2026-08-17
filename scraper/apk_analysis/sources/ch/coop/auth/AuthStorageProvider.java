package ch.coop.auth;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import io.sentry.Sentry;
import java.io.IOException;
import java.security.GeneralSecurityException;
import net.openid.appauth.AuthState;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class AuthStorageProvider {
    private static final String KEY_AUTHORIZATION_ENDPOINT = "authorization_endpoint";
    private static final String KEY_AUTH_STATE = "auth_state";
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_END_SESSION_ENDPOINT = "end_session_endpoint";
    private static final String KEY_POST_LOGOUT_REDIRECT_URI = "post_logout_redirect_uri";
    private static final String KEY_REDIRECT_URI = "redirect_uri";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_TOKEN_ENDPOINT = "token_endpoint";
    private static final String KEY_UI_OPTIONS = "ui_options";
    private static final String TAG = "AuthStorageProvider";
    private SharedPreferences sharedPreferences;

    public AuthStorageProvider(Context context) {
        try {
            this.sharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(context, "auth", new MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (IOException | GeneralSecurityException e) {
            Sentry.captureException(e);
        }
    }

    public void storeConfiguration(AuthConfiguration authConfiguration) {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor putString = sharedPreferences.edit().putString(KEY_REDIRECT_URI, authConfiguration.getRedirectUri()).putString(KEY_POST_LOGOUT_REDIRECT_URI, authConfiguration.getPostLogoutRedirectUri()).putString(KEY_SCOPE, authConfiguration.getScope()).putString("client_id", authConfiguration.getClientId()).putString(KEY_CLIENT_SECRET, authConfiguration.getClientSecret()).putString(KEY_AUTHORIZATION_ENDPOINT, authConfiguration.getAuthorizationEndpoint()).putString(KEY_TOKEN_ENDPOINT, authConfiguration.getTokenEndpoint()).putString(KEY_END_SESSION_ENDPOINT, authConfiguration.getEndSessionEndpoint());
            if (authConfiguration.getUiOptions() != null) {
                putString.putString(KEY_UI_OPTIONS, authConfiguration.getUiOptions().toJsonObject().toString());
            } else {
                putString.putString(KEY_UI_OPTIONS, null);
            }
            putString.apply();
            return;
        }
        throw new IllegalStateException("sharedPreferences not available.");
    }

    public AuthConfiguration loadConfiguration() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(KEY_REDIRECT_URI, null);
            if (string != null) {
                return new AuthConfiguration(string, this.sharedPreferences.getString(KEY_POST_LOGOUT_REDIRECT_URI, null), this.sharedPreferences.getString(KEY_SCOPE, null), this.sharedPreferences.getString("client_id", null), this.sharedPreferences.getString(KEY_CLIENT_SECRET, null), this.sharedPreferences.getString(KEY_AUTHORIZATION_ENDPOINT, null), this.sharedPreferences.getString(KEY_TOKEN_ENDPOINT, null), this.sharedPreferences.getString(KEY_END_SESSION_ENDPOINT, null), AuthConfigurationUIOptions.fromJsonString(this.sharedPreferences.getString(KEY_UI_OPTIONS, null)));
            }
            return null;
        }
        throw new IllegalStateException("sharedPreferences not available.");
    }

    public void storeAuthState(AuthState authState) {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(KEY_AUTH_STATE, authState.jsonSerializeString());
            edit.apply();
            return;
        }
        throw new IllegalStateException("sharedPreferences not available.");
    }

    public AuthState loadAuthState() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(KEY_AUTH_STATE, null);
            if (string == null) {
                return null;
            }
            try {
                return AuthState.jsonDeserialize(string);
            } catch (JSONException e) {
                Sentry.captureException(e);
                return null;
            }
        }
        throw new IllegalStateException("sharedPreferences not available.");
    }

    public void deleteAuthState() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(KEY_AUTH_STATE);
            edit.apply();
            return;
        }
        throw new IllegalStateException("sharedPreferences not available.");
    }
}
