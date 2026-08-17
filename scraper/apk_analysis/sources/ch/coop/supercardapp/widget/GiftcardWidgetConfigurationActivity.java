package ch.coop.supercardapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.Image;
import ch.coop.apidia.appGateway.cards.model.ImageFormatEnum;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.auth.Auth;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.MainActivity;
import ch.coop.supercardapp.R;
import ch.coop.supercardapp.containers.cards.CardIdentifier;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import ch.coop.supercardapp.containers.employeeDiscountCard.EmployeeDiscountCardState;
import ch.coop.supercardapp.core.data.DataUpdateManager;
import ch.coop.supercardapp.core.data.DataUpdateState;
import ch.coop.supercardapp.core.helpers.ViewHelper;
import ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity;
import com.bumptech.glide.Glide;
import com.salesforce.marketingcloud.push.g;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes3.dex */
public class GiftcardWidgetConfigurationActivity extends AppCompatActivity implements DataUpdateState.DataUpdateStateListener {
    private ProgressBar activityIndicator;
    private Auth auth;
    private CardsAdapter cardsAdapter;
    private DataUpdateManager dataUpdateManager;
    private DataUpdateState dataUpdateState;
    private EmployeeDiscountCardState employeeDiscountCardState;
    private GiftcardState giftcardState;
    private ListView listView;
    private TextView noCardsFoundMessage;
    private Button openSupercardAppButton;
    private int widgetId;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Map<String, Integer> m;
        Map<String, Integer> m2;
        Map<String, Integer> m3;
        Map<String, Integer> m4;
        Map<String, Integer> m5;
        super.onCreate(bundle);
        AppContainer appContainer = AppContainer.getInstance(this);
        this.dataUpdateManager = appContainer.getDataUpdateManager();
        this.dataUpdateState = appContainer.getDataUpdateState();
        this.giftcardState = appContainer.getGiftcardState();
        this.employeeDiscountCardState = appContainer.getEmployeeDiscountCardState();
        this.auth = appContainer.getAuth();
        setContentView(R.layout.widget_giftcard_configuration_activity);
        this.activityIndicator = (ProgressBar) findViewById(R.id.card_select_activity_indicator);
        this.listView = (ListView) findViewById(R.id.card_select_list_view);
        this.noCardsFoundMessage = (TextView) findViewById(R.id.card_select_no_cards_found_message);
        this.openSupercardAppButton = (Button) findViewById(R.id.card_select_open_app_button);
        Bundle extras = getIntent().getExtras();
        this.widgetId = 0;
        if (extras != null) {
            this.widgetId = extras.getInt("appWidgetId", 0);
        }
        setResult(0, new Intent().putExtra("appWidgetId", this.widgetId));
        String appLanguage = appContainer.getAppState().getAppLanguage();
        TextView textView = (TextView) findViewById(R.id.card_select_activity_title);
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_title_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_title_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_title_it))});
        setTextInAppLanguage(this, appLanguage, textView, m);
        TextView textView2 = (TextView) findViewById(R.id.card_select_activity_subtitle);
        m2 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_subtitle_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_subtitle_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_subtitle_it))});
        setTextInAppLanguage(this, appLanguage, textView2, m2);
        this.openSupercardAppButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GiftcardWidgetConfigurationActivity.this.lambda$onCreate$0(view);
            }
        });
        TextView textView3 = this.openSupercardAppButton;
        m3 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_open_app_button_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_open_app_button_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_open_app_button_it))});
        setTextInAppLanguage(this, appLanguage, textView3, m3);
        TextView textView4 = (TextView) findViewById(R.id.card_select_no_cards_found_message);
        m4 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_no_cards_found_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_no_cards_found_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_no_cards_found_it))});
        setTextInAppLanguage(this, appLanguage, textView4, m4);
        CardsAdapter cardsAdapter = new CardsAdapter(this, getCardsForList());
        this.cardsAdapter = cardsAdapter;
        this.listView.setAdapter((ListAdapter) cardsAdapter);
        updateListViewVisibility();
        Button button = (Button) findViewById(R.id.card_select_cancel_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GiftcardWidgetConfigurationActivity.this.lambda$onCreate$1(view);
            }
        });
        m5 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_cancel_button_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_cancel_button_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_cancel_button_it))});
        setTextInAppLanguage(this, appLanguage, button, m5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        finish();
    }

    public void setTextInAppLanguage(Context context, String str, TextView textView, Map<String, Integer> map) {
        Integer num = map.get(str);
        if (textView == null || num == null) {
            return;
        }
        textView.setText(context.getText(num.intValue()));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        updateActivityIndicator();
        this.dataUpdateState.addDataUpdateStateListener(this);
        if (this.dataUpdateState.isDataUpdateInProgress()) {
            return;
        }
        this.dataUpdateManager.startOrRestartPeriodicDataUpdate();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.dataUpdateState.removeDataUpdateStateListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfigurationComplete(CardIdentifier cardIdentifier) {
        this.dataUpdateState.setCardIdentifierForWidgetId(this.widgetId, cardIdentifier);
        new GiftcardWidget().onUpdate(this, AppWidgetManager.getInstance(this), new int[]{this.widgetId});
        setResult(-1, new Intent().putExtra("appWidgetId", this.widgetId));
        finish();
    }

    @Override // ch.coop.supercardapp.core.data.DataUpdateState.DataUpdateStateListener
    public void onDataUpdateStarted() {
        updateActivityIndicator();
    }

    @Override // ch.coop.supercardapp.core.data.DataUpdateState.DataUpdateStateListener
    public void onDataUpdateCompleted() {
        updateActivityIndicator();
        updateCardsInList();
    }

    private void updateActivityIndicator() {
        runOnUiThread(new Runnable() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                GiftcardWidgetConfigurationActivity.this.lambda$updateActivityIndicator$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateActivityIndicator$2() {
        if (this.dataUpdateState.isDataUpdateInProgress()) {
            this.activityIndicator.setVisibility(0);
            this.openSupercardAppButton.setVisibility(8);
        } else if (this.dataUpdateState.getNeedsReauthorization().booleanValue()) {
            this.activityIndicator.setVisibility(8);
            this.openSupercardAppButton.setVisibility(0);
        } else {
            this.activityIndicator.setVisibility(4);
            this.openSupercardAppButton.setVisibility(8);
        }
    }

    private void updateCardsInList() {
        final List<CardViewModel> cardsForList = getCardsForList();
        runOnUiThread(new Runnable() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GiftcardWidgetConfigurationActivity.this.lambda$updateCardsInList$3(cardsForList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCardsInList$3(List list) {
        this.cardsAdapter.clear();
        this.cardsAdapter.addAll(list);
        this.cardsAdapter.notifyDataSetChanged();
        updateListViewVisibility();
    }

    private void updateListViewVisibility() {
        if (this.cardsAdapter.getCount() > 0) {
            this.listView.setVisibility(0);
            this.noCardsFoundMessage.setVisibility(8);
        } else {
            this.listView.setVisibility(8);
            this.noCardsFoundMessage.setVisibility(0);
        }
    }

    private List<CardViewModel> getCardsForList() {
        Map m;
        Map m2;
        List<CardViewModel> list = (List) this.giftcardState.getGiftcards().stream().map(new Function() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                GiftcardWidgetConfigurationActivity.CardViewModel lambda$getCardsForList$5;
                lambda$getCardsForList$5 = GiftcardWidgetConfigurationActivity.this.lambda$getCardsForList$5((CardsGiftcard) obj);
                return lambda$getCardsForList$5;
            }
        }).collect(Collectors.toList());
        Optional<EmployeeDiscountCard> activeEmployeeDiscountCard = this.employeeDiscountCardState.getActiveEmployeeDiscountCard();
        if (activeEmployeeDiscountCard.isPresent()) {
            String appLanguage = AppContainer.getInstance(this).getAppState().getAppLanguage();
            EmployeeDiscountCard employeeDiscountCard = activeEmployeeDiscountCard.get();
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", "https://contentimages.coop.ch/logos/logo_single_scp-cprk-small_de.png"), new AbstractMap.SimpleEntry("fr", "https://contentimages.coop.ch/logos/logo_single_scp-cprk-small_fr.png"), new AbstractMap.SimpleEntry(g.h, "https://contentimages.coop.ch/logos/logo_single_scp-cprk-small_it.png")});
            m2 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_cprk_name_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_cprk_name_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_cprk_name_it))});
            Integer num = (Integer) m2.get(appLanguage);
            list.add(new CardViewModel(this, new CardIdentifier(CardIdentifier.CardType.EMPLOYEE_DISCOUNT_CARD, employeeDiscountCard.getCardNr()), num != null ? getString(num.intValue()) : "", employeeDiscountCard.getCardNr(), (String) m.get(appLanguage), true));
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CardViewModel lambda$getCardsForList$5(CardsGiftcard cardsGiftcard) {
        Optional<Image> findFirst = cardsGiftcard.getLogo().stream().filter(new Predicate() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GiftcardWidgetConfigurationActivity.lambda$getCardsForList$4((Image) obj);
            }
        }).findFirst();
        return new CardViewModel(this, new CardIdentifier(CardIdentifier.CardType.GIFTCARD, cardsGiftcard.getCardNr()), cardsGiftcard.getName(), cardsGiftcard.getCardNrFormatted(), findFirst.isPresent() ? findFirst.get().getUrl() : null, false);
    }

    static /* synthetic */ boolean lambda$getCardsForList$4(Image image) {
        return image.getFormat() == ImageFormatEnum.LOGO_SINGLE_PNG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CardViewModel {
        CardIdentifier cardIdentifier;
        String cardNrFormatted;
        Boolean logoRemovePadding;
        String logoUrl;
        String name;

        public CardViewModel(GiftcardWidgetConfigurationActivity giftcardWidgetConfigurationActivity, CardIdentifier cardIdentifier, String str, String str2, String str3, Boolean bool) {
            this.cardIdentifier = cardIdentifier;
            this.name = str;
            this.cardNrFormatted = str2;
            this.logoUrl = str3;
            this.logoRemovePadding = bool;
        }

        public CardIdentifier getCardIdentifier() {
            return this.cardIdentifier;
        }

        public String getName() {
            return this.name;
        }

        public String getCardNrFormatted() {
            return this.cardNrFormatted;
        }

        public String getLogoUrl() {
            return this.logoUrl;
        }

        public Boolean getLogoRemovePadding() {
            return this.logoRemovePadding;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CardsAdapter extends ArrayAdapter<CardViewModel> {

        private class ViewHolder {
            TextView cardNrTextView;
            TextView cardTitleTextView;
            ImageView logoImageView;
            Button selectButton;

            private ViewHolder(CardsAdapter cardsAdapter) {
            }
        }

        public CardsAdapter(Context context, List<CardViewModel> list) {
            super(context, R.layout.widget_giftcard_configuration_activity_card_item, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            Map<String, Integer> m;
            Context context = getContext();
            final CardViewModel item = getItem(i);
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = LayoutInflater.from(context).inflate(R.layout.widget_giftcard_configuration_activity_card_item, viewGroup, false);
                viewHolder.logoImageView = (ImageView) view2.findViewById(R.id.card_logo_image);
                viewHolder.cardTitleTextView = (TextView) view2.findViewById(R.id.card_title);
                viewHolder.cardNrTextView = (TextView) view2.findViewById(R.id.card_number);
                viewHolder.selectButton = (Button) view2.findViewById(R.id.card_select_button);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            String logoUrl = item.getLogoUrl();
            if (logoUrl != null) {
                Glide.with(getContext()).load(logoUrl).crossFade().into(viewHolder.logoImageView);
                int dpToPixels = item.getLogoRemovePadding().booleanValue() ? 0 : ViewHelper.dpToPixels(context, 4.0f);
                viewHolder.logoImageView.setPadding(dpToPixels, dpToPixels, dpToPixels, dpToPixels);
            } else {
                viewHolder.logoImageView.setImageBitmap(null);
            }
            viewHolder.cardTitleTextView.setText(item.getName());
            viewHolder.cardNrTextView.setText(item.getCardNrFormatted());
            viewHolder.selectButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.supercardapp.widget.GiftcardWidgetConfigurationActivity$CardsAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    GiftcardWidgetConfigurationActivity.CardsAdapter.this.lambda$getView$0(item, view3);
                }
            });
            GiftcardWidgetConfigurationActivity giftcardWidgetConfigurationActivity = GiftcardWidgetConfigurationActivity.this;
            String appLanguage = AppContainer.getInstance(context).getAppState().getAppLanguage();
            Button button = viewHolder.selectButton;
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_configuration_activity_select_button_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_configuration_activity_select_button_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_configuration_activity_select_button_it))});
            giftcardWidgetConfigurationActivity.setTextInAppLanguage(context, appLanguage, button, m);
            return view2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$getView$0(CardViewModel cardViewModel, View view) {
            GiftcardWidgetConfigurationActivity.this.onConfigurationComplete(cardViewModel.getCardIdentifier());
        }
    }
}
