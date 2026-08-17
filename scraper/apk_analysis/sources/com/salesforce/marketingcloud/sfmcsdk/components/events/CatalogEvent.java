package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogEvent.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/EngagementEvent;", "name", "", "catalogObject", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;", "(Ljava/lang/String;Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;)V", "getCatalogObject", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;", "Companion", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CommentCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/FavoriteCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/QuickViewCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ReviewCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShareCatalogEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ViewCatalogDetailEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ViewCatalogEvent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class CatalogEvent extends EngagementEvent {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CatalogObject catalogObject;

    public /* synthetic */ CatalogEvent(String str, CatalogObject catalogObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, catalogObject);
    }

    @JvmStatic
    public static final CommentCatalogEvent comment(CatalogObject catalogObject) {
        return INSTANCE.comment(catalogObject);
    }

    @JvmStatic
    public static final FavoriteCatalogEvent favorite(CatalogObject catalogObject) {
        return INSTANCE.favorite(catalogObject);
    }

    @JvmStatic
    public static final QuickViewCatalogEvent quickView(CatalogObject catalogObject) {
        return INSTANCE.quickView(catalogObject);
    }

    @JvmStatic
    public static final ReviewCatalogEvent review(CatalogObject catalogObject) {
        return INSTANCE.review(catalogObject);
    }

    @JvmStatic
    public static final ShareCatalogEvent share(CatalogObject catalogObject) {
        return INSTANCE.share(catalogObject);
    }

    @JvmStatic
    public static final ViewCatalogEvent view(CatalogObject catalogObject) {
        return INSTANCE.view(catalogObject);
    }

    @JvmStatic
    public static final ViewCatalogDetailEvent viewDetail(CatalogObject catalogObject) {
        return INSTANCE.viewDetail(catalogObject);
    }

    public final CatalogObject getCatalogObject() {
        return this.catalogObject;
    }

    /* compiled from: CatalogEvent.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0013"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogEvent$Companion;", "", "()V", "comment", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CommentCatalogEvent;", "catalogObject", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CatalogObject;", "favorite", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/FavoriteCatalogEvent;", "quickView", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/QuickViewCatalogEvent;", "review", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ReviewCatalogEvent;", FirebaseAnalytics.Event.SHARE, "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShareCatalogEvent;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ViewCatalogEvent;", "viewDetail", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ViewCatalogDetailEvent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ViewCatalogEvent view(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ViewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ViewCatalogDetailEvent viewDetail(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ViewCatalogDetailEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final QuickViewCatalogEvent quickView(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new QuickViewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ShareCatalogEvent share(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ShareCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ReviewCatalogEvent review(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ReviewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final CommentCatalogEvent comment(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new CommentCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final FavoriteCatalogEvent favorite(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new FavoriteCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private CatalogEvent(String str, CatalogObject catalogObject) {
        super(str, null);
        this.catalogObject = catalogObject;
    }
}
