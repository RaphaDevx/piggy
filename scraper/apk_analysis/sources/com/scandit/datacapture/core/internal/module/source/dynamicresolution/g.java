package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.source.VideoResolution;
import com.scandit.datacapture.core.source.VideoResolutionUtilsKt;
import java.util.TimerTask;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class g extends TimerTask {
    public final /* synthetic */ j a;
    public final /* synthetic */ m b;

    public g(j jVar, m mVar) {
        this.a = jVar;
        this.b = mVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        float f;
        j jVar = this.a;
        m mVar = this.b;
        ((b) jVar.d).b.set(false);
        d dVar = (d) ((b) jVar.d).a;
        synchronized (dVar.a) {
            long j = dVar.c;
            if (j != 0) {
                long j2 = dVar.b;
                if (j2 != 0) {
                    f = 1000 / (j2 / j);
                }
            }
            f = 0.0f;
        }
        float a = ((d) ((b) jVar.d).a).a();
        float coerceAtLeast = 1000 / (RangesKt.coerceAtLeast(((d) ((b) jVar.d).a).a(), 0.0f) + 5);
        float min = Math.min(coerceAtLeast, mVar.b);
        ((b) jVar.d).a();
        ((b) jVar.d).b.set(true);
        String currentResolution = mVar.d;
        if (f > min) {
            l lVar = l.a;
            l.a("Good performance in " + currentResolution + ": \n\tCamera average fps: " + f + "\n\tAverage exposure: " + a + "ms\n\tFps allowed by exposure: " + coerceAtLeast);
            jVar.b(currentResolution);
            return;
        }
        VideoResolution videoResolution = VideoResolution.FULL_HD;
        if (Intrinsics.areEqual(currentResolution, VideoResolutionUtilsKt.toJson(videoResolution))) {
            String json = VideoResolutionUtilsKt.toJson(VideoResolution.HD);
            l lVar2 = l.a;
            l.a("Bad performance in " + currentResolution + ", using " + json + ":\n\tCamera average fps: " + f + "\n\tAverage exposure: " + a + "ms\n\tFps allowed by exposure: " + coerceAtLeast);
            jVar.b(json);
            return;
        }
        VideoResolution videoResolution2 = VideoResolution.HD;
        if (Intrinsics.areEqual(currentResolution, VideoResolutionUtilsKt.toJson(videoResolution2))) {
            l lVar3 = l.a;
            l.a("Keeping " + currentResolution + " as it's the lowest: \n\tCamera average fps: " + f + "\n\tAverage exposure: " + a + "ms\n\tFps allowed by exposure: " + coerceAtLeast);
            jVar.b(currentResolution);
            return;
        }
        Intrinsics.checkNotNullParameter(currentResolution, "currentResolution");
        String json2 = Intrinsics.areEqual(currentResolution, VideoResolutionUtilsKt.toJson(VideoResolution.UHD4K)) ? "quadHd" : Intrinsics.areEqual(currentResolution, "quadHd") ? VideoResolutionUtilsKt.toJson(videoResolution) : VideoResolutionUtilsKt.toJson(videoResolution2);
        l lVar4 = l.a;
        l.a("Bad performance, lowering from " + currentResolution + " to " + json2 + ": \n\tCamera average fps: " + f + "\n\tAverage exposure: " + a + "ms\n\tFps allowed by exposure: " + coerceAtLeast);
        jVar.a(json2);
    }
}
