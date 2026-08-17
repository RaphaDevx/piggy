package ch.coop.supercardapp.core.helpers;

import android.content.Context;
import ch.coop.supercardapp.R;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class APIHelper {
    public static final int API_READ_TIMEOUT_MS = 20000;

    public int getReadTimeoutMs() {
        return 20000;
    }

    public String getClientId() {
        return "APP_SUPERCARD";
    }

    public String getCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public String getBasePath(Context context) {
        return context.getString(R.string.api_base);
    }
}
