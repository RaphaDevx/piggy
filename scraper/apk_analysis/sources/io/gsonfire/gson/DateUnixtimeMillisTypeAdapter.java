package io.gsonfire.gson;

import java.util.Date;

/* loaded from: classes2.dex */
public class DateUnixtimeMillisTypeAdapter extends DateUnixtimeTypeAdapter {
    public DateUnixtimeMillisTypeAdapter(boolean z) {
        super(z);
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected long toTimestamp(Date date) {
        return date.getTime();
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected Date fromTimestamp(long j) {
        return new Date(j);
    }
}
