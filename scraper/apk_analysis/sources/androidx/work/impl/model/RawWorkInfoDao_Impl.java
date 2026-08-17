package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.Data;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d7 A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ea A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c6 A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bb A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a4 A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0095 A[Catch: all -> 0x00ff, TryCatch #0 {all -> 0x00ff, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00cb, B:33:0x00d7, B:34:0x00dc, B:36:0x00ea, B:38:0x00ef, B:40:0x00c6, B:41:0x00bb, B:42:0x00a4, B:45:0x00b0, B:46:0x00ac, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    @Override // androidx.work.impl.model.RawWorkInfoDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<androidx.work.impl.model.WorkSpec.WorkInfoPojo> getWorkInfoPojos(final androidx.sqlite.db.SupportSQLiteQuery r21) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.RawWorkInfoDao_Impl.getWorkInfoPojos(androidx.sqlite.db.SupportSQLiteQuery):java.util.List");
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            /* JADX WARN: Removed duplicated region for block: B:23:0x0096  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x00a5  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00bb  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00da A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00ed A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00f2 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:40:0x00c9 A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:41:0x00be A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:42:0x00a7 A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0098 A[Catch: all -> 0x0102, TryCatch #0 {all -> 0x0102, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c9, B:41:0x00be, B:42:0x00a7, B:45:0x00b3, B:46:0x00af, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.util.List<androidx.work.impl.model.WorkSpec.WorkInfoPojo> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 263
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.RawWorkInfoDao_Impl.AnonymousClass1.call():java.util.List");
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.getSize();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                ArrayList<String> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(query.isNull(0) ? null : query.getString(0));
                }
            }
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<Data>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.getSize();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                    arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                ArrayList<Data> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(Data.fromByteArray(query.isNull(0) ? null : query.getBlob(0)));
                }
            }
        } finally {
            query.close();
        }
    }
}
