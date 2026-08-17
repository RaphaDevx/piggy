package com.scandit.datacapture.barcode.selection.ui.overlay;

import com.scandit.datacapture.barcode.selection.internal.module.ui.overlay.NativeBarcodeSelectionBasicOverlay;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeProfilingOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ProfilingOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0016\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0017\u0010\u0013J\u0017\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010&\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010)\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R$\u0010,\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b*\u0010#\"\u0004\b+\u0010%R$\u0010/\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b-\u0010#\"\u0004\b.\u0010%R$\u00105\u001a\u0002002\u0006\u0010!\u001a\u0002008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00108\u001a\u0002002\u0006\u0010!\u001a\u0002008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b6\u00102\"\u0004\b7\u00104R\u0014\u0010<\u001a\u0002098VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R$\u0010B\u001a\u00020=2\u0006\u0010!\u001a\u00020=8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006C"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayProxyAdapter;", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;", "_NativeBarcodeSelectionBasicOverlay", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_dataCaptureOverlayImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "", "clearSelectedBarcodeBrushes", "()V", "", "text", "setTextForSelectOrDoubleTapToFreezeHint", "(Ljava/lang/String;)V", "setTextForTapToSelectHint", "setTextForDoubleTapToUnfreezeHint", "setTextForTapAnywhereToSelectHint", "setTextForAimToSelectAutoHint", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;", "overlay", "_setProfilingOverlay", "(Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/core/ui/style/Brush;", "p0", "getTrackedBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "setTrackedBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "trackedBrush", "getAimedBrush", "setAimedBrush", "aimedBrush", "getSelectingBrush", "setSelectingBrush", "selectingBrush", "getSelectedBrush", "setSelectedBrush", "selectedBrush", "", "getShouldShowScanAreaGuides", "()Z", "setShouldShowScanAreaGuides", "(Z)V", "shouldShowScanAreaGuides", "getShouldShowHints", "setShouldShowHints", "shouldShowHints", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayStyle;", "getStyle", "()Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayStyle;", "style", "", "getFrozenBackgroundColor", "()I", "setFrozenBackgroundColor", "(I)V", "frozenBackgroundColor", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionBasicOverlayProxyAdapter implements BarcodeSelectionBasicOverlayProxy {
    private final NativeBarcodeSelectionBasicOverlay a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureOverlay c;

    public BarcodeSelectionBasicOverlayProxyAdapter(NativeBarcodeSelectionBasicOverlay _NativeBarcodeSelectionBasicOverlay, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeSelectionBasicOverlay, "_NativeBarcodeSelectionBasicOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeSelectionBasicOverlay;
        this.proxyCache = proxyCache;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeBarcodeSelectionBasicOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.c = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy, com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl, reason: from getter */
    public NativeDataCaptureOverlay getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeSelectionBasicOverlay getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void _setProfilingOverlay(ProfilingOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        NativeProfilingOverlay a = overlay.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeProfilingOverlay.class), null, a, overlay);
        this.a.setProfilingOverlay(a);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void clearSelectedBarcodeBrushes() {
        this.a.clearSelectedBarcodeBrushes();
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public Brush getAimedBrush() {
        NativeBrush aimedBrush = this.a.getAimedBrush();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(aimedBrush);
        return coreNativeTypeFactory.convert(aimedBrush);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public int getFrozenBackgroundColor() {
        NativeColor freezeOverlayColor = this.a.getFreezeOverlayColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(freezeOverlayColor);
        return coreNativeTypeFactory.convert(freezeOverlayColor);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public Brush getSelectedBrush() {
        NativeBrush selectedBrush = this.a.getSelectedBrush();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(selectedBrush);
        return coreNativeTypeFactory.convert(selectedBrush);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public Brush getSelectingBrush() {
        NativeBrush selectingBrush = this.a.getSelectingBrush();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(selectingBrush);
        return coreNativeTypeFactory.convert(selectingBrush);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public boolean getShouldShowHints() {
        return this.a.getShowHints();
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public boolean getShouldShowScanAreaGuides() {
        return this.a.getShouldShowScanAreaGuides();
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public BarcodeSelectionBasicOverlayStyle getStyle() {
        BarcodeSelectionBasicOverlayStyle style = this.a.getStyle();
        Intrinsics.checkNotNull(style);
        return style;
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public Brush getTrackedBrush() {
        NativeBrush trackedBrush = this.a.getTrackedBrush();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(trackedBrush);
        return coreNativeTypeFactory.convert(trackedBrush);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setAimedBrush(Brush p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setAimedBrush(CoreNativeTypeFactory.INSTANCE.convert(p0));
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setFrozenBackgroundColor(int i) {
        this.a.setFreezeOverlayColor(CoreNativeTypeFactory.INSTANCE.convert(i));
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setSelectedBrush(Brush p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setSelectedBrush(CoreNativeTypeFactory.INSTANCE.convert(p0));
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setSelectingBrush(Brush p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setSelectingBrush(CoreNativeTypeFactory.INSTANCE.convert(p0));
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setShouldShowHints(boolean z) {
        this.a.setShowHints(z);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setShouldShowScanAreaGuides(boolean z) {
        this.a.setShouldShowScanAreaGuides(z);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTextForAimToSelectAutoHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForAimToSelectAutoHint(text);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTextForDoubleTapToUnfreezeHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForDoubleTapToUnfreezeHint(text);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTextForSelectOrDoubleTapToFreezeHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForSelectOrDoubleTapToFreezeHint(text);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTextForTapAnywhereToSelectHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForTapAnywhereToSelectHint(text);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTextForTapToSelectHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForTapToSelectHint(text);
    }

    @Override // com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayProxy
    public void setTrackedBrush(Brush p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setTrackedBrush(CoreNativeTypeFactory.INSTANCE.convert(p0));
    }

    public /* synthetic */ BarcodeSelectionBasicOverlayProxyAdapter(NativeBarcodeSelectionBasicOverlay nativeBarcodeSelectionBasicOverlay, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeSelectionBasicOverlay, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
