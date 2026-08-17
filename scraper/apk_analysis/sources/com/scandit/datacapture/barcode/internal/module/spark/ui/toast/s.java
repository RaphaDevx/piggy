package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview;
import com.scandit.datacapture.barcode.spark.ui.SparkScanToastSettings;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class s implements SparkScanViewToastPresenter {
    public final x a;
    public final SparkScanViewSettings b;
    public final y c;
    public n d;
    public final r e;

    public s(SparkScanViewMiniPreview toastHolder, SparkScanViewSettings settings) {
        z handler = new z();
        Intrinsics.checkNotNullParameter(toastHolder, "toastHolder");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = toastHolder;
        this.b = settings;
        this.c = handler;
        this.e = new r(this);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter
    public final void a(n toast) {
        q qVar;
        Intrinsics.checkNotNullParameter(toast, "toast");
        SparkScanToastSettings toastSettings = this.b.getToastSettings();
        if (toast instanceof g) {
            String targetModeEnabledMessage = toastSettings.getTargetModeEnabledMessage();
            if (targetModeEnabledMessage == null) {
                targetModeEnabledMessage = ((g) toast).c;
            }
            Integer toastBackgroundColor = toastSettings.getToastBackgroundColor();
            int intValue = toastBackgroundColor != null ? toastBackgroundColor.intValue() : ((g) toast).d;
            Integer toastTextColor = toastSettings.getToastTextColor();
            qVar = new q(targetModeEnabledMessage, intValue, toastTextColor != null ? toastTextColor.intValue() : toast.a);
        } else if (toast instanceof f) {
            String targetModeDisabledMessage = toastSettings.getTargetModeDisabledMessage();
            if (targetModeDisabledMessage == null) {
                targetModeDisabledMessage = ((f) toast).c;
            }
            Integer toastBackgroundColor2 = toastSettings.getToastBackgroundColor();
            int intValue2 = toastBackgroundColor2 != null ? toastBackgroundColor2.intValue() : toast.b;
            Integer toastTextColor2 = toastSettings.getToastTextColor();
            qVar = new q(targetModeDisabledMessage, intValue2, toastTextColor2 != null ? toastTextColor2.intValue() : toast.a);
        } else if (toast instanceof b) {
            String continuousModeEnabledMessage = toastSettings.getContinuousModeEnabledMessage();
            if (continuousModeEnabledMessage == null) {
                continuousModeEnabledMessage = ((b) toast).c;
            }
            Integer toastBackgroundColor3 = toastSettings.getToastBackgroundColor();
            int intValue3 = toastBackgroundColor3 != null ? toastBackgroundColor3.intValue() : ((b) toast).d;
            Integer toastTextColor3 = toastSettings.getToastTextColor();
            qVar = new q(continuousModeEnabledMessage, intValue3, toastTextColor3 != null ? toastTextColor3.intValue() : toast.a);
        } else if (toast instanceof a) {
            String continuousModeDisabledMessage = toastSettings.getContinuousModeDisabledMessage();
            if (continuousModeDisabledMessage == null) {
                continuousModeDisabledMessage = ((a) toast).c;
            }
            Integer toastBackgroundColor4 = toastSettings.getToastBackgroundColor();
            int intValue4 = toastBackgroundColor4 != null ? toastBackgroundColor4.intValue() : toast.b;
            Integer toastTextColor4 = toastSettings.getToastTextColor();
            qVar = new q(continuousModeDisabledMessage, intValue4, toastTextColor4 != null ? toastTextColor4.intValue() : toast.a);
        } else if (toast instanceof e) {
            String scanPausedMessage = toastSettings.getScanPausedMessage();
            if (scanPausedMessage == null) {
                scanPausedMessage = ((e) toast).c;
            }
            Integer toastBackgroundColor5 = toastSettings.getToastBackgroundColor();
            int intValue5 = toastBackgroundColor5 != null ? toastBackgroundColor5.intValue() : toast.b;
            Integer toastTextColor5 = toastSettings.getToastTextColor();
            qVar = new q(scanPausedMessage, intValue5, toastTextColor5 != null ? toastTextColor5.intValue() : toast.a);
        } else if (toast instanceof i) {
            String torchEnabledMessage = toastSettings.getTorchEnabledMessage();
            if (torchEnabledMessage == null) {
                torchEnabledMessage = ((i) toast).c;
            }
            Integer toastBackgroundColor6 = toastSettings.getToastBackgroundColor();
            int intValue6 = toastBackgroundColor6 != null ? toastBackgroundColor6.intValue() : ((i) toast).d;
            Integer toastTextColor6 = toastSettings.getToastTextColor();
            qVar = new q(torchEnabledMessage, intValue6, toastTextColor6 != null ? toastTextColor6.intValue() : toast.a);
        } else if (toast instanceof h) {
            String torchDisabledMessage = toastSettings.getTorchDisabledMessage();
            if (torchDisabledMessage == null) {
                torchDisabledMessage = ((h) toast).c;
            }
            Integer toastBackgroundColor7 = toastSettings.getToastBackgroundColor();
            int intValue7 = toastBackgroundColor7 != null ? toastBackgroundColor7.intValue() : toast.b;
            Integer toastTextColor7 = toastSettings.getToastTextColor();
            qVar = new q(torchDisabledMessage, intValue7, toastTextColor7 != null ? toastTextColor7.intValue() : toast.a);
        } else if (toast instanceof l) {
            String zoomedInMessage = toastSettings.getZoomedInMessage();
            if (zoomedInMessage == null) {
                zoomedInMessage = ((l) toast).c;
            }
            Integer toastBackgroundColor8 = toastSettings.getToastBackgroundColor();
            int intValue8 = toastBackgroundColor8 != null ? toastBackgroundColor8.intValue() : ((l) toast).d;
            Integer toastTextColor8 = toastSettings.getToastTextColor();
            qVar = new q(zoomedInMessage, intValue8, toastTextColor8 != null ? toastTextColor8.intValue() : toast.a);
        } else if (toast instanceof m) {
            String zoomedOutMessage = toastSettings.getZoomedOutMessage();
            if (zoomedOutMessage == null) {
                zoomedOutMessage = ((m) toast).c;
            }
            Integer toastBackgroundColor9 = toastSettings.getToastBackgroundColor();
            int intValue9 = toastBackgroundColor9 != null ? toastBackgroundColor9.intValue() : toast.b;
            Integer toastTextColor9 = toastSettings.getToastTextColor();
            qVar = new q(zoomedOutMessage, intValue9, toastTextColor9 != null ? toastTextColor9.intValue() : toast.a);
        } else if (toast instanceof j) {
            String userFacingCameraEnabledMessage = toastSettings.getUserFacingCameraEnabledMessage();
            if (userFacingCameraEnabledMessage == null) {
                userFacingCameraEnabledMessage = ((j) toast).c;
            }
            Integer toastBackgroundColor10 = toastSettings.getToastBackgroundColor();
            int intValue10 = toastBackgroundColor10 != null ? toastBackgroundColor10.intValue() : ((j) toast).d;
            Integer toastTextColor10 = toastSettings.getToastTextColor();
            qVar = new q(userFacingCameraEnabledMessage, intValue10, toastTextColor10 != null ? toastTextColor10.intValue() : toast.a);
        } else if (toast instanceof k) {
            String worldFacingCameraEnabledMessage = toastSettings.getWorldFacingCameraEnabledMessage();
            if (worldFacingCameraEnabledMessage == null) {
                worldFacingCameraEnabledMessage = ((k) toast).c;
            }
            Integer toastBackgroundColor11 = toastSettings.getToastBackgroundColor();
            int intValue11 = toastBackgroundColor11 != null ? toastBackgroundColor11.intValue() : toast.b;
            Integer toastTextColor11 = toastSettings.getToastTextColor();
            qVar = new q(worldFacingCameraEnabledMessage, intValue11, toastTextColor11 != null ? toastTextColor11.intValue() : toast.a);
        } else if (toast instanceof c) {
            String str = ((c) toast).c;
            Integer toastBackgroundColor12 = toastSettings.getToastBackgroundColor();
            int intValue12 = toastBackgroundColor12 != null ? toastBackgroundColor12.intValue() : toast.b;
            Integer toastTextColor12 = toastSettings.getToastTextColor();
            qVar = new q(str, intValue12, toastTextColor12 != null ? toastTextColor12.intValue() : toast.a);
        } else {
            if (!(toast instanceof d)) {
                throw new NoWhenBranchMatchedException();
            }
            d dVar = (d) toast;
            qVar = new q(dVar.c, dVar.d, dVar.e);
        }
        if (!this.b.getToastSettings().getToastEnabled() || qVar.a.length() <= 0) {
            return;
        }
        ((z) this.c).a.removeCallbacksAndMessages(null);
        this.d = toast;
        this.a.a(qVar.a, qVar.b, qVar.c);
        n nVar = this.d;
        if ((nVar instanceof e) || (nVar instanceof d)) {
            return;
        }
        ((z) this.c).a(this.e, this.b.getToastSettings().getToastDuration());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter
    public final void release() {
        ((z) this.c).a.removeCallbacksAndMessages(null);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter
    public final void a() {
        n nVar = this.d;
        if (nVar instanceof e) {
            this.d = null;
            this.a.h();
        } else if (nVar instanceof d) {
            this.d = null;
            this.a.h();
        }
    }
}
