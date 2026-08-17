package com.scandit.datacapture.barcode.selection.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeSelectionType;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u000f\b\u0000\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\nH\u0097\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u0097\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u0097\u0001R$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00058W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00118W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00078W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapSelection;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionType;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapSelectionProxy;", "()V", "freezeBehavior", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionFreezeBehavior;", "tapBehavior", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapBehavior;", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionFreezeBehavior;Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapBehavior;)V", "impl", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeTapSelection;", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeTapSelection;)V", "<set-?>", "getFreezeBehavior", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionFreezeBehavior;", "setFreezeBehavior", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionFreezeBehavior;)V", "", "shouldFreezeOnDoubleTap", "getShouldFreezeOnDoubleTap", "()Z", "setShouldFreezeOnDoubleTap", "(Z)V", "getTapBehavior", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapBehavior;", "setTapBehavior", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapBehavior;)V", "_impl", "_selectionTypeImpl", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeSelectionType;", "toJson", "", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionTapSelection implements BarcodeSelectionType, BarcodeSelectionTapSelectionProxy {
    private final /* synthetic */ BarcodeSelectionTapSelectionProxyAdapter a;

    public BarcodeSelectionTapSelection(NativeTapSelection impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeSelectionTapSelectionProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeTapSelection getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionType, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelectionProxy
    @NativeImpl
    /* renamed from: _selectionTypeImpl */
    public NativeSelectionType getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "freezeBehavior")
    public BarcodeSelectionFreezeBehavior getFreezeBehavior() {
        return this.a.getFreezeBehavior();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "shouldFreezeOnDoubleTap")
    public boolean getShouldFreezeOnDoubleTap() {
        return this.a.getShouldFreezeOnDoubleTap();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "tapBehavior")
    public BarcodeSelectionTapBehavior getTapBehavior() {
        return this.a.getTapBehavior();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "freezeBehavior")
    public void setFreezeBehavior(BarcodeSelectionFreezeBehavior barcodeSelectionFreezeBehavior) {
        Intrinsics.checkNotNullParameter(barcodeSelectionFreezeBehavior, "<set-?>");
        this.a.setFreezeBehavior(barcodeSelectionFreezeBehavior);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "shouldFreezeOnDoubleTap")
    public void setShouldFreezeOnDoubleTap(boolean z) {
        this.a.setShouldFreezeOnDoubleTap(z);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionProxy
    @ProxyFunction(property = "tapBehavior")
    public void setTapBehavior(BarcodeSelectionTapBehavior barcodeSelectionTapBehavior) {
        Intrinsics.checkNotNullParameter(barcodeSelectionTapBehavior, "<set-?>");
        this.a.setTapBehavior(barcodeSelectionTapBehavior);
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
    public BarcodeSelectionTapSelection() {
        /*
            r2 = this;
            com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection r0 = com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelection.<init>():void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BarcodeSelectionTapSelection(BarcodeSelectionFreezeBehavior freezeBehavior, BarcodeSelectionTapBehavior tapBehavior) {
        this();
        Intrinsics.checkNotNullParameter(freezeBehavior, "freezeBehavior");
        Intrinsics.checkNotNullParameter(tapBehavior, "tapBehavior");
        setFreezeBehavior(freezeBehavior);
        setTapBehavior(tapBehavior);
    }
}
