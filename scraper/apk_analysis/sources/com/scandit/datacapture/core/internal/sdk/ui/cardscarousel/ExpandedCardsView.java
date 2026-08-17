package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import com.tealium.library.DataSources;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0002DEB'\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000f\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u001b\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ1\u0010 \u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007H\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b$\u0010%R?\u00100\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u000e\u0018\u00010&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R*\u00108\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R$\u0010@\u001a\u0004\u0018\u0001098\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0011\u0010C\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010B¨\u0006F"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ExpandedCardsView;", "Landroid/widget/ListView;", "Landroid/widget/AbsListView$OnScrollListener;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "newData", "", "setData", "(Ljava/util/List;)V", "scrollX", "scrollY", "", "clampedX", "clampedY", "onOverScrolled", "(IIZZ)V", "Landroid/widget/AbsListView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "scrollState", "onScrollStateChanged", "(Landroid/widget/AbsListView;I)V", "firstVisibleItem", "visibleItemCount", "totalItemCount", "onScroll", "(Landroid/widget/AbsListView;III)V", "Landroid/view/MotionEvent;", "ev", "dispatchTouchEvent", "(Landroid/view/MotionEvent;)Z", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", TypedValues.CycleType.S_WAVE_OFFSET, "a", "Lkotlin/jvm/functions/Function1;", "getOnScrolled", "()Lkotlin/jvm/functions/Function1;", "setOnScrolled", "(Lkotlin/jvm/functions/Function1;)V", "onScrolled", "Lkotlin/Function0;", "b", "Lkotlin/jvm/functions/Function0;", "getOnSwipeDownFromTop", "()Lkotlin/jvm/functions/Function0;", "setOnSwipeDownFromTop", "(Lkotlin/jvm/functions/Function0;)V", "onSwipeDownFromTop", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "e", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "getCardEventListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "setCardEventListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;)V", "cardEventListener", "getOffsetOfFirstVisiblePosition", "()I", "offsetOfFirstVisiblePosition", "com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/w", "CardListenerForwarder", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ExpandedCardsView extends ListView implements AbsListView.OnScrollListener {

    /* renamed from: a, reason: from kotlin metadata */
    private Function1 onScrolled;

    /* renamed from: b, reason: from kotlin metadata */
    private Function0 onSwipeDownFromTop;
    private final ArrayList c;
    private final w d;

    /* renamed from: e, reason: from kotlin metadata */
    private CardsCarouselView.Listener cardEventListener;
    private final CardListenerForwarder f;
    private boolean g;
    private final ExpandedCardsView$gestureListener$1 h;
    private final Lazy i;

    public final class CardListenerForwarder implements CardsCarouselView.Listener {
        public CardListenerForwarder() {
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
        public final Integer iconResForTextChange(String oldText, String newText) {
            Intrinsics.checkNotNullParameter(oldText, "oldText");
            Intrinsics.checkNotNullParameter(newText, "newText");
            CardsCarouselView.Listener cardEventListener = ExpandedCardsView.this.getCardEventListener();
            if (cardEventListener != null) {
                return cardEventListener.iconResForTextChange(oldText, newText);
            }
            return null;
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
        public final void onCardIconTapped(CardData cardData, Integer num, String str) {
            Intrinsics.checkNotNullParameter(cardData, "cardData");
            CardsCarouselView.Listener cardEventListener = ExpandedCardsView.this.getCardEventListener();
            if (cardEventListener != null) {
                cardEventListener.onCardIconTapped(cardData, num, str);
            }
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
        public final void onCardTapped(CardData cardData) {
            Intrinsics.checkNotNullParameter(cardData, "cardData");
            CardsCarouselView.Listener cardEventListener = ExpandedCardsView.this.getCardEventListener();
            if (cardEventListener != null) {
                cardEventListener.onCardTapped(cardData);
            }
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
        public final void onEditableFieldFinishedEditing(CardData cardData, EditText editText) {
            Intrinsics.checkNotNullParameter(cardData, "cardData");
            Intrinsics.checkNotNullParameter(editText, "editText");
            CardsCarouselView.Listener cardEventListener = ExpandedCardsView.this.getCardEventListener();
            if (cardEventListener != null) {
                cardEventListener.onEditableFieldFinishedEditing(cardData, editText);
            }
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
        public final void onEditableFieldTapped(CardData cardData, EditText editText) {
            Intrinsics.checkNotNullParameter(cardData, "cardData");
            Intrinsics.checkNotNullParameter(editText, "editText");
            CardsCarouselView.Listener cardEventListener = ExpandedCardsView.this.getCardEventListener();
            if (cardEventListener != null) {
                cardEventListener.onEditableFieldTapped(cardData, editText);
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExpandedCardsView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final void access$bind(ExpandedCardsView expandedCardsView, CardView cardView, CardData cardData) {
        expandedCardsView.getClass();
        cardView.setCardData(cardData);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        ((GestureDetector) this.i.getValue()).onTouchEvent(ev);
        if (getChildCount() == 0) {
            return false;
        }
        if (getFirstVisiblePosition() <= 0) {
            View childAt = getChildAt(0);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
            if (((int) ev.getY()) < childAt.getTop()) {
                return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public final CardsCarouselView.Listener getCardEventListener() {
        return this.cardEventListener;
    }

    public final int getOffsetOfFirstVisiblePosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
        return childAt.getTop() - getPaddingTop();
    }

    public final Function1<Integer, Unit> getOnScrolled() {
        return this.onScrolled;
    }

    public final Function0<Unit> getOnSwipeDownFromTop() {
        return this.onSwipeDownFromTop;
    }

    @Override // android.widget.AbsListView, android.view.View
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        this.g = scrollY == 0 && clampedY;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Function1 function1 = this.onScrolled;
        if (function1 != null) {
            int i = 0;
            if (getChildCount() != 0) {
                View childAt = getChildAt(0);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                i = getPaddingTop() + ((childAt.getHeight() * getFirstVisiblePosition()) - childAt.getTop());
            }
            function1.invoke(Integer.valueOf(i));
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    public final void setCardEventListener(CardsCarouselView.Listener listener) {
        this.cardEventListener = listener;
    }

    public final void setData(List<? extends CardData> newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        this.c.clear();
        this.c.addAll(newData);
        this.d.notifyDataSetChanged();
    }

    public final void setOnScrolled(Function1<? super Integer, Unit> function1) {
        this.onScrolled = function1;
    }

    public final void setOnSwipeDownFromTop(Function0<Unit> function0) {
        this.onSwipeDownFromTop = function0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExpandedCardsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ExpandedCardsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ExpandedCardsView$gestureListener$1] */
    public ExpandedCardsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.c = new ArrayList();
        w wVar = new w(this);
        this.d = wVar;
        this.f = new CardListenerForwarder();
        setAdapter((ListAdapter) wVar);
        setVerticalFadingEdgeEnabled(true);
        setClipChildren(false);
        setClipToPadding(false);
        setStackFromBottom(true);
        setVerticalScrollBarEnabled(false);
        setDivider(null);
        CardsCarouselView.Companion companion = CardsCarouselView.Companion;
        setDividerHeight(companion.getSEPARATOR_HEIGHT$scandit_capture_core());
        setSelector(context.getDrawable(R.color.transparent));
        setFadingEdgeLength(companion.getSEPARATOR_HEIGHT$scandit_capture_core());
        setPadding(companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core(), companion.getSEPARATOR_HEIGHT$scandit_capture_core());
        setOnScrollListener(this);
        this.h = new GestureDetector.SimpleOnGestureListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ExpandedCardsView$gestureListener$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean z;
                Function0<Unit> onSwipeDownFromTop;
                Intrinsics.checkNotNullParameter(e2, "e2");
                if (e1 != null) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();
                    if (Math.abs(y) > Math.abs(x * 2) && y > 0.0f) {
                        z = ExpandedCardsView.this.g;
                        if (z && (onSwipeDownFromTop = ExpandedCardsView.this.getOnSwipeDownFromTop()) != null) {
                            onSwipeDownFromTop.invoke();
                        }
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        };
        this.i = LazyKt.lazy(new x(context, this));
    }
}
