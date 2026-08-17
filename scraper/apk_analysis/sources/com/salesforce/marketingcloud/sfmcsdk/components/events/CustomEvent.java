package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomEvent.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CustomEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;", "name", "", "attributes", "", "", "producer", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;", "category", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;", "(Ljava/lang/String;Ljava/util/Map;Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;)V", "getCategory", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;", "getProducer", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;", "toString", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CustomEvent extends Event {
    private final Map<String, Object> attributes;
    private final Event.Category category;
    private final String name;
    private final Event.Producer producer;

    public /* synthetic */ CustomEvent(String str, Map map, Event.Producer producer, Event.Category category, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? Event.Producer.SFMC_SDK : producer, (i & 8) != 0 ? Event.Category.ENGAGEMENT : category);
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.events.Event
    public Event.Producer getProducer() {
        return this.producer;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.events.Event
    public Event.Category getCategory() {
        return this.category;
    }

    public CustomEvent(String name, Map<String, ? extends Object> attributes, Event.Producer producer, Event.Category category) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(producer, "producer");
        Intrinsics.checkNotNullParameter(category, "category");
        this.name = name;
        this.attributes = attributes;
        this.producer = producer;
        this.category = category;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.events.Event
    /* renamed from: name, reason: from getter */
    public String getName() {
        return this.name;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.events.Event
    public Map<String, Object> attributes() {
        return this.attributes;
    }

    public String toString() {
        return "CustomEvent(name='" + this.name + "', producer=" + getProducer() + ", category=" + getCategory() + ", attributes=" + this.attributes + ")";
    }
}
