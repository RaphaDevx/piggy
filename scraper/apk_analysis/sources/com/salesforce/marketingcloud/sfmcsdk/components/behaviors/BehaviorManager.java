package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.EnumSet;
import kotlin.Metadata;

/* compiled from: BehaviorManager.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManager;", "", "registerForBehaviors", "", "behaviorTypes", "Ljava/util/EnumSet;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorListener;", "unregisterForAllBehaviors", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface BehaviorManager {
    void registerForBehaviors(EnumSet<BehaviorType> behaviorTypes, BehaviorListener listener);

    void unregisterForAllBehaviors(BehaviorListener listener);
}
