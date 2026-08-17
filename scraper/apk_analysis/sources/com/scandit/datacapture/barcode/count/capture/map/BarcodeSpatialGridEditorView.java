package com.scandit.datacapture.barcode.count.capture.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintPresenterV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J\u000f\u0010\u0003\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0003\u0010\u0004J/\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0014¢\u0006\u0004\b\n\u0010\u000bR$\u0010\u0012\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorView;", "Landroid/widget/FrameLayout;", "", "onAttachedToWindow", "()V", "", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewListener;", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewListener;", "getListener", "()Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewListener;", "setListener", "(Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSpatialGridEditorView extends FrameLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy i = LazyKt.lazy(b.a);
    private static final Lazy j = LazyKt.lazy(a.a);
    private final BarcodeSpatialGrid a;
    private final BarcodeSpatialGridEditorViewSettings b;
    private final BinsView c;
    private final Button d;
    private final Button e;
    private final HintHolderV2 f;
    private final HintPresenterV2 g;

    /* renamed from: h, reason: from kotlin metadata */
    private BarcodeSpatialGridEditorViewListener listener;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorView$Companion;", "", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGrid;", "grid", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewSettings;", "settings", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorView;", "newInstance", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGrid;Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorViewSettings;)Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridEditorView;", "", "DELAY_BEFORE_SHOWING_HINT", "J", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final float access$getMIN_BIN_VIEWS_HEIGHT_PX(Companion companion) {
            companion.getClass();
            return ((Number) BarcodeSpatialGridEditorView.j.getValue()).floatValue();
        }

        public static final float access$getSPACE_NEEDED_FOR_BUTTONS_PX(Companion companion) {
            companion.getClass();
            return ((Number) BarcodeSpatialGridEditorView.i.getValue()).floatValue();
        }

        @JvmStatic
        public final BarcodeSpatialGridEditorView newInstance(Context context, BarcodeSpatialGrid grid, BarcodeSpatialGridEditorViewSettings settings) throws IllegalStateException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(grid, "grid");
            Intrinsics.checkNotNullParameter(settings, "settings");
            return new BarcodeSpatialGridEditorView(context, grid, settings, null);
        }
    }

    public /* synthetic */ BarcodeSpatialGridEditorView(Context context, BarcodeSpatialGrid barcodeSpatialGrid, BarcodeSpatialGridEditorViewSettings barcodeSpatialGridEditorViewSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, barcodeSpatialGrid, barcodeSpatialGridEditorViewSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodeSpatialGridEditorView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BarcodeSpatialGridEditorViewListener barcodeSpatialGridEditorViewListener = this$0.listener;
        if (barcodeSpatialGridEditorViewListener != null) {
            barcodeSpatialGridEditorViewListener.onEditingFinished(this$0, this$0.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(BarcodeSpatialGridEditorView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BarcodeSpatialGridEditorViewListener barcodeSpatialGridEditorViewListener = this$0.listener;
        if (barcodeSpatialGridEditorViewListener != null) {
            barcodeSpatialGridEditorViewListener.onEditingCancelled(this$0);
        }
    }

    @JvmStatic
    public static final BarcodeSpatialGridEditorView newInstance(Context context, BarcodeSpatialGrid barcodeSpatialGrid, BarcodeSpatialGridEditorViewSettings barcodeSpatialGridEditorViewSettings) throws IllegalStateException {
        return INSTANCE.newInstance(context, barcodeSpatialGrid, barcodeSpatialGridEditorViewSettings);
    }

    public final BarcodeSpatialGridEditorViewListener getListener() {
        return this.listener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(new Runnable() { // from class: com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridEditorView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeSpatialGridEditorView.a(BarcodeSpatialGridEditorView.this);
            }
        }, 50L);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float f = h;
        Companion companion = INSTANCE;
        float coerceIn = RangesKt.coerceIn((f - Companion.access$getSPACE_NEEDED_FOR_BUTTONS_PX(companion)) / Companion.access$getMIN_BIN_VIEWS_HEIGHT_PX(companion), 0.0f, 1.0f);
        this.c.setScaleX(coerceIn);
        this.c.setScaleY(coerceIn);
    }

    public final void setListener(BarcodeSpatialGridEditorViewListener barcodeSpatialGridEditorViewListener) {
        this.listener = barcodeSpatialGridEditorViewListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView$Item] */
    private BarcodeSpatialGridEditorView(Context context, BarcodeSpatialGrid barcodeSpatialGrid, BarcodeSpatialGridEditorViewSettings barcodeSpatialGridEditorViewSettings) {
        super(context);
        this.a = barcodeSpatialGrid;
        this.b = barcodeSpatialGridEditorViewSettings;
        if (barcodeSpatialGrid.columns() != 2 || barcodeSpatialGrid.rows() != 4) {
            throw new IllegalArgumentException(("Unsupported grid size - requires a 4x2 grid, was " + barcodeSpatialGrid.rows() + 'x' + barcodeSpatialGrid.columns()).toString());
        }
        HintHolderV2 implementation = HintHolderV2.INSTANCE.implementation(context);
        this.f = implementation;
        this.g = new HintPresenterV2(implementation);
        BinsView.EditListener editListener = new BinsView.EditListener() { // from class: com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridEditorView$editListener$1
            @Override // com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView.EditListener
            public void onSubSwapped(int fromIndex, int toIndex) {
                BarcodeSpatialGrid barcodeSpatialGrid2;
                BarcodeSpatialGrid barcodeSpatialGrid3;
                BarcodeSpatialGrid barcodeSpatialGrid4;
                BarcodeSpatialGrid barcodeSpatialGrid5;
                BarcodeSpatialGrid barcodeSpatialGrid6;
                barcodeSpatialGrid2 = BarcodeSpatialGridEditorView.this.a;
                int columns = fromIndex / barcodeSpatialGrid2.columns();
                barcodeSpatialGrid3 = BarcodeSpatialGridEditorView.this.a;
                int columns2 = fromIndex % barcodeSpatialGrid3.columns();
                barcodeSpatialGrid4 = BarcodeSpatialGridEditorView.this.a;
                int columns3 = toIndex / barcodeSpatialGrid4.columns();
                barcodeSpatialGrid5 = BarcodeSpatialGridEditorView.this.a;
                int columns4 = toIndex % barcodeSpatialGrid5.columns();
                barcodeSpatialGrid6 = BarcodeSpatialGridEditorView.this.a;
                barcodeSpatialGrid6.getA().swapSubsAtCoordinates(columns, columns2, columns3, columns4);
            }

            @Override // com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView.EditListener
            public void onToteSwapped(int fromIndex, int toIndex) {
                BarcodeSpatialGrid barcodeSpatialGrid2;
                BarcodeSpatialGrid barcodeSpatialGrid3;
                BarcodeSpatialGrid barcodeSpatialGrid4;
                BarcodeSpatialGrid barcodeSpatialGrid5;
                BarcodeSpatialGrid barcodeSpatialGrid6;
                barcodeSpatialGrid2 = BarcodeSpatialGridEditorView.this.a;
                int columns = fromIndex / barcodeSpatialGrid2.columns();
                barcodeSpatialGrid3 = BarcodeSpatialGridEditorView.this.a;
                int columns2 = fromIndex % barcodeSpatialGrid3.columns();
                barcodeSpatialGrid4 = BarcodeSpatialGridEditorView.this.a;
                int columns3 = toIndex / barcodeSpatialGrid4.columns();
                barcodeSpatialGrid5 = BarcodeSpatialGridEditorView.this.a;
                int columns4 = toIndex % barcodeSpatialGrid5.columns();
                barcodeSpatialGrid6 = BarcodeSpatialGridEditorView.this.a;
                barcodeSpatialGrid6.getA().swapElementsAtCoordinates(columns, columns2, columns3, columns4);
            }
        };
        int i2 = 0;
        setClipChildren(false);
        setClipToPadding(false);
        LayoutInflater.from(context).inflate(R.layout.sc_barcode_spatial_grid_editor, this);
        View findViewById = findViewById(R.id.sc_editor);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        BinsView binsView = (BinsView) findViewById;
        this.c = binsView;
        binsView.setOnEditListener(editListener);
        View findViewById2 = findViewById(R.id.confirmButton);
        Button button = (Button) findViewById2;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridEditorView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeSpatialGridEditorView.a(BarcodeSpatialGridEditorView.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById2, "also(...)");
        this.d = button;
        View findViewById3 = findViewById(R.id.rescanButton);
        Button button2 = (Button) findViewById3;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridEditorView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeSpatialGridEditorView.b(BarcodeSpatialGridEditorView.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById3, "also(...)");
        this.e = button2;
        IntRange until = RangesKt.until(0, barcodeSpatialGrid.rows());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(RangesKt.until(0, barcodeSpatialGrid.columns()));
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IntRange intRange = (IntRange) next;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator<Integer> it3 = intRange.iterator();
            while (it3.hasNext()) {
                BarcodeSpatialGridElement elementAt = barcodeSpatialGrid.elementAt(i2, ((IntIterator) it3).nextInt());
                if (elementAt != null) {
                    String data = elementAt.getMainBarcode().getData();
                    data = data == null ? "" : data;
                    Barcode subBarcode = elementAt.getSubBarcode();
                    r7 = new BinsView.Item(data, subBarcode != null ? subBarcode.getData() : null);
                }
                arrayList3.add(r7);
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
            i2 = i3;
        }
        BinsView.Grid grid = new BinsView.Grid(barcodeSpatialGrid.rows(), barcodeSpatialGrid.columns(), arrayList2);
        BarcodeSpatialGridEditorViewSettings barcodeSpatialGridEditorViewSettings2 = this.b;
        binsView.initialize(grid, new BinsView.Settings(barcodeSpatialGridEditorViewSettings2.getToteTextFormat(), barcodeSpatialGridEditorViewSettings2.getToteColor(), barcodeSpatialGridEditorViewSettings2.getSubColor()));
        this.d.setText(this.b.getFinishMappingButtonText());
        this.e.setText(this.b.getCancelMappingButtonText());
        Object obj = this.f;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.view.View");
        addView((View) obj, new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BarcodeSpatialGridEditorView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.g.showToast(new ToastHint(NativeToastHintStyle.SUCCESS, this$0.b.getReorderHintText(), null, true, 4, null));
    }
}
