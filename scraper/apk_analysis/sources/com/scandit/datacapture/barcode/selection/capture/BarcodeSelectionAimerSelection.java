package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeSelectionType;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012R*\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00138\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\t\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010\n¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelection;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionType;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelectionProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;)V", "()V", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;", "aimerBehavior", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeSelectionType;", "_selectionTypeImpl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeSelectionType;", "", "toJson", "()Ljava/lang/String;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionStrategy;", "value", "b", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionStrategy;", "getSelectionStrategy", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionStrategy;", "setSelectionStrategy", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionStrategy;)V", "selectionStrategy", "<set-?>", "getAimerBehavior", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerBehavior;", "setAimerBehavior", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionAimerSelection implements BarcodeSelectionType, BarcodeSelectionAimerSelectionProxy {
    private final /* synthetic */ BarcodeSelectionAimerSelectionProxyAdapter a;

    /* renamed from: b, reason: from kotlin metadata */
    private BarcodeSelectionStrategy selectionStrategy;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeSelectionAimerSelection(NativeAimerSelection impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeSelectionAimerSelectionProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.selectionStrategy = new BarcodeSelectionManualSelectionStrategy();
        setSelectionStrategy(new BarcodeSelectionManualSelectionStrategy());
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeAimerSelection getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionType, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @NativeImpl
    /* renamed from: _selectionTypeImpl */
    public NativeSelectionType getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @ProxyFunction(property = "aimerBehavior")
    public BarcodeSelectionAimerBehavior getAimerBehavior() {
        return this.a.getAimerBehavior();
    }

    public final BarcodeSelectionStrategy getSelectionStrategy() {
        return this.selectionStrategy;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @ProxyFunction(property = "aimerBehavior")
    public void setAimerBehavior(BarcodeSelectionAimerBehavior barcodeSelectionAimerBehavior) {
        Intrinsics.checkNotNullParameter(barcodeSelectionAimerBehavior, "<set-?>");
        this.a.setAimerBehavior(barcodeSelectionAimerBehavior);
    }

    public final void setSelectionStrategy(BarcodeSelectionStrategy value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.selectionStrategy = value;
        getA().setSelectionStrategy(value.getC());
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionType, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @ProxyFunction
    public String toJson() {
        return this.a.toJson();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeSelectionAimerSelection() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection r0 = com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelection.<init>():void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BarcodeSelectionAimerSelection(BarcodeSelectionAimerBehavior aimerBehavior) {
        this();
        Intrinsics.checkNotNullParameter(aimerBehavior, "aimerBehavior");
        setAimerBehavior(aimerBehavior);
    }
}
