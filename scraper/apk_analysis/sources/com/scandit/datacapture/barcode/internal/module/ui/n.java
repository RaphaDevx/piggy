package com.scandit.datacapture.barcode.internal.module.ui;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.count.ui.q;
import com.scandit.datacapture.barcode.internal.module.count.ui.r;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n extends LinearLayout {
    public static final Lazy e = LazyKt.lazy(m.a);
    public final ImageView a;
    public final r b;
    public final TextView c;
    public l d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setOrientation(1);
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = PixelExtensionsKt.pxFromDp$default(10, (Context) null, 1, (Object) null);
        imageView.setLayoutParams(layoutParams);
        setGravity(17);
        imageView.setAdjustViewBounds(true);
        addView(imageView);
        this.a = imageView;
        r rVar = new r(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.bottomMargin = PixelExtensionsKt.pxFromDp$default(10, (Context) null, 1, (Object) null);
        rVar.setLayoutParams(layoutParams2);
        rVar.setGravity(17);
        addView(rVar);
        this.b = rVar;
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(((Number) e.getValue()).intValue(), -2);
        layoutParams3.setMarginStart(48);
        layoutParams3.setMarginEnd(48);
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLayoutParams(layoutParams3);
        textView.setGravity(17);
        com.scandit.datacapture.barcode.internal.module.count.ui.b.a(textView);
        addView(textView);
        this.c = textView;
    }

    public final void a() {
        l userGuidanceState = l.a;
        Intrinsics.checkNotNullParameter(userGuidanceState, "userGuidanceState");
        a(userGuidanceState, "");
    }

    public final void a(l userGuidanceState, String text) {
        Intrinsics.checkNotNullParameter(userGuidanceState, "userGuidanceState");
        Intrinsics.checkNotNullParameter(text, "text");
        this.d = userGuidanceState;
        int ordinal = userGuidanceState.ordinal();
        if (ordinal == 0) {
            setVisibility(8);
        } else if (ordinal == 1) {
            setVisibility(0);
            this.a.setVisibility(8);
            this.b.setVisibility(0);
            int i = R.drawable.sc_ic_move_away_top;
            int i2 = R.drawable.sc_ic_move_away_mid;
            int i3 = R.drawable.sc_ic_move_away_bot;
            r rVar = this.b;
            rVar.b.setImageResource(i);
            rVar.c.setImageResource(i2);
            rVar.d.setImageResource(i3);
            r rVar2 = this.b;
            q qVar = q.b;
            rVar2.getClass();
            Intrinsics.checkNotNullParameter(qVar, "<set-?>");
            rVar2.a = qVar;
        } else if (ordinal == 2) {
            setVisibility(0);
            this.a.setVisibility(8);
            this.b.setVisibility(0);
            int i4 = R.drawable.sc_ic_move_closer_top;
            int i5 = R.drawable.sc_ic_move_closer_mid;
            int i6 = R.drawable.sc_ic_move_closer_bot;
            r rVar3 = this.b;
            rVar3.b.setImageResource(i4);
            rVar3.c.setImageResource(i5);
            rVar3.d.setImageResource(i6);
            r rVar4 = this.b;
            q qVar2 = q.a;
            rVar4.getClass();
            Intrinsics.checkNotNullParameter(qVar2, "<set-?>");
            rVar4.a = qVar2;
        } else if (ordinal == 3) {
            setVisibility(0);
            this.a.setVisibility(8);
            this.b.setVisibility(8);
        }
        this.c.setText(text);
        this.b.a();
    }
}
