package ch.datatrans.payment;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class H6 extends ObjectInputStream {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H6(ByteArrayInputStream inputStream) {
        super(inputStream);
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
    }

    @Override // java.io.ObjectInputStream
    public final ObjectStreamClass readClassDescriptor() {
        ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
        String name = readClassDescriptor.getName();
        if (name.equals("ch.datatrans.payment.PaymentMethodType")) {
            readClassDescriptor = ObjectStreamClass.lookup(J6.class);
        } else if (name.equals("ch.datatrans.payment.PaymentMethod")) {
            readClassDescriptor = ObjectStreamClass.lookup(I6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethod")) {
            readClassDescriptor = ObjectStreamClass.lookup(B6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethodCreditCard")) {
            readClassDescriptor = ObjectStreamClass.lookup(C6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethodELV")) {
            readClassDescriptor = ObjectStreamClass.lookup(D6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethodPayPal")) {
            readClassDescriptor = ObjectStreamClass.lookup(E6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethodPostFinanceCard")) {
            readClassDescriptor = ObjectStreamClass.lookup(F6.class);
        } else if (name.equals("ch.datatrans.payment.AliasPaymentMethodReka")) {
            readClassDescriptor = ObjectStreamClass.lookup(G6.class);
        }
        Intrinsics.checkNotNull(readClassDescriptor);
        return readClassDescriptor;
    }
}
