package com.scandit.datacapture.barcode.ar.ui.annotations;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationFooter;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationHeader;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.C0294b;
import com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.E;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.tealium.library.DataSources;
import io.sentry.SentryLogEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0015\u001a\u00020\u0012H\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R(\u0010#\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R(\u0010(\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b$\u0010\u001c\u0012\u0004\b'\u0010\"\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R(\u0010-\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b)\u0010\u001c\u0012\u0004\b,\u0010\"\u001a\u0004\b*\u0010\u001e\"\u0004\b+\u0010 R(\u00102\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b.\u0010\u001c\u0012\u0004\b1\u0010\"\u001a\u0004\b/\u0010\u001e\"\u0004\b0\u0010 R7\u0010<\u001a\b\u0012\u0004\u0012\u000204032\f\u00105\u001a\b\u0012\u0004\u0012\u000204038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R+\u0010A\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u00128F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u00107\u001a\u0004\b>\u0010\u0014\"\u0004\b?\u0010@R+\u0010H\u001a\u00020B2\u0006\u00105\u001a\u00020B8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u00107\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR+\u0010L\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bI\u00107\u001a\u0004\bJ\u0010\u001e\"\u0004\bK\u0010 R+\u0010S\u001a\u00020M2\u0006\u00105\u001a\u00020M8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bN\u00107\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR/\u0010Z\u001a\u0004\u0018\u00010T2\b\u00105\u001a\u0004\u0018\u00010T8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bU\u00107\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR/\u0010a\u001a\u0004\u0018\u00010[2\b\u00105\u001a\u0004\u0018\u00010[8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u00107\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R*\u0010i\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR$\u0010q\u001a\u0004\u0018\u00010j8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR+\u0010s\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\br\u00107\u001a\u0004\bs\u0010\u001e\"\u0004\bt\u0010 R\"\u0010|\u001a\u00020u8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bv\u0010w\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{¨\u0006}"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotation;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/data/Barcode;)V", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "barcodeLocation", "highlightViewLocation", "Landroid/view/View;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "update", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Landroid/view/View;)V", "createView", "()Landroid/view/View;", "", "tipColor$scandit_barcode_capture", "()I", "tipColor", "b", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "", "c", "Z", "getRecreateBody$scandit_barcode_capture", "()Z", "setRecreateBody$scandit_barcode_capture", "(Z)V", "getRecreateBody$scandit_barcode_capture$annotations", "()V", "recreateBody", "d", "getRecreateCommon$scandit_barcode_capture", "setRecreateCommon$scandit_barcode_capture", "getRecreateCommon$scandit_barcode_capture$annotations", "recreateCommon", "e", "getRecreateHeader$scandit_barcode_capture", "setRecreateHeader$scandit_barcode_capture", "getRecreateHeader$scandit_barcode_capture$annotations", "recreateHeader", "f", "getRecreateFooter$scandit_barcode_capture", "setRecreateFooter$scandit_barcode_capture", "getRecreateFooter$scandit_barcode_capture$annotations", "recreateFooter", "", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationBodyComponent;", "<set-?>", "g", "Lkotlin/properties/ReadWriteProperty;", "getBody", "()Ljava/util/List;", "setBody", "(Ljava/util/List;)V", SentryLogEvent.JsonKeys.BODY, "h", "getBackgroundColor", "setBackgroundColor", "(I)V", "backgroundColor", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;", "i", "getWidth", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;", "setWidth", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;)V", "width", "j", "getHasTip", "setHasTip", "hasTip", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationAnchor;", "k", "getAnchor", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationAnchor;", "setAnchor", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationAnchor;)V", "anchor", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;", "l", "getHeader", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;", "setHeader", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;)V", "header", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationFooter;", "m", "getFooter", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationFooter;", "setFooter", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationFooter;)V", "footer", "Lkotlin/Function0;", "o", "Lkotlin/jvm/functions/Function0;", "getOnAnnotationOrElementTap$scandit_barcode_capture", "()Lkotlin/jvm/functions/Function0;", "setOnAnnotationOrElementTap$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "onAnnotationOrElementTap", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationListener;", "p", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationListener;", "getListener", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationListener;", "setListener", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "q", "isEntireAnnotationTappable", "setEntireAnnotationTappable", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "r", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "getAnnotationTrigger", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "setAnnotationTrigger", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;)V", "annotationTrigger", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeArInfoAnnotation implements BarcodeArAnnotation {
    static final /* synthetic */ KProperty[] w = {a.a(BarcodeArInfoAnnotation.class, SentryLogEvent.JsonKeys.BODY, "getBody()Ljava/util/List;", 0), a.a(BarcodeArInfoAnnotation.class, "backgroundColor", "getBackgroundColor()I", 0), a.a(BarcodeArInfoAnnotation.class, "width", "getWidth()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;", 0), a.a(BarcodeArInfoAnnotation.class, "hasTip", "getHasTip()Z", 0), a.a(BarcodeArInfoAnnotation.class, "anchor", "getAnchor()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationAnchor;", 0), a.a(BarcodeArInfoAnnotation.class, "header", "getHeader()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationHeader;", 0), a.a(BarcodeArInfoAnnotation.class, "footer", "getFooter()Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationFooter;", 0), a.a(BarcodeArInfoAnnotation.class, "isEntireAnnotationTappable", "isEntireAnnotationTappable()Z", 0)};
    private final Context a;

    /* renamed from: b, reason: from kotlin metadata */
    private final Barcode barcode;

    /* renamed from: c, reason: from kotlin metadata */
    private boolean recreateBody;

    /* renamed from: d, reason: from kotlin metadata */
    private boolean recreateCommon;

    /* renamed from: e, reason: from kotlin metadata */
    private boolean recreateHeader;

    /* renamed from: f, reason: from kotlin metadata */
    private boolean recreateFooter;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$1 g;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$2 h;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$3 i;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$4 j;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$5 k;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$6 l;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$7 m;
    private final com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.o n;

    /* renamed from: o, reason: from kotlin metadata */
    private /* synthetic */ Function0 onAnnotationOrElementTap;

    /* renamed from: p, reason: from kotlin metadata */
    private BarcodeArInfoAnnotationListener listener;
    private final BarcodeArInfoAnnotation$special$$inlined$distinctObservable$8 q;

    /* renamed from: r, reason: from kotlin metadata */
    private BarcodeArAnnotationTrigger annotationTrigger;
    private final i s;
    private final l t;
    private final k u;
    private final j v;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BarcodeArInfoAnnotationAnchor.values().length];
            try {
                iArr[BarcodeArInfoAnnotationAnchor.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BarcodeArInfoAnnotationAnchor.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$7] */
    /* JADX WARN: Type inference failed for: r2v9, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$6] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$2] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$3] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$4] */
    /* JADX WARN: Type inference failed for: r3v6, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$5] */
    /* JADX WARN: Type inference failed for: r3v8, types: [com.scandit.datacapture.barcode.ar.ui.annotations.n] */
    /* JADX WARN: Type inference failed for: r3v9, types: [com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$8] */
    public BarcodeArInfoAnnotation(Context context, Barcode barcode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.a = context;
        this.barcode = barcode;
        this.recreateCommon = true;
        final List emptyList = CollectionsKt.emptyList();
        Delegates delegates = Delegates.INSTANCE;
        this.g = new ObservableProperty<List<? extends BarcodeArInfoAnnotationBodyComponent>>(emptyList) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, List<? extends BarcodeArInfoAnnotationBodyComponent> oldValue, List<? extends BarcodeArInfoAnnotationBodyComponent> newValue) {
                Function0 function0;
                Function0<Unit> function02;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                function0 = this.s;
                function0.invoke();
                for (BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent : newValue) {
                    function02 = this.s;
                    barcodeArInfoAnnotationBodyComponent.setPropertyChangedListener$scandit_barcode_capture(function02);
                }
            }
        };
        final Integer valueOf = Integer.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBackgroundColor());
        this.h = new ObservableProperty<Integer>(valueOf) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Function0 function0;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                newValue.intValue();
                function0 = this.s;
                function0.invoke();
            }
        };
        final BarcodeArInfoAnnotationWidthPreset defaultInfoAnnotationWidth = BarcodeArViewDefaults.getDefaultInfoAnnotationWidth();
        this.i = new ObservableProperty<BarcodeArInfoAnnotationWidthPreset>(defaultInfoAnnotationWidth) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, BarcodeArInfoAnnotationWidthPreset oldValue, BarcodeArInfoAnnotationWidthPreset newValue) {
                Function0 function0;
                Function0 function02;
                Function0 function03;
                Function0 function04;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                function0 = this.v;
                function0.invoke();
                function02 = this.s;
                function02.invoke();
                function03 = this.t;
                function03.invoke();
                function04 = this.u;
                function04.invoke();
            }
        };
        final Boolean valueOf2 = Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHasTip());
        this.j = new ObservableProperty<Boolean>(valueOf2) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Function0 function0;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                newValue.getClass();
                function0 = this.v;
                function0.invoke();
            }
        };
        final BarcodeArInfoAnnotationAnchor defaultInfoAnnotationAnchor = BarcodeArViewDefaults.getDefaultInfoAnnotationAnchor();
        this.k = new ObservableProperty<BarcodeArInfoAnnotationAnchor>(defaultInfoAnnotationAnchor) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, BarcodeArInfoAnnotationAnchor oldValue, BarcodeArInfoAnnotationAnchor newValue) {
                Function0 function0;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                function0 = this.v;
                function0.invoke();
            }
        };
        final Object obj = null;
        this.l = new ObservableProperty<BarcodeArInfoAnnotationHeader>(obj) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, BarcodeArInfoAnnotationHeader oldValue, BarcodeArInfoAnnotationHeader newValue) {
                Function0 function0;
                Function0<Unit> function02;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationHeader barcodeArInfoAnnotationHeader = newValue;
                function0 = this.t;
                function0.invoke();
                if (barcodeArInfoAnnotationHeader != null) {
                    function02 = this.t;
                    barcodeArInfoAnnotationHeader.setPropertyChangedListener$scandit_barcode_capture(function02);
                }
            }
        };
        this.m = new ObservableProperty<BarcodeArInfoAnnotationFooter>(obj) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, BarcodeArInfoAnnotationFooter oldValue, BarcodeArInfoAnnotationFooter newValue) {
                Function0 function0;
                Function0<Unit> function02;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BarcodeArInfoAnnotationFooter barcodeArInfoAnnotationFooter = newValue;
                function0 = this.u;
                function0.invoke();
                if (barcodeArInfoAnnotationFooter != null) {
                    function02 = this.u;
                    barcodeArInfoAnnotationFooter.setPropertyChangedListener$scandit_barcode_capture(function02);
                }
            }
        };
        this.n = new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.o(new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.n
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((BarcodeArInfoAnnotation) this.receiver).getListener();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj2) {
                ((BarcodeArInfoAnnotation) this.receiver).setListener((BarcodeArInfoAnnotationListener) obj2);
            }
        }, new o(this));
        final Boolean valueOf3 = Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationEntireAnnotationTappable());
        this.q = new ObservableProperty<Boolean>(valueOf3) { // from class: com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation$special$$inlined$distinctObservable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Function0 function0;
                Function0 function02;
                Function0 function03;
                Function0 function04;
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                newValue.getClass();
                function0 = this.t;
                function0.invoke();
                function02 = this.u;
                function02.invoke();
                function03 = this.s;
                function03.invoke();
                function04 = this.v;
                function04.invoke();
            }
        };
        this.annotationTrigger = BarcodeArViewDefaults.getDefaultInfoAnnotationTrigger();
        this.s = new i(this);
        this.t = new l(this);
        this.u = new k(this);
        this.v = new j(this);
    }

    public static final void access$bodyElementIconTapped(BarcodeArInfoAnnotation barcodeArInfoAnnotation, boolean z, int i, BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent) {
        if (z) {
            barcodeArInfoAnnotation.n.onInfoAnnotationLeftIconTapped(barcodeArInfoAnnotation, barcodeArInfoAnnotationBodyComponent, i);
        } else {
            barcodeArInfoAnnotation.n.onInfoAnnotationRightIconTapped(barcodeArInfoAnnotation, barcodeArInfoAnnotationBodyComponent, i);
        }
    }

    public static final BarcodeArHeaderFooterElementDataHolder access$dataHolderForCurrentFooter(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        BarcodeArInfoAnnotationFooter footer = barcodeArInfoAnnotation.getFooter();
        if (footer == null) {
            return null;
        }
        return new BarcodeArHeaderFooterElementDataHolder(footer.getBackgroundColor(), footer.getIcon(), footer.getText(), footer.getTypeface(), footer.getTextSize(), footer.getTextColor(), barcodeArInfoAnnotation.isEntireAnnotationTappable(), barcodeArInfoAnnotation.getWidth(), false, new g(barcodeArInfoAnnotation));
    }

    public static final BarcodeArHeaderFooterElementDataHolder access$dataHolderForCurrentHeader(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        BarcodeArInfoAnnotationHeader header = barcodeArInfoAnnotation.getHeader();
        if (header == null) {
            return null;
        }
        return new BarcodeArHeaderFooterElementDataHolder(header.getBackgroundColor(), header.getIcon(), header.getText(), header.getTypeface(), header.getTextSize(), header.getTextColor(), barcodeArInfoAnnotation.isEntireAnnotationTappable(), barcodeArInfoAnnotation.getWidth(), true, new h(barcodeArInfoAnnotation));
    }

    public static final BarcodeArInfoAnnotationDataHolder access$getData(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        return new BarcodeArInfoAnnotationDataHolder(barcodeArInfoAnnotation.barcode.getData(), barcodeArInfoAnnotation.getWidth(), barcodeArInfoAnnotation.getAnchor(), barcodeArInfoAnnotation.getHasTip(), barcodeArInfoAnnotation.tipColor$scandit_barcode_capture(), barcodeArInfoAnnotation.isEntireAnnotationTappable(), new m(barcodeArInfoAnnotation));
    }

    public static final void access$headerOrFooterTapped(BarcodeArInfoAnnotation barcodeArInfoAnnotation, boolean z) {
        if (z) {
            barcodeArInfoAnnotation.n.onInfoAnnotationHeaderTapped(barcodeArInfoAnnotation);
        } else {
            barcodeArInfoAnnotation.n.onInfoAnnotationFooterTapped(barcodeArInfoAnnotation);
        }
    }

    public static /* synthetic */ void getRecreateBody$scandit_barcode_capture$annotations() {
    }

    public static /* synthetic */ void getRecreateCommon$scandit_barcode_capture$annotations() {
    }

    public static /* synthetic */ void getRecreateFooter$scandit_barcode_capture$annotations() {
    }

    public static /* synthetic */ void getRecreateHeader$scandit_barcode_capture$annotations() {
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public View createView() {
        Context context = this.a;
        b bVar = new b(this);
        Context context2 = this.a;
        d bodyProvider = new d(this);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(bodyProvider, "bodyProvider");
        C0294b c0294b = new C0294b(context2, bodyProvider);
        Context context3 = this.a;
        e dataFactory = new e(this);
        Intrinsics.checkNotNullParameter(context3, "context");
        Intrinsics.checkNotNullParameter(dataFactory, "dataFactory");
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.q qVar = new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.q(context3, dataFactory);
        Context context4 = this.a;
        f dataFactory2 = new f(this);
        Intrinsics.checkNotNullParameter(context4, "context");
        Intrinsics.checkNotNullParameter(dataFactory2, "dataFactory");
        E e = new E(context, bVar, c0294b, qVar, new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.q(context4, dataFactory2));
        e.a(this.recreateBody, this.recreateHeader, this.recreateFooter, this.recreateCommon);
        this.recreateBody = false;
        this.recreateHeader = false;
        this.recreateFooter = false;
        this.recreateCommon = false;
        return e;
    }

    public final BarcodeArInfoAnnotationAnchor getAnchor() {
        return getValue(this, w[4]);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public BarcodeArAnnotationTrigger getAnnotationTrigger() {
        return this.annotationTrigger;
    }

    public final int getBackgroundColor() {
        return getValue(this, w[1]).intValue();
    }

    public final Barcode getBarcode() {
        return this.barcode;
    }

    public final List<BarcodeArInfoAnnotationBodyComponent> getBody() {
        return (List) getValue(this, w[0]);
    }

    public final BarcodeArInfoAnnotationFooter getFooter() {
        return getValue(this, w[6]);
    }

    public final boolean getHasTip() {
        return getValue(this, w[3]).booleanValue();
    }

    public final BarcodeArInfoAnnotationHeader getHeader() {
        return getValue(this, w[5]);
    }

    public final BarcodeArInfoAnnotationListener getListener() {
        return this.listener;
    }

    public final Function0<Unit> getOnAnnotationOrElementTap$scandit_barcode_capture() {
        return this.onAnnotationOrElementTap;
    }

    /* renamed from: getRecreateBody$scandit_barcode_capture, reason: from getter */
    public final boolean getRecreateBody() {
        return this.recreateBody;
    }

    /* renamed from: getRecreateCommon$scandit_barcode_capture, reason: from getter */
    public final boolean getRecreateCommon() {
        return this.recreateCommon;
    }

    /* renamed from: getRecreateFooter$scandit_barcode_capture, reason: from getter */
    public final boolean getRecreateFooter() {
        return this.recreateFooter;
    }

    /* renamed from: getRecreateHeader$scandit_barcode_capture, reason: from getter */
    public final boolean getRecreateHeader() {
        return this.recreateHeader;
    }

    public final BarcodeArInfoAnnotationWidthPreset getWidth() {
        return getValue(this, w[2]);
    }

    public final boolean isEntireAnnotationTappable() {
        return getValue(this, w[7]).booleanValue();
    }

    public final void setAnchor(BarcodeArInfoAnnotationAnchor barcodeArInfoAnnotationAnchor) {
        Intrinsics.checkNotNullParameter(barcodeArInfoAnnotationAnchor, "<set-?>");
        setValue(this, w[4], barcodeArInfoAnnotationAnchor);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void setAnnotationTrigger(BarcodeArAnnotationTrigger barcodeArAnnotationTrigger) {
        Intrinsics.checkNotNullParameter(barcodeArAnnotationTrigger, "<set-?>");
        this.annotationTrigger = barcodeArAnnotationTrigger;
    }

    public final void setBackgroundColor(int i) {
        setValue(this, w[1], Integer.valueOf(i));
    }

    public final void setBody(List<BarcodeArInfoAnnotationBodyComponent> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        setValue(this, w[0], list);
    }

    public final void setEntireAnnotationTappable(boolean z) {
        setValue(this, w[7], Boolean.valueOf(z));
    }

    public final void setFooter(BarcodeArInfoAnnotationFooter barcodeArInfoAnnotationFooter) {
        setValue(this, w[6], barcodeArInfoAnnotationFooter);
    }

    public final void setHasTip(boolean z) {
        setValue(this, w[3], Boolean.valueOf(z));
    }

    public final void setHeader(BarcodeArInfoAnnotationHeader barcodeArInfoAnnotationHeader) {
        setValue(this, w[5], barcodeArInfoAnnotationHeader);
    }

    public final void setListener(BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener) {
        this.listener = barcodeArInfoAnnotationListener;
    }

    public final void setOnAnnotationOrElementTap$scandit_barcode_capture(Function0<Unit> function0) {
        this.onAnnotationOrElementTap = function0;
    }

    public final void setRecreateBody$scandit_barcode_capture(boolean z) {
        this.recreateBody = z;
    }

    public final void setRecreateCommon$scandit_barcode_capture(boolean z) {
        this.recreateCommon = z;
    }

    public final void setRecreateFooter$scandit_barcode_capture(boolean z) {
        this.recreateFooter = z;
    }

    public final void setRecreateHeader$scandit_barcode_capture(boolean z) {
        this.recreateHeader = z;
    }

    public final void setWidth(BarcodeArInfoAnnotationWidthPreset barcodeArInfoAnnotationWidthPreset) {
        Intrinsics.checkNotNullParameter(barcodeArInfoAnnotationWidthPreset, "<set-?>");
        setValue(this, w[2], barcodeArInfoAnnotationWidthPreset);
    }

    public final int tipColor$scandit_barcode_capture() {
        int i = WhenMappings.$EnumSwitchMapping$0[getAnchor().ordinal()];
        if (i == 1) {
            BarcodeArInfoAnnotationHeader header = getHeader();
            if (header != null) {
                return header.getBackgroundColor();
            }
            BarcodeArInfoAnnotationFooter footer = getFooter();
            return (footer == null || !getBody().isEmpty()) ? getBackgroundColor() : footer.getBackgroundColor();
        }
        if (i != 2) {
            return getBackgroundColor();
        }
        BarcodeArInfoAnnotationFooter footer2 = getFooter();
        if (footer2 != null) {
            return footer2.getBackgroundColor();
        }
        BarcodeArInfoAnnotationHeader header2 = getHeader();
        return (header2 == null || !getBody().isEmpty()) ? getBackgroundColor() : header2.getBackgroundColor();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void update(Quadrilateral barcodeLocation, Quadrilateral highlightViewLocation, View view) {
        int i;
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        Intrinsics.checkNotNullParameter(view, "view");
        E e = view instanceof E ? (E) view : null;
        if (e != null) {
            e.a(this.recreateBody, this.recreateHeader, this.recreateFooter, this.recreateCommon);
            if (highlightViewLocation != null) {
                barcodeLocation = highlightViewLocation;
            }
            Quadrilateral quadrilateral = QuadrilateralUtilsKt.orderVerticesByPosition(barcodeLocation);
            BarcodeArInfoAnnotationAnchor anchor = getAnchor();
            Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
            Intrinsics.checkNotNullParameter(anchor, "anchor");
            float width = e.getWidth();
            float height = e.getHeight();
            if (width > 0.0f && height > 0.0f) {
                e.o = new Size2(width, height);
                e.m = quadrilateral;
                e.n = anchor;
                ViewParent parent = e.getParent();
                FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
                if (frameLayout != null) {
                    e.p.a(e, frameLayout);
                    com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.c.a(e.f, com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor));
                    com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a aVar = e.f;
                    Anchor anchor2 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor);
                    Intrinsics.checkNotNullParameter(aVar, "<this>");
                    Intrinsics.checkNotNullParameter(anchor2, "anchor");
                    int i2 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.b.a[anchor2.ordinal()];
                    if (i2 == 1) {
                        i = 49;
                    } else if (i2 == 2) {
                        i = 81;
                    } else if (i2 == 3) {
                        i = 8388627;
                    } else {
                        if (i2 != 4) {
                            throw new IllegalArgumentException("Unsupported anchor: " + anchor2);
                        }
                        i = 8388629;
                    }
                    ViewGroup.LayoutParams layoutParams = aVar.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                    layoutParams2.gravity = i;
                    aVar.setLayoutParams(layoutParams2);
                    com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.c.b(e.f, com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor));
                }
            }
            this.recreateBody = false;
            this.recreateHeader = false;
            this.recreateFooter = false;
            this.recreateCommon = false;
        }
    }
}
