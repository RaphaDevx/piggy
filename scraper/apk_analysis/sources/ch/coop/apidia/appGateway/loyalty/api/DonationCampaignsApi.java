package ch.coop.apidia.appGateway.loyalty.api;

import ch.coop.apidia.appGateway.loyalty.ApiCallback;
import ch.coop.apidia.appGateway.loyalty.ApiClient;
import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.ApiResponse;
import ch.coop.apidia.appGateway.loyalty.Configuration;
import ch.coop.apidia.appGateway.loyalty.model.Language;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDonationCampaignsDonateRequestBody;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDonationCampaignsResponse;
import ch.coop.apidia.appGateway.loyalty.model.RestSuccess;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import okhttp3.Call;

/* loaded from: classes3.dex */
public class DonationCampaignsApi {
    private String localCustomBaseUrl;
    private int localHostIndex;
    private ApiClient localVarApiClient;

    public DonationCampaignsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DonationCampaignsApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return this.localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return this.localHostIndex;
    }

    public void setHostIndex(int i) {
        this.localHostIndex = i;
    }

    public String getCustomBaseUrl() {
        return this.localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String str) {
        this.localCustomBaseUrl = str;
    }

    public Call getDonationCampaignsCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        String str4 = this.localCustomBaseUrl;
        if (str4 == null) {
            str4 = null;
        }
        String str5 = str4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (language != null) {
            arrayList.addAll(this.localVarApiClient.parameterToPair("language", language));
        }
        if (str != null) {
            hashMap.put("X-Client-ID", this.localVarApiClient.parameterToString(str));
        }
        if (str3 != null) {
            hashMap.put("X-Device-ID", this.localVarApiClient.parameterToString(str3));
        }
        if (str2 != null) {
            hashMap.put("X-Correlation-ID", this.localVarApiClient.parameterToString(str2));
        }
        String selectHeaderAccept = this.localVarApiClient.selectHeaderAccept(new String[]{MediaType.APPLICATION_JSON, MediaType.TEXT_HTML});
        if (selectHeaderAccept != null) {
            hashMap.put(HttpHeaders.ACCEPT, selectHeaderAccept);
        }
        String selectHeaderContentType = this.localVarApiClient.selectHeaderContentType(new String[0]);
        if (selectHeaderContentType != null) {
            hashMap.put(HttpHeaders.CONTENT_TYPE, selectHeaderContentType);
        }
        return this.localVarApiClient.buildCall(str5, "/protected/donation-campaigns", "GET", arrayList, arrayList2, null, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call getDonationCampaignsValidateBeforeCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling getDonationCampaigns(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling getDonationCampaigns(Async)");
        }
        return getDonationCampaignsCall(str, str2, language, str3, apiCallback);
    }

    public LoyaltyDonationCampaignsResponse getDonationCampaigns(String str, String str2, Language language, String str3) throws ApiException {
        return getDonationCampaignsWithHttpInfo(str, str2, language, str3).getData();
    }

    public ApiResponse<LoyaltyDonationCampaignsResponse> getDonationCampaignsWithHttpInfo(String str, String str2, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(getDonationCampaignsValidateBeforeCall(str, str2, language, str3, null), new TypeToken<LoyaltyDonationCampaignsResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.1
        }.getType());
    }

    public Call getDonationCampaignsAsync(String str, String str2, Language language, String str3, ApiCallback<LoyaltyDonationCampaignsResponse> apiCallback) throws ApiException {
        Call donationCampaignsValidateBeforeCall = getDonationCampaignsValidateBeforeCall(str, str2, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(donationCampaignsValidateBeforeCall, new TypeToken<LoyaltyDonationCampaignsResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.2
        }.getType(), apiCallback);
        return donationCampaignsValidateBeforeCall;
    }

    public Call getDonationCampaignsPublicCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        String str4 = this.localCustomBaseUrl;
        if (str4 == null) {
            str4 = null;
        }
        String str5 = str4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (language != null) {
            arrayList.addAll(this.localVarApiClient.parameterToPair("language", language));
        }
        if (str != null) {
            hashMap.put("X-Client-ID", this.localVarApiClient.parameterToString(str));
        }
        if (str3 != null) {
            hashMap.put("X-Device-ID", this.localVarApiClient.parameterToString(str3));
        }
        if (str2 != null) {
            hashMap.put("X-Correlation-ID", this.localVarApiClient.parameterToString(str2));
        }
        String selectHeaderAccept = this.localVarApiClient.selectHeaderAccept(new String[]{MediaType.APPLICATION_JSON, MediaType.TEXT_HTML});
        if (selectHeaderAccept != null) {
            hashMap.put(HttpHeaders.ACCEPT, selectHeaderAccept);
        }
        String selectHeaderContentType = this.localVarApiClient.selectHeaderContentType(new String[0]);
        if (selectHeaderContentType != null) {
            hashMap.put(HttpHeaders.CONTENT_TYPE, selectHeaderContentType);
        }
        return this.localVarApiClient.buildCall(str5, "/public/donation-campaigns", "GET", arrayList, arrayList2, null, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call getDonationCampaignsPublicValidateBeforeCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling getDonationCampaignsPublic(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling getDonationCampaignsPublic(Async)");
        }
        return getDonationCampaignsPublicCall(str, str2, language, str3, apiCallback);
    }

    public LoyaltyDonationCampaignsResponse getDonationCampaignsPublic(String str, String str2, Language language, String str3) throws ApiException {
        return getDonationCampaignsPublicWithHttpInfo(str, str2, language, str3).getData();
    }

    public ApiResponse<LoyaltyDonationCampaignsResponse> getDonationCampaignsPublicWithHttpInfo(String str, String str2, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(getDonationCampaignsPublicValidateBeforeCall(str, str2, language, str3, null), new TypeToken<LoyaltyDonationCampaignsResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.3
        }.getType());
    }

    public Call getDonationCampaignsPublicAsync(String str, String str2, Language language, String str3, ApiCallback<LoyaltyDonationCampaignsResponse> apiCallback) throws ApiException {
        Call donationCampaignsPublicValidateBeforeCall = getDonationCampaignsPublicValidateBeforeCall(str, str2, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(donationCampaignsPublicValidateBeforeCall, new TypeToken<LoyaltyDonationCampaignsResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.4
        }.getType(), apiCallback);
        return donationCampaignsPublicValidateBeforeCall;
    }

    public Call postDonationCampaignDonateCall(String str, String str2, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        String str4 = this.localCustomBaseUrl;
        if (str4 == null) {
            str4 = null;
        }
        String str5 = str4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (language != null) {
            arrayList.addAll(this.localVarApiClient.parameterToPair("language", language));
        }
        if (str != null) {
            hashMap.put("X-Client-ID", this.localVarApiClient.parameterToString(str));
        }
        if (str3 != null) {
            hashMap.put("X-Device-ID", this.localVarApiClient.parameterToString(str3));
        }
        if (str2 != null) {
            hashMap.put("X-Correlation-ID", this.localVarApiClient.parameterToString(str2));
        }
        String selectHeaderAccept = this.localVarApiClient.selectHeaderAccept(new String[]{MediaType.APPLICATION_JSON, MediaType.TEXT_HTML});
        if (selectHeaderAccept != null) {
            hashMap.put(HttpHeaders.ACCEPT, selectHeaderAccept);
        }
        String selectHeaderContentType = this.localVarApiClient.selectHeaderContentType(new String[]{MediaType.APPLICATION_JSON});
        if (selectHeaderContentType != null) {
            hashMap.put(HttpHeaders.CONTENT_TYPE, selectHeaderContentType);
        }
        return this.localVarApiClient.buildCall(str5, "/protected/donation-campaigns/donate", "POST", arrayList, arrayList2, loyaltyDonationCampaignsDonateRequestBody, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call postDonationCampaignDonateValidateBeforeCall(String str, String str2, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postDonationCampaignDonate(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postDonationCampaignDonate(Async)");
        }
        if (loyaltyDonationCampaignsDonateRequestBody == null) {
            throw new ApiException("Missing the required parameter 'loyaltyDonationCampaignsDonateRequestBody' when calling postDonationCampaignDonate(Async)");
        }
        return postDonationCampaignDonateCall(str, str2, loyaltyDonationCampaignsDonateRequestBody, language, str3, apiCallback);
    }

    public RestSuccess postDonationCampaignDonate(String str, String str2, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody, Language language, String str3) throws ApiException {
        return postDonationCampaignDonateWithHttpInfo(str, str2, loyaltyDonationCampaignsDonateRequestBody, language, str3).getData();
    }

    public ApiResponse<RestSuccess> postDonationCampaignDonateWithHttpInfo(String str, String str2, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(postDonationCampaignDonateValidateBeforeCall(str, str2, loyaltyDonationCampaignsDonateRequestBody, language, str3, null), new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.5
        }.getType());
    }

    public Call postDonationCampaignDonateAsync(String str, String str2, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody, Language language, String str3, ApiCallback<RestSuccess> apiCallback) throws ApiException {
        Call postDonationCampaignDonateValidateBeforeCall = postDonationCampaignDonateValidateBeforeCall(str, str2, loyaltyDonationCampaignsDonateRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(postDonationCampaignDonateValidateBeforeCall, new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.loyalty.api.DonationCampaignsApi.6
        }.getType(), apiCallback);
        return postDonationCampaignDonateValidateBeforeCall;
    }
}
