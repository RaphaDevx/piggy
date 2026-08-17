package ch.coop.apidia.appGateway.loyalty.api;

import ch.coop.apidia.appGateway.loyalty.ApiCallback;
import ch.coop.apidia.appGateway.loyalty.ApiClient;
import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.ApiResponse;
import ch.coop.apidia.appGateway.loyalty.Configuration;
import ch.coop.apidia.appGateway.loyalty.model.Language;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercard;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeRequest;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeResponse;
import ch.coop.apidia.appGateway.loyalty.model.RestSuccess;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import okhttp3.Call;

/* loaded from: classes3.dex */
public class LoyaltyApi {
    private String localCustomBaseUrl;
    private int localHostIndex;
    private ApiClient localVarApiClient;

    public LoyaltyApi() {
        this(Configuration.getDefaultApiClient());
    }

    public LoyaltyApi(ApiClient apiClient) {
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

    public Call getLoyaltySupercardCall(String str, String str2, Integer num, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        if (num != null) {
            arrayList.addAll(this.localVarApiClient.parameterToPair("transactionDays", num));
        }
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
        return this.localVarApiClient.buildCall(str5, "/protected/loyalty/supercard", "GET", arrayList, arrayList2, null, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call getLoyaltySupercardValidateBeforeCall(String str, String str2, Integer num, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling getLoyaltySupercard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling getLoyaltySupercard(Async)");
        }
        return getLoyaltySupercardCall(str, str2, num, language, str3, apiCallback);
    }

    public LoyaltySupercard getLoyaltySupercard(String str, String str2, Integer num, Language language, String str3) throws ApiException {
        return getLoyaltySupercardWithHttpInfo(str, str2, num, language, str3).getData();
    }

    public ApiResponse<LoyaltySupercard> getLoyaltySupercardWithHttpInfo(String str, String str2, Integer num, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(getLoyaltySupercardValidateBeforeCall(str, str2, num, language, str3, null), new TypeToken<LoyaltySupercard>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.1
        }.getType());
    }

    public Call getLoyaltySupercardAsync(String str, String str2, Integer num, Language language, String str3, ApiCallback<LoyaltySupercard> apiCallback) throws ApiException {
        Call loyaltySupercardValidateBeforeCall = getLoyaltySupercardValidateBeforeCall(str, str2, num, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(loyaltySupercardValidateBeforeCall, new TypeToken<LoyaltySupercard>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.2
        }.getType(), apiCallback);
        return loyaltySupercardValidateBeforeCall;
    }

    public Call getLoyaltySupercardCodeCall(String str, String str2, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/loyalty/supercard/code", "POST", arrayList, arrayList2, loyaltySupercardCodeRequest, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call getLoyaltySupercardCodeValidateBeforeCall(String str, String str2, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling getLoyaltySupercardCode(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling getLoyaltySupercardCode(Async)");
        }
        if (loyaltySupercardCodeRequest == null) {
            throw new ApiException("Missing the required parameter 'loyaltySupercardCodeRequest' when calling getLoyaltySupercardCode(Async)");
        }
        return getLoyaltySupercardCodeCall(str, str2, loyaltySupercardCodeRequest, language, str3, apiCallback);
    }

    public LoyaltySupercardCodeResponse getLoyaltySupercardCode(String str, String str2, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest, Language language, String str3) throws ApiException {
        return getLoyaltySupercardCodeWithHttpInfo(str, str2, loyaltySupercardCodeRequest, language, str3).getData();
    }

    public ApiResponse<LoyaltySupercardCodeResponse> getLoyaltySupercardCodeWithHttpInfo(String str, String str2, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(getLoyaltySupercardCodeValidateBeforeCall(str, str2, loyaltySupercardCodeRequest, language, str3, null), new TypeToken<LoyaltySupercardCodeResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.3
        }.getType());
    }

    public Call getLoyaltySupercardCodeAsync(String str, String str2, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest, Language language, String str3, ApiCallback<LoyaltySupercardCodeResponse> apiCallback) throws ApiException {
        Call loyaltySupercardCodeValidateBeforeCall = getLoyaltySupercardCodeValidateBeforeCall(str, str2, loyaltySupercardCodeRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(loyaltySupercardCodeValidateBeforeCall, new TypeToken<LoyaltySupercardCodeResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.4
        }.getType(), apiCallback);
        return loyaltySupercardCodeValidateBeforeCall;
    }

    public Call postDeviceMediumCall(String str, String str2, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/device/medium", "POST", arrayList, arrayList2, loyaltyDeviceMediumRequestBody, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call postDeviceMediumValidateBeforeCall(String str, String str2, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postDeviceMedium(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postDeviceMedium(Async)");
        }
        if (loyaltyDeviceMediumRequestBody == null) {
            throw new ApiException("Missing the required parameter 'loyaltyDeviceMediumRequestBody' when calling postDeviceMedium(Async)");
        }
        return postDeviceMediumCall(str, str2, loyaltyDeviceMediumRequestBody, str3, apiCallback);
    }

    public RestSuccess postDeviceMedium(String str, String str2, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody, String str3) throws ApiException {
        return postDeviceMediumWithHttpInfo(str, str2, loyaltyDeviceMediumRequestBody, str3).getData();
    }

    public ApiResponse<RestSuccess> postDeviceMediumWithHttpInfo(String str, String str2, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody, String str3) throws ApiException {
        return this.localVarApiClient.execute(postDeviceMediumValidateBeforeCall(str, str2, loyaltyDeviceMediumRequestBody, str3, null), new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.5
        }.getType());
    }

    public Call postDeviceMediumAsync(String str, String str2, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody, String str3, ApiCallback<RestSuccess> apiCallback) throws ApiException {
        Call postDeviceMediumValidateBeforeCall = postDeviceMediumValidateBeforeCall(str, str2, loyaltyDeviceMediumRequestBody, str3, apiCallback);
        this.localVarApiClient.executeAsync(postDeviceMediumValidateBeforeCall, new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi.6
        }.getType(), apiCallback);
        return postDeviceMediumValidateBeforeCall;
    }
}
