package com.scandit.datacapture.barcode.ar.ui.annotations.info;

import android.graphics.Typeface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
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

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\r\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R/\u0010\u0019\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010 \u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR+\u0010'\u001a\u00020!2\u0006\u0010\u0012\u001a\u00020!8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010,\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u0014\u001a\u0004\b)\u0010\u0010\"\u0004\b*\u0010+R+\u00100\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u0014\u001a\u0004\b.\u0010\u0010\"\u0004\b/\u0010+R/\u00107\u001a\u0004\u0018\u0001012\b\u0010\u0012\u001a\u0004\u0018\u0001018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u0014\u001a\u0004\b3\u00104\"\u0004\b5\u00106R+\u00109\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u0014\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R/\u0010C\u001a\u0004\u0018\u00010=2\b\u0010\u0012\u001a\u0004\u0018\u00010=8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\u0014\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR+\u0010E\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010\u0014\u001a\u0004\bE\u0010:\"\u0004\bF\u0010<R/\u0010J\u001a\u0004\u0018\u00010=2\b\u0010\u0012\u001a\u0004\u0018\u00010=8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010\u0014\u001a\u0004\bH\u0010@\"\u0004\bI\u0010B¨\u0006K"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationBodyComponent;", "", "<init>", "()V", "Lkotlin/Function0;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPropertyChangedListener$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "setPropertyChangedListener", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "<set-?>", "b", "Lkotlin/properties/ReadWriteProperty;", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "text", "Landroid/graphics/Typeface;", "c", "getTypeface", "()Landroid/graphics/Typeface;", "setTypeface", "(Landroid/graphics/Typeface;)V", "typeface", "", "d", "getTextSize", "()F", "setTextSize", "(F)V", "textSize", "e", "getTextColor", "setTextColor", "(I)V", "textColor", "f", "getTextAlignment", "setTextAlignment", "textAlignment", "", "g", "getStyledText", "()Ljava/lang/CharSequence;", "setStyledText", "(Ljava/lang/CharSequence;)V", "styledText", "h", "isLeftIconTappable", "()Z", "setLeftIconTappable", "(Z)V", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "i", "getLeftIcon", "()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "setLeftIcon", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;)V", "leftIcon", "j", "isRightIconTappable", "setRightIconTappable", "k", "getRightIcon", "setRightIcon", "rightIcon", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArInfoAnnotationBodyComponent {
    static final /* synthetic */ KProperty[] l = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "text", "getText()Ljava/lang/String;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "typeface", "getTypeface()Landroid/graphics/Typeface;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "textSize", "getTextSize()F", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "textColor", "getTextColor()I", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "textAlignment", "getTextAlignment()I", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "styledText", "getStyledText()Ljava/lang/CharSequence;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "isLeftIconTappable", "isLeftIconTappable()Z", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "leftIcon", "getLeftIcon()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "isRightIconTappable", "isRightIconTappable()Z", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(BarcodeArInfoAnnotationBodyComponent.class, "rightIcon", "getRightIcon()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", 0)};
    private WeakReference a = new WeakReference(a.a);
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$1 b;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$2 c;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$3 d;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$4 e;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$5 f;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$6 g;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$7 h;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$8 i;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$9 j;
    private final BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$10 k;

    /* JADX WARN: Type inference failed for: r1v10, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$9] */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$10] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$2] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$3] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$4] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$5] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$6] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$7] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$8] */
    public BarcodeArInfoAnnotationBodyComponent() {
        final String defaultInfoAnnotationBodyElementText = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementText();
        Delegates delegates = Delegates.INSTANCE;
        this.b = new ObservableProperty<String>(defaultInfoAnnotationBodyElementText) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, newValue);
            }
        };
        final Typeface defaultInfoAnnotationBodyElementTypeface = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTypeface();
        this.c = new ObservableProperty<Typeface>(defaultInfoAnnotationBodyElementTypeface) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Typeface oldValue, Typeface newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, newValue);
            }
        };
        final Float valueOf = Float.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTextSize());
        this.d = new ObservableProperty<Float>(valueOf) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual((Object) oldValue, (Object) newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, Float.valueOf(newValue.floatValue()));
            }
        };
        final Integer valueOf2 = Integer.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTextColor());
        this.e = new ObservableProperty<Integer>(valueOf2) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, Integer.valueOf(newValue.intValue()));
            }
        };
        final Integer valueOf3 = Integer.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTextAlignment());
        this.f = new ObservableProperty<Integer>(valueOf3) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, Integer.valueOf(newValue.intValue()));
            }
        };
        final CharSequence defaultInfoAnnotationBodyElementStyledText = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementStyledText();
        this.g = new ObservableProperty<CharSequence>(defaultInfoAnnotationBodyElementStyledText) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, CharSequence oldValue, CharSequence newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, newValue);
            }
        };
        final Boolean valueOf4 = Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementLeftIconTappable());
        this.h = new ObservableProperty<Boolean>(valueOf4) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                Boolean bool = newValue;
                bool.booleanValue();
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, bool);
            }
        };
        final ScanditIcon defaultInfoAnnotationBodyElementLeftIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementLeftIcon();
        this.i = new ObservableProperty<ScanditIcon>(defaultInfoAnnotationBodyElementLeftIcon) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, ScanditIcon oldValue, ScanditIcon newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, newValue);
            }
        };
        final Boolean valueOf5 = Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementRightIconTappable());
        this.j = new ObservableProperty<Boolean>(valueOf5) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$9
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                Boolean bool = newValue;
                bool.booleanValue();
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, bool);
            }
        };
        final ScanditIcon defaultInfoAnnotationBodyElementRightIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementRightIcon();
        this.k = new ObservableProperty<ScanditIcon>(defaultInfoAnnotationBodyElementRightIcon) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent$special$$inlined$distinctObservable$10
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, ScanditIcon oldValue, ScanditIcon newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationBodyComponent.access$propertyChanged(this, newValue);
            }
        };
    }

    public static final void access$propertyChanged(BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent, Object obj) {
        Function0 function0 = (Function0) barcodeArInfoAnnotationBodyComponent.a.get();
        if (function0 != null) {
            function0.invoke();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(BarcodeArInfoAnnotationBodyComponent.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent");
        BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent = (BarcodeArInfoAnnotationBodyComponent) other;
        return Intrinsics.areEqual(getText(), barcodeArInfoAnnotationBodyComponent.getText()) && Intrinsics.areEqual(getTypeface(), barcodeArInfoAnnotationBodyComponent.getTypeface()) && getTextSize() == barcodeArInfoAnnotationBodyComponent.getTextSize() && getTextColor() == barcodeArInfoAnnotationBodyComponent.getTextColor() && getTextAlignment() == barcodeArInfoAnnotationBodyComponent.getTextAlignment() && Intrinsics.areEqual(getStyledText(), barcodeArInfoAnnotationBodyComponent.getStyledText()) && isLeftIconTappable() == barcodeArInfoAnnotationBodyComponent.isLeftIconTappable() && Intrinsics.areEqual(getLeftIcon(), barcodeArInfoAnnotationBodyComponent.getLeftIcon()) && isRightIconTappable() == barcodeArInfoAnnotationBodyComponent.isRightIconTappable() && Intrinsics.areEqual(getRightIcon(), barcodeArInfoAnnotationBodyComponent.getRightIcon());
    }

    public final ScanditIcon getLeftIcon() {
        return getValue(this, l[7]);
    }

    public final ScanditIcon getRightIcon() {
        return getValue(this, l[9]);
    }

    public final CharSequence getStyledText() {
        return getValue(this, l[5]);
    }

    public final String getText() {
        return getValue(this, l[0]);
    }

    public final int getTextAlignment() {
        return getValue(this, l[4]).intValue();
    }

    public final int getTextColor() {
        return getValue(this, l[3]).intValue();
    }

    public final float getTextSize() {
        return getValue(this, l[2]).floatValue();
    }

    public final Typeface getTypeface() {
        return getValue(this, l[1]);
    }

    public int hashCode() {
        String text = getText();
        int textAlignment = (getTextAlignment() + ((getTextColor() + ((Float.hashCode(getTextSize()) + ((getTypeface().hashCode() + ((text != null ? text.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31;
        CharSequence styledText = getStyledText();
        int hashCode = (Boolean.hashCode(isLeftIconTappable()) + ((textAlignment + (styledText != null ? styledText.hashCode() : 0)) * 31)) * 31;
        ScanditIcon leftIcon = getLeftIcon();
        int hashCode2 = (Boolean.hashCode(isRightIconTappable()) + ((hashCode + (leftIcon != null ? leftIcon.hashCode() : 0)) * 31)) * 31;
        ScanditIcon rightIcon = getRightIcon();
        return hashCode2 + (rightIcon != null ? rightIcon.hashCode() : 0);
    }

    public final boolean isLeftIconTappable() {
        return getValue(this, l[6]).booleanValue();
    }

    public final boolean isRightIconTappable() {
        return getValue(this, l[8]).booleanValue();
    }

    public final void setLeftIcon(ScanditIcon scanditIcon) {
        setValue(this, l[7], scanditIcon);
    }

    public final void setLeftIconTappable(boolean z) {
        setValue(this, l[6], Boolean.valueOf(z));
    }

    public final void setPropertyChangedListener$scandit_barcode_capture(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = new WeakReference(listener);
    }

    public final void setRightIcon(ScanditIcon scanditIcon) {
        setValue(this, l[9], scanditIcon);
    }

    public final void setRightIconTappable(boolean z) {
        setValue(this, l[8], Boolean.valueOf(z));
    }

    public final void setStyledText(CharSequence charSequence) {
        setValue(this, l[5], charSequence);
    }

    public final void setText(String str) {
        setValue(this, l[0], str);
    }

    public final void setTextAlignment(int i) {
        setValue(this, l[4], Integer.valueOf(i));
    }

    public final void setTextColor(int i) {
        setValue(this, l[3], Integer.valueOf(i));
    }

    public final void setTextSize(float f) {
        setValue(this, l[2], Float.valueOf(f));
    }

    public final void setTypeface(Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        setValue(this, l[1], typeface);
    }
}
