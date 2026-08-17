package com.scandit.datacapture.barcode.count.capture.list;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeTargetBarcode;
import com.scandit.datacapture.barcode.data.BarcodeDataTransformer;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import io.sentry.cache.EnvelopeCache;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0014\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;", "impl", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListSession;)V", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountCaptureList;", "_session", "()Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListSession;", "Lcom/scandit/datacapture/barcode/data/BarcodeDataTransformer;", "transformer", "", "setBarcodeDataTransformer", "(Lcom/scandit/datacapture/barcode/data/BarcodeDataTransformer;)V", "", "getTargetBarcodesQuantity$scandit_barcode_capture", "()I", "getTargetBarcodesQuantity", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCountCaptureList implements BarcodeCountCaptureListProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeCountCaptureListSession a;
    private final /* synthetic */ BarcodeCountCaptureListProxyAdapter b;
    private BarcodeDataTransformer c;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList$Companion;", "", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "Lcom/scandit/datacapture/barcode/count/capture/list/TargetBarcode;", "targetBarcodes", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "create", "(Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;Ljava/util/List;)Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final BarcodeCountCaptureList create(BarcodeCountCaptureListListener listener, List<TargetBarcode> targetBarcodes) {
            HashSet hashSet;
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (targetBarcodes != null) {
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(targetBarcodes, 10));
                for (TargetBarcode targetBarcode : targetBarcodes) {
                    arrayList.add(NativeTargetBarcode.create(targetBarcode.getData(), targetBarcode.getQuantity()));
                }
                hashSet = CollectionsKt.toHashSet(arrayList);
            } else {
                hashSet = null;
            }
            NativeBarcodeCountCaptureList createForAndroid = NativeBarcodeCountCaptureList.createForAndroid(hashSet);
            Intrinsics.checkNotNull(createForAndroid);
            BarcodeCountCaptureList barcodeCountCaptureList = new BarcodeCountCaptureList(createForAndroid, new BarcodeCountCaptureListSession(new a(createForAndroid)));
            barcodeCountCaptureList.getA().addListenerAsync(new BarcodeCountCaptureListListenerReversedAdapter(listener, barcodeCountCaptureList, null, 4, null));
            return barcodeCountCaptureList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCountCaptureList(NativeBarcodeCountCaptureList impl, BarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(session, "session");
        this.a = session;
        this.b = new BarcodeCountCaptureListProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final BarcodeCountCaptureList create(BarcodeCountCaptureListListener barcodeCountCaptureListListener, List<TargetBarcode> list) {
        return INSTANCE.create(barcodeCountCaptureListListener, list);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCountCaptureList getA() {
        return this.b.getA();
    }

    /* renamed from: _session, reason: from getter */
    public final BarcodeCountCaptureListSession getA() {
        return this.a;
    }

    public final int getTargetBarcodesQuantity$scandit_barcode_capture() {
        return getA().getTargetBarcodesQuantity();
    }

    public final void setBarcodeDataTransformer(BarcodeDataTransformer transformer) {
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        this.c = transformer;
        getA().setBarcodeCountTransformer(new com.scandit.datacapture.barcode.internal.module.common.a(transformer));
    }
}
