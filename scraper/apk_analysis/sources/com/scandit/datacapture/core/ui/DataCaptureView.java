package com.scandit.datacapture.core.ui;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.getcapacitor.Bridge;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.push.g;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.SizeWithUnit;
import com.scandit.datacapture.core.internal.module.ui.DataCaptureTextureView;
import com.scandit.datacapture.core.internal.module.ui.ErrorOverlay;
import com.scandit.datacapture.core.internal.module.ui.GestureRecognizer;
import com.scandit.datacapture.core.internal.module.ui.NativeContextStatusListener;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.module.ui.ViewSizeAndRotation;
import com.scandit.datacapture.core.internal.module.ui.control.layout.ControlLayout;
import com.scandit.datacapture.core.internal.module.ui.hint.j;
import com.scandit.datacapture.core.internal.module.ui.hint.k;
import com.scandit.datacapture.core.internal.module.ui.hint.q;
import com.scandit.datacapture.core.internal.module.ui.hint.z;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.common.NativeContextStatus;
import com.scandit.datacapture.core.internal.sdk.common.NativeError;
import com.scandit.datacapture.core.internal.sdk.extensions.AnchorExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.RotationExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter;
import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintPresenterV2;
import com.scandit.datacapture.core.internal.sdk.ui.notification.NotificationPresenterImpl;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.ui.control.CameraSwitchControl;
import com.scandit.datacapture.core.ui.control.Control;
import com.scandit.datacapture.core.ui.control.LinearControlGroup;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import com.scandit.datacapture.core.ui.control.ZoomSwitchControl;
import com.scandit.datacapture.core.ui.gesture.FocusGesture;
import com.scandit.datacapture.core.ui.gesture.SwipeToZoom;
import com.scandit.datacapture.core.ui.gesture.TapToFocus;
import com.scandit.datacapture.core.ui.gesture.ZoomGesture;
import com.scandit.datacapture.core.ui.notification.NotificationPresenter;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\t\b\u0007\u0018\u0000 ¾\u00012\u00020\u00012\u00020\u0002:\u0002¾\u0001B7\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014H\u0097\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u0018J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u001a\u0010\u0013J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001bH\u0097\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u001f\u0010\u0018J\u0018\u0010\"\u001a\u00020\u00112\u0006\u0010!\u001a\u00020 H\u0097\u0001¢\u0006\u0004\b\"\u0010#J\u0015\u0010&\u001a\u00020\u00112\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020\u0011H\u0000¢\u0006\u0004\b(\u0010\u0018J\u000f\u0010+\u001a\u00020\u0011H\u0000¢\u0006\u0004\b*\u0010\u0018J\u000f\u0010-\u001a\u00020\u0011H\u0000¢\u0006\u0004\b,\u0010\u0018J\u0015\u0010.\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b.\u0010\u0013J\u0015\u0010/\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b/\u0010\u0013J\u000f\u00100\u001a\u00020\u0011H\u0014¢\u0006\u0004\b0\u0010\u0018J\u000f\u00101\u001a\u00020\u0011H\u0014¢\u0006\u0004\b1\u0010\u0018J\u0015\u00103\u001a\u00020\u00112\u0006\u0010!\u001a\u000202¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020\u00112\u0006\u0010!\u001a\u000202¢\u0006\u0004\b5\u00104J\u0015\u00103\u001a\u00020\u00112\u0006\u0010!\u001a\u000206¢\u0006\u0004\b3\u00107J\u0015\u00105\u001a\u00020\u00112\u0006\u0010!\u001a\u000206¢\u0006\u0004\b5\u00107J/\u0010=\u001a\u00020\u00112\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u0002082\u0006\u0010;\u001a\u0002082\u0006\u0010<\u001a\u000208H\u0014¢\u0006\u0004\b=\u0010>J\u0015\u0010A\u001a\u00020?2\u0006\u0010@\u001a\u00020?¢\u0006\u0004\bA\u0010BJ\u0015\u0010E\u001a\u00020C2\u0006\u0010D\u001a\u00020C¢\u0006\u0004\bE\u0010FJ\u001d\u0010K\u001a\u00020\u00112\u0006\u0010H\u001a\u00020G2\u0006\u0010J\u001a\u00020I¢\u0006\u0004\bK\u0010LJ\u0015\u0010O\u001a\u00020\u00112\u0006\u0010N\u001a\u00020M¢\u0006\u0004\bO\u0010PJ%\u0010O\u001a\u00020\u00112\u0006\u0010N\u001a\u00020M2\u0006\u0010R\u001a\u00020Q2\u0006\u0010T\u001a\u00020S¢\u0006\u0004\bO\u0010UJ\u0015\u0010V\u001a\u00020\u00112\u0006\u0010N\u001a\u00020M¢\u0006\u0004\bV\u0010PJ\u000f\u0010X\u001a\u00020\u0011H\u0000¢\u0006\u0004\bW\u0010\u0018R&\u0010_\u001a\b\u0012\u0004\u0012\u00020\u000f0Y8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bZ\u0010[\u0012\u0004\b^\u0010\u0018\u001a\u0004\b\\\u0010]R \u0010f\u001a\u00020`8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\ba\u0010b\u0012\u0004\be\u0010\u0018\u001a\u0004\bc\u0010dR\u0014\u0010%\u001a\u00020g8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b%\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bj\u0010kR\u0016\u0010m\u001a\u00020l8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0017\u0010t\u001a\u00020o8\u0006¢\u0006\f\n\u0004\bp\u0010q\u001a\u0004\br\u0010sR1\u0010~\u001a\u00020u2\u0006\u0010v\u001a\u00020u8@@@X\u0081\u008e\u0002¢\u0006\u0018\n\u0004\bw\u0010x\u0012\u0004\b}\u0010\u0018\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R3\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010J\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0086\u000e¢\u0006\u0017\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R7\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u0085\u00012\t\u0010J\u001a\u0005\u0018\u00010\u0085\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u0086\u0001\u0010\u0087\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R7\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u008d\u00012\t\u0010J\u001a\u0005\u0018\u00010\u008d\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u008e\u0001\u0010\u008f\u0001\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u0006\b\u0092\u0001\u0010\u0093\u0001R*\u0010\u009c\u0001\u001a\u00030\u0095\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0096\u0001\u0010\u0097\u0001\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001\"\u0006\b\u009a\u0001\u0010\u009b\u0001R)\u0010¡\u0001\u001a\u00020Q2\u0006\u0010v\u001a\u00020Q8W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001R)\u0010¦\u0001\u001a\u00020S2\u0006\u0010v\u001a\u00020S8W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b¢\u0001\u0010£\u0001\"\u0006\b¤\u0001\u0010¥\u0001R+\u0010¬\u0001\u001a\u00030§\u00012\u0007\u0010v\u001a\u00030§\u00018W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R)\u0010¯\u0001\u001a\u00020S2\u0006\u0010v\u001a\u00020S8W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b\u00ad\u0001\u0010£\u0001\"\u0006\b®\u0001\u0010¥\u0001R+\u0010µ\u0001\u001a\u00030°\u00012\u0007\u0010v\u001a\u00030°\u00018W@WX\u0096\u000f¢\u0006\u0010\u001a\u0006\b±\u0001\u0010²\u0001\"\u0006\b³\u0001\u0010´\u0001R%\u0010º\u0001\u001a\t\u0012\u0004\u0012\u00020M0¶\u00018@X\u0081\u0004¢\u0006\u000f\u0012\u0005\b¹\u0001\u0010\u0018\u001a\u0006\b·\u0001\u0010¸\u0001R+\u0010½\u0001\u001a\u00030\u0095\u00012\u0007\u0010J\u001a\u00030\u0095\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b»\u0001\u0010\u0099\u0001\"\u0006\b¼\u0001\u0010\u009b\u0001¨\u0006¿\u0001"}, d2 = {"Lcom/scandit/datacapture/core/ui/DataCaptureView;", "Landroid/widget/RelativeLayout;", "Lcom/scandit/datacapture/core/ui/DataCaptureViewProxy;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/core/internal/module/ui/video/NativeVideoPreview;", "videoPreview", "Lcom/scandit/datacapture/core/internal/sdk/ui/ContextStatusPresenter;", "contextStatusPresenter", "", "pixelsPerDip", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/core/internal/module/ui/video/NativeVideoPreview;Lcom/scandit/datacapture/core/internal/sdk/ui/ContextStatusPresenter;F)V", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "overlay", "", "_addOverlay", "(Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "_impl", "()Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", "_performUiTriggeredZoomIn", "()V", "_performUiTriggeredZoomOut", "_removeOverlay", "Lcom/scandit/datacapture/core/internal/module/ui/GestureRecognizer;", "recognizer", "_setGestureRecognizer", "(Lcom/scandit/datacapture/core/internal/module/ui/GestureRecognizer;)V", "_setNeedsRedraw", "Lcom/scandit/datacapture/core/internal/sdk/ui/NeedsRedrawListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_setNeedsRedrawListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/NeedsRedrawListener;)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/HintHolderV2;", "hintHolder", "_overrideHintHolderV2", "(Lcom/scandit/datacapture/core/internal/sdk/ui/hint/HintHolderV2;)V", "onSurfaceTextureAvailable$scandit_capture_core", "onSurfaceTextureAvailable", "onSurfaceTextureDestroyed$scandit_capture_core", "onSurfaceTextureDestroyed", "onSurfaceTextureUpdated$scandit_capture_core", "onSurfaceTextureUpdated", "addOverlay", "removeOverlay", "onAttachedToWindow", "onDetachedFromWindow", "Lcom/scandit/datacapture/core/ui/DataCaptureViewListener;", "addListener", "(Lcom/scandit/datacapture/core/ui/DataCaptureViewListener;)V", "removeListener", "Lcom/scandit/datacapture/core/ui/DataCaptureViewSizeListener;", "(Lcom/scandit/datacapture/core/ui/DataCaptureViewSizeListener;)V", "", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", "Lcom/scandit/datacapture/core/common/geometry/Point;", "point", "mapFramePointToView", "(Lcom/scandit/datacapture/core/common/geometry/Point;)Lcom/scandit/datacapture/core/common/geometry/Point;", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "quadrilateral", "mapFrameQuadrilateralToView", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "", "name", "", "value", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "Lcom/scandit/datacapture/core/ui/control/Control;", "control", "addControl", "(Lcom/scandit/datacapture/core/ui/control/Control;)V", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "anchor", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", TypedValues.CycleType.S_WAVE_OFFSET, "(Lcom/scandit/datacapture/core/ui/control/Control;Lcom/scandit/datacapture/core/common/geometry/Anchor;Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "removeControl", "removeAllControls$scandit_capture_core", "removeAllControls", "", "f", "Ljava/util/Set;", "get_overlays", "()Ljava/util/Set;", "get_overlays$annotations", "_overlays", "Lcom/scandit/datacapture/core/internal/module/ui/control/layout/ControlLayout;", "j", "Lcom/scandit/datacapture/core/internal/module/ui/control/layout/ControlLayout;", "getControlLayout$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/module/ui/control/layout/ControlLayout;", "getControlLayout$scandit_capture_core$annotations", "controlLayout", "Lcom/scandit/datacapture/core/internal/module/ui/hint/q;", "Lcom/scandit/datacapture/core/internal/module/ui/hint/q;", "Lcom/scandit/datacapture/core/internal/module/ui/hint/z;", "hintPresenter", "Lcom/scandit/datacapture/core/internal/module/ui/hint/z;", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/HintPresenterV2;", "hintPresenterV2", "Lcom/scandit/datacapture/core/internal/sdk/ui/hint/HintPresenterV2;", "Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "l", "Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "get_notificationPresenter", "()Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "_notificationPresenter", "Lcom/scandit/datacapture/core/internal/module/ui/ViewSizeAndRotation;", "<set-?>", "q", "Lkotlin/properties/ReadWriteProperty;", "getCurrentSize$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/module/ui/ViewSizeAndRotation;", "setCurrentSize$scandit_capture_core", "(Lcom/scandit/datacapture/core/internal/module/ui/ViewSizeAndRotation;)V", "getCurrentSize$scandit_capture_core$annotations", "currentSize", "s", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getDataCaptureContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "setDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "Lcom/scandit/datacapture/core/ui/gesture/FocusGesture;", g.g, "Lcom/scandit/datacapture/core/ui/gesture/FocusGesture;", "getFocusGesture", "()Lcom/scandit/datacapture/core/ui/gesture/FocusGesture;", "setFocusGesture", "(Lcom/scandit/datacapture/core/ui/gesture/FocusGesture;)V", "focusGesture", "Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;", Bridge.CAPACITOR_HTTP_INTERCEPTOR_URL_PARAM, "Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;", "getZoomGesture", "()Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;", "setZoomGesture", "(Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;)V", "zoomGesture", "", "v", "Z", "get_optimizesRendering$scandit_capture_core", "()Z", "set_optimizesRendering$scandit_capture_core", "(Z)V", "_optimizesRendering", "getLogoAnchor", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setLogoAnchor", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "logoAnchor", "getLogoOffset", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "setLogoOffset", "(Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "logoOffset", "Lcom/scandit/datacapture/core/ui/LogoStyle;", "getLogoStyle", "()Lcom/scandit/datacapture/core/ui/LogoStyle;", "setLogoStyle", "(Lcom/scandit/datacapture/core/ui/LogoStyle;)V", "logoStyle", "getPointOfInterest", "setPointOfInterest", "pointOfInterest", "Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "getScanAreaMargins", "()Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "setScanAreaMargins", "(Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;)V", "scanAreaMargins", "", "getControls$scandit_capture_core", "()Ljava/util/Collection;", "getControls$scandit_capture_core$annotations", "controls", "getShouldShowZoomNotification", "setShouldShowZoomNotification", "shouldShowZoomNotification", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class DataCaptureView extends RelativeLayout implements DataCaptureViewProxy {
    private final ContextStatusPresenter a;
    private final float b;
    private final /* synthetic */ DataCaptureViewProxyAdapter c;
    private final CopyOnWriteArraySet d;
    private final CopyOnWriteArraySet e;
    private final CopyOnWriteArraySet f;
    private final DataCaptureTextureView g;
    private final FrameLayout h;
    private final q hintHolder;
    private final z hintPresenter;
    private HintPresenterV2 hintPresenterV2;
    private boolean i;

    /* renamed from: j, reason: from kotlin metadata */
    private final ControlLayout controlLayout;
    private HintHolderV2 k;
    private final NotificationPresenterImpl l;
    private final ErrorOverlay m;
    private final DataCaptureView$contextStatusView$1 n;
    private final DataCaptureView$orientationEventListener$1 o;
    private int p;
    private final DataCaptureView$special$$inlined$distinctObservable$1 q;
    private final DataCaptureView$dataCaptureContextListener$1 r;

    /* renamed from: s, reason: from kotlin metadata */
    private DataCaptureContext dataCaptureContext;

    /* renamed from: t, reason: from kotlin metadata */
    private FocusGesture focusGesture;

    /* renamed from: u, reason: from kotlin metadata */
    private ZoomGesture zoomGesture;

    /* renamed from: v, reason: from kotlin metadata */
    private boolean _optimizesRendering;
    static final /* synthetic */ KProperty[] w = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(DataCaptureView.class), "currentSize", "getCurrentSize$scandit_capture_core()Lcom/scandit/datacapture/core/internal/module/ui/ViewSizeAndRotation;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/core/ui/DataCaptureView$Companion;", "", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "newInstance", "(Landroid/content/Context;Lcom/scandit/datacapture/core/capture/DataCaptureContext;)Lcom/scandit/datacapture/core/ui/DataCaptureView;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final DataCaptureView newInstance(Context context, DataCaptureContext dataCaptureContext) {
            Intrinsics.checkNotNullParameter(context, "context");
            NativeVideoPreview create = NativeVideoPreview.create();
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return new DataCaptureView(context, dataCaptureContext, create, null, 0.0f, 24, null);
        }
    }

    public /* synthetic */ DataCaptureView(Context context, DataCaptureContext dataCaptureContext, NativeVideoPreview nativeVideoPreview, ContextStatusPresenter contextStatusPresenter, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, dataCaptureContext, nativeVideoPreview, (i & 8) != 0 ? new com.scandit.datacapture.core.internal.module.ui.a() : contextStatusPresenter, (i & 16) != 0 ? context.getResources().getDisplayMetrics().density : f);
    }

    public static final void access$updateFrameOfReferenceViewSizeAndOrientation(DataCaptureView dataCaptureView, int i, int i2, int i3) {
        dataCaptureView.getClass();
        int angle = RotationExtensionsKt.toAngle(i3);
        MeasureUnit measureUnit = MeasureUnit.PIXEL;
        SizeWithUnit sizeWithUnit = new SizeWithUnit(new FloatWithUnit(i, measureUnit), new FloatWithUnit(i2, measureUnit));
        dataCaptureView.getA().setPreviewOrientation(angle);
        NativeDataCaptureContext dataCaptureContext = dataCaptureView.getA().getDataCaptureContext();
        if (dataCaptureContext != null) {
            dataCaptureContext.setFrameOfReferenceViewSizeAndOrientation(sizeWithUnit, dataCaptureView.b, angle);
        }
        Iterator it = dataCaptureView.d.iterator();
        while (it.hasNext()) {
            ((DataCaptureViewListener) it.next()).onSizeChanged(i, i2, i3);
        }
        Iterator it2 = dataCaptureView.e.iterator();
        while (it2.hasNext()) {
            ((DataCaptureViewSizeListener) it2.next()).onSizeChanged(dataCaptureView, i, i2, i3);
        }
    }

    public static /* synthetic */ void getControlLayout$scandit_capture_core$annotations() {
    }

    public static /* synthetic */ void getControls$scandit_capture_core$annotations() {
    }

    public static /* synthetic */ void getCurrentSize$scandit_capture_core$annotations() {
    }

    public static /* synthetic */ void get_overlays$annotations() {
    }

    @JvmStatic
    public static final DataCaptureView newInstance(Context context, DataCaptureContext dataCaptureContext) {
        return INSTANCE.newInstance(context, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "addOverlay")
    public void _addOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.c._addOverlay(overlay);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeDataCaptureView getA() {
        return this.c.getA();
    }

    public final void _overrideHintHolderV2(HintHolderV2 hintHolder) {
        Intrinsics.checkNotNullParameter(hintHolder, "hintHolder");
        ViewExtensionsKt.removeFromSuperview(this.k.asView());
        this.k = hintHolder;
        this.hintPresenterV2 = new HintPresenterV2(hintHolder);
        getA().setHintPresenterV2(this.hintPresenterV2.get_impl());
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "performUiTriggeredZoomIn")
    public void _performUiTriggeredZoomIn() {
        this.c._performUiTriggeredZoomIn();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "performUiTriggeredZoomOut")
    public void _performUiTriggeredZoomOut() {
        this.c._performUiTriggeredZoomOut();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "removeOverlay")
    public void _removeOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.c._removeOverlay(overlay);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "setGestureRecognizer")
    public void _setGestureRecognizer(GestureRecognizer recognizer) {
        Intrinsics.checkNotNullParameter(recognizer, "recognizer");
        this.c._setGestureRecognizer(recognizer);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "setNeedsRedraw")
    public void _setNeedsRedraw() {
        this.c._setNeedsRedraw();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(nativeName = "setNeedsRedrawDelegate")
    public void _setNeedsRedrawListener(NeedsRedrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c._setNeedsRedrawListener(listener);
    }

    public final void addControl(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        int i = ControlLayout.c;
        Intrinsics.checkNotNullParameter(control, "control");
        Anchor anchor = control instanceof TorchSwitchControl ? Anchor.TOP_LEFT : control instanceof CameraSwitchControl ? Anchor.TOP_RIGHT : control instanceof ZoomSwitchControl ? Anchor.BOTTOM_RIGHT : control instanceof LinearControlGroup ? Anchor.BOTTOM_RIGHT : Anchor.TOP_LEFT;
        addControl(control, anchor, AnchorExtensionsKt.getDefaultOffset(anchor));
    }

    public final void addListener(DataCaptureViewListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.add(listener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        _addOverlay(overlay);
        if (this.f.add(overlay) && (overlay instanceof ViewBasedDataCaptureOverlay)) {
            View view = (View) overlay;
            if (this.h.indexOfChild(view) == -1) {
                this.h.addView(view, new RelativeLayout.LayoutParams(-1, -1));
                ((ViewBasedDataCaptureOverlay) overlay)._setDataCaptureView(this);
            }
        }
    }

    /* renamed from: getControlLayout$scandit_capture_core, reason: from getter */
    public final ControlLayout getControlLayout() {
        return this.controlLayout;
    }

    public final Collection<Control> getControls$scandit_capture_core() {
        return this.controlLayout.a();
    }

    public final ViewSizeAndRotation getCurrentSize$scandit_capture_core() {
        return getValue(this, w[0]);
    }

    public final DataCaptureContext getDataCaptureContext() {
        return this.dataCaptureContext;
    }

    public final FocusGesture getFocusGesture() {
        return this.focusGesture;
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoAnchor")
    public Anchor getLogoAnchor() {
        return this.c.getLogoAnchor();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoOffset")
    public PointWithUnit getLogoOffset() {
        return this.c.getLogoOffset();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoStyle")
    public LogoStyle getLogoStyle() {
        return this.c.getLogoStyle();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "pointOfInterest")
    public PointWithUnit getPointOfInterest() {
        return this.c.getPointOfInterest();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "scanAreaMargins")
    public MarginsWithUnit getScanAreaMargins() {
        return this.c.getScanAreaMargins();
    }

    public final boolean getShouldShowZoomNotification() {
        return getA().isZoomNotificationShowingAllowed();
    }

    public final ZoomGesture getZoomGesture() {
        return this.zoomGesture;
    }

    public final NotificationPresenter get_notificationPresenter() {
        return this.l;
    }

    /* renamed from: get_optimizesRendering$scandit_capture_core, reason: from getter */
    public final boolean get_optimizesRendering() {
        return this._optimizesRendering;
    }

    public final Set<DataCaptureOverlay> get_overlays() {
        return this.f;
    }

    public final Point mapFramePointToView(Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        Point mapFramePointToView = getA().mapFramePointToView(point);
        return new Point(mapFramePointToView.getX() * this.b, mapFramePointToView.getY() * this.b);
    }

    public final Quadrilateral mapFrameQuadrilateralToView(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point mapFramePointToView = mapFramePointToView(topLeft);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point mapFramePointToView2 = mapFramePointToView(topRight);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point mapFramePointToView3 = mapFramePointToView(bottomRight);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(mapFramePointToView, mapFramePointToView2, mapFramePointToView3, mapFramePointToView(bottomLeft));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.setView(this.n);
        enable();
        getA().setContextStatusListener(new NativeContextStatusListener() { // from class: com.scandit.datacapture.core.ui.DataCaptureView$onAttachedToWindow$1
            @Override // com.scandit.datacapture.core.internal.module.ui.NativeContextStatusListener
            public void onStatusChanged(NativeContextStatus status) {
                ContextStatusPresenter contextStatusPresenter;
                Intrinsics.checkNotNullParameter(status, "status");
                contextStatusPresenter = DataCaptureView.this.a;
                contextStatusPresenter.onStatusChanged(new ContextStatus(status));
            }

            @Override // com.scandit.datacapture.core.internal.module.ui.NativeContextStatusListener
            public void onWarningsChanged(ArrayList<NativeError> warnings) {
                ContextStatusPresenter contextStatusPresenter;
                Intrinsics.checkNotNullParameter(warnings, "warnings");
                contextStatusPresenter = DataCaptureView.this.a;
                contextStatusPresenter.onWarningsChanged(warnings);
            }
        });
        getA().attachToWindow();
        if (isHardwareAccelerated()) {
            return;
        }
        String string = getResources().getString(R.string.sc_hardware_acceleration_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Toast.makeText(getContext(), string, 1).show();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        disable();
        this.a.setView(null);
        getA().setContextStatusListener(null);
        getA().detachFromWindow();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w2, int h, int oldw, int oldh) {
        super.onSizeChanged(w2, h, oldw, oldh);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        setCurrentSize$scandit_capture_core(new ViewSizeAndRotation(w2, h, ContextExtensionsKt.getRotation(context)));
    }

    public final synchronized void onSurfaceTextureAvailable$scandit_capture_core() {
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        if (dataCaptureContext != null) {
            if (!this.i) {
                dataCaptureContext.addListener(this.r);
            }
            getA().setDataCaptureContext(dataCaptureContext.getA());
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            setCurrentSize$scandit_capture_core(new ViewSizeAndRotation(measuredWidth, measuredHeight, ContextExtensionsKt.getRotation(context)));
        }
        this.i = true;
    }

    public final synchronized void onSurfaceTextureDestroyed$scandit_capture_core() {
        this.i = false;
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        if (dataCaptureContext != null) {
            dataCaptureContext.removeListener(this.r);
        }
    }

    public final synchronized void onSurfaceTextureUpdated$scandit_capture_core() {
        if (getA().isDisplayingViewfinder()) {
            this.k.setViewFinderRect(getA().getViewfinderRect());
        } else {
            this.k.setViewFinderRect(null);
        }
    }

    public final void removeAllControls$scandit_capture_core() {
        this.controlLayout.c();
    }

    public final void removeControl(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        this.controlLayout.a(control);
    }

    public final void removeListener(DataCaptureViewListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.remove(listener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeOverlay(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        _removeOverlay(overlay);
        this.f.remove(overlay);
        if (overlay instanceof ViewBasedDataCaptureOverlay) {
            this.h.removeView((View) overlay);
            ((ViewBasedDataCaptureOverlay) overlay)._cleanupViews();
        }
    }

    public final void setCurrentSize$scandit_capture_core(ViewSizeAndRotation viewSizeAndRotation) {
        Intrinsics.checkNotNullParameter(viewSizeAndRotation, "<set-?>");
        setValue(this, w[0], viewSizeAndRotation);
    }

    public final void setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        synchronized (this) {
            DataCaptureContext dataCaptureContext2 = this.dataCaptureContext;
            if (dataCaptureContext2 != null && this.i && dataCaptureContext2 != null) {
                dataCaptureContext2.removeListener(this.r);
            }
            this.dataCaptureContext = dataCaptureContext;
            if (dataCaptureContext != null) {
                if (this.i) {
                    if (dataCaptureContext != null) {
                        dataCaptureContext.addListener(this.r);
                    }
                    int measuredWidth = getMeasuredWidth();
                    int measuredHeight = getMeasuredHeight();
                    Context context = getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                    setCurrentSize$scandit_capture_core(new ViewSizeAndRotation(measuredWidth, measuredHeight, ContextExtensionsKt.getRotation(context)));
                }
                NativeDataCaptureView a = getA();
                DataCaptureContext dataCaptureContext3 = this.dataCaptureContext;
                a.setDataCaptureContext(dataCaptureContext3 != null ? dataCaptureContext3.getA() : null);
            }
            Iterator<T> it = getControls$scandit_capture_core().iterator();
            while (it.hasNext()) {
                ((Control) it.next())._onDataCaptureContextChanged(this.dataCaptureContext);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void setFocusGesture(FocusGesture focusGesture) {
        this.focusGesture = focusGesture;
        getA().setFocusGesture(focusGesture != null ? focusGesture.getC() : null);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoAnchor")
    public void setLogoAnchor(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "<set-?>");
        this.c.setLogoAnchor(anchor);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoOffset")
    public void setLogoOffset(PointWithUnit pointWithUnit) {
        Intrinsics.checkNotNullParameter(pointWithUnit, "<set-?>");
        this.c.setLogoOffset(pointWithUnit);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "logoStyle")
    public void setLogoStyle(LogoStyle logoStyle) {
        Intrinsics.checkNotNullParameter(logoStyle, "<set-?>");
        this.c.setLogoStyle(logoStyle);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "pointOfInterest")
    public void setPointOfInterest(PointWithUnit pointWithUnit) {
        Intrinsics.checkNotNullParameter(pointWithUnit, "<set-?>");
        this.c.setPointOfInterest(pointWithUnit);
    }

    public final void setProperty(String name, Object value) {
        Boolean bool;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(name, "logoHidden")) {
            bool = value instanceof Boolean ? (Boolean) value : null;
            if (bool != null) {
                getA().setLogoHidden(bool.booleanValue());
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(name, "optimizesRendering")) {
            bool = value instanceof Boolean ? (Boolean) value : null;
            if (bool != null) {
                this._optimizesRendering = bool.booleanValue();
            }
        }
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewProxy
    @ProxyFunction(property = "scanAreaMargins")
    public void setScanAreaMargins(MarginsWithUnit marginsWithUnit) {
        Intrinsics.checkNotNullParameter(marginsWithUnit, "<set-?>");
        this.c.setScanAreaMargins(marginsWithUnit);
    }

    public final void setShouldShowZoomNotification(boolean z) {
        getA().setZoomNotificationShowingAllowed(z);
    }

    public final void setZoomGesture(ZoomGesture zoomGesture) {
        this.zoomGesture = zoomGesture;
        getA().setZoomGesture(zoomGesture != null ? zoomGesture.getC() : null);
        Iterator<T> it = getControls$scandit_capture_core().iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onZoomGestureChanged(this.zoomGesture);
        }
    }

    public final void set_optimizesRendering$scandit_capture_core(boolean z) {
        this._optimizesRendering = z;
    }

    public final void addListener(DataCaptureViewSizeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.add(listener);
    }

    public final void removeListener(DataCaptureViewSizeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.remove(listener);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.scandit.datacapture.core.ui.DataCaptureView$orientationEventListener$1] */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.scandit.datacapture.core.ui.DataCaptureView$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.scandit.datacapture.core.ui.DataCaptureView$dataCaptureContextListener$1] */
    public DataCaptureView(final Context context, DataCaptureContext dataCaptureContext, NativeVideoPreview videoPreview, ContextStatusPresenter contextStatusPresenter, float f) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoPreview, "videoPreview");
        Intrinsics.checkNotNullParameter(contextStatusPresenter, "contextStatusPresenter");
        this.a = contextStatusPresenter;
        this.b = f;
        NativeDataCaptureView create = NativeDataCaptureView.create(f, videoPreview.asVideoGeometryListener());
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.c = new DataCaptureViewProxyAdapter(create, null, 2, 0 == true ? 1 : 0);
        this.d = new CopyOnWriteArraySet();
        this.e = new CopyOnWriteArraySet();
        this.f = new CopyOnWriteArraySet();
        DataCaptureTextureView dataCaptureTextureView = new DataCaptureTextureView(context, this, videoPreview);
        this.g = dataCaptureTextureView;
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(R.id.sc_overlay_container);
        this.h = frameLayout;
        ControlLayout controlLayout = new ControlLayout(context, new com.scandit.datacapture.core.internal.module.ui.control.layout.d());
        this.controlLayout = controlLayout;
        j jVar = new j(this);
        this.hintHolder = jVar;
        this.hintPresenter = new z(jVar, new k());
        HintHolderV2 implementation = HintHolderV2.INSTANCE.implementation(context);
        this.k = implementation;
        this.hintPresenterV2 = new HintPresenterV2(implementation);
        this.l = new NotificationPresenterImpl(new a(this));
        ErrorOverlay errorOverlay = new ErrorOverlay(context);
        errorOverlay.setVisibility(4);
        this.m = errorOverlay;
        this.n = new DataCaptureView$contextStatusView$1(this);
        this.o = new OrientationEventListener(context, this) { // from class: com.scandit.datacapture.core.ui.DataCaptureView$orientationEventListener$1
            final /* synthetic */ Context a;
            final /* synthetic */ DataCaptureView b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.a = context;
                this.b = this;
            }

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                int i;
                int rotation = ContextExtensionsKt.getRotation(this.a);
                i = this.b.p;
                if (rotation != i) {
                    DataCaptureView dataCaptureView = this.b;
                    dataCaptureView.setCurrentSize$scandit_capture_core(new ViewSizeAndRotation(dataCaptureView.getMeasuredWidth(), this.b.getMeasuredHeight(), rotation));
                    this.b.p = rotation;
                }
            }
        };
        this.p = ContextExtensionsKt.getRotation(context);
        final ViewSizeAndRotation viewSizeAndRotation = new ViewSizeAndRotation(getMeasuredWidth(), getMeasuredHeight(), ContextExtensionsKt.getRotation(context));
        Delegates delegates = Delegates.INSTANCE;
        this.q = new ObservableProperty<ViewSizeAndRotation>(viewSizeAndRotation) { // from class: com.scandit.datacapture.core.ui.DataCaptureView$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, ViewSizeAndRotation oldValue, ViewSizeAndRotation newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                ViewSizeAndRotation viewSizeAndRotation2 = newValue;
                DataCaptureView.access$updateFrameOfReferenceViewSizeAndOrientation(this, viewSizeAndRotation2.b(), viewSizeAndRotation2.c(), viewSizeAndRotation2.a());
            }
        };
        this.r = new DataCaptureContextListener() { // from class: com.scandit.datacapture.core.ui.DataCaptureView$dataCaptureContextListener$1
            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onFrameSourceChanged(DataCaptureContext dataCaptureContext2, FrameSource frameSource) {
                DataCaptureTextureView dataCaptureTextureView2;
                Intrinsics.checkNotNullParameter(dataCaptureContext2, "dataCaptureContext");
                dataCaptureTextureView2 = DataCaptureView.this.g;
                dataCaptureTextureView2.a(frameSource);
                Iterator<T> it = DataCaptureView.this.getControls$scandit_capture_core().iterator();
                while (it.hasNext()) {
                    ((Control) it.next())._onFrameSourceChanged(frameSource);
                }
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onModeAdded(DataCaptureContext dataCaptureContext2, DataCaptureMode dataCaptureMode) {
                DataCaptureContextListener.DefaultImpls.onModeAdded(this, dataCaptureContext2, dataCaptureMode);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onModeRemoved(DataCaptureContext dataCaptureContext2, DataCaptureMode dataCaptureMode) {
                DataCaptureContextListener.DefaultImpls.onModeRemoved(this, dataCaptureContext2, dataCaptureMode);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onObservationStarted(DataCaptureContext dataCaptureContext2) {
                DataCaptureTextureView dataCaptureTextureView2;
                Intrinsics.checkNotNullParameter(dataCaptureContext2, "dataCaptureContext");
                dataCaptureTextureView2 = DataCaptureView.this.g;
                dataCaptureTextureView2.a(dataCaptureContext2.get_frameSource());
                Iterator<T> it = DataCaptureView.this.getControls$scandit_capture_core().iterator();
                while (it.hasNext()) {
                    ((Control) it.next())._onFrameSourceChanged(dataCaptureContext2.get_frameSource());
                }
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onObservationStopped(DataCaptureContext dataCaptureContext2) {
                DataCaptureTextureView dataCaptureTextureView2;
                Intrinsics.checkNotNullParameter(dataCaptureContext2, "dataCaptureContext");
                dataCaptureTextureView2 = DataCaptureView.this.g;
                dataCaptureTextureView2.a((FrameSource) null);
                Iterator<T> it = DataCaptureView.this.getControls$scandit_capture_core().iterator();
                while (it.hasNext()) {
                    ((Control) it.next())._onFrameSourceChanged(null);
                }
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onStatusChanged(DataCaptureContext dataCaptureContext2, ContextStatus contextStatus) {
                DataCaptureContextListener.DefaultImpls.onStatusChanged(this, dataCaptureContext2, contextStatus);
            }
        };
        this._optimizesRendering = true;
        setDataCaptureContext(dataCaptureContext);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        errorOverlay.setVisibility(4);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(dataCaptureTextureView, layoutParams);
        addView(errorOverlay, layoutParams);
        addView(frameLayout, layoutParams);
        addView(controlLayout, layoutParams);
        addView(this.k.asView(), layoutParams);
        for (Control control : getControls$scandit_capture_core()) {
            control._onDataCaptureContextChanged(dataCaptureContext);
            control._onFrameSourceChanged(dataCaptureContext != null ? dataCaptureContext.get_frameSource() : null);
            control._onDataCaptureViewChanged(this);
            control._onZoomGestureChanged(this.zoomGesture);
        }
        getA().setHintPresenter(this.hintPresenter);
        getA().setHintPresenterV2(this.hintPresenterV2.get_impl());
        setFocusGesture(new TapToFocus());
        setZoomGesture(new SwipeToZoom());
    }

    public final void addControl(Control control, Anchor anchor, PointWithUnit offset) {
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        this.controlLayout.a(control, anchor, offset);
        control._onDataCaptureContextChanged(this.dataCaptureContext);
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        control._onFrameSourceChanged(dataCaptureContext != null ? dataCaptureContext.get_frameSource() : null);
        control._onDataCaptureViewChanged(this);
        control._onZoomGestureChanged(this.zoomGesture);
    }
}
