package ch.coop.supercardapp.containers.employeeDiscountCard;

import android.content.Context;
import ch.coop.apidia.appGateway.employeeDiscountCard.ApiResponse;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.core.helpers.GsonHelper;
import com.google.gson.Gson;
import io.sentry.Sentry;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/* loaded from: classes3.dex */
public class EmployeeDiscountCardState {
    public static final String STORAGE_KEY = "native_employee-discount-card-state";
    private final Context context;
    private EmployeeDiscountCardCredentials credentials = null;
    private EmployeeDiscountCard employeeDiscountCard = null;
    public EmployeeDiscountCardService employeeDiscountCardService;
    private final Gson gson;
    public PersistentStorage storage;
    private OffsetDateTime syncTimeClient;
    private OffsetDateTime syncTimeServer;

    public static class StoredState {
        public EmployeeDiscountCardCredentials credentials;
        public EmployeeDiscountCard employeeDiscountCard;
        public OffsetDateTime syncTimeClient;
        public OffsetDateTime syncTimeServer;

        StoredState(EmployeeDiscountCardCredentials employeeDiscountCardCredentials, EmployeeDiscountCard employeeDiscountCard, OffsetDateTime offsetDateTime, OffsetDateTime offsetDateTime2) {
            this.credentials = employeeDiscountCardCredentials;
            this.employeeDiscountCard = employeeDiscountCard;
            this.syncTimeServer = offsetDateTime;
            this.syncTimeClient = offsetDateTime2;
        }
    }

    public EmployeeDiscountCardState(Context context) {
        this.context = context;
        AppContainer.getInstance(context);
        this.employeeDiscountCardService = new EmployeeDiscountCardService(context);
        this.storage = AppContainer.getInstance(context).getStorage();
        this.gson = GsonHelper.create();
        loadState();
    }

    public void retrieveEmployeeDiscountCardFromAPI() {
        ApiResponse<EmployeeDiscountCard> card;
        EmployeeDiscountCardCredentials employeeDiscountCardCredentials = this.credentials;
        if (employeeDiscountCardCredentials == null || (card = this.employeeDiscountCardService.getCard(employeeDiscountCardCredentials)) == null || card.getData() == null) {
            return;
        }
        this.employeeDiscountCard = card.getData();
        List<String> list = card.getHeaders().get("date");
        if (list != null && list.stream().findFirst().isPresent()) {
            try {
                this.syncTimeServer = OffsetDateTime.parse(list.stream().findFirst().get(), DateTimeFormatter.RFC_1123_DATE_TIME);
                this.syncTimeClient = OffsetDateTime.now();
            } catch (DateTimeParseException e) {
                Sentry.captureException(e);
                this.syncTimeServer = null;
                this.syncTimeClient = null;
            }
        } else {
            this.syncTimeServer = null;
            this.syncTimeClient = null;
        }
        storeState();
    }

    public void retrieveCredentialsFromPhoneApp() {
        EmployeeDiscountCardCredentials employeeDiscountCardCredentialsFromPhoneApp = this.employeeDiscountCardService.getEmployeeDiscountCardCredentialsFromPhoneApp();
        this.credentials = employeeDiscountCardCredentialsFromPhoneApp;
        if (employeeDiscountCardCredentialsFromPhoneApp == null) {
            resetState();
        } else {
            storeState();
        }
    }

    public Optional<EmployeeDiscountCardCredentials> getCredentials() {
        return Optional.ofNullable(this.credentials);
    }

    public Optional<EmployeeDiscountCard> getActiveEmployeeDiscountCard() {
        EmployeeDiscountCard employeeDiscountCard = this.employeeDiscountCard;
        return Optional.ofNullable((employeeDiscountCard == null || employeeDiscountCard.getStatus() != EmployeeDiscountCard.StatusEnum.ACTIVE) ? null : this.employeeDiscountCard);
    }

    public String getEmployeeDiscountCardBarcodeString() {
        OffsetDateTime offsetDateTime;
        EmployeeDiscountCard employeeDiscountCard = this.employeeDiscountCard;
        if (employeeDiscountCard == null || employeeDiscountCard.getCardNr() == null) {
            return null;
        }
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime offsetDateTime2 = this.syncTimeServer;
        if (offsetDateTime2 != null && (offsetDateTime = this.syncTimeClient) != null) {
            now = now.plus((TemporalAmount) Duration.between(offsetDateTime, offsetDateTime2));
        }
        return this.employeeDiscountCard.getCardNr() + DateTimeFormatter.ofPattern("ddMM", Locale.ENGLISH).format(now);
    }

    public void resetState() {
        this.credentials = null;
        this.employeeDiscountCard = null;
        this.syncTimeClient = null;
        this.syncTimeServer = null;
        storeState();
    }

    private void loadState() {
        StoredState storedState;
        String str = this.storage.get(STORAGE_KEY);
        if (str == null || (storedState = (StoredState) this.gson.fromJson(str, StoredState.class)) == null) {
            return;
        }
        this.credentials = storedState.credentials;
        this.employeeDiscountCard = storedState.employeeDiscountCard;
        this.syncTimeServer = storedState.syncTimeServer;
        this.syncTimeClient = storedState.syncTimeClient;
    }

    private void storeState() {
        this.storage.set(STORAGE_KEY, this.gson.toJson(new StoredState(this.credentials, this.employeeDiscountCard, this.syncTimeServer, this.syncTimeClient)), true, false);
    }
}
