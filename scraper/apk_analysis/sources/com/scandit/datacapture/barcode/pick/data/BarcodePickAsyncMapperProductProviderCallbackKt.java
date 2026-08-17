package com.scandit.datacapture.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProviderCallback;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallback;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asNative", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProviderCallback;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;", "scandit-barcode-capture"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickAsyncMapperProductProviderCallbackKt {
    public static final NativeAsyncMapperProductProviderCallback asNative(final BarcodePickAsyncMapperProductProviderCallback barcodePickAsyncMapperProductProviderCallback) {
        Intrinsics.checkNotNullParameter(barcodePickAsyncMapperProductProviderCallback, "<this>");
        return new NativeAsyncMapperProductProviderCallback() { // from class: com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallbackKt$asNative$1
            @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProviderCallback
            public void productIdentifierForItems(ArrayList<String> itemsData, NativeProductProviderCallback providerCallback) {
                Intrinsics.checkNotNullParameter(itemsData, "itemsData");
                Intrinsics.checkNotNullParameter(providerCallback, "providerCallback");
                BarcodePickAsyncMapperProductProviderCallback.this.productIdentifierForItems(itemsData, new BarcodePickProductProviderCallback(providerCallback));
            }
        };
    }
}
