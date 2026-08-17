package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItemSearchOptions;
import com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0390a extends FrameLayout {
    public Function1 a;
    public List b;
    public final ArrayDeque c;
    public final ArrayDeque d;
    public int e;
    public int f;
    public boolean g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0390a(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setClipChildren(false);
        this.b = CollectionsKt.emptyList();
        this.c = new ArrayDeque();
        this.d = new ArrayDeque();
        this.e = -1;
        this.f = -16776961;
        this.g = true;
    }

    public final void a(ArrayList value) {
        C0402m c0402m;
        BarcodeFindItemSearchOptions searchOptions;
        Brush brush;
        Intrinsics.checkNotNullParameter(value, "value");
        this.b = value;
        this.d.clear();
        this.c.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindDotView");
            C0402m c0402m2 = (C0402m) childAt;
            if (c0402m2.e) {
                this.d.addLast(c0402m2);
            } else {
                this.c.addLast(c0402m2);
            }
        }
        Iterator it = value.iterator();
        while (it.hasNext()) {
            y0 y0Var = (y0) it.next();
            if (y0Var.b == null) {
                c0402m = (C0402m) this.d.removeFirstOrNull();
                if (c0402m == null) {
                    c0402m = a();
                    if (true != c0402m.e) {
                        c0402m.e = true;
                        ViewExtensionsKt.runOnMainThread(c0402m, new C0399j(c0402m));
                    }
                }
            } else {
                c0402m = (C0402m) this.c.removeFirstOrNull();
                if (c0402m == null) {
                    c0402m = a();
                    if (this.g) {
                        c0402m.d.start();
                    }
                }
            }
            c0402m.setVisibility(0);
            boolean z = y0Var.b == null;
            if (z != c0402m.e) {
                c0402m.e = z;
                ViewExtensionsKt.runOnMainThread(c0402m, new C0399j(c0402m));
            }
            BarcodeFindItem barcodeFindItem = y0Var.b;
            int fillColor = (barcodeFindItem == null || (searchOptions = barcodeFindItem.getSearchOptions()) == null || (brush = searchOptions.getBrush()) == null) ? this.e : brush.getFillColor();
            if (fillColor != c0402m.a.getColor()) {
                c0402m.a.setColor(fillColor);
                PulsingView pulsingView = c0402m.d;
                Lazy lazy = C0402m.f;
                pulsingView.a(new int[]{com.scandit.datacapture.barcode.internal.module.extensions.a.a(fillColor, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(fillColor, 230)});
                if (!c0402m.e) {
                    ViewExtensionsKt.runOnMainThread(c0402m, new C0401l(c0402m));
                }
            }
            int i2 = this.f;
            if (i2 != c0402m.c.getColor()) {
                c0402m.c.setColor(i2);
                if (c0402m.e) {
                    ViewExtensionsKt.runOnMainThread(c0402m, new C0400k(c0402m));
                }
            }
            float f = y0Var.a.x;
            Lazy lazy2 = C0402m.f;
            c0402m.setTranslationX(f - (C0398i.a() / 2.0f));
            c0402m.setTranslationY(y0Var.a.y - (C0398i.a() / 2.0f));
            c0402m.setTag(y0Var);
            c0402m.setContentDescription(getContext().getString(R.string.sc_barcode_find_indicator_content_description, y0Var.c));
        }
        while (this.c.size() > 2) {
            removeView((View) this.c.removeLast());
        }
        while (this.d.size() > 2) {
            removeView((View) this.d.removeLast());
        }
        Iterator<E> it2 = this.c.iterator();
        while (it2.hasNext()) {
            ((C0402m) it2.next()).setVisibility(8);
        }
        Iterator<E> it3 = this.d.iterator();
        while (it3.hasNext()) {
            ((C0402m) it3.next()).setVisibility(8);
        }
        this.c.clear();
        this.d.clear();
    }

    public final C0402m a() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        C0402m c0402m = new C0402m(context);
        c0402m.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.a$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0390a.a(C0390a.this, view);
            }
        });
        addView(c0402m);
        return c0402m;
    }

    public static final void a(C0390a this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object tag = view.getTag();
        y0 y0Var = tag instanceof y0 ? (y0) tag : null;
        BarcodeFindItem barcodeFindItem = y0Var != null ? y0Var.b : null;
        Function1 function1 = this$0.a;
        if (function1 != null) {
            function1.invoke(barcodeFindItem);
        }
    }
}
