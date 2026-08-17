package com.tealium.internal.data;

import android.content.SharedPreferences;
import com.tealium.library.Tealium;
import java.util.Set;

/* loaded from: classes2.dex */
public final class UserConsentPreferences {
    private static final Set<String> d = null;
    private final SharedPreferences a;
    private String b;
    private Set<String> c;

    public UserConsentPreferences(Tealium.Config config) {
        SharedPreferences sharedPreferences = config.getApplication().getSharedPreferences(a(config), 0);
        this.a = sharedPreferences;
        this.b = sharedPreferences.getString("status", "unknown");
        this.c = sharedPreferences.getStringSet("categories", d);
    }

    private static String a(Tealium.Config config) {
        return "tealium.userconsentpreferences." + Integer.toHexString((config.getAccountName() + config.getProfileName() + config.getEnvironmentName()).hashCode());
    }

    public static UserConsentPreferences create(Tealium.Config config) {
        if (config != null) {
            return new UserConsentPreferences(config);
        }
        throw new IllegalArgumentException();
    }

    public String getConsentStatus() {
        String str = this.b;
        if (str != null) {
            return str;
        }
        String string = this.a.getString("status", "unknown");
        this.b = string;
        return string;
    }

    public Set<String> getConsentedCategories() {
        Set<String> set = this.c;
        if (set != null) {
            return set;
        }
        Set<String> stringSet = this.a.getStringSet("categories", d);
        this.c = stringSet;
        return stringSet;
    }

    public void resetConsentPreferences() {
        this.b = "unknown";
        Set<String> set = d;
        this.c = set;
        this.a.edit().putString("status", "unknown").putStringSet("categories", set).apply();
    }

    public void setConsentCategories(Set<String> set) {
        this.c = set;
        this.a.edit().putStringSet("categories", set).apply();
    }

    public void setConsentStatus(String str) {
        this.b = str;
        this.a.edit().putString("status", str).apply();
    }
}
