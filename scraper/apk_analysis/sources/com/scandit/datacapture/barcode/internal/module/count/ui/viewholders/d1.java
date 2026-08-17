package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public final class d1 implements com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b {
    public final /* synthetic */ h1 a;

    public d1(h1 h1Var) {
        this.a = h1Var;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void a(boolean z) {
        SharedPreferences.Editor putBoolean;
        SharedPreferences.Editor edit = this.a.a.edit();
        if (edit != null && (putBoolean = edit.putBoolean("barcode_count_toolbar_audio_feedback_key", z)) != null) {
            putBoolean.apply();
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b bVar = this.a.b;
        if (bVar != null) {
            bVar.a(z);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void b(boolean z) {
        SharedPreferences.Editor putBoolean;
        SharedPreferences.Editor edit = this.a.a.edit();
        if (edit != null && (putBoolean = edit.putBoolean("barcode_count_toolbar_color_scheme_key", z)) != null) {
            putBoolean.apply();
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b bVar = this.a.b;
        if (bVar != null) {
            bVar.b(z);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void c(boolean z) {
        SharedPreferences.Editor putBoolean;
        SharedPreferences.Editor edit = this.a.a.edit();
        if (edit != null && (putBoolean = edit.putBoolean("barcode_count_toolbar_strap_mode_key", z)) != null) {
            putBoolean.apply();
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b bVar = this.a.b;
        if (bVar != null) {
            bVar.c(z);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void d(boolean z) {
        SharedPreferences.Editor putBoolean;
        SharedPreferences.Editor edit = this.a.a.edit();
        if (edit != null && (putBoolean = edit.putBoolean("barcode_count_toolbar_haptic_feedback_key", z)) != null) {
            putBoolean.apply();
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b bVar = this.a.b;
        if (bVar != null) {
            bVar.d(z);
        }
    }
}
