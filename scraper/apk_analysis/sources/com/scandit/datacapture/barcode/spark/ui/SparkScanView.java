package com.scandit.datacapture.barcode.spark.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareButtonHelperKt;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareKeyEvent;
import com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanEnableDisableListener;
import com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanModeViewListener;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.E;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer;
import com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.A;
import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview;
import com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanBarcodeFeedback;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanFeedbackDelegate;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import io.sentry.protocol.SentryThread;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\bm\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 »\u00012\u00020\u0001:\u0002»\u0001By\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0014¢\u0006\u0004\b!\u0010\"J\u0019\u0010%\u001a\u00020 2\b\u0010$\u001a\u0004\u0018\u00010#H\u0014¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020 ¢\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020 ¢\u0006\u0004\b)\u0010(J\u0017\u0010,\u001a\u00020 2\b\u0010+\u001a\u0004\u0018\u00010*¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020 ¢\u0006\u0004\b.\u0010(J\r\u0010/\u001a\u00020 ¢\u0006\u0004\b/\u0010(J\u0015\u00102\u001a\u00020 2\u0006\u00101\u001a\u000200¢\u0006\u0004\b2\u00103J\r\u00105\u001a\u000204¢\u0006\u0004\b5\u00106J\u0017\u00109\u001a\u00020 2\b\b\u0001\u00108\u001a\u000207¢\u0006\u0004\b9\u0010:R$\u0010B\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010I\u001a\u00020C2\u0006\u0010D\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR$\u0010L\u001a\u00020C2\u0006\u0010D\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bJ\u0010F\"\u0004\bK\u0010HR$\u0010O\u001a\u00020C2\u0006\u0010D\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bM\u0010F\"\u0004\bN\u0010HR$\u0010R\u001a\u00020C2\u0006\u0010D\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bP\u0010F\"\u0004\bQ\u0010HR*\u0010W\u001a\u00020C2\u0006\u0010S\u001a\u00020C8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\bV\u0010(\u001a\u0004\bT\u0010F\"\u0004\bU\u0010HR+\u0010]\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bY\u0010F\"\u0004\bZ\u0010H*\u0004\b[\u0010\\R*\u0010a\u001a\u00020C2\u0006\u0010S\u001a\u00020C8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b`\u0010(\u001a\u0004\b^\u0010F\"\u0004\b_\u0010HR+\u0010e\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bb\u0010F\"\u0004\bc\u0010H*\u0004\bd\u0010\\R+\u0010i\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bf\u0010F\"\u0004\bg\u0010H*\u0004\bh\u0010\\R+\u0010m\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bj\u0010F\"\u0004\bk\u0010H*\u0004\bl\u0010\\R+\u0010q\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bn\u0010F\"\u0004\bo\u0010H*\u0004\bp\u0010\\R+\u0010u\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\br\u0010F\"\u0004\bs\u0010H*\u0004\bt\u0010\\R.\u0010z\u001a\u0004\u0018\u0001002\b\u0010S\u001a\u0004\u0018\u0001008F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\by\u0010(\u001a\u0004\bv\u0010w\"\u0004\bx\u00103R.\u0010~\u001a\u0004\u0018\u0001002\b\u0010S\u001a\u0004\u0018\u0001008F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b}\u0010(\u001a\u0004\b{\u0010w\"\u0004\b|\u00103R1\u0010\u0082\u0001\u001a\u0004\u0018\u0001002\b\u0010S\u001a\u0004\u0018\u0001008F@FX\u0087\u000e¢\u0006\u0014\u0012\u0005\b\u0081\u0001\u0010(\u001a\u0004\b\u007f\u0010w\"\u0005\b\u0080\u0001\u00103R2\u0010\u0086\u0001\u001a\u0004\u0018\u0001002\b\u0010S\u001a\u0004\u0018\u0001008F@FX\u0087\u000e¢\u0006\u0015\u0012\u0005\b\u0085\u0001\u0010(\u001a\u0005\b\u0083\u0001\u0010w\"\u0005\b\u0084\u0001\u00103R4\u0010\u008c\u0001\u001a\u0004\u0018\u0001072\b\u0010S\u001a\u0004\u0018\u0001078F@FX\u0087\u000e¢\u0006\u0017\u0012\u0005\b\u008b\u0001\u0010(\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R4\u0010\u0090\u0001\u001a\u0004\u0018\u0001072\b\u0010S\u001a\u0004\u0018\u0001078F@FX\u0087\u000e¢\u0006\u0017\u0012\u0005\b\u008f\u0001\u0010(\u001a\u0006\b\u008d\u0001\u0010\u0088\u0001\"\u0006\b\u008e\u0001\u0010\u008a\u0001R4\u0010\u0094\u0001\u001a\u0004\u0018\u0001072\b\u0010S\u001a\u0004\u0018\u0001078F@FX\u0087\u000e¢\u0006\u0017\u0012\u0005\b\u0093\u0001\u0010(\u001a\u0006\b\u0091\u0001\u0010\u0088\u0001\"\u0006\b\u0092\u0001\u0010\u008a\u0001R5\u0010\u0098\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u0095\u0001\u0010\u0088\u0001\"\u0006\b\u0096\u0001\u0010\u008a\u0001*\u0005\b\u0097\u0001\u0010\\R5\u0010\u009c\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u0099\u0001\u0010\u0088\u0001\"\u0006\b\u009a\u0001\u0010\u008a\u0001*\u0005\b\u009b\u0001\u0010\\R5\u0010 \u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u009d\u0001\u0010\u0088\u0001\"\u0006\b\u009e\u0001\u0010\u008a\u0001*\u0005\b\u009f\u0001\u0010\\R5\u0010¤\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b¡\u0001\u0010\u0088\u0001\"\u0006\b¢\u0001\u0010\u008a\u0001*\u0005\b£\u0001\u0010\\R5\u0010¨\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b¥\u0001\u0010\u0088\u0001\"\u0006\b¦\u0001\u0010\u008a\u0001*\u0005\b§\u0001\u0010\\R5\u0010¬\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b©\u0001\u0010\u0088\u0001\"\u0006\bª\u0001\u0010\u008a\u0001*\u0005\b«\u0001\u0010\\R5\u0010°\u0001\u001a\u0004\u0018\u0001072\b\u0010X\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u00ad\u0001\u0010\u0088\u0001\"\u0006\b®\u0001\u0010\u008a\u0001*\u0005\b¯\u0001\u0010\\R6\u0010¶\u0001\u001a\u0005\u0018\u00010±\u00012\t\u0010X\u001a\u0005\u0018\u00010±\u00018F@FX\u0086\u008e\u0002¢\u0006\u0016\u001a\u0006\b²\u0001\u0010³\u0001\"\u0005\b9\u0010´\u0001*\u0005\bµ\u0001\u0010\\R/\u0010º\u0001\u001a\u00020C2\u0006\u0010X\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b·\u0001\u0010F\"\u0005\b¸\u0001\u0010H*\u0005\b¹\u0001\u0010\\¨\u0006¼\u0001"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "Landroid/widget/RelativeLayout;", "Landroid/view/View;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "sparkScan", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;", "settings", "Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanStateManager;", "stateManager", "Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanViewCameraManager;", "cameraManager", "Lcom/scandit/datacapture/barcode/internal/module/spark/ui/minipreview/SparkScanViewMiniPreview;", "miniPreview", "Lcom/scandit/datacapture/barcode/internal/module/spark/ui/button/SparkScanViewCaptureButtonContainer;", "sparkCaptureViewScanButtonView", "Lcom/scandit/datacapture/barcode/internal/module/spark/ui/feedback/SparkScanFeedbackManager;", "feedbackManager", "Lcom/scandit/datacapture/barcode/internal/module/spark/ui/toast/SparkScanViewToastPresenter;", "toastPresenter", "Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanAnalyticsManager;", "analyticsManager", "Lcom/scandit/datacapture/barcode/spark/ui/PropertyPushSource;", "propertyPushSource", "<init>", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanStateManager;Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanViewCameraManager;Lcom/scandit/datacapture/barcode/internal/module/spark/ui/minipreview/SparkScanViewMiniPreview;Lcom/scandit/datacapture/barcode/internal/module/spark/ui/button/SparkScanViewCaptureButtonContainer;Lcom/scandit/datacapture/barcode/internal/module/spark/ui/feedback/SparkScanFeedbackManager;Lcom/scandit/datacapture/barcode/internal/module/spark/ui/toast/SparkScanViewToastPresenter;Lcom/scandit/datacapture/barcode/internal/module/spark/data/SparkScanAnalyticsManager;Lcom/scandit/datacapture/barcode/spark/ui/PropertyPushSource;)V", "Landroid/os/Parcelable;", "onSaveInstanceState", "()Landroid/os/Parcelable;", SentryThread.JsonKeys.STATE, "", "onRestoreInstanceState", "(Landroid/os/Parcelable;)V", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "onPause", "()V", "onResume", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanViewUiListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setListener", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScanViewUiListener;)V", "startScanning", "pauseScanning", "", "text", "showToast", "(Ljava/lang/String;)V", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanScanningMode;", "getScanningMode", "()Lcom/scandit/datacapture/barcode/spark/ui/SparkScanScanningMode;", "", "resourceId", "setTriggerButtonImage", "(I)V", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanFeedbackDelegate;", "k", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanFeedbackDelegate;", "getFeedbackDelegate", "()Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanFeedbackDelegate;", "setFeedbackDelegate", "(Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanFeedbackDelegate;)V", "feedbackDelegate", "", "value", "getZoomSwitchControlVisible", "()Z", "setZoomSwitchControlVisible", "(Z)V", "zoomSwitchControlVisible", "getPreviewSizeControlVisible", "setPreviewSizeControlVisible", "previewSizeControlVisible", "getPreviewCloseControlVisible", "setPreviewCloseControlVisible", "previewCloseControlVisible", "getTorchControlVisible", "setTorchControlVisible", "torchControlVisible", "<anonymous parameter 0>", "getTorchButtonVisible", "setTorchButtonVisible", "getTorchButtonVisible$annotations", "torchButtonVisible", "<set-?>", "getScanningBehaviorButtonVisible", "setScanningBehaviorButtonVisible", "getScanningBehaviorButtonVisible$delegate", "(Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;)Ljava/lang/Object;", "scanningBehaviorButtonVisible", "getHandModeButtonVisible", "setHandModeButtonVisible", "getHandModeButtonVisible$annotations", "handModeButtonVisible", "getBarcodeCountButtonVisible", "setBarcodeCountButtonVisible", "getBarcodeCountButtonVisible$delegate", "barcodeCountButtonVisible", "getBarcodeFindButtonVisible", "setBarcodeFindButtonVisible", "getBarcodeFindButtonVisible$delegate", "barcodeFindButtonVisible", "getTargetModeButtonVisible", "setTargetModeButtonVisible", "getTargetModeButtonVisible$delegate", "targetModeButtonVisible", "getLabelCaptureButtonVisible", "setLabelCaptureButtonVisible", "getLabelCaptureButtonVisible$delegate", "labelCaptureButtonVisible", "getCameraSwitchButtonVisible", "setCameraSwitchButtonVisible", "getCameraSwitchButtonVisible$delegate", "cameraSwitchButtonVisible", "getStopCapturingText", "()Ljava/lang/String;", "setStopCapturingText", "getStopCapturingText$annotations", "stopCapturingText", "getStartCapturingText", "setStartCapturingText", "getStartCapturingText$annotations", "startCapturingText", "getResumeCapturingText", "setResumeCapturingText", "getResumeCapturingText$annotations", "resumeCapturingText", "getScanningCapturingText", "setScanningCapturingText", "getScanningCapturingText$annotations", "scanningCapturingText", "getCaptureButtonActiveBackgroundColor", "()Ljava/lang/Integer;", "setCaptureButtonActiveBackgroundColor", "(Ljava/lang/Integer;)V", "getCaptureButtonActiveBackgroundColor$annotations", "captureButtonActiveBackgroundColor", "getCaptureButtonBackgroundColor", "setCaptureButtonBackgroundColor", "getCaptureButtonBackgroundColor$annotations", "captureButtonBackgroundColor", "getCaptureButtonTintColor", "setCaptureButtonTintColor", "getCaptureButtonTintColor$annotations", "captureButtonTintColor", "getTriggerButtonCollapsedColor", "setTriggerButtonCollapsedColor", "getTriggerButtonCollapsedColor$delegate", "triggerButtonCollapsedColor", "getTriggerButtonExpandedColor", "setTriggerButtonExpandedColor", "getTriggerButtonExpandedColor$delegate", "triggerButtonExpandedColor", "getTriggerButtonAnimationColor", "setTriggerButtonAnimationColor", "getTriggerButtonAnimationColor$delegate", "triggerButtonAnimationColor", "getTriggerButtonTintColor", "setTriggerButtonTintColor", "getTriggerButtonTintColor$delegate", "triggerButtonTintColor", "getToolbarBackgroundColor", "setToolbarBackgroundColor", "getToolbarBackgroundColor$delegate", "toolbarBackgroundColor", "getToolbarIconActiveTintColor", "setToolbarIconActiveTintColor", "getToolbarIconActiveTintColor$delegate", "toolbarIconActiveTintColor", "getToolbarIconInactiveTintColor", "setToolbarIconInactiveTintColor", "getToolbarIconInactiveTintColor$delegate", "toolbarIconInactiveTintColor", "Landroid/graphics/Bitmap;", "getTriggerButtonImage", "()Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;)V", "getTriggerButtonImage$delegate", "triggerButtonImage", "getTriggerButtonVisible", "setTriggerButtonVisible", "getTriggerButtonVisible$delegate", "triggerButtonVisible", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class SparkScanView extends RelativeLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DataCaptureContext a;
    private final SparkScan b;
    private final SparkScanStateManager c;
    private final SparkScanViewCameraManager d;
    private final SparkScanViewMiniPreview e;
    private final SparkScanViewCaptureButtonContainer f;
    private final SparkScanFeedbackManager g;
    private final SparkScanViewToastPresenter h;
    private final SparkScanAnalyticsManager i;
    private final PropertyPushSource j;

    /* renamed from: k, reason: from kotlin metadata */
    private SparkScanFeedbackDelegate feedbackDelegate;
    private final SparkScanViewPresenter l;
    private final SparkScanView$sparkScanCameraStartListener$1 m;
    private final SparkScanView$sparkScanEnableListener$1 n;
    private int o;
    private final SparkScanView$orientationEventListener$1 p;
    private final SparkScanView$modeViewListener$1 q;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J1\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0012\u001a\u00020\r8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView$Companion;", "", "Landroid/view/View;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "sparkScan", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;", "settings", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "newInstance", "(Landroid/view/View;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;)Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "", "getHardwareTriggerSupported", "()Z", "getHardwareTriggerSupported$annotations", "()V", "hardwareTriggerSupported", "", "SUPER_STATE_KEY", "Ljava/lang/String;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getHardwareTriggerSupported$annotations() {
        }

        public static /* synthetic */ SparkScanView newInstance$default(Companion companion, View view, DataCaptureContext dataCaptureContext, SparkScan sparkScan, SparkScanViewSettings sparkScanViewSettings, int i, Object obj) {
            if ((i & 8) != 0) {
                sparkScanViewSettings = new SparkScanViewSettings();
            }
            return companion.newInstance(view, dataCaptureContext, sparkScan, sparkScanViewSettings);
        }

        public final boolean getHardwareTriggerSupported() {
            return HardwareButtonHelperKt.isHardwareTriggerSupported();
        }

        @JvmStatic
        public final SparkScanView newInstance(View parentView, DataCaptureContext dataCaptureContext, SparkScan sparkScan) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
            return newInstance$default(this, parentView, dataCaptureContext, sparkScan, null, 8, null);
        }

        @JvmStatic
        public final SparkScanView newInstance(View parentView, DataCaptureContext dataCaptureContext, SparkScan sparkScan, SparkScanViewSettings settings) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return new SparkScanView(parentView, dataCaptureContext, sparkScan, settings.clone$scandit_barcode_capture(), null, null, null, null, null, null, null, null, 4080, null);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HardwareKeyEvent.values().length];
            try {
                iArr[HardwareKeyEvent.CLICK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HardwareKeyEvent.LONG_PRESS_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HardwareKeyEvent.LONG_PRESS_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SparkScanView(android.view.View r17, com.scandit.datacapture.core.capture.DataCaptureContext r18, com.scandit.datacapture.barcode.spark.capture.SparkScan r19, com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings r20, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager r21, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager r22, com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview r23, com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer r24, com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager r25, com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter r26, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager r27, com.scandit.datacapture.barcode.spark.ui.PropertyPushSource r28, int r29, kotlin.jvm.internal.DefaultConstructorMarker r30) {
        /*
            r16 = this;
            r6 = r20
            r7 = r29
            r0 = r7 & 16
            if (r0 == 0) goto L20
            com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager r0 = new com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager
            com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal r1 = r19.get_sparkScanInternal()
            com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan r1 = r1.a()
            com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine r1 = r1.getStateMachine()
            java.lang.String r2 = "getStateMachine(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.<init>(r6, r1)
            r14 = r0
            goto L22
        L20:
            r14 = r21
        L22:
            r0 = r7 & 32
            if (r0 == 0) goto L3d
            com.scandit.datacapture.barcode.internal.module.spark.data.O r8 = new com.scandit.datacapture.barcode.internal.module.spark.data.O
            com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager$Companion r0 = com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager.INSTANCE
            com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager r3 = r0.getSharedInstance()
            com.scandit.datacapture.barcode.spark.capture.SparkScanSettings r4 = r19.getSparkScanSettings()
            r0 = r8
            r1 = r18
            r2 = r14
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5)
            r15 = r8
            goto L3f
        L3d:
            r15 = r22
        L3f:
            r0 = r7 & 64
            java.lang.String r1 = "getContext(...)"
            if (r0 == 0) goto L59
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.F r0 = new com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.F
            android.content.Context r9 = r17.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            r8 = r0
            r10 = r18
            r11 = r19
            r12 = r14
            r13 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L5b
        L59:
            r8 = r23
        L5b:
            r0 = r7 & 128(0x80, float:1.8E-43)
            if (r0 == 0) goto L6d
            com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer r0 = new com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer
            android.content.Context r2 = r17.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            r0.<init>(r2, r14)
            r9 = r0
            goto L6f
        L6d:
            r9 = r24
        L6f:
            r0 = r7 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L7a
            com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager r0 = new com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager
            r0.<init>(r6, r14)
            r10 = r0
            goto L7c
        L7a:
            r10 = r25
        L7c:
            r0 = r7 & 512(0x200, float:7.17E-43)
            if (r0 == 0) goto L87
            com.scandit.datacapture.barcode.internal.module.spark.ui.toast.s r0 = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.s
            r0.<init>(r8, r6)
            r11 = r0
            goto L89
        L87:
            r11 = r26
        L89:
            r0 = r7 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L94
            com.scandit.datacapture.barcode.internal.module.spark.data.g r0 = new com.scandit.datacapture.barcode.internal.module.spark.data.g
            r0.<init>(r14)
            r12 = r0
            goto L96
        L94:
            r12 = r27
        L96:
            r0 = r7 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto La1
            com.scandit.datacapture.barcode.spark.ui.PropertyPushSource r0 = new com.scandit.datacapture.barcode.spark.ui.PropertyPushSource
            r0.<init>(r12)
            r13 = r0
            goto La3
        La1:
            r13 = r28
        La3:
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r14
            r6 = r15
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.spark.ui.SparkScanView.<init>(android.view.View, com.scandit.datacapture.core.capture.DataCaptureContext, com.scandit.datacapture.barcode.spark.capture.SparkScan, com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager, com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview, com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCaptureButtonContainer, com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.SparkScanFeedbackManager, com.scandit.datacapture.barcode.internal.module.spark.ui.toast.SparkScanViewToastPresenter, com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager, com.scandit.datacapture.barcode.spark.ui.PropertyPushSource, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(SparkScanView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l.M();
    }

    public static final boolean access$didRotate180Degrees(SparkScanView sparkScanView, int i) {
        return Math.abs(i - sparkScanView.o) == 2;
    }

    public static final void access$onHardwareKeyEvent(SparkScanView sparkScanView, HardwareKeyEvent hardwareKeyEvent) {
        sparkScanView.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[hardwareKeyEvent.ordinal()];
        if (i == 1) {
            sparkScanView.l.A();
        } else if (i == 2) {
            sparkScanView.l.E();
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            sparkScanView.l.D();
        }
    }

    @Deprecated(message = "Not used anymore.")
    public static /* synthetic */ void getCaptureButtonActiveBackgroundColor$annotations() {
    }

    @Deprecated(message = "Use triggerButtonCollapsedColor and triggerButtonExpandedColor instead.")
    public static /* synthetic */ void getCaptureButtonBackgroundColor$annotations() {
    }

    @Deprecated(message = "Not used anymore.", replaceWith = @ReplaceWith(expression = "triggerButtonTintColor", imports = {}))
    public static /* synthetic */ void getCaptureButtonTintColor$annotations() {
    }

    @Deprecated(message = "There is no hand mode anymore")
    public static /* synthetic */ void getHandModeButtonVisible$annotations() {
    }

    public static final boolean getHardwareTriggerSupported() {
        return INSTANCE.getHardwareTriggerSupported();
    }

    @Deprecated(message = "The trigger button no longer displays text.")
    public static /* synthetic */ void getResumeCapturingText$annotations() {
    }

    @Deprecated(message = "The trigger button no longer displays text.")
    public static /* synthetic */ void getScanningCapturingText$annotations() {
    }

    @Deprecated(message = "The trigger button no longer displays text.")
    public static /* synthetic */ void getStartCapturingText$annotations() {
    }

    @Deprecated(message = "The trigger button no longer displays text.")
    public static /* synthetic */ void getStopCapturingText$annotations() {
    }

    @Deprecated(message = "The torch button has been moved to the mini preview", replaceWith = @ReplaceWith(expression = "torchControlVisible", imports = {}))
    public static /* synthetic */ void getTorchButtonVisible$annotations() {
    }

    @JvmStatic
    public static final SparkScanView newInstance(View view, DataCaptureContext dataCaptureContext, SparkScan sparkScan) {
        return INSTANCE.newInstance(view, dataCaptureContext, sparkScan);
    }

    public final boolean getBarcodeCountButtonVisible() {
        return this.e.u();
    }

    public final boolean getBarcodeFindButtonVisible() {
        return this.e.y();
    }

    public final boolean getCameraSwitchButtonVisible() {
        return this.e.d();
    }

    public final Integer getCaptureButtonActiveBackgroundColor() {
        return null;
    }

    public final Integer getCaptureButtonBackgroundColor() {
        return null;
    }

    public final Integer getCaptureButtonTintColor() {
        return null;
    }

    public final SparkScanFeedbackDelegate getFeedbackDelegate() {
        return this.feedbackDelegate;
    }

    public final boolean getHandModeButtonVisible() {
        return false;
    }

    public final boolean getLabelCaptureButtonVisible() {
        return this.e.e();
    }

    public final boolean getPreviewCloseControlVisible() {
        return this.e.v();
    }

    public final boolean getPreviewSizeControlVisible() {
        return this.e.n();
    }

    public final String getResumeCapturingText() {
        return null;
    }

    public final boolean getScanningBehaviorButtonVisible() {
        return this.e.z();
    }

    public final String getScanningCapturingText() {
        return null;
    }

    public final SparkScanScanningMode getScanningMode() {
        return this.c.s();
    }

    public final String getStartCapturingText() {
        return null;
    }

    public final String getStopCapturingText() {
        return null;
    }

    public final boolean getTargetModeButtonVisible() {
        return this.e.p();
    }

    public final Integer getToolbarBackgroundColor() {
        return this.e.a();
    }

    public final Integer getToolbarIconActiveTintColor() {
        return this.e.t();
    }

    public final Integer getToolbarIconInactiveTintColor() {
        return this.e.i();
    }

    public final boolean getTorchButtonVisible() {
        return false;
    }

    public final boolean getTorchControlVisible() {
        return this.e.q();
    }

    public final Integer getTriggerButtonAnimationColor() {
        return this.f.a();
    }

    public final Integer getTriggerButtonCollapsedColor() {
        return this.f.b();
    }

    public final Integer getTriggerButtonExpandedColor() {
        return this.f.c();
    }

    public final Bitmap getTriggerButtonImage() {
        return this.f.d();
    }

    public final Integer getTriggerButtonTintColor() {
        return this.f.e();
    }

    public final boolean getTriggerButtonVisible() {
        return this.l.c();
    }

    public final boolean getZoomSwitchControlVisible() {
        return this.e.s();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        getHandler().post(new Runnable() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SparkScanView.a(SparkScanView.this);
            }
        });
        super.onConfigurationChanged(newConfig);
    }

    public final void onPause() {
        this.l.F();
        disable();
        this.b._removeEnableDisableListener$scandit_barcode_capture(this.n);
        this.b.set_sparkScanModeViewListener$scandit_barcode_capture(null);
        this.b.setDataCaptureContext$scandit_barcode_capture(null);
        this.b.setPropertyPushSource$scandit_barcode_capture(null);
        this.d.a((SparkScanView$sparkScanCameraStartListener$1) null);
        setOnClickListener(null);
        removeAllViews();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        Object parcelable;
        Intrinsics.checkNotNull(state, "null cannot be cast to non-null type android.os.Bundle");
        Bundle bundle = (Bundle) state;
        this.l.a(bundle);
        if (Build.VERSION.SDK_INT < 33) {
            super.onRestoreInstanceState(bundle.getParcelable("super_state_key"));
        } else {
            parcelable = bundle.getParcelable("super_state_key", Parcelable.class);
            super.onRestoreInstanceState((Parcelable) parcelable);
        }
    }

    public final void onResume() {
        enable();
        this.b.setDataCaptureContext$scandit_barcode_capture(this.a);
        this.d.a(this.m);
        SparkScan sparkScan = this.b;
        sparkScan._addEnableDisableListener$scandit_barcode_capture(this.n);
        sparkScan.set_sparkScanModeViewListener$scandit_barcode_capture(this.q);
        sparkScan._applyNewSettingsForScanningMode$scandit_barcode_capture(this.c.s());
        sparkScan.setPropertyPushSource$scandit_barcode_capture(this.j);
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SparkScanView.a(SparkScanView.this, view);
            }
        });
        this.g.a(this);
        this.f.a(this);
        this.f.a(this.l);
        this.e.a(this);
        this.e.a((A) this.l);
        this.l.b(this.b.isEnabled() ? SparkScanViewState.ACTIVE : SparkScanViewState.IDLE);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("super_state_key", onSaveInstanceState);
        bundle.putAll(this.l.G());
        return bundle;
    }

    public final void pauseScanning() {
        this.l.Q();
    }

    public final void setBarcodeCountButtonVisible(boolean z) {
        this.e.d(z);
    }

    public final void setBarcodeFindButtonVisible(boolean z) {
        this.e.g(z);
    }

    public final void setCameraSwitchButtonVisible(boolean z) {
        this.e.k(z);
    }

    public final void setCaptureButtonActiveBackgroundColor(Integer num) {
    }

    public final void setCaptureButtonBackgroundColor(Integer num) {
    }

    public final void setCaptureButtonTintColor(Integer num) {
    }

    public final void setFeedbackDelegate(SparkScanFeedbackDelegate sparkScanFeedbackDelegate) {
        this.feedbackDelegate = sparkScanFeedbackDelegate;
    }

    public final void setHandModeButtonVisible(boolean z) {
    }

    public final void setLabelCaptureButtonVisible(boolean z) {
        this.e.j(z);
    }

    public final void setListener(SparkScanViewUiListener listener) {
        this.l.a(listener);
    }

    public final void setPreviewCloseControlVisible(boolean z) {
        this.e.f(z);
    }

    public final void setPreviewSizeControlVisible(boolean z) {
        this.e.i(z);
        this.l.k(z);
    }

    public final void setResumeCapturingText(String str) {
    }

    public final void setScanningBehaviorButtonVisible(boolean z) {
        this.e.h(z);
    }

    public final void setScanningCapturingText(String str) {
    }

    public final void setStartCapturingText(String str) {
    }

    public final void setStopCapturingText(String str) {
    }

    public final void setTargetModeButtonVisible(boolean z) {
        this.e.e(z);
    }

    public final void setToolbarBackgroundColor(Integer num) {
        this.e.a(num);
    }

    public final void setToolbarIconActiveTintColor(Integer num) {
        this.e.c(num);
    }

    public final void setToolbarIconInactiveTintColor(Integer num) {
        this.e.b(num);
    }

    public final void setTorchButtonVisible(boolean z) {
    }

    public final void setTorchControlVisible(boolean z) {
        this.e.l(z);
        this.l.o(z);
    }

    public final void setTriggerButtonAnimationColor(Integer num) {
        this.f.a(num);
    }

    public final void setTriggerButtonCollapsedColor(Integer num) {
        this.f.b(num);
    }

    public final void setTriggerButtonExpandedColor(Integer num) {
        this.f.c(num);
    }

    public final void setTriggerButtonImage(Bitmap bitmap) {
        this.f.a(bitmap);
    }

    public final void setTriggerButtonTintColor(Integer num) {
        this.f.d(num);
    }

    public final void setTriggerButtonVisible(boolean z) {
        this.l.l(z);
    }

    public final void setZoomSwitchControlVisible(boolean z) {
        this.e.a(z);
        this.l.p(z);
    }

    public final void showToast(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.l.b(text);
    }

    public final void startScanning() {
        this.l.P();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(SparkScanView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l.L();
    }

    @JvmStatic
    public static final SparkScanView newInstance(View view, DataCaptureContext dataCaptureContext, SparkScan sparkScan, SparkScanViewSettings sparkScanViewSettings) {
        return INSTANCE.newInstance(view, dataCaptureContext, sparkScan, sparkScanViewSettings);
    }

    public final void setTriggerButtonImage(int resourceId) {
        SparkScanViewCaptureButtonContainer sparkScanViewCaptureButtonContainer = this.f;
        sparkScanViewCaptureButtonContainer.getClass();
        sparkScanViewCaptureButtonContainer.a(BitmapExtensionsKt.bitmapFromResource(resourceId));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.scandit.datacapture.barcode.spark.ui.SparkScanView$sparkScanEnableListener$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.scandit.datacapture.barcode.spark.ui.SparkScanView$modeViewListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.scandit.datacapture.barcode.spark.ui.SparkScanView$orientationEventListener$1] */
    public SparkScanView(View parentView, DataCaptureContext dataCaptureContext, SparkScan sparkScan, SparkScanViewSettings settings, SparkScanStateManager stateManager, SparkScanViewCameraManager cameraManager, SparkScanViewMiniPreview miniPreview, SparkScanViewCaptureButtonContainer sparkCaptureViewScanButtonView, SparkScanFeedbackManager feedbackManager, SparkScanViewToastPresenter toastPresenter, SparkScanAnalyticsManager analyticsManager, PropertyPushSource propertyPushSource) {
        super(parentView.getContext());
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        Intrinsics.checkNotNullParameter(miniPreview, "miniPreview");
        Intrinsics.checkNotNullParameter(sparkCaptureViewScanButtonView, "sparkCaptureViewScanButtonView");
        Intrinsics.checkNotNullParameter(feedbackManager, "feedbackManager");
        Intrinsics.checkNotNullParameter(toastPresenter, "toastPresenter");
        Intrinsics.checkNotNullParameter(analyticsManager, "analyticsManager");
        Intrinsics.checkNotNullParameter(propertyPushSource, "propertyPushSource");
        this.a = dataCaptureContext;
        this.b = sparkScan;
        this.c = stateManager;
        this.d = cameraManager;
        this.e = miniPreview;
        this.f = sparkCaptureViewScanButtonView;
        this.g = feedbackManager;
        this.h = toastPresenter;
        this.i = analyticsManager;
        this.j = propertyPushSource;
        this.feedbackDelegate = new SparkScanFeedbackDelegate() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$feedbackDelegate$1
            @Override // com.scandit.datacapture.barcode.spark.feedback.SparkScanFeedbackDelegate
            public SparkScanBarcodeFeedback getFeedbackForBarcode(Barcode barcode) {
                Intrinsics.checkNotNullParameter(barcode, "barcode");
                return new SparkScanBarcodeFeedback.Success(0, null, null, 7, null);
            }
        };
        NativeSparkScanStateMachine stateMachine = sparkScan.get_sparkScanInternal().a().getStateMachine();
        Intrinsics.checkNotNullExpressionValue(stateMachine, "getStateMachine(...)");
        this.l = new SparkScanViewPresenter(this, stateManager, sparkScan, settings, cameraManager, miniPreview, sparkCaptureViewScanButtonView, feedbackManager, toastPresenter, analyticsManager, stateMachine);
        this.m = new SparkScanView$sparkScanCameraStartListener$1(this);
        this.n = new SparkScanEnableDisableListener() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$sparkScanEnableListener$1
            @Override // com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanEnableDisableListener
            public void onEnabledChange(boolean isEnabled) {
                if (isEnabled) {
                    SparkScanView.this.startScanning();
                } else {
                    SparkScanView.this.pauseScanning();
                }
            }
        };
        final Context context = getContext();
        this.p = new OrientationEventListener(context) { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$orientationEventListener$1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                SparkScanViewPresenter sparkScanViewPresenter;
                Context context2 = SparkScanView.this.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                int rotation = ContextExtensionsKt.getRotation(context2);
                if (SparkScanView.access$didRotate180Degrees(SparkScanView.this, rotation)) {
                    sparkScanViewPresenter = SparkScanView.this.l;
                    sparkScanViewPresenter.M();
                }
                SparkScanView.this.o = rotation;
            }
        };
        this.q = new SparkScanModeViewListener() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$modeViewListener$1
            @Override // com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanModeViewListener
            public SparkScanScanningMode getScanningMode() {
                SparkScanStateManager sparkScanStateManager;
                sparkScanStateManager = SparkScanView.this.c;
                return sparkScanStateManager.s();
            }

            @Override // com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanModeViewListener
            public void onApplySettings(SparkScanSettings settings2) {
                SparkScanViewCameraManager sparkScanViewCameraManager;
                Intrinsics.checkNotNullParameter(settings2, "settings");
                sparkScanViewCameraManager = SparkScanView.this.d;
                sparkScanViewCameraManager.setBatterySavingMode(settings2.getBatterySaving());
            }
        };
        ((ViewGroup) parentView).addView(this, new ViewGroup.LayoutParams(-1, -1));
        setElevation(((Number) E.j.getValue()).floatValue());
        setOutlineProvider(null);
        bringToFront();
        if (settings.getCom.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED java.lang.String()) {
            HardwareButtonHelperKt.setupHardwareTrigger(this, settings.getCom.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE java.lang.String(), new a(this));
        }
        setId(E.c);
        setClipChildren(false);
    }
}
