package ch.coop.supercardapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.R;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import com.salesforce.marketingcloud.push.g;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

/* loaded from: classes3.dex */
public class PaymentCardWidget extends BaseWidget {
    public static final WidgetType WIDGET_TYPE = WidgetType.PAYMENTCARD;

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Map<String, Integer> m;
        AppContainer.getInstance(context).getDataUpdateState();
        GiftcardState giftcardState = AppContainer.getInstance(context).getGiftcardState();
        WidgetHelper widgetHelper = new WidgetHelper(context);
        Optional<CardsGiftcard> digitalPaymentCard = giftcardState.getDigitalPaymentCard();
        if (digitalPaymentCard.isPresent()) {
            CardsGiftcard cardsGiftcard = digitalPaymentCard.get();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_payment_card);
            int i = R.id.widget_payment_card_logo;
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.drawable.logo_payment_card_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.drawable.logo_payment_card_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.drawable.logo_payment_card_it))});
            widgetHelper.setImageInAppLanguage(remoteViews, i, m);
            widgetHelper.updateBalanceView(remoteViews, cardsGiftcard);
            widgetHelper.updateITFBarcode(remoteViews, cardsGiftcard.getCardNr());
            widgetHelper.updateLink(remoteViews, context.getString(R.string.widget_payment_card_deeplink_path), WIDGET_TYPE);
            appWidgetManager.updateAppWidget(iArr, remoteViews);
            return;
        }
        appWidgetManager.updateAppWidget(iArr, widgetHelper.dataMissingViews(WIDGET_TYPE));
    }
}
