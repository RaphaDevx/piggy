package com.scandit.datacapture.barcode.internal.module.count.ui.toolbar;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarDefaults;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.d1;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class BarcodeCountToolbarView extends LinearLayout {
    private static final int p = PixelExtensionsKt.pxFromDp$default(30, (Context) null, 1, (Object) null);
    private static final int q = PixelExtensionsKt.pxFromDp$default(16, (Context) null, 1, (Object) null);
    private static final int r = PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null);
    private static final int s = PixelExtensionsKt.pxFromDp$default(36, (Context) null, 1, (Object) null);
    private b a;
    private final View b;
    private final View c;
    private final LinearLayout d;
    private final a e;
    private final a f;
    private final a g;
    private final a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private final BarcodeCountToolbarDefaults o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeCountToolbarView(Context context, c initialState) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        this.j = initialState.a;
        this.k = initialState.b;
        this.l = initialState.c;
        this.m = initialState.d;
        this.o = new BarcodeCountToolbarDefaults();
        View inflate = View.inflate(context, R.layout.sc_barcode_count_toolbar, this);
        View findViewById = inflate.findViewById(R.id.expand_button);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.b = findViewById;
        a();
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeCountToolbarView.a(BarcodeCountToolbarView.this, view);
            }
        });
        View findViewById2 = inflate.findViewById(R.id.collapse_button);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.c = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeCountToolbarView.b(BarcodeCountToolbarView.this, view);
            }
        });
        View findViewById3 = inflate.findViewById(R.id.button_container);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.d = (LinearLayout) findViewById3;
        int i = R.drawable.sc_ic_audio_feedback_selector;
        String string = context.getString(R.string.sc_barcodeCount_toolbar_audio_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = context.getString(R.string.sc_barcodeCount_toolbar_audio_on);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.e = a(context, i, string, string2, this.j, new d(this));
        int i2 = R.drawable.sc_ic_haptic_feedback_selector;
        String string3 = context.getString(R.string.sc_barcodeCount_toolbar_haptic_off);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = context.getString(R.string.sc_barcodeCount_toolbar_haptic_on);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        this.f = a(context, i2, string3, string4, this.k, new e(this));
        int i3 = R.drawable.sc_ic_strap_mode;
        String string5 = context.getString(R.string.sc_barcodeCount_toolbar_strap_mode);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        this.g = a(context, i3, string5, string5, this.l, new f(this));
        int i4 = R.drawable.sc_ic_color_filter;
        String string6 = context.getString(R.string.sc_barcodeCount_toolbar_color_filter);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        this.h = a(context, i4, string6, string6, this.m, new g(this));
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f(BarcodeCountToolbarView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.setVisibility(this$0.i ? 8 : 0);
        this$0.c.setVisibility(this$0.i ? 0 : 8);
        this$0.d.setVisibility(this$0.i ? 0 : 8);
        a aVar = this$0.e;
        aVar.e = this$0.j;
        aVar.a();
        a aVar2 = this$0.f;
        aVar2.e = this$0.k;
        aVar2.a();
        a aVar3 = this$0.g;
        aVar3.e = this$0.l;
        aVar3.a();
        a aVar4 = this$0.h;
        aVar4.e = this$0.m;
        aVar4.a();
    }

    public final void a(c state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.j = state.a;
        this.k = state.b;
        this.l = state.c;
        this.m = state.d;
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c() {
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeCountToolbarView.f(BarcodeCountToolbarView.this);
            }
        });
    }

    public final b b() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(BarcodeCountToolbarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.i = false;
        this$0.c();
    }

    public final void a(d1 d1Var) {
        this.a = d1Var;
    }

    public final void a(boolean z) {
        if (z == this.n) {
            return;
        }
        this.n = z;
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodeCountToolbarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.i = true;
        this$0.c();
    }

    public final void a() {
        int i;
        View view = this.b;
        int i2 = p;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.gravity = 17;
        if (this.n) {
            i = s;
        } else {
            i = r;
        }
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = q;
        view.setLayoutParams(layoutParams);
    }

    private final a a(Context context, int i, String value, String value2, boolean z, final Function0 function0) {
        a aVar = new a(context);
        aVar.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        aVar.a.setImageResource(i);
        aVar.a();
        Intrinsics.checkNotNullParameter(value, "value");
        aVar.c = value;
        aVar.a();
        Intrinsics.checkNotNullParameter(value2, "value");
        aVar.d = value2;
        aVar.a();
        aVar.e = z;
        aVar.a();
        aVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeCountToolbarView.a(Function0.this, view);
            }
        });
        this.d.addView(aVar);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(Function0 onClick, View view) {
        Intrinsics.checkNotNullParameter(onClick, "$onClick");
        onClick.invoke();
    }

    public final void a(BarcodeCountToolbarSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        a aVar = this.e;
        String value = settings.getAudioOnButtonText();
        if (value == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults = this.o;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            value = barcodeCountToolbarDefaults.audioOnButtonText(context);
        }
        aVar.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        aVar.c = value;
        aVar.a();
        a aVar2 = this.e;
        String value2 = settings.getAudioOffButtonText();
        if (value2 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults2 = this.o;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            value2 = barcodeCountToolbarDefaults2.audioOffButtonText(context2);
        }
        aVar2.getClass();
        Intrinsics.checkNotNullParameter(value2, "value");
        aVar2.d = value2;
        aVar2.a();
        a aVar3 = this.e;
        String audioButtonContentDescription = settings.getAudioButtonContentDescription();
        if (audioButtonContentDescription == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults3 = this.o;
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
            audioButtonContentDescription = barcodeCountToolbarDefaults3.audioButtonContentDescription(context3);
        }
        aVar3.setContentDescription(audioButtonContentDescription);
        a aVar4 = this.f;
        String value3 = settings.getVibrationOnButtonText();
        if (value3 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults4 = this.o;
            Context context4 = getContext();
            Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
            value3 = barcodeCountToolbarDefaults4.vibrationOnButtonText(context4);
        }
        aVar4.getClass();
        Intrinsics.checkNotNullParameter(value3, "value");
        aVar4.c = value3;
        aVar4.a();
        a aVar5 = this.f;
        String value4 = settings.getVibrationOffButtonText();
        if (value4 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults5 = this.o;
            Context context5 = getContext();
            Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
            value4 = barcodeCountToolbarDefaults5.vibrationOffButtonText(context5);
        }
        aVar5.getClass();
        Intrinsics.checkNotNullParameter(value4, "value");
        aVar5.d = value4;
        aVar5.a();
        a aVar6 = this.f;
        String vibrationButtonContentDescription = settings.getVibrationButtonContentDescription();
        if (vibrationButtonContentDescription == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults6 = this.o;
            Context context6 = getContext();
            Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
            vibrationButtonContentDescription = barcodeCountToolbarDefaults6.vibrationButtonContentDescription(context6);
        }
        aVar6.setContentDescription(vibrationButtonContentDescription);
        a aVar7 = this.g;
        String value5 = settings.getStrapModeOnButtonText();
        if (value5 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults7 = this.o;
            Context context7 = getContext();
            Intrinsics.checkNotNullExpressionValue(context7, "getContext(...)");
            value5 = barcodeCountToolbarDefaults7.strapModeOnButtonText(context7);
        }
        aVar7.getClass();
        Intrinsics.checkNotNullParameter(value5, "value");
        aVar7.c = value5;
        aVar7.a();
        a aVar8 = this.g;
        String value6 = settings.getStrapModeOffButtonText();
        if (value6 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults8 = this.o;
            Context context8 = getContext();
            Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
            value6 = barcodeCountToolbarDefaults8.strapModeOffButtonText(context8);
        }
        aVar8.getClass();
        Intrinsics.checkNotNullParameter(value6, "value");
        aVar8.d = value6;
        aVar8.a();
        a aVar9 = this.g;
        String strapModeButtonContentDescription = settings.getStrapModeButtonContentDescription();
        if (strapModeButtonContentDescription == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults9 = this.o;
            Context context9 = getContext();
            Intrinsics.checkNotNullExpressionValue(context9, "getContext(...)");
            strapModeButtonContentDescription = barcodeCountToolbarDefaults9.strapModeButtonContentDescription(context9);
        }
        aVar9.setContentDescription(strapModeButtonContentDescription);
        a aVar10 = this.h;
        String value7 = settings.getColorSchemeOnButtonText();
        if (value7 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults10 = this.o;
            Context context10 = getContext();
            Intrinsics.checkNotNullExpressionValue(context10, "getContext(...)");
            value7 = barcodeCountToolbarDefaults10.colorSchemeOnButtonText(context10);
        }
        aVar10.getClass();
        Intrinsics.checkNotNullParameter(value7, "value");
        aVar10.c = value7;
        aVar10.a();
        a aVar11 = this.h;
        String value8 = settings.getColorSchemeOffButtonText();
        if (value8 == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults11 = this.o;
            Context context11 = getContext();
            Intrinsics.checkNotNullExpressionValue(context11, "getContext(...)");
            value8 = barcodeCountToolbarDefaults11.colorSchemeOffButtonText(context11);
        }
        aVar11.getClass();
        Intrinsics.checkNotNullParameter(value8, "value");
        aVar11.d = value8;
        aVar11.a();
        a aVar12 = this.h;
        String colorSchemeButtonContentDescription = settings.getColorSchemeButtonContentDescription();
        if (colorSchemeButtonContentDescription == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults12 = this.o;
            Context context12 = getContext();
            Intrinsics.checkNotNullExpressionValue(context12, "getContext(...)");
            colorSchemeButtonContentDescription = barcodeCountToolbarDefaults12.colorSchemeButtonContentDescription(context12);
        }
        aVar12.setContentDescription(colorSchemeButtonContentDescription);
        BarcodeCountToolbarDefaults barcodeCountToolbarDefaults13 = new BarcodeCountToolbarDefaults();
        Context context13 = getContext();
        Intrinsics.checkNotNullExpressionValue(context13, "getContext(...)");
        String discloseButtonContentDescription = barcodeCountToolbarDefaults13.discloseButtonContentDescription(context13);
        if (discloseButtonContentDescription == null) {
            BarcodeCountToolbarDefaults barcodeCountToolbarDefaults14 = this.o;
            Context context14 = getContext();
            Intrinsics.checkNotNullExpressionValue(context14, "getContext(...)");
            discloseButtonContentDescription = barcodeCountToolbarDefaults14.discloseButtonContentDescription(context14);
        }
        this.b.setContentDescription(discloseButtonContentDescription);
        this.c.setContentDescription(discloseButtonContentDescription);
    }
}
