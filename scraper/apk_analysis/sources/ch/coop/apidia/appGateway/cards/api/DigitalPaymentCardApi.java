package ch.coop.apidia.appGateway.cards.api;

import ch.coop.apidia.appGateway.cards.ApiCallback;
import ch.coop.apidia.appGateway.cards.ApiClient;
import ch.coop.apidia.appGateway.cards.ApiException;
import ch.coop.apidia.appGateway.cards.ApiResponse;
import ch.coop.apidia.appGateway.cards.Configuration;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithGiftcardRequest;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithGiftcardResponse;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardCredentials;
import ch.coop.apidia.appGateway.cards.model.Language;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import okhttp3.Call;

/* loaded from: classes3.dex */
public class DigitalPaymentCardApi {
    private String localCustomBaseUrl;
    private int localHostIndex;
    private ApiClient localVarApiClient;

    public DigitalPaymentCardApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DigitalPaymentCardApi(ApiClient apiClient) {
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

    public Call dpcLoadWithEmployeeGiftcardCall(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-employee-giftcard", "POST", arrayList, arrayList2, cardsGiftcardCredentials, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithEmployeeGiftcardValidateBeforeCall(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithEmployeeGiftcard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithEmployeeGiftcard(Async)");
        }
        if (cardsGiftcardCredentials == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardCredentials' when calling dpcLoadWithEmployeeGiftcard(Async)");
        }
        return dpcLoadWithEmployeeGiftcardCall(str, str2, cardsGiftcardCredentials, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse dpcLoadWithEmployeeGiftcard(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3) throws ApiException {
        return dpcLoadWithEmployeeGiftcardWithHttpInfo(str, str2, cardsGiftcardCredentials, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> dpcLoadWithEmployeeGiftcardWithHttpInfo(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithEmployeeGiftcardValidateBeforeCall(str, str2, cardsGiftcardCredentials, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.1
        }.getType());
    }

    public Call dpcLoadWithEmployeeGiftcardAsync(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> apiCallback) throws ApiException {
        Call dpcLoadWithEmployeeGiftcardValidateBeforeCall = dpcLoadWithEmployeeGiftcardValidateBeforeCall(str, str2, cardsGiftcardCredentials, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithEmployeeGiftcardValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.2
        }.getType(), apiCallback);
        return dpcLoadWithEmployeeGiftcardValidateBeforeCall;
    }

    public Call dpcLoadWithEmployeeGiftcardPreflightCall(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-employee-giftcard-preflight", "POST", arrayList, arrayList2, cardsGiftcardCredentials, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithEmployeeGiftcardPreflight(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithEmployeeGiftcardPreflight(Async)");
        }
        if (cardsGiftcardCredentials == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardCredentials' when calling dpcLoadWithEmployeeGiftcardPreflight(Async)");
        }
        return dpcLoadWithEmployeeGiftcardPreflightCall(str, str2, cardsGiftcardCredentials, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse dpcLoadWithEmployeeGiftcardPreflight(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3) throws ApiException {
        return dpcLoadWithEmployeeGiftcardPreflightWithHttpInfo(str, str2, cardsGiftcardCredentials, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> dpcLoadWithEmployeeGiftcardPreflightWithHttpInfo(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall(str, str2, cardsGiftcardCredentials, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.3
        }.getType());
    }

    public Call dpcLoadWithEmployeeGiftcardPreflightAsync(String str, String str2, CardsGiftcardCredentials cardsGiftcardCredentials, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> apiCallback) throws ApiException {
        Call dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall = dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall(str, str2, cardsGiftcardCredentials, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.4
        }.getType(), apiCallback);
        return dpcLoadWithEmployeeGiftcardPreflightValidateBeforeCall;
    }

    public Call dpcLoadWithGiftcardCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-giftcard", "POST", arrayList, arrayList2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithGiftcardValidateBeforeCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithGiftcard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithGiftcard(Async)");
        }
        if (cardsAccountDigitalPaymentCardLoadWithGiftcardRequest == null) {
            throw new ApiException("Missing the required parameter 'cardsAccountDigitalPaymentCardLoadWithGiftcardRequest' when calling dpcLoadWithGiftcard(Async)");
        }
        return dpcLoadWithGiftcardCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse dpcLoadWithGiftcard(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3) throws ApiException {
        return dpcLoadWithGiftcardWithHttpInfo(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> dpcLoadWithGiftcardWithHttpInfo(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithGiftcardValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.5
        }.getType());
    }

    public Call dpcLoadWithGiftcardAsync(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> apiCallback) throws ApiException {
        Call dpcLoadWithGiftcardValidateBeforeCall = dpcLoadWithGiftcardValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithGiftcardValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.6
        }.getType(), apiCallback);
        return dpcLoadWithGiftcardValidateBeforeCall;
    }

    public Call dpcLoadWithGiftcardPreflightCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-giftcard-preflight", "POST", arrayList, arrayList2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithGiftcardPreflightValidateBeforeCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithGiftcardPreflight(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithGiftcardPreflight(Async)");
        }
        if (cardsAccountDigitalPaymentCardLoadWithGiftcardRequest == null) {
            throw new ApiException("Missing the required parameter 'cardsAccountDigitalPaymentCardLoadWithGiftcardRequest' when calling dpcLoadWithGiftcardPreflight(Async)");
        }
        return dpcLoadWithGiftcardPreflightCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse dpcLoadWithGiftcardPreflight(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3) throws ApiException {
        return dpcLoadWithGiftcardPreflightWithHttpInfo(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> dpcLoadWithGiftcardPreflightWithHttpInfo(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithGiftcardPreflightValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.7
        }.getType());
    }

    public Call dpcLoadWithGiftcardPreflightAsync(String str, String str2, CardsAccountDigitalPaymentCardLoadWithGiftcardRequest cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse> apiCallback) throws ApiException {
        Call dpcLoadWithGiftcardPreflightValidateBeforeCall = dpcLoadWithGiftcardPreflightValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithGiftcardRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithGiftcardPreflightValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.8
        }.getType(), apiCallback);
        return dpcLoadWithGiftcardPreflightValidateBeforeCall;
    }

    public Call dpcLoadWithSuperpointsCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-superpoints", "POST", arrayList, arrayList2, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithSuperpointsValidateBeforeCall(String str, String str2, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithSuperpoints(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithSuperpoints(Async)");
        }
        if (cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest == null) {
            throw new ApiException("Missing the required parameter 'cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest' when calling dpcLoadWithSuperpoints(Async)");
        }
        return dpcLoadWithSuperpointsCall(str, str2, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse dpcLoadWithSuperpoints(String str, String str2, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, Language language, String str3) throws ApiException {
        return dpcLoadWithSuperpointsWithHttpInfo(str, str2, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse> dpcLoadWithSuperpointsWithHttpInfo(String str, String str2, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithSuperpointsValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.9
        }.getType());
    }

    public Call dpcLoadWithSuperpointsAsync(String str, String str2, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse> apiCallback) throws ApiException {
        Call dpcLoadWithSuperpointsValidateBeforeCall = dpcLoadWithSuperpointsValidateBeforeCall(str, str2, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithSuperpointsValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.10
        }.getType(), apiCallback);
        return dpcLoadWithSuperpointsValidateBeforeCall;
    }

    public Call dpcLoadWithSuperpointsStatusCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/digital-payment-card/load-with-superpoints-status", "GET", arrayList, arrayList2, null, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call dpcLoadWithSuperpointsStatusValidateBeforeCall(String str, String str2, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling dpcLoadWithSuperpointsStatus(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling dpcLoadWithSuperpointsStatus(Async)");
        }
        return dpcLoadWithSuperpointsStatusCall(str, str2, language, str3, apiCallback);
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse dpcLoadWithSuperpointsStatus(String str, String str2, Language language, String str3) throws ApiException {
        return dpcLoadWithSuperpointsStatusWithHttpInfo(str, str2, language, str3).getData();
    }

    public ApiResponse<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse> dpcLoadWithSuperpointsStatusWithHttpInfo(String str, String str2, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(dpcLoadWithSuperpointsStatusValidateBeforeCall(str, str2, language, str3, null), new TypeToken<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.11
        }.getType());
    }

    public Call dpcLoadWithSuperpointsStatusAsync(String str, String str2, Language language, String str3, ApiCallback<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse> apiCallback) throws ApiException {
        Call dpcLoadWithSuperpointsStatusValidateBeforeCall = dpcLoadWithSuperpointsStatusValidateBeforeCall(str, str2, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(dpcLoadWithSuperpointsStatusValidateBeforeCall, new TypeToken<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.DigitalPaymentCardApi.12
        }.getType(), apiCallback);
        return dpcLoadWithSuperpointsStatusValidateBeforeCall;
    }
}
