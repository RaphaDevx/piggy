package com.scandit.datacapture.core.internal.module.source.camera;

import android.content.Context;
import android.content.SharedPreferences;
import com.scandit.datacapture.core.internal.module.utils.i;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements b {
    public final SharedPreferences a;

    public c(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context.getSharedPreferences("com.scandit.datacapture.camera_store", 0);
    }

    public final void a(String resolution) {
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        this.a.edit().putString("selected_dynamic_resolution", resolution).apply();
    }

    public final String b() {
        try {
            return this.a.getString("selected_dynamic_resolution", null);
        } catch (ClassCastException e) {
            i.a("The stored dynamic resolution is not valid, clearing: " + e.getMessage());
            this.a.edit().remove("selected_dynamic_resolution").apply();
            return null;
        }
    }

    public final void a() {
        this.a.edit().clear().apply();
    }
}
