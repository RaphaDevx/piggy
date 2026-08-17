package com.tealium.internal;

import android.content.SharedPreferences;
import com.tealium.library.DataSources;

/* compiled from: Session.java */
/* loaded from: classes2.dex */
public final class e {
    private long a;
    private long b;
    private int c;
    private boolean d;

    public e(long j) {
        this(j, 0L, 0, false);
    }

    public static e a(SharedPreferences sharedPreferences) {
        return new e(sharedPreferences.getLong(DataSources.Key.TEALIUM_SESSION_ID, 0L), sharedPreferences.getLong("tealium_session_last_event_time", 0L), sharedPreferences.getInt("tealium_session_event_count", 0), sharedPreferences.getBoolean("tealium_session_started", false));
    }

    public long b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public boolean d() {
        return this.d;
    }

    public e(long j, long j2, int i, boolean z) {
        this.a = j;
        this.b = j2;
        this.c = i;
        this.d = z;
    }

    public static void a(SharedPreferences sharedPreferences, e eVar) {
        sharedPreferences.edit().putLong(DataSources.Key.TEALIUM_SESSION_ID, eVar.b()).putLong("tealium_session_last_event_time", eVar.c()).putInt("tealium_session_event_count", eVar.a()).putBoolean("tealium_session_started", eVar.d()).apply();
    }

    public void a(long j) {
        this.b = j;
    }

    public int a() {
        return this.c;
    }

    void a(int i) {
        this.c = i;
    }

    void a(boolean z) {
        this.d = z;
    }
}
