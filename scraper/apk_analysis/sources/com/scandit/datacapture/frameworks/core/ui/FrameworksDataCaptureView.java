package com.scandit.datacapture.frameworks.core.ui;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.PointDeserializer;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.QuadrilateralDeserializer;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializer;
import com.scandit.datacapture.frameworks.core.data.DataCaptureViewCreationData;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.listeners.FrameworksDataCaptureViewListener;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksDataCaptureView.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 <2\u00020\u0001:\u0001<B+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0011J\u0018\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020%H\u0016J\u001c\u0010-\u001a\u0004\u0018\u0001H.\"\n\b\u0000\u0010.\u0018\u0001*\u00020\u0011H\u0086\b¢\u0006\u0002\u0010/J\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u0002052\u0006\u00102\u001a\u000203J\u0006\u00106\u001a\u00020%J\u0006\u00107\u001a\u00020%J\u000e\u00108\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0011J\u0006\u00109\u001a\u00020%J\u000e\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020+R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "viewDeserializer", "Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "lifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/core/ui/serialization/DataCaptureViewDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;)V", "_parentId", "", "Ljava/lang/Integer;", "_viewId", "overlays", "", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "getOverlays", "()Ljava/util/List;", "parentId", "getParentId", "()Ljava/lang/Integer;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "getView", "()Lcom/scandit/datacapture/core/ui/DataCaptureView;", "setView", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;)V", "viewId", "getViewId", "()I", "viewListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureViewListener;", "viewOverlays", "", "addOverlay", "", "overlay", "deserializeView", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/core/data/DataCaptureViewCreationData;", "dispose", "findFirstOverlayOfType", ExifInterface.GPS_DIRECTION_TRUE, "()Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "mapFramePointToView", "Lcom/scandit/datacapture/core/common/geometry/Point;", "json", "", "mapFrameQuadrilateralToView", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "registerDataCaptureViewListener", "removeAllOverlays", "removeOverlay", "unregisterDataCaptureViewListener", "updateView", "updateData", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksDataCaptureView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Integer _parentId;
    private int _viewId;
    private final Emitter emitter;
    private final DefaultDeserializationLifecycleObserver lifecycleObserver;
    private final MainThread mainThread;
    public DataCaptureView view;
    private final DataCaptureViewDeserializer viewDeserializer;
    private FrameworksDataCaptureViewListener viewListener;
    private final List<DataCaptureOverlay> viewOverlays;

    private FrameworksDataCaptureView(Emitter emitter, DataCaptureViewDeserializer dataCaptureViewDeserializer, MainThread mainThread, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver) {
        this.emitter = emitter;
        this.viewDeserializer = dataCaptureViewDeserializer;
        this.mainThread = mainThread;
        this.lifecycleObserver = defaultDeserializationLifecycleObserver;
        this.viewOverlays = new ArrayList();
    }

    /* synthetic */ FrameworksDataCaptureView(Emitter emitter, DataCaptureViewDeserializer dataCaptureViewDeserializer, DefaultMainThread defaultMainThread, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, dataCaptureViewDeserializer, (i & 4) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread, (i & 8) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId, reason: from getter */
    public Integer get_parentId() {
        return this._parentId;
    }

    public final DataCaptureView getView() {
        DataCaptureView dataCaptureView = this.view;
        if (dataCaptureView != null) {
            return dataCaptureView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    public final void setView(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "<set-?>");
        this.view = dataCaptureView;
    }

    public final List<DataCaptureOverlay> getOverlays() {
        return CollectionsKt.toList(this.viewOverlays);
    }

    public final void addOverlay(final DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.viewOverlays.add(overlay);
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView$addOverlay$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                FrameworksDataCaptureView.this.getView().addOverlay(overlay);
            }
        });
    }

    public final void removeOverlay(final DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.viewOverlays.remove(overlay);
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView$removeOverlay$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver;
                FrameworksDataCaptureView.this.getView().removeOverlay(overlay);
                defaultDeserializationLifecycleObserver = FrameworksDataCaptureView.this.lifecycleObserver;
                defaultDeserializationLifecycleObserver.dispatchOverlayRemovedFromView(overlay);
            }
        });
    }

    public final void removeAllOverlays() {
        Iterator<DataCaptureOverlay> it = getOverlays().iterator();
        while (it.hasNext()) {
            removeOverlay(it.next());
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        removeAllOverlays();
        FrameworksDataCaptureViewListener frameworksDataCaptureViewListener = this.viewListener;
        if (frameworksDataCaptureViewListener != null) {
            getView().removeListener(frameworksDataCaptureViewListener);
        }
        this.viewListener = null;
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView$dispose$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewExtensionsKt.removeFromSuperview(FrameworksDataCaptureView.this.getView());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(DataCaptureContext dataCaptureContext, DataCaptureViewCreationData creationData) {
        this._viewId = creationData.getViewId();
        this._parentId = creationData.getParentId();
        setView(this.viewDeserializer.viewFromJson(dataCaptureContext, creationData.getViewJson()));
        getView().setTag(Integer.valueOf(get_viewId()));
        FrameworksDataCaptureViewListener frameworksDataCaptureViewListener = new FrameworksDataCaptureViewListener(this.emitter, get_viewId());
        getView().addListener(frameworksDataCaptureViewListener);
        frameworksDataCaptureViewListener.enable();
        this.viewListener = frameworksDataCaptureViewListener;
    }

    public final void updateView(final DataCaptureViewCreationData updateData) {
        Intrinsics.checkNotNullParameter(updateData, "updateData");
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView$updateView$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DataCaptureViewDeserializer dataCaptureViewDeserializer;
                dataCaptureViewDeserializer = FrameworksDataCaptureView.this.viewDeserializer;
                dataCaptureViewDeserializer.updateViewFromJson(FrameworksDataCaptureView.this.getView(), updateData.getViewJson());
            }
        });
    }

    public final Point mapFramePointToView(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return getView().mapFramePointToView(PointDeserializer.fromJson(json));
    }

    public final Quadrilateral mapFrameQuadrilateralToView(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return getView().mapFrameQuadrilateralToView(QuadrilateralDeserializer.fromJson(json));
    }

    public final void registerDataCaptureViewListener() {
        FrameworksDataCaptureViewListener frameworksDataCaptureViewListener = this.viewListener;
        if (frameworksDataCaptureViewListener != null) {
            frameworksDataCaptureViewListener.enable();
        }
    }

    public final void unregisterDataCaptureViewListener() {
        FrameworksDataCaptureViewListener frameworksDataCaptureViewListener = this.viewListener;
        if (frameworksDataCaptureViewListener != null) {
            frameworksDataCaptureViewListener.disable();
        }
    }

    public final /* synthetic */ <T extends DataCaptureOverlay> T findFirstOverlayOfType() {
        Object obj;
        Iterator<T> it = getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (((DataCaptureOverlay) obj) instanceof DataCaptureOverlay) {
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) obj;
    }

    /* compiled from: FrameworksDataCaptureView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "context", "Landroid/content/Context;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/core/data/DataCaptureViewCreationData;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksDataCaptureView create(Emitter emitter, Context context, DataCaptureContext dataCaptureContext, DataCaptureViewCreationData creationData) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(creationData, "creationData");
            FrameworksDataCaptureView frameworksDataCaptureView = new FrameworksDataCaptureView(emitter, new DataCaptureViewDeserializer(context, new ArrayList()), null, null, 12, null);
            frameworksDataCaptureView.deserializeView(dataCaptureContext, creationData);
            return frameworksDataCaptureView;
        }
    }
}
