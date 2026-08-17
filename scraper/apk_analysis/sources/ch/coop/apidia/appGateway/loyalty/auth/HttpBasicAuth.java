package ch.coop.apidia.appGateway.loyalty.auth;

import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.Pair;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import okhttp3.Credentials;

/* loaded from: classes3.dex */
public class HttpBasicAuth implements Authentication {
    private String password;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    @Override // ch.coop.apidia.appGateway.loyalty.auth.Authentication
    public void applyToParams(List<Pair> list, Map<String, String> map, Map<String, String> map2, String str, String str2, URI uri) throws ApiException {
        String str3 = this.username;
        if (str3 == null && this.password == null) {
            return;
        }
        if (str3 == null) {
            str3 = "";
        }
        String str4 = this.password;
        map.put(HttpHeaders.AUTHORIZATION, Credentials.basic(str3, str4 != null ? str4 : ""));
    }
}
