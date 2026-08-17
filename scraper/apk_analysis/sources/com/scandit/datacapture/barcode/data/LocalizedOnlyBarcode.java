package com.scandit.datacapture.barcode.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeLocalizedOnlyBarcode;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\r\u001a\u00020\u0003H\u0097\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u0097\u0001R\u0014\u0010\u0005\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/barcode/data/LocalizedOnlyBarcode;", "Lcom/scandit/datacapture/barcode/data/LocalizedOnlyBarcodeProxy;", "impl", "Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeLocalizedOnlyBarcode;", "(Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeLocalizedOnlyBarcode;)V", "frameId", "", "getFrameId", "()I", "location", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "getLocation", "()Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "_impl", "toJson", "", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class LocalizedOnlyBarcode implements LocalizedOnlyBarcodeProxy {
    private final /* synthetic */ LocalizedOnlyBarcodeProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalizedOnlyBarcode(NativeLocalizedOnlyBarcode impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new LocalizedOnlyBarcodeProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.data.LocalizedOnlyBarcodeProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeLocalizedOnlyBarcode getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.data.LocalizedOnlyBarcodeProxy
    @ProxyFunction(property = "frameId")
    public int getFrameId() {
        return this.a.getFrameId();
    }

    @Override // com.scandit.datacapture.barcode.data.LocalizedOnlyBarcodeProxy
    @ProxyFunction(property = "location")
    public Quadrilateral getLocation() {
        return this.a.getLocation();
    }

    @Override // com.scandit.datacapture.barcode.data.LocalizedOnlyBarcodeProxy
    @ProxyFunction(nativeName = "toJson")
    public String toJson() {
        return this.a.toJson();
    }
}
