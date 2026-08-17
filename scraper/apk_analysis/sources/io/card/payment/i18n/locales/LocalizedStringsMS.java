package io.card.payment.i18n.locales;

import io.card.payment.i18n.StringKey;
import io.card.payment.i18n.SupportedLocale;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LocalizedStringsMS implements SupportedLocale<StringKey> {
    private static Map<StringKey, String> mDisplay = new HashMap();
    private static Map<String, String> mAdapted = new HashMap();

    @Override // io.card.payment.i18n.SupportedLocale
    public String getName() {
        return "ms";
    }

    @Override // io.card.payment.i18n.SupportedLocale
    public String getAdaptedDisplay(StringKey stringKey, String str) {
        String str2 = stringKey.toString() + "|" + str;
        if (mAdapted.containsKey(str2)) {
            return mAdapted.get(str2);
        }
        return mDisplay.get(stringKey);
    }

    public LocalizedStringsMS() {
        mDisplay.put(StringKey.CANCEL, "Batal");
        mDisplay.put(StringKey.CARDTYPE_AMERICANEXPRESS, "American Express");
        mDisplay.put(StringKey.CARDTYPE_DISCOVER, "Discover");
        mDisplay.put(StringKey.CARDTYPE_JCB, "JCB");
        mDisplay.put(StringKey.CARDTYPE_MASTERCARD, "MasterCard");
        mDisplay.put(StringKey.CARDTYPE_VISA, "Visa");
        mDisplay.put(StringKey.DONE, "Selesai");
        mDisplay.put(StringKey.ENTRY_CVV, "CVV");
        mDisplay.put(StringKey.ENTRY_POSTAL_CODE, "Poskod");
        mDisplay.put(StringKey.ENTRY_CARDHOLDER_NAME, "Nama Pemegang Kad");
        mDisplay.put(StringKey.ENTRY_EXPIRES, "Luput");
        mDisplay.put(StringKey.EXPIRES_PLACEHOLDER, "BB/TT");
        mDisplay.put(StringKey.SCAN_GUIDE, "Pegang kad di sini.\nIa akan mengimbas secara automatik.");
        mDisplay.put(StringKey.KEYBOARD, "Papan Kekunci…");
        mDisplay.put(StringKey.ENTRY_CARD_NUMBER, "Nombor Kad");
        mDisplay.put(StringKey.MANUAL_ENTRY_TITLE, "Butiran Kad");
        mDisplay.put(StringKey.ERROR_NO_DEVICE_SUPPORT, "Peranti ini tidak dapat menggunakan kamera untuk membaca nombor kad.");
        mDisplay.put(StringKey.ERROR_CAMERA_CONNECT_FAIL, "Kamera peranti tidak tersedia.");
        mDisplay.put(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL, "Peranti mengalami ralat tidak dijangka semasa membuka kamera.");
    }
}
