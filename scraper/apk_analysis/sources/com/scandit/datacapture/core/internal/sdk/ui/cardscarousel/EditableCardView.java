package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u000fR$\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/EditableCardView;", "Landroid/widget/FrameLayout;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardSubView;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/EditableCardData;", "Landroid/content/Context;", "context", "", "useFlatLayout", "<init>", "(Landroid/content/Context;Z)V", "cardData", "", "setCardData", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/EditableCardData;)V", "takeFocus", "()V", "clearEditText", "finishEditing", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "getListener", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;", "setListener", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardsCarouselView$Listener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class EditableCardView extends FrameLayout implements CardSubView<EditableCardData> {

    /* renamed from: a, reason: from kotlin metadata */
    private CardsCarouselView.Listener listener;
    private EditableCardData b;
    private Integer c;
    private final TextView d;
    private final ListenableEditText e;
    private final AnimatedGradientTextView f;
    private final ImageView g;
    private final ImageView h;
    private final TextView i;
    private final ColorBorderDrawable j;

    public /* synthetic */ EditableCardView(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z);
    }

    private final void a() {
        this.e.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.EditableCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                EditableCardView.a(EditableCardView.this, view, z);
            }
        });
        this.e.addTextChangedListener(new TextWatcher() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.EditableCardView$setupListeners$2

            /* renamed from: a, reason: from kotlin metadata */
            private String previousText = "";

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                this.previousText = String.valueOf(s);
            }

            public final String getPreviousText() {
                return this.previousText;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer num;
                Unit unit;
                ImageView imageView;
                ImageView imageView2;
                String valueOf = String.valueOf(s);
                CardsCarouselView.Listener listener = EditableCardView.this.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
                Integer iconResForTextChange = listener != null ? listener.iconResForTextChange(this.previousText, valueOf) : null;
                num = EditableCardView.this.c;
                if (Intrinsics.areEqual(num, iconResForTextChange)) {
                    return;
                }
                EditableCardView.this.c = iconResForTextChange;
                if (iconResForTextChange != null) {
                    EditableCardView editableCardView = EditableCardView.this;
                    int intValue = iconResForTextChange.intValue();
                    imageView2 = editableCardView.h;
                    imageView2.setImageResource(intValue);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    imageView = EditableCardView.this.h;
                    imageView.setImageDrawable(null);
                }
            }

            public final void setPreviousText(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.previousText = str;
            }
        });
        this.e.setOnCloseKeyboardButtonTapped(new v(this));
        this.e.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.EditableCardView$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean a;
                a = EditableCardView.a(EditableCardView.this, textView, i, keyEvent);
                return a;
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.EditableCardView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditableCardView.a(EditableCardView.this, view);
            }
        });
    }

    public final void clearEditText() {
        this.e.setText("");
    }

    public final void finishEditing() {
        CardsCarouselView.Listener listener;
        EditableCardData editableCardData = this.b;
        if (editableCardData == null || (listener = getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        listener.onEditableFieldFinishedEditing(editableCardData, this.e);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    /* renamed from: getListener, reason: from getter */
    public CardsCarouselView.Listener getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String() {
        return this.listener;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    public void setListener(CardsCarouselView.Listener listener) {
        this.listener = listener;
    }

    public final void takeFocus() {
        this.e.requestFocus();
        ListenableEditText listenableEditText = this.e;
        listenableEditText.setSelection(listenableEditText.length());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableCardView(Context context, boolean z) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.j = new ColorBorderDrawable();
        LayoutInflater.from(getContext()).inflate(z ? R.layout.sc_card_carousel_editable_item_flat : R.layout.sc_card_carousel_editable_item, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.titleText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.d = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.editText);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.e = (ListenableEditText) findViewById2;
        View findViewById3 = findViewById(R.id.editTextHint);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.f = (AnimatedGradientTextView) findViewById3;
        View findViewById4 = findViewById(R.id.image_line);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.g = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.iconImageView);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.h = (ImageView) findViewById5;
        View findViewById6 = findViewById(R.id.errorText);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.i = (TextView) findViewById6;
        a();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView
    public void setCardData(EditableCardData cardData) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        this.b = cardData;
        this.c = cardData.getIconRes();
        this.d.setText(cardData.getTitle());
        setContentDescription(cardData.getContentDescription());
        this.e.setHint(cardData.getHint());
        this.e.setText(cardData.getText());
        this.e.setInputType(cardData.getInputType());
        this.i.setText(cardData.getErrorText());
        this.i.setVisibility(!cardData.getShowError() ? 4 : 0);
        this.i.setTextColor(cardData.getErrorColor());
        this.g.setImageTintList(ColorStateList.valueOf(cardData.getShowError() ? cardData.getErrorColor() : ViewCompat.MEASURED_STATE_MASK));
        if (cardData.getIconRes() != null) {
            this.h.setImageResource(cardData.getIconRes().intValue());
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(4);
        }
        if (cardData.getShowLoading()) {
            setBackground(this.j);
            this.j.start();
            this.f.setVisibility(0);
            this.f.start();
            return;
        }
        setBackground(null);
        this.j.stop();
        this.f.setVisibility(8);
        this.f.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(EditableCardView this$0, View view, boolean z) {
        EditableCardData editableCardData;
        CardsCarouselView.Listener listener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        if (!z || (editableCardData = this$0.b) == null || (listener = this$0.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        listener.onEditableFieldTapped(editableCardData, this$0.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean a(EditableCardView this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer valueOf = keyEvent != null ? Integer.valueOf(keyEvent.getKeyCode()) : null;
        this$0.getClass();
        if ((valueOf == null || valueOf.intValue() != 66) && i != 6 && i != 5) {
            return false;
        }
        this$0.finishEditing();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(EditableCardView this$0, View view) {
        CardsCarouselView.Listener listener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditableCardData editableCardData = this$0.b;
        if (editableCardData == null || (listener = this$0.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        listener.onCardIconTapped(editableCardData, this$0.c, this$0.e.getText().toString());
    }
}
