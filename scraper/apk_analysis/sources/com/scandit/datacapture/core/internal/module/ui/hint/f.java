package com.scandit.datacapture.core.internal.module.ui.hint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.scandit.datacapture.core.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public final /* synthetic */ ViewGroup a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ViewGroup viewGroup) {
        super(0);
        this.a = viewGroup;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ViewGroup viewGroup = this.a;
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sc_hint_view, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) inflate;
        this.a.addView(textView);
        return textView;
    }
}
