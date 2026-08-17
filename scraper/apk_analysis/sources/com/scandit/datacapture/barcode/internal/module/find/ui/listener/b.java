package com.scandit.datacapture.barcode.internal.module.find.ui.listener;

import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession;
import java.nio.charset.Charset;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ c a;
    public final /* synthetic */ NativeBarcodeFindSession b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, NativeBarcodeFindSession nativeBarcodeFindSession) {
        super(1);
        this.a = cVar;
        this.b = nativeBarcodeFindSession;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String it = (String) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.a.a.getTransformer() == null) {
            return it;
        }
        NativeBarcodeFindSession nativeBarcodeFindSession = this.b;
        Charset charset = Charsets.UTF_8;
        byte[] bytes = it.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] transformedDataForData = nativeBarcodeFindSession.getTransformedDataForData(bytes);
        if (transformedDataForData != null) {
            return new String(transformedDataForData, charset);
        }
        return null;
    }
}
