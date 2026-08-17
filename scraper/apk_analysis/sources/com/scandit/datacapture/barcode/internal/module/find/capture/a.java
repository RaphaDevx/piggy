package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.core.common.graphic.ImageBuffer;
import com.scandit.datacapture.core.data.FrameData;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(c cVar) {
        super(1);
        this.a = cVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FrameData it = (FrameData) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        ImageBuffer imageBuffer = it.getImageBuffer();
        this.a.c.invoke(Integer.valueOf(imageBuffer.getWidth()), Integer.valueOf(imageBuffer.getHeight()));
        return Unit.INSTANCE;
    }
}
