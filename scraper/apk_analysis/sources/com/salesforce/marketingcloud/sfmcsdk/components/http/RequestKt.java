package com.salesforce.marketingcloud.sfmcsdk.components.http;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"UTF_8", "Ljava/nio/charset/Charset;", "getUTF_8", "()Ljava/nio/charset/Charset;", "sfmcsdk_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RequestKt {
    private static final Charset UTF_8;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        UTF_8 = forName;
    }

    public static final Charset getUTF_8() {
        return UTF_8;
    }
}
