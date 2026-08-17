package com.scandit.datacapture.barcode.internal.module.pick.ui;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickGuidanceHandler {
    public abstract void hideLoadingPopup();

    public abstract void setInitialGuidance(boolean z, String str);

    public abstract void setMoveCloserGuidance(boolean z, String str);

    public abstract void setTapShutterToPauseGuidance(boolean z, String str);

    public abstract void showLoadingPopup(boolean z);
}
