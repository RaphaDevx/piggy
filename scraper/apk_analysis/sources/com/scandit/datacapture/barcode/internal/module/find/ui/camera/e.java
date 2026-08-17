package com.scandit.datacapture.barcode.internal.module.find.ui.camera;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import com.scandit.datacapture.barcode.R;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends LinearLayout {
    public static final int g = Color.parseColor("#59595966");
    public static final Lazy h = LazyKt.lazy(a.a);
    public static final Lazy i = LazyKt.lazy(b.a);
    public List a;
    public float b;
    public Function1 c;
    public boolean d;
    public final c e;
    public final d f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = CollectionsKt.emptyList();
        a();
        this.e = new c(this);
        this.f = new d(this);
    }

    public static final void b(Function1 tmp0, View view) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(view);
    }

    public final void a() {
        setHorizontalGravity(17);
        setVerticalGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (this.d) {
            gradientDrawable.setColor(0);
        } else {
            gradientDrawable.setColor(g);
        }
        gradientDrawable.setCornerRadius(90.0f);
        setBackground(gradientDrawable);
        setShowDividers(2);
        setContentDescription(getContext().getString(R.string.sc_barcode_find_zoom_content_description));
        if (this.d) {
            a(this.b);
        } else {
            a(this.a, this.b);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable state) {
        List<Float> zoomLevels;
        Object parcelable;
        Intrinsics.checkNotNullParameter(state, "state");
        Bundle bundle = (Bundle) state;
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = bundle.getParcelable("superState", Parcelable.class);
            super.onRestoreInstanceState((Parcelable) parcelable);
        } else {
            super.onRestoreInstanceState(bundle.getParcelable("superState"));
        }
        float[] floatArray = bundle.getFloatArray("zoomLevels");
        if (floatArray != null) {
            Intrinsics.checkNotNull(floatArray);
            zoomLevels = ArraysKt.toList(floatArray);
        } else {
            zoomLevels = null;
        }
        float f = bundle.getFloat("currentZoomLevel");
        if (zoomLevels != null) {
            Intrinsics.checkNotNullParameter(zoomLevels, "zoomLevels");
            this.a = CollectionsKt.sorted(CollectionsKt.distinct(zoomLevels));
            if (this.b == f) {
                return;
            }
            this.b = f;
            a();
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putFloatArray("zoomLevels", CollectionsKt.toFloatArray(this.a));
        bundle.putFloat("currentZoomLevel", this.b);
        return bundle;
    }

    public final void a(float f) {
        removeAllViews();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        i iVar = new i(context);
        addView(iVar, new LinearLayout.LayoutParams(0, 0));
        final c cVar = this.e;
        iVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.camera.e$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                e.a(Function1.this, view);
            }
        });
        iVar.b = f;
        iVar.a(f);
        iVar.setSelected(true);
        if (this.b == f) {
            return;
        }
        this.b = f;
        a();
    }

    public static final void a(Function1 tmp0, View view) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(view);
    }

    public final void a(List list, float f) {
        int intValue;
        removeAllViews();
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            float floatValue = ((Number) it.next()).floatValue();
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            i iVar = new i(context);
            addView(iVar, new LinearLayout.LayoutParams(0, 0));
            final d dVar = this.f;
            iVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.camera.e$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    e.b(Function1.this, view);
                }
            });
            iVar.b = floatValue;
            iVar.a(floatValue);
            boolean z = Math.abs(floatValue - f) < 0.001f;
            iVar.setSelected(z);
            if (z && this.b != floatValue) {
                this.b = floatValue;
                a();
            }
        }
        if (list.size() == 2) {
            intValue = ((Number) h.getValue()).intValue();
        } else {
            intValue = ((Number) i.getValue()).intValue();
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setAlpha(0);
        shapeDrawable.setIntrinsicWidth(intValue);
        shapeDrawable.setIntrinsicHeight(intValue);
        setDividerDrawable(shapeDrawable);
    }
}
