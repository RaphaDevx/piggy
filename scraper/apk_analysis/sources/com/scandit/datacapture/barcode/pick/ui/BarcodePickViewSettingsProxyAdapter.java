package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR$\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R$\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R$\u0010#\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R$\u0010&\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R$\u0010)\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013R$\u0010,\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R$\u0010/\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0017\"\u0004\b.\u0010\u0019R$\u00102\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u0010\u0019R$\u00105\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u0019R$\u00108\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b6\u0010\u0011\"\u0004\b7\u0010\u0013R$\u0010;\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010\u0011\"\u0004\b:\u0010\u0013R$\u0010>\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b<\u0010\u0011\"\u0004\b=\u0010\u0013R$\u0010D\u001a\u00020?2\u0006\u0010\u000f\u001a\u00020?8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR$\u0010G\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bE\u0010\u0011\"\u0004\bF\u0010\u0013R$\u0010J\u001a\u00020?2\u0006\u0010\u000f\u001a\u00020?8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bH\u0010A\"\u0004\bI\u0010CR$\u0010M\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bK\u0010\u0011\"\u0004\bL\u0010\u0013R(\u0010S\u001a\u0004\u0018\u00010N2\b\u0010\u000f\u001a\u0004\u0018\u00010N8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR$\u0010V\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bT\u0010\u0017\"\u0004\bU\u0010\u0019¨\u0006W"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettingsProxyAdapter;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettingsProxy;", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/NativeBarcodePickViewSettings;", "_NativeBarcodePickViewSettings", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/pick/ui/NativeBarcodePickViewSettings;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/pick/ui/NativeBarcodePickViewSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "p0", "getShowLoadingDialog", "()Z", "setShowLoadingDialog", "(Z)V", "showLoadingDialog", "", "getLoadingDialogTextForPicking", "()Ljava/lang/String;", "setLoadingDialogTextForPicking", "(Ljava/lang/String;)V", "loadingDialogTextForPicking", "getLoadingDialogTextForUnpicking", "setLoadingDialogTextForUnpicking", "loadingDialogTextForUnpicking", "getShowGuidelines", "setShowGuidelines", "showGuidelines", "getInitialGuidelineText", "setInitialGuidelineText", "initialGuidelineText", "getMoveCloserGuidelineText", "setMoveCloserGuidelineText", "moveCloserGuidelineText", "getShowHints", "setShowHints", "showHints", "getOnFirstItemToPickFoundHintText", "setOnFirstItemToPickFoundHintText", "onFirstItemToPickFoundHintText", "getOnFirstItemPickCompletedHintText", "setOnFirstItemPickCompletedHintText", "onFirstItemPickCompletedHintText", "getOnFirstUnmarkedItemPickCompletedHintText", "setOnFirstUnmarkedItemPickCompletedHintText", "onFirstUnmarkedItemPickCompletedHintText", "getOnFirstItemUnpickCompletedHintText", "setOnFirstItemUnpickCompletedHintText", "onFirstItemUnpickCompletedHintText", "getShowPauseButton", "setShowPauseButton", "showPauseButton", "getShowFinishButton", "setShowFinishButton", "showFinishButton", "getShowZoomButton", "setShowZoomButton", "showZoomButton", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getZoomButtonPosition", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setZoomButtonPosition", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "zoomButtonPosition", "getShowTorchButton", "setShowTorchButton", "showTorchButton", "getTorchButtonPosition", "setTorchButtonPosition", "torchButtonPosition", "getHardwareTriggerEnabled", "setHardwareTriggerEnabled", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, "", "getHardwareTriggerKeyCode", "()Ljava/lang/Integer;", "setHardwareTriggerKeyCode", "(Ljava/lang/Integer;)V", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "getTapShutterToPauseGuidelineText", "setTapShutterToPauseGuidelineText", "tapShutterToPauseGuidelineText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickViewSettingsProxyAdapter implements BarcodePickViewSettingsProxy {
    private final NativeBarcodePickViewSettings a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodePickViewSettingsProxyAdapter(NativeBarcodePickViewSettings _NativeBarcodePickViewSettings, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodePickViewSettings, "_NativeBarcodePickViewSettings");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodePickViewSettings;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodePickViewSettings getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getHardwareTriggerEnabled() {
        return this.a.getHardwareTriggerEnabled();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public Integer getHardwareTriggerKeyCode() {
        return this.a.getHardwareTriggerKeyCode();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getInitialGuidelineText() {
        String initialGuidelineText = this.a.getInitialGuidelineText();
        Intrinsics.checkNotNull(initialGuidelineText);
        return initialGuidelineText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getLoadingDialogTextForPicking() {
        String loadingDialogTextForPicking = this.a.getLoadingDialogTextForPicking();
        Intrinsics.checkNotNull(loadingDialogTextForPicking);
        return loadingDialogTextForPicking;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getLoadingDialogTextForUnpicking() {
        String loadingDialogTextForUnpicking = this.a.getLoadingDialogTextForUnpicking();
        Intrinsics.checkNotNull(loadingDialogTextForUnpicking);
        return loadingDialogTextForUnpicking;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getMoveCloserGuidelineText() {
        String moveCloserGuidelineText = this.a.getMoveCloserGuidelineText();
        Intrinsics.checkNotNull(moveCloserGuidelineText);
        return moveCloserGuidelineText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getOnFirstItemPickCompletedHintText() {
        String onFirstItemPickCompletedHintText = this.a.getOnFirstItemPickCompletedHintText();
        Intrinsics.checkNotNull(onFirstItemPickCompletedHintText);
        return onFirstItemPickCompletedHintText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getOnFirstItemToPickFoundHintText() {
        String onFirstItemToPickFoundHintText = this.a.getOnFirstItemToPickFoundHintText();
        Intrinsics.checkNotNull(onFirstItemToPickFoundHintText);
        return onFirstItemToPickFoundHintText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getOnFirstItemUnpickCompletedHintText() {
        String onFirstItemUnpickCompletedHintText = this.a.getOnFirstItemUnpickCompletedHintText();
        Intrinsics.checkNotNull(onFirstItemUnpickCompletedHintText);
        return onFirstItemUnpickCompletedHintText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getOnFirstUnmarkedItemPickCompletedHintText() {
        String onFirstUnmarkedItemPickCompletedHintText = this.a.getOnFirstUnmarkedItemPickCompletedHintText();
        Intrinsics.checkNotNull(onFirstUnmarkedItemPickCompletedHintText);
        return onFirstUnmarkedItemPickCompletedHintText;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowFinishButton() {
        return this.a.getShowFinishButton();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowGuidelines() {
        return this.a.getShowGuidelines();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowHints() {
        return this.a.getShowHints();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowLoadingDialog() {
        return this.a.getShowLoadingDialog();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowPauseButton() {
        return this.a.getShowPauseButton();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowTorchButton() {
        return this.a.getShowTorchButton();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public boolean getShowZoomButton() {
        return this.a.getShowZoomButton();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public String getTapShutterToPauseGuidelineText() {
        String tapShutterToPauseGuidelineText = this.a.getTapShutterToPauseGuidelineText();
        Intrinsics.checkNotNull(tapShutterToPauseGuidelineText);
        return tapShutterToPauseGuidelineText;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public Anchor getTorchButtonPosition() {
        Anchor torchButtonPosition = this.a.getTorchButtonPosition();
        Intrinsics.checkNotNull(torchButtonPosition);
        return torchButtonPosition;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public Anchor getZoomButtonPosition() {
        Anchor zoomButtonPosition = this.a.getZoomButtonPosition();
        Intrinsics.checkNotNull(zoomButtonPosition);
        return zoomButtonPosition;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setHardwareTriggerEnabled(boolean z) {
        this.a.setHardwareTriggerEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setHardwareTriggerKeyCode(Integer num) {
        this.a.setHardwareTriggerKeyCode(num);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setInitialGuidelineText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setInitialGuidelineText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setLoadingDialogTextForPicking(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setLoadingDialogTextForPicking(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setLoadingDialogTextForUnpicking(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setLoadingDialogTextForUnpicking(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setMoveCloserGuidelineText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setMoveCloserGuidelineText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setOnFirstItemPickCompletedHintText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setOnFirstItemPickCompletedHintText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setOnFirstItemToPickFoundHintText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setOnFirstItemToPickFoundHintText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setOnFirstItemUnpickCompletedHintText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setOnFirstItemUnpickCompletedHintText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setOnFirstUnmarkedItemPickCompletedHintText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setOnFirstUnmarkedItemPickCompletedHintText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowFinishButton(boolean z) {
        this.a.setShowFinishButton(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowGuidelines(boolean z) {
        this.a.setShowGuidelines(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowHints(boolean z) {
        this.a.setShowHints(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowLoadingDialog(boolean z) {
        this.a.setShowLoadingDialog(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowPauseButton(boolean z) {
        this.a.setShowPauseButton(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowTorchButton(boolean z) {
        this.a.setShowTorchButton(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setShowZoomButton(boolean z) {
        this.a.setShowZoomButton(z);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setTapShutterToPauseGuidelineText(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setTapShutterToPauseGuidelineText(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setTorchButtonPosition(Anchor p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setTorchButtonPosition(p0);
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettingsProxy
    public void setZoomButtonPosition(Anchor p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setZoomButtonPosition(p0);
    }

    public /* synthetic */ BarcodePickViewSettingsProxyAdapter(NativeBarcodePickViewSettings nativeBarcodePickViewSettings, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodePickViewSettings, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
