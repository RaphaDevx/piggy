package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import android.content.Context;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ Context a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Context context) {
        super(0);
        this.a = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new TorchSwitchControl(this.a);
    }
}
