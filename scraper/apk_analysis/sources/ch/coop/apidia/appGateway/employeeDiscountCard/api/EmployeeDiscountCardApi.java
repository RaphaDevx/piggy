package ch.coop.apidia.appGateway.employeeDiscountCard.api;

import ch.coop.apidia.appGateway.employeeDiscountCard.ApiCallback;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiClient;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiException;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiResponse;
import ch.coop.apidia.appGateway.employeeDiscountCard.Configuration;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCreateRequest;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardProtectedDeleteRequest;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Language;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.RestSuccess;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.SupercardPayLinkedSavedPaymentMethodDeleteResponse;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import okhttp3.Call;

/* loaded from: classes3.dex */
public class EmployeeDiscountCardApi {
    private String localCustomBaseUrl;
    private int localHostIndex;
    private ApiClient localVarApiClient;

    public EmployeeDiscountCardApi() {
        this(Configuration.getDefaultApiClient());
    }

    public EmployeeDiscountCardApi(ApiClient apiClient) {
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

    public Call postEmployeeDiscountCardCall(String str, String str2, EmployeeDiscountCardCredentials employeeDiscountCardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/employee-discount-card", "POST", arrayList, arrayList2, employeeDiscountCardCredentials, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call postEmployeeDiscountCardValidateBeforeCall(String str, String str2, EmployeeDiscountCardCredentials employeeDiscountCardCredentials, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postEmployeeDiscountCard(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postEmployeeDiscountCard(Async)");
        }
        if (employeeDiscountCardCredentials == null) {
            throw new ApiException("Missing the required parameter 'employeeDiscountCardCredentials' when calling postEmployeeDiscountCard(Async)");
        }
        return postEmployeeDiscountCardCall(str, str2, employeeDiscountCardCredentials, language, str3, apiCallback);
    }

    public EmployeeDiscountCard postEmployeeDiscountCard(String str, String str2, EmployeeDiscountCardCredentials employeeDiscountCardCredentials, Language language, String str3) throws ApiException {
        return postEmployeeDiscountCardWithHttpInfo(str, str2, employeeDiscountCardCredentials, language, str3).getData();
    }

    public ApiResponse<EmployeeDiscountCard> postEmployeeDiscountCardWithHttpInfo(String str, String str2, EmployeeDiscountCardCredentials employeeDiscountCardCredentials, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(postEmployeeDiscountCardValidateBeforeCall(str, str2, employeeDiscountCardCredentials, language, str3, null), new TypeToken<EmployeeDiscountCard>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.1
        }.getType());
    }

    public Call postEmployeeDiscountCardAsync(String str, String str2, EmployeeDiscountCardCredentials employeeDiscountCardCredentials, Language language, String str3, ApiCallback<EmployeeDiscountCard> apiCallback) throws ApiException {
        Call postEmployeeDiscountCardValidateBeforeCall = postEmployeeDiscountCardValidateBeforeCall(str, str2, employeeDiscountCardCredentials, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(postEmployeeDiscountCardValidateBeforeCall, new TypeToken<EmployeeDiscountCard>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.2
        }.getType(), apiCallback);
        return postEmployeeDiscountCardValidateBeforeCall;
    }

    public Call postEmployeeDiscountCardCreateCall(String str, String str2, EmployeeDiscountCardCreateRequest employeeDiscountCardCreateRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/public/employee-discount-card/create", "POST", arrayList, arrayList2, employeeDiscountCardCreateRequest, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call postEmployeeDiscountCardCreateValidateBeforeCall(String str, String str2, EmployeeDiscountCardCreateRequest employeeDiscountCardCreateRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postEmployeeDiscountCardCreate(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postEmployeeDiscountCardCreate(Async)");
        }
        if (employeeDiscountCardCreateRequest == null) {
            throw new ApiException("Missing the required parameter 'employeeDiscountCardCreateRequest' when calling postEmployeeDiscountCardCreate(Async)");
        }
        return postEmployeeDiscountCardCreateCall(str, str2, employeeDiscountCardCreateRequest, language, str3, apiCallback);
    }

    public EmployeeDiscountCard postEmployeeDiscountCardCreate(String str, String str2, EmployeeDiscountCardCreateRequest employeeDiscountCardCreateRequest, Language language, String str3) throws ApiException {
        return postEmployeeDiscountCardCreateWithHttpInfo(str, str2, employeeDiscountCardCreateRequest, language, str3).getData();
    }

    public ApiResponse<EmployeeDiscountCard> postEmployeeDiscountCardCreateWithHttpInfo(String str, String str2, EmployeeDiscountCardCreateRequest employeeDiscountCardCreateRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(postEmployeeDiscountCardCreateValidateBeforeCall(str, str2, employeeDiscountCardCreateRequest, language, str3, null), new TypeToken<EmployeeDiscountCard>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.3
        }.getType());
    }

    public Call postEmployeeDiscountCardCreateAsync(String str, String str2, EmployeeDiscountCardCreateRequest employeeDiscountCardCreateRequest, Language language, String str3, ApiCallback<EmployeeDiscountCard> apiCallback) throws ApiException {
        Call postEmployeeDiscountCardCreateValidateBeforeCall = postEmployeeDiscountCardCreateValidateBeforeCall(str, str2, employeeDiscountCardCreateRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(postEmployeeDiscountCardCreateValidateBeforeCall, new TypeToken<EmployeeDiscountCard>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.4
        }.getType(), apiCallback);
        return postEmployeeDiscountCardCreateValidateBeforeCall;
    }

    public Call postEmployeeDiscountCardDeleteCall(String str, String str2, String str3, String str4, Language language, String str5, ApiCallback apiCallback) throws ApiException {
        String str6 = this.localCustomBaseUrl;
        if (str6 == null) {
            str6 = null;
        }
        String str7 = str6;
        String replace = "/public/employee-discount-card/delete/{cardNr}/{cardAuthCode}".replace("{cardAuthCode}", this.localVarApiClient.escapeString(str.toString())).replace("{cardNr}", this.localVarApiClient.escapeString(str2.toString()));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (language != null) {
            arrayList.addAll(this.localVarApiClient.parameterToPair("language", language));
        }
        if (str3 != null) {
            hashMap.put("X-Client-ID", this.localVarApiClient.parameterToString(str3));
        }
        if (str5 != null) {
            hashMap.put("X-Device-ID", this.localVarApiClient.parameterToString(str5));
        }
        if (str4 != null) {
            hashMap.put("X-Correlation-ID", this.localVarApiClient.parameterToString(str4));
        }
        String selectHeaderAccept = this.localVarApiClient.selectHeaderAccept(new String[]{MediaType.APPLICATION_JSON, MediaType.TEXT_HTML});
        if (selectHeaderAccept != null) {
            hashMap.put(HttpHeaders.ACCEPT, selectHeaderAccept);
        }
        String selectHeaderContentType = this.localVarApiClient.selectHeaderContentType(new String[0]);
        if (selectHeaderContentType != null) {
            hashMap.put(HttpHeaders.CONTENT_TYPE, selectHeaderContentType);
        }
        return this.localVarApiClient.buildCall(str7, replace, HttpMethod.DELETE, arrayList, arrayList2, null, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call postEmployeeDiscountCardDeleteValidateBeforeCall(String str, String str2, String str3, String str4, Language language, String str5, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'cardAuthCode' when calling postEmployeeDiscountCardDelete(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'cardNr' when calling postEmployeeDiscountCardDelete(Async)");
        }
        if (str3 == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postEmployeeDiscountCardDelete(Async)");
        }
        if (str4 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postEmployeeDiscountCardDelete(Async)");
        }
        return postEmployeeDiscountCardDeleteCall(str, str2, str3, str4, language, str5, apiCallback);
    }

    public RestSuccess postEmployeeDiscountCardDelete(String str, String str2, String str3, String str4, Language language, String str5) throws ApiException {
        return postEmployeeDiscountCardDeleteWithHttpInfo(str, str2, str3, str4, language, str5).getData();
    }

    public ApiResponse<RestSuccess> postEmployeeDiscountCardDeleteWithHttpInfo(String str, String str2, String str3, String str4, Language language, String str5) throws ApiException {
        return this.localVarApiClient.execute(postEmployeeDiscountCardDeleteValidateBeforeCall(str, str2, str3, str4, language, str5, null), new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.5
        }.getType());
    }

    public Call postEmployeeDiscountCardDeleteAsync(String str, String str2, String str3, String str4, Language language, String str5, ApiCallback<RestSuccess> apiCallback) throws ApiException {
        Call postEmployeeDiscountCardDeleteValidateBeforeCall = postEmployeeDiscountCardDeleteValidateBeforeCall(str, str2, str3, str4, language, str5, apiCallback);
        this.localVarApiClient.executeAsync(postEmployeeDiscountCardDeleteValidateBeforeCall, new TypeToken<RestSuccess>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.6
        }.getType(), apiCallback);
        return postEmployeeDiscountCardDeleteValidateBeforeCall;
    }

    public Call postEmployeeDiscountCardProtectedDeleteCall(String str, String str2, EmployeeDiscountCardProtectedDeleteRequest employeeDiscountCardProtectedDeleteRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
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
        return this.localVarApiClient.buildCall(str5, "/protected/employee-discount-card/delete", "POST", arrayList, arrayList2, employeeDiscountCardProtectedDeleteRequest, hashMap, hashMap2, hashMap3, new String[0], apiCallback);
    }

    private Call postEmployeeDiscountCardProtectedDeleteValidateBeforeCall(String str, String str2, EmployeeDiscountCardProtectedDeleteRequest employeeDiscountCardProtectedDeleteRequest, Language language, String str3, ApiCallback apiCallback) throws ApiException {
        if (str == null) {
            throw new ApiException("Missing the required parameter 'xClientID' when calling postEmployeeDiscountCardProtectedDelete(Async)");
        }
        if (str2 == null) {
            throw new ApiException("Missing the required parameter 'xCorrelationID' when calling postEmployeeDiscountCardProtectedDelete(Async)");
        }
        if (employeeDiscountCardProtectedDeleteRequest == null) {
            throw new ApiException("Missing the required parameter 'employeeDiscountCardProtectedDeleteRequest' when calling postEmployeeDiscountCardProtectedDelete(Async)");
        }
        return postEmployeeDiscountCardProtectedDeleteCall(str, str2, employeeDiscountCardProtectedDeleteRequest, language, str3, apiCallback);
    }

    public SupercardPayLinkedSavedPaymentMethodDeleteResponse postEmployeeDiscountCardProtectedDelete(String str, String str2, EmployeeDiscountCardProtectedDeleteRequest employeeDiscountCardProtectedDeleteRequest, Language language, String str3) throws ApiException {
        return postEmployeeDiscountCardProtectedDeleteWithHttpInfo(str, str2, employeeDiscountCardProtectedDeleteRequest, language, str3).getData();
    }

    public ApiResponse<SupercardPayLinkedSavedPaymentMethodDeleteResponse> postEmployeeDiscountCardProtectedDeleteWithHttpInfo(String str, String str2, EmployeeDiscountCardProtectedDeleteRequest employeeDiscountCardProtectedDeleteRequest, Language language, String str3) throws ApiException {
        return this.localVarApiClient.execute(postEmployeeDiscountCardProtectedDeleteValidateBeforeCall(str, str2, employeeDiscountCardProtectedDeleteRequest, language, str3, null), new TypeToken<SupercardPayLinkedSavedPaymentMethodDeleteResponse>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.7
        }.getType());
    }

    public Call postEmployeeDiscountCardProtectedDeleteAsync(String str, String str2, EmployeeDiscountCardProtectedDeleteRequest employeeDiscountCardProtectedDeleteRequest, Language language, String str3, ApiCallback<SupercardPayLinkedSavedPaymentMethodDeleteResponse> apiCallback) throws ApiException {
        Call postEmployeeDiscountCardProtectedDeleteValidateBeforeCall = postEmployeeDiscountCardProtectedDeleteValidateBeforeCall(str, str2, employeeDiscountCardProtectedDeleteRequest, language, str3, apiCallback);
        this.localVarApiClient.executeAsync(postEmployeeDiscountCardProtectedDeleteValidateBeforeCall, new TypeToken<SupercardPayLinkedSavedPaymentMethodDeleteResponse>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi.8
        }.getType(), apiCallback);
        return postEmployeeDiscountCardProtectedDeleteValidateBeforeCall;
    }
}
