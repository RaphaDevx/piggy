package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class x extends Lambda implements Function1 {
    public final /* synthetic */ B a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(B b) {
        super(1);
        this.a = b;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeCluster cluster = (NativeCluster) obj;
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        B b = this.a;
        b.getClass();
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        NativeBarcodeClusterLiveEditor beginClusterEditing = b.b.beginClusterEditing();
        if (beginClusterEditing != null) {
            try {
                beginClusterEditing.dissolveCluster(cluster);
                beginClusterEditing.endEditing();
                r rVar = b.i;
                if (rVar != null) {
                    ((com.scandit.datacapture.barcode.internal.module.count.ui.handlers.v) rVar).a(cluster.getIdentifier());
                }
            } catch (Throwable th) {
                beginClusterEditing.endEditing();
                throw th;
            }
        }
        return Unit.INSTANCE;
    }
}
