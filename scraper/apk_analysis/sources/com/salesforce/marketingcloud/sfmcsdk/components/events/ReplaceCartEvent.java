package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CartEvent.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ReplaceCartEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CartEvent;", "lineItems", "", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/LineItem;", "(Ljava/util/List;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ReplaceCartEvent extends CartEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReplaceCartEvent(List<LineItem> lineItems) {
        super("Replace Cart", lineItems, null);
        Intrinsics.checkNotNullParameter(lineItems, "lineItems");
    }
}
