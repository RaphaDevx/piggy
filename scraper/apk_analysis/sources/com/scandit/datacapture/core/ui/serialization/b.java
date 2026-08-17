package com.scandit.datacapture.core.ui.serialization;

import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ DataCaptureViewDeserializer.Helper a;
    public final /* synthetic */ DataCaptureView b;
    public final /* synthetic */ JsonValue c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(DataCaptureViewDeserializer.Helper helper, DataCaptureView dataCaptureView, JsonValue jsonValue) {
        super(1);
        this.a = helper;
        this.b = dataCaptureView;
        this.c = jsonValue;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView it = (DataCaptureView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.a(this.b, this.c);
        return Unit.INSTANCE;
    }
}
