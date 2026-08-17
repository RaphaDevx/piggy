package com.salesforce.marketingcloud.messages.proximity;

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
public final class ProximityMessageResponse implements MessageResponse {
    public final List<Region> beacons;
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
            return "Unable to parse Region from proximity message payload.";
        }
    }

    public ProximityMessageResponse(LatLon refreshCenter, int i, List<Region> beacons) {
        Intrinsics.checkNotNullParameter(refreshCenter, "refreshCenter");
        Intrinsics.checkNotNullParameter(beacons, "beacons");
        this.refreshCenter = refreshCenter;
        this.refreshRadius = i;
        this.beacons = beacons;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "beacons", imports = {}))
    public final List<Region> beacons() {
        return this.beacons;
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
    public ProximityMessageResponse(org.json.JSONObject r9) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.proximity.ProximityMessageResponse.<init>(org.json.JSONObject):void");
    }
}
