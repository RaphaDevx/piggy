package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrderEvent.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/OrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/EngagementEvent;", "name", "", "order", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;", "(Ljava/lang/String;Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;)V", "getOrder", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;", "Companion", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CancelOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/DeliverOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ExchangeOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/PreorderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/PurchaseOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ReturnOrderEvent;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShipOrderEvent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class OrderEvent extends EngagementEvent {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Order order;

    public /* synthetic */ OrderEvent(String str, Order order, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, order);
    }

    @JvmStatic
    public static final CancelOrderEvent cancel(Order order) {
        return INSTANCE.cancel(order);
    }

    @JvmStatic
    public static final DeliverOrderEvent deliver(Order order) {
        return INSTANCE.deliver(order);
    }

    @JvmStatic
    public static final ExchangeOrderEvent exchange(Order order) {
        return INSTANCE.exchange(order);
    }

    @JvmStatic
    public static final PreorderEvent preorder(Order order) {
        return INSTANCE.preorder(order);
    }

    @JvmStatic
    public static final PurchaseOrderEvent purchase(Order order) {
        return INSTANCE.purchase(order);
    }

    @JvmStatic
    public static final ReturnOrderEvent returnOrder(Order order) {
        return INSTANCE.returnOrder(order);
    }

    @JvmStatic
    public static final ShipOrderEvent ship(Order order) {
        return INSTANCE.ship(order);
    }

    public final Order getOrder() {
        return this.order;
    }

    /* compiled from: OrderEvent.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0013"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/OrderEvent$Companion;", "", "()V", "cancel", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/CancelOrderEvent;", "order", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Order;", "deliver", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/DeliverOrderEvent;", "exchange", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ExchangeOrderEvent;", "preorder", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/PreorderEvent;", FirebaseAnalytics.Event.PURCHASE, "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/PurchaseOrderEvent;", "returnOrder", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ReturnOrderEvent;", "ship", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/ShipOrderEvent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PurchaseOrderEvent purchase(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new PurchaseOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final PreorderEvent preorder(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new PreorderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final CancelOrderEvent cancel(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new CancelOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ShipOrderEvent ship(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ShipOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final DeliverOrderEvent deliver(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new DeliverOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ReturnOrderEvent returnOrder(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ReturnOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        @JvmStatic
        public final ExchangeOrderEvent exchange(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ExchangeOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private OrderEvent(String str, Order order) {
        super(str, null);
        this.order = order;
    }
}
