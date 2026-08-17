package com.salesforce.marketingcloud.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.util.Crypto;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public interface b {
    public static final String a = "mcsdk_custprefs_%s";
    public static final String b = "et_attributes_cache";
    public static final String c = "et_tags_cache";
    public static final String d = "et_subscriber_cache";
    public static final String e = "gcm_reg_id_key";
    public static final String f = "et_session_id_cache";
    public static final String g = "et_user_id_cache";
    public static final String h = "mc_last_sent_registration";
    public static final String i = "sender_id";
    public static final String j = "subscriber_jwt";
    public static final String k = "predictive_intelligence_identifier";
    public static final String l = "data_migration_complete";

    public static class a implements b {
        public static final String p = "true";
        private final SharedPreferences m;
        private final Crypto n;
        private final Crypto o;

        a(Context context, Crypto crypto, String str) {
            this(context, crypto, str, null);
        }

        static String b(String str) {
            return String.format(Locale.ENGLISH, b.a, str);
        }

        private String c(String str, String str2) {
            return a(str, str2, this.n);
        }

        private void d(String str, String str2) throws GeneralSecurityException, UnsupportedEncodingException {
            this.m.edit().putString(str, this.n.encString(str2)).apply();
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public final void a(String str, String str2) {
            try {
                d(str, str2);
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                com.salesforce.marketingcloud.g.e(l.f, String.format(Locale.ENGLISH, "Value for key %s not stored.", str), e);
            }
        }

        a(Context context, Crypto crypto, String str, Crypto crypto2) {
            this(context, crypto, str, crypto2, false);
        }

        private void b() {
            if (this.m.contains("gcm_sender_id")) {
                this.m.edit().remove("gcm_sender_id").apply();
            }
        }

        private boolean c() {
            return !this.m.contains(b.l);
        }

        a(Context context, Crypto crypto, String str, Crypto crypto2, boolean z) {
            this.m = context.getSharedPreferences(b(str), 0);
            if (z) {
                com.salesforce.marketingcloud.g.a(l.f, "SFMC New Installation or Encryption Change detected. Resetting the SDK data.", new Object[0]);
                a();
            }
            this.n = crypto;
            this.o = crypto2;
            b();
            a(z);
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public final void a(String str) {
            this.m.edit().remove(str).apply();
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public final String b(String str, String str2) {
            return c(str, str2);
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public final void a() {
            this.m.edit().clear().apply();
        }

        private String a(String str, String str2, Crypto crypto) {
            String str3 = null;
            String string = this.m.getString(str, null);
            if (string != null) {
                try {
                    str3 = crypto.decString(string);
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.e(l.f, e, "Failed to encrypt %s", str);
                }
            }
            return str3 == null ? str2 : str3;
        }

        private void a(boolean z) {
            if (z) {
                com.salesforce.marketingcloud.g.a(l.f, "migration not needed skipping ...", new Object[0]);
                a(b.l, "true");
                return;
            }
            boolean c = c();
            if (c && this.o == null) {
                a();
                return;
            }
            if (!c || this.o == null) {
                return;
            }
            try {
                Iterator<Map.Entry<String, ?>> it = this.m.getAll().entrySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().getKey();
                    String a = a(key, null, this.o);
                    if (a != null) {
                        a(key, a);
                    }
                }
                a(b.l, "true");
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(l.f, e, "Unable to migrate preferences. Starting fresh ...", new Object[0]);
                a();
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.salesforce.marketingcloud.storage.b$b, reason: collision with other inner class name */
    public @interface InterfaceC0096b {
    }

    void a();

    void a(String str);

    void a(String str, String str2);

    String b(String str, String str2);
}
