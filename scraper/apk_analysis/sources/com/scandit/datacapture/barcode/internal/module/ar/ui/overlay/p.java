package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class p extends Lambda implements Function0 {
    public final /* synthetic */ v a;
    public final /* synthetic */ TrackedBarcode b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(v vVar, TrackedBarcode trackedBarcode) {
        super(0);
        this.a = vVar;
        this.b = trackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        v vVar = this.a;
        b bVar = vVar.a;
        TrackedBarcode track = this.b;
        n highlightReadyCallback = new n(vVar, track);
        e eVar = (e) bVar;
        eVar.getClass();
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(highlightReadyCallback, "highlightReadyCallback");
        eVar.a.invoke(track.getBarcode(), new d(highlightReadyCallback));
        v vVar2 = this.a;
        b bVar2 = vVar2.a;
        TrackedBarcode track2 = this.b;
        o annotationReadyCallback = new o(vVar2, track2);
        e eVar2 = (e) bVar2;
        eVar2.getClass();
        Intrinsics.checkNotNullParameter(track2, "track");
        Intrinsics.checkNotNullParameter(annotationReadyCallback, "annotationReadyCallback");
        eVar2.b.invoke(track2.getBarcode(), new c(annotationReadyCallback));
        return Unit.INSTANCE;
    }
}
