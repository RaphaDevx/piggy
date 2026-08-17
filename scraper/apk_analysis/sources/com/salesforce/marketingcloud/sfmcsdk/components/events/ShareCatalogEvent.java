package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShareCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogEvent;", "catalogObject", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ShareCatalogEvent extends CatalogEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShareCatalogEvent(CatalogObject catalogObject) {
        super("Share Catalog Object", catalogObject, null);
        Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
    }
}
