package com.scandit.datacapture.barcode.count.ui.overlay;

import android.content.Context;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountNotInListActionSettings;
import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.barcode.filter.ui.overlay.NativeBarcodeFilterOverlaySettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.extensions.BrushExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.OverlayPreconditionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ProfilingOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.tealium.library.DataSources;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 »\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006¼\u0001»\u0001½\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u0097\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0005H\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0097\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0097\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018H\u0097\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018H\u0097\u0001¢\u0006\u0004\b\u001c\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018H\u0097\u0001¢\u0006\u0004\b\u001d\u0010\u001bJ\u0010\u0010\u001f\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b!\u0010 J\u0010\u0010\"\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b\"\u0010 J\u0010\u0010#\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b#\u0010 J\u0010\u0010$\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b$\u0010 J\u0010\u0010%\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b%\u0010 J\u0010\u0010&\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b&\u0010 J\u0010\u0010'\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b'\u0010 J\u0018\u0010)\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b)\u0010*J\u0018\u0010+\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b+\u0010*J\u0018\u0010,\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b,\u0010*J\u0018\u0010-\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b-\u0010*J\u0018\u0010.\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b.\u0010*J\u0018\u0010/\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b/\u0010*J\u0018\u00100\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b0\u0010*J\u0018\u00101\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b1\u0010*J\u0018\u00102\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b2\u0010*J\u0018\u00103\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b3\u0010*J\u0017\u00105\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u0007H\u0016¢\u0006\u0004\b5\u00106J\u0017\u0010;\u001a\u00020\u00122\u0006\u00108\u001a\u000207H\u0000¢\u0006\u0004\b9\u0010:J\u0017\u0010@\u001a\u00020\u00122\u0006\u0010=\u001a\u00020<H\u0000¢\u0006\u0004\b>\u0010?J\u000f\u0010A\u001a\u00020\u0012H\u0016¢\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0012H\u0014¢\u0006\u0004\bC\u0010BJ\u000f\u0010D\u001a\u00020\u0012H\u0014¢\u0006\u0004\bD\u0010BJ\r\u0010E\u001a\u00020\u0012¢\u0006\u0004\bE\u0010BJ\r\u0010F\u001a\u00020\u0012¢\u0006\u0004\bF\u0010BJ\r\u0010G\u001a\u00020\u0012¢\u0006\u0004\bG\u0010BJ\r\u0010H\u001a\u00020\u0012¢\u0006\u0004\bH\u0010BJ\u0015\u0010I\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bI\u0010\u001bJ\u0015\u0010J\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bJ\u0010\u001bJ\u0015\u0010K\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bK\u0010\u001bJ\u0015\u0010L\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bL\u0010\u001bJ\u0015\u0010M\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bM\u0010\u001bJ\u0015\u0010N\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\bN\u0010\u001bJ\u0015\u0010Q\u001a\u00020\u00122\u0006\u0010P\u001a\u00020O¢\u0006\u0004\bQ\u0010RJ\u0017\u0010W\u001a\u00020\u00122\u0006\u0010T\u001a\u00020SH\u0000¢\u0006\u0004\bU\u0010VJ\u0017\u0010\\\u001a\u00020\u00122\u0006\u0010Y\u001a\u00020XH\u0000¢\u0006\u0004\bZ\u0010[J\u000f\u0010]\u001a\u00020SH\u0016¢\u0006\u0004\b]\u0010^J\u0019\u0010`\u001a\u0004\u0018\u00010_2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b`\u0010aJ\u0019\u0010b\u001a\u0004\u0018\u00010_2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\bb\u0010aJ\u0019\u0010c\u001a\u0004\u0018\u00010_2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\bc\u0010aJ\u0019\u0010d\u001a\u0004\u0018\u00010_2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\bd\u0010aJ\u0019\u0010e\u001a\u0004\u0018\u00010_2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\be\u0010aJ\u0011\u0010f\u001a\u0004\u0018\u00010_H\u0016¢\u0006\u0004\bf\u0010gR\u001c\u0010k\u001a\u0004\u0018\u00010_8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bj\u0010gR\u001c\u0010n\u001a\u0004\u0018\u00010_8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bl\u0010i\u001a\u0004\bm\u0010gR\u001c\u0010q\u001a\u0004\u0018\u00010_8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bo\u0010i\u001a\u0004\bp\u0010gR\u001c\u0010t\u001a\u0004\u0018\u00010_8\u0016X\u0096\u0004¢\u0006\f\n\u0004\br\u0010i\u001a\u0004\bs\u0010gR\u001c\u0010w\u001a\u0004\u0018\u00010_8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bu\u0010i\u001a\u0004\bv\u0010gR$\u0010\u007f\u001a\u0004\u0018\u00010x8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\by\u0010z\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R,\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0080\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R,\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0088\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0006\b\u008d\u0001\u0010\u008e\u0001R0\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0090\u00012\n\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0090\u00018W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R(\u0010\u0099\u0001\u001a\u00020S2\u0007\u0010\u0091\u0001\u001a\u00020S8W@WX\u0096\u000f¢\u0006\u000e\u001a\u0005\b\u0097\u0001\u0010^\"\u0005\b\u0098\u0001\u0010VR(\u0010\u009c\u0001\u001a\u00020S2\u0007\u0010\u0091\u0001\u001a\u00020S8W@WX\u0096\u000f¢\u0006\u000e\u001a\u0005\b\u009a\u0001\u0010^\"\u0005\b\u009b\u0001\u0010VR\u0018\u0010 \u0001\u001a\u00030\u009d\u00018WX\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R4\u0010¢\u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u0016\n\u0005\b¢\u0001\u0010i\u001a\u0005\b£\u0001\u0010g\"\u0006\b¤\u0001\u0010¥\u0001R4\u0010¦\u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u0016\n\u0005\b¦\u0001\u0010i\u001a\u0005\b§\u0001\u0010g\"\u0006\b¨\u0001\u0010¥\u0001R4\u0010©\u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u0016\n\u0005\b©\u0001\u0010i\u001a\u0005\bª\u0001\u0010g\"\u0006\b«\u0001\u0010¥\u0001R4\u0010¬\u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u0016\n\u0005\b¬\u0001\u0010i\u001a\u0005\b\u00ad\u0001\u0010g\"\u0006\b®\u0001\u0010¥\u0001R4\u0010¯\u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_8V@VX\u0096\u000e¢\u0006\u0016\n\u0005\b¯\u0001\u0010i\u001a\u0005\b°\u0001\u0010g\"\u0006\b±\u0001\u0010¥\u0001R(\u0010´\u0001\u001a\u00020S2\u0007\u0010¡\u0001\u001a\u00020S8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b²\u0001\u0010^\"\u0005\b³\u0001\u0010VR,\u0010º\u0001\u001a\u00030µ\u00012\b\u0010¡\u0001\u001a\u00030µ\u00018@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\b¶\u0001\u0010·\u0001\"\u0006\b¸\u0001\u0010¹\u0001¨\u0006¾\u0001"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ViewBasedDataCaptureOverlay;", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/a;", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayProxy;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlay;", "impl", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "dataCaptureView", "<init>", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlay;Lcom/scandit/datacapture/core/ui/DataCaptureView;)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_dataCaptureOverlayImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_impl", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlay;", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;", "overlay", "", "_setProfilingOverlay", "(Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;)V", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeClusterLiveEditor;", "beginClusterEditing", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeClusterLiveEditor;", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "barcode", "didAcceptNotInListBarcode", "(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;)V", "didCancelNotInListBarcode", "didRejectNotInListBarcode", "", "getTextForBarcodesNotInListDetectedHint", "()Ljava/lang/String;", "getTextForMoveCloserAndRescanHint", "getTextForMoveFurtherAndRescanHint", "getTextForScanningHint", "getTextForScreenCleanedUpHint", "getTextForTapShutterToScanHint", "getTextForTapToUncountHint", "getTextForUnscannedBarcodesDetectedHint", "text", "setTextForBarcodesNotInListDetectedHint", "(Ljava/lang/String;)V", "setTextForClusteringGestureHint", "setTextForMoveCloserAndRescanHint", "setTextForMoveFurtherAndRescanHint", "setTextForScanningHint", "setTextForScreenCleanedUpHint", "setTextForTapShutterToScanHint", "setTextForTapToUncountHint", "setTextForUnrecognizedBarcodesInClusterHint", "setTextForUnscannedBarcodesDetectedHint", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "_setDataCaptureView", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;)V", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountGuidanceHandler;", "guidanceHandler", "setGuidanceHandler$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountGuidanceHandler;)V", "setGuidanceHandler", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountViewHandler;", "viewHandler", "setViewHandler$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountViewHandler;)V", "setViewHandler", "_cleanupViews", "()V", "onAttachedToWindow", "onDetachedFromWindow", "clearHighlights", "listButtonPressed", "exitButtonPressed", "shutterButtonPressed", "didTapScannedBarcode", "didTapUnscannedBarcode", "didTapNotInListBarcode", "didTapAcceptedBarcode", "didTapRejectedBarcode", "didTapFilteredBarcode", "Lcom/scandit/datacapture/barcode/tracking/internal/module/data/NativeCluster;", "cluster", "didTapCluster", "(Lcom/scandit/datacapture/barcode/tracking/internal/module/data/NativeCluster;)V", "", "statusModeEnabled", "setStatusModeEnabled$scandit_barcode_capture", "(Z)V", "setStatusModeEnabled", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountNotInListActionSettings;", "settings", "applyNotInListActionSettings$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountNotInListActionSettings;)V", "applyNotInListActionSettings", "isListenerSet", "()Z", "Lcom/scandit/datacapture/core/ui/style/Brush;", "brushForTrackedBarcode", "(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;)Lcom/scandit/datacapture/core/ui/style/Brush;", "brushForUnscannedTrackedBarcode", "brushForTrackedBarcodeNotInList", "brushForAcceptedBarcodeNotInList", "brushForRejectedBarcodeNotInList", "brushFromFilterSettings", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "b", "Lcom/scandit/datacapture/core/ui/style/Brush;", "getDefaultRecognizedBrush", "defaultRecognizedBrush", "c", "getDefaultUnrecognizedBrush", "defaultUnrecognizedBrush", "d", "getDefaultNotInListBrush", "defaultNotInListBrush", "e", "getDefaultAcceptedNotInListBrush", "defaultAcceptedNotInListBrush", "f", "getDefaultRejectedNotInListBrush", "defaultRejectedNotInListBrush", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayListener;", "g", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayListener;", "getListener", "()Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayListener;", "setListener", "(Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayUiListener;", "h", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayUiListener;", "getUiListener", "()Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayUiListener;", "setUiListener", "(Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayUiListener;)V", "uiListener", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayCallback;", "i", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayCallback;", "getCallback", "()Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayCallback;", "setCallback", "(Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlayCallback;)V", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "<set-?>", "getFilterSettings", "()Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "setFilterSettings", "(Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;)V", "filterSettings", "getShouldShowScanAreaGuides", "setShouldShowScanAreaGuides", "shouldShowScanAreaGuides", "getShouldShowStatusIconsOnScan", "setShouldShowStatusIconsOnScan", "shouldShowStatusIconsOnScan", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayStyle;", "getStyle", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayStyle;", "style", "value", "recognizedBrush", "getRecognizedBrush", "setRecognizedBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "unrecognizedBrush", "getUnrecognizedBrush", "setUnrecognizedBrush", "notInListBrush", "getNotInListBrush", "setNotInListBrush", "acceptedNotInListBrush", "getAcceptedNotInListBrush", "setAcceptedNotInListBrush", "rejectedNotInListBrush", "getRejectedNotInListBrush", "setRejectedNotInListBrush", "getShouldShowHints", "setShouldShowHints", "shouldShowHints", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayColorScheme;", "getColorScheme$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayColorScheme;", "setColorScheme$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayColorScheme;)V", "colorScheme", "Companion", "com/scandit/datacapture/barcode/count/ui/overlay/a", "com/scandit/datacapture/barcode/count/ui/overlay/d", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCountBasicOverlay extends ViewBasedDataCaptureOverlay implements DataCaptureOverlay, InterfaceC0329a, BarcodeCountBasicOverlayProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeCountBasicOverlayProxyAdapter a;

    /* renamed from: b, reason: from kotlin metadata */
    private final Brush defaultRecognizedBrush;

    /* renamed from: c, reason: from kotlin metadata */
    private final Brush defaultUnrecognizedBrush;

    /* renamed from: d, reason: from kotlin metadata */
    private final Brush defaultNotInListBrush;

    /* renamed from: e, reason: from kotlin metadata */
    private final Brush defaultAcceptedNotInListBrush;

    /* renamed from: f, reason: from kotlin metadata */
    private final Brush defaultRejectedNotInListBrush;

    /* renamed from: g, reason: from kotlin metadata */
    private BarcodeCountBasicOverlayListener listener;

    /* renamed from: h, reason: from kotlin metadata */
    private BarcodeCountBasicOverlayUiListener uiListener;

    /* renamed from: i, reason: from kotlin metadata */
    private BarcodeCountBasicOverlayCallback callback;
    private WeakReference j;
    private final C0289a k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCountBasicOverlay(NativeBarcodeCountBasicOverlay impl, DataCaptureView dataCaptureView) {
        super(AppAndroidEnvironment.INSTANCE.getApplicationContext());
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeCountBasicOverlayProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        Companion companion = INSTANCE;
        this.defaultRecognizedBrush = companion.defaultRecognizedBrush();
        this.defaultUnrecognizedBrush = companion.defaultUnrecognizedBrush();
        this.defaultNotInListBrush = companion.defaultNotInListBrush();
        this.defaultAcceptedNotInListBrush = companion.defaultAcceptedNotInListBrush();
        this.defaultRejectedNotInListBrush = companion.defaultRejectedNotInListBrush();
        this.j = new WeakReference(dataCaptureView);
        this.k = new C0289a(this);
        impl.setListener(new BarcodeCountBasicOverlayListenerReversedAdapter(new C0292d(this), this, null, 4, null));
        impl.setUiListener(new BarcodeCountBasicOverlayUiListenerReversedAdapter(new C0292d(this), this, null, 4, null));
    }

    @JvmStatic
    public static final Brush defaultAcceptedNotInListBrush() {
        return INSTANCE.defaultAcceptedNotInListBrush();
    }

    @JvmStatic
    public static final Brush defaultNotInListBrush() {
        return INSTANCE.defaultNotInListBrush();
    }

    @JvmStatic
    public static final Brush defaultRecognizedBrush() {
        return INSTANCE.defaultRecognizedBrush();
    }

    @JvmStatic
    public static final Brush defaultRejectedNotInListBrush() {
        return INSTANCE.defaultRejectedNotInListBrush();
    }

    @JvmStatic
    public static final Brush defaultUnrecognizedBrush() {
        return INSTANCE.defaultUnrecognizedBrush();
    }

    @JvmStatic
    public static final BarcodeCountBasicOverlay newInstance(BarcodeCount barcodeCount, DataCaptureView dataCaptureView) {
        return INSTANCE.newInstance(barcodeCount, dataCaptureView);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public void _cleanupViews() {
        BarcodeCountBasicOverlayCallback barcodeCountBasicOverlayCallback = this.callback;
        if (barcodeCountBasicOverlayCallback != null) {
            barcodeCountBasicOverlayCallback.cleanUpOverlayViews();
        }
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    @NativeImpl
    /* renamed from: _dataCaptureOverlayImpl */
    public NativeDataCaptureOverlay getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCountBasicOverlay getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public void _setDataCaptureView(DataCaptureView view) {
        DataCaptureView dataCaptureView;
        Intrinsics.checkNotNullParameter(view, "view");
        this.j = new WeakReference(view);
        if (!isAttachedToWindow() || (dataCaptureView = (DataCaptureView) this.j.get()) == null) {
            return;
        }
        dataCaptureView.addListener(this.k);
        C0289a c0289a = this.k;
        int width = dataCaptureView.getWidth();
        int height = dataCaptureView.getHeight();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        c0289a.onSizeChanged(width, height, ContextExtensionsKt.getRotation(context));
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setProfilingOverlay")
    public void _setProfilingOverlay(ProfilingOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.a._setProfilingOverlay(overlay);
    }

    public final void applyNotInListActionSettings$scandit_barcode_capture(BarcodeCountNotInListActionSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        getA().applyNotInListActionSettings(settings.toNative$scandit_barcode_capture());
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public NativeBarcodeClusterLiveEditor beginClusterEditing() {
        return this.a.beginClusterEditing();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushForAcceptedBarcodeNotInList(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBrush brushForAcceptedBarcode = getA().brushForAcceptedBarcode(barcode.getA());
        if (brushForAcceptedBarcode != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, brushForAcceptedBarcode);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushForRejectedBarcodeNotInList(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBrush brushForRejectedBarcode = getA().brushForRejectedBarcode(barcode.getA());
        if (brushForRejectedBarcode != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, brushForRejectedBarcode);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushForTrackedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBrush brushForTrackedBarcode = getA().brushForTrackedBarcode(barcode.getA());
        if (brushForTrackedBarcode != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, brushForTrackedBarcode);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushForTrackedBarcodeNotInList(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBrush brushForTrackedBarcodeNotInList = getA().brushForTrackedBarcodeNotInList(barcode.getA());
        if (brushForTrackedBarcodeNotInList != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, brushForTrackedBarcodeNotInList);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushForUnscannedTrackedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        NativeBrush brushForUnscannedTrackedBarcode = getA().brushForUnscannedTrackedBarcode(barcode.getA());
        if (brushForUnscannedTrackedBarcode != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, brushForUnscannedTrackedBarcode);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush brushFromFilterSettings() {
        NativeBrush brush;
        NativeBarcodeFilterOverlaySettings filterSettings = getA().getFilterSettings();
        if (filterSettings == null || (brush = filterSettings.getBrush()) == null) {
            return null;
        }
        return BrushExtensionsKt.of(Brush.INSTANCE, brush);
    }

    public final void clearHighlights() {
        getA().clearHighlightsButtonPressed();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "didAcceptNotInListBarcode")
    public void didAcceptNotInListBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.a.didAcceptNotInListBarcode(barcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "didCancelNotInListBarcode")
    public void didCancelNotInListBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.a.didCancelNotInListBarcode(barcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "didRejectNotInListBarcode")
    public void didRejectNotInListBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.a.didRejectNotInListBarcode(barcode);
    }

    public final void didTapAcceptedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapAcceptedBarcode(barcode.getA());
    }

    public final void didTapCluster(NativeCluster cluster) {
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        getA().didTapCluster(cluster);
    }

    public final void didTapFilteredBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapFilteredBarcode(barcode.getA());
    }

    public final void didTapNotInListBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapNotInListBarcode(barcode.getA());
    }

    public final void didTapRejectedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapRejectedBarcode(barcode.getA());
    }

    public final void didTapScannedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapScannedBarcode(barcode.getA());
    }

    public final void didTapUnscannedBarcode(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().didTapUnscannedBarcode(barcode.getA());
    }

    public final void exitButtonPressed() {
        getA().exitButtonPressed();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush getAcceptedNotInListBrush() {
        Brush.Companion companion = Brush.INSTANCE;
        NativeBrush acceptedBrush = getA().getAcceptedBrush();
        Intrinsics.checkNotNullExpressionValue(acceptedBrush, "getAcceptedBrush(...)");
        return BrushExtensionsKt.of(companion, acceptedBrush);
    }

    public final BarcodeCountBasicOverlayCallback getCallback() {
        return this.callback;
    }

    public final NativeBarcodeCountBasicOverlayColorScheme getColorScheme$scandit_barcode_capture() {
        NativeBarcodeCountBasicOverlayColorScheme colorScheme = getA().getColorScheme();
        Intrinsics.checkNotNullExpressionValue(colorScheme, "getColorScheme(...)");
        return colorScheme;
    }

    public Brush getDefaultAcceptedNotInListBrush() {
        return this.defaultAcceptedNotInListBrush;
    }

    public Brush getDefaultNotInListBrush() {
        return this.defaultNotInListBrush;
    }

    public Brush getDefaultRecognizedBrush() {
        return this.defaultRecognizedBrush;
    }

    public Brush getDefaultRejectedNotInListBrush() {
        return this.defaultRejectedNotInListBrush;
    }

    public Brush getDefaultUnrecognizedBrush() {
        return this.defaultUnrecognizedBrush;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "getFilterSettings", property = "filterSettings")
    public BarcodeFilterHighlightSettings getFilterSettings() {
        return this.a.getFilterSettings();
    }

    public final BarcodeCountBasicOverlayListener getListener() {
        return this.listener;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush getNotInListBrush() {
        NativeBrush notInListBrush = getA().getNotInListBrush();
        if (notInListBrush != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, notInListBrush);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush getRecognizedBrush() {
        NativeBrush scannedBrush = getA().getScannedBrush();
        if (scannedBrush != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, scannedBrush);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush getRejectedNotInListBrush() {
        Brush.Companion companion = Brush.INSTANCE;
        NativeBrush rejectedBrush = getA().getRejectedBrush();
        Intrinsics.checkNotNullExpressionValue(rejectedBrush, "getRejectedBrush(...)");
        return BrushExtensionsKt.of(companion, rejectedBrush);
    }

    public final boolean getShouldShowHints() {
        return getA().isHintsEnabled();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "shouldShowScanAreaGuides", property = "shouldShowScanAreaGuides")
    public boolean getShouldShowScanAreaGuides() {
        return this.a.getShouldShowScanAreaGuides();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "shouldShowStatusIconsOnScan", property = "shouldShowStatusIconsOnScan")
    public boolean getShouldShowStatusIconsOnScan() {
        return this.a.getShouldShowStatusIconsOnScan();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(property = "style")
    public NativeBarcodeCountBasicOverlayStyle getStyle() {
        return this.a.getStyle();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "getTextForWrongBarcodesDetectedHint")
    public String getTextForBarcodesNotInListDetectedHint() {
        return this.a.getTextForBarcodesNotInListDetectedHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForMoveCloserAndRescanHint() {
        return this.a.getTextForMoveCloserAndRescanHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForMoveFurtherAndRescanHint() {
        return this.a.getTextForMoveFurtherAndRescanHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForScanningHint() {
        return this.a.getTextForScanningHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForScreenCleanedUpHint() {
        return this.a.getTextForScreenCleanedUpHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForTapShutterToScanHint() {
        return this.a.getTextForTapShutterToScanHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForTapToUncountHint() {
        return this.a.getTextForTapToUncountHint();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public String getTextForUnscannedBarcodesDetectedHint() {
        return this.a.getTextForUnscannedBarcodesDetectedHint();
    }

    public final BarcodeCountBasicOverlayUiListener getUiListener() {
        return this.uiListener;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public Brush getUnrecognizedBrush() {
        NativeBrush unscannedBrush = getA().getUnscannedBrush();
        if (unscannedBrush != null) {
            return BrushExtensionsKt.of(Brush.INSTANCE, unscannedBrush);
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.InterfaceC0329a
    public boolean isListenerSet() {
        return this.listener != null;
    }

    public final void listButtonPressed() {
        getA().listButtonPressed();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        DataCaptureView dataCaptureView;
        super.onAttachedToWindow();
        if (this.j.get() == null || (dataCaptureView = (DataCaptureView) this.j.get()) == null) {
            return;
        }
        dataCaptureView.addListener(this.k);
        C0289a c0289a = this.k;
        int width = dataCaptureView.getWidth();
        int height = dataCaptureView.getHeight();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        c0289a.onSizeChanged(width, height, ContextExtensionsKt.getRotation(context));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DataCaptureView dataCaptureView = (DataCaptureView) this.j.get();
        if (dataCaptureView != null) {
            dataCaptureView.removeListener(this.k);
        }
    }

    public void setAcceptedNotInListBrush(Brush brush) {
        getA().setAcceptedBrush(brush != null ? CoreNativeTypeFactory.INSTANCE.convert(brush) : null);
    }

    public final void setCallback(BarcodeCountBasicOverlayCallback barcodeCountBasicOverlayCallback) {
        this.callback = barcodeCountBasicOverlayCallback;
    }

    public final void setColorScheme$scandit_barcode_capture(NativeBarcodeCountBasicOverlayColorScheme value) {
        Intrinsics.checkNotNullParameter(value, "value");
        getA().setColorScheme(value);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setFilterSettings", property = "filterSettings")
    public void setFilterSettings(BarcodeFilterHighlightSettings barcodeFilterHighlightSettings) {
        this.a.setFilterSettings(barcodeFilterHighlightSettings);
    }

    public final void setGuidanceHandler$scandit_barcode_capture(NativeBarcodeCountGuidanceHandler guidanceHandler) {
        Intrinsics.checkNotNullParameter(guidanceHandler, "guidanceHandler");
        getA().setGuidanceHandler(guidanceHandler);
    }

    public final void setListener(BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener) {
        this.listener = barcodeCountBasicOverlayListener;
    }

    public void setNotInListBrush(Brush brush) {
        getA().setNotInListBrush(brush != null ? CoreNativeTypeFactory.INSTANCE.convert(brush) : null);
    }

    public void setRecognizedBrush(Brush brush) {
        getA().setScannedBrush(brush != null ? CoreNativeTypeFactory.INSTANCE.convert(brush) : null);
    }

    public void setRejectedNotInListBrush(Brush brush) {
        getA().setRejectedBrush(brush != null ? CoreNativeTypeFactory.INSTANCE.convert(brush) : null);
    }

    public final void setShouldShowHints(boolean z) {
        getA().setHintsEnabled(z);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setShouldShowScanAreaGuides", property = "shouldShowScanAreaGuides")
    public void setShouldShowScanAreaGuides(boolean z) {
        this.a.setShouldShowScanAreaGuides(z);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setShouldShowStatusIconsOnScan", property = "shouldShowStatusIconsOnScan")
    public void setShouldShowStatusIconsOnScan(boolean z) {
        this.a.setShouldShowStatusIconsOnScan(z);
    }

    public final void setStatusModeEnabled$scandit_barcode_capture(boolean statusModeEnabled) {
        getA().setStatusModeEnabled(statusModeEnabled);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setTextForWrongBarcodesDetectedHint")
    public void setTextForBarcodesNotInListDetectedHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForBarcodesNotInListDetectedHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setTextForSwipeToGroupHint")
    public void setTextForClusteringGestureHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForClusteringGestureHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForMoveCloserAndRescanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForMoveCloserAndRescanHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForMoveFurtherAndRescanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForMoveFurtherAndRescanHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForScanningHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForScanningHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForScreenCleanedUpHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForScreenCleanedUpHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForTapShutterToScanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForTapShutterToScanHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForTapToUncountHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForTapToUncountHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction(nativeName = "setTextForRescanYellowCodesHint")
    public void setTextForUnrecognizedBarcodesInClusterHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForUnrecognizedBarcodesInClusterHint(text);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayProxy
    @ProxyFunction
    public void setTextForUnscannedBarcodesDetectedHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setTextForUnscannedBarcodesDetectedHint(text);
    }

    public final void setUiListener(BarcodeCountBasicOverlayUiListener barcodeCountBasicOverlayUiListener) {
        this.uiListener = barcodeCountBasicOverlayUiListener;
    }

    public void setUnrecognizedBrush(Brush brush) {
        getA().setUnscannedBrush(brush != null ? CoreNativeTypeFactory.INSTANCE.convert(brush) : null);
    }

    public final void setViewHandler$scandit_barcode_capture(NativeBarcodeCountViewHandler viewHandler) {
        Intrinsics.checkNotNullParameter(viewHandler, "viewHandler");
        getA().setViewHandler(viewHandler);
    }

    public final void shutterButtonPressed() {
        getA().shutterButtonPressed();
    }

    @JvmStatic
    public static final BarcodeCountBasicOverlay newInstance(DataCaptureView dataCaptureView, BarcodeCount barcodeCount, NativeBarcodeCountBasicOverlayStyle nativeBarcodeCountBasicOverlayStyle) {
        return INSTANCE.newInstance(dataCaptureView, barcodeCount, nativeBarcodeCountBasicOverlayStyle);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\b\u0010\u0004J!\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u000e\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay$Companion;", "", "Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultRecognizedBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultUnrecognizedBrush", "defaultNotInListBrush", "defaultAcceptedNotInListBrush", "defaultRejectedNotInListBrush", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "mode", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;", "newInstance", "(Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/core/ui/DataCaptureView;)Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayStyle;", "style", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlayStyle;)Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final Brush defaultAcceptedNotInListBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForAcceptedBarcode = NativeBarcodeCountBasicOverlay.defaultBrushForAcceptedBarcode();
            Intrinsics.checkNotNullExpressionValue(defaultBrushForAcceptedBarcode, "defaultBrushForAcceptedBarcode(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForAcceptedBarcode);
        }

        @JvmStatic
        public final Brush defaultNotInListBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForTrackedBarcodeNotInList = NativeBarcodeCountBasicOverlay.defaultBrushForTrackedBarcodeNotInList();
            Intrinsics.checkNotNullExpressionValue(defaultBrushForTrackedBarcodeNotInList, "defaultBrushForTrackedBarcodeNotInList(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForTrackedBarcodeNotInList);
        }

        @JvmStatic
        public final Brush defaultRecognizedBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForTrackedBarcode = NativeBarcodeCountBasicOverlay.defaultBrushForTrackedBarcode();
            Intrinsics.checkNotNullExpressionValue(defaultBrushForTrackedBarcode, "defaultBrushForTrackedBarcode(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForTrackedBarcode);
        }

        @JvmStatic
        public final Brush defaultRejectedNotInListBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForRejectedBarcode = NativeBarcodeCountBasicOverlay.defaultBrushForRejectedBarcode();
            Intrinsics.checkNotNullExpressionValue(defaultBrushForRejectedBarcode, "defaultBrushForRejectedBarcode(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForRejectedBarcode);
        }

        @JvmStatic
        public final Brush defaultUnrecognizedBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForUnscannedTrackedBarcode = NativeBarcodeCountBasicOverlay.defaultBrushForUnscannedTrackedBarcode();
            Intrinsics.checkNotNullExpressionValue(defaultBrushForUnscannedTrackedBarcode, "defaultBrushForUnscannedTrackedBarcode(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForUnscannedTrackedBarcode);
        }

        @JvmStatic
        public final BarcodeCountBasicOverlay newInstance(BarcodeCount mode, DataCaptureView view) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, C0290b.a);
            BarcodeCountBasicOverlay barcodeCountBasicOverlay = new BarcodeCountBasicOverlay(mode, view, null);
            BarcodeCountBasicOverlay.INSTANCE.getClass();
            if (view != null) {
                view.addOverlay(barcodeCountBasicOverlay);
            }
            new WeakReference(mode);
            barcodeCountBasicOverlay.getClass();
            return barcodeCountBasicOverlay;
        }

        @JvmStatic
        public final BarcodeCountBasicOverlay newInstance(DataCaptureView view, BarcodeCount mode, NativeBarcodeCountBasicOverlayStyle style) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, C0291c.a);
            BarcodeCountBasicOverlay barcodeCountBasicOverlay = new BarcodeCountBasicOverlay(mode, view, style, null);
            BarcodeCountBasicOverlay.INSTANCE.getClass();
            if (view != null) {
                view.addOverlay(barcodeCountBasicOverlay);
            }
            new WeakReference(mode);
            barcodeCountBasicOverlay.getClass();
            return barcodeCountBasicOverlay;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCountBasicOverlay(com.scandit.datacapture.barcode.count.capture.BarcodeCount r1, com.scandit.datacapture.core.ui.DataCaptureView r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount r1 = r1._impl()
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle r3 = com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle.ICON
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay r1 = com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay.create(r1, r3)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay.<init>(com.scandit.datacapture.barcode.count.capture.BarcodeCount, com.scandit.datacapture.core.ui.DataCaptureView, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCountBasicOverlay(com.scandit.datacapture.barcode.count.capture.BarcodeCount r1, com.scandit.datacapture.core.ui.DataCaptureView r2, com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount r1 = r1._impl()
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay r1 = com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay.create(r1, r3)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay.<init>(com.scandit.datacapture.barcode.count.capture.BarcodeCount, com.scandit.datacapture.core.ui.DataCaptureView, com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
