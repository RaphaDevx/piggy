package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.internal.sdk.ui.animation.AnimationSpec;
import com.scandit.datacapture.core.internal.sdk.ui.animation.AnimationSpecKt;
import io.sentry.SentryEnvelopeItemHeader;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 =2\u00020\u0001:\u0005=>?@AB'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\r¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0006¢\u0006\u0004\b\"\u0010#R$\u0010+\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u00103\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R*\u0010<\u001a\u0002042\u0006\u00105\u001a\u0002048\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006B"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView;", "Landroid/widget/RelativeLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "newData", "", "setData", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "", "collapsed", "animated", "setCollapsed", "(ZZ)V", "", "itemId", "moveItemOnTopAndCollapse", "(Ljava/lang/String;)V", "prepareButtonBar", "()V", "", "text", "setCollapseButtonText", "(Ljava/lang/CharSequence;)V", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "setCardsFadingEdgeLength", "(I)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$UiListener;", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$UiListener;", "getUiListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$UiListener;", "setUiListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$UiListener;)V", "uiListener", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "b", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "getListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "setListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarSettings;", "value", "d", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarSettings;", "getButtonBarSettings", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarSettings;", "setButtonBarSettings", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarSettings;)V", "buttonBarSettings", "Companion", "com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/g", "Listener", "com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/h", "UiListener", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class CardsCarouselView extends RelativeLayout {
    public static final int COLLAPSE_BUTTON_TEXT_COLOR = -868399022;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy n = LazyKt.lazy(d.a);
    private static final Lazy o = LazyKt.lazy(c.a);
    private static final Lazy p = LazyKt.lazy(e.a);
    private static final Lazy q = LazyKt.lazy(f.a);

    /* renamed from: a, reason: from kotlin metadata */
    private UiListener uiListener;

    /* renamed from: b, reason: from kotlin metadata */
    private Listener listener;
    private final ArrayList c;

    /* renamed from: d, reason: from kotlin metadata */
    private ButtonBarSettings buttonBarSettings;
    private final TextView e;
    private final ImageButton f;
    private final ImageButton g;
    private final ImageButton h;
    private final RelativeLayout i;
    private final ExpandedCardsView j;
    private final StackedCardsView k;
    private boolean l;
    private boolean m;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001R\u001b\u0010\u0007\u001a\u00020\u00028@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Companion;", "", "", "SEPARATOR_HEIGHT$delegate", "Lkotlin/Lazy;", "getSEPARATOR_HEIGHT$scandit_capture_core", "()I", "SEPARATOR_HEIGHT", "COLLAPSE_BUTTON_TEXT_COLOR", "I", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final int access$getBUTTON_BAR_HEIGHT(Companion companion) {
            companion.getClass();
            return ((Number) CardsCarouselView.o.getValue()).intValue();
        }

        public static final int access$getCOLLAPSE_BUTTON_HEIGHT(Companion companion) {
            companion.getClass();
            return ((Number) CardsCarouselView.n.getValue()).intValue();
        }

        public static final int access$getCOLLAPSE_BUTTON_PADDING(Companion companion) {
            companion.getClass();
            return ((Number) CardsCarouselView.p.getValue()).intValue();
        }

        public final int getSEPARATOR_HEIGHT$scandit_capture_core() {
            return ((Number) CardsCarouselView.q.getValue()).intValue();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0007J+\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "", "iconResForTextChange", "", "oldText", "", "newText", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Integer;", "onCardIconTapped", "", "cardData", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "iconRes", "extraData", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;Ljava/lang/Integer;Ljava/lang/String;)V", "onCardTapped", "onEditableFieldFinishedEditing", "editText", "Landroid/widget/EditText;", "onEditableFieldTapped", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Listener {
        Integer iconResForTextChange(String oldText, String newText);

        void onCardIconTapped(CardData cardData, Integer iconRes, String extraData);

        void onCardTapped(CardData cardData);

        void onEditableFieldFinishedEditing(CardData cardData, EditText editText);

        void onEditableFieldTapped(CardData cardData, EditText editText);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$UiListener;", "", "onAcceptScanButtonTapped", "", "onCancelScanButtonTapped", "onRestartScanButtonTapped", "onSwipedDownWhileExpanded", "onSwipedUpWhileCollapsed", "onTappedWhileCollapsed", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface UiListener {
        void onAcceptScanButtonTapped();

        void onCancelScanButtonTapped();

        void onRestartScanButtonTapped();

        void onSwipedDownWhileExpanded();

        void onSwipedUpWhileCollapsed();

        void onTappedWhileCollapsed();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CardsCarouselView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(CardsCarouselView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiListener uiListener = this$0.uiListener;
        if (uiListener != null) {
            uiListener.onAcceptScanButtonTapped();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(CardsCarouselView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiListener uiListener = this$0.uiListener;
        if (uiListener != null) {
            uiListener.onCancelScanButtonTapped();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(CardsCarouselView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.l) {
            return;
        }
        setCollapsed$default(this$0, true, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(CardsCarouselView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiListener uiListener = this$0.uiListener;
        if (uiListener != null) {
            uiListener.onRestartScanButtonTapped();
        }
    }

    public static /* synthetic */ void setCollapsed$default(CardsCarouselView cardsCarouselView, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        cardsCarouselView.setCollapsed(z, z2);
    }

    public final ButtonBarSettings getButtonBarSettings() {
        return this.buttonBarSettings;
    }

    public final List<CardData> getData() {
        return CollectionsKt.toList(this.c);
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final UiListener getUiListener() {
        return this.uiListener;
    }

    public final void moveItemOnTopAndCollapse(String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Iterator it = this.c.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(((CardData) it.next()).getItemId(), itemId)) {
                break;
            } else {
                i++;
            }
        }
        if (i <= 0) {
            setCollapsed(true, true);
        } else if (this.l) {
            this.k.animateMoveItemOnTop(i, new q(this, itemId));
        } else {
            a(i, new p(this, itemId));
        }
    }

    public final void prepareButtonBar() {
        if (this.l) {
            this.i.setTranslationY(this.k.getTargetOffsetWhenCollapsing$scandit_capture_core());
        } else {
            this.i.setTranslationY(this.k.getTargetOffsetWhenExpanding$scandit_capture_core());
        }
    }

    public final void setButtonBarSettings(ButtonBarSettings value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.buttonBarSettings = value;
        a();
    }

    public final void setCardsFadingEdgeLength(int length) {
        this.j.setFadingEdgeLength(length);
    }

    public final void setCollapseButtonText(CharSequence text) {
        TextView textView = this.e;
        if (text == null) {
            text = getContext().getString(R.string.sc_cards_carousel_collapse_cards);
        }
        textView.setText(text);
    }

    public final void setCollapsed(boolean collapsed, boolean animated) {
        Function1<Float, Unit> interpolateWith;
        if (collapsed == this.l) {
            return;
        }
        if (collapsed) {
            this.m = true;
        }
        if (animated) {
            if (collapsed) {
                a(0, i.a);
                return;
            }
            if (this.buttonBarSettings.buttonBarVisibilityCanChange()) {
                this.i.setVisibility(this.buttonBarSettings.buttonBarVisibility(true));
                this.i.setAlpha(0.0f);
                this.i.setTranslationY(getHeight() - (((CardData) CollectionsKt.getOrNull(this.c, 0)) != null ? r0.getCardHeight() : 0));
            }
            this.j.setSelection(0);
            this.j.scrollTo(0, 0);
            float targetOffsetWhenExpanding$scandit_capture_core = this.k.getTargetOffsetWhenExpanding$scandit_capture_core();
            if (this.buttonBarSettings.buttonBarVisibilityCanChange()) {
                RelativeLayout relativeLayout = this.i;
                interpolateWith = AnimationSpecKt.interpolateWith(relativeLayout, AnimationSpec.copy$default(AnimationSpec.INSTANCE.fromView$scandit_capture_core(relativeLayout), 1.0f, 0.0f, 0.0f, targetOffsetWhenExpanding$scandit_capture_core, 6, null));
            } else {
                RelativeLayout relativeLayout2 = this.i;
                interpolateWith = AnimationSpecKt.interpolateWith(relativeLayout2, AnimationSpec.copy$default(AnimationSpec.INSTANCE.fromView$scandit_capture_core(relativeLayout2), 0.0f, 0.0f, 0.0f, targetOffsetWhenExpanding$scandit_capture_core, 7, null));
            }
            this.k.expand(new l(interpolateWith), new m(this));
            return;
        }
        if (!collapsed) {
            this.l = false;
            this.j.setSelection(0);
            this.j.scrollTo(0, 0);
            this.j.setVisibility(0);
            this.k.setVisibility(8);
            this.k.setCollapsed(false);
            return;
        }
        this.l = true;
        this.j.setVisibility(4);
        this.j.setSelection(0);
        this.j.scrollTo(0, 0);
        this.k.setVisibility(0);
        this.k.setCollapsed(true);
        if (this.buttonBarSettings.buttonBarVisibilityCanChange()) {
            this.i.setVisibility(this.buttonBarSettings.buttonBarVisibility(false));
        }
        this.i.setTranslationY(getHeight() - (((CardData) CollectionsKt.getOrNull(this.c, 0)) != null ? r7.getCardHeight() : 0));
    }

    public final void setData(List<? extends CardData> newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        this.c.clear();
        this.c.addAll(newData);
        this.k.setData(newData);
        this.j.setData(newData);
    }

    public final void setListener(Listener listener) {
        this.listener = listener;
    }

    public final void setUiListener(UiListener uiListener) {
        this.uiListener = uiListener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CardsCarouselView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void a() {
        this.e.setVisibility(!this.buttonBarSettings.getIsCollapseButtonVisible() ? 8 : 0);
        this.f.setVisibility(!this.buttonBarSettings.getIsRestartScanButtonVisible() ? 8 : 0);
        this.g.setVisibility(!this.buttonBarSettings.getIsCancelScanButtonVisible() ? 8 : 0);
        this.h.setVisibility(this.buttonBarSettings.getIsAcceptScanButtonVisible() ? 0 : 8);
        if (this.buttonBarSettings.buttonBarVisibilityCanChange()) {
            return;
        }
        this.i.setVisibility(this.buttonBarSettings.buttonBarVisibility(!this.l));
    }

    public /* synthetic */ CardsCarouselView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardsCarouselView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.c = new ArrayList();
        this.buttonBarSettings = new ButtonBarSettings(ButtonBarShowMode.WhileExpanded, true, false, false, false, 28, null);
        TextView textView = new TextView(context);
        textView.setId(R.id.sc_carousel_collapse_button);
        textView.setBackground(context.getDrawable(R.drawable.sc_collapse_button_background));
        textView.setContentDescription(context.getString(R.string.sc_cards_carousel_collapse_cards_content_description));
        textView.setText(R.string.sc_cards_carousel_collapse_cards);
        textView.setTextColor(COLLAPSE_BUTTON_TEXT_COLOR);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.sc_arrow_down), (Drawable) null, (Drawable) null, (Drawable) null);
        Companion companion = INSTANCE;
        textView.setCompoundDrawablePadding(Companion.access$getCOLLAPSE_BUTTON_PADDING(companion));
        textView.setPadding(Companion.access$getCOLLAPSE_BUTTON_PADDING(companion), 0, Companion.access$getCOLLAPSE_BUTTON_PADDING(companion), 0);
        textView.setGravity(16);
        textView.setVisibility(8);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardsCarouselView.c(CardsCarouselView.this, view);
            }
        });
        this.e = textView;
        ImageButton imageButton = new ImageButton(context);
        imageButton.setImageResource(R.drawable.sc_label_validation_restart_scan);
        imageButton.setBackground(null);
        imageButton.setVisibility(8);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardsCarouselView.d(CardsCarouselView.this, view);
            }
        });
        this.f = imageButton;
        ImageButton imageButton2 = new ImageButton(context);
        imageButton2.setImageResource(R.drawable.sc_label_validation_cancel_scan);
        imageButton2.setBackground(null);
        imageButton2.setVisibility(8);
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardsCarouselView.b(CardsCarouselView.this, view);
            }
        });
        this.g = imageButton2;
        ImageButton imageButton3 = new ImageButton(context);
        imageButton3.setImageResource(R.drawable.sc_label_validation_accept_scan);
        imageButton3.setBackground(null);
        imageButton3.setVisibility(8);
        imageButton3.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardsCarouselView.a(CardsCarouselView.this, view);
            }
        });
        this.h = imageButton3;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setId(R.id.sc_carousel_button_bar);
        relativeLayout.setVisibility(4);
        this.i = relativeLayout;
        ExpandedCardsView expandedCardsView = new ExpandedCardsView(context, null, 0, 6, null);
        expandedCardsView.setOnScrolled(new n(this, expandedCardsView));
        expandedCardsView.setOnSwipeDownFromTop(new o(this));
        expandedCardsView.setVisibility(4);
        expandedCardsView.setCardEventListener(new g(this));
        this.j = expandedCardsView;
        StackedCardsView stackedCardsView = new StackedCardsView(context, null, 0, 6, null);
        stackedCardsView.setCardEventListener(new h(this));
        stackedCardsView.setOnScrolledUp(new r(this));
        stackedCardsView.setVisibility(0);
        this.k = stackedCardsView;
        this.l = true;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, relativeLayout.getId());
        layoutParams.addRule(12);
        expandedCardsView.setLayoutParams(layoutParams);
        addView(expandedCardsView);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(3, relativeLayout.getId());
        layoutParams2.addRule(12);
        stackedCardsView.setLayoutParams(layoutParams2);
        addView(stackedCardsView);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, Companion.access$getCOLLAPSE_BUTTON_HEIGHT(companion));
        layoutParams3.addRule(12, -1);
        layoutParams3.addRule(14, -1);
        textView.setLayoutParams(layoutParams3);
        relativeLayout.addView(textView);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(20, -1);
        imageButton.setLayoutParams(layoutParams4);
        relativeLayout.addView(imageButton);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(20, -1);
        imageButton2.setLayoutParams(layoutParams5);
        relativeLayout.addView(imageButton2);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(21, -1);
        imageButton3.setLayoutParams(layoutParams6);
        relativeLayout.addView(imageButton3);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, Companion.access$getBUTTON_BAR_HEIGHT(companion));
        layoutParams7.addRule(10, -1);
        layoutParams7.addRule(14, -1);
        relativeLayout.setLayoutParams(layoutParams7);
        addView(relativeLayout);
        a();
    }

    private final void a(int i, Function0 function0) {
        AnimationSpec copy$default;
        this.j.setVisibility(4);
        this.j.setSelection(0);
        this.j.scrollTo(0, 0);
        this.k.setVisibility(0);
        int firstVisiblePosition = this.j.getFirstVisiblePosition();
        int lastVisiblePosition = this.j.getLastVisiblePosition();
        int offsetOfFirstVisiblePosition = this.j.getOffsetOfFirstVisiblePosition();
        if (this.buttonBarSettings.buttonBarVisibilityCanChange()) {
            copy$default = AnimationSpec.copy$default(AnimationSpec.INSTANCE.fromView$scandit_capture_core(this.i), 0.0f, 0.0f, 0.0f, getHeight() - (((CardData) CollectionsKt.getOrNull(this.c, 0)) != null ? r5.getCardHeight() : 0), 6, null);
        } else {
            copy$default = AnimationSpec.copy$default(AnimationSpec.INSTANCE.fromView$scandit_capture_core(this.i), 0.0f, 0.0f, 0.0f, this.k.getTargetOffsetWhenCollapsing$scandit_capture_core(), 7, null);
        }
        Function1<Float, Unit> interpolateWith = AnimationSpecKt.interpolateWith(this.i, copy$default);
        this.j.fling(0);
        this.k.collapse(new IntRange(firstVisiblePosition, lastVisiblePosition), offsetOfFirstVisiblePosition, i, new j(interpolateWith), new k(this, function0));
    }
}
