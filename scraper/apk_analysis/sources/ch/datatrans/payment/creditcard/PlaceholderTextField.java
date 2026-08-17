package ch.datatrans.payment.creditcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.L9;
import ch.datatrans.payment.M9;
import ch.datatrans.payment.R;
import ch.datatrans.payment.o4;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\r\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0001,B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ)\u0010\u000f\u001a\u00020\f2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nj\u0004\u0018\u0001`\r¢\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0014\u001a\u00020\f2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016¢\u0006\u0004\b\u0018\u0010\u0019R*\u0010!\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R$\u0010+\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020&8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lch/datatrans/payment/creditcard/PlaceholderTextField;", "Landroid/widget/RelativeLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lkotlin/Function1;", "Landroid/text/Editable;", "", "Lch/datatrans/payment/creditcard/AfterTextWatcher;", "afterTextWatcher", "setAfterTextWatcher", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function0;", "Lch/datatrans/payment/creditcard/ClickWatcher;", "clickWatcher", "setClickWatcher", "(Lkotlin/jvm/functions/Function0;)V", "", "spaces", "setDividerSpaces", "(Ljava/util/List;)V", "value", "p", "I", "getMaxLength", "()I", "setMaxLength", "(I)V", "maxLength", "q", "getHintLength", "setHintLength", "hintLength", "", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "text", "ch/datatrans/payment/M9", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PlaceholderTextField extends RelativeLayout {
    public final TypedArray a;
    public final String b;
    public final int c;
    public final String d;
    public final int e;
    public final String f;
    public final boolean g;
    public final String h;
    public final boolean i;
    public final boolean j;
    public final String k;
    public final String l;
    public final DateAutofillEditText m;
    public final TextView n;
    public final AppCompatImageView o;

    /* renamed from: p, reason: from kotlin metadata */
    public int maxLength;

    /* renamed from: q, reason: from kotlin metadata */
    public int hintLength;
    public List r;
    public Function1 s;
    public Function0 t;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaceholderTextField(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final boolean a(DateAutofillEditText dateAutofillEditText, PlaceholderTextField placeholderTextField, View view, MotionEvent motionEvent) {
        if (!dateAutofillEditText.hasFocus()) {
            dateAutofillEditText.setText("");
        }
        Function0 function0 = placeholderTextField.t;
        if (function0 != null) {
            function0.invoke();
        }
        dateAutofillEditText.performClick();
        return false;
    }

    public final int getHintLength() {
        return this.hintLength;
    }

    public final int getMaxLength() {
        return this.maxLength;
    }

    public final CharSequence getText() {
        Editable text = this.m.getText();
        return text != null ? text : "";
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEvent(int i) {
        this.m.sendAccessibilityEvent(i);
    }

    public final void setAfterTextWatcher(Function1<? super Editable, Unit> afterTextWatcher) {
        this.s = afterTextWatcher;
    }

    public final void setClickWatcher(Function0<Unit> clickWatcher) {
        this.t = clickWatcher;
    }

    public final void setDividerSpaces(List<Integer> spaces) {
        Intrinsics.checkNotNullParameter(spaces, "spaces");
        this.r = spaces;
        a();
    }

    public final void setHintLength(int i) {
        this.hintLength = i;
    }

    public final void setMaxLength(int i) {
        this.maxLength = i;
        this.m.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        a();
    }

    public final void setText(CharSequence value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Editable text = this.m.getText();
        if (!Intrinsics.areEqual(value, text != null ? text.toString() : null) || value.length() <= 0) {
            this.m.setText(value);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaceholderTextField(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PlaceholderTextField(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaceholderTextField(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DTPLPlaceholderEditText, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.a = obtainStyledAttributes;
        String string = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_android_text);
        this.b = string == null ? "" : string;
        this.c = obtainStyledAttributes.getInteger(R.styleable.DTPLPlaceholderEditText_android_inputType, 18);
        String string2 = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_placeholder);
        this.d = string2 == null ? "0" : string2;
        this.e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DTPLPlaceholderEditText_dividerWidth, 15);
        String string3 = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_dividerSymbol);
        this.f = string3 != null ? string3 : "";
        this.g = obtainStyledAttributes.getBoolean(R.styleable.DTPLPlaceholderEditText_clearButton, false);
        this.h = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_android_autofillHints);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.DTPLPlaceholderEditText_clearOnClick, false);
        this.j = obtainStyledAttributes.getBoolean(R.styleable.DTPLPlaceholderEditText_concealInput, false);
        String string4 = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_inputContentDescription);
        if (string4 == null) {
            string4 = context.getString(R.string.datatrans_sdk_accessibility_card_number);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        }
        this.k = string4;
        String string5 = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_clearButtonContentDescription);
        if (string5 == null) {
            string5 = context.getString(R.string.datatrans_sdk_accessibility_clear_card_number);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        }
        this.l = string5;
        int i2 = obtainStyledAttributes.getInt(R.styleable.DTPLPlaceholderEditText_android_maxLength, 16);
        this.maxLength = i2;
        this.hintLength = i2;
        String string6 = obtainStyledAttributes.getString(R.styleable.DTPLPlaceholderEditText_dividerSpaces);
        List split$default = StringsKt.split$default((CharSequence) (string6 == null ? "4 8 12" : string6), new char[]{' '}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(Integer.parseInt((String) it.next())));
        }
        this.r = arrayList;
        this.a.recycle();
        View.inflate(context, R.layout.dtpl_widget_placeholder_text_field, this);
        View findViewById = findViewById(R.id.placeholder_text_field_hint);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.n = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.placeholder_text_field_edit);
        final DateAutofillEditText dateAutofillEditText = (DateAutofillEditText) findViewById2;
        InputFilter[] filters = dateAutofillEditText.getFilters();
        Intrinsics.checkNotNullExpressionValue(filters, "getFilters(...)");
        dateAutofillEditText.setFilters((InputFilter[]) ArraysKt.plus((InputFilter.LengthFilter[]) filters, new InputFilter.LengthFilter(this.maxLength)));
        dateAutofillEditText.setInputType(this.c);
        if (!this.j) {
            dateAutofillEditText.setTransformationMethod(new L9());
        }
        String str = this.h;
        if (str != null) {
            dateAutofillEditText.setDate(Intrinsics.areEqual(HintConstants.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE, str));
            dateAutofillEditText.setAutofillHints(new String[]{this.h});
            dateAutofillEditText.setImportantForAutofill(1);
        }
        dateAutofillEditText.addTextChangedListener(new M9(this));
        dateAutofillEditText.setContentDescription(this.k);
        if (this.i) {
            dateAutofillEditText.setOnTouchListener(new View.OnTouchListener() { // from class: ch.datatrans.payment.creditcard.PlaceholderTextField$$ExternalSyntheticLambda1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return PlaceholderTextField.a(DateAutofillEditText.this, this, view, motionEvent);
                }
            });
        } else {
            dateAutofillEditText.setOnTouchListener(new View.OnTouchListener() { // from class: ch.datatrans.payment.creditcard.PlaceholderTextField$$ExternalSyntheticLambda2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return PlaceholderTextField.a(PlaceholderTextField.this, dateAutofillEditText, view, motionEvent);
                }
            });
        }
        dateAutofillEditText.setLongClickable(true ^ this.i);
        dateAutofillEditText.setOnHoverListener(new View.OnHoverListener() { // from class: ch.datatrans.payment.creditcard.PlaceholderTextField$$ExternalSyntheticLambda3
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return PlaceholderTextField.a(context, this, dateAutofillEditText, view, motionEvent);
            }
        });
        dateAutofillEditText.setTextCursorDrawable(R.drawable.dtpl_cursor_color);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "apply(...)");
        this.m = dateAutofillEditText;
        View findViewById3 = findViewById(R.id.placeholder_text_field_clear);
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById3;
        appCompatImageView.setContentDescription(this.l);
        appCompatImageView.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.creditcard.PlaceholderTextField$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaceholderTextField.a(PlaceholderTextField.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById3, "apply(...)");
        this.o = appCompatImageView;
        setText(this.b);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: ch.datatrans.payment.creditcard.PlaceholderTextField$$ExternalSyntheticLambda5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                PlaceholderTextField.a(PlaceholderTextField.this);
            }
        });
    }

    public static final boolean a(PlaceholderTextField placeholderTextField, DateAutofillEditText dateAutofillEditText, View view, MotionEvent motionEvent) {
        Function0 function0 = placeholderTextField.t;
        if (function0 != null) {
            function0.invoke();
        }
        dateAutofillEditText.performClick();
        return false;
    }

    public static final boolean a(Context context, PlaceholderTextField placeholderTextField, DateAutofillEditText dateAutofillEditText, View view, MotionEvent motionEvent) {
        Object systemService = context.getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        if (!((AccessibilityManager) systemService).isTouchExplorationEnabled()) {
            return false;
        }
        if (placeholderTextField.i) {
            dateAutofillEditText.setText("");
        }
        Function0 function0 = placeholderTextField.t;
        if (function0 != null) {
            function0.invoke();
        }
        dateAutofillEditText.requestFocus();
        Object systemService2 = context.getSystemService("input_method");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService2).toggleSoftInputFromWindow(dateAutofillEditText.getApplicationWindowToken(), 2, 1);
        return false;
    }

    public static final void a(PlaceholderTextField placeholderTextField, View view) {
        placeholderTextField.setText("");
    }

    public static final void a(PlaceholderTextField placeholderTextField) {
        int textSizeUnit;
        int textSizeUnit2;
        if (placeholderTextField.m.getWidth() != placeholderTextField.n.getWidth()) {
            placeholderTextField.m.setWidth(placeholderTextField.n.getWidth());
        }
        if (placeholderTextField.m.getHeight() != placeholderTextField.n.getHeight()) {
            placeholderTextField.m.setHeight(placeholderTextField.n.getHeight());
        }
        if (Build.VERSION.SDK_INT < 30) {
            if (placeholderTextField.m.getTextSize() == placeholderTextField.n.getTextSize()) {
                return;
            }
            placeholderTextField.m.setTextSize(0, placeholderTextField.n.getTextSize());
            return;
        }
        if (placeholderTextField.m.getTextSize() == placeholderTextField.n.getTextSize()) {
            int textSizeUnit3 = placeholderTextField.m.getTextSizeUnit();
            textSizeUnit2 = placeholderTextField.n.getTextSizeUnit();
            if (textSizeUnit3 == textSizeUnit2) {
                return;
            }
        }
        DateAutofillEditText dateAutofillEditText = placeholderTextField.m;
        textSizeUnit = placeholderTextField.n.getTextSizeUnit();
        dateAutofillEditText.setTextSize(textSizeUnit, placeholderTextField.n.getTextSize());
    }

    public final void a() {
        Editable text = this.m.getText();
        if (text != null) {
            this.o.setVisibility((!this.g || text.length() <= 0) ? 8 : 0);
            a(text);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(StringsKt.repeat(StringUtils.SPACE, text.length()));
            int length = this.hintLength - text.length();
            if (length > 0) {
                spannableStringBuilder.append((CharSequence) StringsKt.repeat(this.d, length));
            }
            a(spannableStringBuilder);
            this.n.setText(spannableStringBuilder);
        }
        this.m.setSelection(getText().length());
    }

    public final void a(Editable editable) {
        int i = 0;
        Object[] spans = editable.getSpans(0, editable.length(), JvmClassMappingKt.getJavaClass(Reflection.getOrCreateKotlinClass(o4.class)));
        Intrinsics.checkNotNull(spans);
        for (Object obj : spans) {
            editable.removeSpan(obj);
        }
        int length = editable.length();
        while (i < length) {
            int i2 = i + 1;
            if (this.r.contains(Integer.valueOf(i2))) {
                editable.setSpan(new o4(this.f, this.e), i, i2, 33);
            }
            i = i2;
        }
    }
}
