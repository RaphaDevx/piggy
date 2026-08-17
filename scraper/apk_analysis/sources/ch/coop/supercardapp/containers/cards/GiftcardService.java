package ch.coop.supercardapp.containers.cards;

import android.content.Context;
import ch.coop.apidia.appGateway.cards.ApiClient;
import ch.coop.apidia.appGateway.cards.ApiException;
import ch.coop.apidia.appGateway.cards.Configuration;
import ch.coop.apidia.appGateway.cards.api.GiftcardApi;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardCredentials;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardListRequestBody;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardListRequestBodyEmployeeDiscountCard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardResponse;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTypeEnum;
import ch.coop.apidia.appGateway.cards.model.EmployeeDiscountCardCredentials;
import ch.coop.apidia.appGateway.cards.model.Language;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.app.AppState;
import ch.coop.supercardapp.core.helpers.APIHelper;
import io.sentry.Sentry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GiftcardService {
    public GiftcardApi api;
    private final APIHelper apiHelper;
    public ApiClient client;
    private final Context context;

    public GiftcardService(Context context) {
        this.context = context;
        APIHelper apiHelper = AppContainer.getInstance(context).getApiHelper();
        this.apiHelper = apiHelper;
        ApiClient defaultApiClient = Configuration.getDefaultApiClient();
        this.client = defaultApiClient;
        defaultApiClient.setBasePath(apiHelper.getBasePath(context) + "/cards/api/v1");
        this.client.setReadTimeout(apiHelper.getReadTimeoutMs());
        this.api = new GiftcardApi(this.client);
    }

    public CardsGiftcardResponse getGiftcards(List<CardsGiftcardCredentials> list, EmployeeDiscountCardCredentials employeeDiscountCardCredentials) {
        CardsGiftcardListRequestBody cardsGiftcardListRequestBody = new CardsGiftcardListRequestBody();
        cardsGiftcardListRequestBody.setTransactionDays(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(CardsGiftcardTypeEnum.GIFTCARD);
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            arrayList2.addAll(list);
        }
        cardsGiftcardListRequestBody.setCards(arrayList2);
        if (employeeDiscountCardCredentials != null) {
            CardsGiftcardListRequestBodyEmployeeDiscountCard cardsGiftcardListRequestBodyEmployeeDiscountCard = new CardsGiftcardListRequestBodyEmployeeDiscountCard();
            cardsGiftcardListRequestBodyEmployeeDiscountCard.setCardAuthCode(employeeDiscountCardCredentials.getCardAuthCode());
            cardsGiftcardListRequestBodyEmployeeDiscountCard.setCardNr(employeeDiscountCardCredentials.getCardNr());
            cardsGiftcardListRequestBody.setEmployeeDiscountCard(cardsGiftcardListRequestBodyEmployeeDiscountCard);
            arrayList.add(CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD);
        }
        String clientId = this.apiHelper.getClientId();
        String correlationId = this.apiHelper.getCorrelationId();
        AppState appState = AppContainer.getInstance(this.context).getAppState();
        Language fromValue = Language.fromValue(appState.getAppLanguage());
        String deviceId = appState.getDeviceId();
        String idToken = AppContainer.getInstance(this.context).getAuth().idToken();
        if (idToken != null) {
            this.client.setBearerToken(idToken);
            arrayList.add(CardsGiftcardTypeEnum.DIGITAL_PAYMENT_CARD);
            cardsGiftcardListRequestBody.setCardTypes(arrayList);
            try {
                return this.api.postGiftcards(clientId, correlationId, fromValue, deviceId, cardsGiftcardListRequestBody);
            } catch (ApiException | IllegalArgumentException e) {
                Sentry.captureException(e);
            }
        } else if (!cardsGiftcardListRequestBody.getCards().isEmpty() || cardsGiftcardListRequestBody.getEmployeeDiscountCard() != null) {
            cardsGiftcardListRequestBody.setCardTypes(arrayList);
            try {
                return this.api.postGiftcardsPublic(clientId, correlationId, cardsGiftcardListRequestBody, fromValue, deviceId);
            } catch (ApiException | IllegalArgumentException e2) {
                Sentry.captureException(e2);
            }
        } else {
            CardsGiftcardResponse cardsGiftcardResponse = new CardsGiftcardResponse();
            cardsGiftcardResponse.setCards(Collections.emptyList());
            return cardsGiftcardResponse;
        }
        return null;
    }

    public List<CardsGiftcardCredentials> getGiftcardCredentialsFromPhoneApp() {
        String str;
        JSONArray jSONArray;
        ArrayList arrayList = new ArrayList();
        String str2 = AppContainer.getInstance(this.context).getStorage().get("coop_supercard_app_common_giftcard");
        if (str2 != null) {
            try {
                JSONArray optJSONArray = new JSONObject(str2).optJSONArray("giftcards");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject = optJSONArray.getJSONObject(i);
                        JSONObject optJSONObject = jSONObject.optJSONObject("card");
                        if (optJSONObject != null) {
                            str = optJSONObject.optString("cardNr");
                            jSONArray = optJSONObject.optJSONArray(CardsGiftcard.SERIALIZED_NAME_LINKS);
                        } else {
                            str = null;
                            jSONArray = null;
                        }
                        String optString = jSONObject.optString("pin");
                        if (str != null && optString != null && !"null".equals(optString) && (jSONArray == null || jSONArray.length() == 0)) {
                            CardsGiftcardCredentials cardsGiftcardCredentials = new CardsGiftcardCredentials();
                            cardsGiftcardCredentials.setCardNr(str);
                            cardsGiftcardCredentials.setPin(optString);
                            arrayList.add(cardsGiftcardCredentials);
                        }
                    }
                }
            } catch (JSONException e) {
                Sentry.captureException(e);
            }
        }
        return arrayList;
    }
}
