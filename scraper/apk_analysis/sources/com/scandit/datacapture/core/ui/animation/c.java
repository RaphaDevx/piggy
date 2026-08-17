package com.scandit.datacapture.core.ui.animation;

import android.os.SystemClock;
import android.view.Choreographer;

/* loaded from: classes2.dex */
public final class c extends AnimationHandler$AnimationFrameCallbackProvider {
    public final Choreographer b;
    public final Choreographer.FrameCallback c;

    public c(a aVar) {
        super(aVar);
        this.b = Choreographer.getInstance();
        this.c = new Choreographer.FrameCallback() { // from class: com.scandit.datacapture.core.ui.animation.c$$ExternalSyntheticLambda0
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                c.this.a(j);
            }
        };
    }

    public final void a(long j) {
        a aVar = this.a;
        aVar.a.e = SystemClock.uptimeMillis();
        d dVar = aVar.a;
        long j2 = dVar.e;
        long uptimeMillis = SystemClock.uptimeMillis();
        for (int i = 0; i < dVar.b.size(); i++) {
            b bVar = (b) dVar.b.get(i);
            if (bVar != null) {
                Long l = (Long) dVar.a.get(bVar);
                if (l != null) {
                    if (l.longValue() < uptimeMillis) {
                        dVar.a.remove(bVar);
                    }
                }
                bVar.doAnimationFrame(j2);
            }
        }
        if (dVar.f) {
            for (int size = dVar.b.size() - 1; size >= 0; size--) {
                if (dVar.b.get(size) == null) {
                    dVar.b.remove(size);
                }
            }
            dVar.f = false;
        }
        if (aVar.a.b.size() > 0) {
            d dVar2 = aVar.a;
            if (dVar2.d == null) {
                dVar2.d = new c(dVar2.c);
            }
            c cVar = dVar2.d;
            cVar.b.postFrameCallback(cVar.c);
        }
    }

    public final void a() {
        this.b.postFrameCallback(this.c);
    }
}
