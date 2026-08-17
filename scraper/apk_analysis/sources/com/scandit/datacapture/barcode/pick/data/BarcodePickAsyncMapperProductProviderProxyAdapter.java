package com.scandit.datacapture.barcode.pick.data;

import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeAsyncMapperProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallback;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0013\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderProxyAdapter;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderProxy;", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProvider;", "_NativeAsyncMapperProductProvider", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProvider;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeAsyncMapperProductProvider;", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeProductProvider;", "_productProviderImpl", "()Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeProductProvider;", "Ljava/util/ArrayList;", "", "itemsData", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallback;", PluginMethod.RETURN_CALLBACK, "", "_productIdentifierForItems", "(Ljava/util/ArrayList;Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallback;)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickAsyncMapperProductProviderProxyAdapter implements BarcodePickAsyncMapperProductProviderProxy {
    private final NativeAsyncMapperProductProvider a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeProductProvider c;

    public BarcodePickAsyncMapperProductProviderProxyAdapter(NativeAsyncMapperProductProvider _NativeAsyncMapperProductProvider, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeAsyncMapperProductProvider, "_NativeAsyncMapperProductProvider");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeAsyncMapperProductProvider;
        this.proxyCache = proxyCache;
        NativeProductProvider asProductProvider = _NativeAsyncMapperProductProvider.asProductProvider();
        Intrinsics.checkNotNullExpressionValue(asProductProvider, "asProductProvider(...)");
        this.c = asProductProvider;
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    /* renamed from: _impl, reason: from getter */
    public NativeAsyncMapperProductProvider getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    public void _productIdentifierForItems(ArrayList<String> itemsData, BarcodePickProductProviderCallback callback) {
        Intrinsics.checkNotNullParameter(itemsData, "itemsData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        NativeProductProviderCallback a = callback.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeProductProviderCallback.class), null, a, callback);
        this.a.productIdentifierForItems(itemsData, a);
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderProxy
    /* renamed from: _productProviderImpl, reason: from getter */
    public NativeProductProvider getC() {
        return this.c;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    public /* synthetic */ BarcodePickAsyncMapperProductProviderProxyAdapter(NativeAsyncMapperProductProvider nativeAsyncMapperProductProvider, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeAsyncMapperProductProvider, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
