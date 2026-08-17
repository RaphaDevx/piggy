package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrderEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShipOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/OrderEvent;", "order", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ShipOrderEvent extends OrderEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShipOrderEvent(Order order) {
        super("Ship", order, null);
        Intrinsics.checkNotNullParameter(order, "order");
    }
}
