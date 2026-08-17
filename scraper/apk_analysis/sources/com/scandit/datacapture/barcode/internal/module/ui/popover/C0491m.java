package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0491m extends LinearLayout implements InterfaceC0492n {
    public static final Lazy d = LazyKt.lazy(C0487i.a);
    public final LinkedHashMap a;
    public boolean b;
    public Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0491m(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = new LinkedHashMap();
        setBackground(getResources().getDrawable(R.drawable.sc_count_popup_background, context.getTheme()));
        setVisibility(4);
        setClipToOutline(true);
        this.c = C0490l.a;
    }

    public final void a(EnumC0488j value) {
        Intrinsics.checkNotNullParameter(value, "value");
        int i = 1;
        if ((getOrientation() == 1 ? EnumC0488j.b : EnumC0488j.a) != value) {
            int ordinal = value.ordinal();
            if (ordinal == 0) {
                i = 0;
            } else if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            setOrientation(i);
        }
    }

    public final Size b(EnumC0488j orientation) {
        int i;
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Collection values = this.a.values();
        ArrayList arrayList = new ArrayList();
        Iterator it = values.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((C0485g) next).getVisibility() == 8)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C0485g c0485g = (C0485g) it2.next();
            c0485g.getClass();
            Lazy lazy = C0485g.d;
            Lazy lazy2 = C0485g.d;
            c0485g.c.measure(View.MeasureSpec.makeMeasureSpec(((Number) lazy2.getValue()).intValue() - (((Number) C0485g.h.getValue()).intValue() * 2), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, 0));
            arrayList2.add(new Size(((Number) lazy2.getValue()).intValue(), (((Number) C0485g.g.getValue()).intValue() * 2) + ((Number) C0485g.f.getValue()).intValue() + ((Number) C0485g.e.getValue()).intValue() + c0485g.c.getMeasuredHeight()));
        }
        int ordinal = orientation.ordinal();
        if (ordinal == 0) {
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                i += ((Size) it3.next()).getWidth();
            }
            Iterator it4 = arrayList2.iterator();
            if (!it4.hasNext()) {
                throw new NoSuchElementException();
            }
            int height = ((Size) it4.next()).getHeight();
            while (it4.hasNext()) {
                int height2 = ((Size) it4.next()).getHeight();
                if (height < height2) {
                    height = height2;
                }
            }
            return new Size(i, height);
        }
        if (ordinal != 1) {
            throw new NoWhenBranchMatchedException();
        }
        Iterator it5 = arrayList2.iterator();
        if (!it5.hasNext()) {
            throw new NoSuchElementException();
        }
        int width = ((Size) it5.next()).getWidth();
        while (it5.hasNext()) {
            int width2 = ((Size) it5.next()).getWidth();
            if (width < width2) {
                width = width2;
            }
        }
        Iterator it6 = arrayList2.iterator();
        while (it6.hasNext()) {
            i += ((Size) it6.next()).getHeight();
        }
        return new Size(width, i);
    }

    public final void a() {
        if (this.b) {
            ViewExtensionsKt.addRippleForeground$default(this, null, 1, null);
            setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.popover.m$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    C0491m.a(C0491m.this, view);
                }
            });
        } else {
            setForeground(null);
            setOnClickListener(null);
        }
    }

    public static final void a(C0491m this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.invoke();
    }

    public final void a(LinearPopoverButtonViewSettings settings, Function0 onClick) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        C0485g c0485g = new C0485g(context, new C0489k(onClick));
        c0485g.a(settings);
        Intrinsics.checkNotNullParameter(this, "container");
        Lazy lazy = C0485g.d;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Number) C0485g.d.getValue()).intValue(), -1);
        layoutParams.gravity = 17;
        Lazy lazy2 = C0485g.g;
        c0485g.setPadding(0, ((Number) lazy2.getValue()).intValue(), 0, ((Number) lazy2.getValue()).intValue());
        Unit unit = Unit.INSTANCE;
        addView(c0485g, layoutParams);
        LinkedHashMap linkedHashMap = this.a;
        Pair pair = TuplesKt.to(Integer.valueOf(settings.e()), c0485g);
        linkedHashMap.put(pair.getFirst(), pair.getSecond());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(int r3, boolean r4) {
        /*
            r2 = this;
            java.util.LinkedHashMap r2 = r2.a
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r2 = r2.get(r3)
            com.scandit.datacapture.barcode.internal.module.ui.popover.g r2 = (com.scandit.datacapture.barcode.internal.module.ui.popover.C0485g) r2
            r3 = 1
            r0 = 0
            if (r2 == 0) goto L1d
            int r1 = r2.getVisibility()
            if (r1 != 0) goto L18
            r1 = r3
            goto L19
        L18:
            r1 = r0
        L19:
            if (r1 != r4) goto L1d
            r1 = r3
            goto L1e
        L1d:
            r1 = r0
        L1e:
            r3 = r3 ^ r1
            if (r2 != 0) goto L22
            goto L29
        L22:
            if (r4 != 0) goto L26
            r0 = 8
        L26:
            r2.setVisibility(r0)
        L29:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.ui.popover.C0491m.a(int, boolean):boolean");
    }
}
