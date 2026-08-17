package com.scandit.datacapture.core.internal.module.device.profiles.camera;

import android.os.Build;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.g;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.h;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.i;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.j;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.k;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.l;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.A;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.B;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0504a;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0505b;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0506c;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0507d;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0508e;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0509f;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0510g;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0511h;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0512i;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0513j;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0514k;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0515l;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0516m;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0517n;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0518o;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0519p;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0520q;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0521s;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0522t;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0523u;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0524v;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0525w;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0526x;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0527y;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.C0528z;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.D;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.E;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.F;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.G;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.H;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.I;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.J;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.K;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.L;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.M;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.N;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.O;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.P;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.Q;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.S;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.T;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.U;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.V;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.W;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.X;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.Y;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.Z;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.a0;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.b0;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.r;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public static final c a = new c();

    public c() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Object obj;
        Lazy lazy = d.a;
        String modelName = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(modelName, "MODEL");
        Intrinsics.checkNotNullParameter(modelName, "model");
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lowerCase = modelName.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String obj2 = StringsKt.trim((CharSequence) StringsKt.replace$default(lowerCase, "samsung-", "", false, 4, (Object) null)).toString();
        Iterator it = SequencesKt.sequenceOf(com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.alcatel.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.aquos.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.aquos.b.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.aquos.c.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.blackview.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.c.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.d.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.e.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.cat.f.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.custom.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.crosscall.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.crosscall.b.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.evotor.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.b.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.c.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.d.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.e.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google.f.d, g.d, h.d, i.d, j.d, l.d, k.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.handheld.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.honeywell.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.honeywell.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.htc.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.d.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.e.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.f.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.huawei.c.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.integra.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera.d.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera.c.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera.e.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.lg.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.motorola.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.nokia.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.nokia.b.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.nokia.c.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.nokia.d.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.panasonic.a.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.custom.b.d, C0511h.d, C0512i.d, C0514k.e, C0504a.d, C0505b.d, C0506c.d, C0507d.d, C0508e.d, C0509f.d, C0510g.d, C0513j.d, C0517n.c, C0515l.d, C0516m.d, C0518o.d, C0519p.d, C0520q.c, r.d, C0521s.c, C0522t.d, C0523u.d, C0524v.d, C0525w.d, C0526x.d, C0528z.d, A.e, C0527y.c, D.c, H.d, I.d, J.d, K.d, L.e, M.e, N.c, O.e, P.e, Q.e, S.c, B.e, C.e, E.c, F.c, G.c, T.d, U.c, V.d, W.c, Y.c, X.d, Z.c, a0.c, b0.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sonim.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sonim.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sonim.c.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sony.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.custom.c.c, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.xiomi.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.xiomi.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.b.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.c.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.d.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.e.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.f.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.g.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.h.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.i.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.j.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra.k.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.oneplus.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.scandit.a.d, com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.scandit.b.d, new com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b()).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((CameraProfile) obj).a(obj2)) {
                break;
            }
        }
        CameraProfile cameraProfile = (CameraProfile) obj;
        return cameraProfile == null ? new com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a() : cameraProfile;
    }
}
