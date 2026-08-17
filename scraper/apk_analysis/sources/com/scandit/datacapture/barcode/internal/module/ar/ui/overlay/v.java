package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import android.view.View;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelper;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class v {
    public final b a;
    public final Function2 b;
    public final Function2 c;
    public final Function1 d;
    public final Function3 e;
    public final Function3 f;
    public final Function2 g;
    public final Function0 h;
    public final Function0 i;
    public final MainThreadHelper j;
    public final ReentrantReadWriteLock k;
    public final LinkedHashMap l;
    public final LinkedHashMap m;
    public final AtomicBoolean n;
    public int o;

    public v(e augmentationDataFactory, com.scandit.datacapture.barcode.ar.ui.o addHighlight, com.scandit.datacapture.barcode.ar.ui.p updateHighlight, com.scandit.datacapture.barcode.ar.ui.q removeHighlight, com.scandit.datacapture.barcode.ar.ui.r addAnnotation, com.scandit.datacapture.barcode.ar.ui.s updateAnnotation, com.scandit.datacapture.barcode.ar.ui.t removeAnnotation, com.scandit.datacapture.barcode.ar.ui.j showScrim, com.scandit.datacapture.barcode.ar.ui.k hideScrim, MainThreadHelperImpl mainThreadHelper) {
        Intrinsics.checkNotNullParameter(augmentationDataFactory, "augmentationDataFactory");
        Intrinsics.checkNotNullParameter(addHighlight, "addHighlight");
        Intrinsics.checkNotNullParameter(updateHighlight, "updateHighlight");
        Intrinsics.checkNotNullParameter(removeHighlight, "removeHighlight");
        Intrinsics.checkNotNullParameter(addAnnotation, "addAnnotation");
        Intrinsics.checkNotNullParameter(updateAnnotation, "updateAnnotation");
        Intrinsics.checkNotNullParameter(removeAnnotation, "removeAnnotation");
        Intrinsics.checkNotNullParameter(showScrim, "showScrim");
        Intrinsics.checkNotNullParameter(hideScrim, "hideScrim");
        Intrinsics.checkNotNullParameter(mainThreadHelper, "mainThreadHelper");
        this.a = augmentationDataFactory;
        this.b = addHighlight;
        this.c = updateHighlight;
        this.d = removeHighlight;
        this.e = addAnnotation;
        this.f = updateAnnotation;
        this.g = removeAnnotation;
        this.h = showScrim;
        this.i = hideScrim;
        this.j = mainThreadHelper;
        this.k = new ReentrantReadWriteLock(true);
        this.l = new LinkedHashMap();
        this.m = new LinkedHashMap();
        this.n = new AtomicBoolean(false);
    }

    public final void a(a aVar) {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar.e;
        if (barcodeArAugmentation$Annotation != null) {
            this.j.runOnMainThread(new l(this, barcodeArAugmentation$Annotation, aVar.d));
        }
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = aVar.d;
        if (barcodeArAugmentation$Highlight != null) {
            this.j.runOnMainThread(new m(this, barcodeArAugmentation$Highlight));
        }
    }

    public final ArrayList b() {
        ReentrantReadWriteLock.ReadLock readLock = this.k.readLock();
        readLock.lock();
        try {
            Collection values = this.m.values();
            ArrayList arrayList = new ArrayList();
            for (Object obj : values) {
                a aVar = (a) obj;
                BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar.e;
                if (barcodeArAugmentation$Annotation != null && (barcodeArAugmentation$Annotation.a() instanceof BarcodeArPopoverAnnotation) && aVar.e.b().getParent() != null && aVar.e.a().getAnnotationTrigger() == BarcodeArAnnotationTrigger.HIGHLIGHT_TAP) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        } finally {
            readLock.unlock();
        }
    }

    public final void a(BarcodeArSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.n.get()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = 1000 + currentTimeMillis;
            ReentrantReadWriteLock reentrantReadWriteLock = this.k;
            ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
            int i = 0;
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                readLock.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                Iterator it = this.l.values().iterator();
                while (it.hasNext()) {
                    if (((k) it.next()).a <= currentTimeMillis) {
                        it.remove();
                    }
                }
                Iterator<T> it2 = session.getAddedTrackedBarcodes().iterator();
                while (it2.hasNext()) {
                    a((TrackedBarcode) it2.next());
                }
                Iterator<Map.Entry<Integer, TrackedBarcode>> it3 = session.getTrackedBarcodes().entrySet().iterator();
                while (it3.hasNext()) {
                    b(it3.next().getValue());
                }
                Iterator<T> it4 = session.getRemovedTrackedBarcodes().iterator();
                while (it4.hasNext()) {
                    a(((Number) it4.next()).intValue(), j);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
            }
        }
    }

    public final void b(TrackedBarcode track) {
        a data = (a) this.m.get(Integer.valueOf(track.getIdentifier()));
        if (data == null) {
            e eVar = (e) this.a;
            eVar.getClass();
            Intrinsics.checkNotNullParameter(track, "track");
            a aVar = new a((Quadrilateral) eVar.c.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), (Quadrilateral) eVar.c.invoke(track.getLocation()), track, null, null);
            a aVar2 = (a) this.m.remove(Integer.valueOf(track.getIdentifier()));
            if (aVar2 != null) {
                a(aVar2);
            }
            this.m.put(Integer.valueOf(track.getIdentifier()), aVar);
            this.j.runOnMainThread(new p(this, track));
            return;
        }
        e eVar2 = (e) this.a;
        eVar2.getClass();
        Intrinsics.checkNotNullParameter(data, "data");
        a aVar3 = new a((Quadrilateral) eVar2.c.invoke(data.c.getLocationIgnoringLicense$scandit_barcode_capture()), (Quadrilateral) eVar2.c.invoke(data.c.getLocation()), data.c, data.d, data.e);
        this.m.put(Integer.valueOf(data.c.getIdentifier()), aVar3);
        this.j.runOnMainThread(new u(aVar3, this));
    }

    public final void a() {
        ReentrantReadWriteLock reentrantReadWriteLock = this.k;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            Iterator it = this.m.values().iterator();
            while (it.hasNext()) {
                a((a) it.next());
                it.remove();
            }
            this.l.clear();
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void a(TrackedBarcode track) {
        k kVar = (k) this.l.remove(track.getBarcode().get_uniqueHash());
        if (kVar != null) {
            a aVar = (a) this.m.remove(Integer.valueOf(track.getIdentifier()));
            if (aVar != null) {
                a(aVar);
            }
            b bVar = this.a;
            a aVar2 = kVar.c;
            BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = aVar2.d;
            BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar2.e;
            e eVar = (e) bVar;
            eVar.getClass();
            Intrinsics.checkNotNullParameter(track, "track");
            a aVar3 = new a((Quadrilateral) eVar.c.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), (Quadrilateral) eVar.c.invoke(track.getLocation()), track, barcodeArAugmentation$Highlight, barcodeArAugmentation$Annotation);
            this.m.put(Integer.valueOf(track.getIdentifier()), aVar3);
            this.j.runOnMainThread(new t(this, kVar, aVar3));
            return;
        }
        e eVar2 = (e) this.a;
        eVar2.getClass();
        Intrinsics.checkNotNullParameter(track, "track");
        a aVar4 = new a((Quadrilateral) eVar2.c.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), (Quadrilateral) eVar2.c.invoke(track.getLocation()), track, null, null);
        a aVar5 = (a) this.m.remove(Integer.valueOf(track.getIdentifier()));
        if (aVar5 != null) {
            a(aVar5);
        }
        this.m.put(Integer.valueOf(track.getIdentifier()), aVar4);
        this.j.runOnMainThread(new p(this, track));
    }

    public final void a(int i, long j) {
        View b;
        a aVar = (a) this.m.remove(Integer.valueOf(i));
        if (aVar != null) {
            String str = aVar.c.getBarcode().get_uniqueHash();
            LinkedHashMap linkedHashMap = this.l;
            BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar.e;
            linkedHashMap.put(str, new k(j, ((barcodeArAugmentation$Annotation == null || (b = barcodeArAugmentation$Annotation.b()) == null) ? null : b.getParent()) != null, aVar));
            a(aVar);
        }
    }

    public static final void a(v vVar, int i, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        a aVar = (a) vVar.m.get(Integer.valueOf(i));
        if (aVar == null) {
            return;
        }
        a aVar2 = new a(aVar.a, aVar.b, aVar.c, barcodeArAugmentation$Highlight, aVar.e);
        vVar.m.put(Integer.valueOf(i), aVar2);
        vVar.j.runOnMainThread(new r(aVar, vVar, barcodeArAugmentation$Highlight, aVar2));
    }

    public static final void a(v vVar, int i, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation) {
        a aVar = (a) vVar.m.get(Integer.valueOf(i));
        if (aVar == null) {
            return;
        }
        a aVar2 = new a(aVar.a, aVar.b, aVar.c, aVar.d, barcodeArAugmentation$Annotation);
        vVar.m.put(Integer.valueOf(i), aVar2);
        vVar.j.runOnMainThread(new q(aVar, barcodeArAugmentation$Annotation, vVar, aVar2));
    }

    public final void a(Quadrilateral quadrilateral, TrackedBarcode trackedBarcode, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        if (barcodeArAugmentation$Annotation.a() instanceof BarcodeArPopoverAnnotation) {
            int i = this.o + 1;
            this.o = i;
            if (i > 0) {
                this.h.invoke();
            }
        }
        this.e.invoke(trackedBarcode, barcodeArAugmentation$Annotation, barcodeArAugmentation$Highlight);
        this.f.invoke(quadrilateral, barcodeArAugmentation$Annotation, barcodeArAugmentation$Highlight);
    }

    public final void a(BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        if ((barcodeArAugmentation$Annotation.a() instanceof BarcodeArPopoverAnnotation) && barcodeArAugmentation$Annotation.b().getParent() != null) {
            int i = this.o - 1;
            this.o = i;
            if (i <= 0) {
                this.i.invoke();
            }
        }
        this.g.invoke(barcodeArAugmentation$Annotation, barcodeArAugmentation$Highlight);
    }
}
