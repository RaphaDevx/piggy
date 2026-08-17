package com.scandit.datacapture.barcode.ar.ui.annotations.popover;

import android.content.Context;
import android.graphics.Typeface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.internal.module.ui.popover.LinearPopoverButtonViewSettings;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR+\u0010(\u001a\u00020 2\u0006\u0010!\u001a\u00020 8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R+\u0010/\u001a\u00020)2\u0006\u0010!\u001a\u00020)8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010#\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R+\u00105\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010#\u001a\u0004\b1\u00102\"\u0004\b3\u00104R+\u0010;\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010#\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006<"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationButton;", "", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", BarcodePickDeserializer.FIELD_ICON, "", "text", "<init>", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Ljava/lang/String;)V", "Lkotlin/Function0;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPropertyChangedListener$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "setPropertyChangedListener", "", FirebaseAnalytics.Param.INDEX, "", "isEntirePopoverTappable", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/internal/module/ui/popover/LinearPopoverButtonViewSettings;", "getButtonSettings$scandit_barcode_capture", "(IZLandroid/content/Context;)Lcom/scandit/datacapture/barcode/internal/module/ui/popover/LinearPopoverButtonViewSettings;", "getButtonSettings", "a", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "getIcon", "()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "b", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "", "<set-?>", "d", "Lkotlin/properties/ReadWriteProperty;", "getTextSize", "()F", "setTextSize", "(F)V", "textSize", "Landroid/graphics/Typeface;", "e", "getTypeface", "()Landroid/graphics/Typeface;", "setTypeface", "(Landroid/graphics/Typeface;)V", "typeface", "f", "getTextColor", "()I", "setTextColor", "(I)V", "textColor", "g", "getEnabled", "()Z", "setEnabled", "(Z)V", ExtentionsKt.ENABLED_KEY, "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArPopoverAnnotationButton {
    static final /* synthetic */ KProperty[] h = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArPopoverAnnotationButton.class, "textSize", "getTextSize()F", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArPopoverAnnotationButton.class, "typeface", "getTypeface()Landroid/graphics/Typeface;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArPopoverAnnotationButton.class, "textColor", "getTextColor()I", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArPopoverAnnotationButton.class, ExtentionsKt.ENABLED_KEY, "getEnabled()Z", 0)};

    /* renamed from: a, reason: from kotlin metadata */
    private final ScanditIcon icon;

    /* renamed from: b, reason: from kotlin metadata */
    private final String text;
    private WeakReference c;
    private final BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$1 d;
    private final BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$2 e;
    private final BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$3 f;
    private final BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$4 g;

    /* JADX WARN: Type inference failed for: r3v3, types: [com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$2] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$3] */
    /* JADX WARN: Type inference failed for: r3v6, types: [com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$4] */
    public BarcodeArPopoverAnnotationButton(ScanditIcon icon, String text) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(text, "text");
        this.icon = icon;
        this.text = text;
        this.c = new WeakReference(a.a);
        final Float valueOf = Float.valueOf(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonTextSize());
        Delegates delegates = Delegates.INSTANCE;
        this.d = new ObservableProperty<Float>(valueOf) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual((Object) oldValue, (Object) newValue)) {
                    return;
                }
                BarcodeArPopoverAnnotationButton.access$propertyChanged(this, Float.valueOf(newValue.floatValue()));
            }
        };
        final Typeface defaultBarcodeArPopoverAnnotationButtonTypeFace = BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonTypeFace();
        this.e = new ObservableProperty<Typeface>(defaultBarcodeArPopoverAnnotationButtonTypeFace) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Typeface oldValue, Typeface newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArPopoverAnnotationButton.access$propertyChanged(this, newValue);
            }
        };
        final Integer valueOf2 = Integer.valueOf(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonTextColor());
        this.f = new ObservableProperty<Integer>(valueOf2) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArPopoverAnnotationButton.access$propertyChanged(this, Integer.valueOf(newValue.intValue()));
            }
        };
        final Boolean valueOf3 = Boolean.valueOf(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonEnabled());
        this.g = new ObservableProperty<Boolean>(valueOf3) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton$special$$inlined$distinctObservable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                Boolean bool = newValue;
                bool.booleanValue();
                BarcodeArPopoverAnnotationButton.access$propertyChanged(this, bool);
            }
        };
    }

    public static final void access$propertyChanged(BarcodeArPopoverAnnotationButton barcodeArPopoverAnnotationButton, Object obj) {
        Function0 function0 = (Function0) barcodeArPopoverAnnotationButton.c.get();
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final /* synthetic */ LinearPopoverButtonViewSettings getButtonSettings$scandit_barcode_capture(int index, boolean isEntirePopoverTappable, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new LinearPopoverButtonViewSettings(index, ScanditIconUtilsKt.getDrawable(this.icon, context), this.text, isEntirePopoverTappable ? null : context.getResources().getString(R.string.sc_barcode_ar_annotation_popover_button_content_description, this.text), getTextSize(), getTextColor(), getTypeface(), getEnabled(), !isEntirePopoverTappable, 128);
    }

    public final boolean getEnabled() {
        return getValue(this, h[3]).booleanValue();
    }

    public final ScanditIcon getIcon() {
        return this.icon;
    }

    public final String getText() {
        return this.text;
    }

    public final int getTextColor() {
        return getValue(this, h[2]).intValue();
    }

    public final float getTextSize() {
        return getValue(this, h[0]).floatValue();
    }

    public final Typeface getTypeface() {
        return getValue(this, h[1]);
    }

    public final void setEnabled(boolean z) {
        setValue(this, h[3], Boolean.valueOf(z));
    }

    public final void setPropertyChangedListener$scandit_barcode_capture(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c = new WeakReference(listener);
    }

    public final void setTextColor(int i) {
        setValue(this, h[2], Integer.valueOf(i));
    }

    public final void setTextSize(float f) {
        setValue(this, h[0], Float.valueOf(f));
    }

    public final void setTypeface(Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        setValue(this, h[1], typeface);
    }
}
