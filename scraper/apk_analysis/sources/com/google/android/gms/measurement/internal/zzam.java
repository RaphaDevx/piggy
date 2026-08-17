package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zznt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lib.android.paypal.com.magnessdk.c;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes3.dex */
final class zzam extends zzkh {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", c.f, "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzal zzj;
    private final zzkd zzk;

    zzam(zzkt zzktVar) {
        super(zzktVar);
        this.zzk = new zzkd(this.zzt.zzav());
        this.zzt.zzf();
        this.zzj = new zzal(this, this.zzt.zzau(), "google_app_measurement.db");
    }

    static final void zzV(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put("value", (Double) obj);
        }
    }

    private final long zzZ(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = zzh().rawQuery(str, strArr);
                if (!rawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zzaa(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                cursor = zzh().rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void zzA(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzd("Error deleting user property. appId", zzeh.zzn(str), this.zzt.zzj().zzf(str2), e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0251, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0235, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01df, code lost:
    
        r0 = r25.zzt.zzay().zzk();
        r10 = com.google.android.gms.measurement.internal.zzeh.zzn(r26);
        r12 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01f7, code lost:
    
        if (r11.zzp() == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01f9, code lost:
    
        r16 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0206, code lost:
    
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r10, r12, java.lang.String.valueOf(r16));
        r21 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0204, code lost:
    
        r16 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0297, code lost:
    
        r21 = r7;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02a5, code lost:
    
        if (r0.hasNext() == false) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02a7, code lost:
    
        r3 = (com.google.android.gms.internal.measurement.zzet) r0.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r26);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x02c1, code lost:
    
        if (r3.zze().isEmpty() == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02f3, code lost:
    
        r7 = r3.zzbu();
        r10 = new android.content.ContentValues();
        r10.put("app_id", r26);
        r10.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x030a, code lost:
    
        if (r3.zzj() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x030c, code lost:
    
        r11 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0316, code lost:
    
        r10.put("filter_id", r11);
        r22 = r0;
        r10.put("property_name", r3.zze());
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0328, code lost:
    
        if (r3.zzk() == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x032a, code lost:
    
        r11 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0334, code lost:
    
        r10.put("session_scoped", r11);
        r10.put("data", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0348, code lost:
    
        if (zzh().insertWithOnConflict("property_filters", null, r10, 5) != (-1)) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x035e, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x034a, code lost:
    
        r25.zzt.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzeh.zzn(r26));
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0362, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0363, code lost:
    
        r25.zzt.zzay().zzd().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r26), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0333, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0315, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x02c3, code lost:
    
        r0 = r25.zzt.zzay().zzk();
        r8 = com.google.android.gms.measurement.internal.zzeh.zzn(r26);
        r10 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02db, code lost:
    
        if (r3.zzj() == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02dd, code lost:
    
        r16 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02ea, code lost:
    
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r8, r10, java.lang.String.valueOf(r16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02e8, code lost:
    
        r16 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0178, code lost:
    
        r10 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0184, code lost:
    
        if (r10.hasNext() == false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0190, code lost:
    
        if (((com.google.android.gms.internal.measurement.zzet) r10.next()).zzj() != false) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0192, code lost:
    
        r25.zzt.zzay().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzeh.zzn(r26), java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ab, code lost:
    
        r10 = r0.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01c1, code lost:
    
        if (r10.hasNext() == false) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01c3, code lost:
    
        r11 = (com.google.android.gms.internal.measurement.zzek) r10.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r26);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01dd, code lost:
    
        if (r11.zzg().isEmpty() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0211, code lost:
    
        r3 = r11.zzbu();
        r21 = r7;
        r7 = new android.content.ContentValues();
        r7.put("app_id", r26);
        r7.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x022a, code lost:
    
        if (r11.zzp() == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x022c, code lost:
    
        r8 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0236, code lost:
    
        r7.put("filter_id", r8);
        r7.put(com.tealium.library.DataSources.Key.EVENT_NAME, r11.zzg());
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0246, code lost:
    
        if (r11.zzq() == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0248, code lost:
    
        r8 = java.lang.Boolean.valueOf(r11.zzn());
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0252, code lost:
    
        r7.put("session_scoped", r8);
        r7.put("data", r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0266, code lost:
    
        if (zzh().insertWithOnConflict("event_filters", null, r7, 5) != (-1)) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0268, code lost:
    
        r25.zzt.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzeh.zzn(r26));
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x027b, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0281, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0282, code lost:
    
        r25.zzt.zzay().zzd().zzc("Error storing event filter. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r26), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0376, code lost:
    
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r26);
        r0 = zzh();
        r7 = r18;
        r0.delete("property_filters", r7, new java.lang.String[]{r26, java.lang.String.valueOf(r9)});
        r0.delete("event_filters", r7, new java.lang.String[]{r26, java.lang.String.valueOf(r9)});
        r18 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03a2, code lost:
    
        r7 = r21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzB(java.lang.String r26, java.util.List r27) {
        /*
            Method dump skipped, instructions count: 1157
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzB(java.lang.String, java.util.List):void");
    }

    public final void zzC() {
        zzW();
        zzh().setTransactionSuccessful();
    }

    public final void zzD(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        zzW();
        String zzt = zzhVar.zzt();
        Preconditions.checkNotNull(zzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzt);
        contentValues.put("app_instance_id", zzhVar.zzu());
        contentValues.put("gmp_app_id", zzhVar.zzy());
        contentValues.put("resettable_device_id_hash", zzhVar.zzA());
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put("app_version", zzhVar.zzw());
        contentValues.put("app_store", zzhVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzai()));
        contentValues.put("day", Long.valueOf(zzhVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzb()));
        contentValues.put("firebase_instance_id", zzhVar.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzh()));
        contentValues.put("health_monitor_sample", zzhVar.zzz());
        zzhVar.zza();
        contentValues.put(c.f, (Long) 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzah()));
        contentValues.put("admob_app_id", zzhVar.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzk()));
        contentValues.put("session_stitching_token", zzhVar.zzB());
        List zzC = zzhVar.zzC();
        if (zzC != null) {
            if (zzC.isEmpty()) {
                this.zzt.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", zzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(FirebaseConstants.SEPARATOR, zzC));
            }
        }
        zznt.zzc();
        if (this.zzt.zzf().zzs(null, zzdu.zzai) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            SQLiteDatabase zzh2 = zzh();
            if (zzh2.update("apps", contentValues, "app_id = ?", new String[]{zzt}) == 0 && zzh2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzeh.zzn(zzt));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing app. appId", zzeh.zzn(zzt), e);
        }
    }

    public final void zzE(zzas zzasVar) {
        Preconditions.checkNotNull(zzasVar);
        zzg();
        zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasVar.zza);
        contentValues.put("name", zzasVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzasVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzasVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzasVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzasVar.zzg));
        contentValues.put("last_bundled_day", zzasVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzasVar.zzi);
        contentValues.put("last_sampling_rate", zzasVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzasVar.zze));
        Boolean bool = zzasVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzh().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzeh.zzn(zzasVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing event aggregates. appId", zzeh.zzn(zzasVar.zza), e);
        }
    }

    public final boolean zzF() {
        return zzZ("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzG() {
        return zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzH() {
        return zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    protected final boolean zzI() {
        Context zzau = this.zzt.zzau();
        this.zzt.zzf();
        return zzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzJ(String str, Long l, long j, com.google.android.gms.internal.measurement.zzft zzftVar) {
        zzg();
        zzW();
        Preconditions.checkNotNull(zzftVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbu = zzftVar.zzbu();
        this.zzt.zzay().zzj().zzc("Saving complex main event, appId, data size", this.zzt.zzj().zzd(str), Integer.valueOf(zzbu.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbu);
        try {
            if (zzh().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzt.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzeh.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing complex main event. appId", zzeh.zzn(str), e);
            return false;
        }
    }

    public final boolean zzK(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zzW();
        String str = zzacVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzacVar.zzc.zzb) == null) {
            long zzZ = zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzt.zzf();
            if (zzZ >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzacVar.zzb);
        contentValues.put("name", zzacVar.zzc.zzb);
        zzV(contentValues, "value", Preconditions.checkNotNull(zzacVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzacVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzacVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzacVar.zzh));
        contentValues.put("timed_out_event", this.zzt.zzv().zzan(zzacVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzacVar.zzd));
        contentValues.put("triggered_event", this.zzt.zzv().zzan(zzacVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzacVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzacVar.zzj));
        contentValues.put("expired_event", this.zzt.zzv().zzan(zzacVar.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzt.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzeh.zzn(str));
            return true;
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing conditional user property", zzeh.zzn(str), e);
            return true;
        }
    }

    public final boolean zzL(zzky zzkyVar) {
        Preconditions.checkNotNull(zzkyVar);
        zzg();
        zzW();
        if (zzp(zzkyVar.zza, zzkyVar.zzc) == null) {
            if (zzlb.zzai(zzkyVar.zzc)) {
                if (zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkyVar.zza}) >= this.zzt.zzf().zzf(zzkyVar.zza, zzdu.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzkyVar.zzc)) {
                long zzZ = zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkyVar.zza, zzkyVar.zzb});
                this.zzt.zzf();
                if (zzZ >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkyVar.zza);
        contentValues.put("origin", zzkyVar.zzb);
        contentValues.put("name", zzkyVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkyVar.zzd));
        zzV(contentValues, "value", zzkyVar.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzt.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzeh.zzn(zzkyVar.zza));
            return true;
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing user property. appId", zzeh.zzn(zzkyVar.zza), e);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01fb  */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzU(java.lang.String r20, long r21, long r23, com.google.android.gms.measurement.internal.zzkq r25) {
        /*
            Method dump skipped, instructions count: 511
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzU(java.lang.String, long, long, com.google.android.gms.measurement.internal.zzkq):void");
    }

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzd("Error deleting conditional property", zzeh.zzn(str), this.zzt.zzj().zzf(str2), e);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }

    protected final long zzc(String str, String str2) {
        SQLiteException e;
        long j;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzW();
        SQLiteDatabase zzh2 = zzh();
        zzh2.beginTransaction();
        try {
            try {
                j = zzaa("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
                if (j == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (zzh2.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        this.zzt.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzeh.zzn(str), "first_open_count");
                        return -1L;
                    }
                    j = 0;
                }
            } catch (SQLiteException e2) {
                e = e2;
                j = 0;
            }
            try {
                contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Long.valueOf(1 + j));
            } catch (SQLiteException e3) {
                e = e3;
                this.zzt.zzay().zzd().zzd("Error inserting column. appId", zzeh.zzn(str), "first_open_count", e);
                return j;
            }
            if (zzh2.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
                this.zzt.zzay().zzd().zzc("Failed to update column (got 0). appId", zzeh.zzn(str), "first_open_count");
                return -1L;
            }
            zzh2.setTransactionSuccessful();
            return j;
        } finally {
            zzh2.endTransaction();
        }
    }

    public final long zzd() {
        return zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    public final long zze() {
        return zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzt.zzay().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00db: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:61:0x00db */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle zzi(java.lang.String r8) {
        /*
            r7 = this;
            r7.zzg()
            r7.zzW()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.zzh()     // Catch: java.lang.Throwable -> Lc1 android.database.sqlite.SQLiteException -> Lc3
            java.lang.String[] r2 = new java.lang.String[]{r8}     // Catch: java.lang.Throwable -> Lc1 android.database.sqlite.SQLiteException -> Lc3
            java.lang.String r3 = "select parameters from default_event_params where app_id=?"
            android.database.Cursor r1 = r1.rawQuery(r3, r2)     // Catch: java.lang.Throwable -> Lc1 android.database.sqlite.SQLiteException -> Lc3
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r2 != 0) goto L30
            com.google.android.gms.measurement.internal.zzfr r8 = r7.zzt     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzeh r8 = r8.zzay()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzef r8 = r8.zzj()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            java.lang.String r2 = "Default event parameters not found"
            r8.zza(r2)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r1 == 0) goto L2f
            r1.close()
        L2f:
            return r0
        L30:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzfs r3 = com.google.android.gms.internal.measurement.zzft.zze()     // Catch: java.io.IOException -> La5 android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzll r2 = com.google.android.gms.measurement.internal.zzkv.zzl(r3, r2)     // Catch: java.io.IOException -> La5 android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzfs r2 = (com.google.android.gms.internal.measurement.zzfs) r2     // Catch: java.io.IOException -> La5 android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzkf r2 = r2.zzaC()     // Catch: java.io.IOException -> La5 android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzft r2 = (com.google.android.gms.internal.measurement.zzft) r2     // Catch: java.io.IOException -> La5 android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzkt r8 = r7.zzf     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r8.zzu()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            java.util.List r8 = r2.zzi()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            android.os.Bundle r2 = new android.os.Bundle     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            java.util.Iterator r8 = r8.iterator()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
        L57:
            boolean r3 = r8.hasNext()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r3 == 0) goto L9f
            java.lang.Object r3 = r8.next()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.internal.measurement.zzfx r3 = (com.google.android.gms.internal.measurement.zzfx) r3     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            java.lang.String r4 = r3.zzg()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            boolean r5 = r3.zzu()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r5 == 0) goto L75
            double r5 = r3.zza()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r2.putDouble(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            goto L57
        L75:
            boolean r5 = r3.zzv()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r5 == 0) goto L83
            float r3 = r3.zzb()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r2.putFloat(r4, r3)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            goto L57
        L83:
            boolean r5 = r3.zzy()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r5 == 0) goto L91
            java.lang.String r3 = r3.zzh()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r2.putString(r4, r3)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            goto L57
        L91:
            boolean r5 = r3.zzw()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r5 == 0) goto L57
            long r5 = r3.zzd()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r2.putLong(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            goto L57
        L9f:
            if (r1 == 0) goto La4
            r1.close()
        La4:
            return r2
        La5:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfr r3 = r7.zzt     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzay()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzd()     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzeh.zzn(r8)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            r3.zzc(r4, r8, r2)     // Catch: android.database.sqlite.SQLiteException -> Lbf java.lang.Throwable -> Lda
            if (r1 == 0) goto Lbe
            r1.close()
        Lbe:
            return r0
        Lbf:
            r8 = move-exception
            goto Lc5
        Lc1:
            r7 = move-exception
            goto Ldc
        Lc3:
            r8 = move-exception
            r1 = r0
        Lc5:
            com.google.android.gms.measurement.internal.zzfr r7 = r7.zzt     // Catch: java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzay()     // Catch: java.lang.Throwable -> Lda
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzd()     // Catch: java.lang.Throwable -> Lda
            java.lang.String r2 = "Error selecting default event parameters"
            r7.zzb(r2, r8)     // Catch: java.lang.Throwable -> Lda
            if (r1 == 0) goto Ld9
            r1.close()
        Ld9:
            return r0
        Lda:
            r7 = move-exception
            r0 = r1
        Ldc:
            if (r0 == 0) goto Le1
            r0.close()
        Le1:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzi(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x01fc: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:53:0x01fc */
    /* JADX WARN: Removed duplicated region for block: B:19:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0180 A[Catch: SQLiteException -> 0x01dc, all -> 0x01fb, TryCatch #1 {all -> 0x01fb, blocks: (B:5:0x005f, B:11:0x006b, B:13:0x00d0, B:17:0x00da, B:20:0x0124, B:22:0x0153, B:24:0x015a, B:27:0x0175, B:29:0x0180, B:30:0x0192, B:32:0x01a3, B:34:0x01b1, B:35:0x01ba, B:37:0x01c3, B:42:0x0171, B:44:0x011f, B:47:0x01e2), top: B:2:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01c3 A[Catch: SQLiteException -> 0x01dc, all -> 0x01fb, TRY_LEAVE, TryCatch #1 {all -> 0x01fb, blocks: (B:5:0x005f, B:11:0x006b, B:13:0x00d0, B:17:0x00da, B:20:0x0124, B:22:0x0153, B:24:0x015a, B:27:0x0175, B:29:0x0180, B:30:0x0192, B:32:0x01a3, B:34:0x01b1, B:35:0x01ba, B:37:0x01c3, B:42:0x0171, B:44:0x011f, B:47:0x01e2), top: B:2:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0171 A[Catch: SQLiteException -> 0x01dc, all -> 0x01fb, TryCatch #1 {all -> 0x01fb, blocks: (B:5:0x005f, B:11:0x006b, B:13:0x00d0, B:17:0x00da, B:20:0x0124, B:22:0x0153, B:24:0x015a, B:27:0x0175, B:29:0x0180, B:30:0x0192, B:32:0x01a3, B:34:0x01b1, B:35:0x01ba, B:37:0x01c3, B:42:0x0171, B:44:0x011f, B:47:0x01e2), top: B:2:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011f A[Catch: SQLiteException -> 0x01dc, all -> 0x01fb, TryCatch #1 {all -> 0x01fb, blocks: (B:5:0x005f, B:11:0x006b, B:13:0x00d0, B:17:0x00da, B:20:0x0124, B:22:0x0153, B:24:0x015a, B:27:0x0175, B:29:0x0180, B:30:0x0192, B:32:0x01a3, B:34:0x01b1, B:35:0x01ba, B:37:0x01c3, B:42:0x0171, B:44:0x011f, B:47:0x01e2), top: B:2:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzh zzj(java.lang.String r35) {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzh");
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0126: MOVE (r9 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:33:0x0126 */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0129  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzac zzk(java.lang.String r27, java.lang.String r28) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzk(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzac");
    }

    public final zzak zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1L, false, false, z3, false, z5);
    }

    public final zzak zzm(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        String[] strArr = {str};
        zzak zzakVar = new zzak();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                Cursor query = zzh2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!query.moveToFirst()) {
                    this.zzt.zzay().zzk().zzb("Not updating daily counts, app is not known. appId", zzeh.zzn(str));
                    if (query != null) {
                        query.close();
                    }
                    return zzakVar;
                }
                if (query.getLong(0) == j) {
                    zzakVar.zzb = query.getLong(1);
                    zzakVar.zza = query.getLong(2);
                    zzakVar.zzc = query.getLong(3);
                    zzakVar.zzd = query.getLong(4);
                    zzakVar.zze = query.getLong(5);
                }
                if (z) {
                    zzakVar.zzb += j2;
                }
                if (z2) {
                    zzakVar.zza += j2;
                }
                if (z3) {
                    zzakVar.zzc += j2;
                }
                if (z4) {
                    zzakVar.zzd += j2;
                }
                if (z5) {
                    zzakVar.zze += j2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzakVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzakVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzakVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzakVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzakVar.zze));
                zzh2.update("apps", contentValues, "app_id=?", strArr);
                if (query != null) {
                    query.close();
                }
                return zzakVar;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Error updating daily counts. appId", zzeh.zzn(str), e);
                if (0 != 0) {
                    cursor.close();
                }
                return zzakVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzas zzn(java.lang.String r30, java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzn(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzas");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzky zzp(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r10.zzg()
            r10.zzW()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.zzh()     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteException -> L78
            java.lang.String r2 = "set_timestamp"
            java.lang.String r3 = "value"
            java.lang.String r4 = "origin"
            java.lang.String[] r3 = new java.lang.String[]{r2, r3, r4}     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteException -> L78
            java.lang.String[] r5 = new java.lang.String[]{r11, r12}     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteException -> L78
            java.lang.String r2 = "user_attributes"
            java.lang.String r4 = "app_id=? and name=?"
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L76 android.database.sqlite.SQLiteException -> L78
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            if (r2 != 0) goto L36
            if (r1 == 0) goto L35
            r1.close()
        L35:
            return r0
        L36:
            r2 = 0
            long r7 = r1.getLong(r2)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            r2 = 1
            java.lang.Object r9 = r10.zzq(r1, r2)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            if (r9 != 0) goto L48
            if (r1 == 0) goto L47
            r1.close()
        L47:
            return r0
        L48:
            r2 = 2
            java.lang.String r5 = r1.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzky r2 = new com.google.android.gms.measurement.internal.zzky     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            r3 = r2
            r4 = r11
            r6 = r12
            r3.<init>(r4, r5, r6, r7, r9)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            boolean r3 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            if (r3 == 0) goto L6e
            com.google.android.gms.measurement.internal.zzfr r3 = r10.zzt     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzay()     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzd()     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            java.lang.String r4 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzeh.zzn(r11)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
            r3.zzb(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L74 java.lang.Throwable -> L9e
        L6e:
            if (r1 == 0) goto L73
            r1.close()
        L73:
            return r2
        L74:
            r2 = move-exception
            goto L7b
        L76:
            r10 = move-exception
            goto La0
        L78:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L7b:
            com.google.android.gms.measurement.internal.zzfr r3 = r10.zzt     // Catch: java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzay()     // Catch: java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzd()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r4 = "Error querying user property. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzeh.zzn(r11)     // Catch: java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzfr r10 = r10.zzt     // Catch: java.lang.Throwable -> L9e
            com.google.android.gms.measurement.internal.zzec r10 = r10.zzj()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r10 = r10.zzf(r12)     // Catch: java.lang.Throwable -> L9e
            r3.zzd(r4, r11, r10, r2)     // Catch: java.lang.Throwable -> L9e
            if (r1 == 0) goto L9d
            r1.close()
        L9d:
            return r0
        L9e:
            r10 = move-exception
            r0 = r1
        La0:
            if (r0 == 0) goto La5
            r0.close()
        La5:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzp(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzky");
    }

    final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            this.zzt.zzay().zzd().zza("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type != 4) {
            this.zzt.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
            return null;
        }
        this.zzt.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzr() {
        /*
            r4 = this;
            android.database.sqlite.SQLiteDatabase r0 = r4.zzh()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L24 android.database.sqlite.SQLiteException -> L26
            boolean r2 = r0.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L22 java.lang.Throwable -> L3e
            if (r2 == 0) goto L1c
            r2 = 0
            java.lang.String r4 = r0.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L22 java.lang.Throwable -> L3e
            if (r0 == 0) goto L1b
            r0.close()
        L1b:
            return r4
        L1c:
            if (r0 == 0) goto L21
            r0.close()
        L21:
            return r1
        L22:
            r2 = move-exception
            goto L29
        L24:
            r4 = move-exception
            goto L40
        L26:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L29:
            com.google.android.gms.measurement.internal.zzfr r4 = r4.zzt     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzay()     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzd()     // Catch: java.lang.Throwable -> L3e
            java.lang.String r3 = "Database error getting next bundle app id"
            r4.zzb(r3, r2)     // Catch: java.lang.Throwable -> L3e
            if (r0 == 0) goto L3d
            r0.close()
        L3d:
            return r1
        L3e:
            r4 = move-exception
            r1 = r0
        L40:
            if (r1 == 0) goto L45
            r1.close()
        L45:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzr():java.lang.String");
    }

    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
    
        r2 = r27.zzt.zzay().zzd();
        r27.zzt.zzf();
        r2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzt(java.lang.String r28, java.lang.String[] r29) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzt(java.lang.String, java.lang.String[]):java.util.List");
    }

    public final List zzu(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                this.zzt.zzf();
                cursor = zzh().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j = cursor.getLong(2);
                    Object zzq = zzq(cursor, 3);
                    if (zzq == null) {
                        this.zzt.zzay().zzd().zzb("Read invalid user property value, ignoring it. appId", zzeh.zzn(str));
                    } else {
                        arrayList.add(new zzky(str, str2, string, j, zzq));
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Error querying user properties. appId", zzeh.zzn(str), e);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a9, code lost:
    
        r0 = r17.zzt.zzay().zzd();
        r17.zzt.zzf();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzv(java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzv(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final void zzw() {
        zzW();
        zzh().beginTransaction();
    }

    public final void zzx() {
        zzW();
        zzh().endTransaction();
    }

    final void zzy(List list) {
        zzg();
        zzW();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzI()) {
            String str = "(" + TextUtils.join(FirebaseConstants.SEPARATOR, list) + ")";
            if (zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                this.zzt.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                zzh().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    final void zzz() {
        zzg();
        zzW();
        if (zzI()) {
            long zza2 = this.zzf.zzs().zza.zza();
            long elapsedRealtime = this.zzt.zzav().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            this.zzt.zzf();
            if (abs > ((Long) zzdu.zzx.zza(null)).longValue()) {
                this.zzf.zzs().zza.zzb(elapsedRealtime);
                zzg();
                zzW();
                if (zzI()) {
                    SQLiteDatabase zzh2 = zzh();
                    String valueOf = String.valueOf(this.zzt.zzav().currentTimeMillis());
                    this.zzt.zzf();
                    int delete = zzh2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{valueOf, String.valueOf(zzag.zzA())});
                    if (delete > 0) {
                        this.zzt.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }
}
