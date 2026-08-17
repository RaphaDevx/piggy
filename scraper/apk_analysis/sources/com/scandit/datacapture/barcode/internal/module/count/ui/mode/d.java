package com.scandit.datacapture.barcode.internal.module.count.ui.mode;

import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.internal.module.count.ui.g;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.e;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.f;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.j;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.m;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.n;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.o;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.r;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0353h;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

/* loaded from: classes2.dex */
public final class d implements BarcodeCountInternalModePresenter {
    public final BarcodeCountUiPresenter a;
    public final f b;
    public final n c;
    public BarcodeCountInternalMode d;
    public final WeakReference e;

    public d(J barcodeCountUiPresenter, g layerManager, BarcodeCount barcodeCount, C0353h layoutHelper) {
        j splashViewHolder = new j(layerManager.e, layoutHelper);
        r stepBackGuidanceViewHolder = new r(layerManager.e, layoutHelper);
        Intrinsics.checkNotNullParameter(barcodeCountUiPresenter, "barcodeCountUiPresenter");
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(barcodeCount, "barcodeCount");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(splashViewHolder, "splashViewHolder");
        Intrinsics.checkNotNullParameter(stepBackGuidanceViewHolder, "stepBackGuidanceViewHolder");
        this.a = barcodeCountUiPresenter;
        this.b = splashViewHolder;
        this.c = stepBackGuidanceViewHolder;
        this.e = new WeakReference(barcodeCount);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter
    public final void a(BarcodeCountInternalMode internalMode) {
        Intrinsics.checkNotNullParameter(internalMode, "internalMode");
        this.d = internalMode;
        this.a.a(internalMode);
        j jVar = (j) this.b;
        jVar.c.a(jVar.b, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.g.a);
        r rVar = (r) this.c;
        rVar.c.a(rVar.b, o.a);
        if (Intrinsics.areEqual(internalMode, a.a) || !(internalMode instanceof b)) {
            return;
        }
        b bVar = (b) internalMode;
        int ordinal = bVar.b.ordinal();
        if (ordinal == 0) {
            ((j) this.b).b();
            f fVar = this.b;
            String text = bVar.a.getScanBarcodesGuidanceText();
            j jVar2 = (j) fVar;
            jVar2.getClass();
            Intrinsics.checkNotNullParameter(text, "text");
            e eVar = jVar2.b;
            if (eVar != null) {
                Intrinsics.checkNotNullParameter(text, "text");
                eVar.c.setText(text);
            }
            ((j) this.b).a();
            f fVar2 = this.b;
            c onFinished = new c(this, bVar);
            j jVar3 = (j) fVar2;
            jVar3.getClass();
            Intrinsics.checkNotNullParameter(onFinished, "onFinished");
            e eVar2 = jVar3.b;
            if (eVar2 != null) {
                eVar2.a(onFinished);
                return;
            }
            return;
        }
        if (ordinal != 2) {
            return;
        }
        ((r) this.c).b();
        n nVar = this.c;
        String text2 = bVar.a.getStepBackGuidanceText();
        r rVar2 = (r) nVar;
        rVar2.getClass();
        Intrinsics.checkNotNullParameter(text2, "text");
        m mVar = rVar2.b;
        if (mVar != null) {
            Intrinsics.checkNotNullParameter(text2, "text");
            mVar.b.setText(text2);
        }
        ((r) this.c).a();
        r rVar3 = (r) this.c;
        m mVar2 = rVar3.b;
        if (mVar2 != null) {
            mVar2.removeCallbacks(rVar3.d);
        }
        rVar3.b();
        m mVar3 = rVar3.b;
        if (mVar3 != null) {
            mVar3.postDelayed(rVar3.d, Duration.m5301getInWholeMillisecondsimpl(r.e));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter
    public final void b() {
        BarcodeCountInternalMode barcodeCountInternalMode = this.d;
        b bVar = barcodeCountInternalMode instanceof b ? (b) barcodeCountInternalMode : null;
        if (bVar != null) {
            com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a aVar = bVar.b;
            if (aVar != com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.a) {
                if (aVar == com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.b) {
                    a(new b(bVar.a, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.c));
                }
            } else {
                e eVar = ((j) this.b).b;
                if (eVar != null) {
                    eVar.a();
                }
                j jVar = (j) this.b;
                jVar.c.a(jVar.b, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.g.a);
                a(new b(bVar.a, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.b));
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter
    public final void c() {
        BarcodeCountInternalMode barcodeCountInternalMode = this.d;
        b bVar = barcodeCountInternalMode instanceof b ? (b) barcodeCountInternalMode : null;
        if (bVar != null) {
            int ordinal = bVar.b.ordinal();
            if (ordinal == 1) {
                BarcodeCount barcodeCount = (BarcodeCount) this.e.get();
                if (barcodeCount != null) {
                    barcodeCount.reset();
                    return;
                }
                return;
            }
            if (ordinal != 2) {
                return;
            }
            a(new b(bVar.a, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.b));
            BarcodeCount barcodeCount2 = (BarcodeCount) this.e.get();
            if (barcodeCount2 != null) {
                barcodeCount2.reset();
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter
    public final void d() {
        BarcodeCountInternalMode barcodeCountInternalMode = this.d;
        b bVar = barcodeCountInternalMode instanceof b ? (b) barcodeCountInternalMode : null;
        if (bVar == null || bVar.b != com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.a) {
            return;
        }
        e eVar = ((j) this.b).b;
        if (eVar != null) {
            eVar.a();
        }
        j jVar = (j) this.b;
        jVar.c.a(jVar.b, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.g.a);
        a(new b(bVar.a, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.b));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter
    public final void a() {
        ((j) this.b).a();
        ((r) this.c).a();
    }
}
