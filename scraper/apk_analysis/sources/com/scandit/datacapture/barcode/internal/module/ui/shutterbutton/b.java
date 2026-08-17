package com.scandit.datacapture.barcode.internal.module.ui.shutterbutton;

import android.content.Context;
import com.scandit.datacapture.barcode.R;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements a {
    public final Context a;

    public b(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
    }

    public final f a() {
        f fVar = new f(this.a);
        fVar.setId(((Number) d.a.getValue()).intValue());
        fVar.setContentDescription(fVar.getContext().getString(R.string.sc_barcode_find_shutter_content_description));
        return fVar;
    }
}
