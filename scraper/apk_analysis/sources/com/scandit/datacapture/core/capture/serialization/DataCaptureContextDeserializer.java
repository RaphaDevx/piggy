package com.scandit.datacapture.core.capture.serialization;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.component.DataCaptureComponent;
import com.scandit.datacapture.core.component.serialization.DataCaptureComponentDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializerResult;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializer;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetter;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetterKind;
import com.scandit.datacapture.tools.internal.sdk.ProxySetter;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u00029:B=\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eB5\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0004\b\r\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000bH\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0097\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0097\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b#\u0010$J5\u0010+\u001a\u00020\"2\u0006\u0010&\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010'2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u00062\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b+\u0010,R$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u0010\u001fR$\u00108\u001a\u0002022\u0006\u00103\u001a\u0002028W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006;"}, d2 = {"Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializer;", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerProxy;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;", "frameSourceDeserializer", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;", "viewDeserializer", "", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureModeDeserializer;", "modeDeserializers", "Lcom/scandit/datacapture/core/component/serialization/DataCaptureComponentDeserializer;", "componentDeserializers", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializer;", "impl", "<init>", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;Ljava/util/List;Ljava/util/List;Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializer;)V", "(Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;Ljava/util/List;Ljava/util/List;)V", "_deserializer", "()Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializer;", "_impl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureContextDeserializer;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializer;)V", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerHelper;", "helper", "_setHelper", "(Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerHelper;)V", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_setListener", "(Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerListener;)V", "", "jsonData", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerResult;", "contextFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerResult;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/component/DataCaptureComponent;", "components", "updateContextFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/core/ui/DataCaptureView;Ljava/util/List;Ljava/lang/String;)Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerResult;", "g", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerListener;", "getListener", "()Lcom/scandit/datacapture/core/capture/serialization/DataCaptureContextDeserializerListener;", "setListener", "", "<set-?>", "getAvoidThreadDependencies", "()Z", "setAvoidThreadDependencies", "(Z)V", "avoidThreadDependencies", "com/scandit/datacapture/core/capture/serialization/a", "com/scandit/datacapture/core/capture/serialization/b", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class DataCaptureContextDeserializer implements DataCaptureContextDeserializerProxy {
    private final FrameSourceDeserializer a;
    private final DataCaptureViewDeserializer b;
    private final List c;
    private final List d;
    private final /* synthetic */ DataCaptureContextDeserializerProxyAdapter e;
    private final b f;

    /* renamed from: g, reason: from kotlin metadata */
    private DataCaptureContextDeserializerListener listener;

    /* JADX WARN: Multi-variable type inference failed */
    public DataCaptureContextDeserializer(FrameSourceDeserializer frameSourceDeserializer, DataCaptureViewDeserializer viewDeserializer, List<? extends DataCaptureModeDeserializer> modeDeserializers, List<? extends DataCaptureComponentDeserializer> componentDeserializers, NativeDataCaptureContextDeserializer impl) {
        Intrinsics.checkNotNullParameter(frameSourceDeserializer, "frameSourceDeserializer");
        Intrinsics.checkNotNullParameter(viewDeserializer, "viewDeserializer");
        Intrinsics.checkNotNullParameter(modeDeserializers, "modeDeserializers");
        Intrinsics.checkNotNullParameter(componentDeserializers, "componentDeserializers");
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = frameSourceDeserializer;
        this.b = viewDeserializer;
        this.c = modeDeserializers;
        this.d = componentDeserializers;
        this.e = new DataCaptureContextDeserializerProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        b bVar = new b(frameSourceDeserializer);
        this.f = bVar;
        _setDeserializer(this);
        _setHelper(bVar);
        impl.setListener(new DataCaptureContextDeserializerListenerReversedAdapter(new a(this), this, null, 4, null));
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxyGetter(ProxyGetterKind.WITH_SETTER)
    public DataCaptureContextDeserializer _deserializer() {
        return this.e._deserializer();
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeDataCaptureContextDeserializer getA() {
        return this.e.getA();
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxySetter
    public void _setDeserializer(DataCaptureContextDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.e._setDeserializer(deserializer);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxyFunction(nativeName = "setHelper")
    public void _setHelper(DataCaptureContextDeserializerHelper helper) {
        this.e._setHelper(helper);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxyFunction(nativeName = "setListener")
    public void _setListener(DataCaptureContextDeserializerListener listener) {
        this.e._setListener(listener);
    }

    public final DataCaptureContextDeserializerResult contextFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeDataCaptureContextDeserializerResult contextFromJson = getA().contextFromJson(new JsonValue(jsonData).getA());
        DataCaptureContext dataCaptureContext = this.f.a;
        DataCaptureView deserializedView = this.b.get_helper().getDeserializedView();
        if (dataCaptureContext == null) {
            throw new AssertionError("Null deserializedContext");
        }
        Intrinsics.checkNotNull(contextFromJson);
        DataCaptureContextDeserializerResult dataCaptureContextDeserializerResult = new DataCaptureContextDeserializerResult(contextFromJson, dataCaptureContext, deserializedView);
        this.f.a = null;
        this.a.get_helper().clear();
        this.b.get_helper().clear();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((DataCaptureModeDeserializer) it.next()).get_helper().clear();
        }
        Iterator it2 = this.d.iterator();
        while (it2.hasNext()) {
            ((DataCaptureComponentDeserializer) it2.next()).get_helper().clear();
        }
        return dataCaptureContextDeserializerResult;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxyFunction(property = "avoidThreadDependencies")
    public boolean getAvoidThreadDependencies() {
        return this.e.getAvoidThreadDependencies();
    }

    public final DataCaptureContextDeserializerListener getListener() {
        return this.listener;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerProxy
    @ProxyFunction(property = "avoidThreadDependencies")
    public void setAvoidThreadDependencies(boolean z) {
        this.e.setAvoidThreadDependencies(z);
    }

    public final void setListener(DataCaptureContextDeserializerListener dataCaptureContextDeserializerListener) {
        this.listener = dataCaptureContextDeserializerListener;
    }

    public final DataCaptureContextDeserializerResult updateContextFromJson(DataCaptureContext dataCaptureContext, DataCaptureView view, List<? extends DataCaptureComponent> components, String jsonData) {
        NativeDataCaptureView nativeDataCaptureView;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(components, "components");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        ProxyCacheKt.getGlobalProxyCache().put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, dataCaptureContext._impl(), dataCaptureContext);
        if (view != null) {
            ProxyCacheKt.getGlobalProxyCache().put(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view._impl(), view);
            nativeDataCaptureView = view._impl();
        } else {
            nativeDataCaptureView = null;
        }
        NativeDataCaptureContextDeserializer a = getA();
        NativeDataCaptureContext _impl = dataCaptureContext._impl();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(components, 10));
        Iterator<T> it = components.iterator();
        while (it.hasNext()) {
            arrayList.add(((DataCaptureComponent) it.next()).getC());
        }
        NativeDataCaptureContextDeserializerResult updateContextFromJson = a.updateContextFromJson(_impl, nativeDataCaptureView, new ArrayList<>(arrayList), new JsonValue(jsonData).getA());
        if (view == null) {
            view = this.b.get_helper().getDeserializedView();
        }
        Intrinsics.checkNotNull(updateContextFromJson);
        DataCaptureContextDeserializerResult dataCaptureContextDeserializerResult = new DataCaptureContextDeserializerResult(updateContextFromJson, dataCaptureContext, view);
        this.f.a = null;
        this.a.get_helper().clear();
        this.b.get_helper().clear();
        Iterator it2 = this.c.iterator();
        while (it2.hasNext()) {
            ((DataCaptureModeDeserializer) it2.next()).get_helper().clear();
        }
        Iterator it3 = this.d.iterator();
        while (it3.hasNext()) {
            ((DataCaptureComponentDeserializer) it3.next()).get_helper().clear();
        }
        return dataCaptureContextDeserializerResult;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DataCaptureContextDeserializer(com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer r12, com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializer r13, java.util.List<? extends com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer> r14, java.util.List<? extends com.scandit.datacapture.core.component.serialization.DataCaptureComponentDeserializer> r15) {
        /*
            r11 = this;
            java.lang.String r0 = "frameSourceDeserializer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "viewDeserializer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "modeDeserializers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "componentDeserializers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer r0 = r12.getA()
            com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer r1 = r13.getA()
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r14, r3)
            r2.<init>(r4)
            java.util.Iterator r4 = r14.iterator()
        L2b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L3f
            java.lang.Object r5 = r4.next()
            com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer r5 = (com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer) r5
            com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer r5 = r5.getC()
            r2.add(r5)
            goto L2b
        L3f:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r15, r3)
            r2.<init>(r3)
            java.util.Iterator r3 = r15.iterator()
        L51:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L65
            java.lang.Object r5 = r3.next()
            com.scandit.datacapture.core.component.serialization.DataCaptureComponentDeserializer r5 = (com.scandit.datacapture.core.component.serialization.DataCaptureComponentDeserializer) r5
            com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureComponentDeserializer r5 = r5._componentDeserializerImpl()
            r2.add(r5)
            goto L51
        L65:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer r10 = com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer.create(r0, r1, r4, r3)
            java.lang.String r0 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)
            r5 = r11
            r6 = r12
            r7 = r13
            r8 = r14
            r9 = r15
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializer.<init>(com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer, com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializer, java.util.List, java.util.List):void");
    }
}
