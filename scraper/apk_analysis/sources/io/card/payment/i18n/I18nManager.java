package io.card.payment.i18n;

import android.util.Log;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.lang.Enum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes2.dex */
public class I18nManager<E extends Enum<?>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Set<String> RIGHT_TO_LEFT_LOCALE_SET;
    private static final Map<String, String> SPECIAL_LOCALE_MAP;
    private static final String TAG = "I18nManager";
    private SupportedLocale<E> currentLocale;
    private Class<E> enumClazz;
    private Map<String, SupportedLocale<E>> supportedLocales = new LinkedHashMap();

    static {
        HashMap hashMap = new HashMap();
        SPECIAL_LOCALE_MAP = hashMap;
        HashSet hashSet = new HashSet();
        RIGHT_TO_LEFT_LOCALE_SET = hashSet;
        hashMap.put("zh_CN", "zh-Hans");
        hashMap.put("zh_TW", "zh-Hant_TW");
        hashMap.put("zh_HK", "zh-Hant");
        hashMap.put("en_UK", "en_GB");
        hashMap.put("en_IE", "en_GB");
        hashMap.put("iw_IL", "he");
        hashMap.put(BooleanUtils.NO, "nb");
        hashSet.add("he");
        hashSet.add("ar");
    }

    public I18nManager(Class<E> cls, List<SupportedLocale<E>> list) {
        this.enumClazz = cls;
        Iterator<SupportedLocale<E>> it = list.iterator();
        while (it.hasNext()) {
            addLocale(it.next());
        }
        setLanguage(null);
    }

    private void logMissingLocalizations(String str) {
        Iterator<String> it = getMissingLocaleMessages(str).iterator();
        while (it.hasNext()) {
            Log.i(TAG, it.next());
        }
    }

    private List<String> getMissingLocaleMessages(String str) {
        SupportedLocale<E> supportedLocale = this.supportedLocales.get(str);
        ArrayList arrayList = new ArrayList();
        for (E e : this.enumClazz.getEnumConstants()) {
            String str2 = "[" + str + FirebaseConstants.SEPARATOR + e + "]";
            if (supportedLocale.getAdaptedDisplay(e, null) == null) {
                arrayList.add("Missing " + str2);
            }
        }
        return arrayList;
    }

    public void setLanguage(String str) {
        this.currentLocale = null;
        this.currentLocale = getLocaleFromSpecifier(str);
        Log.d(TAG, "setting locale to:" + this.currentLocale.getName());
    }

    public SupportedLocale<E> getLocaleFromSpecifier(String str) {
        SupportedLocale<E> lookupSupportedLocale = str != null ? lookupSupportedLocale(str) : null;
        if (lookupSupportedLocale == null) {
            String locale = Locale.getDefault().toString();
            Log.d(TAG, str + " not found.  Attempting to look for " + locale);
            lookupSupportedLocale = lookupSupportedLocale(locale);
        }
        if (lookupSupportedLocale != null) {
            return lookupSupportedLocale;
        }
        Log.d(TAG, "defaulting to english");
        return this.supportedLocales.get("en");
    }

    private SupportedLocale<E> lookupSupportedLocale(String str) {
        String str2;
        SupportedLocale<E> supportedLocale = null;
        if (str == null || str.length() < 2) {
            return null;
        }
        Map<String, String> map = SPECIAL_LOCALE_MAP;
        if (map.containsKey(str)) {
            String str3 = map.get(str);
            SupportedLocale<E> supportedLocale2 = this.supportedLocales.get(str3);
            Log.d(TAG, "Overriding locale specifier " + str + " with " + str3);
            supportedLocale = supportedLocale2;
        }
        if (supportedLocale == null) {
            if (str.contains("_")) {
                str2 = str;
            } else {
                str2 = str + "_" + Locale.getDefault().getCountry();
            }
            supportedLocale = this.supportedLocales.get(str2);
        }
        if (supportedLocale == null) {
            supportedLocale = this.supportedLocales.get(str);
        }
        if (supportedLocale != null) {
            return supportedLocale;
        }
        return this.supportedLocales.get(str.substring(0, 2));
    }

    public String getString(E e) {
        return getString(e, this.currentLocale);
    }

    public String getString(E e, SupportedLocale<E> supportedLocale) {
        String upperCase = Locale.getDefault().getCountry().toUpperCase(Locale.US);
        String adaptedDisplay = supportedLocale.getAdaptedDisplay(e, upperCase);
        if (adaptedDisplay == null) {
            Log.i(TAG, "Missing localized string for [" + this.currentLocale.getName() + ",Key." + e.toString() + "]");
            adaptedDisplay = this.supportedLocales.get("en").getAdaptedDisplay(e, upperCase);
        }
        if (adaptedDisplay != null) {
            return adaptedDisplay;
        }
        Log.i(TAG, "Missing localized string for [en,Key." + e.toString() + "], so defaulting to keyname");
        return e.toString();
    }

    private void addLocale(SupportedLocale<E> supportedLocale) {
        String name = supportedLocale.getName();
        if (name == null) {
            throw new RuntimeException("Null localeName");
        }
        if (this.supportedLocales.containsKey(name)) {
            throw new RuntimeException("Locale " + name + " already added");
        }
        this.supportedLocales.put(name, supportedLocale);
        logMissingLocalizations(name);
    }
}
