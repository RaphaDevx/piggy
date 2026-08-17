package com.scandit.datacapture.core.capture.serialization;

import com.scandit.datacapture.core.component.DataCaptureComponent;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.component.NativeDataCaptureComponent;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerResultProxyAdapter;", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerResultProxy;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializerResult;", "_NativeDataCaptureContextDeserializerResult", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializerResult;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializerResult;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "Lcom/scandit/datacapture/core/component/DataCaptureComponent;", "getComponents", "()Ljava/util/List;", "components", "", "getWarnings", "warnings", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureContextDeserializerResultProxyAdapter implements DataCaptureContextDeserializerResultProxy {
    private final NativeDataCaptureContextDeserializerResult a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public DataCaptureContextDeserializerResultProxyAdapter(NativeDataCaptureContextDeserializerResult _NativeDataCaptureContextDeserializerResult, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeDataCaptureContextDeserializerResult, "_NativeDataCaptureContextDeserializerResult");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeDataCaptureContextDeserializerResult;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerResultProxy
    /* renamed from: _impl, reason: from getter */
    public NativeDataCaptureContextDeserializerResult getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerResultProxy
    public List<DataCaptureComponent> getComponents() {
        ArrayList<NativeDataCaptureComponent> components = this.a.getComponents();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(components);
        return coreNativeTypeFactory.convert(components);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerResultProxy
    public List<String> getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    public /* synthetic */ DataCaptureContextDeserializerResultProxyAdapter(NativeDataCaptureContextDeserializerResult nativeDataCaptureContextDeserializerResult, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeDataCaptureContextDeserializerResult, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
