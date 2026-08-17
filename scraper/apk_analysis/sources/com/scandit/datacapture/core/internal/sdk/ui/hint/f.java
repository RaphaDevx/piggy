package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.internal.module.ui.hint.HintView;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ HintHolderV2Impl a;
    public final /* synthetic */ Rect b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(HintHolderV2Impl hintHolderV2Impl, Rect rect) {
        super(1);
        this.a = hintHolderV2Impl;
        this.b = rect;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Map map;
        Map map2;
        HintHolderV2Impl it = (HintHolderV2Impl) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        map = this.a.d;
        Rect rect = this.b;
        for (Map.Entry entry : map.entrySet()) {
            ToastHint toast = (ToastHint) entry.getKey();
            HintView hintView = (HintView) entry.getValue();
            hintView.getClass();
            Intrinsics.checkNotNullParameter(toast, "toast");
            hintView.b(toast.getHintStyle(), rect, false);
        }
        map2 = this.a.e;
        Rect rect2 = this.b;
        for (Map.Entry entry2 : map2.entrySet()) {
            GuidanceHint guidance = (GuidanceHint) entry2.getKey();
            HintView hintView2 = (HintView) entry2.getValue();
            hintView2.getClass();
            Intrinsics.checkNotNullParameter(guidance, "guidance");
            hintView2.b(guidance.getHintStyle(), rect2, true);
        }
        return Unit.INSTANCE;
    }
}
