package com.salesforce.marketingcloud.events;

import java.util.Map;
import kotlin.collections.MapsKt;

/* loaded from: classes2.dex */
public final class a implements Event {
    @Override // com.salesforce.marketingcloud.events.Event
    public Map<String, Object> attributes() {
        return MapsKt.emptyMap();
    }

    @Override // com.salesforce.marketingcloud.events.Event
    public String name() {
        return "$appOpen";
    }
}
