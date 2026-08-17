package ch.datatrans.payment;

import ch.twint.payment.sdk.Twint;
import com.google.android.gms.wallet.Wallet;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView;
import com.samsung.android.sdk.samsungpay.v2.SamsungPay;
import io.card.payment.CardIOActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import lib.android.paypal.com.magnessdk.MagnesSDK;

/* loaded from: classes3.dex */
public final class M7 {
    public static final M7 a = new M7();
    public static final Lazy b = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.a());
        }
    });
    public static final Lazy c = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.f());
        }
    });
    public static final Lazy d = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.e());
        }
    });
    public static final Lazy e = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.b());
        }
    });
    public static final Lazy f = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.d());
        }
    });
    public static final Lazy g = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.M7$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(M7.c());
        }
    });

    public static final boolean a() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(CardIOActivity.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final boolean b() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Wallet.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final boolean c() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(KlarnaPaymentView.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final boolean d() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MagnesSDK.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final boolean e() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SamsungPay.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final boolean f() {
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Twint.class);
            String qualifiedName = orCreateKotlinClass.getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            Class.forName(qualifiedName, false, JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }
}
