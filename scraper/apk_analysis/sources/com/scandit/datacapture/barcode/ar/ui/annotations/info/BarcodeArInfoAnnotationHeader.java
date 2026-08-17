package com.scandit.datacapture.barcode.ar.ui.annotations.info;

import android.graphics.Typeface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import io.sentry.protocol.Request;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R/\u0010\u0019\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R/\u0010 \u001a\u0004\u0018\u00010\u001a2\b\u0010\u0012\u001a\u0004\u0018\u00010\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR+\u0010'\u001a\u00020!2\u0006\u0010\u0012\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010.\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b)\u0010\u0014\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R+\u00103\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u0014\u001a\u0004\b0\u0010\u0010\"\u0004\b1\u00102R+\u00107\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\u0014\u001a\u0004\b5\u0010\u0010\"\u0004\b6\u00102¨\u00068"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;", "", "<init>", "()V", "Lkotlin/Function0;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPropertyChangedListener$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "setPropertyChangedListener", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "<set-?>", "b", "Lkotlin/properties/ReadWriteProperty;", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "text", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "c", "getIcon", "()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "setIcon", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;)V", BarcodePickDeserializer.FIELD_ICON, "", "d", "getTextSize", "()F", "setTextSize", "(F)V", "textSize", "Landroid/graphics/Typeface;", "e", "getTypeface", "()Landroid/graphics/Typeface;", "setTypeface", "(Landroid/graphics/Typeface;)V", "typeface", "f", "getTextColor", "setTextColor", "(I)V", "textColor", "g", "getBackgroundColor", "setBackgroundColor", "backgroundColor", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArInfoAnnotationHeader {
    static final /* synthetic */ KProperty[] h = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, "text", "getText()Ljava/lang/String;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, BarcodePickDeserializer.FIELD_ICON, "getIcon()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, "textSize", "getTextSize()F", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, "typeface", "getTypeface()Landroid/graphics/Typeface;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, "textColor", "getTextColor()I", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationHeader.class, "backgroundColor", "getBackgroundColor()I", 0)};
    private WeakReference a = new WeakReference(c.a);
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$1 b;
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$2 c;
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$3 d;
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$4 e;
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$5 f;
    private final BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$6 g;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$2] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$3] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$4] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$5] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$6] */
    public BarcodeArInfoAnnotationHeader() {
        final String defaultInfoAnnotationHeaderText = BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderText();
        Delegates delegates = Delegates.INSTANCE;
        this.b = new ObservableProperty<String>(defaultInfoAnnotationHeaderText) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, newValue);
            }
        };
        final ScanditIcon defaultInfoAnnotationHeaderIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderIcon();
        this.c = new ObservableProperty<ScanditIcon>(defaultInfoAnnotationHeaderIcon) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, ScanditIcon oldValue, ScanditIcon newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, newValue);
            }
        };
        final Float valueOf = Float.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderTextSize());
        this.d = new ObservableProperty<Float>(valueOf) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual((Object) oldValue, (Object) newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, Float.valueOf(newValue.floatValue()));
            }
        };
        final Typeface defaultInfoAnnotationHeaderTypeface = BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderTypeface();
        this.e = new ObservableProperty<Typeface>(defaultInfoAnnotationHeaderTypeface) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Typeface oldValue, Typeface newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, newValue);
            }
        };
        final Integer valueOf2 = Integer.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderTextColor());
        this.f = new ObservableProperty<Integer>(valueOf2) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, Integer.valueOf(newValue.intValue()));
            }
        };
        final Integer valueOf3 = Integer.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderBackgroundColor());
        this.g = new ObservableProperty<Integer>(valueOf3) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader$special$$inlined$distinctObservable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader.access$propertyChanged(this, Integer.valueOf(newValue.intValue()));
            }
        };
    }

    public static final void access$propertyChanged(BarcodeArInfoAnnotationHeader barcodeArInfoAnnotationHeader, Object obj) {
        Function0 function0 = (Function0) barcodeArInfoAnnotationHeader.a.get();
        if (function0 != null) {
            function0.invoke();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BarcodeArInfoAnnotationHeader)) {
            return false;
        }
        BarcodeArInfoAnnotationHeader barcodeArInfoAnnotationHeader = (BarcodeArInfoAnnotationHeader) other;
        return Intrinsics.areEqual(getText(), barcodeArInfoAnnotationHeader.getText()) && Intrinsics.areEqual(getIcon(), barcodeArInfoAnnotationHeader.getIcon()) && getTextSize() == barcodeArInfoAnnotationHeader.getTextSize() && Intrinsics.areEqual(getTypeface(), barcodeArInfoAnnotationHeader.getTypeface()) && getTextColor() == barcodeArInfoAnnotationHeader.getTextColor() && getBackgroundColor() == barcodeArInfoAnnotationHeader.getBackgroundColor();
    }

    public final int getBackgroundColor() {
        return getValue(this, h[5]).intValue();
    }

    public final ScanditIcon getIcon() {
        return getValue(this, h[1]);
    }

    public final String getText() {
        return getValue(this, h[0]);
    }

    public final int getTextColor() {
        return getValue(this, h[4]).intValue();
    }

    public final float getTextSize() {
        return getValue(this, h[2]).floatValue();
    }

    public final Typeface getTypeface() {
        return getValue(this, h[3]);
    }

    public int hashCode() {
        String text = getText();
        int hashCode = (text != null ? text.hashCode() : 0) * 31;
        ScanditIcon icon = getIcon();
        return getBackgroundColor() + ((getTextColor() + ((getTypeface().hashCode() + ((Float.hashCode(getTextSize()) + ((hashCode + (icon != null ? icon.hashCode() : 0)) * 31)) * 31)) * 31)) * 31);
    }

    public final void setBackgroundColor(int i) {
        setValue(this, h[5], Integer.valueOf(i));
    }

    public final void setIcon(ScanditIcon scanditIcon) {
        setValue(this, h[1], scanditIcon);
    }

    public final void setPropertyChangedListener$scandit_barcode_capture(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = new WeakReference(listener);
    }

    public final void setText(String str) {
        setValue(this, h[0], str);
    }

    public final void setTextColor(int i) {
        setValue(this, h[4], Integer.valueOf(i));
    }

    public final void setTextSize(float f) {
        setValue(this, h[2], Float.valueOf(f));
    }

    public final void setTypeface(Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        setValue(this, h[3], typeface);
    }
}
