package io.card.payment.i18n.locales;

import io.card.payment.i18n.StringKey;
import io.card.payment.i18n.SupportedLocale;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class LocalizedStringsHE implements SupportedLocale<StringKey> {
    private static Map<StringKey, String> mDisplay = new HashMap();
    private static Map<String, String> mAdapted = new HashMap();

    @Override // io.card.payment.i18n.SupportedLocale
    public String getName() {
        return "he";
    }

    @Override // io.card.payment.i18n.SupportedLocale
    public String getAdaptedDisplay(StringKey stringKey, String str) {
        String str2 = stringKey.toString() + "|" + str;
        if (mAdapted.containsKey(str2)) {
            return mAdapted.get(str2);
        }
        return mDisplay.get(stringKey);
    }

    public LocalizedStringsHE() {
        mDisplay.put(StringKey.CANCEL, "ביטול");
        mDisplay.put(StringKey.CARDTYPE_AMERICANEXPRESS, "אמריקן אקספרס");
        mDisplay.put(StringKey.CARDTYPE_DISCOVER, "Discover\u200f");
        mDisplay.put(StringKey.CARDTYPE_JCB, "JCB\u200f");
        mDisplay.put(StringKey.CARDTYPE_MASTERCARD, "מאסטרקארד");
        mDisplay.put(StringKey.CARDTYPE_VISA, "ויזה");
        mDisplay.put(StringKey.DONE, "בוצע");
        mDisplay.put(StringKey.ENTRY_CVV, "קוד אימות כרטיס");
        mDisplay.put(StringKey.ENTRY_POSTAL_CODE, "מיקוד");
        mDisplay.put(StringKey.ENTRY_CARDHOLDER_NAME, "שם בעל הכרטיס");
        mDisplay.put(StringKey.ENTRY_EXPIRES, "תאריך תפוגה");
        mDisplay.put(StringKey.EXPIRES_PLACEHOLDER, "MM/YY\u200f");
        mDisplay.put(StringKey.SCAN_GUIDE, "החזק את הכרטיס כאן.\nהסריקה תתבצע באופן אוטומטי.");
        mDisplay.put(StringKey.KEYBOARD, "מקלדת…");
        mDisplay.put(StringKey.ENTRY_CARD_NUMBER, "מספר כרטיס");
        mDisplay.put(StringKey.MANUAL_ENTRY_TITLE, "פרטי כרטיס");
        mDisplay.put(StringKey.ERROR_NO_DEVICE_SUPPORT, "המכשיר אינו מסוגל להשתמש במצלמה לקריאת מספרי כרטיס.");
        mDisplay.put(StringKey.ERROR_CAMERA_CONNECT_FAIL, "מצלמת המכשיר אינה זמינה.");
        mDisplay.put(StringKey.ERROR_CAMERA_UNEXPECTED_FAIL, "המכשיר נתקל בשגיאה בלתי צפויה בזמן הפעלת המצלמה.");
    }
}
