package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameterKey;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.VideoResolutionDeserializer;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

/* loaded from: classes2.dex */
public final class j implements f {
    public final Function1 a;
    public final Function1 b;
    public final com.scandit.datacapture.core.internal.module.source.camera.b c;
    public final a d;
    public final Function1 e;
    public volatile m f;
    public final AtomicBoolean g;
    public final AtomicBoolean h;
    public final Timer i;
    public g j;
    public final z k;

    public j(com.scandit.datacapture.core.source.b applyResolutionBlock, com.scandit.datacapture.core.source.c applySettingsBlock, com.scandit.datacapture.core.internal.module.source.camera.c resolutionStore, b fpsMeasurer, e logger) {
        Intrinsics.checkNotNullParameter(applyResolutionBlock, "applyResolutionBlock");
        Intrinsics.checkNotNullParameter(applySettingsBlock, "applySettingsBlock");
        Intrinsics.checkNotNullParameter(resolutionStore, "resolutionStore");
        Intrinsics.checkNotNullParameter(fpsMeasurer, "fpsMeasurer");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.a = applyResolutionBlock;
        this.b = applySettingsBlock;
        this.c = resolutionStore;
        this.d = fpsMeasurer;
        this.e = logger;
        this.g = new AtomicBoolean(true);
        this.h = new AtomicBoolean(true);
        this.i = new Timer("dynamic-resolution-timer", true);
        h start = new h(this);
        i stop = new i(this);
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(stop, "stop");
        this.k = new z(start, stop);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void a(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "context");
        z zVar = this.k;
        synchronized (zVar) {
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            s sVar = s.a;
            if (!Intrinsics.areEqual(sVar, zVar.d)) {
                zVar.d = sVar;
                if (!zVar.c) {
                    zVar.a();
                }
            }
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void b(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "context");
        z zVar = this.k;
        synchronized (zVar) {
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            r rVar = new r(dataCaptureContext);
            if (!Intrinsics.areEqual(rVar, zVar.d)) {
                zVar.d = rVar;
                if (!zVar.c) {
                    zVar.a();
                }
            }
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onFrameOutput(FrameSource frameSource, FrameData frame) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(frame, "frame");
        float floatForKeyOr = frame.getA().collectCameraCaptureParametersForAndroid().getFloatForKeyOr(NativeCameraCaptureParameterKey.EXPOSURE_TIME, Float.MAX_VALUE);
        Long nativeStartOfExposureTimestampForAndroid = frame.getA().getNativeStartOfExposureTimestampForAndroid();
        long longValue = nativeStartOfExposureTimestampForAndroid == null ? Long.MAX_VALUE : nativeStartOfExposureTimestampForAndroid.longValue();
        if (!(floatForKeyOr == Float.MAX_VALUE)) {
            this.g.set(true);
        } else if (this.g.compareAndSet(true, false)) {
            this.e.invoke("Exposure not available.");
        }
        if (longValue != Long.MAX_VALUE) {
            this.h.set(true);
        } else if (this.h.compareAndSet(true, false)) {
            this.e.invoke("Timestamp not available.");
        }
        if (floatForKeyOr == Float.MAX_VALUE || longValue == Long.MAX_VALUE) {
            return;
        }
        long j = longValue / DurationKt.NANOS_IN_MILLIS;
        float f = floatForKeyOr * 1000.0f;
        b bVar = (b) this.d;
        if (bVar.b.get()) {
            long j2 = bVar.c;
            if (j2 > 0) {
                long j3 = j - j2;
                if (j3 > 0) {
                    d dVar = (d) bVar.a;
                    synchronized (dVar.a) {
                        dVar.b += j3;
                        dVar.c++;
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            ((d) bVar.a).a(f);
            bVar.c = j;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStarted(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStopped(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onStateChanged(FrameSource frameSource, FrameSourceState newState) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(newState, "newState");
        if (frameSource.getDesiredState() == FrameSourceState.OFF || frameSource.getDesiredState() == FrameSourceState.STANDBY) {
            this.k.a(frameSource);
            return;
        }
        if (frameSource.getDesiredState() == FrameSourceState.ON) {
            z zVar = this.k;
            synchronized (zVar) {
                Intrinsics.checkNotNullParameter(frameSource, "frameSource");
                o oVar = new o(frameSource);
                if (!Intrinsics.areEqual(oVar, zVar.f)) {
                    zVar.f = oVar;
                    if (!zVar.c) {
                        zVar.a();
                    }
                }
            }
        }
    }

    public final void a(String currentResolution) {
        this.a.invoke(currentResolution);
        z zVar = this.k;
        synchronized (zVar) {
            Intrinsics.checkNotNullParameter(currentResolution, "newResolution");
            y yVar = zVar.e;
            if (yVar instanceof w) {
                m mVar = ((w) yVar).a;
                CameraSettings initialCameraSettings = mVar.a;
                int i = mVar.b;
                int i2 = mVar.c;
                Intrinsics.checkNotNullParameter(initialCameraSettings, "initialCameraSettings");
                Intrinsics.checkNotNullParameter(currentResolution, "currentResolution");
                m settings = new m(initialCameraSettings, i, i2, currentResolution);
                Intrinsics.checkNotNullParameter(settings, "settings");
                yVar = new w(settings);
            }
            zVar.e = yVar;
            if (!zVar.c) {
                zVar.a();
            }
        }
    }

    public final void b(String resolution) {
        CameraSettings settings;
        l lVar = l.a;
        l.b("Chosen final resolution: " + resolution);
        ((com.scandit.datacapture.core.internal.module.source.camera.c) this.c).a(resolution);
        this.k.c();
        m mVar = this.f;
        if (mVar == null || (settings = mVar.a) == null) {
            return;
        }
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        Boolean bool = Boolean.FALSE;
        settings.setProperty("quadHd", bool);
        settings.setProperty("arbitraryVideoResolution", null);
        settings.setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", bool);
        settings.setProperty("overwriteWithHighestResolution", bool);
        if (Intrinsics.areEqual(resolution, "quadHd")) {
            settings.setProperty("quadHd", Boolean.TRUE);
        } else {
            try {
                settings.setPreferredResolution(VideoResolutionDeserializer.fromJson(resolution));
            } catch (Throwable unused) {
                l lVar2 = l.a;
                l.a(resolution.concat(" is not a valid resolution"));
            }
        }
        this.b.invoke(settings);
    }
}
