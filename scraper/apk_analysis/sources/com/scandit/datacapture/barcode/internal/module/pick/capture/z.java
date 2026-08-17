package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.core.common.graphic.ImageBuffer;
import com.scandit.datacapture.core.data.FrameData;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class z extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;
    public final /* synthetic */ A b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(com.scandit.datacapture.barcode.pick.ui.l lVar, A a) {
        super(1);
        this.a = lVar;
        this.b = a;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FrameData frame = (FrameData) obj;
        Intrinsics.checkNotNullParameter(frame, "frame");
        ImageBuffer imageBuffer = frame.getImageBuffer();
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();
        this.a.invoke(Float.valueOf(Math.max(height, width) / Math.min(height, width)));
        this.b.a = null;
        return Unit.INSTANCE;
    }
}
