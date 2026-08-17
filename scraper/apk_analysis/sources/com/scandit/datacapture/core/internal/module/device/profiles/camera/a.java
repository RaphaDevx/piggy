package com.scandit.datacapture.core.internal.module.device.profiles.camera;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final String a;
    public final String b;
    public final String c;

    public a(String focusModeContinuousPicture, String focusModeContinuousVideo, String focusModeAuto) {
        Intrinsics.checkNotNullParameter(focusModeContinuousPicture, "focusModeContinuousPicture");
        Intrinsics.checkNotNullParameter(focusModeContinuousVideo, "focusModeContinuousVideo");
        Intrinsics.checkNotNullParameter(focusModeAuto, "focusModeAuto");
        this.a = focusModeContinuousPicture;
        this.b = focusModeContinuousVideo;
        this.c = focusModeAuto;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "CameraParametersKeys(focusModeContinuousPicture=" + this.a + ", focusModeContinuousVideo=" + this.b + ", focusModeAuto=" + this.c + ')';
    }
}
