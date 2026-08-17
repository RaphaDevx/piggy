package com.salesforce.marketingcloud.storage.db.upgrades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.util.Crypto;
import java.security.GeneralSecurityException;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b {
    public static final b a = new b();
    private static final String b = com.salesforce.marketingcloud.g.a("Version11ToVersion12");
    private static SQLiteDatabase c;
    private static Crypto d;
    private static Crypto e;

    static final class a extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Attempting to migrate " + this.b + " ...";
        }
    }

    /* renamed from: com.salesforce.marketingcloud.storage.db.upgrades.b$b, reason: collision with other inner class name */
    static final class C0098b extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0098b(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Migration required for " + this.b + " ...";
        }
    }

    static final class c extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to migrate row from " + this.b + ". Removing row and continuing ...";
        }
    }

    static final class d extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Migration NOT required for " + this.b + ".";
        }
    }

    static final class e extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Migration complete for " + this.b + ".";
        }
    }

    static final class f extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to migrate " + this.b + ". Dropping table ...";
        }
    }

    static final class g extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return this.b + " ready.";
        }
    }

    static final class h extends Lambda implements Function0<String> {
        public static final h b = new h();

        h() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Migrating from legacyCrypto to SFMC encryption";
        }
    }

    static final class i extends Lambda implements Function0<String> {
        public static final i b = new i();

        i() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "The data migration cannot be executed due to the unavailability of the legacyCrypto reference.";
        }
    }

    static final class j extends Lambda implements Function0<String> {
        final /* synthetic */ Exception b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(Exception exc) {
            super(0);
            this.b = exc;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return String.valueOf(this.b.getMessage());
        }
    }

    private b() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0111, code lost:
    
        if (r0 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x012b, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x012c, code lost:
    
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x012f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0127, code lost:
    
        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("database");
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0125, code lost:
    
        if (r0 != null) goto L23;
     */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void a(android.database.sqlite.SQLiteDatabase r17, com.salesforce.marketingcloud.util.Crypto r18, com.salesforce.marketingcloud.util.Crypto r19) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.b.a(android.database.sqlite.SQLiteDatabase, com.salesforce.marketingcloud.util.Crypto, com.salesforce.marketingcloud.util.Crypto):void");
    }

    private final String b(String str, Crypto crypto) {
        return crypto.encString(str);
    }

    private final void a() {
        SQLiteDatabase sQLiteDatabase = c;
        SQLiteDatabase sQLiteDatabase2 = null;
        if (sQLiteDatabase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("database");
            sQLiteDatabase = null;
        }
        sQLiteDatabase.beginTransaction();
        try {
            SQLiteDatabase sQLiteDatabase3 = c;
            if (sQLiteDatabase3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase3 = null;
            }
            sQLiteDatabase3.execSQL("DELETE FROM inbox_messages;");
            SQLiteDatabase sQLiteDatabase4 = c;
            if (sQLiteDatabase4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase4 = null;
            }
            sQLiteDatabase4.execSQL("DELETE FROM messages;");
            SQLiteDatabase sQLiteDatabase5 = c;
            if (sQLiteDatabase5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase5 = null;
            }
            sQLiteDatabase5.execSQL("DELETE FROM registration;");
            SQLiteDatabase sQLiteDatabase6 = c;
            if (sQLiteDatabase6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase6 = null;
            }
            sQLiteDatabase6.execSQL("DELETE FROM device_stats;");
            SQLiteDatabase sQLiteDatabase7 = c;
            if (sQLiteDatabase7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase7 = null;
            }
            sQLiteDatabase7.execSQL("DELETE FROM in_app_messages;");
            SQLiteDatabase sQLiteDatabase8 = c;
            if (sQLiteDatabase8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase8 = null;
            }
            sQLiteDatabase8.execSQL("DELETE FROM analytic_item;");
            SQLiteDatabase sQLiteDatabase9 = c;
            if (sQLiteDatabase9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase9 = null;
            }
            sQLiteDatabase9.execSQL("DELETE FROM regions;");
            SQLiteDatabase sQLiteDatabase10 = c;
            if (sQLiteDatabase10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase10 = null;
            }
            sQLiteDatabase10.execSQL("DELETE FROM location_table;");
            SQLiteDatabase sQLiteDatabase11 = c;
            if (sQLiteDatabase11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase11 = null;
            }
            sQLiteDatabase11.setTransactionSuccessful();
        } finally {
            SQLiteDatabase sQLiteDatabase12 = c;
            if (sQLiteDatabase12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
            } else {
                sQLiteDatabase2 = sQLiteDatabase12;
            }
            sQLiteDatabase2.endTransaction();
        }
    }

    static /* synthetic */ void a(b bVar, String str, String str2, String[] strArr, Set set, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            strArr = null;
        }
        bVar.a(str, str2, strArr, set);
    }

    private final void a(String str, String str2, String[] strArr, Set<String> set) {
        String str3;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            com.salesforce.marketingcloud.g.d(com.salesforce.marketingcloud.g.a, b, null, new a(str), 2, null);
            SQLiteDatabase sQLiteDatabase2 = c;
            if (sQLiteDatabase2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
                sQLiteDatabase2 = null;
            }
            Cursor rawQuery = sQLiteDatabase2.rawQuery(str2, strArr);
            Boolean bool = null;
            while (rawQuery.moveToNext()) {
                try {
                    if (bool == null) {
                        bool = Boolean.valueOf(a.a(set));
                    }
                    if (bool.booleanValue()) {
                        com.salesforce.marketingcloud.g.d(com.salesforce.marketingcloud.g.a, b, null, new C0098b(str), 2, null);
                        try {
                            ContentValues contentValues = new ContentValues();
                            for (String str4 : set) {
                                Intrinsics.checkNotNull(rawQuery);
                                String b2 = com.salesforce.marketingcloud.storage.db.d.b(rawQuery, str4);
                                if (b2 != null) {
                                    for (int i2 = 0; i2 < b2.length(); i2++) {
                                        char charAt = b2.charAt(i2);
                                        if (!Character.isWhitespace(charAt) && charAt != 160 && charAt != 8199 && charAt != 8239) {
                                            str3 = a.a(b2);
                                            break;
                                        }
                                    }
                                }
                                str3 = null;
                                contentValues.put(str4, str3);
                            }
                            SQLiteDatabase sQLiteDatabase3 = c;
                            if (sQLiteDatabase3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("database");
                                sQLiteDatabase3 = null;
                            }
                            Intrinsics.checkNotNull(rawQuery);
                            sQLiteDatabase3.update(str, contentValues, "id=?", new String[]{com.salesforce.marketingcloud.storage.db.d.b(rawQuery, "id")});
                        } catch (Exception e2) {
                            com.salesforce.marketingcloud.g.a.b(b, e2, new c(str));
                            SQLiteDatabase sQLiteDatabase4 = c;
                            if (sQLiteDatabase4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("database");
                                sQLiteDatabase4 = null;
                            }
                            Intrinsics.checkNotNull(rawQuery);
                            sQLiteDatabase4.delete(str, "id=?", new String[]{com.salesforce.marketingcloud.storage.db.d.b(rawQuery, "id")});
                        }
                    } else {
                        com.salesforce.marketingcloud.g.d(com.salesforce.marketingcloud.g.a, b, null, new d(str), 2, null);
                    }
                } finally {
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            com.salesforce.marketingcloud.g.d(com.salesforce.marketingcloud.g.a, b, null, new e(str), 2, null);
        } catch (Exception e3) {
            com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
            String str5 = b;
            gVar.b(str5, e3, new f(str));
            SQLiteDatabase sQLiteDatabase5 = c;
            if (sQLiteDatabase5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("database");
            } else {
                sQLiteDatabase = sQLiteDatabase5;
            }
            sQLiteDatabase.execSQL("DELETE FROM " + str + ";");
            com.salesforce.marketingcloud.g.d(gVar, str5, null, new g(str), 2, null);
        }
    }

    private final boolean a(Set<String> set) {
        Crypto crypto;
        try {
            crypto = d;
            if (crypto == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crypto");
                crypto = null;
            }
        } catch (Exception unused) {
        }
        return crypto.decString((String) CollectionsKt.first(set)) == null;
    }

    private final String a(String str) {
        try {
            Crypto crypto = e;
            Crypto crypto2 = null;
            if (crypto == null) {
                Intrinsics.throwUninitializedPropertyAccessException("legacyCrypto");
                crypto = null;
            }
            String a2 = a(str, crypto);
            if (a2 == null) {
                return null;
            }
            b bVar = a;
            Crypto crypto3 = d;
            if (crypto3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crypto");
            } else {
                crypto2 = crypto3;
            }
            return bVar.b(a2, crypto2);
        } catch (Exception unused) {
            throw new GeneralSecurityException("Failed to migrate data.");
        }
    }

    private final String a(String str, Crypto crypto) {
        return crypto.decString(str);
    }
}
