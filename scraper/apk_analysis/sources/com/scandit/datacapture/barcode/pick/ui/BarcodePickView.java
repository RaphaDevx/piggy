package com.scandit.datacapture.barcode.pick.ui;

import android.content.Context;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareButtonHelperKt;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareKeyEvent;
import com.scandit.datacapture.barcode.internal.module.pick.capture.CameraManager;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper;
import com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.internal.module.ui.exitbutton.ExitButtonWrapper;
import com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper;
import com.scandit.datacapture.barcode.internal.sdk.pick.ui.BarcodePickViewSettingsDefaults;
import com.scandit.datacapture.barcode.internal.sdk.ui.InternalPropertyManager;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 S2\u00020\u0001:\u0001SB\u0081\u0001\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ/\u0010$\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0014¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020#H\u0007¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020#H\u0007¢\u0006\u0004\b(\u0010'J\u000f\u0010)\u001a\u00020#H\u0007¢\u0006\u0004\b)\u0010'J\u000f\u0010*\u001a\u00020#H\u0007¢\u0006\u0004\b*\u0010'J\u000f\u0010+\u001a\u00020#H\u0007¢\u0006\u0004\b+\u0010'J\u000f\u0010,\u001a\u00020#H\u0007¢\u0006\u0004\b,\u0010'J\u000f\u0010-\u001a\u00020#H\u0007¢\u0006\u0004\b-\u0010'J\u0015\u00100\u001a\u00020#2\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101J\u0015\u00102\u001a\u00020#2\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b2\u00101J\u0017\u00105\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b3\u00104J\u000f\u00107\u001a\u00020#H\u0000¢\u0006\u0004\b6\u0010'J\u000f\u00109\u001a\u00020#H\u0000¢\u0006\u0004\b8\u0010'J\u000f\u0010;\u001a\u00020#H\u0000¢\u0006\u0004\b:\u0010'J\u000f\u0010=\u001a\u00020#H\u0000¢\u0006\u0004\b<\u0010'R$\u0010/\u001a\u0004\u0018\u00010>8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR$\u0010L\u001a\u0004\u0018\u00010E8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u0017\u0010R\u001a\u00020M8\u0006¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q¨\u0006T"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "Landroid/widget/RelativeLayout;", "Landroid/content/Context;", "context", "Landroid/view/ViewGroup;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "mode", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "viewSettings", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/CameraManager;", "cameraManager", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/elementsprovider/BarcodePickElementsCache;", "elementsHandler", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightdrawer/BarcodePickDrawer;", "drawer", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/datacaptureview/BarcodePickDataCaptureViewWrapper;", "captureViewWrapper", "Lcom/scandit/datacapture/barcode/internal/module/ui/shutterbutton/ShutterButtonWrapper;", "shutterButtonWrapper", "Lcom/scandit/datacapture/barcode/internal/module/ui/exitbutton/ExitButtonWrapper;", "exitButtonWrapper", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/guidances/BarcodePickGuidanceHandler;", "guidanceHandler", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "barcodePickBasicOverlay", "<init>", "(Landroid/content/Context;Landroid/view/ViewGroup;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;Lcom/scandit/datacapture/barcode/internal/module/pick/capture/CameraManager;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/elementsprovider/BarcodePickElementsCache;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightdrawer/BarcodePickDrawer;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/datacaptureview/BarcodePickDataCaptureViewWrapper;Lcom/scandit/datacapture/barcode/internal/module/ui/shutterbutton/ShutterButtonWrapper;Lcom/scandit/datacapture/barcode/internal/module/ui/exitbutton/ExitButtonWrapper;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/guidances/BarcodePickGuidanceHandler;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;)V", "", "w", "h", "oldw", "oldh", "", "onSizeChanged", "(IIII)V", "onResume", "()V", "onPause", "onDestroy", "start", "stop", "freeze", "reset", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickActionListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addActionListener", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickActionListener;)V", "removeActionListener", "_applySettings$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;)V", "_applySettings", "onStatusListenerStarted$scandit_barcode_capture", "onStatusListenerStarted", "onStatusListenerFreezed$scandit_barcode_capture", "onStatusListenerFreezed", "onStatusListenerPaused$scandit_barcode_capture", "onStatusListenerPaused", "onStatusListenerStopped$scandit_barcode_capture", "onStatusListenerStopped", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewListener;", "j", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewListener;", "getListener", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewListener;", "setListener", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewListener;)V", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewUiListener;", "k", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewUiListener;", "getUiListener", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewUiListener;", "setUiListener", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewUiListener;)V", "uiListener", "Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "l", "Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "get_internalPropertyManager", "()Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "_internalPropertyManager", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickView extends RelativeLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DataCaptureContext a;
    private final BarcodePick b;
    private final CameraManager c;
    private final BarcodePickDrawer d;
    private final BarcodePickDataCaptureViewWrapper e;
    private final ShutterButtonWrapper f;
    private final ExitButtonWrapper g;
    private final BarcodePickGuidanceHandler h;
    private final BarcodePickBasicOverlay i;

    /* renamed from: j, reason: from kotlin metadata */
    private BarcodePickViewListener listener;

    /* renamed from: k, reason: from kotlin metadata */
    private BarcodePickViewUiListener uiListener;
    private final BarcodePickView$_internalPropertyManager$1 l;
    private final MainThreadHelperImpl m;
    private final com.scandit.datacapture.barcode.internal.module.shared.c n;
    private final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f o;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001J9\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0014\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView$Companion;", "", "Landroid/view/ViewGroup;", "parentView", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "mode", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "cameraSettings", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "newInstance", "(Landroid/view/ViewGroup;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "", "getHardwareTriggerSupported", "()Z", "getHardwareTriggerSupported$annotations", "()V", "hardwareTriggerSupported", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getHardwareTriggerSupported$annotations() {
        }

        public static /* synthetic */ BarcodePickView newInstance$default(Companion companion, ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePick barcodePick, BarcodePickViewSettings barcodePickViewSettings, CameraSettings cameraSettings, int i, Object obj) {
            if ((i & 16) != 0) {
                cameraSettings = BarcodePick.INSTANCE.createRecommendedCameraSettings();
            }
            return companion.newInstance(viewGroup, dataCaptureContext, barcodePick, barcodePickViewSettings, cameraSettings);
        }

        public final boolean getHardwareTriggerSupported() {
            return HardwareButtonHelperKt.isHardwareTriggerSupported();
        }

        @JvmStatic
        public final BarcodePickView newInstance(ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, BarcodePickViewSettings settings) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return newInstance$default(this, parentView, dataCaptureContext, mode, settings, null, 16, null);
        }

        @JvmStatic
        public final BarcodePickView newInstance(ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, BarcodePickViewSettings settings, CameraSettings cameraSettings) {
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(settings, "viewSettings");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            BarcodePickViewHighlightStyle highlightStyle = settings.getHighlightStyle();
            if (highlightStyle instanceof BarcodePickViewHighlightStyle.Dot) {
                BarcodePickViewHighlightStyle.Dot style = (BarcodePickViewHighlightStyle.Dot) highlightStyle;
                Intrinsics.checkNotNullParameter(parentView, "parentView");
                Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
                Intrinsics.checkNotNullParameter(mode, "mode");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                Intrinsics.checkNotNullParameter(style, "castedStyle");
                Intrinsics.checkNotNullParameter(style, "style");
                return com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.e.a(parentView, dataCaptureContext, mode, settings, cameraSettings, style, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.a(style), new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.b(style)));
            }
            if (highlightStyle instanceof BarcodePickViewHighlightStyle.Rectangular) {
                BarcodePickViewHighlightStyle.Rectangular style2 = (BarcodePickViewHighlightStyle.Rectangular) highlightStyle;
                Intrinsics.checkNotNullParameter(parentView, "parentView");
                Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
                Intrinsics.checkNotNullParameter(mode, "mode");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                Intrinsics.checkNotNullParameter(style2, "castedStyle");
                Intrinsics.checkNotNullParameter(style2, "style");
                return com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.e.a(parentView, dataCaptureContext, mode, settings, cameraSettings, style2, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.e(style2), new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.f(style2)));
            }
            if (highlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons) {
                BarcodePickViewHighlightStyle.DotWithIcons style3 = (BarcodePickViewHighlightStyle.DotWithIcons) highlightStyle;
                Intrinsics.checkNotNullParameter(parentView, "parentView");
                Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
                Intrinsics.checkNotNullParameter(mode, "mode");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                Intrinsics.checkNotNullParameter(style3, "castedStyle");
                Context context = parentView.getContext();
                Intrinsics.checkNotNull(context);
                BarcodePickBasicOverlay barcodePickBasicOverlay = new BarcodePickBasicOverlay(context, mode, settings);
                com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j a = com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.a.a(context, settings);
                BarcodePickViewHighlightStyleAsyncProvider asyncStyleProvider = style3.getAsyncStyleProvider();
                boolean styleResponseCacheEnabled = style3.getStyleResponseCacheEnabled();
                Intrinsics.checkNotNullParameter(style3, "style");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j brushProvider = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.c(style3), new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.d(style3));
                BarcodePickHighlightStyleIconsHolder overrides = style3.get_iconsHolder();
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c defaults = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.b.a(style3);
                Intrinsics.checkNotNullParameter(overrides, "overrides");
                Intrinsics.checkNotNullParameter(defaults, "defaults");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.d iconProvider = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.d(overrides, defaults);
                Intrinsics.checkNotNullParameter(brushProvider, "brushProvider");
                Intrinsics.checkNotNullParameter(iconProvider, "iconProvider");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c cVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c(asyncStyleProvider, brushProvider, iconProvider, styleResponseCacheEnabled);
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l lVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l();
                CameraPosition cameraPosition = CameraPosition.WORLD_FACING;
                Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                com.scandit.datacapture.barcode.internal.module.pick.capture.A a2 = new com.scandit.datacapture.barcode.internal.module.pick.capture.A(cameraPosition, cameraSettings);
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.l lVar2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.l(mode);
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.m mVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.m(a);
                Intrinsics.checkNotNullParameter(settings, "settings");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.n a3 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.s.a(barcodePickBasicOverlay, lVar2, style3, mVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.j(settings.getDrawDebugInfo(), new Size2(BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * settings.getMinimumHighlightHeightPx$scandit_barcode_capture()), PixelExtensionsKt.pxFromDp$default(settings.maxIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(settings.minIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(BarcodePickViewSettingsDefaults.getIconPadding(), (Context) null, 1, (Object) null), new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture())), cVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.o(lVar, style3, cVar, context, a), Utf8.MASK_2BYTES);
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.p pVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.p(a);
                Intrinsics.checkNotNullParameter(settings, "settings");
                BarcodePickDrawer[] drawers = {a3, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.e.a(barcodePickBasicOverlay, pVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.f(new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture()), settings.getFilterHighlightSettings()))};
                Intrinsics.checkNotNullParameter(aVar, "<this>");
                Intrinsics.checkNotNullParameter(drawers, "drawers");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a aVar2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a(ArraysKt.toList(drawers));
                Context context2 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                Intrinsics.checkNotNullParameter(context2, "context");
                com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b factory = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b(context2);
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c cVar2 = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a;
                Intrinsics.checkNotNullParameter(cVar2, "<this>");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d positioner = new com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d(settings.getUiButtonsOffset());
                Intrinsics.checkNotNullParameter(factory, "factory");
                Intrinsics.checkNotNullParameter(positioner, "positioner");
                com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i iVar = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i(factory, positioner);
                Context context3 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
                Intrinsics.checkNotNullParameter(context3, "context");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b factory2 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b(context3);
                Intrinsics.checkNotNullParameter(cVar2, "<this>");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h positioner2 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h();
                Intrinsics.checkNotNullParameter(factory2, "factory");
                Intrinsics.checkNotNullParameter(positioner2, "positioner");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e eVar = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e(factory2, positioner2);
                Context context4 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                com.scandit.datacapture.barcode.internal.module.pick.ui.f loadingViewFactory = com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.a.b(context, settings);
                Context context5 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
                Intrinsics.checkNotNullParameter(context5, "context");
                com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k guidanceViewFactory = new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k(context5);
                Intrinsics.checkNotNullParameter(context4, "context");
                Intrinsics.checkNotNullParameter(loadingViewFactory, "loadingViewFactory");
                Intrinsics.checkNotNullParameter(guidanceViewFactory, "guidanceViewFactory");
                return new BarcodePickView(context, parentView, dataCaptureContext, mode, settings, a2, cVar, aVar2, a, iVar, eVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.g(context4, loadingViewFactory, guidanceViewFactory), barcodePickBasicOverlay);
            }
            if (highlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) {
                BarcodePickViewHighlightStyle.RectangularWithIcons style4 = (BarcodePickViewHighlightStyle.RectangularWithIcons) highlightStyle;
                Intrinsics.checkNotNullParameter(parentView, "parentView");
                Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
                Intrinsics.checkNotNullParameter(mode, "mode");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                Intrinsics.checkNotNullParameter(style4, "castedStyle");
                Context context6 = parentView.getContext();
                Intrinsics.checkNotNull(context6);
                BarcodePickBasicOverlay barcodePickBasicOverlay2 = new BarcodePickBasicOverlay(context6, mode, settings);
                com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j a4 = com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.a.a(context6, settings);
                BarcodePickViewHighlightStyleAsyncProvider asyncStyleProvider2 = style4.getAsyncStyleProvider();
                boolean styleResponseCacheEnabled2 = style4.getStyleResponseCacheEnabled();
                Intrinsics.checkNotNullParameter(style4, "style");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j brushProvider2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.g(style4), new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.h(style4));
                BarcodePickHighlightStyleIconsHolder overrides2 = style4.get_iconsHolder();
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c defaults2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.b.a(style4);
                Intrinsics.checkNotNullParameter(overrides2, "overrides");
                Intrinsics.checkNotNullParameter(defaults2, "defaults");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.d iconProvider2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.d(overrides2, defaults2);
                Intrinsics.checkNotNullParameter(brushProvider2, "brushProvider");
                Intrinsics.checkNotNullParameter(iconProvider2, "iconProvider");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c cVar3 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c(asyncStyleProvider2, brushProvider2, iconProvider2, styleResponseCacheEnabled2);
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l lVar3 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l();
                CameraPosition cameraPosition2 = CameraPosition.WORLD_FACING;
                Intrinsics.checkNotNullParameter(cameraPosition2, "cameraPosition");
                Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
                com.scandit.datacapture.barcode.internal.module.pick.capture.A a5 = new com.scandit.datacapture.barcode.internal.module.pick.capture.A(cameraPosition2, cameraSettings);
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar3 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.q qVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.q(mode);
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.r rVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.r(a4);
                Intrinsics.checkNotNullParameter(settings, "settings");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.n a6 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.s.a(barcodePickBasicOverlay2, qVar, style4, rVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.j(settings.getDrawDebugInfo(), new Size2(BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * settings.getMinimumHighlightHeightPx$scandit_barcode_capture()), PixelExtensionsKt.pxFromDp$default(settings.maxIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(settings.minIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(BarcodePickViewSettingsDefaults.getIconPadding(), (Context) null, 1, (Object) null), new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture())), cVar3, new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.t(lVar3, style4, cVar3, settings, a4), Utf8.MASK_2BYTES);
                com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.u uVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.u(a4);
                Intrinsics.checkNotNullParameter(settings, "settings");
                BarcodePickDrawer[] drawers2 = {a6, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.e.a(barcodePickBasicOverlay2, uVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.f(new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture()), settings.getFilterHighlightSettings()))};
                Intrinsics.checkNotNullParameter(aVar3, "<this>");
                Intrinsics.checkNotNullParameter(drawers2, "drawers");
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a aVar4 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a(ArraysKt.toList(drawers2));
                Context context7 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context7, "getContext(...)");
                Intrinsics.checkNotNullParameter(context7, "context");
                com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b factory3 = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b(context7);
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c cVar4 = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a;
                Intrinsics.checkNotNullParameter(cVar4, "<this>");
                Intrinsics.checkNotNullParameter(settings, "viewSettings");
                com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d positioner3 = new com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d(settings.getUiButtonsOffset());
                Intrinsics.checkNotNullParameter(factory3, "factory");
                Intrinsics.checkNotNullParameter(positioner3, "positioner");
                com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i iVar2 = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i(factory3, positioner3);
                Context context8 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
                Intrinsics.checkNotNullParameter(context8, "context");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b factory4 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b(context8);
                Intrinsics.checkNotNullParameter(cVar4, "<this>");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h positioner4 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h();
                Intrinsics.checkNotNullParameter(factory4, "factory");
                Intrinsics.checkNotNullParameter(positioner4, "positioner");
                com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e eVar2 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e(factory4, positioner4);
                Context context9 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context9, "getContext(...)");
                com.scandit.datacapture.barcode.internal.module.pick.ui.f loadingViewFactory2 = com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.a.b(context6, settings);
                Context context10 = parentView.getContext();
                Intrinsics.checkNotNullExpressionValue(context10, "getContext(...)");
                Intrinsics.checkNotNullParameter(context10, "context");
                com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k guidanceViewFactory2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k(context10);
                Intrinsics.checkNotNullParameter(context9, "context");
                Intrinsics.checkNotNullParameter(loadingViewFactory2, "loadingViewFactory");
                Intrinsics.checkNotNullParameter(guidanceViewFactory2, "guidanceViewFactory");
                return new BarcodePickView(context6, parentView, dataCaptureContext, mode, settings, a5, cVar3, aVar4, a4, iVar2, eVar2, new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.g(context9, loadingViewFactory2, guidanceViewFactory2), barcodePickBasicOverlay2);
            }
            if (!(highlightStyle instanceof BarcodePickViewHighlightStyle.CustomView)) {
                throw new NoWhenBranchMatchedException();
            }
            BarcodePickViewHighlightStyle.CustomView highlightStyle2 = (BarcodePickViewHighlightStyle.CustomView) highlightStyle;
            Intrinsics.checkNotNullParameter(parentView, "parentView");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(settings, "viewSettings");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            Intrinsics.checkNotNullParameter(highlightStyle2, "castedStyle");
            Context context11 = parentView.getContext();
            Intrinsics.checkNotNull(context11);
            BarcodePickBasicOverlay container = new BarcodePickBasicOverlay(context11, mode, settings);
            Intrinsics.checkNotNullParameter(context11, "context");
            com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.h factory5 = new com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.h(context11);
            Intrinsics.checkNotNullParameter(context11, "context");
            Intrinsics.checkNotNullParameter(factory5, "factory");
            Intrinsics.checkNotNullParameter(settings, "settings");
            com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j jVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j(context11, factory5, settings);
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f cache = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f(highlightStyle2.getAsyncCustomViewProvider());
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l lVar4 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l();
            CameraPosition cameraPosition3 = CameraPosition.WORLD_FACING;
            Intrinsics.checkNotNullParameter(cameraPosition3, "cameraPosition");
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            com.scandit.datacapture.barcode.internal.module.pick.capture.A a7 = new com.scandit.datacapture.barcode.internal.module.pick.capture.A(cameraPosition3, cameraSettings);
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar5 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
            boolean fitViewsToBarcode = highlightStyle2.getFitViewsToBarcode();
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.j drawSettings = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.j(settings);
            Size2 minSize = new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture());
            boolean fitViewsToBarcode2 = highlightStyle2.getFitViewsToBarcode();
            com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.f quadrilateralMapper = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.f(jVar);
            Intrinsics.checkNotNullParameter(minSize, "minSize");
            Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
            Intrinsics.checkNotNullParameter(settings, "viewSettings");
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.c drawDataFactory = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.c(minSize, fitViewsToBarcode2, quadrilateralMapper, settings);
            com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.g onTrackTap = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.g(mode);
            com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.j statusIconViewDrawerProvider = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.j(lVar4, highlightStyle2, cache, settings, jVar);
            HashMap drawingData = new HashMap();
            com.scandit.datacapture.barcode.internal.module.ui.k touchEventHandler = new com.scandit.datacapture.barcode.internal.module.ui.k(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.g(drawingData), com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.h.a, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.i(onTrackTap));
            Paint tapPaint = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.k.a;
            Intrinsics.checkNotNullParameter(aVar5, "<this>");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(onTrackTap, "onTrackTap");
            Intrinsics.checkNotNullParameter(highlightStyle2, "highlightStyle");
            Intrinsics.checkNotNullParameter(statusIconViewDrawerProvider, "statusIconViewDrawerProvider");
            Intrinsics.checkNotNullParameter(drawingData, "drawingData");
            Intrinsics.checkNotNullParameter(touchEventHandler, "touchEventHandler");
            Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
            Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
            Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
            Intrinsics.checkNotNullParameter(cache, "cache");
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.f fVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.f(container, fitViewsToBarcode, touchEventHandler, highlightStyle2, drawSettings, drawDataFactory, cache, statusIconViewDrawerProvider, tapPaint, drawingData);
            com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.k kVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.k(jVar);
            Intrinsics.checkNotNullParameter(settings, "settings");
            BarcodePickDrawer[] drawers3 = {fVar, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.e.a(container, kVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.f(new Size2(settings.getMinimumHighlightWidthPx$scandit_barcode_capture(), settings.getMinimumHighlightHeightPx$scandit_barcode_capture()), settings.getFilterHighlightSettings()))};
            Intrinsics.checkNotNullParameter(aVar5, "<this>");
            Intrinsics.checkNotNullParameter(drawers3, "drawers");
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a aVar6 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a(ArraysKt.toList(drawers3));
            Context context12 = parentView.getContext();
            Intrinsics.checkNotNullExpressionValue(context12, "getContext(...)");
            Intrinsics.checkNotNullParameter(context12, "context");
            com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b factory6 = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b(context12);
            com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c cVar5 = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a;
            Intrinsics.checkNotNullParameter(cVar5, "<this>");
            Intrinsics.checkNotNullParameter(settings, "viewSettings");
            com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d positioner5 = new com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d(settings.getUiButtonsOffset());
            Intrinsics.checkNotNullParameter(factory6, "factory");
            Intrinsics.checkNotNullParameter(positioner5, "positioner");
            com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i iVar3 = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i(factory6, positioner5);
            Context context13 = parentView.getContext();
            Intrinsics.checkNotNullExpressionValue(context13, "getContext(...)");
            Intrinsics.checkNotNullParameter(context13, "context");
            com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b factory7 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b(context13);
            Intrinsics.checkNotNullParameter(cVar5, "<this>");
            com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h positioner6 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h();
            Intrinsics.checkNotNullParameter(factory7, "factory");
            Intrinsics.checkNotNullParameter(positioner6, "positioner");
            com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e eVar3 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e(factory7, positioner6);
            Context context14 = parentView.getContext();
            Intrinsics.checkNotNullExpressionValue(context14, "getContext(...)");
            com.scandit.datacapture.barcode.internal.module.pick.ui.f loadingViewFactory3 = com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.a.b(context11, settings);
            Context context15 = parentView.getContext();
            Intrinsics.checkNotNullExpressionValue(context15, "getContext(...)");
            Intrinsics.checkNotNullParameter(context15, "context");
            com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k guidanceViewFactory3 = new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k(context15);
            Intrinsics.checkNotNullParameter(context14, "context");
            Intrinsics.checkNotNullParameter(loadingViewFactory3, "loadingViewFactory");
            Intrinsics.checkNotNullParameter(guidanceViewFactory3, "guidanceViewFactory");
            return new BarcodePickView(context11, parentView, dataCaptureContext, mode, settings, a7, cache, aVar6, jVar, iVar3, eVar3, new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.g(context14, loadingViewFactory3, guidanceViewFactory3), container);
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
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.scandit.datacapture.barcode.pick.ui.BarcodePickView$_internalPropertyManager$1] */
    public BarcodePickView(Context context, ViewGroup parentView, DataCaptureContext dataCaptureContext, BarcodePick mode, BarcodePickViewSettings viewSettings, CameraManager cameraManager, BarcodePickElementsCache elementsRequestor, BarcodePickDrawer highlightDrawer, BarcodePickDataCaptureViewWrapper captureViewWrapper, ShutterButtonWrapper shutterButtonWrapper, ExitButtonWrapper exitButtonWrapper, BarcodePickGuidanceHandler guidanceHandler, BarcodePickBasicOverlay barcodePickBasicOverlay) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        Intrinsics.checkNotNullParameter(elementsRequestor, "elementsHandler");
        Intrinsics.checkNotNullParameter(highlightDrawer, "drawer");
        Intrinsics.checkNotNullParameter(captureViewWrapper, "captureViewWrapper");
        Intrinsics.checkNotNullParameter(shutterButtonWrapper, "shutterButtonWrapper");
        Intrinsics.checkNotNullParameter(exitButtonWrapper, "exitButtonWrapper");
        Intrinsics.checkNotNullParameter(guidanceHandler, "guidanceHandler");
        Intrinsics.checkNotNullParameter(barcodePickBasicOverlay, "barcodePickBasicOverlay");
        this.a = dataCaptureContext;
        this.b = mode;
        this.c = cameraManager;
        this.d = highlightDrawer;
        this.e = captureViewWrapper;
        this.f = shutterButtonWrapper;
        this.g = exitButtonWrapper;
        this.h = guidanceHandler;
        this.i = barcodePickBasicOverlay;
        this.l = new InternalPropertyManager() { // from class: com.scandit.datacapture.barcode.pick.ui.BarcodePickView$_internalPropertyManager$1
            @Override // com.scandit.datacapture.barcode.internal.sdk.ui.InternalPropertyManager
            public void _setProperty(String key, Object value) {
                BarcodePickDataCaptureViewWrapper barcodePickDataCaptureViewWrapper;
                BarcodePickDataCaptureViewWrapper barcodePickDataCaptureViewWrapper2;
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(key, "add_overlay") && (value instanceof DataCaptureOverlay)) {
                    barcodePickDataCaptureViewWrapper2 = BarcodePickView.this.e;
                    barcodePickDataCaptureViewWrapper2.b((DataCaptureOverlay) value);
                } else if (Intrinsics.areEqual(key, "remove_overlay") && (value instanceof DataCaptureOverlay)) {
                    barcodePickDataCaptureViewWrapper = BarcodePickView.this.e;
                    barcodePickDataCaptureViewWrapper.a((DataCaptureOverlay) value);
                }
            }
        };
        this.m = new MainThreadHelperImpl();
        v onStartScanning = new v(this);
        w onStopScanning = new w(this);
        x onFreezeScanning = new x(this);
        y onReleaseScanning = new y(this);
        z onResetScanning = new z(this);
        com.scandit.datacapture.barcode.internal.module.shared.b initialScanState = com.scandit.datacapture.barcode.internal.module.shared.b.a;
        Intrinsics.checkNotNullParameter(onStartScanning, "onStartScanning");
        Intrinsics.checkNotNullParameter(onStopScanning, "onStopScanning");
        Intrinsics.checkNotNullParameter(onFreezeScanning, "onFreezeScanning");
        Intrinsics.checkNotNullParameter(onReleaseScanning, "onReleaseScanning");
        Intrinsics.checkNotNullParameter(onResetScanning, "onResetScanning");
        Intrinsics.checkNotNullParameter(initialScanState, "initialScanState");
        this.n = new com.scandit.datacapture.barcode.internal.module.shared.c(onStartScanning, onStopScanning, onFreezeScanning, onReleaseScanning, onResetScanning, initialScanState);
        m onDrawerDataUpdatedWithStatusIconsShown = new m(this);
        o runOnMain = new o(this);
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(runOnMain, "runOnMain");
        Intrinsics.checkNotNullParameter(onDrawerDataUpdatedWithStatusIconsShown, "onDrawerDataUpdatedWithStatusIconsShown");
        Intrinsics.checkNotNullParameter(elementsRequestor, "elementsRequestor");
        Intrinsics.checkNotNullParameter(highlightDrawer, "highlightDrawer");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f fVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f(mode, runOnMain, onDrawerDataUpdatedWithStatusIconsShown, elementsRequestor, highlightDrawer);
        this.o = fVar;
        mode._addStatusListener$scandit_barcode_capture(new C0496a(this));
        setClipChildren(false);
        setClipToPadding(false);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        ViewParent parent = getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
        parentView.addView(this, new ViewGroup.LayoutParams(-1, -1));
        captureViewWrapper.a(dataCaptureContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        Unit unit = Unit.INSTANCE;
        captureViewWrapper.a(this, layoutParams);
        captureViewWrapper.a(fVar);
        cameraManager.a(dataCaptureContext);
        shutterButtonWrapper.a(new b(this));
        shutterButtonWrapper.a(this, new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.g(viewSettings.getShowPauseButton()));
        exitButtonWrapper.a(new c(this));
        exitButtonWrapper.a(this, new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.c(viewSettings.getShowFinishButton()));
        if (viewSettings.getHardwareTriggerEnabled()) {
            HardwareButtonHelperKt.setupHardwareTrigger(this, viewSettings.getHardwareTriggerKeyCode(), new d(this));
        }
        barcodePickBasicOverlay.a(guidanceHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodePickView this$0) {
        com.scandit.datacapture.barcode.internal.module.shared.b bVar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.a(new l(this$0));
        this$0.f.a();
        this$0.g.a();
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this$0.n;
        synchronized (cVar) {
            bVar = cVar.g;
        }
        if (bVar == com.scandit.datacapture.barcode.internal.module.shared.b.d) {
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f fVar = this$0.o;
            NativeBarcodePickSession session = fVar.a._impl$scandit_barcode_capture().getSession();
            if (session == null) {
                return;
            }
            fVar.a(new com.scandit.datacapture.barcode.internal.module.pick.capture.v(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.d(session)));
        }
    }

    public static final void access$_freezeScanning(BarcodePickView barcodePickView) {
        barcodePickView.b._freeze$scandit_barcode_capture();
        ViewExtensionsKt.runOnMainThread(barcodePickView, new f(barcodePickView));
    }

    public static final void access$_releaseScanning(BarcodePickView barcodePickView) {
        barcodePickView.b._stop$scandit_barcode_capture();
        ViewExtensionsKt.runOnMainThread(barcodePickView, new g(barcodePickView));
    }

    public static final void access$_resetScanning(BarcodePickView barcodePickView) {
        barcodePickView.b._reset$scandit_barcode_capture();
        ViewExtensionsKt.runOnMainThread(barcodePickView, new h(barcodePickView));
    }

    public static final void access$_startScanning(BarcodePickView barcodePickView) {
        barcodePickView.a.setMode(barcodePickView.b._asDataCaptureMode$scandit_barcode_capture());
        barcodePickView.b._start$scandit_barcode_capture();
        ViewExtensionsKt.runOnMainThread(barcodePickView, new i(barcodePickView));
    }

    public static final void access$_stopScanning(BarcodePickView barcodePickView) {
        barcodePickView.b._pause$scandit_barcode_capture();
        ViewExtensionsKt.runOnMainThread(barcodePickView, new j(barcodePickView));
        barcodePickView.a.removeMode(barcodePickView.b._asDataCaptureMode$scandit_barcode_capture());
    }

    public static final void access$freezeInternal(BarcodePickView barcodePickView) {
        barcodePickView.b._notifyStatusShownWhilePausedUpdated$scandit_barcode_capture(barcodePickView.d.a());
        barcodePickView.e.a();
        barcodePickView.d.b();
    }

    public static final void access$onDrawerDataUpdated(BarcodePickView barcodePickView, boolean z) {
        com.scandit.datacapture.barcode.internal.module.shared.b bVar;
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = barcodePickView.n;
        synchronized (cVar) {
            bVar = cVar.g;
        }
        bVar.getClass();
        if (bVar == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
            return;
        }
        barcodePickView.b._notifyStatusShownWhilePausedUpdated$scandit_barcode_capture(z);
    }

    public static final void access$onExitButtonClick(BarcodePickView barcodePickView) {
        BarcodePickViewUiListener barcodePickViewUiListener = barcodePickView.uiListener;
        if (barcodePickViewUiListener != null) {
            barcodePickViewUiListener.onFinishButtonTapped(barcodePickView);
        }
    }

    public static final void access$onHardwareKeyEvent(BarcodePickView barcodePickView, HardwareKeyEvent hardwareKeyEvent) {
        barcodePickView.getClass();
        if (WhenMappings.$EnumSwitchMapping$0[hardwareKeyEvent.ordinal()] == 1) {
            barcodePickView.a();
        }
    }

    public static final void access$releaseInternal(BarcodePickView barcodePickView) {
        barcodePickView.d.stop();
        barcodePickView.removeView(barcodePickView.h.getView());
        barcodePickView.e.a(barcodePickView.i);
        barcodePickView.b._removeListener$scandit_barcode_capture(barcodePickView.o);
        barcodePickView.c.a(com.scandit.datacapture.barcode.internal.module.pick.capture.y.a);
    }

    public static final void access$resetStateInternal(BarcodePickView barcodePickView) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f fVar = barcodePickView.o;
        NativeBarcodePickSession session = fVar.a._impl$scandit_barcode_capture().getSession();
        if (session == null) {
            return;
        }
        fVar.a(new com.scandit.datacapture.barcode.internal.module.pick.capture.v(new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.d(session)));
    }

    public static final void access$startInternal(BarcodePickView barcodePickView) {
        barcodePickView.e.b();
        if (barcodePickView.h.getView().getParent() == null) {
            barcodePickView.addView(barcodePickView.h.getView(), new ViewGroup.LayoutParams(-1, -1));
        }
        barcodePickView.d.start();
        barcodePickView.b._notifyStatusShownWhilePausedUpdated$scandit_barcode_capture(false);
        barcodePickView.e.b(barcodePickView.i);
        barcodePickView.b._addListener$scandit_barcode_capture(barcodePickView.o);
    }

    public static final void access$stopInternal(BarcodePickView barcodePickView) {
        barcodePickView.d.stop();
        barcodePickView.removeView(barcodePickView.h.getView());
        barcodePickView.e.a(barcodePickView.i);
        barcodePickView.b._removeListener$scandit_barcode_capture(barcodePickView.o);
    }

    public static final boolean getHardwareTriggerSupported() {
        return INSTANCE.getHardwareTriggerSupported();
    }

    @JvmStatic
    public static final BarcodePickView newInstance(ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePick barcodePick, BarcodePickViewSettings barcodePickViewSettings) {
        return INSTANCE.newInstance(viewGroup, dataCaptureContext, barcodePick, barcodePickViewSettings);
    }

    public final void _applySettings$scandit_barcode_capture(BarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        this.i.a(viewSettings);
        ViewExtensionsKt.runOnMainThread(this, new e(this, viewSettings));
    }

    public final void addActionListener(BarcodePickActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b._addActionListener$scandit_barcode_capture(listener);
    }

    public final void freeze() {
        this.n.a();
    }

    public final BarcodePickViewListener getListener() {
        return this.listener;
    }

    public final BarcodePickViewUiListener getUiListener() {
        return this.uiListener;
    }

    public final InternalPropertyManager get_internalPropertyManager() {
        return this.l;
    }

    public final void onDestroy() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.n;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.d;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.e();
            }
            cVar.d.invoke();
        }
    }

    public final void onPause() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.n;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.c;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.b.invoke();
            }
        }
    }

    public final void onResume() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.n;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.b;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.a.invoke();
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.pick.ui.BarcodePickView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodePickView.a(BarcodePickView.this);
            }
        });
    }

    public final void onStatusListenerFreezed$scandit_barcode_capture() {
        this.m.runOnMainThread(new p(this));
        ViewExtensionsKt.runOnMainThread(this, new q(this));
    }

    public final void onStatusListenerPaused$scandit_barcode_capture() {
        this.m.runOnMainThread(new r(this));
    }

    public final void onStatusListenerStarted$scandit_barcode_capture() {
        this.m.runOnMainThread(new s(this));
        ViewExtensionsKt.runOnMainThread(this, new t(this));
    }

    public final void onStatusListenerStopped$scandit_barcode_capture() {
        this.m.runOnMainThread(new u(this));
    }

    public final void removeActionListener(BarcodePickActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b._removeActionListener$scandit_barcode_capture(listener);
    }

    public final void reset() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.n;
        synchronized (cVar) {
            cVar.e.invoke();
        }
    }

    public final void setListener(BarcodePickViewListener barcodePickViewListener) {
        this.listener = barcodePickViewListener;
    }

    public final void setUiListener(BarcodePickViewUiListener barcodePickViewUiListener) {
        this.uiListener = barcodePickViewUiListener;
    }

    public final void start() {
        this.n.d();
    }

    public final void stop() {
        this.n.e();
    }

    @JvmStatic
    public static final BarcodePickView newInstance(ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePick barcodePick, BarcodePickViewSettings barcodePickViewSettings, CameraSettings cameraSettings) {
        return INSTANCE.newInstance(viewGroup, dataCaptureContext, barcodePick, barcodePickViewSettings, cameraSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.n;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.a();
            } else {
                cVar.d();
            }
        }
    }
}
