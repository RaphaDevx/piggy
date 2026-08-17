package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;
import android.widget.LinearLayout;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArBodyElementDataHolder;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArBodyElementRowDataHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0295c extends LinearLayout {
    public final InterfaceC0296d a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0295c(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        C0297e rowFactory = new C0297e(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rowFactory, "rowFactory");
        this.a = rowFactory;
        setId(View.generateViewId());
        setOrientation(1);
        setShowDividers(2);
    }

    public final void a(BarcodeArBodyElementDataHolder newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        setBackgroundColor(newData.getBackgroundColor());
        List<BarcodeArBodyElementRowDataHolder> bodyElements = newData.getBodyElements();
        if (bodyElements.isEmpty()) {
            setPadding(0, 0, 0, 0);
        } else {
            int paddingAndCornerRadius$scandit_barcode_capture = (int) newData.getWidth().toPaddingAndCornerRadius$scandit_barcode_capture();
            setPadding(0, paddingAndCornerRadius$scandit_barcode_capture, 0, paddingAndCornerRadius$scandit_barcode_capture);
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setAlpha(0);
        shapeDrawable.setIntrinsicHeight((int) newData.getWidth().toPaddingAndCornerRadius$scandit_barcode_capture());
        setDividerDrawable(shapeDrawable);
        int childCount = getChildCount() - bodyElements.size();
        if (childCount > 0) {
            removeViews(0, childCount);
        }
        IntRange until = RangesKt.until(0, getChildCount());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            View childAt = getChildAt(((IntIterator) it).nextInt());
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyRowView");
            arrayList.add((n) childAt);
        }
        Iterator it2 = arrayList.iterator();
        int i = 0;
        while (it2.hasNext()) {
            Object next = it2.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            n view = (n) next;
            InterfaceC0296d interfaceC0296d = this.a;
            BarcodeArBodyElementRowDataHolder newData2 = bodyElements.get(i);
            ((C0297e) interfaceC0296d).getClass();
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(newData2, "newData");
            view.getClass();
            Intrinsics.checkNotNullParameter(newData2, "<set-?>");
            view.a.setValue(view, n.j[0], newData2);
            i = i2;
        }
        if (childCount < 0) {
            int size = bodyElements.size();
            for (int childCount2 = getChildCount(); childCount2 < size; childCount2++) {
                InterfaceC0296d interfaceC0296d2 = this.a;
                BarcodeArBodyElementRowDataHolder initialData = bodyElements.get(childCount2);
                C0297e c0297e = (C0297e) interfaceC0296d2;
                c0297e.getClass();
                Intrinsics.checkNotNullParameter(initialData, "initialData");
                addView(new n(c0297e.a, initialData));
            }
        }
    }
}
