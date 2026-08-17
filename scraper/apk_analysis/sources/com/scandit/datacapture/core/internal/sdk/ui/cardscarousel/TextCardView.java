package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u001b\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0006\u0010\nB#\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0006\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R$\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/TextCardView;", "Landroid/widget/FrameLayout;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardSubView;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/TextCardData;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cardData", "", "setCardData", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/TextCardData;)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "getListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "setListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class TextCardView extends FrameLayout implements CardSubView<TextCardData> {

    @Deprecated
    public static final int CONTENT_TEXT_MAX_TEXT_SIZE_SP = 12;

    @Deprecated
    public static final int CONTENT_TEXT_MIN_TEXT_SIZE_SP = 9;

    @Deprecated
    public static final int CONTENT_TEXT_STEP_TEXT_SIZE_SP = 1;

    /* renamed from: a, reason: from kotlin metadata */
    private CardsCarouselView.Listener listener;
    private TextCardData b;
    private final TextView c;
    private final TextView d;
    private final ImageView e;
    private final ImageView f;
    private int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextCardView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.g = -1;
        LayoutInflater.from(getContext()).inflate(R.layout.sc_card_carousel_text_item, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.contentText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        TextView textView = (TextView) findViewById;
        this.d = textView;
        View findViewById2 = findViewById(R.id.titleText);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        TextView textView2 = (TextView) findViewById2;
        this.c = textView2;
        textView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                TextCardView.a(TextCardView.this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        textView.setAutoSizeTextTypeUniformWithConfiguration(9, 12, 1, 2);
        View findViewById3 = findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.e = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iconImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.f = (ImageView) findViewById4;
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextCardView.a(TextCardView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(TextCardView this$0, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.c.getWidth() != this$0.g) {
            this$0.g = this$0.c.getWidth();
            this$0.a(this$0.d.getText());
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    public CardsCarouselView.Listener getListener() {
        return this.listener;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    public void setListener(CardsCarouselView.Listener listener) {
        this.listener = listener;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    public void setCardData(TextCardData cardData) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        this.b = cardData;
        this.c.setText(cardData.getTitle());
        a(cardData.getContent());
        setContentDescription(cardData.getContentDescription());
        if (cardData.getImage() != null) {
            this.e.setImageBitmap(cardData.getImage());
        } else {
            this.e.setImageResource(R.drawable.sc_single_scan);
        }
        if (cardData.getIconRes() == null) {
            this.f.setVisibility(4);
        } else {
            this.f.setImageResource(cardData.getIconRes().intValue());
            this.f.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(TextCardView this$0, View view) {
        CardsCarouselView.Listener listener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextCardData textCardData = this$0.b;
        if (textCardData == null || (listener = this$0.getListener()) == null) {
            return;
        }
        listener.onCardTapped(textCardData);
    }

    private final void a(CharSequence charSequence) {
        this.d.setText(charSequence);
        if (charSequence != null && charSequence.length() != 0) {
            this.d.setVisibility(0);
            TextView textView = this.d;
            TextView textView2 = this.c;
            StaticLayout build = StaticLayout.Builder.obtain(textView2.getText(), 0, textView2.getText().length(), textView2.getPaint(), textView2.getWidth()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            textView.setMaxLines(build.getLineCount() <= 1 ? 2 : 1);
            return;
        }
        this.d.setVisibility(8);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.g = -1;
        LayoutInflater.from(getContext()).inflate(R.layout.sc_card_carousel_text_item, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.contentText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        TextView textView = (TextView) findViewById;
        this.d = textView;
        View findViewById2 = findViewById(R.id.titleText);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        TextView textView2 = (TextView) findViewById2;
        this.c = textView2;
        textView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                TextCardView.a(TextCardView.this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        textView.setAutoSizeTextTypeUniformWithConfiguration(9, 12, 1, 2);
        View findViewById3 = findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.e = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iconImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.f = (ImageView) findViewById4;
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextCardView.a(TextCardView.this, view);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.g = -1;
        LayoutInflater.from(getContext()).inflate(R.layout.sc_card_carousel_text_item, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.contentText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        TextView textView = (TextView) findViewById;
        this.d = textView;
        View findViewById2 = findViewById(R.id.titleText);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        TextView textView2 = (TextView) findViewById2;
        this.c = textView2;
        textView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i22, int i3, int i4, int i5, int i6, int i7, int i8) {
                TextCardView.a(TextCardView.this, view, i2, i22, i3, i4, i5, i6, i7, i8);
            }
        });
        textView.setAutoSizeTextTypeUniformWithConfiguration(9, 12, 1, 2);
        View findViewById3 = findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.e = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iconImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.f = (ImageView) findViewById4;
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextCardView.a(TextCardView.this, view);
            }
        });
    }
}
