package com.salesforce.marketingcloud.messages.geofence;

import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.MessageResponse;
import com.salesforce.marketingcloud.messages.Region;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class GeofenceMessageResponse implements MessageResponse {
    public final List<Region> fences;
    private final LatLon refreshCenter;
    private final int refreshRadius;

    static final class a extends Lambda implements Function0<String> {
        public static final a b = new a();

        a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to parse Region from geofence message payload.";
        }
    }

    public GeofenceMessageResponse(LatLon refreshCenter, int i, List<Region> fences) {
        Intrinsics.checkNotNullParameter(refreshCenter, "refreshCenter");
        Intrinsics.checkNotNullParameter(fences, "fences");
        this.refreshCenter = refreshCenter;
        this.refreshRadius = i;
        this.fences = fences;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "fences", imports = {}))
    public final List<Region> fences() {
        return this.fences;
    }

    @Override // com.salesforce.marketingcloud.messages.MessageResponse
    public LatLon getRefreshCenter() {
        return this.refreshCenter;
    }

    @Override // com.salesforce.marketingcloud.messages.MessageResponse
    public int getRefreshRadius() {
        return this.refreshRadius;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to getter", replaceWith = @ReplaceWith(expression = "getRefreshCenter()", imports = {}))
    public final LatLon refreshCenter() {
        return getRefreshCenter();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to getter", replaceWith = @ReplaceWith(expression = "getRefreshRadius()", imports = {}))
    public final int refreshRadius() {
        return getRefreshRadius();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GeofenceMessageResponse(org.json.JSONObject r9) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse.<init>(org.json.JSONObject):void");
    }
}
