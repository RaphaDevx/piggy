package com.samsung.android.sdk.samsungpay.payment;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISPaymentCallback extends IInterface {

    public static class Default implements ISPaymentCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
        public void onAddressInfoChanged(PaymentInfo paymentInfo) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
        public void onApproveTransaction(PaymentInfo paymentInfo, String str) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
        public void onCardInfoChanged(CardInfo cardInfo) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
        public void onFailPayment(int i, String str) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
        public void onInitiateCompleted(Bundle bundle) throws RemoteException {
        }
    }

    void onAddressInfoChanged(PaymentInfo paymentInfo) throws RemoteException;

    void onApproveTransaction(PaymentInfo paymentInfo, String str) throws RemoteException;

    void onCardInfoChanged(CardInfo cardInfo) throws RemoteException;

    void onFailPayment(int i, String str) throws RemoteException;

    void onInitiateCompleted(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ISPaymentCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback";
        static final int TRANSACTION_onAddressInfoChanged = 3;
        static final int TRANSACTION_onApproveTransaction = 5;
        static final int TRANSACTION_onCardInfoChanged = 4;
        static final int TRANSACTION_onFailPayment = 2;
        static final int TRANSACTION_onInitiateCompleted = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISPaymentCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISPaymentCallback)) {
                return (ISPaymentCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onInitiateCompleted(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onFailPayment(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onAddressInfoChanged(parcel.readInt() != 0 ? PaymentInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onCardInfoChanged(parcel.readInt() != 0 ? CardInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onApproveTransaction(parcel.readInt() != 0 ? PaymentInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISPaymentCallback {
            public static ISPaymentCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
            public void onInitiateCompleted(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInitiateCompleted(bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
            public void onFailPayment(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFailPayment(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
            public void onAddressInfoChanged(PaymentInfo paymentInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (paymentInfo != null) {
                        obtain.writeInt(1);
                        paymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAddressInfoChanged(paymentInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
            public void onCardInfoChanged(CardInfo cardInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cardInfo != null) {
                        obtain.writeInt(1);
                        cardInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCardInfoChanged(cardInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback
            public void onApproveTransaction(PaymentInfo paymentInfo, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (paymentInfo != null) {
                        obtain.writeInt(1);
                        paymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onApproveTransaction(paymentInfo, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISPaymentCallback iSPaymentCallback) {
            if (Proxy.sDefaultImpl != null || iSPaymentCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSPaymentCallback;
            return true;
        }

        public static ISPaymentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
