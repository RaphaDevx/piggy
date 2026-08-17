package io.card.payment.i18n.locales;

import io.card.payment.i18n.StringKey;
import io.card.payment.i18n.SupportedLocale;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LocalizedStringsTR implements SupportedLocale<StringKey> {
    private static Map<StringKey, String> mDisplay = new HashMap();
    private static Map<String, String> mAdapted = new HashMap();

    @Override // io.card.payment.i18n.SupportedLocale
    public String getName() {
        return "tr";
    }

    @Override // io.card.payment.i18n.SupportedLocale
    public String getAdaptedDisplay(StringKey stringKey, String str) {
        String str2 = stringKey.toString() + "|" + str;
        if (mAdapted.containsKey(str2)) {
            return mAdapted.get(str2);
        }
        return mDisplay.get(stringKey);
    }

    public LocalizedStringsTR() {
        mDisplay.put(StringKey.CANCEL, "İptal");
        mDisplay.put(StringKey.CARDTYPE_AMERICANEXPRESS, "American Express");
        mDisplay.put(StringKey.CARDTYPE_DISCOVER, "Discover");
        mDisplay.put(StringKey.CARDTYPE_JCB, "JCB");
        mDisplay.put(StringKey.CARDTYPE_MASTERCARD, "MasterCard");
        mDisplay.put(StringKey.CARDTYPE_VISA, "Visa");
        mDisplay.put(StringKey.DONE, "Bitti");
        mDisplay.put(StringKey.ENTRY_CVV, "CVV");
        mDisplay.put(StringKey.ENTRY_POSTAL_CODE, "Posta Kodu");
        mDisplay.put(StringKey.ENTRY_CARDHOLDER_NAME, "Kart sahibinin adı");
        mDisplay.put(StringKey.ENTRY_EXPIRES, "Son kullanma tarihi");
        mDisplay.put(StringKey.EXPIRES_PLACEHOLDER, "AA/YY");
        mDisplay.put(StringKey.SCAN_GUIDE, "Kartınızı buraya tutun.\nOtomatik olarak taranacaktır.");
        mDisplay.put(StringKey.KEYBOARD, "Klavye…");
        mDisplay.put(StringKey.ENTRY_CARD_NUMBER, "Kart Numarası");
        mDisplay.put(StringKey.MANUAL_ENTRY_TITLE, "Kart Ayrıntıları");
        mDisplay.put(StringKey.ERROR_NO_DEVICE_SUPPORT, "Bu cihazın kamerası kart rakamlarını okuyamaz.");
        mDisplay.put(StringKey.ERROR_CAMERA_CONNECT_FAIL, "Cihaz kamerası kullanılamıyor.");
        mDisplay.put(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL, "Cihaz kamerayı açarken beklenmedik bir hata verdi.");
    }
}
