package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.Region;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class l {
    public static final a a = new a(null);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Region a(LatLon center, int i) {
            Intrinsics.checkNotNullParameter(center, "center");
            return Region.Companion.magicFence$sdk_release(center, i);
        }

        private a() {
        }

        @JvmStatic
        public final boolean a(Region region) {
            Intrinsics.checkNotNullParameter(region, "region");
            return region.isInside$sdk_release();
        }

        @JvmStatic
        public final void a(Region region, boolean z) {
            Intrinsics.checkNotNullParameter(region, "region");
            region.setInside$sdk_release(z);
        }
    }

    @JvmStatic
    public static final boolean a(Region region) {
        return a.a(region);
    }

    @JvmStatic
    public static final Region a(LatLon latLon, int i) {
        return a.a(latLon, i);
    }

    @JvmStatic
    public static final void a(Region region, boolean z) {
        a.a(region, z);
    }
}
