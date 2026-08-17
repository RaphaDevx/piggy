package ch.coop.supercardapp.core.helpers;

import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTypeEnum;
import ch.coop.supercardapp.R;
import io.sentry.protocol.Geo;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes3.dex */
public class ColorHelper {

    /* renamed from: ch.coop.supercardapp.core.helpers.ColorHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum;

        static {
            int[] iArr = new int[CardsGiftcardTypeEnum.values().length];
            $SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum = iArr;
            try {
                iArr[CardsGiftcardTypeEnum.DIGITAL_PAYMENT_CARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum[CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum[CardsGiftcardTypeEnum.GIFTCARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum[CardsGiftcardTypeEnum.UNKNOWN_DEFAULT_OPEN_API.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int gradientResourceForCard(CardsGiftcard cardsGiftcard) {
        int i = AnonymousClass1.$SwitchMap$ch$coop$apidia$appGateway$cards$model$CardsGiftcardTypeEnum[cardsGiftcard.getType().ordinal()];
        if (i == 1) {
            return R.drawable.gradient_paymentcard;
        }
        if (i == 2) {
            return R.drawable.gradient_employee_giftcard;
        }
        if (i == 3) {
            return gradientResourceForGiftcard(cardsGiftcard.getGroupKey());
        }
        return R.drawable.gradient_default;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int gradientResourceForGiftcard(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1772467395:
                if (str.equals("restaurant")) {
                    c = 0;
                    break;
                }
                break;
            case -1750825033:
                if (str.equals("livique-lumimart")) {
                    c = 1;
                    break;
                }
                break;
            case -1361134403:
                if (str.equals("christ")) {
                    c = 2;
                    break;
                }
                break;
            case -979804772:
                if (str.equals("pronto")) {
                    c = 3;
                    break;
                }
                break;
            case -909586492:
                if (str.equals("sapori")) {
                    c = 4;
                    break;
                }
                break;
            case -409534901:
                if (str.equals("universal")) {
                    c = 5;
                    break;
                }
                break;
            case -94227002:
                if (str.equals("microspot")) {
                    c = 6;
                    break;
                }
                break;
            case 3355:
                if (str.equals("id")) {
                    c = 7;
                    break;
                }
                break;
            case 3053931:
                if (str.equals(Geo.JsonKeys.CITY)) {
                    c = '\b';
                    break;
                }
                break;
            case 3059533:
                if (str.equals("coop")) {
                    c = '\t';
                    break;
                }
                break;
            case 3154800:
                if (str.equals("fust")) {
                    c = '\n';
                    break;
                }
                break;
            case 3236387:
                if (str.equals("impo")) {
                    c = 11;
                    break;
                }
                break;
            case 97615773:
                if (str.equals("fooby")) {
                    c = '\f';
                    break;
                }
                break;
            case 101486671:
                if (str.equals("jumbo")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case 101819504:
                if (str.equals("karma")) {
                    c = 14;
                    break;
                }
                break;
            case 104711384:
                if (str.equals("netto")) {
                    c = 15;
                    break;
                }
                break;
            case 440206356:
                if (str.equals("perpiedi")) {
                    c = 16;
                    break;
                }
                break;
            case 1605680418:
                if (str.equals("vitality")) {
                    c = 17;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.drawable.gradient_restaurant;
            case 1:
                return R.drawable.gradient_livique_lumimart;
            case 2:
                return R.drawable.gradient_christ;
            case 3:
                return R.drawable.gradient_pronto;
            case 4:
                return R.drawable.gradient_sapori;
            case 5:
                return R.drawable.gradient_universal;
            case 6:
                return R.drawable.gradient_microspot;
            case 7:
                return R.drawable.gradient_id;
            case '\b':
                return R.drawable.gradient_city;
            case '\t':
                return R.drawable.gradient_coop;
            case '\n':
                return R.drawable.gradient_fust;
            case 11:
                return R.drawable.gradient_impo;
            case '\f':
                return R.drawable.gradient_fooby;
            case '\r':
                return R.drawable.gradient_jumbo;
            case 14:
                return R.drawable.gradient_karma;
            case 15:
                return R.drawable.gradient_netto;
            case 16:
                return R.drawable.gradient_perpiedi;
            case 17:
                return R.drawable.gradient_vitality;
            default:
                return R.drawable.gradient_default;
        }
    }
}
