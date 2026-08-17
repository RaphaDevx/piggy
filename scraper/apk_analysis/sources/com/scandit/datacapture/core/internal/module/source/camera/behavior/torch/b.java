package com.scandit.datacapture.core.internal.module.source.camera.behavior.torch;

import com.scandit.datacapture.core.source.TorchListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements a {
    public final CopyOnWriteArraySet a = new CopyOnWriteArraySet();
    public final CopyOnWriteArraySet b = new CopyOnWriteArraySet();

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void a(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        CopyOnWriteArraySet copyOnWriteArraySet = this.b;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArraySet) {
            if (Intrinsics.areEqual(((WeakReference) obj).get(), listener)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.b.remove((WeakReference) it.next());
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void b(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        CopyOnWriteArraySet copyOnWriteArraySet = this.b;
        if (!(copyOnWriteArraySet instanceof Collection) || !copyOnWriteArraySet.isEmpty()) {
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((WeakReference) it.next()).get(), listener)) {
                    return;
                }
            }
        }
        this.b.add(new WeakReference(listener));
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void c(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a.add(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void d(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a.remove(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a
    public final Collection c() {
        return this.b;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a
    public final Collection a() {
        return this.a;
    }
}
