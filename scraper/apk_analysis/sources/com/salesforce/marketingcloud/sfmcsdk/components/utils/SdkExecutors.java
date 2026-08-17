package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SdkExecutors.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;", "", "diskIO", "Ljava/util/concurrent/ExecutorService;", "networkIO", "(Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ExecutorService;)V", "getDiskIO", "()Ljava/util/concurrent/ExecutorService;", "getNetworkIO", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SdkExecutors {
    private final ExecutorService diskIO;
    private final ExecutorService networkIO;

    /* JADX WARN: Multi-variable type inference failed */
    public SdkExecutors() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public SdkExecutors(ExecutorService diskIO, ExecutorService networkIO) {
        Intrinsics.checkNotNullParameter(diskIO, "diskIO");
        Intrinsics.checkNotNullParameter(networkIO, "networkIO");
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SdkExecutors(java.util.concurrent.ExecutorService r1, java.util.concurrent.ExecutorService r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r4 = r3 & 1
            if (r4 == 0) goto Ld
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.lang.String r4 = "newSingleThreadExecutor(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
        Ld:
            r4 = 2
            r3 = r3 & r4
            if (r3 == 0) goto L1a
            java.util.concurrent.ExecutorService r2 = java.util.concurrent.Executors.newFixedThreadPool(r4)
            java.lang.String r3 = "newFixedThreadPool(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L1a:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors.<init>(java.util.concurrent.ExecutorService, java.util.concurrent.ExecutorService, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final ExecutorService getDiskIO() {
        return this.diskIO;
    }

    public final ExecutorService getNetworkIO() {
        return this.networkIO;
    }
}
