package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements Event {
    private final String a;
    private final Map<String, Object> b;
    private final Event.Producer c;

    public b(String name, Map<String, ? extends Object> attributes, Event.Producer producer) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(producer, "producer");
        this.a = name;
        this.b = attributes;
        this.c = producer;
    }

    public final Map<String, Object> a() {
        return this.b;
    }

    @Override // com.salesforce.marketingcloud.events.Event
    public Map<String, Object> attributes() {
        return this.b;
    }

    @Override // com.salesforce.marketingcloud.events.Event
    public Event.Producer getProducer() {
        return this.c;
    }

    @Override // com.salesforce.marketingcloud.events.Event
    public String name() {
        return this.a;
    }

    public /* synthetic */ b(String str, Map map, Event.Producer producer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map, (i & 4) != 0 ? Event.Producer.PUSH : producer);
    }
}
