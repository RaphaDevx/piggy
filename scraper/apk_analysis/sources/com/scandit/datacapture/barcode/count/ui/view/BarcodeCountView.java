package com.scandit.datacapture.barcode.count.ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.capture.map.BarcodeCountMappingFlowSettings;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.count.ui.BarcodeCountViewInternalUiListener;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProvider;
import com.scandit.datacapture.barcode.count.ui.view.status.InternalBarcodeCountStatusProvider;
import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareButtonHelperKt;
import com.scandit.datacapture.barcode.hardwarebutton.HardwareKeyEvent;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountCameraHandler;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.s;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalMode;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter;
import com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelper;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ProfilingOverlay;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.LogoStyle;
import com.scandit.datacapture.core.ui.style.Brush;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 ò\u00012\u00020\u0001:\u0010ó\u0001ô\u0001õ\u0001ö\u0001ò\u0001÷\u0001ø\u0001ù\u0001Bw\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010!\u001a\u0004\u0018\u00010\u001eH\u0001¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010#\u001a\u00020\"H\u0014¢\u0006\u0004\b#\u0010$J\u000f\u0010&\u001a\u00020\"H\u0000¢\u0006\u0004\b%\u0010$J\u000f\u0010'\u001a\u00020\"H\u0014¢\u0006\u0004\b'\u0010$J/\u0010-\u001a\u00020\"2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020(H\u0014¢\u0006\u0004\b-\u0010.J\u0015\u00101\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b1\u00102J\r\u00103\u001a\u00020/¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b5\u00102J\r\u00106\u001a\u00020/¢\u0006\u0004\b6\u00104J\u0015\u00107\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b7\u00102J\r\u00108\u001a\u00020/¢\u0006\u0004\b8\u00104J\u0015\u00109\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b9\u00102J\r\u0010:\u001a\u00020/¢\u0006\u0004\b:\u00104J\u0015\u0010;\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b;\u00102J\r\u0010<\u001a\u00020/¢\u0006\u0004\b<\u00104J\u0015\u0010=\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b=\u00102J\r\u0010>\u001a\u00020/¢\u0006\u0004\b>\u00104J\u0015\u0010?\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b?\u00102J\r\u0010@\u001a\u00020/¢\u0006\u0004\b@\u00104J\u0015\u0010A\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\bA\u00102J\r\u0010B\u001a\u00020/¢\u0006\u0004\bB\u00104J\u0015\u0010C\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\bC\u00102J\r\u0010D\u001a\u00020/¢\u0006\u0004\bD\u00104J\u0015\u0010E\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\bE\u00102J\r\u0010F\u001a\u00020/¢\u0006\u0004\bF\u00104J\u0015\u0010I\u001a\u00020\"2\u0006\u0010H\u001a\u00020G¢\u0006\u0004\bI\u0010JJ\u0015\u0010M\u001a\u00020\"2\u0006\u0010L\u001a\u00020K¢\u0006\u0004\bM\u0010NJ\u001f\u0010S\u001a\u00020\"2\u0006\u0010P\u001a\u00020O2\b\u0010R\u001a\u0004\u0018\u00010Q¢\u0006\u0004\bS\u0010TJ\u001f\u0010U\u001a\u00020\"2\u0006\u0010P\u001a\u00020O2\b\u0010R\u001a\u0004\u0018\u00010Q¢\u0006\u0004\bU\u0010TJ\u001f\u0010V\u001a\u00020\"2\u0006\u0010P\u001a\u00020O2\b\u0010R\u001a\u0004\u0018\u00010Q¢\u0006\u0004\bV\u0010TJ\u001f\u0010W\u001a\u00020\"2\u0006\u0010P\u001a\u00020O2\b\u0010R\u001a\u0004\u0018\u00010Q¢\u0006\u0004\bW\u0010TJ\u0017\u0010Y\u001a\u00020\"2\b\u0010X\u001a\u0004\u0018\u00010(¢\u0006\u0004\bY\u0010ZJ\u0015\u0010[\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b[\u00102J\r\u0010\\\u001a\u00020/¢\u0006\u0004\b\\\u00104J\u0015\u0010]\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b]\u00102J\r\u0010^\u001a\u00020/¢\u0006\u0004\b^\u00104J\u0015\u0010_\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b_\u00102J\r\u0010`\u001a\u00020/¢\u0006\u0004\b`\u00104J\u0015\u0010a\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\ba\u00102J\r\u0010b\u001a\u00020/¢\u0006\u0004\bb\u00104J\u0015\u0010c\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\bc\u00102J\r\u0010d\u001a\u00020/¢\u0006\u0004\bd\u00104J\u0015\u0010e\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\be\u00102J\r\u0010f\u001a\u00020/¢\u0006\u0004\bf\u00104J\u0015\u0010g\u001a\u00020\"2\u0006\u00100\u001a\u00020/¢\u0006\u0004\bg\u00102J\r\u0010h\u001a\u00020/¢\u0006\u0004\bh\u00104J\r\u0010i\u001a\u00020\"¢\u0006\u0004\bi\u0010$J\u0015\u0010k\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020j¢\u0006\u0004\bk\u0010lR \u0010s\u001a\u00020m8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bn\u0010o\u0012\u0004\br\u0010$\u001a\u0004\bp\u0010qR$\u0010{\u001a\u0004\u0018\u00010t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bu\u0010v\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR&\u0010\u0081\u0001\u001a\u0004\u0018\u00010K8\u0000@\u0000X\u0080\u000e¢\u0006\u0013\n\u0004\b|\u0010}\u001a\u0004\b~\u0010\u007f\"\u0005\b\u0080\u0001\u0010NR,\u0010\u0088\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001R,\u0010\u008b\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0089\u0001\u0010\u0085\u0001\"\u0006\b\u008a\u0001\u0010\u0087\u0001R,\u0010\u008e\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u008c\u0001\u0010\u0085\u0001\"\u0006\b\u008d\u0001\u0010\u0087\u0001R,\u0010\u0091\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u008f\u0001\u0010\u0085\u0001\"\u0006\b\u0090\u0001\u0010\u0087\u0001R,\u0010\u0094\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0092\u0001\u0010\u0085\u0001\"\u0006\b\u0093\u0001\u0010\u0087\u0001R,\u0010\u0097\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0095\u0001\u0010\u0085\u0001\"\u0006\b\u0096\u0001\u0010\u0087\u0001R,\u0010\u009a\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0098\u0001\u0010\u0085\u0001\"\u0006\b\u0099\u0001\u0010\u0087\u0001R,\u0010\u009d\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u009b\u0001\u0010\u0085\u0001\"\u0006\b\u009c\u0001\u0010\u0087\u0001R,\u0010 \u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u009e\u0001\u0010\u0085\u0001\"\u0006\b\u009f\u0001\u0010\u0087\u0001R,\u0010£\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¡\u0001\u0010\u0085\u0001\"\u0006\b¢\u0001\u0010\u0087\u0001R,\u0010¦\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¤\u0001\u0010\u0085\u0001\"\u0006\b¥\u0001\u0010\u0087\u0001R,\u0010©\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b§\u0001\u0010\u0085\u0001\"\u0006\b¨\u0001\u0010\u0087\u0001R,\u0010¯\u0001\u001a\u00030ª\u00012\b\u0010\u0083\u0001\u001a\u00030ª\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b«\u0001\u0010¬\u0001\"\u0006\b\u00ad\u0001\u0010®\u0001R,\u0010²\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b°\u0001\u0010\u0085\u0001\"\u0006\b±\u0001\u0010\u0087\u0001R,\u0010¸\u0001\u001a\u00030³\u00012\b\u0010\u0083\u0001\u001a\u00030³\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b´\u0001\u0010µ\u0001\"\u0006\b¶\u0001\u0010·\u0001R0\u0010¾\u0001\u001a\u0005\u0018\u00010¹\u00012\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010¹\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bº\u0001\u0010»\u0001\"\u0006\b¼\u0001\u0010½\u0001R9\u0010Ç\u0001\u001a\u0005\u0018\u00010¿\u00012\n\u0010À\u0001\u001a\u0005\u0018\u00010¿\u00018F@FX\u0086\u008e\u0002¢\u0006\u0018\u001a\u0006\bÁ\u0001\u0010Â\u0001\"\u0006\bÃ\u0001\u0010Ä\u0001*\u0006\bÅ\u0001\u0010Æ\u0001R3\u0010Í\u0001\u001a\u00020(2\u0007\u0010À\u0001\u001a\u00020(8F@FX\u0086\u008e\u0002¢\u0006\u0018\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001*\u0006\bÌ\u0001\u0010Æ\u0001R,\u0010Ð\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÎ\u0001\u0010\u0085\u0001\"\u0006\bÏ\u0001\u0010\u0087\u0001R\u0015\u0010Ô\u0001\u001a\u00030Ñ\u00018F¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001R,\u0010×\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÕ\u0001\u0010\u0085\u0001\"\u0006\bÖ\u0001\u0010\u0087\u0001R.\u0010Ü\u0001\u001a\u0004\u0018\u00010Q2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010Q8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bØ\u0001\u0010Ù\u0001\"\u0006\bÚ\u0001\u0010Û\u0001R.\u0010ß\u0001\u001a\u0004\u0018\u00010Q2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010Q8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÝ\u0001\u0010Ù\u0001\"\u0006\bÞ\u0001\u0010Û\u0001R.\u0010â\u0001\u001a\u0004\u0018\u00010Q2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010Q8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bà\u0001\u0010Ù\u0001\"\u0006\bá\u0001\u0010Û\u0001R.\u0010å\u0001\u001a\u0004\u0018\u00010Q2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010Q8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bã\u0001\u0010Ù\u0001\"\u0006\bä\u0001\u0010Û\u0001R,\u0010è\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bæ\u0001\u0010\u0085\u0001\"\u0006\bç\u0001\u0010\u0087\u0001R0\u0010î\u0001\u001a\u0005\u0018\u00010é\u00012\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010é\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bê\u0001\u0010ë\u0001\"\u0006\bì\u0001\u0010í\u0001R,\u0010ñ\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bï\u0001\u0010\u0085\u0001\"\u0006\bð\u0001\u0010\u0087\u0001¨\u0006ú\u0001"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "Landroid/widget/RelativeLayout;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "barcodeCount", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/BarcodeCountViewLayerManager;", "layerManager", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/viewholders/BarcodeCountDataCaptureViewHolder;", "dataCaptureViewWrapper", "Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;", "overlay", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/viewholders/BarcodeCountViewUiLayoutHelper;", "layoutHelper", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountUiPresenter;", "uiHandler", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountBrushHandler;", "brushHandler", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/barcodeindicator/BarcodeIndicatorPresenter;", "indicatorPresenter", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/mode/BarcodeCountInternalModePresenter;", "internalModePresenter", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/mode/BarcodeCountInternalMode;", "internalMode", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountCameraHandler;", "cameraHandler", "Lcom/scandit/datacapture/core/internal/sdk/common/async/MainThreadHelper;", "mainThreadHelper", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/internal/module/count/ui/BarcodeCountViewLayerManager;Lcom/scandit/datacapture/barcode/internal/module/count/ui/viewholders/BarcodeCountDataCaptureViewHolder;Lcom/scandit/datacapture/barcode/count/ui/overlay/BarcodeCountBasicOverlay;Lcom/scandit/datacapture/barcode/internal/module/count/ui/viewholders/BarcodeCountViewUiLayoutHelper;Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountUiPresenter;Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountBrushHandler;Lcom/scandit/datacapture/barcode/internal/module/count/ui/barcodeindicator/BarcodeIndicatorPresenter;Lcom/scandit/datacapture/barcode/internal/module/count/ui/mode/BarcodeCountInternalModePresenter;Lcom/scandit/datacapture/barcode/internal/module/count/ui/mode/BarcodeCountInternalMode;Lcom/scandit/datacapture/barcode/internal/module/count/ui/handlers/BarcodeCountCameraHandler;Lcom/scandit/datacapture/core/internal/sdk/common/async/MainThreadHelper;)V", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/toolbar/BarcodeCountToolbarView;", "getToolbar$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/count/ui/toolbar/BarcodeCountToolbarView;", "getToolbar", "", "onAttachedToWindow", "()V", "disableScanningAndResetBarcodeIndicators$scandit_barcode_capture", "disableScanningAndResetBarcodeIndicators", "onDetachedFromWindow", "", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", "", "text", "setClearHighlightsButtonText", "(Ljava/lang/String;)V", "getClearHighlightsButtonText", "()Ljava/lang/String;", "setTextForScreenCleanedUpHint", "getTextForScreenCleanedUpHint", "setExitButtonText", "getExitButtonText", "setClearHighlightsButtonContentDescription", "getClearHighlightsButtonContentDescription", "setExitButtonContentDescription", "getExitButtonContentDescription", "setFloatingShutterButtonContentDescription", "getFloatingShutterButtonContentDescription", "setListButtonContentDescription", "getListButtonContentDescription", "setSingleScanButtonContentDescription", "getSingleScanButtonContentDescription", "setShutterButtonContentDescription", "getShutterButtonContentDescription", "setStatusModeButtonContentDescription", "getStatusModeButtonContentDescription", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountToolbarSettings;", "settings", "setToolbarSettings", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountToolbarSettings;)V", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProvider;", "provider", "setStatusProvider", "(Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProvider;)V", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "barcode", "Lcom/scandit/datacapture/core/ui/style/Brush;", "brush", "setBrushForRecognizedBarcode", "(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;Lcom/scandit/datacapture/core/ui/style/Brush;)V", "setBrushForRecognizedBarcodeNotInList", "setBrushForAcceptedBarcode", "setBrushForRejectedBarcode", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "enableHardwareTrigger", "(Ljava/lang/Integer;)V", "setTextForBarcodesNotInListDetectedHint", "getTextForBarcodesNotInListDetectedHint", "setTextForTapShutterToScanHint", "getTextForTapShutterToScanHint", "setTextForScanningHint", "getTextForScanningHint", "setTextForMoveCloserAndRescanHint", "getTextForMoveCloserAndRescanHint", "setTextForMoveFurtherAndRescanHint", "getTextForMoveFurtherAndRescanHint", "setTextForTapToUncountHint", "getTextForTapToUncountHint", "setTextForClusteringGestureHint", "getTextForClusteringGestureHint", "clearHighlights", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;", "_setProfilingOverlay", "(Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;)V", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/BarcodeCountViewSettings;", "m", "Lcom/scandit/datacapture/barcode/internal/module/count/ui/BarcodeCountViewSettings;", "getViewSettings$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/count/ui/BarcodeCountViewSettings;", "getViewSettings$scandit_barcode_capture$annotations", "viewSettings", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewListener;", "n", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewListener;", "getListener", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewListener;", "setListener", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "p", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProvider;", "getStatusProvider$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProvider;", "setStatusProvider$scandit_barcode_capture", "statusProvider", "", "value", "getShouldDisableModeOnExitButtonTapped", "()Z", "setShouldDisableModeOnExitButtonTapped", "(Z)V", "shouldDisableModeOnExitButtonTapped", "getShouldShowClearHighlightsButton", "setShouldShowClearHighlightsButton", "shouldShowClearHighlightsButton", "getShouldShowExitButton", "setShouldShowExitButton", "shouldShowExitButton", "getShouldShowFloatingShutterButton", "setShouldShowFloatingShutterButton", "shouldShowFloatingShutterButton", "getShouldShowListButton", "setShouldShowListButton", "shouldShowListButton", "getShouldShowShutterButton", "setShouldShowShutterButton", "shouldShowShutterButton", "getShouldShowSingleScanButton", "setShouldShowSingleScanButton", "shouldShowSingleScanButton", "getShouldShowStatusModeButton", "setShouldShowStatusModeButton", "shouldShowStatusModeButton", "getShouldShowToolbar", "setShouldShowToolbar", "shouldShowToolbar", "getShouldShowUserGuidanceView", "setShouldShowUserGuidanceView", "shouldShowUserGuidanceView", "getShouldShowListProgressBar", "setShouldShowListProgressBar", "shouldShowListProgressBar", "getShouldShowTorchControl", "setShouldShowTorchControl", "shouldShowTorchControl", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getTorchControlPosition", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setTorchControlPosition", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "torchControlPosition", "getTapToUncountEnabled", "setTapToUncountEnabled", "tapToUncountEnabled", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountNotInListActionSettings;", "getBarcodeNotInListActionSettings", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountNotInListActionSettings;", "setBarcodeNotInListActionSettings", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountNotInListActionSettings;)V", "barcodeNotInListActionSettings", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewUiListener;", "getUiListener", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewUiListener;", "setUiListener", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewUiListener;)V", "uiListener", "Lcom/scandit/datacapture/barcode/count/ui/BarcodeCountViewInternalUiListener;", "<set-?>", "get_internalUiListener", "()Lcom/scandit/datacapture/barcode/count/ui/BarcodeCountViewInternalUiListener;", "set_internalUiListener", "(Lcom/scandit/datacapture/barcode/count/ui/BarcodeCountViewInternalUiListener;)V", "get_internalUiListener$delegate", "(Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;)Ljava/lang/Object;", "_internalUiListener", "get_scanningDelayMs", "()I", "set_scanningDelayMs", "(I)V", "get_scanningDelayMs$delegate", "_scanningDelayMs", "get_forceAlignCameraPreviewToBottomInPortrait", "set_forceAlignCameraPreviewToBottomInPortrait", "_forceAlignCameraPreviewToBottomInPortrait", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;", "getStyle", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;", "style", "getShouldShowScanAreaGuides", "setShouldShowScanAreaGuides", "shouldShowScanAreaGuides", "getRecognizedBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "setRecognizedBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "recognizedBrush", "getNotInListBrush", "setNotInListBrush", "notInListBrush", "getAcceptedBrush", "setAcceptedBrush", "acceptedBrush", "getRejectedBrush", "setRejectedBrush", "rejectedBrush", "getShouldShowHints", "setShouldShowHints", "shouldShowHints", "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "getFilterSettings", "()Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "setFilterSettings", "(Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;)V", "filterSettings", "getShouldShowStatusIconsOnScan", "setShouldShowStatusIconsOnScan", "shouldShowStatusIconsOnScan", "Companion", "com/scandit/datacapture/barcode/count/ui/view/a", "com/scandit/datacapture/barcode/count/ui/view/b", "com/scandit/datacapture/barcode/count/ui/view/c", "com/scandit/datacapture/barcode/count/ui/view/d", "com/scandit/datacapture/barcode/count/ui/view/e", "com/scandit/datacapture/barcode/count/ui/view/g", "com/scandit/datacapture/barcode/count/ui/view/i", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCountView extends RelativeLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeCountViewLayerManager a;
    private final BarcodeCountDataCaptureViewHolder b;
    private final BarcodeCountBasicOverlay c;
    private final BarcodeCountViewUiLayoutHelper d;
    private final BarcodeCountUiPresenter e;
    private final BarcodeCountBrushHandler f;
    private final BarcodeIndicatorPresenter g;
    private final BarcodeCountInternalModePresenter h;
    private final BarcodeCountCameraHandler i;
    private final MainThreadHelper j;
    private final Handler k;
    private final WeakReference l;

    /* renamed from: m, reason: from kotlin metadata */
    private final BarcodeCountViewSettings viewSettings;

    /* renamed from: n, reason: from kotlin metadata */
    private BarcodeCountViewListener listener;
    private boolean o;

    /* renamed from: p, reason: from kotlin metadata */
    private BarcodeCountStatusProvider statusProvider;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[BarcodeCountScreenRatioForUi.values().length];
            try {
                iArr[BarcodeCountScreenRatioForUi.LARGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[HardwareKeyEvent.values().length];
            try {
                iArr2[HardwareKeyEvent.CLICK.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public /* synthetic */ BarcodeCountView(Context context, BarcodeCount barcodeCount, BarcodeCountViewLayerManager barcodeCountViewLayerManager, BarcodeCountDataCaptureViewHolder barcodeCountDataCaptureViewHolder, BarcodeCountBasicOverlay barcodeCountBasicOverlay, BarcodeCountViewUiLayoutHelper barcodeCountViewUiLayoutHelper, BarcodeCountUiPresenter barcodeCountUiPresenter, BarcodeCountBrushHandler barcodeCountBrushHandler, BarcodeIndicatorPresenter barcodeIndicatorPresenter, BarcodeCountInternalModePresenter barcodeCountInternalModePresenter, BarcodeCountInternalMode barcodeCountInternalMode, BarcodeCountCameraHandler barcodeCountCameraHandler, MainThreadHelper mainThreadHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, barcodeCount, barcodeCountViewLayerManager, barcodeCountDataCaptureViewHolder, barcodeCountBasicOverlay, barcodeCountViewUiLayoutHelper, barcodeCountUiPresenter, barcodeCountBrushHandler, barcodeIndicatorPresenter, barcodeCountInternalModePresenter, (i & 1024) != 0 ? com.scandit.datacapture.barcode.internal.module.count.ui.mode.a.a : barcodeCountInternalMode, (i & 2048) != 0 ? new s(barcodeCount) : barcodeCountCameraHandler, (i & 4096) != 0 ? new MainThreadHelperImpl() : mainThreadHelper);
    }

    private final RelativeLayout.LayoutParams a(Size size) {
        BarcodeCountScreenRatioForUi h = this.d.h();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.getWidth(), -1);
            if (WhenMappings.$EnumSwitchMapping$0[h.ordinal()] == 1) {
                layoutParams.addRule(13);
                return layoutParams;
            }
            layoutParams.addRule(20);
            return layoutParams;
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, size.getHeight());
        if (get_forceAlignCameraPreviewToBottomInPortrait()) {
            layoutParams2.addRule(12);
        } else if (h == BarcodeCountScreenRatioForUi.LARGE) {
            layoutParams2.addRule(13);
        } else {
            layoutParams2.addRule(20);
        }
        return layoutParams2;
    }

    public static final void access$clearIndicatorsOnMainThread(BarcodeCountView barcodeCountView) {
        barcodeCountView.getClass();
        ViewExtensionsKt.runOnMainThread(barcodeCountView, new j(barcodeCountView));
    }

    public static final void access$hideActionPopup(BarcodeCountView barcodeCountView, NativeTrackedBarcode nativeTrackedBarcode) {
        if (barcodeCountView.g.b() || nativeTrackedBarcode == null) {
            return;
        }
        barcodeCountView.e.k();
        barcodeCountView.g.a(new TrackedBarcode(nativeTrackedBarcode));
    }

    public static final void access$onBarcodeStatusesLoading(BarcodeCountView barcodeCountView) {
        barcodeCountView.g.b(false, true);
        barcodeCountView.g.a(true, true);
    }

    public static final void access$onHardwareKeyEvent(BarcodeCountView barcodeCountView, HardwareKeyEvent hardwareKeyEvent) {
        barcodeCountView.getClass();
        if (WhenMappings.$EnumSwitchMapping$1[hardwareKeyEvent.ordinal()] == 1) {
            barcodeCountView.e.i();
        }
    }

    public static final void access$onToolbarColorSchemeChanged(BarcodeCountView barcodeCountView, NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme) {
        barcodeCountView.g.a(nativeBarcodeCountBasicOverlayColorScheme);
        barcodeCountView.g.a(barcodeCountView.viewSettings);
    }

    public static final void access$setListUiEnabled(BarcodeCountView barcodeCountView, boolean z, int i) {
        barcodeCountView.o = z;
        barcodeCountView.e.b(z);
        BarcodeCountUiPresenter barcodeCountUiPresenter = barcodeCountView.e;
        Context context = barcodeCountView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        barcodeCountUiPresenter.a(z, com.scandit.datacapture.barcode.internal.module.extensions.b.a(context));
        barcodeCountView.g.a(z);
        if (barcodeCountView.isAttachedToWindow()) {
            barcodeCountView.j.runOnMainThread(new o(barcodeCountView));
            barcodeCountView.j.runOnMainThread(new n(barcodeCountView, i));
        }
    }

    public static final void access$showActionPopup(BarcodeCountView barcodeCountView, NativeTrackedBarcode nativeTrackedBarcode, NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus) {
        if (barcodeCountView.g.b() || nativeTrackedBarcode == null || nativeBarcodeCountNotInListStatus == null) {
            return;
        }
        barcodeCountView.e.h();
        barcodeCountView.g.a(new TrackedBarcode(nativeTrackedBarcode), nativeBarcodeCountNotInListStatus);
    }

    public static final void access$updateModeFeedback(BarcodeCountView barcodeCountView) {
        BarcodeCount barcodeCount = (BarcodeCount) barcodeCountView.l.get();
        if (barcodeCount != null) {
            barcodeCountView.e.a(barcodeCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(BarcodeCountView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d();
        this$0.j.runOnMainThread(new o(this$0));
    }

    private final void c() {
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeCountView.b(BarcodeCountView.this);
            }
        });
    }

    private final void d() {
        Size a = a();
        BarcodeCountUiPresenter barcodeCountUiPresenter = this.e;
        Configuration configuration = getContext().getResources().getConfiguration();
        barcodeCountUiPresenter.a(new Size(PixelExtensionsKt.pxFromDp$default(configuration.screenWidthDp, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(configuration.screenHeightDp, (Context) null, 1, (Object) null)), a);
        RelativeLayout.LayoutParams a2 = a(a);
        this.b.a(a2);
        this.a.a(a2);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context) || this.d.h() == BarcodeCountScreenRatioForUi.SMALL || get_forceAlignCameraPreviewToBottomInPortrait()) {
            this.a.c().setLayoutParams(a(a));
        }
        BarcodeCountUiPresenter barcodeCountUiPresenter2 = this.e;
        boolean z = this.o;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        barcodeCountUiPresenter2.a(z, com.scandit.datacapture.barcode.internal.module.extensions.b.a(context2));
    }

    @JvmStatic
    public static final Brush defaultAcceptedBrush() {
        return INSTANCE.defaultAcceptedBrush();
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
    public static final Brush defaultRejectedBrush() {
        return INSTANCE.defaultRejectedBrush();
    }

    private final void e() {
        this.e.a(this.viewSettings);
        this.j.runOnMainThread(new o(this));
    }

    public static final boolean getHardwareTriggerSupported() {
        return INSTANCE.getHardwareTriggerSupported();
    }

    public static /* synthetic */ void getViewSettings$scandit_barcode_capture$annotations() {
    }

    @JvmStatic
    public static final BarcodeCountView newInstance(Context context, DataCaptureContext dataCaptureContext, BarcodeCount barcodeCount) {
        return INSTANCE.newInstance(context, dataCaptureContext, barcodeCount);
    }

    @JvmStatic
    public static final BarcodeCountView newInstanceForMapping(Context context, DataCaptureContext dataCaptureContext, BarcodeCount barcodeCount, BarcodeCountViewStyle barcodeCountViewStyle, BarcodeCountMappingFlowSettings barcodeCountMappingFlowSettings) {
        return INSTANCE.newInstanceForMapping(context, dataCaptureContext, barcodeCount, barcodeCountViewStyle, barcodeCountMappingFlowSettings);
    }

    public final void _setProfilingOverlay(ProfilingOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.c._setProfilingOverlay(overlay);
    }

    public final void clearHighlights() {
        this.c.clearHighlights();
    }

    public final void disableScanningAndResetBarcodeIndicators$scandit_barcode_capture() {
        BarcodeCount barcodeCount = (BarcodeCount) this.l.get();
        if (barcodeCount != null) {
            barcodeCount.endScanningPhase$scandit_barcode_capture(new l(this));
        }
    }

    public final void enableHardwareTrigger(Integer hardwareTriggerKeyCode) {
        HardwareButtonHelperKt.setupHardwareTrigger(this, hardwareTriggerKeyCode, new m(this));
    }

    public final Brush getAcceptedBrush() {
        return this.viewSettings.a();
    }

    public final BarcodeCountNotInListActionSettings getBarcodeNotInListActionSettings() {
        return this.viewSettings.b();
    }

    public final String getClearHighlightsButtonContentDescription() {
        return this.viewSettings.c();
    }

    public final String getClearHighlightsButtonText() {
        return this.viewSettings.d();
    }

    public final String getExitButtonContentDescription() {
        return this.viewSettings.e();
    }

    public final String getExitButtonText() {
        return this.viewSettings.f();
    }

    public final BarcodeFilterHighlightSettings getFilterSettings() {
        return this.viewSettings.g();
    }

    public final String getFloatingShutterButtonContentDescription() {
        return this.viewSettings.h();
    }

    public final String getListButtonContentDescription() {
        return this.viewSettings.i();
    }

    public final BarcodeCountViewListener getListener() {
        return this.listener;
    }

    public final Brush getNotInListBrush() {
        return this.viewSettings.j();
    }

    public final Brush getRecognizedBrush() {
        return this.viewSettings.k();
    }

    public final Brush getRejectedBrush() {
        return this.viewSettings.l();
    }

    public final boolean getShouldDisableModeOnExitButtonTapped() {
        return this.viewSettings.m();
    }

    public final boolean getShouldShowClearHighlightsButton() {
        return this.viewSettings.n();
    }

    public final boolean getShouldShowExitButton() {
        return this.viewSettings.o();
    }

    public final boolean getShouldShowFloatingShutterButton() {
        return this.viewSettings.p();
    }

    public final boolean getShouldShowHints() {
        return this.viewSettings.q();
    }

    public final boolean getShouldShowListButton() {
        return this.viewSettings.r();
    }

    public final boolean getShouldShowListProgressBar() {
        return this.viewSettings.s();
    }

    public final boolean getShouldShowScanAreaGuides() {
        return this.viewSettings.t();
    }

    public final boolean getShouldShowShutterButton() {
        return this.viewSettings.u();
    }

    public final boolean getShouldShowSingleScanButton() {
        return this.viewSettings.v();
    }

    public final boolean getShouldShowStatusIconsOnScan() {
        return this.viewSettings.w();
    }

    public final boolean getShouldShowStatusModeButton() {
        return this.viewSettings.x();
    }

    public final boolean getShouldShowToolbar() {
        return this.viewSettings.y();
    }

    public final boolean getShouldShowTorchControl() {
        return this.viewSettings.z();
    }

    public final boolean getShouldShowUserGuidanceView() {
        return this.viewSettings.A();
    }

    public final String getShutterButtonContentDescription() {
        return this.viewSettings.B();
    }

    public final String getSingleScanButtonContentDescription() {
        return this.viewSettings.C();
    }

    public final String getStatusModeButtonContentDescription() {
        return this.viewSettings.D();
    }

    /* renamed from: getStatusProvider$scandit_barcode_capture, reason: from getter */
    public final BarcodeCountStatusProvider getStatusProvider() {
        return this.statusProvider;
    }

    public final BarcodeCountViewStyle getStyle() {
        return BarcodeCountViewStyleExtensionKt.toViewStyle(this.c.getStyle());
    }

    public final boolean getTapToUncountEnabled() {
        NativeBarcodeCount a;
        BarcodeCount barcodeCount = (BarcodeCount) this.l.get();
        boolean tapToUncountEnabled = (barcodeCount == null || (a = barcodeCount.getA()) == null) ? BarcodeCountViewDefaults.INSTANCE.getTapToUncountEnabled() : a.isTapToUncountEnabled();
        this.viewSettings.getClass();
        return tapToUncountEnabled;
    }

    public final String getTextForBarcodesNotInListDetectedHint() {
        String E = this.viewSettings.E();
        return E == null ? BarcodeCountViewDefaults.INSTANCE.getTextForBarcodesNotInListDetectedHint() : E;
    }

    public final String getTextForClusteringGestureHint() {
        String F = this.viewSettings.F();
        return F == null ? BarcodeCountViewDefaults.INSTANCE.getTextForClusteringGestureHint() : F;
    }

    public final String getTextForMoveCloserAndRescanHint() {
        String G = this.viewSettings.G();
        return G == null ? BarcodeCountViewDefaults.INSTANCE.getTextForMoveCloserAndRescanHint() : G;
    }

    public final String getTextForMoveFurtherAndRescanHint() {
        String H = this.viewSettings.H();
        return H == null ? BarcodeCountViewDefaults.INSTANCE.getTextForMoveFurtherAndRescanHint() : H;
    }

    public final String getTextForScanningHint() {
        String I = this.viewSettings.I();
        return I == null ? BarcodeCountViewDefaults.INSTANCE.getTextForScanningHint() : I;
    }

    public final String getTextForScreenCleanedUpHint() {
        String J = this.viewSettings.J();
        return J == null ? BarcodeCountViewDefaults.INSTANCE.getTextForScreenCleanedUpHint() : J;
    }

    public final String getTextForTapShutterToScanHint() {
        String K = this.viewSettings.K();
        return K == null ? BarcodeCountViewDefaults.INSTANCE.getTextForTapShutterToScanHint() : K;
    }

    public final String getTextForTapToUncountHint() {
        String L = this.viewSettings.L();
        return L == null ? BarcodeCountViewDefaults.INSTANCE.getTextForTapToUncountHint() : L;
    }

    public final BarcodeCountToolbarView getToolbar$scandit_barcode_capture() {
        return this.e.g();
    }

    public final Anchor getTorchControlPosition() {
        return this.viewSettings.N();
    }

    public final BarcodeCountViewUiListener getUiListener() {
        BarcodeCountBasicOverlayUiListener uiListener = this.c.getUiListener();
        d dVar = uiListener instanceof d ? (d) uiListener : null;
        if (dVar != null) {
            return dVar.a;
        }
        return null;
    }

    /* renamed from: getViewSettings$scandit_barcode_capture, reason: from getter */
    public final BarcodeCountViewSettings getViewSettings() {
        return this.viewSettings;
    }

    public final boolean get_forceAlignCameraPreviewToBottomInPortrait() {
        return this.e.b();
    }

    public final BarcodeCountViewInternalUiListener get_internalUiListener() {
        return this.e.c();
    }

    public final int get_scanningDelayMs() {
        return this.e.j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d();
        this.b.b(this.c);
        BarcodeCount barcodeCount = (BarcodeCount) this.l.get();
        if (barcodeCount != null) {
            this.e.a(barcodeCount);
        }
        setShouldShowFloatingShutterButton(this.e.e());
        BarcodeIndicatorPresenter barcodeIndicatorPresenter = this.g;
        barcodeIndicatorPresenter.a(this.c.getColorScheme$scandit_barcode_capture());
        barcodeIndicatorPresenter.a(this.a.d());
        barcodeIndicatorPresenter.a(this.viewSettings);
        BarcodeCount barcodeCount2 = (BarcodeCount) this.l.get();
        if (barcodeCount2 == null || !barcodeCount2.isCaptureListSet$scandit_barcode_capture()) {
            this.j.runOnMainThread(new o(this));
            this.j.runOnMainThread(new n(this, 0));
            return;
        }
        int captureListTargetQuantity$scandit_barcode_capture = barcodeCount2.getCaptureListTargetQuantity$scandit_barcode_capture();
        this.o = true;
        this.e.b(true);
        BarcodeCountUiPresenter barcodeCountUiPresenter = this.e;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        barcodeCountUiPresenter.a(true, com.scandit.datacapture.barcode.internal.module.extensions.b.a(context));
        this.g.a(true);
        if (isAttachedToWindow()) {
            this.j.runOnMainThread(new o(this));
            this.j.runOnMainThread(new n(this, captureListTargetQuantity$scandit_barcode_capture));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a(this.c);
        this.g.reset();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        c();
    }

    public final void setAcceptedBrush(Brush brush) {
        this.viewSettings.a(brush);
        e();
    }

    public final void setBarcodeNotInListActionSettings(BarcodeCountNotInListActionSettings value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.viewSettings.a(value);
        this.c.applyNotInListActionSettings$scandit_barcode_capture(value);
        this.g.a(this.viewSettings);
    }

    public final void setBrushForAcceptedBarcode(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.f.b(barcode, brush);
        this.g.b(barcode);
    }

    public final void setBrushForRecognizedBarcode(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.f.a(barcode, brush);
        this.g.b(barcode);
    }

    public final void setBrushForRecognizedBarcodeNotInList(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.f.d(barcode, brush);
        this.g.b(barcode);
    }

    public final void setBrushForRejectedBarcode(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.f.c(barcode, brush);
        this.g.b(barcode);
    }

    public final void setClearHighlightsButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.a(text);
        e();
    }

    public final void setClearHighlightsButtonText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.b(text);
        e();
    }

    public final void setExitButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.c(text);
        e();
    }

    public final void setExitButtonText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.d(text);
        e();
    }

    public final void setFilterSettings(BarcodeFilterHighlightSettings barcodeFilterHighlightSettings) {
        this.viewSettings.a(barcodeFilterHighlightSettings);
        e();
    }

    public final void setFloatingShutterButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.e(text);
        e();
    }

    public final void setListButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.f(text);
        e();
    }

    public final void setListener(BarcodeCountViewListener barcodeCountViewListener) {
        this.listener = barcodeCountViewListener;
    }

    public final void setNotInListBrush(Brush brush) {
        this.viewSettings.b(brush);
        e();
    }

    public final void setRecognizedBrush(Brush brush) {
        this.viewSettings.c(brush);
        e();
    }

    public final void setRejectedBrush(Brush brush) {
        this.viewSettings.d(brush);
        e();
    }

    public final void setShouldDisableModeOnExitButtonTapped(boolean z) {
        this.viewSettings.a(z);
        e();
    }

    public final void setShouldShowClearHighlightsButton(boolean z) {
        this.viewSettings.b(z);
        e();
    }

    public final void setShouldShowExitButton(boolean z) {
        this.viewSettings.c(z);
        e();
    }

    public final void setShouldShowFloatingShutterButton(boolean z) {
        this.viewSettings.d(z);
        e();
    }

    public final void setShouldShowHints(boolean z) {
        this.viewSettings.e(z);
        e();
    }

    public final void setShouldShowListButton(boolean z) {
        this.viewSettings.f(z);
        e();
    }

    public final void setShouldShowListProgressBar(boolean z) {
        this.viewSettings.g(z);
        e();
    }

    public final void setShouldShowScanAreaGuides(boolean z) {
        this.viewSettings.h(z);
        e();
    }

    public final void setShouldShowShutterButton(boolean z) {
        this.viewSettings.i(z);
        e();
    }

    public final void setShouldShowSingleScanButton(boolean z) {
        this.viewSettings.j(z);
        e();
    }

    public final void setShouldShowStatusIconsOnScan(boolean z) {
        this.viewSettings.k(z);
        this.g.a(this.viewSettings);
        e();
    }

    public final void setShouldShowStatusModeButton(boolean z) {
        this.viewSettings.l(z);
        e();
    }

    public final void setShouldShowToolbar(boolean z) {
        this.viewSettings.m(z);
        e();
    }

    public final void setShouldShowTorchControl(boolean z) {
        this.viewSettings.n(z);
        e();
    }

    public final void setShouldShowUserGuidanceView(boolean z) {
        this.viewSettings.o(z);
        e();
    }

    public final void setShutterButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.g(text);
        e();
    }

    public final void setSingleScanButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.h(text);
        e();
    }

    public final void setStatusModeButtonContentDescription(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.i(text);
        e();
    }

    public final void setStatusProvider(BarcodeCountStatusProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        BarcodeCount barcodeCount = (BarcodeCount) this.l.get();
        if (barcodeCount != null) {
            barcodeCount.setInternalStatusProvider$scandit_barcode_capture(new InternalBarcodeCountStatusProvider(this));
        }
        this.statusProvider = provider;
    }

    public final void setStatusProvider$scandit_barcode_capture(BarcodeCountStatusProvider barcodeCountStatusProvider) {
        this.statusProvider = barcodeCountStatusProvider;
    }

    public final void setTapToUncountEnabled(boolean z) {
        BarcodeCount barcodeCount = (BarcodeCount) this.l.get();
        if (barcodeCount != null) {
            barcodeCount.getA().setTapToUncountEnabled(z);
            this.viewSettings.getClass();
        }
    }

    public final void setTextForBarcodesNotInListDetectedHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.j(text);
        e();
    }

    public final void setTextForClusteringGestureHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.k(text);
        e();
    }

    public final void setTextForMoveCloserAndRescanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.l(text);
        e();
    }

    public final void setTextForMoveFurtherAndRescanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.m(text);
        e();
    }

    public final void setTextForScanningHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.n(text);
        e();
    }

    public final void setTextForScreenCleanedUpHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.o(text);
        e();
    }

    public final void setTextForTapShutterToScanHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.p(text);
        e();
    }

    public final void setTextForTapToUncountHint(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.viewSettings.q(text);
        e();
    }

    public final void setToolbarSettings(BarcodeCountToolbarSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.viewSettings.a(settings);
        e();
    }

    public final void setTorchControlPosition(Anchor value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.viewSettings.a(value);
        e();
    }

    public final void setUiListener(BarcodeCountViewUiListener barcodeCountViewUiListener) {
        this.c.setUiListener(barcodeCountViewUiListener != null ? new d(this, barcodeCountViewUiListener) : null);
    }

    public final void set_forceAlignCameraPreviewToBottomInPortrait(boolean z) {
        this.e.a(z);
        c();
    }

    public final void set_internalUiListener(BarcodeCountViewInternalUiListener barcodeCountViewInternalUiListener) {
        this.e.a(barcodeCountViewInternalUiListener);
    }

    public final void set_scanningDelayMs(int i) {
        this.e.a(i);
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0007\u0010\u0004J)\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J1\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u000f\u0010\u0013J'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0016J/\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u000f\u0010\u0017J9\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ7\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001cR\u001a\u0010\"\u001a\u00020\u001d8FX\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010$\u001a\u00020#8\u0002X\u0082T¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView$Companion;", "", "Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultRecognizedBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultNotInListBrush", "defaultAcceptedBrush", "defaultRejectedBrush", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "mode", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "newInstance", "(Landroid/content/Context;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;", "style", "(Landroid/content/Context;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "dataCaptureView", "(Landroid/content/Context;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "(Landroid/content/Context;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeCountMappingFlowSettings;", "mappingFlowSettings", "newInstanceForMapping", "(Landroid/content/Context;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeCountMappingFlowSettings;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "(Landroid/content/Context;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewStyle;Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeCountMappingFlowSettings;)Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "", "getHardwareTriggerSupported", "()Z", "getHardwareTriggerSupported$annotations", "()V", "hardwareTriggerSupported", "", "LIST_COMPLETE_NOTIFICATION_DELAY_MS", "J", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getHardwareTriggerSupported$annotations() {
        }

        @JvmStatic
        public final Brush defaultAcceptedBrush() {
            return BarcodeCountBasicOverlay.INSTANCE.defaultAcceptedNotInListBrush();
        }

        @JvmStatic
        public final Brush defaultNotInListBrush() {
            return BarcodeCountBasicOverlay.INSTANCE.defaultNotInListBrush();
        }

        @JvmStatic
        public final Brush defaultRecognizedBrush() {
            return BarcodeCountBasicOverlay.INSTANCE.defaultRecognizedBrush();
        }

        @JvmStatic
        public final Brush defaultRejectedBrush() {
            return BarcodeCountBasicOverlay.INSTANCE.defaultRejectedNotInListBrush();
        }

        public final boolean getHardwareTriggerSupported() {
            return HardwareButtonHelperKt.isHardwareTriggerSupported();
        }

        @JvmStatic
        public final BarcodeCountView newInstance(Context context, DataCaptureContext dataCaptureContext, BarcodeCount mode) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mode, "mode");
            return newInstance(context, dataCaptureContext, mode, BarcodeCountViewStyle.ICON);
        }

        @JvmStatic
        public final BarcodeCountView newInstanceForMapping(Context context, DataCaptureView dataCaptureView, BarcodeCount mode, BarcodeCountViewStyle style, BarcodeCountMappingFlowSettings mappingFlowSettings) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(mappingFlowSettings, "mappingFlowSettings");
            return com.scandit.datacapture.barcode.internal.module.count.ui.f.a(context, dataCaptureView, mode, style, new com.scandit.datacapture.barcode.internal.module.count.ui.mode.b(mappingFlowSettings, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.a));
        }

        @JvmStatic
        public final BarcodeCountView newInstance(Context context, DataCaptureView dataCaptureView, BarcodeCount mode) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
            Intrinsics.checkNotNullParameter(mode, "mode");
            return newInstance(context, dataCaptureView, mode, BarcodeCountViewStyle.ICON);
        }

        @JvmStatic
        public final BarcodeCountView newInstance(Context context, DataCaptureView dataCaptureView, BarcodeCount mode, BarcodeCountViewStyle style) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            return com.scandit.datacapture.barcode.internal.module.count.ui.f.a(context, dataCaptureView, mode, style, com.scandit.datacapture.barcode.internal.module.count.ui.mode.a.a);
        }

        @JvmStatic
        public final BarcodeCountView newInstanceForMapping(Context context, DataCaptureContext dataCaptureContext, BarcodeCount mode, BarcodeCountViewStyle style, BarcodeCountMappingFlowSettings mappingFlowSettings) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(mappingFlowSettings, "mappingFlowSettings");
            DataCaptureView newInstance = DataCaptureView.INSTANCE.newInstance(context, dataCaptureContext);
            newInstance.setLogoStyle(LogoStyle.MINIMAL);
            newInstance.setLogoAnchor(Anchor.BOTTOM_LEFT);
            return com.scandit.datacapture.barcode.internal.module.count.ui.f.a(context, newInstance, mode, style, new com.scandit.datacapture.barcode.internal.module.count.ui.mode.b(mappingFlowSettings, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.a));
        }

        @JvmStatic
        public final BarcodeCountView newInstance(Context context, DataCaptureContext dataCaptureContext, BarcodeCount mode, BarcodeCountViewStyle style) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            DataCaptureView newInstance = DataCaptureView.INSTANCE.newInstance(context, dataCaptureContext);
            newInstance.setLogoStyle(LogoStyle.MINIMAL);
            newInstance.setLogoAnchor(Anchor.BOTTOM_LEFT);
            return newInstance(context, newInstance, mode, style);
        }
    }

    @JvmStatic
    public static final BarcodeCountView newInstance(Context context, DataCaptureContext dataCaptureContext, BarcodeCount barcodeCount, BarcodeCountViewStyle barcodeCountViewStyle) {
        return INSTANCE.newInstance(context, dataCaptureContext, barcodeCount, barcodeCountViewStyle);
    }

    @JvmStatic
    public static final BarcodeCountView newInstanceForMapping(Context context, DataCaptureView dataCaptureView, BarcodeCount barcodeCount, BarcodeCountViewStyle barcodeCountViewStyle, BarcodeCountMappingFlowSettings barcodeCountMappingFlowSettings) {
        return INSTANCE.newInstanceForMapping(context, dataCaptureView, barcodeCount, barcodeCountViewStyle, barcodeCountMappingFlowSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b() {
        this.k.post(new Runnable() { // from class: com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeCountView.a(BarcodeCountView.this);
            }
        });
    }

    @JvmStatic
    public static final BarcodeCountView newInstance(Context context, DataCaptureView dataCaptureView, BarcodeCount barcodeCount) {
        return INSTANCE.newInstance(context, dataCaptureView, barcodeCount);
    }

    @JvmStatic
    public static final BarcodeCountView newInstance(Context context, DataCaptureView dataCaptureView, BarcodeCount barcodeCount, BarcodeCountViewStyle barcodeCountViewStyle) {
        return INSTANCE.newInstance(context, dataCaptureView, barcodeCount, barcodeCountViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeCountView(Context context, BarcodeCount barcodeCount, BarcodeCountViewLayerManager layerManager, BarcodeCountDataCaptureViewHolder dataCaptureViewWrapper, BarcodeCountBasicOverlay overlay, BarcodeCountViewUiLayoutHelper layoutHelper, BarcodeCountUiPresenter uiHandler, BarcodeCountBrushHandler brushHandler, BarcodeIndicatorPresenter indicatorPresenter, BarcodeCountInternalModePresenter internalModePresenter, BarcodeCountInternalMode internalMode, BarcodeCountCameraHandler cameraHandler, MainThreadHelper mainThreadHelper) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcodeCount, "barcodeCount");
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(dataCaptureViewWrapper, "dataCaptureViewWrapper");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(uiHandler, "uiHandler");
        Intrinsics.checkNotNullParameter(brushHandler, "brushHandler");
        Intrinsics.checkNotNullParameter(indicatorPresenter, "indicatorPresenter");
        Intrinsics.checkNotNullParameter(internalModePresenter, "internalModePresenter");
        Intrinsics.checkNotNullParameter(internalMode, "internalMode");
        Intrinsics.checkNotNullParameter(cameraHandler, "cameraHandler");
        Intrinsics.checkNotNullParameter(mainThreadHelper, "mainThreadHelper");
        this.a = layerManager;
        this.b = dataCaptureViewWrapper;
        this.c = overlay;
        this.d = layoutHelper;
        this.e = uiHandler;
        this.f = brushHandler;
        this.g = indicatorPresenter;
        this.h = internalModePresenter;
        this.i = cameraHandler;
        this.j = mainThreadHelper;
        this.k = new Handler(Looper.getMainLooper());
        a aVar = new a(this);
        WeakReference weakReference = new WeakReference(barcodeCount);
        this.l = weakReference;
        BarcodeCountViewSettings barcodeCountViewSettings = new BarcodeCountViewSettings();
        uiHandler.b(barcodeCountViewSettings);
        this.viewSettings = barcodeCountViewSettings;
        layoutHelper.a(layerManager.f());
        uiHandler.a(layerManager, barcodeCount);
        uiHandler.a(new i(this));
        Configuration configuration = getContext().getResources().getConfiguration();
        uiHandler.a(new Size(PixelExtensionsKt.pxFromDp$default(configuration.screenWidthDp, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(configuration.screenHeightDp, (Context) null, 1, (Object) null)), a());
        dataCaptureViewWrapper.a(this);
        dataCaptureViewWrapper.a(layerManager.i());
        layerManager.a(overlay);
        layerManager.a(this);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        BarcodeCount barcodeCount2 = (BarcodeCount) weakReference.get();
        if (barcodeCount2 != null) {
            uiHandler.a(barcodeCount2);
        }
        barcodeCount.getListListeners$scandit_barcode_capture().add(0, aVar);
        barcodeCount.setFeedbackChangeListener$scandit_barcode_capture(new e(this));
        overlay.setViewHandler$scandit_barcode_capture(new b(this));
        overlay.setCallback(new g(this));
        overlay.setListener(new c(this));
        internalModePresenter.a(internalMode);
    }

    private final Size a() {
        Configuration configuration = getContext().getResources().getConfiguration();
        Size size = new Size(PixelExtensionsKt.pxFromDp$default(configuration.screenWidthDp, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(configuration.screenHeightDp, (Context) null, 1, (Object) null));
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            int height = getHeight();
            Integer valueOf = height != 0 ? Integer.valueOf(height) : null;
            int intValue = valueOf != null ? valueOf.intValue() : size.getHeight();
            return new Size((intValue * 4) / 3, intValue);
        }
        return new Size(size.getWidth(), (int) ((size.getWidth() * 4) / 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodeCountView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.viewSettings.w()) {
            this$0.g.a();
        } else {
            this$0.a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(boolean z) {
        if (z) {
            this.e.d();
            this.g.b(false, true);
            this.g.a(true, true);
        } else if (this.viewSettings.w()) {
            this.g.b(false, true);
            this.g.a(true, true);
            this.g.b(true);
        } else {
            this.g.a(false, true);
            this.g.b(true, true);
            this.e.l();
        }
    }
}
