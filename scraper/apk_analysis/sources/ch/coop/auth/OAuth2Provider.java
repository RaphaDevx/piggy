package ch.coop.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import ch.coop.auth.AuthBrowserView;
import io.sentry.Sentry;
import java.util.Collections;
import lib.android.paypal.com.magnessdk.c;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.EndSessionRequest;
import net.openid.appauth.EndSessionResponse;
import net.openid.appauth.TokenResponse;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OAuth2Provider {
    private final AuthBrowserViewBuilder authBrowserViewBuilder;
    private final AuthorizationService authService;
    private AuthState authState;
    private TokenRequestCallback authorizeTokenRequestCallback;
    private AuthConfiguration configuration;
    private EndSessionCallback endSessionCallback;

    public static final class AlreadyRunningException extends Exception {
        private static final long serialVersionUID = -4934325304272246830L;
    }

    @FunctionalInterface
    public interface EndSessionCallback {
        void onResult(Exception exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    interface GetAuthServiceConfigCallback {
        void onCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration);
    }

    @FunctionalInterface
    public interface TokenRequestCallback {
        void onResult(AuthState authState, Exception exc);
    }

    public OAuth2Provider(AuthorizationService authorizationService, AuthState authState, AuthBrowserViewBuilder authBrowserViewBuilder) {
        this.authService = authorizationService;
        if (authState == null) {
            this.authState = new AuthState();
        } else {
            this.authState = authState;
        }
        this.authBrowserViewBuilder = authBrowserViewBuilder;
    }

    public void setConfiguration(AuthConfiguration authConfiguration) {
        this.configuration = authConfiguration;
    }

    public AuthState getAuthState() {
        return this.authState;
    }

    public void testSupportSetAuthState(AuthState authState) {
        this.authState = authState;
    }

    private void getAuthServiceConfig(Context context, GetAuthServiceConfigCallback getAuthServiceConfigCallback) {
        AuthConfiguration authConfiguration = this.configuration;
        AuthorizationServiceConfiguration authorizationServiceConfiguration = null;
        if (authConfiguration != null) {
            Uri parse = Uri.parse(authConfiguration.getTokenEndpoint());
            String string = Settings.Secure.getString(context.getContentResolver(), c.f);
            if (string != null) {
                parse = parse.buildUpon().appendQueryParameter("X-Device-ID", string).build();
            }
            authorizationServiceConfiguration = new AuthorizationServiceConfiguration(Uri.parse(this.configuration.getAuthorizationEndpoint()), parse, null, Uri.parse(this.configuration.getEndSessionEndpoint()));
        }
        getAuthServiceConfigCallback.onCompleted(authorizationServiceConfiguration);
    }

    public void authorize(final Activity activity, final TokenRequestCallback tokenRequestCallback) {
        getAuthServiceConfig(activity, new GetAuthServiceConfigCallback() { // from class: ch.coop.auth.OAuth2Provider$$ExternalSyntheticLambda0
            @Override // ch.coop.auth.OAuth2Provider.GetAuthServiceConfigCallback
            public final void onCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
                OAuth2Provider.this.lambda$authorize$0(tokenRequestCallback, activity, authorizationServiceConfiguration);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$authorize$0(TokenRequestCallback tokenRequestCallback, Activity activity, AuthorizationServiceConfiguration authorizationServiceConfiguration) {
        AuthBrowserView.Scenario scenario;
        if (authorizationServiceConfiguration == null) {
            throw new IllegalStateException("Call 'setConfiguration' before calling 'authorize'.");
        }
        if (this.authorizeTokenRequestCallback != null) {
            tokenRequestCallback.onResult(this.authState, new AlreadyRunningException());
            return;
        }
        this.authorizeTokenRequestCallback = tokenRequestCallback;
        AuthorizationRequest build = new AuthorizationRequest.Builder(authorizationServiceConfiguration, this.configuration.getClientId(), "code", Uri.parse(this.configuration.getRedirectUri())).setScope(this.configuration.getScope()).setCodeVerifier(null).build();
        if (this.authState.isAuthorized()) {
            scenario = AuthBrowserView.Scenario.Reauthorize;
        } else {
            scenario = AuthBrowserView.Scenario.Authorize;
        }
        this.authBrowserViewBuilder.build(activity).show(scenario, build, this.configuration.getUiOptions(), new AuthBrowserView.ResultCallback() { // from class: ch.coop.auth.OAuth2Provider$$ExternalSyntheticLambda2
            @Override // ch.coop.auth.AuthBrowserView.ResultCallback
            public final void onResult(Intent intent) {
                OAuth2Provider.this.handleAuthorizationResponseIntent(intent);
            }
        });
    }

    public void refreshTokens(AuthState.AuthStateAction authStateAction) {
        this.authState.setNeedsTokenRefresh(true);
        if (this.configuration == null) {
            authStateAction.execute(null, null, AuthorizationException.TokenRequestErrors.CLIENT_ERROR);
        } else {
            this.authState.performActionWithFreshTokens(this.authService, new ClientSecretBasicExtended(this.configuration.getClientSecret()), authStateAction);
        }
    }

    public void endSession(final Activity activity, final EndSessionCallback endSessionCallback) {
        getAuthServiceConfig(activity, new GetAuthServiceConfigCallback() { // from class: ch.coop.auth.OAuth2Provider$$ExternalSyntheticLambda1
            @Override // ch.coop.auth.OAuth2Provider.GetAuthServiceConfigCallback
            public final void onCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
                OAuth2Provider.this.lambda$endSession$1(endSessionCallback, activity, authorizationServiceConfiguration);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$endSession$1(EndSessionCallback endSessionCallback, Activity activity, AuthorizationServiceConfiguration authorizationServiceConfiguration) {
        if (authorizationServiceConfiguration == null) {
            throw new IllegalStateException("Call 'setConfiguration' before calling 'endSession'.");
        }
        if (this.endSessionCallback != null) {
            endSessionCallback.onResult(new AlreadyRunningException());
            return;
        }
        this.endSessionCallback = endSessionCallback;
        EndSessionRequest.Builder additionalParameters = new EndSessionRequest.Builder(authorizationServiceConfiguration).setPostLogoutRedirectUri(Uri.parse(this.configuration.getPostLogoutRedirectUri())).setState(null).setAdditionalParameters(Collections.singletonMap(NotificationCompat.CATEGORY_SERVICE, this.configuration.getPostLogoutRedirectUri()));
        String idToken = this.authState.getIdToken();
        if (idToken != null) {
            additionalParameters.setIdTokenHint(idToken);
        }
        this.authBrowserViewBuilder.build(activity).show(additionalParameters.build(), this.configuration.getUiOptions(), new AuthBrowserView.ResultCallback() { // from class: ch.coop.auth.OAuth2Provider$$ExternalSyntheticLambda3
            @Override // ch.coop.auth.AuthBrowserView.ResultCallback
            public final void onResult(Intent intent) {
                OAuth2Provider.this.handleEndSessionResponseIntent(intent);
            }
        });
    }

    public void deleteTokens() {
        this.authState = new AuthState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuthorizationResponseIntent(Intent intent) {
        final JSONObject jsonSerialize = this.authState.jsonSerialize();
        AuthorizationResponse fromIntent = AuthorizationResponse.fromIntent(intent);
        AuthorizationException fromIntent2 = AuthorizationException.fromIntent(intent);
        this.authState.update(fromIntent, fromIntent2);
        if (fromIntent != null) {
            this.authService.performTokenRequest(fromIntent.createTokenExchangeRequest(), new ClientSecretBasicExtended(this.configuration.getClientSecret()), new AuthorizationService.TokenResponseCallback() { // from class: ch.coop.auth.OAuth2Provider$$ExternalSyntheticLambda4
                @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
                public final void onTokenRequestCompleted(TokenResponse tokenResponse, AuthorizationException authorizationException) {
                    OAuth2Provider.this.lambda$handleAuthorizationResponseIntent$2(jsonSerialize, tokenResponse, authorizationException);
                }
            });
        } else {
            TokenRequestCallback tokenRequestCallback = this.authorizeTokenRequestCallback;
            if (tokenRequestCallback != null) {
                tokenRequestCallback.onResult(this.authState, fromIntent2);
                this.authorizeTokenRequestCallback = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleAuthorizationResponseIntent$2(JSONObject jSONObject, TokenResponse tokenResponse, AuthorizationException authorizationException) {
        if (authorizationException == null) {
            this.authState.update(tokenResponse, authorizationException);
        } else {
            try {
                this.authState = AuthState.jsonDeserialize(jSONObject);
            } catch (JSONException e) {
                Sentry.captureException(e);
            }
        }
        TokenRequestCallback tokenRequestCallback = this.authorizeTokenRequestCallback;
        if (tokenRequestCallback != null) {
            tokenRequestCallback.onResult(this.authState, authorizationException);
            this.authorizeTokenRequestCallback = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEndSessionResponseIntent(Intent intent) {
        EndSessionResponse.fromIntent(intent);
        AuthorizationException fromIntent = AuthorizationException.fromIntent(intent);
        if (fromIntent == null) {
            deleteTokens();
        }
        EndSessionCallback endSessionCallback = this.endSessionCallback;
        if (endSessionCallback != null) {
            endSessionCallback.onResult(fromIntent);
            this.endSessionCallback = null;
        }
    }
}
