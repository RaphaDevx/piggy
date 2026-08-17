package ch.coop.apidia.appGateway.loyalty.auth;

import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.Pair;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class ApiKeyAuth implements Authentication {
    private String apiKey;
    private String apiKeyPrefix;
    private final String location;
    private final String paramName;

    public ApiKeyAuth(String str, String str2) {
        this.location = str;
        this.paramName = str2;
    }

    public String getLocation() {
        return this.location;
    }

    public String getParamName() {
        return this.paramName;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public String getApiKeyPrefix() {
        return this.apiKeyPrefix;
    }

    public void setApiKeyPrefix(String str) {
        this.apiKeyPrefix = str;
    }

    @Override // ch.coop.apidia.appGateway.loyalty.auth.Authentication
    public void applyToParams(List<Pair> list, Map<String, String> map, Map<String, String> map2, String str, String str2, URI uri) throws ApiException {
        String str3 = this.apiKey;
        if (str3 == null) {
            return;
        }
        if (this.apiKeyPrefix != null) {
            str3 = this.apiKeyPrefix + StringUtils.SPACE + this.apiKey;
        }
        if ("query".equals(this.location)) {
            list.add(new Pair(this.paramName, str3));
        } else if ("header".equals(this.location)) {
            map.put(this.paramName, str3);
        } else if ("cookie".equals(this.location)) {
            map2.put(this.paramName, str3);
        }
    }
}
