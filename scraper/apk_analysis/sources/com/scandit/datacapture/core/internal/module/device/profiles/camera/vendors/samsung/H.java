package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class H extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final H d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        H h = new H();
        d = h;
        e = "sgh-t999|sch-i535|sph-l710t|sgh-i748|gravity|sch-l710|sph-l710|sc-06d|gravityquad|shv-e210s|sch-r530m|gt-i9300t|shv-e210k|gt-i9300|sch-s968c|gt-i9305|sgh-t999n|shv-e210l|sch-i939|sgh-t999v|sch-r530c|sc-03e|sch-s960l|sgh-t999l|sch-r530u|sgh-i747m|gt-i9308|gt-i9305t|sch-i939d|sch-r530x|sgh-i747|shw-m440s|sch-i535pp|gt-i9305n";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(h.a, false, -1.0f, false, false, 0, 119);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
