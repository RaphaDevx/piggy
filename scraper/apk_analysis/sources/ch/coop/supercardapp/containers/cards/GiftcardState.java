package ch.coop.supercardapp.containers.cards;

import android.content.Context;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardResponse;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTypeEnum;
import ch.coop.apidia.appGateway.cards.model.EmployeeDiscountCardCredentials;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.employeeDiscountCard.EmployeeDiscountCardState;
import ch.coop.supercardapp.core.helpers.GsonHelper;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes3.dex */
public class GiftcardState {
    public static final String STORAGE_KEY = "native_giftcard-state";
    private List<CardsGiftcard> cards = Collections.emptyList();
    private final Context context;
    public EmployeeDiscountCardState employeeDiscountCardState;
    public GiftcardService giftcardService;
    private final Gson gson;
    public PersistentStorage storage;

    public static class StoredState {
        public List<CardsGiftcard> cards;

        StoredState(List<CardsGiftcard> list) {
            this.cards = list;
        }
    }

    public GiftcardState(Context context) {
        this.context = context;
        this.giftcardService = new GiftcardService(context);
        AppContainer appContainer = AppContainer.getInstance(context);
        this.employeeDiscountCardState = appContainer.getEmployeeDiscountCardState();
        this.storage = appContainer.getStorage();
        this.gson = GsonHelper.create();
        loadState();
    }

    public void retrieveGiftcardsFromAPI() {
        EmployeeDiscountCardCredentials employeeDiscountCardCredentials;
        Optional<ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials> credentials = this.employeeDiscountCardState.getCredentials();
        if (credentials.isPresent()) {
            ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials employeeDiscountCardCredentials2 = credentials.get();
            employeeDiscountCardCredentials = new EmployeeDiscountCardCredentials().cardNr(employeeDiscountCardCredentials2.getCardNr()).cardAuthCode(employeeDiscountCardCredentials2.getCardAuthCode());
        } else {
            employeeDiscountCardCredentials = null;
        }
        GiftcardService giftcardService = this.giftcardService;
        CardsGiftcardResponse giftcards = giftcardService.getGiftcards(giftcardService.getGiftcardCredentialsFromPhoneApp(), employeeDiscountCardCredentials);
        if (giftcards != null) {
            this.cards = giftcards.getCards();
            storeState();
        }
    }

    public List<CardsGiftcard> getGiftcards() {
        return (List) this.cards.stream().filter(new Predicate() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GiftcardState.lambda$getGiftcards$0((CardsGiftcard) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getGiftcards$0(CardsGiftcard cardsGiftcard) {
        List m;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{CardsGiftcardTypeEnum.GIFTCARD, CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD});
        return m.contains(cardsGiftcard.getType());
    }

    public Optional<CardsGiftcard> getGiftcard(final String str) {
        return this.cards.stream().filter(new Predicate() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = str.equals(((CardsGiftcard) obj).getCardNr());
                return equals;
            }
        }).findFirst();
    }

    public Optional<CardsGiftcard> getDigitalPaymentCard() {
        return this.cards.stream().filter(new Predicate() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GiftcardState.lambda$getDigitalPaymentCard$2((CardsGiftcard) obj);
            }
        }).findFirst();
    }

    static /* synthetic */ boolean lambda$getDigitalPaymentCard$2(CardsGiftcard cardsGiftcard) {
        return cardsGiftcard.getType() == CardsGiftcardTypeEnum.DIGITAL_PAYMENT_CARD;
    }

    public void removeAllCardsButEmployeeGiftcard() {
        this.cards = (List) this.cards.stream().filter(new Predicate() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GiftcardState.lambda$removeAllCardsButEmployeeGiftcard$3((CardsGiftcard) obj);
            }
        }).collect(Collectors.toList());
        storeState();
    }

    static /* synthetic */ boolean lambda$removeAllCardsButEmployeeGiftcard$3(CardsGiftcard cardsGiftcard) {
        return cardsGiftcard.getType() == CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD;
    }

    public void resetState() {
        this.cards = Collections.emptyList();
        storeState();
    }

    public void updateWithDataFromPhone(Map<String, Integer> map) {
        if (this.cards != null) {
            map.forEach(new BiConsumer() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GiftcardState.this.lambda$updateWithDataFromPhone$6((String) obj, (Integer) obj2);
                }
            });
            storeState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateWithDataFromPhone$6(final String str, final Integer num) {
        this.cards.stream().filter(new Predicate() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((CardsGiftcard) obj).getCardNr().equals(str);
                return equals;
            }
        }).forEach(new Consumer() { // from class: ch.coop.supercardapp.containers.cards.GiftcardState$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CardsGiftcard) obj).setBalanceValue(num);
            }
        });
    }

    private void loadState() {
        StoredState storedState;
        String str = this.storage.get(STORAGE_KEY);
        if (str == null || (storedState = (StoredState) this.gson.fromJson(str, StoredState.class)) == null) {
            return;
        }
        this.cards = storedState.cards;
    }

    private void storeState() {
        this.storage.set(STORAGE_KEY, this.gson.toJson(new StoredState(this.cards)), true, false);
    }
}
