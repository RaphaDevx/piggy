package com.scandit.datacapture.core.ui;

import android.widget.Toast;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ ArrayList a;
    public final /* synthetic */ DataCaptureView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ArrayList arrayList, DataCaptureView dataCaptureView) {
        super(1);
        this.a = arrayList;
        this.b = dataCaptureView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView it = (DataCaptureView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        Toast.makeText(this.b.getContext(), CollectionsKt.joinToString$default(this.a, StringUtils.LF, null, null, 0, null, b.a, 30, null), 1).show();
        return Unit.INSTANCE;
    }
}
