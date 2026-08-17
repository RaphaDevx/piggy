package ch.coop.apidia.appGateway.employeeDiscountCard.auth;

import ch.coop.apidia.appGateway.employeeDiscountCard.ApiException;
import ch.coop.apidia.appGateway.employeeDiscountCard.Pair;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.TokenResponse;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class HttpBearerAuth implements Authentication {
    private String bearerToken;
    private final String scheme;

    public HttpBearerAuth(String str) {
        this.scheme = str;
    }

    public String getBearerToken() {
        return this.bearerToken;
    }

    public void setBearerToken(String str) {
        this.bearerToken = str;
    }

    @Override // ch.coop.apidia.appGateway.employeeDiscountCard.auth.Authentication
    public void applyToParams(List<Pair> list, Map<String, String> map, Map<String, String> map2, String str, String str2, URI uri) throws ApiException {
        String str3;
        if (this.bearerToken == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (this.scheme != null) {
            str3 = upperCaseBearer(this.scheme) + StringUtils.SPACE;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(this.bearerToken);
        map.put(HttpHeaders.AUTHORIZATION, sb.toString());
    }

    private static String upperCaseBearer(String str) {
        return AuthorizationResponse.TOKEN_TYPE_BEARER.equalsIgnoreCase(str) ? TokenResponse.TOKEN_TYPE_BEARER : str;
    }
}
