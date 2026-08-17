package ch.coop.supercardapp.widget;

import android.content.Context;
import android.widget.RemoteViews;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTypeEnum;
import ch.coop.apidia.appGateway.cards.model.Image;
import ch.coop.apidia.appGateway.cards.model.ImageFormatEnum;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.R;
import ch.coop.supercardapp.containers.cards.CardIdentifier;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import ch.coop.supercardapp.containers.employeeDiscountCard.EmployeeDiscountCardState;
import ch.coop.supercardapp.core.helpers.ColorHelper;
import ch.coop.supercardapp.core.helpers.HashHelper;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.salesforce.marketingcloud.push.g;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/* loaded from: classes3.dex */
public class GiftcardWidget extends BaseWidget {
    public static final WidgetType WIDGET_TYPE = WidgetType.GIFTCARD;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    @Override // android.appwidget.AppWidgetProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUpdate(android.content.Context r8, android.appwidget.AppWidgetManager r9, int[] r10) {
        /*
            r7 = this;
            ch.coop.supercardapp.AppContainer r0 = ch.coop.supercardapp.AppContainer.getInstance(r8)
            ch.coop.supercardapp.core.data.DataUpdateState r0 = r0.getDataUpdateState()
            int r1 = r10.length
            r2 = 0
        La:
            if (r2 >= r1) goto L49
            r3 = r10[r2]
            ch.coop.supercardapp.containers.cards.CardIdentifier r4 = r0.getCardIdentifierForWidgetId(r3)
            if (r4 == 0) goto L31
            int[] r5 = ch.coop.supercardapp.widget.GiftcardWidget.AnonymousClass1.$SwitchMap$ch$coop$supercardapp$containers$cards$CardIdentifier$CardType
            ch.coop.supercardapp.containers.cards.CardIdentifier$CardType r6 = r4.getCardType()
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 1
            if (r5 == r6) goto L2c
            r6 = 2
            if (r5 == r6) goto L27
            goto L31
        L27:
            android.widget.RemoteViews r4 = r7.employeeDiscountCardViews(r8, r3, r4)
            goto L32
        L2c:
            android.widget.RemoteViews r4 = r7.giftcardViews(r8, r3, r4)
            goto L32
        L31:
            r4 = 0
        L32:
            if (r4 == 0) goto L38
            r9.updateAppWidget(r3, r4)
            goto L46
        L38:
            ch.coop.supercardapp.widget.WidgetHelper r4 = new ch.coop.supercardapp.widget.WidgetHelper
            r4.<init>(r8)
            ch.coop.capacitor.extensionconnector.widget.WidgetType r5 = ch.coop.supercardapp.widget.GiftcardWidget.WIDGET_TYPE
            android.widget.RemoteViews r4 = r4.dataMissingViews(r5)
            r9.updateAppWidget(r3, r4)
        L46:
            int r2 = r2 + 1
            goto La
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.coop.supercardapp.widget.GiftcardWidget.onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[]):void");
    }

    /* renamed from: ch.coop.supercardapp.widget.GiftcardWidget$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ch$coop$supercardapp$containers$cards$CardIdentifier$CardType;

        static {
            int[] iArr = new int[CardIdentifier.CardType.values().length];
            $SwitchMap$ch$coop$supercardapp$containers$cards$CardIdentifier$CardType = iArr;
            try {
                iArr[CardIdentifier.CardType.GIFTCARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ch$coop$supercardapp$containers$cards$CardIdentifier$CardType[CardIdentifier.CardType.EMPLOYEE_DISCOUNT_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        AppContainer.getInstance(context).getDataUpdateState().removeCardIdentifierForWidgetIds(iArr);
    }

    private RemoteViews giftcardViews(Context context, int i, CardIdentifier cardIdentifier) {
        String str;
        Map<String, Integer> m;
        Map<String, Integer> m2;
        GiftcardState giftcardState = AppContainer.getInstance(context).getGiftcardState();
        WidgetHelper widgetHelper = new WidgetHelper(context);
        Optional<CardsGiftcard> giftcard = giftcardState.getGiftcard(cardIdentifier.getCardNr());
        if (!giftcard.isPresent()) {
            return null;
        }
        CardsGiftcard cardsGiftcard = giftcard.get();
        boolean z = cardsGiftcard.getType() == CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_giftcard);
        if (z) {
            remoteViews.setViewVisibility(R.id.widget_giftcard_image_logo_wrapper, 8);
            remoteViews.setViewVisibility(R.id.widget_giftcard_text_logo_wrapper, 0);
        } else {
            remoteViews.setViewVisibility(R.id.widget_giftcard_image_logo_wrapper, 0);
            remoteViews.setViewVisibility(R.id.widget_giftcard_text_logo_wrapper, 8);
        }
        if (z) {
            int i2 = R.id.widget_giftcard_text_logo_line1;
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.drawable.logo_employee_line1_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.drawable.logo_employee_giftcard_line1_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.drawable.logo_employee_giftcard_line1_it))});
            widgetHelper.setImageInAppLanguage(remoteViews, i2, m);
            int i3 = R.id.widget_giftcard_text_logo_line2;
            m2 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.drawable.logo_employee_giftcard_line2_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.drawable.logo_employee_giftcard_line2_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.drawable.logo_employee_giftcard_line2_it))});
            widgetHelper.setImageInAppLanguage(remoteViews, i3, m2);
        } else {
            Optional<Image> findFirst = cardsGiftcard.getLogo().stream().filter(new Predicate() { // from class: ch.coop.supercardapp.widget.GiftcardWidget$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return GiftcardWidget.lambda$giftcardViews$0((Image) obj);
                }
            }).findFirst();
            if (findFirst.isPresent()) {
                Glide.with(context.getApplicationContext()).load(findFirst.get().getUrl()).asBitmap().into((BitmapTypeRequest<String>) new AppWidgetTarget(context, remoteViews, R.id.widget_giftcard_logo, i));
            }
        }
        remoteViews.setImageViewResource(R.id.widget_giftcard_background_top, ColorHelper.gradientResourceForCard(cardsGiftcard));
        widgetHelper.updateBalanceView(remoteViews, cardsGiftcard);
        widgetHelper.updateITFBarcode(remoteViews, cardsGiftcard.getCardNr());
        String sha256Hex = HashHelper.sha256Hex(cardsGiftcard.getCardNr());
        if (sha256Hex != null) {
            str = "/app/cards/detail?cardNrHash=" + sha256Hex;
        } else {
            str = "/app/cards";
        }
        widgetHelper.updateLink(remoteViews, str, WIDGET_TYPE);
        return remoteViews;
    }

    static /* synthetic */ boolean lambda$giftcardViews$0(Image image) {
        return image.getFormat() == ImageFormatEnum.LOGO_SINGLE_PNG;
    }

    private RemoteViews employeeDiscountCardViews(Context context, int i, CardIdentifier cardIdentifier) {
        Map<String, Integer> m;
        Map<String, Integer> m2;
        Map<String, Integer> m3;
        EmployeeDiscountCardState employeeDiscountCardState = AppContainer.getInstance(context).getEmployeeDiscountCardState();
        WidgetHelper widgetHelper = new WidgetHelper(context);
        Optional<EmployeeDiscountCard> activeEmployeeDiscountCard = employeeDiscountCardState.getActiveEmployeeDiscountCard();
        if (!activeEmployeeDiscountCard.isPresent()) {
            return null;
        }
        EmployeeDiscountCard employeeDiscountCard = activeEmployeeDiscountCard.get();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_giftcard);
        remoteViews.setViewVisibility(R.id.widget_giftcard_image_logo_wrapper, 8);
        remoteViews.setViewVisibility(R.id.widget_giftcard_text_logo_wrapper, 0);
        int i2 = R.id.widget_giftcard_text_logo_line1;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.drawable.logo_employee_line1_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.drawable.logo_employee_discount_card_line1_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.drawable.logo_employee_discount_card_line1_it))});
        widgetHelper.setImageInAppLanguage(remoteViews, i2, m);
        int i3 = R.id.widget_giftcard_text_logo_line2;
        m2 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.drawable.logo_employee_discount_card_line2_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.drawable.logo_employee_discount_card_line2_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.drawable.logo_employee_discount_card_line2_it))});
        widgetHelper.setImageInAppLanguage(remoteViews, i3, m2);
        remoteViews.setViewVisibility(R.id.widget_giftcard_balance_text, 8);
        remoteViews.setViewVisibility(R.id.widget_giftcard_balance_error_text, 8);
        remoteViews.setViewVisibility(R.id.widget_giftcard_employee_nr_wrapper, 0);
        int i4 = R.id.widget_employee_discount_card_employee_nr_label;
        m3 = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_giftcard_employee_nr_label_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_giftcard_employee_nr_label_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_giftcard_employee_nr_label_it))});
        widgetHelper.setTextInAppLanguage(remoteViews, i4, m3);
        remoteViews.setTextViewText(R.id.widget_employee_discount_card_employee_nr_value, employeeDiscountCard.getEmployeeNr());
        remoteViews.setImageViewResource(R.id.widget_giftcard_background_top, R.drawable.gradient_employee_discountcard);
        String employeeDiscountCardBarcodeString = employeeDiscountCardState.getEmployeeDiscountCardBarcodeString();
        if (employeeDiscountCardBarcodeString != null) {
            widgetHelper.updateCode128Barcode(remoteViews, employeeDiscountCardBarcodeString);
        }
        widgetHelper.updateLink(remoteViews, "/app/cards/cprk", WIDGET_TYPE);
        return remoteViews;
    }
}
