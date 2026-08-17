package com.tealium.internal.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tealium.library.Tealium;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class DispatchStoreImpl implements DispatchStore {
    private final SQLiteDatabase a;
    private int b;
    private int c;
    private float d;

    public DispatchStoreImpl(Tealium.Config config) {
        this.a = a(config);
        update(config.getPublishSettings().getOfflineDispatchLimit(), config.getPublishSettings().getDispatchExpiration());
    }

    private void a() {
        Cursor rawQuery = this.a.rawQuery("SELECT COUNT(*) FROM dispatch", null);
        rawQuery.moveToFirst();
        this.b = rawQuery.getInt(0);
        rawQuery.close();
    }

    @Override // com.tealium.internal.data.DispatchStore
    public Dispatch[] dequeueDispatches() {
        if (this.b == 0) {
            return new Dispatch[0];
        }
        Cursor query = this.a.query("dispatch", new String[]{"post_time", "data_json"}, null, null, null, null, "post_time ASC");
        int count = query.getCount();
        Dispatch[] dispatchArr = new Dispatch[count];
        query.moveToFirst();
        int i = 0;
        while (!query.isAfterLast()) {
            int i2 = i + 1;
            try {
                dispatchArr[i] = new Dispatch(query.getLong(0), new JSONObject(query.getString(1)));
                i = i2;
            } catch (JSONException unused) {
            }
            query.moveToNext();
        }
        if (i != count) {
            dispatchArr = (Dispatch[]) Arrays.copyOf(dispatchArr, i);
        }
        query.close();
        this.a.delete("dispatch", null, null);
        this.b = 0;
        return dispatchArr;
    }

    @Override // com.tealium.internal.data.DispatchStore
    public void enqueueDispatch(Dispatch dispatch) {
        if (dispatch == null) {
            throw new IllegalArgumentException("dispatch must not be null.");
        }
        a(this.a, this.d);
        a();
        int i = this.c;
        if (i == 0) {
            return;
        }
        int i2 = this.b;
        int i3 = i2 + 1;
        if (i == -1 || i3 <= i) {
            this.b = i2 + 1;
        } else {
            this.a.execSQL("DELETE FROM dispatch WHERE rowid IN ( \tSELECT rowid \tFROM dispatch\tORDER BY post_time ASC \tLIMIT ? )", new Object[]{Integer.valueOf(i3 - i)});
            this.b = this.c;
        }
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("data_json", dispatch.toJsonString());
        contentValues.put("post_time", Long.valueOf(dispatch.getCreationTime()));
        this.a.insert("dispatch", null, contentValues);
    }

    @Override // com.tealium.internal.data.DispatchStore
    public int getCount() {
        return this.b;
    }

    @Override // com.tealium.internal.data.DispatchStore
    public void purgeUserNotConsented(Dispatch dispatch) {
        this.a.delete("dispatch", "data_json = ?", new String[]{dispatch.toJsonString()});
    }

    @Override // com.tealium.internal.data.DispatchStore
    public void update(int i, float f) {
        this.c = i;
        this.d = f;
        a(this.a, f);
        a();
    }

    private static void a(SQLiteDatabase sQLiteDatabase, float f) {
        if (f < 0.0f) {
            return;
        }
        sQLiteDatabase.delete("dispatch", "post_time < ?", new String[]{Long.toString(System.currentTimeMillis() - (((long) f) * 86400000))});
    }

    private static SQLiteDatabase a(Tealium.Config config) {
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(new File(config.getTealiumDir(), "tealium.db").getAbsolutePath(), null, 268435456);
        openDatabase.execSQL(String.format(Locale.ROOT, "CREATE TABLE IF NOT EXISTS %s ( \t%s TEXT NOT NULL, \t%s INT NOT NULL )", "dispatch", "data_json", "post_time"));
        return openDatabase;
    }
}
