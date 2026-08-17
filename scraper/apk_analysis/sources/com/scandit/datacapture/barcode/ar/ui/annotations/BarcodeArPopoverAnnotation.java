package com.scandit.datacapture.barcode.ar.ui.annotations;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationListener;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.ui.popover.C0485g;
import com.scandit.datacapture.barcode.internal.module.ui.popover.C0491m;
import com.scandit.datacapture.barcode.internal.module.ui.popover.EnumC0488j;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0492n;
import com.scandit.datacapture.barcode.internal.module.ui.popover.LinearPopoverButtonViewSettings;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2UtilsKt;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 F2\u00020\u0001:\u0001FB%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ)\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR$\u0010$\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R*\u0010)\u001a\u00020%2\u0006\u0010&\u001a\u00020%8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R+\u00105\u001a\u00020-2\u0006\u0010.\u001a\u00020-8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R*\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010E\u001a\u00020>8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D¨\u0006G"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArPopoverAnnotation;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotation;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationButton;", "buttons", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/data/Barcode;Ljava/util/List;)V", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "barcodeLocation", "highlightViewLocation", "Landroid/view/View;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "update", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Landroid/view/View;)V", "createView", "()Landroid/view/View;", "b", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "c", "Ljava/util/List;", "getButtons", "()Ljava/util/List;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationListener;", "d", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationListener;", "getListener", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationListener;", "setListener", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "value", "e", "Z", "isEntirePopoverTappable", "()Z", "setEntirePopoverTappable", "(Z)V", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationAnchor;", "<set-?>", "g", "Lkotlin/properties/ReadWriteProperty;", "getAnchor", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationAnchor;", "setAnchor", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationAnchor;)V", "anchor", "Lkotlin/Function0;", "i", "Lkotlin/jvm/functions/Function0;", "getOnAnnotationOrElementTap$scandit_barcode_capture", "()Lkotlin/jvm/functions/Function0;", "setOnAnnotationOrElementTap$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "onAnnotationOrElementTap", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "j", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "getAnnotationTrigger", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "setAnnotationTrigger", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;)V", "annotationTrigger", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArPopoverAnnotation implements BarcodeArAnnotation {
    private final Context a;

    /* renamed from: b, reason: from kotlin metadata */
    private final Barcode barcode;

    /* renamed from: c, reason: from kotlin metadata */
    private final List buttons;

    /* renamed from: d, reason: from kotlin metadata */
    private BarcodeArPopoverAnnotationListener listener;

    /* renamed from: e, reason: from kotlin metadata */
    private boolean isEntirePopoverTappable;
    private boolean f;
    private final BarcodeArPopoverAnnotation$special$$inlined$distinctObservable$1 g;
    private boolean h;

    /* renamed from: i, reason: from kotlin metadata */
    private /* synthetic */ Function0 onAnnotationOrElementTap;

    /* renamed from: j, reason: from kotlin metadata */
    private BarcodeArAnnotationTrigger annotationTrigger;
    private com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e k;
    private final s l;
    static final /* synthetic */ KProperty[] m = {a.a(BarcodeArPopoverAnnotation.class, "anchor", "getAnchor()Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationAnchor;", 0)};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy n = LazyKt.lazy(p.a);

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001R\u001b\u0010\u0007\u001a\u00020\u00028@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArPopoverAnnotation$Companion;", "", "", "MARGIN$delegate", "Lkotlin/Lazy;", "getMARGIN$scandit_barcode_capture", "()I", "MARGIN", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final int getMARGIN$scandit_barcode_capture() {
            return ((Number) BarcodeArPopoverAnnotation.n.getValue()).intValue();
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation$special$$inlined$distinctObservable$1] */
    public BarcodeArPopoverAnnotation(Context context, Barcode barcode, List<BarcodeArPopoverAnnotationButton> buttons) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        this.a = context;
        this.barcode = barcode;
        this.buttons = buttons;
        this.isEntirePopoverTappable = BarcodeArViewDefaults.getDefaultIsEntirePopoverTappable();
        final BarcodeArPopoverAnnotationAnchor defaultPopoverAnnotationAnchor = BarcodeArViewDefaults.getDefaultPopoverAnnotationAnchor();
        Delegates delegates = Delegates.INSTANCE;
        this.g = new ObservableProperty<BarcodeArPopoverAnnotationAnchor>(defaultPopoverAnnotationAnchor) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, BarcodeArPopoverAnnotationAnchor oldValue, BarcodeArPopoverAnnotationAnchor newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                this.f = true;
            }
        };
        this.annotationTrigger = BarcodeArViewDefaults.getDefaultPopoverAnnotationTrigger();
        this.l = new s(this);
        Iterator<T> it = buttons.iterator();
        while (it.hasNext()) {
            ((BarcodeArPopoverAnnotationButton) it.next()).setPropertyChangedListener$scandit_barcode_capture(this.l);
        }
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public View createView() {
        EnumC0488j enumC0488j;
        FrameLayout frameLayout = new FrameLayout(this.a);
        Companion companion = INSTANCE;
        frameLayout.setPadding(companion.getMARGIN$scandit_barcode_capture(), companion.getMARGIN$scandit_barcode_capture(), companion.getMARGIN$scandit_barcode_capture(), companion.getMARGIN$scandit_barcode_capture());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.setContentDescription(frameLayout.getResources().getString(R.string.sc_barcode_ar_annotation_popover_content_description, this.barcode.getData()));
        C0491m c0491m = new C0491m(this.a);
        c0491m.setId(R.id.sc_ar_linear_popover_view);
        c0491m.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        r rVar = new r(this);
        Intrinsics.checkNotNullParameter(rVar, "<set-?>");
        c0491m.c = rVar;
        boolean z = this.isEntirePopoverTappable;
        if (c0491m.b != z) {
            c0491m.b = z;
            c0491m.a();
        }
        int i = 0;
        c0491m.setVisibility(0);
        for (Object obj : this.buttons) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BarcodeArPopoverAnnotationButton barcodeArPopoverAnnotationButton = (BarcodeArPopoverAnnotationButton) obj;
            c0491m.a(barcodeArPopoverAnnotationButton.getButtonSettings$scandit_barcode_capture(i, this.isEntirePopoverTappable, this.a), new q(this, barcodeArPopoverAnnotationButton, i));
            c0491m.a(i, true);
            i = i2;
        }
        this.k = new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e(c0491m);
        BarcodeArPopoverAnnotationAnchor anchor = getAnchor();
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int i3 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.a.a[anchor.ordinal()];
        if (i3 == 1 || i3 == 2) {
            enumC0488j = EnumC0488j.b;
        } else {
            if (i3 != 3 && i3 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            enumC0488j = EnumC0488j.a;
        }
        c0491m.a(enumC0488j);
        frameLayout.addView(c0491m);
        return frameLayout;
    }

    public final BarcodeArPopoverAnnotationAnchor getAnchor() {
        return getValue(this, m[0]);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public BarcodeArAnnotationTrigger getAnnotationTrigger() {
        return this.annotationTrigger;
    }

    public final Barcode getBarcode() {
        return this.barcode;
    }

    public final List<BarcodeArPopoverAnnotationButton> getButtons() {
        return this.buttons;
    }

    public final BarcodeArPopoverAnnotationListener getListener() {
        return this.listener;
    }

    public final Function0<Unit> getOnAnnotationOrElementTap$scandit_barcode_capture() {
        return this.onAnnotationOrElementTap;
    }

    /* renamed from: isEntirePopoverTappable, reason: from getter */
    public final boolean getIsEntirePopoverTappable() {
        return this.isEntirePopoverTappable;
    }

    public final void setAnchor(BarcodeArPopoverAnnotationAnchor barcodeArPopoverAnnotationAnchor) {
        Intrinsics.checkNotNullParameter(barcodeArPopoverAnnotationAnchor, "<set-?>");
        setValue(this, m[0], barcodeArPopoverAnnotationAnchor);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void setAnnotationTrigger(BarcodeArAnnotationTrigger barcodeArAnnotationTrigger) {
        Intrinsics.checkNotNullParameter(barcodeArAnnotationTrigger, "<set-?>");
        this.annotationTrigger = barcodeArAnnotationTrigger;
    }

    public final void setEntirePopoverTappable(boolean z) {
        this.isEntirePopoverTappable = z;
        this.l.invoke();
    }

    public final void setListener(BarcodeArPopoverAnnotationListener barcodeArPopoverAnnotationListener) {
        this.listener = barcodeArPopoverAnnotationListener;
    }

    public final void setOnAnnotationOrElementTap$scandit_barcode_capture(Function0<Unit> function0) {
        this.onAnnotationOrElementTap = function0;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void update(Quadrilateral barcodeLocation, Quadrilateral highlightViewLocation, View view) {
        EnumC0488j enumC0488j;
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        Intrinsics.checkNotNullParameter(view, "view");
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e eVar = null;
        int i = 0;
        if (this.f) {
            this.f = false;
            com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e eVar2 = this.k;
            if (eVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewHolder");
                eVar2 = null;
            }
            BarcodeArPopoverAnnotationAnchor anchor = getAnchor();
            eVar2.getClass();
            Intrinsics.checkNotNullParameter(anchor, "anchor");
            InterfaceC0492n interfaceC0492n = eVar2.a;
            int i2 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.a.a[anchor.ordinal()];
            if (i2 == 1 || i2 == 2) {
                enumC0488j = EnumC0488j.b;
            } else {
                if (i2 != 3 && i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                enumC0488j = EnumC0488j.a;
            }
            ((C0491m) interfaceC0492n).a(enumC0488j);
        }
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e eVar3 = this.k;
        if (eVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewHolder");
            eVar3 = null;
        }
        if (highlightViewLocation != null) {
            barcodeLocation = highlightViewLocation;
        }
        boolean z = this.isEntirePopoverTappable;
        BarcodeArPopoverAnnotationAnchor anchor2 = getAnchor();
        eVar3.getClass();
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(anchor2, "anchor");
        if (view.getParent() != null) {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width > 0 && height > 0) {
                C0491m c0491m = (C0491m) eVar3.a;
                if (c0491m.b != z) {
                    c0491m.b = z;
                    c0491m.a();
                }
                eVar3.c = anchor2;
                eVar3.b = barcodeLocation;
                eVar3.d = Size2UtilsKt.Size2(width, height);
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.b bVar = eVar3.e;
                ViewParent parent = view.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.widget.FrameLayout");
                bVar.a(view, (FrameLayout) parent);
            }
        }
        if (this.h) {
            this.h = false;
            com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover.e eVar4 = this.k;
            if (eVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewHolder");
            } else {
                eVar = eVar4;
            }
            List list = this.buttons;
            ArrayList settings = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Object obj : list) {
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                settings.add(((BarcodeArPopoverAnnotationButton) obj).getButtonSettings$scandit_barcode_capture(i, this.isEntirePopoverTappable, this.a));
                i = i3;
            }
            eVar.getClass();
            Intrinsics.checkNotNullParameter(settings, "settings");
            InterfaceC0492n interfaceC0492n2 = eVar.a;
            Iterator it = settings.iterator();
            while (it.hasNext()) {
                LinearPopoverButtonViewSettings settings2 = (LinearPopoverButtonViewSettings) it.next();
                C0491m c0491m2 = (C0491m) interfaceC0492n2;
                c0491m2.getClass();
                Intrinsics.checkNotNullParameter(settings2, "settings");
                C0485g c0485g = (C0485g) c0491m2.a.get(Integer.valueOf(settings2.e()));
                if (c0485g != null) {
                    c0485g.a(settings2);
                }
            }
        }
    }
}
