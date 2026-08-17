package com.google.android.gms.wallet.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.wallet.R;
import com.google.android.gms.wallet.button.ButtonOptions;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class PayButton extends FrameLayout implements View.OnClickListener {
    private View.OnClickListener zza;
    private ButtonOptions.Builder zzb;
    private View zzc;
    private final zzf zzd;

    public PayButton(Context context) {
        this(context, null);
    }

    private final void zza(ButtonOptions buttonOptions) {
        Drawable drawable;
        removeAllViews();
        zzg zzgVar = new zzg(new ContextThemeWrapper(getContext(), buttonOptions.getButtonTheme() == 2 ? R.style.PayButtonGenericLightTheme : R.style.PayButtonGenericDarkTheme), null);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(zzgVar.getContext()).inflate(R.layout.paybutton_generic, (ViewGroup) zzgVar, true).findViewById(R.id.pay_button_view);
        Context context = zzgVar.getContext();
        int cornerRadius = buttonOptions.getCornerRadius();
        GradientDrawable gradientDrawable = (GradientDrawable) zzh.zza(context, R.attr.payButtonGenericBackground).mutate();
        float f = cornerRadius;
        gradientDrawable.setCornerRadius(f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[]{R.attr.payButtonGenericRippleColor});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        ColorStateList valueOf = ColorStateList.valueOf(color);
        if (PlatformVersion.isAtLeastLollipop()) {
            drawable = new RippleDrawable(valueOf, gradientDrawable, null);
        } else {
            GradientDrawable gradientDrawable2 = (GradientDrawable) zzh.zza(context, R.attr.payButtonGenericRippleMask).mutate();
            gradientDrawable2.setCornerRadius(f);
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, gradientDrawable2});
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, layerDrawable);
            stateListDrawable.addState(new int[0], gradientDrawable);
            drawable = stateListDrawable;
        }
        linearLayout.setBackground(drawable);
        zzgVar.setContentDescription(zzgVar.getContext().getString(R.string.gpay_logo_description));
        this.zzc = zzgVar;
        addView(zzgVar);
        this.zzc.setOnClickListener(this);
    }

    public void initialize(ButtonOptions buttonOptions) {
        ButtonOptions.Builder builder = this.zzb;
        if (buttonOptions.getButtonType() != 0) {
            ButtonOptions.this.zza = buttonOptions.getButtonType();
        }
        if (buttonOptions.getButtonTheme() != 0) {
            ButtonOptions.this.zzb = buttonOptions.getButtonTheme();
        }
        if (buttonOptions.zze) {
            builder.setCornerRadius(buttonOptions.getCornerRadius());
        }
        if (buttonOptions.getAllowedPaymentMethods() != null) {
            ButtonOptions.this.zzd = buttonOptions.getAllowedPaymentMethods();
        }
        if (isInEditMode()) {
            zza(this.zzb.build());
            return;
        }
        removeAllViews();
        ButtonOptions build = this.zzb.build();
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext(), 232100000) != 0) {
            zza(build);
            Log.e("PayButton", "Failed to create latest buttonView: Google Play Services version is outdated.");
        } else {
            if (TextUtils.isEmpty(build.getAllowedPaymentMethods())) {
                Log.e("PayButton", "Failed to create buttonView: allowedPaymentMethods cannot be empty.");
                return;
            }
            View zza = zzf.zza((Context) Preconditions.checkNotNull(getContext()), build);
            this.zzc = zza;
            if (zza == null) {
                Log.e("PayButton", "Failed to create buttonView");
            } else {
                addView(zza);
                this.zzc.setOnClickListener(this);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener = this.zza;
        if (onClickListener == null || view != this.zzc) {
            return;
        }
        onClickListener.onClick(this);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zza = onClickListener;
    }

    public PayButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PayButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ButtonOptions.Builder newBuilder = ButtonOptions.newBuilder();
        this.zzb = newBuilder;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PayButtonAttributes);
        int i2 = obtainStyledAttributes.getInt(R.styleable.PayButtonAttributes_buttonTheme, 1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PayButtonAttributes_cornerRadius, (int) TypedValue.applyDimension(1, 100.0f, Resources.getSystem().getDisplayMetrics()));
        ButtonOptions buttonOptions = ButtonOptions.this;
        buttonOptions.zzb = i2;
        buttonOptions.zzc = dimensionPixelSize;
        if (obtainStyledAttributes.hasValue(R.styleable.PayButtonAttributes_cornerRadius)) {
            ButtonOptions.this.zze = true;
        }
        obtainStyledAttributes.recycle();
        newBuilder.setButtonType(1);
        this.zzd = new zzf();
        if (isInEditMode()) {
            zza(this.zzb.build());
        }
    }
}
