package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewCameraSwitchButton;
import com.scandit.datacapture.barcode.internal.module.spark.ui.button.SparkScanViewTargetModeButton;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningModeKt;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class N extends FrameLayout {
    public static final /* synthetic */ KProperty[] q = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(N.class, "toolbarBackgroundColor", "getToolbarBackgroundColor()Ljava/lang/Integer;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(N.class, "toolbarIconActiveTintColor", "getToolbarIconActiveTintColor()Ljava/lang/Integer;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(N.class, "toolbarIconInactiveTintColor", "getToolbarIconInactiveTintColor()Ljava/lang/Integer;", 0)};
    public final SparkScanStateManager a;
    public A b;
    public final ImageButton c;
    public final SparkScanViewTargetModeButton d;
    public final ImageButton e;
    public final ImageButton f;
    public final ImageButton g;
    public final SparkScanViewCameraSwitchButton h;
    public final View i;
    public final View j;
    public final View k;
    public final StartStopEventScrollView l;
    public final List m;
    public final K n;
    public final L o;
    public final M p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N(Context context, SparkScanStateManager stateManager) {
        super(context);
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.a = stateManager;
        Integer defaultToolbarBackgroundColor = SparkScanViewDefaults.getDefaultToolbarBackgroundColor();
        Delegates delegates = Delegates.INSTANCE;
        K k = new K(defaultToolbarBackgroundColor, this);
        this.n = k;
        this.o = new L(SparkScanViewDefaults.getDefaultToolbarIconActiveTintColor(), this);
        this.p = new M(SparkScanViewDefaults.getDefaultToolbarIconInactiveTintColor(), this);
        View.inflate(context, R.layout.sc_spark_scan_view_button_triggers_layout, this);
        setId(com.scandit.datacapture.barcode.internal.module.spark.ui.E.b);
        View findViewById = findViewById(R.id.spark_scan_target_mode_button);
        SparkScanViewTargetModeButton sparkScanViewTargetModeButton = (SparkScanViewTargetModeButton) findViewById;
        sparkScanViewTargetModeButton.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.d(N.this, view);
            }
        });
        SparkScanScanningMode scanningMode = stateManager.s();
        Intrinsics.checkNotNullParameter(scanningMode, "scanningMode");
        sparkScanViewTargetModeButton.setSelected(scanningMode instanceof SparkScanScanningMode.Target);
        Intrinsics.checkNotNullExpressionValue(findViewById, "also(...)");
        this.d = sparkScanViewTargetModeButton;
        View findViewById2 = findViewById(R.id.spark_capture_trigger_continuous_mode_button);
        ImageButton imageButton = (ImageButton) findViewById2;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.e(N.this, view);
            }
        });
        imageButton.setSelected(SparkScanScanningModeKt.getScanningBehavior(stateManager.s()) == SparkScanScanningBehavior.CONTINUOUS);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "also(...)");
        this.c = imageButton;
        View findViewById3 = findViewById(R.id.spark_scan_barcode_count_button);
        ImageButton imageButton2 = (ImageButton) findViewById3;
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.f(N.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById3, "also(...)");
        this.e = imageButton2;
        View findViewById4 = findViewById(R.id.spark_scan_barcode_find_button);
        ImageButton imageButton3 = (ImageButton) findViewById4;
        imageButton3.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.a(N.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById4, "also(...)");
        this.f = imageButton3;
        View findViewById5 = findViewById(R.id.spark_scan_label_capture_button);
        ImageButton imageButton4 = (ImageButton) findViewById5;
        imageButton4.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.b(N.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById5, "also(...)");
        this.g = imageButton4;
        View findViewById6 = findViewById(R.id.spark_scan_camera_button);
        SparkScanViewCameraSwitchButton sparkScanViewCameraSwitchButton = (SparkScanViewCameraSwitchButton) findViewById6;
        sparkScanViewCameraSwitchButton.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                N.c(N.this, view);
            }
        });
        CameraPosition cameraPosition = stateManager.a();
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        sparkScanViewCameraSwitchButton.setSelected(cameraPosition == CameraPosition.USER_FACING);
        sparkScanViewCameraSwitchButton.setImageResource(com.scandit.datacapture.barcode.internal.module.spark.ui.button.d.a[cameraPosition.ordinal()] == 1 ? R.drawable.sc_ic_spark_scan_camera_secondary : R.drawable.sc_ic_spark_scan_camera_primary);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "also(...)");
        this.h = sparkScanViewCameraSwitchButton;
        this.m = CollectionsKt.listOf((Object[]) new ImageButton[]{sparkScanViewTargetModeButton, imageButton, imageButton2, imageButton3, sparkScanViewCameraSwitchButton, imageButton4});
        View findViewById7 = findViewById(R.id.trigger_gradient_right);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.j = findViewById7;
        View findViewById8 = findViewById(R.id.trigger_gradient_left);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.k = findViewById8;
        View findViewById9 = findViewById(R.id.external_button_separator);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.i = findViewById9;
        View findViewById10 = findViewById(R.id.toolbar_scroll_view);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        StartStopEventScrollView startStopEventScrollView = (StartStopEventScrollView) findViewById10;
        this.l = startStopEventScrollView;
        imageButton2.setVisibility(!SparkScanViewDefaults.getDefaultBarcodeCountButtonVisible() ? 8 : 0);
        e();
        imageButton3.setVisibility(!SparkScanViewDefaults.getDefaultBarcodeFindButtonVisible() ? 8 : 0);
        e();
        sparkScanViewCameraSwitchButton.setVisibility(!SparkScanViewDefaults.getDefaultCameraSwitchButtonVisible() ? 8 : 0);
        e();
        imageButton.setVisibility(!SparkScanViewDefaults.getDefaultScanningBehaviorButtonVisible() ? 8 : 0);
        e();
        sparkScanViewTargetModeButton.setVisibility(!SparkScanViewDefaults.getDefaultTargetModeButtonVisible() ? 8 : 0);
        e();
        imageButton4.setVisibility(SparkScanViewDefaults.getDefaultLabelCaptureButtonVisible() ? 0 : 8);
        e();
        d();
        Integer num = (Integer) k.getValue(this, q[0]);
        if (num != null) {
            i = num.intValue();
        } else {
            NativeColor defaultToolbarBackgroundColor2 = NativeSparkScanViewDefaults.defaultToolbarBackgroundColor();
            Intrinsics.checkNotNullExpressionValue(defaultToolbarBackgroundColor2, "defaultToolbarBackgroundColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultToolbarBackgroundColor2);
        }
        setBackgroundColor(i);
        startStopEventScrollView.a(new I(this));
        startStopEventScrollView.a(new J(this));
        setClipToOutline(true);
        setOutlineProvider(new O());
    }

    public static final void c(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).z();
        }
    }

    public static final void d(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).I();
        }
    }

    public static final void e(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).H();
        }
    }

    public static final void f(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).x();
        }
    }

    public final ColorStateList a() {
        int i;
        int i2;
        L l = this.o;
        KProperty<?>[] kPropertyArr = q;
        Integer num = (Integer) l.getValue(this, kPropertyArr[1]);
        if (num != null) {
            i = num.intValue();
        } else {
            NativeColor defaultToolbarIconActiveTintColor = NativeSparkScanViewDefaults.defaultToolbarIconActiveTintColor();
            Intrinsics.checkNotNullExpressionValue(defaultToolbarIconActiveTintColor, "defaultToolbarIconActiveTintColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultToolbarIconActiveTintColor);
        }
        Integer num2 = (Integer) this.p.getValue(this, kPropertyArr[2]);
        if (num2 != null) {
            i2 = num2.intValue();
        } else {
            NativeColor defaultToolbarIconInactiveTintColor = NativeSparkScanViewDefaults.defaultToolbarIconInactiveTintColor();
            Intrinsics.checkNotNullExpressionValue(defaultToolbarIconInactiveTintColor, "defaultToolbarIconInactiveTintColor(...)");
            i2 = NativeColorExtensionsKt.toInt(defaultToolbarIconInactiveTintColor);
        }
        int argb = Color.argb(Math.max(0, Color.alpha(i) - 51), Color.red(i), Color.green(i), Color.blue(i));
        int argb2 = Color.argb(Math.max(0, Color.alpha(i2) - 51), Color.red(i2), Color.green(i2), Color.blue(i2));
        Pair unzip = ArraysKt.unzip(new Pair[]{TuplesKt.to(new int[]{android.R.attr.state_selected, android.R.attr.state_pressed}, Integer.valueOf(argb)), TuplesKt.to(new int[]{android.R.attr.state_pressed}, Integer.valueOf(argb2)), TuplesKt.to(new int[]{android.R.attr.state_selected, android.R.attr.state_enabled}, Integer.valueOf(i)), TuplesKt.to(new int[]{android.R.attr.state_selected}, Integer.valueOf(argb)), TuplesKt.to(new int[]{android.R.attr.state_enabled}, Integer.valueOf(i2)), TuplesKt.to(new int[0], Integer.valueOf(argb2))});
        return new ColorStateList((int[][]) ((List) unzip.component1()).toArray(new int[0][]), CollectionsKt.toIntArray((List) unzip.component2()));
    }

    public final ColorStateList b() {
        int i;
        Integer num = (Integer) this.p.getValue(this, q[2]);
        if (num != null) {
            i = num.intValue();
        } else {
            NativeColor defaultToolbarIconInactiveTintColor = NativeSparkScanViewDefaults.defaultToolbarIconInactiveTintColor();
            Intrinsics.checkNotNullExpressionValue(defaultToolbarIconInactiveTintColor, "defaultToolbarIconInactiveTintColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultToolbarIconInactiveTintColor);
        }
        Pair unzip = ArraysKt.unzip(new Pair[]{TuplesKt.to(new int[]{android.R.attr.state_enabled}, Integer.valueOf(i)), TuplesKt.to(new int[0], Integer.valueOf(Color.argb(Math.max(0, Color.alpha(i) - 51), Color.red(i), Color.green(i), Color.blue(i))))});
        return new ColorStateList((int[][]) ((List) unzip.component1()).toArray(new int[0][]), CollectionsKt.toIntArray((List) unzip.component2()));
    }

    public final boolean c() {
        List list = this.m;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((View) it.next()).getVisibility() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void d() {
        this.f.setImageTintList(b());
        this.e.setImageTintList(b());
        this.g.setImageTintList(b());
        this.h.setImageTintList(a());
        this.c.setImageTintList(a());
        this.d.setImageTintList(a());
        View view = this.i;
        Integer num = (Integer) this.p.getValue(this, q[2]);
        view.setBackgroundColor(num != null ? num.intValue() : getContext().getColor(R.color.sc_spark_scan_default_toolbar_separator_color));
    }

    public final void e() {
        this.i.setVisibility(((this.e.getVisibility() == 0 || this.f.getVisibility() == 0 || this.g.getVisibility() == 0) && (this.d.getVisibility() == 0 || this.c.getVisibility() == 0 || this.h.getVisibility() == 0)) ? 0 : 8);
    }

    public final void f() {
        this.c.setSelected(SparkScanScanningModeKt.getScanningBehavior(this.a.s()) == SparkScanScanningBehavior.CONTINUOUS);
        this.c.setEnabled(!this.a.j());
        SparkScanViewTargetModeButton sparkScanViewTargetModeButton = this.d;
        SparkScanScanningMode scanningMode = this.a.s();
        sparkScanViewTargetModeButton.getClass();
        Intrinsics.checkNotNullParameter(scanningMode, "scanningMode");
        sparkScanViewTargetModeButton.setSelected(scanningMode instanceof SparkScanScanningMode.Target);
        SparkScanViewCameraSwitchButton sparkScanViewCameraSwitchButton = this.h;
        CameraPosition cameraPosition = this.a.a();
        sparkScanViewCameraSwitchButton.getClass();
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        sparkScanViewCameraSwitchButton.setSelected(cameraPosition == CameraPosition.USER_FACING);
        sparkScanViewCameraSwitchButton.setImageResource(com.scandit.datacapture.barcode.internal.module.spark.ui.button.d.a[cameraPosition.ordinal()] == 1 ? R.drawable.sc_ic_spark_scan_camera_secondary : R.drawable.sc_ic_spark_scan_camera_primary);
        this.g.setEnabled(!this.a.j());
        this.f.setEnabled(!this.a.j());
        this.e.setEnabled(!this.a.j());
        this.l.a(new I(this));
        this.l.a(new J(this));
        a(this.l.getScrollX());
    }

    public static final void b(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).C();
        }
    }

    public static final void a(N this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        A a = this$0.b;
        if (a != null) {
            ((SparkScanViewPresenter) a).y();
        }
    }

    public final void a(int i) {
        float right = this.l.getChildAt(0).getRight() - this.l.getWidth();
        if (right > 0.0f) {
            float f = i / right;
            this.k.setAlpha(f);
            this.j.setAlpha(1.0f - f);
        } else {
            this.k.setAlpha(0.0f);
            this.j.setAlpha(0.0f);
        }
    }
}
