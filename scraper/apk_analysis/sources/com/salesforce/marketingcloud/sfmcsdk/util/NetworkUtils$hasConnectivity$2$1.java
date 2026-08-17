package com.salesforce.marketingcloud.sfmcsdk.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: NetworkUtils.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class NetworkUtils$hasConnectivity$2$1 extends Lambda implements Function0<String> {
    public static final NetworkUtils$hasConnectivity$2$1 INSTANCE = new NetworkUtils$hasConnectivity$2$1();

    NetworkUtils$hasConnectivity$2$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final String invoke() {
        return "Device has _no_ connectivity.";
    }
}
