package com.scandit.datacapture.core.internal.sdk.common.linearcontrolgroup;

import com.scandit.datacapture.core.ui.control.Control;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ List a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(ArrayList arrayList) {
        super(1);
        this.a = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Control control = (Control) obj;
        Intrinsics.checkNotNullParameter(control, "control");
        this.a.add(control);
        return Unit.INSTANCE;
    }
}
