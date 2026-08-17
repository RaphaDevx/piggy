package com.scandit.datacapture.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.data.NativeBarcodePickProduct;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u000f\b\u0000\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\f\u001a\u00020\nH\u0097\u0001J)\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u00122\u0006\u0010\u0006\u001a\u00020\u0013H\u0097\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u0097\u0001J\u0014\u0010\u0016\u001a\u00020\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProvider;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProvider;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderProxy;", "productsToPick", "", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProduct;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;", "(Ljava/util/Set;Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;)V", "impl", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProvider;", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProvider;)V", "_impl", "_productIdentifierForItems", "", "itemsData", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallback;", "_productProviderImpl", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeProductProvider;", "updateProductList", "products", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickAsyncMapperProductProvider implements BarcodePickProductProvider, BarcodePickAsyncMapperProductProviderProxy {
    private final /* synthetic */ BarcodePickAsyncMapperProductProviderProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodePickAsyncMapperProductProvider(NativeAsyncMapperProductProvider impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodePickAsyncMapperProductProviderProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeAsyncMapperProductProvider getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider, com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    @ProxyFunction(nativeName = "productIdentifierForItems")
    public void _productIdentifierForItems(ArrayList<String> itemsData, BarcodePickProductProviderCallback callback) {
        Intrinsics.checkNotNullParameter(itemsData, "itemsData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.a._productIdentifierForItems(itemsData, callback);
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider, com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    @NativeImpl
    /* renamed from: _productProviderImpl */
    public NativeProductProvider getC() {
        return this.a.getC();
    }

    public final void updateProductList(Set<BarcodePickProduct> products) {
        Intrinsics.checkNotNullParameter(products, "products");
        NativeAsyncMapperProductProvider a = getA();
        ArrayList<NativeBarcodePickProduct> arrayList = new ArrayList<>();
        Iterator<T> it = products.iterator();
        while (it.hasNext()) {
            arrayList.add(((BarcodePickProduct) it.next()).get_impl());
        }
        a.updateProductList(arrayList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickAsyncMapperProductProvider(java.util.Set<com.scandit.datacapture.barcode.pick.data.BarcodePickProduct> r3, com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallback r4) {
        /*
            r2 = this;
            java.lang.String r0 = "productsToPick"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r3 = r3.iterator()
        L13:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L27
            java.lang.Object r1 = r3.next()
            com.scandit.datacapture.barcode.pick.data.BarcodePickProduct r1 = (com.scandit.datacapture.barcode.pick.data.BarcodePickProduct) r1
            com.scandit.datacapture.barcode.internal.module.pick.data.NativeBarcodePickProduct r1 = r1.get_impl()
            r0.add(r1)
            goto L13
        L27:
            com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProviderCallback r3 = com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallbackKt.asNative(r4)
            com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProvider r3 = com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProvider.create(r0, r3)
            java.lang.String r4 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProvider.<init>(java.util.Set, com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallback):void");
    }
}
