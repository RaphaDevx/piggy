package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function1 {
    public final /* synthetic */ v a;
    public final /* synthetic */ TrackedBarcode b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(v vVar, TrackedBarcode trackedBarcode) {
        super(1);
        this.a = vVar;
        this.b = trackedBarcode;
    }

    /* JADX WARN: Finally extract failed */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = (BarcodeArAugmentation$Annotation) obj;
        v vVar = this.a;
        ReentrantReadWriteLock reentrantReadWriteLock = vVar.k;
        TrackedBarcode trackedBarcode = this.b;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            v.a(vVar, trackedBarcode.getIdentifier(), barcodeArAugmentation$Annotation);
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            throw th;
        }
    }
}
