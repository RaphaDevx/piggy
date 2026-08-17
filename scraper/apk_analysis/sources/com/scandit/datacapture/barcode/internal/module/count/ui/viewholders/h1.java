package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.SharedPreferences;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h1 implements b1 {
    public final SharedPreferences a;
    public com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b b;
    public final z1 c;
    public boolean d;
    public BarcodeCountToolbarSettings e;
    public BarcodeCountToolbarView f;

    public h1(FrameLayout container, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.a = sharedPreferences;
        this.c = new z1(container);
        this.d = BarcodeCountViewDefaults.INSTANCE.getShouldShowToolbar();
        this.e = new BarcodeCountToolbarSettings();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        BarcodeCountToolbarView barcodeCountToolbarView;
        BarcodeCountToolbarView barcodeCountToolbarView2 = this.f;
        if (barcodeCountToolbarView2 != null) {
            barcodeCountToolbarView2.setVisibility(this.d ? 0 : 4);
        }
        if (!this.d || (barcodeCountToolbarView = this.f) == null) {
            return;
        }
        barcodeCountToolbarView.a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.c.b(this.f, new f1(this));
        BarcodeCountToolbarView barcodeCountToolbarView = this.f;
        if (barcodeCountToolbarView == null) {
            return;
        }
        barcodeCountToolbarView.setVisibility(4);
    }

    public static String a(a1 a1Var) {
        int ordinal = a1Var.ordinal();
        if (ordinal == 0) {
            return "barcode_count_toolbar_audio_feedback_key";
        }
        if (ordinal == 1) {
            return "barcode_count_toolbar_haptic_feedback_key";
        }
        if (ordinal == 2) {
            return "barcode_count_toolbar_strap_mode_key";
        }
        if (ordinal == 3) {
            return "barcode_count_toolbar_color_scheme_key";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void a(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.a.edit().putBoolean(key, z).apply();
        this.c.a(new g1(this));
    }
}
