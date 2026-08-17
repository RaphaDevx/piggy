package com.salesforce.marketingcloud.storage.db.upgrades;

/* loaded from: classes2.dex */
public class j {
    private static final String a = com.salesforce.marketingcloud.g.a("Version7ToVersion8");

    private j() {
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.database.sqlite.SQLiteDatabase r8) {
        /*
            java.lang.String r0 = "id"
            r1 = 0
            r2 = 0
            java.lang.String r3 = "SELECT id,read,message_deleted FROM cloud_page_messages WHERE message_type=1"
            android.database.Cursor r3 = r8.rawQuery(r3, r1)     // Catch: java.lang.Exception -> L61
            if (r3 == 0) goto L5f
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Exception -> L61
            if (r4 == 0) goto L58
        L12:
            r4 = 1
            java.lang.String r5 = "message_deleted"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Exception -> L5d
            int r5 = r3.getInt(r5)     // Catch: java.lang.Exception -> L5d
            r6 = -1
            if (r5 != r4) goto L22
            r5 = 2
            goto L31
        L22:
            java.lang.String r5 = "read"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Exception -> L5d
            int r5 = r3.getInt(r5)     // Catch: java.lang.Exception -> L5d
            if (r5 != r4) goto L30
            r5 = r4
            goto L31
        L30:
            r5 = r6
        L31:
            if (r5 == r6) goto L51
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5d
            r6.<init>()     // Catch: java.lang.Exception -> L5d
            int r7 = r3.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d
            java.lang.String r7 = r3.getString(r7)     // Catch: java.lang.Exception -> L5d
            r6.put(r0, r7)     // Catch: java.lang.Exception -> L5d
            java.lang.String r7 = "status"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L5d
            r6.put(r7, r5)     // Catch: java.lang.Exception -> L5d
            java.lang.String r5 = "inbox_message_status"
            r8.insert(r5, r1, r6)     // Catch: java.lang.Exception -> L5d
        L51:
            boolean r5 = r3.moveToNext()     // Catch: java.lang.Exception -> L5d
            if (r5 != 0) goto L12
            goto L59
        L58:
            r4 = r2
        L59:
            r3.close()     // Catch: java.lang.Exception -> L5d
            goto L6c
        L5d:
            r0 = move-exception
            goto L63
        L5f:
            r4 = r2
            goto L6c
        L61:
            r0 = move-exception
            r4 = r2
        L63:
            java.lang.String r3 = com.salesforce.marketingcloud.storage.db.upgrades.j.a
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "Unable to set inbox message statuses for legacy messages"
            com.salesforce.marketingcloud.g.b(r3, r0, r6, r5)
        L6c:
            java.lang.String r0 = "DELETE FROM cloud_page_messages WHERE message_type=1"
            if (r4 == 0) goto L9e
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Exception -> L86
            r3.<init>()     // Catch: java.lang.Exception -> L86
            java.lang.String r4 = "message_type"
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L86
            r3.put(r4, r5)     // Catch: java.lang.Exception -> L86
            java.lang.String r4 = "cloud_page_messages"
            r8.update(r4, r3, r1, r1)     // Catch: java.lang.Exception -> L86
            goto L9e
        L86:
            r3 = move-exception
            java.lang.String r4 = com.salesforce.marketingcloud.storage.db.upgrades.j.a
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "Unable to update message_type for legacy Inbox messages.  Attempting to delete them."
            com.salesforce.marketingcloud.g.b(r4, r3, r6, r5)
            r8.execSQL(r0, r1)     // Catch: java.lang.Exception -> L94
            goto L9e
        L94:
            r3 = move-exception
            java.lang.String r4 = com.salesforce.marketingcloud.storage.db.upgrades.j.a
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "Unable to delete legacy Inbox messages."
            com.salesforce.marketingcloud.g.b(r4, r3, r6, r5)
        L9e:
            r8.execSQL(r0, r1)     // Catch: java.lang.Exception -> La2
            goto Lac
        La2:
            r8 = move-exception
            java.lang.String r0 = com.salesforce.marketingcloud.storage.db.upgrades.j.a
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r2 = "Final attempt to delete legacy Inbox messages failed."
            com.salesforce.marketingcloud.g.b(r0, r8, r2, r1)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.j.a(android.database.sqlite.SQLiteDatabase):void");
    }
}
