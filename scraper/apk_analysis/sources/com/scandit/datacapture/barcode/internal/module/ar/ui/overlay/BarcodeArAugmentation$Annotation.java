package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import android.view.View;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class BarcodeArAugmentation$Annotation {
    private final BarcodeArAnnotation a;
    private final View b;
    private boolean c;

    public BarcodeArAugmentation$Annotation(BarcodeArAnnotation annotation, View view) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a = annotation;
        this.b = view;
    }

    public final BarcodeArAnnotation a() {
        return this.a;
    }

    public final View b() {
        return this.b;
    }

    public final boolean c() {
        return this.c;
    }

    public final boolean d() {
        String name;
        Class<?> cls = this.a.getClass();
        if (ArraysKt.contains((Class<?>[]) f.b, cls)) {
            return true;
        }
        Package r0 = cls.getPackage();
        if (r0 == null || (name = r0.getName()) == null || !StringsKt.startsWith$default(name, "com.scandit.datacapture.barcode.ar", false, 2, (Object) null)) {
            return false;
        }
        throw new IllegalStateException(("`" + cls.getSimpleName() + "` class with package `" + name + "` should be registered in `BARCODE_AR_ANNOTATION_CLASSES`").toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BarcodeArAugmentation$Annotation)) {
            return false;
        }
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = (BarcodeArAugmentation$Annotation) obj;
        return Intrinsics.areEqual(this.a, barcodeArAugmentation$Annotation.a) && Intrinsics.areEqual(this.b, barcodeArAugmentation$Annotation.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Annotation(annotation=" + this.a + ", view=" + this.b + ')';
    }

    public final void a(boolean z) {
        this.c = z;
    }
}
