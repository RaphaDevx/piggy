package ch.coop.auth;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.util.Log;
import ch.coop.auth.OAuth2Provider;
import com.auth0.android.jwt.DecodeException;
import com.auth0.android.jwt.JWT;
import io.sentry.Sentry;
import java.util.Objects;
import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.IdToken;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.browser.BrowserAllowList;
import net.openid.appauth.browser.BrowserMatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Auth {
    private static final String TAG = "Auth";
    private final AuthStorageProvider authStorageProvider;
    private final Context context;
    private final OkHttpClient httpClient;
    private final OAuth2Provider oAuth2Provider;
    private static final Boolean DEBUG = false;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static volatile Auth instance = null;

    public interface ActionResultCallback {
        void onResult(ActionResult actionResult);
    }

    public interface RequestCallback {
        void onRequestCompleted(String str);

        void onRequestFailed(Exception exc);
    }

    public enum ResultStatus {
        SUCCESS("SUCCESS"),
        CANCELLED_BY_USER("CANCELLED_BY_USER"),
        INVALID_REFRESH_TOKEN("INVALID_REFRESH_TOKEN"),
        ID_TOKEN_MISSING_ERROR("ID_TOKEN_MISSING_ERROR"),
        UNKNOWN_SERVER_ERROR("UNKNOWN_SERVER_ERROR"),
        NETWORK_ERROR("NETWORK_ERROR"),
        CAPTIVE_PORTAL_ERROR("CAPTIVE_PORTAL_ERROR"),
        AUTH_DATA_EMPTY("AUTH_DATA_EMPTY"),
        AUTH_DATA_REFRESHING("AUTH_DATA_REFRESHING"),
        JSON_ERROR("JSON_ERROR"),
        LOGGED_OUT("LOGGED_OUT"),
        MALFORMED_CAS_REDIRECT_URI("MALFORMED_CAS_REDIRECT_URI"),
        ID_TOKEN_VALIDATION_ERROR("ID_TOKEN_VALIDATION_ERROR"),
        NO_COMPATIBLE_BROWSER_FOUND("NO_COMPATIBLE_BROWSER_FOUND"),
        ALREADY_RUNNING_ERROR("ALREADY_RUNNING_ERROR");

        private final String text;

        ResultStatus(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public static class ActionResult {
        public final ResultStatus status;
        public final String token;

        public ActionResult(ResultStatus resultStatus, String str) {
            this.status = resultStatus;
            this.token = str;
        }
    }

    Auth(Context context, AuthStorageProvider authStorageProvider, OAuth2Provider oAuth2Provider, OkHttpClient okHttpClient) {
        this.context = context;
        this.authStorageProvider = authStorageProvider;
        this.oAuth2Provider = oAuth2Provider;
        this.httpClient = okHttpClient;
        AuthConfiguration loadConfiguration = authStorageProvider.loadConfiguration();
        if (loadConfiguration != null) {
            oAuth2Provider.setConfiguration(loadConfiguration);
        }
    }

    public static Auth getInstance(Context context) {
        if (instance == null) {
            synchronized (Auth.class) {
                if (instance == null) {
                    Context applicationContext = context.getApplicationContext();
                    AuthStorageProvider authStorageProvider = new AuthStorageProvider(applicationContext);
                    instance = new Auth(applicationContext, authStorageProvider, new OAuth2Provider(new AuthorizationService(context, new AppAuthConfiguration.Builder().setBrowserMatcher(new BrowserAllowList(new BrowserMatcher[0])).build()), authStorageProvider.loadAuthState(), new AuthBrowserViewBuilder()), new OkHttpClient());
                }
            }
        }
        return instance;
    }

    public void configure(AuthConfiguration authConfiguration) {
        this.authStorageProvider.storeConfiguration(authConfiguration);
        this.oAuth2Provider.setConfiguration(authConfiguration);
    }

    public boolean isLoggedIn() {
        return this.oAuth2Provider.getAuthState().isAuthorized();
    }

    public String loginIdentifier() {
        return ((IdToken) Objects.requireNonNull(this.oAuth2Provider.getAuthState().getParsedIdToken())).subject;
    }

    public String loginIdentifierNullable() {
        IdToken parsedIdToken = this.oAuth2Provider.getAuthState().getParsedIdToken();
        if (parsedIdToken != null) {
            return parsedIdToken.subject;
        }
        return null;
    }

    public String idToken() {
        return this.oAuth2Provider.getAuthState().getIdToken();
    }

    public void authorize(Activity activity, final ActionResultCallback actionResultCallback) {
        this.oAuth2Provider.authorize(activity, new OAuth2Provider.TokenRequestCallback() { // from class: ch.coop.auth.Auth$$ExternalSyntheticLambda1
            @Override // ch.coop.auth.OAuth2Provider.TokenRequestCallback
            public final void onResult(AuthState authState, Exception exc) {
                Auth.this.lambda$authorize$0(actionResultCallback, authState, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$authorize$0(ActionResultCallback actionResultCallback, AuthState authState, Exception exc) {
        ActionResult actionResult;
        this.authStorageProvider.storeAuthState(authState);
        String idToken = authState.getIdToken();
        if (exc == null && idToken != null) {
            actionResult = new ActionResult(ResultStatus.SUCCESS, idToken);
            if (DEBUG.booleanValue()) {
                Log.d(TAG, "Authorize success: " + authState.getIdToken());
            }
        } else {
            ActionResult actionResult2 = new ActionResult(mapExceptionToResultStatus(exc, idToken, authState), null);
            if (DEBUG.booleanValue()) {
                Log.e(TAG, "Authorize exception: " + exc + " Mapped to ResultStatus: " + actionResult2.status);
            }
            actionResult = actionResult2;
        }
        if (actionResult.status == ResultStatus.CANCELLED_BY_USER) {
            this.oAuth2Provider.deleteTokens();
            this.authStorageProvider.deleteAuthState();
        }
        if (actionResultCallback != null) {
            actionResultCallback.onResult(actionResult);
        }
    }

    public void refreshTokens(final ActionResultCallback actionResultCallback) {
        this.oAuth2Provider.refreshTokens(new AuthState.AuthStateAction() { // from class: ch.coop.auth.Auth$$ExternalSyntheticLambda0
            @Override // net.openid.appauth.AuthState.AuthStateAction
            public final void execute(String str, String str2, AuthorizationException authorizationException) {
                Auth.this.lambda$refreshTokens$1(actionResultCallback, str, str2, authorizationException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshTokens$1(ActionResultCallback actionResultCallback, String str, String str2, AuthorizationException authorizationException) {
        ActionResult actionResult;
        AuthState authState = this.oAuth2Provider.getAuthState();
        this.authStorageProvider.storeAuthState(authState);
        if (authorizationException == null && str2 != null && authState.getIdToken() != null) {
            actionResult = new ActionResult(ResultStatus.SUCCESS, str2);
            if (DEBUG.booleanValue()) {
                Log.d(TAG, "Refresh success: " + str2);
            }
        } else {
            ActionResult actionResult2 = new ActionResult(mapExceptionToResultStatus(authorizationException, str2, authState), null);
            if (DEBUG.booleanValue()) {
                Log.e(TAG, "Refresh exception: " + authorizationException + " Mapped to ResultStatus: " + actionResult2.status);
            }
            actionResult = actionResult2;
        }
        if (actionResultCallback != null) {
            actionResultCallback.onResult(actionResult);
        }
    }

    public void endSession(Activity activity, final ActionResultCallback actionResultCallback) {
        this.oAuth2Provider.endSession(activity, new OAuth2Provider.EndSessionCallback() { // from class: ch.coop.auth.Auth$$ExternalSyntheticLambda2
            @Override // ch.coop.auth.OAuth2Provider.EndSessionCallback
            public final void onResult(Exception exc) {
                Auth.this.lambda$endSession$2(actionResultCallback, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$endSession$2(ActionResultCallback actionResultCallback, Exception exc) {
        ActionResult actionResult;
        if (exc == null) {
            this.authStorageProvider.deleteAuthState();
            actionResult = new ActionResult(ResultStatus.SUCCESS, null);
            if (DEBUG.booleanValue()) {
                Log.d(TAG, "End session success");
            }
        } else {
            ActionResult actionResult2 = new ActionResult(mapExceptionToResultStatus(exc, null, null), null);
            if (DEBUG.booleanValue()) {
                Log.e(TAG, "End session exception: " + exc + " Mapped to ResultStatus: " + actionResult2.status);
            }
            actionResult = actionResult2;
        }
        if (actionResultCallback != null) {
            actionResultCallback.onResult(actionResult);
        }
    }

    public void refreshIdTokenIfNecessary(ActionResultCallback actionResultCallback) {
        ActionResult actionResult = new ActionResult(ResultStatus.AUTH_DATA_EMPTY, null);
        AuthState authState = this.oAuth2Provider.getAuthState();
        String idToken = authState != null ? authState.getIdToken() : null;
        if (idToken != null) {
            try {
                if (new JWT(idToken).isExpired(60L)) {
                    refreshTokens(actionResultCallback);
                    return;
                }
                actionResult = new ActionResult(ResultStatus.SUCCESS, idToken);
            } catch (DecodeException unused) {
            }
        }
        actionResultCallback.onResult(actionResult);
    }

    public void testSupportInvalidateRefreshToken() {
        AuthState authState = this.oAuth2Provider.getAuthState();
        if (authState.getRefreshToken() == null) {
            Log.e(TAG, "No refresh token set. Can't invalidate refresh token.");
            return;
        }
        JSONObject jsonSerialize = authState.jsonSerialize();
        try {
            jsonSerialize.put("refreshToken", "INVALID");
            AuthState jsonDeserialize = AuthState.jsonDeserialize(jsonSerialize);
            this.oAuth2Provider.testSupportSetAuthState(jsonDeserialize);
            this.authStorageProvider.storeAuthState(jsonDeserialize);
        } catch (JSONException unused) {
            Log.e(TAG, "Could not update authState.");
        }
    }

    public void testSupportInvalidateIdToken() {
        AuthState authState = this.oAuth2Provider.getAuthState();
        if (authState.getIdToken() == null) {
            Log.e(TAG, "No ID token set. Can't invalidate ID token.");
            return;
        }
        JSONObject jsonSerialize = authState.jsonSerialize();
        try {
            jsonSerialize.getJSONObject("lastAuthorizationResponse").put("expires_at", 0).put(ResponseTypeValues.ID_TOKEN, "INVALID");
            jsonSerialize.getJSONObject("mLastTokenResponse").put("expires_at", 0).put(ResponseTypeValues.ID_TOKEN, "INVALID");
            AuthState jsonDeserialize = AuthState.jsonDeserialize(jsonSerialize);
            this.oAuth2Provider.testSupportSetAuthState(jsonDeserialize);
            this.authStorageProvider.storeAuthState(jsonDeserialize);
        } catch (JSONException unused) {
            Log.e(TAG, "Could not update authState.");
        }
    }

    private ResultStatus mapExceptionToResultStatus(Exception exc, String str, AuthState authState) {
        ResultStatus resultStatus;
        if (AuthorizationException.GeneralErrors.USER_CANCELED_AUTH_FLOW.equals(exc)) {
            resultStatus = ResultStatus.CANCELLED_BY_USER;
        } else if (AuthorizationException.TokenRequestErrors.INVALID_GRANT.equals(exc)) {
            resultStatus = ResultStatus.INVALID_REFRESH_TOKEN;
        } else if (exc == null && str == null) {
            resultStatus = ResultStatus.ID_TOKEN_MISSING_ERROR;
        } else if (AuthorizationException.GeneralErrors.JSON_DESERIALIZATION_ERROR.equals(exc)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(17)) {
                resultStatus = ResultStatus.CAPTIVE_PORTAL_ERROR;
            } else {
                resultStatus = ResultStatus.INVALID_REFRESH_TOKEN;
            }
        } else if (AuthorizationException.GeneralErrors.NETWORK_ERROR.equals(exc)) {
            resultStatus = ResultStatus.NETWORK_ERROR;
        } else if (AuthorizationException.AuthorizationRequestErrors.CLIENT_ERROR.equals(exc)) {
            resultStatus = ResultStatus.AUTH_DATA_EMPTY;
        } else if (AuthorizationException.GeneralErrors.ID_TOKEN_VALIDATION_ERROR.equals(exc)) {
            resultStatus = ResultStatus.ID_TOKEN_VALIDATION_ERROR;
        } else if (exc instanceof OAuth2Provider.AlreadyRunningException) {
            resultStatus = ResultStatus.ALREADY_RUNNING_ERROR;
        } else if (authState == null || authState.getIdToken() == null) {
            resultStatus = ResultStatus.LOGGED_OUT;
        } else {
            resultStatus = ResultStatus.UNKNOWN_SERVER_ERROR;
        }
        if (resultStatus != ResultStatus.CANCELLED_BY_USER && resultStatus != ResultStatus.INVALID_REFRESH_TOKEN && resultStatus != ResultStatus.CAPTIVE_PORTAL_ERROR && resultStatus != ResultStatus.ALREADY_RUNNING_ERROR) {
            Sentry.captureException(exc);
        }
        return resultStatus;
    }
}
