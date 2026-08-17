package io.card.payment.i18n;

import android.content.Intent;
import io.card.payment.CardIOActivity;
import io.card.payment.i18n.locales.LocalizedStringsList;

/* loaded from: classes2.dex */
public final class LocalizedStrings {
    private static final I18nManager<StringKey> i18nManager = new I18nManager<>(StringKey.class, LocalizedStringsList.ALL_LOCALES);

    public static String getString(StringKey stringKey) {
        return i18nManager.getString(stringKey);
    }

    public static String getString(StringKey stringKey, String str) {
        I18nManager<StringKey> i18nManager2 = i18nManager;
        return i18nManager2.getString(stringKey, i18nManager2.getLocaleFromSpecifier(str));
    }

    public static void setLanguage(Intent intent) {
        i18nManager.setLanguage(intent.getStringExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE));
    }
}
