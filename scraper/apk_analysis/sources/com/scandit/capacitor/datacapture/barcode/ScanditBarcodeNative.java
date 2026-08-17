package com.scandit.capacitor.datacapture.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.salesforce.marketingcloud.config.a;
import com.scandit.capacitor.datacapture.barcode.count.BarcodeCountViewHandler;
import com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler;
import com.scandit.capacitor.datacapture.barcode.pick.BarcodePickViewHandler;
import com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayView;
import com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayViewData;
import com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayViewOptions;
import com.scandit.capacitor.datacapture.core.ScanditCaptureCoreNative;
import com.scandit.capacitor.datacapture.core.data.ResizeAndMoveInfo;
import com.scandit.capacitor.datacapture.core.data.SerializableCallbackAction;
import com.scandit.capacitor.datacapture.core.data.SerializableFinishModeCallbackData;
import com.scandit.capacitor.datacapture.core.errors.JsonParseError;
import com.scandit.capacitor.datacapture.core.utils.CapacitorResult;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.core.ui.style.BrushDeserializer;
import com.scandit.datacapture.frameworks.barcode.BarcodeModule;
import com.scandit.datacapture.frameworks.barcode.batch.BarcodeBatchModule;
import com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule;
import com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule;
import com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule;
import com.scandit.datacapture.frameworks.barcode.generator.BarcodeGeneratorModule;
import com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule;
import com.scandit.datacapture.frameworks.barcode.selection.BarcodeSelectionModule;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionAimedBrushProvider;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionListener;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionTrackedBrushProvider;
import com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.DefaultWorkerThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.scandit.datacapture.frameworks.core.utils.WorkerThread;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.SentryEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ScanditBarcodeNative.kt */
@CapacitorPlugin(name = "ScanditBarcodeNative", permissions = {@Permission(alias = "camera", strings = {"android.permission.CAMERA"})})
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bV\b\u0007\u0018\u0000 å\u00012\u00020\u00012\u00020\u0002:\u0002å\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0014J\b\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0003J\u0010\u0010.\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010/\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00100\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00101\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00102\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00103\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00104\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00105\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00106\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00107\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00108\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u00109\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010:\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010;\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010<\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010=\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010>\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010?\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010@\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010A\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010B\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010C\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010D\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010E\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010F\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010G\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010H\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010I\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010J\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010K\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010L\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010M\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010N\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010O\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010P\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010Q\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010R\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010S\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010T\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010U\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010V\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010W\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010X\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010Y\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010Z\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010[\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010\\\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0012\u0010]\u001a\u00020&2\b\u0010^\u001a\u0004\u0018\u00010_H\u0002J\u0012\u0010`\u001a\u00020&2\b\u0010^\u001a\u0004\u0018\u00010_H\u0002J\u0018\u0010a\u001a\u00020&2\u0006\u0010b\u001a\u00020c2\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010d\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010e\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010f\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010g\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010h\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010i\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010j\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010k\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010l\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010m\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010n\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010o\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010p\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010q\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010r\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010s\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010t\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010u\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010v\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010w\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010x\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010y\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010z\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010{\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010|\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010}\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010~\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0010\u0010\u007f\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0080\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0018\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u00012\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001H\u0002J-\u0010\u0085\u0001\u001a\u00020&2\b\u0010\u0086\u0001\u001a\u00030\u0084\u00012\u0018\u0010\u0087\u0001\u001a\u0013\u0012\u0005\u0012\u00030\u0084\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u00010\u0088\u0001H\u0016J\u0013\u0010\u008a\u0001\u001a\u00020)2\b\u0010\u0086\u0001\u001a\u00030\u0084\u0001H\u0016J\u001d\u0010\u008b\u0001\u001a\u00020)2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010\u0086\u0001\u001a\u00030\u0084\u0001H\u0016J\u0013\u0010\u008e\u0001\u001a\u00020)2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0002J\u0013\u0010\u0091\u0001\u001a\u00020)2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0002J\u001d\u0010\u0092\u0001\u001a\u00020)2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\b\u0010\u0093\u0001\u001a\u00030\u0084\u0001H\u0002J\u0011\u0010\u0094\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0095\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0096\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0097\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0098\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u0099\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009a\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009b\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009c\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009d\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009e\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u009f\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010 \u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¡\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¢\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010£\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¤\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¥\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¦\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010§\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¨\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010©\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010ª\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010«\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¬\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010\u00ad\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010®\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¯\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010°\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010±\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010²\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010³\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010´\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010µ\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¶\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010·\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¸\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¹\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010º\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010»\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¼\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010½\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¾\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010¿\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010À\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Á\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Â\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ã\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ä\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Å\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Æ\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ç\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010È\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010É\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ê\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ë\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ì\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Í\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Î\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ï\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ð\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ñ\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ò\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ó\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ô\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Õ\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ö\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010×\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ø\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ù\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ú\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Û\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ü\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010Ý\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0012\u0010Þ\u0001\u001a\u00030\u008d\u00012\u0006\u0010+\u001a\u00020,H\u0002J\u0012\u0010ß\u0001\u001a\u00030\u008d\u00012\u0006\u0010+\u001a\u00020,H\u0002J\u0012\u0010à\u0001\u001a\u00030\u008d\u00012\u0006\u0010+\u001a\u00020,H\u0002J\u0011\u0010á\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010â\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010ã\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007J\u0011\u0010ä\u0001\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0007R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006æ\u0001"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/ScanditBarcodeNative;", "Lcom/getcapacitor/Plugin;", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "<init>", "()V", "corePlugin", "Lcom/getcapacitor/PluginHandle;", "barcodeModule", "Lcom/scandit/datacapture/frameworks/barcode/BarcodeModule;", "barcodeCaptureModule", "Lcom/scandit/datacapture/frameworks/barcode/capture/BarcodeCaptureModule;", "barcodeBatchModule", "Lcom/scandit/datacapture/frameworks/barcode/batch/BarcodeBatchModule;", "barcodeSelectionModule", "Lcom/scandit/datacapture/frameworks/barcode/selection/BarcodeSelectionModule;", "barcodeCountModule", "Lcom/scandit/datacapture/frameworks/barcode/count/BarcodeCountModule;", "barcodeFindModule", "Lcom/scandit/datacapture/frameworks/barcode/find/BarcodeFindModule;", "barcodePickModule", "Lcom/scandit/datacapture/frameworks/barcode/pick/BarcodePickModule;", "sparkScanModule", "Lcom/scandit/datacapture/frameworks/barcode/spark/SparkScanModule;", "barcodeGeneratorModule", "Lcom/scandit/datacapture/frameworks/barcode/generator/BarcodeGeneratorModule;", "barcodeCountViewHandler", "Lcom/scandit/capacitor/datacapture/barcode/count/BarcodeCountViewHandler;", "barcodeFindViewHandler", "Lcom/scandit/capacitor/datacapture/barcode/find/BarcodeFindViewHandler;", "barcodePickViewHandler", "Lcom/scandit/capacitor/datacapture/barcode/pick/BarcodePickViewHandler;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "workerThread", "Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "load", "", "handleOnDestroy", "checkCameraPermission", "", "checkOrRequestCameraPermissions", NotificationCompat.CATEGORY_CALL, "Lcom/getcapacitor/PluginCall;", "onCameraPermissionResult", "finishCallback", "getDefaults", "registerBarcodeCaptureListenerForEvents", "unregisterBarcodeCaptureListenerForEvents", "setBarcodeCaptureModeEnabledState", "finishBarcodeCaptureDidUpdateSession", "finishBarcodeCaptureDidScan", "resetBarcodeCaptureSession", "updateBarcodeCaptureOverlay", "updateBarcodeCaptureMode", "applyBarcodeCaptureModeSettings", "registerBarcodeBatchListenerForEvents", "unregisterBarcodeBatchListenerForEvents", "finishBarcodeBatchDidUpdateSessionCallback", "setBarcodeBatchModeEnabledState", "registerListenerForBasicOverlayEvents", "unregisterListenerForBasicOverlayEvents", "registerBarcodeSelectionListenerForEvents", "unregisterBarcodeSelectionListenerForEvents", "setTextForAimToSelectAutoHint", "removeAimedBarcodeBrushProvider", "setAimedBarcodeBrushProvider", "finishBrushForAimedBarcodeCallback", "finishBrushForTrackedBarcodeCallback", "removeTrackedBarcodeBrushProvider", "setTrackedBarcodeBrushProvider", "finishBrushForTrackedBarcode", "selectAimedBarcode", "unselectBarcodes", "setSelectBarcodeEnabled", "increaseCountForBarcodes", "clearTrackedBarcodeBrushes", "setBrushForTrackedBarcode", "registerListenerForAdvancedOverlayEvents", "unregisterListenerForAdvancedOverlayEvents", "clearTrackedBarcodeViews", "resetBarcodeBatchSession", "setViewForTrackedBarcode", "updateSizeOfTrackedBarcodeView", "setOffsetForTrackedBarcode", "setAnchorForTrackedBarcode", "getCountForBarcodeInBarcodeSelectionSession", "resetBarcodeSelectionSession", "resetBarcodeSelection", "unfreezeCameraInBarcodeSelection", "finishBarcodeSelectionDidSelect", "finishBarcodeSelectionDidUpdateSession", "onFinishBarcodeSelectionDidUpdateSession", "finishData", "Lcom/scandit/capacitor/datacapture/core/data/SerializableFinishModeCallbackData;", "onFinishBarcodeSelectionDidSelect", "onJsonParseError", "error", "", "createBarcodeCountView", "removeBarcodeCountView", "updateBarcodeCountView", "updateBarcodeCountMode", "finishBarcodeCountOnScan", "registerBarcodeCountListener", "unregisterBarcodeCountListener", "registerBarcodeCountViewListener", "unregisterBarcodeCountViewListener", "registerBarcodeCountViewUiListener", "unregisterBarcodeCountViewUiListener", "resetBarcodeCountSession", "resetBarcodeCount", "startBarcodeCountScanningPhase", "endBarcodeCountScanningPhase", "clearBarcodeCountViewHighlights", "setBarcodeCountCaptureList", "setBarcodeCountViewPositionAndSize", "showBarcodeCountView", "hideBarcodeCountView", "getBarcodeCountSpatialMap", "getBarcodeCountSpatialMapWithHints", "setBarcodeCountModeEnabledState", "updateBarcodeCountFeedback", "finishBarcodeCountBrushForRecognizedBarcode", "finishBarcodeCountBrushForRecognizedBarcodeNotInList", "finishBarcodeCountOnBrushForAcceptedBarcode", "finishBarcodeCountOnBrushForRejectedBarcode", "barcodeCountViewEnableHardwareTrigger", "getBitmapFromBase64EncodedViewData", "Landroid/graphics/Bitmap;", "baseEncodedViewData", "", "emit", a.h, "payload", "", "", "hasListenersForEvent", "hasViewSpecificListenersForEvent", "viewId", "", "isFinishBarcodeSelectionDidUpdateSession", "data", "Lorg/json/JSONObject;", "isFinishBarcodeSelectionDidSelect", "checkFinishCallbackIdFieldForValue", "value", "createFindView", "updateFindView", "removeFindView", "updateFindMode", "registerBarcodeFindListener", "unregisterBarcodeFindListener", "registerBarcodeFindViewListener", "unregisterBarcodeFindViewListener", "barcodeFindSetItemList", "barcodeFindViewStopSearching", "barcodeFindViewStartSearching", "barcodeFindViewPauseSearching", "barcodeFindModeStart", "barcodeFindModePause", "barcodeFindModeStop", "showFindView", "hideFindView", "setBarcodeFindModeEnabledState", "setBarcodeTransformer", "unsetBarcodeTransformer", "submitBarcodeFindTransformerResult", "updateBarcodeFindFeedback", "createPickView", "removePickView", "updatePickView", "setPickViewPositionAndSize", "addPickActionListener", "removePickActionListener", "addBarcodePickScanningListener", "removeBarcodePickScanningListener", "addPickViewListener", "removePickViewListener", "addBarcodePickListener", "removeBarcodePickListener", "registerBarcodePickViewUiListener", "unregisterBarcodePickViewUiListener", "finishOnProductIdentifierForItems", "registerOnProductIdentifierForItemsListener", "unregisterOnProductIdentifierForItemsListener", "pickViewStart", "pickViewFreeze", "pickViewStop", "pickViewPause", "pickViewResume", "finishPickAction", "updateBarcodeSelectionBasicOverlay", "updateBarcodeSelectionMode", "applyBarcodeSelectionModeSettings", "updateBarcodeSelectionFeedback", "updateBarcodeBatchBasicOverlay", "updateBarcodeBatchAdvancedOverlay", "updateBarcodeBatchMode", "applyBarcodeBatchModeSettings", "createSparkScanView", "disposeSparkScanView", "updateSparkScanView", "updateSparkScanMode", "showSparkScanView", "hideSparkScanView", "registerSparkScanListenerForEvents", "unregisterSparkScanListenerForEvents", "setSparkScanModeEnabledState", "finishSparkScanDidUpdateSession", "finishSparkScanDidScan", "registerSparkScanViewListenerEvents", "unregisterSparkScanViewListenerEvents", "prepareSparkScanViewScanning", "startSparkScanViewScanning", "pauseSparkScanViewScanning", "stopSparkScanViewScanning", "registerSparkScanFeedbackDelegateForEvents", "unregisterSparkScanFeedbackDelegateForEvents", "submitSparkScanFeedbackForBarcode", "showSparkScanViewToast", "getViewId", "getModeId", "getDataCaptureViewId", "createBarcodeGenerator", "generateFromBase64EncodedData", "generateFromString", "disposeBarcodeGenerator", "Companion", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ScanditBarcodeNative extends Plugin implements Emitter {
    private static final String CORE_PLUGIN_NAME = "ScanditCaptureCoreNative";
    private static final String FIELD_RESULT = "result";
    private static final String WEB_VIEW_NOT_ATTACHED = "WebView not attached yet";
    private static final String WRONG_INPUT = "Wrong input parameter";
    private final BarcodeBatchModule barcodeBatchModule;
    private final BarcodeCaptureModule barcodeCaptureModule;
    private final BarcodeCountModule barcodeCountModule;
    private final BarcodeFindModule barcodeFindModule;
    private final BarcodePickModule barcodePickModule;
    private final BarcodeSelectionModule barcodeSelectionModule;
    private PluginHandle corePlugin;
    private final SparkScanModule sparkScanModule;
    private final BarcodeModule barcodeModule = new BarcodeModule();
    private final BarcodeGeneratorModule barcodeGeneratorModule = new BarcodeGeneratorModule(null, null, null, 7, null);
    private final BarcodeCountViewHandler barcodeCountViewHandler = new BarcodeCountViewHandler(null, 1, null);
    private final BarcodeFindViewHandler barcodeFindViewHandler = new BarcodeFindViewHandler(null, 1, null);
    private final BarcodePickViewHandler barcodePickViewHandler = new BarcodePickViewHandler(null, 1, null);
    private final FrameworksLog logger = DefaultFrameworksLog.INSTANCE.getInstance();
    private final WorkerThread workerThread = DefaultWorkerThread.INSTANCE.getInstance();
    private final MainThread mainThread = DefaultMainThread.INSTANCE.getInstance();

    @PluginMethod
    public final void updateSizeOfTrackedBarcodeView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
    }

    public ScanditBarcodeNative() {
        ScanditBarcodeNative scanditBarcodeNative = this;
        this.barcodeCaptureModule = BarcodeCaptureModule.INSTANCE.create(scanditBarcodeNative);
        this.barcodeBatchModule = BarcodeBatchModule.INSTANCE.create(scanditBarcodeNative);
        this.barcodeSelectionModule = new BarcodeSelectionModule(new FrameworksBarcodeSelectionListener(scanditBarcodeNative, null, null, 6, null), new FrameworksBarcodeSelectionAimedBrushProvider(scanditBarcodeNative), new FrameworksBarcodeSelectionTrackedBrushProvider(scanditBarcodeNative), null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        this.barcodeCountModule = BarcodeCountModule.INSTANCE.create(scanditBarcodeNative);
        this.barcodeFindModule = BarcodeFindModule.INSTANCE.create(scanditBarcodeNative);
        this.barcodePickModule = new BarcodePickModule(scanditBarcodeNative, null, null, 6, null);
        this.sparkScanModule = SparkScanModule.INSTANCE.create(scanditBarcodeNative);
    }

    @Override // com.getcapacitor.Plugin
    public void load() {
        super.load();
        PluginHandle plugin = this.bridge.getPlugin(CORE_PLUGIN_NAME);
        this.corePlugin = plugin;
        if (plugin != null) {
            Intrinsics.checkNotNull(plugin);
            Plugin pluginHandle = plugin.getInstance();
            Intrinsics.checkNotNull(pluginHandle, "null cannot be cast to non-null type com.scandit.capacitor.datacapture.core.ScanditCaptureCoreNative");
            Plugin pluginHandle2 = getPluginHandle().getInstance();
            Intrinsics.checkNotNullExpressionValue(pluginHandle2, "getInstance(...)");
            ((ScanditCaptureCoreNative) pluginHandle).registerPluginInstance(pluginHandle2);
        } else {
            this.logger.error("Core not found");
        }
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit load$lambda$0;
                load$lambda$0 = ScanditBarcodeNative.load$lambda$0(ScanditBarcodeNative.this);
                return load$lambda$0;
            }
        });
        BarcodeModule barcodeModule = this.barcodeModule;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        barcodeModule.onCreate(context);
        BarcodeCaptureModule barcodeCaptureModule = this.barcodeCaptureModule;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        barcodeCaptureModule.onCreate(context2);
        BarcodeBatchModule barcodeBatchModule = this.barcodeBatchModule;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        barcodeBatchModule.onCreate(context3);
        BarcodeSelectionModule barcodeSelectionModule = this.barcodeSelectionModule;
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        barcodeSelectionModule.onCreate(context4);
        BarcodeCountModule barcodeCountModule = this.barcodeCountModule;
        Context context5 = getContext();
        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
        barcodeCountModule.onCreate(context5);
        BarcodeFindModule barcodeFindModule = this.barcodeFindModule;
        Context context6 = getContext();
        Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
        barcodeFindModule.onCreate(context6);
        BarcodePickModule barcodePickModule = this.barcodePickModule;
        Context context7 = getContext();
        Intrinsics.checkNotNullExpressionValue(context7, "getContext(...)");
        barcodePickModule.onCreate(context7);
        SparkScanModule sparkScanModule = this.sparkScanModule;
        Context context8 = getContext();
        Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
        sparkScanModule.onCreate(context8);
        BarcodeGeneratorModule barcodeGeneratorModule = this.barcodeGeneratorModule;
        Context context9 = getContext();
        Intrinsics.checkNotNullExpressionValue(context9, "getContext(...)");
        barcodeGeneratorModule.onCreate(context9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$0(ScanditBarcodeNative scanditBarcodeNative) {
        BarcodeCountViewHandler barcodeCountViewHandler = scanditBarcodeNative.barcodeCountViewHandler;
        WebView webView = scanditBarcodeNative.bridge.getWebView();
        Intrinsics.checkNotNullExpressionValue(webView, "getWebView(...)");
        AppCompatActivity activity = scanditBarcodeNative.bridge.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        barcodeCountViewHandler.attachWebView(webView, activity);
        BarcodeFindViewHandler barcodeFindViewHandler = scanditBarcodeNative.barcodeFindViewHandler;
        WebView webView2 = scanditBarcodeNative.bridge.getWebView();
        Intrinsics.checkNotNullExpressionValue(webView2, "getWebView(...)");
        barcodeFindViewHandler.attachWebView(webView2);
        BarcodePickViewHandler barcodePickViewHandler = scanditBarcodeNative.barcodePickViewHandler;
        WebView webView3 = scanditBarcodeNative.bridge.getWebView();
        Intrinsics.checkNotNullExpressionValue(webView3, "getWebView(...)");
        AppCompatActivity activity2 = scanditBarcodeNative.bridge.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity2, "getActivity(...)");
        barcodePickViewHandler.attachWebView(webView3, activity2);
        return Unit.INSTANCE;
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnDestroy() {
        this.barcodeModule.onDestroy();
        this.barcodeCaptureModule.onDestroy();
        this.barcodeBatchModule.onDestroy();
        this.barcodeSelectionModule.onDestroy();
        this.barcodeCountModule.onDestroy();
        this.barcodeFindModule.onDestroy();
        this.barcodePickModule.onDestroy();
        this.sparkScanModule.onDestroy();
        this.barcodeGeneratorModule.onDestroy();
    }

    private final boolean checkCameraPermission() {
        return getPermissionState("camera") == PermissionState.GRANTED;
    }

    private final void checkOrRequestCameraPermissions(PluginCall call) {
        if (!checkCameraPermission()) {
            requestPermissionForAlias("camera", call, "onCameraPermissionResult");
        } else {
            onCameraPermissionResult(call);
        }
    }

    @PermissionCallback
    private final void onCameraPermissionResult(PluginCall call) {
        if (checkCameraPermission()) {
            call.resolve();
        } else {
            call.reject("Camera permissions not granted.");
        }
    }

    @PluginMethod
    public final void finishCallback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            JSObject data = call.getData();
            if (!data.has(FIELD_RESULT)) {
                throw new JSONException("Missing result field in response json");
            }
            JSONObject optJSONObject = data.optJSONObject(FIELD_RESULT);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            if (isFinishBarcodeSelectionDidUpdateSession(optJSONObject)) {
                onFinishBarcodeSelectionDidUpdateSession(SerializableFinishModeCallbackData.INSTANCE.fromJson(optJSONObject));
            } else if (isFinishBarcodeSelectionDidSelect(optJSONObject)) {
                onFinishBarcodeSelectionDidSelect(SerializableFinishModeCallbackData.INSTANCE.fromJson(optJSONObject));
            } else {
                throw new JSONException("Cannot recognise finish callback action with result " + optJSONObject);
            }
        } catch (RuntimeException e) {
            onJsonParseError(e, call);
        } catch (JSONException e2) {
            onJsonParseError(e2, call);
        }
    }

    @PluginMethod
    public final void getDefaults(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve(JSObject.fromJSONObject(new JSONObject(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(this.barcodeModule.getDefaults(), MapsKt.mapOf(TuplesKt.to("BarcodeCapture", this.barcodeCaptureModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("BarcodeBatch", this.barcodeBatchModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("BarcodeSelection", this.barcodeSelectionModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("BarcodeCount", this.barcodeCountModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("BarcodeFind", this.barcodeFindModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("BarcodePick", this.barcodePickModule.getDefaults()))), MapsKt.mapOf(TuplesKt.to("SparkScan", this.sparkScanModule.getDefaults()))))));
    }

    @PluginMethod
    public final void registerBarcodeCaptureListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.addListener(getModeId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeCaptureListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.removeListener(getModeId(call));
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeCaptureModeEnabledState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.setModeEnabled(getModeId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeCaptureDidUpdateSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.finishDidUpdateSession(getModeId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeCaptureDidScan(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.finishDidScan(getModeId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void resetBarcodeCaptureSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCaptureModule.resetSession(Long.valueOf(call.getData().getLong("frameSequenceId")));
        call.resolve();
    }

    @PluginMethod
    public final void updateBarcodeCaptureOverlay(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("overlayJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeCaptureModule.updateOverlay(getViewId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeCaptureMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeCaptureModule.updateModeFromJson(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void applyBarcodeCaptureModeSettings(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        int modeId = getModeId(call);
        String string = call.getData().getString("modeSettingsJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeCaptureModule.applyModeSettings(modeId, string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void registerBarcodeBatchListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.addBarcodeBatchListener(getModeId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeBatchListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.removeBarcodeBatchListener(getModeId(call));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeBatchDidUpdateSessionCallback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.finishDidUpdateSession(getModeId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeBatchModeEnabledState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.setModeEnabled(getModeId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void registerListenerForBasicOverlayEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.addBasicOverlayListener(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterListenerForBasicOverlayEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.removeBasicOverlayListener(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void registerBarcodeSelectionListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.addListener();
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeSelectionListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.removeListener();
        call.resolve();
    }

    @PluginMethod
    public final void setTextForAimToSelectAutoHint(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        BarcodeSelectionModule barcodeSelectionModule = this.barcodeSelectionModule;
        String string = call.getData().getString("text");
        if (string == null) {
            string = "";
        }
        barcodeSelectionModule.setTextForAimToSelectAutoHint(string, new CapacitorResult(call));
    }

    @PluginMethod
    public final void removeAimedBarcodeBrushProvider(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.removeAimedBarcodeBrushProvider();
        call.resolve();
    }

    @PluginMethod
    public final void setAimedBarcodeBrushProvider(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.setAimedBarcodeBrushProvider(new CapacitorResult(call));
    }

    @PluginMethod
    public final void finishBrushForAimedBarcodeCallback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("brush");
        String string2 = call.getData().getString("selectionIdentifier");
        if (string2 == null) {
            call.reject("selectionIdentifier is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.finishBrushForAimedBarcode(string, string2);
            call.resolve();
        }
    }

    @PluginMethod
    public final void finishBrushForTrackedBarcodeCallback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("brush");
        String string2 = call.getData().getString("selectionIdentifier");
        if (string2 == null) {
            call.reject("selectionIdentifier is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.finishBrushForTrackedBarcode(string, string2);
            call.resolve();
        }
    }

    @PluginMethod
    public final void removeTrackedBarcodeBrushProvider(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.removeTrackedBarcodeBrushProvider();
        call.resolve();
    }

    @PluginMethod
    public final void setTrackedBarcodeBrushProvider(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.setTrackedBarcodeBrushProvider(new CapacitorResult(call));
    }

    @PluginMethod
    public final void finishBrushForTrackedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("brush");
        String string2 = call.getData().getString("selectionIdentifier");
        if (string2 == null) {
            call.reject("selectionIdentifier is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.finishBrushForTrackedBarcode(string, string2);
            call.resolve();
        }
    }

    @PluginMethod
    public final void selectAimedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.selectAimedBarcode();
        call.resolve();
    }

    @PluginMethod
    public final void unselectBarcodes(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("barcodesStr");
        if (string == null) {
            call.reject("barcodesStr is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.unselectBarcodes(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void setSelectBarcodeEnabled(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("barcodesStr");
        if (string == null) {
            call.reject("barcodesStr is missing in the call parameters.");
        } else if (!call.getData().has(ExtentionsKt.ENABLED_KEY)) {
            call.reject("enabled is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.setSelectBarcodeEnabled(string, call.getData().getBoolean(ExtentionsKt.ENABLED_KEY), new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void increaseCountForBarcodes(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("barcodesStr");
        if (string == null) {
            call.reject("barcodesStr is missing in the call parameters.");
        } else {
            this.barcodeSelectionModule.increaseCountForBarcodes(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void clearTrackedBarcodeBrushes(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.clearBasicOverlayTrackedBarcodeBrushes(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void setBrushForTrackedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            String string = call.getData().getString("brushJson");
            Integer integer = call.getData().getInteger("trackedBarcodeIdentifier");
            long optLong = call.getData().optLong("sessionFrameSequenceID");
            if (string != null && integer != null) {
                this.barcodeBatchModule.setBasicOverlayBrushForTrackedBarcode(getDataCaptureViewId(call), string, integer.intValue(), Long.valueOf(optLong));
                call.resolve();
                return;
            }
            call.reject("Invalid brushJson or trackedBarcodeIdentifier received in setBrushForTrackedBarcode.");
        } catch (RuntimeException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        } catch (JSONException e2) {
            call.reject(new JsonParseError(e2.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void registerListenerForAdvancedOverlayEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.addAdvancedOverlayListener(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterListenerForAdvancedOverlayEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.removeAdvancedOverlayListener(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void clearTrackedBarcodeViews(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.clearAdvancedOverlayTrackedBarcodeViews(getDataCaptureViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void resetBarcodeBatchSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeBatchModule.resetSession(null);
        call.resolve();
    }

    @PluginMethod
    public final void setViewForTrackedBarcode(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            this.workerThread.runOnBackgroundThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit viewForTrackedBarcode$lambda$2;
                    viewForTrackedBarcode$lambda$2 = ScanditBarcodeNative.setViewForTrackedBarcode$lambda$2(PluginCall.this, this);
                    return viewForTrackedBarcode$lambda$2;
                }
            });
        } catch (RuntimeException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        } catch (JSONException e2) {
            call.reject(new JsonParseError(e2.getMessage()).toString());
        }
        call.resolve();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setViewForTrackedBarcode$lambda$2(PluginCall pluginCall, final ScanditBarcodeNative scanditBarcodeNative) {
        JSObject data = pluginCall.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        final SerializableAdvancedOverlayViewData serializableAdvancedOverlayViewData = new SerializableAdvancedOverlayViewData(data);
        SerializableAdvancedOverlayView view = serializableAdvancedOverlayViewData.getView();
        final Bitmap bitmapFromBase64EncodedViewData = scanditBarcodeNative.getBitmapFromBase64EncodedViewData(view != null ? view.getData() : null);
        scanditBarcodeNative.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit viewForTrackedBarcode$lambda$2$lambda$1;
                viewForTrackedBarcode$lambda$2$lambda$1 = ScanditBarcodeNative.setViewForTrackedBarcode$lambda$2$lambda$1(ScanditBarcodeNative.this, serializableAdvancedOverlayViewData, bitmapFromBase64EncodedViewData);
                return viewForTrackedBarcode$lambda$2$lambda$1;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setViewForTrackedBarcode$lambda$2$lambda$1(ScanditBarcodeNative scanditBarcodeNative, SerializableAdvancedOverlayViewData serializableAdvancedOverlayViewData, Bitmap bitmap) {
        SerializableAdvancedOverlayViewOptions options;
        SerializableAdvancedOverlayViewOptions options2;
        View trackedBarcodeViewFromBitmap = scanditBarcodeNative.barcodeBatchModule.getTrackedBarcodeViewFromBitmap(serializableAdvancedOverlayViewData.getTrackedBarcodeId(), bitmap);
        if (trackedBarcodeViewFromBitmap == null) {
            return Unit.INSTANCE;
        }
        SerializableAdvancedOverlayView view = serializableAdvancedOverlayViewData.getView();
        int i = -2;
        int width = (view == null || (options2 = view.getOptions()) == null) ? -2 : options2.getWidth();
        SerializableAdvancedOverlayView view2 = serializableAdvancedOverlayViewData.getView();
        if (view2 != null && (options = view2.getOptions()) != null) {
            i = options.getHeight();
        }
        trackedBarcodeViewFromBitmap.setLayoutParams(new ViewGroup.MarginLayoutParams(width, i));
        scanditBarcodeNative.barcodeBatchModule.setViewForTrackedBarcode(serializableAdvancedOverlayViewData.getDataCaptureViewId(), trackedBarcodeViewFromBitmap, serializableAdvancedOverlayViewData.getTrackedBarcodeId(), serializableAdvancedOverlayViewData.getSessionFrameSequenceId());
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void setOffsetForTrackedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            String string = call.getData().getString("offsetJson");
            Integer integer = call.getData().getInteger("trackedBarcodeIdentifier");
            long optLong = call.getData().optLong("sessionFrameSequenceID");
            if (string != null && integer != null) {
                this.barcodeBatchModule.setOffsetForTrackedBarcode(string, integer.intValue(), Long.valueOf(optLong), getDataCaptureViewId(call));
                call.resolve();
                return;
            }
            call.reject("Invalid offsetJson or trackedBarcodeIdentifier received in setOffsetForTrackedBarcode.");
        } catch (RuntimeException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        } catch (JSONException e2) {
            call.reject(new JsonParseError(e2.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void setAnchorForTrackedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            String string = call.getData().getString("anchor");
            Integer integer = call.getData().getInteger("trackedBarcodeIdentifier");
            long optLong = call.getData().optLong("sessionFrameSequenceID");
            if (string != null && integer != null) {
                this.barcodeBatchModule.setAnchorForTrackedBarcode(string, integer.intValue(), Long.valueOf(optLong), getDataCaptureViewId(call));
                call.resolve();
                return;
            }
            call.reject("Invalid anchorJson or trackedBarcodeIdentifier received in setAnchorForTrackedBarcode.");
        } catch (RuntimeException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        } catch (JSONException e2) {
            call.reject(new JsonParseError(e2.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void getCountForBarcodeInBarcodeSelectionSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        BarcodeSelectionModule barcodeSelectionModule = this.barcodeSelectionModule;
        String string = call.getData().getString("selectionIdentifier");
        if (string == null) {
            string = "";
        }
        barcodeSelectionModule.submitBarcodeCountForIdentifier(string, new CapacitorResult(call));
    }

    @PluginMethod
    public final void resetBarcodeSelectionSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.resetLatestSession(null);
        call.resolve();
    }

    @PluginMethod
    public final void resetBarcodeSelection(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.resetSelection();
        call.resolve();
    }

    @PluginMethod
    public final void unfreezeCameraInBarcodeSelection(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.unfreezeCamera();
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeSelectionDidSelect(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.finishDidSelect(call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeSelectionDidUpdateSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeSelectionModule.finishDidUpdateSession(call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    private final void onFinishBarcodeSelectionDidUpdateSession(SerializableFinishModeCallbackData finishData) {
        this.barcodeSelectionModule.finishDidUpdateSession(finishData != null ? finishData.getEnabled() : true);
    }

    private final void onFinishBarcodeSelectionDidSelect(SerializableFinishModeCallbackData finishData) {
        this.barcodeSelectionModule.finishDidSelect(finishData != null ? finishData.getEnabled() : true);
    }

    private final void onJsonParseError(Throwable error, PluginCall call) {
        error.printStackTrace();
        call.reject(new JsonParseError(error.getMessage()).toString());
    }

    @PluginMethod
    public final void createBarcodeCountView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("viewJson");
        if (string == null) {
            call.reject(WRONG_INPUT, "Missing or invalid viewJson");
            return;
        }
        final BarcodeCountView viewFromJson = this.barcodeCountModule.getViewFromJson(string);
        if (viewFromJson == null) {
            call.reject("Unable to create the BarcodeCountView from the given json=" + string);
            return;
        }
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit createBarcodeCountView$lambda$3;
                createBarcodeCountView$lambda$3 = ScanditBarcodeNative.createBarcodeCountView$lambda$3(ScanditBarcodeNative.this, viewFromJson, call);
                return createBarcodeCountView$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createBarcodeCountView$lambda$3(ScanditBarcodeNative scanditBarcodeNative, BarcodeCountView barcodeCountView, PluginCall pluginCall) {
        BarcodeCountViewHandler barcodeCountViewHandler = scanditBarcodeNative.barcodeCountViewHandler;
        AppCompatActivity activity = scanditBarcodeNative.bridge.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        barcodeCountViewHandler.attachBarcodeCountView(barcodeCountView, activity);
        scanditBarcodeNative.barcodeCountViewHandler.render();
        pluginCall.resolve();
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void removeBarcodeCountView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.viewDisposed(getViewId(call));
        this.barcodeCountViewHandler.disposeCurrentView();
        call.resolve();
    }

    @PluginMethod
    public final void updateBarcodeCountView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (this.barcodeCountViewHandler.getCurrentBarcodeCountView() == null) {
            call.reject("The barcode count view has not been initialized yet.");
            return;
        }
        String string = call.getData().getString("viewJson");
        String str = string;
        if (str == null || str.length() == 0) {
            call.reject("viewJson is required", WRONG_INPUT);
        } else {
            this.barcodeCountModule.updateBarcodeCountView(getViewId(call), string);
            call.resolve();
        }
    }

    @PluginMethod
    public final void updateBarcodeCountMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("barcodeCountJson");
        if (string == null) {
            call.reject(WRONG_INPUT, "barcodeCountJson is required");
        } else {
            this.barcodeCountModule.updateBarcodeCount(getViewId(call), string);
            call.resolve();
        }
    }

    @PluginMethod
    public final void finishBarcodeCountOnScan(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.finishOnScan(getViewId(call), true);
        call.resolve(null);
    }

    @PluginMethod
    public final void registerBarcodeCountListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.addBarcodeCountListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeCountListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.removeBarcodeCountListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void registerBarcodeCountViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.addBarcodeCountViewListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeCountViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.removeBarcodeCountViewListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void registerBarcodeCountViewUiListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.addBarcodeCountViewUiListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterBarcodeCountViewUiListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.removeBarcodeCountViewUiListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void resetBarcodeCountSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.resetBarcodeCountSession(getViewId(call), null);
        call.resolve();
    }

    @PluginMethod
    public final void resetBarcodeCount(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.resetBarcodeCount(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void startBarcodeCountScanningPhase(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.startScanningPhase(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void endBarcodeCountScanningPhase(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.endScanningPhase(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void clearBarcodeCountViewHighlights(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.clearHighlights(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeCountCaptureList(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (!call.getData().has("TargetBarcodes") || call.getData().getJSONArray("TargetBarcodes").length() == 0) {
            call.reject("No data provided");
            return;
        }
        JSONArray jSONArray = call.getData().getJSONArray("TargetBarcodes");
        BarcodeCountModule barcodeCountModule = this.barcodeCountModule;
        int viewId = getViewId(call);
        Intrinsics.checkNotNull(jSONArray);
        barcodeCountModule.setBarcodeCountCaptureList(viewId, jSONArray);
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeCountViewPositionAndSize(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            Double d = call.getDouble(RRWebVideoEvent.JsonKeys.TOP);
            if (d == null) {
                call.reject("Missing top position");
                return;
            }
            double doubleValue = d.doubleValue();
            Double d2 = call.getDouble(RRWebVideoEvent.JsonKeys.LEFT);
            if (d2 == null) {
                call.reject("Missing left position");
                return;
            }
            double doubleValue2 = d2.doubleValue();
            Double d3 = call.getDouble("width");
            if (d3 == null) {
                call.reject("Missing width");
                return;
            }
            double doubleValue3 = d3.doubleValue();
            Double d4 = call.getDouble("height");
            if (d4 == null) {
                call.reject("Missing height");
                return;
            }
            double doubleValue4 = d4.doubleValue();
            Boolean bool = call.getBoolean("shouldBeUnderWebView", false);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(RRWebVideoEvent.JsonKeys.TOP, doubleValue);
            jSONObject.put(RRWebVideoEvent.JsonKeys.LEFT, doubleValue2);
            jSONObject.put("width", doubleValue3);
            jSONObject.put("height", doubleValue4);
            jSONObject.put("shouldBeUnderWebView", bool);
            this.barcodeCountViewHandler.setResizeAndMoveInfo(new ResizeAndMoveInfo(jSONObject));
            call.resolve();
        } catch (JSONException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void showBarcodeCountView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.showView(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void hideBarcodeCountView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.hideView(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void getBarcodeCountSpatialMap(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.submitSpatialMap(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void getBarcodeCountSpatialMapWithHints(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Integer integer = call.getData().getInteger("expectedNumberOfRows");
        Intrinsics.checkNotNull(integer);
        int intValue = integer.intValue();
        Integer integer2 = call.getData().getInteger("expectedNumberOfColumns");
        Intrinsics.checkNotNull(integer2);
        this.barcodeCountModule.submitSpatialMap(getViewId(call), intValue, integer2.intValue(), new CapacitorResult(call));
    }

    @PluginMethod
    public final void setBarcodeCountModeEnabledState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.setModeEnabled(getViewId(call), call.getData().getBoolean("isEnabled"));
        call.resolve();
    }

    @PluginMethod
    public final void updateBarcodeCountFeedback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("feedbackJson");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            call.reject("No feedbackJson was provided for the function.");
        } else {
            this.barcodeCountModule.updateFeedback(getViewId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void finishBarcodeCountBrushForRecognizedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String optString = call.getData().optString("brushJson", "");
        String str = optString;
        this.barcodeCountModule.finishBrushForRecognizedBarcodeEvent(getViewId(call), (str == null || StringsKt.isBlank(str)) ? null : BrushDeserializer.fromJson(optString), call.getData().getInt("trackedBarcodeId"));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeCountBrushForRecognizedBarcodeNotInList(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String optString = call.getData().optString("brushJson", "");
        String str = optString;
        this.barcodeCountModule.finishBrushForRecognizedBarcodeNotInListEvent(getViewId(call), (str == null || StringsKt.isBlank(str)) ? null : BrushDeserializer.fromJson(optString), call.getData().getInt("trackedBarcodeId"));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeCountOnBrushForAcceptedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String optString = call.getData().optString("brushJson", "");
        String str = optString;
        this.barcodeCountModule.finishBrushForAcceptedBarcodeEvent(getViewId(call), (str == null || StringsKt.isBlank(str)) ? null : BrushDeserializer.fromJson(optString), call.getData().getInt("trackedBarcodeId"));
        call.resolve();
    }

    @PluginMethod
    public final void finishBarcodeCountOnBrushForRejectedBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String optString = call.getData().optString("brushJson", "");
        String str = optString;
        this.barcodeCountModule.finishBrushForRejectedBarcodeEvent(getViewId(call), (str == null || StringsKt.isBlank(str)) ? null : BrushDeserializer.fromJson(optString), call.getData().getInt("trackedBarcodeId"));
        call.resolve();
    }

    @PluginMethod
    public final void barcodeCountViewEnableHardwareTrigger(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeCountModule.enableHardwareTrigger(getViewId(call), call.getData().has(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE) ? call.getData().getInteger(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE) : null, new CapacitorResult(call));
    }

    private final Bitmap getBitmapFromBase64EncodedViewData(String baseEncodedViewData) {
        if (baseEncodedViewData == null) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(StringsKt.removeRange((CharSequence) baseEncodedViewData, 0, StringsKt.indexOf$default((CharSequence) baseEncodedViewData, FirebaseConstants.SEPARATOR, 0, false, 6, (Object) null)).toString(), 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public void emit(String eventName, Map<String, Object> payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(payload, "payload");
        JSObject jSObject = new JSObject();
        jSObject.put("name", eventName);
        jSObject.put("data", new JSONObject(payload).toString());
        notifyListeners(eventName, jSObject);
    }

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public boolean hasListenersForEvent(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        return hasListeners(eventName);
    }

    @Override // com.scandit.datacapture.frameworks.core.events.Emitter
    public boolean hasViewSpecificListenersForEvent(int viewId, String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        return hasListenersForEvent(eventName);
    }

    private final boolean isFinishBarcodeSelectionDidUpdateSession(JSONObject data) {
        return checkFinishCallbackIdFieldForValue(data, "BarcodeSelectionListener.didUpdateSession");
    }

    private final boolean isFinishBarcodeSelectionDidSelect(JSONObject data) {
        return checkFinishCallbackIdFieldForValue(data, "BarcodeSelectionListener.didUpdateSelection");
    }

    private final boolean checkFinishCallbackIdFieldForValue(JSONObject data, String value) {
        return data.has(SerializableCallbackAction.FIELD_FINISH_CALLBACK_ID) && Intrinsics.areEqual(data.get(SerializableCallbackAction.FIELD_FINISH_CALLBACK_ID), value);
    }

    @PluginMethod
    public final void createFindView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        final String string = call.getData().getString("json");
        if (string == null) {
            call.reject("missing parameter for createFindView()");
            return;
        }
        BarcodeFindViewHandler barcodeFindViewHandler = this.barcodeFindViewHandler;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        final FrameLayout prepareContainer = barcodeFindViewHandler.prepareContainer(context);
        prepareContainer.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                ScanditBarcodeNative.createFindView$lambda$5(ScanditBarcodeNative.this, prepareContainer, string, call);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createFindView$lambda$5(ScanditBarcodeNative scanditBarcodeNative, FrameLayout frameLayout, String str, PluginCall pluginCall) {
        scanditBarcodeNative.barcodeFindModule.addViewToContainer(frameLayout, str, new CapacitorResult(pluginCall));
        BarcodeFindViewHandler barcodeFindViewHandler = scanditBarcodeNative.barcodeFindViewHandler;
        int viewId = scanditBarcodeNative.getViewId(pluginCall);
        AppCompatActivity activity = scanditBarcodeNative.bridge.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        barcodeFindViewHandler.addBarcodeFindViewContainer(viewId, frameLayout, activity);
    }

    @PluginMethod
    public final void updateFindView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.updateBarcodeFindView(getViewId(call), call.getData().get("barcodeFindViewJson").toString(), new CapacitorResult(call));
    }

    @PluginMethod
    public final void removeFindView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        int viewId = getViewId(call);
        this.barcodeFindModule.viewDisposed(viewId);
        this.barcodeFindViewHandler.disposeContainer(viewId);
        call.resolve();
    }

    @PluginMethod
    public final void updateFindMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.updateBarcodeFindMode(getViewId(call), call.getData().get("barcodeFindJson").toString(), new CapacitorResult(call));
    }

    @PluginMethod
    public final void registerBarcodeFindListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.addBarcodeFindListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void unregisterBarcodeFindListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.removeBarcodeFindListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void registerBarcodeFindViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.addBarcodeFindViewListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void unregisterBarcodeFindViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.removeBarcodeFindViewListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindSetItemList(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.setItemList(getViewId(call), call.getData().get("itemsJson").toString(), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindViewStopSearching(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.viewStopSearching(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindViewStartSearching(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.viewStartSearching(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindViewPauseSearching(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.viewPauseSearching(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindModeStart(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.modeStart(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindModePause(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.modePause(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void barcodeFindModeStop(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.modeStop(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void showFindView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        int viewId = getViewId(call);
        this.barcodeFindViewHandler.setVisible(viewId);
        this.barcodeFindModule.showView(viewId);
        call.resolve();
    }

    @PluginMethod
    public final void hideFindView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        int viewId = getViewId(call);
        this.barcodeFindViewHandler.setInvisible(viewId);
        this.barcodeFindModule.hideView(viewId);
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeFindModeEnabledState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.setModeEnabled(getViewId(call), call.getData().getBoolean(ExtentionsKt.ENABLED_KEY));
        call.resolve();
    }

    @PluginMethod
    public final void setBarcodeTransformer(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.setBarcodeFindTransformer(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void unsetBarcodeTransformer(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.unsetBarcodeFindTransformer(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void submitBarcodeFindTransformerResult(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodeFindModule.submitBarcodeFindTransformerResult(getViewId(call), call.getData().getString("transformedBarcode", null), new CapacitorResult(call));
    }

    @PluginMethod
    public final void updateBarcodeFindFeedback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("feedbackJson");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            call.reject("No feedbackJson was provided for the function.");
        } else {
            this.barcodeFindModule.updateFeedback(getViewId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void createPickView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        final String string = call.getString("json");
        if (string != null) {
            BarcodePickViewHandler barcodePickViewHandler = this.barcodePickViewHandler;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            final FrameLayout prepareContainer = barcodePickViewHandler.prepareContainer(context);
            prepareContainer.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    ScanditBarcodeNative.createPickView$lambda$7$lambda$6(ScanditBarcodeNative.this, prepareContainer, string, call);
                }
            });
            call.resolve();
            return;
        }
        call.reject("missing parameter for createPickView()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createPickView$lambda$7$lambda$6(ScanditBarcodeNative scanditBarcodeNative, FrameLayout frameLayout, String str, PluginCall pluginCall) {
        scanditBarcodeNative.barcodePickModule.addViewToContainer(frameLayout, str, new CapacitorResult(pluginCall));
        BarcodePickViewHandler barcodePickViewHandler = scanditBarcodeNative.barcodePickViewHandler;
        AppCompatActivity activity = scanditBarcodeNative.bridge.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        barcodePickViewHandler.addBarcodePickViewContainer(frameLayout, activity);
        scanditBarcodeNative.barcodePickViewHandler.render();
    }

    @PluginMethod
    public final void removePickView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.releasePickView(getViewId(call), new CapacitorResult(call));
        this.barcodePickViewHandler.disposeCurrentView();
    }

    @PluginMethod
    public final void updatePickView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String jSObject = call.getData().toString();
        Intrinsics.checkNotNullExpressionValue(jSObject, "toString(...)");
        this.barcodePickModule.updateView(getViewId(call), jSObject, new CapacitorResult(call));
    }

    @PluginMethod
    public final void setPickViewPositionAndSize(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            Double d = call.getDouble(RRWebVideoEvent.JsonKeys.TOP);
            if (d == null) {
                call.reject("Missing top position");
                return;
            }
            double doubleValue = d.doubleValue();
            Double d2 = call.getDouble(RRWebVideoEvent.JsonKeys.LEFT);
            if (d2 == null) {
                call.reject("Missing left position");
                return;
            }
            double doubleValue2 = d2.doubleValue();
            Double d3 = call.getDouble("width");
            if (d3 == null) {
                call.reject("Missing width");
                return;
            }
            double doubleValue3 = d3.doubleValue();
            Double d4 = call.getDouble("height");
            if (d4 == null) {
                call.reject("Missing height");
                return;
            }
            double doubleValue4 = d4.doubleValue();
            Boolean bool = call.getBoolean("shouldBeUnderWebView", false);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(RRWebVideoEvent.JsonKeys.TOP, doubleValue);
            jSONObject.put(RRWebVideoEvent.JsonKeys.LEFT, doubleValue2);
            jSONObject.put("width", doubleValue3);
            jSONObject.put("height", doubleValue4);
            jSONObject.put("shouldBeUnderWebView", bool);
            this.barcodePickViewHandler.setResizeAndMoveInfo(new ResizeAndMoveInfo(jSONObject));
            call.resolve();
        } catch (JSONException e) {
            call.reject(new JsonParseError(e.getMessage()).toString());
        }
    }

    @PluginMethod
    public final void addPickActionListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.addActionListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void removePickActionListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.removeActionListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void addBarcodePickScanningListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.addScanningListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void removeBarcodePickScanningListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.removeScanningListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void addPickViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.addViewListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void removePickViewListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.removeViewListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void addBarcodePickListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.addBarcodePickListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void removeBarcodePickListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.removeBarcodePickListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void registerBarcodePickViewUiListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.addViewUiListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void unregisterBarcodePickViewUiListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.removeViewUiListener(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void finishOnProductIdentifierForItems(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("itemsJson");
        if (string == null) {
            string = "";
        }
        this.barcodePickModule.finishOnProductIdentifierForItems(MapsKt.hashMapOf(TuplesKt.to("viewId", Integer.valueOf(getViewId(call))), TuplesKt.to("data", string)), new CapacitorResult(call));
    }

    @PluginMethod
    public final void registerOnProductIdentifierForItemsListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void unregisterOnProductIdentifierForItemsListener(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void pickViewStart(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.startPickView(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void pickViewFreeze(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.freezePickView(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void pickViewStop(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.stopPickView(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void pickViewPause(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.pausePickView(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void pickViewResume(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.barcodePickModule.resumePickView(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void finishPickAction(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Boolean bool = call.getBoolean(FIELD_RESULT);
        if (bool == null) {
            call.reject("failed to parse finishPickAction JSON.");
            return;
        }
        String string = call.getData().getString("code");
        if (string == null) {
            string = "";
        }
        this.barcodePickModule.finishPickAction(MapsKt.hashMapOf(TuplesKt.to("viewId", Integer.valueOf(getViewId(call))), TuplesKt.to("itemData", string), TuplesKt.to(FIELD_RESULT, bool)), new CapacitorResult(call));
    }

    @PluginMethod
    public final void updateBarcodeSelectionBasicOverlay(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("overlayJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeSelectionModule.updateBasicOverlay(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeSelectionMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeSelectionModule.updateModeFromJson(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void applyBarcodeSelectionModeSettings(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeSettingsJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeSelectionModule.applyModeSettings(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeSelectionFeedback(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("feedbackJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeSelectionModule.updateFeedback(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeBatchBasicOverlay(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("overlayJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeBatchModule.updateBasicOverlay(getDataCaptureViewId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeBatchAdvancedOverlay(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("overlayJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeBatchModule.updateAdvancedOverlay(getDataCaptureViewId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void updateBarcodeBatchMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeBatchModule.updateModeFromJson(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void applyBarcodeBatchModeSettings(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("modeSettingsJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeBatchModule.applyModeSettings(getModeId(call), string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void createSparkScanView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        final WebView webView = this.bridge.getWebView();
        if (webView == null) {
            call.reject(WEB_VIEW_NOT_ATTACHED);
            return;
        }
        final String string = call.getData().getString("viewJson");
        if (string == null) {
            string = "";
        }
        webView.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ScanditBarcodeNative.createSparkScanView$lambda$11(ScanditBarcodeNative.this, webView, string, call);
            }
        });
        checkOrRequestCameraPermissions(call);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createSparkScanView$lambda$11(ScanditBarcodeNative scanditBarcodeNative, WebView webView, String str, PluginCall pluginCall) {
        scanditBarcodeNative.sparkScanModule.addViewToContainer(webView, str, new CapacitorResult(pluginCall));
    }

    @PluginMethod
    public final void disposeSparkScanView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit disposeSparkScanView$lambda$12;
                disposeSparkScanView$lambda$12 = ScanditBarcodeNative.disposeSparkScanView$lambda$12(ScanditBarcodeNative.this, call);
                return disposeSparkScanView$lambda$12;
            }
        });
        call.resolve();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit disposeSparkScanView$lambda$12(ScanditBarcodeNative scanditBarcodeNative, PluginCall pluginCall) {
        scanditBarcodeNative.sparkScanModule.disposeView(scanditBarcodeNative.getViewId(pluginCall));
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void updateSparkScanView(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("viewJson");
        if (string == null) {
            string = "";
        }
        this.sparkScanModule.updateView(getViewId(call), string, new CapacitorResult(call));
    }

    @PluginMethod
    public final void updateSparkScanMode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("sparkScanJson");
        if (string == null) {
            string = "";
        }
        this.sparkScanModule.updateMode(getViewId(call), string, new CapacitorResult(call));
    }

    @PluginMethod
    public final void showSparkScanView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit showSparkScanView$lambda$13;
                showSparkScanView$lambda$13 = ScanditBarcodeNative.showSparkScanView$lambda$13(ScanditBarcodeNative.this, call);
                return showSparkScanView$lambda$13;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSparkScanView$lambda$13(ScanditBarcodeNative scanditBarcodeNative, PluginCall pluginCall) {
        scanditBarcodeNative.sparkScanModule.showView(scanditBarcodeNative.getViewId(pluginCall), new CapacitorResult(pluginCall));
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void hideSparkScanView(final PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.ScanditBarcodeNative$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit hideSparkScanView$lambda$14;
                hideSparkScanView$lambda$14 = ScanditBarcodeNative.hideSparkScanView$lambda$14(ScanditBarcodeNative.this, call);
                return hideSparkScanView$lambda$14;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit hideSparkScanView$lambda$14(ScanditBarcodeNative scanditBarcodeNative, PluginCall pluginCall) {
        scanditBarcodeNative.sparkScanModule.hideView(scanditBarcodeNative.getViewId(pluginCall), new CapacitorResult(pluginCall));
        return Unit.INSTANCE;
    }

    @PluginMethod
    public final void registerSparkScanListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.addSparkScanListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterSparkScanListenerForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.removeSparkScanListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void setSparkScanModeEnabledState(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.setModeEnabled(getViewId(call), call.getData().getBoolean("isEnabled"));
        call.resolve();
    }

    @PluginMethod
    public final void finishSparkScanDidUpdateSession(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.finishDidUpdateSessionCallback(getViewId(call), call.getData().getBoolean("isEnabled"));
        call.resolve();
    }

    @PluginMethod
    public final void finishSparkScanDidScan(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.finishDidScanCallback(getViewId(call), call.getData().getBoolean("isEnabled"));
        call.resolve();
    }

    @PluginMethod
    public final void registerSparkScanViewListenerEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.addSparkScanViewUiListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void unregisterSparkScanViewListenerEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.removeSparkScanViewUiListener(getViewId(call));
        call.resolve();
    }

    @PluginMethod
    public final void prepareSparkScanViewScanning(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void startSparkScanViewScanning(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.startScanning(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void pauseSparkScanViewScanning(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.pauseScanning(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void stopSparkScanViewScanning(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.resolve();
    }

    @PluginMethod
    public final void registerSparkScanFeedbackDelegateForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.addFeedbackDelegate(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void unregisterSparkScanFeedbackDelegateForEvents(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.removeFeedbackDelegate(getViewId(call), new CapacitorResult(call));
    }

    @PluginMethod
    public final void submitSparkScanFeedbackForBarcode(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.sparkScanModule.submitFeedbackForBarcode(getViewId(call), call.getData().getString("feedbackJson", null), new CapacitorResult(call));
    }

    @PluginMethod
    public final void showSparkScanViewToast(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("text");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.sparkScanModule.showToast(getViewId(call), string, new CapacitorResult(call));
        }
    }

    private final int getViewId(PluginCall call) {
        return call.getData().getInt("viewId");
    }

    private final int getModeId(PluginCall call) {
        return call.getData().getInt(ExtentionsKt.MODE_ID_KEY);
    }

    private final int getDataCaptureViewId(PluginCall call) {
        return call.getData().getInt(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY);
    }

    @PluginMethod
    public final void createBarcodeGenerator(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("barcodeGeneratorJson");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeGeneratorModule.createGenerator(string, new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void generateFromBase64EncodedData(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("generatorId");
        if (string == null) {
            call.reject(WRONG_INPUT);
            return;
        }
        String string2 = call.getData().getString("data");
        if (string2 == null) {
            call.reject(WRONG_INPUT);
            return;
        }
        Integer integer = call.getData().getInteger("imageWidth");
        if (integer == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeGeneratorModule.generateFromBase64EncodedData(string, string2, integer.intValue(), new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void generateFromString(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("generatorId");
        if (string == null) {
            call.reject(WRONG_INPUT);
            return;
        }
        String string2 = call.getData().getString("text");
        if (string2 == null) {
            call.reject(WRONG_INPUT);
            return;
        }
        Integer integer = call.getData().getInteger("imageWidth");
        if (integer == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeGeneratorModule.generate(string, string2, integer.intValue(), new CapacitorResult(call));
        }
    }

    @PluginMethod
    public final void disposeBarcodeGenerator(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String string = call.getData().getString("generatorId");
        if (string == null) {
            call.reject(WRONG_INPUT);
        } else {
            this.barcodeGeneratorModule.disposeGenerator(string, new CapacitorResult(call));
        }
    }
}
