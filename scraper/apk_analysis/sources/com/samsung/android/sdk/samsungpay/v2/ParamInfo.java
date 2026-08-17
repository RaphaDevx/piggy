package com.samsung.android.sdk.samsungpay.v2;

import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface ParamInfo {
    boolean checkValue() default false;

    SpaySdk.SdkApiLevel since() default SpaySdk.SdkApiLevel.LEVEL_1_4;
}
