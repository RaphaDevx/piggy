package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SdkExecutors.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¨\u0006\u0007"}, d2 = {"namedRunnable", "", "Ljava/util/concurrent/ExecutorService;", "name", "", "block", "Lkotlin/Function0;", "sfmcsdk_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SdkExecutorsKt {
    public static final void namedRunnable(ExecutorService executorService, final String name, final Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        final Object[] objArr = new Object[0];
        executorService.execute(new NamedRunnable(name, objArr) { // from class: com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt$namedRunnable$1
            @Override // com.salesforce.marketingcloud.sfmcsdk.components.utils.NamedRunnable
            protected void execute() {
                block.invoke();
            }
        });
    }
}
