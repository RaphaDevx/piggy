package com.scandit.datacapture.core.internal.module.source.camera.behavior;

import com.scandit.datacapture.core.source.TorchListener;
import com.scandit.datacapture.core.source.TorchState;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ c a;
    public final /* synthetic */ TorchState b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, TorchState torchState, Function0 function0) {
        super(1);
        this.a = cVar;
        this.b = torchState;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            c cVar = this.a;
            cVar.d = this.b;
            Iterator it = cVar.b.a().iterator();
            while (it.hasNext()) {
                ((TorchListener) it.next()).onTorchStateChanged(this.b);
            }
            Iterator it2 = this.a.b.c().iterator();
            while (it2.hasNext()) {
                TorchListener torchListener = (TorchListener) ((WeakReference) it2.next()).get();
                if (torchListener != null) {
                    torchListener.onTorchStateChanged(this.b);
                }
            }
        }
        this.c.invoke();
        return Unit.INSTANCE;
    }
}
