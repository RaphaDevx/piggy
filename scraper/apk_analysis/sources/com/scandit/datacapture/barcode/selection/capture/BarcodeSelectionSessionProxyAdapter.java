package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001cR\u0014\u0010%\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010(\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSessionProxyAdapter;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSessionProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSession;", "_NativeBarcodeSelectionSession", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSession;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSession;", "", "selectUnselectedBarcodes", "()V", "reset", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "", "getCount", "(Lcom/scandit/datacapture/barcode/data/Barcode;)I", "", "toJson", "()Ljava/lang/String;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getNewlySelectedBarcodes", "()Ljava/util/List;", "newlySelectedBarcodes", "getNewlyUnselectedBarcodes", "newlyUnselectedBarcodes", "getSelectedBarcodes", "selectedBarcodes", "", "getFrameSequenceId", "()J", "frameSequenceId", "getLastProcessedFrameId", "()I", "lastProcessedFrameId", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionSessionProxyAdapter implements BarcodeSelectionSessionProxy {
    private final NativeBarcodeSelectionSession a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeSelectionSessionProxyAdapter(NativeBarcodeSelectionSession _NativeBarcodeSelectionSession, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeSelectionSession, "_NativeBarcodeSelectionSession");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeSelectionSession;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeSelectionSession getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public int getCount(Barcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBarcode a = barcode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcode.class), null, a, barcode);
        return this.a.getCount(a);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public long getFrameSequenceId() {
        return this.a.getFrameSeqIdAndroid();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public int getLastProcessedFrameId() {
        return this.a.getLastProcessedFrameId();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public List<Barcode> getNewlySelectedBarcodes() {
        ArrayList<NativeBarcode> newlySelectedBarcodes = this.a.getNewlySelectedBarcodes();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(newlySelectedBarcodes);
        return barcodeNativeTypeFactory.convertNativeBarcodeList(newlySelectedBarcodes);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public List<Barcode> getNewlyUnselectedBarcodes() {
        ArrayList<NativeBarcode> newlyUnselectedBarcodes = this.a.getNewlyUnselectedBarcodes();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(newlyUnselectedBarcodes);
        return barcodeNativeTypeFactory.convertNativeBarcodeList(newlyUnselectedBarcodes);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public List<Barcode> getSelectedBarcodes() {
        ArrayList<NativeBarcode> selectedBarcodes = this.a.getSelectedBarcodes();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(selectedBarcodes);
        return barcodeNativeTypeFactory.convertNativeBarcodeList(selectedBarcodes);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public void reset() {
        this.a.reset();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public void selectUnselectedBarcodes() {
        this.a.selectAllUnselectedBarcodes();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSessionProxy
    public String toJson() {
        String json = this.a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public /* synthetic */ BarcodeSelectionSessionProxyAdapter(NativeBarcodeSelectionSession nativeBarcodeSelectionSession, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeSelectionSession, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
