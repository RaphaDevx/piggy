package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import ch.datatrans.payment.Sb;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u0000 \u00192\u00060\u0001j\u0002`\u0002:\u0001\u0019B1\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0016\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u001a"}, d2 = {"Lch/datatrans/payment/exception/TransactionException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "message", "", "cause", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "transactionId", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;)V", "a", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getPaymentMethodType", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "b", "Ljava/lang/String;", "getTransactionId", "()Ljava/lang/String;", "c", "getMobileToken", "mobileToken", "getLogString", "logString", "Companion", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class TransactionException extends Exception {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: a, reason: from kotlin metadata */
    public final PaymentMethodType paymentMethodType;

    /* renamed from: b, reason: from kotlin metadata */
    public final String transactionId;

    /* renamed from: c, reason: from kotlin metadata */
    public final String mobileToken;

    public TransactionException(String str, Throwable th, PaymentMethodType paymentMethodType, String str2) {
        super(str, INSTANCE.clearInfoIfNeeded$lib_release(th, paymentMethodType));
        this.paymentMethodType = paymentMethodType;
        this.transactionId = str2;
        MutableLiveData mutableLiveData = Sb.a;
        this.mobileToken = Sb.e;
    }

    public final String getLogString() {
        return INSTANCE.getLogString$lib_release(getCause());
    }

    public final String getMobileToken() {
        return this.mobileToken;
    }

    public PaymentMethodType getPaymentMethodType() {
        return this.paymentMethodType;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J%\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lch/datatrans/payment/exception/TransactionException$Companion;", "", "", "cause", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "clearInfoIfNeeded$lib_release", "(Ljava/lang/Throwable;Lch/datatrans/payment/paymentmethods/PaymentMethodType;)Ljava/lang/Throwable;", "clearInfoIfNeeded", "", "getLogString$lib_release", "(Ljava/lang/Throwable;)Ljava/lang/String;", "getLogString", "", "MAX_STACK_ELEMENTS", "I", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final CharSequence a(Throwable t) {
            Intrinsics.checkNotNullParameter(t, "t");
            TransactionException.INSTANCE.getClass();
            return b(t);
        }

        public static String b(Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append(th.getClass().getSimpleName());
            sb.append("(\"");
            sb.append(th.getMessage());
            sb.append("\", ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            sb.append(CollectionsKt.joinToString$default(ArraysKt.take(stackTrace, 2), "|", null, null, 0, null, new Function1() { // from class: ch.datatrans.payment.exception.TransactionException$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransactionException.Companion.a((StackTraceElement) obj);
                }
            }, 30, null));
            sb.append(")");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }

        public final Throwable clearInfoIfNeeded$lib_release(Throwable cause, PaymentMethodType paymentMethodType) {
            if ((cause instanceof BackendException) && paymentMethodType != PaymentMethodType.CEMBRA_PAY) {
                ((BackendException) cause).getInfo().clear();
            }
            return cause;
        }

        public final String getLogString$lib_release(Throwable cause) {
            if (cause == null) {
                return null;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (cause != null && linkedHashSet.add(cause)) {
                cause = cause.getCause();
            }
            return CollectionsKt.joinToString$default(linkedHashSet, " > ", null, null, 0, null, new Function1() { // from class: ch.datatrans.payment.exception.TransactionException$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TransactionException.Companion.a((Throwable) obj);
                }
            }, 30, null);
        }

        public static final CharSequence a(StackTraceElement element) {
            Intrinsics.checkNotNullParameter(element, "element");
            return element.getClassName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + element.getMethodName() + ':' + element.getLineNumber();
        }
    }
}
