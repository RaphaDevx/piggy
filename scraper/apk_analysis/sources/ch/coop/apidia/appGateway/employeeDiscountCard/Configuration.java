package ch.coop.apidia.appGateway.employeeDiscountCard;

/* loaded from: classes3.dex */
public class Configuration {
    public static final String VERSION = "1.3.7";
    private static ApiClient defaultApiClient = new ApiClient();

    public static ApiClient getDefaultApiClient() {
        return defaultApiClient;
    }

    public static void setDefaultApiClient(ApiClient apiClient) {
        defaultApiClient = apiClient;
    }
}
