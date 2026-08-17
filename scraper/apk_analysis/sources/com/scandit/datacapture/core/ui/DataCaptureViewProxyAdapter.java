package com.scandit.datacapture.core.ui;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.module.ui.GestureRecognizer;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.module.ui.r;
import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener;
import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListenerReversedAdapter;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0011J\u000f\u0010\u001b\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001b\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010&\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010,\u001a\u00020'2\u0006\u0010!\u001a\u00020'8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R$\u00102\u001a\u00020-2\u0006\u0010!\u001a\u00020-8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00105\u001a\u00020'2\u0006\u0010!\u001a\u00020'8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010)\"\u0004\b4\u0010+R$\u0010;\u001a\u0002062\u0006\u0010!\u001a\u0002068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006<"}, d2 = {"Lcom/scandit/datacapture/core/ui/DataCaptureViewProxyAdapter;", "Lcom/scandit/datacapture/core/ui/DataCaptureViewProxy;", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "_NativeDataCaptureView", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "overlay", "", "_addOverlay", "(Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;)V", "_removeOverlay", "_setNeedsRedraw", "()V", "Lcom/scandit/datacapture/core/internal/sdk/ui/NeedsRedrawListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_setNeedsRedrawListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/NeedsRedrawListener;)V", "Lcom/scandit/datacapture/core/internal/module/ui/GestureRecognizer;", "recognizer", "_setGestureRecognizer", "(Lcom/scandit/datacapture/core/internal/module/ui/GestureRecognizer;)V", "_performUiTriggeredZoomIn", "_performUiTriggeredZoomOut", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "p0", "getScanAreaMargins", "()Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "setScanAreaMargins", "(Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;)V", "scanAreaMargins", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "getPointOfInterest", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "setPointOfInterest", "(Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "pointOfInterest", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getLogoAnchor", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setLogoAnchor", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "logoAnchor", "getLogoOffset", "setLogoOffset", "logoOffset", "Lcom/scandit/datacapture/core/ui/LogoStyle;", "getLogoStyle", "()Lcom/scandit/datacapture/core/ui/LogoStyle;", "setLogoStyle", "(Lcom/scandit/datacapture/core/ui/LogoStyle;)V", "logoStyle", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureViewProxyAdapter implements DataCaptureViewProxy {
    private final NativeDataCaptureView a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public DataCaptureViewProxyAdapter(NativeDataCaptureView _NativeDataCaptureView, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeDataCaptureView, "_NativeDataCaptureView");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeDataCaptureView;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _addOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        NativeDataCaptureOverlay c = overlay.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureOverlay.class), null, c, overlay);
        this.a.addOverlay(c);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    /* renamed from: _impl, reason: from getter */
    public NativeDataCaptureView getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _performUiTriggeredZoomIn() {
        this.a.performUiTriggeredZoomIn();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _performUiTriggeredZoomOut() {
        this.a.performUiTriggeredZoomOut();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _removeOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        NativeDataCaptureOverlay c = overlay.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureOverlay.class), null, c, overlay);
        this.a.removeOverlay(c);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _setGestureRecognizer(GestureRecognizer recognizer) {
        Intrinsics.checkNotNullParameter(recognizer, "recognizer");
        this.a.setGestureRecognizer((r) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(GestureRecognizer.class), null, recognizer, new d(recognizer)));
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _setNeedsRedraw() {
        this.a.setNeedsRedraw();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void _setNeedsRedrawListener(NeedsRedrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a.setNeedsRedrawDelegate((NeedsRedrawListenerReversedAdapter) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NeedsRedrawListener.class), null, listener, new e(listener)));
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public Anchor getLogoAnchor() {
        Anchor logoAnchor = this.a.getLogoAnchor();
        Intrinsics.checkNotNull(logoAnchor);
        return logoAnchor;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public PointWithUnit getLogoOffset() {
        PointWithUnit logoOffset = this.a.getLogoOffset();
        Intrinsics.checkNotNull(logoOffset);
        return logoOffset;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public LogoStyle getLogoStyle() {
        LogoStyle logoStyle = this.a.getLogoStyle();
        Intrinsics.checkNotNull(logoStyle);
        return logoStyle;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public PointWithUnit getPointOfInterest() {
        PointWithUnit pointOfInterest = this.a.getPointOfInterest();
        Intrinsics.checkNotNull(pointOfInterest);
        return pointOfInterest;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public MarginsWithUnit getScanAreaMargins() {
        MarginsWithUnit scanAreaMargins = this.a.getScanAreaMargins();
        Intrinsics.checkNotNull(scanAreaMargins);
        return scanAreaMargins;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void setLogoAnchor(Anchor p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setLogoAnchor(p0);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void setLogoOffset(PointWithUnit p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setLogoOffset(p0);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void setLogoStyle(LogoStyle p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setLogoStyle(p0);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void setPointOfInterest(PointWithUnit p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setPointOfInterest(p0);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    public void setScanAreaMargins(MarginsWithUnit p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setScanAreaMargins(p0);
    }

    public /* synthetic */ DataCaptureViewProxyAdapter(NativeDataCaptureView nativeDataCaptureView, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeDataCaptureView, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
