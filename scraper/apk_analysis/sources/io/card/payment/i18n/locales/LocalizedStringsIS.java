package io.card.payment.i18n.locales;

import io.card.payment.i18n.StringKey;
import io.card.payment.i18n.SupportedLocale;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LocalizedStringsIS implements SupportedLocale<StringKey> {
    private static Map<StringKey, String> mDisplay = new HashMap();
    private static Map<String, String> mAdapted = new HashMap();

    @Override // io.card.payment.i18n.SupportedLocale
    public String getName() {
        return "is";
    }

    @Override // io.card.payment.i18n.SupportedLocale
    public String getAdaptedDisplay(StringKey stringKey, String str) {
        String str2 = stringKey.toString() + "|" + str;
        if (mAdapted.containsKey(str2)) {
            return mAdapted.get(str2);
        }
        return mDisplay.get(stringKey);
    }

    public LocalizedStringsIS() {
        mDisplay.put(StringKey.CANCEL, "Hætta við");
        mDisplay.put(StringKey.CARDTYPE_AMERICANEXPRESS, "American Express");
        mDisplay.put(StringKey.CARDTYPE_DISCOVER, "Discover");
        mDisplay.put(StringKey.CARDTYPE_JCB, "JCB");
        mDisplay.put(StringKey.CARDTYPE_MASTERCARD, "MasterCard");
        mDisplay.put(StringKey.CARDTYPE_VISA, "Visa");
        mDisplay.put(StringKey.DONE, "Lokið");
        mDisplay.put(StringKey.ENTRY_CVV, "CVV");
        mDisplay.put(StringKey.ENTRY_POSTAL_CODE, "Póstnúmer");
        mDisplay.put(StringKey.ENTRY_CARDHOLDER_NAME, "Nafn Korthafa");
        mDisplay.put(StringKey.ENTRY_EXPIRES, "Rennur út");
        mDisplay.put(StringKey.EXPIRES_PLACEHOLDER, "MM/ÁÁ");
        mDisplay.put(StringKey.SCAN_GUIDE, "Haltu kortinu kyrru hér.\nÞað verður sjálvirkt skannað.");
        mDisplay.put(StringKey.KEYBOARD, "Lyklaborð…");
        mDisplay.put(StringKey.ENTRY_CARD_NUMBER, "Kortanúmar");
        mDisplay.put(StringKey.MANUAL_ENTRY_TITLE, "Kortaupplýsingar");
        mDisplay.put(StringKey.ERROR_NO_DEVICE_SUPPORT, "Þetta tæki getur ekki notað myndavélina til að lesa af númer af kortinu.");
        mDisplay.put(StringKey.ERROR_CAMERA_CONNECT_FAIL, "Ekki næst samband við myndavélina.");
        mDisplay.put(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL, "Upp kom villa við að opna myndavélina..");
    }
}
