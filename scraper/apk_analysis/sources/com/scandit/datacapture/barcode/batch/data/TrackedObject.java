package com.scandit.datacapture.barcode.batch.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0013\u001a\u00020\u0003H\u0097\u0001J\t\u0010\u0014\u001a\u00020\u0006H\u0097\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/data/TrackedObject;", "Lcom/scandit/datacapture/barcode/batch/data/TrackedObjectProxy;", "impl", "Lcom/scandit/datacapture/barcode/batch/internal/module/data/NativeTrackedObject;", "(Lcom/scandit/datacapture/barcode/batch/internal/module/data/NativeTrackedObject;)V", "data", "", "getData", "()Ljava/lang/String;", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "getIdentifier", "()I", "location", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "getLocation", "()Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "locationIgnoringLicense", "getLocationIgnoringLicense$scandit_barcode_capture", "_impl", "toJson", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TrackedObject implements TrackedObjectProxy {
    private final /* synthetic */ TrackedObjectProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public TrackedObject(NativeTrackedObject impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new TrackedObjectProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedObjectProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeTrackedObject getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedObjectProxy
    @ProxyFunction(nativeName = "getUtf8String", property = "data")
    public String getData() {
        return this.a.getData();
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedObjectProxy
    @ProxyFunction(property = ViewHierarchyNode.JsonKeys.IDENTIFIER)
    public int getIdentifier() {
        return this.a.getIdentifier();
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedObjectProxy
    @ProxyFunction(property = "location")
    public Quadrilateral getLocation() {
        return this.a.getLocation();
    }

    public final /* synthetic */ Quadrilateral getLocationIgnoringLicense$scandit_barcode_capture() {
        Quadrilateral locationIgnoringLicense = getA().getLocationIgnoringLicense();
        Intrinsics.checkNotNullExpressionValue(locationIgnoringLicense, "getLocationIgnoringLicense(...)");
        return locationIgnoringLicense;
    }

    @Override // com.scandit.datacapture.barcode.batch.data.TrackedObjectProxy
    @ProxyFunction
    public String toJson() {
        return this.a.toJson();
    }
}
