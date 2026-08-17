package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CartEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/RemoveFromCartEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CartEvent;", "lineItem", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/LineItem;", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/events/LineItem;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RemoveFromCartEvent extends CartEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoveFromCartEvent(LineItem lineItem) {
        super("Remove From Cart", CollectionsKt.listOf(lineItem), null);
        Intrinsics.checkNotNullParameter(lineItem, "lineItem");
    }
}
