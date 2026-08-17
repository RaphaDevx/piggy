package com.salesforce.marketingcloud.proximity;

import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.proximity.e;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class d extends e {
    private final boolean i;
    private final JSONObject j;

    public d(boolean z, JSONObject jSONObject) {
        this.i = z;
        this.j = jSONObject;
    }

    @Override // com.salesforce.marketingcloud.f
    protected void a(InitializationStatus.a aVar) {
        aVar.d(this.i);
    }

    @Override // com.salesforce.marketingcloud.proximity.e
    public void b(List<c> list) {
        g.d(e.h, "unmonitorBeaconRegions call ignored because of unsupported device.", new Object[0]);
    }

    @Override // com.salesforce.marketingcloud.proximity.e
    public void c() {
        g.d(e.h, "stopMonitoringBeaconRegions() call ignored because of unsupported device.", new Object[0]);
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        return this.j;
    }

    @Override // com.salesforce.marketingcloud.proximity.e
    public void a(List<c> list) {
        g.d(e.h, "monitorBeaconRegions call ignored because of unsupported device.", new Object[0]);
    }

    @Override // com.salesforce.marketingcloud.proximity.e
    public void b(e.a aVar) {
        g.d(e.h, "unregisterProximityEventListener(%s) call ignored because of unsupported device.", aVar != null ? aVar.getClass().getSimpleName() : "null");
    }

    @Override // com.salesforce.marketingcloud.proximity.e
    public void a(e.a aVar) {
        g.d(e.h, "registerProximityEventListener(%s) call ignored because of unsupported device.", aVar != null ? aVar.getClass().getSimpleName() : "null");
    }
}
