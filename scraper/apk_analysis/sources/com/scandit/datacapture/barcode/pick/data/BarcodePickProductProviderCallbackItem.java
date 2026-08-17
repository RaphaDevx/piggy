package com.scandit.datacapture.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallbackItem;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005B\u000f\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\f\u001a\u00020\u0007H\u0097\u0001R\u0014\u0010\u0002\u001a\u00020\u00038WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00038WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\n¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallbackItem;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallbackItemProxy;", "itemData", "", "productIdentifier", "(Ljava/lang/String;Ljava/lang/String;)V", "impl", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeProductProviderCallbackItem;", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeProductProviderCallbackItem;)V", "getItemData", "()Ljava/lang/String;", "getProductIdentifier", "_impl", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickProductProviderCallbackItem implements BarcodePickProductProviderCallbackItemProxy {
    private final /* synthetic */ BarcodePickProductProviderCallbackItemProxyAdapter a;

    public BarcodePickProductProviderCallbackItem(NativeProductProviderCallbackItem impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodePickProductProviderCallbackItemProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItemProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeProductProviderCallbackItem getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItemProxy
    @ProxyFunction(nativeName = "getToBeTransformedData", property = "itemData")
    public String getItemData() {
        return this.a.getItemData();
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItemProxy
    @ProxyFunction(nativeName = "getTransformationResult", property = "productIdentifier")
    public String getProductIdentifier() {
        return this.a.getProductIdentifier();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickProductProviderCallbackItem(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "itemData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallbackItem r2 = com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallbackItem.create(r2, r3)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItem.<init>(java.lang.String, java.lang.String):void");
    }
}
