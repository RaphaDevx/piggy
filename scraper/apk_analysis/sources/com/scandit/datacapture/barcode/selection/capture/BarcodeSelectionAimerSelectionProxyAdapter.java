package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeSelectionType;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelectionProxyAdapter;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelectionProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "_NativeAimerSelection", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeSelectionType;", "_selectionTypeImpl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeSelectionType;", "", "toJson", "()Ljava/lang/String;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;", "p0", "getAimerBehavior", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;", "setAimerBehavior", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;)V", "aimerBehavior", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionAimerSelectionProxyAdapter implements BarcodeSelectionAimerSelectionProxy {
    private final NativeAimerSelection a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeSelectionType c;

    public BarcodeSelectionAimerSelectionProxyAdapter(NativeAimerSelection _NativeAimerSelection, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeAimerSelection, "_NativeAimerSelection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeAimerSelection;
        this.proxyCache = proxyCache;
        NativeSelectionType asSelectionType = _NativeAimerSelection.asSelectionType();
        Intrinsics.checkNotNullExpressionValue(asSelectionType, "asSelectionType(...)");
        this.c = asSelectionType;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    /* renamed from: _impl, reason: from getter */
    public NativeAimerSelection getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    /* renamed from: _selectionTypeImpl, reason: from getter */
    public NativeSelectionType getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    public BarcodeSelectionAimerBehavior getAimerBehavior() {
        BarcodeSelectionAimerBehavior aimerBehavior = this.a.getAimerBehavior();
        Intrinsics.checkNotNull(aimerBehavior);
        return aimerBehavior;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    public void setAimerBehavior(BarcodeSelectionAimerBehavior p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setAimerBehavior(p0);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    public String toJson() {
        String json = this.a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public /* synthetic */ BarcodeSelectionAimerSelectionProxyAdapter(NativeAimerSelection nativeAimerSelection, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeAimerSelection, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
