package com.scandit.datacapture.core.capture;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.provider.Settings;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.storage.db.k;
import com.scandit.datacapture.core.imu.ImuDataCollector;
import com.scandit.datacapture.core.internal.module.capture.NativeDeviceIdUtils;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.capture.AssetResourceLoader;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeLicenseInfo;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.init.CoreLibraryLoader;
import com.scandit.datacapture.core.internal.sdk.license.LicenseTextProvider;
import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.license.LicenseInfo;
import com.scandit.datacapture.core.license.OpenSourceSoftwareLicenseInfo;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetter;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetterKind;
import com.scandit.datacapture.tools.internal.sdk.ProxySetter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\f\b\u0007\u0018\u0000 Y2\u00020\u0001:\u0005YZ[\\]B\u001b\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0097\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u0013\u0010\u0012J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0097\u0001¢\u0006\u0004\b\u0014\u0010\fJ\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0097\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0097\u0001¢\u0006\u0004\b\u001d\u0010\fJ\u0018\u0010 \u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001eH\u0097\u0001¢\u0006\u0004\b \u0010!J%\u0010$\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"H\u0007¢\u0006\u0004\b$\u0010%J\u0015\u0010(\u001a\u00020\u00162\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\u00020\u00162\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b*\u0010)J\u0015\u0010,\u001a\u00020\u00162\u0006\u0010'\u001a\u00020+¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\u00020\u00162\u0006\u0010'\u001a\u00020+¢\u0006\u0004\b.\u0010-J\u0015\u0010/\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b/\u00100J\u0015\u00101\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b1\u00100J\u0015\u00102\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b2\u00100J\r\u00103\u001a\u00020\u0016¢\u0006\u0004\b3\u00104J\r\u00105\u001a\u00020\u0016¢\u0006\u0004\b5\u00104J\r\u00106\u001a\u00020\u0016¢\u0006\u0004\b6\u00104J\r\u00108\u001a\u000207¢\u0006\u0004\b8\u00109J\u0015\u0010;\u001a\u00020\u00162\u0006\u0010:\u001a\u000207¢\u0006\u0004\b;\u0010<R$\u0010C\u001a\u0004\u0018\u00010\u00198\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR \u0010I\u001a\b\u0012\u0004\u0012\u00020&0D8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR \u0010L\u001a\b\u0012\u0004\u0012\u00020+0D8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bJ\u0010F\u001a\u0004\bK\u0010HR\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u00198F¢\u0006\u0006\u001a\u0004\bM\u0010@R\u0013\u0010Q\u001a\u0004\u0018\u00010N8F¢\u0006\u0006\u001a\u0004\bO\u0010PR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020\b0R8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0016\u0010X\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bV\u0010W¨\u0006^"}, d2 = {"Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Lcom/scandit/datacapture/core/capture/DataCaptureContextProxy;", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "impl", "Lcom/scandit/datacapture/core/capture/DataCaptureContextProxyAdapter;", "adapter", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;Lcom/scandit/datacapture/core/capture/DataCaptureContextProxyAdapter;)V", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "mode", "Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_addModeAsyncWrapped", "(Lcom/scandit/datacapture/core/capture/DataCaptureMode;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_context", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "_removeAllModesAsyncWrapped", "()Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_removeCurrentModeAsyncWrapped", "_removeModeAsyncWrapped", "dataCaptureContext", "", "_setContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "Lcom/scandit/datacapture/core/source/FrameSource;", "frameSource", "_setFrameSourceAsyncWrapped", "(Lcom/scandit/datacapture/core/source/FrameSource;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_setModeAsyncWrapped", "Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;", "settings", "applySettings", "(Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;)V", "Ljava/lang/Runnable;", "whenDone", "setFrameSource", "(Lcom/scandit/datacapture/core/source/FrameSource;Ljava/lang/Runnable;)V", "Lcom/scandit/datacapture/core/capture/DataCaptureContextListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/core/capture/DataCaptureContextListener;)V", "removeListener", "Lcom/scandit/datacapture/core/capture/DataCaptureContextFrameListener;", "addFrameListener", "(Lcom/scandit/datacapture/core/capture/DataCaptureContextFrameListener;)V", "removeFrameListener", "addMode", "(Lcom/scandit/datacapture/core/capture/DataCaptureMode;)V", "setMode", "removeMode", "removeCurrentMode", "()V", "removeAllModes", "release", "", "_isCameraTimestampsRealtime", "()Z", "enable", "_setShouldUseImuData", "(Z)V", "b", "Lcom/scandit/datacapture/core/source/FrameSource;", "get_frameSource$scandit_capture_core", "()Lcom/scandit/datacapture/core/source/FrameSource;", "set_frameSource$scandit_capture_core", "(Lcom/scandit/datacapture/core/source/FrameSource;)V", "_frameSource", "Ljava/util/concurrent/CopyOnWriteArraySet;", "g", "Ljava/util/concurrent/CopyOnWriteArraySet;", "getListeners$scandit_capture_core", "()Ljava/util/concurrent/CopyOnWriteArraySet;", "listeners", "h", "getFrameListeners$scandit_capture_core", "frameListeners", "getFrameSource", "Lcom/scandit/datacapture/core/license/LicenseInfo;", "getLicenseInfo", "()Lcom/scandit/datacapture/core/license/LicenseInfo;", "licenseInfo", "", "get_modes$scandit_capture_core", "()Ljava/util/List;", "_modes", "get_activeMode$scandit_capture_core", "()Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "_activeMode", "Companion", "com/scandit/datacapture/core/capture/a", "com/scandit/datacapture/core/capture/b", "com/scandit/datacapture/core/capture/c", "com/scandit/datacapture/core/capture/d", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class DataCaptureContext implements DataCaptureContextProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DEVICE_ID;
    private static DataCaptureContext k;
    private final DataCaptureContextProxyAdapter a;

    /* renamed from: b, reason: from kotlin metadata */
    private FrameSource _frameSource;
    private final ArrayList c;
    private DataCaptureMode d;
    private final Object e;
    private boolean f;

    /* renamed from: g, reason: from kotlin metadata */
    private final CopyOnWriteArraySet listeners;

    /* renamed from: h, reason: from kotlin metadata */
    private final CopyOnWriteArraySet frameListeners;
    private final e i;
    private final ImuDataCollector j;

    static {
        String str;
        if (CoreLibraryLoader.INSTANCE.isInitialized$scandit_capture_core()) {
            str = NativeDeviceIdUtils.hashDeviceId(new com.scandit.datacapture.core.internal.module.device.e(AppAndroidEnvironment.INSTANCE.getApplicationContext()).a());
            Intrinsics.checkNotNull(str);
        } else {
            str = "UNINITIALIZED_DEVICE_ID";
        }
        DEVICE_ID = str;
    }

    public DataCaptureContext(NativeDataCaptureContext impl, DataCaptureContextProxyAdapter adapter) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.a = adapter;
        this.c = new ArrayList();
        Object obj = new Object();
        this.e = obj;
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        this.listeners = copyOnWriteArraySet;
        this.frameListeners = new CopyOnWriteArraySet();
        e eVar = new e(this);
        this.i = eVar;
        this.j = new ImuDataCollector(new c(this));
        _setContext(this);
        int i = 4;
        DefaultConstructorMarker defaultConstructorMarker = null;
        ProxyCache proxyCache = null;
        impl.addListenerAsync(new DataCaptureContextListenerReversedAdapter(new b(this), this, proxyCache, i, defaultConstructorMarker), NativeDataCaptureContext.getListenerPriorityUser());
        impl.addFrameListenerAsync(new DataCaptureContextFrameListenerReversedAdapter(new a(this), this, proxyCache, i, defaultConstructorMarker));
        synchronized (obj) {
            copyOnWriteArraySet.add(new d());
            Unit unit = Unit.INSTANCE;
        }
        AppAndroidEnvironment.INSTANCE.getAppLifecycleListener().registerOnActivityStoppedCallback(eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(DataCaptureContext this$0, FrameSource frameSource, Runnable runnable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._frameSource = frameSource;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(DataCaptureContext this$0, DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mode, "$mode");
        this$0.getClass();
        mode._setDataCaptureContext(null);
        this$0.c.remove(mode);
        if (Intrinsics.areEqual(mode, this$0.d)) {
            this$0.d = null;
            if (this$0.c.isEmpty()) {
                return;
            }
            this$0.d = (DataCaptureMode) CollectionsKt.last((List) this$0.c);
        }
    }

    @JvmStatic
    public static final DataCaptureContextBuilder builder(String str) {
        return INSTANCE.builder(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(DataCaptureContext this$0, DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mode, "$mode");
        this$0.getClass();
        mode._setDataCaptureContext(this$0);
        CollectionsKt.removeAll((List) this$0.c, (Function1) new f(mode));
        this$0.d = mode;
        this$0.c.add(mode);
    }

    @JvmStatic
    public static final DataCaptureContext forLicenseKey(String str) {
        return INSTANCE.forLicenseKey(str);
    }

    public static final OpenSourceSoftwareLicenseInfo getOpenSourceSoftwareLicenseInfo() {
        return INSTANCE.getOpenSourceSoftwareLicenseInfo();
    }

    public static final DataCaptureContext getSharedInstance() {
        return INSTANCE.getSharedInstance();
    }

    @JvmStatic
    public static final DataCaptureContext initialize(String str) {
        return INSTANCE.initialize(str);
    }

    public static /* synthetic */ void setFrameSource$default(DataCaptureContext dataCaptureContext, FrameSource frameSource, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        dataCaptureContext.setFrameSource(frameSource, runnable);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "addModeAsyncWrapped")
    public NativeWrappedFuture _addModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return this.a._addModeAsyncWrapped(mode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyGetter(ProxyGetterKind.WITH_SETTER)
    public DataCaptureContext _context() {
        return this.a._context();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeDataCaptureContext getA() {
        return this.a.getA();
    }

    public final boolean _isCameraTimestampsRealtime() {
        NativeAndroidCamera a;
        FrameSource frameSource = get_frameSource();
        Camera camera = frameSource instanceof Camera ? (Camera) frameSource : null;
        return (camera == null || (a = camera.getA()) == null || !a.isTimestampRealtime()) ? false : true;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "removeAllModesAsyncWrapped")
    public NativeWrappedFuture _removeAllModesAsyncWrapped() {
        return this.a._removeAllModesAsyncWrapped();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "removeCurrentModeAsyncWrapped")
    public NativeWrappedFuture _removeCurrentModeAsyncWrapped() {
        return this.a._removeCurrentModeAsyncWrapped();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "removeModeAsyncWrapped")
    public NativeWrappedFuture _removeModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return this.a._removeModeAsyncWrapped(mode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxySetter
    public void _setContext(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        this.a._setContext(dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "setFrameSourceAsyncWrapped")
    public NativeWrappedFuture _setFrameSourceAsyncWrapped(FrameSource frameSource) {
        return this.a._setFrameSourceAsyncWrapped(frameSource);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "setModeAsyncWrapped")
    public NativeWrappedFuture _setModeAsyncWrapped(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return this.a._setModeAsyncWrapped(mode);
    }

    public final void _setShouldUseImuData(boolean enable) {
        this.j.setEnabled(enable);
        getA().setShouldUseImuDataAsync(enable);
    }

    public final void addFrameListener(DataCaptureContextFrameListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.frameListeners.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    public final void addListener(DataCaptureContextListener listener) {
        boolean z;
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.e) {
            z = this.listeners.add(listener) ? this.f : false;
        }
        if (z) {
            listener.onObservationStarted(this);
        }
    }

    public final void addMode(final DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeExtensionsKt.andThen(_addModeAsyncWrapped(mode), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.a(DataCaptureContext.this, mode);
            }
        });
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextProxy
    @ProxyFunction(nativeName = "applySettings")
    public void applySettings(DataCaptureContextSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.applySettings(settings);
    }

    public final CopyOnWriteArraySet<DataCaptureContextFrameListener> getFrameListeners$scandit_capture_core() {
        return this.frameListeners;
    }

    /* renamed from: getFrameSource, reason: from getter */
    public final FrameSource get_frameSource() {
        return this._frameSource;
    }

    public final LicenseInfo getLicenseInfo() {
        NativeLicenseInfo licenseInfo = getA().getLicenseInfo();
        if (licenseInfo != null) {
            return new LicenseInfo(licenseInfo);
        }
        return null;
    }

    public final CopyOnWriteArraySet<DataCaptureContextListener> getListeners$scandit_capture_core() {
        return this.listeners;
    }

    /* renamed from: get_activeMode$scandit_capture_core, reason: from getter */
    public final /* synthetic */ DataCaptureMode getD() {
        return this.d;
    }

    public final FrameSource get_frameSource$scandit_capture_core() {
        return this._frameSource;
    }

    public final /* synthetic */ List get_modes$scandit_capture_core() {
        return this.c;
    }

    public final void release() {
        AppAndroidEnvironment.INSTANCE.getAppLifecycleListener().deregisterOnActivityStoppedCallback(this.i);
        getA().disposeAsync();
    }

    public final void removeAllModes() {
        NativeExtensionsKt.andThen(_removeAllModesAsyncWrapped(), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.a(DataCaptureContext.this);
            }
        });
    }

    public final void removeCurrentMode() {
        NativeExtensionsKt.andThen(_removeCurrentModeAsyncWrapped(), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.b(DataCaptureContext.this);
            }
        });
    }

    public final void removeFrameListener(DataCaptureContextFrameListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.frameListeners.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    public final void removeListener(DataCaptureContextListener listener) {
        boolean z;
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.e) {
            z = this.listeners.remove(listener) ? this.f : false;
        }
        if (z) {
            listener.onObservationStopped(this);
        }
    }

    public final void removeMode(final DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeExtensionsKt.andThen(_removeModeAsyncWrapped(mode), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.b(DataCaptureContext.this, mode);
            }
        });
    }

    public final void setFrameSource(FrameSource frameSource) {
        setFrameSource$default(this, frameSource, null, 2, null);
    }

    public final void setMode(final DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        NativeExtensionsKt.andThen(_setModeAsyncWrapped(mode), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.c(DataCaptureContext.this, mode);
            }
        });
    }

    public final void set_frameSource$scandit_capture_core(FrameSource frameSource) {
        this._frameSource = frameSource;
    }

    @JvmStatic
    public static final DataCaptureContext initialize(String str, String str2, String str3, String str4, String str5, DataCaptureContextSettings dataCaptureContextSettings) {
        return INSTANCE.initialize(str, str2, str3, str4, str5, dataCaptureContextSettings);
    }

    public final void setFrameSource(final FrameSource frameSource, final Runnable whenDone) {
        NativeExtensionsKt.andThen(_setFrameSourceAsyncWrapped(frameSource), new Runnable() { // from class: com.scandit.datacapture.core.capture.DataCaptureContext$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureContext.a(DataCaptureContext.this, frameSource, whenDone);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(DataCaptureContext this$0, DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mode, "$mode");
        this$0.getClass();
        mode._setDataCaptureContext(this$0);
        CollectionsKt.removeAll((List) this$0.c, (Function1) new f(mode));
        this$0.d = mode;
        this$0.c.add(mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(DataCaptureContext this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d = null;
        Iterator it = this$0.c.iterator();
        while (it.hasNext()) {
            ((DataCaptureMode) it.next())._setDataCaptureContext(null);
        }
        this$0.c.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(DataCaptureContext this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DataCaptureMode dataCaptureMode = this$0.d;
        if (dataCaptureMode != null) {
            dataCaptureMode._setDataCaptureContext(null);
            this$0.c.remove(dataCaptureMode);
            if (Intrinsics.areEqual(dataCaptureMode, this$0.d)) {
                this$0.d = null;
                if (this$0.c.isEmpty()) {
                    return;
                }
                this$0.d = (DataCaptureMode) CollectionsKt.last((List) this$0.c);
            }
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006JE\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0013\u0010\u0006JQ\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0019\u001a\u00020\u00148FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\"\u0010\u001fR\u0018\u0010#\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/scandit/datacapture/core/capture/DataCaptureContext$Companion;", "", "", "licenseKey", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "forLicenseKey", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "frameworkName", "frameworkVersion", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_DEVICE_NAME, "externalId", "Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;", "settings", "_forAllProperties$scandit_capture_core", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;)Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "_forAllProperties", "Lcom/scandit/datacapture/core/capture/DataCaptureContextBuilder;", "builder", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/capture/DataCaptureContextBuilder;", "initialize", "Lcom/scandit/datacapture/core/license/OpenSourceSoftwareLicenseInfo;", "getOpenSourceSoftwareLicenseInfo", "()Lcom/scandit/datacapture/core/license/OpenSourceSoftwareLicenseInfo;", "getOpenSourceSoftwareLicenseInfo$annotations", "()V", "openSourceSoftwareLicenseInfo", "getSharedInstance", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getSharedInstance$annotations", "sharedInstance", "DEVICE_ID", "Ljava/lang/String;", "EMPTY_LICENSE_KEY", "FRAMEWORK_NAME", "UNINITIALIZED_DEVICE_ID", "_sharedInstance", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final File access$getWorkingDirectory(Companion companion) {
            companion.getClass();
            File noBackupFilesDir = AppAndroidEnvironment.INSTANCE.getApplicationContext().getNoBackupFilesDir();
            Intrinsics.checkNotNullExpressionValue(noBackupFilesDir, "getNoBackupFilesDir(...)");
            return noBackupFilesDir;
        }

        @JvmStatic
        public static /* synthetic */ void getOpenSourceSoftwareLicenseInfo$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void getSharedInstance$annotations() {
        }

        public static /* synthetic */ DataCaptureContext initialize$default(Companion companion, String str, String str2, String str3, String str4, String str5, DataCaptureContextSettings dataCaptureContextSettings, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "native";
            }
            String str6 = str2;
            String str7 = (i & 4) != 0 ? null : str3;
            String str8 = (i & 8) != 0 ? null : str4;
            String str9 = (i & 16) != 0 ? null : str5;
            if ((i & 32) != 0) {
                dataCaptureContextSettings = new DataCaptureContextSettings();
            }
            return companion.initialize(str, str6, str7, str8, str9, dataCaptureContextSettings);
        }

        @JvmStatic
        public final DataCaptureContext _forAllProperties$scandit_capture_core(String licenseKey, String frameworkName, String frameworkVersion, String deviceName, String externalId, DataCaptureContextSettings settings) {
            Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
            Intrinsics.checkNotNullParameter(frameworkName, "frameworkName");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return new DataCaptureContext(licenseKey, frameworkName, frameworkVersion, deviceName, externalId, settings, null);
        }

        @JvmStatic
        public final DataCaptureContextBuilder builder(String licenseKey) {
            Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
            return new DataCaptureContextBuilder(licenseKey);
        }

        @JvmStatic
        public final DataCaptureContext forLicenseKey(String licenseKey) {
            Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
            return builder(licenseKey).build();
        }

        public final OpenSourceSoftwareLicenseInfo getOpenSourceSoftwareLicenseInfo() {
            return new OpenSourceSoftwareLicenseInfo(LicenseTextProvider.INSTANCE.getLicenseText());
        }

        public final DataCaptureContext getSharedInstance() {
            DataCaptureContext dataCaptureContext = DataCaptureContext.k;
            return dataCaptureContext == null ? initialize("") : dataCaptureContext;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final DataCaptureContext initialize(String licenseKey, String frameworkName, String frameworkVersion, String deviceName, String externalId, DataCaptureContextSettings settings) {
            String str;
            Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
            Intrinsics.checkNotNullParameter(settings, "settings");
            AppAndroidEnvironment appAndroidEnvironment = AppAndroidEnvironment.INSTANCE;
            File noBackupFilesDir = appAndroidEnvironment.getApplicationContext().getNoBackupFilesDir();
            Intrinsics.checkNotNullExpressionValue(noBackupFilesDir, "getNoBackupFilesDir(...)");
            String absolutePath = noBackupFilesDir.getAbsolutePath();
            String str2 = Build.VERSION.RELEASE;
            String str3 = frameworkName == null ? "native" : frameworkName;
            String str4 = Build.MODEL;
            String packageName = appAndroidEnvironment.getApplicationContext().getPackageName();
            Context context = appAndroidEnvironment.getApplicationContext();
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.scandit.device_id", 0);
            DataCaptureContextProxyAdapter dataCaptureContextProxyAdapter = null;
            Object[] objArr = 0;
            String string = sharedPreferences.getString(k.a.p, null);
            if (string == null) {
                str = Settings.Secure.getString(context.getContentResolver(), lib.android.paypal.com.magnessdk.c.f);
                if (str == null || str.length() < 16) {
                    str = null;
                }
                if (str == null) {
                    String uuid = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
                    str = "bad1d000" + StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(k.a.p, str);
                    edit.apply();
                }
            } else {
                str = string;
            }
            String str5 = deviceName == null ? "" : deviceName;
            String str6 = externalId == null ? "" : externalId;
            ArrayList arrayList = new ArrayList(com.scandit.datacapture.core.internal.module.capture.b.a(appAndroidEnvironment.getApplicationContext()));
            AssetManager assets = appAndroidEnvironment.getApplicationContext().getAssets();
            Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
            NativeDataCaptureContext initialize = NativeDataCaptureContext.initialize(licenseKey, absolutePath, str2, str3, frameworkVersion, str4, packageName, str, str5, str6, arrayList, new AssetResourceLoader(assets), false, settings.getA());
            DataCaptureContext dataCaptureContext = DataCaptureContext.k;
            if (dataCaptureContext == null) {
                Intrinsics.checkNotNull(initialize);
                dataCaptureContext = new DataCaptureContext(initialize, dataCaptureContextProxyAdapter, 2, objArr == true ? 1 : 0);
            }
            DataCaptureContext.k = dataCaptureContext;
            return dataCaptureContext;
        }

        @JvmStatic
        public final DataCaptureContext initialize(String licenseKey) {
            Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
            return initialize(licenseKey, "native", null, null, null, new DataCaptureContextSettings());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ DataCaptureContext(NativeDataCaptureContext nativeDataCaptureContext, DataCaptureContextProxyAdapter dataCaptureContextProxyAdapter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeDataCaptureContext, (i & 2) != 0 ? new DataCaptureContextProxyAdapter(nativeDataCaptureContext, null, 2, 0 == true ? 1 : 0) : dataCaptureContextProxyAdapter);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DataCaptureContext(java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, com.scandit.datacapture.core.capture.DataCaptureContextSettings r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r15 = this;
            com.scandit.datacapture.core.capture.DataCaptureContext$Companion r0 = com.scandit.datacapture.core.capture.DataCaptureContext.INSTANCE
            java.io.File r0 = com.scandit.datacapture.core.capture.DataCaptureContext.Companion.access$getWorkingDirectory(r0)
            java.lang.String r2 = r0.getAbsolutePath()
            java.lang.String r3 = android.os.Build.VERSION.RELEASE
            if (r17 != 0) goto L12
            java.lang.String r0 = "native"
            r4 = r0
            goto L14
        L12:
            r4 = r17
        L14:
            java.lang.String r6 = android.os.Build.MODEL
            com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment r0 = com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment.INSTANCE
            android.content.Context r1 = r0.getApplicationContext()
            java.lang.String r7 = r1.getPackageName()
            com.scandit.datacapture.core.internal.module.device.e r1 = new com.scandit.datacapture.core.internal.module.device.e
            android.content.Context r5 = r0.getApplicationContext()
            r1.<init>(r5)
            java.lang.String r8 = r1.a()
            java.lang.String r1 = ""
            if (r19 != 0) goto L33
            r9 = r1
            goto L35
        L33:
            r9 = r19
        L35:
            if (r20 != 0) goto L39
            r10 = r1
            goto L3b
        L39:
            r10 = r20
        L3b:
            java.util.ArrayList r11 = new java.util.ArrayList
            android.content.Context r1 = r0.getApplicationContext()
            java.util.ArrayList r1 = com.scandit.datacapture.core.internal.module.capture.b.a(r1)
            r11.<init>(r1)
            com.scandit.datacapture.core.internal.sdk.capture.AssetResourceLoader r12 = new com.scandit.datacapture.core.internal.sdk.capture.AssetResourceLoader
            android.content.Context r0 = r0.getApplicationContext()
            android.content.res.AssetManager r0 = r0.getAssets()
            java.lang.String r1 = "getAssets(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r12.<init>(r0)
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings r14 = r21.getA()
            r13 = 0
            r1 = r16
            r5 = r18
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext r0 = com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext.createWithDeviceName(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            java.lang.String r1 = "createWithDeviceName(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r1 = 2
            r2 = 0
            r3 = r15
            r15.<init>(r0, r2, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.capture.DataCaptureContext.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.scandit.datacapture.core.capture.DataCaptureContextSettings, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
