package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/module/spark/ui/minipreview/StartStopEventScrollView;", "Landroid/widget/HorizontalScrollView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class StartStopEventScrollView extends HorizontalScrollView {
    private InterfaceC0453d a;
    private Function1 b;
    private boolean c;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StartStopEventScrollView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void a(J j) {
        this.a = j;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StartStopEventScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void a(I i) {
        this.b = i;
    }

    public /* synthetic */ StartStopEventScrollView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(StartStopEventScrollView this$0, View view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1 function1 = this$0.b;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StartStopEventScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.StartStopEventScrollView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i2, int i3, int i4, int i5) {
                StartStopEventScrollView.a(StartStopEventScrollView.this, view, i2, i3, i4, i5);
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.StartStopEventScrollView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean a;
                a = StartStopEventScrollView.a(StartStopEventScrollView.this, view, motionEvent);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        if (r4 != 8) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean a(com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.StartStopEventScrollView r3, android.view.View r4, android.view.MotionEvent r5) {
        /*
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            int r4 = r5.getAction()
            r5 = 0
            r0 = -1
            r1 = 1
            if (r4 == r1) goto L3d
            r2 = 2
            if (r4 == r2) goto L19
            r2 = 3
            if (r4 == r2) goto L3d
            r2 = 8
            if (r4 == r2) goto L19
            goto L5c
        L19:
            boolean r4 = r3.c
            if (r4 != 0) goto L5c
            boolean r4 = r3.canScrollHorizontally(r1)
            if (r4 != 0) goto L29
            boolean r4 = r3.canScrollHorizontally(r0)
            if (r4 == 0) goto L5c
        L29:
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.d r4 = r3.a
            if (r4 == 0) goto L3a
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.J r4 = (com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.J) r4
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N r4 = r4.a
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.A r4 = r4.b
            if (r4 == 0) goto L3a
            com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter r4 = (com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter) r4
            r4.J()
        L3a:
            r3.c = r1
            goto L5c
        L3d:
            boolean r4 = r3.canScrollHorizontally(r1)
            if (r4 != 0) goto L49
            boolean r4 = r3.canScrollHorizontally(r0)
            if (r4 == 0) goto L5c
        L49:
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.d r4 = r3.a
            if (r4 == 0) goto L5a
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.J r4 = (com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.J) r4
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.N r4 = r4.a
            com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.A r4 = r4.b
            if (r4 == 0) goto L5a
            com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter r4 = (com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter) r4
            r4.K()
        L5a:
            r3.c = r5
        L5c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.StartStopEventScrollView.a(com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.StartStopEventScrollView, android.view.View, android.view.MotionEvent):boolean");
    }
}
