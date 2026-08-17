package io.gsonfire.gson;

import java.util.Date;

/* loaded from: classes2.dex */
public final class DateUnixtimeSecondsTypeAdapter extends DateUnixtimeTypeAdapter {
    public DateUnixtimeSecondsTypeAdapter(boolean z) {
        super(z);
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected long toTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected Date fromTimestamp(long j) {
        return new Date(j * 1000);
    }
}
