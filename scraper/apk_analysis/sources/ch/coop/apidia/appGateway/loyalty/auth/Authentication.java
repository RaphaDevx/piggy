package ch.coop.apidia.appGateway.loyalty.auth;

import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.Pair;
import java.net.URI;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface Authentication {
    void applyToParams(List<Pair> list, Map<String, String> map, Map<String, String> map2, String str, String str2, URI uri) throws ApiException;
}
