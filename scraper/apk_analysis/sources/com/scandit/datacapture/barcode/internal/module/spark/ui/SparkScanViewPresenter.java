package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.spark.data.C0435n;
import com.scandit.datacapture.barcode.internal.module.spark.data.C0436o;
import com.scandit.datacapture.barcode.internal.module.spark.data.EnumC0422a;
import com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0432k;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer;
import com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview;
import com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.capture.SparkScanListener;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSession;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanBarcodeFeedback;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanFeedbackDelegate;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastType;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewStateHelper;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanMiniPreviewSizeDeserializer;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanMiniPreviewSizeSerializer;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanScanningModeDeserializer;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanScanningModeSerializer;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import com.scandit.datacapture.barcode.spark.ui.SparkScanPreviewBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningModeKt;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewStateDeserializer;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewStateSerializer;
import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumSerializer;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.source.TorchStateDeserializer;
import com.scandit.datacapture.core.source.TorchStateSerializer;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class SparkScanViewPresenter implements com.scandit.datacapture.barcode.internal.module.spark.ui.button.i, SparkScanListener, com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.x, com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.A, InterfaceC0432k {
    private final SparkScanView a;
    private final SparkScanStateManager b;
    private final SparkScan c;
    private final SparkScanViewSettings d;
    private final SparkScanViewCameraManager e;
    private final SparkScanViewMiniPreview f;
    private final SparkScanViewCaptureButtonContainer g;
    private final SparkScanFeedbackManager h;
    private final SparkScanViewToastPresenter i;
    private final SparkScanAnalyticsManager j;
    private final NativeSparkScanStateMachine k;
    private f0 l;
    private SparkScanViewState m;
    private SparkScanViewState n;
    private SparkScanViewUiListener o;
    private boolean p;

    public SparkScanViewPresenter(SparkScanView sparkScanView, SparkScanStateManager stateManager, SparkScan sparkScan, SparkScanViewSettings settings, SparkScanViewCameraManager cameraManager, SparkScanViewMiniPreview miniPreview, SparkScanViewCaptureButtonContainer scanButton, SparkScanFeedbackManager feedbackManager, SparkScanViewToastPresenter toastPresenter, SparkScanAnalyticsManager analyticsManager, NativeSparkScanStateMachine nativeStateMachine) {
        Intrinsics.checkNotNullParameter(sparkScanView, "sparkScanView");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        Intrinsics.checkNotNullParameter(miniPreview, "miniPreview");
        Intrinsics.checkNotNullParameter(scanButton, "scanButton");
        Intrinsics.checkNotNullParameter(feedbackManager, "feedbackManager");
        Intrinsics.checkNotNullParameter(toastPresenter, "toastPresenter");
        Intrinsics.checkNotNullParameter(analyticsManager, "analyticsManager");
        Intrinsics.checkNotNullParameter(nativeStateMachine, "nativeStateMachine");
        this.a = sparkScanView;
        this.b = stateManager;
        this.c = sparkScan;
        this.d = settings;
        this.e = cameraManager;
        this.f = miniPreview;
        this.g = scanButton;
        this.h = feedbackManager;
        this.i = toastPresenter;
        this.j = analyticsManager;
        this.k = nativeStateMachine;
        this.l = null;
        nativeStateMachine.setViewHandlerDelegate(new G(new L(this)));
        nativeStateMachine.setToastPresenterDelegate(new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.p(new K(this)));
        nativeStateMachine.addSparkScanViewUiListenerAsync(new J(this));
        this.p = SparkScanViewDefaults.getDefaultTriggerButtonVisible();
    }

    public static final void a(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState) {
        sparkScanViewPresenter.getClass();
        boolean z = (sparkScanViewState == SparkScanViewState.ACTIVE || sparkScanViewState == SparkScanViewState.ERROR || sparkScanViewState == SparkScanViewState.INACTIVE) && sparkScanViewPresenter.p;
        sparkScanViewPresenter.a.setClickable(z);
        sparkScanViewPresenter.a.setFocusable(z);
    }

    public final void A() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(10);
        }
    }

    public final void B() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(37);
        }
    }

    public final void C() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(36);
        }
    }

    public final void D() {
        if (this.d.getHoldToScanEnabled()) {
            this.b.c(false);
            f0 f0Var = this.l;
            if (f0Var != null) {
                ((h0) f0Var).sendEmptyMessage(22);
            }
        }
    }

    public final void E() {
        f0 f0Var;
        if (!this.d.getHoldToScanEnabled() || (f0Var = this.l) == null) {
            return;
        }
        ((h0) f0Var).sendEmptyMessage(21);
    }

    public final void F() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        this.n = viewState;
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).removeCallbacksAndMessages(null);
        }
        this.l = null;
        this.j.a(EnumC0422a.b);
        a(this, SparkScanViewState.IDLE, false, 4);
        this.e.b(null);
        this.h.b();
        this.c.removeListener(this);
        this.f.a((com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.x) null);
        this.f.a((com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.A) null);
        this.f.j();
        this.g.removeAllViews();
        this.i.release();
        this.e.release();
        this.b.b(this);
    }

    public final Bundle G() {
        Bundle bundle = new Bundle();
        SparkScanViewState sparkScanViewState = this.n;
        if (sparkScanViewState != null) {
            bundle.putString("current_state", SparkScanViewStateSerializer.toString(sparkScanViewState));
            bundle.putString("current_mode", SparkScanScanningModeSerializer.toJson$default(this.b.s(), false, 2, null));
            bundle.putString("current_torch_state", TorchStateSerializer.toJson(this.b.x()));
            bundle.putBoolean("current_zoom_state", this.b.B());
            bundle.putString("current_expanded_state", SparkScanMiniPreviewSizeSerializer.toJson(this.b.o()));
            bundle.putString("current_manual_mini_preview_size", SparkScanMiniPreviewSizeSerializer.toJson(this.b.k()));
            bundle.putString("current_camera", NativeEnumSerializer.cameraPositionToString(this.b.a()));
        }
        return bundle;
    }

    public final void H() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(12);
        }
    }

    public final void I() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(16);
        }
    }

    public final void J() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(34, Boolean.TRUE));
        }
    }

    public final void K() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(34, Boolean.FALSE));
        }
    }

    public final void L() {
        f0 f0Var;
        if (!this.p || (f0Var = this.l) == null) {
            return;
        }
        h0 h0Var = (h0) f0Var;
        h0Var.removeCallbacksAndMessages(null);
        h0Var.sendMessage(h0Var.obtainMessage(2, new g0(true, false)));
    }

    public final void M() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (a(viewState)) {
            this.f.c(true);
        } else if (viewState == SparkScanViewState.ERROR) {
            this.f.c(false);
        } else {
            this.f.A();
        }
        this.g.g();
        SparkScanViewState viewState2 = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
        d(viewState2);
        this.f.m();
    }

    public final void N() {
        f0 f0Var;
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (viewState == SparkScanViewState.INACTIVE && SparkScanScanningModeKt.getPreviewBehavior(this.b.s()) == SparkScanPreviewBehavior.PERSISTENT) {
            f0 f0Var2 = this.l;
            if (f0Var2 != null) {
                h0 h0Var = (h0) f0Var2;
                h0Var.removeMessages(2);
                long g = this.b.g();
                Message obtainMessage = h0Var.obtainMessage(2, new g0(false, true));
                Intrinsics.checkNotNullExpressionValue(obtainMessage, "obtainMessage(...)");
                if (g < 0) {
                    return;
                }
                h0Var.sendMessageDelayed(obtainMessage, g);
                return;
            }
            return;
        }
        if (this.b.j()) {
            return;
        }
        SparkScanViewState viewState2 = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
        if (viewState2 == SparkScanViewState.ERROR || (f0Var = this.l) == null) {
            return;
        }
        h0 h0Var2 = (h0) f0Var;
        h0Var2.removeMessages(2);
        h0Var2.removeMessages(1);
        long i = this.b.i();
        Message obtainMessage2 = h0Var2.obtainMessage(1, Boolean.TRUE);
        Intrinsics.checkNotNullExpressionValue(obtainMessage2, "obtainMessage(...)");
        if (i < 0) {
            return;
        }
        h0Var2.sendMessageDelayed(obtainMessage2, i);
    }

    public final void O() {
        f0 f0Var;
        a();
        if (!this.b.l() || (f0Var = this.l) == null) {
            return;
        }
        h0 h0Var = (h0) f0Var;
        h0Var.sendMessage(h0Var.obtainMessage(33, Boolean.TRUE));
    }

    public final void P() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (viewState == SparkScanViewState.ACTIVE) {
            N();
            return;
        }
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(9);
        }
    }

    public final void Q() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(2, new g0(false, false)));
        }
    }

    public final boolean c() {
        return this.p;
    }

    public final void h() {
        if (SparkScanScanningModeKt.getScanningBehavior(this.b.s()) == SparkScanScanningBehavior.SINGLE && this.c.isSuppressed$scandit_barcode_capture()) {
            this.c._disable$scandit_barcode_capture();
        }
    }

    public final void i() {
        this.h.a();
        Callback callback = LambdaExtensionsKt.Callback(new W(this));
        Intrinsics.checkNotNullParameter(callback, "callback");
        a(this, false, false, false, 8);
        this.e.b(callback);
    }

    public final void j() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        d(viewState);
        SparkScanViewState viewState2 = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
        if (viewState2 == SparkScanViewState.ERROR) {
            this.f.g();
            return;
        }
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(1);
        }
    }

    public final void k() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (viewState == SparkScanViewState.IDLE) {
            this.h.a();
        }
        this.f.m(false);
        this.c.setSingleScanModeEnabled$scandit_barcode_capture(false);
        this.b.c(true);
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).removeCallbacksAndMessages(null);
        }
        f0 f0Var2 = this.l;
        if (f0Var2 != null) {
            ((h0) f0Var2).sendEmptyMessage(9);
        }
    }

    public final void l() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (NativeSparkScanViewStateHelper.isCaptureEnabledState(viewState)) {
            this.f.k();
        } else {
            g();
        }
    }

    public final void m(boolean z) {
        EnumC0422a enumC0422a;
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).removeCallbacksAndMessages(null);
        }
        if (!z) {
            enumC0422a = EnumC0422a.b;
        } else {
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            enumC0422a = EnumC0422a.c;
        }
        this.j.a(enumC0422a);
        f0 f0Var2 = this.l;
        if (f0Var2 != null) {
            long g = this.b.g();
            h0 h0Var = (h0) f0Var2;
            Message obtainMessage = h0Var.obtainMessage(2, new g0(false, true));
            Intrinsics.checkNotNullExpressionValue(obtainMessage, "obtainMessage(...)");
            if (g >= 0) {
                h0Var.sendMessageDelayed(obtainMessage, g);
            }
        }
        a(this, SparkScanViewState.INACTIVE, false, 6);
    }

    public final void n() {
        this.h.a();
        N();
        int i = M.e[this.b.o().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            SparkScanStateManager sparkScanStateManager = this.b;
            SparkScanMiniPreviewSize sparkScanMiniPreviewSize = SparkScanMiniPreviewSize.REGULAR;
            sparkScanStateManager.a(sparkScanMiniPreviewSize);
            this.b.b(sparkScanMiniPreviewSize);
            this.f.b(true);
            this.f.g();
            this.f.l();
            return;
        }
        SparkScanStateManager sparkScanStateManager2 = this.b;
        SparkScanMiniPreviewSize sparkScanMiniPreviewSize2 = SparkScanMiniPreviewSize.EXPANDED;
        sparkScanStateManager2.a(sparkScanMiniPreviewSize2);
        this.b.b(sparkScanMiniPreviewSize2);
        this.f.b(true);
        this.f.g();
        this.f.l();
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        d(viewState);
    }

    public final void o(boolean z) {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(30, Boolean.valueOf(z)));
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanListener
    public final void onBarcodeScanned(SparkScan sparkScan, SparkScanSession session, FrameData frameData) {
        Brush defaultBrush;
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(session, "session");
        Barcode newlyRecognizedBarcode = session.getNewlyRecognizedBarcode();
        if (newlyRecognizedBarcode != null) {
            SparkScanFeedbackDelegate feedbackDelegate = this.a.getFeedbackDelegate();
            SparkScanBarcodeFeedback feedbackForBarcode = feedbackDelegate != null ? feedbackDelegate.getFeedbackForBarcode(newlyRecognizedBarcode) : null;
            boolean z = feedbackForBarcode instanceof SparkScanBarcodeFeedback.Success;
            boolean z2 = !z && (feedbackForBarcode instanceof SparkScanBarcodeFeedback.Error) && ((SparkScanBarcodeFeedback.Error) feedbackForBarcode).getResumeCapturingDelay().asMillis() <= 0;
            if (SparkScanScanningModeKt.getScanningBehavior(this.b.s()) != SparkScanScanningBehavior.CONTINUOUS && !z2) {
                if (this.b.h()) {
                    sparkScan._disable$scandit_barcode_capture();
                } else if (this.b.d()) {
                    sparkScan._setResultSuppressionEnabled$scandit_barcode_capture(true);
                } else {
                    sparkScan._disable$scandit_barcode_capture();
                }
            }
            SparkScanViewMiniPreview sparkScanViewMiniPreview = this.f;
            SparkScanBarcodeFeedback.Success success = z ? (SparkScanBarcodeFeedback.Success) feedbackForBarcode : null;
            if (success == null || (defaultBrush = success.getBrush()) == null) {
                defaultBrush = SparkScanViewDefaults.getDefaultBrush();
            }
            sparkScanViewMiniPreview.setBrush(defaultBrush);
            f0 f0Var = this.l;
            if (f0Var != null) {
                h0 h0Var = (h0) f0Var;
                if (z) {
                    h0Var.sendMessage(h0Var.obtainMessage(7, (SparkScanBarcodeFeedback.Success) feedbackForBarcode));
                } else if (feedbackForBarcode instanceof SparkScanBarcodeFeedback.Error) {
                    SparkScanBarcodeFeedback.Error error = (SparkScanBarcodeFeedback.Error) feedbackForBarcode;
                    h0Var.removeCallbacksAndMessages(null);
                    h0Var.sendMessage(h0Var.obtainMessage(5, error));
                    long asMillis = error.getResumeCapturingDelay().asMillis();
                    if (asMillis > 0 && asMillis >= 0) {
                        h0Var.sendEmptyMessageDelayed(4, asMillis);
                    }
                }
            }
            f0 f0Var2 = this.l;
            if (f0Var2 != null) {
                h0 h0Var2 = (h0) f0Var2;
                h0Var2.sendMessage(h0Var2.obtainMessage(6, Boolean.valueOf(z2)));
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanListener
    public final void onSessionUpdated(SparkScan sparkScan, SparkScanSession sparkScanSession, FrameData frameData) {
        SparkScanListener.DefaultImpls.onSessionUpdated(this, sparkScan, sparkScanSession, frameData);
    }

    public final void p(boolean z) {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(26, Boolean.valueOf(z)));
        }
    }

    public final void q() {
        SparkScanScanningBehavior sparkScanScanningBehavior;
        this.h.a();
        int i = M.a[SparkScanScanningModeKt.getScanningBehavior(this.b.s()).ordinal()];
        if (i == 1) {
            sparkScanScanningBehavior = SparkScanScanningBehavior.CONTINUOUS;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            sparkScanScanningBehavior = SparkScanScanningBehavior.SINGLE;
        }
        SparkScanStateManager sparkScanStateManager = this.b;
        sparkScanStateManager.a(SparkScanScanningModeKt.copyWith$default(sparkScanStateManager.s(), sparkScanScanningBehavior, null, 2, null));
        boolean z = SparkScanScanningModeKt.getScanningBehavior(this.b.s()) == SparkScanScanningBehavior.SINGLE;
        this.f.m(z);
        this.c.setSingleScanModeEnabled$scandit_barcode_capture(z);
        this.k.onScanningBehaviorButtonTapped();
        this.f.g();
        N();
    }

    public final void r() {
        this.h.a();
        SparkScanStateManager sparkScanStateManager = this.b;
        sparkScanStateManager.a(SparkScanScanningModeKt.changeMode(sparkScanStateManager.s()));
        SparkScanStateManager sparkScanStateManager2 = this.b;
        sparkScanStateManager2.b(SparkScanScanningModeKt.changeMode(sparkScanStateManager2.t()));
        if (this.b.s() instanceof SparkScanScanningMode.Default) {
            this.c.get_sparkScanInternal().isEnabled();
        }
        this.e.b();
        if (a((SparkScanViewState) null)) {
            SparkScanViewState viewState = this.k.getViewState();
            Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
            int i = M.b[viewState.ordinal()];
            if (i == 1 || i == 2) {
                SparkScanViewState viewState2 = this.k.getViewState();
                Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
                a(this, viewState2, true, 2);
            }
        }
        this.k.onTargetModeButtonTapped();
        this.f.g();
        N();
    }

    public final void s() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(23);
        }
    }

    public final void t() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(31);
        }
    }

    public final void u() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(27);
        }
    }

    public final void v() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(32);
        }
    }

    public final void w() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(25);
        }
    }

    public final void x() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(14);
        }
    }

    public final void y() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(15);
        }
    }

    public final void z() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).sendEmptyMessage(29);
        }
    }

    public final SparkScanViewState b() {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        return viewState;
    }

    public final void c(SparkScanViewState currentState) {
        Intrinsics.checkNotNullParameter(currentState, "currentState");
        if (currentState != SparkScanViewState.INACTIVE || !this.b.d()) {
            if (currentState == SparkScanViewState.ACTIVE) {
                this.c._enable$scandit_barcode_capture();
            } else {
                this.c._disable$scandit_barcode_capture();
            }
        }
        a(new X(this, currentState));
    }

    public final void e(boolean z) {
        if (z) {
            SparkScanViewState viewState = this.k.getViewState();
            Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
            if (viewState != SparkScanViewState.ERROR) {
                f0 f0Var = this.l;
                if (f0Var != null) {
                    ((h0) f0Var).removeMessages(1);
                    return;
                }
                return;
            }
        }
        if (z) {
            return;
        }
        N();
    }

    public final void f() {
        this.h.a();
        SparkScanStateManager sparkScanStateManager = this.b;
        sparkScanStateManager.a(M.c[sparkScanStateManager.a().ordinal()] == 1 ? CameraPosition.USER_FACING : CameraPosition.WORLD_FACING);
        this.e.a(new S(this, new U(this)));
        h(false);
        this.k.onCameraSwitchButtonTapped();
        N();
    }

    public final void g() {
        this.h.a();
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (this.b.h() && viewState == SparkScanViewState.ACTIVE) {
            N();
            this.c._selectBarcodeWithSmartScanSelection$scandit_barcode_capture();
        } else {
            if (viewState == SparkScanViewState.ACTIVE) {
                N();
                return;
            }
            f0 f0Var = this.l;
            if (f0Var != null) {
                ((h0) f0Var).sendEmptyMessage(9);
            }
        }
    }

    public final void b(boolean z) {
        this.g.a(z);
    }

    public final void d() {
        this.h.a();
        Callback callback = LambdaExtensionsKt.Callback(new O(this));
        Intrinsics.checkNotNullParameter(callback, "callback");
        a(this, false, false, false, 8);
        this.e.b(callback);
    }

    public final void o() {
        TorchState torchState;
        this.h.a();
        if (M.d[this.b.x().ordinal()] == 1) {
            torchState = TorchState.OFF;
        } else {
            torchState = TorchState.ON;
        }
        if (a((SparkScanViewState) null)) {
            this.e.a(torchState, C0435n.a);
        }
        this.b.a(torchState);
        N();
        this.k.onTorchButtonTapped();
        this.f.g();
    }

    public final void p() {
        this.h.a();
        N();
        if (this.b.B()) {
            h(true);
            return;
        }
        if (this.b.B()) {
            return;
        }
        this.b.e(true);
        this.f.g();
        this.e.b();
        this.k.onZoomIn();
        this.f.l();
    }

    public final void b(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            Intrinsics.checkNotNullParameter(text, "text");
            h0Var.sendMessage(h0Var.obtainMessage(20, text));
        }
    }

    public final void l(boolean z) {
        this.p = z;
        boolean z2 = false;
        this.g.setVisibility(!z ? 8 : 0);
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if ((viewState == SparkScanViewState.ACTIVE || viewState == SparkScanViewState.ERROR || viewState == SparkScanViewState.INACTIVE) && this.p) {
            z2 = true;
        }
        this.a.setClickable(z2);
        this.a.setFocusable(z2);
    }

    public final void e() {
        this.h.a();
        Callback callback = LambdaExtensionsKt.Callback(new Q(this));
        Intrinsics.checkNotNullParameter(callback, "callback");
        a(this, false, false, false, 8);
        this.e.b(callback);
    }

    public final void j(boolean z) {
        SparkScanViewState viewState = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
        if (viewState == SparkScanViewState.ERROR) {
            return;
        }
        f0 f0Var = this.l;
        if (f0Var != null) {
            ((h0) f0Var).removeMessages(1);
        }
        if (SparkScanScanningModeKt.getScanningBehavior(this.b.s()) == SparkScanScanningBehavior.CONTINUOUS) {
            N();
            return;
        }
        SparkScanViewState viewState2 = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
        if (viewState2 != SparkScanViewState.IDLE) {
            if (z) {
                this.c.setSingleScanModeEnabled$scandit_barcode_capture(SparkScanScanningModeKt.getScanningBehavior(this.b.s()) == SparkScanScanningBehavior.SINGLE);
                N();
                return;
            }
            if (SparkScanScanningModeKt.getPreviewBehavior(this.b.s()) == SparkScanPreviewBehavior.PERSISTENT) {
                this.f.m(false);
                this.c.setSingleScanModeEnabled$scandit_barcode_capture(false);
                this.f.c();
                f0 f0Var2 = this.l;
                if (f0Var2 != null) {
                    ((h0) f0Var2).removeMessages(10);
                }
                f0 f0Var3 = this.l;
                if (f0Var3 != null) {
                    ((h0) f0Var3).sendEmptyMessage(1);
                    return;
                }
                return;
            }
            this.f.c();
            this.f.b();
            f0 f0Var4 = this.l;
            if (f0Var4 != null) {
                ((h0) f0Var4).removeMessages(10);
            }
            f0 f0Var5 = this.l;
            if (f0Var5 != null) {
                ((h0) f0Var5).sendEmptyMessage(1);
            }
        }
    }

    private final void h(boolean z) {
        if (this.b.B()) {
            this.b.e(false);
            this.f.g();
            this.e.b();
            if (z) {
                this.k.onZoomOut();
            }
            this.f.l();
        }
    }

    private final boolean a(SparkScanViewState sparkScanViewState) {
        if (sparkScanViewState == null) {
            sparkScanViewState = this.k.getViewState();
            Intrinsics.checkNotNullExpressionValue(sparkScanViewState, "getViewState(...)");
        }
        return NativeSparkScanViewStateHelper.isCaptureEnabledState(sparkScanViewState) || (SparkScanScanningModeKt.getPreviewBehavior(this.b.s()) == SparkScanPreviewBehavior.PERSISTENT && sparkScanViewState == SparkScanViewState.INACTIVE);
    }

    public final void g(boolean z) {
        if (z) {
            return;
        }
        h(true);
    }

    public final void k(boolean z) {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(28, Boolean.valueOf(z)));
        }
    }

    public static final boolean k(SparkScanViewPresenter sparkScanViewPresenter) {
        return SparkScanScanningModeKt.getPreviewBehavior(sparkScanViewPresenter.b.s()) == SparkScanPreviewBehavior.PERSISTENT;
    }

    public final void a(SparkScanViewUiListener sparkScanViewUiListener) {
        this.o = sparkScanViewUiListener;
    }

    public final void m() {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(2, new g0(true, false)));
        }
    }

    public static final void c(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState) {
        if (sparkScanViewPresenter.a(sparkScanViewState)) {
            sparkScanViewPresenter.f.c(true);
        } else if (sparkScanViewState == SparkScanViewState.ERROR) {
            sparkScanViewPresenter.f.c(false);
        } else {
            sparkScanViewPresenter.f.A();
        }
    }

    public final void a(boolean z) {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(35, Boolean.valueOf(z)));
        }
    }

    public static final TorchState h(SparkScanViewPresenter sparkScanViewPresenter) {
        return sparkScanViewPresenter.b.x();
    }

    public final void a(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.i.a(new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.c(text));
    }

    public final void n(boolean z) {
        this.b.b(z);
        a(new Y(this, z));
    }

    public static void a(SparkScanViewPresenter sparkScanViewPresenter, boolean z, boolean z2, boolean z3, int i) {
        EnumC0422a enumC0422a;
        boolean z4 = (i & 4) != 0;
        if ((i & 8) != 0) {
            z3 = false;
        }
        if (z2) {
            sparkScanViewPresenter.h.a();
        }
        SparkScanViewState sparkScanViewState = SparkScanViewState.IDLE;
        if (z4) {
            sparkScanViewPresenter.getClass();
            a0 a0Var = new a0(sparkScanViewPresenter);
            b0 b0Var = b0.a;
            if (sparkScanViewPresenter.a(sparkScanViewState)) {
                sparkScanViewPresenter.e.a(LambdaExtensionsKt.Callback(new c0(sparkScanViewPresenter, b0Var)));
            } else {
                sparkScanViewPresenter.e.a(new d0(a0Var));
            }
        }
        if (z3) {
            sparkScanViewPresenter.k.forceViewState(sparkScanViewState);
        } else {
            sparkScanViewPresenter.k.setViewState(sparkScanViewState);
        }
        if (z) {
            enumC0422a = EnumC0422a.c;
        } else if (!z) {
            enumC0422a = EnumC0422a.b;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        sparkScanViewPresenter.j.a(enumC0422a);
        sparkScanViewPresenter.i.a();
    }

    public final void c(boolean z) {
        this.b.d(z);
        if (z) {
            this.h.a();
            f0 f0Var = this.l;
            if (f0Var != null) {
                ((h0) f0Var).removeMessages(2);
            }
            f0 f0Var2 = this.l;
            if (f0Var2 != null) {
                ((h0) f0Var2).removeMessages(1);
            }
            this.f.f();
            SparkScanViewMiniPreview sparkScanViewMiniPreview = this.f;
            int i = E.a;
            sparkScanViewMiniPreview.a(((Number) E.l.getValue()).floatValue());
            SparkScanViewState viewState = this.k.getViewState();
            Intrinsics.checkNotNullExpressionValue(viewState, "getViewState(...)");
            if (viewState == SparkScanViewState.ACTIVE) {
                this.c._disable$scandit_barcode_capture();
                return;
            }
            return;
        }
        N();
        this.f.l();
        SparkScanViewMiniPreview sparkScanViewMiniPreview2 = this.f;
        int i2 = E.a;
        sparkScanViewMiniPreview2.a(((Number) E.k.getValue()).floatValue());
        SparkScanViewState viewState2 = this.k.getViewState();
        Intrinsics.checkNotNullExpressionValue(viewState2, "getViewState(...)");
        if (viewState2 == SparkScanViewState.ACTIVE) {
            this.c._enable$scandit_barcode_capture();
        }
    }

    public final void f(boolean z) {
        if (z) {
            return;
        }
        this.b.a(this.d.getDefaultTorchState());
        if (a((SparkScanViewState) null)) {
            this.e.a(this.b.x(), C0435n.a);
        }
    }

    public static final CameraPosition c(SparkScanViewPresenter sparkScanViewPresenter) {
        return sparkScanViewPresenter.b.a();
    }

    static void a(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState, boolean z, int i) {
        boolean z2 = (i & 2) != 0;
        if ((i & 4) != 0) {
            z = false;
        }
        if (z2) {
            sparkScanViewPresenter.getClass();
            a0 a0Var = new a0(sparkScanViewPresenter);
            b0 b0Var = b0.a;
            if (sparkScanViewPresenter.a(sparkScanViewState)) {
                sparkScanViewPresenter.e.a(LambdaExtensionsKt.Callback(new c0(sparkScanViewPresenter, b0Var)));
            } else {
                sparkScanViewPresenter.e.a(new d0(a0Var));
            }
        }
        if (z) {
            sparkScanViewPresenter.k.forceViewState(sparkScanViewState);
        } else {
            sparkScanViewPresenter.k.setViewState(sparkScanViewState);
        }
    }

    public static final boolean j(SparkScanViewPresenter sparkScanViewPresenter) {
        return sparkScanViewPresenter.b.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(final Function0 function0) {
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            function0.invoke();
            return;
        }
        f0 f0Var = this.l;
        if (f0Var != null) {
            f0Var.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SparkScanViewPresenter.b(Function0.this);
                }
            });
        }
    }

    public static final void a(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState, U u, U u2) {
        if (sparkScanViewPresenter.a(sparkScanViewState)) {
            sparkScanViewPresenter.e.a(LambdaExtensionsKt.Callback(new c0(sparkScanViewPresenter, u)));
        } else {
            sparkScanViewPresenter.e.a(new d0(u2));
        }
    }

    public final void a() {
        a(this, SparkScanViewState.ACTIVE, true, 2);
        this.j.a();
        N();
        this.i.a();
        boolean z = SparkScanScanningModeKt.getScanningBehavior(this.b.s()) == SparkScanScanningBehavior.SINGLE;
        this.f.m(z);
        this.c.setSingleScanModeEnabled$scandit_barcode_capture(z);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0432k
    public final void a(SparkScanScanningMode previousMode, SparkScanScanningMode newMode) {
        Intrinsics.checkNotNullParameter(previousMode, "previousMode");
        Intrinsics.checkNotNullParameter(newMode, "newMode");
        if (Intrinsics.areEqual(previousMode.getClass(), newMode.getClass())) {
            return;
        }
        this.c._applyNewSettingsForScanningMode$scandit_barcode_capture(newMode);
    }

    public final void a(SparkScanBarcodeFeedback.Success success) {
        Intrinsics.checkNotNullParameter(success, "success");
        this.h.a(success.getIo.sentry.protocol.Feedback.TYPE java.lang.String());
        this.h.b(success.getVisualFeedbackColor());
    }

    public final void a(SparkScanBarcodeFeedback.Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        boolean z = error.getResumeCapturingDelay().asMillis() > 0;
        if (z) {
            a(this, SparkScanViewState.ERROR, false, 6);
            this.i.a(new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.d(error.getMessage()));
            this.g.h();
        }
        this.h.a(error.getIo.sentry.protocol.Feedback.TYPE java.lang.String());
        this.h.a(error.getVisualFeedbackColor());
        this.f.a(error.getBrush(), !z);
    }

    public final void a(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        String string = bundle.getString("current_state");
        if (string != null) {
            this.m = SparkScanViewStateDeserializer.fromString(string);
        }
        String string2 = bundle.getString("current_mode");
        if (string2 != null) {
            SparkScanStateManager sparkScanStateManager = this.b;
            Intrinsics.checkNotNull(string2);
            sparkScanStateManager.a(SparkScanScanningModeDeserializer.fromJson(string2));
        }
        String string3 = bundle.getString("current_torch_state");
        if (string3 != null) {
            SparkScanStateManager sparkScanStateManager2 = this.b;
            Intrinsics.checkNotNull(string3);
            sparkScanStateManager2.a(TorchStateDeserializer.fromJson(string3));
        }
        this.b.e(bundle.getBoolean("current_zoom_state"));
        String string4 = bundle.getString("current_expanded_state");
        if (string4 != null) {
            SparkScanStateManager sparkScanStateManager3 = this.b;
            Intrinsics.checkNotNull(string4);
            sparkScanStateManager3.b(SparkScanMiniPreviewSizeDeserializer.fromJson(string4));
        }
        String string5 = bundle.getString("current_manual_mini_preview_size");
        if (string5 != null) {
            SparkScanStateManager sparkScanStateManager4 = this.b;
            Intrinsics.checkNotNull(string5);
            sparkScanStateManager4.a(SparkScanMiniPreviewSizeDeserializer.fromJson(string5));
        }
        String string6 = bundle.getString("current_camera");
        if (string6 != null) {
            SparkScanStateManager sparkScanStateManager5 = this.b;
            CameraPosition cameraPositionFromJsonString = NativeEnumDeserializer.cameraPositionFromJsonString(string6);
            Intrinsics.checkNotNullExpressionValue(cameraPositionFromJsonString, "cameraPositionFromJsonString(...)");
            sparkScanStateManager5.a(cameraPositionFromJsonString);
        }
    }

    public static final void a(SparkScanViewPresenter sparkScanViewPresenter, boolean z) {
        if (!z) {
            sparkScanViewPresenter.b.D();
        } else if (!(sparkScanViewPresenter.b.s() instanceof SparkScanScanningMode.Target)) {
            sparkScanViewPresenter.b.b(SparkScanMiniPreviewSize.EXPANDED);
        }
        sparkScanViewPresenter.f.b(false);
        sparkScanViewPresenter.f.g();
        sparkScanViewPresenter.f.l();
    }

    public static final void a(SparkScanViewPresenter sparkScanViewPresenter) {
        sparkScanViewPresenter.h.a();
    }

    public static final void a(SparkScanViewPresenter sparkScanViewPresenter, NativeSparkScanToastType nativeSparkScanToastType) {
        com.scandit.datacapture.barcode.internal.module.spark.ui.toast.n gVar;
        switch (M.f[nativeSparkScanToastType.ordinal()]) {
            case 1:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.g();
                break;
            case 2:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.f();
                break;
            case 3:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.b();
                break;
            case 4:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.a();
                break;
            case 5:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.e();
                break;
            case 6:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.l();
                break;
            case 7:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.m();
                break;
            case 8:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.i();
                break;
            case 9:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.h();
                break;
            case 10:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.j();
                break;
            case 11:
                gVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.k();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        sparkScanViewPresenter.i.a(gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    public static final void b(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState) {
        if (SparkScanScanningModeKt.getPreviewBehavior(sparkScanViewPresenter.b.s()) == SparkScanPreviewBehavior.PERSISTENT && sparkScanViewState == SparkScanViewState.INACTIVE) {
            sparkScanViewPresenter.f.c();
        }
        if (sparkScanViewState == SparkScanViewState.ACTIVE) {
            sparkScanViewPresenter.f.o();
        }
    }

    public final void b(SparkScanViewState initialState) {
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        f0 f0Var = this.l;
        if (f0Var == null) {
            f0Var = new h0(this);
        }
        this.l = f0Var;
        this.c.removeListener(this);
        this.c.addListener(this);
        this.e.a(C0436o.a);
        this.f.a((com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.x) this);
        this.b.a(this);
        SparkScanViewState sparkScanViewState = this.m;
        if (sparkScanViewState != null) {
            initialState = sparkScanViewState;
        }
        int i = M.b[initialState.ordinal()];
        if (i == 1) {
            f0 f0Var2 = this.l;
            if (f0Var2 != null) {
                ((h0) f0Var2).sendEmptyMessage(9);
            }
        } else if (i == 2 || i == 3) {
            f0 f0Var3 = this.l;
            if (f0Var3 != null) {
                ((h0) f0Var3).sendEmptyMessage(1);
            }
        } else if (i == 4) {
            a(this, false, false, true, 4);
        } else if (i == 5) {
            a(this, SparkScanViewState.INITIAL, false, 6);
        }
        this.m = null;
    }

    public final void i(boolean z) {
        f0 f0Var = this.l;
        if (f0Var != null) {
            h0 h0Var = (h0) f0Var;
            h0Var.sendMessage(h0Var.obtainMessage(33, Boolean.valueOf(z)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(SparkScanViewState sparkScanViewState) {
        int i = M.b[sparkScanViewState.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            this.g.i();
        } else {
            this.g.a(sparkScanViewState);
        }
    }

    public static final boolean i(SparkScanViewPresenter sparkScanViewPresenter) {
        return sparkScanViewPresenter.b.B();
    }

    public final void d(boolean z) {
        if (z) {
            return;
        }
        SparkScanStateManager sparkScanStateManager = this.b;
        SparkScanMiniPreviewSize sparkScanMiniPreviewSize = SparkScanMiniPreviewSize.REGULAR;
        sparkScanStateManager.a(sparkScanMiniPreviewSize);
        this.b.b(sparkScanMiniPreviewSize);
        this.f.b(true);
        this.f.g();
        this.f.l();
    }

    public static final void l(SparkScanViewPresenter sparkScanViewPresenter) {
        f0 f0Var = sparkScanViewPresenter.l;
        if (f0Var != null) {
            ((h0) f0Var).removeCallbacksAndMessages(null);
        }
    }
}
