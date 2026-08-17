package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon.l;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends ImageView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setId(R.id.sc_ar_status_icon_tip);
        setVisibility(8);
        Lazy lazy = l.a;
        setElevation(((Number) l.a.getValue()).floatValue());
        setImageDrawable(new RippleDrawable(ColorStateList.valueOf(getContext().getColor(com.scandit.datacapture.core.R.color.sc_default_ripple)), getContext().getDrawable(R.drawable.sc_barcode_ar_status_icon_tip), null));
    }
}
