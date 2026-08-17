package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.internal.sdk.ui.animation.AnimationSpec;
import com.scandit.datacapture.core.internal.sdk.ui.animation.AnimationSpecKt;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001:\u0001HB'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0012¢\u0006\u0004\b \u0010!J>\u0010*\u001a\u00020\r2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\r0\"2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0(¢\u0006\u0004\b*\u0010+JX\u00100\u001a\u00020\r2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00062\b\b\u0002\u0010/\u001a\u00020\u00062!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\r0\"2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0(¢\u0006\u0004\b0\u00101J#\u00102\u001a\u00020\r2\u0006\u0010/\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0(¢\u0006\u0004\b2\u00103R*\u0010:\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010B\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0014\u0010E\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0014\u0010G\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010D¨\u0006I"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/StackedCardsView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "newData", "", "setData", "(Ljava/util/List;)V", "Landroid/view/MotionEvent;", "ev", "", "onInterceptTouchEvent", "(Landroid/view/MotionEvent;)Z", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", "childCount", "drawingPosition", "getChildDrawingOrder", "(II)I", "collapsed", "setCollapsed", "(Z)V", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "input", "onAnimationUpdate", "Lkotlin/Function0;", "onAnimationEnd", "expand", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "Lkotlin/ranges/IntRange;", "visibleIndices", "offsetTop", "indexToShowOnTop", "collapse", "(Lkotlin/ranges/IntRange;IILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "animateMoveItemOnTop", "(ILkotlin/jvm/functions/Function0;)V", "a", "Lkotlin/jvm/functions/Function0;", "getOnScrolledUp", "()Lkotlin/jvm/functions/Function0;", "setOnScrolledUp", "(Lkotlin/jvm/functions/Function0;)V", "onScrolledUp", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "e", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "getCardEventListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "setCardEventListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;)V", "cardEventListener", "getTargetOffsetWhenExpanding$scandit_capture_core", "()I", "targetOffsetWhenExpanding", "getTargetOffsetWhenCollapsing$scandit_capture_core", "targetOffsetWhenCollapsing", "com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/z", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class StackedCardsView extends FrameLayout {

    @Deprecated
    public static final float ALPHA_STEP = 0.25f;

    @Deprecated
    public static final long ANIMATION_DURATION = 500;

    @Deprecated
    public static final float ELEVATION_STEP = 0.75f;

    @Deprecated
    public static final int MAX_CARD_COUNT = 3;

    @Deprecated
    public static final float SCALE_OUT_OF_BOUNDS_CARDS = 0.85f;

    @Deprecated
    public static final float SCALE_STEP = 0.05f;

    /* renamed from: a, reason: from kotlin metadata */
    private Function0 onScrolledUp;
    private final ArrayList b;
    private Animator c;
    private boolean d;

    /* renamed from: e, reason: from kotlin metadata */
    private CardsCarouselView.Listener cardEventListener;
    private final z f;
    private final GestureDetector g;
    private int h;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StackedCardsView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final int a() {
        CardData cardData = (CardData) CollectionsKt.getOrNull(this.b, 0);
        if (cardData != null) {
            return cardData.getCardHeight();
        }
        return 0;
    }

    public static /* synthetic */ void collapse$default(StackedCardsView stackedCardsView, IntRange intRange, int i, int i2, Function1 function1, Function0 function0, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        stackedCardsView.collapse(intRange, i, i2, function1, function0);
    }

    public final void animateMoveItemOnTop(int indexToShowOnTop, Function0<Unit> onAnimationEnd) {
        Intrinsics.checkNotNullParameter(onAnimationEnd, "onAnimationEnd");
        if (this.d) {
            if (indexToShowOnTop == 0) {
                onAnimationEnd.invoke();
                return;
            }
            int coerceAtMost = RangesKt.coerceAtMost(this.b.size(), 3);
            CardView a = a(0);
            a.setCardData((CardData) this.b.get(indexToShowOnTop));
            AnimationSpecKt.applySpec(a, new AnimationSpec(0.0f, CardView.INSTANCE.getELEVATION$scandit_capture_core(), 1.0f, (getHeight() - (a() * 2)) - (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() * (coerceAtMost + 1))));
            int size = this.b.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                CardView a2 = a(i2);
                a2.setCardData((CardData) this.b.get(i));
                AnimationSpecKt.applySpec(a2, a(i, coerceAtMost));
                i = i2;
            }
            IntRange until = RangesKt.until(0, getChildCount());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator<Integer> it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                View childAt = getChildAt(nextInt);
                Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardView");
                arrayList.add(AnimationSpecKt.interpolateWith((CardView) childAt, a(nextInt, coerceAtMost)));
            }
            a(arrayList, A.a, onAnimationEnd);
        }
    }

    public final void collapse(IntRange visibleIndices, int offsetTop, int indexToShowOnTop, Function1<? super Float, Unit> onAnimationUpdate, Function0<Unit> onAnimationEnd) {
        Intrinsics.checkNotNullParameter(visibleIndices, "visibleIndices");
        Intrinsics.checkNotNullParameter(onAnimationUpdate, "onAnimationUpdate");
        Intrinsics.checkNotNullParameter(onAnimationEnd, "onAnimationEnd");
        if (this.d) {
            return;
        }
        this.d = true;
        int i = 0;
        boolean z = indexToShowOnTop != visibleIndices.getFirst();
        List mutableList = CollectionsKt.toMutableList(visibleIndices);
        if (z) {
            mutableList.add(0, Integer.valueOf(indexToShowOnTop));
        }
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        CardsCarouselView.Companion companion = CardsCarouselView.INSTANCE;
        int min = Math.min(RangesKt.coerceAtLeast((int) Math.ceil((companion.getSEPARATOR_HEIGHT$scandit_capture_core() + height) / (companion.getSEPARATOR_HEIGHT$scandit_capture_core() + a())), 0), this.b.size());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mutableList, 10));
        for (Object obj : mutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int intValue = ((Number) obj).intValue();
            CardView a = a(i);
            int i3 = z ? i - 1 : i;
            if (intValue >= this.b.size()) {
                return;
            }
            a.setCardData((CardData) this.b.get(intValue));
            AnimationSpecKt.applySpec(a, new AnimationSpec((i == 0 && z) ? 0.0f : 1.0f, CardView.INSTANCE.getELEVATION$scandit_capture_core(), 1.0f, (i3 * (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() + a())) + offsetTop));
            arrayList.add(AnimationSpecKt.interpolateWith(a, a(i, min)));
            i = i2;
            z = z;
        }
        a(arrayList, onAnimationUpdate, onAnimationEnd);
    }

    public final void expand(Function1<? super Float, Unit> onAnimationUpdate, Function0<Unit> onAnimationEnd) {
        Intrinsics.checkNotNullParameter(onAnimationUpdate, "onAnimationUpdate");
        Intrinsics.checkNotNullParameter(onAnimationEnd, "onAnimationEnd");
        if (this.d) {
            this.d = false;
            int targetOffsetWhenExpanding$scandit_capture_core = getTargetOffsetWhenExpanding$scandit_capture_core();
            IntRange until = RangesKt.until(0, getChildCount());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator<Integer> it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                View childAt = getChildAt(nextInt);
                Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardView");
                getChildCount();
                arrayList.add(AnimationSpecKt.interpolateWith((CardView) childAt, new AnimationSpec(1.0f, CardView.INSTANCE.getELEVATION$scandit_capture_core(), 1.0f, (nextInt * (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() + a())) + targetOffsetWhenExpanding$scandit_capture_core)));
            }
            a(arrayList, onAnimationUpdate, onAnimationEnd);
        }
    }

    public final CardsCarouselView.Listener getCardEventListener() {
        return this.cardEventListener;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int drawingPosition) {
        return (childCount - drawingPosition) - 1;
    }

    public final Function0<Unit> getOnScrolledUp() {
        return this.onScrolledUp;
    }

    public final int getTargetOffsetWhenCollapsing$scandit_capture_core() {
        this.h = Math.max(this.h, getHeight());
        return Math.max(0, (((this.h - a()) - (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() * (RangesKt.coerceAtMost(this.b.size(), 3) - 1))) - getPaddingBottom()) - getPaddingTop());
    }

    public final int getTargetOffsetWhenExpanding$scandit_capture_core() {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        CardsCarouselView.Companion companion = CardsCarouselView.INSTANCE;
        return Math.max(0, (companion.getSEPARATOR_HEIGHT$scandit_capture_core() + height) - ((companion.getSEPARATOR_HEIGHT$scandit_capture_core() + a()) * this.b.size()));
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        return this.g.onTouchEvent(ev);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        a(this.b);
    }

    public final void setCardEventListener(CardsCarouselView.Listener listener) {
        this.cardEventListener = listener;
    }

    public final void setCollapsed(boolean collapsed) {
        this.d = collapsed;
        a(this.b);
    }

    public final void setData(List<? extends CardData> newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        this.b.clear();
        this.b.addAll(newData);
        a(newData);
    }

    public final void setOnScrolledUp(Function0<Unit> function0) {
        this.onScrolledUp = function0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StackedCardsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(List list) {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        CardsCarouselView.Companion companion = CardsCarouselView.INSTANCE;
        int min = Math.min(RangesKt.coerceAtLeast((int) Math.ceil((companion.getSEPARATOR_HEIGHT$scandit_capture_core() + height) / (companion.getSEPARATOR_HEIGHT$scandit_capture_core() + a())), 0), list.size());
        int childCount = getChildCount() - min;
        if (childCount > 0) {
            removeViews(min, childCount);
        }
        boolean z = this.d;
        for (int i = 0; i < min; i++) {
            CardView a = a(i);
            AnimationSpecKt.applySpec(a, z ? a(i, min) : new AnimationSpec(1.0f, CardView.INSTANCE.getELEVATION$scandit_capture_core(), 1.0f, (i * (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() + a())) + 0));
            a.setCardData((CardData) list.get(i));
        }
    }

    public /* synthetic */ StackedCardsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StackedCardsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setClipChildren(false);
        setClipToPadding(false);
        setChildrenDrawingOrderEnabled(true);
        CardsCarouselView.Companion companion = CardsCarouselView.INSTANCE;
        setPadding(companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core());
        this.b = new ArrayList();
        this.d = true;
        this.f = new z(this);
        this.g = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.StackedCardsView$gestureDetector$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Function0<Unit> onScrolledUp;
                Intrinsics.checkNotNullParameter(e2, "e2");
                boolean z = distanceY > 0.0f;
                StackedCardsView stackedCardsView = StackedCardsView.this;
                if (z && (onScrolledUp = stackedCardsView.getOnScrolledUp()) != null) {
                    onScrolledUp.invoke();
                }
                return z;
            }
        });
    }

    private final void a(final ArrayList arrayList, final Function1 function1, final Function0 function0) {
        Animator animator = this.c;
        if (animator != null) {
            animator.cancel();
        }
        this.c = null;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(500L);
        ofFloat.setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.StackedCardsView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                StackedCardsView.a(arrayList, function1, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.StackedCardsView$animateChanges$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                StackedCardsView.this.c = null;
                function0.invoke();
                r1.a(StackedCardsView.this.b);
            }
        });
        ofFloat.start();
        this.c = ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(Collection animations, Function1 onAnimationUpdate, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(animations, "$animations");
        Intrinsics.checkNotNullParameter(onAnimationUpdate, "$onAnimationUpdate");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        Float f = (Float) animatedValue;
        f.floatValue();
        Iterator it2 = animations.iterator();
        while (it2.hasNext()) {
            ((Function1) it2.next()).invoke(f);
        }
        onAnimationUpdate.invoke(f);
    }

    private final AnimationSpec a(int i, int i2) {
        int min = Math.min(i2, 3);
        if (i < min) {
            int i3 = (min - 1) - i;
            float f = i;
            return new AnimationSpec(1.0f - (0.25f * f), CardView.INSTANCE.getELEVATION$scandit_capture_core() - (0.75f * f), 1.0f - (f * 0.05f), (((getHeight() - a()) - (CardsCarouselView.INSTANCE.getSEPARATOR_HEIGHT$scandit_capture_core() * i3)) - getPaddingBottom()) - getPaddingTop());
        }
        return new AnimationSpec(0.0f, 0.0f, 0.85f, (getHeight() - a()) - getPaddingBottom());
    }

    private final CardView a(int i) {
        CardSubView textCardView;
        if (i < getChildCount()) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardView");
            return (CardView) childAt;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        CardSubView.Companion companion = CardSubView.INSTANCE;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        Class<?> cls = CollectionsKt.first((List) this.b).getClass();
        if (Intrinsics.areEqual(cls, EditableCardData.class)) {
            textCardView = new EditableCardView(context2, false, 2, null);
        } else {
            if (!Intrinsics.areEqual(cls, TextCardData.class)) {
                throw new IllegalStateException("Unsupported configuration type".toString());
            }
            textCardView = new TextCardView(context2);
        }
        textCardView.setListener(this.f);
        CardView cardView = new CardView(context, textCardView);
        cardView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        cardView.setClickable(true);
        addView(cardView, Math.min(i, getChildCount()));
        return cardView;
    }
}
