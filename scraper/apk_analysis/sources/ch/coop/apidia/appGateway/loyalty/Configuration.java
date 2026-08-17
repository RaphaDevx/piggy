package ch.coop.apidia.appGateway.loyalty;

/* loaded from: classes3.dex */
public class Configuration {
    public static final String VERSION = "1.5.8";
    private static ApiClient defaultApiClient = new ApiClient();

    public static ApiClient getDefaultApiClient() {
        return defaultApiClient;
    }

    public static void setDefaultApiClient(ApiClient apiClient) {
        defaultApiClient = apiClient;
    }
}
