package com.scandit.datacapture.barcode.find.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.find.feedback.BarcodeFindFeedback;
import com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindTransformer;
import com.scandit.datacapture.barcode.internal.module.find.capture.h;
import com.scandit.datacapture.barcode.internal.module.find.ui.listener.SearchedItemsUpdateListener;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategy;
import com.scandit.datacapture.core.source.VideoResolution;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.protocol.Feedback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 H2\u00020\u00012\u00020\u0002:\u0004HIJKB\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0097\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\rH\u0097\u0001¢\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\rH\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u000fJ\u0019\u0010\u0014\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0019\u001a\u00020\r2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u001b\u0010\u0006J\u0015\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b \u0010\u001fJ\u0017\u0010$\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020!H\u0000¢\u0006\u0004\b\"\u0010#J\u0017\u0010&\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020!H\u0000¢\u0006\u0004\b%\u0010#J\u0017\u0010+\u001a\u00020\r2\u0006\u0010(\u001a\u00020'H\u0000¢\u0006\u0004\b)\u0010*J\u0017\u0010-\u001a\u00020\r2\u0006\u0010(\u001a\u00020'H\u0000¢\u0006\u0004\b,\u0010*J\u0015\u00100\u001a\u00020\r2\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101R*\u0010:\u001a\u0002022\u0006\u00103\u001a\u0002028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R.\u0010/\u001a\u0004\u0018\u00010.2\b\u00103\u001a\u0004\u0018\u00010.8\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u00101R$\u0010B\u001a\u00020@2\u0006\u0010A\u001a\u00020@8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010G¨\u0006L"}, d2 = {"Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindProxy;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindSettings;", "settings", "<init>", "(Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindSettings;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/NativeBarcodeFind;", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/find/capture/NativeBarcodeFind;", "", "pause", "()V", "start", "stop", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "_setDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindItem;", "items", "setItemList", "(Ljava/util/Set;)V", "applySettings", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindListener;)V", "removeListener", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/InternalBarcodeFindListener;", "addOverlayListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/find/capture/InternalBarcodeFindListener;)V", "addOverlayListener", "removeOverlayListener$scandit_barcode_capture", "removeOverlayListener", "Lcom/scandit/datacapture/barcode/internal/module/find/ui/listener/SearchedItemsUpdateListener;", "searchedItemsUpdateListener", "addSearchedItemsUpdateListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/find/ui/listener/SearchedItemsUpdateListener;)V", "addSearchedItemsUpdateListener", "removeSearchedItemsUpdateListener$scandit_barcode_capture", "removeSearchedItemsUpdateListener", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;", "transformer", "setTransformer", "(Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;)V", "Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;", "value", "b", "Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;", "getFeedback", "()Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;", "setFeedback", "(Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;)V", Feedback.TYPE, "i", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;", "getTransformer$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;", "setTransformer$scandit_barcode_capture", "", "<set-?>", "isEnabled", "()Z", "setEnabled", "(Z)V", "getDataCaptureContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Companion", "com/scandit/datacapture/barcode/find/capture/a", "com/scandit/datacapture/barcode/find/capture/b", "InternalTransformer", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFind implements DataCaptureMode, BarcodeFindProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeFindProxy a;

    /* renamed from: b, reason: from kotlin metadata */
    private BarcodeFindFeedback feedback;
    private DataCaptureContext c;
    private Map d;
    private final Object e;
    private final CopyOnWriteArraySet f;
    private final CopyOnWriteArraySet g;
    private final CopyOnWriteArraySet h;

    /* renamed from: i, reason: from kotlin metadata */
    private /* synthetic */ BarcodeFindTransformer transformer;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\r\u001a\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind$Companion;", "", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindProxy;", "proxy", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "withProxy$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindProxy;)Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "withProxy", "Lcom/scandit/datacapture/core/source/CameraSettings;", "getRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/source/CameraSettings;", "getRecommendedCameraSettings$annotations", "()V", "recommendedCameraSettings", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getRecommendedCameraSettings$annotations() {
        }

        public final CameraSettings getRecommendedCameraSettings() {
            CameraSettings cameraSettings = new CameraSettings();
            cameraSettings.setPreferredResolution(VideoResolution.UHD4K);
            cameraSettings.setFocusGestureStrategy(FocusGestureStrategy.MANUAL_UNTIL_CAPTURE);
            cameraSettings.setProperty("preferredAspectRatio", "fourToThree");
            cameraSettings.setZoomGestureZoomFactor(1.0f);
            cameraSettings.setProperty("focusStrategy", "continuousUntilNoScan");
            cameraSettings.setProperty("scanPhaseNoSreTimeout", Float.valueOf(3.0f));
            cameraSettings.setProperty("exposureTargetBias", Float.valueOf(-1.0f));
            return cameraSettings;
        }

        public final BarcodeFind withProxy$scandit_barcode_capture(BarcodeFindProxy proxy) {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            return new BarcodeFind(proxy, null);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind$InternalTransformer;", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/NativeBarcodeFindTransformer;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;", "transformer", "<init>", "(Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;)V", "", "transformBarcodeData", "(Ljava/lang/String;)Ljava/lang/String;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class InternalTransformer extends NativeBarcodeFindTransformer {
        private final BarcodeFindTransformer a;

        public InternalTransformer(BarcodeFindTransformer transformer) {
            Intrinsics.checkNotNullParameter(transformer, "transformer");
            this.a = transformer;
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindTransformer
        public String transformBarcodeData(String transformBarcodeData) {
            return this.a.transformBarcodeData(transformBarcodeData);
        }
    }

    public /* synthetic */ BarcodeFind(BarcodeFindProxy barcodeFindProxy, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeFindProxy);
    }

    private final void a(LinkedHashMap linkedHashMap) {
        synchronized (this.e) {
            this.d = linkedHashMap;
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final Map access$getSearchedItems(BarcodeFind barcodeFind) {
        Map map;
        synchronized (barcodeFind.e) {
            map = barcodeFind.d;
        }
        return map;
    }

    public static final CameraSettings getRecommendedCameraSettings() {
        return INSTANCE.getRecommendedCameraSettings();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @NativeImpl
    /* renamed from: _dataCaptureModeImpl */
    public NativeDataCaptureMode getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeFind getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.c = dataCaptureContext;
    }

    public final void addListener(BarcodeFindListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.add(listener);
    }

    public final void addOverlayListener$scandit_barcode_capture(InternalBarcodeFindListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.g.add(listener)) {
            listener.a(this);
        }
    }

    public final void addSearchedItemsUpdateListener$scandit_barcode_capture(SearchedItemsUpdateListener searchedItemsUpdateListener) {
        Map map;
        Intrinsics.checkNotNullParameter(searchedItemsUpdateListener, "searchedItemsUpdateListener");
        if (this.h.add(searchedItemsUpdateListener)) {
            synchronized (this.e) {
                map = this.d;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put((KeyableByteArray) entry.getKey(), entry.getValue());
            }
            searchedItemsUpdateListener.a(linkedHashMap);
        }
    }

    public final void applySettings(BarcodeFindSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        getA().applySettingsWrapped(settings._impl());
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext, reason: from getter */
    public DataCaptureContext getC() {
        return this.c;
    }

    public final BarcodeFindFeedback getFeedback() {
        return this.feedback;
    }

    /* renamed from: getTransformer$scandit_barcode_capture, reason: from getter */
    public final BarcodeFindTransformer getTransformer() {
        return this.transformer;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public boolean isEnabled() {
        return this.a.isEnabled();
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindProxy
    @ProxyFunction(nativeName = "pauseAsync")
    public void pause() {
        this.a.pause();
    }

    public final void removeListener(BarcodeFindListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.remove(listener);
    }

    public final void removeOverlayListener$scandit_barcode_capture(InternalBarcodeFindListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.g.remove(listener)) {
            listener.b(this);
        }
    }

    public final void removeSearchedItemsUpdateListener$scandit_barcode_capture(SearchedItemsUpdateListener searchedItemsUpdateListener) {
        Intrinsics.checkNotNullParameter(searchedItemsUpdateListener, "searchedItemsUpdateListener");
        this.h.remove(searchedItemsUpdateListener);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public void setEnabled(boolean z) {
        this.a.setEnabled(z);
    }

    public final void setFeedback(BarcodeFindFeedback value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.feedback = value;
        getA().setFeedback(new BarcodeFind$setNativeFeedback$1(value));
        getA().setItemListUpdatedFeedback(new BarcodeFind$setNativeFeedback$2(value));
    }

    public final void setItemList(Set<BarcodeFindItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        synchronized (this.e) {
            NativeBarcodeFind a = getA();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(items, 10));
            Iterator<T> it = items.iterator();
            while (it.hasNext()) {
                arrayList.add(NativeBarcodeFindItem.createWithRaw(((BarcodeFindItem) it.next()).getSearchOptions().getBarcodeRawData()));
            }
            a.setItemListAsync(CollectionsKt.toHashSet(arrayList));
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(items, 10)), 16));
            for (Object obj : items) {
                linkedHashMap.put(BarcodeFindKt.toKeyable(((BarcodeFindItem) obj).getSearchOptions().getBarcodeRawData()), obj);
            }
            a(linkedHashMap);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void setTransformer(BarcodeFindTransformer transformer) {
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        setTransformer$scandit_barcode_capture(transformer);
    }

    public final void setTransformer$scandit_barcode_capture(BarcodeFindTransformer barcodeFindTransformer) {
        this.transformer = barcodeFindTransformer;
        if (barcodeFindTransformer != null) {
            getA().setBarcodeFindTransformerAsync(new InternalTransformer(barcodeFindTransformer));
        }
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindProxy
    @ProxyFunction(nativeName = "startAsync")
    public void start() {
        this.a.start();
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindProxy
    @ProxyFunction(nativeName = "stopAsync")
    public void stop() {
        this.a.stop();
    }

    private BarcodeFind(BarcodeFindProxy barcodeFindProxy) {
        this.a = barcodeFindProxy;
        BarcodeFindFeedback defaultFeedback = BarcodeFindFeedback.INSTANCE.defaultFeedback();
        getA().setFeedback(new BarcodeFind$setNativeFeedback$1(defaultFeedback));
        getA().setItemListUpdatedFeedback(new BarcodeFind$setNativeFeedback$2(defaultFeedback));
        this.feedback = defaultFeedback;
        this.d = MapsKt.emptyMap();
        this.e = new Object();
        this.f = new CopyOnWriteArraySet();
        this.g = new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet();
        getA().addListenerAsync(new h(new b(this), this), NativeDataCaptureContext.getListenerPriorityUser());
        getA().addListenerAsync(new h(new a(this), this), NativeDataCaptureContext.getListenerPriorityOverlay());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeFind(com.scandit.datacapture.barcode.find.capture.BarcodeFindSettings r4) {
        /*
            r3 = this;
            java.lang.String r0 = "settings"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.scandit.datacapture.barcode.find.capture.BarcodeFindProxyAdapter r0 = new com.scandit.datacapture.barcode.find.capture.BarcodeFindProxyAdapter
            com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings r4 = r4._impl()
            r1 = 0
            com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind r4 = com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind.create(r1, r4)
            java.lang.String r2 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            r2 = 2
            r0.<init>(r4, r1, r2, r1)
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.find.capture.BarcodeFind.<init>(com.scandit.datacapture.barcode.find.capture.BarcodeFindSettings):void");
    }
}
