package ch.coop.supercardapp.containers.employeeDiscountCard;

import android.content.Context;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardListRequestBody;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiClient;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiException;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiResponse;
import ch.coop.apidia.appGateway.employeeDiscountCard.Configuration;
import ch.coop.apidia.appGateway.employeeDiscountCard.api.EmployeeDiscountCardApi;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Language;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.app.AppState;
import ch.coop.supercardapp.core.helpers.APIHelper;
import io.sentry.Sentry;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class EmployeeDiscountCardService {
    public EmployeeDiscountCardApi api;
    private final APIHelper apiHelper;
    public ApiClient client;
    private final Context context;

    public EmployeeDiscountCardService(Context context) {
        this.context = context;
        APIHelper apiHelper = AppContainer.getInstance(context).getApiHelper();
        this.apiHelper = apiHelper;
        ApiClient defaultApiClient = Configuration.getDefaultApiClient();
        this.client = defaultApiClient;
        defaultApiClient.setBasePath(apiHelper.getBasePath(context) + "/employee-discount-cards/api/v1");
        this.client.setReadTimeout(apiHelper.getReadTimeoutMs());
        this.api = new EmployeeDiscountCardApi(this.client);
    }

    public ApiResponse<EmployeeDiscountCard> getCard(EmployeeDiscountCardCredentials employeeDiscountCardCredentials) {
        String clientId = this.apiHelper.getClientId();
        String correlationId = this.apiHelper.getCorrelationId();
        AppState appState = AppContainer.getInstance(this.context).getAppState();
        try {
            return this.api.postEmployeeDiscountCardWithHttpInfo(clientId, correlationId, employeeDiscountCardCredentials, Language.fromValue(appState.getAppLanguage()), appState.getDeviceId());
        } catch (ApiException e) {
            Sentry.captureException(e);
            return null;
        }
    }

    public EmployeeDiscountCardCredentials getEmployeeDiscountCardCredentialsFromPhoneApp() {
        String str;
        String str2;
        String str3;
        JSONObject jSONObject;
        String str4 = AppContainer.getInstance(this.context).getStorage().get("coop_supercard_app_common_employee_discount_card");
        if (str4 == null) {
            return null;
        }
        try {
            jSONObject = new JSONObject(str4);
            JSONObject optJSONObject = jSONObject.optJSONObject(CardsGiftcardListRequestBody.SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD);
            str2 = optJSONObject != null ? optJSONObject.optString("cardNr") : null;
        } catch (JSONException e) {
            e = e;
            str = null;
        }
        try {
            str3 = jSONObject.optString("cardAuthCode");
        } catch (JSONException e2) {
            str = str2;
            e = e2;
            Sentry.captureException(e);
            str2 = str;
            str3 = null;
            return str2 != null ? null : null;
        }
        if (str2 != null || str2.isEmpty() || str3 == null || str3.isEmpty()) {
            return null;
        }
        return new EmployeeDiscountCardCredentials().cardNr(str2).cardAuthCode(str3);
    }
}
