package io.card.payment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DateKeyListener;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import io.card.payment.ui.ActivityHelper;
import io.card.payment.ui.Appearance;
import io.card.payment.ui.ViewUtil;

/* loaded from: classes2.dex */
public final class DataEntryActivity extends Activity implements TextWatcher {
    private static final String TAG = "DataEntryActivity";
    private TextView activityTitleTextView;
    private boolean autoAcceptDone;
    private Button cancelBtn;
    private CreditCard capture;
    private ImageView cardView;
    private EditText cardholderNameEdit;
    private Validator cardholderNameValidator;
    private EditText cvvEdit;
    private Validator cvvValidator;
    private int defaultTextColor;
    private Button doneBtn;
    private EditText expiryEdit;
    private Validator expiryValidator;
    private String labelLeftPadding;
    private EditText numberEdit;
    private Validator numberValidator;
    private EditText postalCodeEdit;
    private Validator postalCodeValidator;
    private boolean useApplicationTheme;
    private int viewIdCounter = 1;
    private int editTextIdCounter = 100;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str;
        RelativeLayout relativeLayout;
        String str2;
        super.onCreate(bundle);
        if (getIntent().getExtras() == null) {
            onBackPressed();
            return;
        }
        boolean booleanExtra = getIntent().getBooleanExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, false);
        this.useApplicationTheme = booleanExtra;
        ActivityHelper.setActivityTheme(this, booleanExtra);
        this.defaultTextColor = new TextView(this).getTextColors().getDefaultColor();
        this.labelLeftPadding = ActivityHelper.holoSupported() ? "12dip" : "2dip";
        LocalizedStrings.setLanguage(getIntent());
        int typedDimensionValueToPixelsInt = ViewUtil.typedDimensionValueToPixelsInt("4dip", this);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        if (!this.useApplicationTheme) {
            relativeLayout2.setBackgroundColor(Appearance.DEFAULT_BACKGROUND_COLOR);
        }
        ScrollView scrollView = new ScrollView(this);
        int i = this.viewIdCounter;
        this.viewIdCounter = i + 1;
        scrollView.setId(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        relativeLayout2.addView(scrollView, layoutParams);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        scrollView.addView(linearLayout, -1, -1);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        this.capture = (CreditCard) getIntent().getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
        this.autoAcceptDone = getIntent().getBooleanExtra("debug_autoAcceptResult", false);
        if (this.capture != null) {
            this.numberValidator = new CardNumberValidator(this.capture.cardNumber);
            this.cardView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            this.cardView.setPadding(0, 0, 0, typedDimensionValueToPixelsInt);
            layoutParams3.weight = 1.0f;
            this.cardView.setImageBitmap(CardIOActivity.markedCardImage);
            linearLayout2.addView(this.cardView, layoutParams3);
            ViewUtil.setMargins(this.cardView, null, null, null, "8dip");
        } else {
            TextView textView = new TextView(this);
            this.activityTitleTextView = textView;
            textView.setTextSize(24.0f);
            if (!this.useApplicationTheme) {
                this.activityTitleTextView.setTextColor(Appearance.PAY_BLUE_COLOR);
            }
            linearLayout2.addView(this.activityTitleTextView);
            ViewUtil.setPadding(this.activityTitleTextView, null, null, null, "8dip");
            ViewUtil.setDimensions(this.activityTitleTextView, -2, -2);
            LinearLayout linearLayout3 = new LinearLayout(this);
            linearLayout3.setOrientation(1);
            ViewUtil.setPadding(linearLayout3, null, "4dip", null, "4dip");
            TextView textView2 = new TextView(this);
            ViewUtil.setPadding(textView2, this.labelLeftPadding, null, null, null);
            textView2.setText(LocalizedStrings.getString(StringKey.ENTRY_CARD_NUMBER));
            if (!this.useApplicationTheme) {
                textView2.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            linearLayout3.addView(textView2, -2, -2);
            EditText editText = new EditText(this);
            this.numberEdit = editText;
            int i2 = this.editTextIdCounter;
            this.editTextIdCounter = i2 + 1;
            editText.setId(i2);
            this.numberEdit.setMaxLines(1);
            this.numberEdit.setImeOptions(6);
            this.numberEdit.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
            this.numberEdit.setInputType(3);
            this.numberEdit.setHint("1234 5678 1234 5678");
            if (!this.useApplicationTheme) {
                this.numberEdit.setHintTextColor(-3355444);
            }
            CardNumberValidator cardNumberValidator = new CardNumberValidator();
            this.numberValidator = cardNumberValidator;
            this.numberEdit.addTextChangedListener(cardNumberValidator);
            this.numberEdit.addTextChangedListener(this);
            this.numberEdit.setFilters(new InputFilter[]{new DigitsKeyListener(), this.numberValidator});
            linearLayout3.addView(this.numberEdit, -1, -2);
            linearLayout2.addView(linearLayout3, -1, -1);
        }
        LinearLayout linearLayout4 = new LinearLayout(this);
        ViewGroup.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        ViewUtil.setPadding(linearLayout4, null, "4dip", null, "4dip");
        linearLayout4.setOrientation(0);
        boolean booleanExtra2 = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false);
        boolean booleanExtra3 = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
        boolean booleanExtra4 = getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        if (!booleanExtra2) {
            str = "4dip";
            relativeLayout = relativeLayout2;
            str2 = "8dip";
            this.expiryValidator = new AlwaysValid();
        } else {
            LinearLayout linearLayout5 = new LinearLayout(this);
            relativeLayout = relativeLayout2;
            str = "4dip";
            str2 = "8dip";
            ViewGroup.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout5.setOrientation(1);
            TextView textView3 = new TextView(this);
            if (!this.useApplicationTheme) {
                textView3.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            textView3.setText(LocalizedStrings.getString(StringKey.ENTRY_EXPIRES));
            ViewUtil.setPadding(textView3, this.labelLeftPadding, null, null, null);
            linearLayout5.addView(textView3, -2, -2);
            EditText editText2 = new EditText(this);
            this.expiryEdit = editText2;
            int i3 = this.editTextIdCounter;
            this.editTextIdCounter = i3 + 1;
            editText2.setId(i3);
            this.expiryEdit.setMaxLines(1);
            this.expiryEdit.setImeOptions(6);
            this.expiryEdit.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
            this.expiryEdit.setInputType(3);
            this.expiryEdit.setHint(LocalizedStrings.getString(StringKey.EXPIRES_PLACEHOLDER));
            if (!this.useApplicationTheme) {
                this.expiryEdit.setHintTextColor(-3355444);
            }
            if (this.capture != null) {
                this.expiryValidator = new ExpiryValidator(this.capture.expiryMonth, this.capture.expiryYear);
            } else {
                this.expiryValidator = new ExpiryValidator();
            }
            if (this.expiryValidator.hasFullLength()) {
                this.expiryEdit.setText(this.expiryValidator.getValue());
            }
            this.expiryEdit.addTextChangedListener(this.expiryValidator);
            this.expiryEdit.addTextChangedListener(this);
            this.expiryEdit.setFilters(new InputFilter[]{new DateKeyListener(), this.expiryValidator});
            linearLayout5.addView(this.expiryEdit, -1, -2);
            linearLayout4.addView(linearLayout5, layoutParams5);
            ViewUtil.setMargins(linearLayout5, null, null, (booleanExtra3 || booleanExtra4) ? str : null, null);
        }
        if (booleanExtra3) {
            LinearLayout linearLayout6 = new LinearLayout(this);
            ViewGroup.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout6.setOrientation(1);
            TextView textView4 = new TextView(this);
            if (!this.useApplicationTheme) {
                textView4.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(textView4, this.labelLeftPadding, null, null, null);
            textView4.setText(LocalizedStrings.getString(StringKey.ENTRY_CVV));
            linearLayout6.addView(textView4, -2, -2);
            EditText editText3 = new EditText(this);
            this.cvvEdit = editText3;
            int i4 = this.editTextIdCounter;
            this.editTextIdCounter = i4 + 1;
            editText3.setId(i4);
            this.cvvEdit.setMaxLines(1);
            this.cvvEdit.setImeOptions(6);
            this.cvvEdit.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
            this.cvvEdit.setInputType(3);
            this.cvvEdit.setHint("123");
            if (!this.useApplicationTheme) {
                this.cvvEdit.setHintTextColor(-3355444);
            }
            this.cvvValidator = new FixedLengthValidator(this.capture != null ? CardType.fromCardNumber(this.numberValidator.getValue()).cvvLength() : 4);
            this.cvvEdit.setFilters(new InputFilter[]{new DigitsKeyListener(), this.cvvValidator});
            this.cvvEdit.addTextChangedListener(this.cvvValidator);
            this.cvvEdit.addTextChangedListener(this);
            linearLayout6.addView(this.cvvEdit, -1, -2);
            linearLayout4.addView(linearLayout6, layoutParams6);
            ViewUtil.setMargins(linearLayout6, booleanExtra2 ? str : null, null, booleanExtra4 ? str : null, null);
        } else {
            this.cvvValidator = new AlwaysValid();
        }
        if (booleanExtra4) {
            LinearLayout linearLayout7 = new LinearLayout(this);
            ViewGroup.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout7.setOrientation(1);
            TextView textView5 = new TextView(this);
            if (!this.useApplicationTheme) {
                textView5.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(textView5, this.labelLeftPadding, null, null, null);
            textView5.setText(LocalizedStrings.getString(StringKey.ENTRY_POSTAL_CODE));
            linearLayout7.addView(textView5, -2, -2);
            boolean booleanExtra5 = getIntent().getBooleanExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, false);
            EditText editText4 = new EditText(this);
            this.postalCodeEdit = editText4;
            int i5 = this.editTextIdCounter;
            this.editTextIdCounter = i5 + 1;
            editText4.setId(i5);
            this.postalCodeEdit.setMaxLines(1);
            this.postalCodeEdit.setImeOptions(6);
            this.postalCodeEdit.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
            if (booleanExtra5) {
                this.postalCodeEdit.setInputType(3);
            } else {
                this.postalCodeEdit.setInputType(1);
            }
            if (!this.useApplicationTheme) {
                this.postalCodeEdit.setHintTextColor(-3355444);
            }
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(20);
            this.postalCodeValidator = maxLengthValidator;
            this.postalCodeEdit.addTextChangedListener(maxLengthValidator);
            this.postalCodeEdit.addTextChangedListener(this);
            linearLayout7.addView(this.postalCodeEdit, -1, -2);
            linearLayout4.addView(linearLayout7, layoutParams7);
            ViewUtil.setMargins(linearLayout7, (booleanExtra2 || booleanExtra3) ? str : null, null, null, null);
        } else {
            this.postalCodeValidator = new AlwaysValid();
        }
        linearLayout2.addView(linearLayout4, layoutParams4);
        addCardholderNameIfNeeded(linearLayout2);
        linearLayout.addView(linearLayout2, layoutParams2);
        ViewUtil.setMargins(linearLayout2, "16dip", "20dip", "16dip", "20dip");
        LinearLayout linearLayout8 = new LinearLayout(this);
        int i6 = this.viewIdCounter;
        this.viewIdCounter = i6 + 1;
        linearLayout8.setId(i6);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams8.addRule(12);
        linearLayout8.setPadding(0, typedDimensionValueToPixelsInt, 0, 0);
        linearLayout8.setBackgroundColor(0);
        layoutParams.addRule(2, linearLayout8.getId());
        this.doneBtn = new Button(this);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.doneBtn.setText(LocalizedStrings.getString(StringKey.DONE));
        this.doneBtn.setOnClickListener(new View.OnClickListener() { // from class: io.card.payment.DataEntryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DataEntryActivity.this.completed();
            }
        });
        this.doneBtn.setEnabled(false);
        linearLayout8.addView(this.doneBtn, layoutParams9);
        ViewUtil.styleAsButton(this.doneBtn, true, this, this.useApplicationTheme);
        ViewUtil.setPadding(this.doneBtn, "5dip", null, "5dip", null);
        String str3 = str2;
        ViewUtil.setMargins(this.doneBtn, str3, str3, str3, str3);
        if (!this.useApplicationTheme) {
            this.doneBtn.setTextSize(16.0f);
        }
        this.cancelBtn = new Button(this);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.cancelBtn.setText(LocalizedStrings.getString(StringKey.CANCEL));
        this.cancelBtn.setOnClickListener(new View.OnClickListener() { // from class: io.card.payment.DataEntryActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DataEntryActivity.this.onBackPressed();
            }
        });
        linearLayout8.addView(this.cancelBtn, layoutParams10);
        ViewUtil.styleAsButton(this.cancelBtn, false, this, this.useApplicationTheme);
        ViewUtil.setPadding(this.cancelBtn, "5dip", null, "5dip", null);
        ViewUtil.setMargins(this.cancelBtn, str, str3, str3, str3);
        if (!this.useApplicationTheme) {
            this.cancelBtn.setTextSize(16.0f);
        }
        RelativeLayout relativeLayout3 = relativeLayout;
        relativeLayout3.addView(linearLayout8, layoutParams8);
        ActivityHelper.addActionBarIfSupported(this);
        setContentView(relativeLayout3);
        Drawable drawable = getIntent().getBooleanExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, true) ? getResources().getDrawable(R.drawable.cio_ic_paypal_monogram) : null;
        if (booleanExtra2 && this.expiryValidator.isValid()) {
            afterTextChanged(this.expiryEdit.getEditableText());
        }
        ActivityHelper.setupActionBarIfSupported(this, this.activityTitleTextView, LocalizedStrings.getString(StringKey.MANUAL_ENTRY_TITLE), "card.io - ", drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completed() {
        if (this.capture == null) {
            this.capture = new CreditCard();
        }
        if (this.expiryEdit != null) {
            this.capture.expiryMonth = ((ExpiryValidator) this.expiryValidator).month;
            this.capture.expiryYear = ((ExpiryValidator) this.expiryValidator).year;
        }
        CreditCard creditCard = new CreditCard(this.numberValidator.getValue(), this.capture.expiryMonth, this.capture.expiryYear, this.cvvValidator.getValue(), this.postalCodeValidator.getValue(), this.cardholderNameValidator.getValue());
        Intent intent = new Intent();
        intent.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, creditCard);
        if (getIntent().hasExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE)) {
            intent.putExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE, getIntent().getByteArrayExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE));
        }
        setResult(CardIOActivity.RESULT_CARD_INFO, intent);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        setResult(CardIOActivity.RESULT_ENTRY_CANCELED);
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        getWindow().setFlags(0, 1024);
        ActivityHelper.setFlagSecure(this);
        validateAndEnableDoneButtonIfValid();
        if (this.numberEdit == null && this.expiryEdit != null && !this.expiryValidator.isValid()) {
            this.expiryEdit.requestFocus();
        } else {
            advanceToNextEmptyField();
        }
        if (this.numberEdit == null && this.expiryEdit == null && this.cvvEdit == null && this.postalCodeEdit == null && this.cardholderNameEdit == null) {
            return;
        }
        getWindow().setSoftInputMode(5);
    }

    private EditText advanceToNextEmptyField() {
        int i = 100;
        while (true) {
            int i2 = i + 1;
            EditText editText = (EditText) findViewById(i);
            if (editText == null) {
                return null;
            }
            if (editText.getText().length() == 0 && editText.requestFocus()) {
                return editText;
            }
            i = i2;
        }
    }

    private void validateAndEnableDoneButtonIfValid() {
        this.doneBtn.setEnabled(this.numberValidator.isValid() && this.expiryValidator.isValid() && this.cvvValidator.isValid() && this.postalCodeValidator.isValid() && this.cardholderNameValidator.isValid());
        if (this.autoAcceptDone && this.numberValidator.isValid() && this.expiryValidator.isValid() && this.cvvValidator.isValid() && this.postalCodeValidator.isValid() && this.cardholderNameValidator.isValid()) {
            completed();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        EditText editText = this.numberEdit;
        if (editText != null && editable == editText.getText()) {
            if (this.numberValidator.hasFullLength()) {
                if (!this.numberValidator.isValid()) {
                    this.numberEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                } else {
                    setDefaultColor(this.numberEdit);
                    advanceToNextEmptyField();
                }
            } else {
                setDefaultColor(this.numberEdit);
            }
            if (this.cvvEdit != null) {
                CardType fromCardNumber = CardType.fromCardNumber(this.numberValidator.getValue().toString());
                FixedLengthValidator fixedLengthValidator = (FixedLengthValidator) this.cvvValidator;
                int cvvLength = fromCardNumber.cvvLength();
                fixedLengthValidator.requiredLength = cvvLength;
                this.cvvEdit.setHint(cvvLength == 4 ? "1234" : "123");
            }
        } else {
            EditText editText2 = this.expiryEdit;
            if (editText2 != null && editable == editText2.getText()) {
                if (this.expiryValidator.hasFullLength()) {
                    if (!this.expiryValidator.isValid()) {
                        this.expiryEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                    } else {
                        setDefaultColor(this.expiryEdit);
                        advanceToNextEmptyField();
                    }
                } else {
                    setDefaultColor(this.expiryEdit);
                }
            } else {
                EditText editText3 = this.cvvEdit;
                if (editText3 != null && editable == editText3.getText()) {
                    if (this.cvvValidator.hasFullLength()) {
                        if (!this.cvvValidator.isValid()) {
                            this.cvvEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                        } else {
                            setDefaultColor(this.cvvEdit);
                            advanceToNextEmptyField();
                        }
                    } else {
                        setDefaultColor(this.cvvEdit);
                    }
                } else {
                    EditText editText4 = this.postalCodeEdit;
                    if (editText4 != null && editable == editText4.getText()) {
                        if (this.postalCodeValidator.hasFullLength()) {
                            if (!this.postalCodeValidator.isValid()) {
                                this.postalCodeEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                            } else {
                                setDefaultColor(this.postalCodeEdit);
                            }
                        } else {
                            setDefaultColor(this.postalCodeEdit);
                        }
                    } else {
                        EditText editText5 = this.cardholderNameEdit;
                        if (editText5 != null && editable == editText5.getText()) {
                            if (this.cardholderNameValidator.hasFullLength()) {
                                if (!this.cardholderNameValidator.isValid()) {
                                    this.cardholderNameEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
                                } else {
                                    setDefaultColor(this.cardholderNameEdit);
                                }
                            } else {
                                setDefaultColor(this.cardholderNameEdit);
                            }
                        }
                    }
                }
            }
        }
        validateAndEnableDoneButtonIfValid();
    }

    private void setDefaultColor(EditText editText) {
        if (this.useApplicationTheme) {
            editText.setTextColor(this.defaultTextColor);
        } else {
            editText.setTextColor(-12303292);
        }
    }

    private void addCardholderNameIfNeeded(ViewGroup viewGroup) {
        if (getIntent().getBooleanExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false)) {
            LinearLayout linearLayout = new LinearLayout(this);
            ViewUtil.setPadding(linearLayout, null, "4dip", null, null);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(this);
            if (!this.useApplicationTheme) {
                textView.setTextColor(Appearance.TEXT_COLOR_LABEL);
            }
            ViewUtil.setPadding(textView, this.labelLeftPadding, null, null, null);
            textView.setText(LocalizedStrings.getString(StringKey.ENTRY_CARDHOLDER_NAME));
            linearLayout.addView(textView, -2, -2);
            EditText editText = new EditText(this);
            this.cardholderNameEdit = editText;
            int i = this.editTextIdCounter;
            this.editTextIdCounter = i + 1;
            editText.setId(i);
            this.cardholderNameEdit.setMaxLines(1);
            this.cardholderNameEdit.setImeOptions(6);
            this.cardholderNameEdit.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
            this.cardholderNameEdit.setInputType(1);
            if (!this.useApplicationTheme) {
                this.cardholderNameEdit.setHintTextColor(-3355444);
            }
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(175);
            this.cardholderNameValidator = maxLengthValidator;
            this.cardholderNameEdit.addTextChangedListener(maxLengthValidator);
            this.cardholderNameEdit.addTextChangedListener(this);
            linearLayout.addView(this.cardholderNameEdit, -1, -2);
            viewGroup.addView(linearLayout, -1, -2);
            return;
        }
        this.cardholderNameValidator = new AlwaysValid();
    }
}
