package ch.coop.apidia.appGateway.cards.api;

import ch.coop.apidia.appGateway.cards.ApiCallback;
import ch.coop.apidia.appGateway.cards.ApiClient;
import ch.coop.apidia.appGateway.cards.ApiException;
import ch.coop.apidia.appGateway.cards.ApiResponse;
import ch.coop.apidia.appGateway.cards.Configuration;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardLinkRequestBody;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardListRequestBody;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardResponse;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardUnlinkRequestBody;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardValidateNumberRequestBody;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardValidatePinRequestBody;
import ch.coop.apidia.appGateway.cards.model.Language;
import ch.coop.apidia.appGateway.cards.model.RestSuccess;
import ch.coop.apidia.appGateway.cards.model.SupercardPayLinkedSavedPaymentMethodDeleteResponse;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import okhttp3.Call;

/* loaded from: classes3.dex */
public class GiftcardApi {
    private String localCustomBaseUrl;
    private int localHostIndex;
    private ApiClient localVarApiClient;

    public GiftcardApi() {
        this(Configuration.getDefaultApiClient());
    }

    public GiftcardApi(ApiClient apiClient) {
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

    public Call giftcardValidateNumberCall(String str, String str2, CardsGiftcardValidateNumberRequestBody cardsGiftcardValidateNumberRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/giftcards/validate-number", "POST", arrayList, arrayList2, cardsGiftcardValidateNumberRequestBody, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call giftcardValidateNumberValidateBeforeCall(String str, String str2, CardsGiftcardValidateNumberRequestBody cardsGiftcardValidateNumberRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling giftcardValidateNumber(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling giftcardValidateNumber(Async)");
        }
        if (cardsGiftcardValidateNumberRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardValidateNumberRequestBody' when calling giftcardValidateNumber(Async)");
        }
        return giftcardValidateNumberCall(str, str2, cardsGiftcardValidateNumberRequestBody, language, str3, apiCallback);
    }

    public RestSuccess giftcardValidateNumber(String str, String str2, CardsGiftcardValidateNumberRequestBody cardsGiftcardValidateNumberRequestBody, Language language, String str3) throws ApiException {
        return giftcardValidateNumberWithHttpInfo(str, str2, cardsGiftcardValidateNumberRequestBody, language, str3).getData();
    }

    public ApiResponse<RestSuccess> giftcardValidateNumberWithHttpInfo(String str, String str2, CardsGiftcardValidateNumberRequestBody cardsGiftcardValidateNumberRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(giftcardValidateNumberValidateBeforeCall(str, str2, cardsGiftcardValidateNumberRequestBody, language, str3, null), new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.1
        }.getType());
    }

    public Call giftcardValidateNumberAsync(String str, String str2, CardsGiftcardValidateNumberRequestBody cardsGiftcardValidateNumberRequestBody, Language language, String str3, ApiCallback<RestSuccess> apiCallback) throws ApiException {
        Call giftcardValidateNumberValidateBeforeCall = giftcardValidateNumberValidateBeforeCall(str, str2, cardsGiftcardValidateNumberRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(giftcardValidateNumberValidateBeforeCall, new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.2
        }.getType(), apiCallback);
        return giftcardValidateNumberValidateBeforeCall;
    }

    public Call giftcardValidatePinCall(String str, String str2, CardsGiftcardValidatePinRequestBody cardsGiftcardValidatePinRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/giftcards/validate-pin", "POST", arrayList, arrayList2, cardsGiftcardValidatePinRequestBody, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call giftcardValidatePinValidateBeforeCall(String str, String str2, CardsGiftcardValidatePinRequestBody cardsGiftcardValidatePinRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling giftcardValidatePin(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling giftcardValidatePin(Async)");
        }
        if (cardsGiftcardValidatePinRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardValidatePinRequestBody' when calling giftcardValidatePin(Async)");
        }
        return giftcardValidatePinCall(str, str2, cardsGiftcardValidatePinRequestBody, language, str3, apiCallback);
    }

    public RestSuccess giftcardValidatePin(String str, String str2, CardsGiftcardValidatePinRequestBody cardsGiftcardValidatePinRequestBody, Language language, String str3) throws ApiException {
        return giftcardValidatePinWithHttpInfo(str, str2, cardsGiftcardValidatePinRequestBody, language, str3).getData();
    }

    public ApiResponse<RestSuccess> giftcardValidatePinWithHttpInfo(String str, String str2, CardsGiftcardValidatePinRequestBody cardsGiftcardValidatePinRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(giftcardValidatePinValidateBeforeCall(str, str2, cardsGiftcardValidatePinRequestBody, language, str3, null), new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.3
        }.getType());
    }

    public Call giftcardValidatePinAsync(String str, String str2, CardsGiftcardValidatePinRequestBody cardsGiftcardValidatePinRequestBody, Language language, String str3, ApiCallback<RestSuccess> apiCallback) throws ApiException {
        Call giftcardValidatePinValidateBeforeCall = giftcardValidatePinValidateBeforeCall(str, str2, cardsGiftcardValidatePinRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(giftcardValidatePinValidateBeforeCall, new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.4
        }.getType(), apiCallback);
        return giftcardValidatePinValidateBeforeCall;
    }

    public Call linkGiftcardCall(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/giftcards/link", "POST", arrayList, arrayList2, cardsGiftcardLinkRequestBody, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call linkGiftcardValidateBeforeCall(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling linkGiftcard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling linkGiftcard(Async)");
        }
        if (cardsGiftcardLinkRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardLinkRequestBody' when calling linkGiftcard(Async)");
        }
        return linkGiftcardCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, apiCallback);
    }

    public CardsGiftcard linkGiftcard(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3) throws ApiException {
        return linkGiftcardWithHttpInfo(str, str2, cardsGiftcardLinkRequestBody, language, str3).getData();
    }

    public ApiResponse<CardsGiftcard> linkGiftcardWithHttpInfo(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(linkGiftcardValidateBeforeCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, null), new TypeToken<CardsGiftcard>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.5
        }.getType());
    }

    public Call linkGiftcardAsync(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback<CardsGiftcard> apiCallback) throws ApiException {
        Call linkGiftcardValidateBeforeCall = linkGiftcardValidateBeforeCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(linkGiftcardValidateBeforeCall, new TypeToken<CardsGiftcard>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.6
        }.getType(), apiCallback);
        return linkGiftcardValidateBeforeCall;
    }

    public Call postGiftcardsCall(String str, String str2, Language language, String str3, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/giftcards", "POST", arrayList, arrayList2, cardsGiftcardListRequestBody, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call postGiftcardsValidateBeforeCall(String str, String str2, Language language, String str3, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postGiftcards(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postGiftcards(Async)");
        }
        return postGiftcardsCall(str, str2, language, str3, cardsGiftcardListRequestBody, apiCallback);
    }

    public CardsGiftcardResponse postGiftcards(String str, String str2, Language language, String str3, CardsGiftcardListRequestBody cardsGiftcardListRequestBody) throws ApiException {
        return postGiftcardsWithHttpInfo(str, str2, language, str3, cardsGiftcardListRequestBody).getData();
    }

    public ApiResponse<CardsGiftcardResponse> postGiftcardsWithHttpInfo(String str, String str2, Language language, String str3, CardsGiftcardListRequestBody cardsGiftcardListRequestBody) throws ApiException {
        return this.localVarApiClient.execute(postGiftcardsValidateBeforeCall(str, str2, language, str3, cardsGiftcardListRequestBody, null), new TypeToken<CardsGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.7
        }.getType());
    }

    public Call postGiftcardsAsync(String str, String str2, Language language, String str3, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, ApiCallback<CardsGiftcardResponse> apiCallback) throws ApiException {
        Call postGiftcardsValidateBeforeCall = postGiftcardsValidateBeforeCall(str, str2, language, str3, cardsGiftcardListRequestBody, apiCallback);
        this.localVarApiClient.executeAsync(postGiftcardsValidateBeforeCall, new TypeToken<CardsGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.8
        }.getType(), apiCallback);
        return postGiftcardsValidateBeforeCall;
    }

    public Call postGiftcardsPublicCall(String str, String str2, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/giftcards", "POST", arrayList, arrayList2, cardsGiftcardListRequestBody, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call postGiftcardsPublicValidateBeforeCall(String str, String str2, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postGiftcardsPublic(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postGiftcardsPublic(Async)");
        }
        if (cardsGiftcardListRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardListRequestBody' when calling postGiftcardsPublic(Async)");
        }
        return postGiftcardsPublicCall(str, str2, cardsGiftcardListRequestBody, language, str3, apiCallback);
    }

    public CardsGiftcardResponse postGiftcardsPublic(String str, String str2, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, Language language, String str3) throws ApiException {
        return postGiftcardsPublicWithHttpInfo(str, str2, cardsGiftcardListRequestBody, language, str3).getData();
    }

    public ApiResponse<CardsGiftcardResponse> postGiftcardsPublicWithHttpInfo(String str, String str2, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(postGiftcardsPublicValidateBeforeCall(str, str2, cardsGiftcardListRequestBody, language, str3, null), new TypeToken<CardsGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.9
        }.getType());
    }

    public Call postGiftcardsPublicAsync(String str, String str2, CardsGiftcardListRequestBody cardsGiftcardListRequestBody, Language language, String str3, ApiCallback<CardsGiftcardResponse> apiCallback) throws ApiException {
        Call postGiftcardsPublicValidateBeforeCall = postGiftcardsPublicValidateBeforeCall(str, str2, cardsGiftcardListRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(postGiftcardsPublicValidateBeforeCall, new TypeToken<CardsGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.10
        }.getType(), apiCallback);
        return postGiftcardsPublicValidateBeforeCall;
    }

    public Call publicLinkGiftcardCall(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/giftcards/link", "POST", arrayList, arrayList2, cardsGiftcardLinkRequestBody, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call publicLinkGiftcardValidateBeforeCall(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling publicLinkGiftcard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling publicLinkGiftcard(Async)");
        }
        if (cardsGiftcardLinkRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardLinkRequestBody' when calling publicLinkGiftcard(Async)");
        }
        return publicLinkGiftcardCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, apiCallback);
    }

    public CardsGiftcard publicLinkGiftcard(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3) throws ApiException {
        return publicLinkGiftcardWithHttpInfo(str, str2, cardsGiftcardLinkRequestBody, language, str3).getData();
    }

    public ApiResponse<CardsGiftcard> publicLinkGiftcardWithHttpInfo(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(publicLinkGiftcardValidateBeforeCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, null), new TypeToken<CardsGiftcard>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.11
        }.getType());
    }

    public Call publicLinkGiftcardAsync(String str, String str2, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody, Language language, String str3, ApiCallback<CardsGiftcard> apiCallback) throws ApiException {
        Call publicLinkGiftcardValidateBeforeCall = publicLinkGiftcardValidateBeforeCall(str, str2, cardsGiftcardLinkRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(publicLinkGiftcardValidateBeforeCall, new TypeToken<CardsGiftcard>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.12
        }.getType(), apiCallback);
        return publicLinkGiftcardValidateBeforeCall;
    }

    public Call unlinkGiftcardCall(String str, String str2, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/giftcards/unlink", "POST", arrayList, arrayList2, cardsGiftcardUnlinkRequestBody, hashMap, hashMap2, hashMap3, new String[]{"openId"}, apiCallback);
    }

    private Call unlinkGiftcardValidateBeforeCall(String str, String str2, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling unlinkGiftcard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling unlinkGiftcard(Async)");
        }
        if (cardsGiftcardUnlinkRequestBody == null) {
            throw new ApiException("Missing the required parameter 'cardsGiftcardUnlinkRequestBody' when calling unlinkGiftcard(Async)");
        }
        return unlinkGiftcardCall(str, str2, cardsGiftcardUnlinkRequestBody, language, str3, apiCallback);
    }

    public SupercardPayLinkedSavedPaymentMethodDeleteResponse unlinkGiftcard(String str, String str2, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody, Language language, String str3) throws ApiException {
        return unlinkGiftcardWithHttpInfo(str, str2, cardsGiftcardUnlinkRequestBody, language, str3).getData();
    }

    public ApiResponse<SupercardPayLinkedSavedPaymentMethodDeleteResponse> unlinkGiftcardWithHttpInfo(String str, String str2, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(unlinkGiftcardValidateBeforeCall(str, str2, cardsGiftcardUnlinkRequestBody, language, str3, null), new TypeToken<SupercardPayLinkedSavedPaymentMethodDeleteResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.13
        }.getType());
    }

    public Call unlinkGiftcardAsync(String str, String str2, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody, Language language, String str3, ApiCallback<SupercardPayLinkedSavedPaymentMethodDeleteResponse> apiCallback) throws ApiException {
        Call unlinkGiftcardValidateBeforeCall = unlinkGiftcardValidateBeforeCall(str, str2, cardsGiftcardUnlinkRequestBody, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(unlinkGiftcardValidateBeforeCall, new TypeToken<SupercardPayLinkedSavedPaymentMethodDeleteResponse>() { // from class: ch.coop.apidia.appGateway.cards.api.GiftcardApi.14
        }.getType(), apiCallback);
        return unlinkGiftcardValidateBeforeCall;
    }
}
