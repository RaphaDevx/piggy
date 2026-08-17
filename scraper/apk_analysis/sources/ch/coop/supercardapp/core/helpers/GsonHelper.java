package ch.coop.supercardapp.core.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.OffsetDateTime;

/* loaded from: classes3.dex */
public final class GsonHelper {
    public static Gson create() {
        return new GsonBuilder().registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter()).create();
    }
}
