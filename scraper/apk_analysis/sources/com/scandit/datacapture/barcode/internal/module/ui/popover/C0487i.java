package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0487i extends Lambda implements Function0 {
    public static final C0487i a = new C0487i();

    public C0487i() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, (Context) null, 1, (Object) null));
    }
}
