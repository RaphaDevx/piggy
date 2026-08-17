package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogObject.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\b\b\u0018\u00002\u00020\u0001BI\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u0006¢\u0006\u0002\u0010\tR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;", "", "type", "", "id", "attributes", "", "relatedCatalogObjects", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "getId", "()Ljava/lang/String;", "getRelatedCatalogObjects", "getType", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CatalogObject {
    private final Map<String, Object> attributes;
    private final String id;
    private final Map<String, List<String>> relatedCatalogObjects;
    private final String type;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CatalogObject(String type, String id) {
        this(type, id, null, null, 12, null);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CatalogObject(String type, String id, Map<String, ? extends Object> attributes) {
        this(type, id, attributes, null, 8, null);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CatalogObject(String type, String id, Map<String, ? extends Object> attributes, Map<String, ? extends List<String>> relatedCatalogObjects) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(relatedCatalogObjects, "relatedCatalogObjects");
        this.type = type;
        this.id = id;
        this.attributes = attributes;
        this.relatedCatalogObjects = relatedCatalogObjects;
    }

    public final String getType() {
        return this.type;
    }

    public final String getId() {
        return this.id;
    }

    public /* synthetic */ CatalogObject(String str, String str2, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? MapsKt.emptyMap() : map2);
    }

    public final Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public final Map<String, List<String>> getRelatedCatalogObjects() {
        return this.relatedCatalogObjects;
    }
}
