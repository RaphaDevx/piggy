package ch.datatrans.payment.paymentmethods.boncard;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÀ\u0003¢\u0006\u0004\b\u0007\u0010\bJ \u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\b¨\u0006\u0019"}, d2 = {"Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;", "", "", "Lch/datatrans/payment/paymentmethods/boncard/BoncardType;", "boncardTypes", "<init>", "(Ljava/util/List;)V", "component1$lib_release", "()Ljava/util/List;", "component1", "copy", "(Ljava/util/List;)Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/util/List;", "getBoncardTypes$lib_release", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class BoncardConfig {

    /* renamed from: a, reason: from kotlin metadata */
    public final List boncardTypes;

    /* JADX WARN: Multi-variable type inference failed */
    public BoncardConfig() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BoncardConfig copy$default(BoncardConfig boncardConfig, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = boncardConfig.boncardTypes;
        }
        return boncardConfig.copy(list);
    }

    public final List<BoncardType> component1$lib_release() {
        return this.boncardTypes;
    }

    public final BoncardConfig copy(List<? extends BoncardType> boncardTypes) {
        Intrinsics.checkNotNullParameter(boncardTypes, "boncardTypes");
        return new BoncardConfig(boncardTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BoncardConfig) && Intrinsics.areEqual(this.boncardTypes, ((BoncardConfig) other).boncardTypes);
    }

    public final List<BoncardType> getBoncardTypes$lib_release() {
        return this.boncardTypes;
    }

    public int hashCode() {
        return this.boncardTypes.hashCode();
    }

    public String toString() {
        return "BoncardConfig(boncardTypes=" + this.boncardTypes + ')';
    }

    public BoncardConfig(List<? extends BoncardType> boncardTypes) {
        Intrinsics.checkNotNullParameter(boncardTypes, "boncardTypes");
        this.boncardTypes = boncardTypes;
    }

    public /* synthetic */ BoncardConfig(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.listOf(BoncardType.GIFT_CARD) : list);
    }
}
