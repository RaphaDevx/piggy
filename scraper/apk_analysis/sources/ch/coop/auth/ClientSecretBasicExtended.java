package ch.coop.auth;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.Preconditions;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.internal.UriUtil;

/* loaded from: classes3.dex */
public class ClientSecretBasicExtended implements ClientAuthentication {
    public static final String NAME = "client_secret_basic";
    private final String mClientSecret;

    public ClientSecretBasicExtended(String str) {
        this.mClientSecret = (String) Preconditions.checkNotNull(str, "mClientSecret cannot be null");
    }

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestHeaders(String str) {
        return Collections.singletonMap(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeToString((UriUtil.formUrlEncodeValue(str) + ":" + UriUtil.formUrlEncodeValue(this.mClientSecret)).getBytes(StandardCharsets.UTF_8), 2));
    }

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestParameters(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(TokenRequest.PARAM_CLIENT_ID, str);
        return hashMap;
    }
}
