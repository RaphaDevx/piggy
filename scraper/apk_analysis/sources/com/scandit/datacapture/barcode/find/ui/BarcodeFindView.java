package com.scandit.datacapture.barcode.find.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.feedback.BarcodeFindFeedback;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareButtonHelperKt;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareKeyEvent;
import com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager;
import com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout;
import com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter;
import com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler;
import com.scandit.datacapture.barcode.internal.module.find.ui.u0;
import com.scandit.datacapture.barcode.internal.module.find.ui.x0;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.feedback.Feedback;
import com.scandit.datacapture.core.common.feedback.Vibration;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.ui.DataCaptureView;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 y2\u00020\u0001:\u0002yzJ\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\bJ\r\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\bJ\r\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\bJ/\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0014¢\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0018\u0010\u0013R\u001a\u0010\u001e\u001a\u00020\u00198\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010#\u001a\u00020\u001f8\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010 \u001a\u0004\b!\u0010\"R.\u0010,\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R.\u00100\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010'\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R.\u00104\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010'\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R.\u00108\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010'\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R.\u0010<\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010'\u001a\u0004\b:\u0010)\"\u0004\b;\u0010+R.\u0010@\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010'\u001a\u0004\b>\u0010)\"\u0004\b?\u0010+R.\u0010D\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bA\u0010'\u001a\u0004\bB\u0010)\"\u0004\bC\u0010+R+\u0010M\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bG\u0010H\"\u0004\bI\u0010J*\u0004\bK\u0010LR+\u0010Q\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bN\u0010H\"\u0004\bO\u0010J*\u0004\bP\u0010LR+\u0010U\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bR\u0010H\"\u0004\bS\u0010J*\u0004\bT\u0010LR+\u0010Y\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bV\u0010H\"\u0004\bW\u0010J*\u0004\bX\u0010LR/\u0010]\u001a\u0004\u0018\u00010$2\b\u0010F\u001a\u0004\u0018\u00010$8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bZ\u0010)\"\u0004\b[\u0010+*\u0004\b\\\u0010LR+\u0010a\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b^\u0010H\"\u0004\b_\u0010J*\u0004\b`\u0010LR+\u0010e\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bb\u0010H\"\u0004\bc\u0010J*\u0004\bd\u0010LR+\u0010i\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bf\u0010H\"\u0004\bg\u0010J*\u0004\bh\u0010LR+\u0010p\u001a\u00020j2\u0006\u0010F\u001a\u00020j8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n*\u0004\bo\u0010LR+\u0010t\u001a\u00020E2\u0006\u0010F\u001a\u00020E8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bq\u0010H\"\u0004\br\u0010J*\u0004\bs\u0010LR\u0013\u0010x\u001a\u0004\u0018\u00010u8F¢\u0006\u0006\u001a\u0004\bv\u0010w¨\u0006{"}, d2 = {"Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "Landroid/widget/RelativeLayout;", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewUiListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "setListener", "(Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewUiListener;)V", "startSearching", "()V", "stopSearching", "pauseSearching", "onPause", "onResume", "", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", RRWebVideoEvent.JsonKeys.LEFT, RRWebVideoEvent.JsonKeys.TOP, "right", "bottom", "setPadding", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;", "c", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;", "getSettings$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;", "settings", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/BarcodeFindCameraManager;", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/BarcodeFindCameraManager;", "getCameraManager$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/find/capture/BarcodeFindCameraManager;", "cameraManager", "", "text", "j", "Ljava/lang/String;", "getTextForPointAtBarcodesToSearchHint", "()Ljava/lang/String;", "setTextForPointAtBarcodesToSearchHint", "(Ljava/lang/String;)V", "textForPointAtBarcodesToSearchHint", "k", "getTextForAllItemsFoundSuccessfullyHint", "setTextForAllItemsFoundSuccessfullyHint", "textForAllItemsFoundSuccessfullyHint", "l", "getTextForMoveCloserToBarcodesHint", "setTextForMoveCloserToBarcodesHint", "textForMoveCloserToBarcodesHint", "m", "getTextForTapShutterToPauseScreenHint", "setTextForTapShutterToPauseScreenHint", "textForTapShutterToPauseScreenHint", "n", "getTextForTapShutterToResumeSearchHint", "setTextForTapShutterToResumeSearchHint", "textForTapShutterToResumeSearchHint", "o", "getTextForItemListUpdatedHint", "setTextForItemListUpdatedHint", "textForItemListUpdatedHint", "p", "getTextForItemListUpdatedWhenPausedHint", "setTextForItemListUpdatedWhenPausedHint", "textForItemListUpdatedWhenPausedHint", "", "<set-?>", "getShouldShowPauseButton", "()Z", "setShouldShowPauseButton", "(Z)V", "getShouldShowPauseButton$delegate", "(Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;)Ljava/lang/Object;", "shouldShowPauseButton", "getShouldShowFinishButton", "setShouldShowFinishButton", "getShouldShowFinishButton$delegate", "shouldShowFinishButton", "getShouldShowProgressBar", "setShouldShowProgressBar", "getShouldShowProgressBar$delegate", "shouldShowProgressBar", "getShouldShowCarousel", "setShouldShowCarousel", "getShouldShowCarousel$delegate", "shouldShowCarousel", "getTextForCollapseCardsButton", "setTextForCollapseCardsButton", "getTextForCollapseCardsButton$delegate", "textForCollapseCardsButton", "getShouldShowHints", "setShouldShowHints", "getShouldShowHints$delegate", "shouldShowHints", "getShouldShowUserGuidanceView", "setShouldShowUserGuidanceView", "getShouldShowUserGuidanceView$delegate", "shouldShowUserGuidanceView", "getShouldShowTorchControl", "setShouldShowTorchControl", "getShouldShowTorchControl$delegate", "shouldShowTorchControl", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getTorchControlPosition", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setTorchControlPosition", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "getTorchControlPosition$delegate", "torchControlPosition", "getShouldShowZoomControl", "setShouldShowZoomControl", "getShouldShowZoomControl$delegate", "shouldShowZoomControl", "Lcom/scandit/datacapture/core/source/Camera;", "getCamera", "()Lcom/scandit/datacapture/core/source/Camera;", "camera", "Companion", "com/scandit/datacapture/barcode/find/ui/d", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFindView extends RelativeLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DataCaptureContext a;
    private final BarcodeFind b;

    /* renamed from: c, reason: from kotlin metadata */
    private final BarcodeFindViewSettings settings;
    private final DataCaptureView d;
    private final BarcodeFindViewLayout e;
    private final BarcodeFindViewPresenter f;
    private final BarcodeFindBasicOverlay g;

    /* renamed from: h, reason: from kotlin metadata */
    private final BarcodeFindCameraManager cameraManager;
    private final com.scandit.datacapture.barcode.internal.module.find.ui.listener.c i;

    /* renamed from: j, reason: from kotlin metadata */
    private String textForPointAtBarcodesToSearchHint;

    /* renamed from: k, reason: from kotlin metadata */
    private String textForAllItemsFoundSuccessfullyHint;

    /* renamed from: l, reason: from kotlin metadata */
    private String textForMoveCloserToBarcodesHint;

    /* renamed from: m, reason: from kotlin metadata */
    private String textForTapShutterToPauseScreenHint;

    /* renamed from: n, reason: from kotlin metadata */
    private String textForTapShutterToResumeSearchHint;

    /* renamed from: o, reason: from kotlin metadata */
    private String textForItemListUpdatedHint;

    /* renamed from: p, reason: from kotlin metadata */
    private String textForItemListUpdatedWhenPausedHint;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HardwareKeyEvent.values().length];
            try {
                iArr[HardwareKeyEvent.CLICK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BarcodeFindView(Context context, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings barcodeFindViewSettings, DataCaptureView dataCaptureView, BarcodeFindViewLayout barcodeFindViewLayout, BarcodeFindViewPresenter barcodeFindViewPresenter, BarcodeFindGuidanceHandler barcodeFindGuidanceHandler, BarcodeFindBasicOverlay barcodeFindBasicOverlay, BarcodeFindCameraManager barcodeFindCameraManager, DefaultConstructorMarker defaultConstructorMarker) {
        super(context);
        this.a = dataCaptureContext;
        this.b = barcodeFind;
        this.settings = barcodeFindViewSettings;
        this.d = dataCaptureView;
        this.e = barcodeFindViewLayout;
        this.f = barcodeFindViewPresenter;
        this.g = barcodeFindBasicOverlay;
        this.cameraManager = barcodeFindCameraManager;
        this.i = new com.scandit.datacapture.barcode.internal.module.find.ui.listener.c(barcodeFind, barcodeFindViewPresenter);
        setBackgroundColor(d.a);
        DataCaptureContext.setFrameSource$default(dataCaptureContext, barcodeFindCameraManager.a(), null, 2, null);
        setClipChildren(false);
        setClipToPadding(false);
        barcodeFindViewLayout.a(this, barcodeFindBasicOverlay);
        barcodeFindViewLayout.a(barcodeFindViewPresenter);
        barcodeFindBasicOverlay.a(barcodeFindGuidanceHandler);
        BarcodeFindFeedback barcodeFindFeedback = barcodeFind.getIo.sentry.protocol.Feedback.TYPE java.lang.String();
        barcodeFindFeedback.setFound(new Feedback(barcodeFindViewSettings.getHapticEnabled() ? Vibration.INSTANCE.defaultVibration() : null, barcodeFindViewSettings.getSoundEnabled() ? BarcodeFindFeedback.INSTANCE.defaultFoundSound$scandit_barcode_capture() : null));
        barcodeFindFeedback.setItemListUpdated(new Feedback(barcodeFindViewSettings.getHapticEnabled() ? Vibration.INSTANCE.defaultVibration() : null, barcodeFindViewSettings.getSoundEnabled() ? BarcodeFindFeedback.INSTANCE.defaultItemListUpdatedSound$scandit_barcode_capture() : null));
        if (barcodeFindViewSettings.getCom.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED java.lang.String()) {
            HardwareButtonHelperKt.setupHardwareTrigger(this, barcodeFindViewSettings.getCom.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE java.lang.String(), new a(this));
        }
        this.textForPointAtBarcodesToSearchHint = BarcodeFindViewDefaults.getDefaultTextForPointAtBarcodesToSearchHint();
        this.textForAllItemsFoundSuccessfullyHint = BarcodeFindViewDefaults.getDefaultTextForAllItemsFoundSuccessfullyHint();
        this.textForMoveCloserToBarcodesHint = BarcodeFindViewDefaults.getDefaultTextForMoveCloserToBarcodesHint();
        this.textForTapShutterToPauseScreenHint = BarcodeFindViewDefaults.getDefaultTextForTapShutterToPauseScreenHint();
        this.textForTapShutterToResumeSearchHint = BarcodeFindViewDefaults.getDefaultTextForTapShutterToResumeSearchHint();
        this.textForItemListUpdatedHint = BarcodeFindViewDefaults.getDefaultTextForItemListUpdatedHint();
        this.textForItemListUpdatedWhenPausedHint = BarcodeFindViewDefaults.getDefaultTextForItemListUpdatedWhenPausedHint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e.m();
    }

    public static final void access$onHardwareKeyEvent(BarcodeFindView barcodeFindView, HardwareKeyEvent hardwareKeyEvent) {
        barcodeFindView.getClass();
        if (WhenMappings.$EnumSwitchMapping$0[hardwareKeyEvent.ordinal()] == 1) {
            barcodeFindView.f.b();
        }
    }

    public static final boolean getHardwareTriggerSupported() {
        return INSTANCE.getHardwareTriggerSupported();
    }

    @JvmStatic
    public static final BarcodeFindView newInstance(View view, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind) {
        return INSTANCE.newInstance(view, dataCaptureContext, barcodeFind);
    }

    public final Camera getCamera() {
        return this.cameraManager.a();
    }

    /* renamed from: getCameraManager$scandit_barcode_capture, reason: from getter */
    public final BarcodeFindCameraManager getCameraManager() {
        return this.cameraManager;
    }

    /* renamed from: getSettings$scandit_barcode_capture, reason: from getter */
    public final BarcodeFindViewSettings getSettings() {
        return this.settings;
    }

    public final boolean getShouldShowCarousel() {
        return this.e.h();
    }

    public final boolean getShouldShowFinishButton() {
        return this.e.e();
    }

    public final boolean getShouldShowHints() {
        return this.e.getShouldShowHints();
    }

    public final boolean getShouldShowPauseButton() {
        return this.e.b();
    }

    public final boolean getShouldShowProgressBar() {
        return this.e.p();
    }

    public final boolean getShouldShowTorchControl() {
        return this.e.c();
    }

    public final boolean getShouldShowUserGuidanceView() {
        return this.e.j();
    }

    public final boolean getShouldShowZoomControl() {
        return this.f.e();
    }

    public final String getTextForAllItemsFoundSuccessfullyHint() {
        return this.textForAllItemsFoundSuccessfullyHint;
    }

    public final String getTextForCollapseCardsButton() {
        return this.e.q();
    }

    public final String getTextForItemListUpdatedHint() {
        return this.textForItemListUpdatedHint;
    }

    public final String getTextForItemListUpdatedWhenPausedHint() {
        return this.textForItemListUpdatedWhenPausedHint;
    }

    public final String getTextForMoveCloserToBarcodesHint() {
        return this.textForMoveCloserToBarcodesHint;
    }

    public final String getTextForPointAtBarcodesToSearchHint() {
        return this.textForPointAtBarcodesToSearchHint;
    }

    public final String getTextForTapShutterToPauseScreenHint() {
        return this.textForTapShutterToPauseScreenHint;
    }

    public final String getTextForTapShutterToResumeSearchHint() {
        return this.textForTapShutterToResumeSearchHint;
    }

    public final Anchor getTorchControlPosition() {
        return this.e.i();
    }

    public final void onPause() {
        this.d.removeListener(this.i);
        this.b.removeSearchedItemsUpdateListener$scandit_barcode_capture(this.f);
        this.b.removeOverlayListener$scandit_barcode_capture(this.i);
        this.f.d();
        this.a.removeMode(this.b);
        this.d.removeOverlay(this.g);
    }

    public final void onResume() {
        this.d.addOverlay(this.g);
        this.a.setMode(this.b);
        this.f.f();
        this.b.addOverlayListener$scandit_barcode_capture(this.i);
        this.b.addSearchedItemsUpdateListener$scandit_barcode_capture(this.f);
        this.d.addListener(this.i);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.find.ui.BarcodeFindView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeFindView.a(BarcodeFindView.this);
            }
        });
    }

    public final void pauseSearching() {
        this.f.a();
    }

    public final void setListener(BarcodeFindViewUiListener listener) {
        this.f.a(listener);
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        this.e.k();
    }

    public final void setShouldShowCarousel(boolean z) {
        this.e.d(z);
    }

    public final void setShouldShowFinishButton(boolean z) {
        this.e.e(z);
    }

    public final void setShouldShowHints(boolean z) {
        this.e.setShouldShowHints(z);
    }

    public final void setShouldShowPauseButton(boolean z) {
        this.e.a(z);
    }

    public final void setShouldShowProgressBar(boolean z) {
        this.e.b(z);
    }

    public final void setShouldShowTorchControl(boolean z) {
        this.e.c(z);
    }

    public final void setShouldShowUserGuidanceView(boolean z) {
        this.e.h(z);
    }

    public final void setShouldShowZoomControl(boolean z) {
        this.f.a(z);
    }

    public final void setTextForAllItemsFoundSuccessfullyHint(String str) {
        this.textForAllItemsFoundSuccessfullyHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_all_found);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.e(str);
    }

    public final void setTextForCollapseCardsButton(String str) {
        this.e.d(str);
    }

    public final void setTextForItemListUpdatedHint(String str) {
        this.textForItemListUpdatedHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_item_list_updated);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.b(str);
    }

    public final void setTextForItemListUpdatedWhenPausedHint(String str) {
        this.textForItemListUpdatedWhenPausedHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_item_list_updated_when_paused);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.g(str);
    }

    public final void setTextForMoveCloserToBarcodesHint(String str) {
        this.textForMoveCloserToBarcodesHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_move_closer);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.a(str);
    }

    public final void setTextForPointAtBarcodesToSearchHint(String str) {
        this.textForPointAtBarcodesToSearchHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_point_at_barcodes);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.d(str);
    }

    public final void setTextForTapShutterToPauseScreenHint(String str) {
        this.textForTapShutterToPauseScreenHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_tap_shutter_to_pause);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.f(str);
    }

    public final void setTextForTapShutterToResumeSearchHint(String str) {
        this.textForTapShutterToResumeSearchHint = str;
        BarcodeFindBasicOverlay barcodeFindBasicOverlay = this.g;
        if (str == null) {
            str = getContext().getString(R.string.sc_barcode_find_guidance_tap_shutter_to_resume);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        barcodeFindBasicOverlay.c(str);
    }

    public final void setTorchControlPosition(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "<set-?>");
        this.e.a(anchor);
    }

    public final void startSearching() {
        this.f.h();
    }

    public final void stopSearching() {
        this.f.g();
    }

    @JvmStatic
    public static final BarcodeFindView newInstance(View view, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings barcodeFindViewSettings) {
        return INSTANCE.newInstance(view, dataCaptureContext, barcodeFind, barcodeFindViewSettings);
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ/\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\t\u0010\rJ7\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\t\u0010\u0010J;\u0010\t\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\t\u0010\u0013J_\u0010 \u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0001¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010&\u001a\u00020!8FX\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010%\u001a\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView$Companion;", "", "Landroid/view/View;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "barcodeFind", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "newInstance", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;", "settings", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "Lcom/scandit/datacapture/core/source/CameraSettings;", "cameraSettings", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "dataCaptureView", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "Lcom/scandit/datacapture/barcode/internal/module/find/ui/BarcodeFindViewLayout;", "uiLayout", "Lcom/scandit/datacapture/barcode/internal/module/find/ui/BarcodeFindViewPresenter;", "presenter", "Lcom/scandit/datacapture/barcode/internal/module/find/ui/overlay/BarcodeFindGuidanceHandler;", "guidanceHandler", "Lcom/scandit/datacapture/barcode/internal/module/find/ui/overlay/BarcodeFindBasicOverlay;", "basicOverlay", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/BarcodeFindCameraManager;", "cameraManager", "forTesting$scandit_barcode_capture", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewSettings;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/internal/module/find/ui/BarcodeFindViewLayout;Lcom/scandit/datacapture/barcode/internal/module/find/ui/BarcodeFindViewPresenter;Lcom/scandit/datacapture/barcode/internal/module/find/ui/overlay/BarcodeFindGuidanceHandler;Lcom/scandit/datacapture/barcode/internal/module/find/ui/overlay/BarcodeFindBasicOverlay;Lcom/scandit/datacapture/barcode/internal/module/find/capture/BarcodeFindCameraManager;)Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "forTesting", "", "getHardwareTriggerSupported", "()Z", "getHardwareTriggerSupported$annotations", "()V", "hardwareTriggerSupported", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        private static BarcodeFindView a(Context context, DataCaptureView dataCaptureView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings barcodeFindViewSettings, CameraSettings cameraSettings) {
            ScreenSizeCategoryHandler impl;
            ScreenSizeCategory forcedScreenCategory = barcodeFindViewSettings.getForcedScreenCategory();
            if (forcedScreenCategory == null || (impl = ScreenSizeCategoryHandler.INSTANCE.forced(forcedScreenCategory)) == null) {
                impl = ScreenSizeCategoryHandler.INSTANCE.impl();
            }
            u0 u0Var = new u0(context, barcodeFindViewSettings, dataCaptureView, impl, PreviewResolutionRatioHandler.INSTANCE.impl());
            com.scandit.datacapture.barcode.internal.module.find.capture.c cVar = new com.scandit.datacapture.barcode.internal.module.find.capture.c(cameraSettings, new c(dataCaptureView, u0Var));
            return new BarcodeFindView(context, dataCaptureContext, barcodeFind, barcodeFindViewSettings, dataCaptureView, u0Var, new x0(barcodeFind, u0Var, cVar), new com.scandit.datacapture.barcode.internal.module.find.ui.overlay.f(u0Var), new com.scandit.datacapture.barcode.internal.module.find.ui.overlay.b(context, barcodeFind), cVar, null);
        }

        @JvmStatic
        public static /* synthetic */ void getHardwareTriggerSupported$annotations() {
        }

        public final BarcodeFindView forTesting$scandit_barcode_capture(View parentView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings settings, DataCaptureView dataCaptureView, BarcodeFindViewLayout uiLayout, BarcodeFindViewPresenter presenter, BarcodeFindGuidanceHandler guidanceHandler, BarcodeFindBasicOverlay basicOverlay, BarcodeFindCameraManager cameraManager) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
            Intrinsics.checkNotNullParameter(uiLayout, "uiLayout");
            Intrinsics.checkNotNullParameter(presenter, "presenter");
            Intrinsics.checkNotNullParameter(guidanceHandler, "guidanceHandler");
            Intrinsics.checkNotNullParameter(basicOverlay, "basicOverlay");
            Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
            Context context = parentView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            BarcodeFindView barcodeFindView = new BarcodeFindView(context, dataCaptureContext, barcodeFind, settings, dataCaptureView, uiLayout, presenter, guidanceHandler, basicOverlay, cameraManager, null);
            ((ViewGroup) parentView).addView(barcodeFindView, new RelativeLayout.LayoutParams(-1, -1));
            return barcodeFindView;
        }

        public final boolean getHardwareTriggerSupported() {
            return HardwareButtonHelperKt.isHardwareTriggerSupported();
        }

        @JvmStatic
        public final BarcodeFindView newInstance(View parentView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
            return newInstance(parentView, dataCaptureContext, barcodeFind, new BarcodeFindViewSettings(0, 0, false, false, 15, (DefaultConstructorMarker) null), BarcodeFind.INSTANCE.getRecommendedCameraSettings());
        }

        @JvmStatic
        public final BarcodeFindView newInstance(View parentView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings settings) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return newInstance(parentView, dataCaptureContext, barcodeFind, settings, BarcodeFind.INSTANCE.getRecommendedCameraSettings());
        }

        @JvmStatic
        public final BarcodeFindView newInstance(View parentView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings settings, CameraSettings cameraSettings) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            Context context = parentView.getContext();
            DataCaptureView.Companion companion = DataCaptureView.INSTANCE;
            Intrinsics.checkNotNull(context);
            BarcodeFindView a = a(context, companion.newInstance(context, dataCaptureContext), dataCaptureContext, barcodeFind, settings, cameraSettings);
            ((ViewGroup) parentView).addView(a, new RelativeLayout.LayoutParams(-1, -1));
            return a;
        }

        @JvmStatic
        public final BarcodeFindView newInstance(DataCaptureView dataCaptureView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings settings, CameraSettings cameraSettings) {
            Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            Context context = dataCaptureView.getContext();
            ViewParent parent = dataCaptureView.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(dataCaptureView);
            }
            Intrinsics.checkNotNull(context);
            BarcodeFindView a = a(context, dataCaptureView, dataCaptureContext, barcodeFind, settings, cameraSettings);
            a.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            return a;
        }
    }

    @JvmStatic
    public static final BarcodeFindView newInstance(View view, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings barcodeFindViewSettings, CameraSettings cameraSettings) {
        return INSTANCE.newInstance(view, dataCaptureContext, barcodeFind, barcodeFindViewSettings, cameraSettings);
    }

    @JvmStatic
    public static final BarcodeFindView newInstance(DataCaptureView dataCaptureView, DataCaptureContext dataCaptureContext, BarcodeFind barcodeFind, BarcodeFindViewSettings barcodeFindViewSettings, CameraSettings cameraSettings) {
        return INSTANCE.newInstance(dataCaptureView, dataCaptureContext, barcodeFind, barcodeFindViewSettings, cameraSettings);
    }
}
