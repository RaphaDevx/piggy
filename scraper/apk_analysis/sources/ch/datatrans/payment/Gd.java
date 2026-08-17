package ch.datatrans.payment;

import androidx.activity.ComponentActivity;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionRegistry;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class Gd {
    public static final Function0 a(final Function0 activitySupplier, Class modelClass) {
        Intrinsics.checkNotNullParameter(activitySupplier, "activitySupplier");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(Jc.class)) {
            return new Function0() { // from class: ch.datatrans.payment.Gd$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Gd.a(Function0.this);
                }
            };
        }
        return null;
    }

    public static final Kc a(Function0 function0) {
        Transaction transaction = TransactionRegistry.INSTANCE.getTransaction();
        Intrinsics.checkNotNull(transaction);
        boolean isTesting = transaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().getIsTesting();
        ComponentActivity context = (ComponentActivity) function0.invoke();
        N9 n9 = new N9(context, isTesting);
        Intrinsics.checkNotNullParameter(context, "context");
        I3.a(context).isEmpty();
        return new Kc(transaction, n9, !r3.isEmpty());
    }
}
