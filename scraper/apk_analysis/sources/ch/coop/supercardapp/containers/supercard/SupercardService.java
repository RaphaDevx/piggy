package ch.coop.supercardapp.containers.supercard;

import android.content.Context;
import android.os.Build;
import ch.coop.apidia.appGateway.loyalty.ApiClient;
import ch.coop.apidia.appGateway.loyalty.ApiException;
import ch.coop.apidia.appGateway.loyalty.Configuration;
import ch.coop.apidia.appGateway.loyalty.api.LoyaltyApi;
import ch.coop.apidia.appGateway.loyalty.model.Language;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceOsNameEnum;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercard;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeRequest;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeResponse;
import ch.coop.auth.Auth;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.app.AppState;
import ch.coop.supercardapp.core.helpers.APIHelper;
import io.sentry.Sentry;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SupercardService {
    private static final long NINETY_DAYS_IN_SECONDS = 7776000;
    public LoyaltyApi api;
    public APIHelper apiHelper;
    public AppState appState;
    public Auth auth;
    public ApiClient client;

    @FunctionalInterface
    public interface OnPreflightSuccessCallback {
        void onPreflightSuccess(DataMatrixPreflightData dataMatrixPreflightData);
    }

    public static class DataMatrixPreflightData {
        private final OffsetDateTime date;
        private final LoyaltyDeviceMediumRequestBody requestBody;
        private final Boolean success;

        DataMatrixPreflightData(Boolean bool, OffsetDateTime offsetDateTime, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody) {
            this.success = bool;
            this.date = offsetDateTime;
            this.requestBody = loyaltyDeviceMediumRequestBody;
        }

        public Boolean getSuccess() {
            return this.success;
        }

        public OffsetDateTime getDate() {
            return this.date;
        }

        public LoyaltyDeviceMediumRequestBody getRequestBody() {
            return this.requestBody;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DataMatrixPreflightData dataMatrixPreflightData = (DataMatrixPreflightData) obj;
            return Objects.equals(this.success, dataMatrixPreflightData.success) && Objects.equals(this.date, dataMatrixPreflightData.date) && Objects.equals(this.requestBody, dataMatrixPreflightData.requestBody);
        }

        public int hashCode() {
            return Objects.hash(this.success, this.date, this.requestBody);
        }
    }

    public SupercardService(Context context) {
        this.appState = AppContainer.getInstance(context).getAppState();
        this.auth = AppContainer.getInstance(context).getAuth();
        this.apiHelper = AppContainer.getInstance(context).getApiHelper();
        ApiClient defaultApiClient = Configuration.getDefaultApiClient();
        this.client = defaultApiClient;
        defaultApiClient.setBasePath(this.apiHelper.getBasePath(context) + "/loyalty-programs/api/v1");
        this.client.setReadTimeout(this.apiHelper.getReadTimeoutMs());
        this.api = new LoyaltyApi(this.client);
    }

    public LoyaltySupercard getSupercard() {
        if (setBearerToken()) {
            try {
                return this.api.getLoyaltySupercard(this.apiHelper.getClientId(), this.apiHelper.getCorrelationId(), 0, Language.fromValue(this.appState.getAppLanguage()), this.appState.getDeviceId());
            } catch (ApiException e) {
                Sentry.captureException(e);
            }
        }
        return null;
    }

    private Boolean postDataMatrixPreflight() {
        boolean z;
        try {
            z = this.api.postDeviceMedium(this.apiHelper.getClientId(), this.apiHelper.getCorrelationId(), getLoyaltyDeviceMediumRequestBody(), this.appState.getDeviceId()).getSuccess().booleanValue();
        } catch (ApiException e) {
            Sentry.captureException(e);
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public LoyaltySupercardCodeResponse postSupercardDatamatrix(LoyaltySupercardCodeResponse loyaltySupercardCodeResponse, DataMatrixPreflightData dataMatrixPreflightData, OnPreflightSuccessCallback onPreflightSuccessCallback) {
        if (!setBearerToken()) {
            return null;
        }
        LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody = getLoyaltyDeviceMediumRequestBody();
        if (needPreflight(dataMatrixPreflightData, loyaltyDeviceMediumRequestBody).booleanValue()) {
            if (!postDataMatrixPreflight().booleanValue()) {
                return null;
            }
            if (onPreflightSuccessCallback != null) {
                onPreflightSuccessCallback.onPreflightSuccess(new DataMatrixPreflightData(true, OffsetDateTime.now(), loyaltyDeviceMediumRequestBody));
            }
            return postSupercardDatamatrixAfterPreflight(loyaltySupercardCodeResponse);
        }
        return postSupercardDatamatrixAfterPreflight(loyaltySupercardCodeResponse);
    }

    private LoyaltySupercardCodeResponse postSupercardDatamatrixAfterPreflight(LoyaltySupercardCodeResponse loyaltySupercardCodeResponse) {
        String appInstallationId = this.appState.getAppInstallationId();
        LoyaltySupercardCodeRequest loyaltySupercardCodeRequest = new LoyaltySupercardCodeRequest();
        loyaltySupercardCodeRequest.setUuid(appInstallationId);
        loyaltySupercardCodeRequest.setFormat("default");
        if (loyaltySupercardCodeResponse != null) {
            loyaltySupercardCodeRequest.setHash(loyaltySupercardCodeResponse.getHash());
            loyaltySupercardCodeRequest.setVersion(loyaltySupercardCodeResponse.getVersion());
        }
        try {
            return this.api.getLoyaltySupercardCode(this.apiHelper.getClientId(), this.apiHelper.getCorrelationId(), loyaltySupercardCodeRequest, Language.fromValue(this.appState.getAppLanguage()), this.appState.getDeviceId());
        } catch (ApiException e) {
            Sentry.captureException(e);
            return null;
        }
    }

    private Boolean needPreflight(DataMatrixPreflightData dataMatrixPreflightData, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody) {
        if (dataMatrixPreflightData == null || dataMatrixPreflightData.getDate() == null || dataMatrixPreflightData.getRequestBody() == null || !dataMatrixPreflightData.getSuccess().booleanValue()) {
            return true;
        }
        if (Duration.between(dataMatrixPreflightData.getDate(), OffsetDateTime.now()).getSeconds() > NINETY_DAYS_IN_SECONDS || !dataMatrixPreflightData.getRequestBody().equals(loyaltyDeviceMediumRequestBody)) {
            return true;
        }
        return false;
    }

    private LoyaltyDeviceMediumRequestBody getLoyaltyDeviceMediumRequestBody() {
        return new LoyaltyDeviceMediumRequestBody().appId(this.appState.getAppInstallationId()).appLanguage(this.appState.getAppLanguage()).appName("Supercard").appPushId("").appVersion("6.11.2#2984").deviceLanguage(Locale.getDefault().getLanguage()).deviceModel(Build.MODEL).deviceName("").mediumName("APP").osName(LoyaltyDeviceOsNameEnum.ANDROID_WIDGET).osVersion(Build.VERSION.RELEASE).uuid(this.appState.getAppInstallationId());
    }

    private boolean setBearerToken() {
        String idToken = this.auth.idToken();
        if (idToken == null) {
            return false;
        }
        this.client.setBearerToken(idToken);
        return true;
    }
}
