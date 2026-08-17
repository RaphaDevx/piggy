package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionCallback;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements BarcodePickActionListener {
    public final WeakReference a;

    public c(BarcodePickInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener
    public final void onPick(String itemData, BarcodePickActionCallback callback) {
        CopyOnWriteArraySet e;
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.a.get();
        if (barcodePickInternal == null || (e = barcodePickInternal.e()) == null) {
            return;
        }
        Iterator it = e.iterator();
        while (it.hasNext()) {
            ((BarcodePickActionListener) it.next()).onPick(itemData, callback);
        }
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener
    public final void onUnpick(String itemData, BarcodePickActionCallback callback) {
        CopyOnWriteArraySet e;
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BarcodePickInternal barcodePickInternal = (BarcodePickInternal) this.a.get();
        if (barcodePickInternal == null || (e = barcodePickInternal.e()) == null) {
            return;
        }
        Iterator it = e.iterator();
        while (it.hasNext()) {
            ((BarcodePickActionListener) it.next()).onUnpick(itemData, callback);
        }
    }
}
