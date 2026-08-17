package com.scandit.datacapture.frameworks.core.context;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.capture.DataCaptureContextSettings;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import com.scandit.datacapture.frameworks.core.context.data.DataCaptureContextData;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.listeners.FrameworksFrameSourceDeserializer;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultFrameworksCaptureContext.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0016J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/frameworks/core/context/DefaultFrameworksCaptureContext;", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "()V", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "contextLock", "Ljava/util/concurrent/locks/ReentrantLock;", "dataCaptureContextListener", "Lcom/scandit/datacapture/core/capture/DataCaptureContextListener;", "frameSourceDeserializer", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;", "initialized", "", "addMode", "", "mode", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "configure", "frameSourceDeserializerListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceDeserializer;", "initialize", "json", "", "androidContext", "Landroid/content/Context;", "release", "removeAllModes", "removeMode", "update", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultFrameworksCaptureContext implements FrameworksCaptureContext {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultFrameworksCaptureContext instance = new DefaultFrameworksCaptureContext();
    private final ReentrantLock contextLock = new ReentrantLock();
    private DataCaptureContextListener dataCaptureContextListener;
    private FrameSourceDeserializer frameSourceDeserializer;
    private boolean initialized;

    public final void configure(FrameworksFrameSourceDeserializer frameSourceDeserializerListener, DataCaptureContextListener dataCaptureContextListener) {
        Intrinsics.checkNotNullParameter(frameSourceDeserializerListener, "frameSourceDeserializerListener");
        Intrinsics.checkNotNullParameter(dataCaptureContextListener, "dataCaptureContextListener");
        this.dataCaptureContextListener = dataCaptureContextListener;
        FrameSourceDeserializer frameSourceDeserializer = new FrameSourceDeserializer(new ArrayList());
        frameSourceDeserializer.setListener(frameSourceDeserializerListener);
        this.frameSourceDeserializer = frameSourceDeserializer;
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public DataCaptureContext getContext() {
        if (this.initialized) {
            return DataCaptureContext.INSTANCE.getSharedInstance();
        }
        return null;
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public DataCaptureContext initialize(String json, Context androidContext) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(androidContext, "androidContext");
        this.contextLock.lock();
        try {
            DataCaptureContextData from = DataCaptureContextData.INSTANCE.from(json);
            DataCaptureContextSettings dataCaptureContextSettings = new DataCaptureContextSettings();
            if (from.getSettings() != null) {
                for (Map.Entry<String, Object> entry : from.getSettings().entrySet()) {
                    dataCaptureContextSettings.setProperty(entry.getKey(), entry.getValue());
                }
            }
            DataCaptureContext initialize = DataCaptureContext.INSTANCE.initialize(from.getLicenseKey(), from.getFramework(), from.getFrameworkVersion(), from.getDeviceName(), from.getExternalId(), dataCaptureContextSettings);
            DataCaptureContextListener dataCaptureContextListener = null;
            if (from.getFrameSource() != null) {
                FrameSourceDeserializer frameSourceDeserializer = this.frameSourceDeserializer;
                if (frameSourceDeserializer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frameSourceDeserializer");
                    frameSourceDeserializer = null;
                }
                DataCaptureContext.setFrameSource$default(initialize, frameSourceDeserializer.frameSourceFromJson(from.getFrameSource()), null, 2, null);
            }
            DataCaptureContextListener dataCaptureContextListener2 = this.dataCaptureContextListener;
            if (dataCaptureContextListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataCaptureContextListener");
            } else {
                dataCaptureContextListener = dataCaptureContextListener2;
            }
            initialize.addListener(dataCaptureContextListener);
            this.initialized = true;
            return initialize;
        } finally {
            this.contextLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public void update(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (!this.initialized) {
            throw new DataCaptureContextNotInitialized();
        }
        DataCaptureContextData from = DataCaptureContextData.INSTANCE.from(json);
        if (from.getSettings() != null) {
            DataCaptureContextSettings dataCaptureContextSettings = new DataCaptureContextSettings();
            for (Map.Entry<String, Object> entry : from.getSettings().entrySet()) {
                dataCaptureContextSettings.setProperty(entry.getKey(), entry.getValue());
            }
            DataCaptureContext context = getContext();
            if (context != null) {
                context.applySettings(dataCaptureContextSettings);
            }
        }
        if (from.getFrameSource() != null) {
            FrameSourceDeserializer frameSourceDeserializer = this.frameSourceDeserializer;
            if (frameSourceDeserializer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameSourceDeserializer");
                frameSourceDeserializer = null;
            }
            FrameSource frameSourceFromJson = frameSourceDeserializer.frameSourceFromJson(from.getFrameSource());
            DataCaptureContext context2 = getContext();
            if (context2 != null) {
                DataCaptureContext.setFrameSource$default(context2, frameSourceFromJson, null, 2, null);
            }
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public void release() {
        DataCaptureContext context = getContext();
        if (context != null) {
            DataCaptureContextListener dataCaptureContextListener = this.dataCaptureContextListener;
            if (dataCaptureContextListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataCaptureContextListener");
                dataCaptureContextListener = null;
            }
            context.removeListener(dataCaptureContextListener);
            DataCaptureContext.setFrameSource$default(context, null, null, 2, null);
            context.release();
        }
        this.initialized = false;
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public void addMode(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (!this.initialized) {
            throw new DataCaptureContextNotInitialized();
        }
        DataCaptureContext context = getContext();
        if (context != null) {
            context.addMode(mode);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public void removeMode(DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        DataCaptureContext context = getContext();
        if (context != null) {
            context.removeMode(mode);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext
    public void removeAllModes() {
        DataCaptureContext context = getContext();
        if (context != null) {
            context.removeAllModes();
        }
    }

    /* compiled from: DefaultFrameworksCaptureContext.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/context/DefaultFrameworksCaptureContext$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/context/DefaultFrameworksCaptureContext;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DefaultFrameworksCaptureContext getInstance() {
            return DefaultFrameworksCaptureContext.instance;
        }
    }
}
