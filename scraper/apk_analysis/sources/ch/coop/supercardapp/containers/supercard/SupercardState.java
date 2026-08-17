package ch.coop.supercardapp.containers.supercard;

import android.content.Context;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercard;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeResponse;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.supercard.SupercardService;
import ch.coop.supercardapp.core.helpers.GsonHelper;
import com.google.gson.Gson;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;

/* loaded from: classes3.dex */
public class SupercardState {
    public static final String STORAGE_KEY = "native_supercard-state";
    private final Gson gson = GsonHelper.create();
    private SupercardService.DataMatrixPreflightData preflightData;
    public PersistentStorage storage;
    private LoyaltySupercard supercard;
    private LoyaltySupercardCodeResponse supercardDataMatrix;
    private OffsetDateTime supercardLastUpdate;
    public SupercardService supercardService;

    public static class StoredState {
        SupercardService.DataMatrixPreflightData preflightData;
        LoyaltySupercard supercard;
        LoyaltySupercardCodeResponse supercardDataMatrix;
        OffsetDateTime supercardLastUpdate;

        StoredState(LoyaltySupercard loyaltySupercard, OffsetDateTime offsetDateTime, LoyaltySupercardCodeResponse loyaltySupercardCodeResponse, SupercardService.DataMatrixPreflightData dataMatrixPreflightData) {
            this.supercard = loyaltySupercard;
            this.supercardLastUpdate = offsetDateTime;
            this.supercardDataMatrix = loyaltySupercardCodeResponse;
            this.preflightData = dataMatrixPreflightData;
        }
    }

    public SupercardState(Context context) {
        this.supercardService = new SupercardService(context);
        this.storage = AppContainer.getInstance(context).getStorage();
        loadState();
    }

    public Optional<LoyaltySupercard> getSupercard() {
        return Optional.ofNullable(this.supercard);
    }

    public Optional<String> getDatamatrixImage() {
        LoyaltySupercardCodeResponse loyaltySupercardCodeResponse = this.supercardDataMatrix;
        if (loyaltySupercardCodeResponse != null) {
            return Optional.ofNullable(loyaltySupercardCodeResponse.getDatamatrixImage());
        }
        return Optional.empty();
    }

    public void retrieveSupercardFromAPI() {
        LoyaltySupercard supercard = this.supercardService.getSupercard();
        if (supercard != null) {
            this.supercard = supercard;
            this.supercardLastUpdate = OffsetDateTime.now();
            storeState();
        }
    }

    public void retrieveSupercardDatamatrixFromAPI() {
        LoyaltySupercardCodeResponse postSupercardDatamatrix = this.supercardService.postSupercardDatamatrix(this.supercardDataMatrix, this.preflightData, new SupercardService.OnPreflightSuccessCallback() { // from class: ch.coop.supercardapp.containers.supercard.SupercardState$$ExternalSyntheticLambda0
            @Override // ch.coop.supercardapp.containers.supercard.SupercardService.OnPreflightSuccessCallback
            public final void onPreflightSuccess(SupercardService.DataMatrixPreflightData dataMatrixPreflightData) {
                SupercardState.this.lambda$retrieveSupercardDatamatrixFromAPI$0(dataMatrixPreflightData);
            }
        });
        if (postSupercardDatamatrix == null || postSupercardDatamatrix.getDatamatrixImage() == null) {
            return;
        }
        this.supercardDataMatrix = postSupercardDatamatrix;
        storeState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$retrieveSupercardDatamatrixFromAPI$0(SupercardService.DataMatrixPreflightData dataMatrixPreflightData) {
        this.preflightData = dataMatrixPreflightData;
        storeState();
    }

    public void resetState() {
        this.supercard = null;
        this.supercardLastUpdate = null;
        this.supercardDataMatrix = null;
        this.preflightData = null;
        storeState();
    }

    public void updateWithDataFromPhone(OffsetDateTime offsetDateTime, int i) {
        OffsetDateTime offsetDateTime2;
        if (this.supercard == null || (offsetDateTime2 = this.supercardLastUpdate) == null || Duration.between(offsetDateTime2, offsetDateTime).getSeconds() <= 0) {
            return;
        }
        this.supercard.setPoints(Integer.valueOf(i));
        this.supercardLastUpdate = offsetDateTime;
        storeState();
    }

    private void loadState() {
        StoredState storedState;
        String str = this.storage.get(STORAGE_KEY);
        if (str == null || (storedState = (StoredState) this.gson.fromJson(str, StoredState.class)) == null) {
            return;
        }
        this.supercard = storedState.supercard;
        this.supercardLastUpdate = storedState.supercardLastUpdate;
        this.supercardDataMatrix = storedState.supercardDataMatrix;
        this.preflightData = storedState.preflightData;
    }

    private void storeState() {
        this.storage.set(STORAGE_KEY, this.gson.toJson(new StoredState(this.supercard, this.supercardLastUpdate, this.supercardDataMatrix, this.preflightData)), true, false);
    }
}
