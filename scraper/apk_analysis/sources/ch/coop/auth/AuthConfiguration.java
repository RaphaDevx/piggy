package ch.coop.auth;

import java.util.Objects;

/* loaded from: classes3.dex */
public class AuthConfiguration {
    private final String authorizationEndpoint;
    private final String clientId;
    private final String clientSecret;
    private final String endSessionEndpoint;
    private final String postLogoutRedirectUri;
    private final String redirectUri;
    private final String scope;
    private final String tokenEndpoint;
    private final AuthConfigurationUIOptions uiOptions;

    public AuthConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, AuthConfigurationUIOptions authConfigurationUIOptions) {
        this.redirectUri = str;
        this.postLogoutRedirectUri = str2;
        this.scope = str3;
        this.clientId = str4;
        this.clientSecret = str5;
        this.authorizationEndpoint = str6;
        this.tokenEndpoint = str7;
        this.endSessionEndpoint = str8;
        this.uiOptions = authConfigurationUIOptions;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public String getPostLogoutRedirectUri() {
        return this.postLogoutRedirectUri;
    }

    public String getScope() {
        return this.scope;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getAuthorizationEndpoint() {
        return this.authorizationEndpoint;
    }

    public String getTokenEndpoint() {
        return this.tokenEndpoint;
    }

    public String getEndSessionEndpoint() {
        return this.endSessionEndpoint;
    }

    public AuthConfigurationUIOptions getUiOptions() {
        return this.uiOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConfiguration authConfiguration = (AuthConfiguration) obj;
        return Objects.equals(this.redirectUri, authConfiguration.redirectUri) && Objects.equals(this.postLogoutRedirectUri, authConfiguration.postLogoutRedirectUri) && Objects.equals(this.scope, authConfiguration.scope) && Objects.equals(this.clientId, authConfiguration.clientId) && Objects.equals(this.clientSecret, authConfiguration.clientSecret) && Objects.equals(this.authorizationEndpoint, authConfiguration.authorizationEndpoint) && Objects.equals(this.tokenEndpoint, authConfiguration.tokenEndpoint) && Objects.equals(this.endSessionEndpoint, authConfiguration.endSessionEndpoint) && Objects.equals(this.uiOptions, authConfiguration.uiOptions);
    }

    public int hashCode() {
        return Objects.hash(this.redirectUri, this.postLogoutRedirectUri, this.scope, this.clientId, this.clientSecret, this.authorizationEndpoint, this.tokenEndpoint, this.endSessionEndpoint, this.uiOptions);
    }
}
